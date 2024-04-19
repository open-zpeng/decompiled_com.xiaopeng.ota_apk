package com.xiaopeng.ota.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import com.xiaopeng.xuimanager.userscenario.UserScenarioManager;
import java.util.List;
/* loaded from: classes2.dex */
public class ActivityUtils {
    public static boolean isForeground(Context context, Class cls) {
        return isForeground(context, cls.getName());
    }

    public static boolean isForeground(Context context, String str) {
        ActivityManager activityManager;
        List<ActivityManager.RunningTaskInfo> runningTasks;
        if (context == null || TextUtils.isEmpty(str) || (activityManager = (ActivityManager) context.getSystemService(UserScenarioManager.SOURCE_ACTIVITY)) == null || (runningTasks = activityManager.getRunningTasks(1)) == null || runningTasks.size() <= 0) {
            return false;
        }
        return str.equals(runningTasks.get(0).topActivity.getClassName());
    }
}
