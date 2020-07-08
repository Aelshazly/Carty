package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class LoximuthalProjection extends PseudoCylindricalProjection {
    private static final double EPS = 1.0E-8d;

    /* renamed from: FC */
    private static final double f1712FC = 0.9213177319235613d;

    /* renamed from: RP */
    private static final double f1713RP = 0.3183098861837907d;
    private double cosphi1 = Math.cos(this.phi1);
    private double phi1 = Math.toRadians(40.0d);
    private double tanphi1 = Math.tan((this.phi1 * 0.5d) + 0.7853981633974483d);

    public Double project(double lplam, double lpphi, Double out) {
        double x;
        double y = lpphi - this.phi1;
        if (y < EPS) {
            x = this.cosphi1 * lplam;
        } else {
            double x2 = (0.5d * lpphi) + 0.7853981633974483d;
            if (Math.abs(x2) < EPS || Math.abs(Math.abs(x2) - 1.5707963267948966d) < EPS) {
                x = 0.0d;
            } else {
                x = (lplam * y) / Math.log(Math.tan(x2) / this.tanphi1);
            }
        }
        out.f1626x = x;
        out.f1627y = y;
        return out;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        double longitude;
        double latitude = this.phi1 + xyy;
        if (Math.abs(xyy) < EPS) {
            longitude = xyx / this.cosphi1;
        } else {
            double d = (0.5d * xyy) + 0.7853981633974483d;
            double longitude2 = d;
            if (Math.abs(d) < EPS || Math.abs(Math.abs(xyx) - 1.5707963267948966d) < EPS) {
                longitude = 0.0d;
            } else {
                longitude = (Math.log(Math.tan(longitude2) / this.tanphi1) * xyx) / xyy;
            }
        }
        out.f1626x = longitude;
        out.f1627y = latitude;
        return out;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Loximuthal";
    }
}
