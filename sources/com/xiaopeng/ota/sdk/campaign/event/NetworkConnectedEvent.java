package com.xiaopeng.ota.sdk.campaign.event;

import com.xiaopeng.ota.sdk.eventbroker.Event;
/* loaded from: classes2.dex */
public class NetworkConnectedEvent extends Event {
    public static final int TYPE_MOBILE = 0;
    public static final int TYPE_NONE = -1;
    public static final int TYPE_WIFI = 1;
    private int type;

    private NetworkConnectedEvent(Builder builder) {
        this.type = builder.type;
    }

    public int getType() {
        return this.type;
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private int type;

        public Builder type(int i) {
            this.type = i;
            return this;
        }

        public NetworkConnectedEvent build() {
            return new NetworkConnectedEvent(this);
        }
    }
}
