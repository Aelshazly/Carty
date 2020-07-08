package com.navibees.core.model.postioning;

import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.IndoorLocationRestriction;
import com.navibees.core.model.metadata.json.LineIndoorLocationRestriction;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.navibees.core.model.postioning.a */
/* compiled from: ApplyRestrictions */
public class C1694a {

    /* renamed from: com.navibees.core.model.postioning.a$a */
    /* compiled from: ApplyRestrictions */
    public static class C1695a {

        /* renamed from: a */
        public LineIndoorLocationRestriction f1406a;

        /* renamed from: b */
        public IndoorLocation f1407b;

        public C1695a(LineIndoorLocationRestriction lineIndoorLocationRestriction, IndoorLocation indoorLocation) {
            this.f1406a = lineIndoorLocationRestriction;
            this.f1407b = indoorLocation;
        }
    }

    /* renamed from: a */
    private static double m1032a(double d, double d2, double d3, double d4) {
        return Math.sqrt(Math.pow(d - d3, 2.0d) + Math.pow(d2 - d4, 2.0d));
    }

    /* renamed from: b */
    public static C1695a m1036b(IndoorLocation indoorLocation, List<IndoorLocationRestriction> list) {
        LineIndoorLocationRestriction a = m1033a(indoorLocation, list);
        double[] a2 = m1034a(indoorLocation, a);
        return new C1695a(a, new IndoorLocation(a2[0], a2[1]));
    }

    /* renamed from: a */
    public static double[] m1034a(IndoorLocation indoorLocation, LineIndoorLocationRestriction lineIndoorLocationRestriction) {
        double[] dArr = {0.0d, 0.0d};
        double[] a = m1035a(new double[]{indoorLocation.f1332x, indoorLocation.f1333y}, lineIndoorLocationRestriction);
        double d = 1.0d - a[0];
        IndoorLocation indoorLocation2 = lineIndoorLocationRestriction.start;
        double d2 = d * indoorLocation2.f1332x;
        double d3 = a[0];
        IndoorLocation indoorLocation3 = lineIndoorLocationRestriction.end;
        double d4 = ((1.0d - a[0]) * indoorLocation2.f1333y) + (a[0] * indoorLocation3.f1333y);
        dArr[0] = d2 + (d3 * indoorLocation3.f1332x);
        dArr[1] = d4;
        return dArr;
    }

    /* renamed from: a */
    private static double[] m1035a(double[] dArr, LineIndoorLocationRestriction lineIndoorLocationRestriction) {
        LineIndoorLocationRestriction lineIndoorLocationRestriction2 = lineIndoorLocationRestriction;
        IndoorLocation indoorLocation = lineIndoorLocationRestriction2.start;
        double d = indoorLocation.f1332x;
        IndoorLocation indoorLocation2 = lineIndoorLocationRestriction2.end;
        if (d == indoorLocation2.f1332x) {
            double d2 = indoorLocation.f1333y;
            if (d2 == indoorLocation2.f1333y) {
                return new double[]{0.0d, m1032a(dArr[0], dArr[1], d, d2)};
            }
        }
        IndoorLocation indoorLocation3 = lineIndoorLocationRestriction2.end;
        double d3 = indoorLocation3.f1332x;
        IndoorLocation indoorLocation4 = lineIndoorLocationRestriction2.start;
        double d4 = indoorLocation4.f1332x;
        double d5 = d3 - d4;
        double d6 = d5 * d5;
        double d7 = indoorLocation3.f1333y;
        double d8 = indoorLocation4.f1333y;
        double d9 = d7 - d8;
        double d10 = d6 + (d9 * d9);
        double d11 = (((dArr[0] - d4) * d5) + ((dArr[1] - d8) * d9)) / d10;
        if (d11 <= 0.0d) {
            return new double[]{0.0d, m1032a(dArr[0], dArr[1], d4, d8)};
        } else if (d11 >= 1.0d) {
            return new double[]{1.0d, m1032a(dArr[0], dArr[1], d3, d7)};
        } else {
            return new double[]{d11, Math.abs((((d8 - dArr[1]) * d5) - ((d4 - dArr[0]) * d9)) / d10) * Math.sqrt(d10)};
        }
    }

    /* renamed from: a */
    private static LineIndoorLocationRestriction m1033a(IndoorLocation indoorLocation, List<IndoorLocationRestriction> list) {
        double[] dArr = {indoorLocation.f1332x, indoorLocation.f1333y};
        Iterator it = list.iterator();
        LineIndoorLocationRestriction lineIndoorLocationRestriction = null;
        double d = -1.0d;
        while (it.hasNext()) {
            LineIndoorLocationRestriction lineIndoorLocationRestriction2 = (LineIndoorLocationRestriction) it.next();
            double[] a = m1035a(dArr, lineIndoorLocationRestriction2);
            if (d == -1.0d) {
                d = a[1];
                lineIndoorLocationRestriction = lineIndoorLocationRestriction2;
            } else if (d > a[1]) {
                d = a[1];
                lineIndoorLocationRestriction = lineIndoorLocationRestriction2;
            }
        }
        return lineIndoorLocationRestriction;
    }
}
