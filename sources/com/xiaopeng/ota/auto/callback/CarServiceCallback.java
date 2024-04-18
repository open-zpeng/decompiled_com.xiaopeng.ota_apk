package com.xiaopeng.ota.auto.callback;

import android.car.hardware.CarPropertyValue;
/* loaded from: classes2.dex */
public interface CarServiceCallback {
    void onChangeEvent(CarPropertyValue carPropertyValue);

    void onErrorEvent(int i, int i2);
}
