package com.xiaopeng.ota.sdk.common.util;

import android.car.XpCarFeatures;
import android.content.Context;
import android.text.TextUtils;
import com.lzy.okgo.model.HttpHeaders;
import com.xiaopeng.lib.utils.MD5Utils;
import com.xiaopeng.lib.utils.info.BuildInfoUtils;
import com.xiaopeng.ota.sdk.common.OTAConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class HttpHeaderUtils {
    private static final String PREFIX_HEADER = "XP";
    private static final String TAG = "HttpHeaderUtils";
    private static final int XP_ENCRYPTION_TYPE_IRDETO = 1;
    private static final int XP_ENCRYPTION_TYPE_NONE = 0;

    public static String buildXpClient(Context context, long j) {
        return buildXpClient(context, AppUtils.getVersionName(context), String.valueOf(AppUtils.getVersionCode(context)), j);
    }

    public static String buildXpClient(Context context, String str, String str2, long j) {
        StringBuilder sb = new StringBuilder();
        sb.append("di=");
        sb.append(XpCarFeatures.getMcuHardwareId());
        sb.append(";pn=");
        sb.append(context.getPackageName());
        sb.append(";ve=");
        sb.append(str);
        if (!TextUtils.isEmpty(str2)) {
            sb.append(";vc=");
            sb.append(str2);
        }
        sb.append(";br=");
        sb.append(OTAConstants.ConfigServer.BR);
        sb.append(";mo=");
        sb.append(OTAConstants.ConfigServer.MO);
        sb.append(";st=");
        sb.append("3");
        sb.append(";sv=");
        sb.append(BuildInfoUtils.getSystemVersion());
        sb.append(";nt=");
        sb.append(NetworkUtils.getNetworkType(context));
        sb.append(";t=");
        sb.append(j);
        return sb.toString();
    }

    public static HttpHeaders buildGetHeaders(String str, String str2, String str3, long j, String str4, Map<String, String> map) {
        HashMap hashMap = new HashMap();
        hashMap.put(OTAConstants.HttpHeader.XP_APP_ID, str);
        hashMap.put(OTAConstants.HttpHeader.XP_ENCRYPTION_TYPE, String.valueOf(0));
        hashMap.put(OTAConstants.HttpHeader.XP_CLIENT_TYPE, String.valueOf(3));
        hashMap.put(OTAConstants.HttpHeader.XP_CLIENT, str3);
        hashMap.put(OTAConstants.HttpHeader.XP_NONCE, String.valueOf(j));
        if (!TextUtils.isEmpty(str4)) {
            hashMap.put(OTAConstants.HttpHeader.XP_OTA_TRACE_ID, str4);
        }
        if (map != null && map.size() != 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Map<String, String> buildGetHeaders = buildGetHeaders(str2, hashMap, null);
        HttpHeaders httpHeaders = new HttpHeaders();
        for (String str5 : buildGetHeaders.keySet()) {
            httpHeaders.put(str5, buildGetHeaders.get(str5));
        }
        return httpHeaders;
    }

    public static Map<String, String> buildGetHeaders(String str, Map<String, String> map, Map<String, Object> map2) {
        return buildHeaders(str, OTAConstants.HttpMethod.GET, map, map2, null);
    }

    public static HttpHeaders buildPostHeaders(String str, String str2, String str3, long j, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put(OTAConstants.HttpHeader.XP_APP_ID, str);
        hashMap.put(OTAConstants.HttpHeader.XP_ENCRYPTION_TYPE, String.valueOf(0));
        hashMap.put(OTAConstants.HttpHeader.XP_CLIENT_TYPE, String.valueOf(3));
        hashMap.put(OTAConstants.HttpHeader.XP_CLIENT, str3);
        hashMap.put(OTAConstants.HttpHeader.XP_NONCE, String.valueOf(j));
        Map<String, String> buildHeaders = buildHeaders(str2, OTAConstants.HttpMethod.POST, hashMap, null, str4);
        HttpHeaders httpHeaders = new HttpHeaders();
        for (String str5 : buildHeaders.keySet()) {
            httpHeaders.put(str5, buildHeaders.get(str5));
        }
        return httpHeaders;
    }

    public static Map<String, String> buildHeaders(String str, String str2, Map<String, String> map, Map<String, Object> map2, String str3) {
        String vinCode = XpCarFeatures.getVinCode();
        if (!TextUtils.isEmpty(vinCode) && TextUtils.isPrintableAsciiOnly(vinCode)) {
            map.put(OTAConstants.HttpHeader.XP_VIN, vinCode);
        }
        map.put(OTAConstants.HttpHeader.XP_SIGNATURE, sign(str, str2, map, map2, str3));
        return map;
    }

    private static String sign(String str, String str2, Map<String, String> map, Map<String, Object> map2, String str3) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException("Header is empty");
        }
        HashMap hashMap = new HashMap();
        for (String str4 : map.keySet()) {
            if (!OTAConstants.HttpHeader.XP_SIGNATURE.equalsIgnoreCase(str4) && str4.toUpperCase().startsWith(PREFIX_HEADER)) {
                hashMap.put(str4.toUpperCase(), map.get(str4));
            }
        }
        if (map2 != null && !map2.isEmpty()) {
            hashMap.putAll(map2);
        }
        ArrayList<String> arrayList = new ArrayList(hashMap.keySet());
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        for (String str5 : arrayList) {
            sb.append(str5);
            sb.append(OTAConstants.EQ);
            sb.append(hashMap.get(str5));
            sb.append(OTAConstants.AND);
        }
        String md5 = TextUtils.isEmpty(str3) ? "" : MD5Utils.getMD5(str3);
        return ShaUtils.getSHA256Hmac(str, str2 + OTAConstants.AND + sb.toString() + md5);
    }

    public static Map<String, String> buildHeaders(long j) {
        HashMap hashMap = new HashMap();
        hashMap.put(OTAConstants.HttpHeader.XP_ENCRYPTION_TYPE, String.valueOf(0));
        hashMap.put(OTAConstants.HttpHeader.XP_CLIENT_TYPE, String.valueOf(3));
        hashMap.put(OTAConstants.HttpHeader.XP_NONCE, String.valueOf(j));
        return hashMap;
    }
}
