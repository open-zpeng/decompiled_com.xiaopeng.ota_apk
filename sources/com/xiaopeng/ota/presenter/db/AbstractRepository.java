package com.xiaopeng.ota.presenter.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.util.Locale;
/* loaded from: classes2.dex */
public abstract class AbstractRepository {
    protected static final String LIMIT_ONE = "1";
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private DatabaseOpenHelper mDatabaseOpenHelper;

    public AbstractRepository(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mDatabaseOpenHelper = new DatabaseOpenHelper(this.mContext);
        this.mDatabase = this.mDatabaseOpenHelper.getWritableDatabase();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String whereWrapper(String str, Object... objArr) {
        if (str == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return String.format(Locale.getDefault(), str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String[] whereArgsWrapper(Object... objArr) {
        String[] strArr = new String[objArr.length];
        int length = objArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            strArr[i2] = String.valueOf(objArr[i]);
            i++;
            i2++;
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void transactionExecute(Runnable runnable) {
        synchronized (AbstractRepository.class) {
            this.mDatabase.beginTransaction();
            runnable.run();
            this.mDatabase.setTransactionSuccessful();
            if (this.mDatabase != null && this.mDatabase.inTransaction()) {
                this.mDatabase.endTransaction();
            }
        }
    }

    public synchronized void dispose() {
        if (this.mDatabaseOpenHelper != null) {
            this.mDatabase.close();
            this.mDatabaseOpenHelper.close();
        }
    }

    public SQLiteDatabase getDatabase() {
        return this.mDatabase;
    }
}
