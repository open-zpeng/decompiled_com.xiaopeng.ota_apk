package com.xiaopeng.ota.activity;

import android.view.View;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.helper.ActivityHelper;
import com.xiaopeng.ota.helper.CampaignFeatureHelper;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.model.TimeCountDown;
import com.xiaopeng.ota.presenter.event.EventPresenter;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ResourceUtils;
import com.xiaopeng.ota.utils.StringUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
/* loaded from: classes2.dex */
public class ParkFragment extends UpgradeFragment {
    private static final String TAG = "ParkFragment";

    @Override // com.xiaopeng.ota.activity.UpgradeFragment, com.xiaopeng.ota.activity.BaseFragment
    protected String getFragmentName() {
        return TAG;
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    protected String getThemeFilePath() {
        return "theme/upgrade_fragment.xml";
    }

    @Override // com.xiaopeng.ota.activity.UpgradeFragment
    protected void onDangerousRoad(boolean z) {
        this.mTvMainTip.setText(ConfigHelper.getString(Constants.ConfigKey.PARK_MAIN_TIP));
        this.mTvSubTip.setText(ConfigHelper.getString(Constants.ConfigKey.PARK_SUB_TIP));
        this.mBtnFunc1.setText(String.format(ConfigHelper.getString(Constants.ConfigKey.BUTTON_CONFIRM_PARK) + ResourceUtils.getString(R.string.countdown_format), Integer.valueOf(sCountDown)));
        this.mBtnFunc1.setEnabled(false);
        this.mBtnFunc2.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_NOT_SURE));
    }

    @Override // com.xiaopeng.ota.activity.UpgradeFragment
    protected void onFindCampaign(Campaign campaign) {
        if (campaign == null) {
            LogUtils.e(TAG, "No campaign active");
            return;
        }
        this.mTvUpgradeTime.setText(StringUtils.buildColorText(StringUtils.format(Constants.ConfigKey.UPGRADE_TIME_FORMAT, StringUtils.format(R.string.emphasize_format, String.valueOf(campaign.getEstimateCost()))), ResourceUtils.getColor(R.color.text_emphasize)));
    }

    @Override // com.xiaopeng.ota.activity.UpgradeFragment, com.xiaopeng.ota.activity.BaseFragment
    public void refreshData() {
        super.refreshData();
        final String str = ConfigHelper.getString(Constants.ConfigKey.BUTTON_CONFIRM_PARK) + ResourceUtils.getString(R.string.countdown_format);
        this.mTimeCountDown = new TimeCountDown<BaseFragment>(this, sCountDown * 1000, 1000L) { // from class: com.xiaopeng.ota.activity.ParkFragment.1
            @Override // com.xiaopeng.ota.model.TimeCountDown
            protected void onTick(boolean z) {
                if (!z) {
                    String str2 = str;
                    int i = UpgradeFragment.sCountDown;
                    UpgradeFragment.sCountDown = i - 1;
                    String format = String.format(str2, Integer.valueOf(i));
                    if (UpgradeFragment.sCountDown >= 0) {
                        ParkFragment.this.mBtnFunc1.setText(format);
                        return;
                    }
                }
                ParkFragment.this.mBtnFunc1.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_CONFIRM_PARK));
                ParkFragment.this.mBtnFunc1.setEnabled(true);
            }
        };
        this.mTimeCountDown.start();
    }

    @Override // com.xiaopeng.ota.activity.UpgradeFragment, com.xiaopeng.ota.activity.BaseFragment, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.title_ib_close) {
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$ParkFragment$CvD5DSN-6BKAQ4k5DQucLDMrcaM
                @Override // java.lang.Runnable
                public final void run() {
                    ParkFragment.this.lambda$onClick$0$ParkFragment();
                }
            });
        } else if (view.getId() == R.id.btn_function1) {
            Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
            if (activeCampaign == null) {
                promptExpiredAndGotoMain();
                return;
            }
            clearCountDown();
            ActivityHelper.startUpgrade();
            EventPresenter.getInstance().sendConfirmParkEvent(activeCampaign.getCampaignId());
        } else if (view.getId() == R.id.btn_function2) {
            final Campaign activeCampaign2 = OTAManager.getCampaignManager().getActiveCampaign();
            if (activeCampaign2 == null) {
                promptExpiredAndGotoMain();
            } else {
                ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$ParkFragment$CM2WTk9v_anG1tQZNsr06jkl2Ho
                    @Override // java.lang.Runnable
                    public final void run() {
                        ParkFragment.this.lambda$onClick$1$ParkFragment(activeCampaign2);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onClick$0$ParkFragment() {
        lambda$onClick$1$BaseFragment();
        EventPresenter.getInstance().sendUpgradeLaterEvent(OTAManager.getCampaignManager().getActiveCampaignId());
    }

    public /* synthetic */ void lambda$onClick$1$ParkFragment(Campaign campaign) {
        clearCountDown();
        if (supportSchedule(campaign)) {
            startFragment(CampaignFeatureHelper.getScheduleFragmentClass());
            EventPresenter.getInstance().sendScheduleSettingEvent(campaign.getCampaignId());
            return;
        }
        lambda$onClick$1$BaseFragment();
        EventPresenter.getInstance().sendUpgradeLaterEvent(campaign.getCampaignId());
    }
}
