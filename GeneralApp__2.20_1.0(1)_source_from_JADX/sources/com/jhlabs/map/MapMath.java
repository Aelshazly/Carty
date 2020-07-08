package com.jhlabs.map;

import com.jhlabs.map.Rectangle2D.Double;
import com.jhlabs.map.proj.ProjectionException;

public class MapMath {
    private static final double C00 = 1.0d;
    private static final double C02 = 0.25d;
    private static final double C04 = 0.046875d;
    private static final double C06 = 0.01953125d;
    private static final double C08 = 0.01068115234375d;
    private static final double C22 = 0.75d;
    private static final double C44 = 0.46875d;
    private static final double C46 = 0.013020833333333334d;
    private static final double C48 = 0.007120768229166667d;
    private static final double C66 = 0.3645833333333333d;
    private static final double C68 = 0.005696614583333333d;
    private static final double C88 = 0.3076171875d;
    public static final int COLLINEAR = 2;
    public static final int DONT_INTERSECT = 0;
    public static final int DO_INTERSECT = 1;
    public static final double DTR = 0.017453292519943295d;
    public static final double HALFPI = 1.5707963267948966d;
    private static final int MAX_ITER = 10;
    private static final int N_ITER = 15;
    private static final double P00 = 0.3333333333333333d;
    private static final double P01 = 0.17222222222222222d;
    private static final double P02 = 0.10257936507936508d;
    private static final double P10 = 0.06388888888888888d;
    private static final double P11 = 0.0664021164021164d;
    private static final double P20 = 0.016415012942191543d;
    public static final double QUARTERPI = 0.7853981633974483d;
    public static final double RTD = 57.29577951308232d;
    public static final double TWOPI = 6.283185307179586d;
    public static final Rectangle2D WORLD_BOUNDS;
    public static final Rectangle2D WORLD_BOUNDS_RAD;

    static {
        Double doubleR = new Double(-3.141592653589793d, -1.5707963267948966d, 6.283185307179586d, 3.141592653589793d);
        WORLD_BOUNDS_RAD = doubleR;
        Double doubleR2 = new Double(-180.0d, -90.0d, 360.0d, 180.0d);
        WORLD_BOUNDS = doubleR2;
    }

    public static double sind(double v) {
        return Math.sin(0.017453292519943295d * v);
    }

    public static double cosd(double v) {
        return Math.cos(0.017453292519943295d * v);
    }

    public static double tand(double v) {
        return Math.tan(0.017453292519943295d * v);
    }

    public static double asind(double v) {
        return Math.asin(v) * 57.29577951308232d;
    }

    public static double acosd(double v) {
        return Math.acos(v) * 57.29577951308232d;
    }

    public static double atand(double v) {
        return Math.atan(v) * 57.29577951308232d;
    }

    public static double atan2d(double y, double x) {
        return Math.atan2(y, x) * 57.29577951308232d;
    }

    public static double asin(double v) {
        if (Math.abs(v) <= 1.0d) {
            return Math.asin(v);
        }
        return v < 0.0d ? -1.5707963267948966d : 1.5707963267948966d;
    }

    public static double acos(double v) {
        if (Math.abs(v) <= 1.0d) {
            return Math.acos(v);
        }
        double d = 0.0d;
        if (v < 0.0d) {
            d = 3.141592653589793d;
        }
        return d;
    }

    public static double sqrt(double v) {
        if (v < 0.0d) {
            return 0.0d;
        }
        return Math.sqrt(v);
    }

    public static double distance(double dx, double dy) {
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    public static double distance(Point2D.Double a, Point2D.Double b) {
        return distance(a.f1626x - b.f1626x, a.f1627y - b.f1627y);
    }

    public static double hypot(double x, double y) {
        if (x < 0.0d) {
            x = -x;
        } else if (x == 0.0d) {
            return y < 0.0d ? -y : y;
        }
        if (y < 0.0d) {
            y = -y;
        } else if (y == 0.0d) {
            return x;
        }
        if (x < y) {
            double x2 = x / y;
            return Math.sqrt((x2 * x2) + 1.0d) * y;
        }
        double y2 = y / x;
        return Math.sqrt((y2 * y2) + 1.0d) * x;
    }

    public static double atan2(double y, double x) {
        return Math.atan2(y, x);
    }

    public static double trunc(double v) {
        return v < 0.0d ? Math.ceil(v) : Math.floor(v);
    }

    public static double frac(double v) {
        return v - trunc(v);
    }

    public static double degToRad(double v) {
        return (3.141592653589793d * v) / 180.0d;
    }

    public static double radToDeg(double v) {
        return (180.0d * v) / 3.141592653589793d;
    }

    public static double dmsToRad(double d, double m, double s) {
        if (d >= 0.0d) {
            return ((((m / 60.0d) + d) + (s / 3600.0d)) * 3.141592653589793d) / 180.0d;
        }
        return (((d - (m / 60.0d)) - (s / 3600.0d)) * 3.141592653589793d) / 180.0d;
    }

    public static double dmsToDeg(double d, double m, double s) {
        if (d >= 0.0d) {
            return (m / 60.0d) + d + (s / 3600.0d);
        }
        return (d - (m / 60.0d)) - (s / 3600.0d);
    }

    public static double normalizeLatitude(double angle) {
        if (Double.isInfinite(angle) || Double.isNaN(angle)) {
            throw new ProjectionException("Infinite latitude");
        }
        while (angle > 1.5707963267948966d) {
            angle -= 3.141592653589793d;
        }
        while (angle < -1.5707963267948966d) {
            angle += 3.141592653589793d;
        }
        return angle;
    }

    public static double normalizeLongitude(double angle) {
        if (Double.isInfinite(angle) || Double.isNaN(angle)) {
            throw new ProjectionException("Infinite longitude");
        }
        while (angle > 3.141592653589793d) {
            angle -= 6.283185307179586d;
        }
        while (angle < -3.141592653589793d) {
            angle += 6.283185307179586d;
        }
        return angle;
    }

    public static double normalizeAngle(double angle) {
        if (Double.isInfinite(angle) || Double.isNaN(angle)) {
            throw new ProjectionException("Infinite angle");
        }
        while (angle > 6.283185307179586d) {
            angle -= 6.283185307179586d;
        }
        while (angle < 0.0d) {
            angle += 6.283185307179586d;
        }
        return angle;
    }

    public static double greatCircleDistance(double lon1, double lat1, double lon2, double lat2) {
        double dlat = Math.sin((lat2 - lat1) / 2.0d);
        double dlon = Math.sin((lon2 - lon1) / 2.0d);
        return Math.asin(Math.sqrt((dlat * dlat) + (Math.cos(lat1) * Math.cos(lat2) * dlon * dlon))) * 2.0d;
    }

    public static double sphericalAzimuth(double lat0, double lon0, double lat, double lon) {
        double diff = lon - lon0;
        double coslat = Math.cos(lat);
        return Math.atan2(Math.sin(diff) * coslat, (Math.cos(lat0) * Math.sin(lat)) - ((Math.sin(lat0) * coslat) * Math.cos(diff)));
    }

    public static boolean sameSigns(double a, double b) {
        return ((a > 0.0d ? 1 : (a == 0.0d ? 0 : -1)) < 0) == ((b > 0.0d ? 1 : (b == 0.0d ? 0 : -1)) < 0);
    }

    public static boolean sameSigns(int a, int b) {
        return (a < 0) == (b < 0);
    }

    public static double takeSign(double a, double b) {
        double a2 = Math.abs(a);
        if (b < 0.0d) {
            return -a2;
        }
        return a2;
    }

    public static int takeSign(int a, int b) {
        int a2 = Math.abs(a);
        if (b < 0) {
            return -a2;
        }
        return a2;
    }

    public static int intersectSegments(Point2D.Double aStart, Point2D.Double aEnd, Point2D.Double bStart, Point2D.Double bEnd, Point2D.Double p) {
        Point2D.Double doubleR = aStart;
        Point2D.Double doubleR2 = aEnd;
        Point2D.Double doubleR3 = bStart;
        Point2D.Double doubleR4 = bEnd;
        Point2D.Double doubleR5 = p;
        double a1 = doubleR2.f1627y - doubleR.f1627y;
        double b1 = doubleR.f1626x - doubleR2.f1626x;
        double c1 = (doubleR2.f1626x * doubleR.f1627y) - (doubleR.f1626x * doubleR2.f1627y);
        double r3 = (doubleR3.f1626x * a1) + (doubleR3.f1627y * b1) + c1;
        double a12 = a1;
        double r4 = (doubleR4.f1626x * a1) + (doubleR4.f1627y * b1) + c1;
        if (r3 != 0.0d && r4 != 0.0d && sameSigns(r3, r4)) {
            return 0;
        }
        double d = r3;
        double a2 = doubleR4.f1627y - doubleR3.f1627y;
        double d2 = r4;
        double b2 = doubleR3.f1626x - doubleR4.f1626x;
        double c12 = c1;
        double b12 = b1;
        double c2 = (doubleR4.f1626x * doubleR3.f1627y) - (doubleR3.f1626x * doubleR4.f1627y);
        double r1 = (doubleR.f1626x * a2) + (doubleR.f1627y * b2) + c2;
        double r2 = (doubleR2.f1626x * a2) + (doubleR2.f1627y * b2) + c2;
        if (r1 != 0.0d && r2 != 0.0d && sameSigns(r1, r2)) {
            return 0;
        }
        double denom = (a12 * b2) - (a2 * b12);
        if (denom == 0.0d) {
            return 2;
        }
        double offset = denom < 0.0d ? (-denom) / 2.0d : denom / 2.0d;
        double num = (b12 * c2) - (b2 * c12);
        double d3 = r1;
        Point2D.Double doubleR6 = p;
        doubleR6.f1626x = (num < 0.0d ? num - offset : num + offset) / denom;
        double num2 = (a2 * c12) - (a12 * c2);
        double d4 = offset;
        doubleR6.f1627y = (num2 < 0.0d ? num2 - offset : num2 + offset) / denom;
        return 1;
    }

    public static double dot(Point2D.Double a, Point2D.Double b) {
        return (a.f1626x * b.f1626x) + (a.f1627y * b.f1627y);
    }

    public static Point2D.Double perpendicular(Point2D.Double a) {
        return new Point2D.Double(-a.f1627y, a.f1626x);
    }

    public static Point2D.Double add(Point2D.Double a, Point2D.Double b) {
        return new Point2D.Double(a.f1626x + b.f1626x, a.f1627y + b.f1627y);
    }

    public static Point2D.Double subtract(Point2D.Double a, Point2D.Double b) {
        return new Point2D.Double(a.f1626x - b.f1626x, a.f1627y - b.f1627y);
    }

    public static Point2D.Double multiply(Point2D.Double a, Point2D.Double b) {
        return new Point2D.Double(a.f1626x * b.f1626x, a.f1627y * b.f1627y);
    }

    public static double cross(Point2D.Double a, Point2D.Double b) {
        return (a.f1626x * b.f1627y) - (b.f1626x * a.f1627y);
    }

    public static double cross(double x1, double y1, double x2, double y2) {
        return (x1 * y2) - (x2 * y1);
    }

    public static void normalize(Point2D.Double a) {
        double d = distance(a.f1626x, a.f1627y);
        a.f1626x /= d;
        a.f1627y /= d;
    }

    public static void negate(Point2D.Double a) {
        a.f1626x = -a.f1626x;
        a.f1627y = -a.f1627y;
    }

    public static double longitudeDistance(double l1, double l2) {
        return Math.min(Math.abs(l1 - l2), (l1 < 0.0d ? l1 + 3.141592653589793d : 3.141592653589793d - l1) + (l2 < 0.0d ? 3.141592653589793d + l2 : 3.141592653589793d - l2));
    }

    public static double geocentricLatitude(double lat, double flatness) {
        double f = 1.0d - flatness;
        return Math.atan(f * f * Math.tan(lat));
    }

    public static double geographicLatitude(double lat, double flatness) {
        double f = 1.0d - flatness;
        return Math.atan(Math.tan(lat) / (f * f));
    }

    public static double tsfn(double phi, double sinphi, double e) {
        double sinphi2 = sinphi * e;
        return Math.tan((1.5707963267948966d - phi) * 0.5d) / Math.pow((1.0d - sinphi2) / (1.0d + sinphi2), 0.5d * e);
    }

    public static double msfn(double sinphi, double cosphi, double es) {
        return cosphi / Math.sqrt(1.0d - ((es * sinphi) * sinphi));
    }

    public static double phi2(double ts, double e) {
        double eccnth = 0.5d * e;
        double phi = 1.5707963267948966d - (Math.atan(ts) * 2.0d);
        int i = 15;
        do {
            double con = Math.sin(phi) * e;
            double dphi = (1.5707963267948966d - (Math.atan(Math.pow((1.0d - con) / (1.0d + con), eccnth) * ts) * 2.0d)) - phi;
            phi += dphi;
            if (Math.abs(dphi) <= 1.0E-10d) {
                break;
            }
            i--;
        } while (i != 0);
        if (i > 0) {
            return phi;
        }
        throw new ProjectionException();
    }

    public static double[] enfn(double es) {
        double d = es * es;
        double d2 = d * es;
        return new double[]{1.0d - (((((((es * C08) + C06) * es) + C04) * es) + C02) * es), (C22 - (((((C08 * es) + C06) * es) + C04) * es)) * es, d * (C44 - (((C48 * es) + C46) * es)), d2 * (C66 - (C68 * es)), d2 * es * C88};
    }

    public static double mlfn(double phi, double sphi, double cphi, double[] en) {
        double sphi2 = sphi * sphi;
        return (en[0] * phi) - ((en[1] + ((en[2] + ((en[3] + (en[4] * sphi2)) * sphi2)) * sphi2)) * (cphi * sphi));
    }

    public static double inv_mlfn(double arg, double es, double[] en) {
        double k = 1.0d / (1.0d - es);
        double phi = arg;
        for (int i = 10; i != 0; i--) {
            double s = Math.sin(phi);
            double t = 1.0d - ((es * s) * s);
            double mlfn = (mlfn(phi, s, Math.cos(phi), en) - arg) * Math.sqrt(t) * t * k;
            phi -= mlfn;
            if (Math.abs(mlfn) < 1.0E-11d) {
                return phi;
            }
        }
        return phi;
    }

    public static double[] authset(double es) {
        double[] APA = new double[3];
        APA[0] = P00 * es;
        double t = es * es;
        APA[0] = APA[0] + (P01 * t);
        APA[1] = P10 * t;
        double t2 = t * es;
        APA[0] = APA[0] + (P02 * t2);
        APA[1] = APA[1] + (P11 * t2);
        APA[2] = P20 * t2;
        return APA;
    }

    public static double authlat(double beta, double[] APA) {
        double t = beta + beta;
        return (APA[0] * Math.sin(t)) + beta + (APA[1] * Math.sin(t + t)) + (APA[2] * Math.sin(t + t + t));
    }

    public static double qsfn(double sinphi, double e, double one_es) {
        if (e < 1.0E-7d) {
            return sinphi + sinphi;
        }
        double con = e * sinphi;
        return ((sinphi / (1.0d - (con * con))) - ((0.5d / e) * Math.log((1.0d - con) / (1.0d + con)))) * one_es;
    }

    public static double niceNumber(double x, boolean round) {
        double nf;
        int expv = (int) Math.floor(Math.log(x) / Math.log(10.0d));
        double f = x / Math.pow(10.0d, (double) expv);
        if (round) {
            if (f < 1.5d) {
                nf = 1.0d;
            } else if (f < 3.0d) {
                nf = 2.0d;
            } else if (f < 7.0d) {
                nf = 5.0d;
            } else {
                nf = 10.0d;
            }
        } else if (f <= 1.0d) {
            nf = 1.0d;
        } else if (f <= 2.0d) {
            nf = 2.0d;
        } else if (f <= 5.0d) {
            nf = 5.0d;
        } else {
            nf = 10.0d;
        }
        return Math.pow(10.0d, (double) expv) * nf;
    }
}
