package com.xiaopeng.ota.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import com.xiaopeng.ota.Config;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.activity.FragmentActivity;
import com.xiaopeng.ota.bean.Action;
import com.xiaopeng.ota.bean.ActivityEvent;
import com.xiaopeng.ota.remind.RemindMode;
import com.xiaopeng.ota.sdk.common.util.Dictionary;
import com.xiaopeng.ota.system.OtaService;
import com.xiaopeng.ota.utils.ActivityUtils;
import com.xiaopeng.ota.utils.LogUtils;
import java.util.HashMap;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes2.dex */
public class ActivityHelper {
    private static final String TAG = "ActivityHelper";

    public static void startActivity(Context context, Class cls) {
        startActivity(context, cls, null);
    }

    public static void startActivity(Context context, Class cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        intent.setFlags(268435456);
        if (bundle != null) {
            intent.putExtra(Config.EXTRA_PARAM, bundle);
        }
        context.startActivityAsUser(intent, UserHandle.CURRENT);
    }

    public static void finishActivity(Class cls) {
        finishActivity(cls, null);
    }

    public static void finishActivity(Class cls, Object obj) {
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.setClazz(cls);
        activityEvent.setAction(Action.CLOSE);
        if (obj != null) {
            activityEvent.setData(obj);
        }
        EventBus.getDefault().post(activityEvent);
    }

    public static void finishActivities(Class... clsArr) {
        for (Class cls : clsArr) {
            finishActivity(cls);
        }
    }

    public static void startFragment(Context context, Class cls) {
        if (!ActivityUtils.isForeground(context, FragmentActivity.class.getName())) {
            Intent intent = new Intent(context, FragmentActivity.class);
            intent.putExtra(Config.EXTRA_KEY_FRAGMENT, cls.getSimpleName());
            intent.setFlags(268435456);
            context.startActivityAsUser(intent, UserHandle.CURRENT);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(Config.EXTRA_KEY_FRAGMENT, cls.getSimpleName());
        deliveryEvent(Action.START_FRAGMENT, hashMap, FragmentActivity.class);
    }

    public static void startFragment(Context context, Class cls, Map<String, String> map) {
        if (!ActivityUtils.isForeground(context, FragmentActivity.class.getName())) {
            Intent intent = new Intent(context, FragmentActivity.class);
            intent.putExtra(Config.EXTRA_KEY_FRAGMENT, cls.getSimpleName());
            intent.setFlags(268435456);
            if (map != null) {
                Bundle bundle = new Bundle();
                for (String str : map.keySet()) {
                    bundle.putString(str, map.get(str));
                }
                intent.putExtra(Config.EXTRA_PARAM, bundle);
            }
            context.startActivityAsUser(intent, UserHandle.CURRENT);
            return;
        }
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(Config.EXTRA_KEY_FRAGMENT, cls.getSimpleName());
        deliveryEvent(Action.START_FRAGMENT, map, FragmentActivity.class);
    }

    public static void showCampaign(RemindMode remindMode) {
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.setClazz(OtaService.class);
        activityEvent.setAction(Action.SHOW_CAMPAIGN);
        activityEvent.setData(remindMode);
        EventBus.getDefault().post(activityEvent);
    }

    public static void startUpgrade() {
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.setClazz(OtaService.class);
        activityEvent.setAction(Action.START_INSTALL);
        EventBus.getDefault().post(activityEvent);
    }

    public static void scheduleUpgrade(int i, int i2, String str) {
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.setClazz(OtaService.class);
        activityEvent.setAction(Action.SCHEDULE);
        activityEvent.setData(Dictionary.of(Constants.Schedule.KEY_HOUR, Integer.valueOf(i), Constants.Schedule.KEY_MINUTE, Integer.valueOf(i2), "from", str));
        EventBus.getDefault().post(activityEvent);
    }

    public static void startAutoUpgrade() {
        if (!OTAManager.getPreferencesManager().supportAutoUpgrade()) {
            LogUtils.d(TAG, "Auto upgrade not set");
            return;
        }
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.setClazz(OtaService.class);
        activityEvent.setAction(Action.AUTO_UPGRADE);
        EventBus.getDefault().post(activityEvent);
    }

    public static void sendAutoUpgrade(Action action) {
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.setClazz(CampaignFeatureHelper.getMainFragmentClass());
        activityEvent.setAction(action);
        EventBus.getDefault().post(activityEvent);
    }

    public static void cancelAutoSchedule() {
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.setClazz(OtaService.class);
        activityEvent.setAction(Action.CANCEL_AUTO_SCHEDULE);
        EventBus.getDefault().post(activityEvent);
    }

    public static void cancelUserSchedule(String str) {
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.setClazz(OtaService.class);
        activityEvent.setAction(Action.CANCEL_USER_SCHEDULE);
        activityEvent.setData(str);
        EventBus.getDefault().post(activityEvent);
    }

    public static void clearSchedule() {
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.setClazz(OtaService.class);
        activityEvent.setAction(Action.CLEAR_SCHEDULE);
        EventBus.getDefault().post(activityEvent);
    }

    public static void cancelUpgrade() {
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.setClazz(OtaService.class);
        activityEvent.setAction(Action.CANCEL_UPGRADE);
        EventBus.getDefault().post(activityEvent);
    }

    public static void ecuVersionChanged(Class cls) {
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.setClazz(cls);
        activityEvent.setAction(Action.ECU_VERSION_CHANGED);
        EventBus.getDefault().post(activityEvent);
    }

    public static void campaignCancel(Class cls) {
        ActivityEvent activityEvent = new ActivityEvent();
        activityEvent.setClazz(cls);
        activityEvent.setAction(Action.CAMPAIGN_CANCEL);
        EventBus.getDefault().post(activityEvent);
    }

    public static void deliveryEvent(Action action, Class... clsArr) {
        deliveryEvent(action, null, clsArr);
    }

    public static void deliveryEvent(Action action, Object obj, Class... clsArr) {
        for (Class cls : clsArr) {
            ActivityEvent activityEvent = new ActivityEvent();
            activityEvent.setClazz(cls);
            activityEvent.setAction(action);
            if (obj != null) {
                activityEvent.setData(obj);
            }
            EventBus.getDefault().post(activityEvent);
        }
    }
}
