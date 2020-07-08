package com.timehop.stickyheadersrecyclerview;

import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener;

public class StickyRecyclerHeadersTouchListener implements OnItemTouchListener {
    /* access modifiers changed from: private */
    public final StickyRecyclerHeadersDecoration mDecor;
    /* access modifiers changed from: private */
    public OnHeaderClickListener mOnHeaderClickListener;
    /* access modifiers changed from: private */
    public final RecyclerView mRecyclerView;
    private final GestureDetector mTapDetector;

    public interface OnHeaderClickListener {
        void onHeaderClick(View view, int i, long j);
    }

    private class SingleTapDetector extends SimpleOnGestureListener {
        private SingleTapDetector() {
        }

        public boolean onSingleTapUp(MotionEvent e) {
            int position = StickyRecyclerHeadersTouchListener.this.mDecor.findHeaderPositionUnder((int) e.getX(), (int) e.getY());
            if (position == -1) {
                return false;
            }
            View headerView = StickyRecyclerHeadersTouchListener.this.mDecor.getHeaderView(StickyRecyclerHeadersTouchListener.this.mRecyclerView, position);
            StickyRecyclerHeadersTouchListener.this.mOnHeaderClickListener.onHeaderClick(headerView, position, StickyRecyclerHeadersTouchListener.this.getAdapter().getHeaderId(position));
            StickyRecyclerHeadersTouchListener.this.mRecyclerView.playSoundEffect(0);
            headerView.onTouchEvent(e);
            return true;
        }

        public boolean onDoubleTap(MotionEvent e) {
            return true;
        }
    }

    public StickyRecyclerHeadersTouchListener(RecyclerView recyclerView, StickyRecyclerHeadersDecoration decor) {
        this.mTapDetector = new GestureDetector(recyclerView.getContext(), new SingleTapDetector());
        this.mRecyclerView = recyclerView;
        this.mDecor = decor;
    }

    public StickyRecyclerHeadersAdapter getAdapter() {
        if (this.mRecyclerView.getAdapter() instanceof StickyRecyclerHeadersAdapter) {
            return (StickyRecyclerHeadersAdapter) this.mRecyclerView.getAdapter();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("A RecyclerView with ");
        sb.append(StickyRecyclerHeadersTouchListener.class.getSimpleName());
        sb.append(" requires a ");
        sb.append(StickyRecyclerHeadersAdapter.class.getSimpleName());
        throw new IllegalStateException(sb.toString());
    }

    public void setOnHeaderClickListener(OnHeaderClickListener listener) {
        this.mOnHeaderClickListener = listener;
    }

    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        return this.mOnHeaderClickListener != null && this.mTapDetector.onTouchEvent(e);
    }

    public void onTouchEvent(RecyclerView view, MotionEvent e) {
    }

    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}
