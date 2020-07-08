package com.timehop.stickyheadersrecyclerview.caching;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.timehop.stickyheadersrecyclerview.util.OrientationProvider;

public class HeaderViewCache implements HeaderProvider {
    private final StickyRecyclerHeadersAdapter mAdapter;
    private final LongSparseArray<View> mHeaderViews = new LongSparseArray<>();
    private final OrientationProvider mOrientationProvider;

    public HeaderViewCache(StickyRecyclerHeadersAdapter adapter, OrientationProvider orientationProvider) {
        this.mAdapter = adapter;
        this.mOrientationProvider = orientationProvider;
    }

    public View getHeader(RecyclerView parent, int position) {
        int heightSpec;
        int widthSpec;
        long headerId = this.mAdapter.getHeaderId(position);
        View header = (View) this.mHeaderViews.get(headerId);
        if (header == null) {
            ViewHolder viewHolder = this.mAdapter.onCreateHeaderViewHolder(parent);
            this.mAdapter.onBindHeaderViewHolder(viewHolder, position);
            header = viewHolder.itemView;
            if (header.getLayoutParams() == null) {
                header.setLayoutParams(new LayoutParams(-2, -2));
            }
            if (this.mOrientationProvider.getOrientation(parent) == 1) {
                widthSpec = MeasureSpec.makeMeasureSpec(parent.getWidth(), 1073741824);
                heightSpec = MeasureSpec.makeMeasureSpec(parent.getHeight(), 0);
            } else {
                widthSpec = MeasureSpec.makeMeasureSpec(parent.getWidth(), 0);
                heightSpec = MeasureSpec.makeMeasureSpec(parent.getHeight(), 1073741824);
            }
            header.measure(ViewGroup.getChildMeasureSpec(widthSpec, parent.getPaddingLeft() + parent.getPaddingRight(), header.getLayoutParams().width), ViewGroup.getChildMeasureSpec(heightSpec, parent.getPaddingTop() + parent.getPaddingBottom(), header.getLayoutParams().height));
            header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
            this.mHeaderViews.put(headerId, header);
        }
        return header;
    }

    public void invalidate() {
        this.mHeaderViews.clear();
    }
}
