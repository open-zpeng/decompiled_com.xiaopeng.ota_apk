package com.xiaopeng.ota.sdk.common.util;

import android.os.SystemProperties;
import android.text.TextUtils;
/* loaded from: classes2.dex */
public class SystemPropertiesUtils {
    public static final int BODY_COLOR_BLACK = 3;
    public static final int BODY_COLOR_BLUE = 7;
    public static final int BODY_COLOR_BROWN = 6;
    public static final int BODY_COLOR_CHAMPAGNE = 11;
    public static final int BODY_COLOR_GRAY = 4;
    public static final int BODY_COLOR_GREEN = 10;
    public static final int BODY_COLOR_INVALID = 0;
    public static final int BODY_COLOR_NEW_BLACK = 14;
    public static final int BODY_COLOR_NEW_RED = 12;
    public static final int BODY_COLOR_NEW_WHITE = 13;
    public static final int BODY_COLOR_PURPLE = 8;
    public static final int BODY_COLOR_RED = 1;
    public static final int BODY_COLOR_SILVER = 5;
    public static final int BODY_COLOR_WHITE = 2;
    public static final int BODY_COLOR_WHITE_BLACK_D55 = 12;
    public static final int BODY_COLOR_YELLOW = 9;
    private static final String ON = "on";
    public static final int POWER_STATUS_FAKE_SLEEP = 1;
    public static final int POWER_STATUS_NORMAL = 0;
    public static final int POWER_STATUS_SLEEP = 2;
    public static final int POWER_STATUS_UNKNOWN = -1;
    private static final String SYSTEM_PROPERTY_OTA_STATE = "sys.xiaopeng.ota.state";
    private static final String SYSTEM_PROPERTY_UDISK_PATH = "sys.usb.label";
    private static final String SYSTEM_REPAIR_MODE = "persist.sys.xiaopeng.repairmode";

    public static String getPowerStatusDesc(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "UNKNOWN" : "SLEEP" : "FAKE_SLEEP" : "NORMAL";
    }

    private static String getString(String str) {
        return SystemProperties.get(str, (String) null);
    }

    public static boolean isUsbDiskAvailable() {
        return !TextUtils.isEmpty(getString(SYSTEM_PROPERTY_UDISK_PATH));
    }

    public static int getPowerStatus() {
        return SystemProperties.getInt("sys.xiaopeng.power_state", -1);
    }

    public static void setOtaState(int i) {
        SystemProperties.set(SYSTEM_PROPERTY_OTA_STATE, String.valueOf(i));
    }

    public static boolean isRepairMode() {
        return ON.equalsIgnoreCase(getRepairMode());
    }

    public static int getRepairModeEanbled() {
        return isRepairMode() ? 1 : 0;
    }

    private static final String getRepairMode() {
        return SystemProperties.get(SYSTEM_REPAIR_MODE, "");
    }
}
