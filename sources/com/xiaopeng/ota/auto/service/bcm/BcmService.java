package com.xiaopeng.ota.auto.service.bcm;

import com.xiaopeng.ota.auto.service.BaseService;
import com.xiaopeng.ota.auto.service.ICarService;
/* loaded from: classes2.dex */
public class BcmService extends BaseService implements IBcmService {
    private static final String TAG = "BcmService";
    private IBcmService mService = (IBcmService) this.mICarService;

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
        return new D21BcmServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.BaseService
    protected ICarService createE28Service() {
        return new E28BcmServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.bcm.IBcmService
    public int getATwsState() throws Exception {
        return this.mService.getATwsState();
    }

    @Override // com.xiaopeng.ota.auto.service.bcm.IBcmService
    public int getPowerMode() throws Exception {
        return this.mService.getPowerMode();
    }

    @Override // com.xiaopeng.ota.auto.service.bcm.IBcmService
    public boolean getDriverBeltWarning() throws Exception {
        return this.mService.getDriverBeltWarning();
    }

    @Override // com.xiaopeng.ota.auto.service.bcm.IBcmService
    public int getDoorLockState() throws Exception {
        return this.mService.getDoorLockState();
    }

    @Override // com.xiaopeng.ota.auto.service.bcm.IBcmService
    public int getWindowLockState() throws Exception {
        return this.mService.getWindowLockState();
    }

    @Override // com.xiaopeng.ota.auto.service.bcm.IBcmService
    public int getDriverOnSeat() throws Exception {
        return this.mService.getDriverOnSeat();
    }

    @Override // com.xiaopeng.ota.auto.service.bcm.IBcmService
    public float[] getWindowsState() throws Exception {
        return this.mService.getWindowsState();
    }

    @Override // com.xiaopeng.ota.auto.service.bcm.IBcmService
    public int[] getDoorsState() throws Exception {
        return this.mService.getDoorsState();
    }
}
