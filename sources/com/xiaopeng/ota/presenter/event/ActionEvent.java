package com.xiaopeng.ota.presenter.event;

import com.xiaopeng.ota.presenter.event.CampaignEvent;
import com.xiaopeng.ota.sdk.eventbroker.Event;
/* loaded from: classes2.dex */
public class ActionEvent extends Event {
    public static final String ACTION_AI_COMMON_FAIL_CANNOT_RETRY = "ACTION_AI_COMMON_FAIL_CANNOT_RETRY";
    public static final String ACTION_AI_COMMON_FAIL_CAN_RETRY = "ACTION_AI_COMMON_FAIL_CAN_RETRY";
    public static final String ACTION_AI_COMMON_SUCCESS = "ACTION_AI_COMMON_SUCCESS";
    public static final String ACTION_AI_NOT_REGULAR_POS_SCHEDULE_UPGRADE = "ACTION_AI_NOT_REGULAR_POS_SCHEDULE_UPGRADE";
    public static final String ACTION_AI_NOT_REGULAR_POS_UPGRADE = "ACTION_AI_NOT_REGULAR_POS_UPGRADE";
    public static final String ACTION_AI_OP_CALL_CS_FORMAT = "ACTION_AI_OP_CALL_CS_%d";
    public static final String ACTION_AI_OP_SCHEDULE_SETTING_FORMAT = "ACTION_AI_OP_SCHEDULE_SETTING_%d";
    public static final String ACTION_AI_OP_SCHEDULE_UPGRADE_FORMAT = "ACTION_AI_OP_SCHEDULE_UPGRADE_%d";
    public static final String ACTION_AI_OP_SHOW_UPGRADE_INFO_FORMAT = "ACTION_AI_OP_SHOW_UPGRADE_INFO_%d";
    public static final String ACTION_AI_OP_UPGRADE_NOW_FORMAT = "ACTION_AI_OP_UPGRADE_NOW_%d";
    public static final String ACTION_AI_REGULAR_POS_SCHEDULE_UPGRADE = "ACTION_AI_REGULAR_POS_SCHEDULE_UPGRADE";
    public static final String ACTION_AI_REGULAR_POS_UPGRADE = "ACTION_AI_REGULAR_POS_UPGRADE";
    public static final String ACTION_AI_SCHEDULE_ARRIVED = "ACTION_AI_SCHEDULE_ARRIVED";
    public static final String ACTION_AI_SCHEDULE_FAIL_CANNOT_RETRY = "ACTION_AI_SCHEDULE_FAIL_CANNOT_RETRY";
    public static final String ACTION_AI_SCHEDULE_FAIL_CAN_RETRY = "ACTION_AI_SCHEDULE_FAIL_CAN_RETRY";
    public static final String ACTION_AI_SCHEDULE_SUCCESS = "ACTION_AI_SCHEDULE_SUCCESS";
    public static final String ACTION_AUTO_UPGRADE = "ACTION_AUTO_UPGRADE";
    public static final String ACTION_AUTO_UPGRADE_AUTOMATIC = "ACTION_AUTO_UPGRADE_AUTOMATIC";
    public static final String ACTION_AUTO_UPGRADE_DIALOG_DISMISS = "ACTION_AUTO_UPGRADE_DIALOG_DISMISS";
    public static final String ACTION_AUTO_UPGRADE_DIALOG_POPUP = "ACTION_AUTO_UPGRADE_DIALOG_POPUP";
    public static final String ACTION_AUTO_UPGRADE_SCHEDULE_TIME = "ACTION_AUTO_UPGRADE_SCHEDULE_TIME";
    public static final String ACTION_BACK = "ACTION_BACK";
    public static final String ACTION_CAMPAIGN_EXPIRE_DIALOG_CLOSE = "ACTION_CAMPAIGN_EXPIRE_DIALOG_CLOSE";
    public static final String ACTION_CANCEL = "ACTION_CANCEL";
    public static final String ACTION_CANCEL_AUTO_UPGRADE = "ACTION_CANCEL_AUTO_UPGRADE";
    public static final String ACTION_CANCEL_SCHEDULE = "ACTION_CANCEL_SCHEDULE";
    public static final String ACTION_CLOSE = "ACTION_CLOSE";
    public static final String ACTION_CONDITION_MISMATCH = "ACTION_CONDITION_MISMATCH";
    public static final String ACTION_CONDITION_MISMATCH_DIALOG_CLOSE = "ACTION_CONDITION_MISMATCH_DIALOG_CLOSE";
    public static final String ACTION_CONFIRM_AUTO_UPGRADE = "ACTION_CONFIRM_AUTO_UPGRADE";
    public static final String ACTION_CONFIRM_PARK = "ACTION_CONFIRM_PARK";
    public static final String ACTION_CONFIRM_SCHEDULE_TIME = "ACTION_CONFIRM_SCHEDULE_TIME";
    public static final String ACTION_CONFIRM_UPGRADE = "ACTION_CONFIRM_UPGRADE";
    public static final String ACTION_GEAR_LEVEL = "ACTION_GEAR_LEVEL";
    public static final String ACTION_H5_LINK = "ACTION_H5_LINK";
    public static final String ACTION_IDENTITY_CHECK = "ACTION_IDENTITY_CHECK";
    public static final String ACTION_MODIFY_SCHEDULE_TIME = "ACTION_MODIFY_SCHEDULE_TIME";
    public static final String ACTION_OK = "ACTION_OK";
    public static final String ACTION_PROMPT_UPGRADE_COMMON = "ACTION_PROMPT_UPGRADE_COMMON";
    public static final String ACTION_PROMPT_UPGRADE_WARN = "ACTION_PROMPT_UPGRADE_WARN";
    public static final String ACTION_SCHEDULE = "ACTION_SCHEDULE";
    public static final String ACTION_SCHEDULE_FAIL = "ACTION_SCHEDULE_FAIL";
    public static final String ACTION_SCHEDULE_UPGRADE = "ACTION_SCHEDULE_UPGRADE";
    public static final String ACTION_SPEECH = "ACTION_SPEECH";
    public static final String ACTION_STILL_UPGRADE = "ACTION_STILL_UPGRADE";
    public static final String ACTION_UPGRADE_ICON_CLICKED = "ACTION_UPGRADE_ICON_CLICKED";
    public static final String ACTION_UPGRADE_IMMEDIATELY = "ACTION_UPGRADE_IMMEDIATELY";
    public static final String ACTION_UPGRADE_LATER = "ACTION_UPGRADE_LATER";
    public static final String ACTION_WAKE_UP = "ACTION_WAKE_UP";
    public static final String ACTION_XLOGO_CLICKED = "ACTION_XLOGO_CLICKED";
    private String action;
    private long campaignId;
    private String context;
    private CampaignEvent.State state;

    private ActionEvent(long j, String str) {
        this.state = CampaignEvent.State.OK;
        this.campaignId = j;
        this.action = str;
    }

    private ActionEvent(long j, String str, String str2) {
        this(j, str);
        this.context = str2;
    }

    private ActionEvent(long j, String str, String str2, CampaignEvent.State state) {
        this(j, str);
        this.context = str2;
        if (state != null) {
            this.state = state;
        }
    }

    public long getCampaignId() {
        return this.campaignId;
    }

    public String getAction() {
        return this.action;
    }

    public CampaignEvent.State getState() {
        return this.state;
    }

    public String getContext() {
        return this.context;
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private String action;
        private long campaignId;
        private String context;
        private CampaignEvent.State state;

        public Builder setCampaignId(long j) {
            this.campaignId = j;
            return this;
        }

        public Builder setAction(String str) {
            this.action = str;
            return this;
        }

        public Builder setContext(String str) {
            this.context = str;
            return this;
        }

        public Builder setState(CampaignEvent.State state) {
            this.state = state;
            return this;
        }

        public ActionEvent build() {
            return new ActionEvent(this.campaignId, this.action, this.context, this.state);
        }
    }
}
