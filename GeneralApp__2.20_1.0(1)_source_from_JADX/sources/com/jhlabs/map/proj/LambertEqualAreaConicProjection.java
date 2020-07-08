package com.jhlabs.map.proj;

public class LambertEqualAreaConicProjection extends AlbersProjection {
    public LambertEqualAreaConicProjection() {
        this(false);
    }

    public LambertEqualAreaConicProjection(boolean south) {
        this.minLatitude = Math.toRadians(0.0d);
        this.maxLatitude = Math.toRadians(90.0d);
        this.projectionLatitude1 = south ? -0.7853981633974483d : 0.7853981633974483d;
        this.projectionLatitude2 = south ? -1.5707963267948966d : 1.5707963267948966d;
        initialize();
    }

    public String toString() {
        return "Lambert Equal Area Conic";
    }
}
