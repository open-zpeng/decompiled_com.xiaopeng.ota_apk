package com.xiaopeng.ota.sdk.eventbroker;

import java.util.Iterator;
/* loaded from: classes2.dex */
public interface SubscriptionSet extends Iterable<Subscription> {

    /* loaded from: classes2.dex */
    public interface SubscriptionSetFactory {
        SubscriptionSet create();
    }

    void add(Subscription subscription);

    boolean contains(Subscription subscription);

    boolean isEmpty();

    @Override // java.lang.Iterable
    Iterator<Subscription> iterator();

    void remove(Subscription subscription);

    int size();
}
