package com.navibees.core.model.metadata.json;

import java.util.HashMap;

public class Location {
    public int floorIndex;

    /* renamed from: x */
    public double f1335x;

    /* renamed from: y */
    public double f1336y;

    public Location(double d, double d2, int i) {
        this.f1335x = d;
        this.f1336y = d2;
        this.floorIndex = i;
    }

    public String description() {
        HashMap hashMap = new HashMap();
        hashMap.put("x", String.valueOf(this.f1335x));
        hashMap.put("y", String.valueOf(this.f1336y));
        hashMap.put("floorIndex", String.valueOf(this.floorIndex));
        return hashMap.toString();
    }
}
