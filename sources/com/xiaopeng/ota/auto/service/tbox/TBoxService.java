package com.xiaopeng.ota.auto.service.tbox;

import com.xiaopeng.ota.auto.service.BaseService;
import com.xiaopeng.ota.auto.service.ICarService;
import com.xiaopeng.ota.auto.service.tbox.ITBoxService;
/* loaded from: classes2.dex */
public class TBoxService extends BaseService implements ITBoxService {
    private static final String TAG = "TBoxService";
    private ITBoxService mTBoxService = (ITBoxService) this.mICarService;

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
        return new D21TBoxServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.BaseService
    protected ICarService createE28Service() {
        return new E28TBoxServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public void setTboxVersionInfoRequest() throws Exception {
        this.mTBoxService.setTboxVersionInfoRequest();
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public String getTboxVersionInfoResponse() throws Exception {
        return this.mTBoxService.getTboxVersionInfoResponse();
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public void startTboxOta(String str) throws Exception {
        this.mTBoxService.startTboxOta(str);
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public String getStartTboxOtaResponse() throws Exception {
        return this.mTBoxService.getStartTboxOtaResponse();
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public void stopTboxOta() throws Exception {
        this.mTBoxService.stopTboxOta();
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public String getStopTboxOtaResponse() throws Exception {
        return this.mTBoxService.getStopTboxOtaResponse();
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public int getOtaProgress() throws Exception {
        return this.mTBoxService.getOtaProgress();
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public void getAsyncTboxVersion(ITBoxService.ResponseListener responseListener) throws Exception {
        this.mTBoxService.getAsyncTboxVersion(responseListener);
    }

    @Override // com.xiaopeng.ota.auto.service.tbox.ITBoxService
    public void sendTboxOtaWorkingStatus(int i) throws Exception {
        this.mTBoxService.sendTboxOtaWorkingStatus(i);
    }
}
