package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class VanDerGrintenProjection extends Projection {
    private static final double C2_27 = 0.07407407407407407d;
    private static final double HPISQ = 4.934802200544679d;
    private static final double PI4_3 = 4.188790204786391d;
    private static final double PISQ = 9.869604401089358d;
    private static final double THIRD = 0.3333333333333333d;
    private static final double TOL = 1.0E-10d;
    private static final double TPISQ = 19.739208802178716d;
    private static final double TWO_THRD = 0.6666666666666666d;

    public Double project(double lplam, double lpphi, Double out) {
        double d = lplam;
        Double doubleR = out;
        double p2 = Math.abs(lpphi / 1.5707963267948966d);
        String str = "F";
        if (p2 - TOL <= 1.0d) {
            if (p2 > 1.0d) {
                p2 = 1.0d;
            }
            if (Math.abs(lpphi) <= TOL) {
                doubleR.f1626x = d;
                doubleR.f1627y = 0.0d;
            } else {
                double d2 = 3.141592653589793d;
                if (Math.abs(lplam) <= TOL || Math.abs(p2 - 1.0d) < TOL) {
                    doubleR.f1626x = 0.0d;
                    doubleR.f1627y = Math.tan(Math.asin(p2) * 0.5d) * 3.141592653589793d;
                    if (lpphi < 0.0d) {
                        doubleR.f1627y = -doubleR.f1627y;
                    }
                } else {
                    double al = Math.abs((3.141592653589793d / d) - (d / 3.141592653589793d)) * 0.5d;
                    double al2 = al * al;
                    double g = Math.sqrt(1.0d - (p2 * p2));
                    double g2 = g / ((p2 + g) - 1.0d);
                    double g22 = g2 * g2;
                    double p22 = ((2.0d / p2) - 1.0d) * g2;
                    double p23 = p22 * p22;
                    doubleR.f1626x = g2 - p23;
                    double g3 = p23 + al2;
                    double d3 = al2;
                    doubleR.f1626x = (((doubleR.f1626x * al) + Math.sqrt(((doubleR.f1626x * al2) * doubleR.f1626x) - ((g22 - p23) * g3))) * 3.141592653589793d) / g3;
                    if (d < 0.0d) {
                        doubleR.f1626x = -doubleR.f1626x;
                    }
                    doubleR.f1627y = Math.abs(doubleR.f1626x / 3.141592653589793d);
                    doubleR.f1627y = 1.0d - (doubleR.f1627y * (doubleR.f1627y + (2.0d * al)));
                    if (doubleR.f1627y < -1.0E-10d) {
                        throw new ProjectionException(str);
                    } else if (doubleR.f1627y < 0.0d) {
                        doubleR.f1627y = 0.0d;
                    } else {
                        double sqrt = Math.sqrt(doubleR.f1627y);
                        if (lpphi < 0.0d) {
                            d2 = -3.141592653589793d;
                        }
                        doubleR.f1627y = sqrt * d2;
                    }
                }
            }
            return doubleR;
        }
        throw new ProjectionException(str);
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        Double doubleR = out;
        double x2 = xyx * xyx;
        double abs = Math.abs(xyy);
        double ay = abs;
        double d = 0.0d;
        if (abs < TOL) {
            doubleR.f1627y = 0.0d;
            double t = (x2 * x2) + ((HPISQ + x2) * TPISQ);
            if (Math.abs(xyx) > TOL) {
                d = (((x2 - PISQ) + Math.sqrt(t)) * 0.5d) / xyx;
            }
            doubleR.f1626x = d;
            return doubleR;
        }
        double t2 = xyy * xyy;
        double r = x2 + t2;
        double r2 = r * r;
        double c1 = -3.141592653589793d * ay * (r + PISQ);
        double c3 = r2 + (((ay * r) + ((t2 + ((ay + 1.5707963267948966d) * 3.141592653589793d)) * 3.141592653589793d)) * 6.283185307179586d);
        double c0 = ay * 3.141592653589793d;
        double c2 = (c1 + ((r - (t2 * 3.0d)) * PISQ)) / c3;
        double al = (c1 / c3) - ((c2 * THIRD) * c2);
        double m = Math.sqrt(-0.3333333333333333d * al) * 2.0d;
        double d2 = (3.0d * ((((C2_27 * c2) * c2) * c2) + (((c0 * c0) - ((c2 * THIRD) * c1)) / c3))) / (al * m);
        double d3 = d2;
        double abs2 = Math.abs(d2);
        double t3 = abs2;
        if (abs2 - TOL <= 1.0d) {
            double d4 = t3 > 1.0d ? d3 > 0.0d ? 0.0d : 3.141592653589793d : Math.acos(d3);
            doubleR.f1627y = ((Math.cos((d4 * THIRD) + PI4_3) * m) - (THIRD * c2)) * 3.141592653589793d;
            if (xyy < 0.0d) {
                doubleR.f1627y = -doubleR.f1627y;
            }
            double t4 = r2 + (((x2 - t2) + HPISQ) * TPISQ);
            if (Math.abs(xyx) > TOL) {
                double d5 = r - PISQ;
                if (t4 > 0.0d) {
                    d = Math.sqrt(t4);
                }
                d = ((d5 + d) * 0.5d) / xyx;
            }
            doubleR.f1626x = d;
            return doubleR;
        }
        throw new ProjectionException("I");
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "van der Grinten (I)";
    }
}
