package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class MolleweideProjection extends PseudoCylindricalProjection {
    private static final int MAX_ITER = 10;
    public static final int MOLLEWEIDE = 0;
    private static final double TOLERANCE = 1.0E-7d;
    public static final int WAGNER4 = 1;
    public static final int WAGNER5 = 2;

    /* renamed from: cp */
    private double f1720cp;

    /* renamed from: cx */
    private double f1721cx;

    /* renamed from: cy */
    private double f1722cy;
    private int type;

    public MolleweideProjection() {
        this(1.5707963267948966d);
    }

    public MolleweideProjection(int type2) {
        this.type = 0;
        this.type = type2;
        if (type2 == 0) {
            init(1.5707963267948966d);
        } else if (type2 == 1) {
            init(1.0471975511965976d);
        } else if (type2 == 2) {
            init(1.5707963267948966d);
            this.f1721cx = 0.90977d;
            this.f1722cy = 1.65014d;
            this.f1720cp = 3.00896d;
        }
    }

    public MolleweideProjection(double p) {
        this.type = 0;
        init(p);
    }

    public void init(double p) {
        double p2 = p + p;
        double sp = Math.sin(p);
        double r = Math.sqrt((6.283185307179586d * sp) / (Math.sin(p2) + p2));
        this.f1721cx = (2.0d * r) / 3.141592653589793d;
        this.f1722cy = r / sp;
        this.f1720cp = Math.sin(p2) + p2;
    }

    public MolleweideProjection(double cx, double cy, double cp) {
        this.type = 0;
        this.f1721cx = cx;
        this.f1722cy = cy;
        this.f1720cp = cp;
    }

    public Double project(double lplam, double lpphi, Double xy) {
        double lpphi2;
        double k = this.f1720cp * Math.sin(lpphi);
        int i = 10;
        while (i != 0) {
            double sin = ((Math.sin(lpphi) + lpphi) - k) / (Math.cos(lpphi) + 1.0d);
            lpphi -= sin;
            if (Math.abs(sin) < TOLERANCE) {
                break;
            }
            i--;
        }
        if (i == 0) {
            lpphi2 = lpphi < 0.0d ? -1.5707963267948966d : 1.5707963267948966d;
        } else {
            lpphi2 = lpphi * 0.5d;
        }
        xy.f1626x = this.f1721cx * lplam * Math.cos(lpphi2);
        xy.f1627y = this.f1722cy * Math.sin(lpphi2);
        return xy;
    }

    public Double projectInverse(double x, double y, Double lp) {
        double lat = Math.asin(y / this.f1722cy);
        double lon = x / (this.f1721cx * Math.cos(lat));
        double lat2 = lat + lat;
        double lat3 = Math.asin((Math.sin(lat2) + lat2) / this.f1720cp);
        lp.f1626x = lon;
        lp.f1627y = lat3;
        return lp;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        this.type = this.type;
        int i = this.type;
        if (i == 1) {
            return "Wagner IV";
        }
        if (i != 2) {
            return "Molleweide";
        }
        return "Wagner V";
    }
}
