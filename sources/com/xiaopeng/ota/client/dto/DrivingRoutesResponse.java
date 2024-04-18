package com.xiaopeng.ota.client.dto;

import com.xiaopeng.ota.remind.DrivingRoute;
/* loaded from: classes2.dex */
public class DrivingRoutesResponse extends BaseResponse {
    private DrivingRoute data = new DrivingRoute();

    public DrivingRoute getData() {
        return this.data;
    }

    public void setData(DrivingRoute drivingRoute) {
        this.data = drivingRoute;
    }
}
