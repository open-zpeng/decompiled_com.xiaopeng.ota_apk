package com.xiaopeng.xuimanager.utils;

import android.util.Log;
/* loaded from: classes2.dex */
public class LogUtil {
    private static int defaultLevel = 1;
    private static int targetLevel = -1;
    public static final int xD = 1;
    public static final int xE = 4;
    public static final int xI = 2;
    public static final int xNon = 10;
    public static final int xV = 0;
    public static final int xW = 3;

    public static void setModuleLogLevel(String str, int i) {
    }

    public static void log(int i, String str, String str2) {
        int i2 = targetLevel;
        if (-1 != i2 && i < i2) {
            i = i2;
        }
        if (i >= defaultLevel) {
            if (i == 0) {
                Log.v(str, str2);
            } else if (i == 1) {
                Log.d(str, str2);
            } else if (i == 2) {
                Log.i(str, str2);
            } else if (i == 3) {
                Log.w(str, str2);
            } else if (i != 4) {
            } else {
                Log.e(str, str2);
            }
        }
    }

    public static void setDefaultLevel(int i) {
        if (i >= 0 && i <= 4) {
            defaultLevel = i;
        } else if (10 == i) {
            defaultLevel = i;
        }
    }

    public static int getDefaultLevel() {
        return defaultLevel;
    }

    public static void setOutputLevel(int i) {
        if (i >= 0 && i <= 4) {
            targetLevel = i;
        } else if (-1 == i) {
            targetLevel = -1;
        }
    }

    public static int getOutputLevel() {
        return targetLevel;
    }
}
