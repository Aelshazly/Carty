package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class MBTFPQProjection extends Projection {

    /* renamed from: C */
    private static final double f1715C = 1.7071067811865475d;
    private static final double EPS = 1.0E-7d;
    private static final double FXC = 0.3124597141037825d;
    private static final double FYC = 1.874758284622695d;
    private static final int NITER = 20;
    private static final double ONETOL = 1.000001d;

    /* renamed from: RC */
    private static final double f1716RC = 0.585786437626905d;
    private static final double RXC = 3.2004125807650623d;
    private static final double RYC = 0.533402096794177d;

    public Double project(double lplam, double lpphi, Double out) {
        Double doubleR = out;
        double c = Math.sin(lpphi) * f1715C;
        for (int i = 20; i > 0; i--) {
            double sin = ((Math.sin(lpphi * 0.5d) + Math.sin(lpphi)) - c) / ((Math.cos(lpphi * 0.5d) * 0.5d) + Math.cos(lpphi));
            double th1 = sin;
            doubleR.f1627y -= sin;
            if (Math.abs(th1) < EPS) {
                break;
            }
        }
        doubleR.f1626x = FXC * lplam * (((Math.cos(lpphi) * 2.0d) / Math.cos(lpphi * 0.5d)) + 1.0d);
        doubleR.f1627y = Math.sin(0.5d * lpphi) * FYC;
        return doubleR;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        double lpphi;
        double t;
        double lpphi2;
        Double doubleR = out;
        double lpphi3 = RYC * xyy;
        String str = "I";
        if (Math.abs(lpphi3) <= 1.0d) {
            t = lpphi3;
            lpphi = Math.asin(lpphi3) * 2.0d;
        } else if (Math.abs(lpphi3) > ONETOL) {
            throw new ProjectionException(str);
        } else if (lpphi3 < 0.0d) {
            t = -1.0d;
            lpphi = -3.141592653589793d;
        } else {
            t = 1.0d;
            lpphi = 3.141592653589793d;
        }
        doubleR.f1626x = (RXC * xyx) / (((Math.cos(lpphi) * 2.0d) / Math.cos(0.5d * lpphi)) + 1.0d);
        double lpphi4 = (Math.sin(lpphi) + t) * f1716RC;
        if (Math.abs(lpphi4) <= 1.0d) {
            lpphi2 = Math.asin(lpphi4);
        } else if (Math.abs(lpphi4) <= ONETOL) {
            lpphi2 = lpphi4 < 0.0d ? -1.5707963267948966d : 1.5707963267948966d;
        } else {
            throw new ProjectionException(str);
        }
        doubleR.f1627y = lpphi2;
        return doubleR;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "McBryde-Thomas Flat-Polar Quartic";
    }
}
