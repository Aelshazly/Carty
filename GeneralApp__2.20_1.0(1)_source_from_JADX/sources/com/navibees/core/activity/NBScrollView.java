package com.navibees.core.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class NBScrollView extends ScrollView {

    /* renamed from: a */
    private OnScrollViewListener f1077a;

    public interface OnScrollViewListener {
        /* renamed from: a */
        void mo28849a(NBScrollView nBScrollView, int i, int i2, int i3, int i4);
    }

    public NBScrollView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        this.f1077a.mo28849a(this, i, i2, i3, i4);
        super.onScrollChanged(i, i2, i3, i4);
    }

    public void setOnScrollViewListener(OnScrollViewListener onScrollViewListener) {
        this.f1077a = onScrollViewListener;
    }

    public NBScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NBScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
