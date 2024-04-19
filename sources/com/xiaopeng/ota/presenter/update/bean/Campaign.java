package com.xiaopeng.ota.presenter.update.bean;

import android.text.TextUtils;
import com.android.internal.util.ArrayUtils;
import com.xiaopeng.fota.sdk.EcuType;
import com.xiaopeng.ota.presenter.db.OperationEntity;
import com.xiaopeng.ota.utils.Base64Utils;
import com.xiaopeng.ota.utils.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import java.util.List;
/* loaded from: classes2.dex */
public class Campaign extends OperationEntity {
    public static final String ACTIVE_FLAG = "active_flag";
    public static final String CAMPAIGN_ID = "campaign_id";
    public static final String CAMPAIGN_JSON = "campaign_json";
    public static final String INSTALLED_FALG = "installed_flag";
    public static final String INSTALLED_TIME = "installed_time";
    private static final int NOTIFY_MODE_SILENT = 1;
    public static final String TABLE_NAME = "campaign";
    private static final String TAG = "Campaign";
    private boolean active;
    private long campaignId;
    private String campaignName;
    private List<List<Condition>> conditions;
    private int downloadMode;
    private int estimateCost;
    private List<Feature> features;
    private int identityCheck;
    private boolean installed;
    private long installedTime;
    private int notifyMode;
    private List<List<Package>> packages;
    private int permission;
    private int prepareCondition;
    private String releaseDate;
    private String releaseNotes;
    private String releaseNotesPrompt;
    private String releaseNotesSummary;
    private String releaseVersion;
    private int releaseVersionCode;
    private boolean supportSchedule;
    private int type;
    private List<String> upgradeConfirmations;
    private String upgradeFailedPrompt;
    private String upgradePrompt;
    private String upgradeSuccessPrompt;
    private String upgradedPrompt;
    private List<String> verifyEcus;

    public long getCampaignId() {
        return this.campaignId;
    }

    public void setCampaignId(long j) {
        this.campaignId = j;
    }

    public String getCampaignName() {
        return this.campaignName;
    }

    public void setCampaignName(String str) {
        this.campaignName = str;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean z) {
        this.active = z;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getNotifyMode() {
        return this.notifyMode;
    }

    public void setNotifyMode(int i) {
        this.notifyMode = i;
    }

    public int getPrepareCondition() {
        return this.prepareCondition;
    }

    public void setPrepareCondition(int i) {
        this.prepareCondition = i;
    }

    public int getPermission() {
        return this.permission;
    }

    public void setPermission(int i) {
        this.permission = i;
    }

    public int getDownloadMode() {
        return this.downloadMode;
    }

    public void setDownloadMode(int i) {
        this.downloadMode = i;
    }

    public int getEstimateCost() {
        return this.estimateCost;
    }

    public void setEstimateCost(int i) {
        this.estimateCost = i;
    }

    public int getIdentityCheck() {
        return this.identityCheck;
    }

    public void setIdentityCheck(int i) {
        this.identityCheck = i;
    }

    public boolean isSupportSchedule() {
        return this.supportSchedule;
    }

    public void setSupportSchedule(boolean z) {
        this.supportSchedule = z;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String str) {
        this.releaseDate = str;
    }

    public String getReleaseVersion() {
        return this.releaseVersion;
    }

    public void setReleaseVersion(String str) {
        this.releaseVersion = str;
    }

    public String getReleaseNotesPrompt() {
        return this.releaseNotesPrompt;
    }

    public void setReleaseNotesPrompt(String str) {
        this.releaseNotesPrompt = str;
    }

    public void setReleaseNotesSummary(String str) {
        this.releaseNotesSummary = str;
    }

    public void setReleaseNotes(String str) {
        this.releaseNotes = str;
    }

    public String getUpgradedPrompt() {
        if (this.upgradeSuccessPrompt != null) {
            return getUpgradeSuccessPrompt();
        }
        String str = this.upgradedPrompt;
        return str != null ? Base64Utils.decodeString(str) : "";
    }

    public void setUpgradedPrompt(String str) {
        this.upgradedPrompt = str;
    }

    public void setUpgradeSuccessPrompt(String str) {
        this.upgradeSuccessPrompt = str;
    }

    public String getUpgradeSuccessPrompt() {
        String str = this.upgradeSuccessPrompt;
        return str == null ? "" : Base64Utils.decodeString(str);
    }

    public void setUpgradeFailedPrompt(String str) {
        this.upgradeFailedPrompt = str;
    }

    public String getUpgradeFailedPrompt() {
        String str = this.upgradeFailedPrompt;
        return str == null ? "" : Base64Utils.decodeString(str);
    }

    public List<String> getVerifyEcus() {
        return this.verifyEcus;
    }

    public void setVerifyEcus(List<String> list) {
        this.verifyEcus = list;
    }

    public List<Feature> getFeatures() {
        return this.features;
    }

    public void setFeatures(List<Feature> list) {
        this.features = list;
    }

    public List<List<Package>> getPackages() {
        return this.packages;
    }

    public void setPackages(List<List<Package>> list) {
        this.packages = list;
    }

    public List<List<Condition>> getConditions() {
        return this.conditions;
    }

    public void setConditions(List<List<Condition>> list) {
        this.conditions = list;
    }

    public String getUpgradePrompt() {
        return this.upgradePrompt;
    }

    public void setUpgradePrompt(String str) {
        this.upgradePrompt = str;
    }

    public void setUpgradeConfirmations(List<String> list) {
        this.upgradeConfirmations = list;
    }

    public String getReleaseNotes() {
        return Base64Utils.decodeString(this.releaseNotes);
    }

    public String getHtmlReleaseNotes() {
        Feature feature;
        List<Feature> list = this.features;
        return (list == null || list.size() == 0 || (feature = this.features.get(0)) == null || TextUtils.isEmpty(feature.getContent())) ? "" : Base64Utils.decodeString(feature.getContent());
    }

    public String getInstallingTip() {
        return Base64Utils.decodeString(this.releaseNotesPrompt);
    }

    public String getUpgradeTip() {
        return Base64Utils.decodeString(this.upgradePrompt);
    }

    public List<String> getUpgradeConfirmations() {
        return Base64Utils.decodeArray(this.upgradeConfirmations);
    }

    public String getUpgradedTip() {
        return Base64Utils.decodeString(this.upgradedPrompt);
    }

    public boolean isSilentMode() {
        return getNotifyMode() == 1;
    }

    public String getReleaseNotesSummary() {
        return Base64Utils.decodeString(this.releaseNotesSummary);
    }

    public boolean isInstalled() {
        return this.installed;
    }

    public void setInstalled(boolean z) {
        this.installed = z;
    }

    public long getInstalledTime() {
        return this.installedTime;
    }

    public void setInstalledTime(long j) {
        this.installedTime = j;
    }

    public int getReleaseVersionCode() {
        return this.releaseVersionCode;
    }

    public void setReleaseVersionCode(int i) {
        this.releaseVersionCode = i;
    }

    public List<String> getImageUrls() {
        Feature feature;
        List<Feature> list = this.features;
        if (list == null || list.size() == 0 || (feature = this.features.get(0)) == null) {
            return null;
        }
        return feature.getImages();
    }

    public List<String> getVideoUrls() {
        Feature feature;
        List<Feature> list = this.features;
        if (list == null || list.size() == 0 || (feature = this.features.get(0)) == null) {
            return null;
        }
        return feature.getVideos();
    }

    public int getEcuCount() {
        if (ArrayUtils.isEmpty(this.packages) || ArrayUtils.isEmpty(this.packages.get(0))) {
            return 0;
        }
        return this.packages.get(0).size();
    }

    public int getEcuIndex(EcuType ecuType) {
        if (ecuType == null || ArrayUtils.isEmpty(this.packages) || ArrayUtils.isEmpty(this.packages.get(0))) {
            return 0;
        }
        for (int i = 0; i < this.packages.get(0).size(); i++) {
            Package r2 = this.packages.get(0).get(i);
            if (ecuType.name().equals(r2.getTypeName())) {
                return i + 1;
            }
            if (r2.getTypeName().equals("4G") && ecuType == EcuType.T4G) {
                return i + 1;
            }
        }
        return 0;
    }

    public int getRemainEstimateTime(EcuType ecuType) {
        int ecuIndex = getEcuIndex(ecuType);
        if (ecuIndex == 0) {
            return 0;
        }
        int i = 0;
        for (int i2 = ecuIndex - 1; i2 < this.packages.get(0).size(); i2++) {
            Package r2 = this.packages.get(0).get(i2);
            if (r2.getEstimateCostSecs() != null) {
                i += r2.getEstimateCostSecs().intValue();
            }
        }
        return i;
    }

    public static Campaign convert(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtils.d(TAG, "Json empty");
            return null;
        }
        Campaign campaign = (Campaign) JsonUtils.fromJson(str, (Class<Object>) Campaign.class);
        if (campaign.getCampaignId() == 0 && TextUtils.isEmpty(campaign.campaignName) && TextUtils.isEmpty(campaign.getReleaseVersion())) {
            LogUtils.e(TAG, "Json wrong format");
            return null;
        }
        return campaign;
    }
}
