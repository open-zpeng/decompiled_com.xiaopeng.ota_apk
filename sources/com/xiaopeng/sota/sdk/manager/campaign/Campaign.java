package com.xiaopeng.sota.sdk.manager.campaign;

import com.google.gson.annotations.SerializedName;
import com.xiaopeng.ota.sdk.common.OperationEntity;
import com.xiaopeng.ota.sdk.common.util.ArrayUtils;
import java.util.List;
/* loaded from: classes2.dex */
public class Campaign extends OperationEntity {
    public static final String CAMPAIGN = "campaign";
    public static final String CAMPAIGN_ID = "campaign_id";
    public static final String CDU_FINGERPRINT = "cdu_fingerprint";
    public static final String ENVIRONMENT = "environment";
    public static final String EXTRA = "extra";
    public static final String HOST = "host";
    public static final String PACKAGE = "package";
    public static final String STATE = "state";
    public static final int STATE_COMPLETE = 3;
    public static final int STATE_DOWNLOAD = 1;
    public static final int STATE_DOWNLOAD_COMPLETE = 10;
    public static final int STATE_ERROR = 4;
    public static final int STATE_INIT = 0;
    public static final int STATE_INSTALL = 2;
    public static final String TABLE_NAME = "campaign";
    public static final String TRACE_ID = "trace_id";
    public static final String VALID = "valid";
    private Long campaignId;
    private String campaignName;
    @SerializedName("package")
    private CampaignPackage campaignPackage;
    private String cduFingerprint;
    private String environment;
    private String extra;
    private String host;
    private String packageJson;
    private String releaseNotes;
    private int state;
    private String traceId;
    private Integer upgradeMode;
    private boolean valid;

    public String getEnvironment() {
        return this.environment;
    }

    public void setEnvironment(String str) {
        this.environment = str;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public void setTraceId(String str) {
        this.traceId = str;
    }

    public Long getCampaignId() {
        return this.campaignId;
    }

    public void setCampaignId(Long l) {
        this.campaignId = l;
    }

    public String getCampaignName() {
        return this.campaignName;
    }

    public void setCampaignName(String str) {
        this.campaignName = str;
    }

    public Integer getUpgradeMode() {
        return this.upgradeMode;
    }

    public void setUpgradeMode(Integer num) {
        this.upgradeMode = num;
    }

    public String getReleaseNotes() {
        return this.releaseNotes;
    }

    public void setReleaseNotes(String str) {
        this.releaseNotes = str;
    }

    public CampaignPackage getCampaignPackage() {
        return this.campaignPackage;
    }

    public void setCampaignPackage(CampaignPackage campaignPackage) {
        this.campaignPackage = campaignPackage;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public String getPackageJson() {
        return this.packageJson;
    }

    public void setPackageJson(String str) {
        this.packageJson = str;
    }

    public boolean isValid() {
        return this.valid;
    }

    public void setValid(boolean z) {
        this.valid = z;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public String getCduFingerprint() {
        return this.cduFingerprint;
    }

    public void setCduFingerprint(String str) {
        this.cduFingerprint = str;
    }

    public String toString() {
        return "Campaign{traceId=" + this.traceId + ", campaignId=" + this.campaignId + ", campaignName=" + this.campaignName + ", upgradeMode=" + this.upgradeMode + ", state=" + this.state + ", cduFingerprint=" + this.cduFingerprint + ", valid=" + this.valid + ", package=" + this.campaignPackage.toString() + '}';
    }

    public static String toString(List<Campaign> list) {
        StringBuilder sb = new StringBuilder("CampaignList{");
        if (ArrayUtils.isEmpty(list)) {
            sb.append('}');
            return sb.toString();
        }
        for (Campaign campaign : list) {
            sb.append(campaign.toString());
        }
        sb.append('}');
        return sb.toString();
    }
}
