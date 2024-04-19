package com.xiaopeng.ota.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.android.internal.util.ArrayUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class PackageUtils {
    private static final String TAG = "PackageUtils";

    public static PackageInfo getPackageInfo(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            PackageInfo packageInfo2 = new PackageInfo();
            packageInfo2.packageName = str;
            packageInfo2.versionCode = packageInfo.versionCode;
            packageInfo2.versionName = packageInfo.versionName;
            return packageInfo2;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.e(TAG, e, "Get %s's package information fail", str);
            return null;
        }
    }

    public static List<PackageInfo> getPackageInfoList(Context context, List<String> list) {
        if (ArrayUtils.isEmpty(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        for (String str : list) {
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
                PackageInfo packageInfo2 = new PackageInfo();
                packageInfo2.packageName = str;
                packageInfo2.versionCode = packageInfo.versionCode;
                packageInfo2.versionName = packageInfo.versionName;
                arrayList.add(packageInfo2);
            } catch (PackageManager.NameNotFoundException e) {
                LogUtils.e(TAG, e, "Get %s's package(total=%d) information fail", str, Integer.valueOf(list.size()));
            }
        }
        return arrayList;
    }
}
