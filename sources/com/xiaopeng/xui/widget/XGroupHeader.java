package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.view.XViewDelegate;
import com.xiaopeng.xui.view.font.XFontScaleHelper;
/* loaded from: classes2.dex */
public class XGroupHeader extends XLinearLayout {
    private static final int LOCATION_LEFT = 0;
    private static final int LOCATION_RIGHT = 1;
    private static final int MODE_BUTTON = 2;
    private static final int MODE_CUSTOM = 4;
    private static final int MODE_ICON = 1;
    private static final int MODE_LOADING = 3;
    private static final int MODE_NONE = 0;
    private int mActionMode;
    private View mRightView;
    private TextView mText;

    public XGroupHeader(Context context) {
        this(context, null);
    }

    public XGroupHeader(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XGroupHeader(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public XGroupHeader(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XGroupHeader);
        if (obtainStyledAttributes.getInt(R.styleable.XGroupHeader_group_header_action_location, 0) == 1) {
            LayoutInflater.from(context).inflate(R.layout.x_groupheader_right, this);
        } else {
            LayoutInflater.from(context).inflate(R.layout.x_groupheader, this);
        }
        initView();
        setText(obtainStyledAttributes.getString(R.styleable.XGroupHeader_group_header_text));
        this.mActionMode = obtainStyledAttributes.getInt(R.styleable.XGroupHeader_group_header_action_mode, 0);
        setView(obtainStyledAttributes.getResourceId(R.styleable.XGroupHeader_group_header_action, -1));
        this.mText.setTextSize(0, obtainStyledAttributes.getDimensionPixelSize(R.styleable.XGroupHeader_group_header_text_size, 0));
        final XFontScaleHelper create = XFontScaleHelper.create(obtainStyledAttributes, R.styleable.XGroupHeader_group_header_text_size);
        if (create != null && this.mXViewDelegate != null) {
            this.mXViewDelegate.setFontScaleChangeCallback(new XViewDelegate.onFontScaleChangeCallback() { // from class: com.xiaopeng.xui.widget.-$$Lambda$XGroupHeader$S_WD4TrSqPpLkbbLzZxGEBnE8zc
                @Override // com.xiaopeng.xui.view.XViewDelegate.onFontScaleChangeCallback
                public final void onFontScaleChanged() {
                    XGroupHeader.this.lambda$new$0$XGroupHeader(create);
                }
            });
        }
        obtainStyledAttributes.recycle();
    }

    public /* synthetic */ void lambda$new$0$XGroupHeader(XFontScaleHelper xFontScaleHelper) {
        xFontScaleHelper.refreshTextSize(this.mText);
    }

    private void setView(int i) {
        int i2 = this.mActionMode;
        if (i2 != 0) {
            if (i2 == 1) {
                this.mRightView = LayoutInflater.from(getContext()).inflate(R.layout.x_groupheader_action_icon, (ViewGroup) this, false);
                addView(this.mRightView);
                if (i > 0) {
                    setIcon(i);
                }
            } else if (i2 == 2) {
                this.mRightView = LayoutInflater.from(getContext()).inflate(R.layout.x_groupheader_action_button, (ViewGroup) this, false);
                addView(this.mRightView);
                if (i > 0) {
                    ((TextView) this.mRightView).setText(i);
                }
            } else if (i2 == 3) {
                this.mRightView = LayoutInflater.from(getContext()).inflate(R.layout.x_groupheader_action_loading, (ViewGroup) this, false);
                addView(this.mRightView);
            } else if (i2 == 4 && i > 0) {
                this.mRightView = LayoutInflater.from(getContext()).inflate(i, (ViewGroup) this, false);
                addView(this.mRightView);
            }
        }
    }

    private void initView() {
        this.mText = (TextView) findViewById(R.id.x_groupheader_tv);
    }

    public void setText(@Nullable CharSequence charSequence) {
        this.mText.setText(charSequence);
    }

    public void setCustom(@LayoutRes int i) {
        this.mActionMode = 4;
        removeView(this.mRightView);
        setView(i);
    }

    public void setIcon(@DrawableRes int i) {
        if (this.mActionMode == 1) {
            ((ImageButton) this.mRightView).setImageResource(i);
            return;
        }
        this.mActionMode = 1;
        removeView(this.mRightView);
        setView(i);
    }

    public void setButtonText(@StringRes int i) {
        if (this.mActionMode == 2) {
            ((TextView) this.mRightView).setText(i);
            return;
        }
        this.mActionMode = 2;
        removeView(this.mRightView);
        setView(i);
    }

    public void showLoading(boolean z) {
        if (this.mActionMode == 3) {
            this.mRightView.setVisibility(z ? 0 : 4);
            return;
        }
        this.mActionMode = 3;
        removeView(this.mRightView);
        setView(0);
        this.mRightView.setVisibility(z ? 0 : 4);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (view != null) {
            view.setVisibility(4);
        }
        super.removeView(view);
    }

    public View getRightView() {
        return this.mRightView;
    }
}
