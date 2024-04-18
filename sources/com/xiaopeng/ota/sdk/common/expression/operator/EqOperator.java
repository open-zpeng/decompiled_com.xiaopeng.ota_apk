package com.xiaopeng.ota.sdk.common.expression.operator;

import com.xiaopeng.ota.sdk.common.expression.BaseCondOperator;
import com.xiaopeng.ota.sdk.common.expression.CondContext;
import java.util.List;
/* loaded from: classes2.dex */
public class EqOperator extends BaseCondOperator {
    private String value;

    public EqOperator(Object obj, Object obj2) {
        this.key = obj;
        this.value = String.valueOf(obj2);
    }

    @Override // com.xiaopeng.ota.sdk.common.expression.CondOperator
    public boolean execute(CondContext condContext) {
        Object obj = condContext.get(this.key.toString());
        if (obj == null) {
            return false;
        }
        if (obj instanceof List) {
            return ((List) obj).contains(this.value);
        }
        return this.value.equals(String.valueOf(obj));
    }
}
