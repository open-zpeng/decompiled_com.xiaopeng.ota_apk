package com.xiaopeng.ota.presenter.update.cdu;

import android.text.TextUtils;
import android.util.Log;
import com.xiaopeng.lib.utils.MD5Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Locale;
/* loaded from: classes2.dex */
public class UpdateParser {
    private static final String FILE_URL_PREFIX = "file://";
    private static final String PAYLOAD_BIN_FILE = "payload.bin";
    private static final String PAYLOAD_PROPERTIES = "payload_properties.txt";
    private static final String TAG = "UpdateParser";
    private static final int ZIP_FILE_HEADER = 30;

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static com.xiaopeng.ota.presenter.update.cdu.UpdateParser.ParsedUpdate parse(java.io.File r16) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 227
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.ota.presenter.update.cdu.UpdateParser.parse(java.io.File):com.xiaopeng.ota.presenter.update.cdu.UpdateParser$ParsedUpdate");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$parse$0(int i) {
        return new String[i];
    }

    /* loaded from: classes2.dex */
    public static class ParsedUpdate {
        private String mFileMd5;
        public final long mOffset;
        public final String[] mProps;
        public final long mSize;
        public final String mUrl;

        ParsedUpdate(File file, long j, long j2, String[] strArr) {
            this.mUrl = UpdateParser.FILE_URL_PREFIX + file.getAbsolutePath();
            this.mOffset = j;
            this.mSize = j2;
            this.mProps = strArr;
            try {
                this.mFileMd5 = MD5Utils.getFileMd5(file);
            } catch (FileNotFoundException unused) {
                Log.e(UpdateParser.TAG, "get file md5 fail");
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isValid() {
            return this.mOffset >= 0 && this.mSize > 0 && this.mProps != null;
        }

        boolean isSameFile(File file) {
            try {
                String fileMd5 = MD5Utils.getFileMd5(file);
                if (TextUtils.isEmpty(fileMd5)) {
                    return false;
                }
                return fileMd5.equals(this.mFileMd5);
            } catch (FileNotFoundException unused) {
                Log.e(UpdateParser.TAG, "get " + file.getPath() + " md5 fail");
                return false;
            }
        }

        public String toString() {
            return String.format(Locale.getDefault(), "ParsedUpdate: URL=%s, offset=%d, size=%s, props=%s", this.mUrl, Long.valueOf(this.mOffset), Long.valueOf(this.mSize), Arrays.toString(this.mProps));
        }
    }
}
