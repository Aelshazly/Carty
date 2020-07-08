package com.google.maps.android.clustering.algo;

import androidx.collection.LruCache;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PreCachingAlgorithmDecorator<T extends ClusterItem> implements Algorithm<T> {
    private final Algorithm<T> mAlgorithm;
    private final LruCache<Integer, Set<? extends Cluster<T>>> mCache = new LruCache<>(5);
    private final ReadWriteLock mCacheLock = new ReentrantReadWriteLock();

    private class PrecacheRunnable implements Runnable {
        private final int mZoom;

        public PrecacheRunnable(int zoom) {
            this.mZoom = zoom;
        }

        public void run() {
            try {
                Thread.sleep((long) ((Math.random() * 500.0d) + 500.0d));
            } catch (InterruptedException e) {
            }
            PreCachingAlgorithmDecorator.this.getClustersInternal(this.mZoom);
        }
    }

    public PreCachingAlgorithmDecorator(Algorithm<T> algorithm) {
        this.mAlgorithm = algorithm;
    }

    public void addItem(T item) {
        this.mAlgorithm.addItem(item);
        clearCache();
    }

    public void addItems(Collection<T> items) {
        this.mAlgorithm.addItems(items);
        clearCache();
    }

    public void clearItems() {
        this.mAlgorithm.clearItems();
        clearCache();
    }

    public void removeItem(T item) {
        this.mAlgorithm.removeItem(item);
        clearCache();
    }

    private void clearCache() {
        this.mCache.evictAll();
    }

    public Set<? extends Cluster<T>> getClusters(double zoom) {
        int discreteZoom = (int) zoom;
        Set<? extends Cluster<T>> results = getClustersInternal(discreteZoom);
        if (this.mCache.get(Integer.valueOf(discreteZoom + 1)) == null) {
            new Thread(new PrecacheRunnable(discreteZoom + 1)).start();
        }
        if (this.mCache.get(Integer.valueOf(discreteZoom - 1)) == null) {
            new Thread(new PrecacheRunnable(discreteZoom - 1)).start();
        }
        return results;
    }

    public Collection<T> getItems() {
        return this.mAlgorithm.getItems();
    }

    /* access modifiers changed from: private */
    public Set<? extends Cluster<T>> getClustersInternal(int discreteZoom) {
        this.mCacheLock.readLock().lock();
        Set<? extends Cluster<T>> results = (Set) this.mCache.get(Integer.valueOf(discreteZoom));
        this.mCacheLock.readLock().unlock();
        if (results == null) {
            this.mCacheLock.writeLock().lock();
            results = (Set) this.mCache.get(Integer.valueOf(discreteZoom));
            if (results == null) {
                results = this.mAlgorithm.getClusters((double) discreteZoom);
                this.mCache.put(Integer.valueOf(discreteZoom), results);
            }
            this.mCacheLock.writeLock().unlock();
        }
        return results;
    }
}
