package com.xiaopeng.ota.sdk.common.expression.operator;

import com.xiaopeng.ota.sdk.common.expression.BaseCondOperator;
import com.xiaopeng.ota.sdk.common.expression.CondContext;
import com.xiaopeng.ota.sdk.common.expression.CondOperator;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class AndOperator implements CondOperator {
    private List<BaseCondOperator> values;

    public AndOperator(List<BaseCondOperator> list) {
        this.values = new ArrayList();
        this.values = list;
    }

    @Override // com.xiaopeng.ota.sdk.common.expression.CondOperator
    public boolean execute(CondContext condContext) {
        if (this.values.isEmpty()) {
            return false;
        }
        for (BaseCondOperator baseCondOperator : this.values) {
            if (!baseCondOperator.execute(condContext)) {
                return false;
            }
        }
        return true;
    }
}
