package com.xiaopeng.ota.auto.service.vcu;

import android.car.Car;
import android.car.hardware.vcu.CarVcuManager;
import com.xiaopeng.ota.auto.callback.CarServiceEventAdapter;
import com.xiaopeng.ota.auto.callback.VcuCallbackAdapter;
import com.xiaopeng.ota.auto.service.AbsService;
import com.xiaopeng.ota.sdk.common.log.Logger;
/* loaded from: classes2.dex */
public class BaseVcuService extends AbsService implements IVcuService {
    private static final String TAG = "BaseVcuService";
    protected CarVcuManager mVcuManager;

    @Override // com.xiaopeng.ota.auto.service.AbsService
    protected void register() {
        try {
            this.mVcuManager = (CarVcuManager) getCarManagerService(Car.XP_VCU_SERVICE);
            CarServiceEventAdapter carServiceEventAdapter = new CarServiceEventAdapter(TAG, this.mCallbackList, VcuCallbackAdapter.PROP_IDS);
            this.mVcuManager.registerPropCallback(carServiceEventAdapter.getPropertyIds(), carServiceEventAdapter);
        } catch (Exception e) {
            Logger.d(TAG, e, "register service fail", new Object[0]);
        }
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public int getGearLever() throws Exception {
        CarVcuManager carVcuManager = this.mVcuManager;
        if (carVcuManager == null) {
            throw new Exception("getGearLever error, Car not connected");
        }
        return carVcuManager.getIntProperty(557847045, 0);
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public int getChargeGunStatus() throws Exception {
        CarVcuManager carVcuManager = this.mVcuManager;
        if (carVcuManager == null) {
            throw new Exception("getChargeGunStatus error, Car not connected");
        }
        return carVcuManager.getChargingGunStatus();
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public int getChargeStatus() throws Exception {
        CarVcuManager carVcuManager = this.mVcuManager;
        if (carVcuManager == null) {
            throw new Exception("getChargeStatus error, Car not connected");
        }
        return carVcuManager.getChargeStatus();
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public int getElectricityPercent() throws Exception {
        CarVcuManager carVcuManager = this.mVcuManager;
        if (carVcuManager == null) {
            throw new Exception("getElectricityPercent error, Car not connected");
        }
        return carVcuManager.getElectricityPercent();
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public int getEbsBatterySoc() throws Exception {
        CarVcuManager carVcuManager = this.mVcuManager;
        if (carVcuManager == null) {
            throw new Exception("getEbsBatterySoc error, Car not connected");
        }
        return carVcuManager.getEbsBatterySoc();
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public float getBatteryVolt() throws Exception {
        CarVcuManager carVcuManager = this.mVcuManager;
        if (carVcuManager == null) {
            throw new Exception("getBatteryVolt error, Car not connected");
        }
        return carVcuManager.getBatteryVolt();
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public float getRawCarSpeed() throws Exception {
        CarVcuManager carVcuManager = this.mVcuManager;
        if (carVcuManager == null) {
            throw new Exception("getRawCarSpeed error, Car not connected");
        }
        return carVcuManager.getRawCarSpeed();
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public int getSystemReady() throws Exception {
        CarVcuManager carVcuManager = this.mVcuManager;
        if (carVcuManager == null) {
            throw new Exception("getSystemReady error, Car not connected");
        }
        return carVcuManager.getEvSysReady();
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public int getBatteryPercent() throws Exception {
        CarVcuManager carVcuManager = this.mVcuManager;
        if (carVcuManager == null) {
            throw new Exception("getBatteryPercent error, Car not connected");
        }
        return carVcuManager.getBatteryPercent();
    }
}
