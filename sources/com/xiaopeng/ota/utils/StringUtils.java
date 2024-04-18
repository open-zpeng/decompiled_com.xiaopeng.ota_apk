package com.xiaopeng.ota.utils;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.SparseIntArray;
import com.android.internal.util.ArrayUtils;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.helper.ConfigHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/* loaded from: classes2.dex */
public class StringUtils {
    public static boolean isChinese(char c) {
        return c >= 19968 && c <= 40869;
    }

    public static String buildString(int... iArr) {
        StringBuilder sb = new StringBuilder();
        for (int i : iArr) {
            sb.append(OTAManager.getContext().getString(i));
        }
        return sb.toString();
    }

    public static String buildString(int i, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(OTAManager.getContext().getString(i));
        if (str == null) {
            str = "";
        }
        sb.append(str);
        return sb.toString();
    }

    public static String buildString(List<String> list, String str) {
        if (ArrayUtils.isEmpty(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : list) {
            sb.append(str2);
            sb.append(str);
        }
        return sb.toString().trim();
    }

    public static String format(String str, Object... objArr) {
        String string = ConfigHelper.getString(str);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return String.format(string, objArr);
    }

    public static String format(int i, Object... objArr) {
        String string = OTAManager.getContext().getString(i);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return String.format(string, objArr);
    }

    public static String getPhoneNumber(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt >= '0' && charAt <= '9') {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    public static String getLowerCaseFileName(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt < '[' && charAt > '@' && i != 0) {
                sb.append('_');
            }
            sb.append(charAt);
        }
        return sb.toString().toLowerCase();
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static SpannableStringBuilder buildColorText(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return new SpannableStringBuilder("");
        }
        if (str.endsWith("#")) {
            str = str + " ";
        }
        String[] split = str.split("#");
        if (split.length < 3) {
            return new SpannableStringBuilder(str);
        }
        SparseIntArray sparseIntArray = new SparseIntArray();
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= split.length) {
                break;
            }
            int i4 = i2 + 2;
            if (i4 >= split.length) {
                if (i4 == split.length) {
                    sb.append('#');
                }
                sb.append(split[i2]);
            } else {
                int length = i3 + split[i2].length();
                int i5 = i2 + 1;
                int length2 = split[i5].length() + length;
                sparseIntArray.put(length, length2);
                sb.append(split[i2]);
                sb.append(split[i5]);
                i2 = i4;
                i3 = length2;
            }
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(sb.toString().trim());
        for (int i6 = 0; i6 < sparseIntArray.size(); i6++) {
            int keyAt = sparseIntArray.keyAt(i6);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(i), keyAt, sparseIntArray.get(keyAt), 18);
        }
        return spannableStringBuilder;
    }

    public static boolean isChinese(String str) {
        if (str == null) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }
}
