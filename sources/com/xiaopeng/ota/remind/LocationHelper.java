package com.xiaopeng.ota.remind;
/* loaded from: classes2.dex */
public class LocationHelper {
    private static final float EARTH_RADIUS = 6371000.0f;

    public static double angle(double d) {
        return (d * 180.0d) / 3.141592653589793d;
    }

    public static double radian(double d) {
        return (d * 3.141592653589793d) / 180.0d;
    }

    public static double distance(double d, double d2, double d3, double d4) {
        double radian = radian(d);
        double radian2 = radian(d2);
        double radian3 = radian(d3);
        double radian4 = radian(d4);
        return Math.asin(Math.sqrt(Math.pow(Math.sin(Math.abs(radian - radian3) / 2.0d), 2.0d) + (Math.cos(radian) * Math.cos(radian3) * Math.pow(Math.sin(Math.abs(radian2 - radian4) / 2.0d), 2.0d)))) * 2.0d * 6371000.0d;
    }

    private static double haversin(double d) {
        double sin = Math.sin(d / 2.0d);
        return sin * sin;
    }
}
