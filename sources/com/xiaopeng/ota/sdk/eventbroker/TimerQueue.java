package com.xiaopeng.ota.sdk.eventbroker;

import java.util.Comparator;
import java.util.PriorityQueue;
/* loaded from: classes2.dex */
public class TimerQueue<T> {
    private PriorityQueue<T> queue;

    public TimerQueue(int i, Comparator<T> comparator) {
        this.queue = new PriorityQueue<>(32, comparator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void add(T t) {
        this.queue.add(t);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized T peek() {
        return this.queue.peek();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized T poll() {
        return this.queue.poll();
    }
}
