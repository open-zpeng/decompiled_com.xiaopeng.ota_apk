package com.xiaopeng.ota.sdk.eventbroker;

import android.util.Log;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public class ThreadInvokerGroup implements ThreadInvoker {
    private static final String TAG = "ThreadInvokerGroup";
    private DefaultThreadInvoker defaultInvoker;
    private Map<String, DefaultThreadInvoker> invokers = new ConcurrentHashMap();

    public ThreadInvokerGroup(DefaultThreadInvoker defaultThreadInvoker) {
        this.defaultInvoker = defaultThreadInvoker == null ? new DefaultThreadInvoker(1) : defaultThreadInvoker;
    }

    public ThreadInvokerGroup add(String str, DefaultThreadInvoker defaultThreadInvoker) {
        this.invokers.put(str, defaultThreadInvoker);
        return this;
    }

    @Override // com.xiaopeng.ota.sdk.eventbroker.ThreadInvoker
    public void invoke(Subscription subscription, Object obj, PostFuture postFuture) {
        DefaultThreadInvoker defaultThreadInvoker = this.defaultInvoker;
        String threadGroup = subscription.getSubscribeMethod().getThreadGroup();
        if (threadGroup != null && !threadGroup.isEmpty()) {
            defaultThreadInvoker = this.invokers.get(threadGroup);
        }
        if (defaultThreadInvoker == null) {
            Object[] objArr = new Object[4];
            objArr[0] = threadGroup;
            objArr[1] = subscription.getSubscriber().getClass().getName();
            objArr[2] = subscription.getSubscribeMethod().getMethod().getName();
            objArr[3] = obj == null ? "" : obj.getClass().getName();
            Log.w(TAG, String.format("Thread invoker group %s not found and invoker by default %s:%s(%s)", objArr));
            defaultThreadInvoker = this.defaultInvoker;
        }
        if (obj != null) {
            defaultThreadInvoker.invoke(subscription, obj, postFuture);
        } else {
            defaultThreadInvoker.invoke(subscription);
        }
    }

    @Override // com.xiaopeng.ota.sdk.eventbroker.ThreadInvoker
    public void invoke(Subscription subscription) {
        invoke(subscription, null, null);
    }
}
