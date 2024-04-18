package com.xiaopeng.ota.sdk.eventbroker;

import java.util.Objects;
/* loaded from: classes2.dex */
public class Subscription {
    private boolean active;
    private long activeTime;
    private Object context;
    private SubscribeMethod subscribeMethod;
    private Object subscriber;

    public Subscription(Object obj, SubscribeMethod subscribeMethod) {
        this(obj, null, subscribeMethod);
    }

    public Subscription(Object obj, Object obj2, SubscribeMethod subscribeMethod) {
        this.subscriber = obj;
        this.context = obj2;
        this.subscribeMethod = subscribeMethod;
        this.active = true;
        this.activeTime = System.currentTimeMillis();
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean z) {
        this.active = z;
    }

    public Object getSubscriber() {
        return this.subscriber;
    }

    public Object getContext() {
        return this.context;
    }

    public SubscribeMethod getSubscribeMethod() {
        return this.subscribeMethod;
    }

    public SubscribeTimeoutMethod getSubscribeTimeoutMethod() {
        return (SubscribeTimeoutMethod) this.subscribeMethod;
    }

    public long getActiveTime() {
        return this.activeTime;
    }

    public void updateActiveTime(long j) {
        this.activeTime = j;
    }

    public void updateActiveTime() {
        updateActiveTime(System.currentTimeMillis());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Subscription subscription = (Subscription) obj;
        return Objects.equals(this.subscriber, subscription.subscriber) && Objects.equals(this.subscribeMethod, subscription.subscribeMethod);
    }

    public int hashCode() {
        return Objects.hash(this.subscriber, this.subscribeMethod);
    }
}
