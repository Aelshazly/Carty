package com.jhlabs.map;

public abstract class Rectangle2D {

    public static class Double extends Rectangle2D {

        /* renamed from: h */
        double f1628h;

        /* renamed from: w */
        double f1629w;

        /* renamed from: x */
        double f1630x;

        /* renamed from: y */
        double f1631y;

        public Double(double xIn, double yIn, double wIn, double hIn) {
            this.f1630x = xIn;
            this.f1631y = yIn;
            this.f1629w = wIn;
            this.f1628h = hIn;
        }

        public double getX() {
            return this.f1630x;
        }

        public double getY() {
            return this.f1631y;
        }

        public double getWidth() {
            return this.f1629w;
        }

        public double getHeight() {
            return this.f1628h;
        }

        public void add(double px, double py) {
            double d = this.f1630x;
            if (px < d) {
                this.f1630x = px;
            } else if (px > this.f1629w + d) {
                this.f1629w = px - d;
            }
            double d2 = this.f1631y;
            if (py < d2) {
                this.f1631y = py;
            } else if (py > this.f1628h + d2) {
                this.f1628h = py - d2;
            }
        }
    }

    public abstract void add(double d, double d2);

    public abstract double getHeight();

    public abstract double getWidth();

    public abstract double getX();

    public abstract double getY();
}
