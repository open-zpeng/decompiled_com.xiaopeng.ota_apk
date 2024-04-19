package com.xiaopeng.ota.activity.info;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import com.android.internal.util.ArrayUtils;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.helper.ConfigHelper;
import com.xiaopeng.ota.presenter.update.bean.EcuVersion;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.xui.widget.XTextView;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class EcuVersionAdapter extends ArrayAdapter<EcuVersion> {
    private static final String TAG = "EcuVersionAdapter";
    private Context mContext;
    private String mFormat;
    private List<EcuVersion> mList;
    private int mResourceId;

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public EcuVersionAdapter(@NonNull Context context, int i) {
        super(context, i);
        this.mContext = context;
        this.mResourceId = i;
        this.mFormat = ConfigHelper.getFormat(Constants.ConfigKey.ECU_VERSION_SUMMARY_FORMAT);
        this.mList = new ArrayList();
    }

    public void setData(List<EcuVersion> list) {
        if (ArrayUtils.isEmpty(list)) {
            LogUtils.w(TAG, "list is empty");
            return;
        }
        this.mList.clear();
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    public void refreshData(List<EcuVersion> list) {
        if (list == null) {
            return;
        }
        boolean z = false;
        for (EcuVersion ecuVersion : list) {
            EcuVersion ecuVersionByName = getEcuVersionByName(ecuVersion.getName());
            if (ecuVersionByName == null) {
                this.mList.add(ecuVersion);
            } else if (!ecuVersion.versionEquals(ecuVersionByName)) {
                ecuVersionByName.refresh(ecuVersion);
            }
            z = true;
        }
        if (z) {
            notifyDataSetChanged();
        }
    }

    private EcuVersion getEcuVersionByName(String str) {
        for (EcuVersion ecuVersion : this.mList) {
            if (ecuVersion.getName().equals(str)) {
                return ecuVersion;
            }
        }
        return null;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return this.mList.size();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public EcuVersion getItem(int i) {
        return this.mList.get(i);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = ((Activity) this.mContext).getLayoutInflater().inflate(this.mResourceId, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        EcuVersion ecuVersion = this.mList.get(i);
        if (TextUtils.isEmpty(ecuVersion.getDesc())) {
            viewHolder.tvEcuSummary.setText(ecuVersion.getName());
        } else {
            viewHolder.tvEcuSummary.setText(String.format(this.mFormat, ecuVersion.getName(), ecuVersion.getDesc()));
        }
        viewHolder.tvSwVersion.setText(ecuVersion.getSv());
        viewHolder.tvHwVersion.setText(ecuVersion.getHv());
        viewHolder.tvFingerprint.setText(ecuVersion.getFingerprint());
        return view;
    }

    /* loaded from: classes2.dex */
    class ViewHolder {
        XTextView tvEcuSummary;
        XTextView tvFingerprint;
        XTextView tvFingerprintTitle;
        XTextView tvHwVersion;
        XTextView tvHwVersionTitle;
        XTextView tvSwVersion;
        XTextView tvSwVersionTitle;

        ViewHolder(View view) {
            this.tvEcuSummary = (XTextView) view.findViewById(R.id.tv_ecu_summary);
            this.tvSwVersionTitle = (XTextView) view.findViewById(R.id.tv_sw_version_title);
            this.tvSwVersion = (XTextView) view.findViewById(R.id.tv_sw_version);
            this.tvHwVersionTitle = (XTextView) view.findViewById(R.id.tv_hw_version_title);
            this.tvHwVersion = (XTextView) view.findViewById(R.id.tv_hw_version);
            this.tvFingerprintTitle = (XTextView) view.findViewById(R.id.tv_fingerprint_title);
            this.tvFingerprint = (XTextView) view.findViewById(R.id.tv_fingerprint);
        }
    }
}
