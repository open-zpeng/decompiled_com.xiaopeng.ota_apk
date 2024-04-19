package com.xiaopeng.ota.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.util.ArrayUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xiaopeng.libtheme.ThemeManager;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.activity.VersionAdapter;
import com.xiaopeng.ota.bean.VersionInfo;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.utils.AnimUtils;
import com.xiaopeng.ota.utils.ResourceUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.ota.view.RoundImageView;
import com.xiaopeng.ota.view.SpacesItemDecoration;
import com.xiaopeng.xui.widget.XImageButton;
import com.xiaopeng.xui.widget.XRelativeLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
/* loaded from: classes2.dex */
public class HistoryFragment extends BaseFragment {
    private static final String TAG = "HistoryFragment";
    private XImageButton mIbBack;
    private RoundImageView mIvMedia;
    private XRelativeLayout mRlMediaContainer;
    private RecyclerView mRvVersionList;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    private VersionAdapter mVersionAdapter;

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
        return R.layout.fragment_history;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void onBeforeInitViews() {
        super.onBeforeInitViews();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void initViews(View view) {
        super.initViews(view);
        OTAManager.waitAvailable();
        setTitle(ConfigHelper.getString(Constants.ConfigKey.TITLE_HISTORY));
        this.mRvVersionList = (RecyclerView) view.findViewById(R.id.rv_version_list);
        this.mRlMediaContainer = (XRelativeLayout) view.findViewById(R.id.rl_media_container);
        this.mIvMedia = (RoundImageView) view.findViewById(R.id.iv_media);
        this.mIbBack = (XImageButton) view.findViewById(R.id.ib_back);
        this.mIbBack.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.mRvVersionList.setLayoutManager(linearLayoutManager);
        this.mRvVersionList.addItemDecoration(new SpacesItemDecoration(ResourceUtils.dip2px(16)));
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void refreshData() {
        super.refreshData();
        final Context context = getContext();
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$HistoryFragment$l-YeaMKgHtHF_JHVGxCvL3nJbLg
            @Override // java.lang.Runnable
            public final void run() {
                HistoryFragment.this.lambda$refreshData$2$HistoryFragment(context);
            }
        });
    }

    public /* synthetic */ void lambda$refreshData$2$HistoryFragment(final Context context) {
        final List<VersionInfo> historyData = getHistoryData();
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$HistoryFragment$TnEZ7Be_0xJpuEe0Og4oDxfxrC8
            @Override // java.lang.Runnable
            public final void run() {
                HistoryFragment.this.lambda$null$1$HistoryFragment(context, historyData);
            }
        });
    }

    public /* synthetic */ void lambda$null$1$HistoryFragment(final Context context, List list) {
        this.mVersionAdapter = new VersionAdapter(context, list, 0);
        this.mVersionAdapter.setOnThumbClickedListener(new VersionAdapter.OnThumbClickedListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$HistoryFragment$lXuBJ4klJ39hqhs_Xps0jhfCDvI
            @Override // com.xiaopeng.ota.activity.VersionAdapter.OnThumbClickedListener
            public final void onClicked(String str) {
                HistoryFragment.this.lambda$null$0$HistoryFragment(context, str);
            }
        });
        this.mRvVersionList.setAdapter(this.mVersionAdapter);
    }

    public /* synthetic */ void lambda$null$0$HistoryFragment(Context context, String str) {
        if (this.mRlMediaContainer.getVisibility() == 0) {
            return;
        }
        Glide.with(context).load(str).apply(new RequestOptions().override(ResourceUtils.getInt(R.integer.photo_width), ResourceUtils.getInt(R.integer.photo_height))).into(this.mIvMedia);
        this.mRlMediaContainer.setAlpha(0.0f);
        this.mRlMediaContainer.setVisibility(0);
        AnimUtils.startPhotoAnim(this.mRlMediaContainer, AnimUtils.ACTION_OPEN, 700L);
    }

    private List<VersionInfo> getHistoryData() {
        ArrayList arrayList = new ArrayList();
        List<Campaign> allHistoryCampaign = OTAManager.getCampaignManager().getAllHistoryCampaign();
        if (!ArrayUtils.isEmpty(allHistoryCampaign)) {
            for (Campaign campaign : allHistoryCampaign) {
                VersionInfo versionInfo = new VersionInfo();
                versionInfo.setVersion(campaign.getReleaseVersion());
                if (campaign.getInstalledTime() > 0) {
                    versionInfo.setUpgradeTime(this.mSimpleDateFormat.format(new Date(campaign.getInstalledTime())));
                } else {
                    versionInfo.setUpgradeTime(campaign.getReleaseDate());
                }
                versionInfo.setHtmlReleaseNotes(campaign.getHtmlReleaseNotes());
                versionInfo.setImageUrls(campaign.getImageUrls());
                versionInfo.setVideoUrls(campaign.getVideoUrls());
                arrayList.add(versionInfo);
            }
        }
        return arrayList;
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
        if (view.getId() == R.id.ib_back) {
            AnimUtils.startPhotoAnim(this.mRlMediaContainer, AnimUtils.ACTION_CLOSE, 800L);
        }
    }
}
