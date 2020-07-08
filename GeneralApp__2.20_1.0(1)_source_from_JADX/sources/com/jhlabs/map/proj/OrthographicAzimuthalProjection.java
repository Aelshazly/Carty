package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class OrthographicAzimuthalProjection extends AzimuthalProjection {
    public OrthographicAzimuthalProjection() {
        initialize();
    }

    public Double project(double lam, double phi, Double xy) {
        double cosphi = Math.cos(phi);
        double coslam = Math.cos(lam);
        int i = this.mode;
        if (i == 1) {
            coslam = -coslam;
        } else if (i != 2) {
            if (i == 3) {
                xy.f1627y = Math.sin(phi);
            } else if (i == 4) {
                xy.f1627y = (this.cosphi0 * Math.sin(phi)) - ((this.sinphi0 * cosphi) * coslam);
            }
            xy.f1626x = Math.sin(lam) * cosphi;
            return xy;
        }
        xy.f1627y = cosphi * coslam;
        xy.f1626x = Math.sin(lam) * cosphi;
        return xy;
    }

    public Double projectInverse(double x, double y, Double lp) {
        double x2;
        double d;
        double y2 = y;
        Double doubleR = lp;
        double distance = MapMath.distance(x, y);
        double rh = distance;
        double sinc = distance;
        if (distance > 1.0d) {
            if (sinc - 1.0d <= 1.0E-10d) {
                sinc = 1.0d;
            } else {
                throw new ProjectionException();
            }
        }
        double cosc = Math.sqrt(1.0d - (sinc * sinc));
        double d2 = -1.5707963267948966d;
        if (Math.abs(rh) <= 1.0E-10d) {
            doubleR.f1627y = this.projectionLatitude;
        } else {
            int i = this.mode;
            if (i == 1) {
                y2 = -y2;
                doubleR.f1627y = Math.acos(sinc);
                x2 = x;
            } else if (i == 2) {
                doubleR.f1627y = -Math.acos(sinc);
            } else if (i == 3) {
                doubleR.f1627y = (y2 * sinc) / rh;
                x2 = x * sinc;
                y2 = cosc * rh;
                if (Math.abs(doubleR.f1627y) >= 1.0d) {
                    doubleR.f1627y = doubleR.f1627y < 0.0d ? -1.5707963267948966d : 1.5707963267948966d;
                } else {
                    doubleR.f1627y = Math.asin(doubleR.f1627y);
                }
            } else if (i == 4) {
                doubleR.f1627y = (this.sinphi0 * cosc) + (((y2 * sinc) * this.cosphi0) / rh);
                y2 = (cosc - (this.sinphi0 * doubleR.f1627y)) * rh;
                x2 = this.cosphi0 * sinc * x;
                if (Math.abs(doubleR.f1627y) >= 1.0d) {
                    doubleR.f1627y = doubleR.f1627y < 0.0d ? -1.5707963267948966d : 1.5707963267948966d;
                } else {
                    doubleR.f1627y = Math.asin(doubleR.f1627y);
                }
            }
            if (y2 == 0.0d || !(this.mode == 4 || this.mode == 3)) {
                d2 = Math.atan2(x2, y2);
            } else {
                if (x2 == 0.0d) {
                    d = 0.0d;
                } else if (x2 >= 0.0d) {
                    d = 1.5707963267948966d;
                }
                doubleR.f1626x = d;
                return doubleR;
            }
            d = d2;
            doubleR.f1626x = d;
            return doubleR;
        }
        x2 = x;
        if (y2 == 0.0d) {
        }
        d2 = Math.atan2(x2, y2);
        d = d2;
        doubleR.f1626x = d;
        return doubleR;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Orthographic Azimuthal";
    }
}
