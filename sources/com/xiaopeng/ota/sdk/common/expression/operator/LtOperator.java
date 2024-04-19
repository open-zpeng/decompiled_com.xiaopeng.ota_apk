package com.xiaopeng.ota.sdk.common.expression.operator;

import com.xiaopeng.ota.sdk.common.expression.BaseCondOperator;
import com.xiaopeng.ota.sdk.common.expression.CondContext;
/* loaded from: classes2.dex */
public class LtOperator extends BaseCondOperator {
    private String[] values;

    public LtOperator(Object obj, Object obj2) {
        this.key = obj;
        this.values = String.valueOf(obj2).split("\\.");
    }

    @Override // com.xiaopeng.ota.sdk.common.expression.CondOperator
    public boolean execute(CondContext condContext) {
        Object obj = condContext.get(this.key.toString());
        if (obj == null) {
            return false;
        }
        String[] split = String.valueOf(obj).split("\\.");
        int max = Math.max(split.length, this.values.length);
        int i = 0;
        while (i < max) {
            int valueOf = i < split.length ? valueOf(split[i]) : 0;
            String[] strArr = this.values;
            int valueOf2 = i < strArr.length ? valueOf(strArr[i]) : 0;
            if (valueOf < valueOf2) {
                return true;
            }
            if (valueOf > valueOf2) {
                return false;
            }
            i++;
        }
        return false;
    }

    private int valueOf(String str) {
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int charAt = str.charAt(i2) - '0';
            i = (charAt < 10 ? i * 10 : i * 100) + charAt;
        }
        return i;
    }
}
