package com.xiaopeng.ota.ipc;
/* loaded from: classes2.dex */
public class IpcRouterEvent {
    private String bundle;
    private int id;

    public IpcRouterEvent() {
    }

    public IpcRouterEvent(int i, String str) {
        this.id = i;
        this.bundle = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getBundle() {
        return this.bundle;
    }

    public void setBundle(String str) {
        this.bundle = str;
    }
}
