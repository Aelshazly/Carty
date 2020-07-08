package com.timehop.stickyheadersrecyclerview.util;

import androidx.recyclerview.widget.RecyclerView;

public interface OrientationProvider {
    int getOrientation(RecyclerView recyclerView);

    boolean isReverseLayout(RecyclerView recyclerView);
}
