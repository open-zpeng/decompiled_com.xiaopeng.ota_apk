package com.xiaopeng.xuimanager.contextinfo;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class CameraInfoInterval implements Parcelable {
    public static final Parcelable.Creator<CameraInfoInterval> CREATOR = new Parcelable.Creator<CameraInfoInterval>() { // from class: com.xiaopeng.xuimanager.contextinfo.CameraInfoInterval.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraInfoInterval createFromParcel(Parcel parcel) {
            return new CameraInfoInterval(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraInfoInterval[] newArray(int i) {
            return new CameraInfoInterval[i];
        }
    };
    private int averageSpeed;
    private int cameraDist;
    private String cameraDistDisplay;
    private int cameraDistUnitDisplay;
    private int cameraSpeed;
    private int cameraType;
    private int reasonableSpeedInRemainDist;
    private int remainDistance;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CameraInfoInterval() {
    }

    protected CameraInfoInterval(Parcel parcel) {
        this.cameraDist = parcel.readInt();
        this.cameraSpeed = parcel.readInt();
        this.cameraType = parcel.readInt();
        this.remainDistance = parcel.readInt();
        this.averageSpeed = parcel.readInt();
        this.reasonableSpeedInRemainDist = parcel.readInt();
        this.cameraDistDisplay = parcel.readString();
        this.cameraDistUnitDisplay = parcel.readInt();
    }

    public String getCameraDistDisplay() {
        return this.cameraDistDisplay;
    }

    public void setCameraDistDisplay(String str) {
        this.cameraDistDisplay = str;
    }

    public int getCameraDistUnitDisplay() {
        return this.cameraDistUnitDisplay;
    }

    public void setCameraDistUnitDisplay(int i) {
        this.cameraDistUnitDisplay = i;
    }

    public int getRemainDistance() {
        return this.remainDistance;
    }

    public void setRemainDistance(int i) {
        this.remainDistance = i;
    }

    public int getAverageSpeed() {
        return this.averageSpeed;
    }

    public void setAverageSpeed(int i) {
        this.averageSpeed = i;
    }

    public int getReasonableSpeedInRemainDist() {
        return this.reasonableSpeedInRemainDist;
    }

    public void setReasonableSpeedInRemainDist(int i) {
        this.reasonableSpeedInRemainDist = i;
    }

    public int getCameraDist() {
        return this.cameraDist;
    }

    public void setCameraDist(int i) {
        this.cameraDist = i;
    }

    public int getCameraSpeed() {
        return this.cameraSpeed;
    }

    public void setCameraSpeed(int i) {
        this.cameraSpeed = i;
    }

    public int getCameraType() {
        return this.cameraType;
    }

    public void setCameraType(int i) {
        this.cameraType = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.cameraDist);
        parcel.writeInt(this.cameraSpeed);
        parcel.writeInt(this.cameraType);
        parcel.writeInt(this.remainDistance);
        parcel.writeInt(this.averageSpeed);
        parcel.writeInt(this.reasonableSpeedInRemainDist);
        parcel.writeString(this.cameraDistDisplay);
        parcel.writeInt(this.cameraDistUnitDisplay);
    }
}
