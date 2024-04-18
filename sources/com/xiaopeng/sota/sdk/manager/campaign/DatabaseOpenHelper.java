package com.xiaopeng.sota.sdk.manager.campaign;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.sota.sdk.common.Constants;
/* loaded from: classes2.dex */
class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 2;
    private static final String TAG = "DatabaseOpenHelper-sota";

    public DatabaseOpenHelper(Context context) {
        super(context, Constants.DB_NAME, (SQLiteDatabase.CursorFactory) null, 2);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS campaign(_id INTEGER PRIMARY KEY AUTOINCREMENT,environment TEXT, trace_id TEXT, campaign_id INT, campaign TEXT, extra TEXT, host TEXT, package TEXT, valid INT DEFAULT 1, state INT DEFAULT 0, cdu_fingerprint TEXT, create_time INT, update_time INT)");
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_campaign_id on campaign (environment,campaign_id)");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS campaign_event(_id INTEGER PRIMARY KEY AUTOINCREMENT,environment TEXT, trace_id TEXT, campaign_id INT, package_id INT, event TEXT, status INT, context TEXT, timestamp INT, create_time INT, update_time INT)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Logger.d(TAG, "On upgrade old:" + i + ", new:" + i2, new Object[0]);
        if (i < 2) {
            updateCampaignV2(sQLiteDatabase);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Logger.d(TAG, "On downgrade old:" + i + ", new:" + i2, new Object[0]);
    }

    private void updateCampaignV2(SQLiteDatabase sQLiteDatabase) {
        Logger.d(TAG, "Update campaign to V2", new Object[0]);
        try {
            sQLiteDatabase.execSQL("ALTER TABLE campaign ADD cdu_fingerprint TEXT");
        } catch (SQLException e) {
            Logger.e(TAG, e, "Update campaign cdu fingerprint column failed", new Object[0]);
        }
    }
}
