package com.xiaopeng.sota.sdk;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import android.util.Log;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import com.xiaopeng.ota.sdk.campaign.CampaignEventListener;
import com.xiaopeng.ota.sdk.campaign.event.CampaignEventProducedEvent;
import com.xiaopeng.ota.sdk.common.log.LogConfiguration;
import com.xiaopeng.ota.sdk.common.log.LogFactory;
import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.sdk.common.log.LoggerThread;
import com.xiaopeng.ota.sdk.common.util.AppManager;
import com.xiaopeng.ota.sdk.common.util.AppUtils;
import com.xiaopeng.ota.sdk.common.util.ArrayUtils;
import com.xiaopeng.ota.sdk.common.util.CheckUtils;
import com.xiaopeng.ota.sdk.common.util.DigestUtils;
import com.xiaopeng.ota.sdk.common.util.FileUtils;
import com.xiaopeng.ota.sdk.common.util.ThreadUtils;
import com.xiaopeng.ota.sdk.eventbroker.Event;
import com.xiaopeng.sota.sdk.common.Constants;
import com.xiaopeng.sota.sdk.listener.SOTAListener;
import com.xiaopeng.sota.sdk.listener.SOTAListenerInvoker;
import com.xiaopeng.sota.sdk.manager.ConfigManager;
import com.xiaopeng.sota.sdk.manager.campaign.Campaign;
import com.xiaopeng.sota.sdk.manager.campaign.CampaignEventManager;
import com.xiaopeng.sota.sdk.manager.campaign.CampaignManager;
import com.xiaopeng.sota.sdk.manager.download.CampaignDownloadManager;
import java.util.List;
/* loaded from: classes2.dex */
public class SOTAManager {
    private static final String TAG = "SOTAManager";
    private static boolean initialized;
    private static AppManager sAppManager;
    private static CampaignChecker sCampaignChecker;
    private static CampaignDownloadManager sCampaignDownloadManager;
    private static CampaignEventManager sCampaignEventManager;
    private static CampaignEventUploader sCampaignEventUploader;
    private static CampaignManager sCampaignManager;
    private static CampaignUpdater sCampaignUpdater;
    private static SOTAConfig sConfig;
    private static ConfigManager sConfigManager;
    private static Context sContext;
    private static IHttp sHttp;
    private static LoggerThread sLoggerThread;
    private static NetworkBroadcastReceiver sNetworkBroadcastReceiver;
    private static SOTAListenerInvoker sSOTAListenerInvoker;
    private static final CampaignEventProducedEvent sCampaignEventProducedEvent = new CampaignEventProducedEvent();
    private static CampaignEventListener sCampaignEventListener = new CampaignEventListener() { // from class: com.xiaopeng.sota.sdk.SOTAManager.1
        @Override // com.xiaopeng.ota.sdk.campaign.CampaignEventListener
        public void onCreated(long j, String str, int i) {
            SOTAManager.sendEvent(SOTAManager.sCampaignEventProducedEvent);
        }
    };

    public static void initialize(Application application, String str, String str2, String str3) {
        CheckUtils.isNotNull(application);
        CheckUtils.isNotEmpty(str);
        CheckUtils.isNotEmpty(str2);
        CheckUtils.isNotEmpty(str3);
        Log.d(TAG, "###### SOTA sdk version:2.18.0.17");
        if (!initialized) {
            sContext = application;
            Logger.setDefaultLogLevel(0);
            LogFactory.setConfiguration(new LogConfiguration.Builder().header("SOTA sdk version:2.18.0.17").moduleName(Constants.DOWNLOAD_FOLDER).deleteExpired(false).build());
            sLoggerThread = new LoggerThread();
            sLoggerThread.start();
            SOTAExecutor.initialize();
            sConfig = new SOTAConfig(str, str2, str3);
            sConfigManager = new ConfigManager();
            if (sConfigManager.getHost() != null) {
                getConfig().setHost(sConfigManager.getHost());
            }
            sSOTAListenerInvoker = new SOTAListenerInvoker();
            sCampaignChecker = new CampaignChecker();
            sCampaignManager = new CampaignManager(application);
            sCampaignDownloadManager = new CampaignDownloadManager(application.getDir(Constants.DOWNLOAD_FOLDER, 0).getAbsolutePath(), 1);
            sCampaignEventManager = new CampaignEventManager(application, sCampaignEventListener);
            sCampaignEventUploader = new CampaignEventUploader(sCampaignEventManager);
            sCampaignEventUploader.start();
            sCampaignUpdater = new CampaignUpdater();
            sAppManager = new AppManager();
            sHttp = (IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class);
            sNetworkBroadcastReceiver = new NetworkBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            application.registerReceiver(sNetworkBroadcastReceiver, intentFilter);
            initialized = true;
            Logger.d(TAG, "SOTA initialized", new Object[0]);
            return;
        }
        Logger.d(TAG, "Has initialized", new Object[0]);
    }

    public static Context getContext() {
        return sContext;
    }

    public static SOTAConfig getConfig() {
        return sConfig;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SOTAListenerInvoker getListenerInvoker() {
        return sSOTAListenerInvoker;
    }

    public static String getEnvironment() {
        String host = getConfig().getHost();
        if (TextUtils.isEmpty(host)) {
            return null;
        }
        return DigestUtils.MD5(host);
    }

    public static CampaignManager getCampaignManager() {
        return sCampaignManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CampaignDownloadManager getCampaignDownloadManager() {
        return sCampaignDownloadManager;
    }

    static CampaignChecker getCampaignChecker() {
        return sCampaignChecker;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CampaignUpdater getCampaignUpdater() {
        return sCampaignUpdater;
    }

    public static CampaignEventManager getCampaignEventManager() {
        return sCampaignEventManager;
    }

    static CampaignEventUploader getCampaignEventUploader() {
        return sCampaignEventUploader;
    }

    public static ConfigManager getConfigManager() {
        return sConfigManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AppManager getAppManager() {
        return sAppManager;
    }

    public static IHttp getHttp() {
        return sHttp;
    }

    private static void hasInitialized() {
        if (!initialized) {
            throw new RuntimeException("Please call initialize() first");
        }
    }

    public static void register(SOTAListener sOTAListener) {
        hasInitialized();
        CheckUtils.isNotNull(sOTAListener);
        sSOTAListenerInvoker.register(sOTAListener);
    }

    public static void unregister(SOTAListener sOTAListener) {
        hasInitialized();
        CheckUtils.isNotNull(sOTAListener);
        sSOTAListenerInvoker.unregister(sOTAListener);
    }

    public static void switchHost(String str) {
        if (getConfig() == null) {
            return;
        }
        Object[] objArr = new Object[2];
        objArr[0] = getConfig().getHost() == null ? "" : getConfig().getHost();
        objArr[1] = str;
        Logger.d(TAG, "Host has switched from [%s] to [%s]", objArr);
        getConfig().setHost(str);
        sConfigManager.setHost(str);
    }

    public static void sendEvent(Event event) {
        Logger.d(TAG, "Event:%s", event.getName());
        SOTAEventBroker.getEventBroker().postEvent(event);
    }

    public static synchronized void checkUpdate(List<PackageInfo> list, String str) {
        synchronized (SOTAManager.class) {
            Logger.d(TAG, "checkUpdate", new Object[0]);
            hasInitialized();
            PackageInfo packageInfo = new PackageInfo();
            packageInfo.packageName = sContext.getPackageName();
            packageInfo.versionName = AppUtils.getVersionName(getContext());
            packageInfo.versionCode = AppUtils.getVersionCode(getContext());
            sCampaignChecker.checkUpdate(packageInfo, list, str);
        }
    }

    public static synchronized boolean hasCampaign() {
        synchronized (SOTAManager.class) {
            Logger.d(TAG, "hasCampaign", new Object[0]);
            int campaignCount = sCampaignManager.getCampaignCount();
            if (campaignCount > 0) {
                Logger.d(TAG, "has campaign: " + campaignCount, new Object[0]);
                return true;
            }
            return false;
        }
    }

    public static synchronized void install(boolean z) {
        synchronized (SOTAManager.class) {
            Logger.d(TAG, "install", new Object[0]);
            if (!sCampaignManager.allCampaignDownloaded()) {
                Logger.d(TAG, "Not all campaigns have downloaded", new Object[0]);
                return;
            }
            List<Campaign> silentAvailable = z ? sCampaignManager.getSilentAvailable() : sCampaignManager.getAllAvailable();
            Logger.d(TAG, "Install campaign list size=%d silent=%b", Integer.valueOf(silentAvailable.size()), Boolean.valueOf(z));
            if (!ArrayUtils.isEmpty(silentAvailable)) {
                hasInitialized();
                sCampaignUpdater.install(silentAvailable);
            } else if (!z) {
                sConfigManager.setInstallingState(false);
                Logger.i(TAG, "No available campaign exist, exit install", new Object[0]);
            }
        }
    }

    public static synchronized boolean isInstalling() {
        boolean isInstalling;
        synchronized (SOTAManager.class) {
            isInstalling = sConfigManager.isInstalling();
        }
        return isInstalling;
    }

    public static void clearDatabase() {
        Logger.d(TAG, "clearDatabase", new Object[0]);
        if (sConfigManager.isInstalling()) {
            Logger.d(TAG, "clearDatabase, is installing & cancel clear database", new Object[0]);
            return;
        }
        sCampaignDownloadManager.cancelAll(2000L);
        sConfigManager.clear();
        sCampaignManager.deleteAll();
        sCampaignEventManager.deleteAll();
    }

    public static void clearDownloadFiles() {
        Logger.d(TAG, "clearDownloadFiles", new Object[0]);
        if (sConfigManager.isInstalling()) {
            Logger.d(TAG, "clearDatabase, is installing & cancel clear download files", new Object[0]);
            return;
        }
        CampaignDownloadManager campaignDownloadManager = sCampaignDownloadManager;
        if (campaignDownloadManager == null) {
            Logger.d(TAG, "sCampaignDownloadManager null", new Object[0]);
            return;
        }
        campaignDownloadManager.cancelAll(2000L);
        FileUtils.delete(sCampaignDownloadManager.getDownloadPath());
    }

    public static void cancelCampaign() {
        Logger.d(TAG, "cancelCampaign", new Object[0]);
        if (sConfigManager.isInstalling()) {
            Logger.d(TAG, "Cancel campaign", new Object[0]);
            sCampaignUpdater.stop();
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.sota.sdk.-$$Lambda$SOTAManager$LJ73MCIff8-RV5sA33R8rrnvOcE
                @Override // java.lang.Runnable
                public final void run() {
                    SOTAManager.lambda$cancelCampaign$5();
                }
            }, 20000L);
            return;
        }
        sCampaignDownloadManager.cancelAll(2000L);
        sConfigManager.clear();
        sCampaignManager.deleteAll();
        sCampaignEventManager.deleteAll();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$cancelCampaign$5() {
        sConfigManager.clear();
        sCampaignManager.deleteAll();
        sCampaignEventManager.deleteAll();
    }

    public static void retryDownload() {
        Logger.d(TAG, "retryDownload", new Object[0]);
        if (sCampaignManager.allCampaignDownloaded()) {
            Logger.d(TAG, "retryDownload, all campaign had downloaded", new Object[0]);
        } else {
            sCampaignChecker.resumeDownload();
        }
    }

    public static void dispose() {
        if (sLoggerThread != null) {
            LoggerThread.exit();
        }
        if (getContext() != null) {
            getContext().unregisterReceiver(sNetworkBroadcastReceiver);
        }
        SOTAExecutor.dispose();
        if (initialized) {
            sCampaignManager.dispose();
            sCampaignChecker.dispose();
            sCampaignEventManager.dispose();
            sCampaignEventUploader.dispose();
            sSOTAListenerInvoker.dispose();
            initialized = false;
        }
    }
}
