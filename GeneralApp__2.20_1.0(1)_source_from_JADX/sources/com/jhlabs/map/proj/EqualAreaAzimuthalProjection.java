package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class EqualAreaAzimuthalProjection extends AzimuthalProjection {
    private double[] apa;
    private double cosb1;

    /* renamed from: dd */
    private double f1674dd;
    private double mmf;

    /* renamed from: qp */
    private double f1675qp;

    /* renamed from: rq */
    private double f1676rq;
    private double sinb1;
    private double xmf;
    private double ymf;

    public EqualAreaAzimuthalProjection() {
        initialize();
    }

    public Object clone() {
        EqualAreaAzimuthalProjection p = (EqualAreaAzimuthalProjection) super.clone();
        double[] dArr = this.apa;
        if (dArr != null) {
            p.apa = (double[]) dArr.clone();
        }
        return p;
    }

    public void initialize() {
        super.initialize();
        if (!this.spherical) {
            this.f1675qp = MapMath.qsfn(1.0d, this.f1735e, this.one_es);
            this.mmf = 0.5d / (1.0d - this.f1736es);
            this.apa = MapMath.authset(this.f1736es);
            int i = this.mode;
            if (i == 1 || i == 2) {
                this.f1674dd = 1.0d;
            } else if (i == 3) {
                double sqrt = Math.sqrt(this.f1675qp * 0.5d);
                this.f1676rq = sqrt;
                this.f1674dd = 1.0d / sqrt;
                this.xmf = 1.0d;
                this.ymf = this.f1675qp * 0.5d;
            } else if (i == 4) {
                this.f1676rq = Math.sqrt(this.f1675qp * 0.5d);
                double sinphi = Math.sin(this.projectionLatitude);
                this.sinb1 = MapMath.qsfn(sinphi, this.f1735e, this.one_es) / this.f1675qp;
                double d = this.sinb1;
                this.cosb1 = Math.sqrt(1.0d - (d * d));
                double cos = Math.cos(this.projectionLatitude);
                double sqrt2 = Math.sqrt(1.0d - ((this.f1736es * sinphi) * sinphi));
                double d2 = this.f1676rq;
                this.f1674dd = cos / ((sqrt2 * d2) * this.cosb1);
                this.xmf = d2;
                double d3 = this.f1674dd;
                this.ymf = d2 / d3;
                this.xmf *= d3;
            }
        } else if (this.mode == 4) {
            this.sinphi0 = Math.sin(this.projectionLatitude);
            this.cosphi0 = Math.cos(this.projectionLatitude);
        }
    }

    public Double project(double lam, double phi, Double xy) {
        double sinb;
        double d;
        double d2;
        Double doubleR = xy;
        if (this.spherical) {
            double sinphi = Math.sin(phi);
            double cosphi = Math.cos(phi);
            double coslam = Math.cos(lam);
            int i = this.mode;
            if (i == 1) {
                coslam = -coslam;
            } else if (i != 2) {
                if (i == 3) {
                    doubleR.f1627y = (cosphi * coslam) + 1.0d;
                    if (doubleR.f1627y > 1.0E-10d) {
                        double sqrt = Math.sqrt(2.0d / doubleR.f1627y);
                        doubleR.f1627y = sqrt;
                        doubleR.f1626x = sqrt * cosphi * Math.sin(lam);
                        double d3 = doubleR.f1627y;
                        if (this.mode == 3) {
                            d = sinphi;
                        } else {
                            d = (this.cosphi0 * sinphi) - ((this.sinphi0 * cosphi) * coslam);
                        }
                        doubleR.f1627y = d3 * d;
                    } else {
                        throw new ProjectionException();
                    }
                } else if (i == 4) {
                    doubleR.f1627y = (this.sinphi0 * sinphi) + 1.0d + (this.cosphi0 * cosphi * coslam);
                    if (doubleR.f1627y > 1.0E-10d) {
                        double sqrt2 = Math.sqrt(2.0d / doubleR.f1627y);
                        doubleR.f1627y = sqrt2;
                        doubleR.f1626x = sqrt2 * cosphi * Math.sin(lam);
                        double d4 = doubleR.f1627y;
                        if (this.mode == 3) {
                            d2 = sinphi;
                        } else {
                            d2 = (this.cosphi0 * sinphi) - ((this.sinphi0 * cosphi) * coslam);
                        }
                        doubleR.f1627y = d4 * d2;
                    } else {
                        throw new ProjectionException();
                    }
                }
            }
            if (Math.abs(phi + this.projectionLatitude) >= 1.0E-10d) {
                doubleR.f1627y = 0.7853981633974483d - (0.5d * phi);
                doubleR.f1627y = (this.mode == 2 ? Math.cos(doubleR.f1627y) : Math.sin(doubleR.f1627y)) * 2.0d;
                doubleR.f1626x = doubleR.f1627y * Math.sin(lam);
                doubleR.f1627y *= coslam;
            } else {
                throw new ProjectionException();
            }
        } else {
            double sinb2 = 0.0d;
            double cosb = 0.0d;
            double b = 0.0d;
            double coslam2 = Math.cos(lam);
            double sinlam = Math.sin(lam);
            double q = MapMath.qsfn(Math.sin(phi), this.f1735e, this.one_es);
            if (this.mode == 4 || this.mode == 3) {
                sinb2 = q / this.f1675qp;
                cosb = Math.sqrt(1.0d - (sinb2 * sinb2));
            }
            int i2 = this.mode;
            if (i2 == 1) {
                sinb = sinb2;
                b = phi + 1.5707963267948966d;
                q = this.f1675qp - q;
            } else if (i2 == 2) {
                sinb = sinb2;
                b = phi - 1.5707963267948966d;
                q += this.f1675qp;
            } else if (i2 == 3) {
                sinb = sinb2;
                b = (cosb * coslam2) + 1.0d;
            } else if (i2 != 4) {
                sinb = sinb2;
            } else {
                sinb = sinb2;
                b = (this.sinb1 * sinb2) + 1.0d + (this.cosb1 * cosb * coslam2);
            }
            if (Math.abs(b) >= 1.0E-10d) {
                int i3 = this.mode;
                if (i3 == 1 || i3 == 2) {
                    if (q >= 0.0d) {
                        double sqrt3 = Math.sqrt(q);
                        double b2 = sqrt3;
                        doubleR.f1626x = sqrt3 * sinlam;
                        doubleR.f1627y = (this.mode == 2 ? b2 : -b2) * coslam2;
                    } else {
                        doubleR.f1627y = 0.0d;
                        doubleR.f1626x = 0.0d;
                    }
                } else if (i3 == 3) {
                    double sqrt4 = Math.sqrt(2.0d / ((cosb * coslam2) + 1.0d));
                    double b3 = sqrt4;
                    doubleR.f1627y = sqrt4 * sinb * this.ymf;
                    doubleR.f1626x = this.xmf * b3 * cosb * sinlam;
                } else if (i3 == 4) {
                    double d5 = this.ymf;
                    double sqrt5 = Math.sqrt(2.0d / b);
                    double b4 = sqrt5;
                    doubleR.f1627y = d5 * sqrt5 * ((this.cosb1 * sinb) - ((this.sinb1 * cosb) * coslam2));
                    doubleR.f1626x = this.xmf * b4 * cosb * sinlam;
                }
            } else {
                throw new ProjectionException();
            }
        }
        return doubleR;
    }

    public Double projectInverse(double x, double y, Double lp) {
        double x2;
        double x3;
        double d;
        double y2 = y;
        Double doubleR = lp;
        double d2 = 0.0d;
        if (this.spherical) {
            double cosz = 0.0d;
            double sinz = 0.0d;
            double rh = MapMath.distance(x, y);
            double d3 = 0.5d * rh;
            doubleR.f1627y = d3;
            if (d3 <= 1.0d) {
                doubleR.f1627y = Math.asin(doubleR.f1627y) * 2.0d;
                if (this.mode == 4 || this.mode == 3) {
                    sinz = Math.sin(doubleR.f1627y);
                    cosz = Math.cos(doubleR.f1627y);
                }
                int i = this.mode;
                if (i != 1) {
                    if (i == 2) {
                        doubleR.f1627y -= 1.5707963267948966d;
                    } else if (i == 3) {
                        doubleR.f1627y = Math.abs(rh) <= 1.0E-10d ? 0.0d : Math.asin((y2 * sinz) / rh);
                        x3 = x * sinz;
                        y2 = cosz * rh;
                    } else if (i == 4) {
                        if (Math.abs(rh) <= 1.0E-10d) {
                            d = this.projectionLatitude;
                        } else {
                            d = Math.asin((this.sinphi0 * cosz) + (((y2 * sinz) * this.cosphi0) / rh));
                        }
                        doubleR.f1627y = d;
                        x3 = this.cosphi0 * sinz * x;
                        y2 = (cosz - (Math.sin(doubleR.f1627y) * this.sinphi0)) * rh;
                    }
                    x3 = x;
                } else {
                    y2 = -y2;
                    doubleR.f1627y = 1.5707963267948966d - doubleR.f1627y;
                    x3 = x;
                }
                if (!(y2 == 0.0d && (this.mode == 3 || this.mode == 4))) {
                    d2 = Math.atan2(x3, y2);
                }
                doubleR.f1626x = d2;
            } else {
                throw new ProjectionException();
            }
        } else {
            double ab = 0.0d;
            int i2 = this.mode;
            if (i2 == 1) {
                y2 = -y2;
            } else if (i2 != 2) {
                if (i2 == 3 || i2 == 4) {
                    double d4 = this.f1674dd;
                    double d5 = x / d4;
                    double x4 = d5;
                    double d6 = d4 * y2;
                    double y3 = d6;
                    double distance = MapMath.distance(d5, d6);
                    double rho = distance;
                    if (distance < 1.0E-10d) {
                        doubleR.f1626x = 0.0d;
                        doubleR.f1627y = this.projectionLatitude;
                        return doubleR;
                    }
                    double asin = Math.asin((0.5d * rho) / this.f1676rq) * 2.0d;
                    double sCe = asin;
                    double cCe = Math.cos(asin);
                    double sin = Math.sin(sCe);
                    double sCe2 = sin;
                    double x5 = sin * x4;
                    if (this.mode == 4) {
                        double d7 = this.f1675qp;
                        double x6 = x5;
                        double x7 = this.sinb1;
                        double d8 = cCe * x7;
                        double d9 = y3 * sCe2;
                        double d10 = this.cosb1;
                        double d11 = d8 + ((d9 * d10) / rho);
                        ab = d11;
                        double d12 = d7 * d11;
                        y2 = ((d10 * rho) * cCe) - ((x7 * y3) * sCe2);
                        x2 = x6;
                        doubleR = lp;
                    } else {
                        double x8 = x5;
                        double d13 = (y3 * sCe2) / rho;
                        ab = d13;
                        double d14 = this.f1675qp * d13;
                        y2 = rho * cCe;
                        x2 = x8;
                        doubleR = lp;
                    }
                    doubleR.f1626x = Math.atan2(x2, y2);
                    doubleR.f1627y = MapMath.authlat(Math.asin(ab), this.apa);
                } else {
                    x2 = x;
                    doubleR.f1626x = Math.atan2(x2, y2);
                    doubleR.f1627y = MapMath.authlat(Math.asin(ab), this.apa);
                }
            }
            double d15 = (x * x) + (y2 * y2);
            double q = d15;
            if (d15 == 0.0d) {
                Double doubleR2 = lp;
                doubleR2.f1626x = 0.0d;
                doubleR2.f1627y = this.projectionLatitude;
                return doubleR2;
            }
            doubleR = lp;
            double ab2 = 1.0d - (q / this.f1675qp);
            if (this.mode == 2) {
                x2 = x;
                ab = -ab2;
            } else {
                x2 = x;
                ab = ab2;
            }
            doubleR.f1626x = Math.atan2(x2, y2);
            doubleR.f1627y = MapMath.authlat(Math.asin(ab), this.apa);
        }
        return doubleR;
    }

    public boolean isEqualArea() {
        return true;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Lambert Equal Area Azimuthal";
    }
}
