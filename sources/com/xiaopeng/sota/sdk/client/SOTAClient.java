package com.xiaopeng.sota.sdk.client;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.lzy.okgo.model.HttpHeaders;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import com.xiaopeng.ota.sdk.common.OTAConstants;
import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.sdk.common.util.AppUtils;
import com.xiaopeng.ota.sdk.common.util.ArrayUtils;
import com.xiaopeng.ota.sdk.common.util.HttpHeaderUtils;
import com.xiaopeng.ota.sdk.common.util.JsonUtils;
import com.xiaopeng.ota.sdk.common.util.TraceIdGenerator;
import com.xiaopeng.sota.sdk.NetworkBroadcastReceiver;
import com.xiaopeng.sota.sdk.SOTAManager;
import com.xiaopeng.sota.sdk.client.dto.UpgradeResponse;
import com.xiaopeng.sota.sdk.common.Constants;
import com.xiaopeng.sota.sdk.exception.CampaignSuspendException;
import com.xiaopeng.sota.sdk.manager.campaign.Campaign;
import com.xiaopeng.sota.sdk.manager.campaign.CampaignConverter;
import com.xiaopeng.sota.sdk.manager.campaign.CampaignEvent;
import com.xiaopeng.sota.sdk.manager.campaign.CampaignPackage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class SOTAClient {
    private static final String SEPARATOR_APP = ",";
    private static final String SEPARATOR_INFO = "|";
    private static final String TAG = "SOTAClient";

    public static List<Campaign> checkUpdate(String str, String str2, PackageInfo packageInfo, List<PackageInfo> list, String str3) throws Exception {
        if (ArrayUtils.isEmpty(list)) {
            Logger.d(TAG, "Sota list is empty", new Object[0]);
            return null;
        }
        HashMap hashMap = new HashMap();
        for (PackageInfo packageInfo2 : list) {
            hashMap.put(packageInfo2.packageName, TraceIdGenerator.create());
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            String buildXpClient = HttpHeaderUtils.buildXpClient(SOTAManager.getContext(), packageInfo.versionName, String.valueOf(packageInfo.versionCode), currentTimeMillis);
            HashMap hashMap2 = new HashMap();
            hashMap2.put(OTAConstants.HttpHeader.XP_OTA_INSTALLED_PACKAGES, buildInstalledPackages(list, hashMap));
            hashMap2.put(OTAConstants.HttpHeader.XP_CHECK_SOURCE, str3 + "|" + NetworkBroadcastReceiver.getCurrentNetworkName());
            HttpHeaders buildGetHeaders = HttpHeaderUtils.buildGetHeaders(str, str2, buildXpClient, currentTimeMillis, null, hashMap2);
            IHttp http = SOTAManager.getHttp();
            IRequest iRequest = http.get(SOTAManager.getConfig().getHost() + Constants.SOTA_UPGRADE_URI);
            for (String str4 : buildGetHeaders.getNames()) {
                iRequest.headers(str4, buildGetHeaders.get(str4));
            }
            IResponse execute = iRequest.execute();
            StringBuilder sb = new StringBuilder();
            sb.append("Check response-->");
            sb.append(execute != null ? Integer.valueOf(execute.code()) : "null");
            Logger.d(TAG, sb.toString(), new Object[0]);
            String body = execute.body();
            UpgradeResponse upgradeResponse = (UpgradeResponse) JsonUtils.fromJson(body, (Class<Object>) UpgradeResponse.class);
            Logger.d(TAG, "Check response code:" + upgradeResponse.getCode(), new Object[0]);
            int code = upgradeResponse.getCode();
            if (code != 200) {
                if (code != 14000015) {
                    return null;
                }
                throw new CampaignSuspendException("Campaign error suspended");
            }
            List<Campaign> data = upgradeResponse.getData();
            if (ArrayUtils.isEmpty(data)) {
                return null;
            }
            HashMap hashMap3 = new HashMap();
            JSONObject jSONObject = new JSONObject(body);
            if (jSONObject.has("data")) {
                JSONArray jSONArray = jSONObject.getJSONArray("data");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    if (jSONObject2.has("packageName") && jSONObject2.has(OTAConstants.JsonKey.EXTRAS)) {
                        hashMap3.put(jSONObject2.getString("packageName"), jSONObject2.getString(OTAConstants.JsonKey.EXTRAS));
                    }
                }
            }
            for (Campaign campaign : data) {
                CampaignPackage campaignPackage = campaign.getCampaignPackage();
                if (campaignPackage != null) {
                    campaign.setTraceId((String) hashMap.get(campaignPackage.getPackageName()));
                    campaign.setPackageJson(JsonUtils.toJson(campaignPackage));
                    campaign.setExtra((String) hashMap3.get(campaignPackage.getPackageName()));
                }
            }
            return data;
        } catch (Throwable th) {
            throw new IOException("Check failed", th);
        }
    }

    public static String report(String str, List<CampaignEvent> list) {
        if (list == null || list.isEmpty()) {
            Logger.d(TAG, "Campaign event is empty", new Object[0]);
            return null;
        }
        Context context = SOTAManager.getContext();
        String appId = SOTAManager.getConfig().getAppId();
        String appSecret = SOTAManager.getConfig().getAppSecret();
        long currentTimeMillis = System.currentTimeMillis();
        String campaignEventJsonText = CampaignConverter.getCampaignEventJsonText(list);
        HttpHeaders buildPostHeaders = HttpHeaderUtils.buildPostHeaders(appId, appSecret, HttpHeaderUtils.buildXpClient(context, AppUtils.getVersionName(context), String.valueOf(AppUtils.getVersionCode(context)), currentTimeMillis), currentTimeMillis, campaignEventJsonText);
        try {
            IHttp http = SOTAManager.getHttp();
            IRequest post = http.post(str + Constants.SOTA_EVENT_URI);
            for (String str2 : buildPostHeaders.getNames()) {
                post.headers(str2, buildPostHeaders.get(str2));
            }
            return post.uploadJson(campaignEventJsonText).execute().body();
        } catch (Throwable th) {
            Logger.e(TAG, th, "Convert upgrade response exception", new Object[0]);
            return null;
        }
    }

    private static String buildInstalledPackages(List<PackageInfo> list, Map<String, String> map) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (PackageInfo packageInfo : list) {
            sb.append(packageInfo.packageName);
            sb.append("|");
            sb.append(packageInfo.versionCode);
            sb.append("|");
            sb.append(packageInfo.versionName);
            sb.append("|");
            sb.append(map.get(packageInfo.packageName));
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
