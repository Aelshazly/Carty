package com.github.barteksc.pdfviewer;

import android.graphics.RectF;
import com.github.barteksc.pdfviewer.util.Constants;
import com.github.barteksc.pdfviewer.util.Constants.Cache;
import com.github.barteksc.pdfviewer.util.MathUtils;
import com.github.barteksc.pdfviewer.util.Util;
import com.shockwave.pdfium.util.SizeF;

class PagesLoader {
    private int cacheOrder;
    private final GridSize firstGrid = new GridSize();
    private final Holder firstHolder = new Holder();
    private final GridSize lastGrid = new GridSize();
    private final Holder lastHolder = new Holder();
    private final GridSize middleGrid = new GridSize();
    private float pageRelativePartHeight;
    private float pageRelativePartWidth;
    private float partRenderHeight;
    private float partRenderWidth;
    private PDFView pdfView;
    private final int preloadOffset;
    private final RectF thumbnailRect = new RectF(0.0f, 0.0f, 1.0f, 1.0f);
    private float xOffset;
    private float yOffset;

    private class GridSize {
        int cols;
        int rows;

        private GridSize() {
        }
    }

    private class Holder {
        int col;
        int page;
        int row;

        private Holder() {
        }
    }

    PagesLoader(PDFView pdfView2) {
        this.pdfView = pdfView2;
        this.preloadOffset = Util.getDP(pdfView2.getContext(), Constants.PRELOAD_OFFSET);
    }

    private void getPageColsRows(GridSize grid, int pageIndex) {
        SizeF size = this.pdfView.pdfFile.getPageSize(pageIndex);
        float partWidth = (Constants.PART_SIZE * (1.0f / size.getWidth())) / this.pdfView.getZoom();
        grid.rows = MathUtils.ceil(1.0f / ((Constants.PART_SIZE * (1.0f / size.getHeight())) / this.pdfView.getZoom()));
        grid.cols = MathUtils.ceil(1.0f / partWidth);
    }

    private Holder getPageAndCoordsByOffset(Holder holder, GridSize grid, float localXOffset, float localYOffset, boolean endOffset) {
        float col;
        float col2;
        Holder holder2 = holder;
        GridSize gridSize = grid;
        float fixedXOffset = -MathUtils.max(localXOffset, 0.0f);
        float fixedYOffset = -MathUtils.max(localYOffset, 0.0f);
        holder2.page = this.pdfView.pdfFile.getPageAtOffset(this.pdfView.isSwipeVertical() ? fixedYOffset : fixedXOffset, this.pdfView.getZoom());
        getPageColsRows(gridSize, holder2.page);
        SizeF scaledPageSize = this.pdfView.pdfFile.getScaledPageSize(holder2.page, this.pdfView.getZoom());
        float rowHeight = scaledPageSize.getHeight() / ((float) gridSize.rows);
        float colWidth = scaledPageSize.getWidth() / ((float) gridSize.cols);
        float secondaryOffset = this.pdfView.pdfFile.getSecondaryPageOffset(holder2.page, this.pdfView.getZoom());
        if (this.pdfView.isSwipeVertical()) {
            col = Math.abs(fixedYOffset - this.pdfView.pdfFile.getPageOffset(holder2.page, this.pdfView.getZoom())) / rowHeight;
            col2 = MathUtils.min(fixedXOffset - secondaryOffset, 0.0f) / colWidth;
        } else {
            col = MathUtils.min(fixedYOffset - secondaryOffset, 0.0f) / rowHeight;
            col2 = Math.abs(fixedXOffset - this.pdfView.pdfFile.getPageOffset(holder2.page, this.pdfView.getZoom())) / colWidth;
        }
        if (endOffset) {
            holder2.row = MathUtils.ceil(col);
            holder2.col = MathUtils.ceil(col2);
        } else {
            holder2.row = MathUtils.floor(col);
            holder2.col = MathUtils.floor(col2);
        }
        return holder2;
    }

    private void calculatePartSize(GridSize grid) {
        this.pageRelativePartWidth = 1.0f / ((float) grid.cols);
        this.pageRelativePartHeight = 1.0f / ((float) grid.rows);
        this.partRenderWidth = Constants.PART_SIZE / this.pageRelativePartWidth;
        this.partRenderHeight = Constants.PART_SIZE / this.pageRelativePartHeight;
    }

    private void loadVisible() {
        int i;
        int parts = 0;
        float scaledPreloadOffset = ((float) this.preloadOffset) * this.pdfView.getZoom();
        float f = this.xOffset;
        float firstXOffset = (-f) + scaledPreloadOffset;
        float lastXOffset = ((-f) - ((float) this.pdfView.getWidth())) - scaledPreloadOffset;
        float f2 = this.yOffset;
        float firstYOffset = (-f2) + scaledPreloadOffset;
        float lastYOffset = ((-f2) - ((float) this.pdfView.getHeight())) - scaledPreloadOffset;
        getPageAndCoordsByOffset(this.firstHolder, this.firstGrid, firstXOffset, firstYOffset, false);
        getPageAndCoordsByOffset(this.lastHolder, this.lastGrid, lastXOffset, lastYOffset, true);
        for (int i2 = this.firstHolder.page; i2 <= this.lastHolder.page; i2++) {
            loadThumbnail(i2);
        }
        int pagesCount = (this.lastHolder.page - this.firstHolder.page) + 1;
        for (int page = this.firstHolder.page; page <= this.lastHolder.page && parts < Cache.CACHE_SIZE; page++) {
            if (page == this.firstHolder.page && pagesCount > 1) {
                i = loadPageEnd(this.firstHolder, this.firstGrid, Cache.CACHE_SIZE - parts);
            } else if (page == this.lastHolder.page && pagesCount > 1) {
                i = loadPageStart(this.lastHolder, this.lastGrid, Cache.CACHE_SIZE - parts);
            } else if (pagesCount == 1) {
                i = loadPageCenter(this.firstHolder, this.lastHolder, this.firstGrid, Cache.CACHE_SIZE - parts);
            } else {
                getPageColsRows(this.middleGrid, page);
                i = loadWholePage(page, this.middleGrid, Cache.CACHE_SIZE - parts);
            }
            parts += i;
        }
    }

    private int loadWholePage(int page, GridSize grid, int nbOfPartsLoadable) {
        calculatePartSize(grid);
        return loadPage(page, 0, grid.rows - 1, 0, grid.cols - 1, nbOfPartsLoadable);
    }

    private int loadPageCenter(Holder firstHolder2, Holder lastHolder2, GridSize grid, int nbOfPartsLoadable) {
        calculatePartSize(grid);
        return loadPage(firstHolder2.page, firstHolder2.row, lastHolder2.row, firstHolder2.col, lastHolder2.col, nbOfPartsLoadable);
    }

    private int loadPageEnd(Holder holder, GridSize grid, int nbOfPartsLoadable) {
        calculatePartSize(grid);
        if (this.pdfView.isSwipeVertical()) {
            int firstRow = holder.row;
            return loadPage(holder.page, firstRow, grid.rows - 1, 0, grid.cols - 1, nbOfPartsLoadable);
        }
        return loadPage(holder.page, 0, grid.rows - 1, holder.col, grid.cols - 1, nbOfPartsLoadable);
    }

    private int loadPageStart(Holder holder, GridSize grid, int nbOfPartsLoadable) {
        calculatePartSize(grid);
        if (this.pdfView.isSwipeVertical()) {
            int lastRow = holder.row;
            return loadPage(holder.page, 0, lastRow, 0, grid.cols - 1, nbOfPartsLoadable);
        }
        return loadPage(holder.page, 0, grid.rows - 1, 0, holder.col, nbOfPartsLoadable);
    }

    private int loadPage(int page, int firstRow, int lastRow, int firstCol, int lastCol, int nbOfPartsLoadable) {
        int loaded = 0;
        int row = firstRow;
        while (row <= lastRow) {
            int loaded2 = loaded;
            for (int col = firstCol; col <= lastCol; col++) {
                if (loadCell(page, row, col, this.pageRelativePartWidth, this.pageRelativePartHeight)) {
                    loaded2++;
                }
                if (loaded2 >= nbOfPartsLoadable) {
                    return loaded2;
                }
            }
            row++;
            loaded = loaded2;
        }
        return loaded;
    }

    private boolean loadCell(int page, int row, int col, float pageRelativePartWidth2, float pageRelativePartHeight2) {
        float relX = ((float) col) * pageRelativePartWidth2;
        float relY = ((float) row) * pageRelativePartHeight2;
        float relWidth = pageRelativePartWidth2;
        float relHeight = pageRelativePartHeight2;
        float renderWidth = this.partRenderWidth;
        float renderHeight = this.partRenderHeight;
        if (relX + relWidth > 1.0f) {
            relWidth = 1.0f - relX;
        }
        if (relY + relHeight > 1.0f) {
            relHeight = 1.0f - relY;
        }
        float renderWidth2 = renderWidth * relWidth;
        float renderHeight2 = renderHeight * relHeight;
        RectF pageRelativeBounds = new RectF(relX, relY, relX + relWidth, relY + relHeight);
        if (renderWidth2 <= 0.0f || renderHeight2 <= 0.0f) {
            return false;
        }
        if (!this.pdfView.cacheManager.upPartIfContained(page, pageRelativeBounds, this.cacheOrder)) {
            RenderingHandler renderingHandler = this.pdfView.renderingHandler;
            int i = this.cacheOrder;
            int i2 = i;
            RectF rectF = pageRelativeBounds;
            renderingHandler.addRenderingTask(page, renderWidth2, renderHeight2, pageRelativeBounds, false, i2, this.pdfView.isBestQuality(), this.pdfView.isAnnotationRendering());
        }
        this.cacheOrder++;
        return true;
    }

    private void loadThumbnail(int page) {
        SizeF pageSize = this.pdfView.pdfFile.getPageSize(page);
        float thumbnailWidth = pageSize.getWidth() * Constants.THUMBNAIL_RATIO;
        float thumbnailHeight = pageSize.getHeight() * Constants.THUMBNAIL_RATIO;
        if (!this.pdfView.cacheManager.containsThumbnail(page, this.thumbnailRect)) {
            this.pdfView.renderingHandler.addRenderingTask(page, thumbnailWidth, thumbnailHeight, this.thumbnailRect, true, 0, this.pdfView.isBestQuality(), this.pdfView.isAnnotationRendering());
        }
    }

    /* access modifiers changed from: 0000 */
    public void loadPages() {
        this.cacheOrder = 1;
        this.xOffset = -MathUtils.max(this.pdfView.getCurrentXOffset(), 0.0f);
        this.yOffset = -MathUtils.max(this.pdfView.getCurrentYOffset(), 0.0f);
        loadVisible();
    }
}
