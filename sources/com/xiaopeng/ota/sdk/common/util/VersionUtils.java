package com.xiaopeng.ota.sdk.common.util;

import android.car.XpCarFeatures;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.xiaopeng.lib.utils.info.BuildInfoUtils;
/* loaded from: classes2.dex */
public class VersionUtils {
    private static final String EMPTY_VERSION = "0.0.0";
    private static final String FORMAT_HARDWARE_VERSION = "H.%s";
    private static final String FORMAT_SOFTWARE_VERSION = "%s.%s.%s";
    private static final String SYSTEM_PROPERTY_CFC_INFO = "persist.sys.xiaopeng.cfc_info";
    private static final String SYSTEM_PROPERTY_HW_VERSION = "ro.xiaopeng.hardware";
    private static final String SYSTEM_PROPERTY_MCU_HARDWARE_ID = "sys.mcu.hardwareId";
    private static final String SYSTEM_PROPERTY_PERSIST_MCU_HARDWARE_ID = "persist.sys.mcu.hardwareId";
    private static final String TAG = "VersionUtils";
    private static final String UNDER_LINE = "_";

    /* loaded from: classes2.dex */
    public static class HARDWARE_VERSION {
        public static final int V7 = 7;
        public static final String VALUE_V1 = "REV01";
        public static final String VALUE_V2 = "REV02";
        public static final String VALUE_V3 = "REV03";
        public static final String VALUE_V4 = "REV04";
        public static final String VALUE_V5 = "REV05";
        public static final String VALUE_V6 = "REV06";
        public static final String VALUE_V7 = "REV07";
    }

    public static String getEcuSoftwareVersion(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Empty version");
        }
        if (str.length() >= 3) {
            String substring = str.substring(str.length() - 3);
            for (int i = 0; i < substring.length(); i++) {
                if (!CharUtils.isPrintable(substring.charAt(i))) {
                    throw new IllegalArgumentException("Unprintable version");
                }
            }
            return String.format(FORMAT_SOFTWARE_VERSION, Character.valueOf(substring.charAt(0)), Character.valueOf(substring.charAt(1)), Character.valueOf(substring.charAt(2)));
        }
        throw new IllegalArgumentException("Invalid length");
    }

    public static String getEcuHardwareVersion(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Empty version");
        }
        if (!TextUtils.isEmpty(str) && str.length() > 1) {
            str = str.substring(str.length() - 1);
            for (int i = 0; i < str.length(); i++) {
                if (!CharUtils.isPrintable(str.charAt(i))) {
                    throw new IllegalArgumentException("Unprintable version");
                }
            }
        }
        return str;
    }

    public static String getEcuFingerPrint(String str, String str2, String str3) {
        if (str2 == null || str3 == null || str.length() <= 3) {
            return "";
        }
        return str.substring(0, str.length() - 3) + "_" + String.format(FORMAT_HARDWARE_VERSION, str2) + "_" + str3.replaceAll("[.]", "");
    }

    public static String getBidFromFingerPrint(String str) {
        int indexOf;
        if (!TextUtils.isEmpty(str) && (indexOf = str.indexOf("_")) > -1) {
            String substring = str.substring(0, indexOf);
            if (substring.toUpperCase().startsWith("XMART")) {
                return substring.substring(substring.length() - 1);
            }
        }
        return null;
    }

    public static int getHardwareInt() {
        String str = SystemProperties.get(SYSTEM_PROPERTY_HW_VERSION, HARDWARE_VERSION.VALUE_V1);
        if (str.length() >= 5) {
            try {
                return Integer.parseInt(str.substring(3, str.length()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static String getHardwareId() {
        String mcuHardwareId = XpCarFeatures.getMcuHardwareId();
        return "unknown".equals(mcuHardwareId) ? SystemProperties.get(SYSTEM_PROPERTY_PERSIST_MCU_HARDWARE_ID, "unknown") : mcuHardwareId;
    }

    public static String getCfcInfo() {
        return SystemProperties.get(SYSTEM_PROPERTY_CFC_INFO, "");
    }

    public static String getSystemVersion() {
        return BuildInfoUtils.getSystemVersion();
    }

    public static String getBid() {
        return BuildInfoUtils.getBid();
    }
}
