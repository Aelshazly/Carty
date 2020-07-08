package com.jhlabs.map.proj;

public class PerspectiveConicProjection extends SimpleConicProjection {
    public PerspectiveConicProjection() {
        super(4);
    }

    public String toString() {
        return "Perspective Conic";
    }
}
