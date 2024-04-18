package com.xiaopeng.ota.auto.service.bcm;

import android.car.Car;
import android.car.hardware.bcm.CarBcmManager;
import com.xiaopeng.ota.auto.callback.BcmCallbackAdapter;
import com.xiaopeng.ota.auto.callback.CarServiceEventAdapter;
import com.xiaopeng.ota.auto.service.AbsService;
import com.xiaopeng.ota.sdk.common.log.Logger;
/* loaded from: classes2.dex */
public class BaseBcmService extends AbsService implements IBcmService {
    private static final String TAG = "BaseBcmService";
    protected CarBcmManager mBcmManager;

    @Override // com.xiaopeng.ota.auto.service.AbsService
    protected void register() {
        try {
            this.mBcmManager = (CarBcmManager) getCarManagerService(Car.XP_BCM_SERVICE);
            CarServiceEventAdapter carServiceEventAdapter = new CarServiceEventAdapter(TAG, this.mCallbackList, BcmCallbackAdapter.PROP_IDS);
            this.mBcmManager.registerPropCallback(carServiceEventAdapter.getPropertyIds(), carServiceEventAdapter);
        } catch (Exception unused) {
            Logger.d(TAG, "register service fail", new Object[0]);
        }
    }

    @Override // com.xiaopeng.ota.auto.service.bcm.IBcmService
    public int getATwsState() throws Exception {
        CarBcmManager carBcmManager = this.mBcmManager;
        if (carBcmManager == null) {
            throw new Exception("getATWSState error, car not connected");
        }
        return carBcmManager.getAtwsState();
    }

    @Override // com.xiaopeng.ota.auto.service.bcm.IBcmService
    public int getPowerMode() throws Exception {
        CarBcmManager carBcmManager = this.mBcmManager;
        if (carBcmManager == null) {
            throw new Exception("getPowerMode error, car not connected");
        }
        return carBcmManager.getBcmPowerMode();
    }

    @Override // com.xiaopeng.ota.auto.service.bcm.IBcmService
    public boolean getDriverBeltWarning() throws Exception {
        CarBcmManager carBcmManager = this.mBcmManager;
        if (carBcmManager != null) {
            return carBcmManager.getDriverBeltWarning() != 0;
        }
        throw new Exception("getDriverBeltWarning error, car not connected");
    }

    @Override // com.xiaopeng.ota.auto.service.bcm.IBcmService
    public int getDoorLockState() throws Exception {
        CarBcmManager carBcmManager = this.mBcmManager;
        if (carBcmManager == null) {
            throw new Exception("getDoorLockState error, car not connected");
        }
        return carBcmManager.getDoorLockState();
    }

    @Override // com.xiaopeng.ota.auto.service.bcm.IBcmService
    public int getWindowLockState() throws Exception {
        CarBcmManager carBcmManager = this.mBcmManager;
        if (carBcmManager == null) {
            throw new Exception("getWindowLockState error, car not connected");
        }
        return carBcmManager.getWindowLockState();
    }

    @Override // com.xiaopeng.ota.auto.service.bcm.IBcmService
    public int getDriverOnSeat() throws Exception {
        CarBcmManager carBcmManager = this.mBcmManager;
        if (carBcmManager == null) {
            throw new Exception("getDriverOnSeat error, car not connected");
        }
        return carBcmManager.getDriverOnSeat();
    }

    @Override // com.xiaopeng.ota.auto.service.bcm.IBcmService
    public float[] getWindowsState() throws Exception {
        CarBcmManager carBcmManager = this.mBcmManager;
        if (carBcmManager == null) {
            throw new Exception("getWindowsState error, car not connected");
        }
        return carBcmManager.getWindowsState();
    }

    @Override // com.xiaopeng.ota.auto.service.bcm.IBcmService
    public int[] getDoorsState() throws Exception {
        CarBcmManager carBcmManager = this.mBcmManager;
        if (carBcmManager == null) {
            throw new Exception("getDoorsState error, car not connected");
        }
        return carBcmManager.getDoorsState();
    }
}
