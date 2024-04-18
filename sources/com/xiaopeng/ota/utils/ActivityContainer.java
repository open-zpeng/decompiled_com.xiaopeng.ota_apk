package com.xiaopeng.ota.utils;

import android.app.Activity;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class ActivityContainer {
    private List<Activity> activityList = new ArrayList();

    /* loaded from: classes2.dex */
    private static class ActivityContainerHolder {
        static final ActivityContainer INSTANCE = new ActivityContainer();

        private ActivityContainerHolder() {
        }
    }

    public static ActivityContainer getInstance() {
        return ActivityContainerHolder.INSTANCE;
    }

    public void addActivity(Activity activity) {
        if (activity != null) {
            this.activityList.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (activity != null) {
            this.activityList.remove(activity);
        }
    }

    public boolean isAnyActivityForeground(Context context) {
        if (!this.activityList.isEmpty()) {
            for (Activity activity : this.activityList) {
                if (activity != null && ActivityUtils.isForeground(context, activity.getClass())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void finishAll() {
        if (this.activityList.isEmpty()) {
            return;
        }
        for (Activity activity : this.activityList) {
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
        this.activityList.clear();
    }
}
