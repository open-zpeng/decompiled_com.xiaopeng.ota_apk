package com.xiaopeng.fota.sdk;

import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import com.xiaopeng.lib.utils.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLHandshakeException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class Downloader implements Runnable {
    private static final int BUFFER_SIZE = 524288;
    private String TAG;
    private MessageDigest digest;
    private long fileSize;
    private String md5;
    private File output;
    private String path;
    private long pkgId;
    private int progress;
    private long reqId;
    private long size;
    private long startPos;
    private String[] urls;
    private IHttp httpAccessor = null;
    private volatile boolean keepRunning = false;
    private final Object stopSignal = new Object();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class DownloadException extends Exception {
        private int code;

        DownloadException(int i, String str) {
            super(str);
            this.code = i;
        }

        int code() {
            return this.code;
        }
    }

    private void notifyStop() {
        synchronized (this.stopSignal) {
            this.stopSignal.notify();
        }
    }

    private void waitStop(long j) {
        synchronized (this.stopSignal) {
            try {
                this.stopSignal.wait(j);
            } catch (InterruptedException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Downloader(long j, long j2, String str, long j3, String str2, String[] strArr) {
        this.reqId = j;
        this.pkgId = j2;
        this.size = j3;
        this.path = str;
        this.md5 = str2;
        this.urls = strArr;
        try {
            this.digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException unused) {
            this.digest = null;
        }
        this.progress = 0;
        this.output = new File(str);
        this.TAG = "OtaDL(" + this.output.getName() + ")";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int start(IHttp iHttp) {
        if (this.keepRunning) {
            return -18;
        }
        this.keepRunning = true;
        this.httpAccessor = iHttp;
        return Worker.run(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stop() {
        if (this.keepRunning) {
            this.keepRunning = false;
            this.httpAccessor.cancelTag(this.output.getName());
            Log.i(this.TAG, "Download cancelled", new Object[0]);
            waitStop(1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void update(long j, long j2, long j3, String[] strArr) {
        this.reqId = j;
        this.pkgId = j2;
        this.size = j3;
        this.urls = strArr;
    }

    private void checkExist() throws DownloadException {
        if (!this.output.exists()) {
            this.fileSize = 0L;
            return;
        }
        this.fileSize = this.output.length();
        long j = this.fileSize;
        this.startPos = j;
        long j2 = this.size;
        if (0 != j2) {
            if (j == j2) {
                Log.i(this.TAG, "File exists", new Object[0]);
                throw new DownloadException(0, "File exists");
            } else if (j > j2) {
                String str = this.TAG;
                Log.w(str, "Delete file with larger size " + this.fileSize + "/" + this.size, new Object[0]);
                this.output.delete();
                this.fileSize = 0L;
                return;
            }
        }
        String str2 = this.TAG;
        Log.i(str2, "Existing file size " + this.fileSize, new Object[0]);
    }

    private void trace(MessageDigest messageDigest) {
        Log.i(this.TAG, "Part %d ~ %d, MD5 %s", Long.valueOf(this.startPos), Long.valueOf(this.fileSize), Log.hex(messageDigest.digest()));
    }

    private boolean copyData(InputStream inputStream, RandomAccessFile randomAccessFile, long j) throws IOException, DownloadException {
        randomAccessFile.seek(this.fileSize);
        byte[] bArr = new byte[524288];
        while (this.keepRunning) {
            int read = inputStream.read(bArr);
            if (read > 0) {
                randomAccessFile.write(bArr, 0, read);
                randomAccessFile.getFD().sync();
                this.fileSize += read;
                MessageDigest messageDigest = this.digest;
                if (messageDigest != null) {
                    messageDigest.update(bArr, 0, read);
                    long j2 = this.fileSize;
                    if (j2 == j) {
                        trace(this.digest);
                    } else {
                        int i = (int) ((j2 * 100) / j);
                        int i2 = 10;
                        if (j >= FileUtils.SIZE_1GB) {
                            i2 = 1;
                        } else if (j >= 134217728) {
                            i2 = 5;
                        }
                        if (i < 100 && this.progress + i2 < i) {
                            this.progress = i;
                            try {
                                trace((MessageDigest) this.digest.clone());
                            } catch (CloneNotSupportedException unused) {
                            }
                        }
                    }
                }
                long j3 = this.fileSize;
                if (j3 == j) {
                    randomAccessFile.close();
                    return true;
                }
                UpgradeAgent.downloadProgress(this.reqId, this.pkgId, this.path, j3);
            } else {
                trace(this.digest);
                Log.w(this.TAG, "Read stream failed", new Object[0]);
                return false;
            }
        }
        throw new DownloadException(-26, "Download cancelled");
    }

    private void copyFromFile(File file) throws DownloadException {
        if (file == null || !file.exists()) {
            return;
        }
        long j = this.size;
        if (0 != j && j != file.length()) {
            String str = this.TAG;
            Log.i(str, "File " + file + " size " + file.length() + " != " + this.size, new Object[0]);
            return;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.output, "rws");
            try {
                fileInputStream.skip(this.fileSize);
                String str2 = this.TAG;
                Log.i(str2, "Copy from " + file, new Object[0]);
                if (copyData(fileInputStream, randomAccessFile, file.length())) {
                    throw new DownloadException(0, "Copy from file succeed");
                }
                String str3 = this.TAG;
                Log.i(str3, "Copy from " + file + " failed", new Object[0]);
                randomAccessFile.close();
                fileInputStream.close();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (th != null) {
                        try {
                            randomAccessFile.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    } else {
                        randomAccessFile.close();
                    }
                    throw th2;
                }
            }
        } catch (IOException e) {
            String str4 = this.TAG;
            Log.e(str4, "Open file failed: " + e.getMessage(), new Object[0]);
        }
    }

    private boolean downloadData(IResponse iResponse, long j) throws DownloadException {
        try {
            InputStream byteStream = iResponse.byteStream();
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.output, "rws");
            try {
                boolean copyData = copyData(byteStream, randomAccessFile, j);
                randomAccessFile.close();
                if (byteStream != null) {
                    byteStream.close();
                }
                return copyData;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (th != null) {
                        try {
                            randomAccessFile.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    } else {
                        randomAccessFile.close();
                    }
                    throw th2;
                }
            }
        } catch (IOException e) {
            String str = this.TAG;
            Log.e(str, "Download file failed: " + e.getMessage(), new Object[0]);
            return false;
        }
    }

    private IResponse downloadFromUrlImpl(String str) throws IOException {
        IRequest iRequest = this.httpAccessor.get(str);
        if (0 != this.startPos) {
            iRequest.headers("RANGE", "bytes=" + this.startPos + "-");
        }
        iRequest.tag(this.output.getName());
        return iRequest.execute();
    }

    private boolean downloadFromUrl(String str) throws DownloadException {
        IResponse downloadFromUrlImpl;
        long j = this.fileSize;
        this.startPos = j;
        Log.i(this.TAG, "Download offset %d from %s", Long.valueOf(j), str);
        try {
            downloadFromUrlImpl = downloadFromUrlImpl(str);
        } catch (SSLHandshakeException e) {
            Log.w(this.TAG, "Request failed with: %s, retry with HTTP", e.getMessage());
            try {
                downloadFromUrlImpl = downloadFromUrlImpl(str.replace("https://", "http://"));
            } catch (IOException e2) {
                Log.w(this.TAG, "Request failed again with: %s", e2.getMessage());
                return false;
            }
        } catch (IOException e3) {
            Log.w(this.TAG, "Request failed with: %s", e3.getMessage());
            return false;
        }
        if (this.keepRunning) {
            if (downloadFromUrlImpl == null) {
                String str2 = this.TAG;
                Log.i(str2, "Connect to " + str + " failed", new Object[0]);
                return false;
            } else if (downloadFromUrlImpl.code() >= 400 && downloadFromUrlImpl.code() <= 599) {
                Log.w(this.TAG, "Response failed %d, %s", Integer.valueOf(downloadFromUrlImpl.code()), downloadFromUrlImpl.body());
                return false;
            } else if (downloadFromUrlImpl.byteStream() == null) {
                Log.w(this.TAG, "Response is empty", new Object[0]);
                return false;
            } else {
                long contentLength = this.fileSize + downloadFromUrlImpl.getRawResponse().body().contentLength();
                long j2 = this.size;
                if (0 != j2 && contentLength != j2) {
                    Log.w(this.TAG, "RemoteSize(%d) != LocalSize(%s)", Long.valueOf(contentLength), Long.valueOf(this.size));
                    return false;
                }
                UpgradeAgent.downloadProgress(this.reqId, this.pkgId, this.path, this.fileSize);
                return downloadData(downloadFromUrlImpl, contentLength);
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0057, code lost:
        throw new com.xiaopeng.fota.sdk.Downloader.DownloadException(r8, -27, "Network unavailable");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void downloadFromNet() throws com.xiaopeng.fota.sdk.Downloader.DownloadException {
        /*
            r8 = this;
            long r0 = r8.fileSize
            r2 = 16
            int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r4 >= 0) goto Lc
            long r0 = r0 - r2
            r8.fileSize = r0
            goto L10
        Lc:
            r0 = 0
            r8.fileSize = r0
        L10:
            r0 = 0
            r1 = r0
        L12:
            java.lang.String[] r2 = r8.urls
            int r3 = r2.length
            if (r1 >= r3) goto L9c
            r2 = r2[r1]
            boolean r3 = r8.keepRunning
            if (r3 == 0) goto L92
            long r3 = r8.fileSize
            boolean r5 = r8.downloadFromUrl(r2)
            if (r5 != 0) goto L76
            android.content.Context r5 = com.xiaopeng.fota.sdk.UpgradeUtils.context()
            java.lang.String r6 = "connectivity"
            java.lang.Object r5 = r5.getSystemService(r6)
            android.net.ConnectivityManager r5 = (android.net.ConnectivityManager) r5
            if (r5 == 0) goto L58
            android.net.Network r6 = r5.getActiveNetwork()
            android.net.NetworkCapabilities r5 = r5.getNetworkCapabilities(r6)
            if (r5 == 0) goto L61
            r6 = 12
            boolean r6 = r5.hasCapability(r6)
            if (r6 == 0) goto L4e
            r6 = 16
            boolean r5 = r5.hasCapability(r6)
            if (r5 == 0) goto L4e
            goto L61
        L4e:
            com.xiaopeng.fota.sdk.Downloader$DownloadException r0 = new com.xiaopeng.fota.sdk.Downloader$DownloadException
            r1 = -27
            java.lang.String r2 = "Network unavailable"
            r0.<init>(r1, r2)
            throw r0
        L58:
            java.lang.String r5 = r8.TAG
            java.lang.Object[] r6 = new java.lang.Object[r0]
            java.lang.String r7 = "Get connectivity manager failed"
            com.xiaopeng.fota.sdk.Log.w(r5, r7, r6)
        L61:
            long r5 = r8.fileSize
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 < 0) goto L12
            java.lang.String r3 = r8.TAG
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r4[r0] = r2
            java.lang.String r2 = "Switch URL because no data downloaded from %s"
            com.xiaopeng.fota.sdk.Log.w(r3, r2, r4)
            int r1 = r1 + 1
            goto L12
        L76:
            com.xiaopeng.fota.sdk.Downloader$DownloadException r1 = new com.xiaopeng.fota.sdk.Downloader$DownloadException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Download from "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r2 = " success"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.<init>(r0, r2)
            throw r1
        L92:
            com.xiaopeng.fota.sdk.Downloader$DownloadException r0 = new com.xiaopeng.fota.sdk.Downloader$DownloadException
            r1 = -26
            java.lang.String r2 = "Download cancelled"
            r0.<init>(r1, r2)
            throw r0
        L9c:
            com.xiaopeng.fota.sdk.Downloader$DownloadException r0 = new com.xiaopeng.fota.sdk.Downloader$DownloadException
            r1 = -31
            java.lang.String r2 = "Tried all URLs"
            r0.<init>(r1, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.fota.sdk.Downloader.downloadFromNet():void");
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            checkExist();
            copyFromFile(UpgradeUtils.getUsbFile(this.output.getName()));
            copyFromFile(new File("/sdcard/ota/downloaded", this.output.getName()));
            downloadFromNet();
        } catch (DownloadException e) {
            UpgradeAgent.downloadFinished(this.reqId, this.pkgId, this.path, e.code(), e.getMessage());
        }
        Log.i(this.TAG, "Download stopped", new Object[0]);
        this.keepRunning = false;
        notifyStop();
    }

    void remove() {
        try {
            Log.i(this.TAG, "Deleted", new Object[0]);
            this.output.delete();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSame(String str, String str2) {
        return this.md5.equals(str2) && this.path.equals(str);
    }
}
