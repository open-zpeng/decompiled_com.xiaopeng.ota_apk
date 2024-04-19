package com.xiaopeng.ota.sdk.common.condition;
/* loaded from: classes2.dex */
public enum ConditionKey {
    VEHICLE_LOCKED(ConditionKeyType.VEHICLE.getType(), "LOCKED", "设防状态", "设防", "未设防"),
    VEHICLE_CHARGING(ConditionKeyType.VEHICLE.getType(), "CHARGING", "充电枪状态", "插枪", "未插枪"),
    VEHICLE_UPDATABLE_TIME(ConditionKeyType.VEHICLE.getType(), "UPDATABLE_TIME", "允许时间", "", ""),
    VEHICLE_SOC(ConditionKeyType.VEHICLE.getType(), "SOC", "电量情况", "", ""),
    VEHICLE_GEAR(ConditionKeyType.VEHICLE.getType(), "GEAR", "车辆挡位", "P挡", "非P挡"),
    CDU_SLEEP_MINUTE(ConditionKeyType.CDU.getType(), "SLEEP_MINUTE", "大屏休眠时长", "", "");
    
    private static final String COLON = ":";
    private static final String DOT = ".";
    private String description;
    private String disabledText;
    private String enabledText;
    private String keyName;
    private String type;

    ConditionKey(String str, String str2, String str3, String str4, String str5) {
        this.type = str;
        this.keyName = str2;
        this.description = str3;
        this.enabledText = str4;
        this.disabledText = str5;
    }

    public String getType() {
        return this.type;
    }

    public String getKeyName() {
        return this.keyName;
    }

    public String getDescription() {
        return this.description;
    }

    public String getKey() {
        return this.type + "." + this.keyName;
    }

    public String getEnabledText() {
        return this.enabledText;
    }

    public String getDisabledText() {
        return this.disabledText;
    }

    public static ConditionKey fromKey(String str) {
        int indexOf = str.indexOf(".");
        if (indexOf > -1) {
            return fromCondition(str.substring(0, indexOf), str.substring(indexOf + 1));
        }
        throw new IllegalArgumentException("key invalid, requirements for this format: type.name");
    }

    public static ConditionKey fromCondition(String str, String str2) {
        if (ConditionKeyType.VEHICLE.getType().equals(str)) {
            if (VEHICLE_CHARGING.getKeyName().equals(str2)) {
                return VEHICLE_CHARGING;
            }
            if (VEHICLE_LOCKED.getKeyName().equals(str2)) {
                return VEHICLE_LOCKED;
            }
            if (VEHICLE_SOC.getKeyName().equals(str2)) {
                return VEHICLE_SOC;
            }
            if (VEHICLE_UPDATABLE_TIME.getKeyName().equals(str2)) {
                return VEHICLE_UPDATABLE_TIME;
            }
            if (VEHICLE_GEAR.getKeyName().equals(str2)) {
                return VEHICLE_GEAR;
            }
        } else if (ConditionKeyType.CDU.getType().equals(str) && CDU_SLEEP_MINUTE.getKeyName().equals(str2)) {
            return CDU_SLEEP_MINUTE;
        }
        throw new IllegalArgumentException("Unknown condition key " + str + "." + str2);
    }

    @Override // java.lang.Enum
    public String toString() {
        return getKeyName() + ":" + getDescription();
    }
}
