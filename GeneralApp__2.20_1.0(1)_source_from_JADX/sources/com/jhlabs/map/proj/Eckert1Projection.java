package com.jhlabs.map.proj;

import com.jhlabs.map.Point2D.Double;

public class Eckert1Projection extends Projection {

    /* renamed from: FC */
    private static final double f1670FC = 0.9213177319235613d;

    /* renamed from: RP */
    private static final double f1671RP = 0.3183098861837907d;

    public Double project(double lplam, double lpphi, Double out) {
        out.f1626x = lplam * f1670FC * (1.0d - (Math.abs(lpphi) * f1671RP));
        out.f1627y = f1670FC * lpphi;
        return out;
    }

    public Double projectInverse(double xyx, double xyy, Double out) {
        out.f1627y = xyy / f1670FC;
        out.f1626x = xyx / ((1.0d - (Math.abs(out.f1627y) * f1671RP)) * f1670FC);
        return out;
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Eckert I";
    }
}
