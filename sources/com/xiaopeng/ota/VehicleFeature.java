package com.xiaopeng.ota;

import android.car.Car;
/* loaded from: classes2.dex */
public class VehicleFeature {
    private static final String TAG = "VehicleFeature";
    private static final String sCduType = Car.getXpCduType();

    public static String getCduType() {
        return sCduType;
    }

    public static boolean isD55() {
        char c;
        String str = sCduType;
        int hashCode = str.hashCode();
        if (hashCode != 2562) {
            if (hashCode == 79487 && str.equals(Car.CAR_CDU_TYPE_Q3_D55A)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals("Q3")) {
                c = 0;
            }
            c = 65535;
        }
        return c == 0 || c == 1;
    }
}
