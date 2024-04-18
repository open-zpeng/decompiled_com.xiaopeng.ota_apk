package com.xiaopeng.ota.activity.info;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.SystemProperties;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.internal.util.ArrayUtils;
import com.xiaopeng.fota.sdk.EcuType;
import com.xiaopeng.lib.utils.info.BuildInfoUtils;
import com.xiaopeng.ota.Config;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.activity.BaseFragment;
import com.xiaopeng.ota.bean.Action;
import com.xiaopeng.ota.bean.ActivityEvent;
import com.xiaopeng.ota.bean.AppType;
import com.xiaopeng.ota.bean.AppVersion;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.presenter.update.UpgradePresenter;
import com.xiaopeng.ota.presenter.update.bean.EcuVersion;
import com.xiaopeng.ota.utils.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.PackageUtils;
import com.xiaopeng.ota.utils.ResourceUtils;
import com.xiaopeng.ota.utils.StringUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.ota.view.AppGridView;
import com.xiaopeng.ota.view.SpreadListView;
import com.xiaopeng.xui.widget.XGroupHeader;
import com.xiaopeng.xui.widget.XTextView;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class InfoFragment extends BaseFragment {
    private static final int ASC_LENGTH_LIMIT = 5;
    private static final int CHINESE_LENGTH_LIMIT = 9;
    private static final int SPACE_TYPE_LARGE = 0;
    private static final int SPACE_TYPE_MEDIUM = 1;
    private static final int SPACE_TYPE_SMALL = 2;
    private static final String TAG = "InfoFragment";
    private AppVersionAdapter mAppAdapter;
    private EcuVersionAdapter mEcuAdapter;
    private XGroupHeader mGhApps;
    private XGroupHeader mGhEcus;
    private AppGridView mGvAppVersions;
    private SpreadListView mLvEcuVersions;
    private XTextView mTvCduSummary;
    private XTextView mTvCduVersion;
    private XTextView mTvFingerprint;
    private XTextView mTvMcuVersion;
    private UpgradePresenter mUpgradePresenter;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public String getFragmentName() {
        return TAG;
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    protected int getLayoutId() {
        return R.layout.fragment_info;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void onBeforeInitViews() {
        super.onBeforeInitViews();
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mUpgradePresenter.syncEcuVersions(0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void initViews(View view) {
        super.initViews(view);
        OTAManager.waitAvailable();
        setTitle(ConfigHelper.getString(Constants.ConfigKey.TITLE_INFO));
        this.mUpgradePresenter = new UpgradePresenter();
        this.mGhApps = (XGroupHeader) view.findViewById(R.id.gh_apps);
        this.mGhApps.setText(ConfigHelper.getString(Constants.ConfigKey.APP_GROUP_HEADER_TITLE));
        this.mGvAppVersions = (AppGridView) view.findViewById(R.id.gv_app_versions);
        this.mAppAdapter = new AppVersionAdapter(getActivity(), R.layout.gridview_app_item);
        this.mGvAppVersions.setAdapter((ListAdapter) this.mAppAdapter);
        this.mGvAppVersions.setEnabled(false);
        this.mGhEcus = (XGroupHeader) view.findViewById(R.id.gh_ecus);
        this.mGhEcus.setText(ConfigHelper.getString(Constants.ConfigKey.ECU_GROUP_HEADER_TITLE));
        this.mTvCduSummary = (XTextView) view.findViewById(R.id.tv_cdu_summary);
        this.mTvCduVersion = (XTextView) view.findViewById(R.id.tv_cdu_version);
        this.mTvMcuVersion = (XTextView) view.findViewById(R.id.tv_mcu_version);
        this.mTvFingerprint = (XTextView) view.findViewById(R.id.tv_cdu_fingerprint);
        this.mLvEcuVersions = (SpreadListView) view.findViewById(R.id.lv_ecu_versions);
        this.mEcuAdapter = new EcuVersionAdapter(getActivity(), R.layout.listview_ecu_item);
        this.mLvEcuVersions.setAdapter((ListAdapter) this.mEcuAdapter);
        this.mLvEcuVersions.setEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void refreshData() {
        super.refreshData();
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.info.-$$Lambda$InfoFragment$1kGBxedIYirttDenR117O4ZXsqM
            @Override // java.lang.Runnable
            public final void run() {
                InfoFragment.this.lambda$refreshData$1$InfoFragment();
            }
        });
    }

    public /* synthetic */ void lambda$refreshData$1$InfoFragment() {
        final List<AppVersion> appVersions = getAppVersions();
        final EcuVersion cduVersion = getCduVersion();
        final List<EcuVersion> ecuVersions = getEcuVersions();
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.info.-$$Lambda$InfoFragment$77XmFX8rkYYDGOfv_ephk6o4Q8M
            @Override // java.lang.Runnable
            public final void run() {
                InfoFragment.this.lambda$null$0$InfoFragment(appVersions, cduVersion, ecuVersions);
            }
        });
    }

    public /* synthetic */ void lambda$null$0$InfoFragment(List list, EcuVersion ecuVersion, List list2) {
        int i = R.dimen.version_horizontal_space_large;
        int computeSpaceType = computeSpaceType(list);
        if (computeSpaceType == 0) {
            i = R.dimen.version_horizontal_space_large;
        } else if (computeSpaceType == 1) {
            i = R.dimen.version_horizontal_space_medium;
        } else if (computeSpaceType == 2) {
            i = R.dimen.version_horizontal_space_small;
        }
        this.mGvAppVersions.setHorizontalSpacing(ResourceUtils.dip2px((int) ResourceUtils.getDimen(i)));
        this.mAppAdapter.setData(list);
        this.mTvCduSummary.setText(String.format(ConfigHelper.getFormat(Constants.ConfigKey.ECU_VERSION_SUMMARY_FORMAT), ecuVersion.getName(), ecuVersion.getDesc()));
        this.mTvCduVersion.setText(ecuVersion.getSv());
        this.mTvMcuVersion.setText(SystemProperties.get(Config.MCU_VERSION_PROP, "unknown"));
        this.mTvFingerprint.setText(ecuVersion.getFingerprint());
        if (ArrayUtils.isEmpty(list2)) {
            this.mLvEcuVersions.setVisibility(8);
            return;
        }
        this.mLvEcuVersions.setVisibility(0);
        this.mEcuAdapter.setData(list2);
    }

    private int computeSpaceType(List<AppVersion> list) {
        if (ArrayUtils.isEmpty(list)) {
            return 0;
        }
        int i = 0;
        for (AppVersion appVersion : list) {
            String name = appVersion.getName();
            int length = name.getBytes().length;
            if (StringUtils.isChinese(name)) {
                if (length > 9) {
                    i++;
                }
            } else if (length > 5) {
                i++;
            }
        }
        if (i == 0) {
            return 0;
        }
        return i == 1 ? 1 : 2;
    }

    private List<AppVersion> getAppVersions() {
        List<AppType> list;
        List<String> listFromJson = JsonUtils.listFromJson(ConfigHelper.getString(Constants.ConfigKey.APP_LIST));
        if (ArrayUtils.isEmpty(listFromJson)) {
            list = AppType.getAllTypes();
        } else {
            ArrayList arrayList = new ArrayList();
            for (String str : listFromJson) {
                arrayList.add(AppType.valueOf(str));
            }
            list = arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        for (AppType appType : list) {
            PackageInfo packageInfo = PackageUtils.getPackageInfo(getActivity(), appType.getPackageName());
            if (packageInfo != null) {
                AppVersion appVersion = new AppVersion();
                appVersion.setName(appType.getDesc());
                appVersion.setVersion(packageInfo.versionName);
                arrayList2.add(appVersion);
            }
        }
        return arrayList2;
    }

    private EcuVersion getCduVersion() {
        EcuVersion ecuVersion = new EcuVersion();
        ecuVersion.setName(EcuType.CDU.name());
        ecuVersion.setDesc(ResourceUtils.getString(R.string.info_cdu_desc));
        ecuVersion.setSv(BuildInfoUtils.getSystemVersion());
        ecuVersion.setFingerprint(BuildInfoUtils.getFullSystemVersion());
        return ecuVersion;
    }

    private List<EcuVersion> getEcuVersions() {
        List listFromJson = JsonUtils.listFromJson(ConfigHelper.getString(Constants.ConfigKey.ECU_LIST));
        List<EcuVersion> ecuVersions = this.mUpgradePresenter.getEcuVersions();
        if (ArrayUtils.isEmpty(ecuVersions)) {
            LogUtils.d(TAG, "getEcuVersions and result empty");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (EcuVersion ecuVersion : ecuVersions) {
            if (!EcuType.CDU.name().equals(ecuVersion.getName()) && (ArrayUtils.isEmpty(listFromJson) || listFromJson.contains(ecuVersion.getName()))) {
                try {
                    ecuVersion.createDesc();
                } catch (Exception e) {
                    LogUtils.e(TAG, e, "ECU(%s) createDesc fail", JsonUtils.toJson(ecuVersion));
                }
                arrayList.add(ecuVersion);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void onHandleEvent(ActivityEvent activityEvent) {
        super.lambda$onEvent$0$BaseFragment(activityEvent);
        if (activityEvent.getAction() == Action.ECU_VERSION_CHANGED) {
            List<EcuVersion> ecuVersions = getEcuVersions();
            if (ArrayUtils.isEmpty(ecuVersions)) {
                return;
            }
            this.mLvEcuVersions.setVisibility(0);
            this.mEcuAdapter.refreshData(ecuVersions);
        }
    }
}
