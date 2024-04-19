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
import com.xiaopeng.ota.VehicleFeature;
import com.xiaopeng.ota.activity.BaseInstallActivity;
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
public abstract class BaseInstallActivity extends BaseActivity {
    protected static final String INSTALLING_ANIM = "installing_anim.webp";
    protected static final String INSTALL_COMPLETED_ANIM = "install_completed_anim.webp";
    protected static final int LOOP_INFINITE = 0;
    protected static final int LOOP_ONCE = 1;
    protected static final String TAG = "InstallActivity";
    protected long mInstallTimeout;
    protected XImageView mIvAnim;
    protected XProgressBar mPbProgress;
    protected Timeout mTimeout = new AnonymousClass2();
    protected XTextView mTvInstallInfo;
    protected XTextView mTvProgress;
    protected XTextView mTvTimeRemainder;
    protected XTextView mTvTitle;
    protected Group mViewGroup;
    private IBinder mWakeBinder;

    private void disableWakeupEngine() {
        try {
            if (VehicleFeature.isD55() && CarServiceHelper.isIGLocalStatus()) {
                if (this.mWakeBinder != null) {
                    LogUtils.d(TAG, "Local ig on, wakeup engine has disabled already");
                    return;
                }
                LogUtils.d(TAG, "Local ig on, disable wakeup engine");
                this.mWakeBinder = new Binder();
                SpeechClient.instance().getWakeupEngine().stopDialog();
                SpeechClient.instance().getWakeupEngine().disableWakeupWithInfo(this.mWakeBinder, 1, "OTA", ConfigHelper.getString(Constants.ConfigKey.INSTALL_DISABLE_SPEECH_INFO), 0);
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Disable wakeup engine occurs Exception");
        }
    }

    private void enableWakeupEngine() {
        try {
            if (!VehicleFeature.isD55() || this.mWakeBinder == null) {
                return;
            }
            SpeechClient.instance().getWakeupEngine().enableWakeupWithInfo(this.mWakeBinder, 1, "OTA", 0);
        } catch (Exception e) {
            LogUtils.e(TAG, e, "Enable wakeup engine occurs Exception");
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
        if (VehicleFeature.isD55()) {
            enableWakeupEngine();
            enableToast();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleInstall() {
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
                    ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$BaseInstallActivity$Gyq1JRpOB_rW-n7BT0F1UiaP-FE
                        @Override // java.lang.Runnable
                        public final void run() {
                            BaseInstallActivity.this.lambda$handleInstall$4$BaseInstallActivity();
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
    /* renamed from: com.xiaopeng.ota.activity.BaseInstallActivity$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        AnonymousClass1() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BaseInstallActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$BaseInstallActivity$1$6oN-TsneOEQi5tSIl42c3sEI9_U
                @Override // java.lang.Runnable
                public final void run() {
                    BaseInstallActivity.AnonymousClass1.this.lambda$onAnimationEnd$0$BaseInstallActivity$1();
                }
            });
        }

        public /* synthetic */ void lambda$onAnimationEnd$0$BaseInstallActivity$1() {
            BaseInstallActivity.this.mViewGroup.setVisibility(8);
        }
    }

    public /* synthetic */ void lambda$handleInstall$4$BaseInstallActivity() {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$BaseInstallActivity$Mf3dOSsNOMDarkBeQEvRwFQbgLI
            @Override // java.lang.Runnable
            public final void run() {
                BaseInstallActivity.this.lambda$null$0$BaseInstallActivity();
            }
        });
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$BaseInstallActivity$mUunreGWMuCO9VUSNsKzwio028M
            @Override // java.lang.Runnable
            public final void run() {
                BaseInstallActivity.this.lambda$null$3$BaseInstallActivity();
            }
        });
    }

    public /* synthetic */ void lambda$null$0$BaseInstallActivity() {
        AnimUtils.startAlphaAnim(this.mViewGroup, AnimUtils.ACTION_CLOSE, 500L, new AnonymousClass1());
    }

    public /* synthetic */ void lambda$null$3$BaseInstallActivity() {
        startAnim(INSTALL_COMPLETED_ANIM, new AnimListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$BaseInstallActivity$KJHAGyoj_CgJFio6xlE20pWvOxo
            @Override // com.xiaopeng.ota.activity.AnimListener
            public final void onCompleted() {
                BaseInstallActivity.this.lambda$null$2$BaseInstallActivity();
            }
        }, 1);
    }

    public /* synthetic */ void lambda$null$2$BaseInstallActivity() {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$BaseInstallActivity$GReN6Z7RTok4bqlt7m6AG_dbcOI
            @Override // java.lang.Runnable
            public final void run() {
                BaseInstallActivity.this.lambda$null$1$BaseInstallActivity();
            }
        });
    }

    public /* synthetic */ void lambda$null$1$BaseInstallActivity() {
        finishMyself();
        ActivityHelper.deliveryEvent(Action.INSTALL_COMPLETED, OtaService.class);
    }

    protected synchronized void setInfo(Bundle bundle) {
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

    protected void startAnim(String str, final AnimListener animListener, int i) {
        FrameSequenceUtil.with(this.mIvAnim).asset(str).loopCount(i).loopBehavior(1).onFinishedListener(new FrameSequenceDrawable.OnFinishedListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$BaseInstallActivity$-8EiT7AXVLuDeUOuyhJct8UQGng
            @Override // android.support.rastermill.FrameSequenceDrawable.OnFinishedListener
            public final void onFinished(FrameSequenceDrawable frameSequenceDrawable) {
                BaseInstallActivity.lambda$startAnim$5(AnimListener.this, frameSequenceDrawable);
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
    /* renamed from: com.xiaopeng.ota.activity.BaseInstallActivity$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass2 extends Timeout {
        AnonymousClass2() {
        }

        @Override // com.xiaopeng.ota.model.Timeout
        public void timeout() {
            LogUtils.d(BaseInstallActivity.TAG, "Install UI timeout(%d)", Long.valueOf(BaseInstallActivity.this.mInstallTimeout));
            BaseInstallActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$BaseInstallActivity$2$QqDqMLv-kEaX8JszguNLbT3Oksc
                @Override // java.lang.Runnable
                public final void run() {
                    BaseInstallActivity.AnonymousClass2.this.lambda$timeout$0$BaseInstallActivity$2();
                }
            });
        }

        public /* synthetic */ void lambda$timeout$0$BaseInstallActivity$2() {
            BaseInstallActivity.this.finishMyself();
            BaseInstallActivity.this.finish();
        }
    }

    protected void setTimeout(int i) {
        this.mTimeout.reset();
        if (i == 0 || i == 1 || i == 3 || i == 4 || i == 5) {
            this.mTimeout.block(this.mInstallTimeout);
        }
    }
}
