package com.xiaopeng.ota.view.dialog;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.utils.StringUtils;
import com.xiaopeng.ota.utils.TimeUtils;
import com.xiaopeng.xui.widget.XTextView;
/* loaded from: classes2.dex */
public class AutoUpgradeDialog extends BaseDialog {
    private XTextView mTvContent;
    private XTextView mTvNote;

    public AutoUpgradeDialog(@NonNull Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.view.dialog.BaseDialog
    public void initViews() {
        super.initViews();
        setTitle(ConfigHelper.getString(Constants.ConfigKey.DIALOG_AUTO_UPGRADE_TITLE));
        String string = ConfigHelper.getString(Constants.ConfigKey.DEFAULT_SCHEDULE_TIME);
        this.mTvContent.setText(StringUtils.format(Constants.ConfigKey.DIALOG_AUTO_UPGRADE_CONTENT_FORMAT, TimeUtils.getReminder(string), string));
        this.mTvNote.setText(ConfigHelper.getString(Constants.ConfigKey.DIALOG_AUTO_UPGRADE_NOTE));
        getDialog().setCancelable(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.view.dialog.BaseDialog
    public View createContentView() {
        super.createContentView();
        View parentView = super.getParentView(R.layout.dialog_auto_upgrade);
        this.mTvContent = (XTextView) parentView.findViewById(R.id.tv_content);
        this.mTvNote = (XTextView) parentView.findViewById(R.id.tv_note);
        return parentView;
    }
}
