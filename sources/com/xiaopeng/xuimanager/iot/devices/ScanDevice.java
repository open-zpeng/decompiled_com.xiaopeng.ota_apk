package com.xiaopeng.xuimanager.iot.devices;

import com.xiaopeng.xuimanager.iot.BaseDevice;
/* loaded from: classes2.dex */
public class ScanDevice extends BaseDevice {
    public static final String DEVICE_TYPE = "ScanDevice";
    public static final String PROP_BLUETOOTH_RSSI = "bt_rssi";
    public static final String PROP_REAL_TYPE = "real_type";
    private static final String TAG = "ScanDevice";
    public static final String VAL_TYPE_BLUETOOTH_BLE = "type_bluetooth_ble";

    public ScanDevice() {
        this.deviceName = BaseDevice.VAL_UNKNOWN_DEVICE_NAME;
        this.deviceId = BaseDevice.VAL_UNKNOWN_DEVICE_ID;
        this.deviceType = "ScanDevice";
        this.deviceClass = getClass().getSimpleName();
    }

    public ScanDevice(String str, String str2, String str3) {
        this.deviceName = str;
        this.deviceId = str2;
        this.deviceType = str3;
        this.deviceClass = getClass().getSimpleName();
    }
}
