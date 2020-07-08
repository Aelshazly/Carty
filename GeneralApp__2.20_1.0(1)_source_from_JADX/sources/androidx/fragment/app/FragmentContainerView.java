package androidx.fragment.app;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.fragment.C1016R;
import java.util.ArrayList;

public final class FragmentContainerView extends FrameLayout {
    private ArrayList<View> mDisappearingFragmentChildren;
    private boolean mDrawDisappearingViewsFirst;
    private ArrayList<View> mTransitioningFragmentViews;

    public FragmentContainerView(Context context) {
        super(context);
        this.mDrawDisappearingViewsFirst = true;
    }

    public FragmentContainerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FragmentContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mDrawDisappearingViewsFirst = true;
        if (!isInEditMode()) {
            throw new UnsupportedOperationException("FragmentContainerView must be within a FragmentActivity to be instantiated from XML.");
        }
    }

    FragmentContainerView(Context context, AttributeSet attrs, FragmentManager fm) {
        String tagMessage;
        super(context, attrs);
        this.mDrawDisappearingViewsFirst = true;
        String name = attrs.getClassAttribute();
        TypedArray a = context.obtainStyledAttributes(attrs, C1016R.styleable.FragmentContainerView);
        if (name == null) {
            name = a.getString(C1016R.styleable.FragmentContainerView_android_name);
        }
        String tag = a.getString(C1016R.styleable.FragmentContainerView_android_tag);
        a.recycle();
        int id = getId();
        Fragment existingFragment = fm.findFragmentById(id);
        if (name != null && existingFragment == null) {
            if (id <= 0) {
                if (tag != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(" with tag ");
                    sb.append(tag);
                    tagMessage = sb.toString();
                } else {
                    tagMessage = "";
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("FragmentContainerView must have an android:id to add Fragment ");
                sb2.append(name);
                sb2.append(tagMessage);
                throw new IllegalStateException(sb2.toString());
            }
            Fragment containerFragment = fm.getFragmentFactory().instantiate(context.getClassLoader(), name);
            containerFragment.onInflate(context, attrs, (Bundle) null);
            fm.beginTransaction().setReorderingAllowed(true).add((ViewGroup) this, containerFragment, tag).commitNowAllowingStateLoss();
        }
    }

    public void setLayoutTransition(LayoutTransition transition) {
        if (VERSION.SDK_INT < 18) {
            super.setLayoutTransition(transition);
            return;
        }
        throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
    }

    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).dispatchApplyWindowInsets(new WindowInsets(insets));
        }
        return insets;
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        if (this.mDrawDisappearingViewsFirst && this.mDisappearingFragmentChildren != null) {
            for (int i = 0; i < this.mDisappearingFragmentChildren.size(); i++) {
                super.drawChild(canvas, (View) this.mDisappearingFragmentChildren.get(i), getDrawingTime());
            }
        }
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if (this.mDrawDisappearingViewsFirst) {
            ArrayList<View> arrayList = this.mDisappearingFragmentChildren;
            if (arrayList != null && arrayList.size() > 0 && this.mDisappearingFragmentChildren.contains(child)) {
                return false;
            }
        }
        return super.drawChild(canvas, child, drawingTime);
    }

    public void startViewTransition(View view) {
        if (view.getParent() == this) {
            if (this.mTransitioningFragmentViews == null) {
                this.mTransitioningFragmentViews = new ArrayList<>();
            }
            this.mTransitioningFragmentViews.add(view);
        }
        super.startViewTransition(view);
    }

    public void endViewTransition(View view) {
        ArrayList<View> arrayList = this.mTransitioningFragmentViews;
        if (arrayList != null) {
            arrayList.remove(view);
            ArrayList<View> arrayList2 = this.mDisappearingFragmentChildren;
            if (arrayList2 != null && arrayList2.remove(view)) {
                this.mDrawDisappearingViewsFirst = true;
            }
        }
        super.endViewTransition(view);
    }

    /* access modifiers changed from: 0000 */
    public void setDrawDisappearingViewsLast(boolean drawDisappearingViewsFirst) {
        this.mDrawDisappearingViewsFirst = drawDisappearingViewsFirst;
    }

    public void addView(View child, int index, LayoutParams params) {
        if (FragmentManager.getViewFragment(child) != null) {
            super.addView(child, index, params);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Views added to a FragmentContainerView must be associated with a Fragment. View ");
        sb.append(child);
        sb.append(" is not associated with a Fragment.");
        throw new IllegalStateException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public boolean addViewInLayout(View child, int index, LayoutParams params, boolean preventRequestLayout) {
        if (FragmentManager.getViewFragment(child) != null) {
            return super.addViewInLayout(child, index, params, preventRequestLayout);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Views added to a FragmentContainerView must be associated with a Fragment. View ");
        sb.append(child);
        sb.append(" is not associated with a Fragment.");
        throw new IllegalStateException(sb.toString());
    }

    public void removeViewAt(int index) {
        addDisappearingFragmentView(getChildAt(index));
        super.removeViewAt(index);
    }

    public void removeViewInLayout(View view) {
        addDisappearingFragmentView(view);
        super.removeViewInLayout(view);
    }

    public void removeView(View view) {
        addDisappearingFragmentView(view);
        super.removeView(view);
    }

    public void removeViews(int start, int count) {
        for (int i = start; i < start + count; i++) {
            addDisappearingFragmentView(getChildAt(i));
        }
        super.removeViews(start, count);
    }

    public void removeViewsInLayout(int start, int count) {
        for (int i = start; i < start + count; i++) {
            addDisappearingFragmentView(getChildAt(i));
        }
        super.removeViewsInLayout(start, count);
    }

    public void removeAllViewsInLayout() {
        for (int i = getChildCount() - 1; i >= 0; i--) {
            addDisappearingFragmentView(getChildAt(i));
        }
        super.removeAllViewsInLayout();
    }

    /* access modifiers changed from: protected */
    public void removeDetachedView(View child, boolean animate) {
        if (animate) {
            addDisappearingFragmentView(child);
        }
        super.removeDetachedView(child, animate);
    }

    private void addDisappearingFragmentView(View v) {
        if (v.getAnimation() == null) {
            ArrayList<View> arrayList = this.mTransitioningFragmentViews;
            if (arrayList == null || !arrayList.contains(v)) {
                return;
            }
        }
        if (this.mDisappearingFragmentChildren == null) {
            this.mDisappearingFragmentChildren = new ArrayList<>();
        }
        this.mDisappearingFragmentChildren.add(v);
    }
}
