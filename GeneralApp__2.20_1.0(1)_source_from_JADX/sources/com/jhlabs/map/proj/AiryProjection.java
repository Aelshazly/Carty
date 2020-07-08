package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class AiryProjection extends Projection {
    private static final double EPS = 1.0E-10d;
    private static final int EQUIT = 2;
    private static final int N_POLE = 0;
    private static final int OBLIQ = 3;
    private static final int S_POLE = 1;

    /* renamed from: Cb */
    private double f1632Cb;
    private double cosph0;
    private int mode;
    private boolean no_cut;
    private double p_halfpi;
    private double sinph0;

    public AiryProjection() {
        this.no_cut = true;
        this.minLatitude = Math.toRadians(-60.0d);
        this.maxLatitude = Math.toRadians(60.0d);
        this.minLongitude = Math.toRadians(-90.0d);
        this.maxLongitude = Math.toRadians(90.0d);
        initialize();
    }

    public Double project(double lplam, double lpphi, Double out) {
        double sinphi;
        double Krho;
        Double doubleR = out;
        double sinlam = Math.sin(lplam);
        double coslam = Math.cos(lplam);
        int i = this.mode;
        String str = "F";
        if (i == 0 || i == 1) {
            doubleR.f1627y = Math.abs(this.p_halfpi - lpphi);
            if (this.no_cut || lpphi - EPS <= 1.5707963267948966d) {
                double d = doubleR.f1627y * 0.5d;
                doubleR.f1627y = d;
                if (d > EPS) {
                    double t = Math.tan(lpphi);
                    double Krho2 = ((Math.log(Math.cos(lpphi)) / t) + (this.f1632Cb * t)) * -2.0d;
                    doubleR.f1626x = Krho2 * sinlam;
                    doubleR.f1627y = Krho2 * coslam;
                    if (this.mode == 0) {
                        doubleR.f1627y = -doubleR.f1627y;
                    }
                } else {
                    doubleR.f1627y = 0.0d;
                    doubleR.f1626x = 0.0d;
                }
            } else {
                throw new ProjectionException(str);
            }
        } else if (i == 2 || i == 3) {
            double sinphi2 = Math.sin(lpphi);
            double cosphi = Math.cos(lpphi);
            double cosz = cosphi * coslam;
            if (this.mode == 3) {
                sinphi = sinphi2;
                cosz = (this.sinph0 * sinphi2) + (this.cosph0 * cosz);
            } else {
                sinphi = sinphi2;
            }
            if (this.no_cut || cosz >= -1.0E-10d) {
                double s = 1.0d - cosz;
                if (Math.abs(s) > EPS) {
                    double t2 = (cosz + 1.0d) * 0.5d;
                    Krho = ((-Math.log(t2)) / s) - (this.f1632Cb / t2);
                } else {
                    Krho = 0.5d - this.f1632Cb;
                }
                doubleR.f1626x = Krho * cosphi * sinlam;
                if (this.mode == 3) {
                    doubleR.f1627y = ((this.cosph0 * sinphi) - ((this.sinph0 * cosphi) * coslam)) * Krho;
                } else {
                    doubleR.f1627y = Krho * sinphi;
                }
            } else {
                throw new ProjectionException(str);
            }
        }
        return doubleR;
    }

    public void initialize() {
        super.initialize();
        this.no_cut = false;
        if (Math.abs(0.7853981633974483d) < EPS) {
            this.f1632Cb = -0.5d;
        } else {
            this.f1632Cb = 1.0d / Math.tan(0.7853981633974483d);
            double d = this.f1632Cb;
            this.f1632Cb = d * Math.log(Math.cos(0.7853981633974483d)) * d;
        }
        if (Math.abs(Math.abs(this.projectionLatitude) - 1.5707963267948966d) < EPS) {
            if (this.projectionLatitude < 0.0d) {
                this.p_halfpi = -1.5707963267948966d;
                this.mode = 1;
                return;
            }
            this.p_halfpi = 1.5707963267948966d;
            this.mode = 0;
        } else if (Math.abs(this.projectionLatitude) < EPS) {
            this.mode = 2;
        } else {
            this.mode = 3;
            this.sinph0 = Math.sin(this.projectionLatitude);
            this.cosph0 = Math.cos(this.projectionLatitude);
        }
    }

    public String toString() {
        return "Airy";
    }
}
