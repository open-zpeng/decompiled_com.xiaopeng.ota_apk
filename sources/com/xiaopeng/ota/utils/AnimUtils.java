package com.xiaopeng.ota.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.utils.AnimUtils;
/* loaded from: classes2.dex */
public class AnimUtils {
    public static int ACTION_CLOSE = 1;
    public static int ACTION_OPEN = 0;
    public static float ANIM_SCALE = 0.2f;

    public static void startPhotoAnim(final View view, int i, long j) {
        float f = 1.0f;
        float f2 = 0.0f;
        if (i != ACTION_CLOSE) {
            f2 = 1.0f;
            f = 0.0f;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        ofFloat.setDuration(j);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaopeng.ota.utils.-$$Lambda$AnimUtils$_2z9s6gqt1NJKmdZABfFsmvy0bU
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                AnimUtils.lambda$startPhotoAnim$0(view, valueAnimator);
            }
        });
        ofFloat.addListener(new AnonymousClass1(i, view));
        ofFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$startPhotoAnim$0(View view, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        view.setAlpha(floatValue);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        float dimen = ResourceUtils.getDimen(R.dimen.window_width);
        float f = ANIM_SCALE;
        layoutParams.width = (int) (dimen * (f + ((1.0f - f) * floatValue)));
        float dimen2 = ResourceUtils.getDimen(R.dimen.window_height);
        float f2 = ANIM_SCALE;
        layoutParams.height = (int) (dimen2 * (f2 + ((1.0f - f2) * floatValue)));
        view.setLayoutParams(layoutParams);
    }

    /* renamed from: com.xiaopeng.ota.utils.AnimUtils$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ int val$type;
        final /* synthetic */ View val$view;

        AnonymousClass1(int i, View view) {
            this.val$type = i;
            this.val$view = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            if (this.val$type == AnimUtils.ACTION_CLOSE) {
                this.val$view.setVisibility(8);
                final View view = this.val$view;
                ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.ota.utils.-$$Lambda$AnimUtils$1$nHN71TqZrtB3ELbjbcc5B6Wvgoo
                    @Override // java.lang.Runnable
                    public final void run() {
                        AnimUtils.AnonymousClass1.lambda$onAnimationEnd$0(view);
                    }
                }, 1000L);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onAnimationEnd$0(View view) {
            view.setAlpha(1.0f);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
            layoutParams.width = ResourceUtils.dip2px((int) ResourceUtils.getDimen(R.dimen.window_width));
            layoutParams.height = ResourceUtils.dip2px((int) ResourceUtils.getDimen(R.dimen.window_height));
            view.setLayoutParams(layoutParams);
        }
    }

    public static void startAlphaAnim(final View view, final int i, long j, final AnimatorListenerAdapter animatorListenerAdapter) {
        float f = 1.0f;
        float f2 = 0.0f;
        if (i != ACTION_CLOSE) {
            f2 = 1.0f;
            f = 0.0f;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        ofFloat.setDuration(j);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaopeng.ota.utils.-$$Lambda$AnimUtils$ZbdWyJSkESGn2zPheX-z5fcJMpU
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.xiaopeng.ota.utils.AnimUtils.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                AnimatorListenerAdapter animatorListenerAdapter2 = animatorListenerAdapter;
                if (animatorListenerAdapter2 != null) {
                    animatorListenerAdapter2.onAnimationEnd(animator);
                }
                if (i == AnimUtils.ACTION_CLOSE) {
                    view.setVisibility(8);
                } else {
                    view.setVisibility(0);
                }
            }
        });
        ofFloat.start();
    }
}
