package com.xiaopeng.ota.remind;
/* loaded from: classes2.dex */
public class VehicleLocation {
    private String avgOffTime;
    private String avgOnTime;
    private Long avgUseTime;
    private String lat;
    private String lng;
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

    public String getAvgOffTime() {
        return this.avgOffTime;
    }

    public void setAvgOffTime(String str) {
        this.avgOffTime = str;
    }

    public Long getAvgUseTime() {
        return this.avgUseTime;
    }

    public void setAvgUseTime(Long l) {
        this.avgUseTime = l;
    }
}
