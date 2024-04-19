package com.xiaopeng.ota.sdk.eventbroker;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public class SubscribeMethodFinder {
    private static final Class<?>[] EMPTY_EVENT = {EmptyEvent.class};
    private static final Map<Class<?>, SubscribeMethodGroup> methodsCache = new ConcurrentHashMap();

    /* loaded from: classes2.dex */
    private static class EmptyEvent extends Event {
        private EmptyEvent() {
        }
    }

    public SubscribeMethodGroup getSubscribeMethodGroup(Object obj) {
        return findAllSubscribeMethods(obj);
    }

    private SubscribeMethodGroup findAllSubscribeMethods(Object obj) {
        Method[] methods;
        Class<?> cls = obj.getClass();
        SubscribeMethodGroup subscribeMethodGroup = methodsCache.get(cls);
        if (subscribeMethodGroup != null) {
            return subscribeMethodGroup;
        }
        SubscribeMethodGroup subscribeMethodGroup2 = new SubscribeMethodGroup();
        Class<? super Object> cls2 = cls;
        while (!cls2.equals(Object.class)) {
            try {
                methods = cls2.getDeclaredMethods();
                cls2 = cls2.getSuperclass();
            } catch (Exception unused) {
                methods = cls.getMethods();
                cls2 = Object.class;
            }
            for (Method method : methods) {
                if ((method.getModifiers() & 1) == 1) {
                    Subscribe subscribe = (Subscribe) method.getAnnotation(Subscribe.class);
                    if (subscribe != null) {
                        subscribeMethodGroup2.addSubscribeMethod(new SubscribeMethod(method, subscribe.threadMode(), subscribe.threadGroup(), subscribe.events(), subscribe.priority()));
                    }
                    SubscribeTimeout subscribeTimeout = (SubscribeTimeout) method.getAnnotation(SubscribeTimeout.class);
                    if (subscribeTimeout != null) {
                        subscribeMethodGroup2.addSubscribeTimeoutMethod(new SubscribeTimeoutMethod(method, subscribeTimeout.threadMode(), subscribeTimeout.threadGroup(), subscribeTimeout.events().length > 0 ? subscribeTimeout.events() : EMPTY_EVENT, subscribeTimeout.timeout()));
                    }
                }
            }
        }
        methodsCache.put(cls, subscribeMethodGroup2);
        return subscribeMethodGroup2;
    }
}
