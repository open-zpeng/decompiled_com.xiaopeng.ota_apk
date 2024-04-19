package com.xiaopeng.ota.presenter.event;

import com.xiaopeng.ota.sdk.common.util.Dictionary;
/* loaded from: classes2.dex */
public class CampaignEvent {
    private Long campaignId;
    private Dictionary context = new Dictionary();
    private String environment;
    private String event;
    private Extra extra;
    private Long packageId;
    private Integer status;
    private Long timestamp;
    private String traceId;

    /* loaded from: classes2.dex */
    public static class Extra {
        private Integer code;
        private String message;
        private String network;

        public Extra() {
        }

        public Extra(String str) {
            this.message = str;
        }

        public Extra(String str, String str2) {
            this.network = str;
            this.message = str2;
        }

        public Extra(Integer num, String str) {
            this.code = num;
            this.message = str;
        }

        public Extra(Integer num, String str, String str2) {
            this.code = num;
            this.network = str;
            this.message = str2;
        }

        public Integer getCode() {
            return this.code;
        }

        public String getNetwork() {
            return this.network;
        }

        public String getMessage() {
            return this.message;
        }
    }

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

    public Long getPackageId() {
        return this.packageId;
    }

    public void setPackageId(Long l) {
        this.packageId = l;
    }

    public String getEvent() {
        return this.event;
    }

    public void setEvent(String str) {
        this.event = str;
    }

    public Extra getContext() {
        return this.extra;
    }

    public void setContextX(Dictionary dictionary) {
        if (dictionary != null) {
            this.context = dictionary;
        }
    }

    public Dictionary getContextX() {
        return this.context;
    }

    public void setContext(Extra extra) {
        this.extra = extra;
    }

    public void setStatus(Integer num) {
        this.status = num;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public String toString() {
        return "CampaignEvent{traceId='" + this.traceId + "', campaignId=" + this.campaignId + ", packageId=" + this.packageId + ", event='" + this.event + "', status=" + this.status + ", extra=" + this.extra + ", context=" + this.context + ", timestamp=" + this.timestamp + '}';
    }

    /* loaded from: classes2.dex */
    public enum Event {
        START("开始"),
        ECU_CHECK("检查ECU版本"),
        HASH_CHECK("哈希值校验"),
        DOWNLOAD("下载"),
        DOWNLOAD_CONFIRM("下载确认"),
        PACKAGE_VERIFY("完整性校验"),
        PREPARE("安装准备"),
        USER_CONFIRM("用户确认"),
        STATUS_CHECK("状态检查"),
        DECRYPT("解密"),
        HARDWARE_CHECK("硬件校验"),
        UPGRADE("升级"),
        UPGRADE_VERIFY("升级校验"),
        CONFIG("刷写配置"),
        ROLLBACK("回滚"),
        COMPLETE("完成"),
        ECUS_SYNC("ECU版本同步");
        
        private String desc;

        Event(String str) {
            this.desc = str;
        }

        public String getDesc() {
            return this.desc;
        }
    }

    /* loaded from: classes2.dex */
    public enum State {
        INIT(-1, "开始"),
        OK(0, "成功"),
        ERROR(1, "失败"),
        REJECT(2, "拒绝"),
        WAITING(3, "等待"),
        DELAY(4, "延迟"),
        INTERRUPTED(5, "中断"),
        CANCELLED(6, "取消"),
        ONGOING(7, "进行中");
        
        private int code;
        private String description;

        State(int i, String str) {
            this.code = i;
            this.description = str;
        }

        public int getCode() {
            return this.code;
        }

        public String getDescription() {
            return this.description;
        }

        public static String getDescription(int i) {
            State[] values;
            for (State state : values()) {
                if (state.code == i) {
                    return state.description;
                }
            }
            return null;
        }
    }
}
