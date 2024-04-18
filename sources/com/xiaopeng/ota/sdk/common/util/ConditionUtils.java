package com.xiaopeng.ota.sdk.common.util;

import com.xiaopeng.ota.sdk.common.condition.Condition;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class ConditionUtils {
    private static final String SEPARATOR = ",";

    public static List<String> convert(List<Condition> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < size; i++) {
            arrayList.add(Base64Utils.decodeString(list.get(i).getAlert()));
        }
        return arrayList;
    }

    public static String convert2String(List<Condition> list) {
        List<String> convert = convert(list);
        if (ArrayUtils.isEmpty(convert)) {
            return null;
        }
        return convert2Context(convert);
    }

    public static String convert2Context(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
            sb.append(",");
        }
        String sb2 = sb.toString();
        return sb2.substring(0, sb2.lastIndexOf(","));
    }
}
