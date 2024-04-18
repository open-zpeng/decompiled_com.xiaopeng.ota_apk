package com.xiaopeng.ota.auto.service;

import com.xiaopeng.ota.auto.callback.CarServiceCallback;
/* loaded from: classes2.dex */
public interface ICarService {
    void addCarServiceCallback(CarServiceCallback carServiceCallback);

    void registerCarService();

    void removeCarServiceCallback(CarServiceCallback carServiceCallback);
}
