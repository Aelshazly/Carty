package com.github.barteksc.pdfviewer.util;

import com.shockwave.pdfium.util.Size;
import com.shockwave.pdfium.util.SizeF;

public class PageSizeCalculator {
    private FitPolicy fitPolicy;
    private float heightRatio;
    private SizeF optimalMaxHeightPageSize;
    private SizeF optimalMaxWidthPageSize;
    private final Size originalMaxHeightPageSize;
    private final Size originalMaxWidthPageSize;
    private final Size viewSize;
    private float widthRatio;

    /* renamed from: com.github.barteksc.pdfviewer.util.PageSizeCalculator$1 */
    static /* synthetic */ class C05391 {
        static final /* synthetic */ int[] $SwitchMap$com$github$barteksc$pdfviewer$util$FitPolicy = new int[FitPolicy.values().length];

        static {
            try {
                $SwitchMap$com$github$barteksc$pdfviewer$util$FitPolicy[FitPolicy.HEIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$github$barteksc$pdfviewer$util$FitPolicy[FitPolicy.BOTH.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public PageSizeCalculator(FitPolicy fitPolicy2, Size originalMaxWidthPageSize2, Size originalMaxHeightPageSize2, Size viewSize2) {
        this.fitPolicy = fitPolicy2;
        this.originalMaxWidthPageSize = originalMaxWidthPageSize2;
        this.originalMaxHeightPageSize = originalMaxHeightPageSize2;
        this.viewSize = viewSize2;
        calculateMaxPages();
    }

    public SizeF calculate(Size pageSize) {
        if (pageSize.getWidth() <= 0 || pageSize.getHeight() <= 0) {
            return new SizeF(0.0f, 0.0f);
        }
        int i = C05391.$SwitchMap$com$github$barteksc$pdfviewer$util$FitPolicy[this.fitPolicy.ordinal()];
        if (i == 1) {
            return fitHeight(pageSize, ((float) pageSize.getHeight()) * this.heightRatio);
        }
        if (i != 2) {
            return fitWidth(pageSize, ((float) pageSize.getWidth()) * this.widthRatio);
        }
        return fitBoth(pageSize, ((float) pageSize.getWidth()) * this.widthRatio, ((float) pageSize.getHeight()) * this.heightRatio);
    }

    public SizeF getOptimalMaxWidthPageSize() {
        return this.optimalMaxWidthPageSize;
    }

    public SizeF getOptimalMaxHeightPageSize() {
        return this.optimalMaxHeightPageSize;
    }

    private void calculateMaxPages() {
        int i = C05391.$SwitchMap$com$github$barteksc$pdfviewer$util$FitPolicy[this.fitPolicy.ordinal()];
        if (i == 1) {
            this.optimalMaxHeightPageSize = fitHeight(this.originalMaxHeightPageSize, (float) this.viewSize.getHeight());
            this.heightRatio = this.optimalMaxHeightPageSize.getHeight() / ((float) this.originalMaxHeightPageSize.getHeight());
            Size size = this.originalMaxWidthPageSize;
            this.optimalMaxWidthPageSize = fitHeight(size, ((float) size.getHeight()) * this.heightRatio);
        } else if (i != 2) {
            this.optimalMaxWidthPageSize = fitWidth(this.originalMaxWidthPageSize, (float) this.viewSize.getWidth());
            this.widthRatio = this.optimalMaxWidthPageSize.getWidth() / ((float) this.originalMaxWidthPageSize.getWidth());
            Size size2 = this.originalMaxHeightPageSize;
            this.optimalMaxHeightPageSize = fitWidth(size2, ((float) size2.getWidth()) * this.widthRatio);
        } else {
            float localWidthRatio = fitBoth(this.originalMaxWidthPageSize, (float) this.viewSize.getWidth(), (float) this.viewSize.getHeight()).getWidth() / ((float) this.originalMaxWidthPageSize.getWidth());
            Size size3 = this.originalMaxHeightPageSize;
            this.optimalMaxHeightPageSize = fitBoth(size3, ((float) size3.getWidth()) * localWidthRatio, (float) this.viewSize.getHeight());
            this.heightRatio = this.optimalMaxHeightPageSize.getHeight() / ((float) this.originalMaxHeightPageSize.getHeight());
            this.optimalMaxWidthPageSize = fitBoth(this.originalMaxWidthPageSize, (float) this.viewSize.getWidth(), ((float) this.originalMaxWidthPageSize.getHeight()) * this.heightRatio);
            this.widthRatio = this.optimalMaxWidthPageSize.getWidth() / ((float) this.originalMaxWidthPageSize.getWidth());
        }
    }

    private SizeF fitWidth(Size pageSize, float maxWidth) {
        return new SizeF(maxWidth, (float) Math.floor((double) (maxWidth / (((float) pageSize.getWidth()) / ((float) pageSize.getHeight())))));
    }

    private SizeF fitHeight(Size pageSize, float maxHeight) {
        return new SizeF((float) Math.floor((double) (maxHeight / (((float) pageSize.getHeight()) / ((float) pageSize.getWidth())))), maxHeight);
    }

    private SizeF fitBoth(Size pageSize, float maxWidth, float maxHeight) {
        float ratio = ((float) pageSize.getWidth()) / ((float) pageSize.getHeight());
        float w = maxWidth;
        float h = (float) Math.floor((double) (maxWidth / ratio));
        if (h > maxHeight) {
            h = maxHeight;
            w = (float) Math.floor((double) (maxHeight * ratio));
        }
        return new SizeF(w, h);
    }
}
