package com.xiaopeng.ota.auto.service.srs;

import com.xiaopeng.ota.auto.service.BaseService;
import com.xiaopeng.ota.auto.service.ICarService;
/* loaded from: classes2.dex */
public class SrsService extends BaseService implements ISrsService {
    private static final String TAG = "SrsService";
    private ISrsService mService = (ISrsService) this.mICarService;

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
        return new D21SrsServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.BaseService
    protected ICarService createE28Service() {
        return new E28SrsServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.srs.ISrsService
    public boolean getDriverBeltWarning() throws Exception {
        return this.mService.getDriverBeltWarning();
    }
}
