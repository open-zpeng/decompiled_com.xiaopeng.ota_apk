package com.xiaopeng.ota.sdk.common.expression.operator;

import com.xiaopeng.ota.sdk.common.expression.BaseCondOperator;
import com.xiaopeng.ota.sdk.common.expression.CondContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes2.dex */
public class InOperator extends BaseCondOperator {
    private Set<String> values = new HashSet();

    public InOperator(Object obj, List<Object> list) {
        this.key = obj;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (Object obj2 : list) {
            this.values.add(String.valueOf(obj2));
        }
    }

    @Override // com.xiaopeng.ota.sdk.common.expression.CondOperator
    public boolean execute(CondContext condContext) {
        Object obj = condContext.get(this.key.toString());
        if (!(obj instanceof List)) {
            return obj != null && this.values.contains(String.valueOf(obj));
        }
        List<String> list = (List) obj;
        if (list != null && !list.isEmpty()) {
            for (String str : list) {
                if (this.values.contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
