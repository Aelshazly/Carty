package com.jhlabs.map.proj;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D.Double;

public class NullProjection extends Projection {
    public Double transform(Double src, Double dst) {
        dst.x = src.x;
        dst.y = src.y;
        return dst;
    }

    public Shape projectPath(Shape path, AffineTransform t, boolean filled) {
        if (t != null) {
            t.createTransformedShape(path);
        }
        return path;
    }

    public Shape getBoundingShape() {
        return null;
    }

    public boolean isRectilinear() {
        return true;
    }

    public String toString() {
        return "Null";
    }
}
