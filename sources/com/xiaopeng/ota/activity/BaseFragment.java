package com.xiaopeng.ota.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.xiaopeng.ota.App;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.bean.Action;
import com.xiaopeng.ota.bean.ActivityEvent;
import com.xiaopeng.ota.helper.CampaignFeatureHelper;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.helper.DialogHelper;
import com.xiaopeng.ota.presenter.event.EventPresenter;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.StringUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.xui.theme.XThemeManager;
import com.xiaopeng.xui.widget.XImageButton;
import com.xiaopeng.xui.widget.XTextView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* loaded from: classes2.dex */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private static final String HTML_LINE_SEPARATOR = "<br/>";
    private static final String LINE_SEPARATOR = "\n";
    protected DialogHelper mDialogHelper;
    protected XImageButton mIbTitleButton;
    protected View mParentView;
    protected Class mPreviousClass;
    protected XTextView mTvTitleText;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract String getFragmentName();

    protected abstract int getLayoutId();

    /* JADX INFO: Access modifiers changed from: protected */
    public void onBeforeBack() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onBeforeClose() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onBeforeInitViews() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        LogUtils.d(getFragmentName(), "onAttach");
        super.onAttach(context);
        this.mDialogHelper = DialogHelper.getInstance();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        EventBus.getDefault().register(this);
        this.mParentView = App.getInflateViewManager().inflatedView(getContext(), layoutInflater, getLayoutId(), viewGroup);
        onBeforeInitViews();
        initViews(this.mParentView);
        return this.mParentView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        refreshData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        this.mIbTitleButton = null;
        this.mTvTitleText = null;
        this.mParentView = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            return;
        }
        refreshData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void refreshData() {
        refreshTitle();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void refreshTitle() {
        if (getActivity() == null) {
            LogUtils.d(getFragmentName(), "refreshTitle, Activity is null");
        } else if (this.mPreviousClass != null) {
            this.mIbTitleButton.setImageResource(R.drawable.x_ic_small_back);
        } else {
            this.mIbTitleButton.setImageResource(R.drawable.x_ic_small_close);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initViews(View view) {
        this.mIbTitleButton = (XImageButton) view.findViewById(R.id.title_ib_close);
        this.mIbTitleButton.setOnClickListener(this);
        this.mTvTitleText = (XTextView) view.findViewById(R.id.title_tv_text);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPreviousClass(Class cls) {
        if (cls != null || this.mPreviousClass == null) {
            this.mPreviousClass = cls;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setTitle(String str) {
        if (getActivity() == null) {
            LogUtils.d(getFragmentName(), "setTitle, Activity is null");
            return;
        }
        XTextView xTextView = this.mTvTitleText;
        if (xTextView != null) {
            xTextView.setText(str);
        }
    }

    private void setTitle(int i) {
        if (getActivity() == null) {
            LogUtils.d(getFragmentName(), "setTitle, Activity is null");
            return;
        }
        XTextView xTextView = this.mTvTitleText;
        if (xTextView != null) {
            xTextView.setText(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean supportSchedule(Campaign campaign) {
        return campaign != null && ConfigHelper.getBoolean(Constants.ConfigKey.SCHEDULE_ENABLE) && campaign.isSupportSchedule();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startFragment(Class cls) {
        startFragment(cls, getClass());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startFragment(Class cls, Class cls2) {
        if (getActivity() == null) {
            LogUtils.d(getFragmentName(), "startFragment, Activity is null");
        } else {
            ((FragmentActivity) getActivity()).showFragment(cls.getSimpleName(), cls2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: goBack */
    public void lambda$onClick$1$BaseFragment() {
        if (getActivity() == null) {
            LogUtils.d(getFragmentName(), "goBack, Activity is null");
        } else if (this.mPreviousClass != null) {
            EventPresenter.getInstance().sendBackClickedEvent(getFragmentName());
            onBeforeBack();
            ((FragmentActivity) getActivity()).showFragment(this.mPreviousClass.getSimpleName(), null);
        } else {
            EventPresenter.getInstance().sendCloseClickedEvent(getFragmentName());
            onBeforeClose();
            finishMyself();
        }
    }

    protected void finishMyself() {
        if (getActivity() == null) {
            LogUtils.d(getFragmentName(), "finishMyself, Activity is null");
            return;
        }
        LogUtils.d(getFragmentName(), "finishMyself");
        ((FragmentActivity) getActivity()).finishMyself();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: onHandleEvent */
    public void lambda$onEvent$0$BaseFragment(ActivityEvent activityEvent) {
        if (getActivity() == null) {
            LogUtils.w(getFragmentName(), "onHandleEvent, Activity is null");
        } else if (AnonymousClass2.$SwitchMap$com$xiaopeng$ota$bean$Action[activityEvent.getAction().ordinal()] != 1) {
        } else {
            onBeforeClose();
            finishMyself();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaopeng.ota.activity.BaseFragment$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaopeng$ota$bean$Action = new int[Action.values().length];

        static {
            try {
                $SwitchMap$com$xiaopeng$ota$bean$Action[Action.CLOSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$xiaopeng$ota$bean$Action[Action.SCHEDULE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEvent(final ActivityEvent activityEvent) {
        if (getClass().getName().equals(activityEvent.getClazz().getName())) {
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$BaseFragment$Mwyjq6V8L_J74XYzSNwhF5c_haY
                @Override // java.lang.Runnable
                public final void run() {
                    BaseFragment.this.lambda$onEvent$0$BaseFragment(activityEvent);
                }
            });
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.title_ib_close) {
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$BaseFragment$hzWcvIkgbhTujXzmpw_3b8PBj08
                @Override // java.lang.Runnable
                public final void run() {
                    BaseFragment.this.lambda$onClick$1$BaseFragment();
                }
            });
        }
    }

    protected String getThemeFilePath() {
        return "theme/" + StringUtils.getLowerCaseFileName(getFragmentName()) + ".xml";
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        XThemeManager.onConfigurationChanged(configuration, getContext(), getView(), getThemeFilePath(), null);
    }

    protected boolean isOrientationPortrait() {
        return 1 == getResources().getConfiguration().orientation;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void promptExpiredAndGotoMain() {
        Campaign lastInvalidCampaign = OTAManager.getCampaignManager().getLastInvalidCampaign();
        DialogHelper.getInstance().promptExpired(lastInvalidCampaign != null ? lastInvalidCampaign.getCampaignId() : 0L, new DialogHelper.DialogInterface() { // from class: com.xiaopeng.ota.activity.BaseFragment.1
            @Override // com.xiaopeng.ota.helper.DialogHelper.DialogInterface
            public void onCancelClick() {
            }

            @Override // com.xiaopeng.ota.helper.DialogHelper.DialogInterface
            public void onClose() {
            }

            @Override // com.xiaopeng.ota.helper.DialogHelper.DialogInterface
            public void onOkClick() {
                BaseFragment.this.startFragment(CampaignFeatureHelper.getMainFragmentClass(), null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Class getNewFragmentClass(Campaign campaign) {
        return CampaignFeatureHelper.getNewFragmentClass(campaign);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CharSequence toHtml(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.contains(LINE_SEPARATOR)) {
            str = str.replaceAll(LINE_SEPARATOR, HTML_LINE_SEPARATOR);
        }
        return Html.fromHtml(str, 273);
    }
}
