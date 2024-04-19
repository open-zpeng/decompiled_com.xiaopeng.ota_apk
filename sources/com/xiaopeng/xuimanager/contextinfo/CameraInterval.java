package com.xiaopeng.xuimanager.contextinfo;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* loaded from: classes2.dex */
public class CameraInterval implements Parcelable {
    public static final Parcelable.Creator<CameraInterval> CREATOR = new Parcelable.Creator<CameraInterval>() { // from class: com.xiaopeng.xuimanager.contextinfo.CameraInterval.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraInterval createFromParcel(Parcel parcel) {
            return new CameraInterval(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraInterval[] newArray(int i) {
            return new CameraInterval[i];
        }
    };
    private List<CameraInfoInterval> cameraInfoInterval;
    private boolean isIntervalCameraShow;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CameraInterval() {
    }

    protected CameraInterval(Parcel parcel) {
        this.isIntervalCameraShow = parcel.readByte() != 0;
        this.cameraInfoInterval = parcel.createTypedArrayList(CameraInfoInterval.CREATOR);
    }

    public boolean isIntervalCameraShow() {
        return this.isIntervalCameraShow;
    }

    public void setIntervalCameraShow(boolean z) {
        this.isIntervalCameraShow = z;
    }

    public List<CameraInfoInterval> getIntervalCameraInfo() {
        return this.cameraInfoInterval;
    }

    public void setIntervalCameraInfo(List<CameraInfoInterval> list) {
        this.cameraInfoInterval = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isIntervalCameraShow ? (byte) 1 : (byte) 0);
        parcel.writeTypedList(this.cameraInfoInterval);
    }
}
