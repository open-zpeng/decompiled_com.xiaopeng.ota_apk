package com.xiaopeng.ota.sdk.common.util;

import java.util.Arrays;
import java.util.List;
/* loaded from: classes2.dex */
public class ArrayUtils {
    public static int[] addToArray(int[] iArr, int i) {
        if (iArr == null) {
            iArr = new int[0];
        }
        int[] copyOf = Arrays.copyOf(iArr, iArr.length + 1);
        copyOf[copyOf.length - 1] = i;
        return copyOf;
    }

    public static String getArrayContent(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int length = iArr.length;
        sb.append("[");
        for (int i = 0; i < length; i++) {
            sb.append(iArr[i]);
            if (i < length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static String getBytesContent(String str, byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder(bArr == null ? 0 : (bArr.length * 5) + 20);
        sb.append(str);
        if (bArr != null) {
            sb.append("(");
            sb.append(i2);
            sb.append("):");
            for (int i3 = i; i3 < i + i2; i3++) {
                sb.append(Integer.toHexString(bArr[i3] & 255));
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static <T> String getListContent(List<T> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                sb.append(String.valueOf(list.get(i)));
                if (i < size - 1) {
                    sb.append(",");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }
}
