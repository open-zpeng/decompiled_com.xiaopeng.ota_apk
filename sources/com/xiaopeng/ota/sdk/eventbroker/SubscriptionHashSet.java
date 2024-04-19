package com.xiaopeng.ota.sdk.eventbroker;

import com.xiaopeng.ota.sdk.eventbroker.SubscriptionSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public class SubscriptionHashSet implements SubscriptionSet {
    public static SubscriptionSet.SubscriptionSetFactory factory = new SubscriptionSet.SubscriptionSetFactory() { // from class: com.xiaopeng.ota.sdk.eventbroker.SubscriptionHashSet.1
        @Override // com.xiaopeng.ota.sdk.eventbroker.SubscriptionSet.SubscriptionSetFactory
        public SubscriptionSet create() {
            return new SubscriptionHashSet();
        }
    };
    private ConcurrentHashMap<Subscription, Subscription> subscriptions = new ConcurrentHashMap<>();

    @Override // com.xiaopeng.ota.sdk.eventbroker.SubscriptionSet
    public boolean contains(Subscription subscription) {
        return this.subscriptions.containsKey(subscription);
    }

    @Override // com.xiaopeng.ota.sdk.eventbroker.SubscriptionSet
    public void add(Subscription subscription) {
        this.subscriptions.put(subscription, subscription);
    }

    @Override // com.xiaopeng.ota.sdk.eventbroker.SubscriptionSet
    public void remove(Subscription subscription) {
        this.subscriptions.remove(subscription);
    }

    @Override // com.xiaopeng.ota.sdk.eventbroker.SubscriptionSet
    public int size() {
        return this.subscriptions.size();
    }

    @Override // com.xiaopeng.ota.sdk.eventbroker.SubscriptionSet
    public boolean isEmpty() {
        return this.subscriptions.isEmpty();
    }

    @Override // com.xiaopeng.ota.sdk.eventbroker.SubscriptionSet, java.lang.Iterable
    public Iterator<Subscription> iterator() {
        return this.subscriptions.keySet().iterator();
    }

    public static SubscriptionSet.SubscriptionSetFactory getDefaultFactory() {
        return factory;
    }
}
