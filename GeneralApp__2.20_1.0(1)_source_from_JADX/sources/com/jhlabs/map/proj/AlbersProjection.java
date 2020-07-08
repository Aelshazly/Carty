package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class AlbersProjection extends Projection {
    private static final double EPS10 = 1.0E-10d;
    private static final double EPSILON = 1.0E-7d;
    private static final int N_ITER = 15;
    private static final double TOL = 1.0E-10d;
    private static final double TOL7 = 1.0E-7d;

    /* renamed from: c */
    private double f1633c;

    /* renamed from: dd */
    private double f1634dd;

    /* renamed from: ec */
    private double f1635ec;

    /* renamed from: en */
    private double[] f1636en;

    /* renamed from: n */
    private double f1637n;

    /* renamed from: n2 */
    private double f1638n2;
    private double phi1;
    private double phi2;
    protected double projectionLatitude1;
    protected double projectionLatitude2;
    private double rho0;

    public AlbersProjection() {
        this.projectionLatitude1 = MapMath.degToRad(45.5d);
        this.projectionLatitude2 = MapMath.degToRad(29.5d);
        this.minLatitude = Math.toRadians(0.0d);
        this.maxLatitude = Math.toRadians(80.0d);
        initialize();
    }

    private static double phi1_(double qs, double Te, double Tone_es) {
        double Phi = Math.asin(qs * 0.5d);
        if (Te < 1.0E-7d) {
            return Phi;
        }
        int i = 15;
        do {
            double sinpi = Math.sin(Phi);
            double con = Te * sinpi;
            double com2 = 1.0d - (con * con);
            double dphi = (((com2 * 0.5d) * com2) / Math.cos(Phi)) * (((qs / Tone_es) - (sinpi / com2)) + ((0.5d / Te) * Math.log((1.0d - con) / (1.0d + con))));
            Phi += dphi;
            if (Math.abs(dphi) <= 1.0E-10d) {
                break;
            }
            i--;
        } while (i != 0);
        return i != 0 ? Phi : Double.MAX_VALUE;
    }

    public Double project(double lplam, double lpphi, Double out) {
        double d;
        double d2;
        double d3 = this.f1633c;
        if (!this.spherical) {
            d2 = this.f1637n;
            d = MapMath.qsfn(Math.sin(lpphi), this.f1735e, this.one_es);
        } else {
            d2 = this.f1638n2;
            d = Math.sin(lpphi);
        }
        double d4 = d3 - (d2 * d);
        double rho = d4;
        if (d4 >= 0.0d) {
            double rho2 = this.f1634dd * Math.sqrt(rho);
            double d5 = this.f1637n * lplam;
            double lplam2 = d5;
            out.f1626x = Math.sin(d5) * rho2;
            out.f1627y = this.rho0 - (Math.cos(lplam2) * rho2);
            return out;
        }
        throw new ProjectionException("F");
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        double lpphi;
        double xyx2 = xyx;
        Double doubleR = out;
        double d = this.rho0 - xyy;
        double xyy2 = d;
        double distance = MapMath.distance(xyx2, d);
        double rho = distance;
        if (distance != 0.0d) {
            if (this.f1637n < 0.0d) {
                rho = -rho;
                xyx2 = -xyx2;
                xyy2 = -xyy2;
            }
            double lpphi2 = rho / this.f1634dd;
            if (!this.spherical) {
                double lpphi3 = (this.f1633c - (lpphi2 * lpphi2)) / this.f1637n;
                if (Math.abs(this.f1635ec - Math.abs(lpphi3)) > 1.0E-7d) {
                    double phi1_ = phi1_(lpphi3, this.f1735e, this.one_es);
                    lpphi = phi1_;
                    if (phi1_ == Double.MAX_VALUE) {
                        throw new ProjectionException("I");
                    }
                } else {
                    lpphi = lpphi3 < 0.0d ? -1.5707963267948966d : 1.5707963267948966d;
                }
            } else {
                double d2 = (this.f1633c - (lpphi2 * lpphi2)) / this.f1638n2;
                doubleR.f1627y = d2;
                if (Math.abs(d2) <= 1.0d) {
                    lpphi = Math.asin(lpphi2);
                } else {
                    lpphi = lpphi2 < 0.0d ? -1.5707963267948966d : 1.5707963267948966d;
                }
            }
            doubleR.f1626x = Math.atan2(xyx2, xyy2) / this.f1637n;
            doubleR.f1627y = lpphi;
        } else {
            doubleR.f1626x = 0.0d;
            doubleR.f1627y = this.f1637n > 0.0d ? 1.5707963267948966d : -1.5707963267948966d;
        }
        return doubleR;
    }

    public void initialize() {
        super.initialize();
        this.phi1 = this.projectionLatitude1;
        this.phi2 = this.projectionLatitude2;
        if (Math.abs(this.phi1 + this.phi2) >= 1.0E-10d) {
            double sin = Math.sin(this.phi1);
            double sinphi = sin;
            this.f1637n = sin;
            double cosphi = Math.cos(this.phi1);
            boolean z = true;
            boolean secant = Math.abs(this.phi1 - this.phi2) >= 1.0E-10d;
            if (this.f1736es <= 0.0d) {
                z = false;
            }
            this.spherical = z;
            if (!this.spherical) {
                double[] enfn = MapMath.enfn(this.f1736es);
                this.f1636en = enfn;
                if (enfn != null) {
                    double d = sinphi;
                    double m1 = MapMath.msfn(d, cosphi, this.f1736es);
                    double ml1 = MapMath.qsfn(d, this.f1735e, this.one_es);
                    if (secant) {
                        double sinphi2 = Math.sin(this.phi2);
                        cosphi = Math.cos(this.phi2);
                        double d2 = sinphi2;
                        double m2 = MapMath.msfn(d2, cosphi, this.f1736es);
                        this.f1637n = ((m1 * m1) - (m2 * m2)) / (MapMath.qsfn(d2, this.f1735e, this.one_es) - ml1);
                        double d3 = sinphi2;
                    }
                    this.f1635ec = 1.0d - (((this.one_es * 0.5d) * Math.log((1.0d - this.f1735e) / (this.f1735e + 1.0d))) / this.f1735e);
                    double d4 = m1 * m1;
                    double d5 = this.f1637n;
                    this.f1633c = d4 + (d5 * ml1);
                    this.f1634dd = 1.0d / d5;
                    double cosphi2 = cosphi;
                    double d6 = ml1;
                    this.rho0 = this.f1634dd * Math.sqrt(this.f1633c - (d5 * MapMath.qsfn(Math.sin(this.projectionLatitude), this.f1735e, this.one_es)));
                    double d7 = cosphi2;
                    return;
                }
                throw new IllegalArgumentException("0");
            }
            if (secant) {
                this.f1637n = (this.f1637n + Math.sin(this.phi2)) * 0.5d;
            }
            double d8 = this.f1637n;
            this.f1638n2 = d8 + d8;
            double d9 = cosphi * cosphi;
            double d10 = this.f1638n2;
            this.f1633c = d9 + (d10 * sinphi);
            this.f1634dd = 1.0d / d8;
            this.rho0 = this.f1634dd * Math.sqrt(this.f1633c - (d10 * Math.sin(this.projectionLatitude)));
            return;
        }
        throw new IllegalArgumentException("-21");
    }

    public boolean isEqualArea() {
        return true;
    }

    public boolean hasInverse() {
        return true;
    }

    public int getEPSGCode() {
        return 9822;
    }

    public String toString() {
        return "Albers Equal Area";
    }
}
