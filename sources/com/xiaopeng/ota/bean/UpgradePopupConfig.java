package com.xiaopeng.ota.bean;

import java.util.List;
/* loaded from: classes2.dex */
public class UpgradePopupConfig {
    public static final String DEFAULT_SCHEDULE_TIME = "03:00";
    public static final int EACH_DAY = 1;
    public static final int EACH_TIME = 0;
    private String buttonCancelText;
    private String buttonOkText;
    private List<Long> campaignList;
    private String countdownButtonOkText;
    private int countdownSeconds;
    private int dismissStrategy;
    private String popupContent;
    private int popupFrequency;
    private int popupStrategy;
    private String popupTitle;
    private String scheduleTime;

    public List<Long> getCampaignList() {
        return this.campaignList;
    }

    public void setCampaignList(List<Long> list) {
        this.campaignList = list;
    }

    public int getPopupStrategy() {
        return this.popupStrategy;
    }

    public void setPopupStrategy(int i) {
        this.popupStrategy = i;
    }

    public int getDismissStrategy() {
        return this.dismissStrategy;
    }

    public void setDismissStrategy(int i) {
        this.dismissStrategy = i;
    }

    public int getPopupFrequency() {
        return this.popupFrequency;
    }

    public void setPopupFrequency(int i) {
        this.popupFrequency = i;
    }

    public String getPopupTitle() {
        return this.popupTitle;
    }

    public void setPopupTitle(String str) {
        this.popupTitle = str;
    }

    public String getPopupContent() {
        return this.popupContent;
    }

    public void setPopupContent(String str) {
        this.popupContent = str;
    }

    public String getScheduleTime() {
        return this.scheduleTime;
    }

    public void setScheduleTime(String str) {
        this.scheduleTime = str;
    }

    public int getCountdownSeconds() {
        return this.countdownSeconds;
    }

    public void setCountdownSeconds(int i) {
        this.countdownSeconds = i;
    }

    public String getCountdownButtonOkText() {
        return this.countdownButtonOkText;
    }

    public void setCountdownButtonOkText(String str) {
        this.countdownButtonOkText = str;
    }

    public String getButtonOkText() {
        return this.buttonOkText;
    }

    public void setButtonOkText(String str) {
        this.buttonOkText = str;
    }

    public String getButtonCancelText() {
        return this.buttonCancelText;
    }

    public void setButtonCancelText(String str) {
        this.buttonCancelText = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AutoUpgradeConfig{");
        sb.append("campaignList=");
        Object obj = this.campaignList;
        if (obj == null) {
            obj = "[]";
        }
        sb.append(obj);
        sb.append(", popupStrategy=");
        sb.append(this.popupStrategy);
        sb.append(", dismissStrategy=");
        sb.append(this.dismissStrategy);
        sb.append(", popupFrequency=");
        sb.append(this.popupFrequency);
        sb.append(", popupTitle='");
        sb.append(this.popupTitle);
        sb.append('\'');
        sb.append(", popupContent='");
        sb.append(this.popupContent);
        sb.append('\'');
        sb.append(", scheduleTime='");
        sb.append(this.scheduleTime);
        sb.append('\'');
        sb.append(", countdownSeconds=");
        sb.append(this.countdownSeconds);
        sb.append(", countdownButtonOkText='");
        sb.append(this.countdownButtonOkText);
        sb.append('\'');
        sb.append(", buttonOkText='");
        sb.append(this.buttonOkText);
        sb.append('\'');
        sb.append(", buttonCancelText='");
        sb.append(this.buttonCancelText);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }

    /* loaded from: classes2.dex */
    public enum PopupStrategy {
        POWER_ON(1),
        PARKING_GEAR(16),
        POWER_ON_OR_PARKING_GEAR(17);
        
        private int code;

        PopupStrategy(int i) {
            this.code = i;
        }

        public int getCode() {
            return this.code;
        }

        public static PopupStrategy of(int i) {
            PopupStrategy[] values;
            for (PopupStrategy popupStrategy : values()) {
                if (i == popupStrategy.getCode()) {
                    return popupStrategy;
                }
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public enum DismissStrategy {
        GEAR_REVERS(1),
        GEAR_DRIVE(16),
        GEAR_DRIVE_REVERS(17);
        
        private int code;

        DismissStrategy(int i) {
            this.code = i;
        }

        public int getCode() {
            return this.code;
        }

        public static DismissStrategy of(int i) {
            DismissStrategy[] values;
            for (DismissStrategy dismissStrategy : values()) {
                if (i == dismissStrategy.getCode()) {
                    return dismissStrategy;
                }
            }
            return null;
        }
    }
}
