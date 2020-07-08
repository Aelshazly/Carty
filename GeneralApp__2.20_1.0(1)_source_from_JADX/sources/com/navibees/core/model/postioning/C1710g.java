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
import java.util.List;

/* renamed from: com.navibees.core.model.postioning.g */
/* compiled from: WeightedCentroidPositionLocator */
public final class C1710g extends PositionLocator {

    /* renamed from: e */
    static final String f1526e = "WeightedCentroidPositionLocator";

    /* renamed from: f */
    static final int f1527f = 4;

    /* renamed from: com.navibees.core.model.postioning.g$a */
    /* compiled from: WeightedCentroidPositionLocator */
    class C1711a implements Comparator<BeaconNode> {
        C1711a() {
        }

        /* renamed from: a */
        public int compare(BeaconNode beaconNode, BeaconNode beaconNode2) {
            if (beaconNode.meanRSSI() == beaconNode2.meanRSSI()) {
                return 0;
            }
            return beaconNode.meanRSSI() < beaconNode2.meanRSSI() ? 1 : -1;
        }
    }

    public C1710g(Application application) throws NaviBeesLicenseNotAuthorizedException, NaviBeesLicenseExpireException {
        super(application);
    }

    /* renamed from: b */
    private double m1108b(double d) {
        return Math.sqrt(Math.pow(Math.pow(10.0d, d / 10.0d), 1.0d));
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
        ArrayList arrayList = new ArrayList();
        Collections.sort(a, new C1711a());
        if (4 < size) {
            size = 4;
        }
        double d = 0.0d;
        for (int i = 0; i < size; i++) {
            double a2 = m1105a(((BeaconNode) a.get(i)).meanRSSI());
            arrayList.add(i, Double.valueOf(a2));
            d += a2;
        }
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i2 = 0;
        while (i2 < size) {
            double doubleValue = ((Double) arrayList.get(i2)).doubleValue() / d;
            d2 += ((BeaconNode) a.get(i2)).location.f1332x * doubleValue;
            int i3 = i2;
            d3 += doubleValue * ((BeaconNode) a.get(i3)).location.f1333y;
            i2 = i3 + 1;
            d = d;
        }
        IndoorLocation indoorLocation = new IndoorLocation(d2, d3, c.floor);
        indoorLocation.buildingId = c.buildingId;
        Venue currentVenue = NaviBeesManager.getInstance(this.f1362a).getMetaDataManager().getCurrentVenue();
        if (currentVenue != null) {
            indoorLocation.venueId = currentVenue.f1342id;
        }
        indoorLocation.confidence = mo29286b(a);
        ArrayList arrayList2 = new ArrayList();
        for (int i4 = 0; i4 < size; i4++) {
            arrayList2.add(a.get(i4));
        }
        CalculatedLocation calculatedLocation = new CalculatedLocation();
        calculatedLocation.indoorLocation = indoorLocation;
        calculatedLocation.beaconNodes = arrayList2;
        return calculatedLocation;
    }

    /* renamed from: a */
    private float m1107a(float f, float f2, float f3, float f4, float f5, float f6) {
        float a = m1106a(f, f2, f3, f4);
        float a2 = m1106a(f3, f4, f5, f6);
        float a3 = m1106a(f, f2, f5, f6);
        float f7 = ((a + a2) + a3) / 2.0f;
        return (((float) Math.sqrt((double) ((((f7 - a) * f7) * (f7 - a2)) * (f7 - a3)))) * 2.0f) / a2;
    }

    /* renamed from: a */
    private float m1106a(float f, float f2, float f3, float f4) {
        float f5 = f3 - f;
        float f6 = f4 - f2;
        return (float) Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
    }

    /* renamed from: a */
    private double m1105a(double d) {
        return Math.pow(Math.pow(10.0d, d / 20.0d), 1.3d);
    }
}
