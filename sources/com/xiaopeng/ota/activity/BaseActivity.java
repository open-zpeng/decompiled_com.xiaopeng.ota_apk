package com.xiaopeng.ota.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.xiaopeng.ota.VehicleFeature;
import com.xiaopeng.ota.bean.Action;
import com.xiaopeng.ota.bean.ActivityEvent;
import com.xiaopeng.ota.utils.ActivityContainer;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.StringUtils;
import com.xiaopeng.xui.theme.XThemeManager;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* loaded from: classes2.dex */
public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int getLayoutId();

    /* JADX INFO: Access modifiers changed from: protected */
    public void initViews() {
    }

    protected void onAfterInitViews() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onBeforeClose() {
    }

    protected void onBeforeInitViews() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onBeforeSetContent() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        LogUtils.d(getClass().getSimpleName(), "onCreate");
        super.onCreate(bundle);
        EventBus.getDefault().register(this);
        onBeforeSetContent();
        requestWindowFeature(14);
        int layoutId = getLayoutId();
        if (layoutId > 0) {
            setContentView(layoutId);
        }
        onBeforeInitViews();
        initViews();
        onAfterInitViews();
        ActivityContainer.getInstance().addActivity(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtils.d(getClass().getSimpleName(), "onDestroy");
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ActivityContainer.getInstance().removeActivity(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtils.d(getClass().getSimpleName(), "onResume");
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        LogUtils.d(getClass().getSimpleName(), "onNewIntent");
        super.onNewIntent(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void finishMyself() {
        String simpleName = getClass().getSimpleName();
        LogUtils.d(simpleName, "finishMyself:" + getClass().getSimpleName());
        if (isFinishing()) {
            return;
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaopeng.ota.activity.BaseActivity$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaopeng$ota$bean$Action = new int[Action.values().length];

        static {
            try {
                $SwitchMap$com$xiaopeng$ota$bean$Action[Action.CLOSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: onHandleEvent */
    public void lambda$onEvent$0$BaseActivity(ActivityEvent activityEvent) {
        if (AnonymousClass1.$SwitchMap$com$xiaopeng$ota$bean$Action[activityEvent.getAction().ordinal()] != 1) {
            return;
        }
        onBeforeClose();
        finishMyself();
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEvent(final ActivityEvent activityEvent) {
        if (getClass().getName().equals(activityEvent.getClazz().getName())) {
            String simpleName = getClass().getSimpleName();
            LogUtils.d(simpleName, "onEvent:" + activityEvent.getAction().name());
            runOnUiThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$BaseActivity$ssC81KH1IP1tqYkfkearaBuROSE
                @Override // java.lang.Runnable
                public final void run() {
                    BaseActivity.this.lambda$onEvent$0$BaseActivity(activityEvent);
                }
            });
        }
    }

    protected String getThemeFilePath() {
        return "theme/" + StringUtils.getLowerCaseFileName(getClass().getSimpleName()) + ".xml";
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        XThemeManager.onConfigurationChanged(configuration, getApplicationContext(), getWindow().getDecorView(), getThemeFilePath(), null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isOrientationPortrait() {
        return 1 == getResources().getConfiguration().orientation;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void disableToast() {
        setToastEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void enableToast() {
        setToastEnabled(true);
    }

    private void setToastEnabled(boolean z) {
        if ("D55".equals(VehicleFeature.getCarType())) {
            String simpleName = getClass().getSimpleName();
            LogUtils.d(simpleName, "setToastEnabled value=" + z);
            try {
                Class.forName("com.xiaopeng.xuimanager.xapp.XAppManager").getMethod("setToastEnabled", Boolean.TYPE).invoke(null, Boolean.valueOf(z));
            } catch (Exception e) {
                LogUtils.e(getClass().getSimpleName(), e, "Set toast enabled occurs Exception");
            }
        }
    }
}
