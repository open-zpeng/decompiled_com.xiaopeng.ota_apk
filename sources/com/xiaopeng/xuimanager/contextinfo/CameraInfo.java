package com.xiaopeng.xuimanager.contextinfo;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class CameraInfo implements Parcelable {
    public static final Parcelable.Creator<CameraInfo> CREATOR = new Parcelable.Creator<CameraInfo>() { // from class: com.xiaopeng.xuimanager.contextinfo.CameraInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraInfo createFromParcel(Parcel parcel) {
            return new CameraInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraInfo[] newArray(int i) {
            return new CameraInfo[i];
        }
    };
    private int cameraDist;
    private String cameraDistDisplay;
    private int cameraDistUnitDisplay;
    private int cameraSpeed;
    private int cameraType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CameraInfo() {
    }

    protected CameraInfo(Parcel parcel) {
        this.cameraDist = parcel.readInt();
        this.cameraSpeed = parcel.readInt();
        this.cameraType = parcel.readInt();
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
        parcel.writeString(this.cameraDistDisplay);
        parcel.writeInt(this.cameraDistUnitDisplay);
    }
}
