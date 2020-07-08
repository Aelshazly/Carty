package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class ObliqueMercatorProjection extends Projection {
    private static final double TOL = 1.0E-7d;
    private double Gamma;

    /* renamed from: al */
    private double f1723al;
    private double alpha;

    /* renamed from: bl */
    private double f1724bl;
    private double cosgam;
    private double cosrot;

    /* renamed from: el */
    private double f1725el;
    private boolean ellips;
    private double lam1;
    private double lam2;
    private double lamc;
    private double phi1;
    private double phi2;
    private boolean rot;
    private double singam;
    private double sinrot;
    private double u_0;

    public ObliqueMercatorProjection() {
        this.ellipsoid = Ellipsoid.WGS_1984;
        this.projectionLatitude = Math.toRadians(0.0d);
        this.projectionLongitude = Math.toRadians(0.0d);
        this.minLongitude = Math.toRadians(-60.0d);
        this.maxLongitude = Math.toRadians(60.0d);
        this.minLatitude = Math.toRadians(-80.0d);
        this.maxLatitude = Math.toRadians(80.0d);
        this.alpha = Math.toRadians(-45.0d);
        initialize();
    }

    public ObliqueMercatorProjection(Ellipsoid ellipsoid, double lon_0, double lat_0, double alpha2, double k, double x_0, double y_0) {
        setEllipsoid(ellipsoid);
        this.lamc = lon_0;
        this.projectionLatitude = lat_0;
        this.alpha = alpha2;
        this.scaleFactor = k;
        this.falseEasting = x_0;
        this.falseNorthing = y_0;
        initialize();
    }

    public void initialize() {
        double d;
        double f;
        double l;
        double h;
        double d2;
        double con;
        double f2;
        double f3;
        super.initialize();
        boolean z = true;
        this.rot = true;
        if (1 == 0) {
            if (Math.abs(this.phi1 - this.phi2) > TOL) {
                double abs = Math.abs(this.phi1);
                double con2 = abs;
                if (abs > TOL) {
                    if (Math.abs(con2 - 1.5707963267948966d) > TOL) {
                        if (Math.abs(Math.abs(this.projectionLatitude) - 1.5707963267948966d) > TOL) {
                            if (Math.abs(Math.abs(this.phi2) - 1.5707963267948966d) <= TOL) {
                            }
                        }
                    }
                }
            }
            throw new ProjectionException("Obl 2");
        } else if (Math.abs(this.alpha) <= TOL || Math.abs(Math.abs(this.projectionLatitude) - 1.5707963267948966d) <= TOL || Math.abs(Math.abs(this.alpha) - 1.5707963267948966d) <= TOL) {
            throw new ProjectionException("Obl 1");
        }
        if (this.f1736es != 0.0d) {
            z = false;
        }
        this.spherical = z;
        double com2 = z ? 1.0d : Math.sqrt(this.one_es);
        if (Math.abs(this.projectionLatitude) > 1.0E-10d) {
            double sinphi0 = Math.sin(this.projectionLatitude);
            double cosphi0 = Math.cos(this.projectionLatitude);
            if (!this.spherical) {
                double con3 = 1.0d - ((this.f1736es * sinphi0) * sinphi0);
                this.f1724bl = cosphi0 * cosphi0;
                double d3 = this.f1736es;
                double d4 = this.f1724bl;
                this.f1724bl = Math.sqrt((((d3 * d4) * d4) / this.one_es) + 1.0d);
                this.f1723al = ((this.f1724bl * this.scaleFactor) * com2) / con3;
                d2 = (this.f1724bl * com2) / (Math.sqrt(con3) * cosphi0);
                con = 1.0d;
            } else {
                con = 1.0d;
                this.f1724bl = 1.0d;
                this.f1723al = this.scaleFactor;
                d2 = 1.0d / cosphi0;
            }
            double d5 = (d2 * d2) - con;
            double f4 = d5;
            if (d5 <= 0.0d) {
                f2 = 0.0d;
            } else {
                f2 = Math.sqrt(f4);
                if (this.projectionLatitude < 0.0d) {
                    f2 = -f2;
                }
            }
            double d6 = f2 + d2;
            double f5 = d6;
            this.f1725el = d6;
            if (!this.spherical) {
                f3 = f5;
                d = d2;
                this.f1725el *= Math.pow(MapMath.tsfn(this.projectionLatitude, sinphi0, this.f1735e), this.f1724bl);
            } else {
                f3 = f5;
                d = d2;
                this.f1725el *= Math.tan((1.5707963267948966d - this.projectionLatitude) * 0.5d);
            }
            f = f3;
        } else {
            this.f1724bl = 1.0d / com2;
            this.f1723al = this.scaleFactor;
            f = 1.0d;
            this.f1725el = 1.0d;
            d = 1.0d;
        }
        if (1 != 0) {
            this.Gamma = Math.asin(Math.sin(this.alpha) / d);
            this.projectionLongitude = this.lamc - (Math.asin(((f - (1.0d / f)) * 0.5d) * Math.tan(this.Gamma)) / this.f1724bl);
        } else {
            if (!this.spherical) {
                double d7 = this.phi1;
                h = Math.pow(MapMath.tsfn(d7, Math.sin(d7), this.f1735e), this.f1724bl);
                double d8 = this.phi2;
                l = Math.pow(MapMath.tsfn(d8, Math.sin(d8), this.f1735e), this.f1724bl);
            } else {
                h = Math.tan((1.5707963267948966d - this.phi1) * 0.5d);
                l = Math.tan((1.5707963267948966d - this.phi2) * 0.5d);
            }
            double d9 = this.f1725el;
            double f6 = d9 / h;
            double p = (l - h) / (l + h);
            double j = d9 * d9;
            double j2 = (j - (l * h)) / (j + (l * h));
            double j3 = this.lam1;
            double d10 = h;
            double d11 = this.lam2;
            double d12 = j3 - d11;
            double con4 = d12;
            if (d12 < -3.141592653589793d) {
                this.lam2 = d11 - 6.283185307179586d;
            } else if (con4 > 3.141592653589793d) {
                this.lam2 = d11 + 6.283185307179586d;
            }
            double d13 = this.lam1;
            double d14 = this.lam2;
            double d15 = l;
            this.projectionLongitude = MapMath.normalizeLongitude(((d13 + d14) * 0.5d) - (Math.atan((Math.tan((this.f1724bl * 0.5d) * (d13 - d14)) * j2) / p) / this.f1724bl));
            this.Gamma = Math.atan((Math.sin(this.f1724bl * MapMath.normalizeLongitude(this.lam1 - this.projectionLongitude)) * 2.0d) / (f6 - (1.0d / f6)));
            this.alpha = Math.asin(Math.sin(this.Gamma) * d);
        }
        this.singam = Math.sin(this.Gamma);
        this.cosgam = Math.cos(this.Gamma);
        double f7 = this.alpha;
        this.sinrot = Math.sin(f7);
        this.cosrot = Math.cos(f7);
        this.u_0 = Math.abs((this.f1723al * Math.atan(Math.sqrt((d * d) - 1.0d) / this.cosrot)) / this.f1724bl);
        if (this.projectionLatitude < 0.0d) {
            this.u_0 = -this.u_0;
        }
    }

    public Double project(double lam, double phi, Double xy) {
        double ul;
        double us;
        double d;
        double d2;
        Double doubleR = xy;
        double vl = Math.sin(this.f1724bl * lam);
        if (Math.abs(Math.abs(phi) - 1.5707963267948966d) <= 1.0E-10d) {
            us = (this.f1723al * phi) / this.f1724bl;
            ul = phi < 0.0d ? -this.singam : this.singam;
        } else {
            double d3 = this.f1725el;
            if (!this.spherical) {
                d = d3;
                d2 = Math.pow(MapMath.tsfn(phi, Math.sin(phi), this.f1735e), this.f1724bl);
            } else {
                d = d3;
                d2 = Math.tan((1.5707963267948966d - phi) * 0.5d);
            }
            double q = d / d2;
            double s = (q - (1.0d / q)) * 0.5d;
            double ul2 = (((this.singam * s) - (this.cosgam * vl)) * 2.0d) / ((1.0d / q) + q);
            double con = Math.cos(this.f1724bl * lam);
            if (Math.abs(con) >= TOL) {
                double d4 = s;
                double atan = this.f1723al * Math.atan(((this.cosgam * s) + (this.singam * vl)) / con);
                double d5 = this.f1724bl;
                double us2 = atan / d5;
                if (con < 0.0d) {
                    ul = ul2;
                    us = ((this.f1723al * 3.141592653589793d) / d5) + us2;
                } else {
                    ul = ul2;
                    us = us2;
                }
            } else {
                ul = ul2;
                us = this.f1723al * this.f1724bl * lam;
            }
        }
        if (Math.abs(Math.abs(ul) - 1.0d) > 1.0E-10d) {
            double vs = ((this.f1723al * 0.5d) * Math.log((1.0d - ul) / (ul + 1.0d))) / this.f1724bl;
            double us3 = us - this.u_0;
            if (!this.rot) {
                doubleR.f1626x = us3;
                doubleR.f1627y = vs;
            } else {
                double d6 = this.cosrot;
                double d7 = vs * d6;
                double d8 = this.sinrot;
                doubleR.f1626x = d7 + (us3 * d8);
                doubleR.f1627y = (d6 * us3) - (d8 * vs);
            }
            return doubleR;
        }
        throw new ProjectionException("Obl 3");
    }

    public Double projectInverse(double x, double y, Double lp) {
        double vs;
        double us;
        Double doubleR = lp;
        if (!this.rot) {
            us = x;
            vs = y;
        } else {
            double us2 = this.cosrot;
            double d = x * us2;
            double d2 = this.sinrot;
            vs = d - (y * d2);
            us = (us2 * y) + (d2 * x);
        }
        double us3 = us + this.u_0;
        double q = Math.exp(((-this.f1724bl) * vs) / this.f1723al);
        double s = (q - (1.0d / q)) * 0.5d;
        double vl = Math.sin((this.f1724bl * us3) / this.f1723al);
        double ul = (((this.cosgam * vl) + (this.singam * s)) * 2.0d) / (q + (1.0d / q));
        double d3 = 1.5707963267948966d;
        if (Math.abs(Math.abs(ul) - 1.0d) < 1.0E-10d) {
            doubleR.f1626x = 0.0d;
            if (ul < 0.0d) {
                d3 = -1.5707963267948966d;
            }
            doubleR.f1627y = d3;
            double d4 = us3;
            double d5 = vs;
        } else {
            doubleR.f1627y = this.f1725el / Math.sqrt((ul + 1.0d) / (1.0d - ul));
            if (!this.spherical) {
                double d6 = vs;
                doubleR.f1627y = MapMath.phi2(Math.pow(doubleR.f1627y, 1.0d / this.f1724bl), this.f1735e);
            } else {
                doubleR.f1627y = 1.5707963267948966d - (Math.atan(doubleR.f1627y) * 2.0d);
            }
            double d7 = us3;
            doubleR.f1626x = (-Math.atan2((this.cosgam * s) - (this.singam * vl), Math.cos((this.f1724bl * us3) / this.f1723al))) / this.f1724bl;
        }
        return doubleR;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Oblique Mercator";
    }
}
