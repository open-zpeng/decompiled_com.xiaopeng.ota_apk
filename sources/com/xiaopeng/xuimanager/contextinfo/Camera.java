package com.xiaopeng.xuimanager.contextinfo;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* loaded from: classes2.dex */
public class Camera implements Parcelable {
    public static final Parcelable.Creator<Camera> CREATOR = new Parcelable.Creator<Camera>() { // from class: com.xiaopeng.xuimanager.contextinfo.Camera.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Camera createFromParcel(Parcel parcel) {
            return new Camera(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Camera[] newArray(int i) {
            return new Camera[i];
        }
    };
    private List<CameraInfo> cameraInfo;
    private boolean isCameraShow;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Camera() {
    }

    protected Camera(Parcel parcel) {
        this.isCameraShow = parcel.readByte() != 0;
        this.cameraInfo = parcel.createTypedArrayList(CameraInfo.CREATOR);
    }

    public boolean getIsCameraShow() {
        return this.isCameraShow;
    }

    public void setIsCameraShow(boolean z) {
        this.isCameraShow = z;
    }

    public List<CameraInfo> getCameraInfo() {
        return this.cameraInfo;
    }

    public void setCameraInfo(List<CameraInfo> list) {
        this.cameraInfo = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isCameraShow ? (byte) 1 : (byte) 0);
        parcel.writeTypedList(this.cameraInfo);
    }
}
