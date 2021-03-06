package androidx.viewpager2.widget;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;
import java.util.Locale;

final class ScrollEventAdapter extends OnScrollListener {
    private static final int NO_POSITION = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_IN_PROGRESS_FAKE_DRAG = 4;
    private static final int STATE_IN_PROGRESS_IMMEDIATE_SCROLL = 3;
    private static final int STATE_IN_PROGRESS_MANUAL_DRAG = 1;
    private static final int STATE_IN_PROGRESS_SMOOTH_SCROLL = 2;
    private int mAdapterState;
    private OnPageChangeCallback mCallback;
    private boolean mDataSetChangeHappened;
    private boolean mDispatchSelected;
    private int mDragStartPosition;
    private boolean mFakeDragging;
    private final LinearLayoutManager mLayoutManager = ((LinearLayoutManager) this.mRecyclerView.getLayoutManager());
    private final RecyclerView mRecyclerView = this.mViewPager.mRecyclerView;
    private boolean mScrollHappened;
    private int mScrollState;
    private ScrollEventValues mScrollValues = new ScrollEventValues();
    private int mTarget;
    private final ViewPager2 mViewPager;

    private static final class ScrollEventValues {
        float mOffset;
        int mOffsetPx;
        int mPosition;

        ScrollEventValues() {
        }

        /* access modifiers changed from: 0000 */
        public void reset() {
            this.mPosition = -1;
            this.mOffset = 0.0f;
            this.mOffsetPx = 0;
        }
    }

    ScrollEventAdapter(ViewPager2 viewPager) {
        this.mViewPager = viewPager;
        resetState();
    }

    private void resetState() {
        this.mAdapterState = 0;
        this.mScrollState = 0;
        this.mScrollValues.reset();
        this.mDragStartPosition = -1;
        this.mTarget = -1;
        this.mDispatchSelected = false;
        this.mScrollHappened = false;
        this.mFakeDragging = false;
        this.mDataSetChangeHappened = false;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (!(this.mAdapterState == 1 && this.mScrollState == 1) && newState == 1) {
            startDrag(false);
        } else if (!isInAnyDraggingState() || newState != 2) {
            if (isInAnyDraggingState() && newState == 0) {
                boolean dispatchIdle = false;
                updateScrollEventValues();
                if (!this.mScrollHappened) {
                    if (this.mScrollValues.mPosition != -1) {
                        dispatchScrolled(this.mScrollValues.mPosition, 0.0f, 0);
                    }
                    dispatchIdle = true;
                } else if (this.mScrollValues.mOffsetPx == 0) {
                    dispatchIdle = true;
                    if (this.mDragStartPosition != this.mScrollValues.mPosition) {
                        dispatchSelected(this.mScrollValues.mPosition);
                    }
                }
                if (dispatchIdle) {
                    dispatchStateChanged(0);
                    resetState();
                }
            }
            if (this.mAdapterState == 2 && newState == 0 && this.mDataSetChangeHappened) {
                updateScrollEventValues();
                if (this.mScrollValues.mOffsetPx == 0) {
                    if (this.mTarget != this.mScrollValues.mPosition) {
                        dispatchSelected(this.mScrollValues.mPosition == -1 ? 0 : this.mScrollValues.mPosition);
                    }
                    dispatchStateChanged(0);
                    resetState();
                }
            }
        } else {
            if (this.mScrollHappened) {
                dispatchStateChanged(2);
                this.mDispatchSelected = true;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        if ((r8 < 0) == r6.mViewPager.isRtl()) goto L_0x0022;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onScrolled(androidx.recyclerview.widget.RecyclerView r7, int r8, int r9) {
        /*
            r6 = this;
            r0 = 1
            r6.mScrollHappened = r0
            r6.updateScrollEventValues()
            boolean r1 = r6.mDispatchSelected
            r2 = -1
            r3 = 0
            if (r1 == 0) goto L_0x0041
            r6.mDispatchSelected = r3
            if (r9 > 0) goto L_0x0022
            if (r9 != 0) goto L_0x0020
            if (r8 >= 0) goto L_0x0016
            r1 = 1
            goto L_0x0017
        L_0x0016:
            r1 = 0
        L_0x0017:
            androidx.viewpager2.widget.ViewPager2 r4 = r6.mViewPager
            boolean r4 = r4.isRtl()
            if (r1 != r4) goto L_0x0020
            goto L_0x0022
        L_0x0020:
            r1 = 0
            goto L_0x0023
        L_0x0022:
            r1 = 1
        L_0x0023:
            if (r1 == 0) goto L_0x0031
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r4 = r6.mScrollValues
            int r4 = r4.mOffsetPx
            if (r4 == 0) goto L_0x0031
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r4 = r6.mScrollValues
            int r4 = r4.mPosition
            int r4 = r4 + r0
            goto L_0x0035
        L_0x0031:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r4 = r6.mScrollValues
            int r4 = r4.mPosition
        L_0x0035:
            r6.mTarget = r4
            int r4 = r6.mDragStartPosition
            int r5 = r6.mTarget
            if (r4 == r5) goto L_0x0052
            r6.dispatchSelected(r5)
            goto L_0x0052
        L_0x0041:
            int r1 = r6.mAdapterState
            if (r1 != 0) goto L_0x0052
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r1 = r6.mScrollValues
            int r1 = r1.mPosition
            if (r1 != r2) goto L_0x004d
            r4 = 0
            goto L_0x004e
        L_0x004d:
            r4 = r1
        L_0x004e:
            r6.dispatchSelected(r4)
            goto L_0x0053
        L_0x0052:
        L_0x0053:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r1 = r6.mScrollValues
            int r1 = r1.mPosition
            if (r1 != r2) goto L_0x005b
            r1 = 0
            goto L_0x005f
        L_0x005b:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r1 = r6.mScrollValues
            int r1 = r1.mPosition
        L_0x005f:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r4 = r6.mScrollValues
            float r4 = r4.mOffset
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r6.mScrollValues
            int r5 = r5.mOffsetPx
            r6.dispatchScrolled(r1, r4, r5)
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r1 = r6.mScrollValues
            int r1 = r1.mPosition
            int r4 = r6.mTarget
            if (r1 == r4) goto L_0x0074
            if (r4 != r2) goto L_0x0084
        L_0x0074:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r1 = r6.mScrollValues
            int r1 = r1.mOffsetPx
            if (r1 != 0) goto L_0x0084
            int r1 = r6.mScrollState
            if (r1 == r0) goto L_0x0084
            r6.dispatchStateChanged(r3)
            r6.resetState()
        L_0x0084:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager2.widget.ScrollEventAdapter.onScrolled(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    private void updateScrollEventValues() {
        int start;
        int sizePx;
        ScrollEventValues values = this.mScrollValues;
        values.mPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        if (values.mPosition == -1) {
            values.reset();
            return;
        }
        View firstVisibleView = this.mLayoutManager.findViewByPosition(values.mPosition);
        if (firstVisibleView == null) {
            values.reset();
            return;
        }
        int leftDecorations = this.mLayoutManager.getLeftDecorationWidth(firstVisibleView);
        int rightDecorations = this.mLayoutManager.getRightDecorationWidth(firstVisibleView);
        int topDecorations = this.mLayoutManager.getTopDecorationHeight(firstVisibleView);
        int bottomDecorations = this.mLayoutManager.getBottomDecorationHeight(firstVisibleView);
        LayoutParams params = firstVisibleView.getLayoutParams();
        if (params instanceof MarginLayoutParams) {
            MarginLayoutParams margin = (MarginLayoutParams) params;
            leftDecorations += margin.leftMargin;
            rightDecorations += margin.rightMargin;
            topDecorations += margin.topMargin;
            bottomDecorations += margin.bottomMargin;
        }
        int decoratedHeight = firstVisibleView.getHeight() + topDecorations + bottomDecorations;
        int decoratedWidth = firstVisibleView.getWidth() + leftDecorations + rightDecorations;
        if (this.mLayoutManager.getOrientation() == 0) {
            sizePx = decoratedWidth;
            start = (firstVisibleView.getLeft() - leftDecorations) - this.mRecyclerView.getPaddingLeft();
            if (this.mViewPager.isRtl()) {
                start = -start;
            }
        } else {
            sizePx = decoratedHeight;
            start = (firstVisibleView.getTop() - topDecorations) - this.mRecyclerView.getPaddingTop();
        }
        values.mOffsetPx = -start;
        if (values.mOffsetPx >= 0) {
            values.mOffset = sizePx == 0 ? 0.0f : ((float) values.mOffsetPx) / ((float) sizePx);
        } else if (new AnimateLayoutChangeDetector(this.mLayoutManager).mayHaveInterferingAnimations()) {
            throw new IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.");
        } else {
            throw new IllegalStateException(String.format(Locale.US, "Page can only be offset by a positive amount, not by %d", new Object[]{Integer.valueOf(values.mOffsetPx)}));
        }
    }

    private void startDrag(boolean isFakeDrag) {
        this.mFakeDragging = isFakeDrag;
        this.mAdapterState = isFakeDrag ? 4 : 1;
        int i = this.mTarget;
        if (i != -1) {
            this.mDragStartPosition = i;
            this.mTarget = -1;
        } else if (this.mDragStartPosition == -1) {
            this.mDragStartPosition = getPosition();
        }
        dispatchStateChanged(1);
    }

    /* access modifiers changed from: 0000 */
    public void notifyDataSetChangeHappened() {
        this.mDataSetChangeHappened = true;
    }

    /* access modifiers changed from: 0000 */
    public void notifyProgrammaticScroll(int target, boolean smooth) {
        this.mAdapterState = smooth ? 2 : 3;
        boolean hasNewTarget = false;
        this.mFakeDragging = false;
        if (this.mTarget != target) {
            hasNewTarget = true;
        }
        this.mTarget = target;
        dispatchStateChanged(2);
        if (hasNewTarget) {
            dispatchSelected(target);
        }
    }

    /* access modifiers changed from: 0000 */
    public void notifyBeginFakeDrag() {
        this.mAdapterState = 4;
        startDrag(true);
    }

    /* access modifiers changed from: 0000 */
    public void notifyEndFakeDrag() {
        if (!isDragging() || this.mFakeDragging) {
            this.mFakeDragging = false;
            updateScrollEventValues();
            if (this.mScrollValues.mOffsetPx == 0) {
                if (this.mScrollValues.mPosition != this.mDragStartPosition) {
                    dispatchSelected(this.mScrollValues.mPosition);
                }
                dispatchStateChanged(0);
                resetState();
            } else {
                dispatchStateChanged(2);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void setOnPageChangeCallback(OnPageChangeCallback callback) {
        this.mCallback = callback;
    }

    /* access modifiers changed from: 0000 */
    public int getScrollState() {
        return this.mScrollState;
    }

    /* access modifiers changed from: 0000 */
    public boolean isIdle() {
        return this.mScrollState == 0;
    }

    /* access modifiers changed from: 0000 */
    public boolean isDragging() {
        return this.mScrollState == 1;
    }

    /* access modifiers changed from: 0000 */
    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    private boolean isInAnyDraggingState() {
        int i = this.mAdapterState;
        return i == 1 || i == 4;
    }

    /* access modifiers changed from: 0000 */
    public double getRelativeScrollPosition() {
        updateScrollEventValues();
        return ((double) this.mScrollValues.mPosition) + ((double) this.mScrollValues.mOffset);
    }

    private void dispatchStateChanged(int state) {
        if ((this.mAdapterState != 3 || this.mScrollState != 0) && this.mScrollState != state) {
            this.mScrollState = state;
            OnPageChangeCallback onPageChangeCallback = this.mCallback;
            if (onPageChangeCallback != null) {
                onPageChangeCallback.onPageScrollStateChanged(state);
            }
        }
    }

    private void dispatchSelected(int target) {
        OnPageChangeCallback onPageChangeCallback = this.mCallback;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageSelected(target);
        }
    }

    private void dispatchScrolled(int position, float offset, int offsetPx) {
        OnPageChangeCallback onPageChangeCallback = this.mCallback;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageScrolled(position, offset, offsetPx);
        }
    }

    private int getPosition() {
        return this.mLayoutManager.findFirstVisibleItemPosition();
    }
}
