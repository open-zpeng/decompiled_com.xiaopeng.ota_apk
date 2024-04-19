package com.xiaopeng.ota.activity;

import android.app.Activity;
import android.car.XpCarFeatures;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
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
import com.android.internal.util.ArrayUtils;
import com.xiaopeng.lib.utils.SystemPropertyUtil;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.VehicleFeature;
import com.xiaopeng.ota.activity.MainV2Fragment;
import com.xiaopeng.ota.bean.ActivityEvent;
import com.xiaopeng.ota.helper.CampaignFeatureHelper;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.helper.DialogHelper;
import com.xiaopeng.ota.helper.ScheduleHelper;
import com.xiaopeng.ota.helper.UpgradeAnimHelper;
import com.xiaopeng.ota.presenter.event.EventPresenter;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ResourceUtils;
import com.xiaopeng.ota.utils.StringUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.ota.utils.VersionUtils;
import com.xiaopeng.ota.version.os.OsVersion;
import com.xiaopeng.xui.utils.XTouchAreaUtils;
import com.xiaopeng.xui.widget.XButton;
import com.xiaopeng.xui.widget.XConstraintLayout;
import com.xiaopeng.xui.widget.XImageView;
import com.xiaopeng.xui.widget.XLoading;
import com.xiaopeng.xui.widget.XSwitch;
import com.xiaopeng.xui.widget.XTextView;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class MainV2Fragment extends BaseFragment {
    private static final String TAG = "MainV2Fragment";
    private XButton mBtnNewVersion;
    private XButton mBtnSyncOsVersion;
    private XButton mBtnViewVersionDetail;
    private XButton mBtnViewVersionDetailNew;
    private GridView mGvMainInfo;
    private XImageView mIvVehicle;
    private XImageView mIvVehicleBackground;
    private XLoading mLoadingSyncOsVersion;
    private MainInfoAdapter mMainInfoAdapter;
    private XConstraintLayout mNewVersionLayout;
    private XSwitch mSAutoUpgrade;
    private XTextView mTvFindNewVersion;
    private XTextView mTvNewVersion;
    private XTextView mTvNewVersionTips;
    private XTextView mTvSimpleVersion;
    private XTextView mTvVersionPrefix;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public String getFragmentName() {
        return TAG;
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    protected int getLayoutId() {
        return R.layout.fragment_main_v2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void initViews(View view) {
        super.initViews(view);
        setTitle(ConfigHelper.getString(Constants.ConfigKey.TITLE_MAIN));
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
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$V8FXw2_Bb6hNHVxd1mlzTFxgbCE
            @Override // java.lang.Runnable
            public final void run() {
                MainV2Fragment.this.lambda$initViews$1$MainV2Fragment();
            }
        });
        this.mTvVersionPrefix.setOnClickListener(new AnonymousClass1());
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$v4EnRyi-axzYaanRMqqXwmvTXU4
            @Override // java.lang.Runnable
            public final void run() {
                MainV2Fragment.this.lambda$initViews$3$MainV2Fragment();
            }
        });
        if (VehicleFeature.isD55()) {
            this.mNewVersionLayout = (XConstraintLayout) view.findViewById(R.id.layout_new_version);
            this.mTvFindNewVersion = (XTextView) view.findViewById(R.id.tv_find_new_version);
            this.mTvFindNewVersion.setText(ConfigHelper.getString(Constants.ConfigKey.MAIN_FIND_NEW_VERSION));
            this.mTvNewVersion = (XTextView) view.findViewById(R.id.tv_new_version);
            this.mBtnViewVersionDetailNew = (XButton) view.findViewById(R.id.btn_view_version_detail_new);
            this.mBtnViewVersionDetailNew.setText(ConfigHelper.getString(Constants.ConfigKey.MAIN_BUTTON_VIEW_VERSION_DETAIL_NEW));
            this.mBtnViewVersionDetailNew.setOnClickListener(this);
        }
        this.mBtnNewVersion = (XButton) view.findViewById(R.id.btn_new_version);
        this.mBtnNewVersion.setOnClickListener(this);
        XButton xButton = this.mBtnNewVersion;
        XTouchAreaUtils.extendTouchArea(xButton, (ViewGroup) xButton.getParent(), new int[]{16, 21, 16, 21});
        this.mTvNewVersionTips = (XTextView) view.findViewById(R.id.tv_new_version_tips);
        this.mBtnViewVersionDetail = (XButton) view.findViewById(R.id.btn_view_version_detail);
        this.mBtnViewVersionDetail.setText(ConfigHelper.getString(Constants.ConfigKey.MAIN_BUTTON_VIEW_VERSION_DETAIL));
        this.mBtnViewVersionDetail.setOnClickListener(this);
        this.mBtnSyncOsVersion = (XButton) view.findViewById(R.id.btn_sync_os_version);
        this.mBtnSyncOsVersion.setText(ConfigHelper.getString(Constants.ConfigKey.MAIN_BUTTON_SYNC_OS_VERSION));
        this.mBtnSyncOsVersion.setOnClickListener(this);
        this.mLoadingSyncOsVersion = (XLoading) view.findViewById(R.id.loading_sync_os_version);
        this.mSAutoUpgrade = (XSwitch) view.findViewById(R.id.s_auto_upgrade);
        this.mSAutoUpgrade.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$K0-LzuuDikwBcmpNUwxrtNatO6M
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MainV2Fragment.this.lambda$initViews$4$MainV2Fragment(compoundButton, z);
            }
        });
        XButton xButton2 = this.mBtnNewVersion;
        XTouchAreaUtils.extendTouchArea(xButton2, (ViewGroup) xButton2.getParent(), new int[]{27, 31, 27, 31});
        if (OTAManager.getPreferencesManager().supportAutoUpgrade()) {
            this.mSAutoUpgrade.setChecked(true);
        } else {
            this.mSAutoUpgrade.setChecked(false);
        }
    }

    public /* synthetic */ void lambda$initViews$1$MainV2Fragment() {
        final List<Pair<String, String>> mainInfoData = getMainInfoData(OTAManager.getCurrentCduVersion());
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$rCaLDobQiq5V53bCsML6rOfqKHw
            @Override // java.lang.Runnable
            public final void run() {
                MainV2Fragment.this.lambda$null$0$MainV2Fragment(mainInfoData);
            }
        });
    }

    public /* synthetic */ void lambda$null$0$MainV2Fragment(List list) {
        this.mMainInfoAdapter.setData(list);
        this.mMainInfoAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaopeng.ota.activity.MainV2Fragment$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 implements View.OnClickListener {
        static final int COUNTS = 5;
        static final long DURATION = 2000;
        long[] mHits = new long[5];

        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            long[] jArr = this.mHits;
            System.arraycopy(jArr, 1, jArr, 0, jArr.length - 1);
            long[] jArr2 = this.mHits;
            jArr2[jArr2.length - 1] = SystemClock.uptimeMillis();
            if (SystemClock.uptimeMillis() - this.mHits[0] <= DURATION) {
                final List mainInfoData = MainV2Fragment.this.getMainInfoData(OTAManager.getCurrentCduVersionBuildTime());
                ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$1$XXNt2Tu3zeIiIFZ5HhC9gPBvhzo
                    @Override // java.lang.Runnable
                    public final void run() {
                        MainV2Fragment.AnonymousClass1.this.lambda$onClick$0$MainV2Fragment$1(mainInfoData);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onClick$0$MainV2Fragment$1(List list) {
            MainV2Fragment.this.mTvVersionPrefix.setOnClickListener(null);
            MainV2Fragment.this.mMainInfoAdapter.setData(list);
            MainV2Fragment.this.mMainInfoAdapter.notifyDataSetChanged();
        }
    }

    public /* synthetic */ void lambda$initViews$3$MainV2Fragment() {
        OTAManager.waitAvailable();
        final String currentOTAVersion = OTAManager.getCurrentOTAVersion();
        final int vehicleBodyColor = XpCarFeatures.getVehicleBodyColor();
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$bGHdyjnmhgyQXo-a09Vy0wd4USQ
            @Override // java.lang.Runnable
            public final void run() {
                MainV2Fragment.this.lambda$null$2$MainV2Fragment(currentOTAVersion, vehicleBodyColor);
            }
        });
    }

    public /* synthetic */ void lambda$null$2$MainV2Fragment(String str, int i) {
        this.mTvSimpleVersion.setText(str);
        VehicleImageUtils.setVehicleImage(this.mIvVehicle, i);
    }

    public /* synthetic */ void lambda$initViews$4$MainV2Fragment(CompoundButton compoundButton, boolean z) {
        changeAutoUpgrade(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Pair<String, String>> getMainInfoData(String str) {
        ArrayList arrayList = new ArrayList();
        String vin = SystemPropertyUtil.getVIN();
        if (!TextUtils.isEmpty(vin)) {
            arrayList.add(new Pair(ResourceUtils.getString(R.string.main_vin_prefix), vin));
        } else {
            arrayList.add(new Pair(ResourceUtils.getString(R.string.main_vin_prefix), ResourceUtils.getString(R.string.main_unknown)));
        }
        arrayList.add(new Pair(ResourceUtils.getString(R.string.info_system_version), str));
        return arrayList;
    }

    private void changeAutoUpgrade(boolean z) {
        if (OTAManager.getPreferencesManager().supportAutoUpgrade() == z) {
            return;
        }
        EventPresenter.getInstance().sendAutoUpgradeClicked(!OTAManager.getPreferencesManager().supportAutoUpgrade());
        if (z) {
            this.mDialogHelper.promptAutoUpgrade(new AnonymousClass2());
        } else {
            this.mDialogHelper.closeAutoUpgrade(new AnonymousClass3());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaopeng.ota.activity.MainV2Fragment$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass2 extends DialogHelper.DialogListener {
        AnonymousClass2() {
        }

        @Override // com.xiaopeng.ota.helper.DialogHelper.DialogListener, com.xiaopeng.ota.helper.DialogHelper.DialogInterface
        public void onOkClick() {
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$2$LY-U-Yoe2tZN0Ui1i8SKhKH67NI
                @Override // java.lang.Runnable
                public final void run() {
                    MainV2Fragment.AnonymousClass2.this.lambda$onOkClick$0$MainV2Fragment$2();
                }
            });
            EventPresenter.getInstance().sendOkClickedEvent("AutoUpgrade");
        }

        public /* synthetic */ void lambda$onOkClick$0$MainV2Fragment$2() {
            MainV2Fragment.this.mSAutoUpgrade.setChecked(true);
        }

        @Override // com.xiaopeng.ota.helper.DialogHelper.DialogListener, com.xiaopeng.ota.helper.DialogHelper.DialogInterface
        public void onCancelClick() {
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$2$QnL1sOPtjmgkF28kTAIdTzzvhOk
                @Override // java.lang.Runnable
                public final void run() {
                    MainV2Fragment.AnonymousClass2.this.lambda$onCancelClick$1$MainV2Fragment$2();
                }
            });
            EventPresenter.getInstance().sendCancelClickedEvent("AutoUpgrade");
        }

        public /* synthetic */ void lambda$onCancelClick$1$MainV2Fragment$2() {
            MainV2Fragment.this.mSAutoUpgrade.setChecked(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaopeng.ota.activity.MainV2Fragment$3  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass3 extends DialogHelper.DialogListener {
        AnonymousClass3() {
        }

        @Override // com.xiaopeng.ota.helper.DialogHelper.DialogListener, com.xiaopeng.ota.helper.DialogHelper.DialogInterface
        public void onOkClick() {
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$3$QlX-PgAmeryMZ5lv_G1OKlKPKMM
                @Override // java.lang.Runnable
                public final void run() {
                    MainV2Fragment.AnonymousClass3.this.lambda$onOkClick$0$MainV2Fragment$3();
                }
            });
            EventPresenter.getInstance().sendOkClickedEvent("AutoUpgrade");
        }

        public /* synthetic */ void lambda$onOkClick$0$MainV2Fragment$3() {
            MainV2Fragment.this.mSAutoUpgrade.setChecked(false);
        }

        @Override // com.xiaopeng.ota.helper.DialogHelper.DialogListener, com.xiaopeng.ota.helper.DialogHelper.DialogInterface
        public void onCancelClick() {
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$3$qRVATRGQEBhWq3GaZVoC2wePtoE
                @Override // java.lang.Runnable
                public final void run() {
                    MainV2Fragment.AnonymousClass3.this.lambda$onCancelClick$1$MainV2Fragment$3();
                }
            });
            EventPresenter.getInstance().sendCancelClickedEvent("AutoUpgrade");
        }

        public /* synthetic */ void lambda$onCancelClick$1$MainV2Fragment$3() {
            MainV2Fragment.this.mSAutoUpgrade.setChecked(true);
        }
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    /* renamed from: refreshData */
    public void lambda$onHandleEvent$5$MainV2Fragment() {
        String str;
        super.refreshData();
        Campaign activeWaitingCampaign = OTAManager.getCampaignManager().getActiveWaitingCampaign();
        if (activeWaitingCampaign != null) {
            if (VehicleFeature.isD55()) {
                this.mTvNewVersionTips.setVisibility(8);
                this.mNewVersionLayout.setVisibility(0);
                this.mTvNewVersion.setText(VersionUtils.getSimpleVersion(activeWaitingCampaign.getReleaseVersion()));
                if (ScheduleHelper.getInstance().isSetScheduleTime()) {
                    str = StringUtils.format(Constants.ConfigKey.MAIN_BUTTON_HAS_SCHEDULED_WITH_TIME, ScheduleHelper.getInstance().getScheduleTime());
                } else if (OTAManager.getPreferencesManager().supportAutoUpgrade()) {
                    str = ConfigHelper.getString(Constants.ConfigKey.MAIN_BUTTON_UPGRADE_AUTO);
                } else {
                    str = ConfigHelper.getString(Constants.ConfigKey.MAIN_BUTTON_UPGRADE_TO_NEW_VERSION);
                }
            } else {
                str = ConfigHelper.getString(Constants.ConfigKey.MAIN_NEW_VERSION_PREFIX) + " " + VersionUtils.getSimpleVersion(activeWaitingCampaign.getReleaseVersion());
                this.mTvNewVersionTips.setVisibility(0);
                if (!ConfigHelper.getBoolean(Constants.ConfigKey.SCHEDULE_ENABLE)) {
                    this.mTvNewVersionTips.setText(ConfigHelper.getString(Constants.ConfigKey.MAIN_NEW_VERSION_TIPS));
                    return;
                } else if (ScheduleHelper.getInstance().isSetScheduleTime()) {
                    this.mTvNewVersionTips.setText(StringUtils.format(Constants.ConfigKey.MAIN_SCHEDULE_TIME_FORMAT, ScheduleHelper.getInstance().getScheduleTime()));
                } else {
                    this.mTvNewVersionTips.setText(ConfigHelper.getString(Constants.ConfigKey.MAIN_NEW_VERSION_TIPS));
                }
            }
            this.mBtnNewVersion.setText(str);
            this.mBtnNewVersion.setVisibility(0);
            this.mBtnViewVersionDetail.setVisibility(8);
            this.mBtnSyncOsVersion.setVisibility(8);
            this.mLoadingSyncOsVersion.setVisibility(8);
            this.mIvVehicleBackground.setImageResource(R.mipmap.vehicle_background_ota);
            UpgradeAnimHelper.showUpgradeAnim(getActivity());
        } else {
            if (VehicleFeature.isD55()) {
                this.mNewVersionLayout.setVisibility(8);
            }
            String currentOTAVersion = OTAManager.getCurrentOTAVersion();
            if (TextUtils.isEmpty(currentOTAVersion)) {
                this.mBtnNewVersion.setVisibility(8);
                this.mBtnViewVersionDetail.setVisibility(8);
                this.mBtnSyncOsVersion.setVisibility(0);
                this.mLoadingSyncOsVersion.setVisibility(8);
                if (this.mBtnSyncOsVersion.isEnabled()) {
                    this.mTvNewVersionTips.setVisibility(8);
                } else {
                    this.mTvNewVersionTips.setVisibility(0);
                }
            } else {
                this.mTvSimpleVersion.setText(currentOTAVersion);
                this.mTvNewVersionTips.setText(ConfigHelper.getString(Constants.ConfigKey.MAIN_LATEST_VERSION_TIPS));
                showVersionDetailButton();
                this.mBtnNewVersion.setVisibility(8);
                this.mBtnSyncOsVersion.setVisibility(8);
                this.mLoadingSyncOsVersion.setVisibility(8);
                this.mTvNewVersionTips.setVisibility(0);
            }
            this.mIvVehicleBackground.setImageResource(R.mipmap.vehicle_background);
            UpgradeAnimHelper.hideUpgradeAnim(getActivity());
        }
        if (OTAManager.getPreferencesManager().supportAutoUpgrade()) {
            this.mSAutoUpgrade.setChecked(true);
        } else {
            this.mSAutoUpgrade.setChecked(false);
        }
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void onHandleEvent(ActivityEvent activityEvent) {
        super.lambda$onEvent$0$BaseFragment(activityEvent);
        switch (activityEvent.getAction()) {
            case SCHEDULE_SUCCESS:
            case CANCEL_SCHEDULE_SUCCESS:
            case CAMPAIGN_READY:
            case AUTO_UPGRADE:
            case CANCEL_AUTO_SCHEDULE:
            case CAMPAIGN_CANCEL:
                ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$b_yKyv5UC3YkGZC2skBmC_C6Lw0
                    @Override // java.lang.Runnable
                    public final void run() {
                        MainV2Fragment.this.lambda$onHandleEvent$5$MainV2Fragment();
                    }
                });
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.btn_new_version) {
            if (VehicleFeature.isD55()) {
                gotoScheduleFragment();
            } else {
                gotoNewFragment();
            }
        } else if (view.getId() == R.id.btn_view_version_detail) {
            final OsVersion osVersion = OTAManager.getOsVersionManager().getOsVersion();
            if (osVersion != null && osVersion.getFeatures() != null && !osVersion.getFeatures().isEmpty()) {
                ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$kc4ESZcs-Aw5oxD0-Q6L9iPzerg
                    @Override // java.lang.Runnable
                    public final void run() {
                        MainV2Fragment.this.lambda$onClick$6$MainV2Fragment(osVersion);
                    }
                });
                return;
            }
            LogUtils.w(TAG, "Not found os version feature and installed campaign");
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$-ZAqgsOrDT7FI4-WDyd2UrE1HjY
                @Override // java.lang.Runnable
                public final void run() {
                    MainV2Fragment.this.lambda$onClick$7$MainV2Fragment();
                }
            });
        } else if (view.getId() == R.id.btn_sync_os_version) {
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$oITcKEPN-lY1_YpwbzFR5qUD6xM
                @Override // java.lang.Runnable
                public final void run() {
                    MainV2Fragment.this.lambda$onClick$8$MainV2Fragment();
                }
            });
        } else if (view.getId() == R.id.btn_view_version_detail_new) {
            gotoNewFragment();
        }
    }

    public /* synthetic */ void lambda$onClick$6$MainV2Fragment(OsVersion osVersion) {
        startFragment(CampaignFeatureHelper.getVersionDetailFragmentClass(osVersion.getFeatures()));
    }

    private void gotoNewFragment() {
        final Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign == null) {
            promptExpiredAndGotoMain();
        } else {
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$AjEhGZNwsquF9vvNu2_8jWPAWl0
                @Override // java.lang.Runnable
                public final void run() {
                    MainV2Fragment.this.lambda$gotoNewFragment$9$MainV2Fragment(activeCampaign);
                }
            });
        }
    }

    public /* synthetic */ void lambda$gotoNewFragment$9$MainV2Fragment(Campaign campaign) {
        startFragment(getNewFragmentClass(campaign));
    }

    private void gotoScheduleFragment() {
        if (OTAManager.getCampaignManager().getActiveCampaign() == null) {
            promptExpiredAndGotoMain();
        } else {
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$VQ2sMNEm7m8WQhn2LG7u2rAMex8
                @Override // java.lang.Runnable
                public final void run() {
                    MainV2Fragment.this.lambda$gotoScheduleFragment$10$MainV2Fragment();
                }
            });
        }
    }

    public /* synthetic */ void lambda$gotoScheduleFragment$10$MainV2Fragment() {
        startFragment(CampaignFeatureHelper.getScheduleFragmentClass());
    }

    private void showVersionDetailButton() {
        OsVersion osVersion = OTAManager.getOsVersionManager().getOsVersion();
        if (osVersion != null) {
            this.mBtnViewVersionDetail.setVisibility(0);
            if (osVersion.getFeatures() == null || osVersion.getFeatures().isEmpty()) {
                this.mBtnViewVersionDetail.setEnabled(false);
                return;
            }
            return;
        }
        this.mBtnViewVersionDetail.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncOsVersion */
    public void lambda$onClick$8$MainV2Fragment() {
        new SyncOsTask(this, null).execute(new Object[0]);
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
                LogUtils.w(MainV2Fragment.TAG, "MainInfo data set is empty");
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
                viewHolder = new ViewHolder(this, null);
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

            /* synthetic */ ViewHolder(MainInfoAdapter mainInfoAdapter, AnonymousClass1 anonymousClass1) {
                this();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class SyncOsTask extends AsyncTask<Object, Integer, String> {
        private SyncOsTask() {
        }

        /* synthetic */ SyncOsTask(MainV2Fragment mainV2Fragment, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$SyncOsTask$lA4jhVcojyhw7iXNQkAQ6t9u9f4
                @Override // java.lang.Runnable
                public final void run() {
                    MainV2Fragment.SyncOsTask.this.lambda$onPreExecute$0$MainV2Fragment$SyncOsTask();
                }
            });
        }

        public /* synthetic */ void lambda$onPreExecute$0$MainV2Fragment$SyncOsTask() {
            MainV2Fragment.this.mBtnSyncOsVersion.setVisibility(8);
            MainV2Fragment.this.mTvNewVersionTips.setVisibility(8);
            MainV2Fragment.this.mLoadingSyncOsVersion.setVisibility(0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(Object... objArr) {
            try {
                return OTAManager.syncOsVersion();
            } catch (Exception e) {
                LogUtils.e(MainV2Fragment.TAG, e, "Sync os version fail");
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            super.onPostExecute((SyncOsTask) str);
            Object[] objArr = new Object[1];
            objArr[0] = TextUtils.isEmpty(str) ? "empty" : str;
            LogUtils.d(MainV2Fragment.TAG, "onPostExecute osVersion:%s", objArr);
            if (TextUtils.isEmpty(str)) {
                ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$SyncOsTask$4Ogp8TQhEwN270ymqMpyGKlV9-U
                    @Override // java.lang.Runnable
                    public final void run() {
                        MainV2Fragment.SyncOsTask.this.lambda$onPostExecute$1$MainV2Fragment$SyncOsTask();
                    }
                });
            } else {
                ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$MainV2Fragment$SyncOsTask$NahzyvgZ1qOKjhfw6jHbSPSds0A
                    @Override // java.lang.Runnable
                    public final void run() {
                        MainV2Fragment.SyncOsTask.this.lambda$onPostExecute$2$MainV2Fragment$SyncOsTask();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onPostExecute$1$MainV2Fragment$SyncOsTask() {
            MainV2Fragment.this.mTvNewVersionTips.setVisibility(0);
            MainV2Fragment.this.mLoadingSyncOsVersion.setVisibility(8);
            MainV2Fragment.this.mBtnSyncOsVersion.setVisibility(0);
            MainV2Fragment.this.mBtnSyncOsVersion.setEnabled(false);
        }

        public /* synthetic */ void lambda$onPostExecute$2$MainV2Fragment$SyncOsTask() {
            MainV2Fragment.this.lambda$onHandleEvent$5$MainV2Fragment();
        }
    }
}
