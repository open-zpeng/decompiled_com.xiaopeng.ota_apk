package com.xiaopeng.ota.sdk.common.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.util.Log;
import android.util.Pair;
/* loaded from: classes2.dex */
public class AppUtils {
    private static final String TAG = "AppUtils";

    public static String getVersionName(Context context) {
        try {
            return (String) getVersionInfo(context).first;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Get version name occurs exception", e);
            return "";
        }
    }

    public static int getVersionCode(Context context) {
        try {
            return ((Integer) getVersionInfo(context).second).intValue();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Get version code occurs exception", e);
            return -1;
        }
    }

    public static Pair<String, Integer> getVersionInfo(Context context) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        return new Pair<>(packageInfo.versionName, Integer.valueOf(packageInfo.versionCode));
    }

    public static Pair<String, Integer> getVersionInfo(Context context, String str) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        return new Pair<>(packageInfo.versionName, Integer.valueOf(packageInfo.versionCode));
    }

    public static PackageInfo getArchivePackageInfo(Context context, String str) {
        return context.getPackageManager().getPackageArchiveInfo(str, 1);
    }

    public static String getArchivePackageName(Context context, String str) {
        PackageInfo archivePackageInfo = getArchivePackageInfo(context, str);
        if (archivePackageInfo != null) {
            return archivePackageInfo.packageName;
        }
        return null;
    }

    public static int getPid() {
        return Process.myPid();
    }

    public static String getAppNameByPID(Context context, int i) {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == i) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }
}
