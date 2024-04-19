package com.xiaopeng.ota.sdk.eventbroker;

import androidx.annotation.NonNull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes2.dex */
public class DefaultThreadInvoker implements ThreadInvoker {
    private ExecutorService executorService;

    public DefaultThreadInvoker(int i) {
        this(Executors.newFixedThreadPool(i, new DefaultThreadFactory()));
    }

    public DefaultThreadInvoker(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override // com.xiaopeng.ota.sdk.eventbroker.ThreadInvoker
    public void invoke(final Subscription subscription, final Object obj, final PostFuture postFuture) {
        this.executorService.execute(new Runnable() { // from class: com.xiaopeng.ota.sdk.eventbroker.DefaultThreadInvoker.1
            @Override // java.lang.Runnable
            public void run() {
                SubscribeMethodInvoker.invoke(subscription, obj, postFuture);
            }
        });
    }

    @Override // com.xiaopeng.ota.sdk.eventbroker.ThreadInvoker
    public void invoke(final Subscription subscription) {
        this.executorService.execute(new Runnable() { // from class: com.xiaopeng.ota.sdk.eventbroker.DefaultThreadInvoker.2
            @Override // java.lang.Runnable
            public void run() {
                SubscribeMethodInvoker.invoke(subscription);
            }
        });
    }

    /* loaded from: classes2.dex */
    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix = "EventBroker-" + poolNumber.getAndIncrement() + "-Thread-";

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
