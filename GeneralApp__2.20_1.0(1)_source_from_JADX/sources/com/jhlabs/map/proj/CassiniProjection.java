package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class CassiniProjection extends Projection {

    /* renamed from: C1 */
    private static final double f1645C1 = 0.16666666666666666d;

    /* renamed from: C2 */
    private static final double f1646C2 = 0.008333333333333333d;

    /* renamed from: C3 */
    private static final double f1647C3 = 0.041666666666666664d;

    /* renamed from: C4 */
    private static final double f1648C4 = 0.3333333333333333d;

    /* renamed from: C5 */
    private static final double f1649C5 = 0.06666666666666667d;
    private static final double EPS10 = 1.0E-10d;

    /* renamed from: a1 */
    private double f1650a1;

    /* renamed from: a2 */
    private double f1651a2;

    /* renamed from: c */
    private double f1652c;

    /* renamed from: d2 */
    private double f1653d2;

    /* renamed from: dd */
    private double f1654dd;

    /* renamed from: en */
    private double[] f1655en;

    /* renamed from: m0 */
    private double f1656m0;

    /* renamed from: n */
    private double f1657n;

    /* renamed from: r */
    private double f1658r;

    /* renamed from: t */
    private double f1659t;

    /* renamed from: tn */
    private double f1660tn;

    public CassiniProjection() {
        this.projectionLatitude = Math.toRadians(0.0d);
        this.projectionLongitude = Math.toRadians(0.0d);
        this.minLongitude = Math.toRadians(-90.0d);
        this.maxLongitude = Math.toRadians(90.0d);
        initialize();
    }

    public Double project(double lplam, double lpphi, Double xy) {
        Double doubleR = xy;
        if (this.spherical) {
            doubleR.f1626x = Math.asin(Math.cos(lpphi) * Math.sin(lplam));
            doubleR.f1627y = Math.atan2(Math.tan(lpphi), Math.cos(lplam)) - this.projectionLatitude;
        } else {
            double sin = Math.sin(lpphi);
            this.f1657n = sin;
            double cos = Math.cos(lpphi);
            this.f1652c = cos;
            doubleR.f1627y = MapMath.mlfn(lpphi, sin, cos, this.f1655en);
            double d = this.f1736es;
            double d2 = this.f1657n;
            this.f1657n = 1.0d / Math.sqrt(1.0d - ((d * d2) * d2));
            this.f1660tn = Math.tan(lpphi);
            double d3 = this.f1660tn;
            this.f1659t = d3 * d3;
            double d4 = this.f1652c;
            this.f1650a1 = lplam * d4;
            this.f1652c = d4 * ((this.f1736es * this.f1652c) / (1.0d - this.f1736es));
            double d5 = this.f1650a1;
            this.f1651a2 = d5 * d5;
            double d6 = this.f1657n * d5;
            double d7 = this.f1651a2;
            double d8 = this.f1659t;
            doubleR.f1626x = d6 * (1.0d - ((d7 * d8) * (f1645C1 - ((((8.0d - d8) + (this.f1652c * 8.0d)) * d7) * f1646C2))));
            double d9 = doubleR.f1627y;
            double d10 = this.f1656m0;
            double d11 = this.f1657n * this.f1660tn;
            double d12 = this.f1651a2;
            doubleR.f1627y = d9 - (d10 - ((d11 * d12) * (((((5.0d - this.f1659t) + (this.f1652c * 6.0d)) * d12) * f1647C3) + 0.5d)));
        }
        return doubleR;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        Double doubleR = out;
        if (this.spherical) {
            double d = xyy + this.projectionLatitude;
            this.f1654dd = d;
            doubleR.f1627y = Math.asin(Math.sin(d) * Math.cos(xyx));
            doubleR.f1626x = Math.atan2(Math.tan(xyx), Math.cos(this.f1654dd));
        } else {
            double ph1 = MapMath.inv_mlfn(this.f1656m0 + xyy, this.f1736es, this.f1655en);
            this.f1660tn = Math.tan(ph1);
            double d2 = this.f1660tn;
            this.f1659t = d2 * d2;
            this.f1657n = Math.sin(ph1);
            double d3 = this.f1736es;
            double d4 = this.f1657n;
            this.f1658r = 1.0d / (1.0d - ((d3 * d4) * d4));
            this.f1657n = Math.sqrt(this.f1658r);
            double d5 = this.f1658r;
            double d6 = 1.0d - this.f1736es;
            double d7 = this.f1657n;
            this.f1658r = d5 * d6 * d7;
            this.f1654dd = xyx / d7;
            double d8 = this.f1654dd;
            this.f1653d2 = d8 * d8;
            double d9 = (d7 * this.f1660tn) / this.f1658r;
            double d10 = this.f1653d2;
            double d11 = d9 * d10;
            double d12 = this.f1659t;
            doubleR.f1627y = ph1 - (d11 * (0.5d - ((((d12 * 3.0d) + 1.0d) * d10) * f1647C3)));
            doubleR.f1626x = (d8 * (((d12 * d10) * (((((d12 * 3.0d) + 1.0d) * d10) * f1649C5) - 13.333333333333334d)) + 1.0d)) / Math.cos(ph1);
        }
        return doubleR;
    }

    public void initialize() {
        super.initialize();
        if (!this.spherical) {
            double[] enfn = MapMath.enfn(this.f1736es);
            this.f1655en = enfn;
            if (enfn != null) {
                this.f1656m0 = MapMath.mlfn(this.projectionLatitude, Math.sin(this.projectionLatitude), Math.cos(this.projectionLatitude), this.f1655en);
                return;
            }
            throw new IllegalArgumentException();
        }
    }

    public boolean hasInverse() {
        return true;
    }

    public int getEPSGCode() {
        return 9806;
    }

    public String toString() {
        return "Cassini";
    }
}
