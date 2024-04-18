package com.xiaopeng.ota.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import com.xiaopeng.ota.R;
/* loaded from: classes2.dex */
public class RoundImageView extends ImageView {
    private static final int MODE_CIRCLE = 1;
    private static final int MODE_NONE = 0;
    private static final int MODE_ROUND = 2;
    private int currMode;
    private int currRound;
    private Paint mPaint;

    public RoundImageView(Context context) {
        super(context);
        this.currMode = 0;
        this.currRound = dp2px(10.0f);
        initViews();
    }

    public RoundImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.currMode = 0;
        this.currRound = dp2px(10.0f);
        obtainStyledAttrs(context, attributeSet, i);
        initViews();
    }

    private void obtainStyledAttrs(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundImageView, i, 0);
        this.currMode = obtainStyledAttributes.hasValue(R.styleable.RoundImageView_type) ? obtainStyledAttributes.getInt(R.styleable.RoundImageView_type, 0) : 0;
        this.currRound = obtainStyledAttributes.hasValue(R.styleable.RoundImageView_radius) ? obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundImageView_radius, this.currRound) : this.currRound;
        obtainStyledAttributes.recycle();
    }

    private void initViews() {
        this.mPaint = new Paint(5);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.currMode == 1) {
            super.onMeasure(i, i2);
            int min = Math.min(getMeasuredHeight(), getMeasuredWidth());
            setMeasuredDimension(min, min);
            return;
        }
        super.onMeasure(i, i2);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        Matrix imageMatrix = getImageMatrix();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0) {
            return;
        }
        if (imageMatrix == null && getPaddingTop() == 0 && getPaddingLeft() == 0) {
            drawable.draw(canvas);
            return;
        }
        int saveCount = canvas.getSaveCount();
        canvas.save();
        if (getCropToPadding()) {
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            canvas.clipRect(getPaddingLeft() + scrollX, getPaddingTop() + scrollY, ((scrollX + getRight()) - getLeft()) - getPaddingRight(), ((scrollY + getBottom()) - getTop()) - getPaddingBottom());
        }
        canvas.translate(getPaddingLeft(), getPaddingTop());
        int i = this.currMode;
        if (i == 1) {
            this.mPaint.setShader(new BitmapShader(drawable2Bitmap(drawable), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, this.mPaint);
        } else if (i == 2) {
            this.mPaint.setShader(new BitmapShader(drawable2Bitmap(drawable), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            RectF rectF = new RectF(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
            int i2 = this.currRound;
            canvas.drawRoundRect(rectF, i2, i2, this.mPaint);
        } else {
            if (imageMatrix != null) {
                canvas.concat(imageMatrix);
            }
            drawable.draw(canvas);
        }
        canvas.restoreToCount(saveCount);
    }

    private Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Matrix imageMatrix = getImageMatrix();
        if (imageMatrix != null) {
            canvas.concat(imageMatrix);
        }
        drawable.draw(canvas);
        return createBitmap;
    }

    private int dp2px(float f) {
        return (int) TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
    }
}
