package com.timehop.stickyheadersrecyclerview;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public interface StickyRecyclerHeadersAdapter<VH extends ViewHolder> {
    long getHeaderId(int i);

    int getItemCount();

    void onBindHeaderViewHolder(VH vh, int i);

    VH onCreateHeaderViewHolder(ViewGroup viewGroup);
}
