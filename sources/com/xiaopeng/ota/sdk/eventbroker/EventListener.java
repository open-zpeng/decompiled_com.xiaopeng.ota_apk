package com.xiaopeng.ota.sdk.eventbroker;

import java.lang.reflect.Method;
/* loaded from: classes2.dex */
public interface EventListener {
    void onCompleted(Object obj, Method method, Object obj2);

    void onException(Object obj, Method method, Exception exc);
}
