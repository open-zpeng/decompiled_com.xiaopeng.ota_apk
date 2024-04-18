package com.xiaopeng.ota.sdk.eventbroker;

import android.util.Log;
import com.xiaopeng.ota.sdk.eventbroker.SubscriptionSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class EventBroker {
    private static final String TAG = "EventBroker";
    private static final EventBroker instance = new EventBroker();
    private volatile boolean disposed;
    private Thread eventLoop;
    private final BlockingQueue<EventEnvelope> eventQueue;
    private MainThreadInvoker mainThreadInvoker;
    private final SubscribeMethodFinder methodFinder;
    private final Map<Class<?>, SubscriptionSkipListSet> subscriptionsByEventType;
    private final Map<Object, List<Subscription>> subscriptionsBySubscriber;
    private ThreadInvoker threadInvoker;
    private final TimerQueue<Subscription> timeoutMethodsQueue;
    private final Map<Class<?>, SubscriptionHashSet> timeoutSubscriptionsByEventType;

    public static EventBroker getDefaultEventBroker() {
        return instance;
    }

    public EventBroker() {
        this(new ThreadInvokerGroup(new DefaultThreadInvoker(1)));
    }

    public EventBroker(ThreadInvoker threadInvoker) {
        this.subscriptionsByEventType = new ConcurrentHashMap();
        this.timeoutSubscriptionsByEventType = new ConcurrentHashMap();
        this.subscriptionsBySubscriber = new ConcurrentHashMap();
        this.timeoutMethodsQueue = new TimerQueue<>(32, new TimerQueueComparator(null));
        this.eventQueue = new LinkedBlockingDeque();
        this.methodFinder = new SubscribeMethodFinder();
        this.mainThreadInvoker = new MainThreadInvoker();
        this.eventLoop = new DefaultEventLoop();
        this.threadInvoker = threadInvoker;
        this.eventLoop.start();
    }

    public void dispose() {
        this.disposed = true;
        try {
            this.eventLoop.join(5000L);
        } catch (Exception unused) {
        }
    }

    private List<Subscription> subscriptionBySubscriber(Object obj) {
        List<Subscription> list = this.subscriptionsBySubscriber.get(obj);
        if (list == null) {
            ArrayList arrayList = new ArrayList();
            this.subscriptionsBySubscriber.put(obj, arrayList);
            return arrayList;
        }
        return list;
    }

    private void register(List<Subscription> list, Object obj, SubscribeMethod subscribeMethod, Object obj2) {
        Class<?>[] eventTypes;
        for (Class<?> cls : subscribeMethod.getEventTypes()) {
            Subscription subscription = new Subscription(obj, obj2, subscribeMethod);
            register(this.subscriptionsByEventType, cls, subscription, SubscriptionSkipListSet.getDefaultFactory());
            if (!list.contains(subscription)) {
                list.add(subscription);
            }
        }
    }

    private void register(List<Subscription> list, Object obj, SubscribeTimeoutMethod subscribeTimeoutMethod, Object obj2) {
        Class<?>[] eventTypes;
        for (Class<?> cls : subscribeTimeoutMethod.getEventTypes()) {
            Subscription subscription = new Subscription(obj, obj2, subscribeTimeoutMethod);
            if (!list.contains(subscription)) {
                if (register(this.timeoutSubscriptionsByEventType, cls, subscription, SubscriptionHashSet.getDefaultFactory())) {
                    this.timeoutMethodsQueue.add(subscription);
                }
                list.add(subscription);
            }
        }
    }

    public synchronized void register(Object obj) {
        register(obj, null);
    }

    public synchronized void register(Object obj, Object obj2) {
        SubscribeMethodGroup subscribeMethodGroup = this.methodFinder.getSubscribeMethodGroup(obj);
        ArrayList arrayList = new ArrayList();
        for (SubscribeMethod subscribeMethod : subscribeMethodGroup.getSubscribeMethods()) {
            register(arrayList, obj, subscribeMethod, obj2);
        }
        for (SubscribeTimeoutMethod subscribeTimeoutMethod : subscribeMethodGroup.getSubscribeTimeoutMethods()) {
            register((List<Subscription>) arrayList, obj, subscribeTimeoutMethod, obj2);
        }
        this.subscriptionsBySubscriber.put(obj, arrayList);
    }

    public synchronized void unregister(Object obj) {
        Class<?>[] eventTypes;
        List<Subscription> list = this.subscriptionsBySubscriber.get(obj);
        if (list == null) {
            return;
        }
        for (Subscription subscription : list) {
            SubscribeMethod subscribeMethod = subscription.getSubscribeMethod();
            for (Class<?> cls : subscribeMethod.getEventTypes()) {
                if (subscribeMethod instanceof SubscribeTimeoutMethod) {
                    unregister(this.timeoutSubscriptionsByEventType, cls, subscription);
                } else {
                    unregister(this.subscriptionsByEventType, cls, subscription);
                }
            }
        }
        this.subscriptionsBySubscriber.remove(obj);
    }

    public PostFuture postEvent(Event event) {
        return postEvent(null, event, null);
    }

    public PostFuture postEvent(Event event, EventListener eventListener) {
        return postEvent(null, event, eventListener);
    }

    public PostFuture postEvent(Object obj, Event event) {
        return postEvent(null, event, null);
    }

    public PostFuture postEvent(Object obj, Event event, EventListener eventListener) {
        PostFuture postFuture = new PostFuture(eventListener);
        this.eventQueue.add(new EventEnvelope(obj, event, postFuture));
        return postFuture;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T extends SubscriptionSet> boolean register(Map<Class<?>, T> map, Class<?> cls, Subscription subscription, SubscriptionSet.SubscriptionSetFactory subscriptionSetFactory) {
        SubscriptionSet subscriptionSet = (SubscriptionSet) map.get(cls);
        if (subscriptionSet == null) {
            subscriptionSet = subscriptionSetFactory.create();
            map.put(cls, subscriptionSet);
        }
        if (subscriptionSet.contains(subscription)) {
            return false;
        }
        subscriptionSet.add(subscription);
        return true;
    }

    private <T extends SubscriptionSet> void unregister(Map<Class<?>, T> map, Class<?> cls, Subscription subscription) {
        T t = map.get(cls);
        if (t == null) {
            return;
        }
        subscription.setActive(false);
        t.remove(subscription);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatch(EventEnvelope eventEnvelope) {
        int dispatchByEvent;
        if (eventEnvelope.subscriber != null) {
            dispatchByEvent = dispatchBySubscriber(eventEnvelope);
        } else {
            dispatchByEvent = dispatchByEvent(eventEnvelope);
        }
        eventEnvelope.future.posted(dispatchByEvent);
    }

    private int dispatchByEvent(EventEnvelope eventEnvelope) {
        SubscriptionSkipListSet<Subscription> subscriptionSkipListSet = this.subscriptionsByEventType.get(eventEnvelope.event.getClass());
        if (subscriptionSkipListSet == null) {
            return 0;
        }
        for (Subscription subscription : subscriptionSkipListSet) {
            invoke(subscription, eventEnvelope.event, eventEnvelope.future);
        }
        SubscriptionHashSet<Subscription> subscriptionHashSet = this.timeoutSubscriptionsByEventType.get(eventEnvelope.event.getClass());
        if (subscriptionHashSet == null) {
            return subscriptionSkipListSet.size();
        }
        long currentTimeMillis = System.currentTimeMillis();
        for (Subscription subscription2 : subscriptionHashSet) {
            subscription2.updateActiveTime(currentTimeMillis);
        }
        return subscriptionSkipListSet.size();
    }

    private int dispatchBySubscriber(EventEnvelope eventEnvelope) {
        List<Subscription> list = this.subscriptionsBySubscriber.get(eventEnvelope.subscriber);
        if (list == null) {
            return 0;
        }
        for (Subscription subscription : list) {
            for (Class<?> cls : subscription.getSubscribeMethod().getEventTypes()) {
                if (cls.equals(eventEnvelope.event.getClass())) {
                    if (subscription.getSubscribeMethod() instanceof SubscribeTimeoutMethod) {
                        subscription.updateActiveTime(System.currentTimeMillis());
                    } else {
                        invoke(subscription, eventEnvelope.event, eventEnvelope.future);
                    }
                }
            }
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchTimeoutEvent(Subscription subscription) {
        invoke(subscription);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaopeng.ota.sdk.eventbroker.EventBroker$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaopeng$ota$sdk$eventbroker$ThreadMode = new int[ThreadMode.values().length];

        static {
            try {
                $SwitchMap$com$xiaopeng$ota$sdk$eventbroker$ThreadMode[ThreadMode.THREAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$xiaopeng$ota$sdk$eventbroker$ThreadMode[ThreadMode.MAIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void invoke(Subscription subscription, Object obj, PostFuture postFuture) {
        int i = AnonymousClass1.$SwitchMap$com$xiaopeng$ota$sdk$eventbroker$ThreadMode[subscription.getSubscribeMethod().getThreadMode().ordinal()];
        if (i == 1) {
            this.threadInvoker.invoke(subscription, obj, postFuture);
        } else if (i == 2) {
            this.mainThreadInvoker.invoke(subscription, obj, postFuture);
        } else {
            throw new IllegalArgumentException("Unsupported ThreadMode " + subscription.getSubscribeMethod().getThreadMode());
        }
    }

    private void invoke(Subscription subscription) {
        int i = AnonymousClass1.$SwitchMap$com$xiaopeng$ota$sdk$eventbroker$ThreadMode[subscription.getSubscribeMethod().getThreadMode().ordinal()];
        if (i == 1) {
            this.threadInvoker.invoke(subscription);
        } else if (i == 2) {
            this.mainThreadInvoker.invoke(subscription);
        } else {
            throw new IllegalArgumentException("Unsupported ThreadMode " + subscription.getSubscribeMethod().getThreadMode());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class EventEnvelope {
        final Event event;
        final PostFuture future;
        final Object subscriber;

        EventEnvelope(Object obj, Event event, PostFuture postFuture) {
            this.subscriber = obj;
            this.event = event;
            this.future = postFuture;
        }
    }

    /* loaded from: classes2.dex */
    private static class TimerQueueComparator implements Comparator<Subscription> {
        private TimerQueueComparator() {
        }

        /* synthetic */ TimerQueueComparator(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // java.util.Comparator
        public int compare(Subscription subscription, Subscription subscription2) {
            return (int) ((subscription.getActiveTime() + subscription.getSubscribeTimeoutMethod().getTimeout()) - (subscription2.getActiveTime() + subscription2.getSubscribeTimeoutMethod().getTimeout()));
        }
    }

    /* loaded from: classes2.dex */
    private class DefaultEventLoop extends Thread {
        DefaultEventLoop() {
            super("EventBroker-DefaultEventLoop");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (!EventBroker.this.disposed) {
                try {
                    EventEnvelope eventEnvelope = (EventEnvelope) EventBroker.this.eventQueue.poll(1L, TimeUnit.SECONDS);
                    if (eventEnvelope != null) {
                        EventBroker.this.dispatch(eventEnvelope);
                    } else {
                        long currentTimeMillis = System.currentTimeMillis();
                        while (true) {
                            Subscription subscription = (Subscription) EventBroker.this.timeoutMethodsQueue.peek();
                            if (subscription == null) {
                                break;
                            } else if (!subscription.isActive()) {
                                EventBroker.this.timeoutMethodsQueue.poll();
                            } else if (subscription.getActiveTime() + subscription.getSubscribeTimeoutMethod().getTimeout() <= currentTimeMillis) {
                                EventBroker.this.timeoutMethodsQueue.poll();
                                EventBroker.this.dispatchTimeoutEvent(subscription);
                                subscription.updateActiveTime(currentTimeMillis);
                                EventBroker.this.timeoutMethodsQueue.add(subscription);
                            }
                        }
                    }
                } catch (Exception e) {
                    Log.e(EventBroker.TAG, "Event loop unexpected exception", e);
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }
}
