package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class PolyconicProjection extends Projection {
    private static final double CONV = 1.0E-10d;
    private static final double ITOL = 1.0E-12d;
    private static final int I_ITER = 20;
    private static final int N_ITER = 10;
    private static final double TOL = 1.0E-10d;

    /* renamed from: en */
    private double[] f1733en;
    private double ml0;

    public PolyconicProjection() {
        this.minLatitude = MapMath.degToRad(0.0d);
        this.maxLatitude = MapMath.degToRad(80.0d);
        this.minLongitude = MapMath.degToRad(-60.0d);
        this.maxLongitude = MapMath.degToRad(60.0d);
        initialize();
    }

    public Double project(double lplam, double lpphi, Double out) {
        double d = lplam;
        Double doubleR = out;
        if (this.spherical) {
            if (Math.abs(lpphi) <= 1.0E-10d) {
                doubleR.f1626x = d;
                doubleR.f1627y = this.ml0;
            } else {
                double cot = 1.0d / Math.tan(lpphi);
                double sin = Math.sin(lpphi) * d;
                double E = sin;
                doubleR.f1626x = Math.sin(sin) * cot;
                doubleR.f1627y = (lpphi - this.projectionLatitude) + ((1.0d - Math.cos(E)) * cot);
            }
        } else if (Math.abs(lpphi) <= 1.0E-10d) {
            doubleR.f1626x = d;
            doubleR.f1627y = -this.ml0;
        } else {
            double sp = Math.sin(lpphi);
            double cos = Math.cos(lpphi);
            double cp = cos;
            double ms = Math.abs(cos) > 1.0E-10d ? MapMath.msfn(sp, cp, this.f1736es) / sp : 0.0d;
            double d2 = doubleR.f1626x * sp;
            doubleR.f1626x = d2;
            doubleR.f1626x = Math.sin(d2) * ms;
            doubleR.f1627y = (MapMath.mlfn(lpphi, sp, cp, this.f1733en) - this.ml0) + ((1.0d - Math.cos(lplam)) * ms);
        }
        return doubleR;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        double mlp;
        double d = xyx;
        Double doubleR = out;
        String str = "I";
        double dPhi = 1.0d;
        if (this.spherical) {
            double d2 = this.projectionLatitude + xyy;
            double d3 = d2;
            if (Math.abs(d2) <= 1.0E-10d) {
                doubleR.f1626x = d;
                doubleR.f1627y = 0.0d;
            } else {
                double lpphi = xyy;
                double B = (d * d) + (xyy * xyy);
                int i = 10;
                while (true) {
                    double tp = Math.tan(lpphi);
                    double d4 = (((((lpphi * tp) + 1.0d) * xyy) - lpphi) - ((((lpphi * lpphi) + B) * 0.5d) * tp)) / (((lpphi - xyy) / tp) - 1.0d);
                    double dphi = d4;
                    doubleR.f1627y -= d4;
                    if (Math.abs(dphi) <= 1.0E-10d) {
                        break;
                    }
                    i--;
                    if (i <= 0) {
                        break;
                    }
                }
                if (i != 0) {
                    doubleR.f1626x = Math.asin(Math.tan(lpphi) * d) / Math.sin(lpphi);
                    doubleR.f1627y = lpphi;
                } else {
                    throw new ProjectionException(str);
                }
            }
            double d5 = xyy;
        } else {
            double xyy2 = xyy + this.ml0;
            if (Math.abs(xyy2) <= 1.0E-10d) {
                doubleR.f1626x = d;
                doubleR.f1627y = 0.0d;
            } else {
                double mlp2 = (xyy2 * xyy2) + (d * d);
                int i2 = 20;
                double lpphi2 = xyy2;
                while (true) {
                    if (i2 <= 0) {
                        double d6 = mlp2;
                        mlp = lpphi2;
                        break;
                    }
                    double sp = Math.sin(lpphi2);
                    double cos = Math.cos(lpphi2);
                    double cp = cos;
                    double s2ph = sp * cos;
                    if (Math.abs(cp) >= ITOL) {
                        double sqrt = Math.sqrt(dPhi - ((this.f1736es * sp) * sp));
                        double mlp3 = sqrt;
                        double c = (sqrt * sp) / cp;
                        double ml = MapMath.mlfn(lpphi2, sp, cp, this.f1733en);
                        double mlb = (ml * ml) + mlp2;
                        double r = mlp2;
                        double mlp4 = (dPhi / this.f1736es) / ((mlp3 * mlp3) * mlp3);
                        double d7 = (((ml + ml) + (c * mlb)) - ((xyy2 * 2.0d) * ((c * ml) + dPhi))) / ((((((this.f1736es * s2ph) * (mlb - ((xyy2 * 2.0d) * ml))) / c) + (((xyy2 - ml) * 2.0d) * ((c * mlp4) - (1.0d / s2ph)))) - mlp4) - mlp4);
                        lpphi2 += d7;
                        if (Math.abs(d7) <= ITOL) {
                            mlp = lpphi2;
                            break;
                        }
                        i2--;
                        mlp2 = r;
                        dPhi = 1.0d;
                    } else {
                        double r2 = mlp2;
                        throw new ProjectionException(str);
                    }
                }
                if (i2 != 0) {
                    double c2 = Math.sin(mlp);
                    doubleR.f1626x = Math.asin((Math.tan(mlp) * d) * Math.sqrt(1.0d - ((this.f1736es * c2) * c2))) / Math.sin(mlp);
                    doubleR.f1627y = mlp;
                } else {
                    throw new ProjectionException(str);
                }
            }
        }
        return doubleR;
    }

    public boolean hasInverse() {
        return true;
    }

    public void initialize() {
        super.initialize();
        this.spherical = true;
        if (!this.spherical) {
            this.f1733en = MapMath.enfn(this.f1736es);
            if (this.f1733en != null) {
                this.ml0 = MapMath.mlfn(this.projectionLatitude, Math.sin(this.projectionLatitude), Math.cos(this.projectionLatitude), this.f1733en);
                return;
            }
            throw new ProjectionException("E");
        }
        this.ml0 = -this.projectionLatitude;
    }

    public String toString() {
        return "Polyconic (American)";
    }
}
