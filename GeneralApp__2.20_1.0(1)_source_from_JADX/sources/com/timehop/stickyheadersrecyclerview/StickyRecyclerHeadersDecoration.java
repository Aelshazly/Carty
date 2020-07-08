package com.timehop.stickyheadersrecyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.State;
import com.timehop.stickyheadersrecyclerview.caching.HeaderProvider;
import com.timehop.stickyheadersrecyclerview.caching.HeaderViewCache;
import com.timehop.stickyheadersrecyclerview.calculation.DimensionCalculator;
import com.timehop.stickyheadersrecyclerview.rendering.HeaderRenderer;
import com.timehop.stickyheadersrecyclerview.util.LinearLayoutOrientationProvider;
import com.timehop.stickyheadersrecyclerview.util.OrientationProvider;

public class StickyRecyclerHeadersDecoration extends ItemDecoration {
    private final StickyRecyclerHeadersAdapter mAdapter;
    private final DimensionCalculator mDimensionCalculator;
    private final HeaderPositionCalculator mHeaderPositionCalculator;
    private final HeaderProvider mHeaderProvider;
    private final SparseArray<Rect> mHeaderRects;
    private final OrientationProvider mOrientationProvider;
    private final HeaderRenderer mRenderer;

    public StickyRecyclerHeadersDecoration(StickyRecyclerHeadersAdapter adapter) {
        this(adapter, new LinearLayoutOrientationProvider(), new DimensionCalculator());
    }

    private StickyRecyclerHeadersDecoration(StickyRecyclerHeadersAdapter adapter, OrientationProvider orientationProvider, DimensionCalculator dimensionCalculator) {
        this(adapter, orientationProvider, dimensionCalculator, new HeaderRenderer(orientationProvider), new HeaderViewCache(adapter, orientationProvider));
    }

    private StickyRecyclerHeadersDecoration(StickyRecyclerHeadersAdapter adapter, OrientationProvider orientationProvider, DimensionCalculator dimensionCalculator, HeaderRenderer headerRenderer, HeaderProvider headerProvider) {
        this(adapter, headerRenderer, orientationProvider, dimensionCalculator, headerProvider, new HeaderPositionCalculator(adapter, headerProvider, orientationProvider, dimensionCalculator));
    }

    private StickyRecyclerHeadersDecoration(StickyRecyclerHeadersAdapter adapter, HeaderRenderer headerRenderer, OrientationProvider orientationProvider, DimensionCalculator dimensionCalculator, HeaderProvider headerProvider, HeaderPositionCalculator headerPositionCalculator) {
        this.mHeaderRects = new SparseArray<>();
        this.mAdapter = adapter;
        this.mHeaderProvider = headerProvider;
        this.mOrientationProvider = orientationProvider;
        this.mRenderer = headerRenderer;
        this.mDimensionCalculator = dimensionCalculator;
        this.mHeaderPositionCalculator = headerPositionCalculator;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int itemPosition = parent.getChildAdapterPosition(view);
        if (itemPosition != -1 && this.mHeaderPositionCalculator.hasNewHeader(itemPosition, this.mOrientationProvider.isReverseLayout(parent))) {
            setItemOffsetsForHeader(outRect, getHeaderView(parent, itemPosition), this.mOrientationProvider.getOrientation(parent));
        }
    }

    private void setItemOffsetsForHeader(Rect itemOffsets, View header, int orientation) {
        Rect headerMargins = this.mDimensionCalculator.getMargins(header);
        if (orientation == 1) {
            itemOffsets.top = header.getHeight() + headerMargins.top + headerMargins.bottom;
        } else {
            itemOffsets.left = header.getWidth() + headerMargins.left + headerMargins.right;
        }
    }

    public void onDrawOver(Canvas canvas, RecyclerView parent, State state) {
        super.onDrawOver(canvas, parent, state);
        this.mHeaderRects.clear();
        if (parent.getChildCount() > 0 && this.mAdapter.getItemCount() > 0) {
            for (int i = 0; i < parent.getChildCount(); i++) {
                View itemView = parent.getChildAt(i);
                int position = parent.getChildAdapterPosition(itemView);
                if (position != -1) {
                    boolean hasStickyHeader = this.mHeaderPositionCalculator.hasStickyHeader(itemView, this.mOrientationProvider.getOrientation(parent), position);
                    if (hasStickyHeader || this.mHeaderPositionCalculator.hasNewHeader(position, this.mOrientationProvider.isReverseLayout(parent))) {
                        View header = this.mHeaderProvider.getHeader(parent, position);
                        Rect headerOffset = this.mHeaderPositionCalculator.getHeaderBounds(parent, header, itemView, hasStickyHeader);
                        this.mRenderer.drawHeader(parent, canvas, header, headerOffset);
                        this.mHeaderRects.put(position, headerOffset);
                    }
                }
            }
        }
    }

    public int findHeaderPositionUnder(int x, int y) {
        for (int i = 0; i < this.mHeaderRects.size(); i++) {
            SparseArray<Rect> sparseArray = this.mHeaderRects;
            if (((Rect) sparseArray.get(sparseArray.keyAt(i))).contains(x, y)) {
                return this.mHeaderRects.keyAt(i);
            }
        }
        return -1;
    }

    public View getHeaderView(RecyclerView parent, int position) {
        return this.mHeaderProvider.getHeader(parent, position);
    }

    public void invalidateHeaders() {
        this.mHeaderProvider.invalidate();
    }
}
