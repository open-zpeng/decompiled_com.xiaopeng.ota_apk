package com.ut.mini;

import android.app.Activity;
/* loaded from: classes2.dex */
public class UTInterfaceCallDelegate {
    public static void pageDisAppearByAuto(Activity activity) {
        UTPageHitHelper.getInstance().pageDisAppearByAuto(activity);
    }

    public static void pageAppearByAuto(Activity activity) {
        UTPageHitHelper.getInstance().pageAppearByAuto(activity);
    }
}
