package com.xiaopeng.ota.view.dialog;

import android.content.Context;
import android.support.rastermill.FrameSequenceUtil;
import android.view.View;
import com.xiaopeng.libtheme.ThemeManager;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.utils.ViewUtil;
import com.xiaopeng.xui.widget.XImageView;
import com.xiaopeng.xui.widget.XTextView;
/* loaded from: classes2.dex */
public class SotaUpgradeDialog extends BaseDialog {
    private static final String WEBP_FILE_NAME = "anim_sota_upgrade.webp";
    private static final String WEBP_FILE_NAME_NIGHT = "anim_sota_upgrade_night.webp";
    private XImageView mIvLogo;
    private View mParentView;
    private XTextView mTvDetail;
    private XTextView mTvWaitTips;

    public SotaUpgradeDialog(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.view.dialog.BaseDialog
    public void initViews() {
        super.initViews();
        setTitle(ConfigHelper.getString(Constants.ConfigKey.DIALOG_TITLE_SOTA_UPGRADE));
        this.mTvWaitTips.setText(ConfigHelper.getString(Constants.ConfigKey.TIPS_SOTA_UPGRADE_WAIT));
        loadAnimate(ThemeManager.isNightMode(getDialog().getContext()) ? WEBP_FILE_NAME_NIGHT : WEBP_FILE_NAME);
        getDialog().setCancelable(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.view.dialog.BaseDialog
    public View createContentView() {
        super.createContentView();
        this.mParentView = super.getParentView(R.layout.dialog_sota_upgrade);
        this.mIvLogo = (XImageView) this.mParentView.findViewById(R.id.iv_logo);
        this.mTvWaitTips = (XTextView) this.mParentView.findViewById(R.id.tv_wait_tips);
        this.mTvDetail = (XTextView) this.mParentView.findViewById(R.id.tv_detail);
        return this.mParentView;
    }

    public void updateDetailTips(String str) {
        this.mTvDetail.setText(str);
    }

    @Override // com.xiaopeng.ota.view.dialog.BaseDialog
    public void onThemeChanged(boolean z) {
        super.onThemeChanged(z);
        XImageView xImageView = this.mIvLogo;
        if (xImageView != null) {
            FrameSequenceUtil.destroy(xImageView);
            loadAnimate(z ? WEBP_FILE_NAME : WEBP_FILE_NAME_NIGHT);
        }
    }

    private void loadAnimate(final String str) {
        ViewUtil.onPreDraw(this.mParentView, new Runnable() { // from class: com.xiaopeng.ota.view.dialog.-$$Lambda$SotaUpgradeDialog$HaHc989xxGbtyQglzl76cuhmNoU
            @Override // java.lang.Runnable
            public final void run() {
                SotaUpgradeDialog.this.lambda$loadAnimate$0$SotaUpgradeDialog(str);
            }
        });
    }

    public /* synthetic */ void lambda$loadAnimate$0$SotaUpgradeDialog(String str) {
        FrameSequenceUtil.with(this.mIvLogo).asset(str).loopBehavior(2).apply();
    }

    @Override // com.xiaopeng.xui.app.XDialog
    public void dismiss() {
        super.dismiss();
        XImageView xImageView = this.mIvLogo;
        if (xImageView != null) {
            FrameSequenceUtil.destroy(xImageView);
        }
    }
}
