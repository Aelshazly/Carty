package com.google.maps.android.geometry;

public class Point {

    /* renamed from: x */
    public final double f89x;

    /* renamed from: y */
    public final double f90y;

    public Point(double x, double y) {
        this.f89x = x;
        this.f90y = y;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Point{x=");
        sb.append(this.f89x);
        sb.append(", y=");
        sb.append(this.f90y);
        sb.append('}');
        return sb.toString();
    }
}
