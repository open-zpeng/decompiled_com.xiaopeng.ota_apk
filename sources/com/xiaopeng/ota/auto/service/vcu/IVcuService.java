package com.xiaopeng.ota.auto.service.vcu;

import com.xiaopeng.ota.auto.service.ICarService;
/* loaded from: classes2.dex */
public interface IVcuService extends ICarService {
    int getBatteryPercent() throws Exception;

    float getBatteryVolt() throws Exception;

    int getChargeGunStatus() throws Exception;

    int getChargeStatus() throws Exception;

    int getEbsBatterySoc() throws Exception;

    int getElectricityPercent() throws Exception;

    int getGearLever() throws Exception;

    float getRawCarSpeed() throws Exception;

    int getSystemReady() throws Exception;
}
