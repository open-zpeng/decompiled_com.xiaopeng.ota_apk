package com.xiaopeng.sota.sdk;

import androidx.annotation.NonNull;
import com.xiaopeng.ota.sdk.eventbroker.DefaultThreadInvoker;
import com.xiaopeng.ota.sdk.eventbroker.EventBroker;
import com.xiaopeng.ota.sdk.eventbroker.ThreadInvokerGroup;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes2.dex */
public class SOTAEventBroker {
    private static final int POOL_CORE_SIZE = 3;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(3, new DefaultThreadFactory());
    private static final EventBroker eventBroker = new EventBroker(new ThreadInvokerGroup(new DefaultThreadInvoker(executorService)));

    public static EventBroker getEventBroker() {
        return eventBroker;
    }

    /* loaded from: classes2.dex */
    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix = "SOTAEventBroker-" + poolNumber.getAndIncrement() + "-Thread-";

        DefaultThreadFactory() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(@NonNull Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(this.namePrefix + this.threadNumber.getAndIncrement());
            return thread;
        }
    }
}
