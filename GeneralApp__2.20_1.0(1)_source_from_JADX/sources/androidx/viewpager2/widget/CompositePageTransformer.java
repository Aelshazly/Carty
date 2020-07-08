package androidx.viewpager2.widget;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2.PageTransformer;
import java.util.ArrayList;
import java.util.List;

public final class CompositePageTransformer implements PageTransformer {
    private final List<PageTransformer> mTransformers = new ArrayList();

    public void addTransformer(PageTransformer transformer) {
        this.mTransformers.add(transformer);
    }

    public void removeTransformer(PageTransformer transformer) {
        this.mTransformers.remove(transformer);
    }

    public void transformPage(View page, float position) {
        for (PageTransformer transformer : this.mTransformers) {
            transformer.transformPage(page, position);
        }
    }
}
