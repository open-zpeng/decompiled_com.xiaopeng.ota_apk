package com.xiaopeng.ota.activity;

import android.app.Activity;
import android.car.XpCarFeatures;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import com.xiaopeng.lib.utils.SystemPropertyUtil;
import com.xiaopeng.lib.utils.info.BuildInfoUtils;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.activity.info.InfoFragment;
import com.xiaopeng.ota.bean.Action;
import com.xiaopeng.ota.bean.ActivityEvent;
import com.xiaopeng.ota.helper.CampaignFeatureHelper;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.helper.DialogHelper;
import com.xiaopeng.ota.helper.ScheduleHelper;
import com.xiaopeng.ota.helper.UpgradeAnimHelper;
import com.xiaopeng.ota.presenter.event.EventPresenter;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.sdk.common.util.ArrayUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ResourceUtils;
import com.xiaopeng.ota.utils.StringUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.ota.utils.VersionUtils;
import com.xiaopeng.xui.utils.XTouchAreaUtils;
import com.xiaopeng.xui.widget.XButton;
import com.xiaopeng.xui.widget.XImageView;
import com.xiaopeng.xui.widget.XSwitch;
import com.xiaopeng.xui.widget.XTextView;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class MainFragment extends BaseFragment {
    private static final String TAG = "MainFragment";
    private XButton mBtnCancelSchedule;
    private XButton mBtnHistoryVersion;
    private XButton mBtnModifyTime;
    private XButton mBtnMoreInfo;
    private XButton mBtnNewVersion;
    private String mClickedSequences = "";
    private String mClickedSequencesMask = "122111";
    private Group mGroupScheduleSupported;
    private GridView mGvMainInfo;
    private XImageView mIvVehicle;
    private XImageView mIvVehicleBackground;
    private MainInfoAdapter mMainInfoAdapter;
    private XSwitch mSAutoUpgrade;
    private XTextView mTvCduFullVersion;
    private XTextView mTvScheduleInfo;
    private XTextView mTvSimpleVersion;
    private XTextView mTvVersionPrefix;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public String getFragmentName() {
        return TAG;
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void initViews(View view) {
        super.initViews(view);
        setTitle(ConfigHelper.getString(Constants.ConfigKey.TITLE_MAIN));
        this.mClickedSequencesMask = OTAManager.getConfigManager().getString(Constants.ConfigKey.VERSION_VIEW_CLICK_PASSWORD, this.mClickedSequencesMask);
        this.mIvVehicleBackground = (XImageView) view.findViewById(R.id.iv_vehicle_background);
        this.mIvVehicle = (XImageView) view.findViewById(R.id.iv_vehicle);
        this.mTvVersionPrefix = (XTextView) view.findViewById(R.id.tv_version_prefix);
        this.mTvVersionPrefix.setText(ConfigHelper.getString(Constants.ConfigKey.TEXT_VERSION_PREFIX));
        this.mTvSimpleVersion = (XTextView) view.findViewById(R.id.tv_simple_version);
        this.mTvSimpleVersion.setOnClickListener(this);
        this.mGvMainInfo = (GridView) view.findViewById(R.id.gv_main_info);
        this.mMainInfoAdapter = new MainInfoAdapter(getActivity(), R.layout.gridview_main_info_item);
        this.mGvMainInfo.setAdapter((ListAdapter) this.mMainInfoAdapter);
        this.mGvMainInfo.setEnabled(false);
        this.mTvCduFullVersion = (XTextView) view.findViewById(R.id.tv_cdu_full_version);
        this.mTvCduFullVersion.setOnClickListener(this);
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainFragment$siydk8-bEM-IqFHA9Hk23UHRAt4
            @Override // java.lang.Runnable
            public final void run() {
                MainFragment.this.lambda$initViews$0$MainFragment();
            }
        });
        this.mBtnHistoryVersion = (XButton) view.findViewById(R.id.btn_history_version);
        this.mBtnHistoryVersion.setText(ConfigHelper.getString(Constants.ConfigKey.TEXT_HISTORY_INFO));
        this.mBtnHistoryVersion.setOnClickListener(this);
        this.mBtnHistoryVersion.setVisibility(8);
        XButton xButton = this.mBtnHistoryVersion;
        XTouchAreaUtils.extendTouchArea(xButton, (ViewGroup) xButton.getParent(), new int[]{17, 26, 17, 46});
        this.mTvScheduleInfo = (XTextView) view.findViewById(R.id.tv_schedule_info);
        this.mBtnNewVersion = (XButton) view.findViewById(R.id.btn_new_version);
        this.mBtnNewVersion.setOnClickListener(this);
        XButton xButton2 = this.mBtnNewVersion;
        XTouchAreaUtils.extendTouchArea(xButton2, (ViewGroup) xButton2.getParent(), new int[]{16, 21, 16, 21});
        this.mSAutoUpgrade = (XSwitch) view.findViewById(R.id.s_auto_upgrade);
        this.mSAutoUpgrade.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainFragment$UcSr3hovqTCoY4aHgCIGi8bPUTo
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MainFragment.this.lambda$initViews$1$MainFragment(compoundButton, z);
            }
        });
        XButton xButton3 = this.mBtnNewVersion;
        XTouchAreaUtils.extendTouchArea(xButton3, (ViewGroup) xButton3.getParent(), new int[]{27, 31, 27, 31});
        if (OTAManager.getPreferencesManager().supportAutoUpgrade()) {
            this.mSAutoUpgrade.setChecked(true);
        } else {
            this.mSAutoUpgrade.setChecked(false);
        }
        this.mBtnModifyTime = (XButton) view.findViewById(R.id.btn_modify_time);
        XButton xButton4 = this.mBtnModifyTime;
        if (xButton4 != null) {
            xButton4.setOnClickListener(this);
        }
        this.mBtnCancelSchedule = (XButton) view.findViewById(R.id.btn_cancel_schedule);
        XButton xButton5 = this.mBtnCancelSchedule;
        if (xButton5 != null) {
            xButton5.setOnClickListener(this);
        }
        this.mGroupScheduleSupported = (Group) view.findViewById(R.id.group_schedule_supported);
    }

    public /* synthetic */ void lambda$initViews$0$MainFragment() {
        OTAManager.waitAvailable();
        final String currentOTAVersion = OTAManager.getCurrentOTAVersion();
        final String str = ResourceUtils.getString(R.string.main_cdu_full_version_prefix) + BuildInfoUtils.getFullSystemVersion();
        final ArrayList arrayList = new ArrayList();
        String vin = SystemPropertyUtil.getVIN();
        if (!TextUtils.isEmpty(vin)) {
            arrayList.add(new Pair(ResourceUtils.getString(R.string.main_vin_prefix), vin));
        } else {
            arrayList.add(new Pair(ResourceUtils.getString(R.string.main_vin_prefix), ResourceUtils.getString(R.string.main_unknown)));
        }
        arrayList.add(new Pair(ResourceUtils.getString(R.string.info_system_version), OTAManager.getCurrentCduVersion()));
        final int vehicleBodyColor = XpCarFeatures.getVehicleBodyColor();
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.MainFragment.1
            @Override // java.lang.Runnable
            public void run() {
                MainFragment.this.mTvSimpleVersion.setText(currentOTAVersion);
                MainFragment.this.mTvCduFullVersion.setText(str);
                VehicleImageUtils.setVehicleImage(MainFragment.this.mIvVehicle, vehicleBodyColor);
                MainFragment.this.mMainInfoAdapter.setData(arrayList);
                MainFragment.this.mMainInfoAdapter.notifyDataSetChanged();
            }
        });
    }

    public /* synthetic */ void lambda$initViews$1$MainFragment(CompoundButton compoundButton, boolean z) {
        changeAutoUpgrade(z);
    }

    private void changeAutoUpgrade(boolean z) {
        if (OTAManager.getPreferencesManager().supportAutoUpgrade() == z) {
            return;
        }
        EventPresenter.getInstance().sendAutoUpgradeClicked(!OTAManager.getPreferencesManager().supportAutoUpgrade());
        if (z) {
            this.mDialogHelper.promptAutoUpgrade(new DialogHelper.DialogListener() { // from class: com.xiaopeng.ota.activity.MainFragment.2
                @Override // com.xiaopeng.ota.helper.DialogHelper.DialogListener, com.xiaopeng.ota.helper.DialogHelper.DialogInterface
                public void onOkClick() {
                    EventPresenter.getInstance().sendOkClickedEvent("AutoUpgrade");
                }

                @Override // com.xiaopeng.ota.helper.DialogHelper.DialogListener, com.xiaopeng.ota.helper.DialogHelper.DialogInterface
                public void onCancelClick() {
                    MainFragment.this.mSAutoUpgrade.setChecked(false);
                    EventPresenter.getInstance().sendCancelClickedEvent("AutoUpgrade");
                }
            });
        } else {
            this.mDialogHelper.closeAutoUpgrade(new DialogHelper.DialogListener() { // from class: com.xiaopeng.ota.activity.MainFragment.3
                @Override // com.xiaopeng.ota.helper.DialogHelper.DialogListener, com.xiaopeng.ota.helper.DialogHelper.DialogInterface
                public void onOkClick() {
                    EventPresenter.getInstance().sendOkClickedEvent("AutoUpgrade");
                }

                @Override // com.xiaopeng.ota.helper.DialogHelper.DialogListener, com.xiaopeng.ota.helper.DialogHelper.DialogInterface
                public void onCancelClick() {
                    MainFragment.this.mSAutoUpgrade.setChecked(true);
                    EventPresenter.getInstance().sendCancelClickedEvent("AutoUpgrade");
                }
            });
        }
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void refreshData() {
        Campaign activeWaitingCampaign;
        super.refreshData();
        if (OTAManager.getCampaignManager().getActiveWaitingCampaign() != null) {
            this.mBtnNewVersion.setEnabled(true);
            this.mBtnNewVersion.setText(ConfigHelper.getString(Constants.ConfigKey.MAIN_NEW_VERSION_PREFIX) + " " + VersionUtils.getSimpleVersion(activeWaitingCampaign.getReleaseVersion()));
            this.mIvVehicleBackground.setImageResource(R.mipmap.vehicle_background_ota);
            UpgradeAnimHelper.showUpgradeAnim(getActivity());
        } else {
            this.mBtnNewVersion.setEnabled(false);
            this.mBtnNewVersion.setText(ConfigHelper.getString(Constants.ConfigKey.MAIN_NO_NEW));
            this.mIvVehicleBackground.setImageResource(R.mipmap.vehicle_background);
            UpgradeAnimHelper.hideUpgradeAnim(getActivity());
        }
        if (OTAManager.getPreferencesManager().supportAutoUpgrade()) {
            this.mSAutoUpgrade.setChecked(true);
        } else {
            this.mSAutoUpgrade.setChecked(false);
        }
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainFragment$Aq5dh7s1vHB0e37A1-S4zneTH1k
            @Override // java.lang.Runnable
            public final void run() {
                MainFragment.this.lambda$refreshData$3$MainFragment();
            }
        });
        if (!ConfigHelper.getBoolean(Constants.ConfigKey.SCHEDULE_ENABLE)) {
            this.mGroupScheduleSupported.setVisibility(8);
        } else if (ScheduleHelper.getInstance().isSetScheduleTime()) {
            this.mTvScheduleInfo.setText(StringUtils.format(Constants.ConfigKey.MAIN_SCHEDULE_TIME_FORMAT, ScheduleHelper.getInstance().getScheduleTime()));
            this.mTvScheduleInfo.setVisibility(0);
        } else {
            this.mTvScheduleInfo.setVisibility(8);
        }
    }

    public /* synthetic */ void lambda$refreshData$3$MainFragment() {
        final List<Campaign> allHistoryCampaign = OTAManager.getCampaignManager().getAllHistoryCampaign();
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainFragment$zC5fBDGMFoOqOpDacKq5utXQXw0
            @Override // java.lang.Runnable
            public final void run() {
                MainFragment.this.lambda$null$2$MainFragment(allHistoryCampaign);
            }
        });
    }

    public /* synthetic */ void lambda$null$2$MainFragment(List list) {
        if (ArrayUtils.isEmpty(list)) {
            this.mBtnHistoryVersion.setVisibility(8);
        } else {
            this.mBtnHistoryVersion.setVisibility(0);
        }
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void onHandleEvent(ActivityEvent activityEvent) {
        super.lambda$onEvent$0$BaseFragment(activityEvent);
        int i = AnonymousClass4.$SwitchMap$com$xiaopeng$ota$bean$Action[activityEvent.getAction().ordinal()];
        if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
            refreshData();
        }
    }

    /* renamed from: com.xiaopeng.ota.activity.MainFragment$4  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaopeng$ota$bean$Action = new int[Action.values().length];

        static {
            try {
                $SwitchMap$com$xiaopeng$ota$bean$Action[Action.SCHEDULE_SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$xiaopeng$ota$bean$Action[Action.CANCEL_SCHEDULE_SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$xiaopeng$ota$bean$Action[Action.CAMPAIGN_READY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$xiaopeng$ota$bean$Action[Action.AUTO_UPGRADE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$xiaopeng$ota$bean$Action[Action.CAMPAIGN_CANCEL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.tv_simple_version) {
            this.mClickedSequences += "1";
            if (this.mClickedSequences.equals(this.mClickedSequencesMask)) {
                ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainFragment$iC5FYWmBedMa88KhH84dTUHQYc0
                    @Override // java.lang.Runnable
                    public final void run() {
                        MainFragment.this.lambda$onClick$4$MainFragment();
                    }
                });
                this.mClickedSequences = "";
            } else if (this.mClickedSequencesMask.startsWith(this.mClickedSequences)) {
            } else {
                this.mClickedSequences = "";
            }
        } else if (view.getId() == R.id.tv_cdu_full_version) {
            this.mClickedSequences += "2";
            if (this.mClickedSequencesMask.startsWith(this.mClickedSequences)) {
                return;
            }
            this.mClickedSequences = "";
        } else if (view.getId() == R.id.btn_history_version) {
            this.mClickedSequences = "";
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainFragment$F2HwoGCcU4VwBarI9yN7fcTs6yg
                @Override // java.lang.Runnable
                public final void run() {
                    MainFragment.this.lambda$onClick$5$MainFragment();
                }
            });
        } else if (view.getId() == R.id.btn_new_version) {
            final Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
            if (activeCampaign == null) {
                promptExpiredAndGotoMain();
                return;
            }
            this.mClickedSequences = "";
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainFragment$UH8u1NoyCIotLO34HMn-Oo2K3uI
                @Override // java.lang.Runnable
                public final void run() {
                    MainFragment.this.lambda$onClick$6$MainFragment(activeCampaign);
                }
            });
        } else if (view.getId() == R.id.btn_modify_time) {
            if (OTAManager.getCampaignManager().getActiveCampaign() == null) {
                promptExpiredAndGotoMain();
                return;
            }
            this.mClickedSequences = "";
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainFragment$8V4_6i5NyuQxyG00fz249wH1OzE
                @Override // java.lang.Runnable
                public final void run() {
                    MainFragment.this.lambda$onClick$7$MainFragment();
                }
            });
        } else {
            this.mClickedSequences = "";
        }
    }

    public /* synthetic */ void lambda$onClick$4$MainFragment() {
        startFragment(InfoFragment.class);
    }

    public /* synthetic */ void lambda$onClick$5$MainFragment() {
        startFragment(HistoryFragment.class);
    }

    public /* synthetic */ void lambda$onClick$6$MainFragment(Campaign campaign) {
        startFragment(CampaignFeatureHelper.getNewFragmentClass(campaign));
    }

    public /* synthetic */ void lambda$onClick$7$MainFragment() {
        startFragment(CampaignFeatureHelper.getScheduleFragmentClass());
    }

    /* loaded from: classes2.dex */
    public static class MainInfoAdapter extends ArrayAdapter<Pair<String, String>> {
        private Context mContext;
        private List<Pair<String, String>> mDataSet;

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public MainInfoAdapter(@NonNull Context context, int i) {
            super(context, i);
            this.mContext = context;
            this.mDataSet = new ArrayList();
        }

        public void setData(List<Pair<String, String>> list) {
            if (ArrayUtils.isEmpty(list)) {
                LogUtils.w(MainFragment.TAG, "MainInfo data set is empty");
                return;
            }
            this.mDataSet.clear();
            this.mDataSet.addAll(list);
            notifyDataSetChanged();
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public int getCount() {
            return this.mDataSet.size();
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public Pair<String, String> getItem(int i) {
            return this.mDataSet.get(i);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = ((Activity) this.mContext).getLayoutInflater().inflate(R.layout.gridview_main_info_item, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.tvName = (XTextView) view.findViewById(R.id.tv_name);
                viewHolder.tvValue = (XTextView) view.findViewById(R.id.tv_value);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Pair<String, String> pair = this.mDataSet.get(i);
            viewHolder.tvName.setText((CharSequence) pair.first);
            viewHolder.tvValue.setText((CharSequence) pair.second);
            return view;
        }

        /* loaded from: classes2.dex */
        private class ViewHolder {
            private XTextView tvName;
            private XTextView tvValue;

            private ViewHolder() {
            }
        }
    }
}
