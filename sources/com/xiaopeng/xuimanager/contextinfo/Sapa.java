package com.xiaopeng.xuimanager.contextinfo;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* loaded from: classes2.dex */
public class Sapa implements Parcelable {
    public static final Parcelable.Creator<Sapa> CREATOR = new Parcelable.Creator<Sapa>() { // from class: com.xiaopeng.xuimanager.contextinfo.Sapa.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Sapa createFromParcel(Parcel parcel) {
            return new Sapa(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Sapa[] newArray(int i) {
            return new Sapa[i];
        }
    };
    private boolean isSapaShow;
    private List<SapaInfo> sapaInfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Sapa() {
    }

    protected Sapa(Parcel parcel) {
        this.sapaInfo = parcel.createTypedArrayList(SapaInfo.CREATOR);
        this.isSapaShow = parcel.readByte() != 0;
    }

    public List<SapaInfo> getSapaInfo() {
        return this.sapaInfo;
    }

    public void setSapaInfo(List<SapaInfo> list) {
        this.sapaInfo = list;
    }

    public boolean getIsSapaShow() {
        return this.isSapaShow;
    }

    public void setIsSapaShow(boolean z) {
        this.isSapaShow = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.sapaInfo);
        parcel.writeByte(this.isSapaShow ? (byte) 1 : (byte) 0);
    }
}
