package com.xiaopeng.ota.presenter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ServiceSpecificException;
import android.os.UpdateEngine;
import android.os.UpdateEngineCallback;
import android.util.Log;
import com.xiaopeng.lib.utils.view.ToastUtils;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.activity.FragmentActivity;
import com.xiaopeng.ota.activity.InstallActivity;
import com.xiaopeng.ota.helper.ActivityHelper;
import com.xiaopeng.ota.presenter.AdbPresenter;
import com.xiaopeng.ota.presenter.update.cdu.BootController;
import com.xiaopeng.ota.presenter.update.cdu.RebootWrapper;
import com.xiaopeng.ota.presenter.update.cdu.UpdateParser;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ResourceUtils;
import com.xiaopeng.ota.utils.ThreadUtils;
import java.io.File;
import java.io.IOException;
/* loaded from: classes2.dex */
public class AdbPresenter {
    private static final int FLAG_UPDATED_BUT_NOT_ACTIVE = 52;
    private static final String TAG = "AdbPresenter";
    private Context mContext;

    public AdbPresenter(Context context) {
        this.mContext = context;
    }

    public void adbUpdate(String str) {
        final File file = new File(str);
        if (!file.exists()) {
            ToastUtils.showToast(this.mContext, String.format(ResourceUtils.getString(R.string.adb_install_file_not_exist), str));
            LogUtils.d(TAG, "File not exist");
            return;
        }
        showInstallState(5, ResourceUtils.getString(R.string.adb_install_cdu), 0);
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.presenter.-$$Lambda$AdbPresenter$0CLui0ccgSxLwMjLHaDBAxHsd38
            @Override // java.lang.Runnable
            public final void run() {
                AdbPresenter.this.lambda$adbUpdate$0$AdbPresenter(file);
            }
        });
    }

    /* renamed from: updateCdu */
    public void lambda$adbUpdate$0$AdbPresenter(File file) {
        try {
            UpdateParser.ParsedUpdate parse = UpdateParser.parse(file);
            UpdateEngine updateEngine = new UpdateEngine();
            updateEngine.bind(new AnonymousClass1(), new Handler(this.mContext.getMainLooper()));
            try {
                updateEngine.applyPayload(parse.mUrl, parse.mOffset, parse.mSize, parse.mProps);
            } catch (ServiceSpecificException e) {
                LogUtils.w(TAG, (Throwable) e, "applyPayload fail");
                closeInstallProgress();
            }
        } catch (IOException e2) {
            LogUtils.w(TAG, e2, "Parse file fail");
            closeInstallProgress();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaopeng.ota.presenter.AdbPresenter$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 extends UpdateEngineCallback {
        AnonymousClass1() {
        }

        public void onStatusUpdate(int i, float f) {
            LogUtils.d(AdbPresenter.TAG, "onStatusUpdate, status: " + i + ", percent: " + f);
            if (i == 3) {
                AdbPresenter.this.showInstallState(1, ResourceUtils.getString(R.string.adb_install_cdu), (int) (f * 100.0f));
            } else if (i == 5) {
                AdbPresenter.this.showInstallState(1, ResourceUtils.getString(R.string.adb_install_cdu_final), 100);
            }
        }

        public void onPayloadApplicationComplete(int i) {
            Log.d(AdbPresenter.TAG, "onPayloadApplicationComplete, code: " + i);
            if (i == 0) {
                return;
            }
            if (i != 52) {
                AdbPresenter.this.closeInstallProgress();
                return;
            }
            LogUtils.d(AdbPresenter.TAG, "kUpdatedButNotActive");
            if (new BootController().prepareSlot(true)) {
                AdbPresenter.this.showInstallState(1, ResourceUtils.getString(R.string.adb_install_cdu_success), 100);
                ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.presenter.-$$Lambda$AdbPresenter$1$ESEsyuFyKLBxx98zRYMOv1ykXzY
                    @Override // java.lang.Runnable
                    public final void run() {
                        AdbPresenter.AnonymousClass1.this.lambda$onPayloadApplicationComplete$0$AdbPresenter$1();
                    }
                }, 2000L);
                return;
            }
            LogUtils.w(AdbPresenter.TAG, "Change slot fail");
            AdbPresenter.this.closeInstallProgress();
        }

        public /* synthetic */ void lambda$onPayloadApplicationComplete$0$AdbPresenter$1() {
            new RebootWrapper(AdbPresenter.this.mContext).reboot("Reboot cdu by adb install cdu");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showInstallState(int i, String str, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        bundle.putString("title", str);
        bundle.putInt("progress", i2);
        bundle.putString("info", ResourceUtils.getString(R.string.adb_install_tip));
        ActivityHelper.finishActivities(FragmentActivity.class);
        ActivityHelper.startActivity(this.mContext, InstallActivity.class, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeInstallProgress() {
        LogUtils.d(TAG, "closeInstallProgress");
        Bundle bundle = new Bundle();
        bundle.putInt("type", 6);
        ActivityHelper.startActivity(this.mContext, InstallActivity.class, bundle);
    }
}
