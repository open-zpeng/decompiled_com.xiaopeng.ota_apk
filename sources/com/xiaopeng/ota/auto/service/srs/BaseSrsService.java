package com.xiaopeng.ota.auto.service.srs;

import android.car.Car;
import android.car.hardware.srs.CarSrsManager;
import com.xiaopeng.ota.auto.callback.CarServiceEventAdapter;
import com.xiaopeng.ota.auto.callback.SrsCallbackAdapter;
import com.xiaopeng.ota.auto.service.AbsService;
import com.xiaopeng.ota.sdk.common.log.Logger;
/* loaded from: classes2.dex */
public class BaseSrsService extends AbsService implements ISrsService {
    private static final String TAG = "BaseSrsService";
    protected CarSrsManager mSrsManager;

    @Override // com.xiaopeng.ota.auto.service.AbsService
    protected void register() {
        try {
            this.mSrsManager = (CarSrsManager) getCarManagerService(Car.XP_SRS_SERVICE);
            CarServiceEventAdapter carServiceEventAdapter = new CarServiceEventAdapter(TAG, this.mCallbackList, SrsCallbackAdapter.PROP_IDS);
            this.mSrsManager.registerPropCallback(carServiceEventAdapter.getPropertyIds(), carServiceEventAdapter);
        } catch (Exception unused) {
            Logger.d(TAG, "register service fail", new Object[0]);
        }
    }

    @Override // com.xiaopeng.ota.auto.service.srs.ISrsService
    public boolean getDriverBeltWarning() throws Exception {
        CarSrsManager carSrsManager = this.mSrsManager;
        if (carSrsManager != null) {
            return carSrsManager.getDriverBeltStatus() == 1;
        }
        throw new Exception("getDriverBeltWarning error, car not connected");
    }
}
