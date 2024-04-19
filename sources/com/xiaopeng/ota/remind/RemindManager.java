package com.xiaopeng.ota.remind;

import android.content.Context;
import com.xiaopeng.fota.sdk.UpgradeUtils;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.helper.ActivityHelper;
import com.xiaopeng.ota.helper.CarServiceHelper;
import com.xiaopeng.ota.presenter.update.UpgradePresenter;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.sdk.common.util.PreferencesUtils;
import com.xiaopeng.ota.sdk.common.util.TimeUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.xuimanager.userscenario.UserScenarioManager;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class RemindManager {
    private static final String KEY_LAST_POWER_OFF_TIME = "last_power_off_time";
    private static final String KEY_LAST_POWER_ON_REMIND_TIME = "last_power_on_remind_time";
    private static final String KEY_MAX_FAILED_AND_REMIND_TIMES = "max_failed_retry_remind_times";
    private static final int STATE_ACTIVE_INTERVAL_MIN = 5000;
    private static final String TAG = "RemindManager";
    private boolean mActive;
    private long mActiveTime;
    private Context mContext;
    private DrivingRouteManager mDrivingRouteManager;
    private long mLastPowerRemindTime;
    private long mLastRemindTime;

    public RemindManager(Context context) {
        this.mContext = context;
        this.mDrivingRouteManager = new DrivingRouteManager(context);
    }

    public void init() {
        this.mDrivingRouteManager.init();
    }

    public synchronized void updateRemindTime() {
        this.mLastRemindTime = System.currentTimeMillis();
    }

    public synchronized boolean activate(boolean z) {
        LogUtils.d(TAG, "activate(%b)", Boolean.valueOf(z));
        this.mActive = z;
        this.mActiveTime = System.currentTimeMillis();
        this.mDrivingRouteManager.reset();
        return false;
    }

    public synchronized void updateRemindDate() {
        PreferencesUtils.putLong(this.mContext, KEY_LAST_POWER_ON_REMIND_TIME, System.currentTimeMillis());
        this.mLastRemindTime = System.currentTimeMillis();
    }

    public synchronized void checkNewByPowerOn() {
        LogUtils.d(TAG, "checkNewByPowerOn");
        try {
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Power on and new campaign remind failed");
        }
        if (!checkRemindTime()) {
            LogUtils.i(TAG, "Not after remind time line");
            return;
        }
        long dayMillis = RemindConfiguration.dayMillis();
        if (System.currentTimeMillis() / dayMillis <= PreferencesUtils.getLong(this.mContext, KEY_LAST_POWER_ON_REMIND_TIME, 0L) / dayMillis) {
            LogUtils.i(TAG, "Today had remind already");
            return;
        }
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        int OtaGetCampaignStatus = UpgradeUtils.OtaGetCampaignStatus();
        if (activeCampaign != null && OtaGetCampaignStatus == 7) {
            VehicleLocation matchConstantLocation = this.mDrivingRouteManager.matchConstantLocation();
            if (matchConstantLocation != null) {
                LogUtils.i(TAG, "[%d] Power on and match constant location found", Long.valueOf(activeCampaign.getCampaignId()));
                return;
            } else if (!checkParking(10)) {
                LogUtils.i(TAG, "[%d] No on P gear", Long.valueOf(activeCampaign.getCampaignId()));
                return;
            } else {
                boolean remindByRoute = remindByRoute(activeCampaign, new RemindMode(0, matchConstantLocation));
                Object[] objArr = new Object[4];
                objArr[0] = Long.valueOf(activeCampaign.getCampaignId());
                objArr[1] = remindByRoute ? UserScenarioManager.RET_SUCCESS : "failed";
                objArr[2] = Long.valueOf(System.currentTimeMillis());
                objArr[3] = Long.valueOf(this.mLastRemindTime);
                LogUtils.d(TAG, "[%d] Power on and new campaign remind %s (now=%d,last=%d)", objArr);
                return;
            }
        }
        LogUtils.i(TAG, "Not found active campaign (status=%d)", Integer.valueOf(OtaGetCampaignStatus));
    }

    private boolean checkParking(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (CarServiceHelper.getGearLever() == 4) {
                return true;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(1000L);
            } catch (InterruptedException unused) {
            }
        }
        return false;
    }

    private boolean checkRemindTime() {
        String remindTimeLine = RemindConfiguration.remindTimeLine();
        Calendar calendar = Calendar.getInstance();
        long timeInMillis = calendar.getTimeInMillis();
        calendar.set(11, TimeUtils.getHour(remindTimeLine));
        calendar.set(12, TimeUtils.getMinute(remindTimeLine));
        calendar.set(13, TimeUtils.getSecond(remindTimeLine));
        calendar.set(14, 0);
        return timeInMillis >= calendar.getTimeInMillis();
    }

    public synchronized void checkNewByGear(int i, float f, boolean z) {
        LogUtils.d(TAG, "checkNewByGear, gear=%d/mileage=%f", Integer.valueOf(i), Float.valueOf(f));
        try {
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Gear changed and new campaign remind failed");
        }
        if (this.mActive && System.currentTimeMillis() - this.mActiveTime >= 5000) {
            if (!this.mDrivingRouteManager.isParking(i, f)) {
                LogUtils.d(TAG, "Car maybe not parking (gear=%d, mileage=%f)", Integer.valueOf(i), Float.valueOf(f));
                return;
            } else if (!z) {
                LogUtils.d(TAG, "Not in safe location");
                return;
            } else {
                Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
                int OtaGetCampaignStatus = UpgradeUtils.OtaGetCampaignStatus();
                if (activeCampaign != null && OtaGetCampaignStatus == 7) {
                    VehicleLocation matchConstantLocation = this.mDrivingRouteManager.matchConstantLocation();
                    if (matchConstantLocation == null) {
                        LogUtils.d(TAG, "[%d] Gear changed and match constant location not found", Long.valueOf(activeCampaign.getCampaignId()));
                        return;
                    }
                    boolean remindByRoute = remindByRoute(activeCampaign, new RemindMode(3, matchConstantLocation));
                    Object[] objArr = new Object[4];
                    objArr[0] = Long.valueOf(activeCampaign.getCampaignId());
                    objArr[1] = remindByRoute ? UserScenarioManager.RET_SUCCESS : "failed";
                    objArr[2] = Long.valueOf(System.currentTimeMillis());
                    objArr[3] = Long.valueOf(this.mLastRemindTime);
                    LogUtils.d(TAG, "[%d] Gear changed and new campaign remind %s (now=%d,last=%d)", objArr);
                    return;
                }
                LogUtils.d(TAG, "Not found running campaign (status=%d)", Integer.valueOf(OtaGetCampaignStatus));
                return;
            }
        }
        LogUtils.d(TAG, "Gear changed ignore (active=%b,now=%d,up=%d,min=%d)", Boolean.valueOf(this.mActive), Long.valueOf(System.currentTimeMillis()), Long.valueOf(this.mActiveTime), 5000);
    }

    public synchronized void checkNewByBelt(boolean z) {
        try {
            LogUtils.d(TAG, "Belt changed (buckleUp=%b)", Boolean.valueOf(z));
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Belt changed handle failed");
        }
        if (!z && this.mActive && System.currentTimeMillis() - this.mActiveTime >= 5000) {
            Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
            int OtaGetCampaignStatus = UpgradeUtils.OtaGetCampaignStatus();
            if (activeCampaign != null && OtaGetCampaignStatus == 7) {
                VehicleLocation matchConstantLocation = this.mDrivingRouteManager.matchConstantLocation();
                if (matchConstantLocation == null) {
                    LogUtils.d(TAG, "[%d] Belt changed and match constant location not found", Long.valueOf(activeCampaign.getCampaignId()));
                    return;
                } else if (!checkParking(3)) {
                    LogUtils.d(TAG, "[%d] No on P gear", Long.valueOf(activeCampaign.getCampaignId()));
                    return;
                } else {
                    boolean remindByRoute = remindByRoute(activeCampaign, new RemindMode(4, matchConstantLocation));
                    Object[] objArr = new Object[4];
                    objArr[0] = Long.valueOf(activeCampaign.getCampaignId());
                    objArr[1] = remindByRoute ? UserScenarioManager.RET_SUCCESS : "failed";
                    objArr[2] = Long.valueOf(System.currentTimeMillis());
                    objArr[3] = Long.valueOf(this.mLastRemindTime);
                    LogUtils.d(TAG, "[%d] Belt changed and campaign remind %s (now=%d,last=%d)", objArr);
                    return;
                }
            }
            LogUtils.d(TAG, "Not found running campaign (status=%d)", Integer.valueOf(OtaGetCampaignStatus));
            return;
        }
        LogUtils.d(TAG, "Belt changed ignore (active=%b,now=%d,up=%d,min=%d)", Boolean.valueOf(this.mActive), Long.valueOf(System.currentTimeMillis()), Long.valueOf(this.mActiveTime), 5000);
    }

    private boolean remindByRoute(Campaign campaign, RemindMode remindMode) {
        long currentTimeMillis = System.currentTimeMillis();
        long remindIntervalMin = RemindConfiguration.remindIntervalMin();
        if (currentTimeMillis - this.mLastRemindTime <= remindIntervalMin) {
            LogUtils.d(TAG, "Remind too often interval:%d", Long.valueOf(remindIntervalMin));
            return false;
        }
        ActivityHelper.showCampaign(remindMode);
        this.mLastRemindTime = System.currentTimeMillis();
        return true;
    }

    public boolean isCampaignOutOfRetryRemindLimit() {
        int i = OTAManager.getConfigManager().getInt(KEY_MAX_FAILED_AND_REMIND_TIMES, 2);
        int currentCampaignRetryTimes = UpgradePresenter.getCurrentCampaignRetryTimes();
        if (currentCampaignRetryTimes > i) {
            LogUtils.i(TAG, "Campaign out of retry limit (retry=%d,limit=%d)", Integer.valueOf(currentCampaignRetryTimes), Integer.valueOf(i));
            return true;
        }
        return false;
    }

    public DrivingRouteManager getDrivingRouteManager() {
        return this.mDrivingRouteManager;
    }
}
