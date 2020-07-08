package com.google.maps.android.clustering.algo;

import androidx.collection.LongSparseArray;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GridBasedAlgorithm<T extends ClusterItem> implements Algorithm<T> {
    private static final int GRID_SIZE = 100;
    private final Set<T> mItems = Collections.synchronizedSet(new HashSet());

    public void addItem(T item) {
        this.mItems.add(item);
    }

    public void addItems(Collection<T> items) {
        this.mItems.addAll(items);
    }

    public void clearItems() {
        this.mItems.clear();
    }

    public void removeItem(T item) {
        this.mItems.remove(item);
    }

    public Set<? extends Cluster<T>> getClusters(double zoom) {
        long numCells;
        long numCells2 = (long) Math.ceil((Math.pow(2.0d, zoom) * 256.0d) / 100.0d);
        SphericalMercatorProjection proj = new SphericalMercatorProjection((double) numCells2);
        HashSet hashSet = new HashSet();
        LongSparseArray longSparseArray = new LongSparseArray();
        synchronized (this.mItems) {
            try {
                Iterator it = this.mItems.iterator();
                while (it.hasNext()) {
                    ClusterItem clusterItem = (ClusterItem) it.next();
                    Point point = proj.toPoint(clusterItem.getPosition());
                    Iterator it2 = it;
                    ClusterItem clusterItem2 = clusterItem;
                    Point p = point;
                    long coord = getCoord(numCells2, point.f89x, point.f90y);
                    StaticCluster staticCluster = (StaticCluster) longSparseArray.get(coord);
                    if (staticCluster == null) {
                        numCells = numCells2;
                        staticCluster = new StaticCluster(proj.toLatLng(new Point(Math.floor(p.f89x) + 0.5d, Math.floor(p.f90y) + 0.5d)));
                        longSparseArray.put(coord, staticCluster);
                        hashSet.add(staticCluster);
                    } else {
                        numCells = numCells2;
                    }
                    staticCluster.add(clusterItem2);
                    double d = zoom;
                    numCells2 = numCells;
                    it = it2;
                }
                return hashSet;
            } catch (Throwable th) {
                th = th;
                throw th;
            }
        }
    }

    public Collection<T> getItems() {
        return this.mItems;
    }

    private static long getCoord(long numCells, double x, double y) {
        return (long) ((((double) numCells) * Math.floor(x)) + Math.floor(y));
    }
}
