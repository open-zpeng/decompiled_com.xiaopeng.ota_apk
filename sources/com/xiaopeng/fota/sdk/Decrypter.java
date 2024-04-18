package com.xiaopeng.fota.sdk;

import android.pso.XpPso;
import android.pso.XpPsoException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class Decrypter implements Runnable {
    private static final int BUFFER_SIZE = 8192;
    private String TAG;
    private String decFile;
    private byte[] encData;
    private String encFile;
    private String encMd5;
    private volatile boolean finished;
    private long pkgId;
    private XpPso pso;
    private long reqId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Decrypter(long j, long j2, String str, String str2, String str3) {
        this.reqId = 0L;
        this.pkgId = 0L;
        this.encMd5 = null;
        this.encFile = null;
        this.decFile = null;
        this.encData = null;
        this.finished = false;
        this.pso = null;
        this.reqId = j;
        this.pkgId = j2;
        this.encMd5 = str;
        this.encFile = str2;
        this.decFile = str3;
        this.TAG = "OtaDec(" + str2 + ")";
        this.pso = new XpPso(UpgradeUtils.context());
        this.pso.set_carType(28);
        new Thread(this).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Decrypter(long j, byte[] bArr) {
        this.reqId = 0L;
        this.pkgId = 0L;
        this.encMd5 = null;
        this.encFile = null;
        this.decFile = null;
        this.encData = null;
        this.finished = false;
        this.pso = null;
        this.reqId = j;
        this.encData = bArr;
        this.TAG = "OtaDec(data)";
        this.pso = new XpPso(UpgradeUtils.context());
        this.pso.set_carType(28);
        new Thread(this).start();
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.encData == null) {
            decryptFile();
        } else {
            decryptData();
        }
        synchronized (this) {
            this.pso = null;
            this.finished = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isFinished() {
        return this.finished;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSame(String str, String str2) {
        String str3 = this.encMd5;
        return str3 != null && this.encFile != null && str3.equals(str) && this.encFile.equals(str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSame(long j) {
        return this.reqId == j;
    }

    private void decryptData() {
        try {
            UpgradeAgent.decryptDataFinished(this.reqId, this.pso.ota_package_decryptverify_mtom(this.encData), 0, null);
        } catch (XpPsoException e) {
            UpgradeAgent.decryptDataFinished(this.reqId, null, -4, e.getMessage());
        } catch (Exception e2) {
            UpgradeAgent.decryptDataFinished(this.reqId, null, -4, android.util.Log.getStackTraceString(e2));
        }
    }

    private void decryptFile() {
        try {
            byte[] e28ota_package_decryptverify_tofile = this.pso.e28ota_package_decryptverify_tofile(this.encFile, this.decFile);
            if (e28ota_package_decryptverify_tofile != null && 16 == e28ota_package_decryptverify_tofile.length) {
                UpgradeAgent.decryptFileFinished(this.reqId, this.pkgId, this.encMd5, this.encFile, 0, Log.hex(e28ota_package_decryptverify_tofile));
            } else {
                UpgradeAgent.decryptFileFinished(this.reqId, this.pkgId, this.encMd5, this.encFile, -4, "PSO decrypt failed");
            }
        } catch (XpPsoException e) {
            UpgradeAgent.decryptFileFinished(this.reqId, this.pkgId, this.encMd5, this.encFile, -4, e.getMessage());
        } catch (Exception e2) {
            UpgradeAgent.decryptDataFinished(this.reqId, null, -4, android.util.Log.getStackTraceString(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void abort() {
        synchronized (this) {
            if (this.pso != null) {
                this.pso.ota_dec_stop();
            }
        }
    }

    long getProgress() {
        XpPso xpPso = this.pso;
        if (xpPso == null) {
            return 0L;
        }
        return xpPso.get_dec_tofile_processed_len();
    }
}
