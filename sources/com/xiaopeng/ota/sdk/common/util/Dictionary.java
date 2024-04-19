package com.xiaopeng.ota.sdk.common.util;

import com.google.gson.internal.LinkedTreeMap;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public class Dictionary extends ConcurrentHashMap<String, Object> {
    private static final long serialVersionUID = -751651785565449849L;

    public Dictionary() {
    }

    public Dictionary(Map<? extends String, ?> map) {
        super(map);
    }

    private static Dictionary of(boolean z, String str, Object obj, Object... objArr) {
        if ((objArr.length & 1) == 1) {
            throw new IllegalArgumentException("Size Required Multiples Of 2");
        }
        Dictionary dictionary = new Dictionary();
        dictionary.put(str, obj);
        for (int i = 0; i < objArr.length / 2; i++) {
            int i2 = i * 2;
            String str2 = (String) objArr[i2];
            Object obj2 = objArr[i2 + 1];
            if (!z || obj2 != null) {
                dictionary.put(str2, obj2);
            }
        }
        return dictionary;
    }

    public static Dictionary of(String str, Object obj, Object... objArr) {
        return of(false, str, obj, objArr);
    }

    public static Dictionary ofIgnoreNull(String str, Object obj, Object... objArr) {
        return of(true, str, obj, objArr);
    }

    public Object get(String[] strArr) {
        return get(strArr, 0, strArr.length);
    }

    public Object get(String[] strArr, int i, int i2) {
        int min = Math.min(i2, strArr.length);
        Object obj = this;
        while (i < min) {
            if (obj instanceof LinkedTreeMap) {
                obj = ((LinkedTreeMap) obj).get(strArr[i]);
            } else if (obj instanceof ArrayList) {
                obj = ((ArrayList) obj).get(Integer.parseInt(strArr[i]));
            } else {
                obj = ((Dictionary) obj).get(strArr[i]);
            }
            if (obj == null) {
                break;
            }
            i++;
            obj = obj;
        }
        return obj;
    }

    public <V> V get(String str, V v) {
        V v2 = (V) get(str.split("\\."));
        return v2 == null ? v : v2;
    }

    public String[] getStringArray(String str, String[] strArr) {
        return (String[]) get(str, strArr);
    }

    public String getString(String str, String str2) {
        Object obj = get(str, null);
        return obj == null ? str2 : String.valueOf(obj);
    }

    public int getInt(String str, int i) {
        return (int) getDouble(str, i);
    }

    public float getFloat(String str, float f) {
        return (float) getDouble(str, f);
    }

    public long getLong(String str, long j) {
        return (long) getDouble(str, Long.valueOf(j).longValue());
    }

    public double getDouble(String str, double d) {
        Object obj = get(str, null);
        if (obj == null) {
            return d;
        }
        if (obj instanceof String) {
            return Double.valueOf((String) obj).doubleValue();
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        if (obj instanceof Float) {
            return ((Float) obj).floatValue();
        }
        if (obj instanceof Double) {
            return ((Double) obj).doubleValue();
        }
        return Double.valueOf(String.valueOf(obj)).doubleValue();
    }

    public boolean getBoolean(String str, boolean z) {
        Object obj = get(str, null);
        if (obj == null) {
            return z;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return Boolean.getBoolean(String.valueOf(obj));
    }

    public Object removeByPath(String str) {
        String[] split = str.split("\\.");
        Object obj = get(split, 0, split.length - 1);
        if (obj != null && (obj instanceof AbstractMap)) {
            return ((AbstractMap) obj).remove(split[split.length - 1]);
        }
        return null;
    }
}
