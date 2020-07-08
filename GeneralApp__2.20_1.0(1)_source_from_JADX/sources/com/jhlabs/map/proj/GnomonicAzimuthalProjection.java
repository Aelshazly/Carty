package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class GnomonicAzimuthalProjection extends AzimuthalProjection {
    public GnomonicAzimuthalProjection() {
        this(Math.toRadians(90.0d), Math.toRadians(0.0d));
    }

    public GnomonicAzimuthalProjection(double projectionLatitude, double projectionLongitude) {
        super(projectionLatitude, projectionLongitude);
        this.minLatitude = Math.toRadians(0.0d);
        this.maxLatitude = Math.toRadians(90.0d);
        initialize();
    }

    public void initialize() {
        super.initialize();
    }

    public Double project(double lam, double phi, Double xy) {
        Double doubleR = xy;
        double sinphi = Math.sin(phi);
        double cosphi = Math.cos(phi);
        double coslam = Math.cos(lam);
        int i = this.mode;
        if (i == 1) {
            doubleR.f1627y = sinphi;
        } else if (i == 2) {
            doubleR.f1627y = -sinphi;
        } else if (i == 3) {
            doubleR.f1627y = cosphi * coslam;
        } else if (i == 4) {
            doubleR.f1627y = (this.sinphi0 * sinphi) + (this.cosphi0 * cosphi * coslam);
        }
        if (Math.abs(doubleR.f1627y) > 1.0E-10d) {
            double d = 1.0d / doubleR.f1627y;
            doubleR.f1627y = d;
            doubleR.f1626x = d * cosphi * Math.sin(lam);
            int i2 = this.mode;
            if (i2 == 1) {
                coslam = -coslam;
            } else if (i2 != 2) {
                if (i2 == 3) {
                    doubleR.f1627y *= sinphi;
                } else if (i2 == 4) {
                    doubleR.f1627y *= (this.cosphi0 * sinphi) - ((this.sinphi0 * cosphi) * coslam);
                }
                return doubleR;
            }
            doubleR.f1627y *= cosphi * coslam;
            return doubleR;
        }
        throw new ProjectionException();
    }

    public Double projectInverse(double x, double y, Double lp) {
        double y2;
        double x2 = y;
        Double doubleR = lp;
        double rh = MapMath.distance(x, y);
        double atan = Math.atan(rh);
        doubleR.f1627y = atan;
        double sinz = Math.sin(atan);
        double cosz = Math.sqrt(1.0d - (sinz * sinz));
        if (Math.abs(rh) <= 1.0E-10d) {
            doubleR.f1627y = this.projectionLatitude;
            doubleR.f1626x = 0.0d;
            double d = x;
        } else {
            int i = this.mode;
            if (i != 1) {
                if (i == 2) {
                    doubleR.f1627y -= 1.5707963267948966d;
                } else if (i == 3) {
                    doubleR.f1627y = (x2 * sinz) / rh;
                    if (Math.abs(doubleR.f1627y) >= 1.0d) {
                        doubleR.f1627y = doubleR.f1627y > 0.0d ? 1.5707963267948966d : -1.5707963267948966d;
                    } else {
                        doubleR.f1627y = Math.asin(doubleR.f1627y);
                    }
                    x2 = cosz * rh;
                    y2 = x * sinz;
                } else if (i == 4) {
                    doubleR.f1627y = (this.sinphi0 * cosz) + (((x2 * sinz) * this.cosphi0) / rh);
                    if (Math.abs(doubleR.f1627y) >= 1.0d) {
                        doubleR.f1627y = doubleR.f1627y > 0.0d ? 1.5707963267948966d : -1.5707963267948966d;
                    } else {
                        doubleR.f1627y = Math.asin(doubleR.f1627y);
                    }
                    x2 = (cosz - (this.sinphi0 * Math.sin(doubleR.f1627y))) * rh;
                    y2 = this.cosphi0 * sinz * x;
                }
                y2 = x;
            } else {
                doubleR.f1627y = 1.5707963267948966d - doubleR.f1627y;
                x2 = -x2;
                y2 = x;
            }
            doubleR.f1626x = Math.atan2(y2, x2);
        }
        return doubleR;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Gnomonic Azimuthal";
    }
}
