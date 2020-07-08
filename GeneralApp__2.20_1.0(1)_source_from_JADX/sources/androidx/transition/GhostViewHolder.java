package androidx.transition;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import java.util.ArrayList;

class GhostViewHolder extends FrameLayout {
    private boolean mAttached = true;
    private ViewGroup mParent;

    GhostViewHolder(ViewGroup parent) {
        super(parent.getContext());
        setClipChildren(false);
        this.mParent = parent;
        this.mParent.setTag(C1070R.C1073id.ghost_view_holder, this);
        ViewGroupUtils.getOverlay(this.mParent).add(this);
    }

    public void onViewAdded(View child) {
        if (this.mAttached) {
            super.onViewAdded(child);
            return;
        }
        throw new IllegalStateException("This GhostViewHolder is detached!");
    }

    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        if ((getChildCount() == 1 && getChildAt(0) == child) || getChildCount() == 0) {
            this.mParent.setTag(C1070R.C1073id.ghost_view_holder, null);
            ViewGroupUtils.getOverlay(this.mParent).remove(this);
            this.mAttached = false;
        }
    }

    static GhostViewHolder getHolder(ViewGroup parent) {
        return (GhostViewHolder) parent.getTag(C1070R.C1073id.ghost_view_holder);
    }

    /* access modifiers changed from: 0000 */
    public void popToOverlayTop() {
        if (this.mAttached) {
            ViewGroupUtils.getOverlay(this.mParent).remove(this);
            ViewGroupUtils.getOverlay(this.mParent).add(this);
            return;
        }
        throw new IllegalStateException("This GhostViewHolder is detached!");
    }

    /* access modifiers changed from: 0000 */
    public void addGhostView(GhostViewPort ghostView) {
        ArrayList<View> viewParents = new ArrayList<>();
        getParents(ghostView.mView, viewParents);
        int index = getInsertIndex(viewParents);
        if (index < 0 || index >= getChildCount()) {
            addView(ghostView);
        } else {
            addView(ghostView, index);
        }
    }

    private int getInsertIndex(ArrayList<View> viewParents) {
        ArrayList<View> tempParents = new ArrayList<>();
        int low = 0;
        int high = getChildCount() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            getParents(((GhostViewPort) getChildAt(mid)).mView, tempParents);
            if (isOnTop(viewParents, tempParents)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            tempParents.clear();
        }
        return low;
    }

    private static boolean isOnTop(ArrayList<View> viewParents, ArrayList<View> comparedWith) {
        if (!viewParents.isEmpty() && !comparedWith.isEmpty()) {
            boolean z = false;
            if (viewParents.get(0) == comparedWith.get(0)) {
                int depth = Math.min(viewParents.size(), comparedWith.size());
                for (int i = 1; i < depth; i++) {
                    View viewParent = (View) viewParents.get(i);
                    View comparedWithParent = (View) comparedWith.get(i);
                    if (viewParent != comparedWithParent) {
                        return isOnTop(viewParent, comparedWithParent);
                    }
                }
                if (comparedWith.size() == depth) {
                    z = true;
                }
                return z;
            }
        }
        return true;
    }

    private static void getParents(View view, ArrayList<View> parents) {
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            getParents((View) parent, parents);
        }
        parents.add(view);
    }

    private static boolean isOnTop(View view, View comparedWith) {
        ViewGroup parent = (ViewGroup) view.getParent();
        int childrenCount = parent.getChildCount();
        if (VERSION.SDK_INT < 21 || view.getZ() == comparedWith.getZ()) {
            boolean isOnTop = true;
            int i = 0;
            while (true) {
                if (i >= childrenCount) {
                    break;
                }
                View child = parent.getChildAt(ViewGroupUtils.getChildDrawingOrder(parent, i));
                if (child == view) {
                    isOnTop = false;
                    break;
                } else if (child == comparedWith) {
                    isOnTop = true;
                    break;
                } else {
                    i++;
                }
            }
            return isOnTop;
        }
        return view.getZ() > comparedWith.getZ();
    }
}
