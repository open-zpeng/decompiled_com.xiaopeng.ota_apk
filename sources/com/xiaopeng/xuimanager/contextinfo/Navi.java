package com.xiaopeng.xuimanager.contextinfo;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class Navi implements Parcelable {
    public static final Parcelable.Creator<Navi> CREATOR = new Parcelable.Creator<Navi>() { // from class: com.xiaopeng.xuimanager.contextinfo.Navi.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Navi createFromParcel(Parcel parcel) {
            return new Navi(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Navi[] newArray(int i) {
            return new Navi[i];
        }
    };
    private String curRouteName;
    private String exitInfo;
    private boolean isShowExitInfo;
    private boolean isTightTurnShow;
    private int nextManeuverDist;
    private String nextManeuverDistDisplay;
    private int nextManeuverDistUnitDisplay;
    private long nextManeuverID;
    private String nextRouteName;
    private double routeRemainDist;
    private String routeRemainDistDisplay;
    private int routeRemainDistUnitDisplay;
    private double routeRemainTime;
    private int segmentRemainDist;
    private String segmentRemainDistDisplay;
    private int segmentRemainDistUnitDisplay;
    private int segmentRemainProgress;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Navi() {
    }

    protected Navi(Parcel parcel) {
        this.curRouteName = parcel.readString();
        this.nextRouteName = parcel.readString();
        this.isShowExitInfo = parcel.readByte() != 0;
        this.exitInfo = parcel.readString();
        this.segmentRemainDist = parcel.readInt();
        this.segmentRemainProgress = parcel.readInt();
        this.routeRemainDist = parcel.readDouble();
        this.routeRemainTime = parcel.readDouble();
        this.isTightTurnShow = parcel.readByte() != 0;
        this.nextManeuverID = parcel.readLong();
        this.nextManeuverDist = parcel.readInt();
        this.segmentRemainDistDisplay = parcel.readString();
        this.segmentRemainDistUnitDisplay = parcel.readInt();
        this.routeRemainDistDisplay = parcel.readString();
        this.routeRemainDistUnitDisplay = parcel.readInt();
        this.nextManeuverDistDisplay = parcel.readString();
        this.nextManeuverDistUnitDisplay = parcel.readInt();
    }

    public boolean isShowExitInfo() {
        return this.isShowExitInfo;
    }

    public void setShowExitInfo(boolean z) {
        this.isShowExitInfo = z;
    }

    public boolean isTightTurnShow() {
        return this.isTightTurnShow;
    }

    public void setTightTurnShow(boolean z) {
        this.isTightTurnShow = z;
    }

    public String getSegmentRemainDistDisplay() {
        return this.segmentRemainDistDisplay;
    }

    public void setSegmentRemainDistDisplay(String str) {
        this.segmentRemainDistDisplay = str;
    }

    public int getSegmentRemainDistUnitDisplay() {
        return this.segmentRemainDistUnitDisplay;
    }

    public void setSegmentRemainDistUnitDisplay(int i) {
        this.segmentRemainDistUnitDisplay = i;
    }

    public String getRouteRemainDistDisplay() {
        return this.routeRemainDistDisplay;
    }

    public void setRouteRemainDistDisplay(String str) {
        this.routeRemainDistDisplay = str;
    }

    public int getRouteRemainDistUnitDisplay() {
        return this.routeRemainDistUnitDisplay;
    }

    public void setRouteRemainDistUnitDisplay(int i) {
        this.routeRemainDistUnitDisplay = i;
    }

    public String getNextManeuverDistDisplay() {
        return this.nextManeuverDistDisplay;
    }

    public void setNextManeuverDistDisplay(String str) {
        this.nextManeuverDistDisplay = str;
    }

    public int getNextManeuverDistUnitDisplay() {
        return this.nextManeuverDistUnitDisplay;
    }

    public void setNextManeuverDistUnitDisplay(int i) {
        this.nextManeuverDistUnitDisplay = i;
    }

    public int getSegmentRemainProgress() {
        return this.segmentRemainProgress;
    }

    public void setSegmentRemainProgress(int i) {
        this.segmentRemainProgress = i;
    }

    public int getSegmentRemainDist() {
        return this.segmentRemainDist;
    }

    public void setSegmentRemainDist(int i) {
        this.segmentRemainDist = i;
    }

    public double getRouteRemainDist() {
        return this.routeRemainDist;
    }

    public void setRouteRemainDist(double d) {
        this.routeRemainDist = d;
    }

    public double getRouteRemainTime() {
        return this.routeRemainTime;
    }

    public void setRouteRemainTime(double d) {
        this.routeRemainTime = d;
    }

    public long getNextManeuverID() {
        return this.nextManeuverID;
    }

    public void setNextManeuverID(long j) {
        this.nextManeuverID = j;
    }

    public int getNextManeuverDist() {
        return this.nextManeuverDist;
    }

    public void setNextManeuverDist(int i) {
        this.nextManeuverDist = i;
    }

    public boolean getIsShowExitInfo() {
        return this.isShowExitInfo;
    }

    public void setIsShowExitInfo(boolean z) {
        this.isShowExitInfo = z;
    }

    public String getExitInfo() {
        return this.exitInfo;
    }

    public void setExitInfo(String str) {
        this.exitInfo = str;
    }

    public String getNextRouteName() {
        return this.nextRouteName;
    }

    public void setNextRouteName(String str) {
        this.nextRouteName = str;
    }

    public String getCurRouteName() {
        return this.curRouteName;
    }

    public void setCurRouteName(String str) {
        this.curRouteName = str;
    }

    public boolean getIsTightTurnShow() {
        return this.isTightTurnShow;
    }

    public void setIsTightTurnShow(boolean z) {
        this.isTightTurnShow = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.curRouteName);
        parcel.writeString(this.nextRouteName);
        parcel.writeByte(this.isShowExitInfo ? (byte) 1 : (byte) 0);
        parcel.writeString(this.exitInfo);
        parcel.writeInt(this.segmentRemainDist);
        parcel.writeInt(this.segmentRemainProgress);
        parcel.writeDouble(this.routeRemainDist);
        parcel.writeDouble(this.routeRemainTime);
        parcel.writeByte(this.isTightTurnShow ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.nextManeuverID);
        parcel.writeInt(this.nextManeuverDist);
        parcel.writeString(this.segmentRemainDistDisplay);
        parcel.writeInt(this.segmentRemainDistUnitDisplay);
        parcel.writeString(this.routeRemainDistDisplay);
        parcel.writeInt(this.routeRemainDistUnitDisplay);
        parcel.writeString(this.nextManeuverDistDisplay);
        parcel.writeInt(this.nextManeuverDistUnitDisplay);
    }
}
