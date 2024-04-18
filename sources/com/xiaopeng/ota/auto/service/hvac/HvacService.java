package com.xiaopeng.ota.auto.service.hvac;

import com.xiaopeng.ota.auto.service.BaseService;
import com.xiaopeng.ota.auto.service.ICarService;
/* loaded from: classes2.dex */
public class HvacService extends BaseService implements IHvacService {
    private static final String TAG = "HvacService";
    private IHvacService mService = (IHvacService) this.mICarService;

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
        return new D21HvacServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.BaseService
    protected ICarService createE28Service() {
        return new E28HvacServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.hvac.IHvacService
    public int getHvacPowerMode() throws Exception {
        return this.mService.getHvacPowerMode();
    }
}
