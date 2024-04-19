package com.xiaopeng.ota.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.constraintlayout.widget.Group;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.sdk.common.log.Logger;
import com.xiaopeng.ota.utils.ResourceUtils;
import com.xiaopeng.xui.widget.XImageView;
import com.xiaopeng.xui.widget.XProgressBar;
import com.xiaopeng.xui.widget.XTextView;
/* loaded from: classes2.dex */
public class PortraitInstallActivity extends BaseInstallActivity {
    private View mInstallView;
    private boolean mWindowShow;

    @Override // com.xiaopeng.ota.activity.BaseActivity
    protected int getLayoutId() {
        return R.layout.activity_install_prompt;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseActivity
    public void onBeforeSetContent() {
        this.mInstallTimeout = ConfigHelper.getInt(Constants.ConfigKey.INSTALL_UI_TIMEOUT);
        if (this.mWindowShow) {
            Logger.d("InstallActivity", "window is shown", new Object[0]);
            return;
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2021;
        layoutParams.flags = 150995240;
        layoutParams.gravity = 17;
        layoutParams.format = 1;
        this.mInstallView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_install, (ViewGroup) null);
        this.mInstallView.setBackgroundColor(ResourceUtils.getColor(R.color.install_activity_background));
        ((WindowManager) getApplicationContext().getSystemService("window")).addView(this.mInstallView, layoutParams);
        this.mWindowShow = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseActivity
    public void initViews() {
        this.mIvAnim = (XImageView) this.mInstallView.findViewById(R.id.iv_anim);
        this.mTvProgress = (XTextView) this.mInstallView.findViewById(R.id.tv_progress);
        this.mTvTitle = (XTextView) this.mInstallView.findViewById(R.id.tv_title);
        this.mTvTimeRemainder = (XTextView) this.mInstallView.findViewById(R.id.tv_time_remainder);
        this.mPbProgress = (XProgressBar) this.mInstallView.findViewById(R.id.pb_progress);
        this.mTvInstallInfo = (XTextView) this.mInstallView.findViewById(R.id.tv_install_info);
        this.mViewGroup = (Group) this.mInstallView.findViewById(R.id.g_views);
        handleInstall();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseInstallActivity, com.xiaopeng.ota.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        removeView();
    }

    private void removeView() {
        WindowManager windowManager = (WindowManager) getSystemService("window");
        View view = this.mInstallView;
        if (view != null) {
            windowManager.removeView(view);
            this.mInstallView = null;
        }
        this.mWindowShow = false;
    }
}
