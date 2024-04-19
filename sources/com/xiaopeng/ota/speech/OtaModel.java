package com.xiaopeng.ota.speech;

import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.helper.ActivityHelper;
import com.xiaopeng.ota.helper.CampaignFeatureHelper;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.speech.protocol.node.ota.OtaListener;
/* loaded from: classes2.dex */
public class OtaModel implements OtaListener {
    private static final String TAG = "OtaModel";

    @Override // com.xiaopeng.speech.protocol.node.ota.OtaListener
    public void onOpenOtaPage() {
        LogUtils.d(TAG, "onOpenOtaPage");
        ActivityHelper.startFragment(OTAManager.getContext(), CampaignFeatureHelper.getMainFragmentClass());
    }

    @Override // com.xiaopeng.speech.protocol.node.ota.OtaListener
    public void onOpenReservationPage() {
        LogUtils.d(TAG, "onOpenReservationPage");
        ActivityHelper.startFragment(OTAManager.getContext(), CampaignFeatureHelper.getScheduleFragmentClass());
    }
}
