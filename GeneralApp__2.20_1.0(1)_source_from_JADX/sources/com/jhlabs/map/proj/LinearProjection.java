package com.jhlabs.map.proj;

import java.awt.geom.Point2D.Double;

public class LinearProjection extends Projection {
    public Double transform(Double src, Double dst) {
        dst.x = src.x;
        dst.y = src.y;
        return dst;
    }

    public void transform(double[] srcPoints, int srcOffset, double[] dstPoints, int dstOffset, int numPoints) {
        for (int i = 0; i < numPoints; i++) {
            int dstOffset2 = dstOffset + 1;
            int srcOffset2 = srcOffset + 1;
            dstPoints[dstOffset] = srcPoints[srcOffset];
            dstOffset = dstOffset2 + 1;
            srcOffset = srcOffset2 + 1;
            dstPoints[dstOffset2] = srcPoints[srcOffset2];
        }
    }

    public Double inverseTransform(Double src, Double dst) {
        dst.x = src.x;
        dst.y = src.y;
        return dst;
    }

    public void inverseTransform(double[] srcPoints, int srcOffset, double[] dstPoints, int dstOffset, int numPoints) {
        for (int i = 0; i < numPoints; i++) {
            int dstOffset2 = dstOffset + 1;
            int srcOffset2 = srcOffset + 1;
            dstPoints[dstOffset] = srcPoints[srcOffset];
            dstOffset = dstOffset2 + 1;
            srcOffset = srcOffset2 + 1;
            dstPoints[dstOffset2] = srcPoints[srcOffset2];
        }
    }

    public boolean hasInverse() {
        return true;
    }

    public boolean isRectilinear() {
        return true;
    }

    public String toString() {
        return "Linear";
    }
}
