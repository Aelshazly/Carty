package com.timehop.stickyheadersrecyclerview.rendering;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.timehop.stickyheadersrecyclerview.calculation.DimensionCalculator;
import com.timehop.stickyheadersrecyclerview.util.OrientationProvider;

public class HeaderRenderer {
    private final DimensionCalculator mDimensionCalculator;
    private final OrientationProvider mOrientationProvider;

    public HeaderRenderer(OrientationProvider orientationProvider) {
        this(orientationProvider, new DimensionCalculator());
    }

    private HeaderRenderer(OrientationProvider orientationProvider, DimensionCalculator dimensionCalculator) {
        this.mOrientationProvider = orientationProvider;
        this.mDimensionCalculator = dimensionCalculator;
    }

    public void drawHeader(RecyclerView recyclerView, Canvas canvas, View header, Rect offset) {
        canvas.save();
        if (recyclerView.getLayoutManager().getClipToPadding()) {
            canvas.clipRect(getClipRectForHeader(recyclerView, header));
        }
        canvas.translate((float) offset.left, (float) offset.top);
        header.draw(canvas);
        canvas.restore();
    }

    private Rect getClipRectForHeader(RecyclerView recyclerView, View header) {
        Rect headerMargins = this.mDimensionCalculator.getMargins(header);
        if (this.mOrientationProvider.getOrientation(recyclerView) == 1) {
            return new Rect(recyclerView.getPaddingLeft(), recyclerView.getPaddingTop(), (recyclerView.getWidth() - recyclerView.getPaddingRight()) - headerMargins.right, recyclerView.getHeight() - recyclerView.getPaddingBottom());
        }
        return new Rect(recyclerView.getPaddingLeft(), recyclerView.getPaddingTop(), recyclerView.getWidth() - recyclerView.getPaddingRight(), (recyclerView.getHeight() - recyclerView.getPaddingBottom()) - headerMargins.bottom);
    }
}
