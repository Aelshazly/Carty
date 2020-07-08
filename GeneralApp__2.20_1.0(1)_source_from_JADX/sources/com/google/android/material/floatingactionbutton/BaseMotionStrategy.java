package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import androidx.core.util.Preconditions;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.MotionSpec;
import java.util.ArrayList;
import java.util.List;

abstract class BaseMotionStrategy implements MotionStrategy {
    private final Context context;
    private MotionSpec defaultMotionSpec;
    private final ExtendedFloatingActionButton fab;
    private final ArrayList<AnimatorListener> listeners = new ArrayList<>();
    private MotionSpec motionSpec;
    private final AnimatorTracker tracker;

    BaseMotionStrategy(ExtendedFloatingActionButton fab2, AnimatorTracker tracker2) {
        this.fab = fab2;
        this.context = fab2.getContext();
        this.tracker = tracker2;
    }

    public final void setMotionSpec(MotionSpec motionSpec2) {
        this.motionSpec = motionSpec2;
    }

    public final MotionSpec getCurrentMotionSpec() {
        MotionSpec motionSpec2 = this.motionSpec;
        if (motionSpec2 != null) {
            return motionSpec2;
        }
        if (this.defaultMotionSpec == null) {
            this.defaultMotionSpec = MotionSpec.createFromResource(this.context, getDefaultMotionSpecResource());
        }
        return (MotionSpec) Preconditions.checkNotNull(this.defaultMotionSpec);
    }

    public final void addAnimationListener(AnimatorListener listener) {
        this.listeners.add(listener);
    }

    public final void removeAnimationListener(AnimatorListener listener) {
        this.listeners.remove(listener);
    }

    public final List<AnimatorListener> getListeners() {
        return this.listeners;
    }

    public MotionSpec getMotionSpec() {
        return this.motionSpec;
    }

    public void onAnimationStart(Animator animator) {
        this.tracker.onNextAnimationStart(animator);
    }

    public void onAnimationEnd() {
        this.tracker.clear();
    }

    public void onAnimationCancel() {
        this.tracker.clear();
    }

    public AnimatorSet createAnimator() {
        return createAnimator(getCurrentMotionSpec());
    }

    /* access modifiers changed from: 0000 */
    public AnimatorSet createAnimator(MotionSpec spec) {
        List<Animator> animators = new ArrayList<>();
        String str = "opacity";
        if (spec.hasPropertyValues(str)) {
            animators.add(spec.getAnimator(str, this.fab, View.ALPHA));
        }
        String str2 = "scale";
        if (spec.hasPropertyValues(str2)) {
            animators.add(spec.getAnimator(str2, this.fab, View.SCALE_Y));
            animators.add(spec.getAnimator(str2, this.fab, View.SCALE_X));
        }
        String str3 = "width";
        if (spec.hasPropertyValues(str3)) {
            animators.add(spec.getAnimator(str3, this.fab, ExtendedFloatingActionButton.WIDTH));
        }
        String str4 = "height";
        if (spec.hasPropertyValues(str4)) {
            animators.add(spec.getAnimator(str4, this.fab, ExtendedFloatingActionButton.HEIGHT));
        }
        AnimatorSet set = new AnimatorSet();
        AnimatorSetCompat.playTogether(set, animators);
        return set;
    }
}
