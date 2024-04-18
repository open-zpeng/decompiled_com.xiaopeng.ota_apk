package com.xiaopeng.ota.utils;

import android.text.TextUtils;
import android.util.Base64;
import com.android.internal.util.ArrayUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class Base64Utils {
    public static String encode(String str) {
        try {
            return encode(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String encode(byte[] bArr) {
        return Base64.encodeToString(bArr, 0);
    }

    public static String decodeString(String str) {
        try {
            return new String(decode(str), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static List<String> decodeArray(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (ArrayUtils.isEmpty(list)) {
            return arrayList;
        }
        for (String str : list) {
            String decodeString = decodeString(str);
            if (!TextUtils.isEmpty(decodeString)) {
                arrayList.add(decodeString);
            }
        }
        return arrayList;
    }

    public static String decodeStringQuietly(String str) {
        try {
            return new String(decode(str), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] decode(String str) {
        return Base64.decode(str, 0);
    }
}
