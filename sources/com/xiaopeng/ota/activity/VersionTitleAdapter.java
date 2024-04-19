package com.xiaopeng.ota.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.xiaopeng.ota.R;
import com.xiaopeng.xui.widget.XTextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class VersionTitleAdapter extends RecyclerView.Adapter<Holder> implements View.OnClickListener {
    private static final String TAG = "VersionTitleAdapter";
    private Context mContext;
    private OnItemClickedListener mListener;
    private List<VersionTitle> mData = new ArrayList();
    private Map<Integer, Holder> mHolderCache = new HashMap();
    private int mSelectedPosition = 0;

    /* loaded from: classes2.dex */
    interface OnItemClickedListener {
        void onItemClicked(int i);
    }

    public VersionTitleAdapter(Context context) {
        this.mContext = context;
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.mListener = onItemClickedListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(this.mContext).inflate(R.layout.layout_version_title_container, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Holder holder, int i) {
        if (!this.mHolderCache.containsKey(Integer.valueOf(i))) {
            this.mHolderCache.put(Integer.valueOf(i), holder);
        }
        setItemInfo(holder, this.mData.get(i), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mData.size();
    }

    public void setData(List<VersionTitle> list) {
        this.mData.clear();
        this.mData.addAll(list);
        notifyDataSetChanged();
    }

    private void setItemInfo(Holder holder, VersionTitle versionTitle, int i) {
        holder.mTvTitle.setTag(Integer.valueOf(i));
        holder.mTvTitle.setOnClickListener(this);
        holder.mTvTitle.setText(versionTitle.getTitle());
        holder.mTvTitle.setTextColor(this.mContext.getResources().getColorStateList(R.color.x_catalog_bar_text_color_selector, this.mContext.getTheme()));
        setPositionSelected(holder, i);
    }

    private void setPositionSelected(Holder holder, int i) {
        if (i == this.mSelectedPosition) {
            holder.mTvTitle.setSelected(true);
        } else {
            holder.mTvTitle.setSelected(false);
        }
    }

    public void setItemChanged(int i) {
        this.mSelectedPosition = i;
        for (Map.Entry<Integer, Holder> entry : this.mHolderCache.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                setPositionSelected(entry.getValue(), entry.getKey().intValue());
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        this.mSelectedPosition = intValue;
        OnItemClickedListener onItemClickedListener = this.mListener;
        if (onItemClickedListener != null) {
            onItemClickedListener.onItemClicked(intValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class Holder extends RecyclerView.ViewHolder {
        private XTextView mTvTitle;

        public Holder(View view) {
            super(view);
            this.mTvTitle = (XTextView) view.findViewById(R.id.tv_title);
        }
    }
}
