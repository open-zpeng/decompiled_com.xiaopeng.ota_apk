package com.xiaopeng.fota.sdk;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.File;
/* loaded from: classes2.dex */
public final class UpgradeUtils {
    public static final String ACTION_REQUEST_CLEAN_CACHES = "com.xiaopeng.intent.action.REQUEST_CLEAN_CACHES";
    public static final String CMD_SHELL = "shell";
    public static final String EVENT_CLEAN_CACHES = "clean caches";
    public static final String EVENT_NETWORK_CHANGED = "network changed";
    public static final String EVENT_POWER_OFF = "power off";
    public static final String EVENT_POWER_ON = "power on";
    public static final String EVENT_SCREEN_OFF = "screen off";
    public static final String EVENT_SCREEN_ON = "screen on";
    public static final String EVENT_SHUTDOWN = "shutdown";
    public static final String EVENT_SYNC_INFO = "sync info";
    public static final int OTA_CAMPAIGN_ST_CHECK_CONDITION = 8;
    public static final int OTA_CAMPAIGN_ST_CHECK_VERSION = 2;
    public static final int OTA_CAMPAIGN_ST_CLEANUP = 10;
    public static final int OTA_CAMPAIGN_ST_CLEANUP_ECU = 3;
    public static final int OTA_CAMPAIGN_ST_CONFIGURE = 11;
    public static final int OTA_CAMPAIGN_ST_DOWNLOAD = 4;
    public static final int OTA_CAMPAIGN_ST_IDLE = 1;
    public static final int OTA_CAMPAIGN_ST_INVALID = 0;
    public static final int OTA_CAMPAIGN_ST_PREPARE = 6;
    public static final int OTA_CAMPAIGN_ST_TRANSFER = 5;
    public static final int OTA_CAMPAIGN_ST_UPGRADE = 9;
    public static final int OTA_CAMPAIGN_ST_VERIFY = 12;
    public static final int OTA_CAMPAIGN_ST_WAIT_CONFIRM = 7;
    public static final int OTA_CAMPAIGN_ST_WAIT_RETRY = 13;
    public static final byte OTA_HTTP_GET = 0;
    public static final byte OTA_HTTP_POST = 1;
    public static final byte OTA_HTTP_UPLOAD = 2;
    public static final short OTA_STATUS_ACTIVATE = 5;
    public static final short OTA_STATUS_BUSY = 1;
    public static final short OTA_STATUS_COMMIT = 8;
    public static final short OTA_STATUS_COMPLETED = 9;
    public static final short OTA_STATUS_IDLE = 0;
    public static final short OTA_STATUS_PREPARE = 2;
    public static final short OTA_STATUS_RESET = 6;
    public static final short OTA_STATUS_ROLLBACK = 7;
    public static final short OTA_STATUS_STARTED = 3;
    public static final short OTA_STATUS_UPGRADE = 4;
    public static final byte OTA_ZONE_A = 1;
    public static final byte OTA_ZONE_B = 2;
    public static final byte OTA_ZONE_INVALID = 0;
    public static final int PUSH_EVENT_CAMPAIGN_ABORT = 11026;
    public static final int PUSH_EVENT_CAMPAIGN_CHECK = 11001;
    public static final int PUSH_EVENT_CAMPAIGN_CLEAR = 11008;
    public static final int PUSH_EVENT_CAMPAIGN_INSTALL = 11015;
    public static final int PUSH_EVENT_CAMPAIGN_PROMPT = 11007;
    public static final int PUSH_EVENT_CANCEL_WAKEUP = 11016;
    public static final int PUSH_EVENT_DATABASE_CLEAR = 11005;
    public static final int PUSH_EVENT_DOWNLOAD_CLEAR = 11006;
    public static final int PUSH_EVENT_DOWNLOAD_PAUSE = 11029;
    public static final int PUSH_EVENT_DOWNLOAD_RESUME = 11028;
    public static final int PUSH_EVENT_DOWNLOAD_RETRY = 11013;
    public static final int PUSH_EVENT_RESET_APP = 11019;
    public static final int PUSH_EVENT_RESET_ECU = 11021;
    public static final int PUSH_EVENT_RESET_RETRIES = 11025;
    public static final int PUSH_EVENT_RESET_XOS_VERSION = 11023;
    public static final int PUSH_EVENT_SCHEDULE_WAKEUP = 11017;
    public static final int PUSH_EVENT_SCRIPT_CLEAR = 11031;
    public static final int PUSH_EVENT_SCRIPT_INSTALL = 11030;
    public static final int PUSH_EVENT_SYNC_ALL_ECUS = 11004;
    public static final int PUSH_EVENT_SYNC_STATUS = 11020;
    public static final int PUSH_EVENT_UPDATE_CONFIG = 11002;
    public static final int PUSH_EVENT_UPDATE_XOS_VERSION = 11024;
    public static final int PUSH_EVENT_UPLOAD_LOG = 11003;
    public static final int PUSH_EVENT_VERSION_CLEAR = 11027;
    private static ConnectivityManager.NetworkCallback connCallback;
    private static ConnectivityManager connManager;
    private static Context sContext;
    private static String sStoragePath;
    private static String sUsbPath;
    private static BroadcastReceiver shutdownReceiver;

    public static native int OtaCancelUpgrade(long j);

    public static native int OtaCheckUpdates();

    @Nullable
    public static native String OtaGetCampaign();

    public static native int OtaGetCampaignRetryTimes();

    public static native int OtaGetCampaignStatus();

    public static native int OtaGetEcuVersions();

    @Nullable
    public static native String OtaGetOtaVersion();

    @Nullable
    public static native String OtaGetRemoteConfig();

    public static native String OtaGetScheduleTime();

    public static native int OtaHandlePushEvent(int i, long j, @NonNull String str, @Nullable String str2);

    public static native boolean OtaIsReady();

    @Nullable
    public static native byte[] OtaReadDid(int i, int i2);

    public static native int OtaScheduleWakeup(@NonNull String str, short s, short s2, short s3);

    public static native int OtaSendEvent(long j, @NonNull String str, @Nullable String str2, int i);

    public static native int OtaSendPushFeedback(@NonNull String str);

    public static native int OtaStartUpgrade(long j, boolean z);

    public static native int OtaSuspendUpgrade(long j);

    public static native int OtaSystemEvent(@NonNull String str);

    public static native void OtaUpgradeProgress(long j, short s, short s2, int i, @Nullable String str);

    @Nullable
    public static native int OtaWriteDid(int i, int i2, byte[] bArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getHttpConnectTimeout(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getHttpReadTimeout(int i);

    private static native void init(@NonNull Context context, @NonNull String str);

    private static native int start(@NonNull String str, @NonNull UpgradeAgentBase upgradeAgentBase, @NonNull UpgradeExecutorBase[] upgradeExecutorBaseArr, @NonNull UpgradeInterfaceBase upgradeInterfaceBase);

    private static native int stop();

    private static native int usbUpgrade(@NonNull String str);

    static {
        System.loadLibrary("ota-cdu");
        sContext = null;
        sStoragePath = null;
        sUsbPath = null;
        shutdownReceiver = null;
        connManager = null;
        connCallback = null;
    }

    static String getDownloadPath() {
        return sStoragePath + "/dl";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static File getUsbFile(@NonNull String str) {
        String str2 = sUsbPath;
        if (str2 == null) {
            return null;
        }
        return new File(str2, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Context context() {
        return sContext;
    }

    public static void OtaInit(@NonNull Context context) {
        OtaInit(context, context.getDir("fota", 0).getAbsolutePath());
    }

    public static void OtaInit(@NonNull Context context, @NonNull String str) {
        sContext = context;
        VehicleProperty.init(context);
        init(context, str);
    }

    public static int OtaStart(@NonNull String str, @NonNull UpgradeExecutorBase[] upgradeExecutorBaseArr, @NonNull UpgradeInterfaceBase upgradeInterfaceBase) {
        sStoragePath = str;
        int start = start(str, new UpgradeAgent(), upgradeExecutorBaseArr, upgradeInterfaceBase);
        if (start == 0) {
            shutdownReceiver = new BroadcastReceiver() { // from class: com.xiaopeng.fota.sdk.UpgradeUtils.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    UpgradeUtils.OtaSystemEvent(UpgradeUtils.EVENT_SHUTDOWN);
                }
            };
            sContext.registerReceiver(shutdownReceiver, new IntentFilter("android.intent.action.ACTION_SHUTDOWN"));
            connManager = (ConnectivityManager) context().getSystemService("connectivity");
            if (connManager != null) {
                connCallback = new ConnectivityManager.NetworkCallback() { // from class: com.xiaopeng.fota.sdk.UpgradeUtils.2
                    public void onAvailable(Network network, NetworkCapabilities networkCapabilities, LinkProperties linkProperties) {
                        if (networkCapabilities != null) {
                            if (networkCapabilities.hasTransport(3) || networkCapabilities.hasTransport(1)) {
                                NetworkInfo networkInfo = UpgradeUtils.connManager.getNetworkInfo(network);
                                if (networkInfo != null) {
                                    Log.w("OTA", "Network connectivity changed " + networkInfo.getSubtypeName() + "/" + networkInfo.getSubtype(), new Object[0]);
                                }
                                UpgradeUtils.OtaSystemEvent(UpgradeUtils.EVENT_NETWORK_CHANGED);
                            }
                        }
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onLost(Network network) {
                        UpgradeUtils.OtaSystemEvent(UpgradeUtils.EVENT_NETWORK_CHANGED);
                    }
                };
                connManager.registerDefaultNetworkCallback(connCallback);
            }
        }
        return start;
    }

    @SuppressLint({"WrongConstant"})
    public static void OtaCleanCache() {
        Intent intent = new Intent(ACTION_REQUEST_CLEAN_CACHES);
        intent.setFlags(16777216);
        sContext.sendBroadcast(intent);
    }

    public static int OtaStop() {
        BroadcastReceiver broadcastReceiver;
        Context context = sContext;
        if (context != null && (broadcastReceiver = shutdownReceiver) != null) {
            context.unregisterReceiver(broadcastReceiver);
            shutdownReceiver = null;
        }
        ConnectivityManager connectivityManager = connManager;
        if (connectivityManager != null) {
            ConnectivityManager.NetworkCallback networkCallback = connCallback;
            if (networkCallback != null) {
                connectivityManager.unregisterNetworkCallback(networkCallback);
                connCallback = null;
            }
            connManager = null;
        }
        return stop();
    }

    public static int OtaCancelWakeup(@NonNull String str) {
        return OtaScheduleWakeup(str, (short) -1, (short) -1, (short) 0);
    }

    public static void OtaUpgradeProgress(long j, short s, short s2, int i) {
        OtaUpgradeProgress(j, s, s2, i, null);
    }

    public static void OtaUpgradeProgress(long j, short s, int i) {
        OtaUpgradeProgress(j, (short) 45, s, i, null);
    }

    public static int OtaUsbUpgrade(@NonNull String str) {
        sUsbPath = str;
        return usbUpgrade(sUsbPath);
    }
}
