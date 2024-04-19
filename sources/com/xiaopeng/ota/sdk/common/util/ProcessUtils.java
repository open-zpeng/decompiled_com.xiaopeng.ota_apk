package com.xiaopeng.ota.sdk.common.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.xiaopeng.xuimanager.userscenario.UserScenarioManager;
/* loaded from: classes2.dex */
public class ProcessUtils {
    public static boolean isProcessRunning(Context context, String str) {
        ActivityManager activityManager;
        if (TextUtils.isEmpty(str) || (activityManager = (ActivityManager) context.getSystemService(UserScenarioManager.SOURCE_ACTIVITY)) == null) {
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
        if (TextUtils.isEmpty(str) || (activityManager = (ActivityManager) context.getSystemService(UserScenarioManager.SOURCE_ACTIVITY)) == null) {
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
