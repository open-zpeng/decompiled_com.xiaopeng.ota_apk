package com.xiaopeng.ota.sdk.eventbroker;
/* loaded from: classes2.dex */
public interface PostListener {
    void onCompleted(int i);

    void onException(Exception exc);
}
