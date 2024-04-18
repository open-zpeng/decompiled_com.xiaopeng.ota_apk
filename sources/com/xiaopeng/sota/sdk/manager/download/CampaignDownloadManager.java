package com.xiaopeng.sota.sdk.manager.download;

import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.sdk.common.util.FileUtils;
import com.xiaopeng.ota.sdk.download.DownloadErrorException;
import com.xiaopeng.sota.sdk.manager.campaign.Campaign;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class CampaignDownloadManager {
    private static final String TAG = "CampaignDownloadManager";
    private String mDownloadFolder;
    private ConcurrentHashMap<Long, CampaignDownloader> mDownloadTasks = new ConcurrentHashMap<>();
    private ExecutorService mExecutorService;

    public CampaignDownloadManager(String str, int i) {
        this.mDownloadFolder = str;
        this.mExecutorService = new ThreadPoolExecutor(0, i, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        FileUtils.mkdirsIfNotExist(this.mDownloadFolder);
    }

    public synchronized void download(Campaign campaign, DownloadListener downloadListener) {
        CampaignDownloader campaignDownloader = this.mDownloadTasks.get(campaign.getCampaignId());
        if (campaignDownloader != null) {
            if (campaignDownloader.getCampaign().equals(campaign)) {
                Logger.d(TAG, "[" + campaign.getCampaignId() + "]Campaign downloading", new Object[0]);
                return;
            }
            Logger.d(TAG, "[" + campaign.getCampaignId() + "]Campaign changed and canceled downloading task", new Object[0]);
            campaignDownloader.cancel();
        }
        Logger.d(TAG, "[" + campaign.getCampaignId() + "]Start campaign download task", new Object[0]);
        CampaignDownloader campaignDownloader2 = new CampaignDownloader(this.mDownloadFolder, campaign, new DownloadListenerDecorator(downloadListener));
        this.mDownloadTasks.put(campaign.getCampaignId(), campaignDownloader2);
        this.mExecutorService.execute(campaignDownloader2);
    }

    public int size() {
        return this.mDownloadTasks.size();
    }

    public void cancel(Campaign campaign, long j) {
        synchronized (this) {
            Logger.d(TAG, "[" + campaign.getCampaignId() + "]Cancel campaign download task", new Object[0]);
            CampaignDownloader campaignDownloader = this.mDownloadTasks.get(campaign.getCampaignId());
            if (campaignDownloader != null && campaignDownloader.getCampaign().equals(campaign)) {
                campaignDownloader.cancel();
            }
        }
        awaitIdle(j);
    }

    public void cancelAll(long j) {
        synchronized (this) {
            for (Map.Entry<Long, CampaignDownloader> entry : this.mDownloadTasks.entrySet()) {
                Logger.d(TAG, "[" + entry.getKey() + "]Cancel campaign download task", new Object[0]);
                entry.getValue().cancel();
            }
        }
        awaitIdle(j);
    }

    public synchronized void clean() {
        if (this.mDownloadTasks.size() > 0) {
            throw new IllegalStateException("Download task queue nonempty");
        }
        for (File file : new File(this.mDownloadFolder).listFiles()) {
            FileUtils.delete(file);
        }
    }

    private void awaitIdle(long j) {
        while (j > 0 && this.mDownloadTasks.size() > 0) {
            try {
                Thread.sleep(1000L);
            } catch (Exception unused) {
            }
            j -= 1000;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void remove(Campaign campaign) {
        CampaignDownloader campaignDownloader = this.mDownloadTasks.get(campaign.getCampaignId());
        if (campaignDownloader != null && campaignDownloader.getCampaign().equals(campaign)) {
            this.mDownloadTasks.remove(campaign.getCampaignId());
        }
    }

    /* loaded from: classes2.dex */
    class DownloadListenerDecorator implements DownloadListener {
        private DownloadListener mListener;

        public DownloadListenerDecorator(DownloadListener downloadListener) {
            this.mListener = downloadListener;
        }

        @Override // com.xiaopeng.sota.sdk.manager.download.DownloadListener
        public void onStarted(boolean z, Campaign campaign) {
            this.mListener.onStarted(z, campaign);
        }

        @Override // com.xiaopeng.sota.sdk.manager.download.DownloadListener
        public void onProgress(Campaign campaign, long j, long j2, int i) {
            this.mListener.onProgress(campaign, j, j2, i);
        }

        @Override // com.xiaopeng.sota.sdk.manager.download.DownloadListener
        public void onCanceled(Campaign campaign) {
            CampaignDownloadManager.this.remove(campaign);
            this.mListener.onCanceled(campaign);
        }

        @Override // com.xiaopeng.sota.sdk.manager.download.DownloadListener
        public void onFailure(Campaign campaign, Exception exc) {
            CampaignDownloadManager.this.remove(campaign);
            this.mListener.onFailure(campaign, exc);
        }

        @Override // com.xiaopeng.sota.sdk.manager.download.DownloadListener
        public void onError(Campaign campaign, DownloadErrorException downloadErrorException) {
            CampaignDownloadManager.this.remove(campaign);
            this.mListener.onError(campaign, downloadErrorException);
        }

        @Override // com.xiaopeng.sota.sdk.manager.download.DownloadListener
        public void onSuccess(Campaign campaign) {
            CampaignDownloadManager.this.remove(campaign);
            this.mListener.onSuccess(campaign);
        }
    }

    public String getDownloadPath() {
        return this.mDownloadFolder;
    }

    public String getOutputPath(Campaign campaign) {
        return CampaignDownloader.getOutputPath(this.mDownloadFolder, campaign.getCampaignPackage());
    }

    public File getOutputFile(Campaign campaign) {
        return CampaignDownloader.getOutputFile(this.mDownloadFolder, campaign.getCampaignPackage());
    }
}
