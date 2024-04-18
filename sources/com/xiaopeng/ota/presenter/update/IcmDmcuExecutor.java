package com.xiaopeng.ota.presenter.update;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.util.Log;
import com.xiaopeng.fota.sdk.EcuType;
import com.xiaopeng.fota.sdk.EcuVersion;
import com.xiaopeng.fota.sdk.OtaUePackage;
import com.xiaopeng.lib.utils.ZipUtils;
import com.xiaopeng.ota.exception.OtaException;
import com.xiaopeng.ota.helper.CarServiceHelper;
import com.xiaopeng.ota.presenter.update.BaseExecutor;
import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.utils.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
/* loaded from: classes2.dex */
public class IcmDmcuExecutor extends BaseExecutor {
    private static final String ACTION_SURFIX = "fw";
    private static final String CLASS_PREFIX = "/sys/class/i2c-adapter/i2c-9/9-000c/ds941_";
    private static final String COPY_DSTPATH = "/data/firmware/XP_CLU_MCU.bin";
    private static final String GET_VERSION = "ver";
    private static final String MCU_FOLDER = "icmdmcu";
    private static final String STATUS_SURFIX = "status";
    private static final String TAG = "IcmDmcuExecutor";
    private static final String UPGRADE_SIGNAL = "1";
    private static final String XPENG_PREFIX = "/sys/xpeng/cluster/cluster_";
    private final String mActionPath;
    private Long mCampaignId;
    private final String mMcuFolder;
    private final String mStatusPath;

    public IcmDmcuExecutor(Context context, String str) {
        super(context);
        setDisplayManager(false);
        this.mMcuFolder = str + File.separator + MCU_FOLDER;
        this.mProgress = 0;
        File file = new File("/sys/xpeng/cluster/cluster_fw");
        if (file.isFile()) {
            this.mActionPath = file.getPath();
            this.mStatusPath = "/sys/xpeng/cluster/cluster_status";
            return;
        }
        this.mActionPath = "/sys/class/i2c-adapter/i2c-9/9-000c/ds941_fw";
        this.mStatusPath = "/sys/class/i2c-adapter/i2c-9/9-000c/ds941_status";
    }

    private boolean setDisplayManager(boolean z) {
        try {
            Method method = DisplayManager.class.getMethod("upgradeIcmDisplayFirmware", Boolean.TYPE);
            DisplayManager displayManager = (DisplayManager) this.mContext.getSystemService("display");
            if (displayManager == null) {
                LogUtils.w(TAG, "Get display service failed");
                return false;
            }
            method.invoke(displayManager, Boolean.valueOf(z));
            LogUtils.i(TAG, "Invoke setDisplayManager finished");
            return true;
        } catch (Exception e) {
            LogUtils.w(TAG, "Invoke upgradeIcmDisplayFirmware failed: " + Log.getStackTraceString(e));
            return false;
        }
    }

    @Override // com.xiaopeng.ota.presenter.update.BaseExecutor, com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int campaignStarted(final long j) {
        return runTask(j, (short) 3, new BaseExecutor.TaskListener() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$IcmDmcuExecutor$VwAC2m3sqlF-ZXdee5kF5PGquyY
            @Override // com.xiaopeng.ota.presenter.update.BaseExecutor.TaskListener
            public final void onExecuteTask() {
                IcmDmcuExecutor.this.lambda$campaignStarted$0$IcmDmcuExecutor(j);
            }
        });
    }

    public /* synthetic */ void lambda$campaignStarted$0$IcmDmcuExecutor(long j) throws Exception {
        setDisplayManager(true);
        this.mPreferencesManager.setCampaignId(getId(), j);
        this.mPreferencesManager.setUeState(getId(), this.mState);
    }

    @Override // com.xiaopeng.ota.presenter.update.BaseExecutor, com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int campaignCompleted(long j) {
        int runTask = runTask(j, (short) 9, new BaseExecutor.TaskListener() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$IcmDmcuExecutor$CJ78FL79oYLrMIl4qO4lSg4tO68
            @Override // com.xiaopeng.ota.presenter.update.BaseExecutor.TaskListener
            public final void onExecuteTask() {
                IcmDmcuExecutor.this.lambda$campaignCompleted$1$IcmDmcuExecutor();
            }
        });
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.presenter.update.-$$Lambda$IcmDmcuExecutor$lMQwKBr9L1u_k1ZpOdAlTaedq_Q
            @Override // java.lang.Runnable
            public final void run() {
                IcmDmcuExecutor.this.lambda$campaignCompleted$2$IcmDmcuExecutor();
            }
        }, 5000L);
        return runTask;
    }

    public /* synthetic */ void lambda$campaignCompleted$1$IcmDmcuExecutor() throws Exception {
        lambda$campaignCompleted$1$BaseExecutor();
    }

    public /* synthetic */ void lambda$campaignCompleted$2$IcmDmcuExecutor() {
        setDisplayManager(false);
        this.mState = (short) 0;
        this.mProgress = 0;
        this.mPreferencesManager.setUeState(getId(), this.mState);
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int getId() {
        return EcuType.ICMDMCU.id();
    }

    @Override // com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public Object[] getVersions(Object obj) {
        boolean z;
        String str;
        if (CarServiceHelper.isIGLocalStatus()) {
            z = false;
        } else {
            LogUtils.i(TAG, "getVersion : set upgradeIcmDisplayFirmware true");
            z = setDisplayManager(true);
        }
        String str2 = null;
        for (int i = 0; i < 5; i++) {
            writeFile(GET_VERSION, this.mStatusPath);
            str2 = readFile(this.mStatusPath);
            if (str2 != null) {
                break;
            }
            LogUtils.e(TAG, "get version failed");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (str2 == null) {
            return null;
        }
        LogUtils.i(TAG, "Version is " + str2);
        EcuVersion[] ecuVersionArr = {new EcuVersion()};
        ecuVersionArr[0].setActiveZone((byte) 1);
        ecuVersionArr[0].setSoftwareVersion(str2.substring(1) + ".0");
        ecuVersionArr[0].setHardwareVersion("1");
        ecuVersionArr[0].setFingerprint("8620000ED10005_H.1_" + str.replace(".", ""));
        if (z) {
            setDisplayManager(false);
        }
        return ecuVersionArr;
    }

    @Override // com.xiaopeng.ota.presenter.update.BaseExecutor, com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int upgrade(OtaUePackage otaUePackage, byte b) {
        LogUtils.i(TAG, "Upgrade " + JsonUtils.toJson(otaUePackage));
        this.mCampaignId = Long.valueOf(otaUePackage.getCampaignId());
        sendProgress(this.mCampaignId.longValue(), (short) 4, 0);
        File file = new File(otaUePackage.getFile());
        if (!file.exists()) {
            long longValue = this.mCampaignId.longValue();
            sendProgress(longValue, (short) 4, -5, otaUePackage.getFile() + " not found");
            return 0;
        }
        try {
            unzipMcuFile(file, this.mMcuFolder);
            File FindFileByKey = FindFileByKey(".BIN", this.mMcuFolder);
            if (FindFileByKey == null) {
                long longValue2 = this.mCampaignId.longValue();
                sendProgress(longValue2, (short) 4, -3, "No BIN file in " + otaUePackage.getFile());
                return 0;
            }
            try {
                Path path = Paths.get(COPY_DSTPATH, new String[0]);
                Files.deleteIfExists(path);
                Files.createSymbolicLink(path, Paths.get(FindFileByKey.getPath(), new String[0]), new FileAttribute[0]);
                try {
                    try {
                        writeFile("1", this.mActionPath);
                        sendProgress(this.mCampaignId.longValue(), (short) 4, 100, "Upgrade success!");
                    } catch (Exception e) {
                        long longValue3 = this.mCampaignId.longValue();
                        sendProgress(longValue3, (short) 4, -49, "Trigger upgrade failed: " + Log.getStackTraceString(e));
                    }
                    return 0;
                } finally {
                    deleteFiles(this.mMcuFolder);
                }
            } catch (Exception e2) {
                long longValue4 = this.mCampaignId.longValue();
                sendProgress(longValue4, (short) 4, -51, "Create soft link from " + FindFileByKey.getPath() + " to " + COPY_DSTPATH + " failed: " + Log.getStackTraceString(e2));
                return 0;
            }
        } catch (OtaException e3) {
            sendProgress(this.mCampaignId.longValue(), (short) 4, -3, e3.getMessage());
            return 0;
        }
    }

    @Override // com.xiaopeng.ota.presenter.update.BaseExecutor, com.xiaopeng.fota.sdk.UpgradeExecutorBase
    public int getStatus() {
        if (this.mState == 4) {
            String readFile = readFile(this.mActionPath);
            if (readFile == null) {
                return super.getStatus();
            }
            LogUtils.i(TAG, "Get upgrade status " + readFile);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < readFile.length(); i++) {
                if (readFile.charAt(i) >= '0' && readFile.charAt(i) <= '9') {
                    sb.append(readFile.charAt(i));
                }
            }
            int parseInt = Integer.parseInt(sb.toString());
            if (parseInt < 0) {
                long longValue = this.mCampaignId.longValue();
                sendProgress(longValue, (short) 4, -49, "Get upgrade result: " + parseInt);
            } else {
                if (100 <= parseInt) {
                    parseInt = 99;
                }
                sendProgress(this.mCampaignId.longValue(), (short) 4, parseInt);
            }
            return 0;
        }
        return super.getStatus();
    }

    private void unzipMcuFile(File file, String str) throws OtaException {
        if (!ZipUtils.isArchiveFile(file)) {
            Logger.w(TAG, "Not a zip file, wrong file format", new Object[0]);
            throw new OtaException("Not a zip file, wrong file format");
        }
        try {
            deleteFiles(str);
            com.xiaopeng.ota.sdk.common.util.ZipUtils.unzip(file.getAbsoluteFile(), str);
            Logger.i(TAG, "Unzip mcu file completed", new Object[0]);
        } catch (Exception e) {
            Logger.e(TAG, e, "Unzip file " + file + " failed: " + Log.getStackTraceString(e), new Object[0]);
            throw new OtaException(e);
        }
    }

    private void deleteFiles(String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (!file2.delete()) {
                    LogUtils.d(TAG, "error:delete file failed");
                }
            }
        }
    }

    private File FindFileByKey(String str, String str2) {
        File[] listFiles;
        File file = new File(str2);
        if (file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.getName().toUpperCase().contains(str)) {
                    return file2;
                }
            }
        }
        return null;
    }

    private void writeFile(String str, String str2) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            LogUtils.w(TAG, "Write file " + str2 + " failed: " + Log.getStackTraceString(e));
        }
    }

    private String readFile(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[1024];
            int read = fileInputStream.read(bArr);
            fileInputStream.close();
            String str2 = new String(bArr, 0, read);
            fileInputStream.close();
            return str2;
        } catch (Exception e) {
            LogUtils.d(TAG, "Read from file " + str + " failed: " + Log.getStackTraceString(e));
            return null;
        }
    }
}
