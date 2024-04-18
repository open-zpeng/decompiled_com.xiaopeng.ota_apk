package com.xiaopeng.fota.sdk;
/* loaded from: classes2.dex */
final class Log {
    private static final int LVL_DEBUG = 1;
    private static final int LVL_ERROR = 4;
    private static final int LVL_FATAL = 5;
    private static final int LVL_INFO = 2;
    private static final int LVL_VERBOSE = 0;
    private static final int LVL_WARNING = 3;

    private static native void log(int i, String str, String str2);

    Log() {
    }

    private static String strFormat(String str, Object[] objArr) {
        return objArr.length > 0 ? String.format(str, objArr) : str;
    }

    static void v(String str, String str2, Object... objArr) {
        log(0, str, strFormat(str2, objArr));
    }

    static void d(String str, String str2, Object... objArr) {
        log(1, str, strFormat(str2, objArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void i(String str, String str2, Object... objArr) {
        log(2, str, strFormat(str2, objArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void w(String str, String str2, Object... objArr) {
        log(3, str, strFormat(str2, objArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(String str, String str2, Object... objArr) {
        log(4, str, strFormat(str2, objArr));
    }

    static void f(String str, String str2, Object... objArr) {
        log(5, str, strFormat(str2, objArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String hex(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02x", Byte.valueOf(bArr[i])));
        }
        return sb.toString();
    }
}
