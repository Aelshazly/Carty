package com.timehop.stickyheadersrecyclerview.calculation;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

public class DimensionCalculator {
    public Rect getMargins(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof MarginLayoutParams) {
            return getMarginRect((MarginLayoutParams) layoutParams);
        }
        return new Rect();
    }

    private Rect getMarginRect(MarginLayoutParams marginLayoutParams) {
        return new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
    }
}
