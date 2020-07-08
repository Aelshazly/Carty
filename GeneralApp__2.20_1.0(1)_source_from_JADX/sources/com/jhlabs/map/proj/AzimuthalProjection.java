package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;

public class AzimuthalProjection extends Projection {
    public static final int EQUATOR = 3;
    public static final int NORTH_POLE = 1;
    public static final int OBLIQUE = 4;
    public static final int SOUTH_POLE = 2;
    protected double cosphi0;
    private double mapRadius;
    protected int mode;
    protected double sinphi0;

    public AzimuthalProjection() {
        this(Math.toRadians(45.0d), Math.toRadians(45.0d));
    }

    public AzimuthalProjection(double projectionLatitude, double projectionLongitude) {
        this.mapRadius = 90.0d;
        this.projectionLatitude = projectionLatitude;
        this.projectionLongitude = projectionLongitude;
        initialize();
    }

    public void initialize() {
        super.initialize();
        if (Math.abs(Math.abs(this.projectionLatitude) - 1.5707963267948966d) < 1.0E-10d) {
            this.mode = this.projectionLatitude < 0.0d ? 2 : 1;
        } else if (Math.abs(this.projectionLatitude) > 1.0E-10d) {
            this.mode = 4;
            this.sinphi0 = Math.sin(this.projectionLatitude);
            this.cosphi0 = Math.cos(this.projectionLatitude);
        } else {
            this.mode = 3;
        }
    }

    public boolean inside(double lon, double lat) {
        return MapMath.greatCircleDistance(Math.toRadians(lon), Math.toRadians(lat), this.projectionLongitude, this.projectionLatitude) < Math.toRadians(this.mapRadius);
    }

    public void setMapRadius(double mapRadius2) {
        this.mapRadius = mapRadius2;
    }

    public double getMapRadius() {
        return this.mapRadius;
    }
}
