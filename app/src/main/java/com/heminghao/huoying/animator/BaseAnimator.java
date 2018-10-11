package com.heminghao.huoying.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.view.View;

public abstract class BaseAnimator {
    public static final long mDuration = 3000;

    private AnimatorSet mAnimatorSet;

    {
        mAnimatorSet = new AnimatorSet();

    }

    protected abstract void setupAnimation(View view);

    public void start(View view) {
        reset();

        setupAnimation(view);
        mAnimatorSet.start();
    }

    public void reset() {
        mAnimatorSet.cancel();
    }

    public AnimatorSet getmAnimatorSet() {
        return mAnimatorSet;
    }

    public void setListener(Animator.AnimatorListener animatorListener) {
        mAnimatorSet.addListener(animatorListener);
    }
}
