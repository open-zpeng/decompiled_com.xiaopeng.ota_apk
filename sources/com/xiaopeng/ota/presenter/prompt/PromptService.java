package com.xiaopeng.ota.presenter.prompt;

import android.text.TextUtils;
import com.xiaopeng.libconfig.ipc.IpcConfig;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.auto.CarApi;
import com.xiaopeng.ota.auto.service.mcu.IMcuService;
import com.xiaopeng.ota.bean.AiScene;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.presenter.update.bean.UpgradeResult;
import com.xiaopeng.ota.remind.RemindMode;
import com.xiaopeng.ota.sdk.common.util.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.PreferencesUtils;
import com.xiaopeng.ota.utils.StringUtils;
import java.util.List;
import java.util.stream.Collectors;
/* loaded from: classes2.dex */
public abstract class PromptService {
    private static final String TAG = "PromptService";
    static final String WAITING_MESSAGE_KEY = "message.waiting_message";
    String AI_ASSISTANT_PKG_NAME = IpcConfig.App.AI;
    String ECU_JOINER = "、";

    public abstract void closeAllMessage();

    public abstract void dispose();

    public abstract void setListener(IOnReceiveListener iOnReceiveListener);

    public abstract void showCommonUpgrade(Campaign campaign, RemindMode remindMode);

    public abstract void showConditionMiss(Campaign campaign, UpgradeResult upgradeResult);

    public abstract void showScheduleArrived(Campaign campaign, String str);

    public abstract void showScheduleUpgrade(Campaign campaign, RemindMode remindMode);

    public abstract void showUpgradeFail(Campaign campaign, UpgradeResult upgradeResult);

    public abstract void showUpgradeSuccess(Campaign campaign, boolean z);

    public abstract void showVerifyFail(Campaign campaign, boolean z);

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isLocalIgOn() {
        int i = -1;
        try {
            i = ((IMcuService) CarApi.getCarApi(101)).getIgStatusFromMcu();
            if (1 != i) {
                LogUtils.i(TAG, "Mcu ig status: " + i);
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Get mcu ig status occur Exception");
        }
        return 1 == i;
    }

    public AiScene convertToScene(RemindMode remindMode, Campaign campaign) {
        if (remindMode.getLocation() != null) {
            if (ConfigHelper.getBoolean(Constants.ConfigKey.SCHEDULE_ENABLE) && campaign.isSupportSchedule()) {
                return AiScene.REGULAR_POS_SCHEDULE_UPGRADE;
            }
            return AiScene.REGULAR_POS_UPGRADE;
        } else if (ConfigHelper.getBoolean(Constants.ConfigKey.SCHEDULE_ENABLE) && campaign.isSupportSchedule()) {
            return AiScene.NOT_REGULAR_POS_SCHEDULE_UPGRADE;
        } else {
            return AiScene.NOT_REGULAR_POS_UPGRADE;
        }
    }

    public AiScene convertToScene(boolean z, boolean z2) {
        if (z) {
            if (z2) {
                return AiScene.SCHEDULE_FAIL_RETRY;
            }
            return AiScene.SCHEDULE_FAIL_NO_RETRY;
        } else if (z2) {
            return AiScene.COMMON_FAIL_RETRY;
        } else {
            return AiScene.COMMON_FAIL_NO_RETRY;
        }
    }

    public String assembleConditionMissContent(UpgradeResult upgradeResult) {
        if (upgradeResult != null) {
            String conditionMissContent = getConditionMissContent(upgradeResult);
            if (UpgradeResult.isUserTrigger(upgradeResult.getFrom())) {
                if (!upgradeResult.isEcuNotReady()) {
                    return TextUtils.isEmpty(conditionMissContent) ? ConfigHelper.getString(Constants.ConfigKey.PROMPT_DIALOG_CONDITION_MISS_CONTENT) : conditionMissContent;
                }
                if (TextUtils.isEmpty(conditionMissContent)) {
                    conditionMissContent = ConfigHelper.getString(Constants.ConfigKey.UPGRADE_CONDITION_MISS_TEXT);
                }
                return StringUtils.format(Constants.ConfigKey.PROMPT_DIALOG_CONDITION_MISS_CUSTOMER_SERVICE_CONTENT, conditionMissContent);
            } else if (!upgradeResult.isEcuNotReady()) {
                return TextUtils.isEmpty(conditionMissContent) ? ConfigHelper.getString(Constants.ConfigKey.PROMPT_DIALOG_CONDITION_MISS_CONTENT) : conditionMissContent;
            } else {
                if (TextUtils.isEmpty(conditionMissContent)) {
                    conditionMissContent = ConfigHelper.getString(Constants.ConfigKey.UPGRADE_CONDITION_MISS_TEXT);
                }
                return StringUtils.format(Constants.ConfigKey.PROMPT_UPGRADE_CONDITION_MISS_CUSTOMER_SERVICE_CONTENT, conditionMissContent);
            }
        }
        return ConfigHelper.getString(Constants.ConfigKey.PROMPT_DIALOG_CONDITION_MISS_CONTENT);
    }

    public String getConditionMissContent(UpgradeResult upgradeResult) {
        if (upgradeResult != null) {
            if (upgradeResult.isEcuNotReady()) {
                return join(upgradeResult.getDecodeList(upgradeResult.getEcuNotReady()));
            }
            return getFirstOrDefaultCondition(upgradeResult.getDecodeList(upgradeResult.getMessage()));
        }
        return "";
    }

    protected String getFirstOrDefaultCondition(List<String> list) {
        return (list == null || list.isEmpty()) ? "" : list.get(0);
    }

    protected String join(List<String> list) {
        return (list == null || list.isEmpty()) ? "" : (String) list.stream().collect(Collectors.joining(this.ECU_JOINER));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void saveMessage(PromptMessage promptMessage) {
        LogUtils.d(TAG, "saveMessage: %s", JsonUtils.toJson(promptMessage));
        if (promptMessage != null) {
            PreferencesUtils.putString(OTAManager.getContext(), WAITING_MESSAGE_KEY, JsonUtils.toJson(promptMessage));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeWaitingMessage() {
        PreferencesUtils.remove(OTAManager.getContext(), WAITING_MESSAGE_KEY);
    }
}
