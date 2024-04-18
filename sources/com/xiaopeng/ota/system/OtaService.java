package com.xiaopeng.ota.system;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.Nullable;
import com.xiaopeng.fota.sdk.EcuType;
import com.xiaopeng.fota.sdk.UpgradeExecutorBase;
import com.xiaopeng.fota.sdk.UpgradeProgress;
import com.xiaopeng.fota.sdk.UpgradeUtils;
import com.xiaopeng.lib.apirouter.server.Authority;
import com.xiaopeng.lib.apirouter.server.IServicePublisher;
import com.xiaopeng.lib.apirouter.server.Parameter;
import com.xiaopeng.lib.apirouter.server.Path;
import com.xiaopeng.lib.apirouter.server.Publish;
import com.xiaopeng.lib.utils.info.BuildInfoUtils;
import com.xiaopeng.libconfig.ipc.IpcConfig;
import com.xiaopeng.ota.Config;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.VehicleFeature;
import com.xiaopeng.ota.activity.CheckActivity;
import com.xiaopeng.ota.activity.FragmentActivity;
import com.xiaopeng.ota.activity.InstallActivity;
import com.xiaopeng.ota.activity.UpgradeFragment;
import com.xiaopeng.ota.activity.UsbFragment;
import com.xiaopeng.ota.activity.info.InfoFragment;
import com.xiaopeng.ota.auto.CarApi;
import com.xiaopeng.ota.auto.callback.PowerStateListener;
import com.xiaopeng.ota.auto.callback.SrsCallbackAdapter;
import com.xiaopeng.ota.auto.callback.VcuCallbackAdapter;
import com.xiaopeng.ota.auto.service.ICarConnect;
import com.xiaopeng.ota.auto.service.vcu.VcuService;
import com.xiaopeng.ota.bean.Action;
import com.xiaopeng.ota.bean.ActivityEvent;
import com.xiaopeng.ota.bean.InterfaceType;
import com.xiaopeng.ota.bean.RpcResponse;
import com.xiaopeng.ota.bean.UpgradePopupConfig;
import com.xiaopeng.ota.helper.ActivityHelper;
import com.xiaopeng.ota.helper.CampaignFeatureHelper;
import com.xiaopeng.ota.helper.CarServiceHelper;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.helper.DialogHelper;
import com.xiaopeng.ota.helper.ReadDidHelper;
import com.xiaopeng.ota.helper.RoadHelper;
import com.xiaopeng.ota.helper.RpcResponseHelper;
import com.xiaopeng.ota.helper.ScheduleHelper;
import com.xiaopeng.ota.helper.TextToSpeechHelper;
import com.xiaopeng.ota.helper.UpgradeAnimHelper;
import com.xiaopeng.ota.helper.UpgradePopupHelper;
import com.xiaopeng.ota.helper.VehicleHelper;
import com.xiaopeng.ota.presenter.AdbPresenter;
import com.xiaopeng.ota.presenter.event.EventPresenter;
import com.xiaopeng.ota.presenter.prompt.AiContent;
import com.xiaopeng.ota.presenter.prompt.IOnReceiveListener;
import com.xiaopeng.ota.presenter.prompt.PromptPresenter;
import com.xiaopeng.ota.presenter.update.AppExecutor;
import com.xiaopeng.ota.presenter.update.IcmDmcuExecutor;
import com.xiaopeng.ota.presenter.update.McuExecutor;
import com.xiaopeng.ota.presenter.update.UpdateListener;
import com.xiaopeng.ota.presenter.update.UpgradePresenter;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.presenter.update.bean.UpgradeResult;
import com.xiaopeng.ota.presenter.update.cdu.CduExecutor;
import com.xiaopeng.ota.remind.RemindMode;
import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.sdk.common.util.Dictionary;
import com.xiaopeng.ota.sdk.common.util.HexUtils;
import com.xiaopeng.ota.sdk.common.util.SystemPropertiesUtils;
import com.xiaopeng.ota.utils.ActivityUtils;
import com.xiaopeng.ota.utils.EcuDisplayUtils;
import com.xiaopeng.ota.utils.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.StringUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.ota.utils.TimeUtils;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.NotNull;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.SubscriberExceptionEvent;
import org.greenrobot.eventbus.ThreadMode;
@Authority(packageName = "com.xiaopeng.ota", serviceName = "OTAService")
/* loaded from: classes2.dex */
public class OtaService extends Service implements UpdateListener, IOnReceiveListener, IServicePublisher {
    private static final String TAG = "OtaService";
    private DialogHelper mDialogHelper;
    private PromptPresenter mMessagePresenter;
    private StorageManager mStorageManager;
    private UpgradePresenter mUpgradePresenter;
    private UsbDiskListener mUsbDiskListener;
    private int mCurrProgress = 0;
    private VcuCallbackAdapter mVcuCallbackAdapter = new VcuCallbackAdapter() { // from class: com.xiaopeng.ota.system.OtaService.1
        @Override // com.xiaopeng.ota.auto.callback.VcuCallbackAdapter
        protected void onGearLeverChanged(int i) {
            LogUtils.d(OtaService.TAG, "onGearLevelChanged:" + i);
            VehicleHelper.onGearLeverChanged(i);
            OtaService.this.tryPopupUpgradeDialogByGearLevel(i);
            OtaService.this.tryDismissUpgradeDialog(i);
            if (i == 1 || i == 2 || i == 3) {
                int OtaGetCampaignStatus = UpgradeUtils.OtaGetCampaignStatus();
                if (7 >= OtaGetCampaignStatus || OtaGetCampaignStatus >= 10) {
                    OtaService.this.mDialogHelper.closeAll();
                    OtaService.this.mMessagePresenter.closeAllMessage();
                    ActivityHelper.finishActivities(FragmentActivity.class);
                } else {
                    LogUtils.d(OtaService.TAG, "Installing and ignore view closing");
                    return;
                }
            }
            OTAManager.getRemindManager().checkNewByGear(i, CarServiceHelper.getLastStartUpMileage(), true ^ RoadHelper.getInstance().onDangerousRoad());
        }
    };
    private SrsCallbackAdapter mSrsCallbackAdapter = new SrsCallbackAdapter() { // from class: com.xiaopeng.ota.system.OtaService.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.xiaopeng.ota.auto.callback.SrsCallbackAdapter
        public void onBuckleStateChanged(boolean z) {
            super.onBuckleStateChanged(z);
            OTAManager.getRemindManager().checkNewByBelt(z);
        }
    };
    private PowerStateListener mPowerStateListener = new PowerStateListener() { // from class: com.xiaopeng.ota.system.OtaService.3
        @Override // com.xiaopeng.ota.auto.callback.PowerStateListener
        public void onCarNotConnected(Exception exc) {
        }

        @Override // com.xiaopeng.ota.auto.callback.PowerStateListener
        public void onStateChanged(int i, int i2) {
            LogUtils.i(OtaService.TAG, "onStateChanged(state=%d,reason=%d)", Integer.valueOf(i), Integer.valueOf(i2));
            if (VehicleFeature.isCarTypeD55()) {
                onD55StateChanged(i);
            } else {
                onE28StateChanged(i, i2);
            }
        }

        private void onE28StateChanged(int i, int i2) {
            if (i != 3) {
                if (i == 2) {
                    LogUtils.d(OtaService.TAG, "Power off");
                    UpgradeUtils.OtaSystemEvent(UpgradeUtils.EVENT_POWER_OFF);
                    return;
                }
                return;
            }
            if (i2 == 0 || i2 == 1 || i2 == 4 || i2 == 9) {
                LogUtils.d(OtaService.TAG, "Power on");
            }
            OtaService.this.tryStartServiceByPowerOn(i2);
            OtaService.this.tryPopupUpgradeDialogByPower(i2);
            UpgradeUtils.OtaSystemEvent(UpgradeUtils.EVENT_POWER_ON + i2);
        }

        private void onD55StateChanged(int i) {
            if (i == 6) {
                LogUtils.d(OtaService.TAG, "Power on");
                OtaService.this.handleLocalIgOn();
                OtaService.this.tryPopupUpgradeDialog(UpgradePopupConfig.PopupStrategy.POWER_ON);
                UpgradeUtils.OtaSystemEvent(UpgradeUtils.EVENT_POWER_ON + i);
            } else if (i == 7) {
                LogUtils.d(OtaService.TAG, "Power off");
                UpgradeUtils.OtaSystemEvent(UpgradeUtils.EVENT_POWER_OFF);
                OtaService.this.handleNotLocalIgOn();
            } else if (i != 9) {
            } else {
                LogUtils.d(OtaService.TAG, "Power state on screen off");
                UpgradeUtils.OtaSystemEvent(UpgradeUtils.EVENT_SCREEN_OFF);
                OtaService.this.handleNotLocalIgOn();
            }
        }
    };
    private ICarConnect mCarConnectListener = new ICarConnect() { // from class: com.xiaopeng.ota.system.OtaService.4
        @Override // com.xiaopeng.ota.auto.service.ICarConnect
        public void onDisconnected() {
        }

        @Override // com.xiaopeng.ota.auto.service.ICarConnect
        public void onConnected() {
            CarServiceHelper.addSrsServiceListener(OtaService.this.mSrsCallbackAdapter);
            CarServiceHelper.addVcuServiceListener(OtaService.this.mVcuCallbackAdapter);
            CarServiceHelper.addPowerStateListener(OtaService.this.mPowerStateListener);
        }
    };
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.xiaopeng.ota.system.OtaService.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            OtaService.this.handleAction(intent);
        }
    };

    /* loaded from: classes2.dex */
    class OtaStart implements Runnable {
        private final String folder;
        private int retries = 0;
        private final UpgradeExecutorBase[] ues;

        OtaStart() {
            this.folder = OtaService.this.getDir("fota", 0).getAbsolutePath();
            this.ues = new UpgradeExecutorBase[]{new CduExecutor(OtaService.this), new McuExecutor(OtaService.this, this.folder), new IcmDmcuExecutor(OtaService.this, this.folder), new AppExecutor(OtaService.this)};
        }

        @Override // java.lang.Runnable
        public void run() {
            int OtaStart = UpgradeUtils.OtaStart(this.folder, this.ues, OtaService.this.mUpgradePresenter);
            if (OtaStart == 0) {
                return;
            }
            int i = this.retries;
            if (10 > i) {
                this.retries = i + 1;
                LogUtils.w(OtaService.TAG, "Start OTA failed %d, retries %d", Integer.valueOf(OtaStart), Integer.valueOf(this.retries));
                ThreadUtils.postBackground(this, 5000L);
                return;
            }
            LogUtils.e(OtaService.TAG, "Start OTA failed %d, exiting", Integer.valueOf(OtaStart));
            System.exit(0);
        }
    }

    private void handleCleanCaches() {
        LogUtils.i(TAG, "Receive clean caches event");
        Logger.releaseSpace();
        UpgradeUtils.OtaSystemEvent(UpgradeUtils.EVENT_CLEAN_CACHES);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLocalIgOn() {
        if (CarServiceHelper.isIGLocalStatus()) {
            LogUtils.i(TAG, "Screen on %d, mcu local ig on", Integer.valueOf(SystemPropertiesUtils.getPowerStatus()));
            if (OTAManager.getCampaignManager().hasWaitingCampaign()) {
                UpgradeAnimHelper.showUpgradeAnim(this);
            }
            this.mMessagePresenter.handleByPowerOn();
            OTAManager.getRemindManager().activate(true);
            int OtaGetCampaignStatus = UpgradeUtils.OtaGetCampaignStatus();
            if (7 >= OtaGetCampaignStatus || 10 < OtaGetCampaignStatus) {
                OTAManager.handlePowerOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleNotLocalIgOn() {
        if (CarServiceHelper.isIGLocalStatus()) {
            return;
        }
        LogUtils.d(TAG, "Screen off %d, mcu not local ig on", Integer.valueOf(SystemPropertiesUtils.getPowerStatus()));
        OTAManager.getRemindManager().activate(false);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtils.w(TAG, "OTA service start");
        OTAManager.waitAvailable(TimeUnit.SECONDS.toMillis(10L));
        EventBus.getDefault().register(this);
        this.mMessagePresenter = new PromptPresenter();
        this.mMessagePresenter.setListener(this);
        this.mDialogHelper = DialogHelper.getInstance();
        this.mDialogHelper.setContext(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xiaopeng.broadcast.ACTION_ADB_OTA");
        intentFilter.addAction(Config.ACTION_SCREEN_ON);
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction(Config.ACTION_UPGRADE_ANIM_CLICKED);
        intentFilter.addAction(Config.ACTION_APP_INSTALL);
        intentFilter.addAction(Config.ACTION_CLEAN_APP_CACHES);
        registerReceiver(this.mReceiver, intentFilter);
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.system.-$$Lambda$OtaService$OjYSFhYdfo4nDFawjS_xZ1fTHTI
            @Override // java.lang.Runnable
            public final void run() {
                OtaService.this.lambda$onCreate$0$OtaService();
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$OtaService() {
        printVersionInfo();
        this.mUpgradePresenter = new UpgradePresenter(this, this);
        UpgradeUtils.OtaInit(getApplicationContext());
        ThreadUtils.postBackground(new OtaStart());
        this.mUsbDiskListener = new UsbDiskListener(this);
        this.mStorageManager = (StorageManager) getSystemService(StorageManager.class);
        StorageManager storageManager = this.mStorageManager;
        if (storageManager != null) {
            storageManager.registerListener(this.mUsbDiskListener);
        } else {
            LogUtils.w(TAG, "Get StorageManager fail");
        }
        TextToSpeechHelper.getInstance().init(this);
        CarServiceHelper.addSrsServiceListener(this.mSrsCallbackAdapter);
        CarServiceHelper.addVcuServiceListener(this.mVcuCallbackAdapter);
        CarServiceHelper.addPowerStateListener(this.mPowerStateListener);
        CarServiceHelper.addRegisterConnectListener(this.mCarConnectListener);
        if (OTAManager.getCampaignManager().hasWaitingCampaign()) {
            UpgradeAnimHelper.showUpgradeAnim(this);
        } else {
            UpgradeAnimHelper.hideUpgradeAnim(this);
        }
    }

    private void printVersionInfo() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            LogUtils.d(TAG, String.format("OTA current version: Code=[%s], Name=[%s]", Integer.valueOf(packageInfo.versionCode), packageInfo.versionName));
            LogUtils.d(TAG, "FingerPrint:" + BuildInfoUtils.getFullSystemVersion());
        } catch (Exception unused) {
            LogUtils.e(TAG, "Get OTA version info fail!");
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtils.d(TAG, "onStartCommand");
        handleAction(intent);
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        UsbDiskListener usbDiskListener;
        LogUtils.d(TAG, "onDestroy");
        BroadcastReceiver broadcastReceiver = this.mReceiver;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        UpgradeUtils.OtaStop();
        TextToSpeechHelper.getInstance().dispose();
        EventBus.getDefault().unregister(this);
        StorageManager storageManager = this.mStorageManager;
        if (storageManager != null && (usbDiskListener = this.mUsbDiskListener) != null) {
            storageManager.unregisterListener(usbDiskListener);
        }
        try {
            CarApi.unregisterConnectListener(this.mCarConnectListener);
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Unregister connect listener fail");
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        LogUtils.d(TAG, "onBind action=" + intent.getAction() + ", intent=" + intent);
        int intExtra = intent.getIntExtra(InterfaceType.KEY, 0);
        if (intExtra == 0 || intExtra != 2) {
            return null;
        }
        return new OtaServiceImpl(this);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        LogUtils.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    private void showNewCampaign(RemindMode remindMode) {
        if (ScheduleHelper.getInstance().isSetScheduleTime()) {
            LogUtils.i(TAG, "Have set schedule time and ignore prompt");
        } else if (OTAManager.getRemindManager().isCampaignOutOfRetryRemindLimit()) {
            LogUtils.i(TAG, "Skip show notification when out of retry remind limit");
        } else if (DialogHelper.getInstance().isUpgradePopupDialogShowing()) {
            LogUtils.i(TAG, "Upgrade dialog showing");
        } else {
            Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
            if (activeCampaign != null) {
                this.mMessagePresenter.showCommonUpgrade(activeCampaign, remindMode);
            } else {
                LogUtils.i(TAG, "onNewCampaign, getActiveCampaign null");
            }
        }
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onNewCampaign(Campaign campaign) {
        LogUtils.d(TAG, "onNewCampaign");
        UpgradeAnimHelper.showUpgradeAnim(this);
        if (DialogHelper.getInstance().isUpgradePopupDialogShowing()) {
            LogUtils.i(TAG, "Upgrade dialog showing");
            return;
        }
        ActivityHelper.deliveryEvent(Action.CAMPAIGN_READY, CampaignFeatureHelper.getMainFragmentClass());
        if (ActivityUtils.isForeground(this, FragmentActivity.class.getName())) {
            LogUtils.i(TAG, "FragmentActivity is foreground");
        } else if (ActivityUtils.isForeground(this, InstallActivity.class.getName())) {
            LogUtils.i(TAG, "InstallActivity is foreground");
        }
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onUsbNewCampaign(Campaign campaign) {
        LogUtils.d(TAG, "onUsbNewCampaign");
        UpgradeAnimHelper.showUpgradeAnim(this);
        ActivityHelper.finishActivity(FragmentActivity.class);
        showNewCampaign(new RemindMode(5));
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onWakeUp(Campaign campaign) {
        LogUtils.d(TAG, "onWakeUp");
        if (!ScheduleHelper.getInstance().isSetScheduleTime() && !OTAManager.getPreferencesManager().supportAutoUpgrade()) {
            LogUtils.w(TAG, "Schedule time setting not found and unsupported auto upgrade");
            return;
        }
        EventPresenter.getInstance().sendWakeUpInitEvent(campaign.getCampaignId());
        String scheduleTime = ScheduleHelper.getInstance().getScheduleTime();
        if (TextUtils.isEmpty(scheduleTime)) {
            LogUtils.w(TAG, "onWakeUp: Schedule time not found");
            EventPresenter.getInstance().sendWakeUpUpgradeNotMeetEvent(campaign.getCampaignId(), "Schedule time not found");
            return;
        }
        LogUtils.d(TAG, "onWakeUp: " + scheduleTime);
        ActivityHelper.clearSchedule();
        int powerStatus = SystemPropertiesUtils.getPowerStatus();
        LogUtils.i(TAG, "Start upgrade for schedule power status: %d", Integer.valueOf(powerStatus));
        if (powerStatus == 0) {
            LogUtils.w(TAG, "System is interactive and ignore schedule, last scheduleTime:%s", scheduleTime);
            EventPresenter.getInstance().sendWakeUpUpgradeNotMeetEvent(campaign.getCampaignId(), "CDU is interactive");
            if (!OTAManager.getPreferencesManager().supportAutoUpgrade()) {
                EventPresenter.getInstance().sendCancelScheduleEvent(campaign.getCampaignId(), "CDU is interactive");
            }
            this.mMessagePresenter.showScheduleArrived(campaign, scheduleTime);
            return;
        }
        EventPresenter.getInstance().sendWakeUp2UpgradeEvent(campaign.getCampaignId());
        if (!OTAManager.getPreferencesManager().supportAutoUpgrade()) {
            EventPresenter.getInstance().sendCancelScheduleEvent(campaign.getCampaignId(), "Waken up and start upgrade");
        }
        this.mUpgradePresenter.startUpgrade(true);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onStart() {
        showInstallState(0, 0, null, 0, 0, 0, 0, 0);
        OTAManager.getRemindManager().activate(false);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onPrepare(EcuType ecuType, UpgradeProgress upgradeProgress) {
        LogUtils.d(TAG, "onPrepare, Ecu(%s)->Progress(%d)", ecuType.name(), Integer.valueOf(upgradeProgress.getProgress()));
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onChecking(boolean z) {
        LogUtils.d(TAG, "onChecking(%b)", Boolean.valueOf(z));
        if (z) {
            ActivityHelper.startActivity(this, CheckActivity.class);
        } else {
            ActivityHelper.finishActivity(CheckActivity.class);
        }
        DialogHelper.getInstance().closeAll();
        ActivityHelper.finishActivity(FragmentActivity.class);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onProgress(EcuType ecuType, UpgradeProgress upgradeProgress) {
        showInstallState(1, 2, ecuType, upgradeProgress.getProgress(), upgradeProgress.getPkgIndex(), upgradeProgress.getPkgCount(), upgradeProgress.getRemainingSeconds(), upgradeProgress.getCategory());
        this.mCurrProgress = upgradeProgress.getProgress();
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onFinalizing(EcuType ecuType, UpgradeProgress upgradeProgress) {
        showInstallState(1, 3, ecuType, 100, upgradeProgress.getPkgIndex(), upgradeProgress.getPkgCount(), upgradeProgress.getRemainingSeconds(), upgradeProgress.getCategory());
        this.mCurrProgress = 100;
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onSuccess(EcuType ecuType) {
        LogUtils.d(TAG, "onSuccess, Ecu(%s)", ecuType.name());
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onError(final String str, Exception exc) {
        LogUtils.e(TAG, "onError from=%s", str);
        showInstallState(3, 12, null, this.mCurrProgress, 0, 0, 0, 0);
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.system.-$$Lambda$OtaService$vCZCSdmYz3rRQkne2bzL1UiF-nM
            @Override // java.lang.Runnable
            public final void run() {
                OtaService.this.lambda$onError$1$OtaService(str);
            }
        }, 2000L);
    }

    public /* synthetic */ void lambda$onError$1$OtaService(String str) {
        closeInstallProgress();
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign == null) {
            LogUtils.e(TAG, "Current active campaign not found");
            return;
        }
        UpgradeResult upgradeResult = new UpgradeResult();
        upgradeResult.setFrom(str);
        this.mMessagePresenter.showUpgradeFail(activeCampaign, upgradeResult);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onCanceled(long j) {
        LogUtils.e(TAG, "onCanceled:" + j);
        ActivityHelper.finishActivity(FragmentActivity.class);
        ActivityHelper.finishActivity(CheckActivity.class);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onConditionMiss(long j, UpgradeResult upgradeResult) {
        Object[] objArr = new Object[2];
        objArr[0] = Long.valueOf(j);
        objArr[1] = upgradeResult == null ? "" : JsonUtils.toJson(upgradeResult);
        LogUtils.d(TAG, "onConditionMiss:%d, %s", objArr);
        EventPresenter.getInstance().sendNotSatisfiedEvent(j, upgradeResult != null ? upgradeResult.getConditionName() : null, this.mMessagePresenter.assembleConditionMissContent(upgradeResult));
        ActivityHelper.finishActivity(FragmentActivity.class);
        ActivityHelper.finishActivity(CheckActivity.class);
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign == null) {
            LogUtils.e(TAG, "Current active campaign not found");
        } else {
            this.mMessagePresenter.showConditionMiss(activeCampaign, upgradeResult);
        }
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onExpired(long j) {
        Campaign lastInvalidCampaign;
        LogUtils.d(TAG, "onExpired: " + j);
        if (j == 0 && (lastInvalidCampaign = OTAManager.getCampaignManager().getLastInvalidCampaign()) != null) {
            j = lastInvalidCampaign.getCampaignId();
        }
        this.mDialogHelper.promptExpired(j);
        ActivityHelper.deliveryEvent(Action.CAMPAIGN_CANCEL, CampaignFeatureHelper.getMainFragmentClass());
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onCampaignCompleted(long j, boolean z) {
        LogUtils.d(TAG, "onCampaignCompleted, campaignId:%d, animate:%b", Long.valueOf(j), Boolean.valueOf(z));
        this.mMessagePresenter.closeAllMessage();
        try {
            OTAManager.saveOsVersion(j);
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Save os version occurs Exception");
        }
        OTAManager.getCampaignManager().campaignCompleted(j);
        if (z) {
            showInstallState(2, 11, null, 100, 0, 0, 0, 0);
        } else {
            ActivityHelper.finishActivity(InstallActivity.class);
            ActivityHelper.deliveryEvent(Action.INSTALL_COMPLETED, OtaService.class);
        }
        UpgradeAnimHelper.hideUpgradeAnim(this);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onEcuVersionChanged() {
        LogUtils.d(TAG, "onEcuVersionChanged");
        ActivityHelper.ecuVersionChanged(InfoFragment.class);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onUsbProgress(String str) {
        if (ActivityUtils.isForeground(this, FragmentActivity.class.getName())) {
            ActivityHelper.deliveryEvent(Action.USB_PROGRESS, str, UsbFragment.class);
        }
    }

    private void showInstallState(int i, int i2, EcuType ecuType, int i3, int i4, int i5, int i6, int i7) {
        String str;
        String str2;
        String str3;
        int i8;
        int i9;
        String format;
        String string;
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign != null) {
            if (i2 == 1 || i2 == 2 || i2 == 3) {
                if (i4 == 0) {
                    i8 = activeCampaign.getEcuIndex(ecuType);
                    if (i8 == 0) {
                        LogUtils.e(TAG, "Ecu(%s) not in campaign(%s)", ecuType.name(), activeCampaign.getCampaignName());
                        return;
                    }
                } else {
                    i8 = i4;
                }
                if (i5 == 0) {
                    i9 = activeCampaign.getEcuCount();
                    if (i9 == 0) {
                        LogUtils.e(TAG, "get Ecu count error, campaign(%s)", activeCampaign.getCampaignName());
                        return;
                    }
                } else {
                    i9 = i5;
                }
                int remainEstimateTime = i6 == 0 ? activeCampaign.getRemainEstimateTime(ecuType) : i6;
                if (remainEstimateTime == 0) {
                    format = StringUtils.format(R.string.install_time_total_default, Integer.valueOf(activeCampaign.getEstimateCost()));
                } else {
                    format = StringUtils.format(R.string.install_time_remainder_format, Integer.valueOf(remainEstimateTime / 60));
                }
            } else if (i2 == 0) {
                i9 = i5;
                format = StringUtils.format(R.string.install_time_total_default, Integer.valueOf(activeCampaign.getEstimateCost()));
                i8 = i4;
            } else {
                i8 = i4;
                i9 = i5;
                format = null;
            }
            String ecuDisplay = EcuDisplayUtils.getEcuDisplay(ecuType);
            if (i2 == 0) {
                string = ConfigHelper.getString(Constants.ConfigKey.INSTALL_INIT);
            } else if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 == 11) {
                            string = ConfigHelper.getString(Constants.ConfigKey.INSTALL_SUCCESS);
                        } else if (i2 == 12) {
                            string = ConfigHelper.getString(Constants.ConfigKey.INSTALL_FAIL);
                        } else {
                            LogUtils.e(TAG, "Wrong install phase: " + i2);
                            return;
                        }
                    } else if (10 == i7) {
                        string = activeCampaign.getReleaseVersion();
                    } else {
                        string = StringUtils.format(R.string.install_finalizing_title_format, ecuDisplay, Integer.valueOf(i8), Integer.valueOf(i9));
                    }
                } else if (10 == i7) {
                    string = activeCampaign.getReleaseVersion();
                } else if (1 == i7) {
                    string = StringUtils.format(R.string.install_app_title_format, Integer.valueOf(i8), Integer.valueOf(i9));
                } else if (2 == i7) {
                    string = StringUtils.format(R.string.install_cfg_title_format, ecuDisplay, Integer.valueOf(i8), Integer.valueOf(i9));
                } else {
                    string = StringUtils.format(R.string.install_fmw_title_format, ecuDisplay, Integer.valueOf(i8), Integer.valueOf(i9));
                }
            } else if (10 == i7) {
                string = activeCampaign.getReleaseVersion();
            } else {
                string = StringUtils.format(R.string.install_prepare_title_format, ecuDisplay, Integer.valueOf(i8), Integer.valueOf(i9));
            }
            str = string;
            str3 = TextUtils.isEmpty(activeCampaign.getInstallingTip()) ? null : activeCampaign.getInstallingTip().replace("#", "");
            str2 = format;
        } else {
            str = null;
            str2 = null;
            str3 = null;
        }
        sendInstallState(i, str, str2, i3, str3);
    }

    private void sendInstallState(int i, String str, String str2, int i2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        bundle.putString("title", str);
        bundle.putString(Config.EXTRA_KEY_TIME, str2);
        bundle.putString("info", str3);
        bundle.putInt("progress", i2);
        if (ActivityUtils.isForeground(this, FragmentActivity.class.getName())) {
            ActivityHelper.finishActivity(FragmentActivity.class);
        }
        if (ActivityUtils.isForeground(this, CheckActivity.class.getName())) {
            ActivityHelper.finishActivity(CheckActivity.class, CheckActivity.DISABLE_TOAST);
        }
        ActivityHelper.startActivity(this, InstallActivity.class, bundle);
    }

    private void closeInstallProgress() {
        LogUtils.d(TAG, "closeInstallProgress");
        ActivityHelper.finishActivities(FragmentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("type", 6);
        ActivityHelper.startActivity(this, InstallActivity.class, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void handleAction(Intent intent) {
        char c;
        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        String action = intent.getAction();
        int i = -1;
        switch (action.hashCode()) {
            case -2128145023:
                if (action.equals("android.intent.action.SCREEN_OFF")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -2110209792:
                if (action.equals(Config.ACTION_APP_INSTALL)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -2080500012:
                if (action.equals("com.xiaopeng.broadcast.ACTION_ADB_OTA")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1454123155:
                if (action.equals(Config.ACTION_SCREEN_ON)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1443749852:
                if (action.equals(Config.ACTION_XUI_BUSINESS_EVENT)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -810471698:
                if (action.equals("android.intent.action.PACKAGE_REPLACED")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 172491798:
                if (action.equals("android.intent.action.PACKAGE_CHANGED")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 215282783:
                if (action.equals(Config.ACTION_UPGRADE_ANIM_CLICKED)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 525384130:
                if (action.equals("android.intent.action.PACKAGE_REMOVED")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1544582882:
                if (action.equals("android.intent.action.PACKAGE_ADDED")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 2135852351:
                if (action.equals(Config.ACTION_CLEAN_APP_CACHES)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                if (!BuildInfoUtils.isEngVersion() && !BuildInfoUtils.isUserDebugVersion()) {
                    LogUtils.d(TAG, "Not in Eng or UserDebug version");
                    return;
                } else if (TextUtils.isEmpty(intent.getStringExtra(Constants.Schedule.FROM_CDU))) {
                    return;
                } else {
                    new AdbPresenter(this).adbUpdate(intent.getStringExtra(Constants.Schedule.FROM_CDU));
                    return;
                }
            case 1:
                handleCleanCaches();
                return;
            case 2:
                if ("E28".equals(VehicleFeature.getCarType())) {
                    handleLocalIgOn();
                    return;
                }
                return;
            case 3:
                if ("E28".equals(VehicleFeature.getCarType())) {
                    handleNotLocalIgOn();
                    return;
                }
                return;
            case 4:
                LogUtils.d(TAG, "Upgrade anim clicked");
                onShowUpgrade();
                EventPresenter.getInstance().sendUpgradeIconClickedEvent(OTAManager.getCampaignManager().getActiveCampaignId());
                return;
            case 5:
            case 6:
            case 7:
            case '\b':
            case '\t':
                AppExecutor.handleAction(intent);
                return;
            case '\n':
                String stringExtra = intent.getStringExtra(String.valueOf(557847561));
                LogUtils.d(TAG, "Handle %s mcu ig status:%s", Config.ACTION_XUI_BUSINESS_EVENT, stringExtra);
                try {
                    i = Integer.parseInt(stringExtra);
                } catch (NumberFormatException unused) {
                }
                if (i != 1) {
                    return;
                }
                handleLocalIgOn();
                return;
            default:
                return;
        }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onExceptionEvent(SubscriberExceptionEvent subscriberExceptionEvent) {
        LogUtils.e(getClass().getSimpleName(), subscriberExceptionEvent.throwable, "onExceptionEvent");
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEvent(ActivityEvent activityEvent) {
        if (getClass().getName().equals(activityEvent.getClazz().getName())) {
            switch (activityEvent.getAction()) {
                case SHOW_CAMPAIGN:
                    try {
                        showNewCampaign((RemindMode) activityEvent.getData());
                        return;
                    } catch (Exception unused) {
                        showNewCampaign(new RemindMode(-1));
                        return;
                    }
                case START_INSTALL:
                    this.mUpgradePresenter.startUpgrade(false);
                    return;
                case SCHEDULE:
                    Dictionary dictionary = (Dictionary) activityEvent.getData();
                    if (dictionary == null || dictionary.get(Constants.Schedule.KEY_HOUR) == null || dictionary.get(Constants.Schedule.KEY_MINUTE) == null) {
                        LogUtils.e(TAG, "Schedule, but time empty");
                        return;
                    } else {
                        setSchedule(((Integer) dictionary.get(Constants.Schedule.KEY_HOUR)).intValue(), ((Integer) dictionary.get(Constants.Schedule.KEY_MINUTE)).intValue(), dictionary.getString("from", Constants.Schedule.FROM_USER));
                        return;
                    }
                case AUTO_UPGRADE:
                    LogUtils.d(TAG, "Set auto upgrade");
                    if (ScheduleHelper.getInstance().isSetScheduleTime() || ScheduleHelper.getInstance().isSetAutoScheduled()) {
                        LogUtils.i(TAG, "Upgrade scheduled(user=%b, auto=%b)", Boolean.valueOf(ScheduleHelper.getInstance().isSetScheduleTime()), Boolean.valueOf(ScheduleHelper.getInstance().isSetAutoScheduled()));
                        return;
                    } else if (OTAManager.getRemindManager().isCampaignOutOfRetryRemindLimit()) {
                        LogUtils.i(TAG, "Skip auto upgrade when out of retry remind limit");
                        return;
                    } else {
                        setDefaultSchedule(true);
                        return;
                    }
                case CANCEL_USER_SCHEDULE:
                    LogUtils.d(TAG, "Cancel user schedule upgrade");
                    this.mUpgradePresenter.cancelSchedule((String) activityEvent.getData());
                    ScheduleHelper.getInstance().cancelSchedule();
                    Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
                    if (activeCampaign != null) {
                        ActivityHelper.deliveryEvent(Action.CANCEL_SCHEDULE_SUCCESS, CampaignFeatureHelper.getMainFragmentClass(), CampaignFeatureHelper.getNewFragmentClass(activeCampaign));
                        return;
                    }
                    return;
                case CANCEL_AUTO_SCHEDULE:
                    LogUtils.d(TAG, "Cancel auto schedule upgrade");
                    if (!ScheduleHelper.getInstance().isSetAutoScheduled()) {
                        LogUtils.d(TAG, "Skip cancel auto upgrade");
                        return;
                    }
                    this.mUpgradePresenter.cancelSchedule(Constants.Schedule.FROM_AUTO);
                    ScheduleHelper.getInstance().cancelAutoSchedule();
                    return;
                case CLEAR_SCHEDULE:
                    ScheduleHelper.getInstance().cancelSchedule();
                    return;
                case CANCEL_UPGRADE:
                    this.mUpgradePresenter.cancelUpgrade();
                    return;
                case INSTALL_COMPLETED:
                    boolean isIGRemoteStatus = CarServiceHelper.isIGRemoteStatus();
                    ScheduleHelper.getInstance().clear();
                    Campaign curInstalledCampaign = OTAManager.getCampaignManager().getCurInstalledCampaign();
                    if (curInstalledCampaign != null) {
                        this.mMessagePresenter.showUpgradeSuccess(curInstalledCampaign, isIGRemoteStatus);
                        return;
                    } else {
                        LogUtils.e(TAG, "Install completed and current installed campaign not found");
                        return;
                    }
                default:
                    LogUtils.d(TAG, "Unsupported action " + activityEvent.getAction());
                    return;
            }
        }
    }

    private void setDefaultSchedule(boolean z) {
        Pair<Integer, Integer> hourAndMinute;
        String str;
        if (z) {
            String OtaGetScheduleTime = UpgradeUtils.OtaGetScheduleTime();
            LogUtils.d(TAG, "serverScheduleTime:" + OtaGetScheduleTime);
            boolean isEmpty = TextUtils.isEmpty(OtaGetScheduleTime);
            str = Constants.Schedule.FROM_AUTO;
            if (!isEmpty) {
                hourAndMinute = TimeUtils.getHourAndMinute(OtaGetScheduleTime);
                if (hourAndMinute != null) {
                    str = Constants.Schedule.FROM_PLANNED;
                } else {
                    hourAndMinute = TimeUtils.getHourAndMinute(ScheduleHelper.getInstance().getDefaultTime());
                }
            } else {
                hourAndMinute = TimeUtils.getHourAndMinute(ScheduleHelper.getInstance().getDefaultTime());
            }
        } else {
            hourAndMinute = TimeUtils.getHourAndMinute(ScheduleHelper.getInstance().getDefaultTime());
            str = Constants.Schedule.FROM_USER;
        }
        if (hourAndMinute == null) {
            LogUtils.e(TAG, "Convert default schedule time (%s) failed", ScheduleHelper.getInstance().getDefaultTime());
        } else {
            setSchedule(((Integer) hourAndMinute.first).intValue(), ((Integer) hourAndMinute.second).intValue(), str);
        }
    }

    private void enableAutoUpgrade() {
        LogUtils.i(TAG, "Enable auto upgrade");
        if (OTAManager.getPreferencesManager().supportAutoUpgrade()) {
            return;
        }
        OTAManager.getPreferencesManager().setAutoUpgrade(true);
        ActivityHelper.startAutoUpgrade();
    }

    private void setSchedule(int i, int i2, String str) {
        int random;
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign == null) {
            LogUtils.d(TAG, "No active campaign");
        } else if (!activeCampaign.isSupportSchedule()) {
            LogUtils.d(TAG, "Campaign %d unsupported schedule", Long.valueOf(activeCampaign.getCampaignId()));
        } else {
            LogUtils.d(TAG, "setSchedule %d:%d, %s", Integer.valueOf(i), Integer.valueOf(i2), str);
            if (Constants.Schedule.FROM_PLANNED.equals(str)) {
                random = 0;
                str = Constants.Schedule.FROM_AUTO;
            } else {
                Pair<Integer, Integer> hourAndMinute = TimeUtils.getHourAndMinute(ScheduleHelper.getInstance().getDefaultTime());
                random = (hourAndMinute != null && i == ((Integer) hourAndMinute.first).intValue() && i2 == ((Integer) hourAndMinute.second).intValue()) ? (int) (Math.random() * 5.0d) : 0;
            }
            if (!this.mUpgradePresenter.scheduleUpgrade(str, i, i2, random)) {
                LogUtils.w(TAG, "Set schedule time failed, and try second time");
                if (!this.mUpgradePresenter.scheduleUpgrade(str, i, i2, random)) {
                    LogUtils.w(TAG, "Set schedule time failed again");
                    EventPresenter.getInstance().sendScheduleFailEvent(activeCampaign.getCampaignId(), "Set schedule time failed");
                    return;
                }
            }
            if (str.equals(Constants.Schedule.FROM_AUTO)) {
                ScheduleHelper.getInstance().saveAutoScheduleTime(i, i2, 0);
            } else {
                ScheduleHelper.getInstance().cancelAutoSchedule();
                ScheduleHelper.getInstance().saveScheduleTime(i, i2, 0);
                ActivityHelper.deliveryEvent(Action.SCHEDULE_SUCCESS, CampaignFeatureHelper.getMainFragmentClass(), CampaignFeatureHelper.getNewFragmentClass(activeCampaign));
            }
            LogUtils.d(TAG, "Set default schedule time from %s success %02d:%02d", str, Integer.valueOf(i), Integer.valueOf(i2));
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.IOnReceiveListener
    public void onUpgradeNow() {
        if (OTAManager.getCampaignManager().getActiveCampaign() == null) {
            onExpired(0L);
            return;
        }
        UpgradeFragment.resetCountDown();
        ActivityHelper.startFragment(this, CampaignFeatureHelper.getUpgradeFragmentClass());
    }

    @Override // com.xiaopeng.ota.presenter.prompt.IOnReceiveListener
    public void onUpgradeSuccess() {
        UpgradeFragment.resetCountDown();
        Campaign curInstalledCampaign = OTAManager.getCampaignManager().getCurInstalledCampaign();
        if (curInstalledCampaign != null) {
            ActivityHelper.startFragment(this, CampaignFeatureHelper.getVersionDetailFragmentClass(curInstalledCampaign));
        } else {
            LogUtils.i(TAG, "Not found installed campaign");
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.IOnReceiveListener
    public void onScheduleDefault(boolean z) {
        setDefaultSchedule(z);
    }

    @Override // com.xiaopeng.ota.presenter.prompt.IOnReceiveListener
    public void onScheduleWithTime(int i, int i2) {
        setSchedule(i, i2, Constants.Schedule.FROM_USER);
    }

    @Override // com.xiaopeng.ota.presenter.prompt.IOnReceiveListener
    public void onAutoUpgradeEnable() {
        enableAutoUpgrade();
    }

    @Override // com.xiaopeng.ota.presenter.prompt.IOnReceiveListener
    public void onSettingSchedule() {
        if (OTAManager.getCampaignManager().getActiveCampaign() == null) {
            onExpired(0L);
        } else {
            ActivityHelper.startFragment(this, CampaignFeatureHelper.getScheduleFragmentClass());
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.IOnReceiveListener
    public void onSchedule() {
        if (OTAManager.getCampaignManager().getActiveCampaign() == null) {
            onExpired(0L);
            return;
        }
        UpgradeFragment.resetCountDown();
        ActivityHelper.startFragment(this, CampaignFeatureHelper.getUpgradeFragmentClass());
    }

    @Override // com.xiaopeng.ota.presenter.prompt.IOnReceiveListener
    public void onShowUpgrade() {
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign == null) {
            onExpired(0L);
        } else {
            ActivityHelper.startFragment(this, CampaignFeatureHelper.getNewFragmentClass(activeCampaign));
        }
    }

    @Override // com.xiaopeng.ota.presenter.prompt.IOnReceiveListener
    public void onShowMain() {
        HashMap hashMap = new HashMap();
        hashMap.put("packageName", IpcConfig.App.AI);
        ActivityHelper.startFragment(this, CampaignFeatureHelper.getMainFragmentClass(), hashMap);
    }

    @Override // com.xiaopeng.ota.presenter.prompt.IOnReceiveListener
    public void onCallCustomerService() {
        if (ActivityUtils.isForeground(this, FragmentActivity.class.getName())) {
            ActivityHelper.finishActivity(FragmentActivity.class);
            ActivityHelper.finishActivity(CheckActivity.class);
        }
        String string = ConfigHelper.getString(Constants.ConfigKey.DEFAULT_SERVICE_PHONE);
        if (TextUtils.isEmpty(string)) {
            string = getString(R.string.default_service_phone);
        }
        String phoneNumber = StringUtils.getPhoneNumber(string);
        if (TextUtils.isEmpty(phoneNumber)) {
            return;
        }
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivityAsUser(intent, UserHandle.CURRENT);
    }

    @Override // com.xiaopeng.ota.presenter.prompt.IOnReceiveListener
    public void onSendPromptEvent(AiContent aiContent) {
        EventPresenter.getInstance().sendPromptAiEvent(aiContent);
    }

    @Override // com.xiaopeng.ota.presenter.prompt.IOnReceiveListener
    public void onSendAiResponseEvent(AiContent aiContent) {
        EventPresenter.getInstance().sendAiResponseEvent(aiContent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryStartServiceByPowerOn(int i) {
        if (i == 0 || i == 1 || i == 4) {
            OTAManager.sendStartFotaService();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryPopupUpgradeDialogByPower(int i) {
        if (i == 0 || i == 1 || i == 4) {
            tryPopupUpgradeDialog(UpgradePopupConfig.PopupStrategy.POWER_ON);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryPopupUpgradeDialogByGearLevel(int i) {
        if (i != 4) {
            return;
        }
        tryPopupUpgradeDialog(UpgradePopupConfig.PopupStrategy.PARKING_GEAR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryPopupUpgradeDialog(final UpgradePopupConfig.PopupStrategy popupStrategy) {
        final Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign != null) {
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.system.-$$Lambda$OtaService$d8ad_BppKBRF2fRDoIQuKIAwLf8
                @Override // java.lang.Runnable
                public final void run() {
                    UpgradePopupHelper.getInstance().tryPopupAutoDialog(Campaign.this, popupStrategy);
                }
            }, TimeUnit.SECONDS.toMillis(2L));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryDismissUpgradeDialog(int i) {
        final UpgradePopupConfig.DismissStrategy dismissStrategy;
        final Campaign activeCampaign;
        if (i == 1) {
            dismissStrategy = UpgradePopupConfig.DismissStrategy.GEAR_DRIVE;
        } else {
            dismissStrategy = i != 3 ? null : UpgradePopupConfig.DismissStrategy.GEAR_REVERS;
        }
        if (dismissStrategy == null || (activeCampaign = OTAManager.getCampaignManager().getActiveCampaign()) == null) {
            return;
        }
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.system.-$$Lambda$OtaService$LXfYy9t1vl5N6tVdBELj6Uk1XiA
            @Override // java.lang.Runnable
            public final void run() {
                UpgradePopupHelper.getInstance().tryDismissAutoDialog(Campaign.this, dismissStrategy);
            }
        });
    }

    @Publish
    public int getOTAState() {
        int upgradeState = UpgradePresenter.getUpgradeState();
        LogUtils.d(TAG, "OTA state " + upgradeState);
        return upgradeState;
    }

    @Publish
    public boolean existNewVersion() {
        return OTAManager.getCampaignManager().hasCampaign();
    }

    @Publish
    @Path("readDid")
    public String readDid(@Parameter("requestJsonContent") @NotNull String str) {
        return ReadDidHelper.getInstance().readDid(str);
    }

    @Publish
    @Path("getVcuMode")
    public String getVcuMode() {
        RpcResponse errorResponse;
        try {
            byte[] OtaReadDid = UpgradeUtils.OtaReadDid(EcuType.VCU.id(), 256);
            if (OtaReadDid != null && OtaReadDid.length == 1) {
                errorResponse = RpcResponseHelper.getInstance().successResponse(Integer.valueOf(OtaReadDid[0]));
            } else if (UpgradeUtils.OtaIsReady()) {
                Object[] objArr = new Object[1];
                objArr[0] = OtaReadDid == null ? "null" : HexUtils.toHexString(OtaReadDid);
                errorResponse = RpcResponseHelper.getInstance().errorResponse(String.format("Vcu response data invalid:%s", objArr));
            } else {
                errorResponse = RpcResponseHelper.getInstance().serviceInitializationResponse();
            }
        } catch (Exception e) {
            errorResponse = RpcResponseHelper.getInstance().errorResponse(Log.getStackTraceString(e));
        }
        LogUtils.d(TAG, "getVcuMode " + errorResponse);
        return JsonUtils.toJson(errorResponse);
    }

    @Publish
    @Path("setVcuMode")
    public String setVcuMode(@Parameter("mode") @NotNull int i) {
        RpcResponse errorResponse;
        if (i > 3 || i < 0) {
            errorResponse = RpcResponseHelper.getInstance().errorResponse("mode parameter invalid, 0-3 allowed");
        } else {
            try {
                checkVehicleParking();
                if (UpgradeUtils.OtaWriteDid(EcuType.VCU.id(), 256, new byte[]{(byte) i}) == 0) {
                    errorResponse = RpcResponseHelper.getInstance().successResponse();
                } else if (UpgradeUtils.OtaIsReady()) {
                    errorResponse = RpcResponseHelper.getInstance().errorResponse("write did fail, please retry later");
                } else {
                    errorResponse = RpcResponseHelper.getInstance().serviceInitializationResponse();
                }
            } catch (Exception e) {
                errorResponse = RpcResponseHelper.getInstance().errorResponse(Log.getStackTraceString(e));
            }
        }
        LogUtils.d(TAG, "setVcuMode " + errorResponse);
        return JsonUtils.toJson(errorResponse);
    }

    private void checkVehicleParking() throws Exception {
        VcuService vcuService = (VcuService) CarApi.getCarApi(102);
        if (vcuService != null) {
            if (vcuService.getGearLever() != 4) {
                throw new IllegalStateException("Not in parking gear");
            }
            return;
        }
        throw new IllegalStateException("Car api vcu service init fail");
    }
}
