package com.xiaopeng.ota.auto.callback;

import android.car.hardware.CarPropertyValue;
import com.xiaopeng.ota.sdk.common.log.Logger;
/* loaded from: classes2.dex */
public abstract class BaseCallbackAdapter implements CarServiceCallback {
    private static final String TAG = "BaseCallbackAdapter";

    protected abstract void onChangeEvent(int i, Object obj);

    @Override // com.xiaopeng.ota.auto.callback.CarServiceCallback
    public void onErrorEvent(int i, int i2) {
    }

    @Override // com.xiaopeng.ota.auto.callback.CarServiceCallback
    public final void onChangeEvent(CarPropertyValue carPropertyValue) {
        if (carPropertyValue == null) {
            return;
        }
        Object value = carPropertyValue.getValue();
        if (value == null) {
            Logger.e(TAG, "onChangeEvent error, value is null.", new Object[0]);
        } else {
            onChangeEvent(carPropertyValue.getPropertyId(), value);
        }
    }
}
