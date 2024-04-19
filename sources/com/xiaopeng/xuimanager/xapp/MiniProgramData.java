package com.xiaopeng.xuimanager.xapp;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class MiniProgramData implements Parcelable {
    public static final Parcelable.Creator<MiniProgramData> CREATOR = new Parcelable.Creator<MiniProgramData>() { // from class: com.xiaopeng.xuimanager.xapp.MiniProgramData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MiniProgramData createFromParcel(Parcel parcel) {
            return new MiniProgramData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MiniProgramData[] newArray(int i) {
            return new MiniProgramData[i];
        }
    };
    private String mAlipayVersion;
    private int mContentType;
    private String mIconName;
    private String mId;
    private String mMiniAppId;
    private String mName;
    private String mParams;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MiniProgramData() {
        this.mName = "";
        this.mId = "";
    }

    protected MiniProgramData(Parcel parcel) {
        this.mName = "";
        this.mId = "";
        this.mName = parcel.readStringNoHelper();
        this.mId = parcel.readStringNoHelper();
        this.mMiniAppId = parcel.readStringNoHelper();
        this.mAlipayVersion = parcel.readStringNoHelper();
        this.mIconName = parcel.readStringNoHelper();
        this.mParams = parcel.readStringNoHelper();
        this.mContentType = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringNoHelper(this.mName);
        parcel.writeStringNoHelper(this.mId);
        parcel.writeStringNoHelper(this.mMiniAppId);
        parcel.writeStringNoHelper(this.mAlipayVersion);
        parcel.writeStringNoHelper(this.mIconName);
        parcel.writeStringNoHelper(this.mParams);
        parcel.writeInt(this.mContentType);
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public String getMiniAppId() {
        return this.mMiniAppId;
    }

    public void setMiniAppId(String str) {
        this.mMiniAppId = str;
    }

    public String getAlipayVersion() {
        return this.mAlipayVersion;
    }

    public void setAlipayVersion(String str) {
        this.mAlipayVersion = str;
    }

    public String getIconName() {
        return this.mIconName;
    }

    public void setIconName(String str) {
        this.mIconName = str;
    }

    public String getParams() {
        return this.mParams;
    }

    public void setParams(String str) {
        this.mParams = str;
    }

    public int getContentType() {
        return this.mContentType;
    }

    public void setContentType(int i) {
        this.mContentType = i;
    }

    public String toString() {
        return "MiniProgramData{mId=" + this.mId + ", mName='" + this.mName + "', mMiniAppId=" + this.mMiniAppId + ", mAlipayVersion=" + this.mAlipayVersion + ", mIconName=" + this.mIconName + ", mContentType=" + this.mContentType + '}';
    }
}
