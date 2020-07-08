package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class Eckert2Projection extends Projection {
    private static final double C13 = 0.3333333333333333d;
    private static final double FXC = 0.46065886596178063d;
    private static final double FYC = 1.4472025091165353d;
    private static final double ONEEPS = 1.0000001d;

    public Double project(double lplam, double lpphi, Double out) {
        double d = FXC * lplam;
        double sqrt = Math.sqrt(4.0d - (Math.sin(Math.abs(lpphi)) * 3.0d));
        out.f1627y = sqrt;
        out.f1626x = d * sqrt;
        out.f1627y = (2.0d - out.f1627y) * FYC;
        if (lpphi < 0.0d) {
            out.f1627y = -out.f1627y;
        }
        return out;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        double abs = 2.0d - (Math.abs(xyy) / FYC);
        out.f1627y = abs;
        out.f1626x = xyx / (abs * FXC);
        out.f1627y = (4.0d - (out.f1627y * out.f1627y)) * C13;
        if (Math.abs(out.f1627y) < 1.0d) {
            out.f1627y = Math.asin(out.f1627y);
        } else if (Math.abs(out.f1627y) <= ONEEPS) {
            out.f1627y = out.f1627y < 0.0d ? -1.5707963267948966d : 1.5707963267948966d;
        } else {
            throw new ProjectionException("I");
        }
        if (xyy < 0.0d) {
            out.f1627y = -out.f1627y;
        }
        return out;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Eckert II";
    }
}
