package com.bumptech.glide.request.target;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.WindowManager;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Deprecated
public abstract class ViewTarget<T extends View, Z> extends BaseTarget<Z> {
    private static final String TAG = "ViewTarget";
    private static boolean isTagUsedAtLeastOnce;
    private static Integer tagId;
    private OnAttachStateChangeListener attachStateListener;
    private boolean isAttachStateListenerAdded;
    private boolean isClearedByUs;
    private final SizeDeterminer sizeDeterminer;
    protected final T view;

    static final class SizeDeterminer {
        private static final int PENDING_SIZE = 0;
        static Integer maxDisplayLength;
        private final List<SizeReadyCallback> cbs = new ArrayList();
        private SizeDeterminerLayoutListener layoutListener;
        private final View view;
        boolean waitForLayout;

        private static final class SizeDeterminerLayoutListener implements OnPreDrawListener {
            private final WeakReference<SizeDeterminer> sizeDeterminerRef;

            SizeDeterminerLayoutListener(SizeDeterminer sizeDeterminer) {
                this.sizeDeterminerRef = new WeakReference<>(sizeDeterminer);
            }

            public boolean onPreDraw() {
                String str = ViewTarget.TAG;
                if (Log.isLoggable(str, 2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("OnGlobalLayoutListener called attachStateListener=");
                    sb.append(this);
                    Log.v(str, sb.toString());
                }
                SizeDeterminer sizeDeterminer = (SizeDeterminer) this.sizeDeterminerRef.get();
                if (sizeDeterminer != null) {
                    sizeDeterminer.checkCurrentDimens();
                }
                return true;
            }
        }

        SizeDeterminer(View view2) {
            this.view = view2;
        }

        private static int getMaxDisplayLength(Context context) {
            if (maxDisplayLength == null) {
                Display display = ((WindowManager) Preconditions.checkNotNull((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
                Point displayDimensions = new Point();
                display.getSize(displayDimensions);
                maxDisplayLength = Integer.valueOf(Math.max(displayDimensions.x, displayDimensions.y));
            }
            return maxDisplayLength.intValue();
        }

        private void notifyCbs(int width, int height) {
            Iterator it = new ArrayList(this.cbs).iterator();
            while (it.hasNext()) {
                ((SizeReadyCallback) it.next()).onSizeReady(width, height);
            }
        }

        /* access modifiers changed from: 0000 */
        public void checkCurrentDimens() {
            if (!this.cbs.isEmpty()) {
                int currentWidth = getTargetWidth();
                int currentHeight = getTargetHeight();
                if (isViewStateAndSizeValid(currentWidth, currentHeight)) {
                    notifyCbs(currentWidth, currentHeight);
                    clearCallbacksAndListener();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void getSize(SizeReadyCallback cb) {
            int currentWidth = getTargetWidth();
            int currentHeight = getTargetHeight();
            if (isViewStateAndSizeValid(currentWidth, currentHeight)) {
                cb.onSizeReady(currentWidth, currentHeight);
                return;
            }
            if (!this.cbs.contains(cb)) {
                this.cbs.add(cb);
            }
            if (this.layoutListener == null) {
                ViewTreeObserver observer = this.view.getViewTreeObserver();
                this.layoutListener = new SizeDeterminerLayoutListener(this);
                observer.addOnPreDrawListener(this.layoutListener);
            }
        }

        /* access modifiers changed from: 0000 */
        public void removeCallback(SizeReadyCallback cb) {
            this.cbs.remove(cb);
        }

        /* access modifiers changed from: 0000 */
        public void clearCallbacksAndListener() {
            ViewTreeObserver observer = this.view.getViewTreeObserver();
            if (observer.isAlive()) {
                observer.removeOnPreDrawListener(this.layoutListener);
            }
            this.layoutListener = null;
            this.cbs.clear();
        }

        private boolean isViewStateAndSizeValid(int width, int height) {
            return isDimensionValid(width) && isDimensionValid(height);
        }

        private int getTargetHeight() {
            int verticalPadding = this.view.getPaddingTop() + this.view.getPaddingBottom();
            LayoutParams layoutParams = this.view.getLayoutParams();
            return getTargetDimen(this.view.getHeight(), layoutParams != null ? layoutParams.height : 0, verticalPadding);
        }

        private int getTargetWidth() {
            int horizontalPadding = this.view.getPaddingLeft() + this.view.getPaddingRight();
            LayoutParams layoutParams = this.view.getLayoutParams();
            return getTargetDimen(this.view.getWidth(), layoutParams != null ? layoutParams.width : 0, horizontalPadding);
        }

        private int getTargetDimen(int viewSize, int paramSize, int paddingSize) {
            int adjustedParamSize = paramSize - paddingSize;
            if (adjustedParamSize > 0) {
                return adjustedParamSize;
            }
            if (this.waitForLayout && this.view.isLayoutRequested()) {
                return 0;
            }
            int adjustedViewSize = viewSize - paddingSize;
            if (adjustedViewSize > 0) {
                return adjustedViewSize;
            }
            if (this.view.isLayoutRequested() || paramSize != -2) {
                return 0;
            }
            String str = ViewTarget.TAG;
            if (Log.isLoggable(str, 4)) {
                Log.i(str, "Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use .override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.");
            }
            return getMaxDisplayLength(this.view.getContext());
        }

        private boolean isDimensionValid(int size) {
            return size > 0 || size == Integer.MIN_VALUE;
        }
    }

    public ViewTarget(T view2) {
        this.view = (View) Preconditions.checkNotNull(view2);
        this.sizeDeterminer = new SizeDeterminer(view2);
    }

    @Deprecated
    public ViewTarget(T view2, boolean waitForLayout) {
        this(view2);
        if (waitForLayout) {
            waitForLayout();
        }
    }

    public final ViewTarget<T, Z> clearOnDetach() {
        if (this.attachStateListener != null) {
            return this;
        }
        this.attachStateListener = new OnAttachStateChangeListener() {
            public void onViewAttachedToWindow(View v) {
                ViewTarget.this.resumeMyRequest();
            }

            public void onViewDetachedFromWindow(View v) {
                ViewTarget.this.pauseMyRequest();
            }
        };
        maybeAddAttachStateListener();
        return this;
    }

    /* access modifiers changed from: 0000 */
    public void resumeMyRequest() {
        Request request = getRequest();
        if (request != null && request.isCleared()) {
            request.begin();
        }
    }

    /* access modifiers changed from: 0000 */
    public void pauseMyRequest() {
        Request request = getRequest();
        if (request != null) {
            this.isClearedByUs = true;
            request.clear();
            this.isClearedByUs = false;
        }
    }

    public final ViewTarget<T, Z> waitForLayout() {
        this.sizeDeterminer.waitForLayout = true;
        return this;
    }

    public void onLoadStarted(Drawable placeholder) {
        super.onLoadStarted(placeholder);
        maybeAddAttachStateListener();
    }

    private void maybeAddAttachStateListener() {
        OnAttachStateChangeListener onAttachStateChangeListener = this.attachStateListener;
        if (onAttachStateChangeListener != null && !this.isAttachStateListenerAdded) {
            this.view.addOnAttachStateChangeListener(onAttachStateChangeListener);
            this.isAttachStateListenerAdded = true;
        }
    }

    private void maybeRemoveAttachStateListener() {
        OnAttachStateChangeListener onAttachStateChangeListener = this.attachStateListener;
        if (onAttachStateChangeListener != null && this.isAttachStateListenerAdded) {
            this.view.removeOnAttachStateChangeListener(onAttachStateChangeListener);
            this.isAttachStateListenerAdded = false;
        }
    }

    public T getView() {
        return this.view;
    }

    public void getSize(SizeReadyCallback cb) {
        this.sizeDeterminer.getSize(cb);
    }

    public void removeCallback(SizeReadyCallback cb) {
        this.sizeDeterminer.removeCallback(cb);
    }

    public void onLoadCleared(Drawable placeholder) {
        super.onLoadCleared(placeholder);
        this.sizeDeterminer.clearCallbacksAndListener();
        if (!this.isClearedByUs) {
            maybeRemoveAttachStateListener();
        }
    }

    public void setRequest(Request request) {
        setTag(request);
    }

    public Request getRequest() {
        Object tag = getTag();
        if (tag == null) {
            return null;
        }
        if (tag instanceof Request) {
            return (Request) tag;
        }
        throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Target for: ");
        sb.append(this.view);
        return sb.toString();
    }

    private void setTag(Object tag) {
        Integer num = tagId;
        if (num == null) {
            isTagUsedAtLeastOnce = true;
            this.view.setTag(tag);
            return;
        }
        this.view.setTag(num.intValue(), tag);
    }

    private Object getTag() {
        Integer num = tagId;
        if (num == null) {
            return this.view.getTag();
        }
        return this.view.getTag(num.intValue());
    }

    public static void setTagId(int tagId2) {
        if (tagId != null || isTagUsedAtLeastOnce) {
            throw new IllegalArgumentException("You cannot set the tag id more than once or change the tag id after the first request has been made");
        }
        tagId = Integer.valueOf(tagId2);
    }
}
