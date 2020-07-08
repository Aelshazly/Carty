package androidx.recyclerview.widget;

import androidx.recyclerview.widget.AsyncDifferConfig.Builder;
import androidx.recyclerview.widget.AsyncListDiffer.ListListener;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.List;

public abstract class ListAdapter<T, VH extends ViewHolder> extends Adapter<VH> {
    final AsyncListDiffer<T> mDiffer;
    private final ListListener<T> mListener = new ListListener<T>() {
        public void onCurrentListChanged(List<T> previousList, List<T> currentList) {
            ListAdapter.this.onCurrentListChanged(previousList, currentList);
        }
    };

    protected ListAdapter(ItemCallback<T> diffCallback) {
        this.mDiffer = new AsyncListDiffer<>((ListUpdateCallback) new AdapterListUpdateCallback(this), new Builder(diffCallback).build());
        this.mDiffer.addListListener(this.mListener);
    }

    protected ListAdapter(AsyncDifferConfig<T> config) {
        this.mDiffer = new AsyncListDiffer<>((ListUpdateCallback) new AdapterListUpdateCallback(this), config);
        this.mDiffer.addListListener(this.mListener);
    }

    public void submitList(List<T> list) {
        this.mDiffer.submitList(list);
    }

    public void submitList(List<T> list, Runnable commitCallback) {
        this.mDiffer.submitList(list, commitCallback);
    }

    /* access modifiers changed from: protected */
    public T getItem(int position) {
        return this.mDiffer.getCurrentList().get(position);
    }

    public int getItemCount() {
        return this.mDiffer.getCurrentList().size();
    }

    public List<T> getCurrentList() {
        return this.mDiffer.getCurrentList();
    }

    public void onCurrentListChanged(List<T> list, List<T> list2) {
    }
}
