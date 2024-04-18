package com.xiaopeng.sota.sdk;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Pair;
import com.xiaopeng.lib.utils.crypt.AESUtils;
import com.xiaopeng.lib.utils.info.BuildInfoUtils;
import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.sdk.common.util.AppUtils;
import com.xiaopeng.ota.sdk.common.util.ArrayUtils;
import com.xiaopeng.ota.sdk.common.util.CryptoUtils;
import com.xiaopeng.ota.sdk.common.util.DigestUtils;
import com.xiaopeng.ota.sdk.common.util.ExceptionUtils;
import com.xiaopeng.ota.sdk.common.util.FileUtils;
import com.xiaopeng.ota.sdk.common.util.JsonUtils;
import com.xiaopeng.ota.sdk.common.util.PermissionUtils;
import com.xiaopeng.ota.sdk.common.util.ThreadUtils;
import com.xiaopeng.ota.sdk.exception.DecryptException;
import com.xiaopeng.sota.sdk.common.Constants;
import com.xiaopeng.sota.sdk.exception.CampaignSuspendException;
import com.xiaopeng.sota.sdk.manager.campaign.Campaign;
import com.xiaopeng.sota.sdk.manager.campaign.CampaignEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class CampaignUpdater {
    private static final String TAG = "CampaignUpdater";
    private volatile BlockingDeque<Campaign> mCampaignQueue;
    private volatile boolean mIsInstalling;
    private AtomicInteger mPackageIndex;
    private AtomicInteger mTotalNumber;

    public void install(final List<Campaign> list) {
        if (this.mIsInstalling) {
            Logger.d(TAG, "Has campaign installing, not to install again", new Object[0]);
            return;
        }
        this.mIsInstalling = true;
        SOTAManager.getConfigManager().setInstallingState(true);
        SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.CampaignUpdater.1
            @Override // java.lang.Runnable
            public void run() {
                CampaignUpdater.this.mCampaignQueue = new LinkedBlockingDeque(list);
                CampaignUpdater.this.mTotalNumber = new AtomicInteger(SOTAManager.getConfigManager().getInstallNumber());
                int size = list.size();
                SOTAManager.getConfigManager().setInstallNumber(size);
                SOTAManager.getListenerInvoker().fireInstallReady(size);
                if (CampaignUpdater.this.mTotalNumber.get() == 0) {
                    CampaignUpdater.this.mTotalNumber.set(size);
                }
                CampaignUpdater.this.mPackageIndex = new AtomicInteger(size - list.size());
                CampaignUpdater.this.installNextPackage();
            }
        });
    }

    /* renamed from: resumeInstall */
    public void lambda$resumeInstallationDelay$3$CampaignUpdater() {
        this.mIsInstalling = true;
        SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.CampaignUpdater.2
            @Override // java.lang.Runnable
            public void run() {
                List<Campaign> allAvailable = SOTAManager.getCampaignManager().getAllAvailable();
                if (ArrayUtils.isEmpty(allAvailable)) {
                    Logger.d(CampaignUpdater.TAG, "resumeInstall, and find no more campaign", new Object[0]);
                    CampaignUpdater.this.installComplete();
                    return;
                }
                CampaignUpdater.this.mCampaignQueue = new LinkedBlockingDeque(allAvailable);
                int installNumber = SOTAManager.getConfigManager().getInstallNumber();
                if (installNumber == 0) {
                    installNumber = allAvailable.size();
                    SOTAManager.getConfigManager().setInstallNumber(installNumber);
                }
                CampaignUpdater.this.mTotalNumber = new AtomicInteger(installNumber);
                CampaignUpdater.this.mPackageIndex = new AtomicInteger(installNumber - allAvailable.size());
                CampaignUpdater.this.installNextPackage();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void installNextPackage() {
        SOTAExecutor.execute(new Runnable() { // from class: com.xiaopeng.sota.sdk.CampaignUpdater.3
            @Override // java.lang.Runnable
            public void run() {
                if (CampaignUpdater.this.mCampaignQueue == null) {
                    Logger.e(CampaignUpdater.TAG, "installNextPackage, queue is null", new Object[0]);
                    return;
                }
                Logger.d(CampaignUpdater.TAG, "installNextPackage, size=" + CampaignUpdater.this.mCampaignQueue.size(), new Object[0]);
                Campaign campaign = (Campaign) CampaignUpdater.this.mCampaignQueue.poll();
                if (campaign != null) {
                    CampaignUpdater.this.executeSync(campaign);
                } else {
                    CampaignUpdater.this.installComplete();
                }
            }
        });
    }

    private void installStart(Campaign campaign) {
        this.mPackageIndex.incrementAndGet();
        SOTAManager.getListenerInvoker().fireInstallStart(campaign.getCampaignPackage().getPackageName(), campaign.getUpgradeMode().intValue(), this.mPackageIndex.get(), SOTAManager.getConfigManager().getInstallNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void installComplete() {
        Logger.d(TAG, "No more package to install", new Object[0]);
        this.mIsInstalling = false;
        SOTAManager.getConfigManager().setInstallNumber(0);
        SOTAManager.getConfigManager().setInstallingState(false);
        SOTAManager.getListenerInvoker().fireInstallComplete();
        AtomicInteger atomicInteger = this.mTotalNumber;
        if (atomicInteger == null || atomicInteger.get() != 0) {
            return;
        }
        FileUtils.deleteOldFile(SOTAManager.getCampaignDownloadManager().getDownloadPath(), 0L);
    }

    public void stop() {
        this.mCampaignQueue.clear();
    }

    private void sendUpgradeErrorEvent(Campaign campaign, Exception exc) {
        HashMap hashMap = new HashMap();
        hashMap.put("message", ExceptionUtils.collectStack(exc));
        SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.UPGRADE, CampaignEvent.State.ERROR, hashMap));
        SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.COMPLETE, CampaignEvent.State.ERROR));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeSync(final Campaign campaign) {
        Logger.d(TAG, "Start to install app=%s", campaign.getCampaignPackage().getPackageName());
        installStart(campaign);
        SOTAManager.getCampaignManager().updateState(campaign.getCampaignId().longValue(), 2);
        try {
            final String decryptAndVerify = decryptAndVerify(campaign);
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.xiaopeng.sota.sdk.-$$Lambda$CampaignUpdater$YJwOv7XbG5ZUg69FzSGRM1VHp2M
                @Override // java.lang.Runnable
                public final void run() {
                    CampaignUpdater.this.lambda$executeSync$2$CampaignUpdater(campaign, decryptAndVerify);
                }
            });
        } catch (Exception e) {
            Logger.d(TAG, e, "[%d] Campaign decryptAndVerify fail", campaign.getCampaignId());
            SOTAManager.getListenerInvoker().fireInstallError(campaign.getCampaignPackage().getPackageName(), e);
            installNextPackage();
        }
    }

    public /* synthetic */ void lambda$executeSync$2$CampaignUpdater(Campaign campaign, String str) {
        String fullSystemVersion;
        String cduFingerprint;
        try {
            SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.UPGRADE, CampaignEvent.State.INIT));
            try {
                Logger.d(TAG, "[" + campaign.getCampaignId() + "]Change mode", new Object[0]);
                PermissionUtils.changeMode(getInstallApkDir(), PermissionUtils.MODE_ALL);
                TimeUnit.MILLISECONDS.sleep(100L);
            } catch (IOException e) {
                Logger.e(TAG, e, "[" + campaign.getCampaignId() + "]Change mode failed", new Object[0]);
            }
            try {
                fullSystemVersion = BuildInfoUtils.getFullSystemVersion();
                cduFingerprint = SOTAManager.getCampaignManager().getCduFingerprint(campaign.getCampaignId().longValue());
            } catch (Exception e2) {
                Logger.d(TAG, e2, "install exception", new Object[0]);
                SOTAManager.getListenerInvoker().fireInstallationResult(campaign.getCampaignPackage().getPackageName(), false);
                HashMap hashMap = new HashMap();
                hashMap.put("message", ExceptionUtils.collectStack(e2));
                SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.UPGRADE, CampaignEvent.State.REJECT, hashMap));
                installNextPackage();
            }
            if (!fullSystemVersion.equals(cduFingerprint)) {
                String format = String.format("Cdu fingerprint not match, cur=%s, pre=%s", fullSystemVersion, cduFingerprint);
                Logger.d(TAG, format, new Object[0]);
                throw new CampaignSuspendException(format);
            }
            SOTAManager.getListenerInvoker().canPackageInstalled(campaign.getCampaignPackage().getPackageName());
            SOTAManager.getAppManager().install(SOTAManager.getContext(), campaign.getCampaignPackage().getPackageName(), str, AppReceiver.class);
            Logger.d(TAG, "[" + campaign.getCampaignId() + "]Install finished!!", new Object[0]);
        } catch (Exception e3) {
            Logger.e(TAG, e3, "Install occurs exception", new Object[0]);
            sendUpgradeErrorEvent(campaign, e3);
            SOTAManager.getListenerInvoker().fireInstallError(campaign.getCampaignPackage().getPackageName(), e3);
            installNextPackage();
        }
    }

    private String decryptAndVerify(Campaign campaign) throws Exception {
        File outputFile = SOTAManager.getCampaignDownloadManager().getOutputFile(campaign);
        if (!outputFile.exists() || !outputFile.isFile()) {
            Logger.w(TAG, "[" + campaign.getCampaignId() + "]Install file not exist-->" + outputFile.getAbsolutePath(), new Object[0]);
            throw new FileNotFoundException("Download file not exist");
        } else if (outputFile.length() != campaign.getCampaignPackage().getSize().longValue()) {
            String str = "[" + campaign.getCampaignId() + "]Install file does not download complete";
            Logger.w(TAG, str, new Object[0]);
            throw new Exception(str);
        } else {
            String MD5 = DigestUtils.MD5(outputFile);
            if (TextUtils.isEmpty(MD5) || !MD5.equals(campaign.getCampaignPackage().getHash())) {
                String str2 = "[" + campaign.getCampaignId() + "]Hash not match";
                Logger.e(TAG, str2, new Object[0]);
                throw new Exception(str2);
            }
            Logger.d(TAG, "[" + campaign.getCampaignId() + "]Download file-->" + outputFile.getAbsolutePath(), new Object[0]);
            StringBuilder sb = new StringBuilder();
            sb.append(campaign.getCampaignPackage().getHash());
            sb.append(Constants.SUFFIX_DECRYPT_APK);
            String destFilepath = getDestFilepath(sb.toString());
            Logger.d(TAG, "[" + campaign.getCampaignId() + "]Install file-->" + destFilepath, new Object[0]);
            try {
                SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.DECRYPT, CampaignEvent.State.INIT));
                decrypt(outputFile, destFilepath);
                SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.DECRYPT, CampaignEvent.State.OK));
                return destFilepath;
            } catch (DecryptException e) {
                Logger.e(TAG, e, "Decrypt occurs exception", new Object[0]);
                HashMap hashMap = new HashMap();
                hashMap.put("message", ExceptionUtils.collectStack(e));
                SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.DECRYPT, CampaignEvent.State.ERROR, hashMap));
                SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.COMPLETE, CampaignEvent.State.ERROR));
                throw e;
            }
        }
    }

    public String getInstallApkDir() {
        String downloadPath = SOTAManager.getCampaignDownloadManager().getDownloadPath();
        FileUtils.mkdirsIfNotExist(downloadPath);
        return downloadPath;
    }

    private String getDestFilepath(String str) {
        return getInstallApkDir() + "/" + str;
    }

    private void decrypt(File file, String str) throws DecryptException {
        try {
            File file2 = new File(str);
            if (!file2.exists()) {
                file2.createNewFile();
            }
            if (!AESUtils.decrypt(file, file2, CryptoUtils.getAESKey())) {
                throw new DecryptException("Decrypt failed");
            }
            Logger.d(TAG, "Decrypt success", new Object[0]);
        } catch (Exception e) {
            throw new DecryptException("Decrypt failed", e);
        }
    }

    public void checkPackage(String str) {
        Context context = SOTAManager.getContext();
        Campaign campaign = SOTAManager.getCampaignManager().getCampaign(str);
        if (campaign == null) {
            Logger.w(TAG, "Not found available campaign by packageName(%s)", str);
            return;
        }
        SOTAManager.getCampaignManager().updateState(campaign.getCampaignId().longValue(), 3);
        Pair<String, Integer> pair = null;
        try {
            pair = AppUtils.getVersionInfo(context, str);
        } catch (PackageManager.NameNotFoundException unused) {
            Logger.w(TAG, "check, get %s current version fail", str);
        }
        Pair<String, Integer> campaignVersion = getCampaignVersion(campaign);
        boolean pairEquals = pairEquals(pair, campaignVersion);
        if (pairEquals) {
            SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.UPGRADE, CampaignEvent.State.OK));
            Logger.d(TAG, "[" + campaign.getCampaignId() + "]Install success, delete files", new Object[0]);
            FileUtils.delete(SOTAManager.getCampaignDownloadManager().getOutputFile(campaign));
            FileUtils.delete(getDestFilepath(campaign.getCampaignPackage().getHash() + Constants.SUFFIX_DECRYPT_APK));
            SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.COMPLETE, CampaignEvent.State.OK));
            SOTAManager.getCampaignManager().delete(campaign);
            AtomicInteger atomicInteger = this.mTotalNumber;
            if (atomicInteger != null) {
                atomicInteger.decrementAndGet();
            }
        } else {
            sendUpgradeErrorEvent(campaign, new Exception(String.format("Version does not match, current: %s, target: %s", JsonUtils.toJson(pair), JsonUtils.toJson(campaignVersion))));
            SOTAManager.getCampaignManager().invalidCampaign(campaign.getCampaignId().longValue());
        }
        Logger.d(TAG, "[" + campaign.getCampaignId() + "]Upgrade result, current:%s, target:%s", JsonUtils.toJson(pair), JsonUtils.toJson(campaignVersion));
        SOTAManager.getListenerInvoker().fireInstallationResult(str, pairEquals);
        if (SOTAManager.getContext().getPackageName().equals(str)) {
            resumeInstallationDelay();
        } else {
            installNextPackage();
        }
    }

    private void resumeInstallationDelay() {
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.sota.sdk.-$$Lambda$CampaignUpdater$g37Ti_TapybiwmDdZgJu1zaty2s
            @Override // java.lang.Runnable
            public final void run() {
                CampaignUpdater.this.lambda$resumeInstallationDelay$3$CampaignUpdater();
            }
        }, 1000L);
    }

    private Pair<String, Integer> getCampaignVersion(Campaign campaign) {
        return new Pair<>(campaign.getCampaignPackage().getVersionName(), Integer.valueOf(Integer.parseInt(campaign.getCampaignPackage().getVersionCode())));
    }

    private boolean pairEquals(Pair<String, Integer> pair, Pair<String, Integer> pair2) {
        return pair != null && pair2 != null && ((String) pair.first).equals(pair2.first) && ((Integer) pair.second).equals(pair2.second);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void installFailed(int i, String str) {
        Campaign campaign = SOTAManager.getCampaignManager().getCampaign(str);
        if (campaign != null) {
            SOTAManager.getCampaignManager().updateState(campaign.getCampaignId().longValue(), 4);
            HashMap hashMap = new HashMap();
            hashMap.put("code", Integer.valueOf(i));
            hashMap.put("message", "Apk install failed");
            SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.UPGRADE, CampaignEvent.State.ERROR, hashMap));
            SOTAManager.getCampaignEventManager().add(new CampaignEvent(campaign, CampaignEvent.Event.COMPLETE, CampaignEvent.State.ERROR));
            SOTAManager.getListenerInvoker().fireInstallationResult(str, false);
        }
    }
}
