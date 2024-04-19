package com.xiaopeng.ota.presenter.update.bean;

import android.text.TextUtils;
import com.xiaopeng.ota.Constants;
/* loaded from: classes2.dex */
public enum UpgradeTrigger {
    INIT("init"),
    AUTO("auto"),
    CDU(Constants.Schedule.FROM_CDU),
    SCHEDULE(Constants.Schedule.KEY_SCHEDULE),
    BACKEND("backend"),
    APP("app"),
    RPM("rpm"),
    UNKNOWN("unknown");
    
    private String code;

    UpgradeTrigger(String str) {
        this.code = str;
    }

    public String getCode() {
        return this.code;
    }

    public static UpgradeTrigger getByCode(String str) {
        UpgradeTrigger[] values;
        if (TextUtils.isEmpty(str)) {
            return UNKNOWN;
        }
        for (UpgradeTrigger upgradeTrigger : values()) {
            if (upgradeTrigger.getCode().equals(str)) {
                return upgradeTrigger;
            }
        }
        return UNKNOWN;
    }
}
