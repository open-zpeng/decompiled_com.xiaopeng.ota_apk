package com.xiaopeng.sota.sdk.manager.campaign;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.xiaopeng.ota.sdk.campaign.CampaignEventListener;
import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.sdk.common.util.IoUtils;
import com.xiaopeng.ota.sdk.common.util.JsonUtils;
import com.xiaopeng.ota.sdk.common.util.TraceIdGenerator;
import com.xiaopeng.sota.sdk.SOTAManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class CampaignEventManager extends AbstractCampaignManager {
    private static final String SQL_QUERY_EMPTY_ENVIRONMENT = "SELECT COUNT(1) FROM campaign_event WHERE environment=?";
    private static final String TAG = "CampaignEventManager";
    private CampaignEventListener mCampaignEventListener;
    private SQLiteDatabase mDatabase;
    private DatabaseOpenHelper mDatabaseOpenHelper;

    @Override // com.xiaopeng.sota.sdk.manager.campaign.AbstractCampaignManager
    public /* bridge */ /* synthetic */ SQLiteDatabase getDatabase() {
        return super.getDatabase();
    }

    @Override // com.xiaopeng.sota.sdk.manager.campaign.AbstractCampaignManager
    public /* bridge */ /* synthetic */ void open() {
        super.open();
    }

    public CampaignEventManager(Context context, CampaignEventListener campaignEventListener) {
        super(context);
        this.mCampaignEventListener = campaignEventListener;
    }

    @Override // com.xiaopeng.sota.sdk.manager.campaign.AbstractCampaignManager
    public synchronized void dispose() {
        if (this.mDatabaseOpenHelper != null) {
            this.mDatabase.close();
            this.mDatabaseOpenHelper.close();
        }
    }

    public void add(final CampaignEvent campaignEvent) {
        CampaignEventListener campaignEventListener = this.mCampaignEventListener;
        if (campaignEventListener != null) {
            campaignEventListener.onCreated(campaignEvent.getCampaignId().longValue(), campaignEvent.getEvent(), campaignEvent.getStatus().intValue());
        }
        transactionExecute(new Runnable() { // from class: com.xiaopeng.sota.sdk.manager.campaign.CampaignEventManager.1
            @Override // java.lang.Runnable
            public void run() {
                CampaignEventManager.this.addInternal(campaignEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addInternal(CampaignEvent campaignEvent) {
        String str;
        String environment = getEnvironment();
        if (TextUtils.isEmpty(environment)) {
            Logger.d(TAG, "Server url did not fetch, use campaign env", new Object[0]);
            Campaign campaign = SOTAManager.getCampaignManager().get(campaignEvent.getCampaignId().longValue());
            if (campaign != null) {
                environment = campaign.getEnvironment();
            }
            if (TextUtils.isEmpty(environment)) {
                Logger.d(TAG, "Not found campaign use empty env", new Object[0]);
                environment = "";
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("environment", environment);
        contentValues.put("trace_id", campaignEvent.getTraceId());
        contentValues.put("campaign_id", campaignEvent.getCampaignId());
        contentValues.put(CampaignEvent.PACKAGE_ID, campaignEvent.getPackageId());
        if (campaignEvent.getEvent() != null) {
            contentValues.put("event", campaignEvent.getEvent());
        }
        if (campaignEvent.getStatus() != null) {
            contentValues.put("status", campaignEvent.getStatus());
        }
        if (campaignEvent.getTimestamp() != null) {
            contentValues.put("timestamp", campaignEvent.getTimestamp());
        }
        if (campaignEvent.getCreateTime() != 0) {
            contentValues.put("create_time", Long.valueOf(campaignEvent.getCreateTime()));
        }
        if (campaignEvent.getUpdateTime() != 0) {
            contentValues.put("update_time", Long.valueOf(campaignEvent.getUpdateTime()));
        }
        if (campaignEvent.getContext() != null) {
            try {
                str = new JSONObject(campaignEvent.getContext()).toString();
            } catch (Exception e) {
                Logger.e(TAG, e, "Campaign event context convert json failed", new Object[0]);
                str = null;
            }
            if (!TextUtils.isEmpty(str)) {
                contentValues.put("context", str);
            }
        }
        getDatabase().insert(CampaignEvent.TABLE_NAME, null, contentValues);
    }

    public void addInBatch(final List<CampaignEvent> list) {
        transactionExecute(new Runnable() { // from class: com.xiaopeng.sota.sdk.manager.campaign.CampaignEventManager.2
            @Override // java.lang.Runnable
            public void run() {
                for (CampaignEvent campaignEvent : list) {
                    CampaignEventManager.this.addInternal(campaignEvent);
                }
            }
        });
    }

    private List<CampaignEvent> query(String str, String... strArr) {
        Cursor cursor = null;
        try {
            ArrayList arrayList = new ArrayList();
            cursor = getDatabase().query(CampaignEvent.TABLE_NAME, null, whereWrapper(str, new Object[0]), whereArgsWrapper(strArr), null, null, null, null);
            while (cursor.moveToNext()) {
                arrayList.add(parse(cursor));
            }
            return arrayList;
        } finally {
            IoUtils.close(cursor);
        }
    }

    private CampaignEvent parse(Cursor cursor) {
        CampaignEvent campaignEvent = new CampaignEvent();
        campaignEvent.setId(cursor.getLong(cursor.getColumnIndex("_id")));
        campaignEvent.setEnvironment(cursor.getString(cursor.getColumnIndex("environment")));
        campaignEvent.setTraceId(cursor.getString(cursor.getColumnIndex("trace_id")));
        campaignEvent.setCampaignId(Long.valueOf(cursor.getLong(cursor.getColumnIndex("campaign_id"))));
        campaignEvent.setPackageId(Long.valueOf(cursor.getLong(cursor.getColumnIndex(CampaignEvent.PACKAGE_ID))));
        campaignEvent.setEvent(cursor.getString(cursor.getColumnIndex("event")));
        campaignEvent.setStatus(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("status"))));
        campaignEvent.setTimestamp(Long.valueOf(cursor.getLong(cursor.getColumnIndex("timestamp"))));
        campaignEvent.setCreateTime(cursor.getLong(cursor.getColumnIndex("create_time")));
        campaignEvent.setUpdateTime(cursor.getLong(cursor.getColumnIndex("update_time")));
        String string = cursor.getString(cursor.getColumnIndex("context"));
        if (!TextUtils.isEmpty(string)) {
            try {
                campaignEvent.setContext(JsonUtils.toMap(new JSONObject(string)));
            } catch (Exception e) {
                Logger.e(TAG, e, "Campaign event context convert map failed", new Object[0]);
            }
        }
        campaignEvent.setEventId(TraceIdGenerator.create());
        return campaignEvent;
    }

    public synchronized List<CampaignEvent> getAll() {
        return query(null, new String[0]);
    }

    public synchronized void deleteAll() {
        getDatabase().delete(CampaignEvent.TABLE_NAME, whereWrapper("", new Object[0]), whereArgsWrapper(new Object[0]));
    }

    public synchronized void delete(final CampaignEvent campaignEvent) {
        transactionExecute(new Runnable() { // from class: com.xiaopeng.sota.sdk.manager.campaign.CampaignEventManager.3
            @Override // java.lang.Runnable
            public void run() {
                CampaignEventManager.this.getDatabase().delete(CampaignEvent.TABLE_NAME, String.format("%s=?", "_id"), new String[]{String.valueOf(campaignEvent.getId())});
            }
        });
    }

    public synchronized List<String> getAllTraceId() {
        ArrayList arrayList;
        String[] strArr = {"trace_id"};
        arrayList = new ArrayList();
        Cursor query = getDatabase().query(true, CampaignEvent.TABLE_NAME, strArr, whereWrapper("", new Object[0]), whereArgsWrapper(new Object[0]), null, null, null, null);
        while (query.moveToNext()) {
            arrayList.add(query.getString(query.getColumnIndex("trace_id")));
        }
        IoUtils.close(query);
        return arrayList;
    }

    public synchronized List<CampaignEvent> getByTraceId(String str, int i) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Cursor query = getDatabase().query(CampaignEvent.TABLE_NAME, null, String.format("%s=?", "trace_id"), new String[]{str}, null, null, "_id ASC", String.valueOf(i));
        while (query.moveToNext()) {
            arrayList.add(parse(query));
        }
        IoUtils.close(query);
        return arrayList;
    }

    public synchronized int deleteByTraceId(String str, long j) {
        int i;
        SQLiteDatabase database;
        try {
            getDatabase().beginTransaction();
            i = getDatabase().delete(CampaignEvent.TABLE_NAME, String.format("%s=? AND %s<=?", "trace_id", "_id"), new String[]{str, String.valueOf(j)});
            try {
                getDatabase().setTransactionSuccessful();
                database = getDatabase();
            } catch (Exception e) {
                e = e;
                Logger.e(TAG, e, "Database delete data occurs exception", new Object[0]);
                database = getDatabase();
                database.endTransaction();
                return i;
            }
        } catch (Exception e2) {
            e = e2;
            i = 0;
        }
        database.endTransaction();
        return i;
    }
}
