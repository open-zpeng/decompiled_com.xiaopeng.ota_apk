package com.xiaopeng.ota.auto.service.icm;

import com.xiaopeng.ota.auto.service.BaseService;
import com.xiaopeng.ota.auto.service.ICarService;
/* loaded from: classes2.dex */
public class IcmService extends BaseService implements IIcmService {
    private static final String TAG = "IcmService";
    private IIcmService mService = (IIcmService) this.mICarService;

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
        return new D21IcmServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.BaseService
    protected ICarService createE28Service() {
        return new E28IcmServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.icm.IIcmService
    public boolean getIcmConnectionState() throws Exception {
        return this.mService.getIcmConnectionState();
    }

    @Override // com.xiaopeng.ota.auto.service.icm.IIcmService
    public void sendBinMsg(int i, byte[] bArr, byte[] bArr2) throws Exception {
        this.mService.sendBinMsg(i, bArr, bArr2);
    }

    @Override // com.xiaopeng.ota.auto.service.icm.IIcmService
    public void sendRomBinMsg(byte[] bArr) throws Exception {
        this.mService.sendRomBinMsg(bArr);
    }

    @Override // com.xiaopeng.ota.auto.service.icm.IIcmService
    public float getLastStartUpMileage() throws Exception {
        return this.mService.getLastStartUpMileage();
    }
}
