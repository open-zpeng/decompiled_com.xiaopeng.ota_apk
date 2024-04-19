package com.xiaopeng.ota.version;

import java.io.Serializable;
import java.util.Objects;
/* loaded from: classes2.dex */
public class ReleaseVersion implements Serializable {
    public static final String CDU_VERSION = "cdu_version";
    public static final String DATE = "date";
    public static final String RELEASE_NOTE = "release_note";
    public static final String TABLE_RELEASE_VERSION = "release_version";
    private static final String TAG = "ReleaseVersion";
    public static final String VERSION = "version";
    private String cduVersion;
    private String date;
    private long id;
    private String releaseNote;
    private String version;

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getReleaseNote() {
        return this.releaseNote;
    }

    public void setReleaseNote(String str) {
        this.releaseNote = str;
    }

    public String getCduVersion() {
        return this.cduVersion;
    }

    public void setCduVersion(String str) {
        this.cduVersion = str;
    }

    public String toString() {
        return "ReleaseVersion{version='" + this.version + "'date='" + this.date + "'releaseNote='" + this.releaseNote + "'cduVersion='" + this.cduVersion + "'}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ReleaseVersion releaseVersion = (ReleaseVersion) obj;
        return Objects.equals(Long.valueOf(this.id), Long.valueOf(releaseVersion.id)) && Objects.equals(this.version, releaseVersion.version) && Objects.equals(this.date, releaseVersion.date) && Objects.equals(this.releaseNote, releaseVersion.releaseNote) && Objects.equals(this.cduVersion, releaseVersion.cduVersion);
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.id), this.version, this.date, this.cduVersion);
    }
}
