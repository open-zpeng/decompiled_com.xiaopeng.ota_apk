package com.xiaopeng.ota.utils;

import android.text.TextUtils;
import com.xiaopeng.lib.utils.info.BuildInfoUtils;
import javax.validation.constraints.NotNull;
/* loaded from: classes2.dex */
public class VersionUtils {
    private static final String NEW_LOGO_VERSION = "1.3.0";
    private static final String SEPARATOR = "_";
    private static final String VERSION_DETAIL_OS_VERSION = "1.4.0";
    private static final String VERSION_PREFIX = "Xmart OS";

    public static String getSoftwareFromFingerprint(@NotNull String str) {
        String[] split = str.split("_");
        if (split.length >= 2) {
            return split[1].replaceFirst("[Vv]", "");
        }
        return null;
    }

    public static String getCduSwVersion() {
        return BuildInfoUtils.getFullSystemVersion().split("_")[1];
    }

    @Deprecated
    public static boolean isNewLogoVersion() {
        String softwareFromFingerprint = getSoftwareFromFingerprint(BuildInfoUtils.getFullSystemVersion());
        return softwareFromFingerprint != null && NEW_LOGO_VERSION.compareTo(softwareFromFingerprint) <= 0;
    }

    public static boolean isNewVersionDetailOs() {
        String softwareFromFingerprint = getSoftwareFromFingerprint(BuildInfoUtils.getFullSystemVersion());
        return softwareFromFingerprint != null && VERSION_DETAIL_OS_VERSION.compareTo(softwareFromFingerprint) <= 0;
    }

    public static String getSimpleVersion(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String replace = str.replace(VERSION_PREFIX, "");
        if (TextUtils.isEmpty(replace)) {
            return str;
        }
        String trim = replace.trim();
        return (trim.startsWith("V") || trim.startsWith("v")) ? trim.substring(1) : trim;
    }

    public static String getCduVersionBuildTime() {
        int indexOf;
        String fullSystemVersion = BuildInfoUtils.getFullSystemVersion();
        if (!"unknown".equals(fullSystemVersion) && (indexOf = fullSystemVersion.indexOf("_")) > 1) {
            int i = indexOf + 1;
            int indexOf2 = fullSystemVersion.indexOf("_", fullSystemVersion.indexOf("_", i) + 1);
            return indexOf2 > indexOf ? fullSystemVersion.substring(i, indexOf2) : fullSystemVersion;
        }
        return fullSystemVersion;
    }
}
