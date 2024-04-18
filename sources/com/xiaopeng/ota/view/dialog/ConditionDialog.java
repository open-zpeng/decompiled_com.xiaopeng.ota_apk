package com.xiaopeng.ota.view.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import com.android.internal.util.ArrayUtils;
import com.xiaopeng.lib.utils.view.UIUtils;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ResourceUtils;
import com.xiaopeng.xui.app.XDialog;
import com.xiaopeng.xui.app.XDialogInterface;
import com.xiaopeng.xui.widget.XLinearLayout;
import com.xiaopeng.xui.widget.XTextView;
import java.util.List;
/* loaded from: classes2.dex */
public class ConditionDialog extends BaseDialog {
    private static final String TAG = "ConditionDialog";
    private XDialogInterface.OnClickListener mListener;
    private XLinearLayout mLlContent;
    private int mTextSize;

    public ConditionDialog(@NonNull Context context) {
        super(context);
    }

    public ConditionDialog(@NonNull Context context, int i) {
        super(context, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.view.dialog.BaseDialog
    public View createContentView() {
        super.createContentView();
        View parentView = getParentView(R.layout.dialog_condition);
        this.mLlContent = (XLinearLayout) parentView.findViewById(R.id.ll_content);
        setTitle(ConfigHelper.getString(Constants.ConfigKey.DIALOG_TITLE_CONDITION));
        setPositiveButton(ConfigHelper.getString(Constants.ConfigKey.BUTTON_OK), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.ota.view.dialog.-$$Lambda$ConditionDialog$shhH5DE6ySPKv7QNMbY382F2G_o
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog, int i) {
                ConditionDialog.this.lambda$createContentView$0$ConditionDialog(xDialog, i);
            }
        });
        this.mTextSize = ResourceUtils.sp2px((int) ResourceUtils.getDimen(R.dimen.x_font_body_01_size));
        return parentView;
    }

    public /* synthetic */ void lambda$createContentView$0$ConditionDialog(XDialog xDialog, int i) {
        XDialogInterface.OnClickListener onClickListener = this.mListener;
        if (onClickListener != null) {
            onClickListener.onClick(xDialog, i);
        }
    }

    public void setOkButtonListener(XDialogInterface.OnClickListener onClickListener) {
        this.mListener = onClickListener;
    }

    public void setMismatchConditions(List<String> list) {
        if (ArrayUtils.isEmpty(list)) {
            LogUtils.d(TAG, "getTextView");
            this.mLlContent.addView(getTextView(ConfigHelper.getString(Constants.ConfigKey.DIALOG_HELP_CONTENT_MISS_CONDITIONS), true));
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            XTextView textView = getTextView(list.get(i), false);
            this.mLlContent.addView(textView);
            if (i != 0) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
                layoutParams.setMargins(0, ResourceUtils.dip2px(12), 0, 0);
                textView.setLayoutParams(layoutParams);
            }
        }
    }

    private XTextView getTextView(String str, boolean z) {
        LogUtils.d(TAG, "getTextView");
        XTextView xTextView = new XTextView(this.mContext);
        xTextView.setText(str);
        xTextView.setTextSize(this.mTextSize);
        xTextView.setLineSpacing(UIUtils.dip2px(this.mContext, 10.0f), 1.0f);
        xTextView.setTextColor(ResourceUtils.getColor(R.color.x_theme_text_02));
        if (!z) {
            xTextView.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getDrawable(R.drawable.bg_dot_condition), (Drawable) null, (Drawable) null, (Drawable) null);
            xTextView.setCompoundDrawablePadding(ResourceUtils.dip2px(22));
        }
        return xTextView;
    }
}
