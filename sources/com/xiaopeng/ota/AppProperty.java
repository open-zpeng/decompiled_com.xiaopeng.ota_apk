package com.xiaopeng.ota;

import android.car.XpCarFeatures;
import com.xiaopeng.ota.sdk.common.OTAConstants;
/* loaded from: classes2.dex */
public class AppProperty {
    private static final String OTA_PROP_COUNTRY_CODE = "OTA_PROP_COUNTRY_CODE";
    private static final String OTA_PROP_HTTP_HOST = "OTA_PROP_HTTP_HOST";
    private static final String OTA_PROP_IVI_SN = "OTA_PROP_IVI_SN";
    private static final String OTA_PROP_SERVER_VERSION = "OTA_PROP_SERVER_VERSION";
    private static final String OTA_PROP_VIN = "OTA_PROP_VIN";
    private static final String OTA_PROP_XOS_VERSION_CODE = "OTA_PROP_XOS_VERSION_CODE";
    private static final String OTA_PROP_XOS_VERSION_NAME = "OTA_PROP_XOS_VERSION_NAME";

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String getStr(String str) {
        char c;
        switch (str.hashCode()) {
            case -1147177682:
                if (str.equals(OTA_PROP_XOS_VERSION_NAME)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -73984826:
                if (str.equals(OTA_PROP_HTTP_HOST)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 979846914:
                if (str.equals(OTA_PROP_VIN)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 995078639:
                if (str.equals(OTA_PROP_COUNTRY_CODE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1232480853:
                if (str.equals(OTA_PROP_SERVER_VERSION)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1661443159:
                if (str.equals(OTA_PROP_IVI_SN)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c != 1) {
                if (c != 2) {
                    if (c != 3) {
                        if (c != 4) {
                            if (c != 5) {
                                return null;
                            }
                            return XpCarFeatures.getMcuHardwareId();
                        }
                        return XpCarFeatures.getVinCode();
                    }
                    return "ZH";
                }
                return OTAManager.getCurrentOTAVersion();
            }
            return OTAConstants.Server.OTA_URI_VERSION;
        }
        return LocaleConfig.getHttpHost();
    }

    public static int getInt(String str) {
        if (((str.hashCode() == -1147492208 && str.equals(OTA_PROP_XOS_VERSION_CODE)) ? (char) 0 : (char) 65535) != 0) {
            return 0;
        }
        return OTAManager.getOsVersionCode();
    }
}
