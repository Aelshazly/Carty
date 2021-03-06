package androidx.viewpager2.widget;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;
import androidx.viewpager2.widget.ViewPager2.PageTransformer;
import java.util.Locale;

final class PageTransformerAdapter extends OnPageChangeCallback {
    private final LinearLayoutManager mLayoutManager;
    private PageTransformer mPageTransformer;

    PageTransformerAdapter(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    /* access modifiers changed from: 0000 */
    public PageTransformer getPageTransformer() {
        return this.mPageTransformer;
    }

    /* access modifiers changed from: 0000 */
    public void setPageTransformer(PageTransformer transformer) {
        this.mPageTransformer = transformer;
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (this.mPageTransformer != null) {
            float transformOffset = -positionOffset;
            int i = 0;
            while (i < this.mLayoutManager.getChildCount()) {
                View view = this.mLayoutManager.getChildAt(i);
                if (view != null) {
                    this.mPageTransformer.transformPage(view, ((float) (this.mLayoutManager.getPosition(view) - position)) + transformOffset);
                    i++;
                } else {
                    throw new IllegalStateException(String.format(Locale.US, "LayoutManager returned a null child at pos %d/%d while transforming pages", new Object[]{Integer.valueOf(i), Integer.valueOf(this.mLayoutManager.getChildCount())}));
                }
            }
        }
    }

    public void onPageSelected(int position) {
    }

    public void onPageScrollStateChanged(int state) {
    }
}
