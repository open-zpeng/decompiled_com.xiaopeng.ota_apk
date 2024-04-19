package com.xiaopeng.ota.presenter.update;

import com.xiaopeng.fota.sdk.EcuType;
import com.xiaopeng.fota.sdk.UpgradeProgress;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.presenter.update.bean.UpgradeResult;
import com.xiaopeng.ota.utils.ThreadUtils;
/* loaded from: classes2.dex */
public class UpdateListenerInvoker implements UpdateListener {
    private UpdateListener mListener;

    public UpdateListenerInvoker(UpdateListener updateListener) {
        this.mListener = updateListener;
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onNewCampaign(final Campaign campaign) {
        if (this.mListener == null) {
            return;
        }
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$UpdateListenerInvoker$Xe9XqEluJVXsvRKuRf0YlWP2O7k
            @Override // java.lang.Runnable
            public final void run() {
                UpdateListenerInvoker.this.lambda$onNewCampaign$0$UpdateListenerInvoker(campaign);
            }
        });
    }

    public /* synthetic */ void lambda$onNewCampaign$0$UpdateListenerInvoker(Campaign campaign) {
        this.mListener.onNewCampaign(campaign);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onUsbNewCampaign(final Campaign campaign) {
        if (this.mListener == null) {
            return;
        }
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$UpdateListenerInvoker$KYrT6Z-CdTRyF5r_URhmAYp_N58
            @Override // java.lang.Runnable
            public final void run() {
                UpdateListenerInvoker.this.lambda$onUsbNewCampaign$1$UpdateListenerInvoker(campaign);
            }
        });
    }

    public /* synthetic */ void lambda$onUsbNewCampaign$1$UpdateListenerInvoker(Campaign campaign) {
        this.mListener.onUsbNewCampaign(campaign);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onWakeUp(final Campaign campaign) {
        if (this.mListener == null) {
            return;
        }
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$UpdateListenerInvoker$bbaxRrF4MQuue6swi_L6voImaig
            @Override // java.lang.Runnable
            public final void run() {
                UpdateListenerInvoker.this.lambda$onWakeUp$2$UpdateListenerInvoker(campaign);
            }
        });
    }

    public /* synthetic */ void lambda$onWakeUp$2$UpdateListenerInvoker(Campaign campaign) {
        this.mListener.onWakeUp(campaign);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onStart() {
        if (this.mListener == null) {
            return;
        }
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$UpdateListenerInvoker$N7nwkm_hyMI7ODVx_5veT7PFcsI
            @Override // java.lang.Runnable
            public final void run() {
                UpdateListenerInvoker.this.lambda$onStart$3$UpdateListenerInvoker();
            }
        });
    }

    public /* synthetic */ void lambda$onStart$3$UpdateListenerInvoker() {
        this.mListener.onStart();
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onPrepare(final EcuType ecuType, final UpgradeProgress upgradeProgress) {
        if (this.mListener == null) {
            return;
        }
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$UpdateListenerInvoker$VOyi2X24m9m-0-1ZOq7TfrFDSnk
            @Override // java.lang.Runnable
            public final void run() {
                UpdateListenerInvoker.this.lambda$onPrepare$4$UpdateListenerInvoker(ecuType, upgradeProgress);
            }
        });
    }

    public /* synthetic */ void lambda$onPrepare$4$UpdateListenerInvoker(EcuType ecuType, UpgradeProgress upgradeProgress) {
        this.mListener.onPrepare(ecuType, upgradeProgress);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onChecking(final boolean z) {
        if (this.mListener == null) {
            return;
        }
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$UpdateListenerInvoker$b4b9ZQGPrHlLJRcnW0pqhgFk4co
            @Override // java.lang.Runnable
            public final void run() {
                UpdateListenerInvoker.this.lambda$onChecking$5$UpdateListenerInvoker(z);
            }
        });
    }

    public /* synthetic */ void lambda$onChecking$5$UpdateListenerInvoker(boolean z) {
        this.mListener.onChecking(z);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onProgress(final EcuType ecuType, final UpgradeProgress upgradeProgress) {
        if (this.mListener == null) {
            return;
        }
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$UpdateListenerInvoker$4w_0s38rM5rRoGhjWzVyy8JFtlE
            @Override // java.lang.Runnable
            public final void run() {
                UpdateListenerInvoker.this.lambda$onProgress$6$UpdateListenerInvoker(ecuType, upgradeProgress);
            }
        });
    }

    public /* synthetic */ void lambda$onProgress$6$UpdateListenerInvoker(EcuType ecuType, UpgradeProgress upgradeProgress) {
        this.mListener.onProgress(ecuType, upgradeProgress);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onFinalizing(final EcuType ecuType, final UpgradeProgress upgradeProgress) {
        if (this.mListener == null) {
            return;
        }
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$UpdateListenerInvoker$fi7xKMnX0QzPHLubXWFfpQVHO1c
            @Override // java.lang.Runnable
            public final void run() {
                UpdateListenerInvoker.this.lambda$onFinalizing$7$UpdateListenerInvoker(ecuType, upgradeProgress);
            }
        });
    }

    public /* synthetic */ void lambda$onFinalizing$7$UpdateListenerInvoker(EcuType ecuType, UpgradeProgress upgradeProgress) {
        this.mListener.onFinalizing(ecuType, upgradeProgress);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onSuccess(final EcuType ecuType) {
        if (this.mListener == null) {
            return;
        }
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$UpdateListenerInvoker$s20byHgRM2y6_fbmO3dP9lyqshc
            @Override // java.lang.Runnable
            public final void run() {
                UpdateListenerInvoker.this.lambda$onSuccess$8$UpdateListenerInvoker(ecuType);
            }
        });
    }

    public /* synthetic */ void lambda$onSuccess$8$UpdateListenerInvoker(EcuType ecuType) {
        this.mListener.onSuccess(ecuType);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onError(final String str, final Exception exc) {
        if (this.mListener == null) {
            return;
        }
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$UpdateListenerInvoker$A4gRpztADztRRyeTf9WaLHG5IiY
            @Override // java.lang.Runnable
            public final void run() {
                UpdateListenerInvoker.this.lambda$onError$9$UpdateListenerInvoker(str, exc);
            }
        });
    }

    public /* synthetic */ void lambda$onError$9$UpdateListenerInvoker(String str, Exception exc) {
        this.mListener.onError(str, exc);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onCanceled(final long j) {
        if (this.mListener == null) {
            return;
        }
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$UpdateListenerInvoker$pLpQ9J9VOnBAtsW1bzp5x0t3z-Y
            @Override // java.lang.Runnable
            public final void run() {
                UpdateListenerInvoker.this.lambda$onCanceled$10$UpdateListenerInvoker(j);
            }
        });
    }

    public /* synthetic */ void lambda$onCanceled$10$UpdateListenerInvoker(long j) {
        this.mListener.onCanceled(j);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onConditionMiss(final long j, final UpgradeResult upgradeResult) {
        if (this.mListener == null) {
            return;
        }
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$UpdateListenerInvoker$Cu3YpxbFzSlbGBfbXffrWjzhIA8
            @Override // java.lang.Runnable
            public final void run() {
                UpdateListenerInvoker.this.lambda$onConditionMiss$11$UpdateListenerInvoker(j, upgradeResult);
            }
        });
    }

    public /* synthetic */ void lambda$onConditionMiss$11$UpdateListenerInvoker(long j, UpgradeResult upgradeResult) {
        this.mListener.onConditionMiss(j, upgradeResult);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onExpired(final long j) {
        if (this.mListener == null) {
            return;
        }
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$UpdateListenerInvoker$Ju0fRb-8goBqFc_bClXiW6m2ORY
            @Override // java.lang.Runnable
            public final void run() {
                UpdateListenerInvoker.this.lambda$onExpired$12$UpdateListenerInvoker(j);
            }
        });
    }

    public /* synthetic */ void lambda$onExpired$12$UpdateListenerInvoker(long j) {
        this.mListener.onExpired(j);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onCampaignCompleted(final long j, final boolean z) {
        if (this.mListener == null) {
            return;
        }
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$UpdateListenerInvoker$4EMIV-X1K6ttiioRBKq7Fj6BOKg
            @Override // java.lang.Runnable
            public final void run() {
                UpdateListenerInvoker.this.lambda$onCampaignCompleted$13$UpdateListenerInvoker(j, z);
            }
        });
    }

    public /* synthetic */ void lambda$onCampaignCompleted$13$UpdateListenerInvoker(long j, boolean z) {
        this.mListener.onCampaignCompleted(j, z);
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onEcuVersionChanged() {
        if (this.mListener == null) {
            return;
        }
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$UpdateListenerInvoker$c-weLoSPlYdx5Aq5Je4qKb-tBMc
            @Override // java.lang.Runnable
            public final void run() {
                UpdateListenerInvoker.this.lambda$onEcuVersionChanged$14$UpdateListenerInvoker();
            }
        });
    }

    public /* synthetic */ void lambda$onEcuVersionChanged$14$UpdateListenerInvoker() {
        this.mListener.onEcuVersionChanged();
    }

    @Override // com.xiaopeng.ota.presenter.update.UpdateListener
    public void onUsbProgress(final String str) {
        if (this.mListener == null) {
            return;
        }
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$UpdateListenerInvoker$bSAWf9egQnsTda2TfrAC-yAu2IQ
            @Override // java.lang.Runnable
            public final void run() {
                UpdateListenerInvoker.this.lambda$onUsbProgress$15$UpdateListenerInvoker(str);
            }
        });
    }

    public /* synthetic */ void lambda$onUsbProgress$15$UpdateListenerInvoker(String str) {
        this.mListener.onUsbProgress(str);
    }
}
