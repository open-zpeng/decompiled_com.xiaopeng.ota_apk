package com.xiaopeng.ota.view.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.xiaopeng.libtheme.ThemeManager;
import com.xiaopeng.libtheme.ThemeViewModel;
import com.xiaopeng.xui.app.XDialog;
import com.xiaopeng.xui.app.XDialogSystemType;
/* loaded from: classes2.dex */
public class BaseDialog extends XDialog {
    protected boolean mContentUserScroll;
    protected Context mContext;
    protected ThemeViewModel mThemeViewModel;

    public void onThemeChanged(boolean z) {
    }

    public BaseDialog(@NonNull final Context context) {
        super(context);
        initViews();
        this.mThemeViewModel = ThemeViewModel.create(context, null);
        this.mThemeViewModel.setCallback(new ThemeViewModel.OnCallback() { // from class: com.xiaopeng.ota.view.dialog.-$$Lambda$BaseDialog$ZaECP1Rf_8JKhq0mM89rinK825A
            @Override // com.xiaopeng.libtheme.ThemeViewModel.OnCallback
            public final void onThemeChanged() {
                BaseDialog.this.lambda$new$0$BaseDialog(context);
            }
        });
    }

    public /* synthetic */ void lambda$new$0$BaseDialog(Context context) {
        onThemeChanged(!ThemeManager.isNightMode(context));
    }

    public BaseDialog(@NonNull Context context, int i) {
        super(context, i);
        initViews();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initViews() {
        this.mContentUserScroll = true;
        setSystemDialog(XDialogSystemType.TYPE_SYSTEM_DIALOG);
        View createContentView = createContentView();
        if (createContentView != null) {
            setCustomView(createContentView, this.mContentUserScroll);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View createContentView() {
        this.mContext = getDialog().getContext();
        return null;
    }

    public View getParentView(int i) {
        return LayoutInflater.from(this.mContext).inflate(i, (ViewGroup) null);
    }

    @Override // com.xiaopeng.xui.app.XDialog
    public boolean isShowing() {
        return getDialog().isShowing();
    }
}
