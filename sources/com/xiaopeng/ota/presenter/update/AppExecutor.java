package com.xiaopeng.ota.presenter.update;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import com.xiaopeng.fota.sdk.AppVersion;
import com.xiaopeng.fota.sdk.EcuType;
import com.xiaopeng.fota.sdk.OtaUePackage;
import com.xiaopeng.fota.sdk.UpgradeUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.lib.utils.crypt.AESUtils;
import com.xiaopeng.libconfig.ipc.AccountConfig;
import com.xiaopeng.ota.Config;
import com.xiaopeng.ota.sdk.common.util.CryptoUtils;
import com.xiaopeng.ota.utils.LogUtils;
import java.io.File;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class AppExecutor extends BaseExecutor {
    private static final String TAG = "AppExecutor";
    private long campaignId;

    public static void handleAction(Intent intent) {
        int i;
        String action = intent.getAction();
        if (!Config.ACTION_APP_INSTALL.equals(action)) {
            LogUtils.i(TAG, "%s action %s", intent.getData().getSchemeSpecificPart(), action);
            return;
        }
        String stringExtra = intent.getStringExtra("android.content.pm.extra.PACKAGE_NAME");
        String stringExtra2 = intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE");
        int intExtra = intent.getIntExtra("android.content.pm.extra.STATUS", 1);
        LogUtils.w(TAG, "%s result status %d, %s", stringExtra, Integer.valueOf(intExtra), stringExtra2);
        long longExtra = intent.getLongExtra("campaign_id", 0L);
        short shortExtra = intent.getShortExtra(Config.EXTRA_KEY_CAMPAIGN_STAGE, (short) 2);
        if (intExtra != 0) {
            if (intExtra != 7) {
                if (intExtra == 4) {
                    i = -3;
                } else if (intExtra != 5) {
                    i = -34;
                }
            }
            i = -25;
        } else {
            i = 100;
        }
        UpgradeUtils.OtaUpgradeProgress(longExtra, (short) EcuType.APP.id(), shortExtra, i, stringExtra2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class SessionCallback extends PackageInstaller.SessionCallback {
        private String packageName;
        private int sessionId;

        public SessionCallback(int i, String str) {
            this.sessionId = i;
            this.packageName = str;
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onCreated(int i) {
            LogUtils.i(AppExecutor.TAG, "Session %d created", Integer.valueOf(i));
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onBadgingChanged(int i) {
            LogUtils.i(AppExecutor.TAG, "Session %d bind", Integer.valueOf(i));
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onActiveChanged(int i, boolean z) {
            LogUtils.i(AppExecutor.TAG, "Session %d active %d", Integer.valueOf(i), Integer.valueOf(z ? 1 : 0));
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onProgressChanged(int i, float f) {
            if (i == this.sessionId) {
                int i2 = (int) (f * 100.0f);
                if (100 <= i2) {
                    i2 = 99;
                }
                if (i2 > AppExecutor.this.mProgress) {
                    AppExecutor.this.mProgress = i2;
                }
                LogUtils.i(AppExecutor.TAG, "Session %d progress %d", Integer.valueOf(i), Integer.valueOf(i2));
                UpgradeUtils.OtaUpgradeProgress(AppExecutor.this.campaignId, (short) AppExecutor.this.getId(), AppExecutor.this.mState, AppExecutor.this.mProgress);
            }
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onFinished(int i, boolean z) {
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(i);
            objArr[1] = z ? AccountConfig.FaceIDRegisterAction.STATUS_SUCCEED : "failed";
            LogUtils.i(AppExecutor.TAG, "Session %d %s", objArr);
            if (z) {
                AppExecutor.this.mProgress = 100;
            }
        }
    }

    public AppExecutor(Context context) {
        super(context);
        this.campaignId = 0L;
        this.mState = (short) 0;
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int getId() {
        return EcuType.APP.id();
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public Object[] getVersions(Object obj) {
        String[] strArr;
        if (obj instanceof String) {
            LogUtils.i(TAG, "Get versions of (%s)", obj);
            String str = (String) obj;
            if (!str.isEmpty()) {
                strArr = str.split(";");
            }
            strArr = null;
        } else {
            if (obj instanceof String[]) {
                strArr = (String[]) obj;
            }
            strArr = null;
        }
        if (strArr == null) {
            strArr = new String[]{this.mContext.getPackageName()};
        }
        PackageManager packageManager = this.mContext.getPackageManager();
        ArrayList arrayList = new ArrayList();
        for (String str2 : strArr) {
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(str2, 0);
                AppVersion appVersion = new AppVersion();
                appVersion.setPackageName(str2);
                appVersion.setVersionName(packageInfo.versionName);
                appVersion.setVersionCode(packageInfo.getLongVersionCode());
                arrayList.add(appVersion);
            } catch (PackageManager.NameNotFoundException e) {
                LogUtils.e(TAG, e, "getPackageInfo(%s) failed", str2);
            }
        }
        return arrayList.toArray();
    }

    @Override // com.xiaopeng.ota.presenter.update.BaseExecutor, com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int prepare(final OtaUePackage otaUePackage) {
        this.campaignId = otaUePackage.getCampaignId();
        sendProgress(this.campaignId, (short) 2, 0);
        this.mPreferencesManager.setCampaignId(getId(), this.campaignId);
        this.mPreferencesManager.setUeState(getId(), this.mState);
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$AppExecutor$j-y1Kc1ZEuLTxHO-lqlSRhRXRUo
            @Override // java.lang.Runnable
            public final void run() {
                AppExecutor.this.lambda$prepare$0$AppExecutor(otaUePackage);
            }
        }, 1000L);
        return 0;
    }

    @Override // com.xiaopeng.ota.presenter.update.BaseExecutor, com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int upgrade(final OtaUePackage otaUePackage, byte b) {
        this.campaignId = otaUePackage.getCampaignId();
        sendProgress(this.campaignId, (short) 4, 0);
        this.mPreferencesManager.setCampaignId(getId(), this.campaignId);
        this.mPreferencesManager.setUeState(getId(), this.mState);
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$AppExecutor$3n7TI07QIaUTWAPzIvcwQc5_bbM
            @Override // java.lang.Runnable
            public final void run() {
                AppExecutor.this.lambda$upgrade$1$AppExecutor(otaUePackage);
            }
        }, 1000L);
        return 0;
    }

    private File decryptAndVerify(String str) throws Exception {
        File file = new File(str);
        File file2 = new File(file.getParent() + "/tmp", file.getName());
        if (file2.exists()) {
            file2.delete();
        }
        if (AESUtils.decrypt(file, file2, CryptoUtils.getAESKey())) {
            return file2;
        }
        throw new Exception("Decrypt function return false");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: installApp */
    public void lambda$upgrade$1$AppExecutor(com.xiaopeng.fota.sdk.OtaUePackage r19) {
        /*
            Method dump skipped, instructions count: 605
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.ota.presenter.update.AppExecutor.lambda$upgrade$1$AppExecutor(com.xiaopeng.fota.sdk.OtaUePackage):void");
    }
}
