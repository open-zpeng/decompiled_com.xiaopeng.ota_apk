package com.xiaopeng.xuimanager.iot;

import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public interface IDeviceListener {
    void onDeviceAdd(List<BaseDevice> list);

    void onOperationResult(String str, String str2, String str3);

    void onPropertiesUpdated(String str, Map<String, String> map);
}
