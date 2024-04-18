package com.xiaopeng.ota.helper;

import android.text.TextUtils;
import com.xiaopeng.ota.VehicleFeature;
import com.xiaopeng.ota.activity.MainFragment;
import com.xiaopeng.ota.activity.MainV2Fragment;
import com.xiaopeng.ota.activity.NewFragment;
import com.xiaopeng.ota.activity.NewV2Fragment;
import com.xiaopeng.ota.activity.ScheduleFragment;
import com.xiaopeng.ota.activity.ScheduleV2Fragment;
import com.xiaopeng.ota.activity.UpgradeFragment;
import com.xiaopeng.ota.activity.UpgradeV2Fragment;
import com.xiaopeng.ota.activity.VersionDetailFragment;
import com.xiaopeng.ota.activity.VersionDetailV2Fragment;
import com.xiaopeng.ota.activity.VersionTitle;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.presenter.update.bean.Feature;
import com.xiaopeng.ota.presenter.update.bean.FeatureResource;
import com.xiaopeng.ota.utils.Base64Utils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.VersionUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class CampaignFeatureHelper {
    private static final String TAG = "FeatureHelper";

    public static Class getMainFragmentClass() {
        String carType = VehicleFeature.getCarType();
        if (((carType.hashCode() == 67915 && carType.equals("E28")) ? (char) 0 : (char) 65535) != 0 || VersionUtils.isNewVersionDetailOs()) {
            return MainV2Fragment.class;
        }
        return MainFragment.class;
    }

    public static Class getNewFragmentClass(Campaign campaign) {
        if (campaign != null && isTitlesFeature(campaign.getFeatures())) {
            return NewV2Fragment.class;
        }
        return NewFragment.class;
    }

    public static Class getUpgradeFragmentClass() {
        String carType = VehicleFeature.getCarType();
        if (((carType.hashCode() == 67044 && carType.equals("D55")) ? (char) 0 : (char) 65535) == 0) {
            return UpgradeV2Fragment.class;
        }
        return UpgradeFragment.class;
    }

    public static Class getScheduleFragmentClass() {
        String carType = VehicleFeature.getCarType();
        if (((carType.hashCode() == 67044 && carType.equals("D55")) ? (char) 0 : (char) 65535) == 0) {
            return ScheduleV2Fragment.class;
        }
        return ScheduleFragment.class;
    }

    public static Class getVersionDetailFragmentClass(Campaign campaign) {
        if (campaign == null) {
            return VersionDetailFragment.class;
        }
        return getVersionDetailFragmentClass(campaign.getFeatures());
    }

    public static Class getVersionDetailFragmentClass(List<Feature> list) {
        if (isTitlesFeature(list)) {
            return VersionDetailV2Fragment.class;
        }
        return VersionDetailFragment.class;
    }

    public static Map<String, FeatureResource> getResourceMap(List<Feature> list) {
        if (isTitlesFeature(list)) {
            HashMap hashMap = new HashMap();
            for (Feature feature : list) {
                List<FeatureResource> resources = feature.getResources();
                if (resources != null && !resources.isEmpty()) {
                    for (FeatureResource featureResource : resources) {
                        if (!TextUtils.isEmpty(featureResource.getDownloadUrl())) {
                            hashMap.put(featureResource.getDownloadUrl(), featureResource);
                        }
                    }
                }
            }
            return hashMap;
        }
        return null;
    }

    private static boolean isTitlesFeature(List<Feature> list) {
        return isFirstFeatureContainsTitle(list) || isMultiFeatures(list);
    }

    private static boolean isFirstFeatureContainsTitle(List<Feature> list) {
        Feature feature;
        return (list == null || list.size() == 0 || (feature = list.get(0)) == null || TextUtils.isEmpty(feature.getTitle())) ? false : true;
    }

    private static boolean isMultiFeatures(List<Feature> list) {
        return list != null && list.size() > 1;
    }

    public static List<VersionTitle> getVersionTitle(List<Feature> list) {
        ArrayList arrayList = new ArrayList();
        if (isTitlesFeature(list)) {
            for (Feature feature : list) {
                if (TextUtils.isEmpty(feature.getTitle())) {
                    LogUtils.w(TAG, "Feature title is empty");
                } else {
                    VersionTitle versionTitle = new VersionTitle();
                    versionTitle.setTitle(feature.getTitle());
                    versionTitle.setContent(getHtmlReleaseNotes(feature));
                    arrayList.add(versionTitle);
                }
            }
        } else {
            LogUtils.w(TAG, "Feature is empty");
        }
        return arrayList;
    }

    public static String getHtmlReleaseNotes(Feature feature) {
        return (feature == null || TextUtils.isEmpty(feature.getContent())) ? "" : Base64Utils.decodeString(feature.getContent());
    }
}
