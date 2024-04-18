package com.xiaopeng.ota.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.rastermill.FrameSequenceDrawable;
import android.support.rastermill.FrameSequenceUtil;
import android.text.TextUtils;
import android.view.MotionEvent;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import com.xiaopeng.ota.Config;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.VehicleFeature;
import com.xiaopeng.ota.activity.InstallActivity;
import com.xiaopeng.ota.bean.Action;
import com.xiaopeng.ota.helper.ActivityHelper;
import com.xiaopeng.ota.helper.CarServiceHelper;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.model.Timeout;
import com.xiaopeng.ota.system.OtaService;
import com.xiaopeng.ota.utils.AnimUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.speech.SpeechClient;
import com.xiaopeng.xui.widget.XImageView;
import com.xiaopeng.xui.widget.XProgressBar;
import com.xiaopeng.xui.widget.XTextView;
/* loaded from: classes2.dex */
public class InstallActivity extends BaseActivity {
    private static final String INSTALLING_ANIM = "installing_anim.webp";
    private static final String INSTALL_COMPLETED_ANIM = "install_completed_anim.webp";
    private static final int LOOP_INFINITE = 0;
    private static final int LOOP_ONCE = 1;
    private static final String TAG = "InstallActivity";
    private long mInstallTimeout;
    private XImageView mIvAnim;
    private XProgressBar mPbProgress;
    private Timeout mTimeout = new AnonymousClass2();
    private XTextView mTvInstallInfo;
    private XTextView mTvProgress;
    private XTextView mTvTimeRemainder;
    private XTextView mTvTitle;
    private Group mViewGroup;
    private IBinder mWakeBinder;

    private void disableWakeupEngine() {
        if ("D55".equals(VehicleFeature.getCarType()) && CarServiceHelper.isIGLocalStatus()) {
            if (this.mWakeBinder != null) {
                LogUtils.d(TAG, "Local ig on, wakeup engine has disabled already");
                return;
            }
            LogUtils.d(TAG, "Local ig on, disable wakeup engine");
            this.mWakeBinder = new Binder();
            SpeechClient.instance().getWakeupEngine().stopDialog();
            SpeechClient.instance().getWakeupEngine().disableWakeupWithInfo(this.mWakeBinder, 1, "OTA", ConfigHelper.getString(Constants.ConfigKey.INSTALL_DISABLE_SPEECH_INFO), 0);
        }
    }

    private void enableWakeupEngine() {
        if (!"D55".equals(VehicleFeature.getCarType()) || this.mWakeBinder == null) {
            return;
        }
        SpeechClient.instance().getWakeupEngine().enableWakeupWithInfo(this.mWakeBinder, 1, "OTA", 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if ("D55".equals(VehicleFeature.getCarType())) {
            disableToast();
        }
    }

    @Override // com.xiaopeng.ota.activity.BaseActivity
    protected int getLayoutId() {
        return R.layout.activity_install;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseActivity
    public void onBeforeSetContent() {
        this.mInstallTimeout = ConfigHelper.getInt(Constants.ConfigKey.INSTALL_UI_TIMEOUT);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseActivity
    public void initViews() {
        super.initViews();
        this.mIvAnim = (XImageView) findViewById(R.id.iv_anim);
        this.mTvProgress = (XTextView) findViewById(R.id.tv_progress);
        this.mTvTitle = (XTextView) findViewById(R.id.tv_title);
        this.mTvTimeRemainder = (XTextView) findViewById(R.id.tv_time_remainder);
        this.mPbProgress = (XProgressBar) findViewById(R.id.pb_progress);
        this.mTvInstallInfo = (XTextView) findViewById(R.id.tv_install_info);
        this.mViewGroup = (Group) findViewById(R.id.g_views);
        handleInstall();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleInstall();
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtils.d(TAG, "onBackPressed");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        FrameSequenceUtil.destroy(this.mIvAnim);
        if ("D55".equals(VehicleFeature.getCarType())) {
            enableWakeupEngine();
            enableToast();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    private void handleInstall() {
        Bundle bundleExtra = getIntent().getBundleExtra(Config.EXTRA_PARAM);
        if (bundleExtra != null) {
            int i = bundleExtra.getInt("type");
            setTimeout(i);
            switch (i) {
                case 0:
                    LogUtils.d(TAG, "Handle install start");
                    disableWakeupEngine();
                    setInfo(bundleExtra);
                    startAnim(INSTALLING_ANIM, null, 0);
                    if (this.mViewGroup.getVisibility() != 0) {
                        this.mViewGroup.setVisibility(0);
                        return;
                    }
                    return;
                case 1:
                    setInfo(bundleExtra);
                    if (this.mViewGroup.getVisibility() != 0) {
                        disableWakeupEngine();
                        startAnim(INSTALLING_ANIM, null, 0);
                        this.mViewGroup.setVisibility(0);
                        return;
                    }
                    return;
                case 2:
                    setInfo(bundleExtra);
                    ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$InstallActivity$xNvCh6vSkweWP-39PGDB27vohb8
                        @Override // java.lang.Runnable
                        public final void run() {
                            InstallActivity.this.lambda$handleInstall$4$InstallActivity();
                        }
                    }, 2000L);
                    return;
                case 3:
                case 4:
                    setInfo(bundleExtra);
                    return;
                case 5:
                    LogUtils.d(TAG, "Handle usb install start");
                    this.mViewGroup.setVisibility(0);
                    startAnim(INSTALLING_ANIM, null, 0);
                    setInfo(bundleExtra);
                    return;
                case 6:
                    LogUtils.d(TAG, "Handle close");
                    this.mViewGroup.setVisibility(8);
                    finishMyself();
                    return;
                default:
                    LogUtils.w(TAG, "Handle unknown type(%d)", Integer.valueOf(i));
                    return;
            }
        }
        LogUtils.w(TAG, "Handle empty bundle");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaopeng.ota.activity.InstallActivity$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        AnonymousClass1() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            InstallActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$InstallActivity$1$JgORdNkxk_jsWe1AjwnByxxkla4
                @Override // java.lang.Runnable
                public final void run() {
                    InstallActivity.AnonymousClass1.this.lambda$onAnimationEnd$0$InstallActivity$1();
                }
            });
        }

        public /* synthetic */ void lambda$onAnimationEnd$0$InstallActivity$1() {
            InstallActivity.this.mViewGroup.setVisibility(8);
        }
    }

    public /* synthetic */ void lambda$handleInstall$4$InstallActivity() {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$InstallActivity$W1_JFFWYTkPRr7HLE1XWLfKf0iY
            @Override // java.lang.Runnable
            public final void run() {
                InstallActivity.this.lambda$null$0$InstallActivity();
            }
        });
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$InstallActivity$OGNKCS1AFbeUKTrlPxHfxbG7NH0
            @Override // java.lang.Runnable
            public final void run() {
                InstallActivity.this.lambda$null$3$InstallActivity();
            }
        });
    }

    public /* synthetic */ void lambda$null$0$InstallActivity() {
        AnimUtils.startAlphaAnim(this.mViewGroup, AnimUtils.ACTION_CLOSE, 500L, new AnonymousClass1());
    }

    public /* synthetic */ void lambda$null$3$InstallActivity() {
        startAnim(INSTALL_COMPLETED_ANIM, new AnimListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$InstallActivity$w6uXiJyyPPqitrJvngv-KMrHrmY
            @Override // com.xiaopeng.ota.activity.AnimListener
            public final void onCompleted() {
                InstallActivity.this.lambda$null$2$InstallActivity();
            }
        }, 1);
    }

    public /* synthetic */ void lambda$null$2$InstallActivity() {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$InstallActivity$pLyffhtYKxdvYNbLdcvpG5LSnkU
            @Override // java.lang.Runnable
            public final void run() {
                InstallActivity.this.lambda$null$1$InstallActivity();
            }
        });
    }

    public /* synthetic */ void lambda$null$1$InstallActivity() {
        finishMyself();
        ActivityHelper.deliveryEvent(Action.INSTALL_COMPLETED, OtaService.class);
    }

    private synchronized void setInfo(Bundle bundle) {
        String string = bundle.getString("title");
        String string2 = bundle.getString(Config.EXTRA_KEY_TIME);
        String string3 = bundle.getString("info");
        int i = bundle.getInt("progress");
        LogUtils.d(TAG, "Handle display info(progress=%d)", Integer.valueOf(i));
        if (!TextUtils.isEmpty(string)) {
            this.mTvTitle.setText(string);
        }
        if (!TextUtils.isEmpty(string2)) {
            this.mTvTimeRemainder.setText(string2);
        }
        if (!TextUtils.isEmpty(string3)) {
            this.mTvInstallInfo.setText(string3);
        }
        this.mTvProgress.setText(String.valueOf(i));
        this.mPbProgress.setProgress(i);
    }

    private void startAnim(String str, final AnimListener animListener, int i) {
        FrameSequenceUtil.with(this.mIvAnim).asset(str).loopCount(i).loopBehavior(1).onFinishedListener(new FrameSequenceDrawable.OnFinishedListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$InstallActivity$k6E8O3uOrt8TIoTMh0DO654Qd6A
            @Override // android.support.rastermill.FrameSequenceDrawable.OnFinishedListener
            public final void onFinished(FrameSequenceDrawable frameSequenceDrawable) {
                InstallActivity.lambda$startAnim$5(AnimListener.this, frameSequenceDrawable);
            }
        }).applyAsync();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$startAnim$5(AnimListener animListener, FrameSequenceDrawable frameSequenceDrawable) {
        if (animListener != null) {
            animListener.onCompleted();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaopeng.ota.activity.InstallActivity$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass2 extends Timeout {
        AnonymousClass2() {
        }

        @Override // com.xiaopeng.ota.model.Timeout
        public void timeout() {
            LogUtils.d(InstallActivity.TAG, "Install UI timeout(%d)", Long.valueOf(InstallActivity.this.mInstallTimeout));
            InstallActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$InstallActivity$2$fhkhgdDcikUp-J-83-41u2GjX0k
                @Override // java.lang.Runnable
                public final void run() {
                    InstallActivity.AnonymousClass2.this.lambda$timeout$0$InstallActivity$2();
                }
            });
        }

        public /* synthetic */ void lambda$timeout$0$InstallActivity$2() {
            InstallActivity.this.finishMyself();
            InstallActivity.this.finish();
        }
    }

    private void setTimeout(int i) {
        this.mTimeout.reset();
        if (i == 0 || i == 1 || i == 3 || i == 4 || i == 5) {
            this.mTimeout.block(this.mInstallTimeout);
        }
    }
}
