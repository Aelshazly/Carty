package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class HatanoProjection extends Projection {

    /* renamed from: CN */
    private static final double f1694CN = 2.67595d;

    /* renamed from: CS */
    private static final double f1695CS = 2.43763d;
    private static final double EPS = 1.0E-7d;
    private static final double FXC = 0.85d;
    private static final double FYCN = 1.75859d;
    private static final double FYCS = 1.93052d;
    private static final int NITER = 20;
    private static final double ONETOL = 1.000001d;
    private static final double RCN = 0.3736990601468637d;
    private static final double RCS = 0.4102345310814193d;
    private static final double RXC = 1.1764705882352942d;
    private static final double RYCN = 0.5686373742600607d;
    private static final double RYCS = 0.5179951515653813d;

    public Double project(double lplam, double lpphi, Double out) {
        Double doubleR = out;
        double c = Math.sin(lpphi) * (lpphi < 0.0d ? f1695CS : f1694CN);
        double lpphi2 = lpphi;
        for (int i = 20; i > 0; i--) {
            double sin = ((Math.sin(lpphi2) + lpphi2) - c) / (Math.cos(lpphi2) + 1.0d);
            lpphi2 -= sin;
            if (Math.abs(sin) < EPS) {
                break;
            }
        }
        double d = 0.5d * lpphi2;
        double lpphi3 = d;
        doubleR.f1626x = FXC * lplam * Math.cos(d);
        doubleR.f1627y = Math.sin(lpphi3) * (lpphi3 < 0.0d ? FYCS : FYCN);
        return doubleR;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        double th;
        Double doubleR = out;
        double th2 = (xyy < 0.0d ? RYCS : RYCN) * xyy;
        double d = 1.5707963267948966d;
        String str = "I";
        if (Math.abs(th2) <= 1.0d) {
            th = Math.asin(th2);
        } else if (Math.abs(th2) <= ONETOL) {
            th = th2 > 0.0d ? 1.5707963267948966d : -1.5707963267948966d;
        } else {
            throw new ProjectionException(str);
        }
        doubleR.f1626x = (RXC * xyx) / Math.cos(th);
        double th3 = th + th;
        doubleR.f1627y = (Math.sin(th3) + th3) * (xyy < 0.0d ? RCS : RCN);
        if (Math.abs(doubleR.f1627y) <= 1.0d) {
            doubleR.f1627y = Math.asin(doubleR.f1627y);
        } else if (Math.abs(doubleR.f1627y) <= ONETOL) {
            if (doubleR.f1627y <= 0.0d) {
                d = -1.5707963267948966d;
            }
            doubleR.f1627y = d;
        } else {
            throw new ProjectionException(str);
        }
        return doubleR;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Hatano Asymmetrical Equal Area";
    }
}
