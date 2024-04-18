package com.xiaopeng.ota.bean;

import com.xiaopeng.libconfig.ipc.IpcConfig;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes2.dex */
public enum AppType {
    OTA("com.xiaopeng.ota", "OTA"),
    MONTECARLO(IpcConfig.App.MAP_NAVI, "地图"),
    CARGALLERY(IpcConfig.App.CAR_GALLERY, "相册"),
    AUTOPILOT(IpcConfig.App.CAR_AUTOPILOT, "自动泊车"),
    CARSPEECH("com.xiaopeng.carspeechservice", "语音"),
    CARCONTROL(IpcConfig.App.CAR_CONTROL, "车控");
    
    private String desc;
    private String packageName;

    public String getPackageName() {
        return this.packageName;
    }

    public String getDesc() {
        return this.desc;
    }

    AppType(String str, String str2) {
        this.packageName = str;
        this.desc = str2;
    }

    public static List<AppType> getAllTypes() {
        return Arrays.asList(values());
    }
}
