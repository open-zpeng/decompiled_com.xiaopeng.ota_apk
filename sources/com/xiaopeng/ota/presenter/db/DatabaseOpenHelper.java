package com.xiaopeng.ota.presenter.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.xiaopeng.ota.utils.LogUtils;
/* loaded from: classes2.dex */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ota-app.db";
    private static final int DB_VERSION = 6;
    private static final String TAG = "DatabaseOpenHelper";

    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 6);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        LogUtils.d(TAG, "onCreate");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS campaign(_id INTEGER PRIMARY KEY AUTOINCREMENT,campaign_id INT, active_flag INT DEFAULT 0, campaign_json TEXT, installed_flag INT DEFAULT 0, installed_time BIGINT DEFAULT 0,create_time INT, update_time INT)");
        createDrivingRoutes(sQLiteDatabase);
        createReleaseVersion(sQLiteDatabase);
        createOsVersion(sQLiteDatabase);
    }

    private void createDrivingRoutes(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS driving_routes(_id INTEGER PRIMARY KEY AUTOINCREMENT,environment TEXT, version INT DEFAULT 0, data TEXT, create_time INT, update_time INT)");
    }

    private void createReleaseVersion(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS release_version(_id INTEGER PRIMARY KEY AUTOINCREMENT,version TEXT, date TEXT, release_note TEXT, cdu_version TEXT, create_time INT, update_time INT)");
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_release_version on release_version (version)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        LogUtils.d(TAG, "On upgrade old:%d, new:%d", Integer.valueOf(i), Integer.valueOf(i2));
        if (i < 2) {
            addInstallFlagV2(sQLiteDatabase);
        }
        if (i < 3) {
            createDrivingRoutes(sQLiteDatabase);
        }
        if (i < 4) {
            createReleaseVersion(sQLiteDatabase);
        }
        if (i < 5) {
            addInstalledTimeV5(sQLiteDatabase);
        }
        if (i < 6) {
            createOsVersion(sQLiteDatabase);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        LogUtils.d(TAG, "On downgrade old:%d, new:%d", Integer.valueOf(i), Integer.valueOf(i2));
    }

    private void addInstallFlagV2(SQLiteDatabase sQLiteDatabase) {
        LogUtils.d(TAG, "add install result flag V2");
        try {
            sQLiteDatabase.execSQL("ALTER TABLE campaign ADD installed_flag INT DEFAULT 0");
        } catch (SQLException e) {
            LogUtils.e(TAG, e, "add install result flag V2 failed");
        }
    }

    private void addInstalledTimeV5(SQLiteDatabase sQLiteDatabase) {
        LogUtils.d(TAG, "add installed time V5");
        try {
            sQLiteDatabase.execSQL("ALTER TABLE campaign ADD installed_time BIGINT DEFAULT 0");
        } catch (SQLException e) {
            LogUtils.e(TAG, e, "add installed time V5 failed");
        }
    }

    private void createOsVersion(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS os_version(_id INTEGER PRIMARY KEY AUTOINCREMENT,version_code INT, version_name TEXT, features TEXT, create_time INT, update_time INT)");
        } catch (SQLException e) {
            LogUtils.e(TAG, e, "create os version failed");
        }
    }
}
