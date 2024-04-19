package com.xiaopeng.ota.activity;

import android.view.View;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.helper.ActivityHelper;
import com.xiaopeng.ota.helper.CampaignFeatureHelper;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.helper.RoadHelper;
import com.xiaopeng.ota.helper.VehicleHelper;
import com.xiaopeng.ota.model.TimeCountDown;
import com.xiaopeng.ota.presenter.event.EventPresenter;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ResourceUtils;
import com.xiaopeng.ota.utils.StringUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.xui.widget.XButton;
import com.xiaopeng.xui.widget.XImageView;
import com.xiaopeng.xui.widget.XTextView;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class UpgradeFragment extends BaseFragment {
    private static final String TAG = "UpgradeFragment";
    protected static int sCountDown = 0;
    protected static boolean sUnsafeRoad = false;
    protected XButton mBtnFunc1;
    protected XButton mBtnFunc2;
    protected XImageView mIvBackground;
    protected TimeCountDown mTimeCountDown;
    protected XTextView mTvMainTip;
    protected XTextView mTvSubTip;
    protected XTextView mTvTitle;
    protected XTextView mTvUpgradeTime;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public String getFragmentName() {
        return TAG;
    }

    public static void resetCountDown() {
        long j = OTAManager.getConfigManager().getLong(Constants.ConfigKey.SAFE_PARKED_MIN_TIME_INTERVAL, TimeUnit.MINUTES.toMillis(20L));
        long parkedTime = VehicleHelper.getParkedTime();
        LogUtils.d(TAG, "Check upgrade safe (road=%b, packedTime=%d, parkedSafeInterval=%d)", Boolean.valueOf(sUnsafeRoad), Long.valueOf(parkedTime), Long.valueOf(j));
        if (parkedTime < j) {
            sUnsafeRoad = RoadHelper.getInstance().onDangerousRoad();
        } else {
            sUnsafeRoad = false;
        }
        if (sUnsafeRoad) {
            sCountDown = ConfigHelper.getInt(Constants.ConfigKey.UPGRADE_UNSAFE_COUNTDOWN_IN_SECOND);
        } else {
            sCountDown = ConfigHelper.getInt(Constants.ConfigKey.UPGRADE_COUNTDOWN_IN_SECOND);
        }
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    protected int getLayoutId() {
        return R.layout.fragment_upgrade;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void initViews(View view) {
        super.initViews(view);
        setTitle(ConfigHelper.getString(Constants.ConfigKey.TITLE_UPGRADE));
        this.mIvBackground = (XImageView) view.findViewById(R.id.iv_background);
        this.mTvTitle = (XTextView) view.findViewById(R.id.tv_title);
        this.mTvMainTip = (XTextView) view.findViewById(R.id.tv_main_tip);
        this.mTvSubTip = (XTextView) view.findViewById(R.id.tv_sub_tip);
        this.mTvUpgradeTime = (XTextView) view.findViewById(R.id.tv_upgrade_time);
        this.mBtnFunc1 = (XButton) view.findViewById(R.id.btn_function1);
        this.mBtnFunc2 = (XButton) view.findViewById(R.id.btn_function2);
        this.mBtnFunc1.setOnClickListener(this);
        this.mBtnFunc2.setOnClickListener(this);
        this.mTvTitle.setText(ConfigHelper.getString(Constants.ConfigKey.TITLE_UPGRADE_WARN));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void refreshData() {
        super.refreshData();
        clearCountDown();
        if (sUnsafeRoad) {
            this.mIvBackground.setImageResource(R.mipmap.bg_upgrade_warn);
            onDangerousRoad(true);
        } else {
            this.mIvBackground.setImageResource(R.mipmap.bg_cannot_drive);
            onDangerousRoad(false);
        }
        onFindCampaign(OTAManager.getCampaignManager().getActiveCampaign());
    }

    protected void onDangerousRoad(boolean z) {
        if (!z) {
            this.mTvMainTip.setText(ConfigHelper.getString(Constants.ConfigKey.UPGRADE_MAIN_TIP));
            this.mTvSubTip.setText(ConfigHelper.getString(Constants.ConfigKey.UPGRADE_SUB_TIP));
            this.mBtnFunc1.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_UPGRADE_NOW));
            this.mBtnFunc2.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_SCHEDULE));
        } else {
            this.mTvMainTip.setText(ConfigHelper.getString(Constants.ConfigKey.UPGRADE_MAIN_TIP_TEMP_PARK));
            this.mTvSubTip.setText(ConfigHelper.getString(Constants.ConfigKey.UPGRADE_SUB_TIP_TEMP_PARK));
            this.mBtnFunc1.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_UPGRADE_NOW));
            this.mBtnFunc2.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_SCHEDULE));
        }
        EventPresenter.getInstance().sendPromptUpgradeEvent(OTAManager.getCampaignManager().getActiveCampaignId(), z ? 1 : 0);
    }

    protected void onFindCampaign(Campaign campaign) {
        if (campaign == null) {
            LogUtils.e(TAG, "No campaign active");
            return;
        }
        this.mTvUpgradeTime.setText(StringUtils.buildColorText(StringUtils.format(Constants.ConfigKey.UPGRADE_TIME_FORMAT, StringUtils.format(R.string.emphasize_format, String.valueOf(campaign.getEstimateCost()))), ResourceUtils.getColor(R.color.text_emphasize)));
        if (sUnsafeRoad) {
            if (supportSchedule(campaign)) {
                this.mBtnFunc1.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_SCHEDULE));
            } else {
                this.mBtnFunc1.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_UPGRADE_LATER));
            }
            this.mBtnFunc2.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_STILL_UPGRADE));
            return;
        }
        final String str = ConfigHelper.getString(Constants.ConfigKey.BUTTON_CONFIRM_UPGRADE) + ResourceUtils.getString(R.string.countdown_format);
        this.mBtnFunc1.setText(String.format(str, Integer.valueOf(sCountDown)));
        this.mBtnFunc1.setEnabled(false);
        this.mTimeCountDown = new TimeCountDown<BaseFragment>(this, sCountDown * 1000, 1000L) { // from class: com.xiaopeng.ota.activity.UpgradeFragment.1
            @Override // com.xiaopeng.ota.model.TimeCountDown
            protected void onTick(boolean z) {
                if (!z) {
                    String str2 = str;
                    int i = UpgradeFragment.sCountDown;
                    UpgradeFragment.sCountDown = i - 1;
                    String format = String.format(str2, Integer.valueOf(i));
                    if (UpgradeFragment.sCountDown >= 0) {
                        UpgradeFragment.this.mBtnFunc1.setText(format);
                        return;
                    }
                }
                UpgradeFragment.this.mBtnFunc1.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_CONFIRM_UPGRADE));
                UpgradeFragment.this.mBtnFunc1.setEnabled(true);
            }
        };
        this.mTimeCountDown.start();
        if (supportSchedule(campaign)) {
            this.mBtnFunc2.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_SCHEDULE));
        } else {
            this.mBtnFunc2.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_UPGRADE_LATER));
        }
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.title_ib_close) {
            EventPresenter.getInstance().sendUpgradeLaterEvent(OTAManager.getCampaignManager().getActiveCampaignId());
        } else if (view.getId() == R.id.btn_function1) {
            final Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
            if (activeCampaign == null) {
                promptExpiredAndGotoMain();
            } else {
                ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$UpgradeFragment$Vr4vg4XP7UlnCm9ihd5jtX3-heg
                    @Override // java.lang.Runnable
                    public final void run() {
                        UpgradeFragment.this.lambda$onClick$0$UpgradeFragment(activeCampaign);
                    }
                });
            }
        } else if (view.getId() == R.id.btn_function2) {
            final Campaign activeCampaign2 = OTAManager.getCampaignManager().getActiveCampaign();
            if (activeCampaign2 == null) {
                promptExpiredAndGotoMain();
            } else {
                ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$UpgradeFragment$4XILnCxZdpDh0d4kL4ChRQO16wk
                    @Override // java.lang.Runnable
                    public final void run() {
                        UpgradeFragment.this.lambda$onClick$1$UpgradeFragment(activeCampaign2);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onClick$0$UpgradeFragment(Campaign campaign) {
        clearCountDown();
        if (sUnsafeRoad) {
            if (supportSchedule(campaign)) {
                startFragment(CampaignFeatureHelper.getScheduleFragmentClass());
                EventPresenter.getInstance().sendScheduleSettingEvent(campaign.getCampaignId());
                return;
            }
            lambda$onClick$1$BaseFragment();
            EventPresenter.getInstance().sendUpgradeLaterEvent(campaign.getCampaignId());
            return;
        }
        ActivityHelper.startUpgrade();
        EventPresenter.getInstance().sendConfirmUpgradeEvent(campaign.getCampaignId(), supportSchedule(campaign));
    }

    public /* synthetic */ void lambda$onClick$1$UpgradeFragment(Campaign campaign) {
        clearCountDown();
        if (sUnsafeRoad) {
            startFragment(ParkFragment.class);
            EventPresenter.getInstance().sendStillUpgradeEvent(campaign.getCampaignId());
        } else if (supportSchedule(campaign)) {
            startFragment(CampaignFeatureHelper.getScheduleFragmentClass());
            EventPresenter.getInstance().sendScheduleSettingEvent(campaign.getCampaignId());
        } else {
            lambda$onClick$1$BaseFragment();
            EventPresenter.getInstance().sendUpgradeLaterEvent(campaign.getCampaignId());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void onBeforeClose() {
        super.onBeforeClose();
        clearCountDown();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void onBeforeBack() {
        super.onBeforeBack();
        clearCountDown();
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        clearCountDown();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void clearCountDown() {
        TimeCountDown timeCountDown = this.mTimeCountDown;
        if (timeCountDown != null) {
            timeCountDown.cancel();
            this.mTimeCountDown = null;
        }
    }
}
