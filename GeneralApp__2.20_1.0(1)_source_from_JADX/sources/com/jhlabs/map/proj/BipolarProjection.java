package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class BipolarProjection extends Projection {
    private static final double Azab = 0.8165004367468637d;
    private static final double Azba = 1.8226184385618593d;
    private static final double C20 = 0.9396926207859084d;
    private static final double C45 = 0.7071067811865476d;
    private static final double EPS = 1.0E-10d;
    private static final double EPS10 = 1.0E-10d;

    /* renamed from: F */
    private static final double f1640F = 1.8972474256746104d;
    private static final int NITER = 10;
    private static final double ONEEPS = 1.000000001d;
    private static final double R104 = 1.8151424220741028d;
    private static final double R110 = 1.9198621771937625d;
    private static final double S20 = -0.3420201433256687d;
    private static final double S45 = 0.7071067811865476d;

    /* renamed from: T */
    private static final double f1641T = 1.27246578267089d;
    private static final double cAzc = 0.6969152303867837d;
    private static final double lamB = -0.3489497672625068d;

    /* renamed from: n */
    private static final double f1642n = 0.6305584488127469d;
    private static final double rhoc = 1.2070912152156872d;
    private static final double sAzc = 0.7171535133114361d;
    private boolean noskew;

    public BipolarProjection() {
        this.minLatitude = Math.toRadians(-80.0d);
        this.maxLatitude = Math.toRadians(80.0d);
        this.projectionLongitude = Math.toRadians(-90.0d);
        this.minLongitude = Math.toRadians(-90.0d);
        this.maxLongitude = Math.toRadians(90.0d);
    }

    public Double project(double lplam, double lpphi, Double out) {
        double tphi;
        double Az;
        double z;
        double Av;
        double al;
        double al2;
        double z2;
        Double doubleR = out;
        double cphi = Math.cos(lpphi);
        double sphi = Math.sin(lpphi);
        double d = lamB - lplam;
        double sdlam = d;
        double cdlam = Math.cos(d);
        double sdlam2 = Math.sin(sdlam);
        if (Math.abs(Math.abs(lpphi) - 1.5707963267948966d) < 1.0E-10d) {
            Az = lpphi < 0.0d ? 3.141592653589793d : 0.0d;
            tphi = Double.MAX_VALUE;
        } else {
            tphi = sphi / cphi;
            Az = Math.atan2(sdlam2, (tphi - cdlam) * 0.7071067811865476d);
        }
        boolean z3 = Az > Azba;
        boolean tag = z3;
        double d2 = -1.0d;
        String str = "F";
        if (z3) {
            double d3 = lplam + R110;
            double sdlam3 = d3;
            double cdlam2 = Math.cos(d3);
            sdlam2 = Math.sin(sdlam3);
            double z4 = (sphi * S20) + (cphi * C20 * cdlam2);
            if (Math.abs(z4) <= 1.0d) {
                z = Math.acos(z4);
            } else if (Math.abs(z4) <= ONEEPS) {
                z = z4 < 0.0d ? -1.0d : 1.0d;
            } else {
                throw new ProjectionException(str);
            }
            if (tphi != Double.MAX_VALUE) {
                Az = Math.atan2(sdlam2, (C20 * tphi) - (S20 * cdlam2));
            }
            Av = Azab;
            double cdlam3 = cdlam2;
            doubleR.f1627y = rhoc;
            cdlam = cdlam3;
            double cdlam4 = cphi;
        } else {
            double z5 = ((cphi * cdlam) + sphi) * 0.7071067811865476d;
            if (Math.abs(z5) <= 1.0d) {
                z2 = Math.acos(z5);
            } else if (Math.abs(z5) <= ONEEPS) {
                z2 = z5 < 0.0d ? -1.0d : 1.0d;
            } else {
                throw new ProjectionException(str);
            }
            Av = Azba;
            double d4 = cphi;
            doubleR.f1627y = -1.2070912152156872d;
        }
        if (z >= 0.0d) {
            double d5 = sphi;
            double pow = Math.pow(Math.tan(z * 0.5d), f1642n);
            double t = pow;
            double r = pow * f1640F;
            double d6 = (R104 - z) * 0.5d;
            double al3 = d6;
            if (d6 >= 0.0d) {
                double d7 = cdlam;
                double al4 = (t + Math.pow(al3, f1642n)) / f1641T;
                if (Math.abs(al4) <= 1.0d) {
                    al = Math.acos(al4);
                } else if (Math.abs(al4) <= ONEEPS) {
                    if (al4 >= 0.0d) {
                        d2 = 1.0d;
                    }
                    al = d2;
                } else {
                    throw new ProjectionException(str);
                }
                double d8 = (Av - Az) * f1642n;
                double t2 = d8;
                if (Math.abs(d8) < al) {
                    double d9 = sdlam2;
                    r /= Math.cos((tag ? t2 : -t2) + al);
                }
                doubleR.f1626x = Math.sin(t2) * r;
                double d10 = doubleR.f1627y;
                if (tag) {
                    double d11 = al;
                    al2 = -r;
                } else {
                    al2 = r;
                }
                doubleR.f1627y = d10 + (al2 * Math.cos(t2));
                if (this.noskew) {
                    double t3 = doubleR.f1626x;
                    double d12 = r;
                    doubleR.f1626x = ((-doubleR.f1626x) * cAzc) - (doubleR.f1627y * sAzc);
                    doubleR.f1627y = ((-doubleR.f1627y) * cAzc) + (sAzc * t3);
                }
                return doubleR;
            }
            throw new ProjectionException(str);
        }
        throw new ProjectionException(str);
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        double Av;
        double c;
        double s;
        double z;
        double Az;
        double d = xyx;
        double d2 = xyy;
        Double doubleR = out;
        double z2 = 0.0d;
        if (this.noskew) {
            double t = xyx;
            doubleR.f1626x = ((-d) * cAzc) + (d2 * sAzc);
            doubleR.f1627y = ((-d2) * cAzc) - (sAzc * t);
        }
        boolean z3 = d < 0.0d;
        boolean neg = z3;
        if (z3) {
            doubleR.f1627y = rhoc - d2;
            s = S20;
            c = C20;
            Av = Azab;
        } else {
            doubleR.f1627y += rhoc;
            s = 0.7071067811865476d;
            c = 0.7071067811865476d;
            Av = Azba;
        }
        double rl = MapMath.distance(xyx, xyy);
        double r = rl;
        double rp = rl;
        double atan2 = Math.atan2(xyx, xyy);
        double Az2 = atan2;
        double fAz = Math.abs(atan2);
        int i = 10;
        while (true) {
            if (i <= 0) {
                z = z2;
                Az = Az2;
                break;
            }
            double z4 = Math.atan(Math.pow(r / f1640F, 1.585895806935677d)) * 2.0d;
            z = z4;
            double al = Math.acos((Math.pow(Math.tan(z4 * 0.5d), f1642n) + Math.pow(Math.tan((R104 - z4) * 0.5d), f1642n)) / f1641T);
            if (fAz < al) {
                Az = Az2;
                r = Math.cos((neg ? Az : -Az) + al) * rp;
            } else {
                Az = Az2;
            }
            if (Math.abs(rl - r) < 1.0E-10d) {
                break;
            }
            rl = r;
            i--;
            double al2 = xyx;
            Az2 = Az;
            z2 = z;
            double Az3 = xyy;
        }
        if (i != 0) {
            double Az4 = Av - (Az / f1642n);
            doubleR.f1627y = Math.asin((Math.cos(z) * s) + (Math.sin(z) * c * Math.cos(Az4)));
            doubleR.f1626x = Math.atan2(Math.sin(Az4), (c / Math.tan(z)) - (Math.cos(Az4) * s));
            if (neg) {
                doubleR.f1626x -= R110;
            } else {
                doubleR.f1626x = lamB - doubleR.f1626x;
            }
            return doubleR;
        }
        throw new ProjectionException("I");
    }

    public boolean hasInverse() {
        return true;
    }

    public void initialize() {
        super.initialize();
    }

    public String toString() {
        return "Bipolar Conic of Western Hemisphere";
    }
}
