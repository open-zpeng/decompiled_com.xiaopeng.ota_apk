package com.xiaopeng.ota.activity;

import android.car.Car;
import android.content.Intent;
import android.os.Bundle;
import android.support.rastermill.FrameSequenceUtil;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import com.xiaopeng.libtheme.ThemeManager;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.VehicleFeature;
import com.xiaopeng.ota.activity.CheckActivity;
import com.xiaopeng.ota.bean.Action;
import com.xiaopeng.ota.bean.ActivityEvent;
import com.xiaopeng.ota.helper.ActivityHelper;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.model.Timeout;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ResourceUtils;
import com.xiaopeng.ota.utils.ViewUtil;
import com.xiaopeng.xui.widget.XImageView;
import com.xiaopeng.xui.widget.XTextView;
/* loaded from: classes2.dex */
public class CheckActivity extends BaseActivity {
    private static final String ANIM_DAY_RES = "anim_env_check.webp";
    private static final String ANIM_NIGHT_RES = "anim_env_check_night.webp";
    public static final String DISABLE_TOAST = "disableToast";
    private static final String TAG = "CheckActivity";
    private long mCheckTimeout;
    private XImageView mCheckingBackground;
    private XImageView mIvLogo;
    private XTextView mTvDesc;
    private XTextView mTvTitle;
    private volatile boolean mEnableToastWhileDestroy = true;
    private Timeout mTimeout = new AnonymousClass1();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaopeng.ota.activity.CheckActivity$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 extends Timeout {
        AnonymousClass1() {
        }

        @Override // com.xiaopeng.ota.model.Timeout
        public void timeout() {
            LogUtils.d(CheckActivity.TAG, "Check activity timeout(%d)", Long.valueOf(CheckActivity.this.mCheckTimeout));
            CheckActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$CheckActivity$1$KFpNq5g_BVOU0i_YiLJiFcXWSHI
                @Override // java.lang.Runnable
                public final void run() {
                    CheckActivity.AnonymousClass1.this.lambda$timeout$0$CheckActivity$1();
                }
            });
        }

        public /* synthetic */ void lambda$timeout$0$CheckActivity$1() {
            CheckActivity.this.finishMyself();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (VehicleFeature.isD55()) {
            disableToast();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    public void onPostCreate(@Nullable Bundle bundle) {
        super.onPostCreate(bundle);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        if (isOrientationPortrait()) {
            attributes.gravity = 17;
        } else {
            attributes.gravity = 8388659;
        }
        attributes.x = ResourceUtils.dip2px((int) ResourceUtils.getDimen(R.dimen.window_x));
        attributes.y = ResourceUtils.dip2px((int) ResourceUtils.getDimen(R.dimen.window_y));
        attributes.height = ResourceUtils.dip2px((int) ResourceUtils.getDimen(R.dimen.window_height));
        attributes.width = ResourceUtils.dip2px((int) ResourceUtils.getDimen(R.dimen.window_width));
        getWindow().setAttributes(attributes);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseActivity
    public void onBeforeSetContent() {
        super.onBeforeSetContent();
        this.mCheckTimeout = ConfigHelper.getInt(Constants.ConfigKey.CHECK_UI_TIMEOUT);
    }

    @Override // com.xiaopeng.ota.activity.BaseActivity
    protected int getLayoutId() {
        return R.layout.activity_check;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseActivity
    public void initViews() {
        super.initViews();
        this.mTvTitle = (XTextView) findViewById(R.id.title_tv_text);
        XTextView xTextView = this.mTvTitle;
        if (xTextView != null) {
            xTextView.setText(ConfigHelper.getString(Constants.ConfigKey.TITLE_CHECK));
        }
        this.mIvLogo = (XImageView) findViewById(R.id.iv_checking_logo);
        this.mTvDesc = (XTextView) findViewById(R.id.tv_desc);
        this.mTvDesc.setText(ConfigHelper.getString(Constants.ConfigKey.TITLE_CHECKING));
        this.mCheckingBackground = (XImageView) findViewById(R.id.iv_checking_backgroud);
        String cduType = VehicleFeature.getCduType();
        char c = 65535;
        int hashCode = cduType.hashCode();
        if (hashCode != 2562) {
            if (hashCode == 79487 && cduType.equals(Car.CAR_CDU_TYPE_Q3_D55A)) {
                c = 1;
            }
        } else if (cduType.equals("Q3")) {
            c = 0;
        }
        if (c == 0) {
            this.mCheckingBackground.setImageResource(R.mipmap.bg_checking);
        } else if (c == 1) {
            this.mCheckingBackground.setImageResource(R.mipmap.d55a_bg_checking);
        }
        final String str = ThemeManager.isNightMode(getApplicationContext()) ? ANIM_NIGHT_RES : ANIM_DAY_RES;
        ViewUtil.onPreDraw(findViewById(16908290), new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$CheckActivity$ysm-O1cUTTOySa9Iq_XhN1NDb6I
            @Override // java.lang.Runnable
            public final void run() {
                CheckActivity.this.lambda$initViews$0$CheckActivity(str);
            }
        });
        LogUtils.d(TAG, "Check activity views init");
        this.mTimeout.block(this.mCheckTimeout);
    }

    public /* synthetic */ void lambda$initViews$0$CheckActivity(String str) {
        FrameSequenceUtil.with(this.mIvLogo).asset(str).loopBehavior(2).apply();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtils.d(TAG, "onBackPressed");
    }

    /* renamed from: com.xiaopeng.ota.activity.CheckActivity$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaopeng$ota$bean$Action = new int[Action.values().length];

        static {
            try {
                $SwitchMap$com$xiaopeng$ota$bean$Action[Action.GEARLEVER_CHANGED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$xiaopeng$ota$bean$Action[Action.CLOSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseActivity
    public void onHandleEvent(ActivityEvent activityEvent) {
        int i = AnonymousClass2.$SwitchMap$com$xiaopeng$ota$bean$Action[activityEvent.getAction().ordinal()];
        if (i == 1) {
            if (activityEvent.getData() == null || !(activityEvent.getData() instanceof Integer)) {
                return;
            }
            int intValue = ((Integer) activityEvent.getData()).intValue();
            LogUtils.d(TAG, "Gear level changed level:%d", Integer.valueOf(intValue));
            if (4 != intValue) {
                ActivityHelper.cancelUpgrade();
            }
        } else if (i != 2) {
        } else {
            Object data = activityEvent.getData();
            if (data != null && (data instanceof String) && DISABLE_TOAST.equals(data)) {
                this.mEnableToastWhileDestroy = false;
            }
            onBeforeClose();
            finishMyself();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Timeout timeout = this.mTimeout;
        if (timeout != null) {
            timeout.reset();
        }
        if (VehicleFeature.isD55() && this.mEnableToastWhileDestroy) {
            enableToast();
        }
    }
}
