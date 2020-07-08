package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class SimpleConicProjection extends ConicProjection {
    private static final double EPS = 1.0E-10d;
    private static final double EPS10 = 1.0E-10d;
    public static final int EULER = 0;
    public static final int MURD1 = 1;
    public static final int MURD2 = 2;
    public static final int MURD3 = 3;
    public static final int PCONIC = 4;
    public static final int TISSOT = 5;
    public static final int VITK1 = 6;

    /* renamed from: c1 */
    private double f1744c1;

    /* renamed from: c2 */
    private double f1745c2;

    /* renamed from: n */
    private double f1746n;
    private double rho_0;
    private double rho_c;
    private double sig;
    private int type;

    public SimpleConicProjection() {
        this(0);
    }

    public SimpleConicProjection(int type2) {
        this.type = type2;
        this.minLatitude = Math.toRadians(0.0d);
        this.maxLatitude = Math.toRadians(80.0d);
    }

    public String toString() {
        return "Simple Conic";
    }

    public Double project(double lplam, double lpphi, Double out) {
        double rho;
        int i = this.type;
        if (i == 2) {
            rho = this.rho_c + Math.tan(this.sig - lpphi);
        } else if (i != 4) {
            rho = this.rho_c - lpphi;
        } else {
            rho = this.f1745c2 * (this.f1744c1 - Math.tan(lpphi));
        }
        double d = this.f1746n * lplam;
        double lplam2 = d;
        out.f1626x = Math.sin(d) * rho;
        out.f1627y = this.rho_0 - (Math.cos(lplam2) * rho);
        return out;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        double d = this.rho_0 - xyy;
        out.f1627y = d;
        double rho = MapMath.distance(xyx, d);
        if (this.f1746n < 0.0d) {
            rho = -rho;
            out.f1626x = -xyx;
            out.f1627y = -xyy;
        }
        out.f1626x = Math.atan2(xyx, xyy) / this.f1746n;
        int i = this.type;
        if (i == 2) {
            out.f1627y = this.sig - Math.atan(rho - this.rho_c);
        } else if (i != 4) {
            out.f1627y = this.rho_c - rho;
        } else {
            out.f1627y = Math.atan(this.f1744c1 - (rho / this.f1745c2)) + this.sig;
        }
        return out;
    }

    public boolean hasInverse() {
        return true;
    }

    public void initialize() {
        super.initialize();
        double p1 = Math.toRadians(30.0d);
        double p2 = Math.toRadians(60.0d);
        double del = (p2 - p1) * 0.5d;
        this.sig = (p2 + p1) * 0.5d;
        int err = (Math.abs(del) < 1.0E-10d || Math.abs(this.sig) < 1.0E-10d) ? -42 : 0;
        if (err == 0) {
            switch (this.type) {
                case 0:
                    this.f1746n = (Math.sin(this.sig) * Math.sin(del)) / del;
                    double del2 = del * 0.5d;
                    this.rho_c = (del2 / (Math.tan(del2) * Math.tan(this.sig))) + this.sig;
                    this.rho_0 = this.rho_c - this.projectionLatitude;
                    return;
                case 1:
                    this.rho_c = (Math.sin(del) / (Math.tan(this.sig) * del)) + this.sig;
                    this.rho_0 = this.rho_c - this.projectionLatitude;
                    this.f1746n = Math.sin(this.sig);
                    return;
                case 2:
                    double sqrt = Math.sqrt(Math.cos(del));
                    double cs = sqrt;
                    this.rho_c = sqrt / Math.tan(this.sig);
                    this.rho_0 = this.rho_c + Math.tan(this.sig - this.projectionLatitude);
                    this.f1746n = Math.sin(this.sig) * cs;
                    return;
                case 3:
                    this.rho_c = (del / (Math.tan(this.sig) * Math.tan(del))) + this.sig;
                    this.rho_0 = this.rho_c - this.projectionLatitude;
                    this.f1746n = ((Math.sin(this.sig) * Math.sin(del)) * Math.tan(del)) / (del * del);
                    return;
                case 4:
                    this.f1746n = Math.sin(this.sig);
                    this.f1745c2 = Math.cos(del);
                    this.f1744c1 = 1.0d / Math.tan(this.sig);
                    double d = this.projectionLatitude - this.sig;
                    double del3 = d;
                    if (Math.abs(d) - 1.0E-10d < 1.5707963267948966d) {
                        this.rho_0 = this.f1745c2 * (this.f1744c1 - Math.tan(del3));
                        this.maxLatitude = Math.toRadians(60.0d);
                        return;
                    }
                    throw new ProjectionException("-43");
                case 5:
                    this.f1746n = Math.sin(this.sig);
                    double cs2 = Math.cos(del);
                    double d2 = this.f1746n;
                    this.rho_c = (d2 / cs2) + (cs2 / d2);
                    this.rho_0 = Math.sqrt((this.rho_c - (Math.sin(this.projectionLatitude) * 2.0d)) / this.f1746n);
                    return;
                case 6:
                    double tan = Math.tan(del);
                    double cs3 = tan;
                    this.f1746n = (tan * Math.sin(this.sig)) / del;
                    this.rho_c = (del / (Math.tan(this.sig) * cs3)) + this.sig;
                    this.rho_0 = this.rho_c - this.projectionLatitude;
                    return;
                default:
                    return;
            }
        } else {
            StringBuilder sb = new StringBuilder("Error ");
            sb.append(err);
            throw new ProjectionException(sb.toString());
        }
    }
}
