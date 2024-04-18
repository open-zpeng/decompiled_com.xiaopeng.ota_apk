package com.xiaopeng.ota.remind;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class DrivingRoutePoint implements Serializable {
    private static final long serialVersionUID = 9005412781420653270L;
    private String avgOnTime;
    private String lat;
    private String lng;
    private String maxOnTime;
    private Integer minOnDistance;
    private String minOnTime;
    private Integer minUseTime;
    private Integer radius;

    public String getLng() {
        return this.lng;
    }

    public float getLngFloat() {
        return Float.valueOf(this.lng).floatValue();
    }

    public void setLng(String str) {
        this.lng = str;
    }

    public String getLat() {
        return this.lat;
    }

    public float getLatFloat() {
        return Float.valueOf(this.lat).floatValue();
    }

    public void setLat(String str) {
        this.lat = str;
    }

    public Integer getRadius() {
        return this.radius;
    }

    public void setRadius(Integer num) {
        this.radius = num;
    }

    public String getAvgOnTime() {
        return this.avgOnTime;
    }

    public void setAvgOnTime(String str) {
        this.avgOnTime = str;
    }

    public String getMinOnTime() {
        return this.minOnTime;
    }

    public void setMinOnTime(String str) {
        this.minOnTime = str;
    }

    public String getMaxOnTime() {
        return this.maxOnTime;
    }

    public void setMaxOnTime(String str) {
        this.maxOnTime = str;
    }

    public Integer getMinUseTime() {
        return this.minUseTime;
    }

    public void setMinUseTime(Integer num) {
        this.minUseTime = num;
    }

    public Integer getMinOnDistance() {
        return this.minOnDistance;
    }

    public float getMinOnDistanceKm() {
        Integer num = this.minOnDistance;
        if (num != null) {
            return num.intValue() / 1000.0f;
        }
        return 0.0f;
    }

    public void setMinOnDistance(Integer num) {
        this.minOnDistance = num;
    }

    public String toString() {
        return "DrivingRoutePoint{lng='" + this.lng + "', lat='" + this.lat + "', radius=" + this.radius + ", avgOnTime='" + this.avgOnTime + "', minOnTime='" + this.minOnTime + "', maxOnTime='" + this.maxOnTime + "', minUseTime=" + this.minUseTime + ", minOnDistance=" + this.minOnDistance + '}';
    }
}
