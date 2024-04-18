package com.xiaopeng.ota.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
/* loaded from: classes2.dex */
public class SpreadListView extends ListView {
    public SpreadListView(Context context) {
        super(context);
    }

    public SpreadListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SpreadListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
