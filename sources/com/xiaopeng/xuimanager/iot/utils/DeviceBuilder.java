package com.xiaopeng.xuimanager.iot.utils;

import android.util.Log;
import com.xiaopeng.xuimanager.iot.BaseDevice;
import com.xiaopeng.xuimanager.iot.devices.FragranceDevice;
import com.xiaopeng.xuimanager.iot.devices.FridgeDevice;
import com.xiaopeng.xuimanager.iot.devices.ScanDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class DeviceBuilder {
    public static final String FIELD_DEVICE_CLASS = "device_class";
    public static final String FIELD_DEVICE_ID = "device_id";
    public static final String FIELD_DEVICE_NAME = "device_name";
    public static final String FIELD_DEVICE_PROPERTY = "device_property";
    public static final String FIELD_DEVICE_TYPE = "device_type";
    private static final String TAG = "DeviceBuilder##";
    private static final Map<String, Class<?>> classMap = new HashMap();

    static {
        classMap.put(FragranceDevice.class.getSimpleName(), FragranceDevice.class);
        classMap.put(FridgeDevice.class.getSimpleName(), FridgeDevice.class);
        classMap.put(ScanDevice.class.getSimpleName(), ScanDevice.class);
    }

    public static JSONObject toJson(BaseDevice baseDevice) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject();
            try {
                jSONObject.put(FIELD_DEVICE_NAME, baseDevice.getDeviceName());
                jSONObject.put(FIELD_DEVICE_ID, baseDevice.getDeviceId());
                jSONObject.put(FIELD_DEVICE_TYPE, baseDevice.getDeviceType());
                jSONObject.put(FIELD_DEVICE_CLASS, baseDevice.getDeviceClass());
                Map<String, String> propertyMap = baseDevice.getPropertyMap();
                if (propertyMap != null) {
                    jSONObject.put(FIELD_DEVICE_PROPERTY, propToJson(propertyMap).toString());
                } else {
                    jSONObject.put(FIELD_DEVICE_PROPERTY, (Object) null);
                }
            } catch (JSONException e) {
                e = e;
                Log.e(TAG, "toJson:" + e.toString());
                return jSONObject;
            }
        } catch (JSONException e2) {
            e = e2;
            jSONObject = null;
        }
        return jSONObject;
    }

    public static JSONObject propToJson(BaseDevice baseDevice) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject();
            try {
                Map<String, String> propertyMap = baseDevice.getPropertyMap();
                if (propertyMap != null) {
                    for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
                        jSONObject.put(entry.getKey(), entry.getValue());
                    }
                }
            } catch (JSONException e) {
                e = e;
                Log.e(TAG, "propToJson by device:" + e.toString());
                return jSONObject;
            }
        } catch (JSONException e2) {
            e = e2;
            jSONObject = null;
        }
        return jSONObject;
    }

    public static JSONObject propToJson(Map<String, String> map) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject();
            if (map != null) {
                try {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        jSONObject.put(entry.getKey(), entry.getValue());
                    }
                } catch (JSONException e) {
                    e = e;
                    Log.e(TAG, "propToJson by map:" + e.toString());
                    return jSONObject;
                }
            }
        } catch (JSONException e2) {
            e = e2;
            jSONObject = null;
        }
        return jSONObject;
    }

    public static JSONArray toJsonArray(List<BaseDevice> list) {
        if (list == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (BaseDevice baseDevice : list) {
            JSONObject json = toJson(baseDevice);
            if (json != null) {
                jSONArray.put(json);
            }
        }
        return jSONArray;
    }

    public static Map<String, String> jsonStrToPropMap(String str) {
        JSONException e;
        HashMap hashMap;
        try {
            JSONObject jSONObject = new JSONObject(str);
            hashMap = new HashMap();
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, jSONObject.getString(next));
                }
            } catch (JSONException e2) {
                e = e2;
                Log.e(TAG, "jsonStrToPropMap:" + e.toString());
                return hashMap;
            }
        } catch (JSONException e3) {
            e = e3;
            hashMap = null;
        }
        return hashMap;
    }

    public static BaseDevice fromJson(JSONObject jSONObject) {
        BaseDevice baseDevice;
        Map<String, String> jsonStrToPropMap;
        try {
            baseDevice = (BaseDevice) classMap.get(jSONObject.getString(FIELD_DEVICE_CLASS)).newInstance();
        } catch (Exception e) {
            e = e;
            baseDevice = null;
        }
        try {
            baseDevice.setDeviceName(jSONObject.getString(FIELD_DEVICE_NAME)).setDeviceId(jSONObject.getString(FIELD_DEVICE_ID)).setDeviceType(jSONObject.getString(FIELD_DEVICE_TYPE)).setDeviceClass(jSONObject.getString(FIELD_DEVICE_CLASS));
            try {
                String string = jSONObject.getString(FIELD_DEVICE_PROPERTY);
                if (string != null && (jsonStrToPropMap = jsonStrToPropMap(string)) != null) {
                    baseDevice.setPropertyMap(jsonStrToPropMap);
                }
            } catch (JSONException e2) {
                Log.e(TAG, "fromJson 1:" + e2.toString());
            }
        } catch (Exception e3) {
            e = e3;
            Log.e(TAG, "fromJson 2:" + e.toString());
            return baseDevice;
        }
        return baseDevice;
    }

    public static List<BaseDevice> fromJsonArray(String str) {
        ArrayList arrayList = null;
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    BaseDevice fromJson = fromJson(jSONArray.getJSONObject(i));
                    if (fromJson != null) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(fromJson);
                    }
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "fromJsonArray:" + e.toString());
        }
        return arrayList;
    }
}
