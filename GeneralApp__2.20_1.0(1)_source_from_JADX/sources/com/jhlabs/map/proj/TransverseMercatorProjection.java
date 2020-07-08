package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class TransverseMercatorProjection extends CylindricalProjection {
    private static final double FC1 = 1.0d;
    private static final double FC2 = 0.5d;
    private static final double FC3 = 0.16666666666666666d;
    private static final double FC4 = 0.08333333333333333d;
    private static final double FC5 = 0.05d;
    private static final double FC6 = 0.03333333333333333d;
    private static final double FC7 = 0.023809523809523808d;
    private static final double FC8 = 0.017857142857142856d;

    /* renamed from: en */
    private double[] f1747en;
    private double esp;
    private double ml0;

    public TransverseMercatorProjection() {
        this.ellipsoid = Ellipsoid.GRS_1980;
        this.projectionLatitude = Math.toRadians(0.0d);
        this.projectionLongitude = Math.toRadians(0.0d);
        this.minLongitude = Math.toRadians(-90.0d);
        this.maxLongitude = Math.toRadians(90.0d);
        initialize();
    }

    public TransverseMercatorProjection(Ellipsoid ellipsoid, double lon_0, double lat_0, double k, double x_0, double y_0) {
        setEllipsoid(ellipsoid);
        this.projectionLongitude = lon_0;
        this.projectionLatitude = lat_0;
        this.scaleFactor = k;
        this.falseEasting = x_0;
        this.falseNorthing = y_0;
        initialize();
    }

    public Object clone() {
        TransverseMercatorProjection p = (TransverseMercatorProjection) super.clone();
        double[] dArr = this.f1747en;
        if (dArr != null) {
            p.f1747en = (double[]) dArr.clone();
        }
        return p;
    }

    public boolean isRectilinear() {
        return false;
    }

    public void initialize() {
        super.initialize();
        if (this.spherical) {
            this.esp = this.scaleFactor;
            this.ml0 = this.esp * FC2;
            return;
        }
        this.f1747en = MapMath.enfn(this.f1736es);
        this.ml0 = MapMath.mlfn(this.projectionLatitude, Math.sin(this.projectionLatitude), Math.cos(this.projectionLatitude), this.f1747en);
        this.esp = this.f1736es / (1.0d - this.f1736es);
    }

    public int getRowFromNearestParallel(double latitude) {
        int degrees = (int) MapMath.radToDeg(MapMath.normalizeLatitude(latitude));
        if (degrees < -80 || degrees > 84) {
            return 0;
        }
        if (degrees > 80) {
            return 24;
        }
        return ((degrees + 80) / 8) + 3;
    }

    public int getZoneFromNearestMeridian(double longitude) {
        int zone = ((int) Math.floor(((MapMath.normalizeLongitude(longitude) + 3.141592653589793d) * 30.0d) / 3.141592653589793d)) + 1;
        if (zone < 1) {
            return 1;
        }
        if (zone > 60) {
            return 60;
        }
        return zone;
    }

    public void setUTMZone(int zone) {
        this.projectionLongitude = (((((double) (zone - 1)) + FC2) * 3.141592653589793d) / 30.0d) - 3.141592653589793d;
        this.projectionLatitude = 0.0d;
        this.scaleFactor = 0.9996d;
        this.falseEasting = 500000.0d;
        initialize();
    }

    public Double project(double lplam, double lpphi, Double xy) {
        Double doubleR = xy;
        double d = 0.0d;
        if (this.spherical) {
            double cosphi = Math.cos(lpphi);
            double b = Math.sin(lplam) * cosphi;
            doubleR.f1626x = this.ml0 * this.scaleFactor * Math.log((b + 1.0d) / (1.0d - b));
            double ty = MapMath.acos((Math.cos(lplam) * cosphi) / Math.sqrt(1.0d - (b * b)));
            if (lpphi < 0.0d) {
                ty = -ty;
            }
            doubleR.f1627y = this.esp * (ty - this.projectionLatitude);
        } else {
            double sinphi = Math.sin(lpphi);
            double cosphi2 = Math.cos(lpphi);
            if (Math.abs(cosphi2) > 1.0E-10d) {
                d = sinphi / cosphi2;
            }
            double t = d;
            double t2 = t * t;
            double al = cosphi2 * lplam;
            double als = al * al;
            double al2 = al / Math.sqrt(1.0d - ((this.f1736es * sinphi) * sinphi));
            double n = this.esp * cosphi2 * cosphi2;
            doubleR.f1626x = this.scaleFactor * al2 * ((FC3 * als * ((1.0d - t2) + n + (FC5 * als * (((t2 - 18.0d) * t2) + 5.0d + ((14.0d - (t2 * 58.0d)) * n) + (FC7 * als * (((((179.0d - t2) * t2) - 479.0d) * t2) + 61.0d)))))) + 1.0d);
            doubleR.f1627y = this.scaleFactor * ((MapMath.mlfn(lpphi, sinphi, cosphi2, this.f1747en) - this.ml0) + (sinphi * al2 * lplam * FC2 * ((FC4 * als * ((5.0d - t2) + (((4.0d * n) + 9.0d) * n) + (FC6 * als * (((t2 - 58.0d) * t2) + 61.0d + ((270.0d - (330.0d * t2)) * n) + (FC8 * als * (((((543.0d - t2) * t2) - 3111.0d) * t2) + 1385.0d)))))) + 1.0d)));
        }
        return doubleR;
    }

    public Double projectInverse(double x, double y, Double out) {
        Double doubleR = out;
        double t = 0.0d;
        if (this.spherical) {
            double h = Math.exp(x / this.scaleFactor);
            double g = (h - (1.0d / h)) * FC2;
            double h2 = Math.cos(this.projectionLatitude + (y / this.scaleFactor));
            doubleR.f1627y = MapMath.asin(Math.sqrt((1.0d - (h2 * h2)) / ((g * g) + 1.0d)));
            if (y < 0.0d) {
                doubleR.f1627y = -doubleR.f1627y;
            }
            doubleR.f1626x = Math.atan2(g, h2);
        } else {
            doubleR.f1627y = MapMath.inv_mlfn(this.ml0 + (y / this.scaleFactor), this.f1736es, this.f1747en);
            double d = 1.5707963267948966d;
            if (Math.abs(y) >= 1.5707963267948966d) {
                if (y < 0.0d) {
                    d = -1.5707963267948966d;
                }
                doubleR.f1627y = d;
                doubleR.f1626x = 0.0d;
            } else {
                double sinphi = Math.sin(doubleR.f1627y);
                double cosphi = Math.cos(doubleR.f1627y);
                if (Math.abs(cosphi) > 1.0E-10d) {
                    t = sinphi / cosphi;
                }
                double n = this.esp * cosphi * cosphi;
                double d2 = 1.0d - ((this.f1736es * sinphi) * sinphi);
                double con = d2;
                double d3 = (Math.sqrt(d2) * x) / this.scaleFactor;
                double con2 = con * t;
                double t2 = t * t;
                double ds = d3 * d3;
                double d4 = sinphi;
                double cosphi2 = cosphi;
                doubleR.f1627y -= (((con2 * ds) / (1.0d - this.f1736es)) * FC2) * (1.0d - ((FC4 * ds) * (((((3.0d - (9.0d * n)) * t2) + 5.0d) + ((1.0d - (4.0d * n)) * n)) - ((FC6 * ds) * ((((((90.0d - (252.0d * n)) + (45.0d * t2)) * t2) + 61.0d) + (46.0d * n)) - ((FC8 * ds) * ((((((1574.0d * t2) + 4095.0d) * t2) + 3633.0d) * t2) + 1385.0d)))))));
                doubleR.f1626x = ((1.0d - ((FC3 * ds) * ((((2.0d * t2) + 1.0d) + n) - ((FC5 * ds) * (((((((24.0d * t2) + 28.0d) + (8.0d * n)) * t2) + 5.0d) + (6.0d * n)) - ((FC7 * ds) * ((((((720.0d * t2) + 1320.0d) * t2) + 662.0d) * t2) + 61.0d))))))) * d3) / cosphi2;
            }
        }
        return doubleR;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Transverse Mercator";
    }
}
