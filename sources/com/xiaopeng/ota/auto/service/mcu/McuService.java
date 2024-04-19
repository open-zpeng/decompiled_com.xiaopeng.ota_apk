package com.xiaopeng.ota.auto.service.mcu;

import com.xiaopeng.ota.auto.service.BaseService;
import com.xiaopeng.ota.auto.service.ICarService;
/* loaded from: classes2.dex */
public class McuService extends BaseService implements IMcuService {
    private static final String TAG = "McuService";
    private IMcuService mService = (IMcuService) this.mICarService;

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
        return new D21McuServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.BaseService
    protected ICarService createE28Service() {
        return new E28McuServiceImpl();
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public void sendOta1MsgToMcu(int[] iArr) throws Exception {
        this.mService.sendOta1MsgToMcu(iArr);
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public void sendPsuOtaMsgToMcu(int[] iArr) throws Exception {
        this.mService.sendPsuOtaMsgToMcu(iArr);
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public int getOtaMcuReqStatus() throws Exception {
        return this.mService.getOtaMcuReqStatus();
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public void setOtaMcuReqStatus(int i) throws Exception {
        this.mService.setOtaMcuReqStatus(i);
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public int getOtaMcuReqUpdateFile() throws Exception {
        return this.mService.getOtaMcuReqUpdateFile();
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public void setOtaMcuReqUpdateFile(int i) throws Exception {
        this.mService.setOtaMcuReqUpdateFile(i);
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public void setOtaMcuSendUpdateFile(String str) throws Exception {
        this.mService.setOtaMcuSendUpdateFile(str);
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public int getOtaMcuUpdateStatus() throws Exception {
        return this.mService.getOtaMcuUpdateStatus();
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public String getXpCduType() throws Exception {
        return this.mService.getXpCduType();
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public String getHardwareCarType() throws Exception {
        return this.mService.getHardwareCarType();
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public String getHardwareCarStage() throws Exception {
        return this.mService.getHardwareCarStage();
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public int getIgStatusFromMcu() throws Exception {
        return this.mService.getIgStatusFromMcu();
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public int getFactoryModeSwitchStatus() throws Exception {
        return this.mService.getFactoryModeSwitchStatus();
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public void setMcuDelaySleep(int i) throws Exception {
        this.mService.setMcuDelaySleep(i);
    }
}
