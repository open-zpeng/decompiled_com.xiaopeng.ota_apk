package com.xiaopeng.ota.sdk.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes2.dex */
public class DigestUtils {
    private static final int MAX_BUFFER_SIZE = 4096;

    public static String MD5(File file) {
        return digest("MD5", file);
    }

    public static String MD5(String str) {
        return MD5(str.getBytes());
    }

    public static String MD5(byte[] bArr) {
        return MD5(bArr, 0, bArr.length);
    }

    public static String MD5(byte[] bArr, int i, int i2) {
        return digest("MD5", bArr, i, i2);
    }

    public static String SHA1(File file) {
        return digest(McElieceCCA2KeyGenParameterSpec.SHA1, file);
    }

    public static String SHA1(String str) {
        return SHA1(str.getBytes());
    }

    public static String SHA1(byte[] bArr) {
        return digest(McElieceCCA2KeyGenParameterSpec.SHA1, bArr, 0, bArr.length);
    }

    public static String SHA1(byte[] bArr, int i, int i2) {
        return digest(McElieceCCA2KeyGenParameterSpec.SHA1, bArr, i, i2);
    }

    public static String SHA256(File file) {
        return digest(McElieceCCA2KeyGenParameterSpec.SHA256, file);
    }

    public static String SHA256(String str) {
        return SHA256(str.getBytes());
    }

    public static String SHA256(byte[] bArr) {
        return SHA256(bArr, 0, bArr.length);
    }

    public static String SHA256(byte[] bArr, int i, int i2) {
        return digest(McElieceCCA2KeyGenParameterSpec.SHA256, bArr, i, i2);
    }

    public static String SHA3_256(String str) {
        return SHA3_256(str.getBytes());
    }

    public static String SHA3_256(byte[] bArr) {
        return SHA3_256(bArr, 0, bArr.length);
    }

    public static String SHA3_256(byte[] bArr, int i, int i2) {
        SHA3.Digest256 digest256 = new SHA3.Digest256();
        digest256.update(bArr, i, i2);
        return Hex.toHexString(digest256.digest());
    }

    private static String digest(String str, byte[] bArr, int i, int i2) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr, i, i2);
            return Hex.toHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such algorithm " + str, e);
        }
    }

    private static String digest(String str, File file) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read > 0) {
                        messageDigest.update(bArr, 0, read);
                    } else {
                        fileInputStream.close();
                        return Hex.toHexString(messageDigest.digest());
                    }
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (th != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    } else {
                        fileInputStream.close();
                    }
                    throw th2;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + file.getName(), e);
        } catch (IOException e2) {
            throw new RuntimeException("Read file error " + file.getName(), e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new RuntimeException("No such algorithm " + str, e3);
        }
    }
}
