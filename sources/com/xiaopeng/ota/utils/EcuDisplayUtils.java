package com.xiaopeng.ota.utils;

import com.xiaopeng.fota.sdk.EcuType;
/* loaded from: classes2.dex */
public class EcuDisplayUtils {
    public static String getEcuDisplay(EcuType ecuType) {
        if (ecuType == null) {
            return "";
        }
        if (RegionUtils.isEuropeRegion()) {
            return ecuType.name();
        }
        return ecuType.desc();
    }
}
