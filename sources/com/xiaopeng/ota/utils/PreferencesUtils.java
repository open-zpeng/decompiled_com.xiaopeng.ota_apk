package com.xiaopeng.ota.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;
/* loaded from: classes2.dex */
public class PreferencesUtils {
    public static String DEFAULT_PREFERENCE_NAME = "com.xiaopeng.ota.preferences";

    public static boolean putAll(Context context, Map<String, String> map) {
        return putAll(context, DEFAULT_PREFERENCE_NAME, map);
    }

    public static boolean putAll(Context context, String str, Map<String, String> map) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            edit.putString(entry.getKey(), entry.getValue());
        }
        return edit.commit();
    }

    public static Map<String, ?> getAll(Context context) {
        return getAll(context, DEFAULT_PREFERENCE_NAME);
    }

    public static Map<String, ?> getAll(Context context, String str) {
        return context.getSharedPreferences(str, 0).getAll();
    }

    public static boolean putString(Context context, String str, String str2) {
        return putString(context, DEFAULT_PREFERENCE_NAME, str, str2);
    }

    public static boolean putString(Context context, String str, String str2, String str3) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString(str2, str3);
        return edit.commit();
    }

    public static String getString(Context context, String str, String str2) {
        return getString(context, DEFAULT_PREFERENCE_NAME, str, str2);
    }

    public static String getString(Context context, String str, String str2, String str3) {
        return context.getSharedPreferences(str, 0).getString(str2, str3);
    }

    public static boolean putInt(Context context, String str, int i) {
        return putInt(context, DEFAULT_PREFERENCE_NAME, str, i);
    }

    public static boolean putInt(Context context, String str, String str2, int i) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putInt(str2, i);
        return edit.commit();
    }

    public static int getInt(Context context, String str, int i) {
        return getInt(context, DEFAULT_PREFERENCE_NAME, str, i);
    }

    public static int getInt(Context context, String str, String str2, int i) {
        return context.getSharedPreferences(str, 0).getInt(str2, i);
    }

    public static boolean putLong(Context context, String str, long j) {
        return putLong(context, DEFAULT_PREFERENCE_NAME, str, j);
    }

    public static boolean putLong(Context context, String str, String str2, long j) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putLong(str2, j);
        return edit.commit();
    }

    public static long getLong(Context context, String str, long j) {
        return getLong(context, DEFAULT_PREFERENCE_NAME, str, j);
    }

    public static long getLong(Context context, String str, String str2, long j) {
        return context.getSharedPreferences(str, 0).getLong(str2, j);
    }

    public static boolean putFloat(Context context, String str, float f) {
        return putFloat(context, DEFAULT_PREFERENCE_NAME, str, f);
    }

    public static boolean putFloat(Context context, String str, String str2, float f) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putFloat(str2, f);
        return edit.commit();
    }

    public static float getFloat(Context context, String str, float f) {
        return getFloat(context, DEFAULT_PREFERENCE_NAME, str, f);
    }

    public static float getFloat(Context context, String str, String str2, float f) {
        return context.getSharedPreferences(str, 0).getFloat(str2, f);
    }

    public static boolean putBoolean(Context context, String str, boolean z) {
        return putBoolean(context, DEFAULT_PREFERENCE_NAME, str, z);
    }

    public static boolean putBoolean(Context context, String str, String str2, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putBoolean(str2, z);
        return edit.commit();
    }

    public static boolean getBoolean(Context context, String str, boolean z) {
        return getBoolean(context, DEFAULT_PREFERENCE_NAME, str, z);
    }

    public static boolean getBoolean(Context context, String str, String str2, boolean z) {
        return context.getSharedPreferences(str, 0).getBoolean(str2, z);
    }

    public static boolean contains(Context context, String str) {
        return contains(context, DEFAULT_PREFERENCE_NAME, str);
    }

    public static boolean contains(Context context, String str, String str2) {
        return context.getSharedPreferences(str, 0).contains(str2);
    }

    public static boolean remove(Context context, String str) {
        return remove(context, DEFAULT_PREFERENCE_NAME, str);
    }

    public static boolean remove(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.remove(str2);
        return edit.commit();
    }

    public static boolean clear(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.clear();
        return edit.commit();
    }
}
