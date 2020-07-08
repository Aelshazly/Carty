package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class MercatorProjection extends CylindricalProjection {
    public MercatorProjection() {
        this.minLatitude = MapMath.degToRad(-85.0d);
        this.maxLatitude = MapMath.degToRad(85.0d);
    }

    public Double project(double lam, double phi, Double out) {
        if (this.spherical) {
            out.f1626x = this.scaleFactor * lam;
            out.f1627y = this.scaleFactor * Math.log(Math.tan((0.5d * phi) + 0.7853981633974483d));
        } else {
            out.f1626x = this.scaleFactor * lam;
            out.f1627y = (-this.scaleFactor) * Math.log(MapMath.tsfn(phi, Math.sin(phi), this.f1735e));
        }
        return out;
    }

    public Double projectInverse(double x, double y, Double out) {
        if (this.spherical) {
            out.f1627y = 1.5707963267948966d - (Math.atan(Math.exp((-y) / this.scaleFactor)) * 2.0d);
            out.f1626x = x / this.scaleFactor;
        } else {
            out.f1627y = MapMath.phi2(Math.exp((-y) / this.scaleFactor), this.f1735e);
            out.f1626x = x / this.scaleFactor;
        }
        return out;
    }

    public boolean hasInverse() {
        return true;
    }

    public boolean isRectilinear() {
        return true;
    }

    public int getEPSGCode() {
        return 9804;
    }

    public String toString() {
        return "Mercator";
    }
}
