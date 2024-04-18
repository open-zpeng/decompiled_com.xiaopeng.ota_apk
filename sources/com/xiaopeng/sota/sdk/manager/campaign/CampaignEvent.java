package com.xiaopeng.sota.sdk.manager.campaign;

import com.xiaopeng.ota.sdk.common.OperationEntity;
import java.util.Map;
/* loaded from: classes2.dex */
public class CampaignEvent extends OperationEntity {
    public static final String CAMPAIGN_ID = "campaign_id";
    public static final String CONTEXT = "context";
    public static final String CONTEXT_CODE = "code";
    public static final String CONTEXT_MESSAGE = "message";
    public static final String ENVIRONMENT = "environment";
    public static final String EVENT = "event";
    public static final String PACKAGE_ID = "package_id";
    public static final String STATUS = "status";
    public static final String TABLE_NAME = "campaign_event";
    public static final String TIMESTAMP = "timestamp";
    public static final String TRACE_ID = "trace_id";
    private Long campaignId;
    private Map<String, Object> context;
    private String environment;
    private String event;
    private String eventId;
    private Long packageId;
    private Integer status;
    private Long timestamp;
    private String traceId;

    /* loaded from: classes2.dex */
    public enum Event {
        START,
        DOWNLOAD,
        DOWNLOAD_CONFIRM,
        VERIFY,
        USER_CONFIRM,
        DECRYPT,
        UPGRADE,
        COMPLETE
    }

    public CampaignEvent() {
    }

    public CampaignEvent(Campaign campaign, Event event, State state) {
        this(campaign, event, state, null);
    }

    public CampaignEvent(Campaign campaign, Event event, State state, Map<String, Object> map) {
        setEnvironment(campaign.getEnvironment());
        setTraceId(campaign.getTraceId());
        setCampaignId(campaign.getCampaignId());
        setPackageId(campaign.getCampaignPackage().getId());
        setEvent(event.name());
        setStatus(Integer.valueOf(state.code));
        setContext(map);
        setTimestamp(Long.valueOf(System.currentTimeMillis()));
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

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer num) {
        this.status = num;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public Map<String, Object> getContext() {
        return this.context;
    }

    public void setContext(Map<String, Object> map) {
        this.context = map;
    }

    public String getEventId() {
        return this.eventId;
    }

    public void setEventId(String str) {
        this.eventId = str;
    }

    /* loaded from: classes2.dex */
    public enum State {
        INIT(-1, "开始"),
        OK(0, "成功"),
        ERROR(1, "失败"),
        REJECT(2, "拒绝"),
        WAITING(3, "等待"),
        DELAY(4, "延迟"),
        INTERRUPTED(5, "中断");
        
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
    }
}
