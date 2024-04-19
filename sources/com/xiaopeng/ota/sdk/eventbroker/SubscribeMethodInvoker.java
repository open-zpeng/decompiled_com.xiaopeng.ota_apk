package com.xiaopeng.ota.sdk.eventbroker;

import android.util.Log;
/* loaded from: classes2.dex */
public class SubscribeMethodInvoker {
    private static final String TAG = "SubscribeMethodInvoker";

    public static void invoke(Subscription subscription, Object obj, PostFuture postFuture) {
        Object invoke;
        try {
            if (subscription.getContext() == null) {
                invoke = subscription.getSubscribeMethod().getMethod().invoke(subscription.getSubscriber(), obj);
            } else {
                invoke = subscription.getSubscribeMethod().getMethod().invoke(subscription.getSubscriber(), subscription.getContext(), obj);
            }
            postFuture.callback(subscription, invoke);
        } catch (Exception e) {
            Log.e(TAG, String.format("Unexpected exception %s:%s(%s)", subscription.getSubscriber().getClass().getName(), subscription.getSubscribeMethod().getMethod().getName(), obj.getClass().getName()), e);
            postFuture.callback(subscription, e);
        }
    }

    public static void invoke(Subscription subscription) {
        try {
            if (subscription.getContext() != null) {
                subscription.getSubscribeMethod().getMethod().invoke(subscription.getSubscriber(), subscription.getContext());
            } else {
                subscription.getSubscribeMethod().getMethod().invoke(subscription.getSubscriber(), new Object[0]);
            }
        } catch (Exception e) {
            Log.e(TAG, String.format("Unexpected exception %s:%s()", subscription.getSubscriber().getClass(), subscription.getSubscribeMethod().getMethod().getName()), e);
        }
    }
}
