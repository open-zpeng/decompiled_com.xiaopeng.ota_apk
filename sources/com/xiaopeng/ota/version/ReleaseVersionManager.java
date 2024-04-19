package com.xiaopeng.ota.version;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.xiaopeng.ota.client.dto.VehicleVersion;
import com.xiaopeng.ota.presenter.db.AbstractRepository;
import com.xiaopeng.ota.sdk.common.util.IoUtils;
import com.xiaopeng.ota.sdk.common.util.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
/* loaded from: classes2.dex */
public class ReleaseVersionManager extends AbstractRepository {
    private static final String TAG = "ReleaseVersionManager";
    private ReleaseVersion mReleaseVersion;

    public ReleaseVersionManager(Context context) {
        super(context);
    }

    @Override // com.xiaopeng.ota.presenter.db.AbstractRepository
    public void init() {
        super.init();
    }

    public synchronized void insertReleaseVersion(ReleaseVersion releaseVersion) {
        insert(releaseVersion);
    }

    public synchronized void saveReleaseVersion(ReleaseVersion releaseVersion) {
        LogUtils.d(TAG, "saveReleaseVersion: " + JsonUtils.toJson(releaseVersion));
        if (TextUtils.isEmpty(releaseVersion.getVersion())) {
            LogUtils.w(TAG, "saveReleaseVersion version is empty");
            return;
        }
        try {
            ReleaseVersion queryOne = queryOne();
            if (queryOne != null) {
                updateByNewer(queryOne, releaseVersion);
            } else {
                insert(releaseVersion);
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e, "saveReleaseVersion fail");
        }
    }

    private void updateByNewer(ReleaseVersion releaseVersion, ReleaseVersion releaseVersion2) {
        if (!TextUtils.isEmpty(releaseVersion2.getVersion())) {
            releaseVersion.setVersion(releaseVersion2.getVersion());
        }
        if (!TextUtils.isEmpty(releaseVersion2.getCduVersion())) {
            releaseVersion.setCduVersion(releaseVersion2.getCduVersion());
        }
        if (!TextUtils.isEmpty(releaseVersion2.getReleaseNote())) {
            releaseVersion.setReleaseNote(releaseVersion2.getReleaseNote());
        }
        if (!TextUtils.isEmpty(releaseVersion2.getDate())) {
            releaseVersion.setDate(releaseVersion2.getDate());
        }
        updateReleaseVersion(releaseVersion);
    }

    private void updateReleaseVersion(final ReleaseVersion releaseVersion) {
        this.mReleaseVersion = releaseVersion;
        transactionExecute(new Runnable() { // from class: com.xiaopeng.ota.version.-$$Lambda$ReleaseVersionManager$WGyC8Qv3UzJpjS4Ao7S2UDiLigo
            @Override // java.lang.Runnable
            public final void run() {
                ReleaseVersionManager.this.lambda$updateReleaseVersion$0$ReleaseVersionManager(releaseVersion);
            }
        });
    }

    public /* synthetic */ void lambda$updateReleaseVersion$0$ReleaseVersionManager(ReleaseVersion releaseVersion) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("version", releaseVersion.getVersion());
        contentValues.put("date", releaseVersion.getDate());
        contentValues.put(ReleaseVersion.RELEASE_NOTE, releaseVersion.getReleaseNote());
        contentValues.put(ReleaseVersion.CDU_VERSION, releaseVersion.getCduVersion());
        contentValues.put("update_time", Long.valueOf(System.currentTimeMillis()));
        getDatabase().update(ReleaseVersion.TABLE_RELEASE_VERSION, contentValues, "_id=?", new String[]{String.valueOf(releaseVersion.getId())});
    }

    private void insert(final ReleaseVersion releaseVersion) {
        this.mReleaseVersion = releaseVersion;
        transactionExecute(new Runnable() { // from class: com.xiaopeng.ota.version.-$$Lambda$ReleaseVersionManager$TOnjgDcGj0rkvRjziF9eOIvdZc0
            @Override // java.lang.Runnable
            public final void run() {
                ReleaseVersionManager.this.lambda$insert$1$ReleaseVersionManager(releaseVersion);
            }
        });
    }

    public /* synthetic */ void lambda$insert$1$ReleaseVersionManager(ReleaseVersion releaseVersion) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("version", releaseVersion.getVersion());
        contentValues.put("date", releaseVersion.getDate());
        contentValues.put(ReleaseVersion.RELEASE_NOTE, releaseVersion.getReleaseNote());
        contentValues.put(ReleaseVersion.CDU_VERSION, releaseVersion.getCduVersion());
        contentValues.put("create_time", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("update_time", Long.valueOf(System.currentTimeMillis()));
        getDatabase().insert(ReleaseVersion.TABLE_RELEASE_VERSION, null, contentValues);
    }

    public ReleaseVersion getReleaseVersion() {
        if (this.mReleaseVersion == null) {
            this.mReleaseVersion = queryOne();
        }
        return this.mReleaseVersion;
    }

    public ReleaseVersion queryOne() {
        Cursor cursor;
        try {
            cursor = getDatabase().query(ReleaseVersion.TABLE_RELEASE_VERSION, new String[]{"_id", "version", "date", ReleaseVersion.RELEASE_NOTE, ReleaseVersion.CDU_VERSION}, null, null, null, null, "_id DESC", "1");
            if (cursor != null) {
                try {
                    if (cursor.moveToNext()) {
                        ReleaseVersion releaseVersion = new ReleaseVersion();
                        releaseVersion.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                        releaseVersion.setVersion(cursor.getString(cursor.getColumnIndex("version")));
                        releaseVersion.setDate(cursor.getString(cursor.getColumnIndex("date")));
                        releaseVersion.setReleaseNote(cursor.getString(cursor.getColumnIndex(ReleaseVersion.RELEASE_NOTE)));
                        releaseVersion.setCduVersion(cursor.getString(cursor.getColumnIndex(ReleaseVersion.CDU_VERSION)));
                        IoUtils.close(cursor);
                        return releaseVersion;
                    }
                } catch (Throwable th) {
                    th = th;
                    IoUtils.close(cursor);
                    throw th;
                }
            }
            IoUtils.close(cursor);
            return null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    public void updateCduVersion(String str) {
        ReleaseVersion releaseVersion = new ReleaseVersion();
        releaseVersion.setCduVersion(str);
        saveReleaseVersion(releaseVersion);
    }

    public void updateFromVersionInfo(VehicleVersion vehicleVersion) {
        ReleaseVersion queryOne = queryOne();
        if (queryOne == null) {
            queryOne = new ReleaseVersion();
        }
        if (vehicleVersion.getVersionName().equals(queryOne.getVersion())) {
            return;
        }
        queryOne.setVersion(vehicleVersion.getVersionName());
        saveReleaseVersion(queryOne);
    }

    public synchronized void deleteAll() {
        this.mReleaseVersion = null;
        transactionExecute(new Runnable() { // from class: com.xiaopeng.ota.version.-$$Lambda$ReleaseVersionManager$-ihqflK_wqSqM_O4grk4wdditxE
            @Override // java.lang.Runnable
            public final void run() {
                ReleaseVersionManager.this.lambda$deleteAll$2$ReleaseVersionManager();
            }
        });
    }

    public /* synthetic */ void lambda$deleteAll$2$ReleaseVersionManager() {
        getDatabase().delete(ReleaseVersion.TABLE_RELEASE_VERSION, null, null);
    }
}
