package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class Eckert4Projection extends Projection {
    private static final double C_p = 3.5707963267948966d;
    private static final double C_x = 0.4222382003157712d;
    private static final double C_y = 1.3265004281770023d;
    private static final double EPS = 1.0E-7d;
    private static final double RC_p = 0.2800495767557787d;
    private static final double RC_y = 0.7538633073600218d;
    private final int NITER = 6;

    public Double project(double lplam, double lpphi, Double out) {
        Double doubleR = out;
        double p = Math.sin(lpphi) * C_p;
        double V = lpphi * lpphi;
        double lpphi2 = ((((0.00826809d * V) + 0.0218849d) * V) + 0.895168d) * lpphi;
        int i = 6;
        while (i > 0) {
            double c = Math.cos(lpphi2);
            double s = Math.sin(lpphi2);
            double d = ((lpphi2 + ((c + 2.0d) * s)) - p) / ((((2.0d + c) * c) + 1.0d) - (s * s));
            lpphi2 -= d;
            if (Math.abs(d) < EPS) {
                break;
            }
            i--;
        }
        double c2 = C_y;
        if (i == 0) {
            doubleR.f1626x = lplam * C_x;
            if (lpphi2 < 0.0d) {
                c2 = -1.3265004281770023d;
            }
            doubleR.f1627y = c2;
        } else {
            doubleR.f1626x = C_x * lplam * (Math.cos(lpphi2) + 1.0d);
            doubleR.f1627y = Math.sin(lpphi2) * C_y;
        }
        return doubleR;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        out.f1627y = MapMath.asin(xyy / C_y);
        double cos = Math.cos(out.f1627y);
        double c = cos;
        out.f1626x = xyx / ((cos + 1.0d) * C_x);
        out.f1627y = MapMath.asin((out.f1627y + (Math.sin(out.f1627y) * (2.0d + c))) / C_p);
        return out;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Eckert IV";
    }
}
