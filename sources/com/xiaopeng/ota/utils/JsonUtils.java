package com.xiaopeng.ota.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
/* loaded from: classes2.dex */
public class JsonUtils {
    public static <T> T fromJson(String str, Class<T> cls) {
        return (T) new Gson().fromJson(str, (Class<Object>) cls);
    }

    public static <T> T fromJson(String str, Type type) {
        return (T) new Gson().fromJson(str, type);
    }

    public static <T> String toJson(T t) {
        return new GsonBuilder().disableHtmlEscaping().create().toJson(t);
    }

    public static <T> List<T> listFromJson(String str) {
        return (List) fromJson(str, new TypeToken<List<T>>() { // from class: com.xiaopeng.ota.utils.JsonUtils.1
        }.getType());
    }
}
