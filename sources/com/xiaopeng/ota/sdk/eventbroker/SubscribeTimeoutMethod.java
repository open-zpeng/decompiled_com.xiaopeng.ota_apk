package com.xiaopeng.ota.sdk.eventbroker;

import java.lang.reflect.Method;
/* loaded from: classes2.dex */
public class SubscribeTimeoutMethod extends SubscribeMethod {
    private long timeout;

    public SubscribeTimeoutMethod(Method method, ThreadMode threadMode, String str, long j) {
        super(method, threadMode, str, new Class[0], 0);
        this.timeout = j;
    }

    public SubscribeTimeoutMethod(Method method, ThreadMode threadMode, String str, Class<?> cls, long j) {
        super(method, threadMode, str, new Class[]{cls}, 0);
        this.timeout = j;
    }

    public SubscribeTimeoutMethod(Method method, ThreadMode threadMode, String str, Class<?>[] clsArr, long j) {
        super(method, threadMode, str, clsArr, 0);
        this.timeout = j;
    }

    public long getTimeout() {
        return this.timeout;
    }
}
