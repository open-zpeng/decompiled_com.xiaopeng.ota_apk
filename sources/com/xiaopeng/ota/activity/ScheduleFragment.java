package com.xiaopeng.ota.activity;

import android.util.Pair;
import android.view.View;
import com.xiaopeng.lib.utils.view.ToastUtils;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.helper.ActivityHelper;
import com.xiaopeng.ota.helper.CampaignFeatureHelper;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.helper.ScheduleHelper;
import com.xiaopeng.ota.presenter.event.EventPresenter;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.ota.utils.TimeUtils;
import com.xiaopeng.xui.widget.XButton;
import com.xiaopeng.xui.widget.XTextView;
import com.xiaopeng.xui.widget.timepicker.XTimePicker;
/* loaded from: classes2.dex */
public class ScheduleFragment extends BaseFragment {
    private static final String TAG = "ScheduleFragment";
    private XButton mBtnCancel;
    private XButton mBtnConfirm;
    private XTimePicker mTpScheduleTime;
    private XTextView mTvDesc;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public String getFragmentName() {
        return TAG;
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    protected int getLayoutId() {
        return R.layout.fragment_schedule;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void initViews(View view) {
        super.initViews(view);
        setTitle(ConfigHelper.getString(Constants.ConfigKey.TITLE_SCHEDULE));
        this.mTvDesc = (XTextView) view.findViewById(R.id.tv_desc);
        this.mTvDesc.setText(ConfigHelper.getString(Constants.ConfigKey.SCHEDULE_UPGRADE_DESC));
        this.mTpScheduleTime = (XTimePicker) view.findViewById(R.id.tp_schedule_time);
        this.mBtnConfirm = (XButton) view.findViewById(R.id.btn_schedule);
        this.mBtnConfirm.setOnClickListener(this);
        this.mBtnCancel = (XButton) view.findViewById(R.id.btn_cancel_schedule);
        this.mBtnCancel.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void refreshData() {
        super.refreshData();
        Pair<Integer, Integer> hourAndMinute = TimeUtils.getHourAndMinute(ScheduleHelper.getInstance().getScheduleTime());
        if (hourAndMinute == null && (hourAndMinute = TimeUtils.getHourAndMinute(ScheduleHelper.getInstance().getDefaultTime())) == null) {
            LogUtils.e(TAG, "Get schedule time fail");
            return;
        }
        this.mTpScheduleTime.setCurrentHour((Integer) hourAndMinute.first);
        this.mTpScheduleTime.setCurrentMinute((Integer) hourAndMinute.second);
        if (ScheduleHelper.getInstance().isSetScheduleTime()) {
            this.mBtnCancel.setVisibility(0);
        } else {
            this.mBtnCancel.setVisibility(8);
        }
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
        if (view.getId() == R.id.btn_schedule) {
            final Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
            if (activeCampaign == null) {
                promptExpiredAndGotoMain();
                return;
            }
            final int intValue = this.mTpScheduleTime.getCurrentHour().intValue();
            final int intValue2 = this.mTpScheduleTime.getCurrentMinute().intValue();
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$ScheduleFragment$cCOfoa14dDjPnztg04XPNpPiidk
                @Override // java.lang.Runnable
                public final void run() {
                    ScheduleFragment.this.lambda$onClick$0$ScheduleFragment(intValue, intValue2, activeCampaign);
                }
            });
        } else if (view.getId() == R.id.btn_cancel_schedule) {
            if (OTAManager.getCampaignManager().getActiveCampaign() == null) {
                promptExpiredAndGotoMain();
                return;
            }
            ScheduleHelper.getInstance().cancelSchedule();
            ActivityHelper.cancelUserSchedule(Constants.Schedule.FROM_USER);
            refreshData();
            ToastUtils.showToast(getContext(), R.string.main_schedule_cancel_tip);
            lambda$onClick$1$BaseFragment();
        }
    }

    public /* synthetic */ void lambda$onClick$0$ScheduleFragment(int i, int i2, Campaign campaign) {
        ActivityHelper.scheduleUpgrade(i, i2, Constants.Schedule.FROM_USER);
        ((FragmentActivity) getActivity()).showFragment(getNewFragmentClass(campaign).getSimpleName(), CampaignFeatureHelper.getMainFragmentClass());
    }
}
