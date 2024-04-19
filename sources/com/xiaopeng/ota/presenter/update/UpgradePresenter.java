package com.xiaopeng.ota.presenter.update;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import com.xiaopeng.fota.sdk.EcuType;
import com.xiaopeng.fota.sdk.UpgradeInterfaceBase;
import com.xiaopeng.fota.sdk.UpgradeProgress;
import com.xiaopeng.fota.sdk.UpgradeUtils;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.VehicleFeature;
import com.xiaopeng.ota.activity.FragmentActivity;
import com.xiaopeng.ota.bean.Action;
import com.xiaopeng.ota.helper.ActivityHelper;
import com.xiaopeng.ota.helper.CampaignFeatureHelper;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.helper.ScheduleHelper;
import com.xiaopeng.ota.helper.UpgradeAnimHelper;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.presenter.update.bean.EcuVersion;
import com.xiaopeng.ota.presenter.update.bean.EcuVersionSyncData;
import com.xiaopeng.ota.presenter.update.bean.UpgradeResult;
import com.xiaopeng.ota.presenter.update.bean.UpgradeTrigger;
import com.xiaopeng.ota.sdk.common.OTAConstants;
import com.xiaopeng.ota.utils.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class UpgradePresenter implements UpgradeInterfaceBase {
    public static final int OTA_STATE_IDLE = 1;
    public static final int OTA_STATE_UPGRADING = 2;
    private static final String TAG = "UpgradePresenter";
    private static List<EcuVersion> sEcuVersions = new ArrayList();
    private Context mContext;
    private UpdateListenerInvoker mListenerInvoker;

    @Override // com.xiaopeng.fota.sdk.UpgradeInterfaceBase
    public void notifyStatus(int i) {
    }

    public UpgradePresenter() {
    }

    public UpgradePresenter(Context context, UpdateListener updateListener) {
        this.mContext = context;
        this.mListenerInvoker = new UpdateListenerInvoker(updateListener);
        syncEcuVersions(5000L);
    }

    public void startUpgrade(boolean z) {
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign == null) {
            LogUtils.d(TAG, "Current campaign is null");
            return;
        }
        LogUtils.d(TAG, "OtaStartUpgrade, campaignId=%d", Long.valueOf(activeCampaign.getCampaignId()));
        int OtaStartUpgrade = UpgradeUtils.OtaStartUpgrade(activeCampaign.getCampaignId(), z);
        if (OtaStartUpgrade != 0) {
            LogUtils.e(TAG, "OtaStartUpgrade fail");
            this.mListenerInvoker.onError((z ? UpgradeTrigger.SCHEDULE : UpgradeTrigger.CDU).getCode(), new Exception(String.format(Locale.getDefault(), "OtaStartUpgrade error, code=%d", Integer.valueOf(OtaStartUpgrade))));
        }
    }

    public boolean scheduleUpgrade(String str, int i, int i2, int i3) {
        if (i < 0 || i2 < 0) {
            LogUtils.e(TAG, "scheduleUpgrade, param wrong.");
            return false;
        } else if (UpgradeUtils.OtaScheduleWakeup(str, (short) i, (short) i2, (short) i3) != 0) {
            LogUtils.w(TAG, "scheduleUpgrade fail");
            return false;
        } else {
            LogUtils.d(TAG, "Set schedule(%02d:%02d+02d) success", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
            return true;
        }
    }

    public boolean cancelSchedule(String str) {
        if (UpgradeUtils.OtaCancelWakeup(str) != 0) {
            LogUtils.w(TAG, "cancelSchedule fail");
            return false;
        }
        return true;
    }

    public void cancelUpgrade() {
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign == null) {
            LogUtils.d(TAG, "Cancel failed current campaign is null");
        } else if (UpgradeUtils.OtaCancelUpgrade(activeCampaign.getCampaignId()) != 0) {
            LogUtils.w(TAG, "cancelUpgrade fail");
        } else {
            LogUtils.w(TAG, "cancelUpgrade success");
        }
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeInterfaceBase
    public void notifyCampaign(int i, String str) {
        LogUtils.d(TAG, "notifyCampaign, message: " + str);
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "message empty");
            OTAManager.getCampaignManager().cancelActive();
            UpgradeAnimHelper.hideUpgradeAnim(this.mContext);
            ActivityHelper.campaignCancel(FragmentActivity.class);
            ActivityHelper.clearSchedule();
            return;
        }
        Campaign convert = Campaign.convert(str);
        if (convert == null) {
            return;
        }
        OTAManager.getCampaignManager().save(convert);
        if (i == 0) {
            this.mListenerInvoker.onNewCampaign(convert);
            if (convert.isSupportSchedule() && OTAManager.getPreferencesManager().supportAutoUpgrade()) {
                ActivityHelper.startAutoUpgrade();
            } else {
                LogUtils.i(TAG, "Disable auto upgrade (enable=%b,support=%b,switch=%b)", Boolean.valueOf(ConfigHelper.getBoolean(Constants.ConfigKey.SCHEDULE_ENABLE)), Boolean.valueOf(convert.isSupportSchedule()), Boolean.valueOf(OTAManager.getPreferencesManager().supportAutoUpgrade()));
            }
        } else if (i == 1) {
            this.mListenerInvoker.onUsbNewCampaign(convert);
        } else if (i == 2) {
            this.mListenerInvoker.onWakeUp(convert);
        } else {
            LogUtils.e(TAG, "Unknown source: " + i);
        }
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeInterfaceBase
    public void notifyStart(long j, int i) {
        Object[] objArr = new Object[2];
        objArr[0] = Long.valueOf(j);
        objArr[1] = Boolean.valueOf(i == 1);
        LogUtils.d(TAG, "notifyStart, campaignId: %d, schedule:%b", objArr);
        sendUpgradeStateBroadcast(2);
        this.mListenerInvoker.onChecking(false);
        this.mListenerInvoker.onStart();
        ScheduleHelper.getInstance().cancelSchedule();
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign != null) {
            ActivityHelper.deliveryEvent(Action.CANCEL_SCHEDULE_SUCCESS, CampaignFeatureHelper.getMainFragmentClass(), CampaignFeatureHelper.getNewFragmentClass(activeCampaign));
        }
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeInterfaceBase
    public void notifyProgress(UpgradeProgress upgradeProgress) {
        LogUtils.d(TAG, String.format(Locale.getDefault(), "[%d] notifyProgress(ecu=%d, state=%d), progress: %d", Long.valueOf(upgradeProgress.getCampaignId()), Integer.valueOf(upgradeProgress.getEcuId()), Integer.valueOf(upgradeProgress.getUpgradeState()), Integer.valueOf(upgradeProgress.getProgress())));
        EcuType valueById = EcuType.valueById(upgradeProgress.getEcuId());
        if (upgradeProgress.getProgress() < 0) {
            LogUtils.e(TAG, "[%d] Wrong progress(%s, %d): %d", Long.valueOf(upgradeProgress.getCampaignId()), valueById.desc(), Integer.valueOf(upgradeProgress.getUpgradeState()), Integer.valueOf(upgradeProgress.getProgress()));
            return;
        }
        int upgradeState = upgradeProgress.getUpgradeState();
        if (upgradeState == 1) {
            if (100 > upgradeProgress.getProgress()) {
                this.mListenerInvoker.onChecking(true);
            }
        } else if (upgradeState == 2) {
            this.mListenerInvoker.onChecking(false);
        } else if (upgradeState == 3) {
            if (upgradeProgress.getProgress() == 0) {
                this.mListenerInvoker.onChecking(false);
            }
            this.mListenerInvoker.onProgress(valueById, upgradeProgress);
        } else if (upgradeState == 4) {
            this.mListenerInvoker.onFinalizing(valueById, upgradeProgress);
        } else {
            LogUtils.d(TAG, "Wrong state returned");
        }
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeInterfaceBase
    public void notifyResult(long j, int i, Object obj) {
        UpgradeResult upgradeResult;
        LogUtils.d(TAG, String.format(Locale.getDefault(), "[%d] notifyResult, errorCode: %d, message:%s", Long.valueOf(j), Integer.valueOf(i), JsonUtils.toJson(obj)));
        this.mListenerInvoker.onChecking(false);
        if (i != 0) {
            String str = null;
            if (i == 1) {
                try {
                    str = new JSONObject((String) obj).getString("from");
                } catch (JSONException unused) {
                    LogUtils.e(TAG, "Parse from from message fail");
                }
                if (TextUtils.isEmpty(str)) {
                    str = UpgradeTrigger.SCHEDULE.getCode();
                }
                this.mListenerInvoker.onError(str, new Exception((String) obj));
            } else if (i == 2) {
                try {
                    upgradeResult = (UpgradeResult) JsonUtils.fromJson((String) obj, (Class<Object>) UpgradeResult.class);
                } catch (Exception e) {
                    LogUtils.e(TAG, e, "Parse condition miss message fail");
                    upgradeResult = null;
                }
                this.mListenerInvoker.onConditionMiss(j, upgradeResult);
            } else if (i == 3) {
                OTAManager.getCampaignManager().cancelActive();
                this.mListenerInvoker.onExpired(j);
            } else if (i == 4) {
                this.mListenerInvoker.onCanceled(j);
            } else if (i == 5) {
                this.mListenerInvoker.onCampaignCompleted(j, false);
            }
        } else if (VehicleFeature.isD55()) {
            this.mListenerInvoker.onCampaignCompleted(j, false);
        } else {
            this.mListenerInvoker.onCampaignCompleted(j, true);
        }
        sendUpgradeStateBroadcast(1);
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeInterfaceBase
    public void notifyEcuVersions(String str) {
        LogUtils.d(TAG, "notifyEcuVersions: " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        sEcuVersions = ((EcuVersionSyncData) JsonUtils.fromJson(str, (Class<Object>) EcuVersionSyncData.class)).getEcus();
        UpdateListenerInvoker updateListenerInvoker = this.mListenerInvoker;
        if (updateListenerInvoker != null) {
            updateListenerInvoker.onEcuVersionChanged();
        }
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeInterfaceBase
    public void notifyUsbProgress(String str) {
        LogUtils.d(TAG, "notifyUsbProgress, %s", str);
        this.mListenerInvoker.onUsbProgress(str);
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeInterfaceBase
    public void onRemoteControl(int i, String str) {
        LogUtils.d(TAG, "onRemoteControl: %d, %s", Integer.valueOf(i), str);
    }

    public void syncEcuVersions(long j) {
        ThreadUtils.postBackgroundLong(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$UpgradePresenter$FVDx0AQ0vwrpS9VLv3JHAv0LXnA
            @Override // java.lang.Runnable
            public final void run() {
                UpgradeUtils.OtaGetEcuVersions();
            }
        }, j);
    }

    public int getCampaignRetryTimes() {
        return UpgradeUtils.OtaGetCampaignRetryTimes();
    }

    public List<EcuVersion> getEcuVersions() {
        return sEcuVersions;
    }

    public static boolean hasCampaign() {
        int OtaGetCampaignStatus = UpgradeUtils.OtaGetCampaignStatus();
        return (OtaGetCampaignStatus == 0 || OtaGetCampaignStatus == 1) ? false : true;
    }

    public static int getUpgradeState() {
        return UpgradeUtils.OtaGetCampaignStatus() > 7 ? 2 : 1;
    }

    public static int getCurrentCampaignRetryTimes() {
        return UpgradeUtils.OtaGetCampaignRetryTimes();
    }

    private void sendUpgradeStateBroadcast(int i) {
        LogUtils.d(TAG, "Send broadcast %s (%d)", OTAConstants.Action.OTA_STATE, Integer.valueOf(i));
        Context context = OTAManager.getContext();
        Intent intent = new Intent();
        intent.setAction(OTAConstants.Action.OTA_STATE);
        intent.putExtra("state", i);
        context.sendBroadcastAsUser(intent, UserHandle.CURRENT);
    }
}
