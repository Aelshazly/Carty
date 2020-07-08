package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

class STSProjection extends ConicProjection {
    private double C_p;
    private double C_x;
    private double C_y;
    private boolean tan_mode;

    protected STSProjection(double p, double q, boolean mode) {
        this.f1736es = 0.0d;
        this.C_x = q / p;
        this.C_y = p;
        this.C_p = 1.0d / q;
        this.tan_mode = mode;
        initialize();
    }

    public Double project(double lplam, double lpphi, Double xy) {
        xy.f1626x = this.C_x * lplam * Math.cos(lpphi);
        xy.f1627y = this.C_y;
        double lpphi2 = lpphi * this.C_p;
        double c = Math.cos(lpphi2);
        if (this.tan_mode) {
            xy.f1626x *= c * c;
            xy.f1627y *= Math.tan(lpphi2);
        } else {
            xy.f1626x /= c;
            xy.f1627y *= Math.sin(lpphi2);
        }
        return xy;
    }

    public Double projectInverse(double xyx, double xyy, Double lp) {
        double xyy2 = xyy / this.C_y;
        double atan = this.tan_mode ? Math.atan(xyy2) : MapMath.asin(xyy2);
        lp.f1627y = atan;
        double c = Math.cos(atan);
        lp.f1627y /= this.C_p;
        double d = this.C_x;
        double d2 = lp.f1627y / this.C_p;
        lp.f1627y = d2;
        lp.f1626x = xyx / (d * Math.cos(d2));
        if (this.tan_mode) {
            lp.f1626x /= c * c;
        } else {
            lp.f1626x *= c;
        }
        return lp;
    }

    public boolean hasInverse() {
        return true;
    }
}
