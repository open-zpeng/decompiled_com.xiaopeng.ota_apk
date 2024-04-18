package com.xiaopeng.sota.sdk.manager;

import android.content.Context;
import android.os.SystemClock;
import com.xiaopeng.ota.sdk.common.util.PreferencesUtils;
import com.xiaopeng.sota.sdk.SOTAManager;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class ConfigManager {
    private static final String KEY_HOST = "HOST";
    private static final String KEY_INSTALLING = "INSTALLING";
    private static final String KEY_INSTALLING_TIMESTAMP = "INSTALLING_TIMESTAMP";
    private static final String KEY_INSTALL_NUMBER = "INSTALL_NUMBER";
    private static final String PREFERENCE_NAME = PreferencesUtils.DEFAULT_PREFERENCE_NAME + ".sota";
    private static final String TAG = "ConfigManager";
    private Context mContext = SOTAManager.getContext();

    public void clear() {
        PreferencesUtils.remove(this.mContext, KEY_INSTALL_NUMBER);
        PreferencesUtils.remove(this.mContext, KEY_INSTALLING);
    }

    public void setInstallNumber(int i) {
        PreferencesUtils.putInt(this.mContext, PREFERENCE_NAME, KEY_INSTALL_NUMBER, i);
    }

    public int getInstallNumber() {
        return PreferencesUtils.getInt(this.mContext, PREFERENCE_NAME, KEY_INSTALL_NUMBER, 0);
    }

    public void setInstallingState(boolean z) {
        PreferencesUtils.putBoolean(this.mContext, PREFERENCE_NAME, KEY_INSTALLING, z);
        PreferencesUtils.putLong(this.mContext, PREFERENCE_NAME, KEY_INSTALLING_TIMESTAMP, SystemClock.elapsedRealtime());
    }

    public boolean isInstalling() {
        boolean z = PreferencesUtils.getBoolean(this.mContext, PREFERENCE_NAME, KEY_INSTALLING, false);
        if (SystemClock.elapsedRealtime() - PreferencesUtils.getLong(this.mContext, PREFERENCE_NAME, KEY_INSTALLING_TIMESTAMP, 0L) > TimeUnit.MINUTES.toMillis(30L)) {
            return false;
        }
        return z;
    }

    public void setHost(String str) {
        PreferencesUtils.putString(this.mContext, PREFERENCE_NAME, KEY_HOST, str);
    }

    public String getHost() {
        return PreferencesUtils.getString(this.mContext, PREFERENCE_NAME, KEY_HOST, null);
    }
}
