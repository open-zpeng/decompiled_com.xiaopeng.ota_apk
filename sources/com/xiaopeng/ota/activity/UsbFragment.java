package com.xiaopeng.ota.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.xiaopeng.fota.sdk.UpgradeUtils;
import com.xiaopeng.lib.utils.SystemPropertyUtil;
import com.xiaopeng.lib.utils.info.BuildInfoUtils;
import com.xiaopeng.lib.utils.view.ToastUtils;
import com.xiaopeng.ota.Config;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.bean.Action;
import com.xiaopeng.ota.bean.ActivityEvent;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.StringUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.ota.utils.VersionUtils;
import com.xiaopeng.xui.widget.XButton;
import com.xiaopeng.xui.widget.XTextView;
import java.io.File;
import java.util.Map;
/* loaded from: classes2.dex */
public class UsbFragment extends BaseFragment {
    private static final String TAG = "UsbFragment";
    private XButton mBtnUpgrade;
    private String mOtaFolder;
    private XTextView mTvInfo;
    private XTextView mTvSimpleVersion;
    private XTextView mTvVin;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public String getFragmentName() {
        return TAG;
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment
    protected int getLayoutId() {
        return R.layout.fragment_usb;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void initViews(View view) {
        super.initViews(view);
        setTitle(ConfigHelper.getString(Constants.ConfigKey.TITLE_USB_UPGRADE));
        this.mTvSimpleVersion = (XTextView) view.findViewById(R.id.tv_simple_version);
        Campaign activeCampaign = OTAManager.getCampaignManager().getActiveCampaign();
        if (activeCampaign != null) {
            this.mTvSimpleVersion.setText(VersionUtils.getSimpleVersion(activeCampaign.getReleaseVersion()));
        } else {
            String string = ConfigHelper.getString(Constants.ConfigKey.DEFAULT_VERSION);
            if (TextUtils.isEmpty(string)) {
                this.mTvSimpleVersion.setText(BuildInfoUtils.getSystemVersion());
            } else {
                this.mTvSimpleVersion.setText(string);
            }
        }
        this.mTvVin = (XTextView) view.findViewById(R.id.tv_vin);
        String vin = SystemPropertyUtil.getVIN();
        if (!TextUtils.isEmpty(vin)) {
            this.mTvVin.setText(StringUtils.buildString(R.string.main_vin_prefix, vin));
        } else {
            this.mTvVin.setText(StringUtils.buildString(R.string.main_vin_prefix, R.string.main_unknown));
        }
        this.mTvInfo = (XTextView) view.findViewById(R.id.tv_info);
        this.mBtnUpgrade = (XButton) view.findViewById(R.id.btn_upgrade);
        this.mBtnUpgrade.setOnClickListener(this);
        Intent intent = getActivity().getIntent();
        if (intent == null) {
            LogUtils.d(TAG, "Intent is empty");
            return;
        }
        Bundle bundleExtra = intent.getBundleExtra(Config.EXTRA_PARAM);
        if (bundleExtra != null) {
            String string2 = bundleExtra.getString(Config.EXTRA_KEY_OTA_FOLDER);
            if (!TextUtils.isEmpty(string2)) {
                this.mOtaFolder = string2;
                LogUtils.d(TAG, "get folder: " + string2);
            }
            handleProgress(null);
        }
    }

    private void handleProgress(String str) {
        LogUtils.d(TAG, "Get progress %s", str);
        if (!TextUtils.isEmpty(str)) {
            this.mBtnUpgrade.setEnabled(false);
            this.mTvInfo.setText(str);
            return;
        }
        this.mBtnUpgrade.setEnabled(true);
        this.mTvInfo.setText("");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.activity.BaseFragment
    public void onHandleEvent(ActivityEvent activityEvent) {
        Map map;
        super.lambda$onEvent$0$BaseFragment(activityEvent);
        if (isVisible()) {
            int i = AnonymousClass1.$SwitchMap$com$xiaopeng$ota$bean$Action[activityEvent.getAction().ordinal()];
            if (i == 1) {
                String str = (String) activityEvent.getData();
                if (str != null) {
                    handleProgress(str);
                }
            } else if (i == 2 && (map = (Map) activityEvent.getData()) != null) {
                String str2 = (String) map.get(Config.EXTRA_KEY_OTA_FOLDER);
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                this.mOtaFolder = str2;
                LogUtils.d(TAG, "get folder: " + str2);
            }
        }
    }

    /* renamed from: com.xiaopeng.ota.activity.UsbFragment$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaopeng$ota$bean$Action = new int[Action.values().length];

        static {
            try {
                $SwitchMap$com$xiaopeng$ota$bean$Action[Action.USB_PROGRESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$xiaopeng$ota$bean$Action[Action.USB_FOLODER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.xiaopeng.ota.activity.BaseFragment, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.btn_upgrade) {
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$UsbFragment$6bgO1V17EurrARm21LuB5nWF2hM
                @Override // java.lang.Runnable
                public final void run() {
                    UsbFragment.this.lambda$onClick$1$UsbFragment();
                }
            });
        }
    }

    public /* synthetic */ void lambda$onClick$1$UsbFragment() {
        if (new File(this.mOtaFolder).exists()) {
            LogUtils.d(TAG, "OtaFolder: " + this.mOtaFolder);
            UpgradeUtils.OtaUsbUpgrade(this.mOtaFolder);
            return;
        }
        final String format = StringUtils.format(R.string.usb_folder_not_found, this.mOtaFolder);
        LogUtils.w(TAG, format);
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$UsbFragment$W1KLykQ4-Bf40cF9zVIxeFpJr6E
            @Override // java.lang.Runnable
            public final void run() {
                UsbFragment.this.lambda$null$0$UsbFragment(format);
            }
        });
    }

    public /* synthetic */ void lambda$null$0$UsbFragment(String str) {
        ToastUtils.showToast(getContext(), str);
    }
}
