package com.xiaopeng.ota.activity;

import android.support.rastermill.FrameSequenceUtil;
import android.view.View;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.helper.ActivityHelper;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.helper.DialogHelper;
import com.xiaopeng.ota.utils.ViewUtil;
import com.xiaopeng.xui.widget.XImageView;
import com.xiaopeng.xui.widget.XTextView;
/* loaded from: classes2.dex */
public class CheckFragment extends BaseFragment {
    private static final String ANIM_DAY_RES = "p7_checking_anim.webp";
    private static final String TAG = "CheckFragment";
    private XImageView mIvLogo;
    private XTextView mTvDesc;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public String getFragmentName() {
        return TAG;
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    protected int getLayoutId() {
        return R.layout.fragment_check;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void initViews(View view) {
        super.initViews(view);
        setTitle(ConfigHelper.getString(Constants.ConfigKey.TITLE_CHECK));
        this.mIvLogo = (XImageView) view.findViewById(R.id.iv_checking_logo);
        this.mTvDesc = (XTextView) view.findViewById(R.id.tv_desc);
        this.mTvDesc.setText(ConfigHelper.getString(Constants.ConfigKey.TITLE_CHECKING));
        ViewUtil.onPreDraw(view, new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$CheckFragment$p12rT6LrCBw2FuhrpoOoUD7VPgw
            @Override // java.lang.Runnable
            public final void run() {
                CheckFragment.this.lambda$initViews$0$CheckFragment();
            }
        });
    }

    public /* synthetic */ void lambda$initViews$0$CheckFragment() {
        FrameSequenceUtil.with(this.mIvLogo).asset(ANIM_DAY_RES).loopBehavior(2).apply();
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.title_ib_close) {
            this.mDialogHelper.promptCancelUpgradeConfirmDialog(new DialogHelper.DialogListener() { // from class: com.xiaopeng.ota.activity.CheckFragment.1
                @Override // com.xiaopeng.ota.helper.DialogHelper.DialogListener, com.xiaopeng.ota.helper.DialogHelper.DialogInterface
                public void onOkClick() {
                    CheckFragment.this.mTvDesc.setText(ConfigHelper.getString(Constants.ConfigKey.TITLE_CHECKING_CANCEL));
                    ActivityHelper.cancelUpgrade();
                }
            });
        }
    }
}
