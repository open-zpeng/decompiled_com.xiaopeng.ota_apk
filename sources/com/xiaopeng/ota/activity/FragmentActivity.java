package com.xiaopeng.ota.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.badge.BadgeDrawable;
import com.xiaopeng.ota.Config;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.VehicleFeature;
import com.xiaopeng.ota.activity.info.InfoFragment;
import com.xiaopeng.ota.bean.Action;
import com.xiaopeng.ota.bean.ActivityEvent;
import com.xiaopeng.ota.helper.ActivityHelper;
import com.xiaopeng.ota.helper.CampaignFeatureHelper;
import com.xiaopeng.ota.utils.ActivityContainer;
import com.xiaopeng.ota.utils.ActivityUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ResourceUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.xui.app.XPanelActivity;
import java.util.HashMap;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* loaded from: classes2.dex */
public class FragmentActivity extends XPanelActivity {
    private static final String DEFAULT_CLASS_NAME;
    private static final Map<String, Class> FRAGMENT_CLASSES = new HashMap();
    private static final String TAG = "FragmentActivity";
    private Map<String, BaseFragment> mFragmentMap;

    static {
        FRAGMENT_CLASSES.put(MainFragment.class.getSimpleName(), MainFragment.class);
        FRAGMENT_CLASSES.put(MainV2Fragment.class.getSimpleName(), MainV2Fragment.class);
        FRAGMENT_CLASSES.put(InfoFragment.class.getSimpleName(), InfoFragment.class);
        FRAGMENT_CLASSES.put(HistoryFragment.class.getSimpleName(), HistoryFragment.class);
        FRAGMENT_CLASSES.put(NewFragment.class.getSimpleName(), NewFragment.class);
        if (VehicleFeature.isCarTypeD55()) {
            FRAGMENT_CLASSES.put(UpgradeV2Fragment.class.getSimpleName(), UpgradeV2Fragment.class);
            FRAGMENT_CLASSES.put(ScheduleV2Fragment.class.getSimpleName(), ScheduleV2Fragment.class);
        } else {
            FRAGMENT_CLASSES.put(UpgradeFragment.class.getSimpleName(), UpgradeFragment.class);
            FRAGMENT_CLASSES.put(ParkFragment.class.getSimpleName(), ParkFragment.class);
            FRAGMENT_CLASSES.put(ScheduleFragment.class.getSimpleName(), ScheduleFragment.class);
        }
        FRAGMENT_CLASSES.put(CheckFragment.class.getSimpleName(), CheckFragment.class);
        FRAGMENT_CLASSES.put(UsbFragment.class.getSimpleName(), UsbFragment.class);
        FRAGMENT_CLASSES.put(NewV2Fragment.class.getSimpleName(), NewV2Fragment.class);
        FRAGMENT_CLASSES.put(VersionDetailFragment.class.getSimpleName(), VersionDetailFragment.class);
        FRAGMENT_CLASSES.put(VersionDetailV2Fragment.class.getSimpleName(), VersionDetailV2Fragment.class);
        DEFAULT_CLASS_NAME = CampaignFeatureHelper.getMainFragmentClass().getSimpleName();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.app.XPanelActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_fragment);
        this.mFragmentMap = new HashMap();
        showFragment(getIntent().getStringExtra(Config.EXTRA_KEY_FRAGMENT), null);
        OTAManager.sendStartFotaService();
        ActivityContainer.getInstance().addActivity(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.app.XPanelActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity
    public void onPostCreate(@Nullable Bundle bundle) {
        super.onPostCreate(bundle);
        getLayoutBuilder().setX(ResourceUtils.dip2px((int) ResourceUtils.getDimen(R.dimen.window_x))).setY(ResourceUtils.dip2px((int) ResourceUtils.getDimen(R.dimen.window_y))).setWidth(ResourceUtils.dip2px((int) ResourceUtils.getDimen(R.dimen.window_width))).setHeight(ResourceUtils.dip2px((int) ResourceUtils.getDimen(R.dimen.window_height)));
        if (1 == getResources().getConfiguration().orientation) {
            getLayoutBuilder().setGravity(17);
        } else {
            getLayoutBuilder().setGravity(BadgeDrawable.TOP_START);
        }
        getLayoutBuilder().apply();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        Bundle bundleExtra = getIntent().getBundleExtra(Config.EXTRA_PARAM);
        if (bundleExtra != null) {
            String string = bundleExtra.getString("packageName");
            LogUtils.i(TAG, "packageName:" + string);
            return;
        }
        OTAManager.handleXLogoClicked();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ActivityContainer.getInstance().removeActivity(this);
        OTAManager.sendStopFotaServiceAndActivity();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        showFragment(intent.getStringExtra(Config.EXTRA_KEY_FRAGMENT), null);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void showFragment(String str, Class cls) {
        LogUtils.d(TAG, "showFragment: " + str);
        if (TextUtils.isEmpty(str)) {
            str = DEFAULT_CLASS_NAME;
        }
        try {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            hideFragments(beginTransaction);
            Class cls2 = FRAGMENT_CLASSES.get(str);
            if (cls2 != null) {
                BaseFragment baseFragment = this.mFragmentMap.get(str);
                if (baseFragment == null) {
                    try {
                        baseFragment = (BaseFragment) cls2.newInstance();
                        beginTransaction.add(R.id.fl_fragment_container, baseFragment);
                        this.mFragmentMap.put(str, baseFragment);
                    } catch (Exception e) {
                        LogUtils.e(TAG, e, "New instance fail: %s", str);
                        return;
                    }
                }
                baseFragment.setPreviousClass(cls);
                beginTransaction.show(baseFragment);
                beginTransaction.commit();
                if (baseFragment instanceof CheckFragment) {
                    setCloseOnTouchOutside(false);
                    setCloseOnPauseOneshot(false);
                    return;
                }
                setCloseOnTouchOutside(true);
                setCloseOnPauseOneshot(true);
            }
        } catch (Exception e2) {
            LogUtils.e(TAG, e2, "Show fragment Exception");
        }
    }

    private void hideFragments(FragmentTransaction fragmentTransaction) {
        for (String str : this.mFragmentMap.keySet()) {
            BaseFragment baseFragment = this.mFragmentMap.get(str);
            if (baseFragment != null) {
                fragmentTransaction.hide(baseFragment);
            }
        }
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

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEvent(final ActivityEvent activityEvent) {
        if (getClass().getName().equals(activityEvent.getClazz().getName())) {
            runOnUiThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$FragmentActivity$xgIPW29UZEvawtdSbyNkMR8kLE0
                @Override // java.lang.Runnable
                public final void run() {
                    FragmentActivity.this.lambda$onEvent$1$FragmentActivity(activityEvent);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onEvent$1$FragmentActivity(ActivityEvent activityEvent) {
        BaseFragment baseFragment;
        if (activityEvent.getAction() == Action.START_FRAGMENT) {
            final Map map = (Map) activityEvent.getData();
            if (map != null) {
                String str = (String) map.get(Config.EXTRA_KEY_FRAGMENT);
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                showFragment(str, null);
                if (str.equals(UsbFragment.class.getSimpleName())) {
                    ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$FragmentActivity$_pP7zD1qYa_rzEA3L9n-hocJ9DQ
                        @Override // java.lang.Runnable
                        public final void run() {
                            ActivityHelper.deliveryEvent(Action.USB_FOLODER, map, UsbFragment.class);
                        }
                    }, 1000L);
                }
            }
        } else if (activityEvent.getAction() == Action.CLOSE) {
            finishMyself();
        } else if (activityEvent.getAction() == Action.CAMPAIGN_CANCEL && ActivityUtils.isForeground(getApplicationContext(), FragmentActivity.class.getName()) && (baseFragment = this.mFragmentMap.get(CampaignFeatureHelper.getMainFragmentClass().getSimpleName())) != null && baseFragment.isVisible()) {
            baseFragment.refreshData();
        }
    }
}
