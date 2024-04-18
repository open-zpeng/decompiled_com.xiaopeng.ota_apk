package com.xiaopeng.ota.utils;

import com.xiaopeng.ota.sdk.common.log.Logger;
/* loaded from: classes2.dex */
public class LogUtils {
    public static void i(String str, String str2) {
        Logger.i(str, str2, new Object[0]);
    }

    public static void i(String str, String str2, Object... objArr) {
        Logger.i(str, str2, objArr);
    }

    public static void d(String str, String str2) {
        Logger.d(str, str2, new Object[0]);
    }

    public static void d(String str, String str2, Object... objArr) {
        Logger.d(str, str2, objArr);
    }

    public static void w(String str, String str2) {
        Logger.w(str, str2, new Object[0]);
    }

    public static void w(String str, String str2, Object... objArr) {
        Logger.w(str, str2, objArr);
    }

    public static void w(String str, Throwable th, String str2) {
        Logger.w(str, th, str2, new Object[0]);
    }

    public static void w(String str, Throwable th, String str2, Object... objArr) {
        Logger.w(str, th, str2, objArr);
    }

    public static void e(String str, String str2) {
        Logger.e(str, str2, new Object[0]);
    }

    public static void e(String str, String str2, Object... objArr) {
        Logger.e(str, str2, objArr);
    }

    public static void e(String str, Throwable th, String str2) {
        Logger.e(str, th, str2, new Object[0]);
    }

    public static void e(String str, Throwable th, String str2, Object... objArr) {
        Logger.e(str, th, str2, objArr);
    }
}
