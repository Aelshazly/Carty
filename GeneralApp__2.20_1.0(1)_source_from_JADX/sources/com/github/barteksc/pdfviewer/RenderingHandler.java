package com.github.barteksc.pdfviewer;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.github.barteksc.pdfviewer.exception.PageRenderingException;
import com.github.barteksc.pdfviewer.model.PagePart;

class RenderingHandler extends Handler {
    static final int MSG_RENDER_TASK = 1;
    private static final String TAG = RenderingHandler.class.getName();
    /* access modifiers changed from: private */
    public PDFView pdfView;
    private RectF renderBounds = new RectF();
    private Matrix renderMatrix = new Matrix();
    private Rect roundedRenderBounds = new Rect();
    private boolean running = false;

    private class RenderingTask {
        boolean annotationRendering;
        boolean bestQuality;
        RectF bounds;
        int cacheOrder;
        float height;
        int page;
        boolean thumbnail;
        float width;

        RenderingTask(float width2, float height2, RectF bounds2, int page2, boolean thumbnail2, int cacheOrder2, boolean bestQuality2, boolean annotationRendering2) {
            this.page = page2;
            this.width = width2;
            this.height = height2;
            this.bounds = bounds2;
            this.thumbnail = thumbnail2;
            this.cacheOrder = cacheOrder2;
            this.bestQuality = bestQuality2;
            this.annotationRendering = annotationRendering2;
        }
    }

    RenderingHandler(Looper looper, PDFView pdfView2) {
        super(looper);
        this.pdfView = pdfView2;
    }

    /* access modifiers changed from: 0000 */
    public void addRenderingTask(int page, float width, float height, RectF bounds, boolean thumbnail, int cacheOrder, boolean bestQuality, boolean annotationRendering) {
        RenderingTask task = new RenderingTask(width, height, bounds, page, thumbnail, cacheOrder, bestQuality, annotationRendering);
        sendMessage(obtainMessage(1, task));
    }

    public void handleMessage(Message message) {
        try {
            final PagePart part = proceed((RenderingTask) message.obj);
            if (part == null) {
                return;
            }
            if (this.running) {
                this.pdfView.post(new Runnable() {
                    public void run() {
                        RenderingHandler.this.pdfView.onBitmapRendered(part);
                    }
                });
            } else {
                part.getRenderedBitmap().recycle();
            }
        } catch (PageRenderingException ex) {
            this.pdfView.post(new Runnable() {
                public void run() {
                    RenderingHandler.this.pdfView.onPageError(ex);
                }
            });
        }
    }

    private PagePart proceed(RenderingTask renderingTask) throws PageRenderingException {
        PdfFile pdfFile = this.pdfView.pdfFile;
        pdfFile.openPage(renderingTask.page);
        int w = Math.round(renderingTask.width);
        int h = Math.round(renderingTask.height);
        if (w == 0 || h == 0 || pdfFile.pageHasError(renderingTask.page)) {
            return null;
        }
        try {
            Bitmap render = Bitmap.createBitmap(w, h, renderingTask.bestQuality ? Config.ARGB_8888 : Config.RGB_565);
            calculateBounds(w, h, renderingTask.bounds);
            pdfFile.renderPageBitmap(render, renderingTask.page, this.roundedRenderBounds, renderingTask.annotationRendering);
            PagePart pagePart = new PagePart(renderingTask.page, render, renderingTask.bounds, renderingTask.thumbnail, renderingTask.cacheOrder);
            return pagePart;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Cannot create bitmap", e);
            return null;
        }
    }

    private void calculateBounds(int width, int height, RectF pageSliceBounds) {
        this.renderMatrix.reset();
        this.renderMatrix.postTranslate((-pageSliceBounds.left) * ((float) width), (-pageSliceBounds.top) * ((float) height));
        this.renderMatrix.postScale(1.0f / pageSliceBounds.width(), 1.0f / pageSliceBounds.height());
        this.renderBounds.set(0.0f, 0.0f, (float) width, (float) height);
        this.renderMatrix.mapRect(this.renderBounds);
        this.renderBounds.round(this.roundedRenderBounds);
    }

    /* access modifiers changed from: 0000 */
    public void stop() {
        this.running = false;
    }

    /* access modifiers changed from: 0000 */
    public void start() {
        this.running = true;
    }
}
