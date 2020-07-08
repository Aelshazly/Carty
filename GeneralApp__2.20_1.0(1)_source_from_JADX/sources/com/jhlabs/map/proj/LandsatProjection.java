package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class LandsatProjection extends Projection {
    private static final double PI_HALFPI = 4.71238898038469d;
    private static final double TOL = 1.0E-7d;
    private static final double TWOPI_HALFPI = 7.853981633974483d;

    /* renamed from: a2 */
    private double f1700a2;

    /* renamed from: a4 */
    private double f1701a4;

    /* renamed from: b */
    private double f1702b;

    /* renamed from: c1 */
    private double f1703c1;

    /* renamed from: c3 */
    private double f1704c3;

    /* renamed from: ca */
    private double f1705ca;
    private double p22;

    /* renamed from: q */
    private double f1706q;
    private double rlm;
    private double rlm2;

    /* renamed from: sa */
    private double f1707sa;

    /* renamed from: t */
    private double f1708t;

    /* renamed from: u */
    private double f1709u;

    /* renamed from: w */
    private double f1710w;

    /* renamed from: xj */
    private double f1711xj;

    public Double project(double lplam, double lpphi, Double xy) {
        double lpphi2;
        double lamtp;
        int l;
        int nn;
        Double doubleR = xy;
        double lamt = 0.0d;
        double lamdp = 0.0d;
        double d = 1.5707963267948966d;
        if (lpphi > 1.5707963267948966d) {
            lpphi2 = 1.5707963267948966d;
        } else if (lpphi < -1.5707963267948966d) {
            lpphi2 = -1.5707963267948966d;
        } else {
            lpphi2 = lpphi;
        }
        double d2 = 0.0d;
        double lampp = lpphi2 >= 0.0d ? 1.5707963267948966d : PI_HALFPI;
        double tanphi = Math.tan(lpphi2);
        int nn2 = 0;
        while (true) {
            double sav = lampp;
            lamtp = lplam + (this.p22 * lampp);
            double cl = Math.cos(lamtp);
            if (Math.abs(cl) < TOL) {
                lamtp -= TOL;
            }
            double fac = lampp - (Math.sin(lampp) * (cl < d2 ? -1.5707963267948966d : d));
            l = 50;
            while (true) {
                if (l <= 0) {
                    break;
                }
                double lamt2 = lplam + (this.p22 * sav);
                double cos = Math.cos(lamt2);
                double c = cos;
                if (Math.abs(cos) < TOL) {
                    lamt2 -= TOL;
                }
                double lamt3 = lamt2;
                lamdp = Math.atan((((this.one_es * tanphi) * this.f1707sa) + (Math.sin(lamt2) * this.f1705ca)) / c) + fac;
                if (Math.abs(Math.abs(sav) - Math.abs(lamdp)) < TOL) {
                    lamt = lamt3;
                    break;
                }
                double d3 = lpphi2;
                sav = lamdp;
                l--;
                lamt = lamt3;
            }
            if (l == 0) {
                break;
            }
            nn = nn2 + 1;
            if (nn >= 3 || (lamdp > this.rlm && lamdp < this.rlm2)) {
                int i = nn;
            } else if (lamdp <= this.rlm) {
                lampp = TWOPI_HALFPI;
                nn2 = nn;
                d = 1.5707963267948966d;
                d2 = 0.0d;
            } else if (lamdp >= this.rlm2) {
                lampp = 1.5707963267948966d;
                nn2 = nn;
                d = 1.5707963267948966d;
                d2 = 0.0d;
            } else {
                nn2 = nn;
                d = 1.5707963267948966d;
                d2 = 0.0d;
            }
        }
        int i2 = nn;
        if (l != 0) {
            double sp = Math.sin(lpphi2);
            double d4 = lamtp;
            double phidp = MapMath.asin((((this.one_es * this.f1705ca) * sp) - ((this.f1707sa * Math.cos(lpphi2)) * Math.sin(lamt))) / Math.sqrt(1.0d - ((this.f1736es * sp) * sp)));
            double tanph = Math.log(Math.tan((0.5d * phidp) + 0.7853981633974483d));
            double sd = Math.sin(lamdp);
            double sdsq = sd * sd;
            double d5 = lamt;
            double d6 = phidp;
            double d7 = sp;
            double d8 = lpphi2;
            double s = this.p22 * this.f1707sa * Math.cos(lamdp) * Math.sqrt(((this.f1708t * sdsq) + 1.0d) / (((this.f1710w * sdsq) + 1.0d) * ((this.f1706q * sdsq) + 1.0d)));
            double d9 = this.f1711xj;
            double d10 = Math.sqrt((d9 * d9) + (s * s));
            doubleR.f1626x = (((this.f1702b * lamdp) + (this.f1700a2 * Math.sin(2.0d * lamdp))) + (this.f1701a4 * Math.sin(4.0d * lamdp))) - ((tanph * s) / d10);
            doubleR.f1627y = (this.f1703c1 * sd) + (this.f1704c3 * Math.sin(3.0d * lamdp)) + ((this.f1711xj * tanph) / d10);
        } else {
            double d11 = lamtp;
            double d12 = lpphi2;
            doubleR.f1627y = Double.POSITIVE_INFINITY;
            doubleR.f1626x = Double.POSITIVE_INFINITY;
        }
        return doubleR;
    }

    private void seraz0(double lam, double mult) {
        double lam2 = 0.017453292519943295d * lam;
        double sd = Math.sin(lam2);
        double sdsq = sd * sd;
        double d = sd;
        double s = this.p22 * this.f1707sa * Math.cos(lam2) * Math.sqrt(((this.f1708t * sdsq) + 1.0d) / (((this.f1710w * sdsq) + 1.0d) * ((this.f1706q * sdsq) + 1.0d)));
        double d2 = this.f1706q;
        double d__1 = (d2 * sdsq) + 1.0d;
        double d3 = sdsq;
        double h = Math.sqrt(((d2 * sdsq) + 1.0d) / ((this.f1710w * sdsq) + 1.0d)) * ((((this.f1710w * sdsq) + 1.0d) / (d__1 * d__1)) - (this.p22 * this.f1705ca));
        double d4 = this.f1711xj;
        double sq = Math.sqrt((d4 * d4) + (s * s));
        double d5 = (((this.f1711xj * h) - (s * s)) * mult) / sq;
        double fc = d5;
        this.f1702b += d5;
        this.f1700a2 += Math.cos(lam2 + lam2) * fc;
        this.f1701a4 += Math.cos(4.0d * lam2) * fc;
        double fc2 = ((mult * s) * (this.f1711xj + h)) / sq;
        this.f1703c1 += Math.cos(lam2) * fc2;
        this.f1704c3 += Math.cos(3.0d * lam2) * fc2;
    }

    public void initialize() {
        double alf;
        super.initialize();
        if (1 <= 0 || 1 > 5) {
            throw new ProjectionException("-28");
        }
        if (120 > 0) {
            if ('x' <= (1 <= 3 ? (char) 251 : 233)) {
                if (1 <= 3) {
                    this.projectionLongitude = 2.2492058070450924d - (((double) 120) * 0.025032610785576042d);
                    this.p22 = 103.2669323d;
                    alf = 1.729481662386221d;
                } else {
                    this.projectionLongitude = 2.2567107228286685d - (((double) 120) * 0.026966460545835135d);
                    this.p22 = 98.8841202d;
                    alf = 1.7139133254584316d;
                }
                this.p22 /= 1440.0d;
                this.f1707sa = Math.sin(alf);
                this.f1705ca = Math.cos(alf);
                if (Math.abs(this.f1705ca) < 1.0E-9d) {
                    this.f1705ca = 1.0E-9d;
                }
                double d = this.f1736es;
                double d2 = this.f1705ca;
                double esc = d * d2 * d2;
                double d3 = this.f1736es;
                double d4 = this.f1707sa;
                double ess = d3 * d4 * d4;
                double d5 = 1.0d;
                this.f1710w = (1.0d - esc) * this.rone_es;
                double d6 = this.f1710w;
                this.f1710w = (d6 * d6) - 1.0d;
                this.f1706q = this.rone_es * ess;
                this.f1708t = (2.0d - this.f1736es) * ess * this.rone_es * this.rone_es;
                this.f1709u = this.rone_es * esc;
                this.f1711xj = this.one_es * this.one_es * this.one_es;
                this.rlm = 1.6341348883592068d;
                this.rlm2 = this.rlm + 6.283185307179586d;
                this.f1704c3 = 0.0d;
                this.f1703c1 = 0.0d;
                this.f1702b = 0.0d;
                this.f1701a4 = 0.0d;
                this.f1700a2 = 0.0d;
                seraz0(0.0d, 1.0d);
                double lam = 9.0d;
                while (lam <= 81.0001d) {
                    seraz0(lam, 4.0d);
                    lam += 18.0d;
                    d5 = 1.0d;
                }
                for (double lam2 = 18.0d; lam2 <= 72.0001d; lam2 += 18.0d) {
                    seraz0(lam2, 2.0d);
                }
                seraz0(90.0d, d5);
                this.f1700a2 /= 30.0d;
                this.f1701a4 /= 60.0d;
                this.f1702b /= 30.0d;
                this.f1703c1 /= 15.0d;
                this.f1704c3 /= 45.0d;
                return;
            }
        }
        throw new ProjectionException("-29");
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Landsat";
    }
}
