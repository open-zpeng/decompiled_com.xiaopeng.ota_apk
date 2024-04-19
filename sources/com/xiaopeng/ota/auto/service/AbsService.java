package com.xiaopeng.ota.auto.service;

import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import com.xiaopeng.ota.auto.CarApi;
import com.xiaopeng.ota.auto.callback.CarServiceCallback;
import com.xiaopeng.ota.sdk.common.log.Logger;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes2.dex */
public abstract class AbsService implements ICarService {
    private static final String TAG = "AbsService";
    protected CopyOnWriteArrayList<CarServiceCallback> mCallbackList = new CopyOnWriteArrayList<>();

    protected abstract void register();

    @Override // com.xiaopeng.ota.auto.service.ICarService
    public void registerCarService() {
        register();
    }

    @Override // com.xiaopeng.ota.auto.service.ICarService
    public final void addCarServiceCallback(CarServiceCallback carServiceCallback) {
        if (carServiceCallback == null) {
            throw new RuntimeException("CarService call back can not be null.");
        }
        if (this.mCallbackList.contains(carServiceCallback)) {
            return;
        }
        this.mCallbackList.add(carServiceCallback);
    }

    @Override // com.xiaopeng.ota.auto.service.ICarService
    public final void removeCarServiceCallback(CarServiceCallback carServiceCallback) {
        this.mCallbackList.remove(carServiceCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final CarManagerBase getCarManagerService(String str) {
        try {
            Object carManager = CarApi.getCar().getCarManager(str);
            if (carManager instanceof CarManagerBase) {
                return (CarManagerBase) carManager;
            }
            return null;
        } catch (CarNotConnectedException e) {
            Logger.e(TAG, e, "getCarManagerService error, CarNotConnectedException", new Object[0]);
            return null;
        } catch (Exception e2) {
            Logger.e(TAG, e2, "getCarManagerService error", new Object[0]);
            return null;
        }
    }
}
