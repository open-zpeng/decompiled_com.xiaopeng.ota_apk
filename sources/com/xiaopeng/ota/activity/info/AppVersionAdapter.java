package com.xiaopeng.ota.activity.info;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import com.android.internal.util.ArrayUtils;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.bean.AppVersion;
import com.xiaopeng.ota.utils.JsonUtils;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.xui.widget.XTextView;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class AppVersionAdapter extends ArrayAdapter<AppVersion> {
    private static final String TAG = "AppVersionAdapter";
    private Context mContext;
    private List<AppVersion> mList;
    private int mResourceId;

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public AppVersionAdapter(@NonNull Context context, int i) {
        super(context, i);
        this.mContext = context;
        this.mResourceId = i;
        this.mList = new ArrayList();
    }

    public void setData(List<AppVersion> list) {
        if (ArrayUtils.isEmpty(list)) {
            LogUtils.w(TAG, "list is empty");
            return;
        }
        this.mList.clear();
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return this.mList.size();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public AppVersion getItem(int i) {
        return this.mList.get(i);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = ((Activity) this.mContext).getLayoutInflater().inflate(this.mResourceId, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.tvAppName = (XTextView) view.findViewById(R.id.tv_app_name);
            viewHolder.tvAppVersion = (XTextView) view.findViewById(R.id.tv_app_version);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        AppVersion appVersion = this.mList.get(i);
        LogUtils.d(TAG, JsonUtils.toJson(appVersion));
        viewHolder.tvAppName.setText(appVersion.getName());
        viewHolder.tvAppVersion.setText(appVersion.getVersion());
        return view;
    }

    /* loaded from: classes2.dex */
    private class ViewHolder {
        private XTextView tvAppName;
        private XTextView tvAppVersion;

        private ViewHolder() {
        }
    }
}
