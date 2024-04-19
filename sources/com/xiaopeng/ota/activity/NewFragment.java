package com.xiaopeng.ota.activity;

import android.content.res.Configuration;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xiaopeng.libtheme.ThemeManager;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.activity.VersionAdapter;
import com.xiaopeng.ota.bean.Action;
import com.xiaopeng.ota.bean.ActivityEvent;
import com.xiaopeng.ota.bean.VersionInfo;
import com.xiaopeng.ota.helper.ActivityHelper;
import com.xiaopeng.ota.helper.CampaignFeatureHelper;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.helper.ScheduleHelper;
import com.xiaopeng.ota.presenter.event.EventPresenter;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.utils.AnimUtils;
import com.xiaopeng.ota.utils.ResourceUtils;
import com.xiaopeng.ota.utils.StringUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.ota.utils.TimeUtils;
import com.xiaopeng.ota.view.RoundImageView;
import com.xiaopeng.xui.utils.XTouchAreaUtils;
import com.xiaopeng.xui.widget.XButton;
import com.xiaopeng.xui.widget.XImageButton;
import com.xiaopeng.xui.widget.XRelativeLayout;
import com.xiaopeng.xui.widget.XTextView;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class NewFragment extends BaseFragment {
    private static final long ANIM_DURATION_MILLIS = 300;
    private static final String TAG = "NewFragment";
    private static final int TYPE_MODIFY_TIME = 1;
    private static final int TYPE_SCHEDULE = 0;
    private static final int TYPE_UPGRADE_LATER = 2;
    private XButton mBtnSchedule;
    private XButton mBtnUpgrade;
    private XImageButton mIbBack;
    private RoundImageView mIvMedia;
    private XRelativeLayout mRlMediaContainer;
    private RecyclerView mRlVersionInfo;
    private XTextView mTvScheduleTime;
    private VersionAdapter mVersionAdapter;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public String getFragmentName() {
        return TAG;
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    protected int getLayoutId() {
        return R.layout.fragment_new;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void initViews(View view) {
        super.initViews(view);
        OTAManager.waitAvailable();
        setTitle(ConfigHelper.getString(Constants.ConfigKey.TITLE_NEW));
        this.mRlVersionInfo = (RecyclerView) view.findViewById(R.id.rv_version_info);
        this.mBtnSchedule = (XButton) view.findViewById(R.id.btn_schedule);
        this.mBtnSchedule.setOnClickListener(this);
        XButton xButton = this.mBtnSchedule;
        XTouchAreaUtils.extendTouchArea(xButton, (ViewGroup) xButton.getParent(), new int[]{16, 20, 16, 20});
        this.mBtnUpgrade = (XButton) view.findViewById(R.id.btn_upgrade);
        this.mBtnUpgrade.setText(ConfigHelper.getString(Constants.ConfigKey.NEW_BUTTON_UPGRADE_NOW));
        this.mBtnUpgrade.setOnClickListener(this);
        XButton xButton2 = this.mBtnSchedule;
        XTouchAreaUtils.extendTouchArea(xButton2, (ViewGroup) xButton2.getParent(), new int[]{16, 20, 16, 20});
        this.mTvScheduleTime = (XTextView) view.findViewById(R.id.tv_schedule_time);
        this.mRlMediaContainer = (XRelativeLayout) view.findViewById(R.id.rl_media_container);
        this.mIvMedia = (RoundImageView) view.findViewById(R.id.iv_media);
        this.mIvMedia.setOnClickListener(this);
        this.mIbBack = (XImageButton) view.findViewById(R.id.ib_back);
        this.mIbBack.setOnClickListener(this);
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void refreshData() {
        super.refreshData();
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign == null) {
            this.mRlVersionInfo.setAdapter(null);
            lambda$onClick$1$BaseFragment();
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(1);
            this.mRlVersionInfo.setLayoutManager(linearLayoutManager);
            VersionInfo versionInfo = new VersionInfo();
            versionInfo.setVersion(activeCampaign.getReleaseVersion());
            versionInfo.setUpgradeTime(activeCampaign.getReleaseDate());
            versionInfo.setHtmlReleaseNotes(activeCampaign.getHtmlReleaseNotes());
            versionInfo.setImageUrls(activeCampaign.getImageUrls());
            versionInfo.setVideoUrls(activeCampaign.getVideoUrls());
            ArrayList arrayList = new ArrayList();
            arrayList.add(versionInfo);
            this.mVersionAdapter = new VersionAdapter(getContext(), arrayList);
            this.mVersionAdapter.setOnThumbClickedListener(new VersionAdapter.OnThumbClickedListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$NewFragment$nd9HZQhwLkI-5YDlJ7tdnqvIbdQ
                @Override // com.xiaopeng.ota.activity.VersionAdapter.OnThumbClickedListener
                public final void onClicked(String str) {
                    NewFragment.this.lambda$refreshData$0$NewFragment(str);
                }
            });
            this.mRlVersionInfo.setAdapter(this.mVersionAdapter);
        }
        refreshScheduleButton();
    }

    public /* synthetic */ void lambda$refreshData$0$NewFragment(String str) {
        if (this.mRlMediaContainer.getVisibility() == 0) {
            return;
        }
        Glide.with(getContext()).load(str).apply(new RequestOptions().override(ResourceUtils.getInt(R.integer.photo_width), ResourceUtils.getInt(R.integer.photo_height))).into(this.mIvMedia);
        this.mRlMediaContainer.setAlpha(0.0f);
        this.mRlMediaContainer.setVisibility(0);
        AnimUtils.startPhotoAnim(this.mRlMediaContainer, AnimUtils.ACTION_OPEN, ANIM_DURATION_MILLIS);
    }

    private void refreshScheduleButton() {
        Campaign activeWaitingCampaign = OTAManager.getCampaignManager().getActiveWaitingCampaign();
        if (activeWaitingCampaign == null) {
            this.mBtnSchedule.setTag(2);
            this.mBtnSchedule.setText(ConfigHelper.getString(Constants.ConfigKey.BUTTON_UPGRADE_LATER));
            this.mBtnUpgrade.setEnabled(false);
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
            this.mBtnSchedule.setText(StringUtils.format(Constants.ConfigKey.BUTTON_SCHEDULE_FORMAT, ScheduleHelper.getInstance().getDefaultTime()));
            this.mBtnSchedule.setTag(0);
            this.mTvScheduleTime.setVisibility(8);
        }
        this.mBtnUpgrade.setEnabled(true);
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (ThemeManager.isThemeChanged(configuration)) {
            this.mVersionAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void onHandleEvent(ActivityEvent activityEvent) {
        super.lambda$onEvent$0$BaseFragment(activityEvent);
        int i = AnonymousClass1.$SwitchMap$com$xiaopeng$ota$bean$Action[activityEvent.getAction().ordinal()];
        if (i == 1 || i == 2) {
            refreshScheduleButton();
        }
    }

    /* renamed from: com.xiaopeng.ota.activity.NewFragment$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
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
        }
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.btn_schedule) {
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$NewFragment$aVKmfirIKtGrThAuGIkqpyEcmzU
                @Override // java.lang.Runnable
                public final void run() {
                    NewFragment.this.lambda$onClick$1$NewFragment();
                }
            });
        } else if (view.getId() == R.id.btn_upgrade) {
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$NewFragment$J7_GWbly4EU6jk87vbVIDK6cju0
                @Override // java.lang.Runnable
                public final void run() {
                    NewFragment.this.lambda$onClick$2$NewFragment();
                }
            });
        } else if (view.getId() == R.id.ib_back || view.getId() == R.id.iv_media) {
            AnimUtils.startPhotoAnim(this.mRlMediaContainer, AnimUtils.ACTION_CLOSE, ANIM_DURATION_MILLIS);
        }
    }

    public /* synthetic */ void lambda$onClick$1$NewFragment() {
        Integer num = (Integer) this.mBtnSchedule.getTag();
        if (num == null) {
            lambda$onClick$1$BaseFragment();
            return;
        }
        int intValue = num.intValue();
        if (intValue == 0) {
            Pair<Integer, Integer> hourAndMinute = TimeUtils.getHourAndMinute(ScheduleHelper.getInstance().getDefaultTime());
            if (hourAndMinute != null) {
                ActivityHelper.scheduleUpgrade(((Integer) hourAndMinute.first).intValue(), ((Integer) hourAndMinute.second).intValue(), Constants.Schedule.FROM_USER);
            }
        } else if (intValue == 1) {
            startFragment(CampaignFeatureHelper.getScheduleFragmentClass());
        } else if (intValue != 2) {
        } else {
            lambda$onClick$1$BaseFragment();
        }
    }

    public /* synthetic */ void lambda$onClick$2$NewFragment() {
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
