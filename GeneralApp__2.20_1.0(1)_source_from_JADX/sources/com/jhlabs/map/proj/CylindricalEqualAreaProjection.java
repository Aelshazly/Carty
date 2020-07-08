package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class CylindricalEqualAreaProjection extends Projection {
    private double[] apa;

    /* renamed from: qp */
    private double f1664qp;
    private double trueScaleLatitude;

    public CylindricalEqualAreaProjection() {
        this(0.0d, 0.0d, 0.0d);
    }

    public CylindricalEqualAreaProjection(double projectionLatitude, double projectionLongitude, double trueScaleLatitude2) {
        this.projectionLatitude = projectionLatitude;
        this.projectionLongitude = projectionLongitude;
        this.trueScaleLatitude = trueScaleLatitude2;
        initialize();
    }

    public void initialize() {
        super.initialize();
        double t = this.trueScaleLatitude;
        this.scaleFactor = Math.cos(t);
        if (this.f1736es != 0.0d) {
            double t2 = Math.sin(t);
            this.scaleFactor /= Math.sqrt(1.0d - ((this.f1736es * t2) * t2));
            this.apa = MapMath.authset(this.f1736es);
            this.f1664qp = MapMath.qsfn(1.0d, this.f1735e, this.one_es);
        }
    }

    public Double project(double lam, double phi, Double xy) {
        if (this.spherical) {
            xy.f1626x = this.scaleFactor * lam;
            xy.f1627y = Math.sin(phi) / this.scaleFactor;
        } else {
            xy.f1626x = this.scaleFactor * lam;
            xy.f1627y = (MapMath.qsfn(Math.sin(phi), this.f1735e, this.one_es) * 0.5d) / this.scaleFactor;
        }
        return xy;
    }

    public Double projectInverse(double x, double y, Double lp) {
        if (this.spherical) {
            double d = this.scaleFactor * y;
            double y2 = d;
            double abs = Math.abs(d);
            double t = abs;
            if (abs - 1.0E-10d <= 1.0d) {
                if (t >= 1.0d) {
                    lp.f1627y = y2 < 0.0d ? -1.5707963267948966d : 1.5707963267948966d;
                } else {
                    lp.f1627y = Math.asin(y2);
                }
                lp.f1626x = x / this.scaleFactor;
            } else {
                throw new ProjectionException();
            }
        } else {
            lp.f1627y = MapMath.authlat(Math.asin(((2.0d * y) * this.scaleFactor) / this.f1664qp), this.apa);
            lp.f1626x = x / this.scaleFactor;
        }
        return lp;
    }

    public boolean hasInverse() {
        return true;
    }

    public boolean isRectilinear() {
        return true;
    }
}
