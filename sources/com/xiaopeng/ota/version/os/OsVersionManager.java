package com.xiaopeng.ota.version.os;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.xiaopeng.ota.presenter.db.AbstractRepository;
import com.xiaopeng.ota.sdk.common.util.FileUtils;
import com.xiaopeng.ota.sdk.common.util.IoUtils;
import com.xiaopeng.ota.sdk.common.util.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
/* loaded from: classes2.dex */
public class OsVersionManager extends AbstractRepository {
    private static final String OS_FILE_PATH = "/mnt/vmap/ota_release";
    private static final String TAG = "OsVersionManager";
    private OsVersion mOsVersion;

    public OsVersionManager(Context context) {
        super(context);
    }

    public OsVersion readFile() {
        OsVersion osVersion = null;
        try {
            File file = new File(OS_FILE_PATH);
            if (file.exists() && file.isFile()) {
                String read = read(file);
                if (!TextUtils.isEmpty(read)) {
                    osVersion = parseAndCheck(read);
                }
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Parse os version from file fail");
        }
        if (osVersion != null) {
            save(osVersion, false);
        }
        return osVersion;
    }

    private OsVersion parseAndCheck(String str) {
        OsVersion osVersion = (OsVersion) JsonUtils.fromJson(str, (Class<Object>) OsVersion.class);
        if (osVersion == null || osVersion.getVersionCode() <= 0 || TextUtils.isEmpty(osVersion.getVersionName())) {
            return null;
        }
        return osVersion;
    }

    private String read(File file) {
        StringBuffer stringBuffer = new StringBuffer();
        if (file != null && file.exists() && file.isFile()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine);
                }
                bufferedReader.close();
            } catch (Exception e) {
                LogUtils.e(TAG, e, "Read file fail");
            }
        }
        return stringBuffer.toString();
    }

    private void write2File(OsVersion osVersion) {
        if (osVersion == null || osVersion.getVersionCode() <= 0) {
            LogUtils.i(TAG, "OS version code invalid");
            return;
        }
        File file = new File(OS_FILE_PATH);
        try {
            if (!file.exists() || !file.isFile()) {
                file.createNewFile();
            }
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(JsonUtils.toJson(osVersion));
                bufferedWriter.close();
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            LogUtils.e(TAG, e2, "Write version to file fail");
        }
    }

    public OsVersion getOsVersion() {
        if (this.mOsVersion == null) {
            this.mOsVersion = queryOne();
        }
        if (this.mOsVersion == null) {
            this.mOsVersion = readFile();
        }
        return this.mOsVersion;
    }

    public OsVersion queryOne() {
        Cursor cursor;
        Throwable th;
        try {
            cursor = getDatabase().query(OsVersion.TABLE_NAME, null, null, null, null, null, "_id DESC", "1");
            if (cursor != null) {
                try {
                    if (cursor.moveToNext()) {
                        OsVersion osVersion = new OsVersion();
                        osVersion.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                        osVersion.setVersionCode(cursor.getInt(cursor.getColumnIndex("version_code")));
                        osVersion.setVersionName(cursor.getString(cursor.getColumnIndex(OsVersion.VERSION_NAME)));
                        osVersion.setFeaturesWithJson(cursor.getString(cursor.getColumnIndex(OsVersion.FEATURES)));
                        IoUtils.close(cursor);
                        return osVersion;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    IoUtils.close(cursor);
                    throw th;
                }
            }
            IoUtils.close(cursor);
            return null;
        } catch (Throwable th3) {
            cursor = null;
            th = th3;
        }
    }

    public void save(OsVersion osVersion) {
        save(osVersion, true);
    }

    private synchronized void save(OsVersion osVersion, boolean z) {
        if (checkOsVersion(osVersion)) {
            OsVersion queryOne = queryOne();
            if (queryOne != null) {
                osVersion.setId(queryOne.getId());
                update(osVersion);
            } else {
                insert(osVersion);
            }
            if (z) {
                write2File(osVersion);
            }
        }
    }

    private boolean checkOsVersion(OsVersion osVersion) {
        if (osVersion == null) {
            LogUtils.w(TAG, "Save fail: empty");
            return false;
        } else if (TextUtils.isEmpty(osVersion.getVersionName())) {
            LogUtils.w(TAG, "Save fail: version name empty");
            return false;
        } else {
            return true;
        }
    }

    private void update(final OsVersion osVersion) {
        this.mOsVersion = osVersion;
        transactionExecute(new Runnable() { // from class: com.xiaopeng.ota.version.os.-$$Lambda$OsVersionManager$1CqyOeSh69NMeVBMAXjOBLGqteM
            @Override // java.lang.Runnable
            public final void run() {
                OsVersionManager.this.lambda$update$0$OsVersionManager(osVersion);
            }
        });
    }

    public /* synthetic */ void lambda$update$0$OsVersionManager(OsVersion osVersion) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("version_code", Integer.valueOf(osVersion.getVersionCode()));
        contentValues.put(OsVersion.VERSION_NAME, osVersion.getVersionName());
        contentValues.put(OsVersion.FEATURES, osVersion.getFeaturesJson());
        contentValues.put("update_time", Long.valueOf(System.currentTimeMillis()));
        getDatabase().update(OsVersion.TABLE_NAME, contentValues, "_id=?", new String[]{String.valueOf(osVersion.getId())});
    }

    private void insert(final OsVersion osVersion) {
        this.mOsVersion = osVersion;
        transactionExecute(new Runnable() { // from class: com.xiaopeng.ota.version.os.-$$Lambda$OsVersionManager$HyualIG_oEGiVMkUEz70kSxeXwE
            @Override // java.lang.Runnable
            public final void run() {
                OsVersionManager.this.lambda$insert$1$OsVersionManager(osVersion);
            }
        });
    }

    public /* synthetic */ void lambda$insert$1$OsVersionManager(OsVersion osVersion) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("version_code", Integer.valueOf(osVersion.getVersionCode()));
        contentValues.put(OsVersion.VERSION_NAME, osVersion.getVersionName());
        contentValues.put(OsVersion.FEATURES, osVersion.getFeaturesJson());
        contentValues.put("create_time", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("update_time", Long.valueOf(System.currentTimeMillis()));
        getDatabase().insert(OsVersion.TABLE_NAME, null, contentValues);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: deleteAllUnsafe */
    public int lambda$deleteAll$2$OsVersionManager() {
        int delete = getDatabase().delete(OsVersion.TABLE_NAME, whereWrapper(null, new Object[0]), null);
        this.mOsVersion = null;
        return delete;
    }

    public synchronized void clear() {
        deleteAll();
        FileUtils.deleteFile(OS_FILE_PATH);
    }

    public synchronized void deleteAll() {
        transactionExecute(new Runnable() { // from class: com.xiaopeng.ota.version.os.-$$Lambda$OsVersionManager$qSA-PkZWZaNWvoBoz7GoP_jfSjM
            @Override // java.lang.Runnable
            public final void run() {
                OsVersionManager.this.lambda$deleteAll$2$OsVersionManager();
            }
        });
    }
}
