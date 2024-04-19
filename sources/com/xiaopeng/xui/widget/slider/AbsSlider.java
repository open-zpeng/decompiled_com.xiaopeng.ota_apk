package com.xiaopeng.xui.widget.slider;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.xiaopeng.vui.commons.IVuiElementListener;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.theme.XThemeManager;
import com.xiaopeng.xui.widget.XViewGroup;
import java.math.RoundingMode;
import java.text.DecimalFormat;
/* loaded from: classes2.dex */
public abstract class AbsSlider extends XViewGroup implements IVuiElementListener {
    protected static final int BG_ITEM_MARGIN = 18;
    protected static final int BG_ITEM_SIZE = 30;
    protected static final int BG_ITEM_SIZE_MIN = 3;
    protected static final int CHILDREN_LAYOUT_HEIGHT = 40;
    protected static final int CHILDREN_LAYOUT_WIDTH = 20;
    private static final int INDICATOR_BALL_RADIUS = 9;
    protected static final int INDICATOR_HOLD_HORIZONTAL = 0;
    protected static final int INDICATOR_HOLD_VERTICAL = 40;
    protected static final int INDICATOR_MARGIN = 16;
    private static final int INDICATOR_OUTER = 7;
    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;
    private static final String TAG = "AbsSlider";
    protected float accuracy;
    private LinearGradient barGradient;
    @ColorInt
    int bgBallColor;
    @ColorInt
    int bgDayColor;
    private final Paint bgGradientPaint;
    private final float bgHeight;
    protected float bgItemGap;
    @ColorInt
    int bgLineColorSelect;
    @ColorInt
    int bgNightColor;
    private Paint bgPaint;
    protected float currentUpdateIndex;
    @ColorInt
    private int customBackground;
    protected int decimal;
    protected DecimalFormat decimalFormat;
    private float desireHeight;
    private final float desireWidth;
    protected int disableAlpha;
    protected boolean dismissPop;
    private final int enableAlpha;
    protected int endColor;
    protected int endIndex;
    private final Paint gradientPaint;
    protected boolean hidePop;
    IndicatorDrawable indicatorDrawable;
    protected float indicatorValue;
    protected int initIndex;
    protected boolean isNight;
    private int itemCount;
    @ColorInt
    int lineColorSelect;
    protected Paint lineSelectPaint;
    protected float mBgThickness;
    protected float mBgThicknessHalf;
    protected boolean mIsDragging;
    protected int mOrientation;
    protected int mProgressLeftColor;
    protected int mProgressRightColor;
    protected float mProgressViewLength;
    protected float mScaledTouchSlop;
    protected int mStep;
    protected int mTextTagSize;
    private Drawable mThumb;
    private int mThumbResId;
    private int mTickMarkStyleRes;
    protected float mTouchDownX;
    protected float mTouchDownY;
    protected String prefixUnit;
    private final float roundRadius;
    protected int startIndex;
    protected int topColor;
    protected String unit;
    protected int upperLimit;
    protected int workableTotalLength;

    private int resetAlpha(@ColorInt int i, int i2) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (i2 << 24);
    }

    public AbsSlider(Context context) {
        this(context, null);
    }

    public AbsSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AbsSlider(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public AbsSlider(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.gradientPaint = new Paint(1);
        this.bgGradientPaint = new Paint(1);
        this.enableAlpha = 92;
        this.desireWidth = 644.0f;
        this.bgHeight = 40.0f;
        this.roundRadius = 16.0f;
        this.disableAlpha = 40;
        this.initIndex = -1;
        this.upperLimit = Integer.MIN_VALUE;
        this.mBgThickness = 32.0f;
        this.mBgThicknessHalf = this.mBgThickness / 2.0f;
        this.accuracy = 1.0f;
        this.dismissPop = false;
        this.endColor = 1555808977;
        this.topColor = 1555808977;
        this.mProgressRightColor = -12871169;
        this.mProgressLeftColor = -12860929;
        this.mStep = 1;
        this.hidePop = false;
        this.bgLineColorSelect = -15945223;
        this.bgNightColor = 1543503872;
        this.bgDayColor = 1560281087;
        this.bgBallColor = -12871169;
        this.lineColorSelect = -1;
        this.customBackground = 0;
        this.desireHeight = 100.0f;
        this.itemCount = 30;
        this.mOrientation = 0;
        this.mTickMarkStyleRes = R.style.XSliderLine;
        initView(context, attributeSet, i, i2);
        if (isInEditMode()) {
            return;
        }
        this.isNight = XThemeManager.isNight(getContext());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XViewGroup, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (!isInEditMode()) {
            this.isNight = XThemeManager.isNight(getContext());
            if (XThemeManager.isThemeChanged(configuration)) {
                IndicatorDrawable indicatorDrawable = this.indicatorDrawable;
                if (indicatorDrawable != null) {
                    indicatorDrawable.refreshUI(getResources(), getContext().getTheme());
                }
                if (this.mThumbResId != 0) {
                    this.mThumb = ContextCompat.getDrawable(getContext(), this.mThumbResId);
                }
            }
        }
        invalidate();
    }

    private void initPaint() {
        if (this.bgPaint == null) {
            this.bgPaint = new Paint(1);
            this.bgPaint.setStyle(Paint.Style.FILL);
            this.bgPaint.setColor(this.bgNightColor);
        }
        if (this.lineSelectPaint == null) {
            this.lineSelectPaint = new Paint(1);
            this.lineSelectPaint.setStyle(Paint.Style.FILL);
            this.lineSelectPaint.setStrokeCap(Paint.Cap.ROUND);
            this.lineSelectPaint.setStrokeWidth(12.0f);
            this.lineSelectPaint.setColor(this.lineColorSelect);
        }
        this.barGradient = new LinearGradient(16.0f, 0.0f, this.workableTotalLength, 0.0f, new int[]{this.mProgressLeftColor, this.mProgressRightColor}, (float[]) null, Shader.TileMode.CLAMP);
        this.gradientPaint.setShader(this.barGradient);
    }

    private void initView(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.XSlider, i, i2);
        this.unit = obtainStyledAttributes.getString(R.styleable.XSlider_slider_unit);
        this.startIndex = obtainStyledAttributes.getInteger(R.styleable.XSlider_slider_start_index, 0);
        this.mStep = obtainStyledAttributes.getInteger(R.styleable.XSlider_slider_step, 1);
        this.initIndex = obtainStyledAttributes.getInteger(R.styleable.XSlider_slider_init_index, -1);
        this.endIndex = obtainStyledAttributes.getInteger(R.styleable.XSlider_slider_end_index, 100);
        this.upperLimit = obtainStyledAttributes.getInteger(R.styleable.XSlider_slider_upper_limit, Integer.MIN_VALUE);
        this.decimal = obtainStyledAttributes.getInteger(R.styleable.XSlider_slider_index_decimal, 0);
        this.prefixUnit = obtainStyledAttributes.getString(R.styleable.XSlider_slider_unit_prefix);
        this.accuracy = obtainStyledAttributes.getFloat(R.styleable.XSlider_slider_accuracy, 0.0f);
        obtainStyledAttributes.recycle();
        if (this.initIndex == -1) {
            this.initIndex = Math.min(this.startIndex, this.endIndex);
        }
        int i3 = this.initIndex;
        int i4 = this.startIndex;
        this.indicatorValue = i3 - i4;
        if (this.endIndex == i4) {
            throw new RuntimeException("startIndex = endIndex!!! please check the xml");
        }
        int i5 = this.decimal;
        this.decimalFormat = i5 == 0 ? null : i5 == 1 ? new DecimalFormat("0.0") : new DecimalFormat("0.00");
        DecimalFormat decimalFormat = this.decimalFormat;
        if (decimalFormat != null) {
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
        }
        if (this.accuracy == 0.0f) {
            int i6 = this.decimal;
            this.accuracy = i6 == 0 ? 1.0f : i6 == 1 ? 0.1f : 0.01f;
        }
        setStyle(attributeSet, i2);
    }

    public void setStyle(@StyleRes int i) {
        setStyle(null, i);
    }

    private void setStyle(AttributeSet attributeSet, @StyleRes int i) {
        readStyleAttrs(attributeSet, i);
        applyStyleValues();
        if (this.hidePop) {
            return;
        }
        this.indicatorDrawable = new IndicatorDrawable();
        this.indicatorDrawable.inflateAttr(getResources(), getContext().getTheme(), attributeSet, i);
        this.indicatorDrawable.setState(getDrawableState());
        this.indicatorDrawable.setCallback(this);
        this.indicatorDrawable.updateCenter(filterValidValue(), getPopString(), false, getSliderLength());
    }

    private void readStyleAttrs(AttributeSet attributeSet, @StyleRes int i) {
        TypedArray obtainStyledAttributes;
        if (attributeSet != null) {
            obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.XSlider, 0, i);
        } else {
            obtainStyledAttributes = getContext().obtainStyledAttributes(i, R.styleable.XSlider);
        }
        if (obtainStyledAttributes.hasValueOrEmpty(R.styleable.XSlider_slider_bg_color)) {
            this.bgNightColor = obtainStyledAttributes.getColor(R.styleable.XSlider_slider_bg_color, this.bgNightColor);
        }
        if (obtainStyledAttributes.hasValueOrEmpty(R.styleable.XSlider_slider_bg_line_color)) {
            this.bgLineColorSelect = obtainStyledAttributes.getColor(R.styleable.XSlider_slider_bg_line_color, this.bgLineColorSelect);
        }
        if (obtainStyledAttributes.hasValueOrEmpty(R.styleable.XSlider_slider_background)) {
            this.customBackground = obtainStyledAttributes.getColor(R.styleable.XSlider_slider_background, 0);
        }
        if (obtainStyledAttributes.hasValueOrEmpty(R.styleable.XSlider_slider_hide_pop)) {
            this.hidePop = obtainStyledAttributes.getBoolean(R.styleable.XSlider_slider_hide_pop, false);
        }
        if (obtainStyledAttributes.hasValueOrEmpty(R.styleable.XSlider_slider_dismiss_pop)) {
            this.dismissPop = obtainStyledAttributes.getBoolean(R.styleable.XSlider_slider_dismiss_pop, false);
        }
        if (obtainStyledAttributes.hasValueOrEmpty(R.styleable.XSlider_slider_item_count)) {
            this.itemCount = obtainStyledAttributes.getInteger(R.styleable.XSlider_slider_item_count, 30);
        }
        if (obtainStyledAttributes.hasValueOrEmpty(R.styleable.XSlider_slider_orientation)) {
            this.mOrientation = obtainStyledAttributes.getInt(R.styleable.XSlider_slider_orientation, 0);
        }
        if (obtainStyledAttributes.hasValueOrEmpty(R.styleable.XSlider_slider_thumb)) {
            this.mThumbResId = obtainStyledAttributes.getResourceId(R.styleable.XSlider_slider_thumb, 0);
            if (this.mThumbResId != 0) {
                this.mThumb = ContextCompat.getDrawable(getContext(), this.mThumbResId);
            } else {
                this.mThumb = null;
            }
        }
        if (obtainStyledAttributes.hasValueOrEmpty(R.styleable.XSlider_slider_progress_color_start)) {
            this.mProgressLeftColor = obtainStyledAttributes.getColor(R.styleable.XSlider_slider_progress_color_start, this.mProgressLeftColor);
        }
        if (obtainStyledAttributes.hasValueOrEmpty(R.styleable.XSlider_slider_progress_color_end)) {
            this.mProgressRightColor = obtainStyledAttributes.getColor(R.styleable.XSlider_slider_progress_color_end, this.mProgressRightColor);
        }
        if (obtainStyledAttributes.hasValueOrEmpty(R.styleable.XSlider_slider_tickMarkStyle)) {
            this.mTickMarkStyleRes = obtainStyledAttributes.getResourceId(R.styleable.XSlider_slider_tickMarkStyle, R.style.XSliderLine);
        }
        obtainStyledAttributes.recycle();
    }

    private void applyStyleValues() {
        if (this.itemCount < 3) {
            this.itemCount = 3;
        }
        if (this.dismissPop) {
            this.hidePop = true;
            this.desireHeight = 32.0f;
        }
        if (!this.hidePop) {
            this.mTextTagSize = 40;
        } else {
            this.mTextTagSize = 0;
        }
        initPaint();
        if (this.mOrientation == 1) {
            setMinimumWidth(0);
            setMinimumHeight(80);
        } else {
            setMinimumWidth(80);
            setMinimumHeight(0);
        }
        setBackground(new ColorDrawable(this.customBackground));
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof SlideLineView) {
                ((SlideLineView) childAt).setStyle(this.mTickMarkStyleRes);
            }
        }
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int measuredWidth;
        super.onMeasure(i, i2);
        if (this.mOrientation == 1) {
            measuredWidth = (int) this.desireHeight;
            if (View.MeasureSpec.getMode(i2) == 1073741824) {
                r1 = getMeasuredHeight();
            }
        } else {
            measuredWidth = View.MeasureSpec.getMode(i) != Integer.MIN_VALUE ? getMeasuredWidth() : 644;
            r1 = (int) this.desireHeight;
        }
        setMeasuredDimension(measuredWidth, r1);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int save = canvas.save();
        if (this.mOrientation == 1) {
            canvas.translate(0.0f, getHeight());
            canvas.rotate(270.0f);
        }
        super.onDraw(canvas);
        float heightExIndicator = (getHeightExIndicator() / 2.0f) - this.mBgThicknessHalf;
        float widthExIndicator = getWidthExIndicator();
        float heightExIndicator2 = (getHeightExIndicator() / 2.0f) + this.mBgThicknessHalf;
        if (this.isNight) {
            this.bgPaint.setColor(this.bgNightColor);
            canvas.drawRoundRect(0.0f, heightExIndicator, widthExIndicator, heightExIndicator2, 16.0f, 16.0f, this.bgPaint);
        } else {
            if (isEnabled()) {
                this.bgGradientPaint.setColor(this.endColor);
                canvas.drawRoundRect(0.0f, heightExIndicator, widthExIndicator, heightExIndicator2, 16.0f, 16.0f, this.bgGradientPaint);
            } else {
                this.bgPaint.setColor(this.bgDayColor);
                canvas.drawRoundRect(0.0f, heightExIndicator, widthExIndicator, heightExIndicator2, 16.0f, 16.0f, this.bgPaint);
            }
            if (isEnabled()) {
                canvas.drawRoundRect(0.0f, heightExIndicator, filterValidValue() + 9.0f + 7.0f, heightExIndicator2, 16.0f, 16.0f, this.gradientPaint);
            } else {
                this.bgPaint.setColor(this.bgBallColor);
                canvas.drawRoundRect(0.0f, heightExIndicator, filterValidValue() + 9.0f + 7.0f, heightExIndicator2, 16.0f, 16.0f, this.bgPaint);
            }
        }
        canvas.restoreToCount(save);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        setPadding(0, 0, 0, 0);
        if (this.mOrientation != 0) {
            i = i2;
        }
        this.workableTotalLength = i - 32;
        this.bgItemGap = this.workableTotalLength / (this.itemCount - 1);
        int i5 = this.initIndex;
        int i6 = this.startIndex;
        this.mProgressViewLength = (Math.abs((i5 - i6) / (this.endIndex - i6)) * this.workableTotalLength) + 16.0f;
        for (int i7 = 0; i7 < this.itemCount; i7++) {
            addView(new SlideLineView(getContext(), this.mProgressViewLength > (this.bgItemGap * ((float) i7)) + 16.0f, this.mTickMarkStyleRes));
        }
        this.barGradient = new LinearGradient(16.0f, 0.0f, this.workableTotalLength, 0.0f, new int[]{this.mProgressLeftColor, this.mProgressRightColor}, (float[]) null, Shader.TileMode.CLAMP);
        this.gradientPaint.setShader(this.barGradient);
        IndicatorDrawable indicatorDrawable = this.indicatorDrawable;
        if (indicatorDrawable != null) {
            indicatorDrawable.updateCenter(filterValidValue(), getPopString(), false, getSliderLength());
        }
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float sliderLength = (getSliderLength() - 36) / (getChildCount() - 1);
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            float f = (i5 * sliderLength) + 18.0f;
            getChildAt(i5).layout((int) (f - 10.0f), (((int) getHeightExIndicator()) / 2) - 20, (int) (f + 10.0f), (((int) getHeightExIndicator()) / 2) + 20);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void invalidateAll() {
        invalidate();
        IndicatorDrawable indicatorDrawable = this.indicatorDrawable;
        if (indicatorDrawable != null) {
            indicatorDrawable.updateCenter(filterValidValue(), getPopString(), false, getSliderLength());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ViewGroup isInScrollContainer() {
        for (ViewParent parent = getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if (viewGroup.shouldDelayChildPressedState()) {
                return viewGroup;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void stickIndicator() {
        if (this.mStep == 1) {
            return;
        }
        float f = this.workableTotalLength / (this.endIndex - this.startIndex);
        this.mProgressViewLength = (((int) ((this.mProgressViewLength - 16.0f) / f)) * f) + 16.0f;
    }

    public float getIndicatorValue() {
        return (this.indicatorValue + this.startIndex) * this.mStep;
    }

    public void setAccuracy(float f) {
        this.accuracy = f;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(@NonNull Drawable drawable) {
        IndicatorDrawable indicatorDrawable;
        Drawable drawable2 = this.mThumb;
        return (drawable2 != null && drawable == drawable2) || ((indicatorDrawable = this.indicatorDrawable) != null && drawable == indicatorDrawable) || super.verifyDrawable(drawable);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        IndicatorDrawable indicatorDrawable = this.indicatorDrawable;
        if (indicatorDrawable != null && indicatorDrawable.isStateful() && indicatorDrawable.setState(drawableState)) {
            invalidateDrawable(indicatorDrawable);
        }
        Drawable drawable = this.mThumb;
        if (drawable != null && drawable.isStateful() && this.mThumb.setState(drawableState)) {
            invalidateDrawable(this.mThumb);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        int save = canvas.save();
        if (this.mOrientation == 1) {
            canvas.translate(0.0f, getHeight());
            canvas.rotate(270.0f);
        }
        super.dispatchDraw(canvas);
        float filterValidValue = filterValidValue();
        if (filterValidValue == 0.0f) {
            return;
        }
        float heightExIndicator = getHeightExIndicator() / 2.0f;
        IndicatorDrawable indicatorDrawable = this.indicatorDrawable;
        if (indicatorDrawable != null) {
            indicatorDrawable.draw(canvas);
        }
        if (isEnabled()) {
            Drawable drawable = this.mThumb;
            if (drawable != null) {
                float intrinsicWidth = filterValidValue - (this.mThumb.getIntrinsicWidth() / 2.0f);
                float intrinsicHeight = heightExIndicator - (drawable.getIntrinsicHeight() / 2.0f);
                Drawable drawable2 = this.mThumb;
                drawable2.setBounds((int) intrinsicWidth, (int) intrinsicHeight, (int) (intrinsicWidth + drawable2.getIntrinsicWidth()), (int) (intrinsicHeight + this.mThumb.getIntrinsicHeight()));
                this.mThumb.draw(canvas);
            }
            canvas.restoreToCount(save);
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public float getHeightExIndicator() {
        return getSliderThickness() + this.mTextTagSize;
    }

    public float getWidthExIndicator() {
        return getSliderLength();
    }

    public int getSliderLength() {
        return this.mOrientation == 1 ? getHeight() : getWidth();
    }

    public int getSliderThickness() {
        return this.mOrientation == 1 ? getWidth() : getHeight();
    }

    public float getProgressViewLength() {
        return this.mProgressViewLength;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float filterValidValue() {
        if (this.mProgressViewLength < 16.0f) {
            return 16.0f;
        }
        float sliderLength = (getSliderLength() - 16) - upperLimitDistance();
        float f = this.mProgressViewLength;
        return f > sliderLength ? sliderLength : f;
    }

    private int upperLimitDistance() {
        int i;
        int i2;
        int i3 = this.upperLimit;
        if (i3 != Integer.MIN_VALUE && (i = this.startIndex) < (i2 = this.endIndex) && i <= i3 && i3 <= i2) {
            return ((i2 - i3) * this.workableTotalLength) / (i2 - i);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getPopString() {
        if (this.unit == null) {
            this.unit = "";
        }
        if (this.prefixUnit == null) {
            this.prefixUnit = "";
        }
        if (this.decimalFormat == null) {
            if (this.mStep == 1) {
                return this.prefixUnit + (this.startIndex + ((int) this.indicatorValue)) + this.unit;
            }
            return this.prefixUnit + getIndicatorValue() + this.unit;
        }
        return this.prefixUnit + this.decimalFormat.format((this.startIndex + this.indicatorValue) * this.mStep) + this.unit;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setAlphaByEnable(boolean z) {
        this.bgNightColor = resetAlpha(this.bgNightColor, z ? 92 : this.disableAlpha);
        this.bgDayColor = resetAlpha(this.bgDayColor, z ? 92 : this.disableAlpha);
        this.bgBallColor = resetAlpha(this.bgBallColor, z ? 255 : this.disableAlpha);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XViewGroup, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XViewGroup, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) {
            return;
        }
        this.isNight = XThemeManager.isNight(getContext());
    }
}
