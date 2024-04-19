package com.xiaopeng.ota.system;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.os.ConditionVariable;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.xiaopeng.ota.Config;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.IOtaService;
import com.xiaopeng.ota.bean.InterfaceType;
import com.xiaopeng.ota.sdk.common.OTAConstants;
import com.xiaopeng.ota.sdk.common.util.ArrayUtils;
import com.xiaopeng.ota.sdk.common.util.ThreadUtils;
import com.xiaopeng.ota.system.SotaService;
import com.xiaopeng.ota.utils.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.PackageUtils;
import com.xiaopeng.ota.utils.ProcessUtils;
import com.xiaopeng.sota.sdk.SOTAManager;
import com.xiaopeng.sota.sdk.listener.SOTAListener;
import java.util.Collections;
import java.util.List;
/* loaded from: classes2.dex */
public class SotaService extends Service {
    private static final String TAG = "SotaService";
    private BroadcastReceiver mCheckReceiver;
    private IOtaService mOtaService;
    private volatile boolean mServiceConnected;
    private long mTimeout;
    private ConditionVariable mTimeoutLock;
    private ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.xiaopeng.ota.system.SotaService.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtils.d(SotaService.TAG, "onServiceConnected");
            SotaService.this.mOtaService = IOtaService.Stub.asInterface(iBinder);
            if (SotaService.this.mOtaService != null) {
                SotaService.this.mServiceConnected = true;
                try {
                    String configString = SotaService.this.mOtaService.getConfigString("SERVER_URL");
                    LogUtils.d(SotaService.TAG, "get serverUrl:" + configString);
                    if (TextUtils.isEmpty(configString)) {
                        return;
                    }
                    SOTAManager.switchHost(configString);
                    SotaService.this.mTimeout = SotaService.this.mOtaService.getConfigLong(Constants.ConfigKey.SOTA_UPGRADE_TIMEOUT);
                    LogUtils.d(SotaService.TAG, "Sota timeout config:" + SotaService.this.mTimeout);
                    return;
                } catch (RemoteException unused) {
                    LogUtils.w(SotaService.TAG, "get server url fail");
                    return;
                }
            }
            LogUtils.w(SotaService.TAG, "get IOtaService fail");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            SotaService.this.mServiceConnected = false;
            SotaService.this.mOtaService = null;
        }
    };
    private BroadcastReceiver mInstallReceiver = new BroadcastReceiver() { // from class: com.xiaopeng.ota.system.SotaService.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SotaService.this.handleAction(intent);
        }
    };
    private SOTAListener mSotaListener = new AnonymousClass4();

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        LogUtils.d(TAG, "onCreate");
        super.onCreate();
        bindService();
        SOTAManager.register(this.mSotaListener);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(OTAConstants.Action.RESUME_INSTALLATION);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mInstallReceiver, intentFilter);
        registerCheckReceiver();
        this.mTimeoutLock = new ConditionVariable();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtils.d(TAG, "onStartCommand");
        bindService();
        if (intent != null && intent.getIntExtra("type", 0) == 3) {
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.system.-$$Lambda$SotaService$Vs3PPX40-8hR-PahSfxD59HYsKo
                @Override // java.lang.Runnable
                public final void run() {
                    SotaService.this.lambda$onStartCommand$0$SotaService();
                }
            }, Config.HANDLE_DELAY_MILS);
        }
        handleAction(intent);
        return 1;
    }

    public /* synthetic */ void lambda$onStartCommand$0$SotaService() {
        if (ProcessUtils.isProcessRunning(this, "com.xiaopeng.ota")) {
            return;
        }
        LogUtils.d(TAG, "Main thread is not running when boot");
        checkUpdate(OTAConstants.CheckSource.TRIGGER_ACTION_BOOT_COMPLETED);
    }

    @Override // android.app.Service
    public void onDestroy() {
        LogUtils.d(TAG, "onDestroy");
        unbindService(this.mServiceConnection);
        SOTAManager.unregister(this.mSotaListener);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mInstallReceiver);
        unregisterCheckReceiver();
        super.onDestroy();
    }

    private synchronized void bindService() {
        if (this.mServiceConnected) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(getPackageName(), OtaService.class.getName());
        intent.putExtra(InterfaceType.KEY, 2);
        startServiceAsUser(intent, UserHandle.CURRENT);
        bindServiceAsUser(intent, this.mServiceConnection, 1, UserHandle.CURRENT);
    }

    private void registerCheckReceiver() {
        this.mCheckReceiver = new BroadcastReceiver() { // from class: com.xiaopeng.ota.system.SotaService.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                LogUtils.d(SotaService.TAG, "Check receiver onReceive");
                SotaService.this.handleAction(intent);
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Config.ACTION_SCREEN_ON);
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction(Config.ACTION_SOTA_CHECK_UPDATE);
        intentFilter.addAction(Config.ACTION_SOTA_SERVER_MESSAGE);
        registerReceiver(this.mCheckReceiver, intentFilter);
    }

    private void unregisterCheckReceiver() {
        BroadcastReceiver broadcastReceiver = this.mCheckReceiver;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAction(Intent intent) {
        boolean z;
        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        bindService();
        String action = intent.getAction();
        int hashCode = action.hashCode();
        if (hashCode == -1454123155) {
            if (action.equals(Config.ACTION_SCREEN_ON)) {
                z = false;
            }
            z = true;
        } else if (hashCode != -517734172) {
            if (hashCode == 221889614 && action.equals(Config.ACTION_SOTA_CHECK_UPDATE)) {
                z = true;
            }
            z = true;
        } else {
            if (action.equals(Config.ACTION_SOTA_SERVER_MESSAGE)) {
                z = true;
            }
            z = true;
        }
        if (!z) {
            LogUtils.d(TAG, "Power on");
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.system.-$$Lambda$SotaService$AVEz1jayGw7fRNJ6lH2vQaqG3XE
                @Override // java.lang.Runnable
                public final void run() {
                    SotaService.this.lambda$handleAction$1$SotaService();
                }
            }, Config.HANDLE_DELAY_MILS);
        } else if (z) {
            LogUtils.d(TAG, "Sota check update");
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.system.-$$Lambda$SotaService$QOy2TF-1Jmgttf3aNEt-iP_64LM
                @Override // java.lang.Runnable
                public final void run() {
                    SotaService.this.lambda$handleAction$2$SotaService();
                }
            }, Config.HANDLE_DELAY_MILS);
        } else if (!z) {
        } else {
            int intExtra = intent.getIntExtra("type", -1);
            LogUtils.d(TAG, "Server's sota push: %d", Integer.valueOf(intExtra));
            switch (intExtra) {
                case 11009:
                    checkUpdate(OTAConstants.CheckSource.TRIGGER_ACTION_PUSH);
                    return;
                case 11010:
                    SOTAManager.clearDatabase();
                    return;
                case 11011:
                    SOTAManager.clearDownloadFiles();
                    return;
                case 11012:
                    SOTAManager.cancelCampaign();
                    return;
                case 11013:
                default:
                    return;
                case 11014:
                    SOTAManager.retryDownload();
                    return;
            }
        }
    }

    public /* synthetic */ void lambda$handleAction$1$SotaService() {
        if (isFotaBusy()) {
            return;
        }
        if (SOTAManager.hasCampaign() && SOTAManager.getCampaignManager().allCampaignDownloaded()) {
            LogUtils.d(TAG, "Has sota campaign and goto install");
            SOTAManager.install(false);
            return;
        }
        LogUtils.d(TAG, "No sota campaign and goto check update");
        checkUpdate(OTAConstants.CheckSource.TRIGGER_ACTION_POWER_ON);
    }

    public /* synthetic */ void lambda$handleAction$2$SotaService() {
        checkUpdate(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFotaBusy() {
        if (ProcessUtils.isProcessRunning(this, "com.xiaopeng.ota")) {
            try {
                if (this.mServiceConnected) {
                    if (this.mOtaService.hasCampaign()) {
                        LogUtils.d(TAG, "Fota has campaign");
                        return true;
                    }
                    LogUtils.d(TAG, "Fota is idle");
                    return false;
                }
                LogUtils.d(TAG, "Fota service disconnected");
                return false;
            } catch (Exception e) {
                LogUtils.e(TAG, e, "Fota service hasCampaign exception");
                return false;
            }
        }
        LogUtils.e(TAG, "Fota service stopped");
        return false;
    }

    private void checkUpdate(String str) {
        List<PackageInfo> packageInfoList = PackageUtils.getPackageInfoList(this, getSotaList(Constants.ConfigKey.SOTA_LIST));
        if (ArrayUtils.isEmpty(packageInfoList)) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            SOTAManager.checkUpdate(packageInfoList, str);
        } else {
            SOTAManager.checkUpdate(packageInfoList, OTAConstants.CheckSource.TRIGGER_ACTION_USER);
        }
    }

    private List<String> getSotaList(String str) {
        if (this.mServiceConnected) {
            try {
                String configString = this.mOtaService.getConfigString(str);
                LogUtils.d(TAG, "getSotaList:" + configString);
                List<String> listFromJson = JsonUtils.listFromJson(configString);
                if (listFromJson != null) {
                    if (!listFromJson.isEmpty()) {
                        return listFromJson;
                    }
                }
            } catch (RemoteException unused) {
                LogUtils.w(TAG, "get getSotaList(%s) fail", str);
            }
        }
        return Collections.singletonList("com.xiaopeng.ota");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void timeoutTask() {
        LogUtils.d(TAG, "Sota install timeout");
        if (this.mServiceConnected) {
            try {
                this.mOtaService.closeSotaUpgrade();
            } catch (RemoteException e) {
                LogUtils.e(TAG, e, "timeoutTask, call closeSotaUpgrade fail");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaopeng.ota.system.SotaService$4  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass4 extends SOTAListener {
        AnonymousClass4() {
        }

        @Override // com.xiaopeng.sota.sdk.listener.SOTAListener
        public void onNewVersion(List<String> list) {
            super.onNewVersion(list);
            LogUtils.d(SotaService.TAG, "onNewVersion: " + JsonUtils.toJson(list));
        }

        @Override // com.xiaopeng.sota.sdk.listener.SOTAListener
        public void onUpgradeInvalid() {
            super.onUpgradeInvalid();
            LogUtils.d(SotaService.TAG, "onUpgradeInvalid");
            if (SotaService.this.mServiceConnected) {
                try {
                    SotaService.this.mOtaService.closeSotaUpgrade();
                } catch (RemoteException e) {
                    LogUtils.e(SotaService.TAG, e, "onUpgradeInvalid, call closeSotaUpgrade fail");
                }
            }
        }

        @Override // com.xiaopeng.sota.sdk.listener.SOTAListener
        public void onDownloadReady(List<String> list, long j) {
            super.onDownloadReady(list, j);
            LogUtils.d(SotaService.TAG, "onDownloadReady, list: %s, total: %d", JsonUtils.toJson(list), Long.valueOf(j));
        }

        @Override // com.xiaopeng.sota.sdk.listener.SOTAListener
        public void onDownloadStart(String str, long j) {
            super.onDownloadStart(str, j);
            LogUtils.d(SotaService.TAG, "onDownloadStart packageName(%s)", str);
        }

        @Override // com.xiaopeng.sota.sdk.listener.SOTAListener
        public void onDownloading(String str, long j, long j2) {
            super.onDownloading(str, j, j2);
            LogUtils.d(SotaService.TAG, "onDownloading packageName(%s) -> %d", str, Long.valueOf((j * 100) / j2));
        }

        @Override // com.xiaopeng.sota.sdk.listener.SOTAListener
        public void onDownloadEnd(String str) {
            super.onDownloadEnd(str);
            LogUtils.d(SotaService.TAG, "onDownloadEnd packageName(%s)", str);
        }

        @Override // com.xiaopeng.sota.sdk.listener.SOTAListener
        public void onDownloadComplete() {
            super.onDownloadComplete();
            LogUtils.d(SotaService.TAG, "onDownloadComplete");
            if (SotaService.this.isFotaBusy()) {
                return;
            }
            SOTAManager.install(true);
        }

        @Override // com.xiaopeng.sota.sdk.listener.SOTAListener
        public void onDownloadError(String str, Exception exc) {
            super.onDownloadError(str, exc);
            LogUtils.e(SotaService.TAG, exc, "onDownloadError packageName(%s)", str);
        }

        @Override // com.xiaopeng.sota.sdk.listener.SOTAListener
        public void onInstallReady(int i) {
            super.onInstallReady(i);
            LogUtils.d(SotaService.TAG, "onInstallReady: %d", Integer.valueOf(i));
        }

        @Override // com.xiaopeng.sota.sdk.listener.SOTAListener
        public void onInstallStart(String str, int i, int i2, int i3) {
            super.onInstallStart(str, i, i2, i3);
            LogUtils.d(SotaService.TAG, "onInstallStart(%d/%d) packageName(%s), mode(%d)", Integer.valueOf(i2), Integer.valueOf(i3), str, Integer.valueOf(i));
            SotaService.this.mTimeoutLock.close();
            ThreadUtils.postBackgroundLong(new Runnable() { // from class: com.xiaopeng.ota.system.-$$Lambda$SotaService$4$5IQp8QsEAXU8FGuktAtqoz4KSpg
                @Override // java.lang.Runnable
                public final void run() {
                    SotaService.AnonymousClass4.this.lambda$onInstallStart$0$SotaService$4();
                }
            });
            if (!SotaService.this.mServiceConnected || i == 2) {
                return;
            }
            try {
                SotaService.this.mOtaService.promptSotaUpgrade(i3, i2);
            } catch (RemoteException unused) {
                LogUtils.d(SotaService.TAG, "onInstallStart, call promptSotaUpgrade fail");
            }
        }

        public /* synthetic */ void lambda$onInstallStart$0$SotaService$4() {
            if (SotaService.this.mTimeoutLock.block(SotaService.this.mTimeout)) {
                return;
            }
            SotaService.this.timeoutTask();
        }

        @Override // com.xiaopeng.sota.sdk.listener.SOTAListener
        public void onInstallationResult(String str, boolean z) {
            super.onInstallationResult(str, z);
            LogUtils.d(SotaService.TAG, "onInstallationResult packageName(%s) -> result: %b", str, Boolean.valueOf(z));
            SotaService.this.mTimeoutLock.open();
        }

        @Override // com.xiaopeng.sota.sdk.listener.SOTAListener
        public void onInstallComplete() {
            super.onInstallComplete();
            LogUtils.d(SotaService.TAG, "onInstallComplete");
            SotaService.this.mTimeoutLock.open();
        }

        @Override // com.xiaopeng.sota.sdk.listener.SOTAListener
        public void onInstallError(String str, Exception exc) {
            super.onInstallError(str, exc);
            LogUtils.e(SotaService.TAG, exc, "onInstallError packageName(%s)", str);
            if (SotaService.this.mServiceConnected) {
                try {
                    SotaService.this.mOtaService.closeSotaUpgrade();
                } catch (RemoteException e) {
                    LogUtils.e(SotaService.TAG, e, "onInstallComplete, call closeSotaUpgrade fail");
                }
            }
        }

        @Override // com.xiaopeng.sota.sdk.listener.SOTAListener
        public void onCampaignCheckResult(boolean z) {
            super.onCampaignCheckResult(z);
        }
    }
}
