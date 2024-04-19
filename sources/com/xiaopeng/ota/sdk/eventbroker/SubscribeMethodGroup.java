package com.xiaopeng.ota.sdk.eventbroker;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class SubscribeMethodGroup {
    private final List<SubscribeMethod> subscribeMethods = new ArrayList();
    private final List<SubscribeTimeoutMethod> subscribeTimeoutMethods = new ArrayList();

    public void addSubscribeMethod(SubscribeMethod subscribeMethod) {
        this.subscribeMethods.add(subscribeMethod);
    }

    public void addSubscribeTimeoutMethod(SubscribeTimeoutMethod subscribeTimeoutMethod) {
        this.subscribeTimeoutMethods.add(subscribeTimeoutMethod);
    }

    public List<SubscribeMethod> getSubscribeMethods() {
        return this.subscribeMethods;
    }

    public List<SubscribeTimeoutMethod> getSubscribeTimeoutMethods() {
        return this.subscribeTimeoutMethods;
    }
}
