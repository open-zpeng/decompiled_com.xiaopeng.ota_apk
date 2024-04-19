package com.xiaopeng.ota.sdk.common.expression;
/* loaded from: classes2.dex */
public class CondExpression implements CondOperator {
    private CondOperator operator;

    public CondExpression(CondOperator condOperator) {
        this.operator = condOperator;
    }

    public CondExpression() {
    }

    public CondOperator getOperator() {
        return this.operator;
    }

    public void setOperator(CondOperator condOperator) {
        this.operator = condOperator;
    }

    @Override // com.xiaopeng.ota.sdk.common.expression.CondOperator
    public boolean execute(CondContext condContext) {
        return this.operator.execute(condContext);
    }
}
