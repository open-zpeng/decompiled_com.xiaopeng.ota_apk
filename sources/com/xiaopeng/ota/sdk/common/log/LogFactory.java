package com.xiaopeng.ota.sdk.common.log;
/* loaded from: classes2.dex */
public class LogFactory {
    public static final String LOG_DIRECTORY = "/data/Log/ota/";
    private static LogConfiguration logConfiguration;

    public static void setConfiguration(LogConfiguration logConfiguration2) {
        logConfiguration = logConfiguration2;
    }

    public static LogConfiguration getConfiguration() {
        return logConfiguration;
    }
}
