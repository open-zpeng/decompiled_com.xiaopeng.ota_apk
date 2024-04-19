package com.xiaopeng.ota.presenter.prompt;

import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.presenter.update.bean.UpgradeResult;
import com.xiaopeng.ota.remind.RemindMode;
import com.xiaopeng.ota.sdk.common.util.AppUtils;
import com.xiaopeng.ota.sdk.common.util.JsonUtils;
import com.xiaopeng.ota.sdk.common.util.SystemPropertiesUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.PreferencesUtils;
/* loaded from: classes2.dex */
public class PromptPresenter extends PromptService {
    private static final long AI_SUPPORT_VERSION_CODE = 300;
    private static final String TAG = "PromptPresenter";
    private boolean mAiUseFlag = false;
    private PromptService mPromptService = null;
    private IOnReceiveListener mListener = null;
    private PromptService mAiPromptService = new AiPromptService();
    private PromptService mDialogPromptService = new DialogPromptService();

    public PromptPresenter() {
        checkAiUseFlag();
    }

    private boolean supportAiMessage() {
        try {
            return ((long) ((Integer) AppUtils.getVersionInfo(OTAManager.getContext(), this.AI_ASSISTANT_PKG_NAME).second).intValue()) >= AI_SUPPORT_VERSION_CODE;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtils.e(TAG, "Ai assistant not found");
            return false;
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void setListener(IOnReceiveListener iOnReceiveListener) {
        this.mListener = iOnReceiveListener;
        PromptService promptService = this.mAiPromptService;
        if (promptService != null) {
            promptService.setListener(this.mListener);
        }
        PromptService promptService2 = this.mDialogPromptService;
        if (promptService2 != null) {
            promptService2.setListener(this.mListener);
        }
    }

    private void checkAiUseFlag() {
        boolean supportAiMessage = supportAiMessage();
        if (supportAiMessage) {
            supportAiMessage = ConfigHelper.getBoolean(Constants.ConfigKey.AI_ASSISTANT_ENABLE);
        }
        if (this.mAiUseFlag != supportAiMessage || this.mPromptService == null) {
            if (!supportAiMessage) {
                this.mPromptService = this.mDialogPromptService;
            } else {
                this.mPromptService = this.mAiPromptService;
            }
            IOnReceiveListener iOnReceiveListener = this.mListener;
            if (iOnReceiveListener != null) {
                this.mPromptService.setListener(iOnReceiveListener);
            }
            this.mAiUseFlag = supportAiMessage;
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showCommonUpgrade(Campaign campaign, RemindMode remindMode) {
        checkAiUseFlag();
        PromptService promptService = this.mPromptService;
        if (promptService != null) {
            promptService.showCommonUpgrade(campaign, remindMode);
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showScheduleUpgrade(Campaign campaign, RemindMode remindMode) {
        checkAiUseFlag();
        PromptService promptService = this.mPromptService;
        if (promptService != null) {
            promptService.showScheduleUpgrade(campaign, remindMode);
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showScheduleArrived(Campaign campaign, String str) {
        if (SystemPropertiesUtils.getPowerStatus() != 0) {
            saveMessage(new PromptMessage(3, campaign, str));
            return;
        }
        checkAiUseFlag();
        PromptService promptService = this.mPromptService;
        if (promptService != null) {
            promptService.showScheduleArrived(campaign, str);
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showConditionMiss(Campaign campaign, UpgradeResult upgradeResult) {
        if (upgradeResult != null && UpgradeResult.isUserTrigger(upgradeResult.getFrom())) {
            this.mDialogPromptService.showConditionMiss(campaign, upgradeResult);
        } else if (SystemPropertiesUtils.getPowerStatus() != 0) {
            saveMessage(new PromptMessage(7, campaign, upgradeResult));
        } else {
            checkAiUseFlag();
            PromptService promptService = this.mPromptService;
            if (promptService != null) {
                promptService.showConditionMiss(campaign, upgradeResult);
            }
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showUpgradeSuccess(Campaign campaign, boolean z) {
        if (SystemPropertiesUtils.getPowerStatus() != 0) {
            saveMessage(new PromptMessage(4, campaign, z));
            return;
        }
        checkAiUseFlag();
        PromptService promptService = this.mPromptService;
        if (promptService != null) {
            promptService.showUpgradeSuccess(campaign, z);
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showUpgradeFail(Campaign campaign, UpgradeResult upgradeResult) {
        if (SystemPropertiesUtils.getPowerStatus() != 0) {
            saveMessage(new PromptMessage(5, campaign, upgradeResult));
        } else if (upgradeResult != null && upgradeResult.isUserTrigger()) {
            this.mDialogPromptService.showUpgradeFail(campaign, upgradeResult);
        } else {
            checkAiUseFlag();
            PromptService promptService = this.mPromptService;
            if (promptService != null) {
                promptService.showUpgradeFail(campaign, upgradeResult);
            }
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void showVerifyFail(Campaign campaign, boolean z) {
        if (SystemPropertiesUtils.getPowerStatus() != 0) {
            saveMessage(new PromptMessage(6, campaign, z));
            return;
        }
        checkAiUseFlag();
        PromptService promptService = this.mPromptService;
        if (promptService != null) {
            promptService.showVerifyFail(campaign, z);
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void closeAllMessage() {
        checkAiUseFlag();
        PromptService promptService = this.mPromptService;
        if (promptService != null) {
            promptService.closeAllMessage();
        }
        removeWaitingMessage();
    }

    public void handleByPowerOn() {
        if (PreferencesUtils.contains(OTAManager.getContext(), "message.waiting_message")) {
            LogUtils.d(TAG, "handleByPowerOn handle message");
            String string = PreferencesUtils.getString(OTAManager.getContext(), "message.waiting_message", null);
            removeWaitingMessage();
            if (TextUtils.isEmpty(string)) {
                LogUtils.d(TAG, "Wait message is empty");
                return;
            }
            PromptMessage promptMessage = (PromptMessage) JsonUtils.fromJson(string, (Class<Object>) PromptMessage.class);
            if (promptMessage.getType() == 0) {
                LogUtils.d(TAG, "Message type unknown");
                return;
            }
            Campaign campaign = OTAManager.getCampaignManager().getCampaign(promptMessage.getCampaignId());
            if (campaign == null) {
                LogUtils.d(TAG, "Campaign %d not found", Long.valueOf(promptMessage.getCampaignId()));
                return;
            }
            checkAiUseFlag();
            if (this.mPromptService != null) {
                int type = promptMessage.getType();
                if (type == 3) {
                    this.mPromptService.showScheduleArrived(campaign, promptMessage.getScheduleTime());
                } else if (type == 4) {
                    this.mPromptService.showUpgradeSuccess(campaign, promptMessage.isSchedule());
                } else if (type == 5) {
                    this.mPromptService.showUpgradeFail(campaign, promptMessage.getUpgradeResult());
                } else if (type == 6) {
                    this.mPromptService.showVerifyFail(campaign, promptMessage.isSchedule());
                } else if (type != 7) {
                } else {
                    this.mPromptService.showConditionMiss(campaign, promptMessage.getUpgradeResult());
                }
            }
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.PromptService
    public void dispose() {
        PromptService promptService = this.mPromptService;
        if (promptService != null) {
            promptService.dispose();
        }
        PromptService promptService2 = this.mDialogPromptService;
        if (promptService2 != null) {
            promptService2.dispose();
        }
        PromptService promptService3 = this.mAiPromptService;
        if (promptService3 != null) {
            promptService3.dispose();
        }
    }
}
