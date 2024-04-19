package com.xiaopeng.sota.sdk.manager.campaign;

import android.text.TextUtils;
import com.xiaopeng.ota.sdk.common.OTAConstants;
import com.xiaopeng.ota.sdk.common.log.Logger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class CampaignConverter {
    private static final String TAG = "CampaignConverter";

    public static CampaignPackage convertPackage(JSONObject jSONObject) throws JSONException {
        CampaignPackage campaignPackage = new CampaignPackage();
        if (jSONObject.has(OTAConstants.JsonKey.ID)) {
            campaignPackage.setId(Long.valueOf(jSONObject.getLong(OTAConstants.JsonKey.ID)));
        }
        if (jSONObject.has("name")) {
            campaignPackage.setName(jSONObject.getString("name"));
        }
        if (jSONObject.has("packageName")) {
            campaignPackage.setPackageName(jSONObject.getString("packageName"));
        }
        if (jSONObject.has(OTAConstants.JsonKey.SIZE)) {
            campaignPackage.setSize(Long.valueOf(jSONObject.getLong(OTAConstants.JsonKey.SIZE)));
        }
        if (jSONObject.has(OTAConstants.JsonKey.VERSION_NAME)) {
            campaignPackage.setVersionName(jSONObject.getString(OTAConstants.JsonKey.VERSION_NAME));
        }
        if (jSONObject.has(OTAConstants.JsonKey.VERSION_CODE)) {
            campaignPackage.setVersionCode(jSONObject.getString(OTAConstants.JsonKey.VERSION_CODE));
        }
        if (jSONObject.has(OTAConstants.JsonKey.HASH)) {
            campaignPackage.setHash(jSONObject.getString(OTAConstants.JsonKey.HASH));
        }
        if (jSONObject.has(OTAConstants.JsonKey.DOWNLOAD_URLS)) {
            JSONArray jSONArray = jSONObject.getJSONArray(OTAConstants.JsonKey.DOWNLOAD_URLS);
            int length = jSONArray.length();
            ArrayList arrayList = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                arrayList.add(jSONArray.getString(i));
            }
            campaignPackage.setDownloadUrls(arrayList);
        }
        return campaignPackage;
    }

    public static String getCampaignJsonText(Campaign campaign) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(OTAConstants.JsonKey.CAMPAIGN_NAME, campaign.getCampaignName());
            jSONObject.put(OTAConstants.JsonKey.UPGRADE_MODE, campaign.getUpgradeMode());
            jSONObject.put(OTAConstants.JsonKey.RELEASE_NOTES, campaign.getReleaseNotes());
        } catch (JSONException e) {
            Logger.e(TAG, e, "Convert campaign to json occurs exception", new Object[0]);
        }
        return jSONObject.toString();
    }

    public static String getCampaignPackageJsonText(CampaignPackage campaignPackage) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(OTAConstants.JsonKey.ID, campaignPackage.getId());
            jSONObject.put("name", campaignPackage.getName());
            jSONObject.put("packageName", campaignPackage.getPackageName());
            jSONObject.put(OTAConstants.JsonKey.SIZE, campaignPackage.getSize());
            jSONObject.put(OTAConstants.JsonKey.VERSION_NAME, campaignPackage.getVersionName());
            jSONObject.put(OTAConstants.JsonKey.VERSION_CODE, campaignPackage.getVersionCode());
            jSONObject.put(OTAConstants.JsonKey.HASH, campaignPackage.getHash());
            jSONObject.put(OTAConstants.JsonKey.DOWNLOAD_URLS, campaignPackage.getDownloadUrls());
        } catch (JSONException e) {
            Logger.e(TAG, e, "Convert campaign to json occurs exception", new Object[0]);
        }
        return jSONObject.toString();
    }

    public static String getCampaignEventJsonText(List<CampaignEvent> list) {
        JSONObject jSONObject = new JSONObject();
        try {
            int size = list.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                arrayList.add(getCampaignEventJson(list.get(i)));
            }
            jSONObject.put(OTAConstants.JsonKey.EVENTS, new JSONArray((Collection) arrayList));
        } catch (JSONException e) {
            Logger.e(TAG, e, "Convert campaign events to json occurs exception", new Object[0]);
        }
        return jSONObject.toString();
    }

    private static JSONObject getCampaignEventJson(CampaignEvent campaignEvent) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("traceId", campaignEvent.getTraceId());
            jSONObject.put(OTAConstants.JsonKey.CAMPAIGN_ID, campaignEvent.getCampaignId());
            jSONObject.put("event", campaignEvent.getEvent());
            jSONObject.put("status", campaignEvent.getStatus());
            jSONObject.put(OTAConstants.JsonKey.PACKAGE_ID, campaignEvent.getPackageId());
            jSONObject.put("timestamp", campaignEvent.getTimestamp());
            if (campaignEvent.getContext() != null) {
                jSONObject.put("context", new JSONObject(campaignEvent.getContext()));
            }
            if (!TextUtils.isEmpty(campaignEvent.getEventId())) {
                jSONObject.put("eventId", campaignEvent.getEventId());
            }
        } catch (JSONException e) {
            Logger.e(TAG, e, "Convert campaign event to json occurs exception", new Object[0]);
        }
        return jSONObject;
    }
}
