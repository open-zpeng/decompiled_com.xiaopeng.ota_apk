package com.xiaopeng.ota.remind;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Pair;
import com.xiaopeng.ota.bean.LocationInfo;
import com.xiaopeng.ota.helper.RoadHelper;
import com.xiaopeng.ota.presenter.db.AbstractRepository;
import com.xiaopeng.ota.sdk.common.util.IoUtils;
import com.xiaopeng.ota.sdk.common.util.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/* loaded from: classes2.dex */
public class DrivingRouteManager extends AbstractRepository {
    private static final long A_DAY_SECONDS = 86400;
    private static final String TAG = "DrivingRouteManager";
    private long mActiveTime;
    private int mCurrentGearLevel;
    private float mCurrentMileage;
    private float mLastChangedRMileage;
    private long mLastChangedRTime;
    private List<DrivingRoute> mRoutes;

    public DrivingRouteManager(Context context) {
        super(context);
        this.mActiveTime = System.currentTimeMillis();
    }

    @Override // com.xiaopeng.ota.presenter.db.AbstractRepository
    public void init() {
        super.init();
        this.mRoutes = queryUnsafe();
    }

    public synchronized boolean isParking(int i, float f) {
        this.mCurrentGearLevel = i;
        this.mCurrentMileage = f;
        if (i == 3) {
            this.mLastChangedRTime = System.currentTimeMillis();
            this.mLastChangedRMileage = f;
        } else if (i == 4) {
            long gearChangedTimeIntervalMax = RemindConfiguration.gearChangedTimeIntervalMax();
            float gearChangedMileageIntervalMax = RemindConfiguration.gearChangedMileageIntervalMax();
            LogUtils.d(TAG, "Vehicle parking check(rtime=%d,ptime=%d,tinterval=%d,pmileage=%f,rmileage=%f,minterval=%f)", Long.valueOf(this.mLastChangedRTime), Long.valueOf(System.currentTimeMillis()), Long.valueOf(gearChangedTimeIntervalMax), Float.valueOf(f), Float.valueOf(this.mLastChangedRMileage), Float.valueOf(gearChangedMileageIntervalMax));
            if (System.currentTimeMillis() - this.mLastChangedRTime < gearChangedTimeIntervalMax && f - this.mLastChangedRMileage < gearChangedMileageIntervalMax) {
                LogUtils.d(TAG, "Vehicle maybe has parked");
                return true;
            }
        }
        return false;
    }

    public synchronized DrivingRoutePoint matchLastRouteByGear(int i, float f) {
        if (isParking(i, f)) {
            return matchLastRoute();
        }
        return null;
    }

    public synchronized DrivingRoutePoint matchLastRouteByBelt(boolean z) {
        LogUtils.d(TAG, "Vehicle belt state (gear=%d,buckle=%b)", Integer.valueOf(this.mCurrentGearLevel), Boolean.valueOf(z));
        if (!z && this.mCurrentGearLevel == 4) {
            return matchLastRoute();
        }
        return null;
    }

    public synchronized long getDrivingRoutesUpdateTime() {
        if (this.mRoutes != null && !this.mRoutes.isEmpty()) {
            return this.mRoutes.get(0).getUpdateTime().longValue();
        }
        return 0L;
    }

    public synchronized boolean isEmpty() {
        boolean z;
        if (this.mRoutes != null) {
            z = this.mRoutes.isEmpty();
        }
        return z;
    }

    public synchronized void reset() {
        this.mLastChangedRTime = 0L;
        this.mLastChangedRMileage = 0.0f;
        this.mActiveTime = System.currentTimeMillis();
    }

    public synchronized VehicleLocation matchConstantLocation() {
        LocationInfo currentLocationInfo = RoadHelper.getInstance().getCurrentLocationInfo();
        if (currentLocationInfo == null) {
            LogUtils.d(TAG, "Match constant location failed, empty location");
            return null;
        }
        if (this.mRoutes != null && !this.mRoutes.isEmpty()) {
            int hourAndMinuteTime = hourAndMinuteTime(System.currentTimeMillis());
            for (DrivingRoute drivingRoute : this.mRoutes) {
                LogUtils.d(TAG, "Start match constant(id=%d, locations=%d)", drivingRoute.getId(), Integer.valueOf(drivingRoute.getConstantLocation().size()));
                Pair<Double, Double> pair = new Pair<>(Double.valueOf(currentLocationInfo.getMatchPosLat()), Double.valueOf(currentLocationInfo.getMatchPosLon()));
                for (VehicleLocation vehicleLocation : drivingRoute.getConstantLocation()) {
                    if (matchLocation(vehicleLocation, pair)) {
                        LogUtils.d(TAG, "Match location");
                        if (matchTime(vehicleLocation, hourAndMinuteTime)) {
                            LogUtils.d(TAG, "Match time");
                            return vehicleLocation;
                        }
                        LogUtils.d(TAG, "Time not match");
                    }
                }
            }
            return null;
        }
        LogUtils.d(TAG, "Match constant location failed, empty routes");
        return null;
    }

    private boolean matchLocation(VehicleLocation vehicleLocation, Pair<Double, Double> pair) {
        double distance = LocationHelper.distance(vehicleLocation.getLatFloat(), vehicleLocation.getLngFloat(), ((Double) pair.first).doubleValue(), ((Double) pair.second).doubleValue());
        if (distance > vehicleLocation.getRadius().intValue()) {
            LogUtils.d(TAG, "Location not match (lat1=%s,lon1=%s,lat2=%f,lon2=%f,d=%f,r=%d)", vehicleLocation.getLat(), vehicleLocation.getLng(), pair.first, pair.second, Double.valueOf(distance), vehicleLocation.getRadius());
            return false;
        }
        LogUtils.d(TAG, "Location matched (lat1=%s,lon1=%s,lat2=%f,lon2=%f,d=%f,r=%d)", vehicleLocation.getLat(), vehicleLocation.getLng(), pair.first, pair.second, Double.valueOf(distance), vehicleLocation.getRadius());
        return true;
    }

    private boolean matchTime(VehicleLocation vehicleLocation, int i) {
        try {
            int intValue = Integer.valueOf(vehicleLocation.getAvgOnTime()).intValue();
            int intValue2 = Integer.valueOf(vehicleLocation.getAvgOffTime()).intValue();
            if (vehicleLocation.getAvgUseTime().longValue() >= A_DAY_SECONDS) {
                return true;
            }
            return intValue <= intValue2 ? i >= intValue && i <= intValue2 : i < intValue2 || i > intValue;
        } catch (Exception e) {
            LogUtils.e(TAG, e, "ParseInt fail");
            return false;
        }
    }

    private DrivingRoutePoint matchLastRoute() {
        String str;
        LocationInfo currentLocationInfo = RoadHelper.getInstance().getCurrentLocationInfo();
        DrivingRoutePoint drivingRoutePoint = null;
        String str2 = TAG;
        if (currentLocationInfo == null) {
            LogUtils.d(TAG, "Match route failed, empty location");
            return null;
        }
        List<DrivingRoute> list = this.mRoutes;
        if (list == null || list.isEmpty()) {
            LogUtils.d(TAG, "Match route failed, empty routes");
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int hourAndMinuteTime = hourAndMinuteTime(currentTimeMillis);
        for (DrivingRoute drivingRoute : this.mRoutes) {
            char c = 0;
            LogUtils.d(str2, "Start match route (id=%d, points=%d)", drivingRoute.getId(), Integer.valueOf(drivingRoute.getLastRoute().getPoints().size()));
            for (DrivingRoutePoint drivingRoutePoint2 : drivingRoute.getLastRoute().getPoints()) {
                int intValue = Integer.valueOf(drivingRoutePoint2.getMinOnTime()).intValue();
                int intValue2 = Integer.valueOf(drivingRoutePoint2.getMaxOnTime()).intValue();
                if (hourAndMinuteTime < intValue || hourAndMinuteTime > intValue2) {
                    String str3 = str2;
                    c = 0;
                    LogUtils.d(str3, "Match arrived time failed (lat=%s,lon=%s,curr=%04d,min=%04d,max=%04d)", drivingRoutePoint2.getLat(), drivingRoutePoint2.getLng(), Integer.valueOf(hourAndMinuteTime), Integer.valueOf(intValue), Integer.valueOf(intValue2));
                    str2 = str3;
                    drivingRoutePoint = null;
                } else {
                    String str4 = str2;
                    if (currentTimeMillis - this.mActiveTime < drivingRoutePoint2.getMinUseTime().intValue()) {
                        Object[] objArr = new Object[5];
                        objArr[c] = drivingRoutePoint2.getLat();
                        objArr[1] = drivingRoutePoint2.getLng();
                        objArr[2] = Long.valueOf(currentTimeMillis);
                        objArr[3] = Long.valueOf(this.mActiveTime);
                        objArr[4] = drivingRoutePoint2.getMinUseTime();
                        str = str4;
                        LogUtils.d(str, "Match use time failed (lat=%s,lon=%s,now=%d,active=%d,min=%d)", objArr);
                    } else {
                        str = str4;
                        if (this.mCurrentMileage < drivingRoutePoint2.getMinOnDistanceKm()) {
                            Object[] objArr2 = new Object[4];
                            objArr2[c] = drivingRoutePoint2.getLat();
                            objArr2[1] = drivingRoutePoint2.getLng();
                            objArr2[2] = Float.valueOf(this.mCurrentMileage);
                            objArr2[3] = Float.valueOf(drivingRoutePoint2.getMinOnDistanceKm());
                            LogUtils.d(str, "Match distance failed (lat=%s,lon=%s,mileage=%f,min=%f)", objArr2);
                        } else {
                            double distance = LocationHelper.distance(drivingRoutePoint2.getLatFloat(), drivingRoutePoint2.getLngFloat(), currentLocationInfo.getMatchPosLat(), currentLocationInfo.getMatchPosLon());
                            if (distance > drivingRoutePoint2.getRadius().intValue()) {
                                LogUtils.d(str, "Match scope failed (lat1=%s,lon1=%s,lat2=%f,lon2=%f,d=%f,r=%d)", drivingRoutePoint2.getLat(), drivingRoutePoint2.getLng(), Double.valueOf(currentLocationInfo.getMatchPosLat()), Double.valueOf(currentLocationInfo.getMatchPosLon()), Double.valueOf(distance), drivingRoutePoint2.getRadius());
                                str2 = str;
                                drivingRoutePoint = null;
                                c = 0;
                            } else {
                                LogUtils.d(str, "Match route success (lat=%s,lon=%s,lat2=%f,lon2=%f,d=%f,r=%d)", drivingRoutePoint2.getLat(), drivingRoutePoint2.getLng(), Double.valueOf(currentLocationInfo.getMatchPosLat()), Double.valueOf(currentLocationInfo.getMatchPosLon()), Double.valueOf(distance), drivingRoutePoint2.getRadius());
                                reset();
                                return drivingRoutePoint2;
                            }
                        }
                    }
                    str2 = str;
                    drivingRoutePoint = null;
                }
            }
        }
        return drivingRoutePoint;
    }

    private int hourAndMinuteTime(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return (calendar.get(11) * 100) + calendar.get(12);
    }

    public synchronized void save(final DrivingRoute drivingRoute) {
        transactionExecute(new Runnable() { // from class: com.xiaopeng.ota.remind.DrivingRouteManager.1
            @Override // java.lang.Runnable
            public void run() {
                DrivingRouteManager.this.deleteAllUnsafe();
                drivingRoute.setCreateTime(Long.valueOf(System.currentTimeMillis()));
                drivingRoute.setUpdateTime(Long.valueOf(System.currentTimeMillis()));
                DrivingRoute drivingRoute2 = drivingRoute;
                drivingRoute2.setId(Integer.valueOf((int) DrivingRouteManager.this.insertUnsafe(drivingRoute2)));
                DrivingRouteManager.this.mRoutes.clear();
                DrivingRouteManager.this.mRoutes.add(drivingRoute);
            }
        });
    }

    private List<DrivingRoute> queryUnsafe() {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = getDatabase().query(DrivingRoute.TABLE_NAME, null, whereWrapper(null, new Object[0]), null, null, null, null, null);
            while (cursor.moveToNext()) {
                Integer valueOf = Integer.valueOf(cursor.getInt(cursor.getColumnIndex("_id")));
                Integer.valueOf(cursor.getInt(cursor.getColumnIndex("version")));
                String string = cursor.getString(cursor.getColumnIndex("data"));
                try {
                    DrivingRoute drivingRoute = (DrivingRoute) JsonUtils.fromJson(string, (Class<Object>) DrivingRoute.class);
                    drivingRoute.setId(valueOf);
                    arrayList.add(drivingRoute);
                } catch (Exception e) {
                    LogUtils.e(TAG, e, "Load convert driving routes from json failed (data=%s)", string);
                    getDatabase().delete(DrivingRoute.TABLE_NAME, whereWrapper("%s=?", "_id"), whereArgsWrapper(valueOf));
                }
            }
            return arrayList;
        } finally {
            IoUtils.close(cursor);
        }
    }

    private int updateUnsafe(DrivingRoute drivingRoute) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("version", (Integer) 0);
        contentValues.put("data", JsonUtils.toJson(drivingRoute));
        contentValues.put("data", JsonUtils.toJson(drivingRoute));
        contentValues.put("update_time", Long.valueOf(System.currentTimeMillis()));
        int update = getDatabase().update(DrivingRoute.TABLE_NAME, contentValues, whereWrapper("%s=?", "_id"), whereArgsWrapper(drivingRoute.getId()));
        LogUtils.d(TAG, "Update driving route constantLocation(points=%d,rows=%d)", Integer.valueOf(drivingRoute.getConstantLocation().size()), Integer.valueOf(update));
        LogUtils.d(TAG, "Update driving route lastRoute(points=%d, rows=%d)", Integer.valueOf(drivingRoute.getLastRoute().getPoints().size()), Integer.valueOf(update));
        return update;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long insertUnsafe(DrivingRoute drivingRoute) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("version", (Integer) 0);
        contentValues.put("data", JsonUtils.toJson(drivingRoute));
        contentValues.put("update_time", drivingRoute.getCreateTime());
        contentValues.put("create_time", drivingRoute.getUpdateTime());
        long insert = getDatabase().insert(DrivingRoute.TABLE_NAME, null, contentValues);
        LogUtils.d(TAG, "Insert driving route constantLocation(points=%d, rowId=%d)", Integer.valueOf(drivingRoute.getConstantLocation().size()), Long.valueOf(insert));
        LogUtils.d(TAG, "Insert driving route lastRoute(points=%d, rowId=%d)", Integer.valueOf(drivingRoute.getLastRoute().getPoints().size()), Long.valueOf(insert));
        return insert;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int deleteAllUnsafe() {
        return getDatabase().delete(DrivingRoute.TABLE_NAME, whereWrapper(null, new Object[0]), null);
    }
}
