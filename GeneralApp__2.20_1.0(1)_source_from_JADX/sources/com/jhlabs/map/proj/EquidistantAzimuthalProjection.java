package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class EquidistantAzimuthalProjection extends AzimuthalProjection {
    private static final double TOL = 1.0E-8d;

    /* renamed from: G */
    private double f1677G;

    /* renamed from: He */
    private double f1678He;

    /* renamed from: M1 */
    private double f1679M1;

    /* renamed from: Mp */
    private double f1680Mp;

    /* renamed from: N1 */
    private double f1681N1;
    private double cosphi0;

    /* renamed from: en */
    private double[] f1682en;
    private int mode;
    private double sinphi0;

    public EquidistantAzimuthalProjection() {
        this(Math.toRadians(90.0d), Math.toRadians(0.0d));
    }

    public EquidistantAzimuthalProjection(double projectionLatitude, double projectionLongitude) {
        super(projectionLatitude, projectionLongitude);
        initialize();
    }

    public Object clone() {
        EquidistantAzimuthalProjection p = (EquidistantAzimuthalProjection) super.clone();
        double[] dArr = this.f1682en;
        if (dArr != null) {
            p.f1682en = (double[]) dArr.clone();
        }
        return p;
    }

    public void initialize() {
        super.initialize();
        if (Math.abs(Math.abs(this.projectionLatitude) - 1.5707963267948966d) < 1.0E-10d) {
            this.mode = this.projectionLatitude < 0.0d ? 2 : 1;
            this.sinphi0 = this.projectionLatitude < 0.0d ? -1.0d : 1.0d;
            this.cosphi0 = 0.0d;
        } else if (Math.abs(this.projectionLatitude) < 1.0E-10d) {
            this.mode = 3;
            this.sinphi0 = 0.0d;
            this.cosphi0 = 1.0d;
        } else {
            this.mode = 4;
            this.sinphi0 = Math.sin(this.projectionLatitude);
            this.cosphi0 = Math.cos(this.projectionLatitude);
        }
        if (!this.spherical) {
            this.f1682en = MapMath.enfn(this.f1736es);
            int i = this.mode;
            if (i == 1) {
                this.f1680Mp = MapMath.mlfn(1.5707963267948966d, 1.0d, 0.0d, this.f1682en);
            } else if (i == 2) {
                this.f1680Mp = MapMath.mlfn(-1.5707963267948966d, -1.0d, 0.0d, this.f1682en);
            } else if (i == 3 || i == 4) {
                double d = this.f1736es;
                double d2 = this.sinphi0;
                this.f1681N1 = 1.0d / Math.sqrt(1.0d - ((d * d2) * d2));
                double d3 = this.sinphi0;
                double sqrt = this.f1735e / Math.sqrt(this.one_es);
                this.f1678He = sqrt;
                this.f1677G = d3 * sqrt;
                this.f1678He *= this.cosphi0;
            }
        }
    }

    public Double project(double lam, double phi, Double xy) {
        double coslam;
        double cosphi;
        double d;
        double phi2;
        double d2;
        double d3 = phi;
        Double doubleR = xy;
        if (this.spherical) {
            double sinphi = Math.sin(phi);
            double cosphi2 = Math.cos(phi);
            double coslam2 = Math.cos(lam);
            int i = this.mode;
            if (i == 1) {
                phi2 = -phi;
                coslam2 = -coslam2;
            } else if (i == 2) {
                phi2 = phi;
            } else if (i == 3 || i == 4) {
                if (this.mode == 3) {
                    doubleR.f1627y = cosphi2 * coslam2;
                } else {
                    doubleR.f1627y = (this.sinphi0 * sinphi) + (this.cosphi0 * cosphi2 * coslam2);
                }
                if (Math.abs(Math.abs(doubleR.f1627y) - 1.0d) >= TOL) {
                    doubleR.f1627y = Math.acos(doubleR.f1627y);
                    doubleR.f1627y /= Math.sin(doubleR.f1627y);
                    doubleR.f1626x = doubleR.f1627y * cosphi2 * Math.sin(lam);
                    double d4 = doubleR.f1627y;
                    if (this.mode == 3) {
                        d2 = sinphi;
                    } else {
                        d2 = (this.cosphi0 * sinphi) - ((this.sinphi0 * cosphi2) * coslam2);
                    }
                    doubleR.f1627y = d4 * d2;
                } else if (doubleR.f1627y >= 0.0d) {
                    doubleR.f1627y = 0.0d;
                    doubleR.f1626x = 0.0d;
                } else {
                    throw new ProjectionException();
                }
            } else {
                double d5 = d3;
                return doubleR;
            }
            if (Math.abs(phi2 - 1.5707963267948966d) >= 1.0E-10d) {
                double d6 = 1.5707963267948966d + phi2;
                doubleR.f1627y = d6;
                doubleR.f1626x = d6 * Math.sin(lam);
                doubleR.f1627y *= coslam2;
                return doubleR;
            }
            throw new ProjectionException();
        }
        double coslam3 = Math.cos(lam);
        double cosphi3 = Math.cos(phi);
        double sinphi2 = Math.sin(phi);
        int i2 = this.mode;
        if (i2 == 1) {
            cosphi = cosphi3;
            coslam = -coslam3;
        } else if (i2 == 2) {
            cosphi = cosphi3;
            coslam = coslam3;
        } else if (i2 == 3 || i2 == 4) {
            if (Math.abs(lam) >= 1.0E-10d || Math.abs(d3 - this.projectionLatitude) >= 1.0E-10d) {
                double t = Math.atan2((this.one_es * sinphi2) + (this.f1736es * this.f1681N1 * this.sinphi0 * Math.sqrt(1.0d - ((this.f1736es * sinphi2) * sinphi2))), cosphi3);
                double ct = Math.cos(t);
                double st = Math.sin(t);
                double d7 = cosphi3;
                double d8 = t;
                double Az = Math.atan2(Math.sin(lam) * ct, (this.cosphi0 * st) - ((this.sinphi0 * coslam3) * ct));
                double cA = Math.cos(Az);
                double sA = Math.sin(Az);
                if (Math.abs(sA) < TOL) {
                    double d9 = Az;
                    double d10 = st;
                    d = ((this.cosphi0 * st) - ((this.sinphi0 * coslam3) * ct)) / cA;
                } else {
                    double d11 = st;
                    d = (Math.sin(lam) * ct) / sA;
                }
                double s = MapMath.asin(d);
                double H = this.f1678He * cA;
                double d12 = ct;
                double H2 = H * H;
                double d13 = coslam3;
                double d14 = this.f1681N1 * s;
                double d15 = s * s;
                double cA2 = cA;
                double d16 = ((-H2) * (1.0d - H2)) / 6.0d;
                double sA2 = sA;
                double d17 = this.f1677G;
                double c = d14 * ((d15 * (d16 + (((((d17 * H) * (1.0d - ((2.0d * H2) * H2))) / 8.0d) + ((((((4.0d - (H2 * 7.0d)) * H2) - (((3.0d * d17) * d17) * (1.0d - (7.0d * H2)))) / 120.0d) - (((d17 * s) * H) / 48.0d)) * s)) * s))) + 1.0d);
                doubleR.f1626x = c * sA2;
                doubleR.f1627y = c * cA2;
            } else {
                doubleR.f1627y = 0.0d;
                doubleR.f1626x = 0.0d;
            }
        }
        double abs = Math.abs(this.f1680Mp - MapMath.mlfn(phi, sinphi2, cosphi, this.f1682en));
        double rho = abs;
        doubleR.f1626x = abs * Math.sin(lam);
        doubleR.f1627y = rho * coslam;
        double d18 = phi;
        return doubleR;
    }

    public Double projectInverse(double x, double y, Double lp) {
        double y2;
        double x2;
        double d = x;
        double d2 = y;
        Double doubleR = lp;
        if (this.spherical) {
            double distance = MapMath.distance(x, y);
            double c_rh = distance;
            if (distance > 3.141592653589793d) {
                if (c_rh - 1.0E-10d <= 3.141592653589793d) {
                    c_rh = 3.141592653589793d;
                } else {
                    throw new ProjectionException();
                }
            } else if (c_rh < 1.0E-10d) {
                doubleR.f1627y = this.projectionLatitude;
                doubleR.f1626x = 0.0d;
                return doubleR;
            }
            int i = this.mode;
            if (i == 4 || i == 3) {
                double sinc = Math.sin(c_rh);
                double cosc = Math.cos(c_rh);
                if (this.mode == 3) {
                    doubleR.f1627y = MapMath.asin((d2 * sinc) / c_rh);
                    x2 = d * sinc;
                    y2 = cosc * c_rh;
                } else {
                    doubleR.f1627y = MapMath.asin((this.sinphi0 * cosc) + (((d2 * sinc) * this.cosphi0) / c_rh));
                    x2 = d * this.cosphi0 * sinc;
                    y2 = (cosc - (this.sinphi0 * Math.sin(doubleR.f1627y))) * c_rh;
                }
                doubleR.f1626x = y2 == 0.0d ? 0.0d : Math.atan2(x2, y2);
                return doubleR;
            } else if (i == 1) {
                doubleR.f1627y = 1.5707963267948966d - c_rh;
                doubleR.f1626x = Math.atan2(d, -d2);
            } else {
                doubleR.f1627y = c_rh - 1.5707963267948966d;
                doubleR.f1626x = Math.atan2(x, y);
            }
        } else {
            double distance2 = MapMath.distance(x, y);
            double c = distance2;
            if (distance2 < 1.0E-10d) {
                doubleR.f1627y = this.projectionLatitude;
                doubleR.f1626x = 0.0d;
                return doubleR;
            }
            int i2 = this.mode;
            if (i2 == 4 || i2 == 3) {
                double atan2 = Math.atan2(x, y);
                double Az = atan2;
                double cosAz = Math.cos(atan2);
                double t = this.cosphi0 * cosAz;
                double B = (this.f1736es * t) / this.one_es;
                double A = (-B) * t;
                double d3 = (1.0d - A) * 3.0d;
                double d4 = this.sinphi0;
                double B2 = B * d3 * d4;
                double D = c / this.f1681N1;
                double E = (1.0d - ((D * D) * ((((A + 1.0d) * A) / 6.0d) + (((((3.0d * A) + 1.0d) * B2) * D) / 24.0d)))) * D;
                double F = 1.0d - ((E * E) * ((A / 2.0d) + ((B2 * E) / 6.0d)));
                double psi = MapMath.asin((d4 * Math.cos(E)) + (Math.sin(E) * t));
                double d5 = D;
                doubleR.f1626x = MapMath.asin((Math.sin(Az) * Math.sin(E)) / Math.cos(psi));
                double abs = Math.abs(psi);
                double t2 = abs;
                if (abs < 1.0E-10d) {
                    doubleR.f1627y = 0.0d;
                } else if (Math.abs(t2 - 1.5707963267948966d) < 0.0d) {
                    doubleR.f1627y = 1.5707963267948966d;
                } else {
                    double d6 = cosAz;
                    doubleR.f1627y = Math.atan(((1.0d - (((this.f1736es * F) * this.sinphi0) / Math.sin(psi))) * Math.tan(psi)) / this.one_es);
                }
            } else {
                doubleR.f1627y = MapMath.inv_mlfn(i2 == 1 ? this.f1680Mp - c : this.f1680Mp + c, this.f1736es, this.f1682en);
                doubleR.f1626x = Math.atan2(d, this.mode == 1 ? -d2 : d2);
            }
        }
        double psi2 = x;
        double d7 = y;
        return doubleR;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Equidistant Azimuthal";
    }
}
