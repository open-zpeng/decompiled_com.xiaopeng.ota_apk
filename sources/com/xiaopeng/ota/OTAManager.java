package com.xiaopeng.ota;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.ConditionVariable;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import com.xiaopeng.fota.sdk.UpgradeUtils;
import com.xiaopeng.lib.framework.ipcmodule.IpcModuleEntry;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.ipcmodule.IIpcService;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import com.xiaopeng.lib.framework.netchannelmodule.common.TrafficeStaFlagInterceptor;
import com.xiaopeng.lib.http.HttpsUtils;
import com.xiaopeng.lib.utils.info.BuildInfoUtils;
import com.xiaopeng.ota.auto.CarApi;
import com.xiaopeng.ota.client.OTAClient;
import com.xiaopeng.ota.presenter.config.ConfigManager;
import com.xiaopeng.ota.presenter.config.PreferencesManager;
import com.xiaopeng.ota.presenter.db.CampaignManager;
import com.xiaopeng.ota.presenter.event.EventPresenter;
import com.xiaopeng.ota.presenter.push.PushReceiver;
import com.xiaopeng.ota.presenter.update.UpgradePresenter;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.remind.RemindManager;
import com.xiaopeng.ota.sdk.common.log.LogConfiguration;
import com.xiaopeng.ota.sdk.common.log.LogFactory;
import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.sdk.common.log.LoggerThread;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.PackageUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.ota.utils.VersionUtils;
import com.xiaopeng.ota.utils.WebViewUtils;
import com.xiaopeng.ota.version.ReleaseVersion;
import com.xiaopeng.ota.version.ReleaseVersionManager;
import com.xiaopeng.ota.version.os.OsVersion;
import com.xiaopeng.ota.version.os.OsVersionManager;
import com.xiaopeng.sota.sdk.SOTAManager;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes2.dex */
public class OTAManager {
    public static final int MODE_FOTA = 0;
    public static final int MODE_SOTA = 1;
    private static final String TAG = "OTAManager";
    private static CampaignManager sCampaignManager;
    private static ConfigManager sConfigManager;
    private static Context sContext;
    private static LoggerThread sLoggerThread;
    private static OsVersionManager sOsVersionManager;
    private static PreferencesManager sPreferencesManager;
    private static PushReceiver sPushReceiver;
    private static ReleaseVersionManager sReleaseVersionManager;
    private static RemindManager sRemindManager;
    private static Lock sLock = new ReentrantLock();
    private static ConditionVariable sConditionVariable = new ConditionVariable();

    public static void init(Application application, int i, Runnable runnable) {
        sContext = application;
        if (i == 1) {
            initForSOTA(application, runnable);
        } else {
            initForFOTA(application, runnable);
        }
    }

    public static void dispose() {
        LoggerThread.exit();
        PushReceiver pushReceiver = sPushReceiver;
        if (pushReceiver != null) {
            pushReceiver.destroy();
        }
    }

    private static void initForFOTA(final Application application, final Runnable runnable) {
        sConditionVariable.close();
        Module.register(IpcModuleEntry.class, new IpcModuleEntry(application));
        Module.register(NetworkChannelsEntry.class, new NetworkChannelsEntry());
        try {
            EventBus.builder().sendNoSubscriberEvent(false).logNoSubscriberMessages(false).logSubscriberExceptions(false).installDefaultEventBus();
        } catch (Exception unused) {
            Log.w(TAG, "EventBus init error");
        }
        sConfigManager = new ConfigManager(application);
        sCampaignManager = new CampaignManager(application);
        sReleaseVersionManager = new ReleaseVersionManager(application);
        sPreferencesManager = new PreferencesManager(application);
        sRemindManager = new RemindManager(application);
        sOsVersionManager = new OsVersionManager(application);
        ThreadUtils.postBackgroundLong(new Runnable() { // from class: com.xiaopeng.ota.-$$Lambda$OTAManager$TLV0Q8fpUOR-XQPVb0eb88VQopw
            @Override // java.lang.Runnable
            public final void run() {
                OTAManager.lambda$initForFOTA$0(application, runnable);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initForFOTA$0(Application application, Runnable runnable) {
        WebViewUtils.hookWebView();
        sConfigManager.init();
        sCampaignManager.init();
        sReleaseVersionManager.init();
        sPreferencesManager.init();
        sRemindManager.init();
        sOsVersionManager.init();
        try {
            HttpsUtils.init(application, false);
        } catch (Exception unused) {
            Log.w(TAG, "Https init error");
        }
        ((IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class)).config().applicationContext(application).retryCount(5).enableTrafficStats().addInterceptor(new TrafficeStaFlagInterceptor()).apply();
        Log.d(TAG, "Init ipc service");
        ((IIpcService) Module.get(IpcModuleEntry.class).get(IIpcService.class)).init();
        CarApi.init(application);
        sPushReceiver = new PushReceiver(application);
        sPushReceiver.initialize();
        initLogUtil(application);
        Log.d(TAG, "Fota init completed");
        sConditionVariable.open();
        ThreadUtils.postMainThread(runnable);
    }

    private static void initForSOTA(Application application, Runnable runnable) {
        sConditionVariable.close();
        Module.register(NetworkChannelsEntry.class, new NetworkChannelsEntry());
        try {
            EventBus.builder().sendNoSubscriberEvent(false).logNoSubscriberMessages(false).logSubscriberExceptions(false).installDefaultEventBus();
        } catch (Exception unused) {
            Log.w(TAG, "EventBus init error");
        }
        try {
            HttpsUtils.init(application, false);
        } catch (Exception unused2) {
            Log.w(TAG, "Https init error");
        }
        ((IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class)).config().applicationContext(application).retryCount(5).enableTrafficStats().addInterceptor(new TrafficeStaFlagInterceptor()).apply();
        SOTAManager.initialize(application, LocaleConfig.getHttpHost(), Constants.CONFIG_APP_ID, Constants.CONFIG_AP_SECRET);
        sConditionVariable.open();
        ThreadUtils.postMainThread(runnable);
    }

    private static void initLogUtil(Application application) {
        Log.w(TAG, "Init log util");
        PackageInfo packageInfo = PackageUtils.getPackageInfo(application, application.getPackageName());
        String str = packageInfo != null ? packageInfo.versionName : "UnKnown";
        LogConfiguration.Builder builder = new LogConfiguration.Builder();
        LogFactory.setConfiguration(builder.header("ota version:" + str).moduleName("fota").deleteExpired(true).capacity(30).build());
        sLoggerThread = new LoggerThread();
        sLoggerThread.start();
        Logger.setDefaultLogLevel(0);
    }

    public static void handlePowerOn() {
        ThreadUtils.postBackgroundLong(new Runnable() { // from class: com.xiaopeng.ota.-$$Lambda$OTAManager$jbCaaF5IXyB_NgnN_6VGV8ygt2k
            @Override // java.lang.Runnable
            public final void run() {
                OTAManager.lambda$handlePowerOn$1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$handlePowerOn$1() {
        getRemindManager().checkNewByPowerOn();
        LogUtils.d(TAG, "Check new campaign");
        UpgradeUtils.OtaSystemEvent(UpgradeUtils.EVENT_SCREEN_ON);
        if (!sLock.tryLock()) {
            LogUtils.w(TAG, "Request remote data sync in progress");
            return;
        }
        try {
            getConfigManager().sync(!UpgradePresenter.hasCampaign());
            for (int i = 0; i < 3; i++) {
                try {
                    syncOsVersion();
                } catch (Exception e) {
                    LogUtils.e(TAG, e, "Sync os version failed");
                    try {
                        Thread.sleep(TimeUnit.SECONDS.toMillis(5L));
                    } catch (Exception unused) {
                    }
                }
            }
            try {
                getRemindManager().getDrivingRouteManager().save(OTAClient.getInstance().requestDrivingRoute());
            } catch (Exception e2) {
                LogUtils.e(TAG, e2, "Request driving route failed");
            }
        } finally {
            sLock.unlock();
        }
    }

    public static void handleXLogoClicked() {
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.-$$Lambda$OTAManager$_rw3kcyh3KRHlChLHgolePOqqsE
            @Override // java.lang.Runnable
            public final void run() {
                EventPresenter.getInstance().sendXlogoClickedEvent(OTAManager.getCampaignManager().hasCampaign());
            }
        });
        ThreadUtils.postBackgroundLong(new Runnable() { // from class: com.xiaopeng.ota.-$$Lambda$OTAManager$iTu_cIxgay4cSD0cpXluqKs4YQk
            @Override // java.lang.Runnable
            public final void run() {
                OTAManager.lambda$handleXLogoClicked$3();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$handleXLogoClicked$3() {
        LogUtils.d(TAG, "Check campaign");
        UpgradeUtils.OtaCheckUpdates();
        if (getContext() != null && UpgradeUtils.OtaGetCampaignStatus() == 1) {
            LogUtils.d(TAG, "Check sota campaign");
            Intent intent = new Intent();
            intent.setAction(Config.ACTION_SOTA_CHECK_UPDATE);
            getContext().sendBroadcastAsUser(intent, UserHandle.CURRENT);
            return;
        }
        LogUtils.d(TAG, "Skip sota campaign");
    }

    public static Context getContext() {
        return sContext;
    }

    public static ReleaseVersionManager getReleaseVersionManager() {
        return sReleaseVersionManager;
    }

    public static ConfigManager getConfigManager() {
        return sConfigManager;
    }

    public static CampaignManager getCampaignManager() {
        return sCampaignManager;
    }

    public static PreferencesManager getPreferencesManager() {
        return sPreferencesManager;
    }

    public static RemindManager getRemindManager() {
        return sRemindManager;
    }

    public static OsVersionManager getOsVersionManager() {
        return sOsVersionManager;
    }

    public static void waitAvailable() {
        waitAvailable(TimeUnit.SECONDS.toMillis(5L));
    }

    public static void waitAvailable(long j) {
        sConditionVariable.block(j);
    }

    public static String getCurrentOTAVersion() {
        return getOsVersion();
    }

    public static int getOsVersionCode() {
        OsVersion osVersion = getOsVersionManager().getOsVersion();
        if (osVersion != null) {
            return osVersion.getVersionCode();
        }
        return 0;
    }

    private static String getOsVersion() {
        String str = null;
        try {
            OsVersion osVersion = getOsVersionManager().getOsVersion();
            if (osVersion != null && !TextUtils.isEmpty(osVersion.getVersionName())) {
                str = VersionUtils.getSimpleVersion(osVersion.getVersionName());
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Get os version occurs Exception");
        }
        return TextUtils.isEmpty(str) ? getReleaseVersion() : str;
    }

    private static String getReleaseVersion() {
        try {
            ReleaseVersion releaseVersion = getReleaseVersionManager().getReleaseVersion();
            return (releaseVersion == null || TextUtils.isEmpty(releaseVersion.getVersion())) ? "" : VersionUtils.getSimpleVersion(releaseVersion.getVersion());
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Get release version occurs Exception");
            return "";
        }
    }

    public static String getCurrentCduVersion() {
        return removeVersionPrefix(BuildInfoUtils.getSystemVersion());
    }

    public static String getCurrentCduVersionBuildTime() {
        return removeVersionPrefix(VersionUtils.getCduVersionBuildTime());
    }

    private static String removeVersionPrefix(String str) {
        return TextUtils.isEmpty(str) ? "" : (str.startsWith("V") || str.startsWith("v")) ? str.substring(1) : str;
    }

    public static String syncOsVersion() throws Exception {
        OsVersion osVersion = getOsVersionManager().getOsVersion();
        if (osVersion == null) {
            Logger.d(TAG, "Local os version not exist, pull os version", new Object[0]);
            OsVersion pullOSVersion = OTAClient.getInstance().pullOSVersion();
            if (pullOSVersion != null) {
                getOsVersionManager().save(pullOSVersion);
                if (!TextUtils.isEmpty(pullOSVersion.getVersionName())) {
                    return pullOSVersion.getVersionName();
                }
            }
            return null;
        }
        return osVersion.getVersionName();
    }

    public static void saveOsVersion(long j) {
        Campaign campaign = getCampaignManager().getCampaign(j);
        if (campaign != null) {
            if (campaign.getCampaignId() <= 0 || campaign.isSilentMode()) {
                return;
            }
            OsVersion osVersion = new OsVersion();
            osVersion.setVersionCode(campaign.getReleaseVersionCode());
            osVersion.setVersionName(campaign.getReleaseVersion());
            osVersion.setFeatures(campaign.getFeatures());
            getOsVersionManager().save(osVersion);
            return;
        }
        LogUtils.d(TAG, "Os version not save: campaign is null");
    }
}
