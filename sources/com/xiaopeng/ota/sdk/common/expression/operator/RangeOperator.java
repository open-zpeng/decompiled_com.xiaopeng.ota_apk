package com.xiaopeng.ota.sdk.common.expression.operator;

import android.text.TextUtils;
import com.xiaopeng.ota.sdk.common.expression.BaseCondOperator;
import com.xiaopeng.ota.sdk.common.expression.CondContext;
/* loaded from: classes2.dex */
public class RangeOperator extends BaseCondOperator {
    private Object[] values;

    /* JADX WARN: Removed duplicated region for block: B:13:0x002a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public RangeOperator(java.lang.Object r4, java.util.List<java.lang.Object> r5) {
        /*
            r3 = this;
            r3.<init>()
            r3.key = r4
            r4 = 0
            r0 = 1
            if (r5 == 0) goto L27
            int r1 = r5.size()
            r2 = 2
            if (r1 != r2) goto L27
            java.lang.Object[] r1 = new java.lang.Object[r2]
            r3.values = r1
            java.lang.Object r1 = r5.get(r4)
            if (r1 == 0) goto L27
            java.lang.Object r1 = r5.get(r0)
            if (r1 == 0) goto L27
            java.lang.Object[] r5 = r5.toArray()
            r3.values = r5
            goto L28
        L27:
            r4 = r0
        L28:
            if (r4 != 0) goto L2b
            return
        L2b:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "values size is not 2"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.ota.sdk.common.expression.operator.RangeOperator.<init>(java.lang.Object, java.util.List):void");
    }

    @Override // com.xiaopeng.ota.sdk.common.expression.CondOperator
    public boolean execute(CondContext condContext) {
        Object[] objArr;
        Object obj = condContext.get(this.key.toString());
        if (obj == null || (objArr = this.values) == null || objArr.length != 2) {
            return false;
        }
        return judgeRange(obj);
    }

    private boolean judgeRange(Object obj) {
        Object[] objArr = this.values;
        Object obj2 = objArr[0];
        if (obj2 instanceof Integer) {
            int intValue = ((Integer) obj2).intValue();
            int intValue2 = ((Integer) this.values[1]).intValue();
            int intValue3 = ((Integer) obj).intValue();
            return intValue3 >= intValue && intValue3 <= intValue2;
        } else if (obj2 instanceof Number) {
            double doubleValue = ((Number) obj2).doubleValue();
            double doubleValue2 = ((Number) this.values[1]).doubleValue();
            double doubleValue3 = ((Number) obj).doubleValue();
            return doubleValue3 >= doubleValue && doubleValue3 <= doubleValue2;
        } else if (obj2 instanceof String) {
            String str = (String) obj2;
            String str2 = (String) objArr[1];
            String str3 = (String) obj;
            return (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str3.compareToIgnoreCase(str) < 0 || str3.compareToIgnoreCase(str2) > 0) ? false : true;
        } else {
            return false;
        }
    }
}
