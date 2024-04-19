package com.xiaopeng.ota.sdk.common.expression.operator;

import com.xiaopeng.ota.sdk.common.expression.CondContext;
/* loaded from: classes2.dex */
public class NeOperator extends EqOperator {
    public NeOperator(Object obj, Object obj2) {
        super(obj, obj2);
    }

    @Override // com.xiaopeng.ota.sdk.common.expression.operator.EqOperator, com.xiaopeng.ota.sdk.common.expression.CondOperator
    public boolean execute(CondContext condContext) {
        return condContext.containsKey(this.key.toString()) && !super.execute(condContext);
    }
}
