package com.xiaopeng.ota.remind;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class VehicleRoute {
    private String avgEndTime;
    private String avgStartTime;
    private Integer avgUseTime;
    private String maxEndTime;
    private String maxStartTime;
    private Integer maxUseTime;
    private String minEndTime;
    private String minStartTime;
    private Integer minUseTime;
    private List<DrivingRoutePoint> points = new ArrayList();

    public String getAvgStartTime() {
        return this.avgStartTime;
    }

    public void setAvgStartTime(String str) {
        this.avgStartTime = str;
    }

    public String getMaxStartTime() {
        return this.maxStartTime;
    }

    public void setMaxStartTime(String str) {
        this.maxStartTime = str;
    }

    public String getMinStartTime() {
        return this.minStartTime;
    }

    public void setMinStartTime(String str) {
        this.minStartTime = str;
    }

    public String getAvgEndTime() {
        return this.avgEndTime;
    }

    public void setAvgEndTime(String str) {
        this.avgEndTime = str;
    }

    public String getMaxEndTime() {
        return this.maxEndTime;
    }

    public void setMaxEndTime(String str) {
        this.maxEndTime = str;
    }

    public String getMinEndTime() {
        return this.minEndTime;
    }

    public void setMinEndTime(String str) {
        this.minEndTime = str;
    }

    public Integer getAvgUseTime() {
        return this.avgUseTime;
    }

    public void setAvgUseTime(Integer num) {
        this.avgUseTime = num;
    }

    public Integer getMaxUseTime() {
        return this.maxUseTime;
    }

    public void setMaxUseTime(Integer num) {
        this.maxUseTime = num;
    }

    public Integer getMinUseTime() {
        return this.minUseTime;
    }

    public void setMinUseTime(Integer num) {
        this.minUseTime = num;
    }

    public List<DrivingRoutePoint> getPoints() {
        return this.points;
    }

    public void setPoints(List<DrivingRoutePoint> list) {
        this.points = list;
    }

    public boolean isValid() {
        List<DrivingRoutePoint> list = this.points;
        return (list == null || list.isEmpty()) ? false : true;
    }
}
