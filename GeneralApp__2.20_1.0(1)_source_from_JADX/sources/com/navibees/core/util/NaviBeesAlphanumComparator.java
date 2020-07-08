package com.navibees.core.util;

import com.navibees.core.model.metadata.json.Facility;
import com.navibees.core.model.metadata.json.POI;
import com.navibees.core.model.metadata.json.POICategory;

public class NaviBeesAlphanumComparator extends AlphanumComparator {

    /* renamed from: a */
    private ComparatorType f1609a = ComparatorType.BY_NAME;

    public NaviBeesAlphanumComparator(ComparatorType comparatorType) {
        if (comparatorType != null) {
            this.f1609a = comparatorType;
        }
    }

    /* renamed from: a */
    private int m1177a(POI poi, POI poi2) {
        if (this.f1609a.equals(ComparatorType.BY_NAME)) {
            return super.compare(poi.name.getText(), poi2.name.getText());
        }
        if (this.f1609a.equals(ComparatorType.BY_ID)) {
            return poi.f1339id.compareTo(poi2.f1339id);
        }
        return 0;
    }

    public int compare(Object obj, Object obj2) {
        if ((obj instanceof POI) && (obj2 instanceof POI)) {
            return m1177a((POI) obj, (POI) obj2);
        }
        if ((obj instanceof POICategory) && (obj2 instanceof POICategory)) {
            return m1178a((POICategory) obj, (POICategory) obj2);
        }
        if (!(obj instanceof Facility) || !(obj2 instanceof Facility)) {
            return 0;
        }
        return m1176a((Facility) obj, (Facility) obj2);
    }

    /* renamed from: a */
    private int m1178a(POICategory pOICategory, POICategory pOICategory2) {
        if (this.f1609a.equals(ComparatorType.BY_NAME)) {
            return super.compare(pOICategory.name.getText(), pOICategory2.name.getText());
        }
        if (this.f1609a.equals(ComparatorType.BY_ID)) {
            return pOICategory.f1340id.compareTo(pOICategory2.f1340id);
        }
        return 0;
    }

    /* renamed from: a */
    private int m1176a(Facility facility, Facility facility2) {
        if (this.f1609a.equals(ComparatorType.BY_NAME)) {
            return super.compare(facility.name.getText(), facility2.name.getText());
        }
        if (this.f1609a.equals(ComparatorType.BY_ID)) {
            return facility.f1330id.compareTo(facility2.f1330id);
        }
        return 0;
    }
}
