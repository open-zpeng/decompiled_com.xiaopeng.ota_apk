package com.xiaopeng.ota.sdk.eventbroker;
/* loaded from: classes2.dex */
public interface ThreadInvoker {
    void invoke(Subscription subscription);

    void invoke(Subscription subscription, Object obj, PostFuture postFuture);
}
