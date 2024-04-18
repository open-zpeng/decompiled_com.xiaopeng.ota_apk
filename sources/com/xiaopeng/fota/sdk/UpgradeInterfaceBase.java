package com.xiaopeng.fota.sdk;
/* loaded from: classes2.dex */
public interface UpgradeInterfaceBase {
    public static final int CAMPAIGN_SRC_SERVER = 0;
    public static final int CAMPAIGN_SRC_TIMER = 2;
    public static final int CAMPAIGN_SRC_USB = 1;
    public static final int CATEGORY_APPLICATION = 1;
    public static final int CATEGORY_CAMPAIGN = 10;
    public static final int CATEGORY_CONFIGURATION = 2;
    public static final int CATEGORY_FIRMWARE = 0;
    public static final int CODE_CANCELLED = 4;
    public static final int CODE_CONDITION_MISS = 2;
    public static final int CODE_ERROR = 1;
    public static final int CODE_EXPIRED = 3;
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_SUCCESS_NO_ANIMATE = 5;
    public static final int NOTIFY_METHOD_NOTIFICATION = 1;
    public static final int NOTIFY_METHOD_POPUP = 0;
    public static final int NOTIFY_METHOD_TOAST = 2;
    public static final int NOTIFY_SEVERITY_ERR = 2;
    public static final int NOTIFY_SEVERITY_INF = 0;
    public static final int NOTIFY_SEVERITY_WRN = 1;
    public static final int OTA_STATUS_DOWNLOAD = 1;
    public static final int OTA_STATUS_IDLE = 0;
    public static final int OTA_STATUS_UPGRADE = 2;
    public static final int PROGRESS_BEGIN = 0;
    public static final int PROGRESS_END = 100;
    public static final int START_NORMAL = 0;
    public static final int START_RESCHEDULED = 1;
    public static final int UPGRADE_STATE_CHECK_CONDITION = 1;
    public static final int UPGRADE_STATE_FINALIZE = 4;
    public static final int UPGRADE_STATE_IDLE = 0;
    public static final int UPGRADE_STATE_START = 2;
    public static final int UPGRADE_STATE_UPGRADE = 3;

    void notifyCampaign(int i, String str);

    void notifyEcuVersions(String str);

    void notifyProgress(UpgradeProgress upgradeProgress);

    void notifyResult(long j, int i, Object obj);

    void notifyStart(long j, int i);

    void notifyStatus(int i);

    void notifyUsbProgress(String str);

    void onRemoteControl(int i, String str);
}
