package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StaticCluster<T extends ClusterItem> implements Cluster<T> {
    private final LatLng mCenter;
    private final List<T> mItems = new ArrayList();

    public StaticCluster(LatLng center) {
        this.mCenter = center;
    }

    public boolean add(T t) {
        return this.mItems.add(t);
    }

    public LatLng getPosition() {
        return this.mCenter;
    }

    public boolean remove(T t) {
        return this.mItems.remove(t);
    }

    public Collection<T> getItems() {
        return this.mItems;
    }

    public int getSize() {
        return this.mItems.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StaticCluster{mCenter=");
        sb.append(this.mCenter);
        sb.append(", mItems.size=");
        sb.append(this.mItems.size());
        sb.append('}');
        return sb.toString();
    }

    public int hashCode() {
        return this.mCenter.hashCode() + this.mItems.hashCode();
    }

    public boolean equals(Object other) {
        boolean z = false;
        if (!(other instanceof StaticCluster)) {
            return false;
        }
        if (((StaticCluster) other).mCenter.equals(this.mCenter) && ((StaticCluster) other).mItems.equals(this.mItems)) {
            z = true;
        }
        return z;
    }
}
