package com.navibees.core.model.postioning;

import android.app.Application;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.license.NaviBeesFeature;
import com.navibees.core.model.license.NaviBeesLicenseExpireException;
import com.navibees.core.model.license.NaviBeesLicenseNotAuthorizedException;
import com.navibees.core.model.metadata.BeaconNode;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.IndoorLocationConfidence;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public abstract class PositionLocator {

    /* renamed from: c */
    public static double f1360c;

    /* renamed from: d */
    public static double f1361d;

    /* renamed from: a */
    protected Application f1362a;

    /* renamed from: b */
    private final int f1363b = 3;

    public class CalculatedLocation {
        public List<BeaconNode> beaconNodes;
        public IndoorLocation indoorLocation;
        public double locDifference;
        public IndoorLocation modifiedLocation;

        /* renamed from: pc */
        public int f1367pc;
        public int pcCounter;

        public CalculatedLocation() {
        }
    }

    public enum LOCALIZER_ALGORITHM {
        WEIGHTED_CENTROID,
        TRILLATERATION,
        BOTH
    }

    public PositionLocator(Application application) throws NaviBeesLicenseNotAuthorizedException, NaviBeesLicenseExpireException {
        NaviBeesManager.getInstance(application).getLicenseManager().mo29048a(NaviBeesFeature.Positioning);
        this.f1362a = application;
    }

    /* renamed from: d */
    private double mo29309d(List<BeaconNode> list) {
        if (list.size() > 3) {
            Collections.sort(list, new Comparator<BeaconNode>() {
                public int compare(BeaconNode beaconNode, BeaconNode beaconNode2) {
                    return Integer.valueOf((int) beaconNode2.meanRSSI()).compareTo(Integer.valueOf((int) beaconNode.meanRSSI()));
                }
            });
            list.subList(3, list.size()).clear();
        }
        Double valueOf = Double.valueOf(0.0d);
        if (list.isEmpty()) {
            return valueOf.doubleValue();
        }
        for (BeaconNode meanRSSI : list) {
            valueOf = Double.valueOf(valueOf.doubleValue() + meanRSSI.meanRSSI());
        }
        return valueOf.doubleValue() / ((double) list.size());
    }

    /* renamed from: a */
    public abstract CalculatedLocation mo29283a(List<BeaconNode> list);

    /* renamed from: a */
    public List<Entry<String, Integer>> mo29284a(HashMap<String, Integer> hashMap) {
        ArrayList arrayList = new ArrayList(hashMap.entrySet());
        Collections.sort(arrayList, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> entry, Entry<String, Integer> entry2) {
                return ((Integer) entry2.getValue()).compareTo((Integer) entry.getValue());
            }
        });
        return arrayList;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public IndoorLocationConfidence mo29286b(List<BeaconNode> list) {
        int size = list.size();
        if (size == 0) {
            return IndoorLocationConfidence.Low;
        }
        if (size == 1 || size == 2) {
            return IndoorLocationConfidence.Average;
        }
        return IndoorLocationConfidence.High;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public IndoorLocation mo29287c(List<BeaconNode> list) {
        HashMap hashMap = new HashMap();
        for (BeaconNode beaconNode : list) {
            List list2 = (List) hashMap.get(beaconNode.getUniqueId());
            if (list2 == null) {
                list2 = new ArrayList();
            }
            list2.add(beaconNode);
            hashMap.put(beaconNode.getUniqueId(), list2);
        }
        RSSIMAP rssimap = new RSSIMAP();
        int minimumBeacons = NaviBeesManager.getInstance(this.f1362a).getMetaDataManager().getMinimumBeacons();
        double d = -200.0d;
        BeaconNode beaconNode2 = null;
        String str = "";
        for (String str2 : hashMap.keySet()) {
            List list3 = (List) hashMap.get(str2);
            double d2 = mo29309d(list3);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" Floor:");
            sb.append(((BeaconNode) list3.get(0)).location.floor);
            sb.append("| rssi=");
            sb.append((int) d2);
            String sb2 = sb.toString();
            rssimap.map.put(Integer.valueOf(((BeaconNode) list3.get(0)).location.floor), Double.valueOf(d2));
            if (d2 > d && list3.size() >= minimumBeacons) {
                beaconNode2 = (BeaconNode) list3.get(0);
                d = d2;
            }
            str = sb2;
        }
        Intent intent = new Intent();
        intent.setAction("avg_rssi_dbg");
        intent.putExtra("value", str);
        intent.putExtra("map", rssimap);
        LocalBroadcastManager.getInstance(this.f1362a).sendBroadcast(intent);
        IndoorLocation indoorLocation = new IndoorLocation(-1.0d, -1.0d);
        indoorLocation.floor = beaconNode2.location.floor;
        indoorLocation.buildingId = beaconNode2.buildingId;
        return indoorLocation;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public List<BeaconNode> mo29285a(List<BeaconNode> list, String str, int i) {
        ArrayList arrayList = new ArrayList();
        for (BeaconNode beaconNode : list) {
            if (beaconNode.location.floor == i && beaconNode.buildingId.equals(str)) {
                arrayList.add(beaconNode);
            }
        }
        return arrayList;
    }
}
