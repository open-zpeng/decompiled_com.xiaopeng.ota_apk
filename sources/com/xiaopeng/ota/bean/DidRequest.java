package com.xiaopeng.ota.bean;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class DidRequest implements Serializable {
    private int address;
    private int did;

    public int getAddress() {
        return this.address;
    }

    public void setAddress(int i) {
        this.address = i;
    }

    public int getDid() {
        return this.did;
    }

    public void setDid(int i) {
        this.did = i;
    }
}
