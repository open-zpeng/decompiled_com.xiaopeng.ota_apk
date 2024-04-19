package com.xiaopeng.sota.sdk.manager.download;

import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.sdk.download.DownloadCanceledException;
import com.xiaopeng.ota.sdk.download.DownloadErrorException;
import com.xiaopeng.sota.sdk.manager.campaign.Campaign;
import com.xiaopeng.sota.sdk.manager.campaign.CampaignPackage;
import java.io.File;
/* loaded from: classes2.dex */
public class CampaignDownloader extends AbstractCampaignDownloader implements Runnable {
    private static final String TAG = "CampaignDownloader";
    private volatile boolean mCanceled;
    private String mDownloadFolder;
    private DownloadListener mDownloadListener;

    public CampaignDownloader(String str, Campaign campaign, DownloadListener downloadListener) {
        super(campaign);
        this.mCampaign = campaign;
        this.mDownloadFolder = str;
        this.mDownloadListener = downloadListener;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            String outputPath = getOutputPath(this.mDownloadFolder, this.mCampaign.getCampaignPackage());
            boolean z = true;
            File file = new File(outputPath);
            if (!file.exists() || !file.isFile()) {
                z = false;
            }
            this.mDownloadListener.onStarted(z, this.mCampaign);
            execute(outputPath);
            this.mDownloadListener.onSuccess(this.mCampaign);
        } catch (DownloadCanceledException unused) {
            Logger.d(TAG, "[" + this.mCampaign.getCampaignId() + "]Package file download canceled", new Object[0]);
            this.mDownloadListener.onCanceled(this.mCampaign);
        } catch (DownloadErrorException e) {
            Logger.d(TAG, "[" + this.mCampaign.getCampaignId() + "]Package file download error", new Object[0]);
            this.mDownloadListener.onError(this.mCampaign, e);
        } catch (Exception e2) {
            Logger.d(TAG, "[" + this.mCampaign.getCampaignId() + "]Package file download exception", new Object[0]);
            this.mDownloadListener.onFailure(this.mCampaign, e2);
        }
    }

    public void cancel() {
        this.mCanceled = true;
    }

    public Campaign getCampaign() {
        return this.mCampaign;
    }

    public static String getOutputPath(String str, CampaignPackage campaignPackage) {
        return str + "/" + getOutputFileName(campaignPackage);
    }

    public static File getOutputFile(String str, CampaignPackage campaignPackage) {
        return new File(getOutputPath(str, campaignPackage));
    }

    public static String getOutputFileName(CampaignPackage campaignPackage) {
        return campaignPackage.getHash();
    }

    @Override // com.xiaopeng.sota.sdk.manager.download.AbstractCampaignDownloader
    boolean isCanceled() {
        return this.mCanceled;
    }

    @Override // com.xiaopeng.sota.sdk.manager.download.AbstractCampaignDownloader
    void onProgress(Campaign campaign, long j, long j2, int i) {
        this.mDownloadListener.onProgress(this.mCampaign, j, j2, i);
    }
}
