package com.xiaopeng.ota.client.dto;
/* loaded from: classes2.dex */
public class VersionResponse extends BaseResponse {
    private VehicleVersion data;

    public VehicleVersion getData() {
        return this.data;
    }

    public void setData(VehicleVersion vehicleVersion) {
        this.data = vehicleVersion;
    }
}
