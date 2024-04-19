package com.xiaopeng.ota.bean;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class ActivityEvent<T> implements Serializable {
    private Action action;
    private Class clazz;
    private T data;

    public Class getClazz() {
        return this.clazz;
    }

    public void setClazz(Class cls) {
        this.clazz = cls;
    }

    public Action getAction() {
        return this.action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }
}
