package com.xiaopeng.xuimanager.contextinfo;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class Maneuver implements Parcelable {
    public static final Parcelable.Creator<Maneuver> CREATOR = new Parcelable.Creator<Maneuver>() { // from class: com.xiaopeng.xuimanager.contextinfo.Maneuver.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Maneuver createFromParcel(Parcel parcel) {
            return new Maneuver(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Maneuver[] newArray(int i) {
            return new Maneuver[i];
        }
    };
    private String maneuverData;
    private long maneuverID;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Maneuver() {
    }

    protected Maneuver(Parcel parcel) {
        this.maneuverID = parcel.readLong();
        this.maneuverData = parcel.readString();
    }

    public long getManeuverID() {
        return this.maneuverID;
    }

    public void setManeuverID(long j) {
        this.maneuverID = j;
    }

    public String getManeuverData() {
        return this.maneuverData;
    }

    public void setManeuverData(String str) {
        this.maneuverData = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.maneuverID);
        parcel.writeString(this.maneuverData);
    }
}
