package com.xiaopeng.ota.sdk.common.expression.operator;

import com.xiaopeng.ota.sdk.common.expression.CondContext;
import com.xiaopeng.ota.sdk.common.expression.CondOperator;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class OrOperator implements CondOperator {
    private List<CondOperator> values;

    public OrOperator(List<CondOperator> list) {
        this.values = new ArrayList();
        this.values = list;
    }

    @Override // com.xiaopeng.ota.sdk.common.expression.CondOperator
    public boolean execute(CondContext condContext) {
        if (this.values.isEmpty()) {
            return false;
        }
        for (CondOperator condOperator : this.values) {
            if (condOperator.execute(condContext)) {
                return true;
            }
        }
        return false;
    }
}
