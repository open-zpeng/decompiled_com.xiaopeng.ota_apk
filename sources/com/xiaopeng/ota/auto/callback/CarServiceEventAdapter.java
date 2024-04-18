package com.xiaopeng.ota.auto.callback;

import android.car.hardware.CarPropertyValue;
import android.car.hardware.avm.CarAvmManager;
import android.car.hardware.bcm.CarBcmManager;
import android.car.hardware.ciu.CarCiuManager;
import android.car.hardware.icm.CarIcmManager;
import android.car.hardware.mcu.CarMcuManager;
import android.car.hardware.tbox.CarTboxManager;
import android.car.hardware.vcu.CarVcuManager;
import com.xiaopeng.ota.auto.CarApiThreadPool;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes2.dex */
public class CarServiceEventAdapter implements CarBcmManager.CarBcmEventCallback, CarMcuManager.CarMcuEventCallback, CarVcuManager.CarVcuEventCallback, CarIcmManager.CarIcmEventCallback, CarAvmManager.CarAvmEventCallback, CarCiuManager.CarCiuEventCallback, CarTboxManager.CarTboxEventCallback {
    private static final String TAG = "CarServiceEventAdapter";
    private CopyOnWriteArrayList<CarServiceCallback> mCallbackList;
    private List<Integer> mPropertyIds = new ArrayList();
    private String mServiceName;

    public CarServiceEventAdapter(String str, CopyOnWriteArrayList<CarServiceCallback> copyOnWriteArrayList, int[] iArr) {
        this.mServiceName = str;
        this.mCallbackList = copyOnWriteArrayList;
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        for (int i : iArr) {
            this.mPropertyIds.add(Integer.valueOf(i));
        }
    }

    public List<Integer> getPropertyIds() {
        return this.mPropertyIds;
    }

    @Override // android.car.hardware.CarEcuManager.CarEcuEventCallback
    public void onChangeEvent(final CarPropertyValue carPropertyValue) {
        if (carPropertyValue != null && filterEvent(carPropertyValue.getPropertyId())) {
            CarApiThreadPool.execute(new Runnable() { // from class: com.xiaopeng.ota.auto.callback.-$$Lambda$CarServiceEventAdapter$dLgRBfVyynquwHGbfObohVlKH8g
                @Override // java.lang.Runnable
                public final void run() {
                    CarServiceEventAdapter.this.lambda$onChangeEvent$0$CarServiceEventAdapter(carPropertyValue);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onChangeEvent$0$CarServiceEventAdapter(CarPropertyValue carPropertyValue) {
        if (isCallbackEmpty()) {
            return;
        }
        Iterator<CarServiceCallback> it = this.mCallbackList.iterator();
        while (it.hasNext()) {
            CarServiceCallback next = it.next();
            if (next != null) {
                next.onChangeEvent(carPropertyValue);
            }
        }
    }

    @Override // android.car.hardware.CarEcuManager.CarEcuEventCallback
    public void onErrorEvent(final int i, final int i2) {
        if (filterEvent(i)) {
            return;
        }
        CarApiThreadPool.execute(new Runnable() { // from class: com.xiaopeng.ota.auto.callback.-$$Lambda$CarServiceEventAdapter$AYM-NQDCbW50MDwZdSdM-vrQ9zo
            @Override // java.lang.Runnable
            public final void run() {
                CarServiceEventAdapter.this.lambda$onErrorEvent$1$CarServiceEventAdapter(i, i2);
            }
        });
    }

    public /* synthetic */ void lambda$onErrorEvent$1$CarServiceEventAdapter(int i, int i2) {
        if (isCallbackEmpty()) {
            return;
        }
        Iterator<CarServiceCallback> it = this.mCallbackList.iterator();
        while (it.hasNext()) {
            CarServiceCallback next = it.next();
            if (next != null) {
                next.onErrorEvent(i, i2);
            }
        }
    }

    private boolean filterEvent(int i) {
        List<Integer> list = this.mPropertyIds;
        if (list == null) {
            return true;
        }
        return list.contains(Integer.valueOf(i));
    }

    private boolean isCallbackEmpty() {
        CopyOnWriteArrayList<CarServiceCallback> copyOnWriteArrayList = this.mCallbackList;
        return copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty();
    }
}
