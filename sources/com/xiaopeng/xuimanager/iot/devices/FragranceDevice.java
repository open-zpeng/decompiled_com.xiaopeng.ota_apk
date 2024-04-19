package com.xiaopeng.xuimanager.iot.devices;

import com.xiaopeng.xuimanager.iot.BaseDevice;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes2.dex */
public class FragranceDevice extends BaseDevice {
    public static final int CHANNEL_COUNT = 3;
    public static final String DEVICE_TYPE = "Fragrance";
    public static final String PROP_ACTIVE_CHANNEL = "active";
    public static final String PROP_CHANNEL_TYPES = "channel_types";
    public static final String PROP_CONCENTRATION = "concentration";
    public static final String PROP_CO_CONCENTRATION = "co_concentration";
    public static final String VAL_CHANNEL_1 = "0";
    public static final String VAL_CHANNEL_2 = "1";
    public static final String VAL_CHANNEL_3 = "2";
    public static final String VAL_CONCENTRATION_HIGH = "2";
    public static final String VAL_CONCENTRATION_LOW = "1";
    public static final String VAL_CO_CONCENTRATION_HIGH = "2";
    public static final String VAL_CO_CONCENTRATION_LOW = "1";
    public static final String VAL_CO_CONCENTRATION_NONE = "0";
    public static final String VAL_OFF = "0";
    public static final String VAL_ON = "1";
    public static final int VAL_TYPE_1 = 1;
    public static final int VAL_TYPE_2 = 2;
    public static final int VAL_TYPE_3 = 3;
    public static final int VAL_TYPE_4 = 4;
    public static final int VAL_TYPE_MAX = 100;
    public static final int VAL_TYPE_UNINIT = -1;
    public static final int VAL_TYPE_UNPLUG = 0;
    public static final String CMD_SET_SWITCH = "setSwitch";
    public static final String CMD_SET_CHANNEL = "setActiveChannel";
    public static final String CMD_SET_CONCENTRATION = "setConcentration";
    private static final List<String> cmdList = Arrays.asList(CMD_SET_SWITCH, CMD_SET_CHANNEL, CMD_SET_CONCENTRATION);
    private static final String TAG = FragranceDevice.class.getSimpleName();

    public FragranceDevice() {
    }

    public FragranceDevice(String str, String str2, String str3) {
        this.deviceName = str;
        this.deviceId = str2;
        this.deviceType = str3;
        this.deviceClass = getClass().getSimpleName();
    }

    public static int[] readChannelTypes(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(",");
        int[] iArr = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            iArr[i] = Integer.parseInt(split[i]);
        }
        return iArr;
    }
}
