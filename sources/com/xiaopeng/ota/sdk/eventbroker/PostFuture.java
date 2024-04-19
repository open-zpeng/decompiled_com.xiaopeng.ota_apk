package com.xiaopeng.ota.sdk.eventbroker;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes2.dex */
public class PostFuture {
    private EventListener listener;
    private int receiver;
    private volatile boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = this.lock.newCondition();

    public PostFuture() {
    }

    public PostFuture(EventListener eventListener) {
        this.listener = eventListener;
    }

    public void posted(int i) {
        this.lock.lock();
        this.done = true;
        try {
            this.condition.signalAll();
        } finally {
            this.lock.unlock();
        }
    }

    public void callback(Subscription subscription, Object obj) {
        EventListener eventListener = this.listener;
        if (eventListener != null) {
            eventListener.onCompleted(subscription.getSubscriber(), subscription.getSubscribeMethod().getMethod(), obj);
        }
    }

    public void callback(Subscription subscription, Exception exc) {
        EventListener eventListener = this.listener;
        if (eventListener != null) {
            eventListener.onException(subscription.getSubscriber(), subscription.getSubscribeMethod().getMethod(), exc);
        }
    }

    public boolean isPosted() {
        return this.done;
    }

    public boolean await(long j) {
        this.lock.lock();
        while (!this.done) {
            try {
                if (!this.condition.await(j, TimeUnit.MILLISECONDS)) {
                    break;
                }
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        }
        this.lock.unlock();
        return this.done;
    }
}
