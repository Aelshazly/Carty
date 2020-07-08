package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class BonneProjection extends Projection {
    private double am1;
    private double cphi1;

    /* renamed from: en */
    private double[] f1643en;

    /* renamed from: m1 */
    private double f1644m1;
    private double phi1;

    public Double project(double lplam, double lpphi, Double out) {
        Double doubleR = out;
        if (this.spherical) {
            double rh = (this.cphi1 + this.phi1) - lpphi;
            if (Math.abs(rh) > 1.0E-10d) {
                double cos = (Math.cos(lpphi) * lplam) / rh;
                double E = cos;
                doubleR.f1626x = Math.sin(cos) * rh;
                doubleR.f1627y = this.cphi1 - (Math.cos(E) * rh);
            } else {
                doubleR.f1627y = 0.0d;
                doubleR.f1626x = 0.0d;
            }
        } else {
            double d = this.am1 + this.f1644m1;
            double sin = Math.sin(lpphi);
            double E2 = sin;
            double cos2 = Math.cos(lpphi);
            double rh2 = d - MapMath.mlfn(lpphi, sin, cos2, this.f1643en);
            double E3 = (cos2 * lplam) / (Math.sqrt(1.0d - ((this.f1736es * E2) * E2)) * rh2);
            doubleR.f1626x = Math.sin(E3) * rh2;
            doubleR.f1627y = this.am1 - (Math.cos(E3) * rh2);
        }
        return doubleR;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        double d = xyx;
        Double doubleR = out;
        String str = "I";
        if (this.spherical) {
            double d2 = this.cphi1 - xyy;
            doubleR.f1627y = d2;
            double rh = MapMath.distance(d, d2);
            doubleR.f1627y = (this.cphi1 + this.phi1) - rh;
            if (Math.abs(doubleR.f1627y) > 1.5707963267948966d) {
                throw new ProjectionException(str);
            } else if (Math.abs(Math.abs(doubleR.f1627y) - 1.5707963267948966d) <= 1.0E-10d) {
                doubleR.f1626x = 0.0d;
            } else {
                doubleR.f1626x = (Math.atan2(xyx, xyy) * rh) / Math.cos(doubleR.f1627y);
            }
        } else {
            double d3 = this.am1 - xyy;
            doubleR.f1627y = d3;
            double rh2 = MapMath.distance(d, d3);
            doubleR.f1627y = MapMath.inv_mlfn((this.am1 + this.f1644m1) - rh2, this.f1736es, this.f1643en);
            double abs = Math.abs(doubleR.f1627y);
            double s = abs;
            if (abs < 1.5707963267948966d) {
                double s2 = Math.sin(doubleR.f1627y);
                doubleR.f1626x = ((Math.atan2(xyx, xyy) * rh2) * Math.sqrt(1.0d - ((this.f1736es * s2) * s2))) / Math.cos(doubleR.f1627y);
            } else if (Math.abs(s - 1.5707963267948966d) <= 1.0E-10d) {
                doubleR.f1626x = 0.0d;
            } else {
                throw new ProjectionException(str);
            }
        }
        return doubleR;
    }

    public boolean isEqualArea() {
        return true;
    }

    public boolean hasInverse() {
        return true;
    }

    public void initialize() {
        super.initialize();
        this.phi1 = 1.5707963267948966d;
        if (Math.abs(this.phi1) < 1.0E-10d) {
            throw new ProjectionException("-23");
        } else if (!this.spherical) {
            this.f1643en = MapMath.enfn(this.f1736es);
            double d = this.phi1;
            double sin = Math.sin(d);
            this.am1 = sin;
            double cos = Math.cos(this.phi1);
            double c = cos;
            this.f1644m1 = MapMath.mlfn(d, sin, cos, this.f1643en);
            double d2 = this.f1736es;
            double d3 = this.am1;
            this.am1 = c / (Math.sqrt(1.0d - ((d2 * d3) * d3)) * this.am1);
        } else if (Math.abs(this.phi1) + 1.0E-10d >= 1.5707963267948966d) {
            this.cphi1 = 0.0d;
        } else {
            this.cphi1 = 1.0d / Math.tan(this.phi1);
        }
    }

    public String toString() {
        return "Bonne";
    }
}
