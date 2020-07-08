package androidx.viewpager2.adapter;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public final class FragmentViewHolder extends ViewHolder {
    private FragmentViewHolder(FrameLayout container) {
        super(container);
    }

    static FragmentViewHolder create(ViewGroup parent) {
        FrameLayout container = new FrameLayout(parent.getContext());
        container.setLayoutParams(new LayoutParams(-1, -1));
        container.setId(ViewCompat.generateViewId());
        container.setSaveEnabled(false);
        return new FragmentViewHolder(container);
    }

    /* access modifiers changed from: 0000 */
    public FrameLayout getContainer() {
        return (FrameLayout) this.itemView;
    }
}
