package com.xiaopeng.ota.sdk.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.xiaopeng.ota.sdk.common.condition.Condition;
import com.xiaopeng.ota.sdk.common.condition.ConditionOperator;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class JsonUtils {
    public static <T> T fromJson(String str, Class<T> cls) {
        return (T) create().fromJson(str, (Class<Object>) cls);
    }

    public static <T> T fromJson(String str, Type type) {
        return (T) create().fromJson(str, type);
    }

    public static <T> String toJson(T t) {
        return new GsonBuilder().disableHtmlEscaping().create().toJson(t);
    }

    private static Gson create() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Condition.class, new ConditionDeserializer());
        return gsonBuilder.create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ConditionDeserializer implements JsonDeserializer<Condition> {
        private static final String TAG = "ConditionDeserializer";

        private ConditionDeserializer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public Condition deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            Condition condition = new Condition();
            condition.setType(asJsonObject.get("type").getAsString());
            condition.setName(asJsonObject.get("name").getAsString());
            condition.setOperator(asJsonObject.get("operator").getAsString());
            if (ConditionOperator.RANGE.getCode().equals(condition.getOperator()) || ConditionOperator.IN.getCode().equals(condition.getOperator()) || ConditionOperator.NIN.getCode().equals(condition.getOperator())) {
                JsonArray asJsonArray = asJsonObject.getAsJsonArray("value");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < asJsonArray.size(); i++) {
                    JsonPrimitive asJsonPrimitive = asJsonArray.get(i).getAsJsonPrimitive();
                    if (asJsonPrimitive.isNumber()) {
                        arrayList.add(Integer.valueOf(asJsonPrimitive.getAsInt()));
                    } else if (asJsonPrimitive.isBoolean()) {
                        arrayList.add(Boolean.valueOf(asJsonPrimitive.getAsBoolean()));
                    } else if (asJsonPrimitive.isString()) {
                        arrayList.add(asJsonPrimitive.getAsString());
                    } else {
                        arrayList.add(asJsonPrimitive.getAsString());
                    }
                }
                condition.setValue(arrayList);
            } else {
                condition.setValue(asJsonObject.get("value").getAsString());
            }
            if (asJsonObject.has("alert")) {
                condition.setAlert(asJsonObject.get("alert").getAsString());
            }
            return condition;
        }
    }

    public static Map<String, Object> toMap(JSONObject jSONObject) throws JSONException {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.get(next));
        }
        return hashMap;
    }
}
