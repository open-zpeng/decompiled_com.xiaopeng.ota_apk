package com.xiaopeng.ota.presenter.prompt;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.xiaopeng.lib.framework.ipcmodule.IpcModuleEntry;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.ipcmodule.IIpcService;
import com.xiaopeng.libconfig.ipc.IpcConfig;
import com.xiaopeng.libconfig.ipc.bean.MessageCenterBean;
import com.xiaopeng.libconfig.ipc.bean.MessageContentBean;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.bean.AiAction;
import com.xiaopeng.ota.bean.AiScene;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.presenter.update.bean.UpgradeResult;
import com.xiaopeng.ota.presenter.update.bean.UpgradeTrigger;
import com.xiaopeng.ota.remind.RemindMode;
import com.xiaopeng.ota.utils.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ResourceUtils;
import com.xiaopeng.ota.utils.StringUtils;
import com.xiaopeng.ota.utils.TimeUtils;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* loaded from: classes2.dex */
public class AiPromptService extends PromptService {
    private static final String CURRENT_SCENE_KEY = "current_scene";
    private static final int OTA_BIZ_TYPE = 11;
    private static final String TAG = "AiPromptService";
    private IOnReceiveListener mListener;
    private Map<String, MessageCenterBean> mPromptedMessages = new ConcurrentHashMap();
    private IIpcService mIpcService = (IIpcService) Module.get(IpcModuleEntry.class).get(IIpcService.class);

    public AiPromptService() {
        EventBus.getDefault().register(this);
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void dispose() {
        EventBus.getDefault().unregister(this);
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void setListener(IOnReceiveListener iOnReceiveListener) {
        this.mListener = iOnReceiveListener;
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showCommonUpgrade(Campaign campaign, RemindMode remindMode) {
        if (campaign.isSupportSchedule()) {
            showScheduleUpgrade(campaign, remindMode);
        } else {
            showLocalUpgrade(campaign, remindMode);
        }
    }

    public void showLocalUpgrade(Campaign campaign, RemindMode remindMode) {
        LogUtils.d(TAG, "showLocalUpgrade(mode=%d,campaignId=%d)", Integer.valueOf(remindMode.getRemindMode()), Long.valueOf(campaign.getCampaignId()));
        MessageContentBean createContent = createContent(1);
        createContent.addTitle(ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_LOCAL_UPGRADE_TITLE, campaign));
        createContent.addTitle(ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_LOCAL_UPGRADE_CONTENT, campaign));
        createContent.setTts(ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_LOCAL_UPGRADE_TTS, campaign));
        AiContent aiContent = new AiContent(convertToScene(remindMode, campaign), AiAction.OP_SHOW_UPGRADE_INFO, campaign.getCampaignId());
        createContent.addButton(MessageContentBean.MsgButton.create(ConfigHelper.getString(Constants.ConfigKey.BUTTON_ALL_RIGHT), "com.xiaopeng.ota", aiContent.toContentString()));
        sendMessage(aiContent, createContent);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0037 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void showAutoUpgrade(com.xiaopeng.ota.presenter.update.bean.Campaign r9, com.xiaopeng.ota.remind.RemindMode r10) {
        /*
            r8 = this;
            java.lang.String r0 = "PROMPT_AUTO_UPGRADE_ENABLED"
            java.lang.String r0 = com.xiaopeng.ota.helper.ConfigHelper.getString(r0)
            r1 = 3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            int r2 = r10.getRemindMode()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3 = 0
            r1[r3] = r2
            long r2 = r9.getCampaignId()
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r3 = 1
            r1[r3] = r2
            r2 = 2
            r1[r2] = r0
            java.lang.String r2 = "AiPromptService"
            java.lang.String r4 = "showAutoUpgrade(mode=%d,campaignId=%d, enabled=%s)"
            com.xiaopeng.ota.utils.LogUtils.d(r2, r4, r1)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L34
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L34
            goto L35
        L34:
            r0 = r3
        L35:
            if (r3 == r0) goto L38
            return
        L38:
            java.lang.String r0 = "PROMPT_AUTO_UPGRADE_TITLE"
            java.lang.String r4 = com.xiaopeng.ota.helper.ConfigHelper.getFormattedWithCampaign(r0, r9)
            java.lang.String r0 = "PROMPT_AUTO_UPGRADE_CONTENT"
            java.lang.String r5 = com.xiaopeng.ota.helper.ConfigHelper.getFormattedWithCampaign(r0, r9)
            java.lang.String r0 = "PROMPT_AUTO_UPGRADE_TTS"
            java.lang.String r6 = com.xiaopeng.ota.helper.ConfigHelper.getFormattedWithCampaign(r0, r9)
            com.xiaopeng.ota.bean.AiAction r7 = com.xiaopeng.ota.bean.AiAction.OP_SHOW_MAIN
            r1 = r8
            r2 = r9
            r3 = r10
            r1.showScheduleUpgrade(r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.ota.presenter.prompt.AiPromptService.showAutoUpgrade(com.xiaopeng.ota.presenter.update.bean.Campaign, com.xiaopeng.ota.remind.RemindMode):void");
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showScheduleUpgrade(Campaign campaign, RemindMode remindMode) {
        if (OTAManager.getPreferencesManager().supportAutoUpgrade()) {
            showAutoUpgrade(campaign, remindMode);
        } else {
            showScheduleUpgrade(campaign, remindMode, ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_SCHEDULE_UPGRADE_TITLE, campaign), ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_SCHEDULE_UPGRADE_CONTENT, campaign), ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_SCHEDULE_UPGRADE_TTS, campaign), AiAction.OP_ENABLE_AUTO_UPGRADE);
        }
    }

    private void showScheduleUpgrade(Campaign campaign, RemindMode remindMode, String str, String str2, String str3, AiAction aiAction) {
        LogUtils.d(TAG, "showScheduleUpgrade(mode=%d,campaignId=%d)", Integer.valueOf(remindMode.getRemindMode()), Long.valueOf(campaign.getCampaignId()));
        MessageContentBean createContent = createContent(1);
        createContent.addTitle(str);
        createContent.addTitle(str2);
        createContent.setTts(str3);
        AiContent aiContent = new AiContent(convertToScene(remindMode, campaign), aiAction, campaign.getCampaignId());
        createContent.addButton(MessageContentBean.MsgButton.create(ConfigHelper.getString(Constants.ConfigKey.BUTTON_ALL_RIGHT), "com.xiaopeng.ota", aiContent.toContentString()));
        sendMessage(aiContent, createContent);
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showScheduleArrived(Campaign campaign, String str) {
        int i;
        int i2;
        LogUtils.d(TAG, "showScheduleArrived(campaignId=%d, scheduleTime=%s)", Long.valueOf(campaign.getCampaignId()), str);
        if (OTAManager.getPreferencesManager().supportAutoUpgrade()) {
            LogUtils.d(TAG, "Auto upgrade enabled");
            return;
        }
        MessageContentBean createContent = createContent(1);
        createContent.addTitle(ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_SCHEDULE_UPGRADE_TIMES_UP_TITLE, campaign));
        createContent.addTitle(ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_SCHEDULE_UPGRADE_TIMES_UP_CONTENT, campaign));
        createContent.setTts(ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_SCHEDULE_UPGRADE_TIMES_UP_TTS, campaign));
        AiContent aiContent = new AiContent(AiScene.SCHEDULE_ARRIVED, AiAction.OP_SHOW_MAIN, campaign.getCampaignId());
        createContent.addButton(MessageContentBean.MsgButton.create(ConfigHelper.getString(Constants.ConfigKey.BUTTON_ALL_RIGHT), "com.xiaopeng.ota", aiContent.toContentString()));
        sendMessage(aiContent, createContent);
        if (this.mListener != null) {
            Pair<Integer, Integer> hourAndMinute = TimeUtils.getHourAndMinute(str);
            if (hourAndMinute != null) {
                i = ((Integer) hourAndMinute.first).intValue();
                i2 = ((Integer) hourAndMinute.second).intValue();
            } else {
                Calendar calendar = Calendar.getInstance();
                i = calendar.get(11);
                i2 = calendar.get(12);
            }
            LogUtils.d(TAG, "Schedule upgrade at %02d:%02d", Integer.valueOf(i), Integer.valueOf(i2));
            this.mListener.onScheduleWithTime(i, i2);
            return;
        }
        LogUtils.d(TAG, "Listener is null");
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showConditionMiss(Campaign campaign, UpgradeResult upgradeResult) {
        String code = UpgradeTrigger.SCHEDULE.getCode();
        if (upgradeResult != null) {
            code = upgradeResult.getFrom();
        }
        String assembleConditionMissContent = assembleConditionMissContent(upgradeResult);
        if (upgradeResult != null && upgradeResult.isEcuNotReady()) {
            showContactCustomerService(campaign, code, assembleConditionMissContent, getConditionMissContent(upgradeResult));
        } else {
            showConditionMiss(campaign, code, assembleConditionMissContent);
        }
    }

    private void showContactCustomerService(Campaign campaign, String str, String str2, String str3) {
        String formattedWithCampaign = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_UPGRADE_CONDITION_MISS_CUSTOMER_SERVICE_TITLE, campaign);
        if (TextUtils.isEmpty(str2)) {
            str2 = ConfigHelper.getString(Constants.ConfigKey.PROMPT_DIALOG_CONDITION_MISS_CONTENT);
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = ConfigHelper.getString(Constants.ConfigKey.UPGRADE_CONDITION_MISS_TEXT);
        }
        String format = StringUtils.format(Constants.ConfigKey.PROMPT_UPGRADE_CONDITION_MISS_CUSTOMER_SERVICE_TTS, str3);
        MessageContentBean createContent = createContent(1);
        createContent.addTitle(formattedWithCampaign);
        createContent.addTitle(str2);
        AiContent aiContent = new AiContent(!UpgradeResult.isUserTrigger(str) ? AiScene.SCHEDULE_FAIL_RETRY : AiScene.COMMON_FAIL_RETRY, AiAction.OP_CALL_CS, campaign.getCampaignId());
        createContent.addButton(MessageContentBean.MsgButton.create(ConfigHelper.getString(Constants.ConfigKey.BUTTON_ALL_RIGHT), "com.xiaopeng.ota", aiContent.toContentString()));
        createContent.setTts(format);
        sendMessage(aiContent, createContent);
    }

    private void showConditionMiss(Campaign campaign, String str, String str2) {
        String string = ConfigHelper.getString(Constants.ConfigKey.PROMPT_UPGRADE_CONDITION_MISS_TITLE);
        if (TextUtils.isEmpty(str2)) {
            str2 = ConfigHelper.getString(Constants.ConfigKey.PROMPT_UPGRADE_CONDITION_MISS_CONTENT);
        }
        String string2 = ConfigHelper.getString(Constants.ConfigKey.PROMPT_UPGRADE_CONDITION_MISS_TTS);
        AiAction aiAction = AiAction.OP_SHOW_MAIN;
        if (OTAManager.getPreferencesManager().supportAutoUpgrade()) {
            string = ConfigHelper.getString(Constants.ConfigKey.PROMPT_UPGRADE_CONDITION_MISS_AUTO_TITLE);
            string2 = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_UPGRADE_CONDITION_MISS_AUTO_TTS, campaign);
        }
        MessageContentBean createContent = createContent(1);
        createContent.addTitle(string);
        createContent.addTitle(str2);
        createContent.setTts(string2);
        AiContent aiContent = new AiContent(!UpgradeResult.isUserTrigger(str) ? AiScene.SCHEDULE_FAIL_RETRY : AiScene.COMMON_FAIL_RETRY, aiAction, campaign.getCampaignId());
        createContent.addButton(MessageContentBean.MsgButton.create(ConfigHelper.getString(Constants.ConfigKey.BUTTON_ALL_RIGHT), "com.xiaopeng.ota", aiContent.toContentString()));
        sendMessage(aiContent, createContent);
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showUpgradeSuccess(Campaign campaign, boolean z) {
        LogUtils.d(TAG, "showUpgradeSuccess(campaignId=%d, schedule=%b)", Long.valueOf(campaign.getCampaignId()), Boolean.valueOf(z));
        String formattedWithCampaign = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_UPGRADE_SUCCESS_TITLE, campaign);
        String formattedWithCampaign2 = ConfigHelper.getFormattedWithCampaign(z ? Constants.ConfigKey.PROMPT_UPGRADE_SUCCESS_CONTENT_REMOTE : Constants.ConfigKey.PROMPT_UPGRADE_SUCCESS_CONTENT, campaign);
        String formatWithCampaign = ConfigHelper.formatWithCampaign(campaign.getUpgradedPrompt(), campaign);
        if (TextUtils.isEmpty(formatWithCampaign)) {
            formatWithCampaign = ConfigHelper.getFormattedWithCampaign(z ? Constants.ConfigKey.PROMPT_UPGRADE_SUCCESS_TTS_REMOTE : Constants.ConfigKey.PROMPT_UPGRADE_SUCCESS_TTS, campaign);
        }
        MessageContentBean createContent = createContent(6);
        createContent.addTitle(formattedWithCampaign);
        createContent.addTitle(formattedWithCampaign2);
        createContent.setTts(formatWithCampaign);
        AiContent aiContent = new AiContent(z ? AiScene.SCHEDULE_SUCCESS : AiScene.COMMON_SUCCESS, AiAction.OP_SHOW_UPGRADE_HISTORY, campaign.getCampaignId());
        createContent.addButton(MessageContentBean.MsgButton.create(ConfigHelper.getString(Constants.ConfigKey.BUTTON_DETAIL), "com.xiaopeng.ota", aiContent.toContentString()));
        sendMessage(aiContent, createContent);
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showUpgradeFail(Campaign campaign, UpgradeResult upgradeResult) {
        boolean z = false;
        LogUtils.d(TAG, "showUpgradeFail(campaignId=%d)", Long.valueOf(campaign.getCampaignId()));
        String formattedWithCampaign = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_UPGRADE_FAILED_TITLE, campaign);
        String formattedWithCampaign2 = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_UPGRADE_FAILED_CONTENT, campaign);
        String formattedWithCampaign3 = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_UPGRADE_FAILED_TTS, campaign);
        AiAction aiAction = AiAction.OP_SHOW_MAIN;
        if (OTAManager.getPreferencesManager().supportAutoUpgrade()) {
            formattedWithCampaign = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_UPGRADE_FAILED_AUTO_TITLE, campaign);
            formattedWithCampaign2 = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_UPGRADE_FAILED_AUTO_CONTENT, campaign);
            formattedWithCampaign3 = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_UPGRADE_FAILED_AUTO_TTS, campaign);
        }
        if (this.mListener != null) {
            LogUtils.d(TAG, "Schedule upgrade at default time");
            this.mListener.onScheduleDefault(OTAManager.getPreferencesManager().supportAutoUpgrade());
        } else {
            LogUtils.d(TAG, "Listener is null");
        }
        MessageContentBean createContent = createContent(7);
        createContent.addTitle(formattedWithCampaign);
        createContent.addTitle(formattedWithCampaign2);
        createContent.setTts(formattedWithCampaign3);
        if (upgradeResult == null || !upgradeResult.isUserTrigger()) {
            z = true;
        }
        AiContent aiContent = new AiContent(convertToScene(!z, true), aiAction, campaign.getCampaignId());
        createContent.addButton(MessageContentBean.MsgButton.create(ConfigHelper.getString(Constants.ConfigKey.BUTTON_ALL_RIGHT), "com.xiaopeng.ota", aiContent.toContentString()));
        sendMessage(aiContent, createContent);
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showVerifyFail(Campaign campaign, boolean z) {
        AiScene aiScene;
        LogUtils.d(TAG, "showVerifyFail(schedule=%b)", Boolean.valueOf(z));
        MessageContentBean createContent = createContent(7);
        createContent.addTitle(ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_VERIFY_FAILED_TITLE, campaign));
        if (z) {
            aiScene = AiScene.SCHEDULE_FAIL_RETRY;
        } else {
            aiScene = AiScene.COMMON_FAIL_RETRY;
        }
        sendMessage(new AiContent(aiScene, AiAction.NONE, campaign.getCampaignId()), createContent);
    }

    private MessageContentBean createContent(int i) {
        long validTime;
        MessageContentBean createContent = MessageContentBean.createContent();
        createContent.setType(i);
        createContent.setPermanent(1);
        createContent.setSensingType(ResourceUtils.getString(R.string.ai_ota_sensing_type));
        if (i == 6) {
            validTime = getValidTime(Constants.ConfigKey.AI_SUCCESS_MESSAGE_VALID_SECONDS);
        } else if (i == 7) {
            validTime = getValidTime(Constants.ConfigKey.AI_FAILED_MESSAGE_VALID_SECONDS);
        } else {
            validTime = getValidTime(Constants.ConfigKey.AI_UPGRADE_MESSAGE_VALID_SECONDS);
        }
        createContent.setValidTime(System.currentTimeMillis() + validTime);
        return createContent;
    }

    private long getValidTime(String str) {
        return ConfigHelper.getInt(str) * 1000;
    }

    private void sendMessage(AiContent aiContent, MessageContentBean messageContentBean) {
        long currentTimeMillis = System.currentTimeMillis();
        MessageCenterBean create = MessageCenterBean.create(11, messageContentBean);
        create.setScene(aiContent.getScene().getScene());
        create.setValidStartTs(currentTimeMillis);
        create.setValidEndTs(messageContentBean.getValidTime());
        closeAllMessage();
        ipcSend(JsonUtils.toJson(create));
        this.mPromptedMessages.put(create.getMessageId(), create);
        sendPromptEvent(aiContent);
    }

    private void ipcSend(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(IpcConfig.IPCKey.STRING_MSG, str);
        this.mIpcService.sendData(IpcConfig.MessageCenterConfig.IPC_ID_APP_PUSH_MESSAGE, bundle, IpcConfig.App.MESSAGE_CENTER);
        LogUtils.d(TAG, "Send to Ai assistant:" + str);
    }

    private void sendPromptEvent(AiContent aiContent) {
        IOnReceiveListener iOnReceiveListener = this.mListener;
        if (iOnReceiveListener != null) {
            iOnReceiveListener.onSendPromptEvent(aiContent);
        }
    }

    private void sendActionEvent(AiContent aiContent) {
        IOnReceiveListener iOnReceiveListener = this.mListener;
        if (iOnReceiveListener != null) {
            iOnReceiveListener.onSendAiResponseEvent(aiContent);
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void closeAllMessage() {
        if (this.mPromptedMessages.size() > 0) {
            Set<String> keySet = this.mPromptedMessages.keySet();
            LogUtils.d(TAG, "MessageIds: " + JsonUtils.toJson(keySet));
            for (String str : keySet) {
                this.mPromptedMessages.remove(str);
                closeMessage(str);
            }
        }
        removeWaitingMessage();
    }

    private void closeMessage(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(IpcConfig.IPCKey.STRING_MSG, str);
        this.mIpcService.sendData(11014, bundle, IpcConfig.App.AI);
        LogUtils.d(TAG, "Close message: %s", str);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(IIpcService.IpcMessageEvent ipcMessageEvent) {
        LogUtils.d(TAG, "onEvent, packageName:%s, mMsgID=%d", ipcMessageEvent.getSenderPackageName(), Integer.valueOf(ipcMessageEvent.getMsgID()));
        if (this.AI_ASSISTANT_PKG_NAME.equals(ipcMessageEvent.getSenderPackageName()) && ipcMessageEvent.getMsgID() == 10011) {
            String string = ipcMessageEvent.getPayloadData().getString(IpcConfig.IPCKey.STRING_MSG);
            LogUtils.d(TAG, "Payload data string:" + string);
            AiContent parse = AiContent.parse(string);
            if (parse == null || parse.getAction() == null) {
                LogUtils.w(TAG, "Convert op to AiAction fail");
                return;
            }
            sendActionEvent(parse);
            switch (parse.getAction()) {
                case OP_UPGRADE_NOW:
                    IOnReceiveListener iOnReceiveListener = this.mListener;
                    if (iOnReceiveListener != null) {
                        iOnReceiveListener.onUpgradeNow();
                        return;
                    }
                    return;
                case OP_SHOW_UPGRADE_HISTORY:
                    IOnReceiveListener iOnReceiveListener2 = this.mListener;
                    if (iOnReceiveListener2 != null) {
                        iOnReceiveListener2.onUpgradeSuccess();
                        return;
                    }
                    return;
                case OP_SETTING_SCHEDULE_DEFAULT:
                    IOnReceiveListener iOnReceiveListener3 = this.mListener;
                    if (iOnReceiveListener3 != null) {
                        iOnReceiveListener3.onScheduleDefault(false);
                        return;
                    }
                    return;
                case OP_SETTING_SCHEDULE_AUTO:
                    IOnReceiveListener iOnReceiveListener4 = this.mListener;
                    if (iOnReceiveListener4 != null) {
                        iOnReceiveListener4.onScheduleDefault(true);
                        return;
                    }
                    return;
                case OP_ENABLE_AUTO_UPGRADE:
                    IOnReceiveListener iOnReceiveListener5 = this.mListener;
                    if (iOnReceiveListener5 != null) {
                        iOnReceiveListener5.onAutoUpgradeEnable();
                        return;
                    }
                    return;
                case OP_SETTING_SCHEDULE:
                    IOnReceiveListener iOnReceiveListener6 = this.mListener;
                    if (iOnReceiveListener6 != null) {
                        iOnReceiveListener6.onSettingSchedule();
                        return;
                    }
                    return;
                case OP_SHOW_UPGRADE_INFO:
                    IOnReceiveListener iOnReceiveListener7 = this.mListener;
                    if (iOnReceiveListener7 != null) {
                        iOnReceiveListener7.onShowUpgrade();
                        return;
                    }
                    return;
                case OP_SCHEDULE:
                    IOnReceiveListener iOnReceiveListener8 = this.mListener;
                    if (iOnReceiveListener8 != null) {
                        iOnReceiveListener8.onSchedule();
                        return;
                    }
                    return;
                case OP_CALL_CS:
                    IOnReceiveListener iOnReceiveListener9 = this.mListener;
                    if (iOnReceiveListener9 != null) {
                        iOnReceiveListener9.onCallCustomerService();
                        return;
                    }
                    return;
                case OP_SHOW_MAIN:
                    IOnReceiveListener iOnReceiveListener10 = this.mListener;
                    if (iOnReceiveListener10 != null) {
                        iOnReceiveListener10.onShowMain();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
