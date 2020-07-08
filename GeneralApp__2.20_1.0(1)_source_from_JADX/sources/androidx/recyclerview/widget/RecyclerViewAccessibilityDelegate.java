package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import java.util.Map;
import java.util.WeakHashMap;

public class RecyclerViewAccessibilityDelegate extends AccessibilityDelegateCompat {
    private final ItemDelegate mItemDelegate;
    final RecyclerView mRecyclerView;

    public static class ItemDelegate extends AccessibilityDelegateCompat {
        private Map<View, AccessibilityDelegateCompat> mOriginalItemDelegates = new WeakHashMap();
        final RecyclerViewAccessibilityDelegate mRecyclerViewDelegate;

        public ItemDelegate(RecyclerViewAccessibilityDelegate recyclerViewDelegate) {
            this.mRecyclerViewDelegate = recyclerViewDelegate;
        }

        /* access modifiers changed from: 0000 */
        public void saveOriginalDelegate(View itemView) {
            AccessibilityDelegateCompat delegate = ViewCompat.getAccessibilityDelegate(itemView);
            if (delegate != null && delegate != this) {
                this.mOriginalItemDelegates.put(itemView, delegate);
            }
        }

        /* access modifiers changed from: 0000 */
        public AccessibilityDelegateCompat getAndRemoveOriginalDelegateForItem(View itemView) {
            return (AccessibilityDelegateCompat) this.mOriginalItemDelegates.remove(itemView);
        }

        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
            if (this.mRecyclerViewDelegate.shouldIgnore() || this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager() == null) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                return;
            }
            this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfoForItem(host, info);
            AccessibilityDelegateCompat originalDelegate = (AccessibilityDelegateCompat) this.mOriginalItemDelegates.get(host);
            if (originalDelegate != null) {
                originalDelegate.onInitializeAccessibilityNodeInfo(host, info);
            } else {
                super.onInitializeAccessibilityNodeInfo(host, info);
            }
        }

        public boolean performAccessibilityAction(View host, int action, Bundle args) {
            if (this.mRecyclerViewDelegate.shouldIgnore() || this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager() == null) {
                return super.performAccessibilityAction(host, action, args);
            }
            AccessibilityDelegateCompat originalDelegate = (AccessibilityDelegateCompat) this.mOriginalItemDelegates.get(host);
            if (originalDelegate != null) {
                if (originalDelegate.performAccessibilityAction(host, action, args)) {
                    return true;
                }
            } else if (super.performAccessibilityAction(host, action, args)) {
                return true;
            }
            return this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager().performAccessibilityActionForItem(host, action, args);
        }

        public void sendAccessibilityEvent(View host, int eventType) {
            AccessibilityDelegateCompat originalDelegate = (AccessibilityDelegateCompat) this.mOriginalItemDelegates.get(host);
            if (originalDelegate != null) {
                originalDelegate.sendAccessibilityEvent(host, eventType);
            } else {
                super.sendAccessibilityEvent(host, eventType);
            }
        }

        public void sendAccessibilityEventUnchecked(View host, AccessibilityEvent event) {
            AccessibilityDelegateCompat originalDelegate = (AccessibilityDelegateCompat) this.mOriginalItemDelegates.get(host);
            if (originalDelegate != null) {
                originalDelegate.sendAccessibilityEventUnchecked(host, event);
            } else {
                super.sendAccessibilityEventUnchecked(host, event);
            }
        }

        public boolean dispatchPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
            AccessibilityDelegateCompat originalDelegate = (AccessibilityDelegateCompat) this.mOriginalItemDelegates.get(host);
            if (originalDelegate != null) {
                return originalDelegate.dispatchPopulateAccessibilityEvent(host, event);
            }
            return super.dispatchPopulateAccessibilityEvent(host, event);
        }

        public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
            AccessibilityDelegateCompat originalDelegate = (AccessibilityDelegateCompat) this.mOriginalItemDelegates.get(host);
            if (originalDelegate != null) {
                originalDelegate.onPopulateAccessibilityEvent(host, event);
            } else {
                super.onPopulateAccessibilityEvent(host, event);
            }
        }

        public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            AccessibilityDelegateCompat originalDelegate = (AccessibilityDelegateCompat) this.mOriginalItemDelegates.get(host);
            if (originalDelegate != null) {
                originalDelegate.onInitializeAccessibilityEvent(host, event);
            } else {
                super.onInitializeAccessibilityEvent(host, event);
            }
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
            AccessibilityDelegateCompat originalDelegate = (AccessibilityDelegateCompat) this.mOriginalItemDelegates.get(host);
            if (originalDelegate != null) {
                return originalDelegate.onRequestSendAccessibilityEvent(host, child, event);
            }
            return super.onRequestSendAccessibilityEvent(host, child, event);
        }

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View host) {
            AccessibilityDelegateCompat originalDelegate = (AccessibilityDelegateCompat) this.mOriginalItemDelegates.get(host);
            if (originalDelegate != null) {
                return originalDelegate.getAccessibilityNodeProvider(host);
            }
            return super.getAccessibilityNodeProvider(host);
        }
    }

    public RecyclerViewAccessibilityDelegate(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        AccessibilityDelegateCompat itemDelegate = getItemDelegate();
        if (itemDelegate == null || !(itemDelegate instanceof ItemDelegate)) {
            this.mItemDelegate = new ItemDelegate(this);
        } else {
            this.mItemDelegate = (ItemDelegate) itemDelegate;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean shouldIgnore() {
        return this.mRecyclerView.hasPendingAdapterUpdates();
    }

    public boolean performAccessibilityAction(View host, int action, Bundle args) {
        if (super.performAccessibilityAction(host, action, args)) {
            return true;
        }
        if (shouldIgnore() || this.mRecyclerView.getLayoutManager() == null) {
            return false;
        }
        return this.mRecyclerView.getLayoutManager().performAccessibilityAction(action, args);
    }

    public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
        super.onInitializeAccessibilityNodeInfo(host, info);
        if (!shouldIgnore() && this.mRecyclerView.getLayoutManager() != null) {
            this.mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfo(info);
        }
    }

    public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(host, event);
        if ((host instanceof RecyclerView) && !shouldIgnore()) {
            RecyclerView rv = (RecyclerView) host;
            if (rv.getLayoutManager() != null) {
                rv.getLayoutManager().onInitializeAccessibilityEvent(event);
            }
        }
    }

    public AccessibilityDelegateCompat getItemDelegate() {
        return this.mItemDelegate;
    }
}