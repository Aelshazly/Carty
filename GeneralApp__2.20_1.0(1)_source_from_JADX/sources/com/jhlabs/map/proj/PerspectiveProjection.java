package com.jhlabs.map.proj;

public class PerspectiveProjection extends Projection {
    private static final double EPS10 = 1.0E-10d;
    private static final int EQUIT = 2;
    private static final int N_POLE = 0;
    private static final int OBLIQ = 3;
    private static final int S_POLE = 1;

    /* renamed from: cg */
    private double f1726cg;

    /* renamed from: cw */
    private double f1727cw;

    /* renamed from: h */
    private double f1728h;
    private double height;
    private int mode;

    /* renamed from: p */
    private double f1729p;
    private double pcosph0;
    private double pfact;
    private double pn1;
    private double psinph0;

    /* renamed from: rp */
    private double f1730rp;

    /* renamed from: sg */
    private double f1731sg;

    /* renamed from: sw */
    private double f1732sw;
    private int tilt;

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00b7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.jhlabs.map.Point2D.Double project(double r19, double r21, com.jhlabs.map.Point2D.Double r23) {
        /*
            r18 = this;
            r0 = r18
            r1 = r23
            double r2 = java.lang.Math.sin(r21)
            double r4 = java.lang.Math.cos(r21)
            double r6 = java.lang.Math.cos(r19)
            int r8 = r0.mode
            r9 = 3
            r10 = 2
            r11 = 1
            if (r8 == 0) goto L_0x0035
            if (r8 == r11) goto L_0x0031
            if (r8 == r10) goto L_0x002c
            if (r8 == r9) goto L_0x001e
            goto L_0x0037
        L_0x001e:
            double r12 = r0.psinph0
            double r12 = r12 * r2
            double r14 = r0.pcosph0
            double r14 = r14 * r4
            double r14 = r14 * r6
            double r12 = r12 + r14
            r1.f1627y = r12
            goto L_0x0037
        L_0x002c:
            double r12 = r4 * r6
            r1.f1627y = r12
            goto L_0x0037
        L_0x0031:
            double r12 = -r2
            r1.f1627y = r12
            goto L_0x0037
        L_0x0035:
            r1.f1627y = r2
        L_0x0037:
            double r12 = r0.pn1
            double r14 = r0.f1729p
            double r9 = r1.f1627y
            double r14 = r14 - r9
            double r12 = r12 / r14
            r1.f1627y = r12
            double r9 = r1.f1627y
            double r9 = r9 * r4
            double r12 = java.lang.Math.sin(r19)
            double r9 = r9 * r12
            r1.f1626x = r9
            int r9 = r0.mode
            if (r9 == 0) goto L_0x0073
            if (r9 == r11) goto L_0x0074
            r10 = 2
            if (r9 == r10) goto L_0x006c
            r8 = 3
            if (r9 == r8) goto L_0x005a
            goto L_0x007c
        L_0x005a:
            double r8 = r1.f1627y
            double r10 = r0.pcosph0
            double r10 = r10 * r2
            double r12 = r0.psinph0
            double r12 = r12 * r4
            double r12 = r12 * r6
            double r10 = r10 - r12
            double r8 = r8 * r10
            r1.f1627y = r8
            goto L_0x007c
        L_0x006c:
            double r8 = r1.f1627y
            double r8 = r8 * r2
            r1.f1627y = r8
            goto L_0x007c
        L_0x0073:
            double r6 = -r6
        L_0x0074:
            double r8 = r1.f1627y
            double r10 = r4 * r6
            double r8 = r8 * r10
            r1.f1627y = r8
        L_0x007c:
            int r8 = r0.tilt
            if (r8 == 0) goto L_0x00b7
            double r8 = r1.f1627y
            double r10 = r0.f1726cg
            double r8 = r8 * r10
            double r10 = r1.f1626x
            double r12 = r0.f1731sg
            double r10 = r10 * r12
            double r8 = r8 + r10
            r10 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r12 = r0.f1732sw
            double r12 = r12 * r8
            double r14 = r0.f1728h
            double r12 = r12 * r14
            double r14 = r0.f1727cw
            double r12 = r12 + r14
            double r10 = r10 / r12
            double r12 = r1.f1626x
            double r14 = r0.f1726cg
            double r12 = r12 * r14
            double r14 = r1.f1627y
            r16 = r2
            double r2 = r0.f1731sg
            double r14 = r14 * r2
            double r12 = r12 - r14
            double r2 = r0.f1727cw
            double r12 = r12 * r2
            double r12 = r12 * r10
            r1.f1626x = r12
            double r2 = r8 * r10
            r1.f1627y = r2
            goto L_0x00b9
        L_0x00b7:
            r16 = r2
        L_0x00b9:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jhlabs.map.proj.PerspectiveProjection.project(double, double, com.jhlabs.map.Point2D$Double):com.jhlabs.map.Point2D$Double");
    }

    public boolean hasInverse() {
        return false;
    }

    public void initialize() {
        super.initialize();
        this.mode = 2;
        this.height = this.f1734a;
        this.tilt = 0;
        this.pn1 = this.height / this.f1734a;
        double d = this.pn1;
        this.f1729p = d + 1.0d;
        double d2 = this.f1729p;
        this.f1730rp = 1.0d / d2;
        this.f1728h = 1.0d / d;
        this.pfact = (d2 + 1.0d) * this.f1728h;
        this.f1736es = 0.0d;
    }

    public String toString() {
        return "Perspective";
    }
}
