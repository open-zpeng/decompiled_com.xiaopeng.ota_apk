package com.xiaopeng.ota.client.dto;

import com.xiaopeng.ota.version.os.OsVersion;
/* loaded from: classes2.dex */
public class OSVersionResponse extends BaseResponse {
    private OsVersion data;

    public OsVersion getData() {
        return this.data;
    }

    public void setData(OsVersion osVersion) {
        this.data = osVersion;
    }
}
