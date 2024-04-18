package com.xiaopeng.sota.sdk.manager.download;

import com.xiaopeng.ota.sdk.download.DownloadErrorException;
import com.xiaopeng.sota.sdk.manager.campaign.Campaign;
/* loaded from: classes2.dex */
public interface DownloadListener {
    void onCanceled(Campaign campaign);

    void onError(Campaign campaign, DownloadErrorException downloadErrorException);

    void onFailure(Campaign campaign, Exception exc);

    void onProgress(Campaign campaign, long j, long j2, int i);

    void onStarted(boolean z, Campaign campaign);

    void onSuccess(Campaign campaign);
}
