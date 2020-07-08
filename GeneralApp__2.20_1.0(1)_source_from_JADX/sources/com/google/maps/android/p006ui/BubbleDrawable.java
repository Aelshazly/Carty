package com.google.maps.android.p006ui;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.google.maps.android.C1160R;

/* renamed from: com.google.maps.android.ui.BubbleDrawable */
class BubbleDrawable extends Drawable {
    private int mColor = -1;
    private final Drawable mMask;
    private final Drawable mShadow;

    public BubbleDrawable(Resources res) {
        this.mMask = res.getDrawable(C1160R.C1162drawable.amu_bubble_mask);
        this.mShadow = res.getDrawable(C1160R.C1162drawable.amu_bubble_shadow);
    }

    public void setColor(int color) {
        this.mColor = color;
    }

    public void draw(Canvas canvas) {
        this.mMask.draw(canvas);
        canvas.drawColor(this.mColor, Mode.SRC_IN);
        this.mShadow.draw(canvas);
    }

    public void setAlpha(int alpha) {
        throw new UnsupportedOperationException();
    }

    public void setColorFilter(ColorFilter cf) {
        throw new UnsupportedOperationException();
    }

    public int getOpacity() {
        return -3;
    }

    public void setBounds(int left, int top, int right, int bottom) {
        this.mMask.setBounds(left, top, right, bottom);
        this.mShadow.setBounds(left, top, right, bottom);
    }

    public void setBounds(Rect bounds) {
        this.mMask.setBounds(bounds);
        this.mShadow.setBounds(bounds);
    }

    public boolean getPadding(Rect padding) {
        return this.mMask.getPadding(padding);
    }
}
