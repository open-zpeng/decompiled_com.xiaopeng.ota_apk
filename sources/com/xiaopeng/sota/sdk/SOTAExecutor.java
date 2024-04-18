package com.xiaopeng.sota.sdk;

import androidx.annotation.NonNull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes2.dex */
public class SOTAExecutor {
    private static final long KEEP_ALIVE_TIME = 60;
    private static final int POOL_CORE_SIZE = 5;
    private static ExecutorService executorService;

    /* loaded from: classes2.dex */
    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix = "SOTAExecutor-" + poolNumber.getAndIncrement() + "-Thread-";

        DefaultThreadFactory() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(@NonNull Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(this.namePrefix + this.threadNumber.getAndIncrement());
            return thread;
        }
    }

    public static void initialize() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, 5, (long) KEEP_ALIVE_TIME, TimeUnit.SECONDS, new SynchronousQueue(), new DefaultThreadFactory());
        }
    }

    public static void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

    public static void dispose() {
        ExecutorService executorService2 = executorService;
        if (executorService2 != null) {
            executorService2.shutdownNow();
            executorService = null;
        }
    }
}
