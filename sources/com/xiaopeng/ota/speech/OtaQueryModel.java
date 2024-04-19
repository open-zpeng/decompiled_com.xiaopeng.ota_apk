package com.xiaopeng.ota.speech;

import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.speech.protocol.query.ota.IOtaCaller;
/* loaded from: classes2.dex */
public class OtaQueryModel implements IOtaCaller {
    private static final String TAG = "OtaQueryModel";

    @Override // com.xiaopeng.speech.protocol.query.ota.IOtaCaller
    public boolean isLatestVersion() {
        boolean hasCampaign = OTAManager.getCampaignManager().hasCampaign();
        LogUtils.d(TAG, "latestVersion has campaign:" + hasCampaign);
        return !hasCampaign;
    }
}
