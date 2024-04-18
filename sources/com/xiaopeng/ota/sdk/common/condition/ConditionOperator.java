package com.xiaopeng.ota.sdk.common.condition;
/* loaded from: classes2.dex */
public enum ConditionOperator {
    EQ("EQ", "=="),
    NE("NE", "<>"),
    GT("GT", ">"),
    GE("GE", ">="),
    LT("LT", "<"),
    LE("LE", "<="),
    IN("IN", "属于"),
    NIN("NIN", "不属于"),
    RANGE("RANGE", "区间");
    
    private String code;
    private String desc;

    ConditionOperator(String str, String str2) {
        this.code = str;
        this.desc = str2;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public static ConditionOperator of(String str) {
        if (EQ.getCode().equals(str)) {
            return EQ;
        }
        if (NE.getCode().equals(str)) {
            return NE;
        }
        if (GT.getCode().equals(str)) {
            return GT;
        }
        if (GE.getCode().equals(str)) {
            return GE;
        }
        if (LT.getCode().equals(str)) {
            return LT;
        }
        if (LE.getCode().equals(str)) {
            return LE;
        }
        if (IN.getCode().equals(str)) {
            return IN;
        }
        if (NIN.getCode().equals(str)) {
            return NIN;
        }
        if (RANGE.getCode().equals(str)) {
            return RANGE;
        }
        throw new UnsupportedOperationException(str);
    }
}
