package com.xiaopeng.ota.auto.service.hvac;

import android.car.Car;
import android.car.hardware.hvac.CarHvacManager;
import com.xiaopeng.ota.auto.service.AbsService;
import com.xiaopeng.ota.sdk.common.log.Logger;
/* loaded from: classes2.dex */
public class BaseHvacService extends AbsService implements IHvacService {
    private static final String TAG = "BaseHvacService";
    private CarHvacManager mHvacManager;

    @Override // com.xiaopeng.ota.auto.service.AbsService
    protected void register() {
        try {
            this.mHvacManager = (CarHvacManager) getCarManagerService(Car.HVAC_SERVICE);
        } catch (Exception unused) {
            Logger.d(TAG, "register service fail", new Object[0]);
        }
    }

    @Override // com.xiaopeng.ota.auto.service.hvac.IHvacService
    public int getHvacPowerMode() throws Exception {
        CarHvacManager carHvacManager = this.mHvacManager;
        if (carHvacManager == null) {
            throw new Exception("getHvacPowerMode error, car not connected");
        }
        return carHvacManager.getHvacPowerMode();
    }
}
