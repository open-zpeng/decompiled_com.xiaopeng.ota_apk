package com.xiaopeng.ota.sdk.download;

import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import java.io.File;
/* loaded from: classes2.dex */
public class FileDownloader implements Runnable {
    private static final int BUFFER_SIZE = 8192;
    private static final String TAG = "FileDownloader";
    private volatile boolean mCanceled;
    private Listener mDownloadListener;
    private String mDownloadUrl;
    private Exception mException;
    private volatile boolean mFailed;
    private File mFile;
    private IHttp mHttp;
    private String mOutputPath;
    private volatile boolean mRestored;
    private volatile boolean mSuccess;

    /* loaded from: classes2.dex */
    public interface Listener {
        void onCanceled();

        void onError(DownloadErrorException downloadErrorException);

        void onFailure(Exception exc);

        void onProgress(long j, long j2, byte[] bArr, int i);

        void onStarted(long j);

        void onSuccess(File file);

        void onWillStart();
    }

    public FileDownloader(IHttp iHttp, String str, String str2, Listener listener) {
        this.mHttp = iHttp;
        this.mDownloadUrl = str;
        this.mOutputPath = str2;
        this.mDownloadListener = listener;
    }

    public void cancel() {
        this.mCanceled = true;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    @Override // java.lang.Runnable
    public void run() {
        /*
            Method dump skipped, instructions count: 390
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.ota.sdk.download.FileDownloader.run():void");
    }

    public boolean isSuccess() {
        return this.mSuccess;
    }

    public boolean isRestored() {
        return this.mRestored;
    }

    public boolean isFailed() {
        return this.mFailed;
    }

    public boolean isCanceled() {
        return this.mCanceled;
    }

    public File getFile() {
        return this.mFile;
    }

    public Exception getException() {
        return this.mException;
    }
}
