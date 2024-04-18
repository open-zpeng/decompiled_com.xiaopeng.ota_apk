package com.xiaopeng.ota.auto.service.ciu;

import android.car.Car;
import android.car.hardware.ciu.CarCiuManager;
import com.xiaopeng.ota.auto.callback.CarServiceEventAdapter;
import com.xiaopeng.ota.auto.callback.CiuCallbackAdapter;
import com.xiaopeng.ota.auto.service.AbsService;
import com.xiaopeng.ota.sdk.common.log.Logger;
/* loaded from: classes2.dex */
public class BaseCiuService extends AbsService implements ICiuService {
    private static final String TAG = "BaseCiuService";
    protected CarCiuManager mCiuManager;

    @Override // com.xiaopeng.ota.auto.service.AbsService
    protected void register() {
        try {
            this.mCiuManager = (CarCiuManager) getCarManagerService(Car.XP_CIU_SERVICE);
            CarServiceEventAdapter carServiceEventAdapter = new CarServiceEventAdapter(TAG, this.mCallbackList, CiuCallbackAdapter.PROP_IDS);
            this.mCiuManager.registerPropCallback(carServiceEventAdapter.getPropertyIds(), carServiceEventAdapter);
        } catch (Exception unused) {
            Logger.d(TAG, "register service fail", new Object[0]);
        }
    }

    @Override // com.xiaopeng.ota.auto.service.ciu.ICiuService
    public void setDvrMode(int i) throws Exception {
        CarCiuManager carCiuManager = this.mCiuManager;
        if (carCiuManager == null) {
            throw new Exception("setDvrMode error, car not connected");
        }
        carCiuManager.setDvrMode(i);
    }

    @Override // com.xiaopeng.ota.auto.service.ciu.ICiuService
    public int getDvrMode() throws Exception {
        CarCiuManager carCiuManager = this.mCiuManager;
        if (carCiuManager == null) {
            throw new Exception("getDvrMode error, car not connected");
        }
        return carCiuManager.getDvrMode();
    }
}
