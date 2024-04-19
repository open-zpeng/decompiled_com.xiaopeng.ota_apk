package com.xiaopeng.ota.view.dialog;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import com.xiaopeng.ota.R;
import com.xiaopeng.xui.widget.XTextView;
/* loaded from: classes2.dex */
public class TextDialog extends BaseDialog {
    private static final String TAG = "TextDialog";
    private XTextView mTvContent;

    public TextDialog(@NonNull Context context) {
        super(context);
        initDialog();
    }

    public TextDialog(@NonNull Context context, int i) {
        super(context, i);
        initDialog();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.view.dialog.BaseDialog
    public View createContentView() {
        super.createContentView();
        View parentView = getParentView(R.layout.dialog_text);
        this.mTvContent = (XTextView) parentView.findViewById(R.id.tv_content);
        return parentView;
    }

    private void initDialog() {
        getDialog().setCancelable(true);
    }

    public void setContent(String str) {
        this.mTvContent.setText(str);
    }
}
