package com.xiaopeng.ota.activity;

import android.graphics.Typeface;
import android.view.View;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.VehicleFeature;
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
import com.xiaopeng.xui.widget.XButton;
import com.xiaopeng.xui.widget.XImageView;
import com.xiaopeng.xui.widget.XTextView;
/* loaded from: classes2.dex */
public class UpgradeV2Fragment extends UpgradeFragment {
    private static final String TAG = "UpgradeV2Fragment";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.UpgradeFragment, com.xiaopeng.ota.activity.BaseFragment
    public String getFragmentName() {
        return TAG;
    }

    @Override // com.xiaopeng.ota.activity.UpgradeFragment, com.xiaopeng.ota.activity.BaseFragment
    protected int getLayoutId() {
        return R.layout.fragment_upgrade_v2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.UpgradeFragment, com.xiaopeng.ota.activity.BaseFragment
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
        if ("D55".equals(VehicleFeature.getCarType())) {
            this.mTvTitle.setTypeface(Typeface.defaultFromStyle(1));
        }
        this.mTvTitle.setText(ConfigHelper.getString(Constants.ConfigKey.TITLE_UPGRADE_WARN));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.UpgradeFragment, com.xiaopeng.ota.activity.BaseFragment
    public void refreshData() {
        super.refreshTitle();
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

    @Override // com.xiaopeng.ota.activity.UpgradeFragment
    protected void onDangerousRoad(boolean z) {
        if (!z) {
            this.mTvMainTip.setText(toHtml(ConfigHelper.getString(Constants.ConfigKey.UPGRADE_MAIN_TIP)));
            this.mTvSubTip.setText(toHtml(ConfigHelper.getString(Constants.ConfigKey.UPGRADE_SUB_TIP)));
            this.mBtnFunc1.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_UPGRADE_NOW));
            this.mBtnFunc2.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_SCHEDULE));
        } else {
            this.mTvMainTip.setText(toHtml(ConfigHelper.getString(Constants.ConfigKey.UPGRADE_MAIN_TIP_TEMP_PARK)));
            this.mTvSubTip.setText(toHtml(ConfigHelper.getString(Constants.ConfigKey.UPGRADE_SUB_TIP_TEMP_PARK)));
            this.mBtnFunc1.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_UPGRADE_NOW));
            this.mBtnFunc2.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_SCHEDULE));
        }
        EventPresenter.getInstance().sendPromptUpgradeEvent(OTAManager.getCampaignManager().getActiveCampaignId(), z ? 1 : 0);
    }

    @Override // com.xiaopeng.ota.activity.UpgradeFragment
    protected void onFindCampaign(Campaign campaign) {
        if (campaign == null) {
            LogUtils.e(TAG, "No campaign active");
            return;
        }
        this.mTvUpgradeTime.setText(toHtml(StringUtils.format(Constants.ConfigKey.UPGRADE_TIME_V2, String.valueOf(campaign.getEstimateCost()))));
        if (supportSchedule(campaign)) {
            this.mBtnFunc2.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_SCHEDULE));
        } else {
            this.mBtnFunc2.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_UPGRADE_LATER));
        }
        final String str = ConfigHelper.getString(Constants.ConfigKey.BUTTON_UPGRADE_NOW) + ResourceUtils.getString(R.string.countdown_format);
        this.mBtnFunc1.setText(String.format(str, Integer.valueOf(sCountDown)));
        this.mBtnFunc1.setEnabled(false);
        this.mTimeCountDown = new TimeCountDown<UpgradeV2Fragment>(this, sCountDown * 1000, 1000L) { // from class: com.xiaopeng.ota.activity.UpgradeV2Fragment.1
            @Override // com.xiaopeng.ota.model.TimeCountDown
            protected void onTick(boolean z) {
                if (!z) {
                    String str2 = str;
                    int i = UpgradeFragment.sCountDown;
                    UpgradeFragment.sCountDown = i - 1;
                    String format = String.format(str2, Integer.valueOf(i));
                    if (UpgradeFragment.sCountDown >= 0) {
                        UpgradeV2Fragment.this.mBtnFunc1.setText(format);
                        return;
                    }
                }
                UpgradeV2Fragment.this.mBtnFunc1.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_CONFIRM_UPGRADE));
                UpgradeV2Fragment.this.mBtnFunc1.setEnabled(true);
            }
        };
        this.mTimeCountDown.start();
    }

    @Override // com.xiaopeng.ota.activity.UpgradeFragment, com.xiaopeng.ota.activity.BaseFragment, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.title_ib_close) {
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$UpgradeV2Fragment$omK0p6KDbWn29ORNSuymsLVkUFs
                @Override // java.lang.Runnable
                public final void run() {
                    UpgradeV2Fragment.this.lambda$onClick$0$UpgradeV2Fragment();
                }
            });
        } else if (view.getId() == R.id.btn_function1) {
            final Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
            if (activeCampaign == null) {
                promptExpiredAndGotoMain();
            } else {
                ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$UpgradeV2Fragment$WLy_dxaZHnf7MQGIk41W0UpxE1I
                    @Override // java.lang.Runnable
                    public final void run() {
                        UpgradeV2Fragment.this.lambda$onClick$1$UpgradeV2Fragment(activeCampaign);
                    }
                });
            }
        } else if (view.getId() == R.id.btn_function2) {
            final Campaign activeCampaign2 = OTAManager.getCampaignManager().getActiveCampaign();
            if (activeCampaign2 == null) {
                promptExpiredAndGotoMain();
            } else {
                ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$UpgradeV2Fragment$h-MNV17RVDFPHXqfY4mVHaBBXsY
                    @Override // java.lang.Runnable
                    public final void run() {
                        UpgradeV2Fragment.this.lambda$onClick$2$UpgradeV2Fragment(activeCampaign2);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onClick$0$UpgradeV2Fragment() {
        lambda$onClick$1$BaseFragment();
        EventPresenter.getInstance().sendUpgradeLaterEvent(OTAManager.getCampaignManager().getActiveCampaignId());
    }

    public /* synthetic */ void lambda$onClick$1$UpgradeV2Fragment(Campaign campaign) {
        clearCountDown();
        ActivityHelper.startUpgrade();
        EventPresenter.getInstance().sendConfirmUpgradeEvent(campaign.getCampaignId(), supportSchedule(campaign));
    }

    public /* synthetic */ void lambda$onClick$2$UpgradeV2Fragment(Campaign campaign) {
        clearCountDown();
        if (supportSchedule(campaign)) {
            startFragment(CampaignFeatureHelper.getScheduleFragmentClass(), CampaignFeatureHelper.getMainFragmentClass());
            EventPresenter.getInstance().sendScheduleSettingEvent(campaign.getCampaignId());
            return;
        }
        lambda$onClick$1$BaseFragment();
        EventPresenter.getInstance().sendUpgradeLaterEvent(campaign.getCampaignId());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.UpgradeFragment, com.xiaopeng.ota.activity.BaseFragment
    public void onBeforeClose() {
        super.onBeforeClose();
        clearCountDown();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.UpgradeFragment, com.xiaopeng.ota.activity.BaseFragment
    public void onBeforeBack() {
        super.onBeforeBack();
        clearCountDown();
    }

    @Override // com.xiaopeng.ota.activity.UpgradeFragment, com.xiaopeng.ota.activity.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        clearCountDown();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.UpgradeFragment
    public void clearCountDown() {
        if (this.mTimeCountDown != null) {
            this.mTimeCountDown.cancel();
            this.mTimeCountDown = null;
        }
    }
}
