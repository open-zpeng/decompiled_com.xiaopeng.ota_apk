package com.xiaopeng.ota.sdk.push;

import java.util.HashMap;
/* loaded from: classes2.dex */
public class Feedback {
    public static final int ERROR_CAMPAIGN_NOT_FOUND = 2;
    public static final int ERROR_NON_PARKING = 3;
    public static final int ERROR_POWER_NORMAL = 4;
    public static final int FAILURE = 1;
    public static final String KEY_MESSAGE = "message";
    public static final int SUCCESS = 0;
    private HashMap<String, Object> context;
    private String msgId;
    private Long receiveTime;
    private Long startTime;
    private Integer status;

    private Feedback() {
    }

    public String getMsgId() {
        return this.msgId;
    }

    public Long getReceiveTime() {
        return this.receiveTime;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public HashMap<String, Object> getContext() {
        return this.context;
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private Feedback mFeedback = new Feedback();

        public Builder msgId(String str) {
            this.mFeedback.msgId = str;
            return this;
        }

        public Builder receiveTime(Long l) {
            this.mFeedback.receiveTime = l;
            return this;
        }

        public Builder startTime(Long l) {
            this.mFeedback.startTime = l;
            return this;
        }

        public Builder status(Integer num) {
            this.mFeedback.status = num;
            return this;
        }

        public Builder context(String str, String str2) {
            return addContext(str, str2);
        }

        private Builder addContext(String str, Object obj) {
            synchronized (this.mFeedback) {
                if (this.mFeedback.context == null) {
                    this.mFeedback.context = new HashMap();
                }
                this.mFeedback.context.put(str, obj);
            }
            return this;
        }

        public Feedback build() {
            return this.mFeedback;
        }
    }
}
