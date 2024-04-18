package com.xiaopeng.sota.sdk;

import android.content.pm.PackageInfo;
import android.text.TextUtils;
import android.util.Log;
import com.lzy.okgo.OkGo;
import com.xiaopeng.lib.utils.MD5Utils;
import com.xiaopeng.ota.sdk.campaign.event.NetworkConnectedEvent;
import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.sdk.common.util.RetryTimerLatch;
import com.xiaopeng.ota.sdk.download.DownloadErrorException;
import com.xiaopeng.ota.sdk.eventbroker.Subscribe;
import com.xiaopeng.ota.sdk.eventbroker.SubscribeTimeout;
import com.xiaopeng.ota.sdk.eventbroker.ThreadMode;
import com.xiaopeng.ota.sdk.exception.BusyException;
import com.xiaopeng.sota.sdk.manager.campaign.Campaign;
import com.xiaopeng.sota.sdk.manager.campaign.CampaignEvent;
import com.xiaopeng.sota.sdk.manager.campaign.CampaignPackage;
import com.xiaopeng.sota.sdk.manager.download.DownloadListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingDeque;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class CampaignChecker {
    private static final String TAG = "CampaignChecker";
    private String mCachedCheckSource;
    private PackageInfo mCachedPackageInfo;
    private List<PackageInfo> mCachedPackageList;
    private volatile boolean mCheckingUpdate;
    private BlockingDeque<Campaign> mDownloadQueue;
    private long mLastCheckTime;
    private RetryTimerLatch mRetryTimerLatch = new RetryTimerLatch("SOTACampaignChecker", 10, RetryTimerLatch.MINUTE_1, 30 * RetryTimerLatch.MINUTE_1);

    public CampaignChecker() {
        SOTAEventBroker.getEventBroker().register(this);
    }

    public void dispose() {
        SOTAEventBroker.getEventBroker().unregister(this);
    }

    @SubscribeTimeout(timeout = OkGo.DEFAULT_MILLISECONDS)
    public void onCheckTimer() {
        if (this.mRetryTimerLatch.isActive()) {
            long check = this.mRetryTimerLatch.check();
            if (check > 0) {
                Logger.d(TAG, "Check timeout wait (%ds)", Long.valueOf(check / 1000));
                return;
            }
            Logger.d(TAG, "Check campaign timeout", new Object[0]);
            if (this.mRetryTimerLatch.increase()) {
                PackageInfo packageInfo = this.mCachedPackageInfo;
                if (packageInfo != null) {
                    checkUpdate(packageInfo, this.mCachedPackageList, this.mCachedCheckSource);
                } else {
                    resumeDownload();
                }
            }
        }
    }

    public void checkUpdate(final PackageInfo packageInfo, final List<PackageInfo> list, final String str) {
        SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.CampaignChecker.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    try {
                        CampaignChecker.this.checkUpdateSync(packageInfo, list, str);
                    } catch (BusyException e) {
                        Logger.w(CampaignChecker.TAG, e, "Checking for updates, ignore", new Object[0]);
                    }
                } finally {
                    SOTAManager.getListenerInvoker().fireCampaignCheckResult(false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:56:0x013a A[Catch: all -> 0x010c, TryCatch #7 {, blocks: (B:10:0x0045, B:12:0x0049, B:20:0x0077, B:21:0x0079, B:67:0x01b2, B:68:0x01b4, B:72:0x01b9, B:73:0x01be, B:14:0x0053, B:15:0x005b, B:17:0x005f, B:19:0x0069, B:23:0x007b, B:25:0x0081, B:26:0x0093, B:28:0x0099, B:30:0x00a1, B:32:0x00a7, B:33:0x00c6, B:34:0x00d0, B:35:0x00d3, B:56:0x013a, B:57:0x0161, B:59:0x0167, B:61:0x0173, B:62:0x0183, B:63:0x0194, B:65:0x01a5, B:66:0x01a9, B:51:0x0111, B:54:0x0124, B:41:0x0100), top: B:83:0x0045 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01a9 A[Catch: all -> 0x010c, TRY_LEAVE, TryCatch #7 {, blocks: (B:10:0x0045, B:12:0x0049, B:20:0x0077, B:21:0x0079, B:67:0x01b2, B:68:0x01b4, B:72:0x01b9, B:73:0x01be, B:14:0x0053, B:15:0x005b, B:17:0x005f, B:19:0x0069, B:23:0x007b, B:25:0x0081, B:26:0x0093, B:28:0x0099, B:30:0x00a1, B:32:0x00a7, B:33:0x00c6, B:34:0x00d0, B:35:0x00d3, B:56:0x013a, B:57:0x0161, B:59:0x0167, B:61:0x0173, B:62:0x0183, B:63:0x0194, B:65:0x01a5, B:66:0x01a9, B:51:0x0111, B:54:0x0124, B:41:0x0100), top: B:83:0x0045 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void checkUpdateSync(android.content.pm.PackageInfo r12, java.util.List<android.content.pm.PackageInfo> r13, java.lang.String r14) throws com.xiaopeng.ota.sdk.exception.BusyException {
        /*
            Method dump skipped, instructions count: 474
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.sota.sdk.CampaignChecker.checkUpdateSync(android.content.pm.PackageInfo, java.util.List, java.lang.String):void");
    }

    private void printVersionInfo(PackageInfo packageInfo) {
        Logger.d(TAG, "App[%s] versionCode=%d, versionName=%s", packageInfo.packageName, Integer.valueOf(packageInfo.versionCode), packageInfo.versionName);
    }

    private void printVersionInfo(List<PackageInfo> list) {
        for (PackageInfo packageInfo : list) {
            printVersionInfo(packageInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadCampaign(Campaign campaign) {
        int i = 0;
        Logger.d(TAG, "[" + campaign.getCampaignId() + "]Start download file", new Object[0]);
        File outputFile = SOTAManager.getCampaignDownloadManager().getOutputFile(campaign);
        if (outputFile.exists() && outputFile.isFile()) {
            try {
                String fileMd5 = MD5Utils.getFileMd5(outputFile);
                if (!TextUtils.isEmpty(fileMd5) && fileMd5.equals(campaign.getCampaignPackage().getHash())) {
                    Logger.d(TAG, "[" + campaign.getCampaignId() + "]Campaign package(%s) has downloaded", campaign.getCampaignPackage().getPackageName());
                    i = 1;
                } else {
                    Logger.d(TAG, "[" + campaign.getCampaignId() + "] file md5 not match", new Object[0]);
                }
            } catch (FileNotFoundException e) {
                Logger.w(TAG, e, "Can't get md5 of campaignId=%d's file", new Object[i]);
            }
        }
        if (i == 0) {
            SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.START, CampaignEvent.State.OK));
            SOTAManager.getCampaignDownloadManager().download(campaign, new CampaignDownloadListener());
            return;
        }
        SOTAManager.getCampaignManager().updateState(campaign.getCampaignId().longValue(), 10);
        SOTAManager.getListenerInvoker().fireDownloadEnd(campaign.getCampaignPackage().getPackageName());
        Campaign poll = this.mDownloadQueue.poll();
        if (poll != null) {
            downloadCampaign(poll);
            return;
        }
        this.mRetryTimerLatch.inactivate();
        SOTAManager.getListenerInvoker().fireDownloadComplete();
    }

    @Subscribe(events = {NetworkConnectedEvent.class}, threadMode = ThreadMode.THREAD)
    public void onReceiveNetworkEvent(NetworkConnectedEvent networkConnectedEvent) {
        int type = networkConnectedEvent.getType();
        if (type != -1) {
            if (type == 0 || type == 1) {
                resumeDownload();
            }
        }
    }

    public void resumeDownload() {
        Campaign poll;
        BlockingDeque<Campaign> blockingDeque = this.mDownloadQueue;
        if (blockingDeque == null || blockingDeque.size() <= 0 || SOTAManager.getCampaignDownloadManager().size() != 0 || (poll = this.mDownloadQueue.poll()) == null) {
            return;
        }
        Logger.d(TAG, "[" + poll.getCampaignId() + "]Resume download", new Object[0]);
        downloadCampaign(poll);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class CampaignDownloadListener implements DownloadListener {
        private CampaignDownloadListener() {
        }

        @Override // com.xiaopeng.sota.sdk.manager.download.DownloadListener
        public void onStarted(boolean z, Campaign campaign) {
            Logger.d(CampaignChecker.TAG, "[" + campaign.getCampaignId() + "]Download started...", new Object[0]);
            SOTAManager.getCampaignManager().updateState(campaign.getCampaignId().longValue(), 1);
            if (!z) {
                SOTAManager.getListenerInvoker().fireDownloadStart(campaign.getCampaignPackage().getPackageName(), campaign.getCampaignPackage().getSize().longValue());
            }
            SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.DOWNLOAD, CampaignEvent.State.INIT));
        }

        @Override // com.xiaopeng.sota.sdk.manager.download.DownloadListener
        public void onSuccess(Campaign campaign) {
            Logger.d(CampaignChecker.TAG, "[" + campaign.getCampaignId() + "]Download completed", new Object[0]);
            SOTAManager.getCampaignManager().updateState(campaign.getCampaignId().longValue(), 10);
            SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.DOWNLOAD, CampaignEvent.State.OK));
            SOTAManager.getListenerInvoker().fireDownloadEnd(campaign.getCampaignPackage().getPackageName());
            if (!SOTAManager.getCampaignManager().allCampaignDownloaded()) {
                Campaign campaign2 = (Campaign) CampaignChecker.this.mDownloadQueue.poll();
                if (campaign2 != null) {
                    CampaignChecker.this.downloadCampaign(campaign2);
                    return;
                }
                return;
            }
            SOTAManager.getListenerInvoker().fireDownloadComplete();
            List<Campaign> allAvailable = SOTAManager.getCampaignManager().getAllAvailable();
            if (SOTAManager.getCampaignManager().getUpgradeMode() == 0) {
                Logger.d(CampaignChecker.TAG, "Notify new version, size=%d", Integer.valueOf(allAvailable.size()));
                ArrayList arrayList = new ArrayList();
                for (Campaign campaign3 : allAvailable) {
                    CampaignPackage campaignPackage = campaign3.getCampaignPackage();
                    if (campaignPackage == null) {
                        Logger.e(CampaignChecker.TAG, "onSuccess, campaign(%d) have no package info", campaign3.getCampaignId());
                    } else {
                        arrayList.add(campaignPackage.getPackageName());
                    }
                }
                SOTAManager.getListenerInvoker().fireNewVersion(arrayList);
                return;
            }
            Logger.d(CampaignChecker.TAG, "Campaign list will install after power off", new Object[0]);
        }

        @Override // com.xiaopeng.sota.sdk.manager.download.DownloadListener
        public void onCanceled(Campaign campaign) {
            Logger.d(CampaignChecker.TAG, "[" + campaign.getCampaignId() + "]Download canceled", new Object[0]);
            SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.DOWNLOAD, CampaignEvent.State.INTERRUPTED));
        }

        @Override // com.xiaopeng.sota.sdk.manager.download.DownloadListener
        public void onFailure(Campaign campaign, Exception exc) {
            Logger.e(CampaignChecker.TAG, exc, "[" + campaign.getCampaignId() + "]Download failure", new Object[0]);
            CampaignChecker.this.mRetryTimerLatch.activate();
            HashMap hashMap = new HashMap();
            hashMap.put("message", Log.getStackTraceString(exc));
            SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.DOWNLOAD, CampaignEvent.State.INTERRUPTED, hashMap));
        }

        @Override // com.xiaopeng.sota.sdk.manager.download.DownloadListener
        public void onError(Campaign campaign, DownloadErrorException downloadErrorException) {
            Logger.e(CampaignChecker.TAG, downloadErrorException, "[" + campaign.getCampaignId() + "]Download error", new Object[0]);
            CampaignChecker.this.mRetryTimerLatch.activate();
            HashMap hashMap = new HashMap();
            hashMap.put("code", Integer.valueOf(downloadErrorException.getCode()));
            hashMap.put("message", Log.getStackTraceString(downloadErrorException));
            SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.DOWNLOAD, CampaignEvent.State.ERROR, hashMap));
            SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.COMPLETE, CampaignEvent.State.ERROR));
            SOTAManager.getListenerInvoker().fireDownloadError(campaign.getCampaignPackage().getPackageName(), downloadErrorException);
        }

        @Override // com.xiaopeng.sota.sdk.manager.download.DownloadListener
        public void onProgress(Campaign campaign, long j, long j2, int i) {
            Logger.d(CampaignChecker.TAG, "[%d, %s]Download progress-->" + i, campaign.getCampaignId(), campaign.getCampaignPackage().getPackageName());
            SOTAManager.getListenerInvoker().fireDownloading(campaign.getCampaignPackage().getPackageName(), j, j2);
        }
    }
}
