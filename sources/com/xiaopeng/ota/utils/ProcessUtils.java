package com.xiaopeng.ota.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
/* loaded from: classes2.dex */
public class ProcessUtils {
    public static boolean isProcessRunning(Context context, String str) {
        ActivityManager activityManager;
        if (TextUtils.isEmpty(str) || (activityManager = (ActivityManager) context.getSystemService("activity")) == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            if (str.equals(runningAppProcessInfo.processName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCurrentProcess(Context context, String str) {
        ActivityManager activityManager;
        if (TextUtils.isEmpty(str) || (activityManager = (ActivityManager) context.getSystemService("activity")) == null) {
            return false;
        }
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return str.equals(runningAppProcessInfo.processName);
            }
        }
        return false;
    }
}
