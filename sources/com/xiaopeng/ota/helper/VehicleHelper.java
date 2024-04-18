package com.xiaopeng.ota.helper;
/* loaded from: classes2.dex */
public class VehicleHelper {
    public static final int GEAR_LEVEL_D = 1;
    public static final int GEAR_LEVEL_N = 2;
    public static final int GEAR_LEVEL_P = 4;
    public static final int GEAR_LEVEL_R = 3;
    private static int sCurrentGearLever;
    private static long sLastGearLeverChangedTime;

    public static synchronized void onGearLeverChanged(int i) {
        synchronized (VehicleHelper.class) {
            sLastGearLeverChangedTime = System.currentTimeMillis();
            sCurrentGearLever = i;
        }
    }

    public static synchronized int getCurrentGearLever() {
        int i;
        synchronized (VehicleHelper.class) {
            i = sCurrentGearLever;
        }
        return i;
    }

    public static synchronized long getParkedTime() {
        synchronized (VehicleHelper.class) {
            if (sCurrentGearLever != 4 || sLastGearLeverChangedTime <= 0) {
                return 0L;
            }
            return System.currentTimeMillis() - sLastGearLeverChangedTime;
        }
    }
}
