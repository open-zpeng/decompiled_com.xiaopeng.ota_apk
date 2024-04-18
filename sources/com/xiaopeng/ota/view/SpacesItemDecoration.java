package com.xiaopeng.ota.view;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes2.dex */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;

    public SpacesItemDecoration(int i) {
        this.mSpace = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.bottom = this.mSpace;
    }
}
