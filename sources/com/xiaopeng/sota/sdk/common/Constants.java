package com.xiaopeng.sota.sdk.common;
/* loaded from: classes2.dex */
public class Constants {
    public static final String DB_NAME = "sota.db";
    public static final String DB_NAME_TEMP = "sota.db-journal";
    public static final int DISABLED = 0;
    public static final String DOWNLOAD_FOLDER = "sota";
    public static final int DOWNLOAD_THREAD_NUM = 1;
    public static final int ENABLED = 1;
    public static final String INSTALL_CAMPAIGN_ID = "SOTA_CAMPAIGN_ID";
    public static final int RESUME_INSTALLATION_DELAY = 1000;
    public static final int SELF_UPGRADE_DELAY = 1500;
    private static final String SOTA_BASE = "/sota";
    public static final String SOTA_EVENT_CONTEXT = "/v2/event";
    public static final String SOTA_EVENT_URI = "/sota/v2/event";
    public static final String SOTA_PROCESS_NAME = "com.xiaopeng.ota:upgrade";
    public static final String SOTA_UPGRADE_URI = "/sota/v2/upgrade";
    public static final String SUFFIX_DECRYPT_APK = ".ist";

    /* loaded from: classes2.dex */
    public static class Mode {
        public static final int UPGRADE_FORCE = 1;
        public static final int UPGRADE_OPTIONAL = 0;
        public static final int UPGRADE_SILENT = 2;
    }
}
