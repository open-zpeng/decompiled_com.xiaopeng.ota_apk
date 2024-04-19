package com.xiaopeng.ota.auto.service.vcu;

import com.xiaopeng.ota.auto.service.BaseService;
import com.xiaopeng.ota.auto.service.ICarService;
/* loaded from: classes2.dex */
public class VcuService extends BaseService implements IVcuService {
    private static final String TAG = "VcuService";
    private IVcuService mService = (IVcuService) this.mICarService;

    @Override // com.xiaopeng.ota.auto.service.BaseService
    protected ICarService createD20Service() {
        return null;
    }

    @Override // com.xiaopeng.ota.auto.service.BaseService
    protected ICarService createD25Service() {
        return null;
    }

    @Override // com.xiaopeng.ota.auto.service.BaseService
    protected String getName() {
        return TAG;
    }

    @Override // com.xiaopeng.ota.auto.service.BaseService
    protected ICarService createD21Service() {
        return new D21VcuServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.BaseService
    protected ICarService createE28Service() {
        return new E28VcuServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public int getGearLever() throws Exception {
        return this.mService.getGearLever();
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public int getChargeGunStatus() throws Exception {
        return this.mService.getChargeGunStatus();
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public int getChargeStatus() throws Exception {
        return this.mService.getChargeStatus();
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public int getElectricityPercent() throws Exception {
        return this.mService.getElectricityPercent();
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public int getEbsBatterySoc() throws Exception {
        return this.mService.getEbsBatterySoc();
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public float getBatteryVolt() throws Exception {
        return this.mService.getBatteryVolt();
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public float getRawCarSpeed() throws Exception {
        return this.mService.getRawCarSpeed();
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public int getSystemReady() throws Exception {
        return this.mService.getSystemReady();
    }

    @Override // com.xiaopeng.ota.auto.service.vcu.IVcuService
    public int getBatteryPercent() throws Exception {
        return this.mService.getBatteryPercent();
    }
}
