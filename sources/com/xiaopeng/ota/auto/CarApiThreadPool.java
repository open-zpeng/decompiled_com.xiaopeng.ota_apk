package com.xiaopeng.ota.auto;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes2.dex */
public class CarApiThreadPool {
    private static ExecutorService sThreadPool = Executors.newCachedThreadPool();

    public static void execute(Runnable runnable) {
        sThreadPool.execute(runnable);
    }

    public static void shutdown() {
        sThreadPool.shutdown();
    }

    public static ExecutorService getPooledExecutors() {
        return sThreadPool;
    }
}
