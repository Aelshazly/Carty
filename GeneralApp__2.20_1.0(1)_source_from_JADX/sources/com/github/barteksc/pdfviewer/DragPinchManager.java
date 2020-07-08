package com.github.barteksc.pdfviewer;

import android.graphics.PointF;
import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.View.OnTouchListener;
import com.github.barteksc.pdfviewer.listener.Callbacks;
import com.github.barteksc.pdfviewer.model.LinkTapEvent;
import com.github.barteksc.pdfviewer.scroll.ScrollHandle;
import com.github.barteksc.pdfviewer.util.Constants.Pinch;
import com.shockwave.pdfium.PdfDocument.Link;
import com.shockwave.pdfium.util.SizeF;

class DragPinchManager implements OnGestureListener, OnDoubleTapListener, OnScaleGestureListener, OnTouchListener {
    private AnimationManager animationManager;
    private boolean enabled = false;
    private GestureDetector gestureDetector;
    private PDFView pdfView;
    private ScaleGestureDetector scaleGestureDetector;
    private boolean scaling = false;
    private boolean scrolling = false;

    DragPinchManager(PDFView pdfView2, AnimationManager animationManager2) {
        this.pdfView = pdfView2;
        this.animationManager = animationManager2;
        this.gestureDetector = new GestureDetector(pdfView2.getContext(), this);
        this.scaleGestureDetector = new ScaleGestureDetector(pdfView2.getContext(), this);
        pdfView2.setOnTouchListener(this);
    }

    /* access modifiers changed from: 0000 */
    public void enable() {
        this.enabled = true;
    }

    /* access modifiers changed from: 0000 */
    public void disable() {
        this.enabled = false;
    }

    public boolean onSingleTapConfirmed(MotionEvent e) {
        boolean onTapHandled = this.pdfView.callbacks.callOnTap(e);
        boolean linkTapped = checkLinkTapped(e.getX(), e.getY());
        if (!onTapHandled && !linkTapped) {
            ScrollHandle ps = this.pdfView.getScrollHandle();
            if (ps != null && !this.pdfView.documentFitsView()) {
                if (!ps.shown()) {
                    ps.show();
                } else {
                    ps.hide();
                }
            }
        }
        this.pdfView.performClick();
        return true;
    }

    private boolean checkLinkTapped(float x, float y) {
        int pageY;
        int pageX;
        DragPinchManager dragPinchManager = this;
        PdfFile pdfFile = dragPinchManager.pdfView.pdfFile;
        float mappedX = (-dragPinchManager.pdfView.getCurrentXOffset()) + x;
        float mappedY = (-dragPinchManager.pdfView.getCurrentYOffset()) + y;
        int page = pdfFile.getPageAtOffset(dragPinchManager.pdfView.isSwipeVertical() ? mappedY : mappedX, dragPinchManager.pdfView.getZoom());
        SizeF pageSize = pdfFile.getScaledPageSize(page, dragPinchManager.pdfView.getZoom());
        if (dragPinchManager.pdfView.isSwipeVertical()) {
            pageX = (int) pdfFile.getSecondaryPageOffset(page, dragPinchManager.pdfView.getZoom());
            pageY = (int) pdfFile.getPageOffset(page, dragPinchManager.pdfView.getZoom());
        } else {
            pageY = (int) pdfFile.getSecondaryPageOffset(page, dragPinchManager.pdfView.getZoom());
            pageX = (int) pdfFile.getPageOffset(page, dragPinchManager.pdfView.getZoom());
        }
        for (Link link : pdfFile.getPageLinks(page)) {
            RectF mapped = pdfFile.mapRectToDevice(page, pageX, pageY, (int) pageSize.getWidth(), (int) pageSize.getHeight(), link.getBounds());
            mapped.sort();
            if (mapped.contains(mappedX, mappedY)) {
                LinkTapEvent linkTapEvent = r3;
                PdfFile pdfFile2 = pdfFile;
                Callbacks callbacks = dragPinchManager.pdfView.callbacks;
                LinkTapEvent linkTapEvent2 = new LinkTapEvent(x, y, mappedX, mappedY, mapped, link);
                callbacks.callLinkHandler(linkTapEvent);
                return true;
            }
            dragPinchManager = this;
        }
        return false;
    }

    private void startPageFling(MotionEvent downEvent, MotionEvent ev, float velocityX, float velocityY) {
        int direction;
        float f;
        float f2;
        if (checkDoPageFling(velocityX, velocityY)) {
            int i = -1;
            if (this.pdfView.isSwipeVertical()) {
                if (velocityY <= 0.0f) {
                    i = 1;
                }
                direction = i;
            } else {
                if (velocityX <= 0.0f) {
                    i = 1;
                }
                direction = i;
            }
            if (this.pdfView.isSwipeVertical()) {
                f2 = ev.getY();
                f = downEvent.getY();
            } else {
                f2 = ev.getX();
                f = downEvent.getX();
            }
            float delta = f2 - f;
            int targetPage = Math.max(0, Math.min(this.pdfView.getPageCount() - 1, this.pdfView.findFocusPage(this.pdfView.getCurrentXOffset() - (this.pdfView.getZoom() * delta), this.pdfView.getCurrentYOffset() - (this.pdfView.getZoom() * delta)) + direction));
            this.animationManager.startPageFlingAnimation(-this.pdfView.snapOffsetForPage(targetPage, this.pdfView.findSnapEdge(targetPage)));
        }
    }

    public boolean onDoubleTap(MotionEvent e) {
        if (!this.pdfView.isDoubletapEnabled()) {
            return false;
        }
        if (this.pdfView.getZoom() < this.pdfView.getMidZoom()) {
            this.pdfView.zoomWithAnimation(e.getX(), e.getY(), this.pdfView.getMidZoom());
        } else if (this.pdfView.getZoom() < this.pdfView.getMaxZoom()) {
            this.pdfView.zoomWithAnimation(e.getX(), e.getY(), this.pdfView.getMaxZoom());
        } else {
            this.pdfView.resetZoomWithAnimation();
        }
        return true;
    }

    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    public boolean onDown(MotionEvent e) {
        this.animationManager.stopFling();
        return true;
    }

    public void onShowPress(MotionEvent e) {
    }

    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        this.scrolling = true;
        if (this.pdfView.isZooming() || this.pdfView.isSwipeEnabled()) {
            this.pdfView.moveRelativeTo(-distanceX, -distanceY);
        }
        if (!this.scaling || this.pdfView.doRenderDuringScale()) {
            this.pdfView.loadPageByOffset();
        }
        return true;
    }

    private void onScrollEnd(MotionEvent event) {
        this.pdfView.loadPages();
        hideHandle();
        if (!this.animationManager.isFlinging()) {
            this.pdfView.performPageSnap();
        }
    }

    public void onLongPress(MotionEvent e) {
        this.pdfView.callbacks.callOnLongPress(e);
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float minX;
        float minY;
        float f = velocityX;
        float f2 = velocityY;
        if (!this.pdfView.isSwipeEnabled()) {
            return false;
        }
        if (this.pdfView.doPageFling()) {
            if (this.pdfView.pageFillsScreen()) {
                onBoundedFling(f, f2);
            } else {
                startPageFling(e1, e2, velocityX, velocityY);
            }
            return true;
        }
        int xOffset = (int) this.pdfView.getCurrentXOffset();
        int yOffset = (int) this.pdfView.getCurrentYOffset();
        PdfFile pdfFile = this.pdfView.pdfFile;
        if (this.pdfView.isSwipeVertical()) {
            minX = -(this.pdfView.toCurrentScale(pdfFile.getMaxPageWidth()) - ((float) this.pdfView.getWidth()));
            minY = -(pdfFile.getDocLen(this.pdfView.getZoom()) - ((float) this.pdfView.getHeight()));
        } else {
            minX = -(pdfFile.getDocLen(this.pdfView.getZoom()) - ((float) this.pdfView.getWidth()));
            minY = -(this.pdfView.toCurrentScale(pdfFile.getMaxPageHeight()) - ((float) this.pdfView.getHeight()));
        }
        float f3 = minY;
        int i = (int) minY;
        float f4 = minX;
        this.animationManager.startFlingAnimation(xOffset, yOffset, (int) f, (int) f2, (int) minX, 0, i, 0);
        return true;
    }

    private void onBoundedFling(float velocityX, float velocityY) {
        float minX;
        float maxX;
        float minY;
        float maxY;
        int xOffset = (int) this.pdfView.getCurrentXOffset();
        int yOffset = (int) this.pdfView.getCurrentYOffset();
        PdfFile pdfFile = this.pdfView.pdfFile;
        float pageStart = -pdfFile.getPageOffset(this.pdfView.getCurrentPage(), this.pdfView.getZoom());
        float pageEnd = pageStart - pdfFile.getPageLength(this.pdfView.getCurrentPage(), this.pdfView.getZoom());
        if (this.pdfView.isSwipeVertical()) {
            minX = -(this.pdfView.toCurrentScale(pdfFile.getMaxPageWidth()) - ((float) this.pdfView.getWidth()));
            minY = ((float) this.pdfView.getHeight()) + pageEnd;
            maxX = 0.0f;
            maxY = pageStart;
        } else {
            minX = ((float) this.pdfView.getWidth()) + pageEnd;
            minY = -(this.pdfView.toCurrentScale(pdfFile.getMaxPageHeight()) - ((float) this.pdfView.getHeight()));
            maxX = pageStart;
            maxY = 0.0f;
        }
        float f = maxX;
        PdfFile pdfFile2 = pdfFile;
        int i = (int) maxY;
        int i2 = yOffset;
        int i3 = (int) minX;
        float f2 = maxY;
        int i4 = (int) maxX;
        float f3 = minY;
        this.animationManager.startFlingAnimation(xOffset, i2, (int) velocityX, (int) velocityY, i3, i4, (int) minY, i);
    }

    public boolean onScale(ScaleGestureDetector detector) {
        float dr = detector.getScaleFactor();
        float wantedZoom = this.pdfView.getZoom() * dr;
        if (wantedZoom < Pinch.MINIMUM_ZOOM) {
            dr = Pinch.MINIMUM_ZOOM / this.pdfView.getZoom();
        } else if (wantedZoom > Pinch.MAXIMUM_ZOOM) {
            dr = Pinch.MAXIMUM_ZOOM / this.pdfView.getZoom();
        }
        this.pdfView.zoomCenteredRelativeTo(dr, new PointF(detector.getFocusX(), detector.getFocusY()));
        return true;
    }

    public boolean onScaleBegin(ScaleGestureDetector detector) {
        this.scaling = true;
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector detector) {
        this.pdfView.loadPages();
        hideHandle();
        this.scaling = false;
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (!this.enabled) {
            return false;
        }
        boolean retVal = this.gestureDetector.onTouchEvent(event) || this.scaleGestureDetector.onTouchEvent(event);
        if (event.getAction() == 1 && this.scrolling) {
            this.scrolling = false;
            onScrollEnd(event);
        }
        return retVal;
    }

    private void hideHandle() {
        ScrollHandle scrollHandle = this.pdfView.getScrollHandle();
        if (scrollHandle != null && scrollHandle.shown()) {
            scrollHandle.hideDelayed();
        }
    }

    private boolean checkDoPageFling(float velocityX, float velocityY) {
        float absX = Math.abs(velocityX);
        float absY = Math.abs(velocityY);
        if (this.pdfView.isSwipeVertical()) {
            if (absY > absX) {
                return true;
            }
        } else if (absX > absY) {
            return true;
        }
        return false;
    }
}
