package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class URMFPSProjection extends Projection {
    private static final double C_x = 0.8773826753d;

    /* renamed from: Cy */
    private static final double f1748Cy = 1.139753528477d;
    private double C_y;

    /* renamed from: n */
    private double f1749n = 0.8660254037844386d;

    public Double project(double lplam, double lpphi, Double out) {
        out.f1627y = MapMath.asin(this.f1749n * Math.sin(lpphi));
        out.f1626x = C_x * lplam * Math.cos(lpphi);
        out.f1627y = this.C_y * lpphi;
        return out;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        double xyy2 = xyy / this.C_y;
        out.f1627y = MapMath.asin(Math.sin(xyy2) / this.f1749n);
        out.f1626x = xyx / (Math.cos(xyy2) * C_x);
        return out;
    }

    public boolean hasInverse() {
        return true;
    }

    public void initialize() {
        super.initialize();
        double d = this.f1749n;
        if (d <= 0.0d || d > 1.0d) {
            throw new ProjectionException("-40");
        }
        this.C_y = f1748Cy / d;
    }

    public void setN(double n) {
        this.f1749n = n;
    }

    public double getN() {
        return this.f1749n;
    }

    public String toString() {
        return "Urmaev Flat-Polar Sinusoidal";
    }
}
