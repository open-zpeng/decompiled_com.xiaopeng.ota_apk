package com.xiaopeng.xuimanager.contextinfo;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class Lane implements Parcelable {
    public static final Parcelable.Creator<Lane> CREATOR = new Parcelable.Creator<Lane>() { // from class: com.xiaopeng.xuimanager.contextinfo.Lane.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Lane createFromParcel(Parcel parcel) {
            return new Lane(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Lane[] newArray(int i) {
            return new Lane[i];
        }
    };
    private int[] backLane;
    private int[] frontLane;
    private boolean isLaneShow;
    private int laneType;
    private int[] tollGateInfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isLaneShow() {
        return this.isLaneShow;
    }

    public void setLaneShow(boolean z) {
        this.isLaneShow = z;
    }

    public int getLaneType() {
        return this.laneType;
    }

    public void setLaneType(int i) {
        this.laneType = i;
    }

    public int[] getBackLane() {
        return this.backLane;
    }

    public void setBackLane(int[] iArr) {
        this.backLane = iArr;
    }

    public int[] getFrontLane() {
        return this.frontLane;
    }

    public void setFrontLane(int[] iArr) {
        this.frontLane = iArr;
    }

    public int[] getTollGateInfo() {
        return this.tollGateInfo;
    }

    public void setTollGateInfo(int[] iArr) {
        this.tollGateInfo = iArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isLaneShow ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.laneType);
        parcel.writeIntArray(this.backLane);
        parcel.writeIntArray(this.frontLane);
        parcel.writeIntArray(this.tollGateInfo);
    }

    public Lane() {
    }

    protected Lane(Parcel parcel) {
        this.isLaneShow = parcel.readByte() != 0;
        this.laneType = parcel.readInt();
        this.backLane = parcel.createIntArray();
        this.frontLane = parcel.createIntArray();
        this.tollGateInfo = parcel.createIntArray();
    }
}
