package com.github.barteksc.pdfviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import com.github.barteksc.pdfviewer.exception.PageRenderingException;
import com.github.barteksc.pdfviewer.link.DefaultLinkHandler;
import com.github.barteksc.pdfviewer.link.LinkHandler;
import com.github.barteksc.pdfviewer.listener.Callbacks;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnLongPressListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.github.barteksc.pdfviewer.model.PagePart;
import com.github.barteksc.pdfviewer.scroll.ScrollHandle;
import com.github.barteksc.pdfviewer.source.AssetSource;
import com.github.barteksc.pdfviewer.source.ByteArraySource;
import com.github.barteksc.pdfviewer.source.DocumentSource;
import com.github.barteksc.pdfviewer.source.FileSource;
import com.github.barteksc.pdfviewer.source.InputStreamSource;
import com.github.barteksc.pdfviewer.source.UriSource;
import com.github.barteksc.pdfviewer.util.Constants;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.github.barteksc.pdfviewer.util.MathUtils;
import com.github.barteksc.pdfviewer.util.SnapEdge;
import com.github.barteksc.pdfviewer.util.Util;
import com.shockwave.pdfium.PdfDocument.Bookmark;
import com.shockwave.pdfium.PdfDocument.Link;
import com.shockwave.pdfium.PdfDocument.Meta;
import com.shockwave.pdfium.PdfiumCore;
import com.shockwave.pdfium.util.Size;
import com.shockwave.pdfium.util.SizeF;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PDFView extends RelativeLayout {
    public static final float DEFAULT_MAX_SCALE = 3.0f;
    public static final float DEFAULT_MID_SCALE = 1.75f;
    public static final float DEFAULT_MIN_SCALE = 1.0f;
    private static final String TAG = PDFView.class.getSimpleName();
    private AnimationManager animationManager;
    private boolean annotationRendering = false;
    private PaintFlagsDrawFilter antialiasFilter = new PaintFlagsDrawFilter(0, 3);
    private boolean autoSpacing = false;
    private boolean bestQuality = false;
    CacheManager cacheManager;
    Callbacks callbacks = new Callbacks();
    private int currentPage;
    private float currentXOffset = 0.0f;
    private float currentYOffset = 0.0f;
    private Paint debugPaint;
    private DecodingAsyncTask decodingAsyncTask;
    private int defaultPage = 0;
    private boolean doubletapEnabled = true;
    private DragPinchManager dragPinchManager;
    private boolean enableAntialiasing = true;
    private boolean enableSwipe = true;
    /* access modifiers changed from: private */
    public boolean hasSize = false;
    private boolean isScrollHandleInit = false;
    private float maxZoom = 3.0f;
    private float midZoom = 1.75f;
    private float minZoom = 1.0f;
    private boolean nightMode = false;
    private List<Integer> onDrawPagesNums = new ArrayList(10);
    private FitPolicy pageFitPolicy = FitPolicy.WIDTH;
    private boolean pageFling = true;
    private boolean pageSnap = true;
    private PagesLoader pagesLoader;
    private Paint paint;
    PdfFile pdfFile;
    private PdfiumCore pdfiumCore;
    private boolean recycled = true;
    private boolean renderDuringScale = false;
    RenderingHandler renderingHandler;
    private final HandlerThread renderingHandlerThread = new HandlerThread("PDF renderer");
    private ScrollDir scrollDir = ScrollDir.NONE;
    private ScrollHandle scrollHandle;
    private int spacingPx = 0;
    private State state = State.DEFAULT;
    private boolean swipeVertical = true;
    /* access modifiers changed from: private */
    public Configurator waitingDocumentConfigurator;
    private float zoom = 1.0f;

    public class Configurator {
        private boolean annotationRendering;
        private boolean antialiasing;
        private boolean autoSpacing;
        private int defaultPage;
        private final DocumentSource documentSource;
        private boolean enableDoubletap;
        private boolean enableSwipe;
        private LinkHandler linkHandler;
        private boolean nightMode;
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
        private FitPolicy pageFitPolicy;
        private boolean pageFling;
        private int[] pageNumbers;
        private boolean pageSnap;
        private String password;
        private ScrollHandle scrollHandle;
        private int spacing;
        private boolean swipeHorizontal;

        private Configurator(DocumentSource documentSource2) {
            this.pageNumbers = null;
            this.enableSwipe = true;
            this.enableDoubletap = true;
            this.linkHandler = new DefaultLinkHandler(PDFView.this);
            this.defaultPage = 0;
            this.swipeHorizontal = false;
            this.annotationRendering = false;
            this.password = null;
            this.scrollHandle = null;
            this.antialiasing = true;
            this.spacing = 0;
            this.autoSpacing = false;
            this.pageFitPolicy = FitPolicy.WIDTH;
            this.pageFling = false;
            this.pageSnap = false;
            this.nightMode = false;
            this.documentSource = documentSource2;
        }

        public Configurator pages(int... pageNumbers2) {
            this.pageNumbers = pageNumbers2;
            return this;
        }

        public Configurator enableSwipe(boolean enableSwipe2) {
            this.enableSwipe = enableSwipe2;
            return this;
        }

        public Configurator enableDoubletap(boolean enableDoubletap2) {
            this.enableDoubletap = enableDoubletap2;
            return this;
        }

        public Configurator enableAnnotationRendering(boolean annotationRendering2) {
            this.annotationRendering = annotationRendering2;
            return this;
        }

        public Configurator onDraw(OnDrawListener onDrawListener2) {
            this.onDrawListener = onDrawListener2;
            return this;
        }

        public Configurator onDrawAll(OnDrawListener onDrawAllListener2) {
            this.onDrawAllListener = onDrawAllListener2;
            return this;
        }

        public Configurator onLoad(OnLoadCompleteListener onLoadCompleteListener2) {
            this.onLoadCompleteListener = onLoadCompleteListener2;
            return this;
        }

        public Configurator onPageScroll(OnPageScrollListener onPageScrollListener2) {
            this.onPageScrollListener = onPageScrollListener2;
            return this;
        }

        public Configurator onError(OnErrorListener onErrorListener2) {
            this.onErrorListener = onErrorListener2;
            return this;
        }

        public Configurator onPageError(OnPageErrorListener onPageErrorListener2) {
            this.onPageErrorListener = onPageErrorListener2;
            return this;
        }

        public Configurator onPageChange(OnPageChangeListener onPageChangeListener2) {
            this.onPageChangeListener = onPageChangeListener2;
            return this;
        }

        public Configurator onRender(OnRenderListener onRenderListener2) {
            this.onRenderListener = onRenderListener2;
            return this;
        }

        public Configurator onTap(OnTapListener onTapListener2) {
            this.onTapListener = onTapListener2;
            return this;
        }

        public Configurator onLongPress(OnLongPressListener onLongPressListener2) {
            this.onLongPressListener = onLongPressListener2;
            return this;
        }

        public Configurator linkHandler(LinkHandler linkHandler2) {
            this.linkHandler = linkHandler2;
            return this;
        }

        public Configurator defaultPage(int defaultPage2) {
            this.defaultPage = defaultPage2;
            return this;
        }

        public Configurator swipeHorizontal(boolean swipeHorizontal2) {
            this.swipeHorizontal = swipeHorizontal2;
            return this;
        }

        public Configurator password(String password2) {
            this.password = password2;
            return this;
        }

        public Configurator scrollHandle(ScrollHandle scrollHandle2) {
            this.scrollHandle = scrollHandle2;
            return this;
        }

        public Configurator enableAntialiasing(boolean antialiasing2) {
            this.antialiasing = antialiasing2;
            return this;
        }

        public Configurator spacing(int spacing2) {
            this.spacing = spacing2;
            return this;
        }

        public Configurator autoSpacing(boolean autoSpacing2) {
            this.autoSpacing = autoSpacing2;
            return this;
        }

        public Configurator pageFitPolicy(FitPolicy pageFitPolicy2) {
            this.pageFitPolicy = pageFitPolicy2;
            return this;
        }

        public Configurator pageSnap(boolean pageSnap2) {
            this.pageSnap = pageSnap2;
            return this;
        }

        public Configurator pageFling(boolean pageFling2) {
            this.pageFling = pageFling2;
            return this;
        }

        public Configurator nightMode(boolean nightMode2) {
            this.nightMode = nightMode2;
            return this;
        }

        public void load() {
            if (!PDFView.this.hasSize) {
                PDFView.this.waitingDocumentConfigurator = this;
                return;
            }
            PDFView.this.recycle();
            PDFView.this.callbacks.setOnLoadComplete(this.onLoadCompleteListener);
            PDFView.this.callbacks.setOnError(this.onErrorListener);
            PDFView.this.callbacks.setOnDraw(this.onDrawListener);
            PDFView.this.callbacks.setOnDrawAll(this.onDrawAllListener);
            PDFView.this.callbacks.setOnPageChange(this.onPageChangeListener);
            PDFView.this.callbacks.setOnPageScroll(this.onPageScrollListener);
            PDFView.this.callbacks.setOnRender(this.onRenderListener);
            PDFView.this.callbacks.setOnTap(this.onTapListener);
            PDFView.this.callbacks.setOnLongPress(this.onLongPressListener);
            PDFView.this.callbacks.setOnPageError(this.onPageErrorListener);
            PDFView.this.callbacks.setLinkHandler(this.linkHandler);
            PDFView.this.setSwipeEnabled(this.enableSwipe);
            PDFView.this.setNightMode(this.nightMode);
            PDFView.this.enableDoubletap(this.enableDoubletap);
            PDFView.this.setDefaultPage(this.defaultPage);
            PDFView.this.setSwipeVertical(!this.swipeHorizontal);
            PDFView.this.enableAnnotationRendering(this.annotationRendering);
            PDFView.this.setScrollHandle(this.scrollHandle);
            PDFView.this.enableAntialiasing(this.antialiasing);
            PDFView.this.setSpacing(this.spacing);
            PDFView.this.setAutoSpacing(this.autoSpacing);
            PDFView.this.setPageFitPolicy(this.pageFitPolicy);
            PDFView.this.setPageSnap(this.pageSnap);
            PDFView.this.setPageFling(this.pageFling);
            int[] iArr = this.pageNumbers;
            if (iArr != null) {
                PDFView.this.load(this.documentSource, this.password, iArr);
            } else {
                PDFView.this.load(this.documentSource, this.password);
            }
        }
    }

    enum ScrollDir {
        NONE,
        START,
        END
    }

    private enum State {
        DEFAULT,
        LOADED,
        SHOWN,
        ERROR
    }

    /* access modifiers changed from: 0000 */
    public ScrollHandle getScrollHandle() {
        return this.scrollHandle;
    }

    public PDFView(Context context, AttributeSet set) {
        super(context, set);
        if (!isInEditMode()) {
            this.cacheManager = new CacheManager();
            this.animationManager = new AnimationManager(this);
            this.dragPinchManager = new DragPinchManager(this, this.animationManager);
            this.pagesLoader = new PagesLoader(this);
            this.paint = new Paint();
            this.debugPaint = new Paint();
            this.debugPaint.setStyle(Style.STROKE);
            this.pdfiumCore = new PdfiumCore(context);
            setWillNotDraw(false);
        }
    }

    /* access modifiers changed from: private */
    public void load(DocumentSource docSource, String password) {
        load(docSource, password, null);
    }

    /* access modifiers changed from: private */
    public void load(DocumentSource docSource, String password, int[] userPages) {
        if (this.recycled) {
            this.recycled = false;
            DecodingAsyncTask decodingAsyncTask2 = new DecodingAsyncTask(docSource, password, userPages, this, this.pdfiumCore);
            this.decodingAsyncTask = decodingAsyncTask2;
            this.decodingAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        throw new IllegalStateException("Don't call load on a PDF View without recycling it first.");
    }

    public void jumpTo(int page, boolean withAnimation) {
        PdfFile pdfFile2 = this.pdfFile;
        if (pdfFile2 != null) {
            int page2 = pdfFile2.determineValidPageNumberFrom(page);
            float offset = page2 == 0 ? 0.0f : -this.pdfFile.getPageOffset(page2, this.zoom);
            if (this.swipeVertical) {
                if (withAnimation) {
                    this.animationManager.startYAnimation(this.currentYOffset, offset);
                } else {
                    moveTo(this.currentXOffset, offset);
                }
            } else if (withAnimation) {
                this.animationManager.startXAnimation(this.currentXOffset, offset);
            } else {
                moveTo(offset, this.currentYOffset);
            }
            showPage(page2);
        }
    }

    public void jumpTo(int page) {
        jumpTo(page, false);
    }

    /* access modifiers changed from: 0000 */
    public void showPage(int pageNb) {
        if (!this.recycled) {
            this.currentPage = this.pdfFile.determineValidPageNumberFrom(pageNb);
            loadPages();
            if (this.scrollHandle != null && !documentFitsView()) {
                this.scrollHandle.setPageNum(this.currentPage + 1);
            }
            this.callbacks.callOnPageChange(this.currentPage, this.pdfFile.getPagesCount());
        }
    }

    public float getPositionOffset() {
        float offset;
        if (this.swipeVertical) {
            offset = (-this.currentYOffset) / (this.pdfFile.getDocLen(this.zoom) - ((float) getHeight()));
        } else {
            offset = (-this.currentXOffset) / (this.pdfFile.getDocLen(this.zoom) - ((float) getWidth()));
        }
        return MathUtils.limit(offset, 0.0f, 1.0f);
    }

    public void setPositionOffset(float progress, boolean moveHandle) {
        if (this.swipeVertical) {
            moveTo(this.currentXOffset, ((-this.pdfFile.getDocLen(this.zoom)) + ((float) getHeight())) * progress, moveHandle);
        } else {
            moveTo(((-this.pdfFile.getDocLen(this.zoom)) + ((float) getWidth())) * progress, this.currentYOffset, moveHandle);
        }
        loadPageByOffset();
    }

    public void setPositionOffset(float progress) {
        setPositionOffset(progress, true);
    }

    public void stopFling() {
        this.animationManager.stopFling();
    }

    public int getPageCount() {
        PdfFile pdfFile2 = this.pdfFile;
        if (pdfFile2 == null) {
            return 0;
        }
        return pdfFile2.getPagesCount();
    }

    public void setSwipeEnabled(boolean enableSwipe2) {
        this.enableSwipe = enableSwipe2;
    }

    public void setNightMode(boolean nightMode2) {
        this.nightMode = nightMode2;
        if (nightMode2) {
            this.paint.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 255.0f, 0.0f, -1.0f, 0.0f, 0.0f, 255.0f, 0.0f, 0.0f, -1.0f, 0.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f})));
            return;
        }
        this.paint.setColorFilter(null);
    }

    /* access modifiers changed from: 0000 */
    public void enableDoubletap(boolean enableDoubletap) {
        this.doubletapEnabled = enableDoubletap;
    }

    /* access modifiers changed from: 0000 */
    public boolean isDoubletapEnabled() {
        return this.doubletapEnabled;
    }

    /* access modifiers changed from: 0000 */
    public void onPageError(PageRenderingException ex) {
        if (!this.callbacks.callOnPageError(ex.getPage(), ex.getCause())) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot open page ");
            sb.append(ex.getPage());
            Log.e(str, sb.toString(), ex.getCause());
        }
    }

    public void recycle() {
        this.waitingDocumentConfigurator = null;
        this.animationManager.stopAll();
        this.dragPinchManager.disable();
        RenderingHandler renderingHandler2 = this.renderingHandler;
        if (renderingHandler2 != null) {
            renderingHandler2.stop();
            this.renderingHandler.removeMessages(1);
        }
        DecodingAsyncTask decodingAsyncTask2 = this.decodingAsyncTask;
        if (decodingAsyncTask2 != null) {
            decodingAsyncTask2.cancel(true);
        }
        this.cacheManager.recycle();
        ScrollHandle scrollHandle2 = this.scrollHandle;
        if (scrollHandle2 != null && this.isScrollHandleInit) {
            scrollHandle2.destroyLayout();
        }
        PdfFile pdfFile2 = this.pdfFile;
        if (pdfFile2 != null) {
            pdfFile2.dispose();
            this.pdfFile = null;
        }
        this.renderingHandler = null;
        this.scrollHandle = null;
        this.isScrollHandleInit = false;
        this.currentYOffset = 0.0f;
        this.currentXOffset = 0.0f;
        this.zoom = 1.0f;
        this.recycled = true;
        this.callbacks = new Callbacks();
        this.state = State.DEFAULT;
    }

    public boolean isRecycled() {
        return this.recycled;
    }

    public void computeScroll() {
        super.computeScroll();
        if (!isInEditMode()) {
            this.animationManager.computeFling();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        recycle();
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.hasSize = true;
        Configurator configurator = this.waitingDocumentConfigurator;
        if (configurator != null) {
            configurator.load();
        }
        if (!isInEditMode() && this.state == State.SHOWN) {
            this.animationManager.stopAll();
            this.pdfFile.recalculatePageSizes(new Size(w, h));
            if (this.swipeVertical) {
                moveTo(this.currentXOffset, -this.pdfFile.getPageOffset(this.currentPage, this.zoom));
            } else {
                moveTo(-this.pdfFile.getPageOffset(this.currentPage, this.zoom), this.currentYOffset);
            }
            loadPageByOffset();
        }
    }

    public boolean canScrollHorizontally(int direction) {
        if (this.pdfFile == null) {
            return true;
        }
        if (this.swipeVertical) {
            if (direction < 0 && this.currentXOffset < 0.0f) {
                return true;
            }
            if (direction > 0 && this.currentXOffset + toCurrentScale(this.pdfFile.getMaxPageWidth()) > ((float) getWidth())) {
                return true;
            }
        } else if (direction < 0 && this.currentXOffset < 0.0f) {
            return true;
        } else {
            if (direction > 0 && this.currentXOffset + this.pdfFile.getDocLen(this.zoom) > ((float) getWidth())) {
                return true;
            }
        }
        return false;
    }

    public boolean canScrollVertically(int direction) {
        if (this.pdfFile == null) {
            return true;
        }
        if (this.swipeVertical) {
            if (direction < 0 && this.currentYOffset < 0.0f) {
                return true;
            }
            if (direction > 0 && this.currentYOffset + this.pdfFile.getDocLen(this.zoom) > ((float) getHeight())) {
                return true;
            }
        } else if (direction < 0 && this.currentYOffset < 0.0f) {
            return true;
        } else {
            if (direction > 0 && this.currentYOffset + toCurrentScale(this.pdfFile.getMaxPageHeight()) > ((float) getHeight())) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (!isInEditMode()) {
            if (this.enableAntialiasing) {
                canvas.setDrawFilter(this.antialiasFilter);
            }
            Drawable bg = getBackground();
            if (bg == null) {
                canvas.drawColor(this.nightMode ? ViewCompat.MEASURED_STATE_MASK : -1);
            } else {
                bg.draw(canvas);
            }
            if (!this.recycled && this.state == State.SHOWN) {
                float currentXOffset2 = this.currentXOffset;
                float currentYOffset2 = this.currentYOffset;
                canvas.translate(currentXOffset2, currentYOffset2);
                for (PagePart part : this.cacheManager.getThumbnails()) {
                    drawPart(canvas, part);
                }
                for (PagePart part2 : this.cacheManager.getPageParts()) {
                    drawPart(canvas, part2);
                    if (this.callbacks.getOnDrawAll() != null && !this.onDrawPagesNums.contains(Integer.valueOf(part2.getPage()))) {
                        this.onDrawPagesNums.add(Integer.valueOf(part2.getPage()));
                    }
                }
                for (Integer page : this.onDrawPagesNums) {
                    drawWithListener(canvas, page.intValue(), this.callbacks.getOnDrawAll());
                }
                this.onDrawPagesNums.clear();
                drawWithListener(canvas, this.currentPage, this.callbacks.getOnDraw());
                canvas.translate(-currentXOffset2, -currentYOffset2);
            }
        }
    }

    private void drawWithListener(Canvas canvas, int page, OnDrawListener listener) {
        float translateY;
        float translateX;
        if (listener != null) {
            if (this.swipeVertical) {
                translateX = 0.0f;
                translateY = this.pdfFile.getPageOffset(page, this.zoom);
            } else {
                translateY = 0.0f;
                translateX = this.pdfFile.getPageOffset(page, this.zoom);
            }
            canvas.translate(translateX, translateY);
            SizeF size = this.pdfFile.getPageSize(page);
            listener.onLayerDrawn(canvas, toCurrentScale(size.getWidth()), toCurrentScale(size.getHeight()), page);
            canvas.translate(-translateX, -translateY);
        }
    }

    private void drawPart(Canvas canvas, PagePart part) {
        float localTranslationX;
        float localTranslationY;
        Canvas canvas2 = canvas;
        RectF pageRelativeBounds = part.getPageRelativeBounds();
        Bitmap renderedBitmap = part.getRenderedBitmap();
        if (!renderedBitmap.isRecycled()) {
            SizeF size = this.pdfFile.getPageSize(part.getPage());
            if (this.swipeVertical) {
                localTranslationY = this.pdfFile.getPageOffset(part.getPage(), this.zoom);
                localTranslationX = toCurrentScale(this.pdfFile.getMaxPageWidth() - size.getWidth()) / 2.0f;
            } else {
                localTranslationX = this.pdfFile.getPageOffset(part.getPage(), this.zoom);
                localTranslationY = toCurrentScale(this.pdfFile.getMaxPageHeight() - size.getHeight()) / 2.0f;
            }
            canvas2.translate(localTranslationX, localTranslationY);
            Rect srcRect = new Rect(0, 0, renderedBitmap.getWidth(), renderedBitmap.getHeight());
            float offsetX = toCurrentScale(pageRelativeBounds.left * size.getWidth());
            float offsetY = toCurrentScale(pageRelativeBounds.top * size.getHeight());
            RectF rectF = pageRelativeBounds;
            RectF dstRect = new RectF((float) ((int) offsetX), (float) ((int) offsetY), (float) ((int) (offsetX + toCurrentScale(pageRelativeBounds.width() * size.getWidth()))), (float) ((int) (offsetY + toCurrentScale(pageRelativeBounds.height() * size.getHeight()))));
            float translationX = this.currentXOffset + localTranslationX;
            float translationY = this.currentYOffset + localTranslationY;
            if (dstRect.left + translationX >= ((float) getWidth()) || dstRect.right + translationX <= 0.0f || dstRect.top + translationY >= ((float) getHeight()) || dstRect.bottom + translationY <= 0.0f) {
                canvas2.translate(-localTranslationX, -localTranslationY);
                return;
            }
            canvas2.drawBitmap(renderedBitmap, srcRect, dstRect, this.paint);
            if (Constants.DEBUG_MODE) {
                this.debugPaint.setColor(part.getPage() % 2 == 0 ? SupportMenu.CATEGORY_MASK : -16776961);
                canvas2.drawRect(dstRect, this.debugPaint);
            }
            canvas2.translate(-localTranslationX, -localTranslationY);
        }
    }

    public void loadPages() {
        if (this.pdfFile != null) {
            RenderingHandler renderingHandler2 = this.renderingHandler;
            if (renderingHandler2 != null) {
                renderingHandler2.removeMessages(1);
                this.cacheManager.makeANewSet();
                this.pagesLoader.loadPages();
                redraw();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void loadComplete(PdfFile pdfFile2) {
        this.state = State.LOADED;
        this.pdfFile = pdfFile2;
        if (!this.renderingHandlerThread.isAlive()) {
            this.renderingHandlerThread.start();
        }
        this.renderingHandler = new RenderingHandler(this.renderingHandlerThread.getLooper(), this);
        this.renderingHandler.start();
        ScrollHandle scrollHandle2 = this.scrollHandle;
        if (scrollHandle2 != null) {
            scrollHandle2.setupLayout(this);
            this.isScrollHandleInit = true;
        }
        this.dragPinchManager.enable();
        this.callbacks.callOnLoadComplete(pdfFile2.getPagesCount());
        jumpTo(this.defaultPage, false);
    }

    /* access modifiers changed from: 0000 */
    public void loadError(Throwable t) {
        this.state = State.ERROR;
        OnErrorListener onErrorListener = this.callbacks.getOnError();
        recycle();
        invalidate();
        if (onErrorListener != null) {
            onErrorListener.onError(t);
        } else {
            Log.e("PDFView", "load pdf error", t);
        }
    }

    /* access modifiers changed from: 0000 */
    public void redraw() {
        invalidate();
    }

    public void onBitmapRendered(PagePart part) {
        if (this.state == State.LOADED) {
            this.state = State.SHOWN;
            this.callbacks.callOnRender(this.pdfFile.getPagesCount());
        }
        if (part.isThumbnail()) {
            this.cacheManager.cacheThumbnail(part);
        } else {
            this.cacheManager.cachePart(part);
        }
        redraw();
    }

    public void moveTo(float offsetX, float offsetY) {
        moveTo(offsetX, offsetY, true);
    }

    public void moveTo(float offsetX, float offsetY, boolean moveHandle) {
        if (this.swipeVertical) {
            float scaledPageWidth = toCurrentScale(this.pdfFile.getMaxPageWidth());
            if (scaledPageWidth < ((float) getWidth())) {
                offsetX = ((float) (getWidth() / 2)) - (scaledPageWidth / 2.0f);
            } else if (offsetX > 0.0f) {
                offsetX = 0.0f;
            } else if (offsetX + scaledPageWidth < ((float) getWidth())) {
                offsetX = ((float) getWidth()) - scaledPageWidth;
            }
            float contentHeight = this.pdfFile.getDocLen(this.zoom);
            if (contentHeight < ((float) getHeight())) {
                offsetY = (((float) getHeight()) - contentHeight) / 2.0f;
            } else if (offsetY > 0.0f) {
                offsetY = 0.0f;
            } else if (offsetY + contentHeight < ((float) getHeight())) {
                offsetY = (-contentHeight) + ((float) getHeight());
            }
            float f = this.currentYOffset;
            if (offsetY < f) {
                this.scrollDir = ScrollDir.END;
            } else if (offsetY > f) {
                this.scrollDir = ScrollDir.START;
            } else {
                this.scrollDir = ScrollDir.NONE;
            }
        } else {
            float scaledPageHeight = toCurrentScale(this.pdfFile.getMaxPageHeight());
            if (scaledPageHeight < ((float) getHeight())) {
                offsetY = ((float) (getHeight() / 2)) - (scaledPageHeight / 2.0f);
            } else if (offsetY > 0.0f) {
                offsetY = 0.0f;
            } else if (offsetY + scaledPageHeight < ((float) getHeight())) {
                offsetY = ((float) getHeight()) - scaledPageHeight;
            }
            float contentWidth = this.pdfFile.getDocLen(this.zoom);
            if (contentWidth < ((float) getWidth())) {
                offsetX = (((float) getWidth()) - contentWidth) / 2.0f;
            } else if (offsetX > 0.0f) {
                offsetX = 0.0f;
            } else if (offsetX + contentWidth < ((float) getWidth())) {
                offsetX = (-contentWidth) + ((float) getWidth());
            }
            float f2 = this.currentXOffset;
            if (offsetX < f2) {
                this.scrollDir = ScrollDir.END;
            } else if (offsetX > f2) {
                this.scrollDir = ScrollDir.START;
            } else {
                this.scrollDir = ScrollDir.NONE;
            }
        }
        this.currentXOffset = offsetX;
        this.currentYOffset = offsetY;
        float positionOffset = getPositionOffset();
        if (moveHandle && this.scrollHandle != null && !documentFitsView()) {
            this.scrollHandle.setScroll(positionOffset);
        }
        this.callbacks.callOnPageScroll(getCurrentPage(), positionOffset);
        redraw();
    }

    /* access modifiers changed from: 0000 */
    public void loadPageByOffset() {
        float screenCenter;
        float offset;
        if (this.pdfFile.getPagesCount() != 0) {
            if (this.swipeVertical) {
                offset = this.currentYOffset;
                screenCenter = ((float) getHeight()) / 2.0f;
            } else {
                offset = this.currentXOffset;
                screenCenter = ((float) getWidth()) / 2.0f;
            }
            int page = this.pdfFile.getPageAtOffset(-(offset - screenCenter), this.zoom);
            if (page < 0 || page > this.pdfFile.getPagesCount() - 1 || page == getCurrentPage()) {
                loadPages();
            } else {
                showPage(page);
            }
        }
    }

    public void performPageSnap() {
        if (this.pageSnap) {
            PdfFile pdfFile2 = this.pdfFile;
            if (!(pdfFile2 == null || pdfFile2.getPagesCount() == 0)) {
                int centerPage = findFocusPage(this.currentXOffset, this.currentYOffset);
                SnapEdge edge = findSnapEdge(centerPage);
                if (edge != SnapEdge.NONE) {
                    float offset = snapOffsetForPage(centerPage, edge);
                    if (this.swipeVertical) {
                        this.animationManager.startYAnimation(this.currentYOffset, -offset);
                    } else {
                        this.animationManager.startXAnimation(this.currentXOffset, -offset);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public SnapEdge findSnapEdge(int page) {
        if (!this.pageSnap || page < 0) {
            return SnapEdge.NONE;
        }
        float currentOffset = this.swipeVertical ? this.currentYOffset : this.currentXOffset;
        float offset = -this.pdfFile.getPageOffset(page, this.zoom);
        int length = this.swipeVertical ? getHeight() : getWidth();
        float pageLength = this.pdfFile.getPageLength(page, this.zoom);
        if (((float) length) >= pageLength) {
            return SnapEdge.CENTER;
        }
        if (currentOffset >= offset) {
            return SnapEdge.START;
        }
        if (offset - pageLength > currentOffset - ((float) length)) {
            return SnapEdge.END;
        }
        return SnapEdge.NONE;
    }

    /* access modifiers changed from: 0000 */
    public float snapOffsetForPage(int pageIndex, SnapEdge edge) {
        float offset = this.pdfFile.getPageOffset(pageIndex, this.zoom);
        float length = (float) (this.swipeVertical ? getHeight() : getWidth());
        float pageLength = this.pdfFile.getPageLength(pageIndex, this.zoom);
        if (edge == SnapEdge.CENTER) {
            return (offset - (length / 2.0f)) + (pageLength / 2.0f);
        }
        if (edge == SnapEdge.END) {
            return (offset - length) + pageLength;
        }
        return offset;
    }

    /* access modifiers changed from: 0000 */
    public int findFocusPage(float xOffset, float yOffset) {
        float currOffset = this.swipeVertical ? yOffset : xOffset;
        float length = (float) (this.swipeVertical ? getHeight() : getWidth());
        if (currOffset > -1.0f) {
            return 0;
        }
        if (currOffset < (-this.pdfFile.getDocLen(this.zoom)) + length + 1.0f) {
            return this.pdfFile.getPagesCount() - 1;
        }
        return this.pdfFile.getPageAtOffset(-(currOffset - (length / 2.0f)), this.zoom);
    }

    public boolean pageFillsScreen() {
        float start = -this.pdfFile.getPageOffset(this.currentPage, this.zoom);
        float end = start - this.pdfFile.getPageLength(this.currentPage, this.zoom);
        boolean z = true;
        if (isSwipeVertical()) {
            float f = this.currentYOffset;
            if (start <= f || end >= f - ((float) getHeight())) {
                z = false;
            }
            return z;
        }
        float f2 = this.currentXOffset;
        if (start <= f2 || end >= f2 - ((float) getWidth())) {
            z = false;
        }
        return z;
    }

    public void moveRelativeTo(float dx, float dy) {
        moveTo(this.currentXOffset + dx, this.currentYOffset + dy);
    }

    public void zoomTo(float zoom2) {
        this.zoom = zoom2;
    }

    public void zoomCenteredTo(float zoom2, PointF pivot) {
        float dzoom = zoom2 / this.zoom;
        zoomTo(zoom2);
        moveTo((this.currentXOffset * dzoom) + (pivot.x - (pivot.x * dzoom)), (this.currentYOffset * dzoom) + (pivot.y - (pivot.y * dzoom)));
    }

    public void zoomCenteredRelativeTo(float dzoom, PointF pivot) {
        zoomCenteredTo(this.zoom * dzoom, pivot);
    }

    public boolean documentFitsView() {
        float len = this.pdfFile.getDocLen(1.0f);
        boolean z = true;
        if (this.swipeVertical) {
            if (len >= ((float) getHeight())) {
                z = false;
            }
            return z;
        }
        if (len >= ((float) getWidth())) {
            z = false;
        }
        return z;
    }

    public void fitToWidth(int page) {
        if (this.state != State.SHOWN) {
            Log.e(TAG, "Cannot fit, document not rendered yet");
            return;
        }
        zoomTo(((float) getWidth()) / this.pdfFile.getPageSize(page).getWidth());
        jumpTo(page);
    }

    public SizeF getPageSize(int pageIndex) {
        PdfFile pdfFile2 = this.pdfFile;
        if (pdfFile2 == null) {
            return new SizeF(0.0f, 0.0f);
        }
        return pdfFile2.getPageSize(pageIndex);
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public float getCurrentXOffset() {
        return this.currentXOffset;
    }

    public float getCurrentYOffset() {
        return this.currentYOffset;
    }

    public float toRealScale(float size) {
        return size / this.zoom;
    }

    public float toCurrentScale(float size) {
        return this.zoom * size;
    }

    public float getZoom() {
        return this.zoom;
    }

    public boolean isZooming() {
        return this.zoom != this.minZoom;
    }

    /* access modifiers changed from: private */
    public void setDefaultPage(int defaultPage2) {
        this.defaultPage = defaultPage2;
    }

    public void resetZoom() {
        zoomTo(this.minZoom);
    }

    public void resetZoomWithAnimation() {
        zoomWithAnimation(this.minZoom);
    }

    public void zoomWithAnimation(float centerX, float centerY, float scale) {
        this.animationManager.startZoomAnimation(centerX, centerY, this.zoom, scale);
    }

    public void zoomWithAnimation(float scale) {
        this.animationManager.startZoomAnimation((float) (getWidth() / 2), (float) (getHeight() / 2), this.zoom, scale);
    }

    /* access modifiers changed from: private */
    public void setScrollHandle(ScrollHandle scrollHandle2) {
        this.scrollHandle = scrollHandle2;
    }

    public int getPageAtPositionOffset(float positionOffset) {
        PdfFile pdfFile2 = this.pdfFile;
        return pdfFile2.getPageAtOffset(pdfFile2.getDocLen(this.zoom) * positionOffset, this.zoom);
    }

    public float getMinZoom() {
        return this.minZoom;
    }

    public void setMinZoom(float minZoom2) {
        this.minZoom = minZoom2;
    }

    public float getMidZoom() {
        return this.midZoom;
    }

    public void setMidZoom(float midZoom2) {
        this.midZoom = midZoom2;
    }

    public float getMaxZoom() {
        return this.maxZoom;
    }

    public void setMaxZoom(float maxZoom2) {
        this.maxZoom = maxZoom2;
    }

    public void useBestQuality(boolean bestQuality2) {
        this.bestQuality = bestQuality2;
    }

    public boolean isBestQuality() {
        return this.bestQuality;
    }

    public boolean isSwipeVertical() {
        return this.swipeVertical;
    }

    public boolean isSwipeEnabled() {
        return this.enableSwipe;
    }

    /* access modifiers changed from: private */
    public void setSwipeVertical(boolean swipeVertical2) {
        this.swipeVertical = swipeVertical2;
    }

    public void enableAnnotationRendering(boolean annotationRendering2) {
        this.annotationRendering = annotationRendering2;
    }

    public boolean isAnnotationRendering() {
        return this.annotationRendering;
    }

    public void enableRenderDuringScale(boolean renderDuringScale2) {
        this.renderDuringScale = renderDuringScale2;
    }

    public boolean isAntialiasing() {
        return this.enableAntialiasing;
    }

    public void enableAntialiasing(boolean enableAntialiasing2) {
        this.enableAntialiasing = enableAntialiasing2;
    }

    public int getSpacingPx() {
        return this.spacingPx;
    }

    public boolean doAutoSpacing() {
        return this.autoSpacing;
    }

    public void setPageFling(boolean pageFling2) {
        this.pageFling = pageFling2;
    }

    public boolean doPageFling() {
        return this.pageFling;
    }

    /* access modifiers changed from: private */
    public void setSpacing(int spacing) {
        this.spacingPx = Util.getDP(getContext(), spacing);
    }

    /* access modifiers changed from: private */
    public void setAutoSpacing(boolean autoSpacing2) {
        this.autoSpacing = autoSpacing2;
    }

    /* access modifiers changed from: private */
    public void setPageFitPolicy(FitPolicy pageFitPolicy2) {
        this.pageFitPolicy = pageFitPolicy2;
    }

    public FitPolicy getPageFitPolicy() {
        return this.pageFitPolicy;
    }

    public boolean doPageSnap() {
        return this.pageSnap;
    }

    public void setPageSnap(boolean pageSnap2) {
        this.pageSnap = pageSnap2;
    }

    public boolean doRenderDuringScale() {
        return this.renderDuringScale;
    }

    public Meta getDocumentMeta() {
        PdfFile pdfFile2 = this.pdfFile;
        if (pdfFile2 == null) {
            return null;
        }
        return pdfFile2.getMetaData();
    }

    public List<Bookmark> getTableOfContents() {
        PdfFile pdfFile2 = this.pdfFile;
        if (pdfFile2 == null) {
            return Collections.emptyList();
        }
        return pdfFile2.getBookmarks();
    }

    public List<Link> getLinks(int page) {
        PdfFile pdfFile2 = this.pdfFile;
        if (pdfFile2 == null) {
            return Collections.emptyList();
        }
        return pdfFile2.getPageLinks(page);
    }

    public Configurator fromAsset(String assetName) {
        return new Configurator(new AssetSource(assetName));
    }

    public Configurator fromFile(File file) {
        return new Configurator(new FileSource(file));
    }

    public Configurator fromUri(Uri uri) {
        return new Configurator(new UriSource(uri));
    }

    public Configurator fromBytes(byte[] bytes) {
        return new Configurator(new ByteArraySource(bytes));
    }

    public Configurator fromStream(InputStream stream) {
        return new Configurator(new InputStreamSource(stream));
    }

    public Configurator fromSource(DocumentSource docSource) {
        return new Configurator(docSource);
    }
}
