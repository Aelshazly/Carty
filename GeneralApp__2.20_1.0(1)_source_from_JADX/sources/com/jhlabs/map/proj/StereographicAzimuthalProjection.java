package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class StereographicAzimuthalProjection extends AzimuthalProjection {
    private static final double TOL = 1.0E-8d;
    private double akm1;

    public StereographicAzimuthalProjection() {
        this(Math.toRadians(90.0d), Math.toRadians(0.0d));
    }

    public StereographicAzimuthalProjection(double projectionLatitude, double projectionLongitude) {
        super(projectionLatitude, projectionLongitude);
        initialize();
    }

    public void setupUPS(int pole) {
        this.projectionLatitude = pole == 2 ? -1.5707963267948966d : 1.5707963267948966d;
        this.projectionLongitude = 0.0d;
        this.scaleFactor = 0.994d;
        this.falseEasting = 2000000.0d;
        this.falseNorthing = 2000000.0d;
        this.trueScaleLatitude = 1.5707963267948966d;
        initialize();
    }

    public void initialize() {
        double d;
        super.initialize();
        double abs = Math.abs(this.projectionLatitude);
        double t = abs;
        if (Math.abs(abs - 1.5707963267948966d) < 1.0E-10d) {
            this.mode = this.projectionLatitude < 0.0d ? 2 : 1;
        } else {
            this.mode = t > 1.0E-10d ? 4 : 3;
        }
        this.trueScaleLatitude = Math.abs(this.trueScaleLatitude);
        if (this.spherical) {
            int i = this.mode;
            if (i == 1 || i == 2) {
                if (Math.abs(this.trueScaleLatitude - 1.5707963267948966d) < 1.0E-10d) {
                    this.akm1 = (this.scaleFactor * 2.0d) / Math.sqrt(Math.pow(this.f1735e + 1.0d, this.f1735e + 1.0d) * Math.pow(1.0d - this.f1735e, 1.0d - this.f1735e));
                    return;
                }
                double cos = Math.cos(this.trueScaleLatitude);
                double d2 = this.trueScaleLatitude;
                double sin = Math.sin(this.trueScaleLatitude);
                double t2 = sin;
                this.akm1 = cos / MapMath.tsfn(d2, sin, this.f1735e);
                double t3 = t2 * this.f1735e;
                this.akm1 /= Math.sqrt(1.0d - (t3 * t3));
            } else if (i == 3) {
                this.akm1 = this.scaleFactor * 2.0d;
            } else if (i == 4) {
                double t4 = Math.sin(this.projectionLatitude);
                double X = (Math.atan(ssfn(this.projectionLatitude, t4, this.f1735e)) * 2.0d) - 1.5707963267948966d;
                double t5 = this.f1735e * t4;
                this.akm1 = ((this.scaleFactor * 2.0d) * Math.cos(this.projectionLatitude)) / Math.sqrt(1.0d - (t5 * t5));
                this.sinphi0 = Math.sin(X);
                this.cosphi0 = Math.cos(X);
            }
        } else {
            int i2 = this.mode;
            if (i2 == 1 || i2 == 2) {
                if (Math.abs(this.trueScaleLatitude - 1.5707963267948966d) >= 1.0E-10d) {
                    d = Math.cos(this.trueScaleLatitude) / Math.tan(0.7853981633974483d - (this.trueScaleLatitude * 0.5d));
                } else {
                    d = this.scaleFactor * 2.0d;
                }
                this.akm1 = d;
                return;
            }
            if (i2 != 3) {
                if (i2 == 4) {
                    this.sinphi0 = Math.sin(this.projectionLatitude);
                    this.cosphi0 = Math.cos(this.projectionLatitude);
                } else {
                    return;
                }
            }
            this.akm1 = this.scaleFactor * 2.0d;
        }
    }

    public Double project(double lam, double phi, Double xy) {
        double sinphi;
        double sinlam;
        double sinphi2;
        double phi2;
        double phi3;
        double d = phi;
        Double doubleR = xy;
        double coslam = Math.cos(lam);
        double sinlam2 = Math.sin(lam);
        double sinphi3 = Math.sin(phi);
        if (this.spherical) {
            double cosphi = Math.cos(phi);
            int i = this.mode;
            if (i == 1) {
                coslam = -coslam;
                phi3 = -phi;
            } else if (i != 2) {
                if (i == 3) {
                    doubleR.f1627y = (cosphi * coslam) + 1.0d;
                    if (doubleR.f1627y > 1.0E-10d) {
                        double d2 = this.akm1 / doubleR.f1627y;
                        doubleR.f1627y = d2;
                        doubleR.f1626x = d2 * cosphi * sinlam2;
                        doubleR.f1627y *= sinphi3;
                    } else {
                        throw new ProjectionException();
                    }
                } else if (i != 4) {
                    double d3 = d;
                    double d4 = sinlam2;
                } else {
                    doubleR.f1627y = (this.sinphi0 * sinphi3) + 1.0d + (this.cosphi0 * cosphi * coslam);
                    if (doubleR.f1627y > 1.0E-10d) {
                        double d5 = this.akm1 / doubleR.f1627y;
                        doubleR.f1627y = d5;
                        doubleR.f1626x = d5 * cosphi * sinlam2;
                        doubleR.f1627y *= (this.cosphi0 * sinphi3) - ((this.sinphi0 * cosphi) * coslam);
                    } else {
                        throw new ProjectionException();
                    }
                }
                double d6 = phi;
                double d7 = sinlam2;
            } else {
                phi3 = phi;
            }
            if (Math.abs(phi3 - 1.5707963267948966d) >= TOL) {
                double tan = this.akm1 * Math.tan((0.5d * phi3) + 0.7853981633974483d);
                doubleR.f1627y = tan;
                doubleR.f1626x = tan * sinlam2;
                doubleR.f1627y *= coslam;
                double d42 = sinlam2;
            } else {
                throw new ProjectionException();
            }
        } else {
            double sinX = 0.0d;
            double cosX = 0.0d;
            if (this.mode == 4 || this.mode == 3) {
                sinlam = sinlam2;
                sinphi = sinphi3;
                double atan = (Math.atan(ssfn(phi, sinphi3, this.f1735e)) * 2.0d) - 1.5707963267948966d;
                double X = atan;
                sinX = Math.sin(atan);
                cosX = Math.cos(X);
            } else {
                sinphi = sinphi3;
                sinlam = sinlam2;
            }
            int i2 = this.mode;
            if (i2 == 1) {
                sinphi2 = sinphi;
                phi2 = d;
            } else if (i2 != 2) {
                if (i2 == 3) {
                    double A = (this.akm1 * 2.0d) / ((cosX * coslam) + 1.0d);
                    doubleR.f1627y = A * sinX;
                    doubleR.f1626x = A * cosX;
                } else if (i2 == 4) {
                    double A2 = this.akm1 / (this.cosphi0 * (((this.sinphi0 * sinX) + 1.0d) + ((this.cosphi0 * cosX) * coslam)));
                    doubleR.f1627y = ((this.cosphi0 * sinX) - ((this.sinphi0 * cosX) * coslam)) * A2;
                    doubleR.f1626x = A2 * cosX;
                }
                double A3 = d;
                double d8 = sinphi;
                doubleR.f1626x *= sinlam;
            } else {
                phi2 = -d;
                coslam = -coslam;
                sinphi2 = -sinphi;
            }
            doubleR.f1626x = this.akm1 * MapMath.tsfn(phi2, sinphi2, this.f1735e);
            doubleR.f1627y = (-doubleR.f1626x) * coslam;
            double d9 = sinphi2;
            doubleR.f1626x *= sinlam;
        }
        return doubleR;
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01e8 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.jhlabs.map.Point2D.Double projectInverse(double r30, double r32, com.jhlabs.map.Point2D.Double r34) {
        /*
            r29 = this;
            r0 = r29
            r1 = r30
            r3 = r32
            r5 = r34
            boolean r6 = r0.spherical
            r10 = 4611686018427387904(0x4000000000000000, double:2.0)
            r12 = 2
            r13 = 0
            if (r6 == 0) goto L_0x00fb
            double r15 = com.jhlabs.map.MapMath.distance(r30, r32)
            r17 = r15
            double r8 = r0.akm1
            double r15 = r15 / r8
            double r8 = java.lang.Math.atan(r15)
            double r8 = r8 * r10
            r10 = r8
            double r8 = java.lang.Math.sin(r8)
            r15 = r8
            double r7 = java.lang.Math.cos(r10)
            r5.f1626x = r13
            int r9 = r0.mode
            r6 = 1
            if (r9 == r6) goto L_0x00c4
            if (r9 == r12) goto L_0x00c1
            r6 = 3
            if (r9 == r6) goto L_0x008e
            r6 = 4
            if (r9 == r6) goto L_0x003d
            r23 = r10
            goto L_0x00f9
        L_0x003d:
            double r21 = java.lang.Math.abs(r17)
            r19 = 4457293557087583675(0x3ddb7cdfd9d7bdbb, double:1.0E-10)
            int r6 = (r21 > r19 ? 1 : (r21 == r19 ? 0 : -1))
            if (r6 > 0) goto L_0x0051
            double r13 = r0.projectionLatitude
            r5.f1627y = r13
            r23 = r10
            goto L_0x0067
        L_0x0051:
            double r12 = r0.sinphi0
            double r12 = r12 * r7
            double r19 = r3 * r15
            r23 = r10
            double r9 = r0.cosphi0
            double r19 = r19 * r9
            double r19 = r19 / r17
            double r12 = r12 + r19
            double r9 = java.lang.Math.asin(r12)
            r5.f1627y = r9
        L_0x0067:
            double r9 = r0.sinphi0
            double r11 = r5.f1627y
            double r11 = java.lang.Math.sin(r11)
            double r9 = r9 * r11
            double r9 = r7 - r9
            r11 = r9
            r13 = 0
            int r6 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r6 != 0) goto L_0x007e
            int r6 = (r1 > r13 ? 1 : (r1 == r13 ? 0 : -1))
            if (r6 == 0) goto L_0x00fa
        L_0x007e:
            double r9 = r1 * r15
            double r13 = r0.cosphi0
            double r9 = r9 * r13
            double r13 = r11 * r17
            double r9 = java.lang.Math.atan2(r9, r13)
            r5.f1626x = r9
            goto L_0x00fa
        L_0x008e:
            r23 = r10
            double r9 = java.lang.Math.abs(r17)
            r11 = 4457293557087583675(0x3ddb7cdfd9d7bdbb, double:1.0E-10)
            int r6 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r6 > 0) goto L_0x00a2
            r9 = 0
            r5.f1627y = r9
            goto L_0x00ae
        L_0x00a2:
            r9 = 0
            double r11 = r3 * r15
            double r11 = r11 / r17
            double r11 = java.lang.Math.asin(r11)
            r5.f1627y = r11
        L_0x00ae:
            int r6 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r6 != 0) goto L_0x00b6
            int r6 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r6 == 0) goto L_0x00fa
        L_0x00b6:
            double r9 = r1 * r15
            double r11 = r7 * r17
            double r9 = java.lang.Math.atan2(r9, r11)
            r5.f1626x = r9
            goto L_0x00fa
        L_0x00c1:
            r23 = r10
            goto L_0x00c7
        L_0x00c4:
            r23 = r10
            double r3 = -r3
        L_0x00c7:
            double r9 = java.lang.Math.abs(r17)
            r13 = 4457293557087583675(0x3ddb7cdfd9d7bdbb, double:1.0E-10)
            int r6 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r6 > 0) goto L_0x00d9
            double r9 = r0.projectionLatitude
            r5.f1627y = r9
            goto L_0x00e6
        L_0x00d9:
            int r6 = r0.mode
            if (r6 != r12) goto L_0x00df
            double r9 = -r7
            goto L_0x00e0
        L_0x00df:
            r9 = r7
        L_0x00e0:
            double r9 = java.lang.Math.asin(r9)
            r5.f1627y = r9
        L_0x00e6:
            r9 = 0
            int r6 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r6 != 0) goto L_0x00f3
            int r6 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r6 != 0) goto L_0x00f3
            r13 = 0
            goto L_0x00f7
        L_0x00f3:
            double r13 = java.lang.Math.atan2(r1, r3)
        L_0x00f7:
            r5.f1626x = r13
        L_0x00f9:
        L_0x00fa:
            return r5
        L_0x00fb:
            double r7 = com.jhlabs.map.MapMath.distance(r30, r32)
            int r9 = r0.mode
            r6 = 1
            if (r9 == r6) goto L_0x015b
            if (r9 == r12) goto L_0x0158
            double r12 = r0.cosphi0
            double r12 = r12 * r7
            r17 = r7
            double r6 = r0.akm1
            double r6 = java.lang.Math.atan2(r12, r6)
            double r6 = r6 * r10
            r12 = r6
            double r6 = java.lang.Math.cos(r6)
            double r23 = java.lang.Math.sin(r12)
            double r8 = r0.sinphi0
            double r8 = r8 * r6
            double r25 = r3 * r23
            double r14 = r0.cosphi0
            double r25 = r25 * r14
            double r25 = r25 / r17
            double r8 = r8 + r25
            double r8 = java.lang.Math.asin(r8)
            r14 = 4609753056924675352(0x3ff921fb54442d18, double:1.5707963267948966)
            double r14 = r14 + r8
            r25 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r14 = r14 * r25
            double r12 = java.lang.Math.tan(r14)
            double r1 = r1 * r23
            double r14 = r0.cosphi0
            double r14 = r14 * r17
            double r14 = r14 * r6
            double r10 = r0.sinphi0
            double r10 = r10 * r3
            double r10 = r10 * r23
            double r14 = r14 - r10
            r3 = 4609753056924675352(0x3ff921fb54442d18, double:1.5707963267948966)
            double r10 = r0.f1735e
            double r10 = r10 * r25
            r6 = r17
            goto L_0x0184
        L_0x0158:
            r17 = r7
            goto L_0x015e
        L_0x015b:
            r17 = r7
            double r3 = -r3
        L_0x015e:
            r6 = r17
            double r8 = -r6
            double r10 = r0.akm1
            double r8 = r8 / r10
            r12 = r8
            double r8 = java.lang.Math.atan(r8)
            r10 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r8 = r8 * r10
            r10 = 4609753056924675352(0x3ff921fb54442d18, double:1.5707963267948966)
            double r8 = r10 - r8
            r10 = -4613618979930100456(0xbff921fb54442d18, double:-1.5707963267948966)
            r14 = -4620693217682128896(0xbfe0000000000000, double:-0.5)
            double r1 = r0.f1735e
            double r1 = r1 * r14
            r14 = r3
            r3 = r10
            r10 = r1
            r1 = r30
        L_0x0184:
            r16 = 8
        L_0x0186:
            int r17 = r16 + -1
            if (r16 == 0) goto L_0x01e8
            r23 = r6
            double r6 = r0.f1735e
            double r25 = java.lang.Math.sin(r8)
            double r6 = r6 * r25
            r25 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r27 = r6 + r25
            double r25 = r25 - r6
            r30 = r6
            double r6 = r27 / r25
            double r6 = java.lang.Math.pow(r6, r10)
            double r6 = r6 * r12
            double r6 = java.lang.Math.atan(r6)
            r25 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r6 = r6 * r25
            double r6 = r6 - r3
            r5.f1627y = r6
            double r6 = r5.f1627y
            double r6 = r8 - r6
            double r6 = java.lang.Math.abs(r6)
            r18 = 4457293557087583675(0x3ddb7cdfd9d7bdbb, double:1.0E-10)
            int r16 = (r6 > r18 ? 1 : (r6 == r18 ? 0 : -1))
            if (r16 >= 0) goto L_0x01de
            int r6 = r0.mode
            r7 = 2
            if (r6 != r7) goto L_0x01ca
            double r6 = r5.f1627y
            double r6 = -r6
            r5.f1627y = r6
        L_0x01ca:
            r20 = 0
            int r6 = (r1 > r20 ? 1 : (r1 == r20 ? 0 : -1))
            if (r6 != 0) goto L_0x01d7
            int r6 = (r14 > r20 ? 1 : (r14 == r20 ? 0 : -1))
            if (r6 != 0) goto L_0x01d7
            r6 = r20
            goto L_0x01db
        L_0x01d7:
            double r6 = java.lang.Math.atan2(r1, r14)
        L_0x01db:
            r5.f1626x = r6
            return r5
        L_0x01de:
            r7 = 2
            r20 = 0
            double r8 = r5.f1627y
            r16 = r17
            r6 = r23
            goto L_0x0186
        L_0x01e8:
            r23 = r6
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.String r7 = "Iteration didn't converge"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jhlabs.map.proj.StereographicAzimuthalProjection.projectInverse(double, double, com.jhlabs.map.Point2D$Double):com.jhlabs.map.Point2D$Double");
    }

    public boolean isConformal() {
        return true;
    }

    public boolean hasInverse() {
        return true;
    }

    private double ssfn(double phit, double sinphi, double eccen) {
        double sinphi2 = sinphi * eccen;
        return Math.tan((1.5707963267948966d + phit) * 0.5d) * Math.pow((1.0d - sinphi2) / (1.0d + sinphi2), 0.5d * eccen);
    }

    public String toString() {
        return "Stereographic Azimuthal";
    }
}
