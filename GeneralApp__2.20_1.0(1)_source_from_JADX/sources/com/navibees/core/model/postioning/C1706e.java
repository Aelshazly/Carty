package com.navibees.core.model.postioning;

import android.app.Application;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.license.NaviBeesLicenseExpireException;
import com.navibees.core.model.license.NaviBeesLicenseNotAuthorizedException;
import com.navibees.core.model.metadata.BeaconNode;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.Venue;
import com.navibees.core.model.postioning.PositionLocator.CalculatedLocation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.navibees.core.model.postioning.e */
/* compiled from: PythonDerivedTrilaterationPositionLocator */
public class C1706e extends PositionLocator {

    /* renamed from: g */
    static final int f1491g = 3;

    /* renamed from: e */
    private double f1492e;

    /* renamed from: f */
    private double f1493f;

    /* renamed from: com.navibees.core.model.postioning.e$a */
    /* compiled from: PythonDerivedTrilaterationPositionLocator */
    class C1707a implements Comparator<BeaconNode> {
        C1707a() {
        }

        /* renamed from: a */
        public int compare(BeaconNode beaconNode, BeaconNode beaconNode2) {
            if (beaconNode.meanRSSI() == beaconNode2.meanRSSI()) {
                return 0;
            }
            return beaconNode.meanRSSI() < beaconNode2.meanRSSI() ? 1 : -1;
        }
    }

    public C1706e(Application application) throws NaviBeesLicenseNotAuthorizedException, NaviBeesLicenseExpireException {
        super(application);
        HashMap venueProperties = NaviBeesManager.getInstance(application).getMetaDataManager().getVenueProperties();
        String str = "RSSI_d0";
        this.f1492e = venueProperties.get(str) != null ? Double.valueOf((String) venueProperties.get(str)).doubleValue() : -60.0d;
        String str2 = "eta";
        this.f1493f = venueProperties.get(str2) != null ? Double.valueOf((String) venueProperties.get(str2)).doubleValue() : 2.86d;
    }

    /* renamed from: d */
    private double[] m1095d(List<BeaconNode> list) {
        double d;
        double d2;
        double d3;
        double d4;
        double d5;
        double d6;
        List<BeaconNode> list2 = list;
        double d7 = 0.0d;
        double d8 = 0.0d;
        double d9 = 0.0d;
        double d10 = 0.0d;
        double d11 = 0.0d;
        double d12 = 0.0d;
        double d13 = 0.0d;
        double d14 = 0.0d;
        double d15 = 0.0d;
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                double d16 = ((BeaconNode) list2.get(i)).location.f1332x;
                double d17 = ((BeaconNode) list2.get(i)).location.f1333y;
                d7 = Math.pow(10.0d, (this.f1492e - ((BeaconNode) list2.get(i)).meanRSSI()) / (this.f1493f * 10.0d));
                d9 = d16;
                d10 = d17;
            }
            if (i == 1) {
                double d18 = ((BeaconNode) list2.get(i)).location.f1332x;
                double d19 = ((BeaconNode) list2.get(i)).location.f1333y;
                d8 = Math.pow(10.0d, (this.f1492e - ((BeaconNode) list2.get(i)).meanRSSI()) / (this.f1493f * 10.0d));
                d12 = d18;
                d13 = d19;
            }
            if (i == 2) {
                double d20 = ((BeaconNode) list2.get(i)).location.f1332x;
                double d21 = ((BeaconNode) list2.get(i)).location.f1333y;
                d11 = Math.pow(10.0d, (this.f1492e - ((BeaconNode) list2.get(i)).meanRSSI()) / (this.f1493f * 10.0d));
                d14 = d20;
                d15 = d21;
            }
        }
        if (d7 < 1.0d) {
            return new double[]{d9, d10};
        } else if (d8 < 1.0d) {
            return new double[]{d12, d13};
        } else if (d11 < 1.0d) {
            return new double[]{d14, d15};
        } else {
            double[] a = m1094a(d9, d10, d7, d12, d13, d8);
            double d22 = a[0];
            double d23 = a[1];
            double d24 = a[2];
            double d25 = a[3];
            double[] a2 = m1094a(d12, d13, d8, d14, d15, d11);
            double d26 = a2[0];
            double d27 = a2[1];
            double d28 = a2[2];
            double d29 = a2[3];
            double[] a3 = m1094a(d14, d15, d11, d9, d10, d7);
            double d30 = a3[0];
            double d31 = a3[1];
            double d32 = a3[2];
            double d33 = a3[3];
            double d34 = d14;
            double d35 = d15;
            if (m1093a(d22, d23, d34, d35) > m1093a(d24, d25, d34, d35)) {
                d2 = d24;
                d = d25;
            } else {
                d2 = d22;
                d = d23;
            }
            double d36 = d9;
            double d37 = d10;
            if (m1093a(d26, d27, d36, d37) > m1093a(d28, d29, d36, d37)) {
                d4 = d28;
                d3 = d29;
            } else {
                d4 = d26;
                d3 = d27;
            }
            double d38 = d12;
            double d39 = d13;
            if (m1093a(d30, d31, d38, d39) > m1093a(d32, d33, d38, d39)) {
                d6 = d32;
                d5 = d33;
            } else {
                d6 = d30;
                d5 = d31;
            }
            double d40 = (d22 == 0.0d && d23 == 0.0d && d24 == 0.0d && d25 == 0.0d) ? 1.0d : 0.0d;
            double d41 = (d26 == 0.0d && d27 == 0.0d && d28 == 0.0d && d29 == 0.0d) ? 1.0d : 0.0d;
            double d42 = (d30 == 0.0d && d31 == 0.0d && d32 == 0.0d && d33 == 0.0d) ? 1.0d : 0.0d;
            int i2 = (d40 > 0.0d ? 1 : (d40 == 0.0d ? 0 : -1));
            if (i2 == 0 && d41 == 0.0d && d42 == 0.0d) {
                return new double[]{((d2 + d4) + d6) / 3.0d, ((d + d3) + d5) / 3.0d};
            }
            int i3 = (d40 > 1.0d ? 1 : (d40 == 1.0d ? 0 : -1));
            if (i3 == 0 && d41 == 0.0d && d42 == 0.0d) {
                return new double[]{(d4 + d6) / 2.0d, (d3 + d5) / 2.0d};
            } else if (i2 == 0 && d41 == 1.0d && d42 == 0.0d) {
                return new double[]{(d2 + d6) / 2.0d, (d + d5) / 2.0d};
            } else if (i2 == 0 && d41 == 0.0d && d42 == 1.0d) {
                return new double[]{(d2 + d4) / 2.0d, (d + d3) / 2.0d};
            } else if (i3 == 0 && d41 == 1.0d && d42 == 0.0d) {
                return new double[]{d6, d5};
            } else if (i3 == 0 && d41 == 0.0d && d42 == 1.0d) {
                return new double[]{d4, d3};
            } else if (i2 != 0 || d41 != 1.0d || d42 != 1.0d) {
                return new double[]{0.0d, 0.0d};
            } else {
                return new double[]{d2, d};
            }
        }
    }

    /* renamed from: a */
    public CalculatedLocation mo29283a(List<BeaconNode> list) {
        IndoorLocation c = mo29287c(list);
        if (c == null) {
            c = mo29287c(list);
        }
        List a = mo29285a(list, c.buildingId, c.floor);
        int size = a.size();
        if (size <= 0) {
            return null;
        }
        Collections.sort(a, new C1707a());
        if (3 < size) {
            size = 3;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            arrayList.add(a.get(i));
        }
        double[] d = m1095d(arrayList);
        IndoorLocation indoorLocation = new IndoorLocation(d[0], d[1], c.floor);
        indoorLocation.floor = c.floor;
        indoorLocation.buildingId = c.buildingId;
        Venue currentVenue = NaviBeesManager.getInstance(this.f1362a).getMetaDataManager().getCurrentVenue();
        if (currentVenue != null) {
            indoorLocation.venueId = currentVenue.f1342id;
        }
        indoorLocation.confidence = mo29286b(arrayList);
        CalculatedLocation calculatedLocation = new CalculatedLocation();
        calculatedLocation.indoorLocation = indoorLocation;
        calculatedLocation.beaconNodes = arrayList;
        return calculatedLocation;
    }

    /* renamed from: a */
    private double[] m1094a(double d, double d2, double d3, double d4, double d5, double d6) {
        double[] dArr = new double[4];
        double d7 = d - d4;
        double d8 = d2 - d5;
        double sqrt = Math.sqrt((d7 * d7) + (d8 * d8));
        if (sqrt > d3 + d6) {
            dArr[0] = 0.0d;
            dArr[1] = 0.0d;
            dArr[2] = 0.0d;
            dArr[3] = 0.0d;
            return dArr;
        } else if (sqrt <= Math.abs(d6 - d3)) {
            dArr[0] = 0.0d;
            dArr[1] = 0.0d;
            dArr[2] = 0.0d;
            dArr[3] = 0.0d;
            return dArr;
        } else {
            double d9 = sqrt * sqrt;
            double d10 = d3 * d3;
            double d11 = d6 * d6;
            double d12 = d10 - d11;
            double d13 = d12 / (d9 * 2.0d);
            double sqrt2 = Math.sqrt(((((d10 + d11) * 2.0d) / d9) - ((d12 * d12) / (d9 * d9))) - 1.0d);
            double d14 = ((d + d4) / 2.0d) + ((d4 - d) * d13);
            double d15 = d5 - d2;
            double d16 = (sqrt2 * d15) / 2.0d;
            double d17 = d14 + d16;
            double d18 = d14 - d16;
            double d19 = ((d2 + d5) / 2.0d) + (d13 * d15);
            double d20 = (sqrt2 * d7) / 2.0d;
            double d21 = d19 + d20;
            double d22 = d19 - d20;
            dArr[0] = d17;
            dArr[1] = d21;
            dArr[2] = d18;
            dArr[3] = d22;
            return dArr;
        }
    }

    /* renamed from: a */
    private double m1093a(double d, double d2, double d3, double d4) {
        return Math.sqrt(Math.pow(d3 - d, 2.0d) + Math.pow(d4 - d2, 2.0d));
    }
}
