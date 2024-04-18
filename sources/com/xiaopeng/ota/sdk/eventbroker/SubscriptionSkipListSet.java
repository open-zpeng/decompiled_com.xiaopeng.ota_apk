package com.xiaopeng.ota.sdk.eventbroker;

import com.xiaopeng.ota.sdk.eventbroker.SubscriptionSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListSet;
/* loaded from: classes2.dex */
public class SubscriptionSkipListSet implements SubscriptionSet {
    private static SubscriptionSet.SubscriptionSetFactory factory = new SubscriptionSet.SubscriptionSetFactory() { // from class: com.xiaopeng.ota.sdk.eventbroker.SubscriptionSkipListSet.1
        @Override // com.xiaopeng.ota.sdk.eventbroker.SubscriptionSet.SubscriptionSetFactory
        public SubscriptionSet create() {
            return new SubscriptionSkipListSet();
        }
    };
    private ConcurrentSkipListSet<Subscription> subscriptions = new ConcurrentSkipListSet<>(new SubscriptionComparator());

    @Override // com.xiaopeng.ota.sdk.eventbroker.SubscriptionSet
    public boolean contains(Subscription subscription) {
        return this.subscriptions.contains(subscription);
    }

    @Override // com.xiaopeng.ota.sdk.eventbroker.SubscriptionSet
    public void add(Subscription subscription) {
        this.subscriptions.add(subscription);
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
        return this.subscriptions.iterator();
    }

    public static SubscriptionSet.SubscriptionSetFactory getDefaultFactory() {
        return factory;
    }

    /* loaded from: classes2.dex */
    public static class SubscriptionComparator implements Comparator<Subscription> {
        @Override // java.util.Comparator
        public int compare(Subscription subscription, Subscription subscription2) {
            if (subscription.equals(subscription2)) {
                return 0;
            }
            int priority = subscription2.getSubscribeMethod().getPriority() - subscription.getSubscribeMethod().getPriority();
            if (priority != 0) {
                return priority;
            }
            return -1;
        }
    }
}
