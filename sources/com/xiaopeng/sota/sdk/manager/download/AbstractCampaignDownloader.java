package com.xiaopeng.sota.sdk.manager.download;

import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.sdk.download.DownloadCanceledException;
import com.xiaopeng.ota.sdk.download.DownloadErrorException;
import com.xiaopeng.ota.sdk.download.FileDownloader;
import com.xiaopeng.sota.sdk.manager.campaign.Campaign;
import java.io.File;
/* loaded from: classes2.dex */
public abstract class AbstractCampaignDownloader {
    private static final int FIRST_HASH_SIZE = 10240;
    private static final String TAG = "CampaignDownloader";
    protected Campaign mCampaign;
    private byte[] mFirstPartBytes = new byte[10240];
    private int mFirstPartReadIndex = 0;
    private boolean mFirstPartCheckPressed = false;
    private int mProgress = 0;
    private FileDownloadListener mDownloadListener = new FileDownloadListener();

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkFirstHashPart(File file) {
        return true;
    }

    abstract boolean isCanceled();

    abstract void onProgress(Campaign campaign, long j, long j2, int i);

    public AbstractCampaignDownloader(Campaign campaign) {
        this.mCampaign = campaign;
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x01af, code lost:
        r4 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01b0, code lost:
        r15 = r15 + 1;
        r4 = r4;
     */
    /* JADX WARN: Type inference failed for: r4v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.io.File execute(java.lang.String r15) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 437
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.sota.sdk.manager.download.AbstractCampaignDownloader.execute(java.lang.String):java.io.File");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean feedFirstPartBytes(byte[] bArr, int i) {
        int i2 = this.mFirstPartReadIndex;
        if (i2 < 10240) {
            int min = Math.min(i, 10240 - i2);
            System.arraycopy(bArr, 0, this.mFirstPartBytes, this.mFirstPartReadIndex, min);
            this.mFirstPartReadIndex += min;
        }
        return this.mFirstPartReadIndex >= 10240;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkDownloadCanceled() {
        if (isCanceled()) {
            throw new DownloadCanceledException();
        }
    }

    /* loaded from: classes2.dex */
    class FileDownloadListener implements FileDownloader.Listener {
        @Override // com.xiaopeng.ota.sdk.download.FileDownloader.Listener
        public void onCanceled() {
        }

        @Override // com.xiaopeng.ota.sdk.download.FileDownloader.Listener
        public void onError(DownloadErrorException downloadErrorException) {
        }

        @Override // com.xiaopeng.ota.sdk.download.FileDownloader.Listener
        public void onFailure(Exception exc) {
        }

        @Override // com.xiaopeng.ota.sdk.download.FileDownloader.Listener
        public void onSuccess(File file) {
        }

        FileDownloadListener() {
        }

        @Override // com.xiaopeng.ota.sdk.download.FileDownloader.Listener
        public void onWillStart() {
            AbstractCampaignDownloader.this.checkDownloadCanceled();
            Logger.d(AbstractCampaignDownloader.TAG, "[" + AbstractCampaignDownloader.this.mCampaign.getCampaignId() + "] Download package file will start", new Object[0]);
        }

        @Override // com.xiaopeng.ota.sdk.download.FileDownloader.Listener
        public void onStarted(long j) {
            AbstractCampaignDownloader.this.checkDownloadCanceled();
            Logger.d(AbstractCampaignDownloader.TAG, "[" + AbstractCampaignDownloader.this.mCampaign.getCampaignId() + "] Download package file started size=" + j, new Object[0]);
        }

        @Override // com.xiaopeng.ota.sdk.download.FileDownloader.Listener
        public void onProgress(long j, long j2, byte[] bArr, int i) {
            AbstractCampaignDownloader.this.checkDownloadCanceled();
            int i2 = (int) ((100 * j2) / j);
            if (i2 <= AbstractCampaignDownloader.this.mProgress) {
                if (i2 < AbstractCampaignDownloader.this.mProgress) {
                    AbstractCampaignDownloader.this.mProgress = i2;
                }
            } else {
                AbstractCampaignDownloader abstractCampaignDownloader = AbstractCampaignDownloader.this;
                abstractCampaignDownloader.onProgress(abstractCampaignDownloader.mCampaign, j2, j, i2);
                AbstractCampaignDownloader.this.mProgress = i2;
            }
            if (AbstractCampaignDownloader.this.mFirstPartCheckPressed || !AbstractCampaignDownloader.this.feedFirstPartBytes(bArr, i)) {
                return;
            }
            if (AbstractCampaignDownloader.this.checkFirstHashPart(null)) {
                AbstractCampaignDownloader.this.mFirstPartCheckPressed = true;
                return;
            }
            throw new IllegalArgumentException("First part signature invalid");
        }
    }
}
