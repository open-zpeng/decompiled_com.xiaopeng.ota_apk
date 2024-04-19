package com.xiaopeng.ota.helper;

import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaopeng.lib.apirouter.ApiRouter;
import com.xiaopeng.ota.bean.LocationInfo;
import com.xiaopeng.ota.utils.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
/* loaded from: classes2.dex */
public class RoadHelper {
    private static final String METHOD_GET_LOCATION_PATH = "getCurrentLocationInfo";
    private static final int ROAD_CLASS_COMMON = 9;
    private static final int ROAD_CLASS_INVALID = -1;
    private static final String SERVICE_NAME = "com.xiaopeng.montecarlo.GuideInfoService";
    private static final String TAG = "RoadHelper";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class RoadHelperHolder {
        static final RoadHelper INSTANCE = new RoadHelper();

        private RoadHelperHolder() {
        }
    }

    public static RoadHelper getInstance() {
        return RoadHelperHolder.INSTANCE;
    }

    public boolean onDangerousRoad() {
        LocationInfo currentLocationInfo = getCurrentLocationInfo();
        if (currentLocationInfo == null) {
            return true;
        }
        return currentLocationInfo.getRoadClass() != -1 && currentLocationInfo.getRoadClass() < 9;
    }

    public LocationInfo getCurrentLocationInfo() {
        Uri.Builder builder = new Uri.Builder();
        builder.authority(SERVICE_NAME).path(METHOD_GET_LOCATION_PATH);
        try {
            String str = (String) ApiRouter.route(builder.build());
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            LogUtils.d(TAG, "getCurrentLocationInfo, result:" + str);
            return (LocationInfo) JsonUtils.fromJson(str, (Class<Object>) LocationInfo.class);
        } catch (RemoteException e) {
            LogUtils.w(TAG, e, "Get remote locationInfo fail");
            return null;
        }
    }
}
