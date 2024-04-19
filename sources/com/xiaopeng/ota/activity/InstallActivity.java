package com.xiaopeng.ota.activity;

import androidx.constraintlayout.widget.Group;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.xui.widget.XImageView;
import com.xiaopeng.xui.widget.XProgressBar;
import com.xiaopeng.xui.widget.XTextView;
/* loaded from: classes2.dex */
public class InstallActivity extends BaseInstallActivity {
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
    public void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }
}
