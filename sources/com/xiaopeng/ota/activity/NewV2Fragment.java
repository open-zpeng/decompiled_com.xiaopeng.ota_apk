package com.xiaopeng.ota.activity;

import android.content.res.Configuration;
import android.util.Pair;
import android.view.View;
import android.webkit.WebView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.xiaopeng.libtheme.ThemeManager;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.VehicleFeature;
import com.xiaopeng.ota.activity.VersionTitleAdapter;
import com.xiaopeng.ota.activity.WebViewVersionFragment;
import com.xiaopeng.ota.helper.ActivityHelper;
import com.xiaopeng.ota.helper.CampaignFeatureHelper;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.helper.ScheduleHelper;
import com.xiaopeng.ota.presenter.event.EventPresenter;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.presenter.update.bean.Feature;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.StringUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.ota.utils.TimeUtils;
import com.xiaopeng.ota.utils.VersionUtils;
import com.xiaopeng.xui.widget.XButton;
import com.xiaopeng.xui.widget.XImageButton;
import com.xiaopeng.xui.widget.XImageView;
import com.xiaopeng.xui.widget.XTextView;
import java.util.List;
/* loaded from: classes2.dex */
public class NewV2Fragment extends WebViewVersionFragment {
    private static final String TAG = "NewV2Fragment";
    private static final int TYPE_MODIFY_TIME = 1;
    private static final int TYPE_SCHEDULE = 0;
    private static final int TYPE_UPGRADE_LATER = 2;
    private XButton mBtnSchedule;
    private XButton mBtnUpgrade;
    private int mCheckedIndex = 0;
    private XImageButton mIbBack;
    private XImageView mIvNewTitle;
    private RecyclerView mRecyclerViewTitle;
    private int mTitleSize;
    private XTextView mTvScheduleTime;
    private XTextView mTvVersion;
    private VersionTitleAdapter mVersionTitleAdapter;
    private WebView mWvContent;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public String getFragmentName() {
        return TAG;
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    protected int getLayoutId() {
        return R.layout.fragment_new_v2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void initViews(View view) {
        super.initViews(view);
        OTAManager.waitAvailable();
        this.mIbBack = (XImageButton) view.findViewById(R.id.title_ib_close);
        this.mTvVersion = (XTextView) view.findViewById(R.id.title_tv_version);
        this.mRecyclerViewTitle = (RecyclerView) view.findViewById(R.id.rv_version_title);
        this.mWvContent = (WebView) view.findViewById(R.id.wv_content);
        this.mIbBack.setOnClickListener(this);
        this.mIvNewTitle = (XImageView) view.findViewById(R.id.iv_title_new);
        this.mBtnSchedule = (XButton) view.findViewById(R.id.btn_schedule);
        this.mBtnSchedule.setOnClickListener(this);
        this.mTvScheduleTime = (XTextView) view.findViewById(R.id.tv_schedule_time);
        this.mBtnUpgrade = (XButton) view.findViewById(R.id.btn_upgrade);
        this.mBtnUpgrade.setOnClickListener(this);
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        List<Feature> features = activeCampaign != null ? activeCampaign.getFeatures() : null;
        setTitleIndexChangedListener(new WebViewVersionFragment.TitleIndexChangedListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$NewV2Fragment$40UYGZLXSmP9FHQuN8OvpSGzPG8
            @Override // com.xiaopeng.ota.activity.WebViewVersionFragment.TitleIndexChangedListener
            public final void onChanged(int i) {
                NewV2Fragment.this.lambda$initViews$1$NewV2Fragment(i);
            }
        });
        init(1, this.mWvContent, features);
    }

    public /* synthetic */ void lambda$initViews$1$NewV2Fragment(final int i) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$NewV2Fragment$9LDducEPUmaA3EN3o0v3Ku6xmew
            @Override // java.lang.Runnable
            public final void run() {
                NewV2Fragment.this.lambda$null$0$NewV2Fragment(i);
            }
        });
    }

    public /* synthetic */ void lambda$null$0$NewV2Fragment(int i) {
        setChecked(i, true);
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void refreshData() {
        super.refreshData();
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign == null) {
            lambda$onClick$1$BaseFragment();
            return;
        }
        String carType = VehicleFeature.getCarType();
        char c = 65535;
        if (carType.hashCode() == 67044 && carType.equals("D55")) {
            c = 0;
        }
        if (c == 0) {
            setTitle(String.format("%s %s", VersionUtils.getSimpleVersion(activeCampaign.getReleaseVersion()), ConfigHelper.getString(Constants.ConfigKey.NEW_V2_INTRODUCTION)));
            this.mIvNewTitle.setVisibility(0);
        } else {
            this.mTvVersion.setText(VersionUtils.getSimpleVersion(activeCampaign.getReleaseVersion()));
            this.mIvNewTitle.setVisibility(8);
        }
        this.mRecyclerViewTitle.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mVersionTitleAdapter = new VersionTitleAdapter(getContext());
        this.mRecyclerViewTitle.setAdapter(this.mVersionTitleAdapter);
        List<VersionTitle> versionTitle = CampaignFeatureHelper.getVersionTitle(activeCampaign.getFeatures());
        this.mTitleSize = versionTitle.size();
        this.mVersionTitleAdapter.setData(versionTitle);
        this.mVersionTitleAdapter.setOnItemClickedListener(new VersionTitleAdapter.OnItemClickedListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$NewV2Fragment$RgQy50Gmy60Um9zWLAyCr9Zht7A
            @Override // com.xiaopeng.ota.activity.VersionTitleAdapter.OnItemClickedListener
            public final void onItemClicked(int i) {
                NewV2Fragment.this.lambda$refreshData$2$NewV2Fragment(i);
            }
        });
        if (versionTitle != null && !versionTitle.isEmpty()) {
            showAllInWebView(getContext(), this.mWvContent, versionTitle);
            setChecked(0);
        }
        refreshScheduleButton();
    }

    public /* synthetic */ void lambda$refreshData$2$NewV2Fragment(int i) {
        if (this.mCheckedIndex == i) {
            return;
        }
        setChecked(i);
        showCheckedContent(this.mWvContent, i);
    }

    void setChecked(int i) {
        setChecked(i, false);
    }

    void setChecked(int i, boolean z) {
        if (z || this.mCheckedIndex != i) {
            if (i >= this.mTitleSize) {
                LogUtils.d(TAG, "index out of range, index:%d, size:%d", Integer.valueOf(i), Integer.valueOf(this.mTitleSize));
                return;
            }
            this.mCheckedIndex = i;
            this.mRecyclerViewTitle.scrollToPosition(i);
            if (z) {
                this.mVersionTitleAdapter.setItemChanged(i);
            }
            this.mVersionTitleAdapter.notifyDataSetChanged();
        }
    }

    private void refreshScheduleButton() {
        String format;
        Campaign activeWaitingCampaign = OTAManager.getCampaignManager().getActiveWaitingCampaign();
        if (activeWaitingCampaign == null) {
            this.mBtnSchedule.setTag(2);
            this.mBtnSchedule.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_UPGRADE_LATER));
            this.mBtnUpgrade.setEnabled(false);
            this.mTvScheduleTime.setVisibility(8);
            return;
        }
        if (!supportSchedule(activeWaitingCampaign)) {
            this.mBtnSchedule.setTag(2);
            this.mBtnSchedule.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_UPGRADE_LATER));
            this.mTvScheduleTime.setVisibility(8);
        } else if (ScheduleHelper.getInstance().isSetScheduleTime()) {
            this.mBtnSchedule.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_CHANGE_SCHEDULE));
            this.mBtnSchedule.setTag(1);
            this.mTvScheduleTime.setVisibility(0);
            this.mTvScheduleTime.setText(StringUtils.format(Constants.ConfigKey.NEW_SCHEDULE_TIME_FORMAT, ScheduleHelper.getInstance().getScheduleTime()));
        } else {
            if (VehicleFeature.isCarTypeD55()) {
                format = ConfigHelper.getString(Constants.ConfigKey.NEW_V2_BUTTON_UPGRADE);
            } else {
                format = StringUtils.format(Constants.ConfigKey.BUTTON_SCHEDULE_FORMAT, ScheduleHelper.getInstance().getDefaultTime());
            }
            this.mBtnSchedule.setText(format);
            this.mBtnSchedule.setTag(0);
            this.mTvScheduleTime.setVisibility(8);
        }
        this.mBtnUpgrade.setEnabled(true);
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (ThemeManager.isThemeChanged(configuration)) {
            refreshWebViewTheme();
        }
    }

    private void refreshWebViewTheme() {
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign != null) {
            showAllInWebView(getContext(), this.mWvContent, CampaignFeatureHelper.getVersionTitle(activeCampaign.getFeatures()));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        WebView webView = this.mWvContent;
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.btn_schedule) {
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$NewV2Fragment$YZJJ5OYTpWi2x-u2kOS_WT02Ltc
                @Override // java.lang.Runnable
                public final void run() {
                    NewV2Fragment.this.lambda$onClick$3$NewV2Fragment();
                }
            });
        } else if (view.getId() != R.id.btn_upgrade || VehicleFeature.isCarTypeD55()) {
        } else {
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$NewV2Fragment$OtrpRndcLSa-enKZIYBrMp0HfLs
                @Override // java.lang.Runnable
                public final void run() {
                    NewV2Fragment.this.lambda$onClick$4$NewV2Fragment();
                }
            });
        }
    }

    public /* synthetic */ void lambda$onClick$3$NewV2Fragment() {
        Integer num = (Integer) this.mBtnSchedule.getTag();
        if (num == null) {
            lambda$onClick$1$BaseFragment();
            return;
        }
        int intValue = num.intValue();
        if (intValue != 0) {
            if (intValue == 1) {
                startFragment(CampaignFeatureHelper.getScheduleFragmentClass());
            } else if (intValue != 2) {
            } else {
                lambda$onClick$1$BaseFragment();
            }
        } else if (VehicleFeature.isCarTypeD55()) {
            startFragment(CampaignFeatureHelper.getScheduleFragmentClass());
        } else {
            Pair<Integer, Integer> hourAndMinute = TimeUtils.getHourAndMinute(ScheduleHelper.getInstance().getDefaultTime());
            if (hourAndMinute != null) {
                ActivityHelper.scheduleUpgrade(((Integer) hourAndMinute.first).intValue(), ((Integer) hourAndMinute.second).intValue(), Constants.Schedule.FROM_USER);
            }
            startFragment(CampaignFeatureHelper.getMainFragmentClass(), null);
        }
    }

    public /* synthetic */ void lambda$onClick$4$NewV2Fragment() {
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
