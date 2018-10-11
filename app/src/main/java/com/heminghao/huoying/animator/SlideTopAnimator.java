package com.heminghao.huoying.animator;

import android.animation.ObjectAnimator;
import android.view.View;

public class SlideTopAnimator extends BaseAnimator {
    @Override
    protected void setupAnimation(View view) {
        getmAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "translationY", -300, 0).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration*3/2)

        );
    }
}
