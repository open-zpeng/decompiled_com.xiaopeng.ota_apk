package com.xiaopeng.ota.sdk.eventbroker;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes2.dex */
public class SubscribeMethod {
    private Class<?>[] eventTypes;
    private Method method;
    private int priority;
    private String threadGroup;
    private ThreadMode threadMode;

    public SubscribeMethod(Method method, ThreadMode threadMode, String str, Class<?>[] clsArr, int i) {
        this.method = method;
        this.threadMode = threadMode;
        this.threadGroup = str;
        this.eventTypes = clsArr;
        this.priority = i;
    }

    public Method getMethod() {
        return this.method;
    }

    public ThreadMode getThreadMode() {
        return this.threadMode;
    }

    public String getThreadGroup() {
        return this.threadGroup;
    }

    public Class<?>[] getEventTypes() {
        return this.eventTypes;
    }

    public int getPriority() {
        return this.priority;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SubscribeMethod subscribeMethod = (SubscribeMethod) obj;
        return this.priority == subscribeMethod.priority && Objects.equals(this.method, subscribeMethod.method) && this.threadMode == subscribeMethod.threadMode && Objects.equals(this.threadGroup, subscribeMethod.threadGroup) && Arrays.equals(this.eventTypes, subscribeMethod.eventTypes);
    }

    public int hashCode() {
        return (Objects.hash(this.method, this.threadMode, this.threadGroup, Integer.valueOf(this.priority)) * 31) + Arrays.hashCode(this.eventTypes);
    }
}
