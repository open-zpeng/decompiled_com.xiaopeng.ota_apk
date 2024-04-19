package com.xiaopeng.xuimanager.store.bean;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class ResourceBean implements Parcelable {
    public static final Parcelable.Creator<ResourceBean> CREATOR = new Parcelable.Creator<ResourceBean>() { // from class: com.xiaopeng.xuimanager.store.bean.ResourceBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResourceBean createFromParcel(Parcel parcel) {
            return new ResourceBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResourceBean[] newArray(int i) {
            return new ResourceBean[i];
        }
    };
    private String mCreateTime;
    private String mDes;
    private String mDownloadUrl;
    private String mExpandContent;
    private String mExpandInstalledContent;
    private String mPrice;
    private String mRscIcon;
    private String mRscId;
    private String mRscName;
    private int mSource;
    private int mStatus;
    private String mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ResourceBean() {
        this.mStatus = 1;
    }

    protected ResourceBean(Parcel parcel) {
        this.mStatus = 1;
        this.mDownloadUrl = parcel.readStringNoHelper();
        this.mRscId = parcel.readStringNoHelper();
        this.mRscName = parcel.readStringNoHelper();
        this.mRscIcon = parcel.readStringNoHelper();
        this.mExpandContent = parcel.readStringNoHelper();
        this.mDes = parcel.readStringNoHelper();
        this.mPrice = parcel.readStringNoHelper();
        this.mStatus = parcel.readInt();
        this.mCreateTime = parcel.readStringNoHelper();
        this.mType = parcel.readStringNoHelper();
        this.mExpandInstalledContent = parcel.readStringNoHelper();
        this.mSource = parcel.readInt();
    }

    public int getSource() {
        return this.mSource;
    }

    public void setSource(int i) {
        this.mSource = i;
    }

    public String getExpandInstalledContent() {
        return this.mExpandInstalledContent;
    }

    public void setExpandInstalledContent(String str) {
        this.mExpandInstalledContent = str;
    }

    public String getExpandContent() {
        return this.mExpandContent;
    }

    public void setExpandContent(String str) {
        this.mExpandContent = str;
    }

    public String getDes() {
        return this.mDes;
    }

    public void setDes(String str) {
        this.mDes = str;
    }

    public String getPrice() {
        return this.mPrice;
    }

    public void setPrice(String str) {
        this.mPrice = str;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringNoHelper(this.mDownloadUrl);
        parcel.writeStringNoHelper(this.mRscId);
        parcel.writeStringNoHelper(this.mRscName);
        parcel.writeStringNoHelper(this.mRscIcon);
        parcel.writeStringNoHelper(this.mExpandContent);
        parcel.writeStringNoHelper(this.mDes);
        parcel.writeStringNoHelper(this.mPrice);
        parcel.writeInt(this.mStatus);
        parcel.writeStringNoHelper(this.mCreateTime);
        parcel.writeStringNoHelper(this.mType);
        parcel.writeStringNoHelper(this.mExpandInstalledContent);
        parcel.writeInt(this.mSource);
    }

    public String getDownloadUrl() {
        return this.mDownloadUrl;
    }

    public void setDownloadUrl(String str) {
        this.mDownloadUrl = str;
    }

    public String getRscId() {
        return this.mRscId;
    }

    public void setRscId(String str) {
        this.mRscId = str;
    }

    public String getRscName() {
        return this.mRscName;
    }

    public void setRscName(String str) {
        this.mRscName = str;
    }

    public String getRscIcon() {
        return this.mRscIcon;
    }

    public void setRscIcon(String str) {
        this.mRscIcon = str;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int i) {
        this.mStatus = i;
    }

    public String getCreateTime() {
        return this.mCreateTime;
    }

    public void setCreateTime(String str) {
        this.mCreateTime = str;
    }

    public String toString() {
        return "ResourceBean{mDownloadUrl='" + this.mDownloadUrl + "', mRscId='" + this.mRscId + "', mRscName='" + this.mRscName + "', mRscIcon='" + this.mRscIcon + "', mStatus=" + this.mStatus + ", mExpandContent=" + this.mExpandContent + ", mExpandInstalledContent=" + this.mExpandInstalledContent + ", mCreateTime='" + this.mCreateTime + "'}";
    }
}
