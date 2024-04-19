package com.xiaopeng.ota.sdk.common.expression.operator;

import com.xiaopeng.ota.sdk.common.expression.CondContext;
import java.util.List;
/* loaded from: classes2.dex */
public class NinOperator extends InOperator {
    public NinOperator(Object obj, List<Object> list) {
        super(obj, list);
    }

    @Override // com.xiaopeng.ota.sdk.common.expression.operator.InOperator, com.xiaopeng.ota.sdk.common.expression.CondOperator
    public boolean execute(CondContext condContext) {
        return condContext.containsKey(this.key.toString()) && !super.execute(condContext);
    }
}
