package com.xiaopeng.xuimanager.contextinfo;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class RemainInfo implements Parcelable {
    public static final Parcelable.Creator<RemainInfo> CREATOR = new Parcelable.Creator<RemainInfo>() { // from class: com.xiaopeng.xuimanager.contextinfo.RemainInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RemainInfo createFromParcel(Parcel parcel) {
            return new RemainInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RemainInfo[] newArray(int i) {
            return new RemainInfo[i];
        }
    };
    private int carRemainDist;
    private String carRemainDistDisplay;
    private int carRemainDistUnitDisplay;
    private int distType;
    private int routeRemainDist;
    private String routeRemainDistDisplay;
    private int routeRemainDistUnitDisplay;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RemainInfo() {
    }

    protected RemainInfo(Parcel parcel) {
        this.routeRemainDist = parcel.readInt();
        this.carRemainDist = parcel.readInt();
        this.distType = parcel.readInt();
        this.routeRemainDistDisplay = parcel.readString();
        this.routeRemainDistUnitDisplay = parcel.readInt();
        this.carRemainDistDisplay = parcel.readString();
        this.carRemainDistUnitDisplay = parcel.readInt();
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

    public String getCarRemainDistDisplay() {
        return this.carRemainDistDisplay;
    }

    public void setCarRemainDistDisplay(String str) {
        this.carRemainDistDisplay = str;
    }

    public int getCarRemainDistUnitDisplay() {
        return this.carRemainDistUnitDisplay;
    }

    public void setCarRemainDistUnitDisplay(int i) {
        this.carRemainDistUnitDisplay = i;
    }

    public int getDistType() {
        return this.distType;
    }

    public void setDistType(int i) {
        this.distType = i;
    }

    public int getRouteRemainDist() {
        return this.routeRemainDist;
    }

    public void setRouteRemainDist(int i) {
        this.routeRemainDist = i;
    }

    public int getCarRemainDist() {
        return this.carRemainDist;
    }

    public void setCarRemainDist(int i) {
        this.carRemainDist = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.routeRemainDist);
        parcel.writeInt(this.carRemainDist);
        parcel.writeInt(this.distType);
        parcel.writeString(this.routeRemainDistDisplay);
        parcel.writeInt(this.routeRemainDistUnitDisplay);
        parcel.writeString(this.carRemainDistDisplay);
        parcel.writeInt(this.carRemainDistUnitDisplay);
    }
}
