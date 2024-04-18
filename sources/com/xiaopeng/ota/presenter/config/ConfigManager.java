package com.xiaopeng.ota.presenter.config;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.xiaopeng.fota.sdk.UpgradeUtils;
import com.xiaopeng.ota.LocaleConfig;
import com.xiaopeng.ota.sdk.common.OTAConstants;
import com.xiaopeng.ota.sdk.common.util.ArrayUtils;
import com.xiaopeng.ota.sdk.common.util.Base64Utils;
import com.xiaopeng.ota.sdk.common.util.Dictionary;
import com.xiaopeng.ota.sdk.common.util.DigestUtils;
import com.xiaopeng.ota.sdk.common.util.JsonUtils;
import com.xiaopeng.ota.sdk.common.util.PreferencesUtils;
import com.xiaopeng.ota.sdk.common.util.Values;
import com.xiaopeng.ota.sdk.exception.NotFoundException;
import com.xiaopeng.ota.utils.LogUtils;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class ConfigManager {
    private static final String KEY_REMOTE_PROPERTIES = "REMOTE_PROPERTIES";
    private static final String KEY_SERVER_URL = "SERVER_URL";
    private static final String KEY_SERVER_URL_OLD = "COMMON.OTA_SERVER_URL";
    private static final String KEY_UPLOAD_EVENT = "UPLOAD_EVENT";
    private static final long MIN_SYNC_TIME_INTERVAL = 60000;
    private static final String PREFERENCE_NAME = PreferencesUtils.DEFAULT_PREFERENCE_NAME + ".sdk";
    private static final String TAG = "ConfigManager";
    private Context mContext;
    private long mLastSyncTimestamp;
    private Dictionary mCachedPreferences = new Dictionary();
    private volatile boolean mLoaded = false;

    public void dispose() {
    }

    public ConfigManager(Context context) {
        this.mContext = context;
    }

    public void init() {
        load();
        String serverUrlInternal = getServerUrlInternal();
        if (this.mCachedPreferences.isEmpty() || TextUtils.isEmpty(serverUrlInternal)) {
            return;
        }
        this.mLoaded = true;
        if (serverUrlInternal.equals(LocaleConfig.getHttpHost())) {
            return;
        }
        LogUtils.d(TAG, "Cached server host is not default, notify SOTA");
        sendServerUrlChangedBroadcast(serverUrlInternal);
    }

    public void sync(boolean z) {
        boolean z2;
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this) {
            if (currentTimeMillis - this.mLastSyncTimestamp < 60000) {
                LogUtils.d(TAG, "Synced (now=%d, last=%d)", Long.valueOf(currentTimeMillis), Long.valueOf(this.mLastSyncTimestamp));
                return;
            }
            String serverUrlInternal = getServerUrlInternal();
            String OtaGetRemoteConfig = UpgradeUtils.OtaGetRemoteConfig();
            if (TextUtils.isEmpty(OtaGetRemoteConfig)) {
                LogUtils.d(TAG, "Remote config empty");
                return;
            }
            LogUtils.d(TAG, "Remote config: " + OtaGetRemoteConfig);
            Map map = (Map) JsonUtils.fromJson(OtaGetRemoteConfig, new TypeToken<Map<String, Object>>() { // from class: com.xiaopeng.ota.presenter.config.ConfigManager.1
            }.getType());
            if (map == null || map.isEmpty()) {
                z2 = false;
            } else {
                Dictionary dictionary = new Dictionary(map);
                String serverUrlInternal2 = getServerUrlInternal(dictionary);
                z2 = !((String) Values.value(serverUrlInternal, "")).equals(serverUrlInternal2);
                if (!z && z2) {
                    LogUtils.d(TAG, "Server has changed and skip config sync (old=%s,new=%s)", Values.value(serverUrlInternal, ""), Values.value(serverUrlInternal2, ""));
                    return;
                }
                save(KEY_REMOTE_PROPERTIES, dictionary);
            }
            String serverUrl = getServerUrl();
            if (!isLoaded()) {
                this.mLoaded = true;
            }
            if (z2) {
                LogUtils.d(TAG, "Server has changed, send broadcast and reload campaign (old=%s, new=%s)", Values.value(serverUrlInternal, ""), serverUrl);
                sendServerUrlChangedBroadcast(serverUrl);
            }
            this.mLastSyncTimestamp = System.currentTimeMillis();
            LogUtils.d(TAG, "Sync remote configuration success (size=%d)", Integer.valueOf(this.mCachedPreferences.size()));
        }
    }

    private void load() {
        Map map;
        String string = PreferencesUtils.getString(this.mContext, PREFERENCE_NAME, KEY_REMOTE_PROPERTIES, "");
        if (TextUtils.isEmpty(string)) {
            PreferencesUtils.clear(this.mContext, PREFERENCE_NAME);
            return;
        }
        String decodeString = Base64Utils.decodeString(string);
        if (TextUtils.isEmpty(decodeString) || (map = (Map) JsonUtils.fromJson(decodeString, new TypeToken<Map<String, Object>>() { // from class: com.xiaopeng.ota.presenter.config.ConfigManager.2
        }.getType())) == null) {
            return;
        }
        this.mCachedPreferences.clear();
        this.mCachedPreferences.putAll(map);
    }

    public void save(String str, Map<String, Object> map) {
        this.mCachedPreferences.clear();
        this.mCachedPreferences.putAll(map);
        PreferencesUtils.putString(this.mContext, PREFERENCE_NAME, str, Base64Utils.encode(JsonUtils.toJson(map)));
    }

    public void remove(String str) {
        this.mCachedPreferences.remove(str);
        PreferencesUtils.remove(this.mContext, PREFERENCE_NAME, str);
    }

    public boolean isLoaded() {
        return this.mLoaded;
    }

    public String getEnvironment() {
        try {
            return DigestUtils.MD5(getServerUrl());
        } catch (Exception unused) {
            return null;
        }
    }

    private void sendServerUrlChangedBroadcast(String str) {
        if (this.mContext == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(OTAConstants.Action.CAMPAIGN_HOST_CHANGED);
        intent.putExtra("host", str);
        intent.setPackage(this.mContext.getPackageName());
        this.mContext.sendBroadcastAsUser(intent, UserHandle.CURRENT);
    }

    public String getString(String str, String str2) {
        return this.mCachedPreferences.getString(str, str2);
    }

    public int getInt(String str, int i) {
        return this.mCachedPreferences.getInt(str, i);
    }

    public float getFloat(String str, float f) {
        return this.mCachedPreferences.getFloat(str, f);
    }

    public long getLong(String str, long j) {
        return this.mCachedPreferences.getLong(str, j);
    }

    public double getDouble(String str, double d) {
        return this.mCachedPreferences.getDouble(str, d);
    }

    public boolean getBoolean(String str, boolean z) {
        return this.mCachedPreferences.getBoolean(str, z);
    }

    private String getServerUrlInternal() {
        return getServerUrlInternal(this.mCachedPreferences);
    }

    private String getServerUrlInternal(Dictionary dictionary) {
        return dictionary.getString("SERVER_URL", dictionary.getString(KEY_SERVER_URL_OLD, null));
    }

    public String getServerUrl() throws NotFoundException {
        String serverUrlInternal = getServerUrlInternal();
        if (serverUrlInternal != null) {
            return serverUrlInternal;
        }
        throw new NotFoundException("Server address not found");
    }

    public boolean isUploadEventEnabled() {
        return getBoolean(KEY_UPLOAD_EVENT, true);
    }

    public boolean isPackageAvailable(String str, String str2) {
        LogUtils.d(TAG, "isPackageAvailable, ecu: %s, md5: %s", str, str2);
        String string = getString("AVAILABLE_PACKAGES." + str, null);
        if (TextUtils.isEmpty(string)) {
            return true;
        }
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        List<AvailableObject> list = (List) JsonUtils.fromJson(string, new TypeToken<List<AvailableObject>>() { // from class: com.xiaopeng.ota.presenter.config.ConfigManager.3
        }.getType());
        if (ArrayUtils.isEmpty(list)) {
            return false;
        }
        for (AvailableObject availableObject : list) {
            if (str2.equals(availableObject.getHash())) {
                return true;
            }
        }
        return false;
    }
}
