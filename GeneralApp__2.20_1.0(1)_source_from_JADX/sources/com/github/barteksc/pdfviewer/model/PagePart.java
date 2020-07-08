package com.github.barteksc.pdfviewer.model;

import android.graphics.Bitmap;
import android.graphics.RectF;

public class PagePart {
    private int cacheOrder;
    private int page;
    private RectF pageRelativeBounds;
    private Bitmap renderedBitmap;
    private boolean thumbnail;

    public PagePart(int page2, Bitmap renderedBitmap2, RectF pageRelativeBounds2, boolean thumbnail2, int cacheOrder2) {
        this.page = page2;
        this.renderedBitmap = renderedBitmap2;
        this.pageRelativeBounds = pageRelativeBounds2;
        this.thumbnail = thumbnail2;
        this.cacheOrder = cacheOrder2;
    }

    public int getCacheOrder() {
        return this.cacheOrder;
    }

    public int getPage() {
        return this.page;
    }

    public Bitmap getRenderedBitmap() {
        return this.renderedBitmap;
    }

    public RectF getPageRelativeBounds() {
        return this.pageRelativeBounds;
    }

    public boolean isThumbnail() {
        return this.thumbnail;
    }

    public void setCacheOrder(int cacheOrder2) {
        this.cacheOrder = cacheOrder2;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof PagePart)) {
            return false;
        }
        PagePart part = (PagePart) obj;
        if (part.getPage() == this.page && part.getPageRelativeBounds().left == this.pageRelativeBounds.left && part.getPageRelativeBounds().right == this.pageRelativeBounds.right && part.getPageRelativeBounds().top == this.pageRelativeBounds.top && part.getPageRelativeBounds().bottom == this.pageRelativeBounds.bottom) {
            z = true;
        }
        return z;
    }
}
