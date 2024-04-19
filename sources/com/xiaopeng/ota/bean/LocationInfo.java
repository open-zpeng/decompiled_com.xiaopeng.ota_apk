package com.xiaopeng.ota.bean;
/* loaded from: classes2.dex */
public class LocationInfo {
    private String cityName;
    private int formway;
    private int linkType;
    private double matchPosLat;
    private double matchPosLon;
    private int roadClass;

    public int getRoadClass() {
        return this.roadClass;
    }

    public void setRoadClass(int i) {
        this.roadClass = i;
    }

    public int getLinkType() {
        return this.linkType;
    }

    public void setLinkType(int i) {
        this.linkType = i;
    }

    public int getFormway() {
        return this.formway;
    }

    public void setFormway(int i) {
        this.formway = i;
    }

    public double getMatchPosLat() {
        return this.matchPosLat;
    }

    public void setMatchPosLat(double d) {
        this.matchPosLat = d;
    }

    public double getMatchPosLon() {
        return this.matchPosLon;
    }

    public void setMatchPosLon(double d) {
        this.matchPosLon = d;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }
}
