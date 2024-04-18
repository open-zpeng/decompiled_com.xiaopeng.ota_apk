package com.xiaopeng.ota;

import com.xiaopeng.lib.utils.config.CommonConfig;
import com.xiaopeng.lib.utils.config.EnvConfig;
import com.xiaopeng.ota.utils.RegionUtils;
/* loaded from: classes2.dex */
public class LocaleConfig {
    private static final String HTTP_HOST_EUROPE = "https://xmart-eu.xiaopeng.com";

    public static String getHttpHost() {
        if (RegionUtils.isEuropeRegion()) {
            return getEuropeHttpHost();
        }
        return CommonConfig.HTTP_HOST;
    }

    private static String getEuropeHttpHost() {
        return EnvConfig.getHostInString(HTTP_HOST_EUROPE, HTTP_HOST_EUROPE);
    }
}
