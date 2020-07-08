package com.github.barteksc.pdfviewer.listener;

import android.view.MotionEvent;
import com.github.barteksc.pdfviewer.link.LinkHandler;
import com.github.barteksc.pdfviewer.model.LinkTapEvent;

public class Callbacks {
    private LinkHandler linkHandler;
    private OnDrawListener onDrawAllListener;
    private OnDrawListener onDrawListener;
    private OnErrorListener onErrorListener;
    private OnLoadCompleteListener onLoadCompleteListener;
    private OnLongPressListener onLongPressListener;
    private OnPageChangeListener onPageChangeListener;
    private OnPageErrorListener onPageErrorListener;
    private OnPageScrollListener onPageScrollListener;
    private OnRenderListener onRenderListener;
    private OnTapListener onTapListener;

    public void setOnLoadComplete(OnLoadCompleteListener onLoadCompleteListener2) {
        this.onLoadCompleteListener = onLoadCompleteListener2;
    }

    public void callOnLoadComplete(int pagesCount) {
        OnLoadCompleteListener onLoadCompleteListener2 = this.onLoadCompleteListener;
        if (onLoadCompleteListener2 != null) {
            onLoadCompleteListener2.loadComplete(pagesCount);
        }
    }

    public void setOnError(OnErrorListener onErrorListener2) {
        this.onErrorListener = onErrorListener2;
    }

    public OnErrorListener getOnError() {
        return this.onErrorListener;
    }

    public void setOnPageError(OnPageErrorListener onPageErrorListener2) {
        this.onPageErrorListener = onPageErrorListener2;
    }

    public boolean callOnPageError(int page, Throwable error) {
        OnPageErrorListener onPageErrorListener2 = this.onPageErrorListener;
        if (onPageErrorListener2 == null) {
            return false;
        }
        onPageErrorListener2.onPageError(page, error);
        return true;
    }

    public void setOnRender(OnRenderListener onRenderListener2) {
        this.onRenderListener = onRenderListener2;
    }

    public void callOnRender(int pagesCount) {
        OnRenderListener onRenderListener2 = this.onRenderListener;
        if (onRenderListener2 != null) {
            onRenderListener2.onInitiallyRendered(pagesCount);
        }
    }

    public void setOnPageChange(OnPageChangeListener onPageChangeListener2) {
        this.onPageChangeListener = onPageChangeListener2;
    }

    public void callOnPageChange(int page, int pagesCount) {
        OnPageChangeListener onPageChangeListener2 = this.onPageChangeListener;
        if (onPageChangeListener2 != null) {
            onPageChangeListener2.onPageChanged(page, pagesCount);
        }
    }

    public void setOnPageScroll(OnPageScrollListener onPageScrollListener2) {
        this.onPageScrollListener = onPageScrollListener2;
    }

    public void callOnPageScroll(int currentPage, float offset) {
        OnPageScrollListener onPageScrollListener2 = this.onPageScrollListener;
        if (onPageScrollListener2 != null) {
            onPageScrollListener2.onPageScrolled(currentPage, offset);
        }
    }

    public void setOnDraw(OnDrawListener onDrawListener2) {
        this.onDrawListener = onDrawListener2;
    }

    public OnDrawListener getOnDraw() {
        return this.onDrawListener;
    }

    public void setOnDrawAll(OnDrawListener onDrawAllListener2) {
        this.onDrawAllListener = onDrawAllListener2;
    }

    public OnDrawListener getOnDrawAll() {
        return this.onDrawAllListener;
    }

    public void setOnTap(OnTapListener onTapListener2) {
        this.onTapListener = onTapListener2;
    }

    public boolean callOnTap(MotionEvent event) {
        OnTapListener onTapListener2 = this.onTapListener;
        return onTapListener2 != null && onTapListener2.onTap(event);
    }

    public void setOnLongPress(OnLongPressListener onLongPressListener2) {
        this.onLongPressListener = onLongPressListener2;
    }

    public void callOnLongPress(MotionEvent event) {
        OnLongPressListener onLongPressListener2 = this.onLongPressListener;
        if (onLongPressListener2 != null) {
            onLongPressListener2.onLongPress(event);
        }
    }

    public void setLinkHandler(LinkHandler linkHandler2) {
        this.linkHandler = linkHandler2;
    }

    public void callLinkHandler(LinkTapEvent event) {
        LinkHandler linkHandler2 = this.linkHandler;
        if (linkHandler2 != null) {
            linkHandler2.handleLinkEvent(event);
        }
    }
}
