package com.xiaopeng.ota.sdk.common.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes2.dex */
public class ShaUtils {
    public static byte[] getOffsetFileContent(File file, long j, int i) {
        RandomAccessFile randomAccessFile;
        byte[] bArr = new byte[i];
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                try {
                    randomAccessFile = new RandomAccessFile(file, "rw");
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            randomAccessFile.seek(j);
            randomAccessFile.read(bArr, 0, bArr.length);
            randomAccessFile.close();
        } catch (Exception e3) {
            e = e3;
            randomAccessFile2 = randomAccessFile;
            e.printStackTrace();
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
            return bArr;
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile2 = randomAccessFile;
            if (randomAccessFile2 != null) {
                try {
                    randomAccessFile2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
        return bArr;
    }

    public static String SH3Digest256(byte[] bArr) {
        return Hex.toHexString(new SHA3.Digest256().digest(bArr));
    }

    public static String getSHA256Hmac(String str, String str2) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            byte[] bytes = str.getBytes("UTF-8");
            mac.init(new SecretKeySpec(bytes, 0, bytes.length, "HmacSHA256"));
            return HexUtils.toHexString(mac.doFinal(str2.getBytes("UTF-8")));
        } catch (Exception unused) {
            return "";
        }
    }
}
