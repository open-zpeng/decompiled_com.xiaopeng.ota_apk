package com.xiaopeng.ota.auto.service.icm;

import android.car.Car;
import android.car.hardware.icm.CarIcmManager;
import com.xiaopeng.ota.auto.callback.CarServiceEventAdapter;
import com.xiaopeng.ota.auto.callback.IcmCallbackAdapter;
import com.xiaopeng.ota.auto.service.AbsService;
/* loaded from: classes2.dex */
public class BaseIcmService extends AbsService implements IIcmService {
    private static final String TAG = "BaseIcmService";
    protected CarIcmManager mIcmManager;

    @Override // com.xiaopeng.ota.auto.service.AbsService
    protected void register() {
        try {
            this.mIcmManager = (CarIcmManager) getCarManagerService(Car.XP_ICM_SERVICE);
            CarServiceEventAdapter carServiceEventAdapter = new CarServiceEventAdapter(TAG, this.mCallbackList, IcmCallbackAdapter.PROP_IDS);
            this.mIcmManager.registerPropCallback(carServiceEventAdapter.getPropertyIds(), carServiceEventAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.xiaopeng.ota.auto.service.icm.IIcmService
    public boolean getIcmConnectionState() throws Exception {
        CarIcmManager carIcmManager = this.mIcmManager;
        if (carIcmManager != null) {
            return carIcmManager.getIcmConnectionState() == 1;
        }
        throw new Exception("getIcmConnectionState error, car not connected");
    }

    @Override // com.xiaopeng.ota.auto.service.icm.IIcmService
    public void sendBinMsg(int i, byte[] bArr, byte[] bArr2) throws Exception {
        CarIcmManager carIcmManager = this.mIcmManager;
        if (carIcmManager == null) {
            throw new Exception("sendBinMsg error, car not connected");
        }
        carIcmManager.sendRomBinMsg(i, bArr, bArr2);
    }

    @Override // com.xiaopeng.ota.auto.service.icm.IIcmService
    public void sendRomBinMsg(byte[] bArr) throws Exception {
        throw new Exception("Not support");
    }

    @Override // com.xiaopeng.ota.auto.service.icm.IIcmService
    public float getLastStartUpMileage() throws Exception {
        CarIcmManager carIcmManager = this.mIcmManager;
        if (carIcmManager == null) {
            throw new Exception("getLastStartUpMileage error, car not connected");
        }
        return carIcmManager.getLastStartUpMileage();
    }
}
