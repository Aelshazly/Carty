package com.jhlabs.map.proj;

import com.jhlabs.map.AngleFormat;
import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;
import com.jhlabs.map.Rectangle2D;
import java.text.FieldPosition;

public class Projection implements Cloneable {
    protected static final double DTR = 0.017453292519943295d;
    protected static final double EPS10 = 1.0E-10d;
    protected static final double RTD = 57.29577951308232d;

    /* renamed from: a */
    protected double f1734a = 0.0d;

    /* renamed from: e */
    protected double f1735e = 0.0d;
    protected Ellipsoid ellipsoid;

    /* renamed from: es */
    protected double f1736es = 0.0d;
    protected double falseEasting = 0.0d;
    protected double falseNorthing = 0.0d;
    protected double fromMetres = 1.0d;
    protected boolean geocentric;
    protected double maxLatitude = 1.5707963267948966d;
    protected double maxLongitude = 3.141592653589793d;
    protected double minLatitude = -1.5707963267948966d;
    protected double minLongitude = -3.141592653589793d;
    protected String name = null;
    protected double one_es = 0.0d;
    protected double projectionLatitude = 0.0d;
    protected double projectionLatitude1 = 0.0d;
    protected double projectionLatitude2 = 0.0d;
    protected double projectionLongitude = 0.0d;
    protected double rone_es = 0.0d;
    protected double scaleFactor = 1.0d;
    protected boolean spherical;
    private double totalFalseEasting = 0.0d;
    private double totalFalseNorthing = 0.0d;
    private double totalScale = 0.0d;
    protected double trueScaleLatitude = 0.0d;

    protected Projection() {
        setEllipsoid(Ellipsoid.SPHERE);
    }

    public Object clone() {
        try {
            return (Projection) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    public Double transform(Double src, Double dst) {
        double x = src.f1626x * 0.017453292519943295d;
        double d = this.projectionLongitude;
        if (d != 0.0d) {
            x = MapMath.normalizeLongitude(x - d);
        }
        project(x, src.f1627y * 0.017453292519943295d, dst);
        dst.f1626x = (this.totalScale * dst.f1626x) + this.totalFalseEasting;
        dst.f1627y = (this.totalScale * dst.f1627y) + this.totalFalseNorthing;
        return dst;
    }

    public Double transformRadians(Double src, Double dst) {
        double x = src.f1626x;
        double d = this.projectionLongitude;
        if (d != 0.0d) {
            x = MapMath.normalizeLongitude(x - d);
        }
        project(x, src.f1627y, dst);
        dst.f1626x = (this.totalScale * dst.f1626x) + this.totalFalseEasting;
        dst.f1627y = (this.totalScale * dst.f1627y) + this.totalFalseNorthing;
        return dst;
    }

    public Double project(double x, double y, Double dst) {
        dst.f1626x = x;
        dst.f1627y = y;
        return dst;
    }

    public void transform(double[] srcPoints, int srcOffset, double[] dstPoints, int dstOffset, int numPoints) {
        Double in = new Double();
        Double out = new Double();
        for (int i = 0; i < numPoints; i++) {
            int srcOffset2 = srcOffset + 1;
            in.f1626x = srcPoints[srcOffset];
            srcOffset = srcOffset2 + 1;
            in.f1627y = srcPoints[srcOffset2];
            transform(in, out);
            int dstOffset2 = dstOffset + 1;
            dstPoints[dstOffset] = out.f1626x;
            dstOffset = dstOffset2 + 1;
            dstPoints[dstOffset2] = out.f1627y;
        }
    }

    public void transformRadians(double[] srcPoints, int srcOffset, double[] dstPoints, int dstOffset, int numPoints) {
        Double in = new Double();
        Double out = new Double();
        for (int i = 0; i < numPoints; i++) {
            int srcOffset2 = srcOffset + 1;
            in.f1626x = srcPoints[srcOffset];
            srcOffset = srcOffset2 + 1;
            in.f1627y = srcPoints[srcOffset2];
            transform(in, out);
            int dstOffset2 = dstOffset + 1;
            dstPoints[dstOffset] = out.f1626x;
            dstOffset = dstOffset2 + 1;
            dstPoints[dstOffset2] = out.f1627y;
        }
    }

    public Double inverseTransform(Double src, Double dst) {
        projectInverse((src.f1626x - this.totalFalseEasting) / this.totalScale, (src.f1627y - this.totalFalseNorthing) / this.totalScale, dst);
        if (dst.f1626x < -3.141592653589793d) {
            dst.f1626x = -3.141592653589793d;
        } else if (dst.f1626x > 3.141592653589793d) {
            dst.f1626x = 3.141592653589793d;
        }
        if (this.projectionLongitude != 0.0d) {
            dst.f1626x = MapMath.normalizeLongitude(dst.f1626x + this.projectionLongitude);
        }
        dst.f1626x *= 57.29577951308232d;
        dst.f1627y *= 57.29577951308232d;
        return dst;
    }

    public Double inverseTransformRadians(Double src, Double dst) {
        projectInverse((src.f1626x - this.totalFalseEasting) / this.totalScale, (src.f1627y - this.totalFalseNorthing) / this.totalScale, dst);
        if (dst.f1626x < -3.141592653589793d) {
            dst.f1626x = -3.141592653589793d;
        } else if (dst.f1626x > 3.141592653589793d) {
            dst.f1626x = 3.141592653589793d;
        }
        if (this.projectionLongitude != 0.0d) {
            dst.f1626x = MapMath.normalizeLongitude(dst.f1626x + this.projectionLongitude);
        }
        return dst;
    }

    public Double projectInverse(double x, double y, Double dst) {
        dst.f1626x = x;
        dst.f1627y = y;
        return dst;
    }

    public void inverseTransform(double[] srcPoints, int srcOffset, double[] dstPoints, int dstOffset, int numPoints) {
        Double in = new Double();
        Double out = new Double();
        for (int i = 0; i < numPoints; i++) {
            int srcOffset2 = srcOffset + 1;
            in.f1626x = srcPoints[srcOffset];
            srcOffset = srcOffset2 + 1;
            in.f1627y = srcPoints[srcOffset2];
            inverseTransform(in, out);
            int dstOffset2 = dstOffset + 1;
            dstPoints[dstOffset] = out.f1626x;
            dstOffset = dstOffset2 + 1;
            dstPoints[dstOffset2] = out.f1627y;
        }
    }

    public void inverseTransformRadians(double[] srcPoints, int srcOffset, double[] dstPoints, int dstOffset, int numPoints) {
        Double in = new Double();
        Double out = new Double();
        for (int i = 0; i < numPoints; i++) {
            int srcOffset2 = srcOffset + 1;
            in.f1626x = srcPoints[srcOffset];
            srcOffset = srcOffset2 + 1;
            in.f1627y = srcPoints[srcOffset2];
            inverseTransformRadians(in, out);
            int dstOffset2 = dstOffset + 1;
            dstPoints[dstOffset] = out.f1626x;
            dstOffset = dstOffset2 + 1;
            dstPoints[dstOffset2] = out.f1627y;
        }
    }

    public Rectangle2D inverseTransform(Rectangle2D r) {
        Double in = new Double();
        Double out = new Double();
        Rectangle2D.Double doubleR = null;
        if (isRectilinear()) {
            for (int ix = 0; ix < 2; ix++) {
                double x = r.getX() + (r.getWidth() * ((double) ix));
                for (int iy = 0; iy < 2; iy++) {
                    double y = r.getY() + (r.getHeight() * ((double) iy));
                    in.f1626x = x;
                    in.f1627y = y;
                    inverseTransform(in, out);
                    if (ix == 0 && iy == 0) {
                        Rectangle2D.Double doubleR2 = new Rectangle2D.Double(out.f1626x, out.f1627y, 0.0d, 0.0d);
                        doubleR = doubleR2;
                    } else {
                        doubleR.add(out.f1626x, out.f1627y);
                    }
                }
            }
        } else {
            int ix2 = 0;
            while (true) {
                int i = 7;
                if (ix2 >= 7) {
                    break;
                }
                double d = 6.0d;
                double x2 = r.getX() + ((r.getWidth() * ((double) ix2)) / 6.0d);
                int iy2 = 0;
                while (iy2 < i) {
                    double y2 = r.getY() + ((r.getHeight() * ((double) iy2)) / d);
                    x2 = x2;
                    in.f1626x = x2;
                    in.f1627y = y2;
                    inverseTransform(in, out);
                    if (ix2 == 0 && iy2 == 0) {
                        Rectangle2D.Double doubleR3 = new Rectangle2D.Double(out.f1626x, out.f1627y, 0.0d, 0.0d);
                        doubleR = doubleR3;
                    } else {
                        doubleR.add(out.f1626x, out.f1627y);
                    }
                    iy2++;
                    i = 7;
                    d = 6.0d;
                }
                ix2++;
            }
        }
        return doubleR;
    }

    public Rectangle2D transform(Rectangle2D r) {
        Double in = new Double();
        Double out = new Double();
        Rectangle2D.Double doubleR = null;
        if (isRectilinear()) {
            for (int ix = 0; ix < 2; ix++) {
                double x = r.getX() + (r.getWidth() * ((double) ix));
                for (int iy = 0; iy < 2; iy++) {
                    double y = r.getY() + (r.getHeight() * ((double) iy));
                    in.f1626x = x;
                    in.f1627y = y;
                    transform(in, out);
                    if (ix == 0 && iy == 0) {
                        Rectangle2D.Double doubleR2 = new Rectangle2D.Double(out.f1626x, out.f1627y, 0.0d, 0.0d);
                        doubleR = doubleR2;
                    } else {
                        doubleR.add(out.f1626x, out.f1627y);
                    }
                }
            }
        } else {
            int ix2 = 0;
            while (true) {
                int i = 7;
                if (ix2 >= 7) {
                    break;
                }
                double d = 6.0d;
                double x2 = r.getX() + ((r.getWidth() * ((double) ix2)) / 6.0d);
                int iy2 = 0;
                while (iy2 < i) {
                    double y2 = r.getY() + ((r.getHeight() * ((double) iy2)) / d);
                    x2 = x2;
                    in.f1626x = x2;
                    in.f1627y = y2;
                    transform(in, out);
                    if (ix2 == 0 && iy2 == 0) {
                        Rectangle2D.Double doubleR3 = new Rectangle2D.Double(out.f1626x, out.f1627y, 0.0d, 0.0d);
                        doubleR = doubleR3;
                    } else {
                        doubleR.add(out.f1626x, out.f1627y);
                    }
                    iy2++;
                    i = 7;
                    d = 6.0d;
                }
                ix2++;
            }
        }
        return doubleR;
    }

    public boolean isConformal() {
        return false;
    }

    public boolean isEqualArea() {
        return false;
    }

    public boolean hasInverse() {
        return false;
    }

    public boolean isRectilinear() {
        return false;
    }

    public boolean parallelsAreParallel() {
        return isRectilinear();
    }

    public boolean inside(double x, double y) {
        double x2 = (double) normalizeLongitude((float) ((0.017453292519943295d * x) - this.projectionLongitude));
        return this.minLongitude <= x2 && x2 <= this.maxLongitude && this.minLatitude <= y && y <= this.maxLatitude;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getName() {
        String str = this.name;
        if (str != null) {
            return str;
        }
        return toString();
    }

    public String getPROJ4Description() {
        AngleFormat format = new AngleFormat(AngleFormat.ddmmssPattern, false);
        StringBuffer sb = new StringBuffer();
        StringBuilder sb2 = new StringBuilder("+proj=");
        sb2.append(getName());
        sb2.append(" +a=");
        sb2.append(this.f1734a);
        sb.append(sb2.toString());
        if (this.f1736es != 0.0d) {
            StringBuilder sb3 = new StringBuilder(" +es=");
            sb3.append(this.f1736es);
            sb.append(sb3.toString());
        }
        sb.append(" +lon_0=");
        format.format(this.projectionLongitude, sb, (FieldPosition) null);
        sb.append(" +lat_0=");
        format.format(this.projectionLatitude, sb, (FieldPosition) null);
        if (this.falseEasting != 1.0d) {
            StringBuilder sb4 = new StringBuilder(" +x_0=");
            sb4.append(this.falseEasting);
            sb.append(sb4.toString());
        }
        if (this.falseNorthing != 1.0d) {
            StringBuilder sb5 = new StringBuilder(" +y_0=");
            sb5.append(this.falseNorthing);
            sb.append(sb5.toString());
        }
        if (this.scaleFactor != 1.0d) {
            StringBuilder sb6 = new StringBuilder(" +k=");
            sb6.append(this.scaleFactor);
            sb.append(sb6.toString());
        }
        if (this.fromMetres != 1.0d) {
            StringBuilder sb7 = new StringBuilder(" +fr_meters=");
            sb7.append(this.fromMetres);
            sb.append(sb7.toString());
        }
        return sb.toString();
    }

    public String toString() {
        return "None";
    }

    public void setMinLatitude(double minLatitude2) {
        this.minLatitude = minLatitude2;
    }

    public double getMinLatitude() {
        return this.minLatitude;
    }

    public void setMaxLatitude(double maxLatitude2) {
        this.maxLatitude = maxLatitude2;
    }

    public double getMaxLatitude() {
        return this.maxLatitude;
    }

    public double getMaxLatitudeDegrees() {
        return this.maxLatitude * 57.29577951308232d;
    }

    public double getMinLatitudeDegrees() {
        return this.minLatitude * 57.29577951308232d;
    }

    public void setMinLongitude(double minLongitude2) {
        this.minLongitude = minLongitude2;
    }

    public double getMinLongitude() {
        return this.minLongitude;
    }

    public void setMinLongitudeDegrees(double minLongitude2) {
        this.minLongitude = 0.017453292519943295d * minLongitude2;
    }

    public double getMinLongitudeDegrees() {
        return this.minLongitude * 57.29577951308232d;
    }

    public void setMaxLongitude(double maxLongitude2) {
        this.maxLongitude = maxLongitude2;
    }

    public double getMaxLongitude() {
        return this.maxLongitude;
    }

    public void setMaxLongitudeDegrees(double maxLongitude2) {
        this.maxLongitude = 0.017453292519943295d * maxLongitude2;
    }

    public double getMaxLongitudeDegrees() {
        return this.maxLongitude * 57.29577951308232d;
    }

    public void setProjectionLatitude(double projectionLatitude3) {
        this.projectionLatitude = projectionLatitude3;
    }

    public double getProjectionLatitude() {
        return this.projectionLatitude;
    }

    public void setProjectionLatitudeDegrees(double projectionLatitude3) {
        this.projectionLatitude = 0.017453292519943295d * projectionLatitude3;
    }

    public double getProjectionLatitudeDegrees() {
        return this.projectionLatitude * 57.29577951308232d;
    }

    public void setProjectionLongitude(double projectionLongitude2) {
        this.projectionLongitude = normalizeLongitudeRadians(projectionLongitude2);
    }

    public double getProjectionLongitude() {
        return this.projectionLongitude;
    }

    public void setProjectionLongitudeDegrees(double projectionLongitude2) {
        this.projectionLongitude = 0.017453292519943295d * projectionLongitude2;
    }

    public double getProjectionLongitudeDegrees() {
        return this.projectionLongitude * 57.29577951308232d;
    }

    public void setTrueScaleLatitude(double trueScaleLatitude2) {
        this.trueScaleLatitude = trueScaleLatitude2;
    }

    public double getTrueScaleLatitude() {
        return this.trueScaleLatitude;
    }

    public void setTrueScaleLatitudeDegrees(double trueScaleLatitude2) {
        this.trueScaleLatitude = 0.017453292519943295d * trueScaleLatitude2;
    }

    public double getTrueScaleLatitudeDegrees() {
        return this.trueScaleLatitude * 57.29577951308232d;
    }

    public void setProjectionLatitude1(double projectionLatitude12) {
        this.projectionLatitude1 = projectionLatitude12;
    }

    public double getProjectionLatitude1() {
        return this.projectionLatitude1;
    }

    public void setProjectionLatitude1Degrees(double projectionLatitude12) {
        this.projectionLatitude1 = 0.017453292519943295d * projectionLatitude12;
    }

    public double getProjectionLatitude1Degrees() {
        return this.projectionLatitude1 * 57.29577951308232d;
    }

    public void setProjectionLatitude2(double projectionLatitude22) {
        this.projectionLatitude2 = projectionLatitude22;
    }

    public double getProjectionLatitude2() {
        return this.projectionLatitude2;
    }

    public void setProjectionLatitude2Degrees(double projectionLatitude22) {
        this.projectionLatitude2 = 0.017453292519943295d * projectionLatitude22;
    }

    public double getProjectionLatitude2Degrees() {
        return this.projectionLatitude2 * 57.29577951308232d;
    }

    public void setFalseNorthing(double falseNorthing2) {
        this.falseNorthing = falseNorthing2;
    }

    public double getFalseNorthing() {
        return this.falseNorthing;
    }

    public void setFalseEasting(double falseEasting2) {
        this.falseEasting = falseEasting2;
    }

    public double getFalseEasting() {
        return this.falseEasting;
    }

    public void setScaleFactor(double scaleFactor2) {
        this.scaleFactor = scaleFactor2;
    }

    public double getScaleFactor() {
        return this.scaleFactor;
    }

    public double getEquatorRadius() {
        return this.f1734a;
    }

    public void setFromMetres(double fromMetres2) {
        this.fromMetres = fromMetres2;
    }

    public double getFromMetres() {
        return this.fromMetres;
    }

    public void setEllipsoid(Ellipsoid ellipsoid2) {
        this.ellipsoid = ellipsoid2;
        this.f1734a = ellipsoid2.equatorRadius;
        this.f1735e = ellipsoid2.eccentricity;
        this.f1736es = ellipsoid2.eccentricity2;
    }

    public Ellipsoid getEllipsoid() {
        return this.ellipsoid;
    }

    public int getEPSGCode() {
        return 0;
    }

    public void initialize() {
        this.spherical = this.f1735e == 0.0d;
        this.one_es = 1.0d - this.f1736es;
        this.rone_es = 1.0d / this.one_es;
        double d = this.f1734a;
        double d2 = this.fromMetres;
        this.totalScale = d * d2;
        this.totalFalseEasting = this.falseEasting * d2;
        this.totalFalseNorthing = this.falseNorthing * d2;
    }

    public static float normalizeLongitude(float angle) {
        if (Double.isInfinite((double) angle) || Double.isNaN((double) angle)) {
            throw new IllegalArgumentException("Infinite longitude");
        }
        while (angle > 180.0f) {
            angle -= 360.0f;
        }
        while (angle < -180.0f) {
            angle += 360.0f;
        }
        return angle;
    }

    public static double normalizeLongitudeRadians(double angle) {
        if (Double.isInfinite(angle) || Double.isNaN(angle)) {
            throw new IllegalArgumentException("Infinite longitude");
        }
        while (angle > 3.141592653589793d) {
            angle -= 6.283185307179586d;
        }
        while (angle < -3.141592653589793d) {
            angle += 6.283185307179586d;
        }
        return angle;
    }
}
