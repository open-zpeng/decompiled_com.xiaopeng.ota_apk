package com.xiaopeng.xuimanager.iot.devices;

import com.xiaopeng.xuimanager.iot.BaseDevice;
/* loaded from: classes2.dex */
public class FridgeDevice extends BaseDevice {
    public static final String DEVICE_TYPE = "Fridge";
    public static final int ERROR_1 = 1;
    public static final int ERROR_2 = 2;
    public static final int ERROR_3 = 4;
    public static final int ERROR_4 = 8;
    public static final int ERROR_5 = 16;
    public static final int ERROR_6 = 32;
    public static final int ERROR_7 = 64;
    public static final int ERROR_NONE = 0;
    public static final String PROP_ERROR_CODE = "error_code";
    public static final String PROP_TARGET_TEMPERATURE = "target_temp";
    public static final String PROP_TEMPERATURE = "temperature";
    private static final String TAG = FragranceDevice.class.getSimpleName();
    public static final String VAL_ERROR_CODE_DOOR_OPEN = "door open";
    public static final String VAL_SWITCH_OFF = "0";
    public static final String VAL_SWITCH_ON = "1";
    public static final String VAL_TEMPERATURE_TARGET_HIGH = "+06";
    public static final String VAL_TEMPERATURE_TARGET_LOW = "-06";
    public static final String VAL_TEMPERATURE_TARGET_MIDDLE = "+00";

    public FridgeDevice() {
    }

    public FridgeDevice(String str, String str2, String str3) {
        this.deviceName = str;
        this.deviceId = str2;
        this.deviceType = str3;
        this.deviceClass = getClass().getSimpleName();
    }
}
