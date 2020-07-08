package com.navibees.core.model.postioning;

import com.navibees.core.model.metadata.json.CircleIndoorLocationRestriction;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.LineIndoorLocationRestriction;
import com.navibees.core.model.metadata.json.PolygonIndoorLocationRestriction;
import java.util.ArrayList;

/* renamed from: com.navibees.core.model.postioning.c */
/* compiled from: NaviBeesMath */
public class C1697c {

    /* renamed from: com.navibees.core.model.postioning.c$a */
    /* compiled from: NaviBeesMath */
    static class C1698a {

        /* renamed from: a */
        double f1409a;

        /* renamed from: b */
        double f1410b;

        public C1698a(double d, double d2) {
            this.f1409a = d;
            this.f1410b = d2;
        }
    }

    /* renamed from: com.navibees.core.model.postioning.c$b */
    /* compiled from: NaviBeesMath */
    static class C1699b {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public IndoorLocation f1411a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public boolean f1412b;

        C1699b(IndoorLocation indoorLocation, boolean z) {
            this.f1411a = indoorLocation;
            this.f1412b = z;
        }
    }

    /* renamed from: com.navibees.core.model.postioning.c$c */
    /* compiled from: NaviBeesMath */
    static class C1700c {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public double f1413a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public boolean f1414b;

        C1700c(double d, boolean z) {
            this.f1413a = d;
            this.f1414b = z;
        }
    }

    /* renamed from: a */
    private static double m1041a(double[] dArr, double[] dArr2) {
        double d = 0.0d;
        if (dArr.length != dArr2.length) {
            return 0.0d;
        }
        for (int i = 0; i < dArr.length; i++) {
            d += dArr[i] * dArr2[i];
        }
        return d;
    }

    /* renamed from: b */
    private static C1698a m1058b(IndoorLocation indoorLocation, IndoorLocation indoorLocation2) {
        return new C1698a(indoorLocation2.f1332x - indoorLocation.f1332x, indoorLocation2.f1333y - indoorLocation.f1333y);
    }

    /* renamed from: c */
    private static double m1060c(IndoorLocation indoorLocation, IndoorLocation indoorLocation2) {
        return m1041a(new double[]{indoorLocation.f1332x, indoorLocation.f1333y}, new double[]{indoorLocation2.f1332x, indoorLocation2.f1333y});
    }

    /* renamed from: d */
    public static double m1064d(IndoorLocation indoorLocation, IndoorLocation indoorLocation2) {
        return m1055b(new double[]{indoorLocation.f1332x, indoorLocation.f1333y}, new double[]{indoorLocation2.f1332x, indoorLocation2.f1333y});
    }

    /* renamed from: e */
    public static double m1066e(IndoorLocation indoorLocation, IndoorLocation indoorLocation2) {
        double d = indoorLocation2.f1332x - indoorLocation.f1332x;
        if (d == 0.0d) {
            d = 1.0E-4d;
        }
        double d2 = indoorLocation2.f1333y - indoorLocation.f1333y;
        double degrees = Math.toDegrees(Math.atan(d2 / d));
        int i = (d > 0.0d ? 1 : (d == 0.0d ? 0 : -1));
        if (i > 0 && d2 > 0.0d) {
            return 90.0d - degrees;
        }
        int i2 = (d > 0.0d ? 1 : (d == 0.0d ? 0 : -1));
        if (i2 < 0 && d2 > 0.0d) {
            return (-degrees) + 270.0d;
        }
        if (i > 0 && d2 < 0.0d) {
            return (-degrees) + 90.0d;
        }
        if (i2 < 0 && d2 < 0.0d) {
            return 270.0d - degrees;
        }
        if (i > 0 && d2 == 0.0d) {
            return 90.0d;
        }
        if (i2 >= 0 || d2 != 0.0d) {
            return degrees;
        }
        return 270.0d;
    }

    /* renamed from: b */
    private static double m1055b(double[] dArr, double[] dArr2) {
        int max = Math.max(dArr.length, dArr2.length);
        int i = 0;
        double d = 0.0d;
        while (i < max) {
            d += Math.pow(((i < 0 || i >= dArr2.length) ? 0.0d : dArr2[i]) - ((i < 0 || i >= dArr.length) ? 0.0d : dArr[i]), 2.0d);
            i++;
        }
        return Math.sqrt(d);
    }

    /* renamed from: c */
    public static IndoorLocation m1061c(LineIndoorLocationRestriction lineIndoorLocationRestriction, IndoorLocation indoorLocation) {
        IndoorLocation indoorLocation2 = lineIndoorLocationRestriction.start;
        IndoorLocation indoorLocation3 = lineIndoorLocationRestriction.end;
        IndoorLocation a = m1045a(lineIndoorLocationRestriction, indoorLocation);
        double d = a.f1333y;
        double d2 = indoorLocation2.f1333y;
        double d3 = d - d2;
        double d4 = indoorLocation3.f1332x;
        double d5 = indoorLocation2.f1332x;
        if (Math.abs((d3 * (d4 - d5)) - ((a.f1332x - d5) * (indoorLocation3.f1333y - d2))) > 0.01d) {
            return null;
        }
        double d6 = a.f1332x;
        double d7 = indoorLocation2.f1332x;
        double d8 = indoorLocation3.f1332x - d7;
        double d9 = (d6 - d7) * d8;
        double d10 = a.f1333y;
        double d11 = indoorLocation2.f1333y;
        double d12 = indoorLocation3.f1333y - d11;
        double d13 = d9 + ((d10 - d11) * d12);
        if (d13 >= 0.0d && d13 <= (d8 * d8) + (d12 * d12)) {
            return a;
        }
        return null;
    }

    /* renamed from: d */
    public static boolean m1065d(LineIndoorLocationRestriction lineIndoorLocationRestriction, IndoorLocation indoorLocation) {
        LineIndoorLocationRestriction lineIndoorLocationRestriction2 = lineIndoorLocationRestriction;
        IndoorLocation indoorLocation2 = indoorLocation;
        IndoorLocation indoorLocation3 = lineIndoorLocationRestriction2.start;
        double d = indoorLocation3.f1332x;
        double d2 = indoorLocation3.f1333y;
        IndoorLocation indoorLocation4 = lineIndoorLocationRestriction2.end;
        double d3 = indoorLocation4.f1332x;
        double d4 = indoorLocation4.f1333y;
        double d5 = indoorLocation2.f1332x;
        double d6 = indoorLocation2.f1333y;
        double d7 = (d4 - d2) / (d3 - d);
        double d8 = d2 - (d7 * d);
        if (d >= d3) {
            double d9 = d;
            d = d3;
            d3 = d9;
        }
        if (d2 >= d4) {
            double d10 = d2;
            d2 = d4;
            d4 = d10;
        }
        double d11 = (d7 * d5) + d8;
        return d11 > d6 - 0.01d && d11 < 0.01d + d6 && d5 >= d && d5 <= d3 && d6 >= d2 && d6 <= d4;
    }

    /* renamed from: a */
    private static double m1040a(double[] dArr) {
        return Math.sqrt(m1054b(dArr));
    }

    /* renamed from: a */
    private static double m1037a(IndoorLocation indoorLocation) {
        return m1050b(indoorLocation);
    }

    /* renamed from: a */
    private static C1700c m1047a(IndoorLocation indoorLocation, IndoorLocation indoorLocation2) {
        double d = indoorLocation.f1332x;
        double d2 = indoorLocation2.f1332x;
        if (d == d2) {
            return new C1700c(2.147483647E9d, true);
        }
        return new C1700c((indoorLocation2.f1333y - indoorLocation.f1333y) / (d2 - d), false);
    }

    /* renamed from: c */
    public static boolean m1062c(CircleIndoorLocationRestriction circleIndoorLocationRestriction, IndoorLocation indoorLocation) {
        return m1049b(circleIndoorLocationRestriction, indoorLocation) <= 0.0d;
    }

    /* renamed from: a */
    public static double m1038a(LineIndoorLocationRestriction lineIndoorLocationRestriction, LineIndoorLocationRestriction lineIndoorLocationRestriction2) {
        double d = m1064d(lineIndoorLocationRestriction.start, lineIndoorLocationRestriction.end);
        double d2 = m1064d(lineIndoorLocationRestriction2.start, lineIndoorLocationRestriction2.end);
        double d3 = m1064d(lineIndoorLocationRestriction.start, lineIndoorLocationRestriction2.end);
        return Math.toDegrees(Math.acos((((d * d) + (d2 * d2)) - (d3 * d3)) / ((d * 2.0d) * d2)));
    }

    /* renamed from: c */
    public static boolean m1063c(PolygonIndoorLocationRestriction polygonIndoorLocationRestriction, IndoorLocation indoorLocation) {
        PolygonIndoorLocationRestriction polygonIndoorLocationRestriction2 = polygonIndoorLocationRestriction;
        IndoorLocation indoorLocation2 = indoorLocation;
        int size = polygonIndoorLocationRestriction2.vertices.size() - 1;
        boolean z = false;
        for (int i = 0; i < polygonIndoorLocationRestriction2.vertices.size(); i++) {
            boolean z2 = ((((IndoorLocation) polygonIndoorLocationRestriction2.vertices.get(i)).f1333y > indoorLocation2.f1333y ? 1 : (((IndoorLocation) polygonIndoorLocationRestriction2.vertices.get(i)).f1333y == indoorLocation2.f1333y ? 0 : -1)) > 0) != ((((IndoorLocation) polygonIndoorLocationRestriction2.vertices.get(size)).f1333y > indoorLocation2.f1333y ? 1 : (((IndoorLocation) polygonIndoorLocationRestriction2.vertices.get(size)).f1333y == indoorLocation2.f1333y ? 0 : -1)) > 0);
            boolean z3 = indoorLocation2.f1332x < (((((IndoorLocation) polygonIndoorLocationRestriction2.vertices.get(size)).f1332x - ((IndoorLocation) polygonIndoorLocationRestriction2.vertices.get(i)).f1332x) * (indoorLocation2.f1333y - ((IndoorLocation) polygonIndoorLocationRestriction2.vertices.get(i)).f1333y)) / (((IndoorLocation) polygonIndoorLocationRestriction2.vertices.get(size)).f1333y - ((IndoorLocation) polygonIndoorLocationRestriction2.vertices.get(i)).f1333y)) + ((IndoorLocation) polygonIndoorLocationRestriction2.vertices.get(i)).f1332x;
            if (z2 && z3) {
                z = !z;
            }
            size = i;
        }
        return z;
    }

    /* renamed from: b */
    private static double m1054b(double[] dArr) {
        double d = 0.0d;
        for (double pow : dArr) {
            d += Math.pow(pow, 2.0d);
        }
        return d;
    }

    /* renamed from: b */
    private static double m1050b(IndoorLocation indoorLocation) {
        return m1054b(new double[]{indoorLocation.f1332x, indoorLocation.f1333y});
    }

    /* renamed from: a */
    private static IndoorLocation m1044a(IndoorLocation indoorLocation, IndoorLocation indoorLocation2, IndoorLocation indoorLocation3) {
        C1698a b = m1058b(indoorLocation, indoorLocation2);
        C1698a b2 = m1058b(indoorLocation, indoorLocation3);
        double a = m1041a(new double[]{b.f1409a, b.f1410b}, new double[]{b2.f1409a, b2.f1410b});
        double b3 = m1054b(new double[]{b.f1409a, b.f1410b});
        IndoorLocation indoorLocation4 = new IndoorLocation(0.0d, 0.0d);
        indoorLocation4.f1332x = indoorLocation.f1332x + ((b.f1409a * a) / b3);
        indoorLocation4.f1333y = indoorLocation.f1333y + ((a * b.f1410b) / b3);
        return indoorLocation4;
    }

    /* renamed from: b */
    private static C1699b m1059b(LineIndoorLocationRestriction lineIndoorLocationRestriction, LineIndoorLocationRestriction lineIndoorLocationRestriction2) {
        LineIndoorLocationRestriction lineIndoorLocationRestriction3 = lineIndoorLocationRestriction;
        LineIndoorLocationRestriction lineIndoorLocationRestriction4 = lineIndoorLocationRestriction2;
        IndoorLocation indoorLocation = lineIndoorLocationRestriction3.start;
        double d = indoorLocation.f1332x;
        double d2 = indoorLocation.f1333y;
        IndoorLocation indoorLocation2 = lineIndoorLocationRestriction3.end;
        double d3 = indoorLocation2.f1332x;
        double d4 = indoorLocation2.f1333y;
        IndoorLocation indoorLocation3 = lineIndoorLocationRestriction4.start;
        double d5 = indoorLocation3.f1332x;
        double d6 = indoorLocation3.f1333y;
        IndoorLocation indoorLocation4 = lineIndoorLocationRestriction4.end;
        double d7 = indoorLocation4.f1332x;
        double d8 = indoorLocation4.f1333y;
        double d9 = d - d3;
        double d10 = d6 - d8;
        double d11 = d2 - d4;
        double d12 = d5 - d7;
        double d13 = (d9 * d10) - (d11 * d12);
        double d14 = d6;
        if (d13 == 0.0d) {
            return new C1699b(new IndoorLocation(0.0d, 0.0d), true);
        }
        double d15 = (d * d4) - (d2 * d3);
        double d16 = (d5 * d8) - (d14 * d7);
        return new C1699b(new IndoorLocation(((d12 * d15) - (d9 * d16)) / d13, ((d15 * d10) - (d11 * d16)) / d13), false);
    }

    /* renamed from: a */
    public static IndoorLocation m1045a(LineIndoorLocationRestriction lineIndoorLocationRestriction, IndoorLocation indoorLocation) {
        return m1044a(lineIndoorLocationRestriction.start, lineIndoorLocationRestriction.end, indoorLocation);
    }

    /* renamed from: a */
    public static double[] m1048a(LineIndoorLocationRestriction lineIndoorLocationRestriction, IndoorLocation indoorLocation, double d) {
        IndoorLocation indoorLocation2 = lineIndoorLocationRestriction.start;
        IndoorLocation indoorLocation3 = lineIndoorLocationRestriction.end;
        IndoorLocation a = m1045a(lineIndoorLocationRestriction, indoorLocation);
        double d2 = m1064d(indoorLocation, a);
        if (d2 > d) {
            return new double[]{0.0d, 0.0d};
        }
        double d3 = a.f1333y;
        double d4 = indoorLocation2.f1333y;
        double d5 = d3 - d4;
        double d6 = indoorLocation3.f1332x;
        double d7 = indoorLocation2.f1332x;
        if (Math.abs((d5 * (d6 - d7)) - ((a.f1332x - d7) * (indoorLocation3.f1333y - d4))) > 0.01d) {
            return new double[]{0.0d, 0.0d};
        }
        double d8 = a.f1332x;
        double d9 = indoorLocation2.f1332x;
        double d10 = indoorLocation3.f1332x - d9;
        double d11 = (d8 - d9) * d10;
        double d12 = a.f1333y;
        double d13 = indoorLocation2.f1333y;
        double d14 = indoorLocation3.f1333y - d13;
        double d15 = d11 + ((d12 - d13) * d14);
        if (d15 < 0.0d) {
            return new double[]{0.0d, 0.0d};
        }
        if (d15 > (d10 * d10) + (d14 * d14)) {
            return new double[]{0.0d, 0.0d};
        }
        return new double[]{1.0d, d2};
    }

    /* renamed from: b */
    private static double m1051b(IndoorLocation indoorLocation, IndoorLocation indoorLocation2, IndoorLocation indoorLocation3) {
        IndoorLocation indoorLocation4 = indoorLocation;
        IndoorLocation indoorLocation5 = indoorLocation2;
        IndoorLocation indoorLocation6 = indoorLocation3;
        double d = indoorLocation5.f1333y;
        double d2 = indoorLocation4.f1333y;
        double d3 = ((d - d2) * indoorLocation6.f1332x) + 0.0d;
        double d4 = indoorLocation5.f1332x;
        double d5 = indoorLocation4.f1332x;
        return Math.abs(((d3 - ((d4 - d5) * indoorLocation6.f1333y)) + (d4 * d2)) - (d * d5)) / m1064d(indoorLocation, indoorLocation2);
    }

    /* renamed from: b */
    public static double m1052b(LineIndoorLocationRestriction lineIndoorLocationRestriction, IndoorLocation indoorLocation) {
        return m1051b(lineIndoorLocationRestriction.start, lineIndoorLocationRestriction.end, indoorLocation);
    }

    /* renamed from: a */
    public static IndoorLocation m1043a(CircleIndoorLocationRestriction circleIndoorLocationRestriction, IndoorLocation indoorLocation) {
        double d;
        double d2;
        double d3;
        double d4;
        CircleIndoorLocationRestriction circleIndoorLocationRestriction2 = circleIndoorLocationRestriction;
        IndoorLocation indoorLocation2 = indoorLocation;
        C1700c a = m1047a(indoorLocation2, circleIndoorLocationRestriction2.center);
        double a2 = a.f1413a;
        if (a.f1414b) {
            d3 = indoorLocation2.f1332x;
            IndoorLocation indoorLocation3 = circleIndoorLocationRestriction2.center;
            double d5 = indoorLocation3.f1333y * -2.0d;
            double pow = Math.pow(d5, 2.0d) - ((((Math.pow(indoorLocation3.f1332x, 2.0d) + Math.pow(circleIndoorLocationRestriction2.center.f1333y, 2.0d)) - Math.pow(circleIndoorLocationRestriction2.radius, 2.0d)) + (Math.pow(d3, 2.0d) - ((indoorLocation2.f1332x * 2.0d) * circleIndoorLocationRestriction2.center.f1332x))) * 4.0d);
            double d6 = d5 * -1.0d;
            d2 = (Math.sqrt(pow) + d6) / 2.0d;
            d = (d6 - Math.sqrt(pow)) / 2.0d;
            d4 = d3;
        } else {
            double d7 = indoorLocation2.f1333y - (indoorLocation2.f1332x * a2);
            double pow2 = Math.pow(a2, 2.0d) + 1.0d;
            IndoorLocation indoorLocation4 = circleIndoorLocationRestriction2.center;
            double d8 = indoorLocation4.f1332x;
            double d9 = (d8 * -2.0d) + (a2 * 2.0d * d7) + (a2 * -2.0d * indoorLocation4.f1333y);
            double pow3 = Math.pow(d9, 2.0d) - ((4.0d * pow2) * ((((Math.pow(d8, 2.0d) + Math.pow(circleIndoorLocationRestriction2.center.f1333y, 2.0d)) - Math.pow(circleIndoorLocationRestriction2.radius, 2.0d)) + Math.pow(d7, 2.0d)) + ((-2.0d * d7) * circleIndoorLocationRestriction2.center.f1333y)));
            double d10 = d9 * -1.0d;
            double d11 = pow2 * 2.0d;
            d4 = (Math.sqrt(pow3) + d10) / d11;
            double sqrt = (d10 - Math.sqrt(pow3)) / d11;
            d2 = (a2 * d4) + d7;
            d = (a2 * sqrt) + d7;
            d3 = sqrt;
        }
        IndoorLocation indoorLocation5 = indoorLocation;
        double d12 = d;
        if (m1055b(new double[]{indoorLocation5.f1332x, indoorLocation5.f1333y}, new double[]{d4, d2}) >= m1055b(new double[]{indoorLocation5.f1332x, indoorLocation5.f1333y}, new double[]{d3, d12})) {
            d4 = d3;
            d2 = d12;
        }
        return new IndoorLocation(d4, d2);
    }

    /* renamed from: b */
    public static double m1049b(CircleIndoorLocationRestriction circleIndoorLocationRestriction, IndoorLocation indoorLocation) {
        return m1064d(circleIndoorLocationRestriction.center, indoorLocation) - circleIndoorLocationRestriction.radius;
    }

    /* renamed from: b */
    private static IndoorLocation m1057b(IndoorLocation[] indoorLocationArr) {
        IndoorLocation[] indoorLocationArr2 = indoorLocationArr;
        double d = 0.0d;
        int i = 0;
        double d2 = 0.0d;
        double d3 = 0.0d;
        while (i < indoorLocationArr2.length) {
            double d4 = indoorLocationArr2[i].f1332x;
            int i2 = i + 1;
            double d5 = indoorLocationArr2[i2 % indoorLocationArr2.length].f1332x;
            double d6 = indoorLocationArr2[i].f1333y;
            double d7 = d2;
            double d8 = indoorLocationArr2[i2 % indoorLocationArr2.length].f1333y;
            double d9 = (d4 * d8) - (d5 * d6);
            d += (d4 + d5) * d9;
            d3 += (d6 + d8) * d9;
            d2 = d7 + d9;
            i = i2;
        }
        double d10 = d2 * 3.0d;
        return new IndoorLocation(d / d10, d3 / d10);
    }

    /* renamed from: b */
    private static IndoorLocation m1056b(PolygonIndoorLocationRestriction polygonIndoorLocationRestriction) {
        IndoorLocation[] indoorLocationArr = new IndoorLocation[polygonIndoorLocationRestriction.vertices.size()];
        polygonIndoorLocationRestriction.vertices.toArray(indoorLocationArr);
        return m1057b(indoorLocationArr);
    }

    /* renamed from: b */
    public static double m1053b(PolygonIndoorLocationRestriction polygonIndoorLocationRestriction, IndoorLocation indoorLocation) {
        IndoorLocation b = m1056b(polygonIndoorLocationRestriction);
        String str = "";
        LineIndoorLocationRestriction lineIndoorLocationRestriction = new LineIndoorLocationRestriction(str, -1, indoorLocation, b);
        double d = m1064d(b, indoorLocation);
        int i = 0;
        while (i < polygonIndoorLocationRestriction.vertices.size()) {
            IndoorLocation indoorLocation2 = (IndoorLocation) polygonIndoorLocationRestriction.vertices.get(i);
            ArrayList<IndoorLocation> arrayList = polygonIndoorLocationRestriction.vertices;
            i++;
            LineIndoorLocationRestriction lineIndoorLocationRestriction2 = new LineIndoorLocationRestriction(str, -1, indoorLocation2, (IndoorLocation) arrayList.get(i % arrayList.size()));
            C1699b b2 = m1059b(lineIndoorLocationRestriction, lineIndoorLocationRestriction2);
            IndoorLocation a = b2.f1411a;
            if (!b2.f1412b) {
                double d2 = m1064d(indoorLocation, a);
                if (d2 < d && m1065d(lineIndoorLocationRestriction2, a)) {
                    d = d2;
                }
            }
        }
        return d;
    }

    /* renamed from: a */
    private double m1042a(IndoorLocation[] indoorLocationArr) {
        double d = 0.0d;
        int i = 0;
        while (i < indoorLocationArr.length) {
            int i2 = i + 1;
            d += (indoorLocationArr[i].f1332x * indoorLocationArr[i2 % indoorLocationArr.length].f1333y) - (indoorLocationArr[i2 % indoorLocationArr.length].f1332x * indoorLocationArr[i].f1333y);
            i = i2;
        }
        return Math.abs(d) / 2.0d;
    }

    /* renamed from: a */
    private double m1039a(PolygonIndoorLocationRestriction polygonIndoorLocationRestriction) {
        IndoorLocation[] indoorLocationArr = new IndoorLocation[polygonIndoorLocationRestriction.vertices.size()];
        polygonIndoorLocationRestriction.vertices.toArray(indoorLocationArr);
        return m1042a(indoorLocationArr);
    }

    /* renamed from: a */
    public static IndoorLocation m1046a(PolygonIndoorLocationRestriction polygonIndoorLocationRestriction, IndoorLocation indoorLocation) {
        IndoorLocation b = m1056b(polygonIndoorLocationRestriction);
        String str = "";
        LineIndoorLocationRestriction lineIndoorLocationRestriction = new LineIndoorLocationRestriction(str, -1, indoorLocation, b);
        double d = m1064d(b, indoorLocation);
        int i = 0;
        while (i < polygonIndoorLocationRestriction.vertices.size()) {
            IndoorLocation indoorLocation2 = (IndoorLocation) polygonIndoorLocationRestriction.vertices.get(i);
            ArrayList<IndoorLocation> arrayList = polygonIndoorLocationRestriction.vertices;
            i++;
            LineIndoorLocationRestriction lineIndoorLocationRestriction2 = new LineIndoorLocationRestriction(str, -1, indoorLocation2, (IndoorLocation) arrayList.get(i % arrayList.size()));
            C1699b b2 = m1059b(lineIndoorLocationRestriction, lineIndoorLocationRestriction2);
            IndoorLocation a = b2.f1411a;
            if (!b2.f1412b) {
                double d2 = m1064d(indoorLocation, a);
                if (d2 < d && m1065d(lineIndoorLocationRestriction2, a)) {
                    b = a;
                    d = d2;
                }
            }
        }
        return b;
    }
}
