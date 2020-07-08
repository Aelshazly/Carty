package com.jhlabs.map;

public class Point2D {

    public static class Double {

        /* renamed from: x */
        public double f1626x;

        /* renamed from: y */
        public double f1627y;

        public Double() {
            this.f1627y = 0.0d;
            this.f1626x = 0.0d;
        }

        public Double(double xIn, double yIn) {
            this.f1626x = xIn;
            this.f1627y = yIn;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("com.jhlabs.map.Point2D.Double: x=");
            sb.append(this.f1626x);
            sb.append(" y=");
            sb.append(this.f1627y);
            return sb.toString();
        }
    }
}
