package com.xiaopeng.ota.presenter.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.xiaopeng.fota.sdk.UpgradeUtils;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.utils.IoUtils;
import com.xiaopeng.ota.utils.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class CampaignManager extends AbstractRepository {
    private static final String TAG = "CampaignManager";
    private Campaign mActiveCampaign;

    public CampaignManager(Context context) {
        super(context);
    }

    @Override // com.xiaopeng.ota.presenter.db.AbstractRepository
    public void init() {
        super.init();
        this.mActiveCampaign = queryOne(String.format("%s=?", Campaign.ACTIVE_FLAG), 1);
        LogUtils.d(TAG, "init: " + JsonUtils.toJson(this.mActiveCampaign));
    }

    private synchronized Campaign queryOne(String str, Object... objArr) {
        List<Campaign> query;
        query = query(1, str, objArr);
        return query.isEmpty() ? null : query.get(0);
    }

    private List<Campaign> query(Integer num, String str, Object... objArr) {
        return queryInternal(num, whereWrapper(str, new Object[0]), whereArgsWrapper(objArr));
    }

    private synchronized List<Campaign> queryInternal(Integer num, String str, String... strArr) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Cursor query = getDatabase().query("campaign", null, str, strArr, null, null, "_id DESC", num == null ? null : String.valueOf(num));
        while (query.moveToNext()) {
            Campaign campaign = (Campaign) JsonUtils.fromJson(query.getString(query.getColumnIndex(Campaign.CAMPAIGN_JSON)), (Class<Object>) Campaign.class);
            try {
                long j = query.getLong(query.getColumnIndex(Campaign.INSTALLED_TIME));
                if (j > 0) {
                    campaign.setInstalledTime(j);
                }
            } catch (Exception unused) {
                campaign.setInstalledTime(0L);
            }
            campaign.setId(query.getLong(query.getColumnIndex("_id")));
            campaign.setCampaignId(query.getLong(query.getColumnIndex("campaign_id")));
            boolean z = false;
            campaign.setActive(query.getInt(query.getColumnIndex(Campaign.ACTIVE_FLAG)) == 1);
            if (query.getInt(query.getColumnIndex(Campaign.INSTALLED_FALG)) == 1) {
                z = true;
            }
            campaign.setInstalled(z);
            arrayList.add(campaign);
        }
        IoUtils.close(query);
        return arrayList;
    }

    public boolean save(Campaign campaign) {
        Campaign campaign2 = this.mActiveCampaign;
        if (campaign2 != null && campaign2.getCampaignId() == campaign.getCampaignId()) {
            campaign.setId(this.mActiveCampaign.getId());
            campaign.setActive(true);
            update(campaign);
            return false;
        }
        add(campaign);
        return true;
    }

    public synchronized void add(final Campaign campaign) {
        transactionExecute(new Runnable() { // from class: com.xiaopeng.ota.presenter.db.-$$Lambda$CampaignManager$AkGoMpsiG7AHskZSiO_0uGvfyEI
            @Override // java.lang.Runnable
            public final void run() {
                CampaignManager.this.lambda$add$0$CampaignManager(campaign);
            }
        });
    }

    public /* synthetic */ void lambda$add$0$CampaignManager(Campaign campaign) {
        inactiveAll();
        LogUtils.d(TAG, "before add:" + JsonUtils.toJson(campaign));
        campaign.setActive(true);
        long addUnsafe = addUnsafe(campaign);
        if (addUnsafe <= 0) {
            LogUtils.e(TAG, "Insert campaign fail, code: " + addUnsafe);
        }
        campaign.setId(addUnsafe);
        this.mActiveCampaign = campaign;
    }

    private long addUnsafe(Campaign campaign) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("campaign_id", Long.valueOf(campaign.getCampaignId()));
        contentValues.put(Campaign.ACTIVE_FLAG, Integer.valueOf(campaign.isActive() ? 1 : 0));
        contentValues.put(Campaign.CAMPAIGN_JSON, JsonUtils.toJson(campaign));
        contentValues.put("create_time", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("update_time", Long.valueOf(System.currentTimeMillis()));
        long insert = getDatabase().insert("campaign", null, contentValues);
        LogUtils.d(TAG, "addUnsafe, rid: " + insert);
        return insert;
    }

    public synchronized void cancelActive() {
        transactionExecute(new Runnable() { // from class: com.xiaopeng.ota.presenter.db.-$$Lambda$CampaignManager$EaNd4lFvMlilwpm58nthtrE2t00
            @Override // java.lang.Runnable
            public final void run() {
                CampaignManager.this.lambda$cancelActive$1$CampaignManager();
            }
        });
    }

    public /* synthetic */ void lambda$cancelActive$1$CampaignManager() {
        Campaign campaign = this.mActiveCampaign;
        if (campaign == null) {
            return;
        }
        deleteUnsafe(campaign.getCampaignId());
        this.mActiveCampaign = null;
    }

    private void deleteUnsafe(long j) {
        LogUtils.w(TAG, "delete campaign(%d)", Long.valueOf(j));
        getDatabase().delete("campaign", whereWrapper("%s=?", "campaign_id"), whereArgsWrapper(Long.valueOf(j)));
    }

    public void deleteAll() {
        getDatabase().delete("campaign", "1 = 1", null);
    }

    private void inactiveAll() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Campaign.ACTIVE_FLAG, (Integer) 0);
        getDatabase().update("campaign", contentValues, null, null);
    }

    public synchronized void update(final Campaign campaign) {
        transactionExecute(new Runnable() { // from class: com.xiaopeng.ota.presenter.db.-$$Lambda$CampaignManager$b6bc7vV_T7EaN3nCq1hzeik5YlY
            @Override // java.lang.Runnable
            public final void run() {
                CampaignManager.this.lambda$update$2$CampaignManager(campaign);
            }
        });
    }

    public /* synthetic */ void lambda$update$2$CampaignManager(Campaign campaign) {
        LogUtils.d(TAG, "before update:" + JsonUtils.toJson(campaign));
        updateUnsafe(campaign);
        Campaign campaign2 = this.mActiveCampaign;
        if (campaign2 == null || campaign2.getCampaignId() != campaign.getCampaignId()) {
            return;
        }
        this.mActiveCampaign = campaign;
    }

    private void updateUnsafe(Campaign campaign) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Campaign.ACTIVE_FLAG, Integer.valueOf(campaign.isActive() ? 1 : 0));
        contentValues.put(Campaign.CAMPAIGN_JSON, JsonUtils.toJson(campaign));
        contentValues.put("update_time", Long.valueOf(System.currentTimeMillis()));
        getDatabase().update("campaign", contentValues, whereWrapper("%s=?", "_id"), whereArgsWrapper(Long.valueOf(campaign.getId())));
    }

    public synchronized void campaignCompleted(final long j) {
        transactionExecute(new Runnable() { // from class: com.xiaopeng.ota.presenter.db.-$$Lambda$CampaignManager$5vkxeZwsG8X8xP0zmUPR7JzjjBs
            @Override // java.lang.Runnable
            public final void run() {
                CampaignManager.this.lambda$campaignCompleted$3$CampaignManager(j);
            }
        });
    }

    public /* synthetic */ void lambda$campaignCompleted$3$CampaignManager(long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Campaign.ACTIVE_FLAG, (Integer) 0);
        contentValues.put(Campaign.INSTALLED_FALG, (Integer) 1);
        contentValues.put(Campaign.INSTALLED_TIME, Long.valueOf(System.currentTimeMillis()));
        contentValues.put("update_time", Long.valueOf(System.currentTimeMillis()));
        getDatabase().delete("campaign", whereWrapper("%s=? and %s=?", "campaign_id", Campaign.INSTALLED_FALG), whereArgsWrapper(Long.valueOf(j), 1));
        getDatabase().update("campaign", contentValues, whereWrapper("%s=?", "campaign_id"), whereArgsWrapper(Long.valueOf(j)));
        Campaign campaign = this.mActiveCampaign;
        if (campaign == null || campaign.getCampaignId() != j) {
            return;
        }
        this.mActiveCampaign = null;
    }

    public Campaign getActiveCampaign() {
        return this.mActiveCampaign;
    }

    public Campaign getActiveWaitingCampaign() {
        if (hasWaitingCampaign()) {
            return getActiveCampaign();
        }
        return null;
    }

    public boolean hasWaitingCampaign() {
        if (getActiveCampaign() != null) {
            int OtaGetCampaignStatus = UpgradeUtils.OtaGetCampaignStatus();
            LogUtils.d(TAG, "Campaign status: %d", Integer.valueOf(OtaGetCampaignStatus));
            return 7 == OtaGetCampaignStatus;
        }
        return false;
    }

    public long getActiveCampaignId() {
        Campaign activeCampaign = getActiveCampaign();
        if (activeCampaign != null) {
            return activeCampaign.getCampaignId();
        }
        return 0L;
    }

    public List<Campaign> getAllHistoryCampaign() {
        return query(null, String.format("%s=? and %s=?", Campaign.ACTIVE_FLAG, Campaign.INSTALLED_FALG), 0, 1);
    }

    public Campaign getCurInstalledCampaign() {
        return queryOne(String.format("%s=?", Campaign.INSTALLED_FALG), 1);
    }

    public Campaign getCampaign(long j) {
        return queryOne(String.format("%s=?", "campaign_id"), Long.valueOf(j));
    }

    public Campaign getLastInvalidCampaign() {
        return queryOne(String.format("%s=? and %s=?", Campaign.ACTIVE_FLAG, Campaign.INSTALLED_FALG), 0, 0);
    }

    public boolean hasCampaign() {
        if (getActiveCampaign() != null) {
            int OtaGetCampaignStatus = UpgradeUtils.OtaGetCampaignStatus();
            return OtaGetCampaignStatus == 7 || OtaGetCampaignStatus == 8 || OtaGetCampaignStatus == 9;
        }
        return false;
    }
}
