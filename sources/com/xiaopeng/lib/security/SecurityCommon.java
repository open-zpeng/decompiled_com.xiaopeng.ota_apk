package com.xiaopeng.lib.security;

import android.content.Context;
import android.util.Base64;
import androidx.annotation.Nullable;
import com.xiaopeng.lib.framework.netchannelmodule.remotestorage.aws.UploadInfo;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.info.BuildInfoUtils;
import com.xiaopeng.ota.sdk.common.OTAConstants;
/* loaded from: classes2.dex */
public final class SecurityCommon {
    private static final String TAG = "SecurityCommon";
    private static Boolean sIsSystemUid;

    public static boolean checkSystemUid(@Nullable Context context) {
        if (sIsSystemUid == null) {
            if (context == null) {
                return false;
            }
            try {
                sIsSystemUid = Boolean.valueOf(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).uid == 1000);
            } catch (Exception e) {
                LogUtils.w(TAG, "init uid error!", e);
                sIsSystemUid = false;
            }
        }
        return sIsSystemUid.booleanValue();
    }

    public static String getBuildInfoFlag() {
        return BuildInfoUtils.isLanVersion() ? "1" : UploadInfo.VERSION;
    }

    public static String base64UrlEncode(byte[] bArr) {
        return new String(Base64.encode(bArr, 11)).split(OTAConstants.EQ)[0].replace('+', '-').replace('/', '_');
    }

    public static String parseByte2HexStr(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append("0123456789ABCDEF".charAt((bArr[i] >> 4) & 15));
            stringBuffer.append("0123456789ABCDEF".charAt(bArr[i] & 15));
        }
        return stringBuffer.toString();
    }

    public static byte[] parseHexStr2Byte(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }

    public static String getUidIdsKey(String str, String str2) {
        return str + "-" + str2;
    }
}
