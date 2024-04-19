package com.xiaopeng.ota.presenter.push;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Pair;
import com.xiaopeng.fota.sdk.UpgradeUtils;
import com.xiaopeng.ota.Config;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.bean.Action;
import com.xiaopeng.ota.helper.ActivityHelper;
import com.xiaopeng.ota.helper.CampaignFeatureHelper;
import com.xiaopeng.ota.presenter.event.EventPresenter;
import com.xiaopeng.ota.sdk.push.Feedback;
import com.xiaopeng.ota.utils.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.ota.utils.TimeUtils;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class PushPresenter {
    private static final String AUTO_UPGRADE = "autoUpgrade";
    private static final String CONTENT = "content";
    private static final String TAG = "PushPresenter";
    private static final String UPLOAD_DATE = "uploadDate";
    private Context mContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PushPresenter(Context context) {
        this.mContext = context;
    }

    public void checkCampaign(String str) {
        UpgradeUtils.OtaHandlePushEvent(11001, System.currentTimeMillis(), str, null);
    }

    public void updateRemoteConfig(String str) {
        UpgradeUtils.OtaHandlePushEvent(11002, System.currentTimeMillis(), str, null);
        ThreadUtils.postBackgroundLong(new Runnable() { // from class: com.xiaopeng.ota.presenter.push.-$$Lambda$PushPresenter$IyNND_AkLN8XvHDkj14zqDGnP6Q
            @Override // java.lang.Runnable
            public final void run() {
                OTAManager.getConfigManager().sync(true);
            }
        }, 6000L);
    }

    public void uploadLog(String str, JSONObject jSONObject) {
        String contentInfoByTag = getContentInfoByTag(jSONObject, UPLOAD_DATE);
        if (TextUtils.isEmpty(contentInfoByTag)) {
            LogUtils.d(TAG, "uploadDate empty");
        } else {
            UpgradeUtils.OtaHandlePushEvent(11003, System.currentTimeMillis(), str, contentInfoByTag);
        }
    }

    public void syncEcu(String str) {
        UpgradeUtils.OtaHandlePushEvent(11004, System.currentTimeMillis(), str, null);
    }

    public void clearDatabase(String str) {
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.presenter.push.-$$Lambda$PushPresenter$6WZeswo14DF7_CmMGerV-eusw4k
            @Override // java.lang.Runnable
            public final void run() {
                OTAManager.getCampaignManager().deleteAll();
            }
        });
        UpgradeUtils.OtaHandlePushEvent(11005, System.currentTimeMillis(), str, null);
    }

    public void clearDownloadPackages(String str) {
        UpgradeUtils.OtaHandlePushEvent(11006, System.currentTimeMillis(), str, null);
    }

    public void clearVersions(String str) {
        UpgradeUtils.OtaHandlePushEvent(11027, System.currentTimeMillis(), str, null);
    }

    public void promptNewVersion(String str) {
        UpgradeUtils.OtaHandlePushEvent(11007, System.currentTimeMillis(), str, null);
    }

    public void cancelCampaign(String str) {
        UpgradeUtils.OtaHandlePushEvent(11008, System.currentTimeMillis(), str, null);
    }

    public void resetCampaignRetries(String str) {
        UpgradeUtils.OtaHandlePushEvent(11025, System.currentTimeMillis(), str, null);
    }

    public void abortCampaign(String str) {
        UpgradeUtils.OtaHandlePushEvent(11026, System.currentTimeMillis(), str, null);
    }

    public void retryDownload(String str) {
        UpgradeUtils.OtaHandlePushEvent(11013, System.currentTimeMillis(), str, null);
    }

    public void upgradeNow(String str, JSONObject jSONObject) {
        String str2 = null;
        try {
            str2 = jSONObject.getString("content");
            enableAutoUpgrade(str2);
        } catch (JSONException unused) {
        }
        UpgradeUtils.OtaHandlePushEvent(11015, System.currentTimeMillis(), str, str2);
    }

    public void forwardToSdk(int i, String str) {
        UpgradeUtils.OtaHandlePushEvent(i, System.currentTimeMillis(), str, null);
    }

    private void enableAutoUpgrade(String str) {
        if (TextUtils.isEmpty(str) || OTAManager.getPreferencesManager().supportAutoUpgrade()) {
            return;
        }
        try {
            if (new JSONObject(str).optBoolean(AUTO_UPGRADE, false)) {
                EventPresenter.getInstance().sendAutoUpgradeClicked(true, "app");
                OTAManager.getPreferencesManager().setAutoUpgrade(true);
                ActivityHelper.deliveryEvent(Action.AUTO_UPGRADE, CampaignFeatureHelper.getMainFragmentClass());
                ActivityHelper.startAutoUpgrade();
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Content json parse exception");
        }
    }

    public void schedule(String str, JSONObject jSONObject) {
        String str2;
        long currentTimeMillis = System.currentTimeMillis();
        int i = 1;
        String str3 = null;
        if (OTAManager.getCampaignManager().getActiveCampaign() == null) {
            i = 2;
        } else if (UpgradeUtils.OtaGetCampaignStatus() == 7) {
            String contentInfoByTag = getContentInfoByTag(jSONObject, "scheduleTime");
            if (TextUtils.isEmpty(contentInfoByTag)) {
                str2 = "Schedule time empty";
            } else {
                Pair<Integer, Integer> hourAndMinute = TimeUtils.getHourAndMinute(contentInfoByTag);
                if (hourAndMinute == null) {
                    str2 = "Schedule time convert to integer fail";
                } else {
                    String contentInfoByTag2 = getContentInfoByTag(jSONObject, "from");
                    if (TextUtils.isEmpty(contentInfoByTag2)) {
                        contentInfoByTag2 = Constants.Schedule.FROM_SERVER;
                    }
                    ActivityHelper.scheduleUpgrade(((Integer) hourAndMinute.first).intValue(), ((Integer) hourAndMinute.second).intValue(), contentInfoByTag2);
                    i = 0;
                }
            }
            str3 = str2;
        } else {
            str3 = "Campaign not in wait confirm state";
        }
        Feedback.Builder status = new Feedback.Builder().msgId(str).receiveTime(Long.valueOf(currentTimeMillis)).startTime(Long.valueOf(System.currentTimeMillis())).status(Integer.valueOf(i));
        if (str3 != null) {
            status.context("message", str3);
        }
        UpgradeUtils.OtaSendPushFeedback(JsonUtils.toJson(status.build()));
    }

    public void cancelSchedule(String str, JSONObject jSONObject) {
        long currentTimeMillis = System.currentTimeMillis();
        String contentInfoByTag = getContentInfoByTag(jSONObject, "from");
        if (TextUtils.isEmpty(contentInfoByTag)) {
            ActivityHelper.cancelUserSchedule(Constants.Schedule.FROM_SERVER);
        } else {
            ActivityHelper.cancelUserSchedule(contentInfoByTag);
        }
        UpgradeUtils.OtaSendPushFeedback(JsonUtils.toJson(new Feedback.Builder().msgId(str).receiveTime(Long.valueOf(currentTimeMillis)).startTime(Long.valueOf(System.currentTimeMillis())).status(0).build()));
    }

    public void resetApp(String str) {
        UpgradeUtils.OtaSendPushFeedback(JsonUtils.toJson(new Feedback.Builder().msgId(str).receiveTime(Long.valueOf(System.currentTimeMillis())).startTime(Long.valueOf(System.currentTimeMillis())).status(0).build()));
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.presenter.push.-$$Lambda$PushPresenter$J_676gtZeAEsGNqI9MuuC-Hy_5c
            @Override // java.lang.Runnable
            public final void run() {
                System.exit(0);
            }
        }, TimeUnit.SECONDS.toMillis(30L));
    }

    public void resetPeps(String str) {
        UpgradeUtils.OtaSendPushFeedback(JsonUtils.toJson(new Feedback.Builder().msgId(str).receiveTime(Long.valueOf(System.currentTimeMillis())).startTime(Long.valueOf(System.currentTimeMillis())).status(1).context("message", "Not support").build()));
    }

    public void resetPsu(String str) {
        UpgradeUtils.OtaSendPushFeedback(JsonUtils.toJson(new Feedback.Builder().msgId(str).receiveTime(Long.valueOf(System.currentTimeMillis())).startTime(Long.valueOf(System.currentTimeMillis())).status(1).context("message", "Not support").build()));
    }

    public void queryVehicleStatus(String str) {
        UpgradeUtils.OtaHandlePushEvent(11020, System.currentTimeMillis(), str, null);
    }

    public void refreshXOSVersion(String str) {
        Feedback.Builder context = new Feedback.Builder().msgId(str).receiveTime(Long.valueOf(System.currentTimeMillis())).startTime(Long.valueOf(System.currentTimeMillis())).status(0).context("xosVersion", OTAManager.getCurrentOTAVersion());
        try {
            OTAManager.syncOsVersion();
            context.context("requestXosVersion", OTAManager.getCurrentOTAVersion());
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Request remote os version failed");
            context.status(1).context("message", "Request refresh XOS version failed");
        }
        UpgradeUtils.OtaSendPushFeedback(JsonUtils.toJson(context.build()));
    }

    public void cleanXOSVersion(String str) {
        Feedback.Builder status = new Feedback.Builder().msgId(str).receiveTime(Long.valueOf(System.currentTimeMillis())).startTime(Long.valueOf(System.currentTimeMillis())).status(0);
        try {
            OTAManager.getOsVersionManager().clear();
            OTAManager.getReleaseVersionManager().deleteAll();
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Clean os version failed");
            status.status(1).context("message", "Clean os version failed");
        }
        UpgradeUtils.OtaSendPushFeedback(JsonUtils.toJson(status.build()));
    }

    public void handleSotaAction(String str, int i) {
        long currentTimeMillis = System.currentTimeMillis();
        sendSotaPushBroadcast(i);
        UpgradeUtils.OtaSendPushFeedback(JsonUtils.toJson(new Feedback.Builder().msgId(str).receiveTime(Long.valueOf(currentTimeMillis)).startTime(Long.valueOf(System.currentTimeMillis())).status(0).build()));
    }

    private String getContentInfoByTag(JSONObject jSONObject, String str) {
        try {
            return new JSONObject(jSONObject.getString("content")).getString(str);
        } catch (JSONException e) {
            LogUtils.e(TAG, e, "get %s from fail", str);
            return null;
        }
    }

    private void sendSotaPushBroadcast(int i) {
        Intent intent = new Intent();
        intent.setAction(Config.ACTION_SOTA_SERVER_MESSAGE);
        intent.putExtra("type", i);
        this.mContext.sendBroadcast(intent);
    }
}
