package com.xiaopeng.xuimanager.iot;

import com.xiaopeng.ota.sdk.common.OTAConstants;
import java.util.Map;
/* loaded from: classes2.dex */
public abstract class BaseDevice {
    public static final String CMD_ADD_DEVICE = "cmd_add_device";
    public static final String CMD_ONOFF = "cmd_base_onoff";
    public static final String CMD_REMOVE_DEVICE = "cmd_remove_device";
    public static final String CMD_SCAN_DEVICE_START = "cmd_scan_device_start";
    public static final String CMD_SCAN_DEVICE_STOP = "cmd_scan_device_stop";
    public static final String GET_BY_DEVICE_CLASS = "by_dev_class";
    public static final String GET_BY_DEVICE_TYPE = "by_dev_type";
    public static final String PROP_CONNECT_STATE = "connect_state";
    public static final String PROP_SWITCH_STATE = "switch_state";
    public static final String SCAN_TYPE_BLE = "scan_type_ble";
    private static final String TAG = BaseDevice.class.getSimpleName() + "##";
    public static final String VAL_CONNECTED = "1";
    public static final String VAL_DISCONNECTED = "0";
    public static final int VAL_INVALID_INT = Integer.MIN_VALUE;
    public static final String VAL_INVALID_STR = "-2147483648";
    public static final String VAL_OFF = "0";
    public static final String VAL_ON = "1";
    public static final String VAL_UNINIT = "-1";
    public static final int VAL_UNINIT_INT = -1;
    public static final String VAL_UNKNOWN_DEVICE_ID = "unknown_device_id";
    public static final String VAL_UNKNOWN_DEVICE_NAME = "unknown_device_name";
    protected String deviceClass;
    protected String deviceId;
    protected String deviceName;
    protected String deviceType;
    protected Map<String, String> propertyMap;

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getDeviceClass() {
        return this.deviceClass;
    }

    public Map<String, String> getPropertyMap() {
        return this.propertyMap;
    }

    public BaseDevice setDeviceName(String str) {
        this.deviceName = str;
        return this;
    }

    public BaseDevice setDeviceId(String str) {
        this.deviceId = str;
        return this;
    }

    public BaseDevice setDeviceType(String str) {
        this.deviceType = str;
        return this;
    }

    public BaseDevice setDeviceClass(String str) {
        this.deviceClass = str;
        return this;
    }

    public BaseDevice setPropertyMap(Map<String, String> map) {
        this.propertyMap = map;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("deviceClass[");
        sb.append(this.deviceClass);
        sb.append("],deviceName[");
        sb.append(this.deviceName);
        sb.append("],deviceId[");
        sb.append(this.deviceId);
        sb.append("],deviceType[");
        sb.append(this.deviceType);
        sb.append("]");
        if (this.propertyMap != null) {
            sb.append(",props:");
            for (Map.Entry<String, String> entry : this.propertyMap.entrySet()) {
                sb.append(entry.getKey() + OTAConstants.EQ + entry.getValue() + ",");
            }
        }
        return sb.toString();
    }
}
