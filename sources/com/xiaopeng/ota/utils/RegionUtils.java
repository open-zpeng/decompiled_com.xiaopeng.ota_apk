package com.xiaopeng.ota.utils;

import android.car.Car;
import android.util.Log;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
/* loaded from: classes2.dex */
public class RegionUtils {
    public static final String CAR_REGION_EU = "EU";
    public static final String CAR_REGION_ZH = "ZH";
    private static final String TAG = "RegionUtils";

    public static boolean isEuropeRegion() {
        try {
            return "EU".equals(getCountryCode());
        } catch (Exception e) {
            Log.e(TAG, "Get country code occurs Exception", e);
            return false;
        }
    }

    private static String getCountryCode() throws Exception {
        if (existGetVersionInCountryCodeMethod()) {
            return Car.getVersionInCountryCode();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    private static boolean existGetVersionInCountryCodeMethod() {
        String str = TAG;
        boolean z = false;
        try {
            Method method = Class.forName("android.car.Car").getMethod("getVersionInCountryCode", new Class[0]);
            if (method != null) {
                str = str;
                if (Modifier.isStatic(method.getModifiers())) {
                    boolean isPublic = Modifier.isPublic(method.getModifiers());
                    str = isPublic;
                    if (isPublic) {
                        str = 1;
                        z = true;
                    }
                }
            } else {
                Log.d(TAG, "Car.getVersionInCountryCode() method not found");
                str = str;
            }
        } catch (Exception e) {
            Log.e(str, "Method exist Car.getVersionInCountryCode() occurs Exception", e);
        }
        return z;
    }
}
