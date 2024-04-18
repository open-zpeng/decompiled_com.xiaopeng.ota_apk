package com.xiaopeng.ota.sdk.eventbroker;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
/* loaded from: classes2.dex */
public class MainThreadInvoker implements ThreadInvoker {
    private final Handler handler = new MainThreadHandler();

    @Override // com.xiaopeng.ota.sdk.eventbroker.ThreadInvoker
    public void invoke(final Subscription subscription, final Object obj, final PostFuture postFuture) {
        Message.obtain(this.handler, new Runnable() { // from class: com.xiaopeng.ota.sdk.eventbroker.MainThreadInvoker.1
            @Override // java.lang.Runnable
            public void run() {
                SubscribeMethodInvoker.invoke(subscription, obj, postFuture);
            }
        }).sendToTarget();
    }

    @Override // com.xiaopeng.ota.sdk.eventbroker.ThreadInvoker
    public void invoke(final Subscription subscription) {
        Message.obtain(this.handler, new Runnable() { // from class: com.xiaopeng.ota.sdk.eventbroker.MainThreadInvoker.2
            @Override // java.lang.Runnable
            public void run() {
                SubscribeMethodInvoker.invoke(subscription);
            }
        }).sendToTarget();
    }

    /* loaded from: classes2.dex */
    private static class MainThreadHandler extends Handler {
        public MainThreadHandler() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    }
}
