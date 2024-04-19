package com.xiaopeng.sota.sdk.manager.campaign;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.xiaopeng.ota.sdk.common.OTAConstants;
import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.sdk.common.util.IoUtils;
import com.xiaopeng.ota.sdk.exception.SQLNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class CampaignManager extends AbstractCampaignManager {
    private static final Verifier DEFAULT_VERIFIER = new Verifier() { // from class: com.xiaopeng.sota.sdk.manager.campaign.CampaignManager.1
        @Override // com.xiaopeng.sota.sdk.manager.campaign.CampaignManager.Verifier
        public boolean verify(Campaign campaign) {
            return campaign.isValid() && campaign.getState() != 3;
        }
    };
    private static String TAG = "CampaignManager";
    private final LinkedHashMap<Long, Campaign> mCampaignCache;
    private Verifier mVerifier;

    /* loaded from: classes2.dex */
    public interface Verifier {
        boolean verify(Campaign campaign);
    }

    @Override // com.xiaopeng.sota.sdk.manager.campaign.AbstractCampaignManager
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    @Override // com.xiaopeng.sota.sdk.manager.campaign.AbstractCampaignManager
    public /* bridge */ /* synthetic */ SQLiteDatabase getDatabase() {
        return super.getDatabase();
    }

    @Override // com.xiaopeng.sota.sdk.manager.campaign.AbstractCampaignManager
    public /* bridge */ /* synthetic */ void open() {
        super.open();
    }

    public CampaignManager(Context context) {
        this(context, DEFAULT_VERIFIER);
    }

    public CampaignManager(Context context, Verifier verifier) {
        super(context);
        this.mCampaignCache = new LinkedHashMap<>();
        this.mVerifier = verifier;
        load();
    }

    public void load() {
        Logger.d(TAG, "Load campaign from db", new Object[0]);
        for (Campaign campaign : query(null, null, new Object[0])) {
            if (this.mVerifier.verify(campaign)) {
                this.mCampaignCache.put(campaign.getCampaignId(), campaign);
            }
        }
        String str = TAG;
        Logger.d(str, "Cache data:" + mapToString(), new Object[0]);
    }

    private synchronized List<Campaign> query(Integer num, String str, Object... objArr) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Cursor query = getDatabase().query("campaign", null, whereWrapper(str, new Object[0]), whereArgsWrapper(objArr), null, null, "_id ASC", num == null ? null : String.valueOf(num));
        while (query.moveToNext()) {
            Campaign campaign = new Campaign();
            campaign.setId(query.getLong(query.getColumnIndex("_id")));
            campaign.setEnvironment(query.getString(query.getColumnIndex("environment")));
            campaign.setTraceId(query.getString(query.getColumnIndex("trace_id")));
            campaign.setCampaignId(Long.valueOf(query.getLong(query.getColumnIndex("campaign_id"))));
            campaign.setExtra(query.getString(query.getColumnIndex("extra")));
            campaign.setHost(query.getString(query.getColumnIndex("host")));
            campaign.setState(query.getInt(query.getColumnIndex("state")));
            campaign.setCduFingerprint(query.getString(query.getColumnIndex(Campaign.CDU_FINGERPRINT)));
            boolean z = true;
            if (1 != query.getInt(query.getColumnIndex(Campaign.VALID))) {
                z = false;
            }
            campaign.setValid(z);
            campaign.setCreateTime(query.getLong(query.getColumnIndex("create_time")));
            campaign.setUpdateTime(query.getLong(query.getColumnIndex("update_time")));
            parseCampaign(campaign, query.getString(query.getColumnIndex("campaign")));
            campaign.setCampaignPackage(parsePackage(query.getString(query.getColumnIndex("package"))));
            arrayList.add(campaign);
        }
        IoUtils.close(query);
        return arrayList;
    }

    private void parseCampaign(Campaign campaign, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(OTAConstants.JsonKey.UPGRADE_MODE)) {
                campaign.setUpgradeMode(Integer.valueOf(jSONObject.getInt(OTAConstants.JsonKey.UPGRADE_MODE)));
            }
            if (jSONObject.has(OTAConstants.JsonKey.CAMPAIGN_NAME)) {
                campaign.setCampaignName(jSONObject.getString(OTAConstants.JsonKey.CAMPAIGN_NAME));
            }
            if (jSONObject.has(OTAConstants.JsonKey.RELEASE_NOTES)) {
                campaign.setReleaseNotes(jSONObject.getString(OTAConstants.JsonKey.RELEASE_NOTES));
            }
        } catch (Exception e) {
            String str2 = TAG;
            Logger.e(str2, e, "Parse campaign occurs exception:" + str, new Object[0]);
        }
    }

    private CampaignPackage parsePackage(String str) {
        try {
            return CampaignConverter.convertPackage(new JSONObject(str));
        } catch (Exception e) {
            String str2 = TAG;
            Logger.e(str2, e, "Parse campaign package occurs exception:" + str, new Object[0]);
            return null;
        }
    }

    public synchronized void saveBatch(final List<Campaign> list) {
        transactionExecute(new Runnable() { // from class: com.xiaopeng.sota.sdk.manager.campaign.CampaignManager.2
            @Override // java.lang.Runnable
            public void run() {
                CampaignManager.this.updateAllInvalid();
                for (Campaign campaign : list) {
                    Campaign findById = CampaignManager.this.findById(campaign.getCampaignId().longValue());
                    if (findById != null) {
                        CampaignManager.this.updateCampaign(campaign);
                        campaign.setTraceId(findById.getTraceId());
                    } else {
                        CampaignManager.this.addUnsafe(campaign);
                    }
                    CampaignManager.this.mCampaignCache.put(campaign.getCampaignId(), campaign);
                }
                String str = CampaignManager.TAG;
                Logger.d(str, "saveBatch:" + CampaignManager.this.mapToString(), new Object[0]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteUnsafe(long j) {
        getDatabase().delete("campaign", whereWrapper("%s=?", "campaign_id"), whereArgsWrapper(Long.valueOf(j)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addUnsafe(Campaign campaign) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("environment", getEnvironment());
        contentValues.put("trace_id", campaign.getTraceId());
        contentValues.put("campaign_id", campaign.getCampaignId());
        contentValues.put("host", campaign.getHost());
        contentValues.put("extra", campaign.getExtra());
        contentValues.put(Campaign.VALID, Integer.valueOf(campaign.isValid() ? 1 : 0));
        contentValues.put("state", Integer.valueOf(campaign.getState()));
        contentValues.put(Campaign.CDU_FINGERPRINT, campaign.getCduFingerprint());
        contentValues.put("create_time", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("update_time", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("campaign", CampaignConverter.getCampaignJsonText(campaign));
        contentValues.put("package", campaign.getPackageJson());
        getDatabase().insert("campaign", null, contentValues);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCampaign(Campaign campaign) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("environment", getEnvironment());
        contentValues.put("host", campaign.getHost());
        contentValues.put("extra", campaign.getExtra());
        contentValues.put(Campaign.VALID, Integer.valueOf(campaign.isValid() ? 1 : 0));
        contentValues.put("state", Integer.valueOf(campaign.getState()));
        contentValues.put(Campaign.CDU_FINGERPRINT, campaign.getCduFingerprint());
        contentValues.put("update_time", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("campaign", CampaignConverter.getCampaignJsonText(campaign));
        contentValues.put("package", campaign.getPackageJson());
        getDatabase().update("campaign", contentValues, whereWrapper("%s=?", "campaign_id"), whereArgsWrapper(campaign.getCampaignId()));
    }

    public synchronized Campaign get(long j) {
        return this.mCampaignCache.get(Long.valueOf(j));
    }

    public Campaign findById(long j) {
        return queryOne(String.format("%s=?", "campaign_id"), Long.valueOf(j));
    }

    private synchronized Campaign queryOne(String str, Object... objArr) {
        List<Campaign> query;
        query = query(1, str, objArr);
        return query.isEmpty() ? null : query.get(0);
    }

    public synchronized List<Campaign> getNotDownloadCompletedList() {
        ArrayList arrayList = new ArrayList();
        Set<Long> keySet = this.mCampaignCache.keySet();
        if (keySet.size() == 0) {
            return arrayList;
        }
        for (Long l : new ArrayList(keySet)) {
            Campaign campaign = this.mCampaignCache.get(Long.valueOf(l.longValue()));
            if (campaign.isValid() && (campaign.getState() == 0 || campaign.getState() == 1)) {
                arrayList.add(campaign);
            }
        }
        return arrayList;
    }

    public synchronized List<Campaign> getAllAvailable() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        String str = TAG;
        Logger.d(str, "getAllAvailable:" + mapToString(), new Object[0]);
        if (this.mCampaignCache.size() == 0) {
            Logger.d(TAG, "getAllAvailable empty & reload", new Object[0]);
            load();
        }
        for (Map.Entry<Long, Campaign> entry : this.mCampaignCache.entrySet()) {
            Campaign value = entry.getValue();
            if (value.isValid() && value.getState() != 3) {
                arrayList.add(value);
            }
        }
        return arrayList;
    }

    public synchronized List<Campaign> getSilentAvailable() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        String str = TAG;
        Logger.d(str, "getAllAvailable:" + mapToString(), new Object[0]);
        if (this.mCampaignCache.size() == 0) {
            Logger.d(TAG, "getAllAvailable empty & reload", new Object[0]);
            load();
        }
        for (Map.Entry<Long, Campaign> entry : this.mCampaignCache.entrySet()) {
            Campaign value = entry.getValue();
            if (value.isValid() && value.getUpgradeMode().intValue() == 2 && value.getState() != 3) {
                arrayList.add(value);
            }
        }
        return arrayList;
    }

    public synchronized Campaign getCampaign(String str) {
        Logger.d(TAG, "getCampaign(%s): %s", str, mapToString());
        if (this.mCampaignCache.size() == 0) {
            for (Campaign campaign : query(null, null, new Object[0])) {
                if (this.mVerifier.verify(campaign)) {
                    this.mCampaignCache.put(campaign.getCampaignId(), campaign);
                }
            }
            String str2 = TAG;
            Logger.d(str2, "getCampaign after:" + mapToString(), new Object[0]);
        }
        for (Map.Entry<Long, Campaign> entry : this.mCampaignCache.entrySet()) {
            Campaign value = entry.getValue();
            if (packageNameEquals(value.getCampaignPackage(), str)) {
                return value;
            }
        }
        return null;
    }

    private boolean packageNameEquals(CampaignPackage campaignPackage, String str) {
        if (campaignPackage == null || TextUtils.isEmpty(str)) {
            return false;
        }
        return str.equals(campaignPackage.getPackageName());
    }

    public synchronized boolean allCampaignDownloaded() {
        for (Map.Entry<Long, Campaign> entry : this.mCampaignCache.entrySet()) {
            Campaign value = entry.getValue();
            if (value.isValid() && (value.getState() == 0 || value.getState() == 1)) {
                return false;
            }
        }
        return true;
    }

    public synchronized int getUpgradeMode() {
        Set<Long> keySet = this.mCampaignCache.keySet();
        if (keySet.size() == 0) {
            return 0;
        }
        return this.mCampaignCache.get(new ArrayList(keySet).get(0)).getUpgradeMode().intValue();
    }

    public synchronized String getCduFingerprint(long j) {
        Campaign campaign = get(j);
        if (campaign != null) {
            return campaign.getCduFingerprint();
        }
        return null;
    }

    public synchronized int getCampaignCount() {
        return this.mCampaignCache.size();
    }

    public synchronized void updateAllInvalid() throws SQLNotFoundException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Campaign.VALID, (Integer) 0);
        contentValues.put("update_time", Long.valueOf(System.currentTimeMillis()));
        getDatabase().update("campaign", contentValues, whereWrapper("%s=?", Campaign.VALID), whereArgsWrapper(1));
        this.mCampaignCache.clear();
    }

    public synchronized void invalidCampaign(long j) throws SQLNotFoundException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Campaign.VALID, (Integer) 0);
        contentValues.put("update_time", Long.valueOf(System.currentTimeMillis()));
        getDatabase().update("campaign", contentValues, whereWrapper("%s=?", "campaign_id"), whereArgsWrapper(Long.valueOf(j)));
        this.mCampaignCache.remove(Long.valueOf(j));
    }

    public synchronized void updateState(final long j, final int i) throws SQLNotFoundException {
        final Campaign campaign = get(j);
        if (campaign == null) {
            throw new SQLNotFoundException("Campaign " + j + " not found");
        }
        transactionExecute(new Runnable() { // from class: com.xiaopeng.sota.sdk.manager.campaign.CampaignManager.3
            @Override // java.lang.Runnable
            public void run() {
                ContentValues contentValues = new ContentValues();
                contentValues.put("state", Integer.valueOf(i));
                contentValues.put("update_time", Long.valueOf(System.currentTimeMillis()));
                CampaignManager.this.getDatabase().update("campaign", contentValues, CampaignManager.this.whereWrapper("%s=?", "campaign_id"), CampaignManager.this.whereArgsWrapper(Long.valueOf(j)));
                campaign.setState(i);
                int i2 = i;
                if (i2 == 3 || i2 == 4) {
                    String str = CampaignManager.TAG;
                    Logger.d(str, "[" + j + "] Campaign has installed，remove it", new Object[0]);
                    CampaignManager.this.mCampaignCache.remove(Long.valueOf(j));
                }
            }
        });
    }

    public synchronized void delete(final Campaign campaign) {
        transactionExecute(new Runnable() { // from class: com.xiaopeng.sota.sdk.manager.campaign.CampaignManager.4
            @Override // java.lang.Runnable
            public void run() {
                CampaignManager.this.deleteUnsafe(campaign.getCampaignId().longValue());
                CampaignManager.this.mCampaignCache.remove(campaign.getCampaignId());
            }
        });
    }

    public synchronized void deleteAll() {
        transactionExecute(new Runnable() { // from class: com.xiaopeng.sota.sdk.manager.campaign.CampaignManager.5
            @Override // java.lang.Runnable
            public void run() {
                for (Long l : CampaignManager.this.mCampaignCache.keySet()) {
                    CampaignManager.this.deleteUnsafe(l.longValue());
                }
                CampaignManager.this.mCampaignCache.clear();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String mapToString() {
        LinkedHashMap<Long, Campaign> linkedHashMap = this.mCampaignCache;
        if (linkedHashMap == null || linkedHashMap.size() == 0) {
            return "CampaignMap{}";
        }
        StringBuilder sb = new StringBuilder("CampaignMap{");
        for (Map.Entry<Long, Campaign> entry : this.mCampaignCache.entrySet()) {
            sb.append(entry.getValue().toString());
        }
        sb.append('}');
        return sb.toString();
    }
}
