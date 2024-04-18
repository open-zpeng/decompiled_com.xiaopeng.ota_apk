package com.xiaopeng.ota.sdk.common.util;
/* loaded from: classes2.dex */
public class CryptoUtils {
    private static final String KEY = "QCFjaGVuemkjMDEwOSQrLw==";
    private static final String TAG = "CryptoUtils";

    public static byte[] cryptoDecodeInByteArray(byte[] bArr) throws Exception {
        return Base64Utils.decode(bArr);
    }

    public static byte[] cryptoDecodeInByteArray() throws Exception {
        return new byte[]{-39, 21, -119, -108, 99, 17, -26, 29, -50, -55, 8, -94, -113, -76, -24, -37, 72, 68, -89, 84, -60, -94, -103, 87};
    }

    public static String getAESKey() throws CryptException {
        return Base64Utils.decodeString(KEY);
    }

    /* loaded from: classes2.dex */
    public static class CryptException extends Exception {
        public CryptException(String str) {
            super(str);
        }

        public CryptException(String str, Throwable th) {
            super(str, th);
        }
    }
}
