package com.xiaopeng.ota;

import android.content.Context;
import android.content.MutableContextWrapper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaopeng.ota.utils.ThreadUtils;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public class AsyncInflateViewManager {
    private ConcurrentHashMap<Integer, View> mCachedViews = new ConcurrentHashMap<>();
    private Context mContext;

    public AsyncInflateViewManager(Context context) {
        this.mContext = context;
    }

    public void preloadView(final int i) {
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.ota.-$$Lambda$AsyncInflateViewManager$ck6Twvv-lwvTqq4b5Ygbsv38gtA
            @Override // java.lang.Runnable
            public final void run() {
                AsyncInflateViewManager.this.lambda$preloadView$0$AsyncInflateViewManager(i);
            }
        });
    }

    public /* synthetic */ void lambda$preloadView$0$AsyncInflateViewManager(int i) {
        View inflate = new BasicInflater(new MutableContextWrapper(this.mContext)).inflate(i, (ViewGroup) null, false);
        if (inflate != null) {
            this.mCachedViews.put(Integer.valueOf(i), inflate);
        }
    }

    public View inflatedView(Context context, LayoutInflater layoutInflater, int i, ViewGroup viewGroup) {
        View view = this.mCachedViews.get(Integer.valueOf(i));
        if (view != null) {
            this.mCachedViews.remove(Integer.valueOf(i));
            Context context2 = view.getContext();
            if (context2 instanceof MutableContextWrapper) {
                ((MutableContextWrapper) context2).setBaseContext(context);
            }
            preloadView(i);
            return view;
        }
        return layoutInflater.inflate(i, viewGroup, false);
    }

    /* loaded from: classes2.dex */
    private static class BasicInflater extends LayoutInflater {
        private static final String[] sClassPrefixList = {"android.widget.", "android.webkit.", "android.app."};

        BasicInflater(Context context) {
            super(context);
        }

        @Override // android.view.LayoutInflater
        public LayoutInflater cloneInContext(Context context) {
            return new BasicInflater(context);
        }

        @Override // android.view.LayoutInflater
        protected View onCreateView(String str, AttributeSet attributeSet) throws ClassNotFoundException {
            View createView;
            for (String str2 : sClassPrefixList) {
                try {
                    createView = createView(str, str2, attributeSet);
                } catch (ClassNotFoundException unused) {
                }
                if (createView != null) {
                    return createView;
                }
            }
            return super.onCreateView(str, attributeSet);
        }
    }
}
