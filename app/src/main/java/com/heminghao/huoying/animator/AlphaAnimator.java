package com.heminghao.huoying.animator;

import android.animation.ObjectAnimator;
import android.view.View;

public class AlphaAnimator extends BaseAnimator {
    @Override
    protected void setupAnimation(View view) {
        getmAnimatorSet().playTogether(ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration));
    }
}
