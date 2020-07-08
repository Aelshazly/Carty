package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class TCCProjection extends CylindricalProjection {
    public TCCProjection() {
        this.minLongitude = MapMath.degToRad(-60.0d);
        this.maxLongitude = MapMath.degToRad(60.0d);
    }

    public Double project(double lplam, double lpphi, Double out) {
        double b = Math.cos(lpphi) * Math.sin(lplam);
        double d = 1.0d - (b * b);
        double bt = d;
        if (d >= 1.0E-10d) {
            out.f1626x = b / Math.sqrt(bt);
            out.f1627y = Math.atan2(Math.tan(lpphi), Math.cos(lplam));
            return out;
        }
        throw new ProjectionException("F");
    }

    public boolean isRectilinear() {
        return false;
    }

    public String toString() {
        return "Transverse Central Cylindrical";
    }
}
