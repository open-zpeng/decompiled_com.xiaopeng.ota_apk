package com.xiaopeng.ota.activity;

import android.graphics.Typeface;
import android.util.Pair;
import android.view.View;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.VehicleFeature;
import com.xiaopeng.ota.helper.ActivityHelper;
import com.xiaopeng.ota.helper.CampaignFeatureHelper;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.helper.ScheduleHelper;
import com.xiaopeng.ota.presenter.event.EventPresenter;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.ota.utils.TimeUtils;
import com.xiaopeng.xui.widget.XButton;
import com.xiaopeng.xui.widget.XImageView;
import com.xiaopeng.xui.widget.XTextView;
import com.xiaopeng.xui.widget.timepicker.XTimePicker;
import java.util.Calendar;
/* loaded from: classes2.dex */
public class ScheduleV2Fragment extends BaseFragment {
    private static final String TAG = "ScheduleV2Fragment";
    private XButton mBtnCancel;
    private XButton mBtnConfirm;
    private XButton mBtnModify;
    private XButton mBtnUpgrade;
    private XImageView mIvScheduleUpgrade;
    private XTimePicker mTpScheduleTime;
    private XTextView mTvDesc;
    private XTextView mTvScheduleInfo;
    private XTextView mTvSelectTime;
    private XTextView mTvSubTip1;
    private XTextView mTvSubTip2;
    private XTextView mTvSubTip3;
    private XTextView mTvTips;
    private XTextView mTvUpgradeTimeConsume;
    private XTextView mTvUpgradeTimePredict;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public String getFragmentName() {
        return TAG;
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    protected int getLayoutId() {
        return R.layout.fragment_schedule_v2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void initViews(View view) {
        super.initViews(view);
        setTitle("");
        this.mTvDesc = (XTextView) view.findViewById(R.id.tv_desc);
        this.mTvDesc.setText(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_UPGRADE_DESC));
        this.mTvSelectTime = (XTextView) view.findViewById(R.id.tv_select_time);
        this.mTvSelectTime.setText(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_SELECT_TIME));
        this.mTpScheduleTime = (XTimePicker) view.findViewById(R.id.tp_schedule_time);
        this.mTvScheduleInfo = (XTextView) view.findViewById(R.id.tv_schedule_info);
        this.mTvScheduleInfo.setText(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_HAS_SCHEDULE_INFO));
        this.mIvScheduleUpgrade = (XImageView) view.findViewById(R.id.iv_schedule_upgrade);
        this.mTvUpgradeTimePredict = (XTextView) view.findViewById(R.id.tv_upgrade_time_predict);
        this.mTvTips = (XTextView) view.findViewById(R.id.tv_tips);
        if (VehicleFeature.isD55()) {
            this.mTvTips.setTypeface(Typeface.defaultFromStyle(1));
        }
        this.mTvSubTip1 = (XTextView) view.findViewById(R.id.tv_sub_tips_1);
        this.mTvSubTip2 = (XTextView) view.findViewById(R.id.tv_sub_tips_2);
        this.mTvSubTip3 = (XTextView) view.findViewById(R.id.tv_sub_tips_3);
        this.mTvTips.setText(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_TIPS));
        this.mTvSubTip1.setText(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_SUB_TIPS1));
        this.mTvSubTip2.setText(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_SUB_TIPS2));
        this.mTvSubTip3.setText(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_SUB_TIPS3));
        this.mTvUpgradeTimeConsume = (XTextView) view.findViewById(R.id.tv_time_consume);
        this.mBtnConfirm = (XButton) view.findViewById(R.id.btn_schedule_confirm);
        this.mBtnConfirm.setOnClickListener(this);
        this.mBtnConfirm.setText(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_BUTTON_CONFIRM));
        this.mBtnModify = (XButton) view.findViewById(R.id.btn_schedule_modify);
        this.mBtnModify.setOnClickListener(this);
        this.mBtnModify.setText(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_BUTTON_MODIFY));
        this.mBtnCancel = (XButton) view.findViewById(R.id.btn_cancel_schedule);
        this.mBtnCancel.setOnClickListener(this);
        this.mBtnCancel.setText(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_BUTTON_CANCEL));
        this.mBtnUpgrade = (XButton) view.findViewById(R.id.btn_upgrade);
        this.mBtnUpgrade.setOnClickListener(this);
        this.mBtnUpgrade.setText(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_BUTTON_UPGRADE));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void refreshData() {
        super.refreshData();
        if (ScheduleHelper.getInstance().isSetScheduleTime()) {
            Pair<Integer, Integer> hourAndMinute = TimeUtils.getHourAndMinute(ScheduleHelper.getInstance().getScheduleTime());
            if (hourAndMinute == null) {
                hourAndMinute = TimeUtils.getHourAndMinute(ScheduleHelper.getInstance().getDefaultTime());
            }
            switchToModifyTime(hourAndMinute);
        } else if (VehicleFeature.isD55() && OTAManager.getPreferencesManager().supportAutoUpgrade()) {
            switchToAutoUpgrade();
        } else {
            switchToConfirmTime();
            Pair<Integer, Integer> hourAndMinute2 = TimeUtils.getHourAndMinute(ScheduleHelper.getInstance().getDefaultTime());
            if (hourAndMinute2 != null) {
                this.mTpScheduleTime.setCurrentHour((Integer) hourAndMinute2.first);
                this.mTpScheduleTime.setCurrentMinute((Integer) hourAndMinute2.second);
            }
        }
    }

    private void switchToConfirmTime() {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$ScheduleV2Fragment$Mb8p-CYp6fyqt2TXYpPeMahEXaE
            @Override // java.lang.Runnable
            public final void run() {
                ScheduleV2Fragment.this.lambda$switchToConfirmTime$0$ScheduleV2Fragment();
            }
        });
    }

    public /* synthetic */ void lambda$switchToConfirmTime$0$ScheduleV2Fragment() {
        if (VehicleFeature.isD55()) {
            this.mTvSelectTime.setText(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_SELECT_TIME));
            this.mTvSelectTime.setVisibility(0);
            this.mTvUpgradeTimeConsume.setVisibility(0);
        } else {
            this.mTvSelectTime.setVisibility(0);
            this.mTvUpgradeTimeConsume.setVisibility(0);
        }
        this.mTpScheduleTime.setVisibility(0);
        this.mTvScheduleInfo.setVisibility(8);
        this.mIvScheduleUpgrade.setVisibility(8);
        this.mTvUpgradeTimePredict.setVisibility(8);
        this.mBtnConfirm.setVisibility(0);
        this.mBtnUpgrade.setVisibility(0);
        this.mBtnModify.setVisibility(8);
        this.mBtnCancel.setVisibility(8);
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign == null) {
            promptExpiredAndGotoMain();
        } else {
            this.mTvUpgradeTimeConsume.setText(getUpgradeTimeConsume(activeCampaign));
        }
    }

    private void switchToAutoUpgrade() {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$ScheduleV2Fragment$uF_Tox7CYYPWXE9_e0BGCEsiu0s
            @Override // java.lang.Runnable
            public final void run() {
                ScheduleV2Fragment.this.lambda$switchToAutoUpgrade$1$ScheduleV2Fragment();
            }
        });
    }

    public /* synthetic */ void lambda$switchToAutoUpgrade$1$ScheduleV2Fragment() {
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign == null) {
            promptExpiredAndGotoMain();
            return;
        }
        this.mTvSelectTime.setText(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_AUTO_UPGRADE_DESC));
        this.mTvSelectTime.setVisibility(0);
        this.mTvUpgradeTimeConsume.setText(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_AUTO_UPGRADE_INFO));
        this.mTvUpgradeTimeConsume.setVisibility(0);
        this.mIvScheduleUpgrade.setImageResource(R.mipmap.schedule_auto_upgrade);
        this.mIvScheduleUpgrade.setVisibility(0);
        this.mTvUpgradeTimePredict.setText(String.format(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_UPGRADE_TIME_PREDICT), getScheduleTimePredict(activeCampaign, TimeUtils.getHourAndMinute(ScheduleHelper.getInstance().getDefaultTime()))));
        this.mTvUpgradeTimePredict.setVisibility(0);
        this.mTpScheduleTime.setVisibility(8);
        this.mTvScheduleInfo.setVisibility(8);
        this.mBtnConfirm.setVisibility(8);
        this.mBtnUpgrade.setVisibility(8);
        this.mBtnModify.setVisibility(0);
        this.mBtnCancel.setVisibility(8);
    }

    private void switchToModifyTime(final Pair<Integer, Integer> pair) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$ScheduleV2Fragment$svwyOnA-yQFqKceDSq7S5MTzpK4
            @Override // java.lang.Runnable
            public final void run() {
                ScheduleV2Fragment.this.lambda$switchToModifyTime$2$ScheduleV2Fragment(pair);
            }
        });
    }

    public /* synthetic */ void lambda$switchToModifyTime$2$ScheduleV2Fragment(Pair pair) {
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign == null) {
            promptExpiredAndGotoMain();
            return;
        }
        if (VehicleFeature.isD55()) {
            this.mTvSelectTime.setText(String.format(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_TIME_FOR_UPDATE), getFormattedTime(pair)));
            this.mTvSelectTime.setVisibility(0);
            this.mTvUpgradeTimeConsume.setVisibility(8);
        } else {
            this.mTvSelectTime.setVisibility(8);
            this.mTvUpgradeTimeConsume.setText(getUpgradeTimeConsume(activeCampaign));
            this.mTvUpgradeTimeConsume.setVisibility(0);
        }
        this.mTpScheduleTime.setVisibility(8);
        this.mTvScheduleInfo.setVisibility(0);
        this.mIvScheduleUpgrade.setImageResource(R.mipmap.icon_schedule_upgrade);
        this.mIvScheduleUpgrade.setVisibility(0);
        this.mTvUpgradeTimePredict.setVisibility(0);
        this.mBtnConfirm.setVisibility(8);
        this.mBtnUpgrade.setVisibility(8);
        this.mBtnModify.setVisibility(0);
        this.mBtnCancel.setVisibility(0);
        this.mTvUpgradeTimePredict.setText(String.format(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_UPGRADE_TIME_PREDICT), getScheduleTimePredict(activeCampaign, pair)));
    }

    private String getUpgradeTimeConsume(Campaign campaign) {
        if (VehicleFeature.isD55()) {
            return String.format(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_TIME_CONSUME), Integer.valueOf(campaign.getEstimateCost()));
        }
        return String.valueOf(campaign.getEstimateCost());
    }

    private String getFormattedTime(Pair<Integer, Integer> pair) {
        return String.format("%02d:%02d", pair.first, pair.second);
    }

    private String getScheduleTimePredict(Campaign campaign, Pair<Integer, Integer> pair) {
        String format = String.format("%02d:%02d", pair.first, pair.second);
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, ((Integer) pair.first).intValue());
        calendar.set(12, ((Integer) pair.second).intValue());
        calendar.add(12, campaign.getEstimateCost());
        return String.format("%s-%s", format, String.format("%02d:%02d", Integer.valueOf(calendar.get(11)), Integer.valueOf(calendar.get(12))));
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        long activeCampaignId = OTAManager.getCampaignManager().getActiveCampaignId();
        if (ScheduleHelper.getInstance().isSetScheduleTime()) {
            EventPresenter.getInstance().sendModifyScheduleTimeEvent(activeCampaignId);
        } else {
            EventPresenter.getInstance().sendScheduleUpgradeEvent(activeCampaignId);
        }
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.btn_schedule_confirm) {
            if (OTAManager.getCampaignManager().getActiveCampaign() == null) {
                promptExpiredAndGotoMain();
                return;
            }
            final int intValue = this.mTpScheduleTime.getCurrentHour().intValue();
            final int intValue2 = this.mTpScheduleTime.getCurrentMinute().intValue();
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$ScheduleV2Fragment$OxtuDuP_cLb3P4yZUdNkHe0cXjM
                @Override // java.lang.Runnable
                public final void run() {
                    ScheduleV2Fragment.this.lambda$onClick$3$ScheduleV2Fragment(intValue, intValue2);
                }
            });
        } else if (view.getId() == R.id.btn_schedule_modify) {
            if (OTAManager.getCampaignManager().getActiveCampaign() == null) {
                promptExpiredAndGotoMain();
            } else {
                switchToConfirmTime();
            }
        } else if (view.getId() == R.id.btn_cancel_schedule) {
            if (OTAManager.getCampaignManager().getActiveCampaign() == null) {
                promptExpiredAndGotoMain();
            } else {
                ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$ScheduleV2Fragment$P18Pdn-tKjtqvHGsA501_U8xOzw
                    @Override // java.lang.Runnable
                    public final void run() {
                        ScheduleV2Fragment.this.lambda$onClick$4$ScheduleV2Fragment();
                    }
                });
            }
        } else if (view.getId() == R.id.btn_upgrade) {
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$ScheduleV2Fragment$VU70waNB4RCZW2DJS5B6Mh4dOqc
                @Override // java.lang.Runnable
                public final void run() {
                    ScheduleV2Fragment.this.lambda$onClick$5$ScheduleV2Fragment();
                }
            });
        }
    }

    public /* synthetic */ void lambda$onClick$3$ScheduleV2Fragment(int i, int i2) {
        ActivityHelper.scheduleUpgrade(i, i2, Constants.Schedule.FROM_USER);
        ((FragmentActivity) getActivity()).showFragment(CampaignFeatureHelper.getMainFragmentClass().getSimpleName(), null);
    }

    public /* synthetic */ void lambda$onClick$4$ScheduleV2Fragment() {
        ScheduleHelper.getInstance().cancelSchedule();
        ActivityHelper.cancelUserSchedule(Constants.Schedule.FROM_USER);
        ((FragmentActivity) getActivity()).showFragment(CampaignFeatureHelper.getMainFragmentClass().getSimpleName(), null);
    }

    public /* synthetic */ void lambda$onClick$5$ScheduleV2Fragment() {
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign != null) {
            EventPresenter.getInstance().sendUpgradeEvent(activeCampaign.getCampaignId());
            UpgradeFragment.resetCountDown();
            startFragment(CampaignFeatureHelper.getUpgradeFragmentClass());
            return;
        }
        promptExpiredAndGotoMain();
    }
}
