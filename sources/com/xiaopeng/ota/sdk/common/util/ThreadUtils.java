package com.xiaopeng.ota.sdk.common.util;
/* loaded from: classes2.dex */
public class ThreadUtils {
    public static boolean postBackground(Runnable runnable) {
        return com.xiaopeng.ota.utils.ThreadUtils.postBackground(runnable);
    }

    public static boolean runOnMainThread(Runnable runnable) {
        return com.xiaopeng.ota.utils.ThreadUtils.postMainThread(runnable);
    }

    public static boolean postBackground(Runnable runnable, long j) {
        return com.xiaopeng.ota.utils.ThreadUtils.postBackground(runnable, j);
    }

    public static boolean postBackgroundLong(Runnable runnable) {
        return com.xiaopeng.ota.utils.ThreadUtils.postBackgroundLong(runnable);
    }
}
