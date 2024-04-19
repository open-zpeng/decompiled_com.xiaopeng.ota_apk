package com.xiaopeng.ota.auto.service.ciu;

import com.xiaopeng.ota.auto.service.BaseService;
import com.xiaopeng.ota.auto.service.ICarService;
/* loaded from: classes2.dex */
public class CiuService extends BaseService implements ICiuService {
    private static final String TAG = "BcmService";
    private ICiuService mService = (ICiuService) this.mICarService;

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
        return new D21CiuServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.BaseService
    protected ICarService createE28Service() {
        return new E28CiuServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.ciu.ICiuService
    public void setDvrMode(int i) throws Exception {
        this.mService.setDvrMode(i);
    }

    @Override // com.xiaopeng.ota.auto.service.ciu.ICiuService
    public int getDvrMode() throws Exception {
        return this.mService.getDvrMode();
    }
}
