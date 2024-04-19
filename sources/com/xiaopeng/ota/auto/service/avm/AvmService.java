package com.xiaopeng.ota.auto.service.avm;

import com.xiaopeng.ota.auto.service.BaseService;
import com.xiaopeng.ota.auto.service.ICarService;
/* loaded from: classes2.dex */
public class AvmService extends BaseService implements IAvmService {
    private static final String TAG = "AvmService";
    private IAvmService mService = (IAvmService) this.mICarService;

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
        return new D21AvmServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.BaseService
    protected ICarService createE28Service() {
        return new E28AvmServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.avm.IAvmService
    public boolean getHasRoofCamera() throws Exception {
        return this.mService.getHasRoofCamera();
    }
}
