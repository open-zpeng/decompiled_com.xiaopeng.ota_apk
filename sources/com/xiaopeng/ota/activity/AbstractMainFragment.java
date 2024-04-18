package com.xiaopeng.ota.activity;

import com.xiaopeng.fota.sdk.UpgradeUtils;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.helper.DialogHelper;
import com.xiaopeng.ota.presenter.event.EventPresenter;
import com.xiaopeng.ota.utils.ThreadUtils;
import com.xiaopeng.xui.widget.XSwitch;
/* loaded from: classes2.dex */
public abstract class AbstractMainFragment extends BaseFragment {
    /* JADX INFO: Access modifiers changed from: protected */
    public void changeAutoUpgrade(XSwitch xSwitch, boolean z) {
        if (OTAManager.getPreferencesManager().supportAutoUpgrade() == z) {
            return;
        }
        EventPresenter.getInstance().sendAutoUpgradeClicked(!OTAManager.getPreferencesManager().supportAutoUpgrade());
        if (z) {
            this.mDialogHelper.promptAutoUpgrade(new AnonymousClass1(xSwitch));
        } else {
            this.mDialogHelper.closeAutoUpgrade(new AnonymousClass2(xSwitch));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaopeng.ota.activity.AbstractMainFragment$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 extends DialogHelper.DialogListener {
        final /* synthetic */ XSwitch val$autoUpgradeView;

        AnonymousClass1(XSwitch xSwitch) {
            this.val$autoUpgradeView = xSwitch;
        }

        @Override // com.xiaopeng.ota.helper.DialogHelper.DialogListener, com.xiaopeng.ota.helper.DialogHelper.DialogInterface
        public void onOkClick() {
            final XSwitch xSwitch = this.val$autoUpgradeView;
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$AbstractMainFragment$1$CqNqWOeYEyhfLEY5TZRD7xZoPX4
                @Override // java.lang.Runnable
                public final void run() {
                    XSwitch.this.setChecked(true);
                }
            });
            EventPresenter.getInstance().sendOkClickedEvent("AutoUpgrade");
            UpgradeUtils.OtaSystemEvent(UpgradeUtils.EVENT_SYNC_INFO);
        }

        @Override // com.xiaopeng.ota.helper.DialogHelper.DialogListener, com.xiaopeng.ota.helper.DialogHelper.DialogInterface
        public void onCancelClick() {
            final XSwitch xSwitch = this.val$autoUpgradeView;
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$AbstractMainFragment$1$Uj94Ls_9VHaa3qQcK10V9DKy8Jo
                @Override // java.lang.Runnable
                public final void run() {
                    XSwitch.this.setChecked(false);
                }
            });
            EventPresenter.getInstance().sendCancelClickedEvent("AutoUpgrade");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaopeng.ota.activity.AbstractMainFragment$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass2 extends DialogHelper.DialogListener {
        final /* synthetic */ XSwitch val$autoUpgradeView;

        AnonymousClass2(XSwitch xSwitch) {
            this.val$autoUpgradeView = xSwitch;
        }

        @Override // com.xiaopeng.ota.helper.DialogHelper.DialogListener, com.xiaopeng.ota.helper.DialogHelper.DialogInterface
        public void onOkClick() {
            final XSwitch xSwitch = this.val$autoUpgradeView;
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$AbstractMainFragment$2$d9lsxgfA3yAfndc5wNxg7C0525U
                @Override // java.lang.Runnable
                public final void run() {
                    XSwitch.this.setChecked(false);
                }
            });
            EventPresenter.getInstance().sendOkClickedEvent("AutoUpgrade");
            UpgradeUtils.OtaSystemEvent(UpgradeUtils.EVENT_SYNC_INFO);
        }

        @Override // com.xiaopeng.ota.helper.DialogHelper.DialogListener, com.xiaopeng.ota.helper.DialogHelper.DialogInterface
        public void onCancelClick() {
            final XSwitch xSwitch = this.val$autoUpgradeView;
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.activity.-$$Lambda$AbstractMainFragment$2$X0WLnQtW-Gwto3OBMp42ftv-DjU
                @Override // java.lang.Runnable
                public final void run() {
                    XSwitch.this.setChecked(true);
                }
            });
            EventPresenter.getInstance().sendCancelClickedEvent("AutoUpgrade");
        }
    }
}
