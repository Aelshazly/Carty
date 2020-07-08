package com.navibees.core.model.postioning;

import android.app.Application;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.license.NaviBeesLicenseExpireException;
import com.navibees.core.model.license.NaviBeesLicenseNotAuthorizedException;
import com.navibees.core.model.metadata.BeaconNode;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.Venue;
import com.navibees.core.model.postioning.PositionLocator.CalculatedLocation;
import com.navibees.core.model.postioning.p026i.C1716b;
import com.navibees.core.model.postioning.p026i.C1717c;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

public class TrillaterationPositionLocator extends PositionLocator {

    /* renamed from: K */
    public static int f1402K = 3;

    /* renamed from: e */
    private double f1403e;

    /* renamed from: f */
    private double f1404f;

    public TrillaterationPositionLocator(Application application) throws NaviBeesLicenseNotAuthorizedException, NaviBeesLicenseExpireException {
        super(application);
        HashMap venueProperties = NaviBeesManager.getInstance(application).getMetaDataManager().getVenueProperties();
        String str = "RSSI_d0";
        this.f1403e = venueProperties.get(str) != null ? Double.valueOf((String) venueProperties.get(str)).doubleValue() : -60.0d;
        String str2 = "eta";
        this.f1404f = venueProperties.get(str2) != null ? Double.valueOf((String) venueProperties.get(str2)).doubleValue() : 2.86d;
    }

    /* renamed from: e */
    private double[] m1027e(List<BeaconNode> list) {
        double[] dArr = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            dArr[i] = Math.pow(10.0d, (this.f1403e - ((BeaconNode) list.get(i)).meanRSSI()) / (this.f1404f * 10.0d));
        }
        return dArr;
    }

    /* renamed from: f */
    private double[][] m1028f(List<BeaconNode> list) {
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{list.size(), 2});
        for (int i = 0; i < list.size(); i++) {
            dArr[i] = new double[]{((BeaconNode) list.get(i)).location.f1332x, ((BeaconNode) list.get(i)).location.f1333y};
        }
        return dArr;
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
        new ArrayList();
        Collections.sort(a, new Comparator<BeaconNode>() {
            /* renamed from: a */
            public int compare(BeaconNode beaconNode, BeaconNode beaconNode2) {
                if (beaconNode.meanRSSI() == beaconNode2.meanRSSI()) {
                    return 0;
                }
                return beaconNode.meanRSSI() < beaconNode2.meanRSSI() ? 1 : -1;
            }
        });
        int i = f1402K;
        if (i < size) {
            size = i;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(a.get(i2));
        }
        IndoorLocation d = mo29309d(arrayList);
        d.floor = c.floor;
        d.buildingId = c.buildingId;
        Venue currentVenue = NaviBeesManager.getInstance(this.f1362a).getMetaDataManager().getCurrentVenue();
        if (currentVenue != null) {
            d.venueId = currentVenue.f1342id;
        }
        d.confidence = mo29286b(arrayList);
        CalculatedLocation calculatedLocation = new CalculatedLocation();
        calculatedLocation.indoorLocation = d;
        calculatedLocation.beaconNodes = arrayList;
        return calculatedLocation;
    }

    /* renamed from: d */
    public IndoorLocation mo29309d(List<BeaconNode> list) {
        double[] array = new C1716b(new C1717c(m1028f(list), m1027e(list)), new LevenbergMarquardtOptimizer()).mo29393a().getPoint().toArray();
        if (array.length > 0) {
            return new IndoorLocation(array[0], array[1]);
        }
        return null;
    }
}
