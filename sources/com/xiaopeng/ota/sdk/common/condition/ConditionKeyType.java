package com.xiaopeng.ota.sdk.common.condition;
/* loaded from: classes2.dex */
public enum ConditionKeyType {
    VEHICLE("VEHICLE", "车辆"),
    CDU("CDU", "大屏");
    
    private String description;
    private String type;

    ConditionKeyType(String str, String str2) {
        this.type = str;
        this.description = str2;
    }

    public String getDescription() {
        return this.description;
    }

    public String getType() {
        return this.type;
    }
}
