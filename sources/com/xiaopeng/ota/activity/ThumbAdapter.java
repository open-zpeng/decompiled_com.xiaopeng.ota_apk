package com.xiaopeng.ota.activity;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.android.internal.util.ArrayUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.activity.VersionAdapter;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.ResourceUtils;
import com.xiaopeng.ota.view.RoundImageView;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class ThumbAdapter extends BaseAdapter {
    private static final String TAG = "ThumbAdapter";
    private Context mContext;
    private List<String> mData = new ArrayList();
    private VersionAdapter.OnThumbClickedListener mListener;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public ThumbAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<String> list) {
        if (ArrayUtils.isEmpty(list)) {
            LogUtils.e(TAG, "Data empty");
            return;
        }
        this.mData.clear();
        this.mData.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnThumbClickedListener(VersionAdapter.OnThumbClickedListener onThumbClickedListener) {
        this.mListener = onThumbClickedListener;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mData.size();
    }

    @Override // android.widget.Adapter
    public String getItem(int i) {
        return this.mData.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        RoundImageView roundImageView;
        if (view == null) {
            view = ((Activity) this.mContext).getLayoutInflater().inflate(R.layout.listview_thumb_item, viewGroup, false);
            roundImageView = (RoundImageView) view.findViewById(R.id.iv_thumbnail);
            view.setTag(roundImageView);
        } else {
            roundImageView = (RoundImageView) view.getTag();
        }
        final String str = this.mData.get(i);
        Glide.with(this.mContext).load(str).apply(new RequestOptions().override(ResourceUtils.getInt(R.integer.thumb_width), ResourceUtils.getInt(R.integer.thumb_height))).into(roundImageView);
        roundImageView.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$ThumbAdapter$2_83Y-Pw-TRIdEhsDD_y2D_pQLU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ThumbAdapter.this.lambda$getView$0$ThumbAdapter(str, view2);
            }
        });
        return view;
    }

    public /* synthetic */ void lambda$getView$0$ThumbAdapter(String str, View view) {
        VersionAdapter.OnThumbClickedListener onThumbClickedListener = this.mListener;
        if (onThumbClickedListener != null) {
            onThumbClickedListener.onClicked(str);
        }
    }
}
