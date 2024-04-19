package com.xiaopeng.xuimanager.xapp;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* loaded from: classes2.dex */
public class MiniProgramGroup implements Parcelable {
    public static final Parcelable.Creator<MiniProgramGroup> CREATOR = new Parcelable.Creator<MiniProgramGroup>() { // from class: com.xiaopeng.xuimanager.xapp.MiniProgramGroup.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MiniProgramGroup createFromParcel(Parcel parcel) {
            return new MiniProgramGroup(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MiniProgramGroup[] newArray(int i) {
            return new MiniProgramGroup[i];
        }
    };
    private int mContentType;
    private List<MiniProgramData> mData;
    private String mGroupName;
    public String mId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MiniProgramGroup() {
    }

    protected MiniProgramGroup(Parcel parcel) {
        this.mId = parcel.readStringNoHelper();
        this.mGroupName = parcel.readStringNoHelper();
        this.mContentType = parcel.readInt();
        this.mData = parcel.createTypedArrayList(MiniProgramData.CREATOR);
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public String getGroupName() {
        return this.mGroupName;
    }

    public void setGroupName(String str) {
        this.mGroupName = str;
    }

    public int getContentType() {
        return this.mContentType;
    }

    public void setContentType(int i) {
        this.mContentType = i;
    }

    public List<MiniProgramData> getData() {
        return this.mData;
    }

    public void setData(List<MiniProgramData> list) {
        this.mData = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringNoHelper(this.mId);
        parcel.writeStringNoHelper(this.mGroupName);
        parcel.writeInt(this.mContentType);
        parcel.writeTypedList(this.mData);
    }

    public String toString() {
        return "MiniProgramGroup{mGroupName=" + this.mGroupName + ", mContentType='" + this.mContentType + "', mId=" + this.mId + '}';
    }
}
