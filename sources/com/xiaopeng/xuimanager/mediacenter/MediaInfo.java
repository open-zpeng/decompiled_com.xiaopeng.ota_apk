package com.xiaopeng.xuimanager.mediacenter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class MediaInfo implements Parcelable {
    private static final int AMBIENTLIGHT_DEFAULT_COLOR = 5;
    public static final int FAVOR_NOT_SET = 0;
    public static final int FAVOR_NOT_SUPPORT = -1;
    public static final int FAVOR_SET = 1;
    public static final int MEDIA_SOURCE_BT = 3;
    public static final int MEDIA_SOURCE_FM = 1;
    public static final int MEDIA_SOURCE_MUSIC = 0;
    public static final int MEDIA_SOURCE_READING = 2;
    public static final int MEDIA_SOURCE_SHOWMODE = 4;
    public static final int MEDIA_STYLE_CLASSIC = 5;
    public static final int MEDIA_STYLE_DEFAULT = -1;
    public static final int MEDIA_STYLE_JAZZ = 2;
    public static final int MEDIA_STYLE_LIGHT = 4;
    public static final int MEDIA_STYLE_MOVIE = 7;
    public static final int MEDIA_STYLE_OPERA = 6;
    public static final int MEDIA_STYLE_POP = 1;
    public static final int MEDIA_STYLE_ROCK = 3;
    public static final String TAG = "MediaInfo";
    private String mAlbum;
    private Bitmap mAlbumBmp;
    private String mAlbumUri;
    private String mArtist;
    private Bundle mExtras;
    private int mFavor;
    private String mId;
    private boolean mIsAudition;
    private boolean mIsXpMusic;
    private String mPackageName;
    private String mQualityName;
    private int mSource;
    private int mStyle;
    private int mStyleColor;
    private String mStyleName;
    private String mTitle;
    private static final int[] STYLECOLOR = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    public static final Parcelable.Creator<MediaInfo> CREATOR = new Parcelable.Creator<MediaInfo>() { // from class: com.xiaopeng.xuimanager.mediacenter.MediaInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaInfo createFromParcel(Parcel parcel) {
            return new MediaInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaInfo[] newArray(int i) {
            return new MediaInfo[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MediaInfo() {
        this.mStyle = -1;
        this.mStyleColor = 5;
        this.mSource = 0;
        this.mFavor = -1;
    }

    private MediaInfo(Parcel parcel) {
        this.mStyle = -1;
        this.mStyleColor = 5;
        this.mSource = 0;
        this.mFavor = -1;
        readFromParcel(parcel);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mId);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mAlbum);
        parcel.writeString(this.mArtist);
        parcel.writeInt(this.mStyle);
        parcel.writeInt(this.mStyleColor);
        parcel.writeInt(this.mSource);
        parcel.writeInt(this.mIsXpMusic ? 1 : 0);
        parcel.writeInt(this.mFavor);
        parcel.writeInt(this.mIsAudition ? 1 : 0);
        parcel.writeString(this.mAlbumUri);
        parcel.writeString(this.mStyleName);
        parcel.writeString(this.mQualityName);
        parcel.writeString(this.mPackageName);
        if (this.mAlbumBmp != null) {
            parcel.writeInt(1);
            this.mAlbumBmp.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeBundle(this.mExtras);
    }

    public void readFromParcel(Parcel parcel) {
        this.mId = parcel.readString();
        this.mTitle = parcel.readString();
        this.mAlbum = parcel.readString();
        this.mArtist = parcel.readString();
        this.mStyle = parcel.readInt();
        this.mStyleColor = parcel.readInt();
        this.mSource = parcel.readInt();
        this.mIsXpMusic = parcel.readInt() == 1;
        this.mFavor = parcel.readInt();
        this.mIsAudition = parcel.readInt() == 1;
        this.mAlbumUri = parcel.readString();
        this.mStyleName = parcel.readString();
        this.mQualityName = parcel.readString();
        this.mPackageName = parcel.readString();
        if (parcel.readInt() == 1) {
            this.mAlbumBmp = (Bitmap) Bitmap.CREATOR.createFromParcel(parcel);
        } else {
            this.mAlbumBmp = null;
        }
        this.mExtras = Bundle.setDefusable(parcel.readBundle(), true);
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getAlbum() {
        return this.mAlbum;
    }

    public void setAlbum(String str) {
        this.mAlbum = str;
    }

    public String getArtist() {
        return this.mArtist;
    }

    public void setArtist(String str) {
        this.mArtist = str;
    }

    public int getStyle() {
        return this.mStyle;
    }

    public void setStyle(int i) {
        this.mStyle = i;
        this.mStyleColor = getColor(i);
    }

    public int getSource() {
        return this.mSource;
    }

    public void setSource(int i) {
        this.mSource = i;
    }

    public boolean isXpMusic() {
        return this.mIsXpMusic;
    }

    public void setXpMusic(boolean z) {
        this.mIsXpMusic = z;
    }

    public int getFavor() {
        return this.mFavor;
    }

    public void setFavor(int i) {
        this.mFavor = i;
    }

    public boolean isFavor() {
        return this.mFavor == 1;
    }

    public void setFavor(boolean z) {
        this.mFavor = z ? 1 : 0;
    }

    public boolean isAudition() {
        return this.mIsAudition;
    }

    public void setAudition(boolean z) {
        this.mIsAudition = z;
    }

    public String getAlbumUri() {
        return this.mAlbumUri;
    }

    public void setAlbumUri(String str) {
        this.mAlbumUri = str;
    }

    public String getStyleName() {
        return this.mStyleName;
    }

    public void setStyleName(String str) {
        this.mStyleName = str;
    }

    public String getQualityName() {
        return this.mQualityName;
    }

    public void setQualityName(String str) {
        this.mQualityName = str;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    public Bitmap getAlbumBitmap() {
        return this.mAlbumBmp;
    }

    public void setAlbumBitmap(Bitmap bitmap) {
        this.mAlbumBmp = bitmap;
    }

    public int getStyleColor() {
        return this.mStyleColor;
    }

    private static int getColor(int i) {
        if (i >= 0) {
            int[] iArr = STYLECOLOR;
            if (i < iArr.length) {
                return iArr[i];
            }
        }
        return 5;
    }

    public String getString(String str) {
        CharSequence text;
        if (this.mExtras == null || (text = getText(str)) == null) {
            return null;
        }
        return text.toString();
    }

    public CharSequence getText(String str) {
        return this.mExtras.getCharSequence(str);
    }

    public void putString(String str, String str2) {
        Bundle bundle = this.mExtras;
        if (bundle != null) {
            bundle.putCharSequence(str, str2);
        }
    }
}
