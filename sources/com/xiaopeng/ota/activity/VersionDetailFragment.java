package com.xiaopeng.ota.activity;

import android.content.res.Configuration;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xiaopeng.libtheme.ThemeManager;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.activity.VersionAdapter;
import com.xiaopeng.ota.bean.VersionInfo;
import com.xiaopeng.ota.helper.CampaignFeatureHelper;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.presenter.update.bean.Feature;
import com.xiaopeng.ota.utils.AnimUtils;
import com.xiaopeng.ota.utils.ResourceUtils;
import com.xiaopeng.ota.version.os.OsVersion;
import com.xiaopeng.ota.view.RoundImageView;
import com.xiaopeng.xui.widget.XImageButton;
import com.xiaopeng.xui.widget.XRelativeLayout;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class VersionDetailFragment extends BaseFragment {
    private static final long ANIM_DURATION_MILLIS = 300;
    private static final String TAG = "VersionDetailFragment";
    private XImageButton mIbBack;
    private RoundImageView mIvMedia;
    private XRelativeLayout mRlMediaContainer;
    private RecyclerView mRlVersionInfo;
    private VersionAdapter mVersionAdapter;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public String getFragmentName() {
        return TAG;
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    protected int getLayoutId() {
        return R.layout.fragment_version_detail;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void initViews(View view) {
        super.initViews(view);
        OTAManager.waitAvailable();
        setTitle(ConfigHelper.getString(Constants.ConfigKey.TITLE_VERSION_DETAIL));
        this.mRlVersionInfo = (RecyclerView) view.findViewById(R.id.rv_version_info);
        this.mRlMediaContainer = (XRelativeLayout) view.findViewById(R.id.rl_media_container);
        this.mIvMedia = (RoundImageView) view.findViewById(R.id.iv_media);
        this.mIvMedia.setOnClickListener(this);
        this.mIbBack = (XImageButton) view.findViewById(R.id.ib_back);
        this.mIbBack.setOnClickListener(this);
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void refreshData() {
        super.refreshData();
        OsVersion osVersion = OTAManager.getOsVersionManager().getOsVersion();
        if (osVersion == null || osVersion.getFeatures() == null || osVersion.getFeatures().isEmpty()) {
            this.mRlVersionInfo.setAdapter(null);
            lambda$onClick$1$BaseFragment();
            return;
        }
        Feature feature = osVersion.getFeatures().get(0);
        String versionName = osVersion.getVersionName();
        String htmlReleaseNotes = CampaignFeatureHelper.getHtmlReleaseNotes(feature);
        List<String> images = feature.getImages();
        List<String> videos = feature.getVideos();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.mRlVersionInfo.setLayoutManager(linearLayoutManager);
        VersionInfo versionInfo = new VersionInfo();
        versionInfo.setVersion(versionName);
        versionInfo.setUpgradeTime("");
        versionInfo.setHtmlReleaseNotes(htmlReleaseNotes);
        versionInfo.setImageUrls(images);
        versionInfo.setVideoUrls(videos);
        ArrayList arrayList = new ArrayList();
        arrayList.add(versionInfo);
        this.mVersionAdapter = new VersionAdapter(getContext(), arrayList);
        this.mVersionAdapter.setOnThumbClickedListener(new VersionAdapter.OnThumbClickedListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$VersionDetailFragment$Xt9SCj6i5kPYhN3RWrtcyRKZbrk
            @Override // com.xiaopeng.ota.activity.VersionAdapter.OnThumbClickedListener
            public final void onClicked(String str) {
                VersionDetailFragment.this.lambda$refreshData$0$VersionDetailFragment(str);
            }
        });
        this.mRlVersionInfo.setAdapter(this.mVersionAdapter);
    }

    public /* synthetic */ void lambda$refreshData$0$VersionDetailFragment(String str) {
        if (this.mRlMediaContainer.getVisibility() == 0) {
            return;
        }
        Glide.with(getContext()).load(str).apply(new RequestOptions().override(ResourceUtils.getInt(R.integer.photo_width), ResourceUtils.getInt(R.integer.photo_height))).into(this.mIvMedia);
        this.mRlMediaContainer.setAlpha(0.0f);
        this.mRlMediaContainer.setVisibility(0);
        AnimUtils.startPhotoAnim(this.mRlMediaContainer, AnimUtils.ACTION_OPEN, ANIM_DURATION_MILLIS);
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (ThemeManager.isThemeChanged(configuration)) {
            this.mVersionAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.ib_back || view.getId() == R.id.iv_media) {
            AnimUtils.startPhotoAnim(this.mRlMediaContainer, AnimUtils.ACTION_CLOSE, ANIM_DURATION_MILLIS);
        }
    }
}
