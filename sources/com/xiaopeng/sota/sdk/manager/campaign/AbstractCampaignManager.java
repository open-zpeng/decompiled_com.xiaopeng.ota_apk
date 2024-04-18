package com.xiaopeng.sota.sdk.manager.campaign;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.xiaopeng.sota.sdk.SOTAManager;
import java.util.Locale;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class AbstractCampaignManager {
    private static final String QUERY_ENVIRONMENT = "environment=?";
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private DatabaseOpenHelper mDatabaseOpenHelper;

    /* JADX INFO: Access modifiers changed from: protected */
    public String getEnvironment() {
        String environment = SOTAManager.getEnvironment();
        return TextUtils.isEmpty(environment) ? "" : environment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String whereWrapper(String str, Object... objArr) {
        if (str == null || TextUtils.isEmpty(str)) {
            return QUERY_ENVIRONMENT;
        }
        Locale locale = Locale.getDefault();
        return String.format(locale, "environment=? AND (" + str + ")", objArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String[] whereArgsWrapper(Object... objArr) {
        int i = 1;
        String[] strArr = new String[objArr.length + 1];
        int i2 = 0;
        strArr[0] = getEnvironment();
        int length = objArr.length;
        while (i2 < length) {
            strArr[i] = String.valueOf(objArr[i2]);
            i2++;
            i++;
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void transactionExecute(Runnable runnable) {
        try {
            this.mDatabase.beginTransaction();
            runnable.run();
            this.mDatabase.setTransactionSuccessful();
        } finally {
            this.mDatabase.endTransaction();
        }
    }

    public AbstractCampaignManager(Context context) {
        this.mContext = context;
        this.mDatabaseOpenHelper = new DatabaseOpenHelper(context);
        this.mDatabase = this.mDatabaseOpenHelper.getWritableDatabase();
    }

    public synchronized void open() {
        if (this.mDatabaseOpenHelper == null) {
            this.mDatabaseOpenHelper = new DatabaseOpenHelper(this.mContext);
            this.mDatabase = this.mDatabaseOpenHelper.getWritableDatabase();
        }
    }

    public synchronized void dispose() {
        if (this.mDatabaseOpenHelper != null) {
            this.mDatabase.close();
            this.mDatabaseOpenHelper.close();
            this.mDatabaseOpenHelper = null;
        }
    }

    public SQLiteDatabase getDatabase() {
        return this.mDatabase;
    }
}
