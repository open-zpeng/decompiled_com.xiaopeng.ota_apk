package com.xiaopeng.ota.presenter.update;

import com.xiaopeng.fota.sdk.EcuType;
import com.xiaopeng.fota.sdk.UpgradeProgress;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.presenter.update.bean.UpgradeResult;
/* loaded from: classes2.dex */
public interface UpdateListener {
    void onCampaignCompleted(long j, boolean z);

    void onCanceled(long j);

    void onChecking(boolean z);

    void onConditionMiss(long j, UpgradeResult upgradeResult);

    void onEcuVersionChanged();

    void onError(String str, Exception exc);

    void onExpired(long j);

    void onFinalizing(EcuType ecuType, UpgradeProgress upgradeProgress);

    void onNewCampaign(Campaign campaign);

    void onPrepare(EcuType ecuType, UpgradeProgress upgradeProgress);

    void onProgress(EcuType ecuType, UpgradeProgress upgradeProgress);

    void onStart();

    void onSuccess(EcuType ecuType);

    void onUsbNewCampaign(Campaign campaign);

    void onUsbProgress(String str);

    void onWakeUp(Campaign campaign);
}
