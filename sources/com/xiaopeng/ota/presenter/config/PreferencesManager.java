package com.xiaopeng.ota.presenter.config;

import android.content.Context;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.PreferencesUtils;
/* loaded from: classes2.dex */
public class PreferencesManager {
    private static final String KEY_AUTO_UPGRADE_FLAG = "AUTO_UPGRADE_FLAG";
    private static final String KEY_CAMPAIGN_ID = "CAMPAIGN_ID";
    private static final String KEY_UE_STATE = "CDU_UE_STATE";
    private static final String TAG = "PreferencesManager";
    private Context mContext;

    public void init() {
    }

    public PreferencesManager(Context context) {
        this.mContext = context;
    }

    public short getUeState(int i) {
        Context context = this.mContext;
        short s = (short) PreferencesUtils.getInt(context, KEY_UE_STATE + i, 0);
        LogUtils.d(TAG, "getUeState: " + ((int) s));
        return s;
    }

    public void setUeState(int i, short s) {
        Context context = this.mContext;
        PreferencesUtils.putInt(context, KEY_UE_STATE + i, s);
        LogUtils.d(TAG, "setUeState: " + ((int) getUeState(i)));
    }

    public long getCampaignId(int i) {
        Context context = this.mContext;
        long j = PreferencesUtils.getLong(context, KEY_CAMPAIGN_ID + i, 0L);
        LogUtils.d(TAG, "getCampaignId: " + j);
        return j;
    }

    public void setCampaignId(int i, long j) {
        Context context = this.mContext;
        PreferencesUtils.putLong(context, KEY_CAMPAIGN_ID + i, j);
        LogUtils.d(TAG, "setCampaignId: " + getCampaignId(i));
    }

    public void setAutoUpgrade(boolean z) {
        PreferencesUtils.putBoolean(this.mContext, KEY_AUTO_UPGRADE_FLAG, z);
    }

    public boolean supportAutoUpgrade() {
        return PreferencesUtils.getBoolean(this.mContext, KEY_AUTO_UPGRADE_FLAG, false);
    }
}
