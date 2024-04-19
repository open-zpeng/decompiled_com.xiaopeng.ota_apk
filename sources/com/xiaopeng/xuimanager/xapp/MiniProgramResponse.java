package com.xiaopeng.xuimanager.xapp;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class MiniProgramResponse implements Parcelable {
    public static final Parcelable.Creator<MiniProgramResponse> CREATOR = new Parcelable.Creator<MiniProgramResponse>() { // from class: com.xiaopeng.xuimanager.xapp.MiniProgramResponse.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MiniProgramResponse createFromParcel(Parcel parcel) {
            return new MiniProgramResponse(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MiniProgramResponse[] newArray(int i) {
            return new MiniProgramResponse[i];
        }
    };
    private int code;
    private boolean isLogin;
    private List<MiniProgramGroup> mMiniProgramGroups;
    private Map mParams;
    private String message;
    private String miniAppId;
    private String userAvatar;
    private String userNick;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MiniProgramResponse() {
        this.miniAppId = "";
        this.isLogin = false;
        this.userAvatar = "";
        this.userNick = "";
        this.message = "";
        this.mParams = new HashMap();
        this.mMiniProgramGroups = new ArrayList();
    }

    protected MiniProgramResponse(Parcel parcel) {
        this.miniAppId = "";
        this.isLogin = false;
        this.userAvatar = "";
        this.userNick = "";
        this.message = "";
        this.mParams = new HashMap();
        this.mMiniProgramGroups = new ArrayList();
        this.code = parcel.readInt();
        this.miniAppId = parcel.readStringNoHelper();
        this.isLogin = parcel.readByte() != 0;
        this.userAvatar = parcel.readStringNoHelper();
        this.userNick = parcel.readStringNoHelper();
        this.message = parcel.readStringNoHelper();
        this.mMiniProgramGroups = parcel.createTypedArrayList(MiniProgramGroup.CREATOR);
        parcel.readMap(this.mParams, Map.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.code);
        parcel.writeStringNoHelper(this.miniAppId);
        parcel.writeByte(this.isLogin ? (byte) 1 : (byte) 0);
        parcel.writeStringNoHelper(this.userAvatar);
        parcel.writeStringNoHelper(this.userNick);
        parcel.writeStringNoHelper(this.message);
        parcel.writeTypedList(this.mMiniProgramGroups);
        parcel.writeMap(this.mParams);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMiniAppId() {
        return this.miniAppId;
    }

    public void setMiniAppId(String str) {
        this.miniAppId = str;
    }

    public boolean isLogin() {
        return this.isLogin;
    }

    public void setLogin(boolean z) {
        this.isLogin = z;
    }

    public String getUserAvatar() {
        return this.userAvatar;
    }

    public void setUserAvatar(String str) {
        this.userAvatar = str;
    }

    public String getUserNick() {
        return this.userNick;
    }

    public void setUserNick(String str) {
        this.userNick = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public Map getParams() {
        return this.mParams;
    }

    public void setParams(Map map) {
        this.mParams = map;
    }

    public List<MiniProgramGroup> getMiniProgramGroups() {
        return this.mMiniProgramGroups;
    }

    public void setMiniProgramGroups(List<MiniProgramGroup> list) {
        this.mMiniProgramGroups.clear();
        this.mMiniProgramGroups.addAll(list);
    }

    public String toString() {
        return "MiniProgramResponse{code=" + this.code + ", miniAppId='" + this.miniAppId + "', isLogin=" + this.isLogin + ", userAvatar=" + this.userAvatar + ", userNick=" + this.userNick + ", message=" + this.message + '}';
    }
}
