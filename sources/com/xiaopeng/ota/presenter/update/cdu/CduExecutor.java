package com.xiaopeng.ota.presenter.update.cdu;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.UpdateEngine;
import android.os.UpdateEngineCallback;
import androidx.annotation.NonNull;
import com.xiaopeng.fota.sdk.EcuType;
import com.xiaopeng.fota.sdk.EcuVersion;
import com.xiaopeng.fota.sdk.OtaUePackage;
import com.xiaopeng.lib.utils.SystemPropertyUtil;
import com.xiaopeng.lib.utils.info.BuildInfoUtils;
import com.xiaopeng.ota.presenter.update.BaseExecutor;
import com.xiaopeng.ota.presenter.update.cdu.UpdateParser;
import com.xiaopeng.ota.utils.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.ota.utils.VersionUtils;
import java.io.File;
/* loaded from: classes2.dex */
public class CduExecutor extends BaseExecutor {
    private static final int DownloadInvalidMetadataMagicString = 21;
    private static final int DownloadInvalidMetadataSignature = 33;
    private static final int DownloadInvalidMetadataSize = 32;
    private static final int DownloadManifestParseError = 23;
    private static final int DownloadMetadataSignatureError = 24;
    private static final int DownloadMetadataSignatureMismatch = 26;
    private static final int DownloadMetadataSignatureMissingError = 39;
    private static final int DownloadMetadataSignatureVerificationError = 25;
    private static final int DownloadNewPartitionInfoError = 13;
    private static final int DownloadOperationExecutionError = 28;
    private static final int DownloadOperationHashMismatch = 29;
    private static final int DownloadOperationHashMissingError = 38;
    private static final int DownloadOperationHashVerificationError = 27;
    private static final int DownloadPayloadPubKeyVerificationError = 18;
    private static final int DownloadPayloadVerificationError = 12;
    private static final int DownloadSignatureMissingInManifest = 22;
    private static final int DownloadStateInitializationError = 20;
    private static final int DownloadTransferError = 9;
    private static final int DownloadWriteError = 14;
    private static final int Error = 1;
    private static final int FilesystemCopierError = 4;
    private static final int FilesystemVerifierError = 47;
    private static final int InstallDeviceOpenError = 7;
    private static final int KernelDeviceOpenError = 8;
    private static final int NewKernelVerificationError = 16;
    private static final int NewRootfsVerificationError = 15;
    private static final int NonCriticalUpdateInOOBE = 49;
    private static final int OmahaErrorInHTTPResponse = 37;
    private static final int OmahaRequestEmptyResponseError = 30;
    private static final int OmahaRequestError = 2;
    private static final int OmahaRequestXMLHasEntityDecl = 46;
    private static final int OmahaRequestXMLParseError = 31;
    private static final int OmahaResponseHandlerError = 3;
    private static final int OmahaResponseInvalid = 34;
    private static final int OmahaUpdateDeferredForBackoff = 40;
    private static final int OmahaUpdateDeferredPerPolicy = 36;
    private static final int OmahaUpdateIgnoredOverCellular = 50;
    private static final int OmahaUpdateIgnoredPerPolicy = 35;
    private static final int PayloadHashMismatchError = 10;
    private static final int PayloadMismatchedType = 6;
    private static final int PayloadSizeMismatchError = 11;
    private static final int PayloadTimestampError = 51;
    private static final int PostInstallBootedFromFirmwareB = 19;
    private static final int PostInstallFirmwareRONotUpdatable = 43;
    private static final int PostInstallPowerwashError = 41;
    private static final int PostInstallRunnerError = 5;
    private static final int SignedDeltaPayloadExpectedError = 17;
    private static final int Success = 0;
    private static final String TAG = "CduExecutor";
    private static final int UnsupportedMajorPayloadVersion = 44;
    private static final int UnsupportedMinorPayloadVersion = 45;
    private static final int UpdateCanceledByChannelChange = 42;
    private static final int UpdatedButNotActive = 52;
    private static final int UserCanceled = 48;
    private HandlerThread mHandlerThread;
    private UpdateEngineCallback mUpdateCallback;
    private UpdateEngine mUpdateEngine;

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean fileInvalid(int i) {
        if (6 == i || 10 == i || 11 == i) {
            return true;
        }
        return 15 <= i && 29 >= i;
    }

    public CduExecutor(Context context) {
        super(context);
        this.mUpdateCallback = new UpdateEngineCallback() { // from class: com.xiaopeng.ota.presenter.update.cdu.CduExecutor.1
            public void onStatusUpdate(int i, float f) {
                LogUtils.d(CduExecutor.TAG, "onStatusUpdate, status: " + i + ", percent: " + f);
                if (i != 3) {
                    if (i == 5) {
                        CduExecutor cduExecutor = CduExecutor.this;
                        cduExecutor.sendProgress(cduExecutor.mPreferencesManager.getCampaignId(CduExecutor.this.getId()), (short) 2, 99);
                        return;
                    } else if (i != 6) {
                        return;
                    } else {
                        LogUtils.d(CduExecutor.TAG, "Need reboot");
                        return;
                    }
                }
                int i2 = (int) (f * 100.0f);
                if (i2 == 100) {
                    i2 = 99;
                }
                LogUtils.d(CduExecutor.TAG, "upgrade progress: " + i2);
                CduExecutor cduExecutor2 = CduExecutor.this;
                cduExecutor2.sendProgress(cduExecutor2.mPreferencesManager.getCampaignId(CduExecutor.this.getId()), (short) 2, i2);
            }

            public void onPayloadApplicationComplete(int i) {
                int i2;
                String str;
                if (i == 0) {
                    LogUtils.d(CduExecutor.TAG, "onPayloadApplicationComplete success");
                    return;
                }
                if (i == 48) {
                    i2 = -26;
                    str = "User cancelled";
                } else if (i == 52) {
                    LogUtils.d(CduExecutor.TAG, "kUpdatedButNotActive");
                    CduExecutor.this.mPreferencesManager.setUeState(CduExecutor.this.getId(), CduExecutor.this.mState);
                    i2 = 100;
                    str = null;
                } else {
                    LogUtils.e(CduExecutor.TAG, "onPayloadApplicationComplete fail, error code: " + i);
                    str = "Update engine return error: " + i;
                    i2 = CduExecutor.fileInvalid(i) ? -3 : -49;
                }
                CduExecutor cduExecutor = CduExecutor.this;
                cduExecutor.sendProgress(cduExecutor.mPreferencesManager.getCampaignId(CduExecutor.this.getId()), (short) 2, i2, str);
            }
        };
    }

    @Override // com.xiaopeng.ota.presenter.update.BaseExecutor
    public void release() {
        super.lambda$campaignCompleted$1$BaseExecutor();
        UpdateEngine updateEngine = this.mUpdateEngine;
        if (updateEngine != null) {
            updateEngine.unbind();
            this.mUpdateEngine = null;
        }
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mHandlerThread = null;
        }
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int getId() {
        return EcuType.CDU.id();
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public Object[] getVersions(Object obj) {
        byte b = 1;
        EcuVersion[] ecuVersionArr = {new EcuVersion()};
        String fullSystemVersion = BuildInfoUtils.getFullSystemVersion();
        try {
            if (new BootController().getCurrentSlot() == 1) {
                b = 2;
            }
        } catch (Exception e) {
            LogUtils.w(TAG, e, "getCurrentSlot fail");
        }
        ecuVersionArr[0].setActiveZone(b);
        ecuVersionArr[0].setSoftwareVersion(VersionUtils.getSoftwareFromFingerprint(fullSystemVersion));
        ecuVersionArr[0].setHardwareVersion(SystemPropertyUtil.getHwVersion());
        ecuVersionArr[0].setFingerprint(fullSystemVersion);
        return ecuVersionArr;
    }

    @Override // com.xiaopeng.ota.presenter.update.BaseExecutor, com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int prepare(@NonNull final OtaUePackage otaUePackage) {
        LogUtils.d(TAG, "prepare: " + JsonUtils.toJson(otaUePackage));
        sendProgress(otaUePackage.getCampaignId(), (short) 2, 0);
        try {
            release();
            if (new BootController().prepareSlot(false)) {
                LogUtils.d(TAG, "PrepareSlot success");
            } else {
                LogUtils.w(TAG, "PrepareSlot failed");
            }
            this.mHandlerThread = new HandlerThread("handlerThread");
            this.mHandlerThread.start();
            this.mUpdateEngine = new UpdateEngine();
            this.mUpdateEngine.bind(this.mUpdateCallback, new Handler(this.mHandlerThread.getLooper()));
            final UpdateParser.ParsedUpdate parse = UpdateParser.parse(new File(otaUePackage.getFile()));
            if (!parse.isValid()) {
                LogUtils.e(TAG, "The update file not valid");
                return -1;
            }
            this.mPreferencesManager.setCampaignId(getId(), otaUePackage.getCampaignId());
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.cdu.-$$Lambda$CduExecutor$z5Pde9i9fe4_mIDZxLyZuuXuMTo
                @Override // java.lang.Runnable
                public final void run() {
                    CduExecutor.this.lambda$prepare$0$CduExecutor(parse, otaUePackage);
                }
            }, 2000L);
            return 0;
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Prepare fail.");
            return -1;
        }
    }

    public /* synthetic */ void lambda$prepare$0$CduExecutor(UpdateParser.ParsedUpdate parsedUpdate, OtaUePackage otaUePackage) {
        try {
            this.mUpdateEngine.applyPayload(parsedUpdate.mUrl, parsedUpdate.mOffset, parsedUpdate.mSize, parsedUpdate.mProps);
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Update engine apply payload fail");
            sendProgress(otaUePackage.getCampaignId(), (short) 2, -54);
        }
    }

    @Override // com.xiaopeng.ota.presenter.update.BaseExecutor, com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int activate(long j, byte b) {
        LogUtils.d(TAG, "activate, campaignId: " + j + ", zone: " + ((int) b));
        if (this.mState == 4 && this.mProgress == 100) {
            return runTask(j, (short) 5, new BaseExecutor.TaskListener() { // from class: com.xiaopeng.ota.presenter.update.cdu.-$$Lambda$CduExecutor$6Dj3dckP9b7PL01qK_yL5H-WwGI
                @Override // com.xiaopeng.ota.presenter.update.BaseExecutor.TaskListener
                public final void onExecuteTask() {
                    CduExecutor.this.lambda$activate$1$CduExecutor();
                }
            });
        }
        LogUtils.e(TAG, "State or progress not right, state: %d, progress: %d", Short.valueOf(this.mState), Integer.valueOf(this.mProgress));
        return 0;
    }

    public /* synthetic */ void lambda$activate$1$CduExecutor() throws Exception {
        if (new BootController().prepareSlot(true)) {
            this.mPreferencesManager.setUeState(getId(), this.mState);
            LogUtils.d(TAG, "ActiveSlot success");
            return;
        }
        throw new BaseExecutor.ExecutorException(-59, "ActiveSlot fail.");
    }

    @Override // com.xiaopeng.ota.presenter.update.BaseExecutor, com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int reset(long j, byte b) {
        LogUtils.d(TAG, "reset, campaignId: " + j + ", mode: " + ((int) b));
        sendProgress(j, (short) 6, 0);
        this.mPreferencesManager.setUeState(getId(), this.mState);
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.cdu.-$$Lambda$CduExecutor$mGhvvPx83l1f-ynVKlsZE1iEr_w
            @Override // java.lang.Runnable
            public final void run() {
                CduExecutor.this.lambda$reset$2$CduExecutor();
            }
        }, 2000L);
        return 0;
    }

    public /* synthetic */ void lambda$reset$2$CduExecutor() {
        this.mRebootWrapper.reboot("Reboot by CDU ab update");
    }
}
