package com.jhlabs.map.proj;

public class CylindricalProjection extends Projection {
    public boolean isRectilinear() {
        return true;
    }

    public String toString() {
        return "Cylindrical";
    }
}
