package com.xiaopeng.ota.presenter.prompt;

import android.text.TextUtils;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.bean.AiAction;
import com.xiaopeng.ota.bean.AiScene;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.helper.ScheduleHelper;
import com.xiaopeng.ota.helper.TextToSpeechHelper;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.presenter.update.bean.UpgradeResult;
import com.xiaopeng.ota.presenter.update.bean.UpgradeTrigger;
import com.xiaopeng.ota.remind.RemindMode;
import com.xiaopeng.ota.sdk.common.util.ThreadUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.view.dialog.TextDialog;
import com.xiaopeng.xui.app.XDialog;
import com.xiaopeng.xui.app.XDialogInterface;
/* loaded from: classes2.dex */
public class DialogPromptService extends PromptService {
    private static final String CURRENT_SCENE_KEY = "dialog_current_scene";
    private static final String TAG = "DialogPromptService";
    private IOnReceiveListener mListener;
    private TextDialog mPromptDialog;

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void setListener(IOnReceiveListener iOnReceiveListener) {
        this.mListener = iOnReceiveListener;
    }

    private void createDialog() {
        TextDialog textDialog = this.mPromptDialog;
        if (textDialog != null) {
            textDialog.dismiss();
        }
        OTAManager.getContext().setTheme(R.style.defaultFontBold);
        this.mPromptDialog = new TextDialog(OTAManager.getContext());
    }

    private void sendPromptEvent(AiScene aiScene, long j) {
        if (this.mListener != null) {
            AiContent aiContent = new AiContent(aiScene, AiAction.NONE, j);
            aiContent.setDialog(true);
            this.mListener.onSendPromptEvent(aiContent);
        }
    }

    private void sendActionEvent(AiContent aiContent) {
        if (this.mListener != null) {
            aiContent.setDialog(true);
            this.mListener.onSendAiResponseEvent(aiContent);
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showCommonUpgrade(Campaign campaign, RemindMode remindMode) {
        if (campaign.isSupportSchedule()) {
            if (ScheduleHelper.getInstance().isSetAutoScheduled()) {
                showAutoUpgrade(campaign, remindMode);
                return;
            } else {
                showScheduleUpgrade(campaign, remindMode);
                return;
            }
        }
        showLocalUpgrade(campaign, remindMode);
    }

    public void showLocalUpgrade(final Campaign campaign, RemindMode remindMode) {
        LogUtils.d(TAG, "showLocalUpgrade(mode=%d,campaignId=%d)", Integer.valueOf(remindMode.getRemindMode()), Long.valueOf(campaign.getCampaignId()));
        final String formattedWithCampaign = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_LOCAL_UPGRADE_TITLE, campaign);
        final String formattedWithCampaign2 = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_LOCAL_UPGRADE_CONTENT, campaign);
        final String string = ConfigHelper.getString(Constants.ConfigKey.BUTTON_ALL_RIGHT);
        final String string2 = ConfigHelper.getString(Constants.ConfigKey.BUTTON_DETAIL);
        final AiScene convertToScene = convertToScene(remindMode, campaign);
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$Z6Ktg0obH9L32VR_Ppmv3CA7L5Y
            @Override // java.lang.Runnable
            public final void run() {
                DialogPromptService.this.lambda$showLocalUpgrade$2$DialogPromptService(formattedWithCampaign, formattedWithCampaign2, string, convertToScene, campaign, string2);
            }
        });
    }

    public /* synthetic */ void lambda$showLocalUpgrade$2$DialogPromptService(String str, String str2, String str3, final AiScene aiScene, final Campaign campaign, String str4) {
        createDialog();
        this.mPromptDialog.setTitle(str);
        this.mPromptDialog.setContent(str2);
        if (TextUtils.isEmpty(str2)) {
            this.mPromptDialog.setTitle(ConfigHelper.getString(Constants.ConfigKey.TITLE_UPGRADE));
            this.mPromptDialog.setContent(str);
        }
        this.mPromptDialog.setPositiveButton(str3, new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$P57lrTrxF-t2ji55t_V7I9fl0Sc
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogPromptService.this.lambda$null$0$DialogPromptService(aiScene, campaign, xDialog, i);
            }
        });
        this.mPromptDialog.setNegativeButton(str4, new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$nQ3KZ-W7c9uSqbn7YUnj-VJJbBk
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogPromptService.this.lambda$null$1$DialogPromptService(aiScene, campaign, xDialog, i);
            }
        });
        this.mPromptDialog.getDialog().setCanceledOnTouchOutside(false);
        this.mPromptDialog.show();
        showTts(ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_LOCAL_UPGRADE_TTS, campaign));
        sendPromptEvent(aiScene, campaign.getCampaignId());
    }

    public /* synthetic */ void lambda$null$0$DialogPromptService(AiScene aiScene, Campaign campaign, XDialog xDialog, int i) {
        sendActionEvent(new AiContent(aiScene, AiAction.OP_UPGRADE_NOW, campaign.getCampaignId()));
        IOnReceiveListener iOnReceiveListener = this.mListener;
        if (iOnReceiveListener != null) {
            iOnReceiveListener.onUpgradeNow();
        }
    }

    public /* synthetic */ void lambda$null$1$DialogPromptService(AiScene aiScene, Campaign campaign, XDialog xDialog, int i) {
        sendActionEvent(new AiContent(aiScene, AiAction.OP_SHOW_UPGRADE_INFO, campaign.getCampaignId()));
        IOnReceiveListener iOnReceiveListener = this.mListener;
        if (iOnReceiveListener != null) {
            iOnReceiveListener.onShowUpgrade();
        }
    }

    private void showAutoUpgrade(final Campaign campaign, RemindMode remindMode) {
        LogUtils.d(TAG, "showAutoUpgrade(mode=%d,campaignId=%d)", Integer.valueOf(remindMode.getRemindMode()), Long.valueOf(campaign.getCampaignId()));
        final String formattedWithCampaign = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_AUTO_UPGRADE_TITLE, campaign);
        final String formattedWithCampaign2 = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_AUTO_UPGRADE_CONTENT, campaign);
        final String string = ConfigHelper.getString(Constants.ConfigKey.BUTTON_ALL_RIGHT);
        final String string2 = ConfigHelper.getString(Constants.ConfigKey.BUTTON_DETAIL);
        final AiScene convertToScene = convertToScene(remindMode, campaign);
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$hyRil9t5J1f18YqAIm27NYzvTVo
            @Override // java.lang.Runnable
            public final void run() {
                DialogPromptService.this.lambda$showAutoUpgrade$5$DialogPromptService(formattedWithCampaign, formattedWithCampaign2, string, convertToScene, campaign, string2);
            }
        });
    }

    public /* synthetic */ void lambda$showAutoUpgrade$5$DialogPromptService(String str, String str2, String str3, final AiScene aiScene, final Campaign campaign, String str4) {
        createDialog();
        this.mPromptDialog.setTitle(str);
        this.mPromptDialog.setContent(str2);
        if (TextUtils.isEmpty(str2)) {
            this.mPromptDialog.setTitle(ConfigHelper.getString(Constants.ConfigKey.TITLE_UPGRADE));
            this.mPromptDialog.setContent(str);
        }
        this.mPromptDialog.setPositiveButton(str3, new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$LLSCsSRYyVpSPHn8yik9A2X33FE
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogPromptService.this.lambda$null$3$DialogPromptService(aiScene, campaign, xDialog, i);
            }
        });
        this.mPromptDialog.setNegativeButton(str4, new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$Bnb6R_myu7klcSz51ZZaF5POA0M
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogPromptService.this.lambda$null$4$DialogPromptService(aiScene, campaign, xDialog, i);
            }
        });
        this.mPromptDialog.getDialog().setCanceledOnTouchOutside(false);
        this.mPromptDialog.show();
        showTts(ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_AUTO_UPGRADE_TTS, campaign));
        sendPromptEvent(aiScene, campaign.getCampaignId());
    }

    public /* synthetic */ void lambda$null$3$DialogPromptService(AiScene aiScene, Campaign campaign, XDialog xDialog, int i) {
        sendActionEvent(new AiContent(aiScene, AiAction.OP_UPGRADE_NOW, campaign.getCampaignId()));
        IOnReceiveListener iOnReceiveListener = this.mListener;
        if (iOnReceiveListener != null) {
            iOnReceiveListener.onUpgradeNow();
        }
    }

    public /* synthetic */ void lambda$null$4$DialogPromptService(AiScene aiScene, Campaign campaign, XDialog xDialog, int i) {
        sendActionEvent(new AiContent(aiScene, AiAction.OP_SHOW_UPGRADE_INFO, campaign.getCampaignId()));
        IOnReceiveListener iOnReceiveListener = this.mListener;
        if (iOnReceiveListener != null) {
            iOnReceiveListener.onShowUpgrade();
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showScheduleUpgrade(final Campaign campaign, RemindMode remindMode) {
        LogUtils.d(TAG, "showScheduleUpgrade(mode=%d,campaignId=%d)", Integer.valueOf(remindMode.getRemindMode()), Long.valueOf(campaign.getCampaignId()));
        final String formattedWithCampaign = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_SCHEDULE_UPGRADE_TITLE, campaign);
        final String formattedWithCampaign2 = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_SCHEDULE_UPGRADE_CONTENT, campaign);
        final String string = ConfigHelper.getString(Constants.ConfigKey.BUTTON_ALL_RIGHT);
        final String string2 = ConfigHelper.getString(Constants.ConfigKey.BUTTON_DETAIL);
        final AiScene convertToScene = convertToScene(remindMode, campaign);
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$RQ9csw8cQwiuAHytfWX1DAuBdTY
            @Override // java.lang.Runnable
            public final void run() {
                DialogPromptService.this.lambda$showScheduleUpgrade$8$DialogPromptService(formattedWithCampaign, formattedWithCampaign2, string, convertToScene, campaign, string2);
            }
        });
    }

    public /* synthetic */ void lambda$showScheduleUpgrade$8$DialogPromptService(String str, String str2, String str3, final AiScene aiScene, final Campaign campaign, String str4) {
        createDialog();
        this.mPromptDialog.setTitle(str);
        this.mPromptDialog.setContent(str2);
        if (TextUtils.isEmpty(str2)) {
            this.mPromptDialog.setTitle(ConfigHelper.getString(Constants.ConfigKey.TITLE_UPGRADE));
            this.mPromptDialog.setContent(str);
        }
        this.mPromptDialog.setPositiveButton(str3, new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$MFRPOu0IGMXAX5oUYt_0DYl1zNQ
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogPromptService.this.lambda$null$6$DialogPromptService(aiScene, campaign, xDialog, i);
            }
        });
        this.mPromptDialog.setNegativeButton(str4, new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$vaNbY-skNdxOP60LKJ54bSwOO-I
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogPromptService.this.lambda$null$7$DialogPromptService(aiScene, campaign, xDialog, i);
            }
        });
        this.mPromptDialog.getDialog().setCanceledOnTouchOutside(false);
        this.mPromptDialog.show();
        showTts(ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_SCHEDULE_UPGRADE_TTS, campaign));
        sendPromptEvent(aiScene, campaign.getCampaignId());
    }

    public /* synthetic */ void lambda$null$6$DialogPromptService(AiScene aiScene, Campaign campaign, XDialog xDialog, int i) {
        sendActionEvent(new AiContent(aiScene, AiAction.OP_SETTING_SCHEDULE, campaign.getCampaignId()));
        IOnReceiveListener iOnReceiveListener = this.mListener;
        if (iOnReceiveListener != null) {
            iOnReceiveListener.onScheduleDefault(false);
        }
    }

    public /* synthetic */ void lambda$null$7$DialogPromptService(AiScene aiScene, Campaign campaign, XDialog xDialog, int i) {
        sendActionEvent(new AiContent(aiScene, AiAction.OP_SHOW_UPGRADE_INFO, campaign.getCampaignId()));
        IOnReceiveListener iOnReceiveListener = this.mListener;
        if (iOnReceiveListener != null) {
            iOnReceiveListener.onShowUpgrade();
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showScheduleArrived(final Campaign campaign, String str) {
        LogUtils.d(TAG, "showScheduleArrived(campaignId=%d)", Long.valueOf(campaign.getCampaignId()));
        final String string = ConfigHelper.getString(Constants.ConfigKey.PROMPT_SCHEDULE_UPGRADE_TIMES_UP_CONTENT);
        final String string2 = ConfigHelper.getString(Constants.ConfigKey.BUTTON_ALL_RIGHT);
        final String string3 = ConfigHelper.getString(Constants.ConfigKey.BUTTON_MODIFY_TIME);
        final AiScene aiScene = AiScene.SCHEDULE_ARRIVED;
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$ODjacsjp0Fnw-QlokLUmXnjq690
            @Override // java.lang.Runnable
            public final void run() {
                DialogPromptService.this.lambda$showScheduleArrived$11$DialogPromptService(string, string2, aiScene, campaign, string3);
            }
        });
    }

    public /* synthetic */ void lambda$showScheduleArrived$11$DialogPromptService(String str, String str2, final AiScene aiScene, final Campaign campaign, String str3) {
        createDialog();
        this.mPromptDialog.setTitle(ConfigHelper.getString(Constants.ConfigKey.TITLE_SCHEDULE));
        this.mPromptDialog.setContent(str);
        this.mPromptDialog.setPositiveButton(str2, new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$hGORKZLtdC8lE3sv5TLcKC9R9O0
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogPromptService.this.lambda$null$9$DialogPromptService(aiScene, campaign, xDialog, i);
            }
        });
        this.mPromptDialog.setNegativeButton(str3, new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$7JkyW2O47iwpePXGRapuKCnT3TM
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogPromptService.this.lambda$null$10$DialogPromptService(aiScene, campaign, xDialog, i);
            }
        });
        this.mPromptDialog.getDialog().setCanceledOnTouchOutside(false);
        this.mPromptDialog.show();
        showTts(ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_SCHEDULE_UPGRADE_TIMES_UP_TTS, campaign));
        sendPromptEvent(aiScene, campaign.getCampaignId());
    }

    public /* synthetic */ void lambda$null$9$DialogPromptService(AiScene aiScene, Campaign campaign, XDialog xDialog, int i) {
        sendActionEvent(new AiContent(aiScene, AiAction.OP_UPGRADE_NOW, campaign.getCampaignId()));
        IOnReceiveListener iOnReceiveListener = this.mListener;
        if (iOnReceiveListener != null) {
            iOnReceiveListener.onUpgradeNow();
        }
    }

    public /* synthetic */ void lambda$null$10$DialogPromptService(AiScene aiScene, Campaign campaign, XDialog xDialog, int i) {
        sendActionEvent(new AiContent(aiScene, AiAction.OP_SETTING_SCHEDULE, campaign.getCampaignId()));
        IOnReceiveListener iOnReceiveListener = this.mListener;
        if (iOnReceiveListener != null) {
            iOnReceiveListener.onSettingSchedule();
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showConditionMiss(Campaign campaign, UpgradeResult upgradeResult) {
        String code = UpgradeTrigger.SCHEDULE.getCode();
        if (upgradeResult != null) {
            code = upgradeResult.getFrom();
        }
        String assembleConditionMissContent = assembleConditionMissContent(upgradeResult);
        if (upgradeResult != null && upgradeResult.isEcuNotReady()) {
            showContactCustomerService(campaign, code, assembleConditionMissContent);
        } else {
            showConditionMissDialog(campaign, code, assembleConditionMissContent);
        }
    }

    private void showContactCustomerService(final Campaign campaign, String str, String str2) {
        final String formattedWithCampaign = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_DIALOG_CONDITION_MISS_CUSTOMER_SERVICE_TITLE, campaign);
        if (TextUtils.isEmpty(str2)) {
            str2 = ConfigHelper.getString(Constants.ConfigKey.PROMPT_DIALOG_CONDITION_MISS_CONTENT);
        }
        final String str3 = str2;
        final AiScene aiScene = AiScene.COMMON_FAIL_RETRY;
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$QG4niSNP1qTPBKbk-rZtFDg36VY
            @Override // java.lang.Runnable
            public final void run() {
                DialogPromptService.this.lambda$showContactCustomerService$13$DialogPromptService(formattedWithCampaign, str3, aiScene, campaign);
            }
        });
    }

    public /* synthetic */ void lambda$showContactCustomerService$13$DialogPromptService(String str, String str2, final AiScene aiScene, final Campaign campaign) {
        createDialog();
        String string = ConfigHelper.getString(Constants.ConfigKey.BUTTON_CALL_CS);
        String string2 = ConfigHelper.getString(Constants.ConfigKey.BUTTON_CANCEL);
        this.mPromptDialog.setTitle(str);
        this.mPromptDialog.setContent(str2);
        this.mPromptDialog.setPositiveButton(string, new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$qnGmwA19-WAXoMHyhRKioon3biw
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogPromptService.this.lambda$null$12$DialogPromptService(aiScene, campaign, xDialog, i);
            }
        });
        this.mPromptDialog.setNegativeButton(string2);
        this.mPromptDialog.getDialog().setCanceledOnTouchOutside(false);
        this.mPromptDialog.show();
        showTts(str2);
        sendPromptEvent(aiScene, campaign.getCampaignId());
    }

    public /* synthetic */ void lambda$null$12$DialogPromptService(AiScene aiScene, Campaign campaign, XDialog xDialog, int i) {
        sendActionEvent(new AiContent(aiScene, AiAction.OP_CALL_CS, campaign.getCampaignId()));
        IOnReceiveListener iOnReceiveListener = this.mListener;
        if (iOnReceiveListener != null) {
            iOnReceiveListener.onCallCustomerService();
        }
    }

    private void showConditionMissDialog(final Campaign campaign, String str, String str2) {
        final String formattedWithCampaign = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_DIALOG_CONDITION_MISS_TITLE, campaign);
        if (TextUtils.isEmpty(str2)) {
            str2 = ConfigHelper.getString(Constants.ConfigKey.PROMPT_DIALOG_CONDITION_MISS_CONTENT);
        }
        final String str3 = str2;
        final AiScene aiScene = AiScene.COMMON_FAIL_RETRY;
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$z3oOA1row4s3NeOtvbkvXm3ooio
            @Override // java.lang.Runnable
            public final void run() {
                DialogPromptService.this.lambda$showConditionMissDialog$15$DialogPromptService(formattedWithCampaign, str3, aiScene, campaign);
            }
        });
    }

    public /* synthetic */ void lambda$showConditionMissDialog$15$DialogPromptService(String str, String str2, final AiScene aiScene, final Campaign campaign) {
        createDialog();
        String string = ConfigHelper.getString(Constants.ConfigKey.PROMPT_DIALOG_CONDITION_MISS_POSITIVE_BUTTON);
        String string2 = ConfigHelper.getString(Constants.ConfigKey.BUTTON_CANCEL);
        this.mPromptDialog.setTitle(str);
        this.mPromptDialog.setContent(str2);
        this.mPromptDialog.setPositiveButton(string, new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$m3oKmaKNjNv1IA1LW5BLekp5pjw
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogPromptService.this.lambda$null$14$DialogPromptService(aiScene, campaign, xDialog, i);
            }
        });
        this.mPromptDialog.setNegativeButton(string2);
        this.mPromptDialog.getDialog().setCanceledOnTouchOutside(false);
        this.mPromptDialog.show();
        showTts(str2);
        sendPromptEvent(aiScene, campaign.getCampaignId());
    }

    public /* synthetic */ void lambda$null$14$DialogPromptService(AiScene aiScene, Campaign campaign, XDialog xDialog, int i) {
        sendActionEvent(new AiContent(aiScene, AiAction.OP_UPGRADE_NOW, campaign.getCampaignId()));
        IOnReceiveListener iOnReceiveListener = this.mListener;
        if (iOnReceiveListener != null) {
            iOnReceiveListener.onUpgradeNow();
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showUpgradeSuccess(final Campaign campaign, boolean z) {
        LogUtils.d(TAG, "showUpgradeSuccess(campaignId=%d, schedule=%b)", Long.valueOf(campaign.getCampaignId()), Boolean.valueOf(z));
        final String formattedWithCampaign = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_UPGRADE_SUCCESS_TITLE, campaign);
        final String formattedWithCampaign2 = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_UPGRADE_SUCCESS_CONTENT, campaign);
        String formatWithCampaign = ConfigHelper.formatWithCampaign(campaign.getUpgradedPrompt(), campaign);
        if (TextUtils.isEmpty(formatWithCampaign)) {
            formatWithCampaign = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_UPGRADE_SUCCESS_TTS, campaign);
        }
        final String str = formatWithCampaign;
        final AiScene aiScene = z ? AiScene.SCHEDULE_SUCCESS : AiScene.COMMON_SUCCESS;
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$3aOyo9ZppOBEy2coOrjEC--D6m0
            @Override // java.lang.Runnable
            public final void run() {
                DialogPromptService.this.lambda$showUpgradeSuccess$16$DialogPromptService(formattedWithCampaign, formattedWithCampaign2, str, aiScene, campaign);
            }
        });
    }

    public /* synthetic */ void lambda$showUpgradeSuccess$16$DialogPromptService(String str, String str2, String str3, AiScene aiScene, Campaign campaign) {
        createDialog();
        this.mPromptDialog.setTitle(str);
        this.mPromptDialog.setContent(str2);
        this.mPromptDialog.setPositiveButton(ConfigHelper.getString(Constants.ConfigKey.BUTTON_OK));
        this.mPromptDialog.getDialog().setCanceledOnTouchOutside(false);
        this.mPromptDialog.show();
        showTts(str3);
        sendPromptEvent(aiScene, campaign.getCampaignId());
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showUpgradeFail(final Campaign campaign, UpgradeResult upgradeResult) {
        boolean z = false;
        LogUtils.d(TAG, "showUpgradeFail(campaignId=%d)", Long.valueOf(campaign.getCampaignId()));
        final String formattedWithCampaign = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_DIALOG_UPGRADE_FAIL_TITLE, campaign);
        final String formattedWithCampaign2 = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_DIALOG_UPGRADE_FAIL_CONTENT, campaign);
        final String formattedWithCampaign3 = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_DIALOG_UPGRADE_FAIL_TTS, campaign);
        if (upgradeResult == null || !upgradeResult.isUserTrigger()) {
            z = true;
        }
        final AiScene convertToScene = convertToScene(z, true);
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$aHG_AvaatltqqA9DxDXuT3zbAXI
            @Override // java.lang.Runnable
            public final void run() {
                DialogPromptService.this.lambda$showUpgradeFail$19$DialogPromptService(formattedWithCampaign, formattedWithCampaign2, r4, convertToScene, campaign, formattedWithCampaign3);
            }
        });
    }

    public /* synthetic */ void lambda$showUpgradeFail$19$DialogPromptService(String str, String str2, boolean z, final AiScene aiScene, final Campaign campaign, String str3) {
        createDialog();
        this.mPromptDialog.setTitle(str);
        this.mPromptDialog.setContent(str2);
        if (z) {
            String string = ConfigHelper.getString(Constants.ConfigKey.PROMPT_DIALOG_UPGRADE_FAIL_BUTTON_POSITIVE);
            String string2 = ConfigHelper.getString(Constants.ConfigKey.BUTTON_CALL_CS);
            this.mPromptDialog.setPositiveButton(string, new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$3IWQhnwKutC5Kso3XBzmrLCxqAw
                @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
                public final void onClick(XDialog xDialog, int i) {
                    DialogPromptService.this.lambda$null$17$DialogPromptService(aiScene, campaign, xDialog, i);
                }
            });
            this.mPromptDialog.setNegativeButton(string2, new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$hcUv9Qh-qYMe0W5K2nQTt7-KzuE
                @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
                public final void onClick(XDialog xDialog, int i) {
                    DialogPromptService.this.lambda$null$18$DialogPromptService(aiScene, campaign, xDialog, i);
                }
            });
        } else {
            this.mPromptDialog.setPositiveButton(ConfigHelper.getString(Constants.ConfigKey.BUTTON_OK));
        }
        this.mPromptDialog.getDialog().setCanceledOnTouchOutside(false);
        this.mPromptDialog.show();
        showTts(str3);
        sendPromptEvent(aiScene, campaign.getCampaignId());
    }

    public /* synthetic */ void lambda$null$17$DialogPromptService(AiScene aiScene, Campaign campaign, XDialog xDialog, int i) {
        sendActionEvent(new AiContent(aiScene, AiAction.OP_UPGRADE_NOW, campaign.getCampaignId()));
        IOnReceiveListener iOnReceiveListener = this.mListener;
        if (iOnReceiveListener != null) {
            iOnReceiveListener.onUpgradeNow();
        }
    }

    public /* synthetic */ void lambda$null$18$DialogPromptService(AiScene aiScene, Campaign campaign, XDialog xDialog, int i) {
        sendActionEvent(new AiContent(aiScene, AiAction.OP_CALL_CS, campaign.getCampaignId()));
        IOnReceiveListener iOnReceiveListener = this.mListener;
        if (iOnReceiveListener != null) {
            iOnReceiveListener.onCallCustomerService();
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showVerifyFail(final Campaign campaign, final boolean z) {
        LogUtils.d(TAG, "showVerifyFail(schedule=%b)", Boolean.valueOf(z));
        final String formattedWithCampaign = ConfigHelper.getFormattedWithCampaign(Constants.ConfigKey.PROMPT_VERIFY_FAILED_TITLE, campaign);
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$7bbi3NcxUYUKhSR_LTjunSVPLOk
            @Override // java.lang.Runnable
            public final void run() {
                DialogPromptService.this.lambda$showVerifyFail$20$DialogPromptService(formattedWithCampaign, z, campaign);
            }
        });
    }

    public /* synthetic */ void lambda$showVerifyFail$20$DialogPromptService(String str, boolean z, Campaign campaign) {
        AiScene aiScene;
        createDialog();
        this.mPromptDialog.setTitle(str);
        this.mPromptDialog.setContent(null);
        this.mPromptDialog.setPositiveButton(ConfigHelper.getString(Constants.ConfigKey.BUTTON_OK));
        this.mPromptDialog.getDialog().setCancelable(false);
        this.mPromptDialog.show();
        if (z) {
            aiScene = AiScene.SCHEDULE_FAIL_RETRY;
        } else {
            aiScene = AiScene.COMMON_FAIL_RETRY;
        }
        sendPromptEvent(aiScene, campaign.getCampaignId());
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void closeAllMessage() {
        if (this.mPromptDialog != null) {
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$tju91DkubSI_vGDNt5lygMtQYJ4
                @Override // java.lang.Runnable
                public final void run() {
                    DialogPromptService.this.lambda$closeAllMessage$21$DialogPromptService();
                }
            });
        }
    }

    public /* synthetic */ void lambda$closeAllMessage$21$DialogPromptService() {
        this.mPromptDialog.dismiss();
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void dispose() {
        if (this.mPromptDialog != null) {
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.prompt.-$$Lambda$DialogPromptService$fKf9Tcs-VZggLCgJJaqs5AgXjH8
                @Override // java.lang.Runnable
                public final void run() {
                    DialogPromptService.this.lambda$dispose$22$DialogPromptService();
                }
            });
        }
    }

    public /* synthetic */ void lambda$dispose$22$DialogPromptService() {
        this.mPromptDialog.dismiss();
        this.mPromptDialog = null;
    }

    private void showTts(String str) {
        if (!TextUtils.isEmpty(str) && isLocalIgOn()) {
            TextToSpeechHelper.getInstance().speak(str);
        }
    }
}
