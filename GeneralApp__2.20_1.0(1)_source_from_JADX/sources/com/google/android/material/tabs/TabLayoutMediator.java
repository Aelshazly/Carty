package com.google.android.material.tabs;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import com.google.android.material.tabs.TabLayout.Tab;
import java.lang.ref.WeakReference;

public final class TabLayoutMediator {
    private Adapter<?> adapter;
    private boolean attached;
    private final boolean autoRefresh;
    private TabLayoutOnPageChangeCallback onPageChangeCallback;
    private OnTabSelectedListener onTabSelectedListener;
    private AdapterDataObserver pagerAdapterObserver;
    private final TabConfigurationStrategy tabConfigurationStrategy;
    private final TabLayout tabLayout;
    private final ViewPager2 viewPager;

    private class PagerAdapterObserver extends AdapterDataObserver {
        PagerAdapterObserver() {
        }

        public void onChanged() {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }
    }

    public interface TabConfigurationStrategy {
        void onConfigureTab(Tab tab, int i);
    }

    private static class TabLayoutOnPageChangeCallback extends OnPageChangeCallback {
        private int previousScrollState;
        private int scrollState;
        private final WeakReference<TabLayout> tabLayoutRef;

        TabLayoutOnPageChangeCallback(TabLayout tabLayout) {
            this.tabLayoutRef = new WeakReference<>(tabLayout);
            reset();
        }

        public void onPageScrollStateChanged(int state) {
            this.previousScrollState = this.scrollState;
            this.scrollState = state;
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            TabLayout tabLayout = (TabLayout) this.tabLayoutRef.get();
            if (tabLayout != null) {
                boolean updateIndicator = false;
                boolean updateText = this.scrollState != 2 || this.previousScrollState == 1;
                if (!(this.scrollState == 2 && this.previousScrollState == 0)) {
                    updateIndicator = true;
                }
                tabLayout.setScrollPosition(position, positionOffset, updateText, updateIndicator);
            }
        }

        public void onPageSelected(int position) {
            TabLayout tabLayout = (TabLayout) this.tabLayoutRef.get();
            if (tabLayout != null && tabLayout.getSelectedTabPosition() != position && position < tabLayout.getTabCount()) {
                int i = this.scrollState;
                tabLayout.selectTab(tabLayout.getTabAt(position), i == 0 || (i == 2 && this.previousScrollState == 0));
            }
        }

        /* access modifiers changed from: 0000 */
        public void reset() {
            this.scrollState = 0;
            this.previousScrollState = 0;
        }
    }

    private static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
        private final ViewPager2 viewPager;

        ViewPagerOnTabSelectedListener(ViewPager2 viewPager2) {
            this.viewPager = viewPager2;
        }

        public void onTabSelected(Tab tab) {
            this.viewPager.setCurrentItem(tab.getPosition(), true);
        }

        public void onTabUnselected(Tab tab) {
        }

        public void onTabReselected(Tab tab) {
        }
    }

    public TabLayoutMediator(TabLayout tabLayout2, ViewPager2 viewPager2, TabConfigurationStrategy tabConfigurationStrategy2) {
        this(tabLayout2, viewPager2, true, tabConfigurationStrategy2);
    }

    public TabLayoutMediator(TabLayout tabLayout2, ViewPager2 viewPager2, boolean autoRefresh2, TabConfigurationStrategy tabConfigurationStrategy2) {
        this.tabLayout = tabLayout2;
        this.viewPager = viewPager2;
        this.autoRefresh = autoRefresh2;
        this.tabConfigurationStrategy = tabConfigurationStrategy2;
    }

    public void attach() {
        if (!this.attached) {
            this.adapter = this.viewPager.getAdapter();
            if (this.adapter != null) {
                this.attached = true;
                this.onPageChangeCallback = new TabLayoutOnPageChangeCallback(this.tabLayout);
                this.viewPager.registerOnPageChangeCallback(this.onPageChangeCallback);
                this.onTabSelectedListener = new ViewPagerOnTabSelectedListener(this.viewPager);
                this.tabLayout.addOnTabSelectedListener(this.onTabSelectedListener);
                if (this.autoRefresh) {
                    this.pagerAdapterObserver = new PagerAdapterObserver();
                    this.adapter.registerAdapterDataObserver(this.pagerAdapterObserver);
                }
                populateTabsFromPagerAdapter();
                this.tabLayout.setScrollPosition(this.viewPager.getCurrentItem(), 0.0f, true);
                return;
            }
            throw new IllegalStateException("TabLayoutMediator attached before ViewPager2 has an adapter");
        }
        throw new IllegalStateException("TabLayoutMediator is already attached");
    }

    public void detach() {
        if (this.autoRefresh) {
            Adapter<?> adapter2 = this.adapter;
            if (adapter2 != null) {
                adapter2.unregisterAdapterDataObserver(this.pagerAdapterObserver);
                this.pagerAdapterObserver = null;
            }
        }
        this.tabLayout.removeOnTabSelectedListener(this.onTabSelectedListener);
        this.viewPager.unregisterOnPageChangeCallback(this.onPageChangeCallback);
        this.onTabSelectedListener = null;
        this.onPageChangeCallback = null;
        this.adapter = null;
        this.attached = false;
    }

    /* access modifiers changed from: 0000 */
    public void populateTabsFromPagerAdapter() {
        this.tabLayout.removeAllTabs();
        Adapter<?> adapter2 = this.adapter;
        if (adapter2 != null) {
            int adapterCount = adapter2.getItemCount();
            for (int i = 0; i < adapterCount; i++) {
                Tab tab = this.tabLayout.newTab();
                this.tabConfigurationStrategy.onConfigureTab(tab, i);
                this.tabLayout.addTab(tab, false);
            }
            if (adapterCount > 0) {
                int currItem = Math.min(this.viewPager.getCurrentItem(), this.tabLayout.getTabCount() - 1);
                if (currItem != this.tabLayout.getSelectedTabPosition()) {
                    TabLayout tabLayout2 = this.tabLayout;
                    tabLayout2.selectTab(tabLayout2.getTabAt(currItem));
                }
            }
        }
    }
}
