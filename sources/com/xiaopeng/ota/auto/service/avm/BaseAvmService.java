package com.xiaopeng.ota.auto.service.avm;

import android.car.Car;
import android.car.hardware.avm.CarAvmManager;
import com.xiaopeng.ota.auto.callback.AvmCallbackAdapter;
import com.xiaopeng.ota.auto.callback.CarServiceEventAdapter;
import com.xiaopeng.ota.auto.service.AbsService;
import com.xiaopeng.ota.sdk.common.log.Logger;
/* loaded from: classes2.dex */
public class BaseAvmService extends AbsService implements IAvmService {
    private static final String TAG = "BaseAvmService";
    protected CarAvmManager mAvmManager;

    @Override // com.xiaopeng.ota.auto.service.AbsService
    protected void register() {
        try {
            this.mAvmManager = (CarAvmManager) getCarManagerService(Car.XP_AVM_SERVICE);
            CarServiceEventAdapter carServiceEventAdapter = new CarServiceEventAdapter(TAG, this.mCallbackList, AvmCallbackAdapter.PROP_IDS);
            this.mAvmManager.registerPropCallback(carServiceEventAdapter.getPropertyIds(), carServiceEventAdapter);
        } catch (Exception unused) {
            Logger.d(TAG, "register service fail", new Object[0]);
        }
    }

    @Override // com.xiaopeng.ota.auto.service.avm.IAvmService
    public boolean getHasRoofCamera() throws Exception {
        CarAvmManager carAvmManager = this.mAvmManager;
        if (carAvmManager != null) {
            return carAvmManager.getHasRoofCamera() == 2;
        }
        throw new Exception("getHasRoofCamera error, car not connected");
    }
}
