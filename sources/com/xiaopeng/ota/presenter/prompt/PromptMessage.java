package com.xiaopeng.ota.presenter.prompt;

import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.presenter.update.bean.UpgradeResult;
/* loaded from: classes2.dex */
public class PromptMessage {
    public static final int TYPE_COMMON_UPGRADE = 1;
    public static final int TYPE_CONDITION_MISMATCH = 7;
    public static final int TYPE_SCHEDULE_ARRIVED = 3;
    public static final int TYPE_SCHEDULE_UPGRADE = 2;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_UPGRADE_FAIL = 5;
    public static final int TYPE_UPGRADE_SUCCESS = 4;
    public static final int TYPE_VERIFY_FAIL = 6;
    private long campaignId;
    private boolean canRetry;
    private boolean isSchedule;
    private String scheduleTime;
    private int type;
    private UpgradeResult upgradeResult;

    public PromptMessage(int i, Campaign campaign) {
        this.type = i;
        this.campaignId = campaign.getCampaignId();
    }

    public PromptMessage(int i, Campaign campaign, String str) {
        this.type = i;
        this.campaignId = campaign.getCampaignId();
        this.scheduleTime = str;
    }

    public PromptMessage(int i, Campaign campaign, boolean z) {
        this.type = i;
        this.campaignId = campaign.getCampaignId();
        this.isSchedule = z;
    }

    public PromptMessage(int i, Campaign campaign, boolean z, boolean z2) {
        this.type = i;
        this.campaignId = campaign.getCampaignId();
        this.isSchedule = z;
        this.canRetry = z2;
    }

    public PromptMessage(int i, Campaign campaign, boolean z, boolean z2, String str) {
        this.type = i;
        this.campaignId = campaign.getCampaignId();
        this.isSchedule = z;
        this.canRetry = z2;
        this.scheduleTime = str;
    }

    public PromptMessage(int i, Campaign campaign, UpgradeResult upgradeResult) {
        this(i, campaign);
        this.upgradeResult = upgradeResult;
    }

    public int getType() {
        return this.type;
    }

    public long getCampaignId() {
        return this.campaignId;
    }

    public boolean isSchedule() {
        return this.isSchedule;
    }

    public boolean isCanRetry() {
        return this.canRetry;
    }

    public String getScheduleTime() {
        return this.scheduleTime;
    }

    public UpgradeResult getUpgradeResult() {
        return this.upgradeResult;
    }

    public void setUpgradeResult(UpgradeResult upgradeResult) {
        this.upgradeResult = upgradeResult;
    }
}
