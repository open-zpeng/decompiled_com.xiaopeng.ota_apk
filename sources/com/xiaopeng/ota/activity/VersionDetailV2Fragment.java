package com.xiaopeng.ota.activity;

import android.content.res.Configuration;
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
import com.xiaopeng.ota.helper.CampaignFeatureHelper;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.presenter.update.bean.Feature;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.ota.utils.VersionUtils;
import com.xiaopeng.ota.version.os.OsVersion;
import com.xiaopeng.xui.widget.XImageButton;
import com.xiaopeng.xui.widget.XTextView;
import java.util.List;
/* loaded from: classes2.dex */
public class VersionDetailV2Fragment extends WebViewVersionFragment {
    private static final String TAG = "VersionDetailV2Fragment";
    private int mCheckedIndex = -1;
    private XImageButton mIbBack;
    private RecyclerView mRecyclerViewTitle;
    private int mTitleSize;
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
        return R.layout.fragment_version_detail_v2;
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
        List<Feature> feature = getFeature();
        setTitleIndexChangedListener(new WebViewVersionFragment.TitleIndexChangedListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$VersionDetailV2Fragment$6Xh_Hgfdj5ANqTNsBWrpa8S3noc
            @Override // com.xiaopeng.ota.activity.WebViewVersionFragment.TitleIndexChangedListener
            public final void onChanged(int i) {
                VersionDetailV2Fragment.this.lambda$initViews$1$VersionDetailV2Fragment(i);
            }
        });
        init(2, this.mWvContent, feature);
    }

    public /* synthetic */ void lambda$initViews$1$VersionDetailV2Fragment(final int i) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$VersionDetailV2Fragment$2IUCG__5OwrKlzE9eYHypWzIw4Q
            @Override // java.lang.Runnable
            public final void run() {
                VersionDetailV2Fragment.this.lambda$null$0$VersionDetailV2Fragment(i);
            }
        });
    }

    public /* synthetic */ void lambda$null$0$VersionDetailV2Fragment(int i) {
        setChecked(i, true);
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void refreshData() {
        super.refreshData();
        OsVersion osVersion = OTAManager.getOsVersionManager().getOsVersion();
        if (osVersion == null) {
            LogUtils.w(TAG, "Os version and installed campaign not found");
            lambda$onClick$1$BaseFragment();
            return;
        }
        String simpleVersion = VersionUtils.getSimpleVersion(osVersion.getVersionName());
        String carType = VehicleFeature.getCarType();
        char c = 65535;
        if (carType.hashCode() == 67044 && carType.equals("D55")) {
            c = 0;
        }
        if (c == 0) {
            setTitle(String.format("%s %s", simpleVersion, ConfigHelper.getString(Constants.ConfigKey.NEW_V2_INTRODUCTION)));
        } else {
            this.mTvVersion.setText(simpleVersion);
        }
        this.mRecyclerViewTitle.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mVersionTitleAdapter = new VersionTitleAdapter(getContext());
        this.mRecyclerViewTitle.setAdapter(this.mVersionTitleAdapter);
        List<VersionTitle> versionTitle = CampaignFeatureHelper.getVersionTitle(getFeature());
        this.mTitleSize = versionTitle.size();
        this.mVersionTitleAdapter.setData(versionTitle);
        this.mVersionTitleAdapter.setOnItemClickedListener(new VersionTitleAdapter.OnItemClickedListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$VersionDetailV2Fragment$AVBdnNS8le8Qil_TFu-flYetM_8
            @Override // com.xiaopeng.ota.activity.VersionTitleAdapter.OnItemClickedListener
            public final void onItemClicked(int i) {
                VersionDetailV2Fragment.this.lambda$refreshData$2$VersionDetailV2Fragment(i);
            }
        });
        if (versionTitle == null || versionTitle.isEmpty()) {
            return;
        }
        showAllInWebView(getContext(), this.mWvContent, versionTitle);
        setChecked(0);
    }

    public /* synthetic */ void lambda$refreshData$2$VersionDetailV2Fragment(int i) {
        if (this.mCheckedIndex == i) {
            return;
        }
        setChecked(i);
        showCheckedContent(this.mWvContent, i);
    }

    private List<Feature> getFeature() {
        OsVersion osVersion = OTAManager.getOsVersionManager().getOsVersion();
        if (osVersion != null) {
            return osVersion.getFeatures();
        }
        return null;
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

    @Override // com.xiaopeng.ota.activity.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (ThemeManager.isThemeChanged(configuration)) {
            refreshWebViewTheme();
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

    private void refreshWebViewTheme() {
        showAllInWebView(getContext(), this.mWvContent, CampaignFeatureHelper.getVersionTitle(getFeature()));
    }
}
