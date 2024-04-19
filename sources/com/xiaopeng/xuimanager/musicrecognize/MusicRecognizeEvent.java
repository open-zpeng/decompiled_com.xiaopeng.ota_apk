package com.xiaopeng.xuimanager.musicrecognize;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class MusicRecognizeEvent implements Parcelable {
    public static final Parcelable.Creator<MusicRecognizeEvent> CREATOR = new Parcelable.Creator<MusicRecognizeEvent>() { // from class: com.xiaopeng.xuimanager.musicrecognize.MusicRecognizeEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicRecognizeEvent createFromParcel(Parcel parcel) {
            return new MusicRecognizeEvent(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicRecognizeEvent[] newArray(int i) {
            return new MusicRecognizeEvent[i];
        }
    };
    private String mAlbumCover;
    private String mAlbumName;
    private String mScore;
    private String mSinger;
    private String mSongName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MusicRecognizeEvent() {
    }

    public MusicRecognizeEvent(String str, String str2, String str3, String str4, String str5) {
        this.mSongName = str;
        this.mAlbumName = str2;
        this.mAlbumCover = str3;
        this.mSinger = str4;
        this.mScore = str5;
    }

    protected MusicRecognizeEvent(Parcel parcel) {
        this.mSongName = parcel.readString();
        this.mAlbumName = parcel.readString();
        this.mAlbumCover = parcel.readString();
        this.mSinger = parcel.readString();
        this.mScore = parcel.readString();
    }

    public String getSongName() {
        return this.mSongName;
    }

    public void setSongName(String str) {
        this.mSongName = str;
    }

    public String getAlbumName() {
        return this.mAlbumName;
    }

    public void setAlbumName(String str) {
        this.mAlbumName = str;
    }

    public String getAlbumCover() {
        return this.mAlbumCover;
    }

    public void setAlbumCover(String str) {
        this.mAlbumCover = str;
    }

    public String getSinger() {
        return this.mSinger;
    }

    public void setSinger(String str) {
        this.mSinger = str;
    }

    public String getScore() {
        return this.mScore;
    }

    public void setScore(String str) {
        this.mScore = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mSongName);
        parcel.writeString(this.mAlbumName);
        parcel.writeString(this.mAlbumCover);
        parcel.writeString(this.mSinger);
        parcel.writeString(this.mScore);
    }
}
