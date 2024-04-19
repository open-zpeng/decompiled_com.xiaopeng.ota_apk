package com.xiaopeng.ota.presenter.update;

import android.content.Context;
import android.os.ConditionVariable;
import android.os.SystemProperties;
import com.xiaopeng.fota.sdk.EcuType;
import com.xiaopeng.fota.sdk.EcuVersion;
import com.xiaopeng.fota.sdk.OtaUePackage;
import com.xiaopeng.lib.utils.ZipUtils;
import com.xiaopeng.lib.utils.info.BuildInfoUtils;
import com.xiaopeng.ota.Config;
import com.xiaopeng.ota.auto.CarApi;
import com.xiaopeng.ota.auto.callback.McuCallbackAdapter;
import com.xiaopeng.ota.auto.service.mcu.IMcuService;
import com.xiaopeng.ota.exception.OtaException;
import com.xiaopeng.ota.utils.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import java.io.File;
/* loaded from: classes2.dex */
public class McuExecutor extends BaseExecutor {
    private static final int DELAY_TIME_SEND_FILE = 100;
    private static final int FIRMWARE_INSTALL_PROGRESS = 98;
    private static final String KEYWORD_MCU_A_BLOCK = "_A.HEX";
    private static final String KEYWORD_MCU_B_BLOCK = "_B.HEX";
    private static final String KEYWORD_MCU_FLASH_DRIVER = "_FLASHDRIVER";
    public static final String MCU_FOLDER = "mcu";
    public static final int OTA_ANDROID_ACK_OK = 0;
    public static final int OTA_MCU_REQ_MCU_RESET = 4;
    public static final int OTA_MCU_REQ_UPDATE = 1;
    private static final int RESET_PROGRESS = 0;
    private static final String TAG = "McuExecutor";
    private static final int TOTAL_TIMEOUT = 300000;
    private static final int UNZIP_PROGRESS = 2;
    private volatile boolean mIsSuccess;
    private ConditionVariable mLock;
    private McuCallbackAdapter mMcuCallback;
    private String mMcuFolder;
    private volatile Exception mUpdateException;

    public McuExecutor(Context context, String str) {
        super(context);
        this.mLock = new ConditionVariable();
        this.mMcuCallback = new McuCallbackAdapter() { // from class: com.xiaopeng.ota.presenter.update.McuExecutor.1
            public static final int OTA_MCU_ACK_OK = 0;
            public static final int OTA_MCU_RSP_UPDATE_SUCCESS_FINISH = 5;
            private String mCurFileName;
            private int mLastProgress;
            private int mType = -1;

            @Override // com.xiaopeng.ota.auto.callback.McuCallbackAdapter
            protected void onReceiveReqStatus(int i) {
                LogUtils.d(McuExecutor.TAG, "Mcu onReceiveReqStatus: " + i);
                if (i == 5) {
                    try {
                        Thread.sleep(100L);
                    } catch (Exception unused) {
                    }
                    McuExecutor.this.mIsSuccess = true;
                    McuExecutor.this.mLock.open();
                }
            }

            private void sendFile(File file) {
                try {
                    this.mLastProgress = 0;
                    this.mCurFileName = file.getName();
                    McuExecutor.this.getMcuService().setOtaMcuSendUpdateFile(file.getAbsolutePath());
                    Thread.sleep(100L);
                    McuExecutor.this.getMcuService().setOtaMcuReqUpdateFile(0);
                } catch (Exception e) {
                    LogUtils.e(McuExecutor.TAG, e, "[MCU] Send %s failed", this.mCurFileName);
                    McuExecutor.this.mUpdateException = e;
                    McuExecutor.this.mLock.open();
                }
            }

            @Override // com.xiaopeng.ota.auto.callback.McuCallbackAdapter
            protected void onReceiveReqUpdateFile(int i) {
                LogUtils.d(McuExecutor.TAG, "Mcu onReceiveReqUpdateFile: " + i);
                this.mType = i;
                if (i == 0) {
                    McuExecutor mcuExecutor = McuExecutor.this;
                    File fileByKeyword = mcuExecutor.getFileByKeyword(McuExecutor.KEYWORD_MCU_FLASH_DRIVER, mcuExecutor.mMcuFolder);
                    if (fileByKeyword == null) {
                        McuExecutor.this.mUpdateException = new Exception("Get flash driver failed");
                        McuExecutor.this.mLock.open();
                        return;
                    }
                    sendFile(fileByKeyword);
                } else if (i == 1) {
                    McuExecutor mcuExecutor2 = McuExecutor.this;
                    File fileByKeyword2 = mcuExecutor2.getFileByKeyword(McuExecutor.KEYWORD_MCU_A_BLOCK, mcuExecutor2.mMcuFolder);
                    if (fileByKeyword2 == null) {
                        McuExecutor.this.mUpdateException = new Exception("Get A block file failed");
                        McuExecutor.this.mLock.open();
                        return;
                    }
                    sendFile(fileByKeyword2);
                } else if (i != 2) {
                } else {
                    McuExecutor mcuExecutor3 = McuExecutor.this;
                    File fileByKeyword3 = mcuExecutor3.getFileByKeyword(McuExecutor.KEYWORD_MCU_B_BLOCK, mcuExecutor3.mMcuFolder);
                    if (fileByKeyword3 == null) {
                        McuExecutor.this.mUpdateException = new Exception("Get B block file failed");
                        McuExecutor.this.mLock.open();
                        return;
                    }
                    sendFile(fileByKeyword3);
                }
            }

            @Override // com.xiaopeng.ota.auto.callback.McuCallbackAdapter
            protected void onReceiveUpdateStatus(int i) {
                if (i > this.mLastProgress) {
                    this.mLastProgress = i;
                    LogUtils.d(McuExecutor.TAG, "Mcu onReceiveUpdateStatus %s, progress: %d", this.mCurFileName, Integer.valueOf(i));
                    int i2 = this.mType;
                    if (i2 == 1 || i2 == 2) {
                        int i3 = ((i * 98) / 100) + 2;
                        if (100 <= i3) {
                            i3 = 99;
                        }
                        McuExecutor mcuExecutor = McuExecutor.this;
                        mcuExecutor.sendProgress(mcuExecutor.mPreferencesManager.getCampaignId(McuExecutor.this.getId()), (short) 4, i3);
                        LogUtils.d(McuExecutor.TAG, "Mcu total install progress: " + i3);
                    }
                }
            }
        };
        this.mMcuFolder = str + File.separator + MCU_FOLDER;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IMcuService getMcuService() {
        return (IMcuService) CarApi.getCarApi(101);
    }

    @Override // com.xiaopeng.ota.presenter.update.BaseExecutor
    public void release() {
        super.lambda$campaignCompleted$1$BaseExecutor();
        deleteFiles(this.mMcuFolder);
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int getId() {
        if ("D55".equals(CarApi.getCarVersion())) {
            return EcuType.MCU.id();
        }
        return EcuType.AMCU.id();
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public Object[] getVersions(Object obj) {
        EcuVersion[] ecuVersionArr = {new EcuVersion()};
        String str = SystemProperties.get(Config.MCU_VERSION_PROP, (String) null);
        ecuVersionArr[0].setActiveZone((byte) 1);
        ecuVersionArr[0].setSoftwareVersion(str);
        ecuVersionArr[0].setHardwareVersion(BuildInfoUtils.BID_LAN);
        EcuVersion ecuVersion = ecuVersionArr[0];
        ecuVersion.setFingerprint("8620000ED10005_H.4_" + str.replace(".", ""));
        return ecuVersionArr;
    }

    @Override // com.xiaopeng.ota.presenter.update.BaseExecutor, com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int upgrade(OtaUePackage otaUePackage, byte b) {
        LogUtils.d(TAG, "upgrade: " + JsonUtils.toJson(otaUePackage));
        sendProgress(otaUePackage.getCampaignId(), (short) 4, 0);
        File file = new File(otaUePackage.getFile());
        if (!file.exists()) {
            LogUtils.e(TAG, "Mcu file to be installed not found");
            long campaignId = otaUePackage.getCampaignId();
            sendProgress(campaignId, (short) 4, -5, otaUePackage.getFile() + " not found");
            return 0;
        }
        try {
            unzipMcuFile(file, this.mMcuFolder);
            this.mPreferencesManager.setCampaignId(getId(), otaUePackage.getCampaignId());
            sendProgress(otaUePackage.getCampaignId(), (short) 4, 2);
            try {
                try {
                    LogUtils.d(TAG, "Mcu upgrade start...");
                    getMcuService().addCarServiceCallback(this.mMcuCallback);
                    this.mIsSuccess = false;
                    this.mUpdateException = null;
                    LogUtils.d(TAG, "Mcu setOtaMcuReqStatus(%d)...", 1);
                    getMcuService().setOtaMcuReqStatus(1);
                    this.mLock.close();
                    if (!this.mLock.block(300000L)) {
                        LogUtils.e(TAG, "Mcu install timeout");
                        throw new Exception("Mcu install timeout");
                    }
                    deleteFiles(this.mMcuFolder);
                    if (!this.mIsSuccess) {
                        if (this.mUpdateException == null) {
                            this.mUpdateException = new Exception("Mcu install failed, reason: unknown");
                        }
                        LogUtils.e(TAG, this.mUpdateException, "Install failed");
                        throw this.mUpdateException;
                    }
                    getMcuService().removeCarServiceCallback(this.mMcuCallback);
                    sendProgress(otaUePackage.getCampaignId(), (short) 4, 100);
                    return 0;
                } catch (Exception e) {
                    LogUtils.e(TAG, e, "Mcu upgrade failed");
                    sendProgress(otaUePackage.getCampaignId(), (short) 4, -34, e.getMessage());
                    getMcuService().removeCarServiceCallback(this.mMcuCallback);
                    return 0;
                }
            } catch (Throwable th) {
                getMcuService().removeCarServiceCallback(this.mMcuCallback);
                throw th;
            }
        } catch (OtaException e2) {
            LogUtils.e(TAG, e2, "Mcu file unzip failed");
            sendProgress(otaUePackage.getCampaignId(), (short) 4, -3, e2.getMessage());
            return 0;
        }
    }

    @Override // com.xiaopeng.ota.presenter.update.BaseExecutor, com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int reset(long j, byte b) {
        LogUtils.d(TAG, "reset, campaignId: " + j + ", mode: " + ((int) b));
        sendProgress(j, (short) 6, 0);
        this.mPreferencesManager.setUeState(getId(), (short) 6);
        try {
            Thread.sleep(200L);
            for (int i = 0; i < 3; i++) {
                getMcuService().setOtaMcuReqStatus(4);
                Thread.sleep(1000L);
            }
            sendProgress(j, (short) 6, 100);
            return 0;
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Mcu reset failed");
            return -34;
        }
    }

    private static void unzipMcuFile(File file, String str) throws OtaException {
        if (!ZipUtils.isArchiveFile(file)) {
            LogUtils.w(TAG, "Not a zip file, wrong file format");
            throw new OtaException("Not a zip file, wrong file format");
        }
        try {
            deleteFiles(str);
            com.xiaopeng.ota.sdk.common.util.ZipUtils.unzip(file.getAbsoluteFile(), str);
            LogUtils.d(TAG, "Unzip mcu file completed");
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Unzip mcu files failed");
            throw new OtaException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File getFileByKeyword(String str, String str2) {
        File[] listFiles;
        File file = new File(str2);
        if (file.exists()) {
            for (File file2 : file.listFiles()) {
                if (file2.getName().toUpperCase().contains(str)) {
                    return file2;
                }
            }
            return null;
        }
        return null;
    }

    private static void deleteFiles(String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                file2.delete();
            }
        }
    }
}
