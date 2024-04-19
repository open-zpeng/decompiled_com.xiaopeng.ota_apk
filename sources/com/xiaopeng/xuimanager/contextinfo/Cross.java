package com.xiaopeng.xuimanager.contextinfo;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class Cross implements Parcelable {
    public static final Parcelable.Creator<Cross> CREATOR = new Parcelable.Creator<Cross>() { // from class: com.xiaopeng.xuimanager.contextinfo.Cross.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Cross createFromParcel(Parcel parcel) {
            return new Cross(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Cross[] newArray(int i) {
            return new Cross[i];
        }
    };
    private String arrowDataBuffer;
    private int crossType;
    private String dataBuffer;
    private boolean isCrossShow;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isCrossShow() {
        return this.isCrossShow;
    }

    public void setCrossShow(boolean z) {
        this.isCrossShow = z;
    }

    public int getCrossType() {
        return this.crossType;
    }

    public void setCrossType(int i) {
        this.crossType = i;
    }

    public String getDataBuffer() {
        return this.dataBuffer;
    }

    public void setDataBuffer(String str) {
        this.dataBuffer = str;
    }

    public String getArrowDataBuffer() {
        return this.arrowDataBuffer;
    }

    public void setArrowDataBuffer(String str) {
        this.arrowDataBuffer = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isCrossShow ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.crossType);
        parcel.writeString(this.dataBuffer);
        parcel.writeString(this.arrowDataBuffer);
    }

    public Cross() {
    }

    protected Cross(Parcel parcel) {
        this.isCrossShow = parcel.readByte() != 0;
        this.crossType = parcel.readInt();
        this.dataBuffer = parcel.readString();
        this.arrowDataBuffer = parcel.readString();
    }
}
