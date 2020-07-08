package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class CollignonProjection extends Projection {
    private static final double FXC = 1.1283791670955126d;
    private static final double FYC = 1.772453850905516d;
    private static final double ONEEPS = 1.0000001d;

    public Double project(double lplam, double lpphi, Double out) {
        double sin = 1.0d - Math.sin(lpphi);
        out.f1627y = sin;
        if (sin <= 0.0d) {
            out.f1627y = 0.0d;
        } else {
            out.f1627y = Math.sqrt(out.f1627y);
        }
        out.f1626x = FXC * lplam * out.f1627y;
        out.f1627y = (1.0d - out.f1627y) * FYC;
        return out;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        Double doubleR = out;
        double lpphi = (xyy / FYC) - 1.0d;
        double d = 1.0d - (lpphi * lpphi);
        doubleR.f1627y = d;
        if (Math.abs(d) < 1.0d) {
            doubleR.f1627y = Math.asin(lpphi);
        } else if (Math.abs(lpphi) <= ONEEPS) {
            doubleR.f1627y = lpphi < 0.0d ? -1.5707963267948966d : 1.5707963267948966d;
        } else {
            throw new ProjectionException("I");
        }
        double sin = 1.0d - Math.sin(lpphi);
        doubleR.f1626x = sin;
        if (sin <= 0.0d) {
            doubleR.f1626x = 0.0d;
        } else {
            doubleR.f1626x = xyx / (Math.sqrt(doubleR.f1626x) * FXC);
        }
        doubleR.f1627y = lpphi;
        return doubleR;
    }

    public boolean isEqualArea() {
        return true;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Collignon";
    }
}
