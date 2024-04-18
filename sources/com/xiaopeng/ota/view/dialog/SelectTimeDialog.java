package com.xiaopeng.ota.view.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import androidx.annotation.NonNull;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.utils.TimeUtils;
import com.xiaopeng.xui.widget.XTextView;
import com.xiaopeng.xui.widget.timepicker.XTimePicker;
/* loaded from: classes2.dex */
public class SelectTimeDialog extends BaseDialog {
    private static final String TAG = "SelectTimeDialog";
    private XTimePicker mTpScheduleTime;
    private XTextView mTvDesc;
    private XTextView mTvTitle;

    public SelectTimeDialog(@NonNull Context context) {
        super(context);
    }

    public SelectTimeDialog(@NonNull Context context, int i) {
        super(context, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.ota.view.dialog.BaseDialog
    public View createContentView() {
        super.createContentView();
        View parentView = getParentView(R.layout.dialog_select_time);
        this.mTvTitle = (XTextView) parentView.findViewById(R.id.tv_title);
        this.mTvDesc = (XTextView) parentView.findViewById(R.id.tv_desc);
        this.mTpScheduleTime = (XTimePicker) parentView.findViewById(R.id.tp_schedule_time);
        return parentView;
    }

    public void setScheduleDefaultTime(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            Pair<Integer, Integer> hourAndMinute = TimeUtils.getHourAndMinute(str);
            this.mTpScheduleTime.setHour(((Integer) hourAndMinute.first).intValue());
            this.mTpScheduleTime.setMinute(((Integer) hourAndMinute.second).intValue());
        } catch (Exception unused) {
        }
    }

    public void setTips(String str) {
        this.mTvTitle.setText(str);
    }

    public void setDesc(String str) {
        this.mTvDesc.setText(str);
    }

    public int getSelectedHour() {
        return this.mTpScheduleTime.getCurrentHour().intValue();
    }

    public int getSelectedMinute() {
        return this.mTpScheduleTime.getCurrentMinute().intValue();
    }
}
