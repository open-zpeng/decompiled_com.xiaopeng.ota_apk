package com.xiaopeng.xuimanager.contextinfo;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class Location implements Parcelable {
    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() { // from class: com.xiaopeng.xuimanager.contextinfo.Location.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Location createFromParcel(Parcel parcel) {
            return new Location(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Location[] newArray(int i) {
            return new Location[i];
        }
    };
    private int carDir;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getCarDir() {
        return this.carDir;
    }

    public void setCarDir(int i) {
        this.carDir = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.carDir);
    }

    public Location() {
    }

    protected Location(Parcel parcel) {
        this.carDir = parcel.readInt();
    }
}
