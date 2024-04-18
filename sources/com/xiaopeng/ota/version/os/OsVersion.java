package com.xiaopeng.ota.version.os;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.xiaopeng.ota.presenter.update.bean.Feature;
import com.xiaopeng.ota.sdk.common.OperationEntity;
import com.xiaopeng.ota.sdk.common.util.ArrayUtils;
import com.xiaopeng.ota.sdk.common.util.JsonUtils;
import java.util.List;
/* loaded from: classes2.dex */
public class OsVersion extends OperationEntity {
    public static final String FEATURES = "features";
    public static final String TABLE_NAME = "os_version";
    public static final String VERSION_CODE = "version_code";
    public static final String VERSION_NAME = "version_name";
    private List<Feature> features;
    private int versionCode;
    private String versionName;

    public void setFeaturesWithJson(String str) {
        if (TextUtils.isEmpty(str)) {
            this.features = null;
        } else {
            this.features = (List) JsonUtils.fromJson(str, new TypeToken<List<Feature>>() { // from class: com.xiaopeng.ota.version.os.OsVersion.1
            }.getType());
        }
    }

    public String getFeaturesJson() {
        return ArrayUtils.isEmpty(this.features) ? "" : JsonUtils.toJson(this.features);
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(int i) {
        this.versionCode = i;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void setVersionName(String str) {
        this.versionName = str;
    }

    public List<Feature> getFeatures() {
        return this.features;
    }

    public void setFeatures(List<Feature> list) {
        this.features = list;
    }
}
