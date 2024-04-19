package com.xiaopeng.ota.utils;

import android.view.View;
import android.view.ViewTreeObserver;
/* loaded from: classes2.dex */
public class ViewUtil {
    public static void onPreDraw(final View view, final Runnable runnable) {
        if (view == null) {
            return;
        }
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.xiaopeng.ota.utils.ViewUtil.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.removeOnPreDrawListener(this);
                    view.post(runnable);
                    return false;
                }
                return false;
            }
        });
    }
}
