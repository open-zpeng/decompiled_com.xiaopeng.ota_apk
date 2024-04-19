package com.xiaopeng.ota.auto.service.mcu;

import android.car.Car;
import android.car.hardware.mcu.CarMcuManager;
import com.xiaopeng.ota.auto.callback.CarServiceEventAdapter;
import com.xiaopeng.ota.auto.callback.McuCallbackAdapter;
import com.xiaopeng.ota.auto.service.AbsService;
import com.xiaopeng.ota.sdk.common.log.Logger;
/* loaded from: classes2.dex */
public class BaseMcuService extends AbsService implements IMcuService {
    private static final String TAG = "BaseMcuService";
    protected CarMcuManager mMcuManager;

    @Override // com.xiaopeng.ota.auto.service.AbsService
    protected void register() {
        try {
            this.mMcuManager = (CarMcuManager) getCarManagerService(Car.XP_MCU_SERVICE);
            CarServiceEventAdapter carServiceEventAdapter = new CarServiceEventAdapter(TAG, this.mCallbackList, McuCallbackAdapter.PROP_IDS);
            this.mMcuManager.registerPropCallback(carServiceEventAdapter.getPropertyIds(), carServiceEventAdapter);
        } catch (Exception unused) {
            Logger.d(TAG, "register service fail", new Object[0]);
        }
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public void sendOta1MsgToMcu(int[] iArr) throws Exception {
        CarMcuManager carMcuManager = this.mMcuManager;
        if (carMcuManager == null) {
            throw new Exception("sendOta1MsgToMcu error, Car not connected");
        }
        carMcuManager.sendOta1MsgToMcu(iArr);
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public void sendPsuOtaMsgToMcu(int[] iArr) throws Exception {
        CarMcuManager carMcuManager = this.mMcuManager;
        if (carMcuManager == null) {
            throw new Exception("sendPsuOtaMsgToMcu error, Car not connected");
        }
        carMcuManager.sendPsuOtaMsgToMcu(iArr);
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public int getOtaMcuReqStatus() throws Exception {
        CarMcuManager carMcuManager = this.mMcuManager;
        if (carMcuManager == null) {
            throw new Exception("getOtaMcuReqStatus error, Car not connected");
        }
        return carMcuManager.getOtaMcuReqStatus();
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public void setOtaMcuReqStatus(int i) throws Exception {
        CarMcuManager carMcuManager = this.mMcuManager;
        if (carMcuManager == null) {
            throw new Exception("setOtaMcuReqStatus error, Car not connected");
        }
        carMcuManager.setOtaMcuReqStatus(i);
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public int getOtaMcuReqUpdateFile() throws Exception {
        CarMcuManager carMcuManager = this.mMcuManager;
        if (carMcuManager == null) {
            throw new Exception("getOtaMcuReqUpdateFile error, Car not connected");
        }
        return carMcuManager.getOtaMcuReqUpdatefile();
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public void setOtaMcuReqUpdateFile(int i) throws Exception {
        CarMcuManager carMcuManager = this.mMcuManager;
        if (carMcuManager == null) {
            throw new Exception("setOtaMcuReqUpdateFile error, Car not connected");
        }
        carMcuManager.setOtaMcuReqUpdatefile(i);
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public void setOtaMcuSendUpdateFile(String str) throws Exception {
        CarMcuManager carMcuManager = this.mMcuManager;
        if (carMcuManager == null) {
            throw new Exception("setOtaMcuSendUpdateFile error, Car not connected");
        }
        carMcuManager.setOtaMcuSendUpdatefile(str);
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public int getOtaMcuUpdateStatus() throws Exception {
        CarMcuManager carMcuManager = this.mMcuManager;
        if (carMcuManager == null) {
            throw new Exception("getOtaMcuUpdateStatus error, Car not connected");
        }
        return carMcuManager.getOtaMcuUpdateStatus();
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public String getXpCduType() throws Exception {
        CarMcuManager carMcuManager = this.mMcuManager;
        if (carMcuManager == null) {
            throw new Exception("getXpCduType error, Car not connected");
        }
        return carMcuManager.getXpCduType();
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public String getHardwareCarType() throws Exception {
        CarMcuManager carMcuManager = this.mMcuManager;
        if (carMcuManager == null) {
            throw new Exception("getHardwareCarType error, Car not connected");
        }
        return carMcuManager.getHardwareCarType();
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public String getHardwareCarStage() throws Exception {
        CarMcuManager carMcuManager = this.mMcuManager;
        if (carMcuManager == null) {
            throw new Exception("getHardwareCarStage error, Car not connected");
        }
        return carMcuManager.getHardwareCarStage();
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public int getIgStatusFromMcu() throws Exception {
        CarMcuManager carMcuManager = this.mMcuManager;
        if (carMcuManager == null) {
            throw new Exception("getIgStatusFromMcu error, Car not connected");
        }
        return carMcuManager.getIgStatusFromMcu();
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public int getFactoryModeSwitchStatus() throws Exception {
        CarMcuManager carMcuManager = this.mMcuManager;
        if (carMcuManager == null) {
            throw new Exception("getFactoryModeSwitchStatus error, Car not connected");
        }
        return carMcuManager.getFactoryModeSwitchStatus();
    }

    @Override // com.xiaopeng.ota.auto.service.mcu.IMcuService
    public void setMcuDelaySleep(int i) throws Exception {
        CarMcuManager carMcuManager = this.mMcuManager;
        if (carMcuManager == null) {
            throw new Exception("setMcuDelaySleep error, Car not connected");
        }
        carMcuManager.setMcuDelaySleep(i);
    }
}
