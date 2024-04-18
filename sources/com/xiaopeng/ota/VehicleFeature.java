package com.xiaopeng.ota;

import android.car.Car;
import android.util.Log;
/* loaded from: classes2.dex */
public class VehicleFeature {
    private static final String TAG = "VehicleFeature";
    private static final String sVehicleHardwareType;

    static {
        String str;
        try {
            str = Car.getHardwareCarType();
        } catch (Exception e) {
            Log.e(TAG, "Car.getHardwareCarType fail", e);
            str = "E28";
        }
        sVehicleHardwareType = str;
    }

    public static String getCarType() {
        return sVehicleHardwareType;
    }

    public static boolean isCarTypeD55() {
        return "D55".equals(sVehicleHardwareType);
    }
}
