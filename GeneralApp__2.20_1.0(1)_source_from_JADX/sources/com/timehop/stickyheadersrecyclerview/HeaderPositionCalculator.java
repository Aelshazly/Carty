package com.timehop.stickyheadersrecyclerview;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;
import com.timehop.stickyheadersrecyclerview.caching.HeaderProvider;
import com.timehop.stickyheadersrecyclerview.calculation.DimensionCalculator;
import com.timehop.stickyheadersrecyclerview.util.OrientationProvider;

public class HeaderPositionCalculator {
    private final StickyRecyclerHeadersAdapter mAdapter;
    private final DimensionCalculator mDimensionCalculator;
    private final HeaderProvider mHeaderProvider;
    private final OrientationProvider mOrientationProvider;

    public HeaderPositionCalculator(StickyRecyclerHeadersAdapter adapter, HeaderProvider headerProvider, OrientationProvider orientationProvider, DimensionCalculator dimensionCalculator) {
        this.mAdapter = adapter;
        this.mHeaderProvider = headerProvider;
        this.mOrientationProvider = orientationProvider;
        this.mDimensionCalculator = dimensionCalculator;
    }

    public boolean hasStickyHeader(View itemView, int orientation, int position) {
        int margin;
        int offset;
        if (orientation == 1) {
            offset = itemView.getTop();
            margin = this.mDimensionCalculator.getMargins(itemView).top;
        } else {
            offset = itemView.getLeft();
            margin = this.mDimensionCalculator.getMargins(itemView).left;
        }
        if (offset > margin || this.mAdapter.getHeaderId(position) < 0) {
            return false;
        }
        return true;
    }

    public boolean hasNewHeader(int position, boolean isReverseLayout) {
        if (indexOutOfBounds(position)) {
            return false;
        }
        long headerId = this.mAdapter.getHeaderId(position);
        if (headerId < 0) {
            return false;
        }
        long nextItemHeaderId = -1;
        boolean z = true;
        int nextItemPosition = (isReverseLayout ? 1 : -1) + position;
        if (!indexOutOfBounds(nextItemPosition)) {
            nextItemHeaderId = this.mAdapter.getHeaderId(nextItemPosition);
        }
        if (position != (isReverseLayout ? this.mAdapter.getItemCount() - 1 : 0) && headerId == nextItemHeaderId) {
            z = false;
        }
        return z;
    }

    private boolean indexOutOfBounds(int position) {
        return position < 0 || position >= this.mAdapter.getItemCount();
    }

    public Rect getHeaderBounds(RecyclerView recyclerView, View header, View firstView, boolean firstHeader) {
        RecyclerView recyclerView2 = recyclerView;
        Rect bounds = getDefaultHeaderOffset(recyclerView2, header, firstView, this.mOrientationProvider.getOrientation(recyclerView2));
        if (firstHeader && isStickyHeaderBeingPushedOffscreen(recyclerView, header)) {
            View viewAfterNextHeader = getFirstViewUnobscuredByHeader(recyclerView, header);
            View secondHeader = this.mHeaderProvider.getHeader(recyclerView2, recyclerView2.getChildAdapterPosition(viewAfterNextHeader));
            translateHeaderWithNextHeader(recyclerView, this.mOrientationProvider.getOrientation(recyclerView2), bounds, header, viewAfterNextHeader, secondHeader);
        }
        return bounds;
    }

    private Rect getDefaultHeaderOffset(RecyclerView recyclerView, View header, View firstView, int orientation) {
        int translationY;
        int translationX;
        Rect headerMargins = this.mDimensionCalculator.getMargins(header);
        if (orientation == 1) {
            translationX = firstView.getLeft() + headerMargins.left;
            translationY = Math.max((firstView.getTop() - header.getHeight()) - headerMargins.bottom, getListTop(recyclerView) + headerMargins.top);
        } else {
            translationY = headerMargins.top + firstView.getTop();
            translationX = Math.max((firstView.getLeft() - header.getWidth()) - headerMargins.right, getListLeft(recyclerView) + headerMargins.left);
        }
        return new Rect(translationX, translationY, header.getWidth() + translationX, header.getHeight() + translationY);
    }

    private boolean isStickyHeaderBeingPushedOffscreen(RecyclerView recyclerView, View stickyHeader) {
        View viewAfterHeader = getFirstViewUnobscuredByHeader(recyclerView, stickyHeader);
        int firstViewUnderHeaderPosition = recyclerView.getChildAdapterPosition(viewAfterHeader);
        if (firstViewUnderHeaderPosition == -1) {
            return false;
        }
        boolean isReverseLayout = this.mOrientationProvider.isReverseLayout(recyclerView);
        if (firstViewUnderHeaderPosition > 0 && hasNewHeader(firstViewUnderHeaderPosition, isReverseLayout)) {
            View nextHeader = this.mHeaderProvider.getHeader(recyclerView, firstViewUnderHeaderPosition);
            Rect nextHeaderMargins = this.mDimensionCalculator.getMargins(nextHeader);
            Rect headerMargins = this.mDimensionCalculator.getMargins(stickyHeader);
            if (this.mOrientationProvider.getOrientation(recyclerView) == 1) {
                if (((viewAfterHeader.getTop() - nextHeaderMargins.bottom) - nextHeader.getHeight()) - nextHeaderMargins.top < recyclerView.getPaddingTop() + stickyHeader.getBottom() + headerMargins.top + headerMargins.bottom) {
                    return true;
                }
            } else if (((viewAfterHeader.getLeft() - nextHeaderMargins.right) - nextHeader.getWidth()) - nextHeaderMargins.left < recyclerView.getPaddingLeft() + stickyHeader.getRight() + headerMargins.left + headerMargins.right) {
                return true;
            }
        }
        return false;
    }

    private void translateHeaderWithNextHeader(RecyclerView recyclerView, int orientation, Rect translation, View currentHeader, View viewAfterNextHeader, View nextHeader) {
        Rect nextHeaderMargins = this.mDimensionCalculator.getMargins(nextHeader);
        Rect stickyHeaderMargins = this.mDimensionCalculator.getMargins(currentHeader);
        if (orientation == 1) {
            int topOfStickyHeader = getListTop(recyclerView) + stickyHeaderMargins.top + stickyHeaderMargins.bottom;
            int shiftFromNextHeader = ((((viewAfterNextHeader.getTop() - nextHeader.getHeight()) - nextHeaderMargins.bottom) - nextHeaderMargins.top) - currentHeader.getHeight()) - topOfStickyHeader;
            if (shiftFromNextHeader < topOfStickyHeader) {
                translation.top += shiftFromNextHeader;
                return;
            }
            return;
        }
        int leftOfStickyHeader = getListLeft(recyclerView) + stickyHeaderMargins.left + stickyHeaderMargins.right;
        int shiftFromNextHeader2 = ((((viewAfterNextHeader.getLeft() - nextHeader.getWidth()) - nextHeaderMargins.right) - nextHeaderMargins.left) - currentHeader.getWidth()) - leftOfStickyHeader;
        if (shiftFromNextHeader2 < leftOfStickyHeader) {
            translation.left += shiftFromNextHeader2;
        }
    }

    private View getFirstViewUnobscuredByHeader(RecyclerView parent, View firstHeader) {
        boolean isReverseLayout = this.mOrientationProvider.isReverseLayout(parent);
        int step = isReverseLayout ? -1 : 1;
        int i = isReverseLayout ? parent.getChildCount() - 1 : 0;
        while (i >= 0 && i <= parent.getChildCount() - 1) {
            View child = parent.getChildAt(i);
            if (!itemIsObscuredByHeader(parent, child, firstHeader, this.mOrientationProvider.getOrientation(parent))) {
                return child;
            }
            i += step;
        }
        return null;
    }

    private boolean itemIsObscuredByHeader(RecyclerView parent, View item, View header, int orientation) {
        LayoutParams layoutParams = (LayoutParams) item.getLayoutParams();
        Rect headerMargins = this.mDimensionCalculator.getMargins(header);
        int adapterPosition = parent.getChildAdapterPosition(item);
        if (adapterPosition == -1 || this.mHeaderProvider.getHeader(parent, adapterPosition) != header) {
            return false;
        }
        if (orientation == 1) {
            if (item.getTop() - layoutParams.topMargin > header.getBottom() + headerMargins.bottom + headerMargins.top) {
                return false;
            }
        } else if (item.getLeft() - layoutParams.leftMargin > header.getRight() + headerMargins.right + headerMargins.left) {
            return false;
        }
        return true;
    }

    private int getListTop(RecyclerView view) {
        if (view.getLayoutManager().getClipToPadding()) {
            return view.getPaddingTop();
        }
        return 0;
    }

    private int getListLeft(RecyclerView view) {
        if (view.getLayoutManager().getClipToPadding()) {
            return view.getPaddingLeft();
        }
        return 0;
    }
}
