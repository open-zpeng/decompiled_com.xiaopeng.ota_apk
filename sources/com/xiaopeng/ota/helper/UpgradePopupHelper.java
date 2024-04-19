package com.xiaopeng.ota.helper;

import android.text.TextUtils;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.bean.UpgradePopupConfig;
import com.xiaopeng.ota.presenter.event.EventPresenter;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.sdk.common.util.PreferencesUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class UpgradePopupHelper {
    private static final String COMMA = ",";
    private static final String KEY_AUTO_UPGRADE_POPUP_TIME = "auto_upgrade_popup_time";
    private static final String TAG = "UpgradePopupHelper";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class Holder {
        static final UpgradePopupHelper INSTANCE = new UpgradePopupHelper();

        private Holder() {
        }
    }

    public static UpgradePopupHelper getInstance() {
        return Holder.INSTANCE;
    }

    public void tryPopupAutoDialog(Campaign campaign, UpgradePopupConfig.PopupStrategy popupStrategy) {
        int gearLever;
        LogUtils.d(TAG, "try popup auto upgrade dialog trigger:%s", popupStrategy.name());
        if (campaign == null) {
            LogUtils.d(TAG, "Not found available campaign");
        } else if (UpgradePopupConfig.PopupStrategy.PARKING_GEAR == popupStrategy && 4 != (gearLever = CarServiceHelper.getGearLever())) {
            LogUtils.i(TAG, "Current gear level(%d) is not P", Integer.valueOf(gearLever));
        } else {
            UpgradePopupConfig config = getConfig();
            if (matchPopupStrategy(popupStrategy, config) && supportAutoUpgrade(campaign, config)) {
                DialogHelper.getInstance().promptUpgradeDialog(campaign, popupStrategy, config);
            }
        }
    }

    public void tryDismissAutoDialog(Campaign campaign, UpgradePopupConfig.DismissStrategy dismissStrategy) {
        if (DialogHelper.getInstance().isUpgradePopupDialogShowing() && matchDismissStrategy(dismissStrategy, getConfig())) {
            DialogHelper.getInstance().dismissUpgradePopupDialog();
            EventPresenter.getInstance().sendAutoUpgradeDialogDismiss(campaign == null ? 0L : campaign.getCampaignId(), dismissStrategy.name());
        }
    }

    private boolean matchPopupStrategy(UpgradePopupConfig.PopupStrategy popupStrategy, UpgradePopupConfig upgradePopupConfig) {
        if (upgradePopupConfig == null) {
            LogUtils.d(TAG, "config is null");
            return false;
        }
        UpgradePopupConfig.PopupStrategy of = UpgradePopupConfig.PopupStrategy.of(upgradePopupConfig.getPopupStrategy());
        if (of == null) {
            LogUtils.d(TAG, "config popup strategy not defined(%d)", Integer.valueOf(upgradePopupConfig.getPopupStrategy()));
            return false;
        }
        LogUtils.d(TAG, "popup trigger strategy:%s, config:%s", popupStrategy.name(), of.name());
        return popupStrategy.getCode() == (popupStrategy.getCode() & of.getCode());
    }

    private boolean matchDismissStrategy(UpgradePopupConfig.DismissStrategy dismissStrategy, UpgradePopupConfig upgradePopupConfig) {
        if (upgradePopupConfig == null) {
            LogUtils.d(TAG, "config is null");
            return false;
        }
        UpgradePopupConfig.DismissStrategy of = UpgradePopupConfig.DismissStrategy.of(upgradePopupConfig.getDismissStrategy());
        if (of == null) {
            LogUtils.d(TAG, "config dismiss strategy not defined(%d)", Integer.valueOf(upgradePopupConfig.getPopupStrategy()));
            return false;
        }
        LogUtils.d(TAG, "dismiss trigger strategy:%s, config:%s", dismissStrategy.name(), of.name());
        return dismissStrategy.getCode() == (dismissStrategy.getCode() & of.getCode());
    }

    private boolean supportAutoUpgrade(Campaign campaign, UpgradePopupConfig upgradePopupConfig) {
        if (campaign == null) {
            LogUtils.d(TAG, "Not found available campaign");
            return false;
        } else if (!campaign.isSupportSchedule()) {
            LogUtils.d(TAG, "Campaign(%s) not support schedule", campaign.getCampaignName());
            return false;
        } else if (ScheduleHelper.getInstance().isSetScheduleTime()) {
            LogUtils.d(TAG, "Campaign(%s) has set schedule time", campaign.getCampaignName());
            return false;
        } else if (upgradePopupConfig == null) {
            LogUtils.d(TAG, "Config is null");
            return false;
        } else if (upgradePopupConfig.getCampaignList() != null && !upgradePopupConfig.getCampaignList().isEmpty()) {
            if (!upgradePopupConfig.getCampaignList().contains(Long.valueOf(campaign.getCampaignId()))) {
                LogUtils.d(TAG, "Campaign id:%d does not in list:%s", Long.valueOf(campaign.getCampaignId()), upgradePopupConfig.getCampaignList());
                return false;
            } else if (isLastPopupOverTime(upgradePopupConfig)) {
                return true;
            } else {
                LogUtils.d(TAG, "Popup too often");
                return false;
            }
        } else {
            LogUtils.d(TAG, "Campaign id list is empty");
            return false;
        }
    }

    private boolean containsCampaignId(UpgradePopupConfig upgradePopupConfig, long j) {
        if (upgradePopupConfig.getCampaignList() != null && !upgradePopupConfig.getCampaignList().isEmpty()) {
            if (upgradePopupConfig.getCampaignList().contains(Long.valueOf(j))) {
                return true;
            }
            LogUtils.d(TAG, "Campaign id:%d does not in list:%s", Long.valueOf(j), upgradePopupConfig.getCampaignList());
            return false;
        }
        LogUtils.d(TAG, "Campaign id list is empty");
        return false;
    }

    private UpgradePopupConfig getConfig() {
        String string = ConfigHelper.getString(Constants.ConfigKey.AUTO_UPGRADE_CAMPAIGN_LIST);
        LogUtils.d(TAG, "campaign list:[%s]", string);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        UpgradePopupConfig upgradePopupConfig = new UpgradePopupConfig();
        upgradePopupConfig.setCampaignList(parseCampaignList(string));
        int i = ConfigHelper.getInt(Constants.ConfigKey.AUTO_UPGRADE_POPUP_STRATEGY);
        int i2 = ConfigHelper.getInt(Constants.ConfigKey.AUTO_UPGRADE_DISMISS_STRATEGY);
        int i3 = ConfigHelper.getInt(Constants.ConfigKey.AUTO_UPGRADE_POPUP_FREQUENCY);
        String string2 = ConfigHelper.getString(Constants.ConfigKey.AUTO_UPGRADE_SCHEDULE_TIME);
        String string3 = ConfigHelper.getString(Constants.ConfigKey.AUTO_UPGRADE_POPUP_TITLE);
        String string4 = ConfigHelper.getString(Constants.ConfigKey.AUTO_UPGRADE_POPUP_CONTENT);
        String string5 = ConfigHelper.getString(Constants.ConfigKey.AUTO_UPGRADE_BUTTON_OK_TEXT);
        String string6 = ConfigHelper.getString(Constants.ConfigKey.AUTO_UPGRADE_BUTTON_CANCEL_TEXT);
        int i4 = ConfigHelper.getInt(Constants.ConfigKey.AUTO_UPGRADE_COUNTDOWN_SECONDS);
        upgradePopupConfig.setPopupStrategy(i);
        upgradePopupConfig.setDismissStrategy(i2);
        upgradePopupConfig.setPopupFrequency(i3);
        upgradePopupConfig.setScheduleTime(string2);
        upgradePopupConfig.setPopupTitle(string3);
        upgradePopupConfig.setPopupContent(string4);
        upgradePopupConfig.setButtonOkText(string5);
        upgradePopupConfig.setButtonCancelText(string6);
        upgradePopupConfig.setCountdownSeconds(i4);
        LogUtils.d(TAG, "Get config result:%s", upgradePopupConfig.toString());
        return upgradePopupConfig;
    }

    private List<Long> parseCampaignList(String str) {
        String[] split;
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            if (str.contains(",")) {
                for (String str2 : str.split(",")) {
                    if (!TextUtils.isEmpty(str2)) {
                        try {
                            arrayList.add(Long.valueOf(Long.parseLong(str2)));
                        } catch (NumberFormatException unused) {
                            LogUtils.d(TAG, "CampaignId(%s) invalid not number", str2);
                        }
                    }
                }
            } else {
                try {
                    arrayList.add(Long.valueOf(Long.parseLong(str)));
                } catch (NumberFormatException unused2) {
                    LogUtils.d(TAG, "CampaignId(%s) invalid not number", str);
                }
            }
        }
        return arrayList;
    }

    private boolean isLastPopupOverTime(UpgradePopupConfig upgradePopupConfig) {
        if (upgradePopupConfig.getPopupFrequency() != 0) {
            long currentTimeMillis = System.currentTimeMillis();
            long popupTime = getPopupTime();
            boolean z = currentTimeMillis - popupTime > TimeUnit.HOURS.toMillis((long) upgradePopupConfig.getPopupFrequency());
            LogUtils.d(TAG, "Popup over time:%b, current:%d, last:%d", Boolean.valueOf(z), Long.valueOf(currentTimeMillis), Long.valueOf(popupTime));
            return z;
        }
        return true;
    }

    private long getPopupTime() {
        return PreferencesUtils.getLong(OTAManager.getContext(), KEY_AUTO_UPGRADE_POPUP_TIME, 0L);
    }

    public void updatePopupTime() {
        PreferencesUtils.putLong(OTAManager.getContext(), KEY_AUTO_UPGRADE_POPUP_TIME, System.currentTimeMillis());
    }
}
