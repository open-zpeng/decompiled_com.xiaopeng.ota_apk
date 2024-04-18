package com.xiaopeng.ota.helper;

import android.content.Context;
import android.util.Pair;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.activity.FragmentActivity;
import com.xiaopeng.ota.bean.Action;
import com.xiaopeng.ota.bean.UpgradePopupConfig;
import com.xiaopeng.ota.helper.DialogHelper;
import com.xiaopeng.ota.presenter.event.EventPresenter;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.utils.ActivityUtils;
import com.xiaopeng.ota.utils.StringUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.ota.utils.TimeUtils;
import com.xiaopeng.ota.view.dialog.AutoUpgradeDialog;
import com.xiaopeng.ota.view.dialog.BaseDialog;
import com.xiaopeng.ota.view.dialog.ConditionDialog;
import com.xiaopeng.ota.view.dialog.SelectTimeDialog;
import com.xiaopeng.ota.view.dialog.SotaUpgradeDialog;
import com.xiaopeng.ota.view.dialog.TextDialog;
import com.xiaopeng.xui.app.XDialog;
import com.xiaopeng.xui.app.XDialogInterface;
import com.xiaopeng.xui.app.XDialogSystemType;
import java.util.List;
/* loaded from: classes2.dex */
public class DialogHelper {
    private static final String TAG = "DialogHelper";
    private AutoUpgradeDialog mAutoUpgradeDialog;
    private TextDialog mCancelUpgradeConfirmDialog;
    private TextDialog mCloseAutoDialog;
    private Context mContext;
    private TextDialog mExpiredDialog;
    private ConditionDialog mMismatchDialog;
    private SelectTimeDialog mSelectTimeDialog;
    private SotaUpgradeDialog mSotaUpgradeDialog;
    private TextDialog mUpgradePopupDialog;

    /* loaded from: classes2.dex */
    public interface DialogInterface {
        void onCancelClick();

        void onClose();

        void onOkClick();
    }

    /* loaded from: classes2.dex */
    public static class DialogListener implements DialogInterface {
        @Override // com.xiaopeng.ota.helper.DialogHelper.DialogInterface
        public void onCancelClick() {
        }

        @Override // com.xiaopeng.ota.helper.DialogHelper.DialogInterface
        public void onClose() {
        }

        @Override // com.xiaopeng.ota.helper.DialogHelper.DialogInterface
        public void onOkClick() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$1(XDialog xDialog, int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class DialogHelperHolder {
        static final DialogHelper INSTANCE = new DialogHelper();

        private DialogHelperHolder() {
        }
    }

    public static DialogHelper getInstance() {
        return DialogHelperHolder.INSTANCE;
    }

    public void setContext(Context context) {
        this.mContext = context;
        this.mContext.setTheme(R.style.defaultFontBold);
    }

    public void promptCancelUpgradeConfirmDialog(final DialogInterface dialogInterface) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$v3pNjtlXuur2DaFwfjTlznC2XcU
            @Override // java.lang.Runnable
            public final void run() {
                DialogHelper.this.lambda$promptCancelUpgradeConfirmDialog$2$DialogHelper(dialogInterface);
            }
        });
    }

    public /* synthetic */ void lambda$promptCancelUpgradeConfirmDialog$2$DialogHelper(final DialogInterface dialogInterface) {
        if (isDialogShowing(this.mCancelUpgradeConfirmDialog)) {
            return;
        }
        LogUtils.d(TAG, "cancelUpgradeConfirmDialog");
        this.mCancelUpgradeConfirmDialog = new TextDialog(this.mContext);
        this.mCancelUpgradeConfirmDialog.setTitle(ConfigHelper.getString(Constants.ConfigKey.DIALOG_CANCEL_UPGRADE_CONFIRM_TITLE));
        this.mCancelUpgradeConfirmDialog.setContent(ConfigHelper.getString(Constants.ConfigKey.DIALOG_CANCEL_UPGRADE_CONFIRM_CONTENT));
        this.mCancelUpgradeConfirmDialog.setPositiveButton(ConfigHelper.getString(Constants.ConfigKey.BUTTON_OK), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$OvFZ0Y_UAhgtWofzh9MNgGt4HNM
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogHelper.lambda$null$0(DialogHelper.DialogInterface.this, xDialog, i);
            }
        });
        this.mCancelUpgradeConfirmDialog.setNegativeButton(ConfigHelper.getString(Constants.ConfigKey.BUTTON_CANCEL), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$q6_03povrs9rEJFU0u3cuQDlM4s
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogHelper.lambda$null$1(xDialog, i);
            }
        });
        this.mCancelUpgradeConfirmDialog.getDialog().setCancelable(false);
        this.mCancelUpgradeConfirmDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$0(DialogInterface dialogInterface, XDialog xDialog, int i) {
        if (dialogInterface != null) {
            dialogInterface.onOkClick();
        }
    }

    public void promptAutoUpgrade(final DialogInterface dialogInterface) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$zAoSo4WI1slR6npA7VK2-gC8hRg
            @Override // java.lang.Runnable
            public final void run() {
                DialogHelper.this.lambda$promptAutoUpgrade$5$DialogHelper(dialogInterface);
            }
        });
    }

    public /* synthetic */ void lambda$promptAutoUpgrade$5$DialogHelper(final DialogInterface dialogInterface) {
        if (isDialogShowing(this.mAutoUpgradeDialog)) {
            return;
        }
        LogUtils.d(TAG, "promptAutoUpgrade");
        this.mAutoUpgradeDialog = new AutoUpgradeDialog(this.mContext);
        this.mAutoUpgradeDialog.setPositiveButton(ConfigHelper.getString(Constants.ConfigKey.BUTTON_CONFIRM_OPEN), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$UM-Krb3yrgh7OMald05jqM2Hyv0
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogHelper.lambda$null$3(DialogHelper.DialogInterface.this, xDialog, i);
            }
        });
        this.mAutoUpgradeDialog.setNegativeButton(ConfigHelper.getString(Constants.ConfigKey.BUTTON_CANCEL), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$k3dM6dB2fDgtxZlYSL0E4uGQB4g
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogHelper.lambda$null$4(DialogHelper.DialogInterface.this, xDialog, i);
            }
        });
        this.mAutoUpgradeDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$3(DialogInterface dialogInterface, XDialog xDialog, int i) {
        OTAManager.getPreferencesManager().setAutoUpgrade(true);
        ActivityHelper.startAutoUpgrade();
        ActivityHelper.sendAutoUpgrade(Action.AUTO_UPGRADE);
        if (dialogInterface != null) {
            dialogInterface.onOkClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$4(DialogInterface dialogInterface, XDialog xDialog, int i) {
        if (dialogInterface != null) {
            dialogInterface.onCancelClick();
        }
    }

    public void closeAutoUpgrade(final DialogInterface dialogInterface) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$wOTC590dvlGPnYQ43DaH1Qe8uws
            @Override // java.lang.Runnable
            public final void run() {
                DialogHelper.this.lambda$closeAutoUpgrade$8$DialogHelper(dialogInterface);
            }
        });
    }

    public /* synthetic */ void lambda$closeAutoUpgrade$8$DialogHelper(final DialogInterface dialogInterface) {
        if (isDialogShowing(this.mCloseAutoDialog)) {
            return;
        }
        LogUtils.d(TAG, "closeAutoUpgrade");
        this.mCloseAutoDialog = new TextDialog(this.mContext);
        this.mCloseAutoDialog.setTitle(ConfigHelper.getString(Constants.ConfigKey.DIALOG_CLOSE_AUTO_TITLE));
        String string = ConfigHelper.getString(Constants.ConfigKey.DEFAULT_SCHEDULE_TIME);
        this.mCloseAutoDialog.setContent(StringUtils.format(Constants.ConfigKey.DIALOG_CLOSE_AUTO_CONTENT_FORMAT, TimeUtils.getReminder(string), string));
        this.mCloseAutoDialog.setPositiveButton(ConfigHelper.getString(Constants.ConfigKey.BUTTON_CONFIRM_CLOSE), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$BNx5Nbbwg8h57z45GHkm8Yn0NFI
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogHelper.lambda$null$6(DialogHelper.DialogInterface.this, xDialog, i);
            }
        });
        this.mCloseAutoDialog.setNegativeButton(ConfigHelper.getString(Constants.ConfigKey.BUTTON_CANCEL), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$NpcOXKBFTvXbH8UBQBiECgnsdTk
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogHelper.lambda$null$7(DialogHelper.DialogInterface.this, xDialog, i);
            }
        });
        this.mCloseAutoDialog.getDialog().setCancelable(false);
        this.mCloseAutoDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$6(DialogInterface dialogInterface, XDialog xDialog, int i) {
        OTAManager.getPreferencesManager().setAutoUpgrade(false);
        ActivityHelper.cancelAutoSchedule();
        ActivityHelper.sendAutoUpgrade(Action.CANCEL_AUTO_SCHEDULE);
        if (dialogInterface != null) {
            dialogInterface.onOkClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$7(DialogInterface dialogInterface, XDialog xDialog, int i) {
        if (dialogInterface != null) {
            dialogInterface.onCancelClick();
        }
    }

    public void promptMismatch(final long j, final List<String> list) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$A4ULkgJ7S851C6v55uvLDLxKxhk
            @Override // java.lang.Runnable
            public final void run() {
                DialogHelper.this.lambda$promptMismatch$10$DialogHelper(list, j);
            }
        });
    }

    public /* synthetic */ void lambda$promptMismatch$10$DialogHelper(List list, final long j) {
        if (isDialogShowing(this.mMismatchDialog)) {
            return;
        }
        LogUtils.d(TAG, "promptMismatch");
        this.mMismatchDialog = new ConditionDialog(this.mContext);
        this.mMismatchDialog.setMismatchConditions(list);
        this.mMismatchDialog.setOkButtonListener(new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$jN-chr902dID0aLSJ5EmkvBOWP8
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                EventPresenter.getInstance().sendConfirmNotSatisfiedEvent(j);
            }
        });
        this.mMismatchDialog.show();
    }

    public void promptExpired(long j) {
        promptExpired(j, null);
    }

    public void promptExpired(final long j, final DialogInterface dialogInterface) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$3RA6Z6hBGg6MM6TywZT2rIZRxkU
            @Override // java.lang.Runnable
            public final void run() {
                DialogHelper.this.lambda$promptExpired$12$DialogHelper(j, dialogInterface);
            }
        });
    }

    public /* synthetic */ void lambda$promptExpired$12$DialogHelper(final long j, final DialogInterface dialogInterface) {
        ActivityHelper.campaignCancel(CampaignFeatureHelper.getMainFragmentClass());
        if (isDialogShowing(this.mExpiredDialog)) {
            return;
        }
        LogUtils.d(TAG, "promptExpired");
        this.mExpiredDialog = new TextDialog(this.mContext);
        this.mExpiredDialog.setTitle(ConfigHelper.getString(Constants.ConfigKey.DIALOG_TITLE_EXPIRED));
        this.mExpiredDialog.setContent(ConfigHelper.getString(Constants.ConfigKey.EXPIRED_CONTENT));
        this.mExpiredDialog.setPositiveButton(ConfigHelper.getString(Constants.ConfigKey.BUTTON_OK), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$97H41t7xLOrpNoe327C7Nm1Ao0g
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogHelper.lambda$null$11(j, dialogInterface, xDialog, i);
            }
        });
        this.mExpiredDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$11(long j, DialogInterface dialogInterface, XDialog xDialog, int i) {
        EventPresenter.getInstance().sendConfirmExpiredEvent(j);
        if (dialogInterface != null) {
            dialogInterface.onOkClick();
        }
    }

    public boolean isUpgradePopupDialogShowing() {
        return isDialogShowing(this.mUpgradePopupDialog) || isDialogShowing(this.mSelectTimeDialog);
    }

    public void promptUpgradeDialog(final Campaign campaign, final UpgradePopupConfig.PopupStrategy popupStrategy, final UpgradePopupConfig upgradePopupConfig) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$BA7ZSTZkm3URf2Yz37VFVaKdkFE
            @Override // java.lang.Runnable
            public final void run() {
                DialogHelper.this.lambda$promptUpgradeDialog$16$DialogHelper(upgradePopupConfig, campaign, popupStrategy);
            }
        });
    }

    public /* synthetic */ void lambda$promptUpgradeDialog$16$DialogHelper(final UpgradePopupConfig upgradePopupConfig, final Campaign campaign, UpgradePopupConfig.PopupStrategy popupStrategy) {
        if (ActivityUtils.isForeground(this.mContext, FragmentActivity.class)) {
            LogUtils.d(TAG, "FragmentActivity is shown, can not popup");
        } else if (isUpgradePopupDialogShowing()) {
        } else {
            LogUtils.d(TAG, "promptUpgradeDialog");
            this.mUpgradePopupDialog = new TextDialog(this.mContext);
            this.mUpgradePopupDialog.setSystemDialog(XDialogSystemType.TYPE_SYSTEM_DIALOG);
            this.mUpgradePopupDialog.setCloseVisibility(false);
            this.mUpgradePopupDialog.setTitle(upgradePopupConfig.getPopupTitle());
            this.mUpgradePopupDialog.setContent(upgradePopupConfig.getPopupContent());
            this.mUpgradePopupDialog.setPositiveButton(upgradePopupConfig.getButtonOkText(), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$-9pZgwEWzFXZWCKnT15tQDRLTOA
                @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
                public final void onClick(XDialog xDialog, int i) {
                    DialogHelper.this.lambda$null$13$DialogHelper(campaign, upgradePopupConfig, xDialog, i);
                }
            });
            this.mUpgradePopupDialog.setCanceledOnTouchOutside(false);
            this.mUpgradePopupDialog.setCancelable(true);
            this.mUpgradePopupDialog.setNegativeButton(upgradePopupConfig.getButtonCancelText(), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$ENjVSPP0iWAbfAYGpfv8xMBKInU
                @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
                public final void onClick(XDialog xDialog, int i) {
                    DialogHelper.this.lambda$null$14$DialogHelper(campaign, upgradePopupConfig, xDialog, i);
                }
            });
            this.mUpgradePopupDialog.setOnCountDownListener(new XDialogInterface.OnCountDownListener() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$vswzpAuf8xTzeHvDFhq8DGfVmm0
                @Override // com.xiaopeng.xui.app.XDialogInterface.OnCountDownListener
                public final boolean onCountDown(XDialog xDialog, int i) {
                    return DialogHelper.this.lambda$null$15$DialogHelper(campaign, upgradePopupConfig, xDialog, i);
                }
            });
            this.mUpgradePopupDialog.show(upgradePopupConfig.getCountdownSeconds(), 0);
            UpgradePopupHelper.getInstance().updatePopupTime();
            EventPresenter.getInstance().sendAutoUpgradeDialogPopup(campaign.getCampaignId(), popupStrategy.name());
        }
    }

    public /* synthetic */ void lambda$null$13$DialogHelper(Campaign campaign, UpgradePopupConfig upgradePopupConfig, XDialog xDialog, int i) {
        LogUtils.d(TAG, "Upgrade popup Click OK button");
        performUpgradePopupOkClick(campaign, upgradePopupConfig);
    }

    public /* synthetic */ void lambda$null$14$DialogHelper(Campaign campaign, UpgradePopupConfig upgradePopupConfig, XDialog xDialog, int i) {
        this.mUpgradePopupDialog.dismiss();
        EventPresenter.getInstance().sendAutoUpgradeScheduleTime(campaign.getCampaignId());
        promptSelectTimeDialog(campaign, upgradePopupConfig);
    }

    public /* synthetic */ boolean lambda$null$15$DialogHelper(Campaign campaign, UpgradePopupConfig upgradePopupConfig, XDialog xDialog, int i) {
        LogUtils.d(TAG, "Upgrade popup count down seconds:%d", Integer.valueOf(i));
        if (i <= 0) {
            this.mUpgradePopupDialog.dismiss();
            performUpgradePopupOkClick(campaign, upgradePopupConfig);
        }
        return true;
    }

    private void performUpgradePopupOkClick(Campaign campaign, final UpgradePopupConfig upgradePopupConfig) {
        EventPresenter.getInstance().sendAutoUpgradeAutomatic(campaign.getCampaignId());
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$Ad4FILeyb89eiGgt3EViSFvT9pQ
            @Override // java.lang.Runnable
            public final void run() {
                DialogHelper.lambda$performUpgradePopupOkClick$17(UpgradePopupConfig.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$performUpgradePopupOkClick$17(UpgradePopupConfig upgradePopupConfig) {
        LogUtils.d(TAG, "Set schedule time:%s", upgradePopupConfig.getScheduleTime());
        Pair<Integer, Integer> hourAndMinute = TimeUtils.getHourAndMinute(upgradePopupConfig.getScheduleTime());
        if (hourAndMinute == null) {
            LogUtils.e(TAG, "Convert default schedule time (%s) failed", upgradePopupConfig.getScheduleTime());
        } else {
            ActivityHelper.scheduleUpgrade(((Integer) hourAndMinute.first).intValue(), ((Integer) hourAndMinute.second).intValue(), Constants.Schedule.FROM_POPUP);
        }
    }

    public void dismissUpgradePopupDialog() {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$Y8v6Nnz6AA0ylAvnHLr7iiJBhIw
            @Override // java.lang.Runnable
            public final void run() {
                DialogHelper.this.lambda$dismissUpgradePopupDialog$18$DialogHelper();
            }
        });
    }

    public /* synthetic */ void lambda$dismissUpgradePopupDialog$18$DialogHelper() {
        if (this.mUpgradePopupDialog != null) {
            LogUtils.d(TAG, "dismissUpgradePopupDialog");
            this.mUpgradePopupDialog.dismiss();
        }
    }

    public void promptSelectTimeDialog(Campaign campaign, final UpgradePopupConfig upgradePopupConfig) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$6_F0427OvVbXxZyLCSEeYBTWkws
            @Override // java.lang.Runnable
            public final void run() {
                DialogHelper.this.lambda$promptSelectTimeDialog$20$DialogHelper(upgradePopupConfig);
            }
        });
    }

    public /* synthetic */ void lambda$promptSelectTimeDialog$20$DialogHelper(UpgradePopupConfig upgradePopupConfig) {
        if (isDialogShowing(this.mSelectTimeDialog)) {
            return;
        }
        LogUtils.d(TAG, "promptSelectTimeDialog");
        this.mSelectTimeDialog = new SelectTimeDialog(this.mContext, R.style.XDialogView_Large);
        this.mSelectTimeDialog.setTitle(upgradePopupConfig.getButtonCancelText());
        this.mSelectTimeDialog.setDesc(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_UPGRADE_DESC));
        this.mSelectTimeDialog.setScheduleDefaultTime(upgradePopupConfig.getScheduleTime());
        this.mSelectTimeDialog.setPositiveButton(ConfigHelper.getString(Constants.ConfigKey.BUTTON_CONFIRM), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$FTxsVAktYqedVEBDH7TTwGT3yo8
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                DialogHelper.this.lambda$null$19$DialogHelper(xDialog, i);
            }
        });
        this.mSelectTimeDialog.setCanceledOnTouchOutside(false);
        this.mSelectTimeDialog.show();
    }

    public /* synthetic */ void lambda$null$19$DialogHelper(XDialog xDialog, int i) {
        LogUtils.d(TAG, "Schedule at %d:%d", Integer.valueOf(this.mSelectTimeDialog.getSelectedHour()), Integer.valueOf(this.mSelectTimeDialog.getSelectedMinute()));
        ActivityHelper.scheduleUpgrade(this.mSelectTimeDialog.getSelectedHour(), this.mSelectTimeDialog.getSelectedMinute(), Constants.Schedule.FROM_POPUP);
    }

    public void promptSotaUpgrade(final int i, final int i2) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$RmXcb-SrFUAFAyc5oqZpIy1STLQ
            @Override // java.lang.Runnable
            public final void run() {
                DialogHelper.this.lambda$promptSotaUpgrade$21$DialogHelper(i, i2);
            }
        });
    }

    public /* synthetic */ void lambda$promptSotaUpgrade$21$DialogHelper(int i, int i2) {
        if (isDialogShowing(this.mSotaUpgradeDialog)) {
            LogUtils.d(TAG, "promptSotaUpgrade refresh, totalNumber=%d, currentIndex=%d", Integer.valueOf(i), Integer.valueOf(i2));
            this.mSotaUpgradeDialog.updateDetailTips(StringUtils.format(Constants.ConfigKey.TIPS_SOTA_UPGRADE_DETAIL, Integer.valueOf(i), Integer.valueOf(i2)));
            return;
        }
        LogUtils.d(TAG, "promptSotaUpgrade, totalNumber=%d, currentIndex=%d", Integer.valueOf(i), Integer.valueOf(i2));
        this.mSotaUpgradeDialog = new SotaUpgradeDialog(this.mContext.getApplicationContext());
        this.mSotaUpgradeDialog.updateDetailTips(StringUtils.format(Constants.ConfigKey.TIPS_SOTA_UPGRADE_DETAIL, Integer.valueOf(i), Integer.valueOf(i2)));
        this.mSotaUpgradeDialog.show();
    }

    public void closeSotaUpgrade() {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$ze05-hEjLOOEFbghu9lqu4tBRUw
            @Override // java.lang.Runnable
            public final void run() {
                DialogHelper.this.lambda$closeSotaUpgrade$22$DialogHelper();
            }
        });
    }

    public /* synthetic */ void lambda$closeSotaUpgrade$22$DialogHelper() {
        if (isDialogShowing(this.mSotaUpgradeDialog)) {
            LogUtils.d(TAG, "closeSotaUpgrade");
            this.mSotaUpgradeDialog.dismiss();
        }
    }

    public void closeAll() {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.helper.-$$Lambda$DialogHelper$Nso8aiAxpdL1Fopf3IFT1f1mBV0
            @Override // java.lang.Runnable
            public final void run() {
                DialogHelper.this.lambda$closeAll$23$DialogHelper();
            }
        });
    }

    public /* synthetic */ void lambda$closeAll$23$DialogHelper() {
        LogUtils.d(TAG, "closeAll");
        closeDialog(this.mMismatchDialog);
        closeDialog(this.mExpiredDialog);
        closeDialog(this.mAutoUpgradeDialog);
        closeDialog(this.mCloseAutoDialog);
        closeDialog(this.mCancelUpgradeConfirmDialog);
        closeDialog(this.mUpgradePopupDialog);
        closeDialog(this.mSelectTimeDialog);
    }

    private void closeDialog(BaseDialog baseDialog) {
        if (baseDialog != null) {
            baseDialog.dismiss();
        }
    }

    private boolean isDialogShowing(BaseDialog baseDialog) {
        return baseDialog != null && baseDialog.isShowing();
    }
}
