package com.xiaopeng.ota.remind;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class DrivingRoute {
    public static final String FIELD_DATA = "data";
    public static final String FIELD_ENVIRONMENT = "environment";
    public static final String FIELD_VERSION = "version";
    public static final String TABLE_NAME = "driving_routes";
    public static final int VERSION = 0;
    private Long createTime;
    private Integer id;
    private Long updateTime;
    private VehicleRoute lastRoute = new VehicleRoute();
    private List<VehicleLocation> constantLocation = new ArrayList();

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public VehicleRoute getLastRoute() {
        return this.lastRoute;
    }

    public void setLastRoute(VehicleRoute vehicleRoute) {
        this.lastRoute = vehicleRoute;
    }

    public List<VehicleLocation> getConstantLocation() {
        return this.constantLocation;
    }

    public void setConstantLocation(List<VehicleLocation> list) {
        this.constantLocation = list;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Long l) {
        this.createTime = l;
    }

    public Long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Long l) {
        this.updateTime = l;
    }
}
