package com.xiaopeng.ota.activity;

import android.car.Car;
import android.webkit.JavascriptInterface;
import com.xiaopeng.lib.utils.info.BuildInfoUtils;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.utils.JsonUtils;
import com.xiaopeng.ota.utils.VersionUtils;
/* loaded from: classes2.dex */
public class JsInterface {
    private int mSource;

    public JsInterface(int i) {
        this.mSource = i;
    }

    @JavascriptInterface
    public String getClientInfo() {
        JsData jsData = new JsData();
        jsData.setSource(this.mSource);
        jsData.setOsVersion(OTAManager.getOsVersionCode());
        try {
            jsData.setVehicleModel(Car.getHardwareCarType());
        } catch (Exception unused) {
        }
        jsData.setCdu(assembleCduVersion());
        return JsonUtils.toJson(jsData);
    }

    private Version assembleCduVersion() {
        Version version = new Version();
        String fullSystemVersion = BuildInfoUtils.getFullSystemVersion();
        version.setVersion(fullSystemVersion);
        version.setSimpleVersion(VersionUtils.getSoftwareFromFingerprint(fullSystemVersion));
        return version;
    }

    /* loaded from: classes2.dex */
    private class JsData {
        private Version cdu;
        private int osVersion;
        private int source;
        private String vehicleModel;

        private JsData() {
        }

        public int getSource() {
            return this.source;
        }

        public void setSource(int i) {
            this.source = i;
        }

        public String getVehicleModel() {
            return this.vehicleModel;
        }

        public void setVehicleModel(String str) {
            this.vehicleModel = str;
        }

        public int getOsVersion() {
            return this.osVersion;
        }

        public void setOsVersion(int i) {
            this.osVersion = i;
        }

        public Version getCdu() {
            return this.cdu;
        }

        public void setCdu(Version version) {
            this.cdu = version;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class Version {
        private String name;
        private String simpleVersion;
        private String version;

        private Version() {
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getVersion() {
            return this.version;
        }

        public void setVersion(String str) {
            this.version = str;
        }

        public String getSimpleVersion() {
            return this.simpleVersion;
        }

        public void setSimpleVersion(String str) {
            this.simpleVersion = str;
        }
    }
}
