package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class Ginsburg8Projection extends Projection {
    private static final double C12 = 0.08333333333333333d;

    /* renamed from: Cl */
    private static final double f1689Cl = 9.52426E-4d;

    /* renamed from: Cp */
    private static final double f1690Cp = 0.162388d;

    public Double project(double lplam, double lpphi, Double out) {
        double t = lpphi * lpphi;
        out.f1627y = ((C12 * t) + 1.0d) * lpphi;
        out.f1626x = (1.0d - (f1690Cp * t)) * lplam;
        double t2 = lplam * lplam;
        out.f1626x *= 0.87d - ((f1689Cl * t2) * t2);
        return out;
    }

    public String toString() {
        return "Ginsburg VIII (TsNIIGAiK)";
    }
}
