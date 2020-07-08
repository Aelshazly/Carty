package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class LambertConformalConicProjection extends ConicProjection {

    /* renamed from: c */
    private double f1698c;

    /* renamed from: n */
    private double f1699n;
    private double rho0;

    public LambertConformalConicProjection() {
        this.minLatitude = Math.toRadians(0.0d);
        this.maxLatitude = Math.toRadians(80.0d);
        this.projectionLatitude = 0.7853981633974483d;
        this.projectionLatitude1 = 0.0d;
        this.projectionLatitude2 = 0.0d;
        initialize();
    }

    public LambertConformalConicProjection(Ellipsoid ellipsoid, double lon_0, double lat_1, double lat_2, double lat_0, double x_0, double y_0) {
        setEllipsoid(ellipsoid);
        this.projectionLongitude = lon_0;
        this.projectionLatitude = lat_0;
        this.scaleFactor = 1.0d;
        this.falseEasting = x_0;
        this.falseNorthing = y_0;
        this.projectionLatitude1 = lat_1;
        this.projectionLatitude2 = lat_2;
        initialize();
    }

    public Double project(double x, double y, Double out) {
        double rho;
        double d;
        if (Math.abs(Math.abs(y) - 1.5707963267948966d) < 1.0E-10d) {
            rho = 0.0d;
        } else {
            double rho2 = this.f1698c;
            if (this.spherical) {
                d = Math.pow(Math.tan((0.5d * y) + 0.7853981633974483d), -this.f1699n);
            } else {
                d = Math.pow(MapMath.tsfn(y, Math.sin(y), this.f1735e), this.f1699n);
            }
            rho = rho2 * d;
        }
        double d2 = this.f1699n * x;
        double x2 = d2;
        out.f1626x = this.scaleFactor * Math.sin(d2) * rho;
        out.f1627y = this.scaleFactor * (this.rho0 - (Math.cos(x2) * rho));
        return out;
    }

    public Double projectInverse(double x, double y, Double out) {
        Double doubleR = out;
        double x2 = x / this.scaleFactor;
        double d = this.rho0 - (y / this.scaleFactor);
        double y2 = d;
        double rho = MapMath.distance(x2, d);
        if (rho != 0.0d) {
            if (this.f1699n < 0.0d) {
                rho = -rho;
                x2 = -x2;
                y2 = -y2;
            }
            if (this.spherical) {
                doubleR.f1627y = (Math.atan(Math.pow(this.f1698c / rho, 1.0d / this.f1699n)) * 2.0d) - 1.5707963267948966d;
            } else {
                doubleR.f1627y = MapMath.phi2(Math.pow(rho / this.f1698c, 1.0d / this.f1699n), this.f1735e);
            }
            doubleR.f1626x = Math.atan2(x2, y2) / this.f1699n;
        } else {
            double d2 = 1.5707963267948966d;
            doubleR.f1626x = 0.0d;
            if (this.f1699n <= 0.0d) {
                d2 = -1.5707963267948966d;
            }
            doubleR.f1627y = d2;
        }
        return doubleR;
    }

    public void initialize() {
        double d;
        double d2;
        super.initialize();
        if (this.projectionLatitude1 == 0.0d) {
            double d3 = this.projectionLatitude;
            this.projectionLatitude2 = d3;
            this.projectionLatitude1 = d3;
        }
        if (Math.abs(this.projectionLatitude1 + this.projectionLatitude2) >= 1.0E-10d) {
            double sin = Math.sin(this.projectionLatitude1);
            double sinphi = sin;
            this.f1699n = sin;
            double cosphi = Math.cos(this.projectionLatitude1);
            boolean z = true;
            boolean secant = Math.abs(this.projectionLatitude1 - this.projectionLatitude2) >= 1.0E-10d;
            if (this.f1736es != 0.0d) {
                z = false;
            }
            this.spherical = z;
            if (!this.spherical) {
                double m1 = MapMath.msfn(sinphi, cosphi, this.f1736es);
                double ml1 = MapMath.tsfn(this.projectionLatitude1, sinphi, this.f1735e);
                if (secant) {
                    double sin2 = Math.sin(this.projectionLatitude2);
                    double sinphi2 = sin2;
                    this.f1699n = Math.log(m1 / MapMath.msfn(sin2, Math.cos(this.projectionLatitude2), this.f1736es));
                    this.f1699n /= Math.log(ml1 / MapMath.tsfn(this.projectionLatitude2, sinphi2, this.f1735e));
                }
                double pow = (Math.pow(ml1, -this.f1699n) * m1) / this.f1699n;
                this.rho0 = pow;
                this.f1698c = pow;
                double d4 = this.rho0;
                if (Math.abs(Math.abs(this.projectionLatitude) - 1.5707963267948966d) < 1.0E-10d) {
                    d2 = 0.0d;
                } else {
                    d2 = Math.pow(MapMath.tsfn(this.projectionLatitude, Math.sin(this.projectionLatitude), this.f1735e), this.f1699n);
                }
                this.rho0 = d4 * d2;
                return;
            }
            if (secant) {
                this.f1699n = Math.log(cosphi / Math.cos(this.projectionLatitude2)) / Math.log(Math.tan((this.projectionLatitude2 * 0.5d) + 0.7853981633974483d) / Math.tan((this.projectionLatitude1 * 0.5d) + 0.7853981633974483d));
            }
            this.f1698c = (Math.pow(Math.tan((this.projectionLatitude1 * 0.5d) + 0.7853981633974483d), this.f1699n) * cosphi) / this.f1699n;
            if (Math.abs(Math.abs(this.projectionLatitude) - 1.5707963267948966d) < 1.0E-10d) {
                d = 0.0d;
            } else {
                d = Math.pow(Math.tan((this.projectionLatitude * 0.5d) + 0.7853981633974483d), -this.f1699n) * this.f1698c;
            }
            this.rho0 = d;
            double d5 = sinphi;
            return;
        }
        throw new ProjectionException();
    }

    public boolean isConformal() {
        return true;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Lambert Conformal Conic";
    }
}
