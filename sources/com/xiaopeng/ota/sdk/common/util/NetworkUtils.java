package com.xiaopeng.ota.sdk.common.util;

import android.content.Context;
import com.xiaopeng.lib.utils.NetUtils;
/* loaded from: classes2.dex */
public class NetworkUtils {
    public static boolean isNetworkAvailable(Context context) {
        return NetUtils.isNetworkAvailable(context);
    }

    public static boolean isWifiEnabled(Context context) {
        return NetUtils.isWifiEnabled(context);
    }

    public static String getNetworkType(Context context) {
        int networkType = NetUtils.getNetworkType(context);
        return networkType != 0 ? networkType != 1 ? networkType != 2 ? networkType != 3 ? networkType != 10 ? "Others" : "WIFI" : "4G" : "3G" : "2G" : "none";
    }

    public static boolean isNetworkConnected(Context context) {
        return NetUtils.checkNetState(context);
    }

    public static String getMacAddress(Context context) {
        return NetUtils.getMacAddress(context);
    }
}
