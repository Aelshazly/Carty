package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior;
import androidx.core.view.ViewCompat;
import com.google.android.material.C1124R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.List;

public class ExtendedFloatingActionButton extends MaterialButton implements AttachedBehavior {
    private static final int ANIM_STATE_HIDING = 1;
    private static final int ANIM_STATE_NONE = 0;
    private static final int ANIM_STATE_SHOWING = 2;
    private static final int DEF_STYLE_RES = C1124R.style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
    static final Property<View, Float> HEIGHT = new Property<View, Float>(Float.class, "height") {
        public void set(View object, Float value) {
            object.getLayoutParams().height = value.intValue();
            object.requestLayout();
        }

        public Float get(View object) {
            return Float.valueOf((float) object.getLayoutParams().height);
        }
    };
    static final Property<View, Float> WIDTH = new Property<View, Float>(Float.class, "width") {
        public void set(View object, Float value) {
            object.getLayoutParams().width = value.intValue();
            object.requestLayout();
        }

        public Float get(View object) {
            return Float.valueOf((float) object.getLayoutParams().width);
        }
    };
    /* access modifiers changed from: private */
    public int animState;
    private final Behavior<ExtendedFloatingActionButton> behavior;
    private final AnimatorTracker changeVisibilityTracker;
    /* access modifiers changed from: private */
    public final MotionStrategy extendStrategy;
    /* access modifiers changed from: private */
    public final MotionStrategy hideStrategy;
    /* access modifiers changed from: private */
    public boolean isExtended;
    /* access modifiers changed from: private */
    public final Rect shadowPadding;
    /* access modifiers changed from: private */
    public final MotionStrategy showStrategy;
    /* access modifiers changed from: private */
    public final MotionStrategy shrinkStrategy;

    class ChangeSizeStrategy extends BaseMotionStrategy {
        private final boolean extending;
        private final Size size;

        ChangeSizeStrategy(AnimatorTracker animatorTracker, Size size2, boolean extending2) {
            super(ExtendedFloatingActionButton.this, animatorTracker);
            this.size = size2;
            this.extending = extending2;
        }

        public void performNow() {
            ExtendedFloatingActionButton.this.isExtended = this.extending;
            LayoutParams layoutParams = ExtendedFloatingActionButton.this.getLayoutParams();
            if (layoutParams != null) {
                if (this.extending) {
                    ExtendedFloatingActionButton.this.measure(0, 0);
                }
                layoutParams.width = this.size.getWidth();
                layoutParams.height = this.size.getHeight();
                ExtendedFloatingActionButton.this.requestLayout();
            }
        }

        public void onChange(OnChangedCallback callback) {
            if (callback != null) {
                if (this.extending) {
                    callback.onExtended(ExtendedFloatingActionButton.this);
                } else {
                    callback.onShrunken(ExtendedFloatingActionButton.this);
                }
            }
        }

        public int getDefaultMotionSpecResource() {
            return C1124R.animator.mtrl_extended_fab_change_size_motion_spec;
        }

        public AnimatorSet createAnimator() {
            MotionSpec spec = getCurrentMotionSpec();
            String str = "width";
            if (spec.hasPropertyValues(str)) {
                PropertyValuesHolder[] widthValues = spec.getPropertyValues(str);
                widthValues[0].setFloatValues(new float[]{(float) ExtendedFloatingActionButton.this.getWidth(), (float) this.size.getWidth()});
                spec.setPropertyValues(str, widthValues);
            }
            String str2 = "height";
            if (spec.hasPropertyValues(str2)) {
                PropertyValuesHolder[] heightValues = spec.getPropertyValues(str2);
                heightValues[0].setFloatValues(new float[]{(float) ExtendedFloatingActionButton.this.getHeight(), (float) this.size.getHeight()});
                spec.setPropertyValues(str2, heightValues);
            }
            return super.createAnimator(spec);
        }

        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            ExtendedFloatingActionButton.this.isExtended = this.extending;
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(true);
        }

        public void onAnimationEnd() {
            super.onAnimationEnd();
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(false);
        }

        public boolean shouldCancel() {
            return this.extending == ExtendedFloatingActionButton.this.isExtended || ExtendedFloatingActionButton.this.getIcon() == null || TextUtils.isEmpty(ExtendedFloatingActionButton.this.getText());
        }
    }

    protected static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends Behavior<T> {
        private static final boolean AUTO_HIDE_DEFAULT = false;
        private static final boolean AUTO_SHRINK_DEFAULT = true;
        private boolean autoHideEnabled;
        private boolean autoShrinkEnabled;
        private OnChangedCallback internalAutoHideCallback;
        private OnChangedCallback internalAutoShrinkCallback;
        private Rect tmpRect;

        public ExtendedFloatingActionButtonBehavior() {
            this.autoHideEnabled = false;
            this.autoShrinkEnabled = AUTO_SHRINK_DEFAULT;
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, C1124R.styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = a.getBoolean(C1124R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.autoShrinkEnabled = a.getBoolean(C1124R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, AUTO_SHRINK_DEFAULT);
            a.recycle();
        }

        public void setAutoHideEnabled(boolean autoHide) {
            this.autoHideEnabled = autoHide;
        }

        public boolean isAutoHideEnabled() {
            return this.autoHideEnabled;
        }

        public void setAutoShrinkEnabled(boolean autoShrink) {
            this.autoShrinkEnabled = autoShrink;
        }

        public boolean isAutoShrinkEnabled() {
            return this.autoShrinkEnabled;
        }

        public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams lp) {
            if (lp.dodgeInsetEdges == 0) {
                lp.dodgeInsetEdges = 80;
            }
        }

        public boolean onDependentViewChanged(CoordinatorLayout parent, ExtendedFloatingActionButton child, View dependency) {
            if (dependency instanceof AppBarLayout) {
                updateFabVisibilityForAppBarLayout(parent, (AppBarLayout) dependency, child);
            } else if (isBottomSheet(dependency)) {
                updateFabVisibilityForBottomSheet(dependency, child);
            }
            return false;
        }

        private static boolean isBottomSheet(View view) {
            LayoutParams lp = view.getLayoutParams();
            if (lp instanceof CoordinatorLayout.LayoutParams) {
                return ((CoordinatorLayout.LayoutParams) lp).getBehavior() instanceof BottomSheetBehavior;
            }
            return false;
        }

        /* access modifiers changed from: 0000 */
        public void setInternalAutoHideCallback(OnChangedCallback callback) {
            this.internalAutoHideCallback = callback;
        }

        /* access modifiers changed from: 0000 */
        public void setInternalAutoShrinkCallback(OnChangedCallback callback) {
            this.internalAutoShrinkCallback = callback;
        }

        private boolean shouldUpdateVisibility(View dependency, ExtendedFloatingActionButton child) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            if ((this.autoHideEnabled || this.autoShrinkEnabled) && lp.getAnchorId() == dependency.getId()) {
                return AUTO_SHRINK_DEFAULT;
            }
            return false;
        }

        private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout parent, AppBarLayout appBarLayout, ExtendedFloatingActionButton child) {
            if (!shouldUpdateVisibility(appBarLayout, child)) {
                return false;
            }
            if (this.tmpRect == null) {
                this.tmpRect = new Rect();
            }
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(parent, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                shrinkOrHide(child);
            } else {
                extendOrShow(child);
            }
            return AUTO_SHRINK_DEFAULT;
        }

        private boolean updateFabVisibilityForBottomSheet(View bottomSheet, ExtendedFloatingActionButton child) {
            if (!shouldUpdateVisibility(bottomSheet, child)) {
                return false;
            }
            if (bottomSheet.getTop() < (child.getHeight() / 2) + ((CoordinatorLayout.LayoutParams) child.getLayoutParams()).topMargin) {
                shrinkOrHide(child);
            } else {
                extendOrShow(child);
            }
            return AUTO_SHRINK_DEFAULT;
        }

        /* access modifiers changed from: protected */
        public void shrinkOrHide(ExtendedFloatingActionButton fab) {
            MotionStrategy strategy;
            OnChangedCallback callback = this.autoShrinkEnabled ? this.internalAutoShrinkCallback : this.internalAutoHideCallback;
            if (this.autoShrinkEnabled) {
                strategy = fab.shrinkStrategy;
            } else {
                strategy = fab.hideStrategy;
            }
            fab.performMotion(strategy, callback);
        }

        /* access modifiers changed from: protected */
        public void extendOrShow(ExtendedFloatingActionButton fab) {
            MotionStrategy strategy;
            OnChangedCallback callback = this.autoShrinkEnabled ? this.internalAutoShrinkCallback : this.internalAutoHideCallback;
            if (this.autoShrinkEnabled) {
                strategy = fab.extendStrategy;
            } else {
                strategy = fab.showStrategy;
            }
            fab.performMotion(strategy, callback);
        }

        public boolean onLayoutChild(CoordinatorLayout parent, ExtendedFloatingActionButton child, int layoutDirection) {
            List<View> dependencies = parent.getDependencies(child);
            int count = dependencies.size();
            for (int i = 0; i < count; i++) {
                View dependency = (View) dependencies.get(i);
                if (!(dependency instanceof AppBarLayout)) {
                    if (isBottomSheet(dependency) && updateFabVisibilityForBottomSheet(dependency, child)) {
                        break;
                    }
                } else if (updateFabVisibilityForAppBarLayout(parent, (AppBarLayout) dependency, child)) {
                    break;
                }
            }
            parent.onLayoutChild(child, layoutDirection);
            offsetIfNeeded(parent, child);
            return AUTO_SHRINK_DEFAULT;
        }

        public boolean getInsetDodgeRect(CoordinatorLayout parent, ExtendedFloatingActionButton child, Rect rect) {
            Rect shadowPadding = child.shadowPadding;
            rect.set(child.getLeft() + shadowPadding.left, child.getTop() + shadowPadding.top, child.getRight() - shadowPadding.right, child.getBottom() - shadowPadding.bottom);
            return AUTO_SHRINK_DEFAULT;
        }

        private void offsetIfNeeded(CoordinatorLayout parent, ExtendedFloatingActionButton fab) {
            Rect padding = fab.shadowPadding;
            if (padding != null && padding.centerX() > 0 && padding.centerY() > 0) {
                CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
                int offsetTB = 0;
                int offsetLR = 0;
                if (fab.getRight() >= parent.getWidth() - lp.rightMargin) {
                    offsetLR = padding.right;
                } else if (fab.getLeft() <= lp.leftMargin) {
                    offsetLR = -padding.left;
                }
                if (fab.getBottom() >= parent.getHeight() - lp.bottomMargin) {
                    offsetTB = padding.bottom;
                } else if (fab.getTop() <= lp.topMargin) {
                    offsetTB = -padding.top;
                }
                if (offsetTB != 0) {
                    ViewCompat.offsetTopAndBottom(fab, offsetTB);
                }
                if (offsetLR != 0) {
                    ViewCompat.offsetLeftAndRight(fab, offsetLR);
                }
            }
        }
    }

    class HideStrategy extends BaseMotionStrategy {
        private boolean isCancelled;

        public HideStrategy(AnimatorTracker animatorTracker) {
            super(ExtendedFloatingActionButton.this, animatorTracker);
        }

        public void performNow() {
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        public void onChange(OnChangedCallback callback) {
            if (callback != null) {
                callback.onHidden(ExtendedFloatingActionButton.this);
            }
        }

        public boolean shouldCancel() {
            return ExtendedFloatingActionButton.this.isOrWillBeHidden();
        }

        public int getDefaultMotionSpecResource() {
            return C1124R.animator.mtrl_extended_fab_hide_motion_spec;
        }

        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            this.isCancelled = false;
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.animState = 1;
        }

        public void onAnimationCancel() {
            super.onAnimationCancel();
            this.isCancelled = true;
        }

        public void onAnimationEnd() {
            super.onAnimationEnd();
            ExtendedFloatingActionButton.this.animState = 0;
            if (!this.isCancelled) {
                ExtendedFloatingActionButton.this.setVisibility(8);
            }
        }
    }

    public static abstract class OnChangedCallback {
        public void onShown(ExtendedFloatingActionButton extendedFab) {
        }

        public void onHidden(ExtendedFloatingActionButton extendedFab) {
        }

        public void onExtended(ExtendedFloatingActionButton extendedFab) {
        }

        public void onShrunken(ExtendedFloatingActionButton extendedFab) {
        }
    }

    class ShowStrategy extends BaseMotionStrategy {
        public ShowStrategy(AnimatorTracker animatorTracker) {
            super(ExtendedFloatingActionButton.this, animatorTracker);
        }

        public void performNow() {
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.setAlpha(1.0f);
            ExtendedFloatingActionButton.this.setScaleY(1.0f);
            ExtendedFloatingActionButton.this.setScaleX(1.0f);
        }

        public void onChange(OnChangedCallback callback) {
            if (callback != null) {
                callback.onShown(ExtendedFloatingActionButton.this);
            }
        }

        public int getDefaultMotionSpecResource() {
            return C1124R.animator.mtrl_extended_fab_show_motion_spec;
        }

        public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.animState = 2;
        }

        public void onAnimationEnd() {
            super.onAnimationEnd();
            ExtendedFloatingActionButton.this.animState = 0;
        }

        public boolean shouldCancel() {
            return ExtendedFloatingActionButton.this.isOrWillBeShown();
        }
    }

    interface Size {
        int getHeight();

        int getWidth();
    }

    public ExtendedFloatingActionButton(Context context) {
        this(context, null);
    }

    public ExtendedFloatingActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, C1124R.attr.extendedFloatingActionButtonStyle);
    }

    public ExtendedFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.shadowPadding = new Rect();
        this.animState = 0;
        this.changeVisibilityTracker = new AnimatorTracker();
        this.showStrategy = new ShowStrategy(this.changeVisibilityTracker);
        this.hideStrategy = new HideStrategy(this.changeVisibilityTracker);
        this.isExtended = true;
        this.behavior = new ExtendedFloatingActionButtonBehavior(context, attrs);
        TypedArray a = ThemeEnforcement.obtainStyledAttributes(context, attrs, C1124R.styleable.ExtendedFloatingActionButton, defStyleAttr, DEF_STYLE_RES, new int[0]);
        MotionSpec showMotionSpec = MotionSpec.createFromAttribute(context, a, C1124R.styleable.ExtendedFloatingActionButton_showMotionSpec);
        MotionSpec hideMotionSpec = MotionSpec.createFromAttribute(context, a, C1124R.styleable.ExtendedFloatingActionButton_hideMotionSpec);
        MotionSpec extendMotionSpec = MotionSpec.createFromAttribute(context, a, C1124R.styleable.ExtendedFloatingActionButton_extendMotionSpec);
        MotionSpec shrinkMotionSpec = MotionSpec.createFromAttribute(context, a, C1124R.styleable.ExtendedFloatingActionButton_shrinkMotionSpec);
        AnimatorTracker changeSizeTracker = new AnimatorTracker();
        this.extendStrategy = new ChangeSizeStrategy(changeSizeTracker, new Size() {
            public int getWidth() {
                return ExtendedFloatingActionButton.this.getMeasuredWidth();
            }

            public int getHeight() {
                return ExtendedFloatingActionButton.this.getMeasuredHeight();
            }
        }, true);
        this.shrinkStrategy = new ChangeSizeStrategy(changeSizeTracker, new Size() {
            public int getWidth() {
                return ExtendedFloatingActionButton.this.getCollapsedSize();
            }

            public int getHeight() {
                return ExtendedFloatingActionButton.this.getCollapsedSize();
            }
        }, false);
        this.showStrategy.setMotionSpec(showMotionSpec);
        this.hideStrategy.setMotionSpec(hideMotionSpec);
        this.extendStrategy.setMotionSpec(extendMotionSpec);
        this.shrinkStrategy.setMotionSpec(shrinkMotionSpec);
        a.recycle();
        setShapeAppearanceModel(ShapeAppearanceModel.builder(context, attrs, defStyleAttr, DEF_STYLE_RES, ShapeAppearanceModel.PILL).build());
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.isExtended && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.isExtended = false;
            this.shrinkStrategy.performNow();
        }
    }

    public Behavior<ExtendedFloatingActionButton> getBehavior() {
        return this.behavior;
    }

    public void setExtended(boolean extended) {
        if (this.isExtended != extended) {
            MotionStrategy motionStrategy = extended ? this.extendStrategy : this.shrinkStrategy;
            if (!motionStrategy.shouldCancel()) {
                motionStrategy.performNow();
            }
        }
    }

    public final boolean isExtended() {
        return this.isExtended;
    }

    public void addOnShowAnimationListener(AnimatorListener listener) {
        this.showStrategy.addAnimationListener(listener);
    }

    public void removeOnShowAnimationListener(AnimatorListener listener) {
        this.showStrategy.removeAnimationListener(listener);
    }

    public void addOnHideAnimationListener(AnimatorListener listener) {
        this.hideStrategy.addAnimationListener(listener);
    }

    public void removeOnHideAnimationListener(AnimatorListener listener) {
        this.hideStrategy.removeAnimationListener(listener);
    }

    public void addOnShrinkAnimationListener(AnimatorListener listener) {
        this.shrinkStrategy.addAnimationListener(listener);
    }

    public void removeOnShrinkAnimationListener(AnimatorListener listener) {
        this.shrinkStrategy.removeAnimationListener(listener);
    }

    public void addOnExtendAnimationListener(AnimatorListener listener) {
        this.extendStrategy.addAnimationListener(listener);
    }

    public void removeOnExtendAnimationListener(AnimatorListener listener) {
        this.extendStrategy.removeAnimationListener(listener);
    }

    public void hide() {
        performMotion(this.hideStrategy, null);
    }

    public void hide(OnChangedCallback callback) {
        performMotion(this.hideStrategy, callback);
    }

    public void show() {
        performMotion(this.showStrategy, null);
    }

    public void show(OnChangedCallback callback) {
        performMotion(this.showStrategy, callback);
    }

    public void extend() {
        performMotion(this.extendStrategy, null);
    }

    public void extend(OnChangedCallback callback) {
        performMotion(this.extendStrategy, callback);
    }

    public void shrink() {
        performMotion(this.shrinkStrategy, null);
    }

    public void shrink(OnChangedCallback callback) {
        performMotion(this.shrinkStrategy, callback);
    }

    public MotionSpec getShowMotionSpec() {
        return this.showStrategy.getMotionSpec();
    }

    public void setShowMotionSpec(MotionSpec spec) {
        this.showStrategy.setMotionSpec(spec);
    }

    public void setShowMotionSpecResource(int id) {
        setShowMotionSpec(MotionSpec.createFromResource(getContext(), id));
    }

    public MotionSpec getHideMotionSpec() {
        return this.hideStrategy.getMotionSpec();
    }

    public void setHideMotionSpec(MotionSpec spec) {
        this.hideStrategy.setMotionSpec(spec);
    }

    public void setHideMotionSpecResource(int id) {
        setHideMotionSpec(MotionSpec.createFromResource(getContext(), id));
    }

    public MotionSpec getExtendMotionSpec() {
        return this.extendStrategy.getMotionSpec();
    }

    public void setExtendMotionSpec(MotionSpec spec) {
        this.extendStrategy.setMotionSpec(spec);
    }

    public void setExtendMotionSpecResource(int id) {
        setExtendMotionSpec(MotionSpec.createFromResource(getContext(), id));
    }

    public MotionSpec getShrinkMotionSpec() {
        return this.shrinkStrategy.getMotionSpec();
    }

    public void setShrinkMotionSpec(MotionSpec spec) {
        this.shrinkStrategy.setMotionSpec(spec);
    }

    public void setShrinkMotionSpecResource(int id) {
        setShrinkMotionSpec(MotionSpec.createFromResource(getContext(), id));
    }

    /* access modifiers changed from: private */
    public void performMotion(final MotionStrategy strategy, final OnChangedCallback callback) {
        if (!strategy.shouldCancel()) {
            if (!shouldAnimateVisibilityChange()) {
                strategy.performNow();
                strategy.onChange(callback);
                return;
            }
            measure(0, 0);
            Animator animator = strategy.createAnimator();
            animator.addListener(new AnimatorListenerAdapter() {
                private boolean cancelled;

                public void onAnimationStart(Animator animation) {
                    strategy.onAnimationStart(animation);
                    this.cancelled = false;
                }

                public void onAnimationCancel(Animator animation) {
                    this.cancelled = true;
                    strategy.onAnimationCancel();
                }

                public void onAnimationEnd(Animator animation) {
                    strategy.onAnimationEnd();
                    if (!this.cancelled) {
                        strategy.onChange(callback);
                    }
                }
            });
            for (AnimatorListener l : strategy.getListeners()) {
                animator.addListener(l);
            }
            animator.start();
        }
    }

    /* access modifiers changed from: private */
    public boolean isOrWillBeShown() {
        boolean z = false;
        if (getVisibility() != 0) {
            if (this.animState == 2) {
                z = true;
            }
            return z;
        }
        if (this.animState != 1) {
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public boolean isOrWillBeHidden() {
        boolean z = false;
        if (getVisibility() == 0) {
            if (this.animState == 1) {
                z = true;
            }
            return z;
        }
        if (this.animState != 2) {
            z = true;
        }
        return z;
    }

    private boolean shouldAnimateVisibilityChange() {
        return ViewCompat.isLaidOut(this) && !isInEditMode();
    }

    /* access modifiers changed from: 0000 */
    public int getCollapsedSize() {
        return (Math.min(ViewCompat.getPaddingStart(this), ViewCompat.getPaddingEnd(this)) * 2) + getIconSize();
    }
}
