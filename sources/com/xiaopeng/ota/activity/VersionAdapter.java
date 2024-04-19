package com.xiaopeng.ota.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ListAdapter;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.util.ArrayUtils;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.bean.VersionInfo;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.ota.utils.WebViewUtils;
import com.xiaopeng.ota.view.HorizontalListView;
import com.xiaopeng.ota.view.SpreadListView;
import com.xiaopeng.xui.widget.XImageView;
import com.xiaopeng.xui.widget.XLinearLayout;
import com.xiaopeng.xui.widget.XTextView;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class VersionAdapter extends RecyclerView.Adapter<Holder> {
    private static final int FLAG_CLOSED = 0;
    private static final int FLAG_OPEN = 1;
    public static final int HISTORY_VERSION = 0;
    public static final int NEW_VERSION = 1;
    private static final String TAG = "VersionAdapter";
    private Context mContext;
    private List<VersionInfo> mData;
    private OnThumbClickedListener mListener;
    private int mResourceId;
    private int mType;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface OnThumbClickedListener {
        void onClicked(String str);
    }

    public VersionAdapter(Context context, List<VersionInfo> list) {
        this(context, list, 1);
    }

    public VersionAdapter(Context context, List<VersionInfo> list, int i) {
        this.mContext = context;
        this.mType = i;
        if (i == 1) {
            this.mResourceId = R.layout.layout_new_version_container;
        } else {
            this.mResourceId = R.layout.layout_history_version_container;
        }
        this.mData = new ArrayList();
        if (ArrayUtils.isEmpty(list)) {
            LogUtils.e(TAG, "Param data is empty");
        } else {
            this.mData.addAll(list);
        }
    }

    public void setOnThumbClickedListener(OnThumbClickedListener onThumbClickedListener) {
        this.mListener = onThumbClickedListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(this.mContext).inflate(this.mResourceId, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Holder holder, int i) {
        setItemInfo(holder, this.mData.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mData.size();
    }

    private void setItemInfo(final Holder holder, final VersionInfo versionInfo) {
        holder.mTvTitle.setText(versionInfo.getVersion());
        holder.mTvSubTitle.setText(versionInfo.getUpgradeTime());
        holder.mIvRight.setTag(0);
        if (this.mType == 1) {
            holder.mIvRight.setVisibility(8);
            showContent(holder, versionInfo);
            Drawable drawable = ResourcesCompat.getDrawable(this.mContext.getResources(), R.mipmap.icon_new, null);
            if (drawable == null) {
                return;
            }
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.mTvTitle.setCompoundDrawablePadding(15);
            holder.mTvTitle.setCompoundDrawables(null, null, drawable, null);
            return;
        }
        holder.mIvRight.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.ota.activity.-$$Lambda$VersionAdapter$EyApJs7Ns9ltUj1MfLIaue_53w4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VersionAdapter.this.lambda$setItemInfo$0$VersionAdapter(holder, versionInfo, view);
            }
        });
    }

    public /* synthetic */ void lambda$setItemInfo$0$VersionAdapter(Holder holder, VersionInfo versionInfo, View view) {
        Integer num = (Integer) holder.mIvRight.getTag();
        if (num == null || num.intValue() != 0) {
            holder.mIvRight.setTag(0);
            holder.mIvRight.setImageResource(R.mipmap.icon_open);
            holder.mLlContent.setVisibility(8);
            return;
        }
        holder.mIvRight.setTag(1);
        holder.mIvRight.setImageResource(R.mipmap.icon_close);
        showContent(holder, versionInfo);
    }

    private void showContent(Holder holder, VersionInfo versionInfo) {
        if (TextUtils.isEmpty(versionInfo.getHtmlReleaseNotes())) {
            holder.mLlContent.setVisibility(8);
            return;
        }
        holder.mLlContent.setVisibility(0);
        WebViewUtils.initWebView(holder.mWvContent);
        Context context = this.mContext;
        WebView webView = holder.mWvContent;
        WebViewUtils.showWVContent(context, webView, versionInfo.getHtmlReleaseNotes() + "<br/>");
        ArrayList arrayList = new ArrayList();
        List<String> videoUrls = versionInfo.getVideoUrls();
        List<String> imageUrls = versionInfo.getImageUrls();
        if (!ArrayUtils.isEmpty(videoUrls)) {
            arrayList.addAll(videoUrls);
        }
        if (!ArrayUtils.isEmpty(imageUrls)) {
            arrayList.addAll(imageUrls);
        }
        ThumbAdapter thumbAdapter = new ThumbAdapter(this.mContext);
        thumbAdapter.setData(arrayList);
        thumbAdapter.setOnThumbClickedListener(this.mListener);
        if (isPortraitOrientation()) {
            holder.mHorizontalListView.setAdapter((ListAdapter) thumbAdapter);
            if (!ArrayUtils.isEmpty(imageUrls) || !ArrayUtils.isEmpty(videoUrls)) {
                holder.mHorizontalListView.setVisibility(0);
                return;
            } else {
                holder.mHorizontalListView.setVisibility(8);
                return;
            }
        }
        holder.mLvThumbs.setAdapter((ListAdapter) thumbAdapter);
        if (!ArrayUtils.isEmpty(imageUrls) || !ArrayUtils.isEmpty(videoUrls)) {
            holder.mLvThumbs.setVisibility(0);
        } else {
            holder.mLvThumbs.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class Holder extends RecyclerView.ViewHolder {
        private HorizontalListView mHorizontalListView;
        private XImageView mIvRight;
        private XLinearLayout mLlContent;
        private SpreadListView mLvThumbs;
        private XTextView mTvSubTitle;
        private XTextView mTvTitle;
        private WebView mWvContent;

        public Holder(View view) {
            super(view);
            this.mIvRight = (XImageView) view.findViewById(R.id.iv_right);
            this.mTvTitle = (XTextView) view.findViewById(R.id.tv_title);
            this.mTvSubTitle = (XTextView) view.findViewById(R.id.tv_sub_title);
            this.mLlContent = (XLinearLayout) view.findViewById(R.id.ll_content);
            this.mWvContent = (WebView) view.findViewById(R.id.wv_content);
            if (VersionAdapter.this.isPortraitOrientation()) {
                this.mHorizontalListView = (HorizontalListView) view.findViewById(R.id.lv_thumb_list);
            } else {
                this.mLvThumbs = (SpreadListView) view.findViewById(R.id.lv_thumb_list);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPortraitOrientation() {
        int i;
        Context context = this.mContext;
        if (context != null) {
            i = context.getResources().getConfiguration().orientation;
        } else {
            i = OTAManager.getContext().getResources().getConfiguration().orientation;
        }
        return 1 == i;
    }
}
