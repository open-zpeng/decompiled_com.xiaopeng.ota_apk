package com.xiaopeng.ota.utils;

import com.xiaopeng.lib.utils.view.UIUtils;
import com.xiaopeng.ota.OTAManager;
/* loaded from: classes2.dex */
public class ResourceUtils {
    public static int getColor(int i) {
        return OTAManager.getContext().getColor(i);
    }

    public static String getString(int i) {
        return OTAManager.getContext().getString(i);
    }

    public static float getDimen(int i) {
        return OTAManager.getContext().getResources().getDimension(i);
    }

    public static String[] getStringArray(int i) {
        return OTAManager.getContext().getResources().getStringArray(i);
    }

    public static int dip2px(int i) {
        return UIUtils.dip2px(OTAManager.getContext(), i);
    }

    public static int sp2px(int i) {
        return UIUtils.sp2px(OTAManager.getContext(), i);
    }

    public static int px2sp(int i) {
        return UIUtils.px2sp(OTAManager.getContext(), i);
    }

    public static int px2dip(int i) {
        return UIUtils.px2dip(OTAManager.getContext(), i);
    }

    public static int getInt(int i) {
        return OTAManager.getContext().getResources().getInteger(i);
    }
}
