package com.xiaopeng.xuimanager.contextinfo;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class SapaInfo implements Parcelable {
    public static final Parcelable.Creator<SapaInfo> CREATOR = new Parcelable.Creator<SapaInfo>() { // from class: com.xiaopeng.xuimanager.contextinfo.SapaInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SapaInfo createFromParcel(Parcel parcel) {
            return new SapaInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SapaInfo[] newArray(int i) {
            return new SapaInfo[i];
        }
    };
    private String name;
    private int remainDist;
    private String remainDistDisplay;
    private int remainDistUnitDisplay;
    private long sapaDetail;
    private int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SapaInfo() {
    }

    protected SapaInfo(Parcel parcel) {
        this.name = parcel.readString();
        this.sapaDetail = parcel.readLong();
        this.type = parcel.readInt();
        this.remainDist = parcel.readInt();
        this.remainDistDisplay = parcel.readString();
        this.remainDistUnitDisplay = parcel.readInt();
    }

    public String getRemainDistDisplay() {
        return this.remainDistDisplay;
    }

    public void setRemainDistDisplay(String str) {
        this.remainDistDisplay = str;
    }

    public int getRemainDistUnitDisplay() {
        return this.remainDistUnitDisplay;
    }

    public void setRemainDistUnitDisplay(int i) {
        this.remainDistUnitDisplay = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public long getSapaDetail() {
        return this.sapaDetail;
    }

    public void setSapaDetail(long j) {
        this.sapaDetail = j;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getRemainDist() {
        return this.remainDist;
    }

    public void setRemainDist(int i) {
        this.remainDist = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeLong(this.sapaDetail);
        parcel.writeInt(this.type);
        parcel.writeInt(this.remainDist);
        parcel.writeString(this.remainDistDisplay);
        parcel.writeInt(this.remainDistUnitDisplay);
    }
}
