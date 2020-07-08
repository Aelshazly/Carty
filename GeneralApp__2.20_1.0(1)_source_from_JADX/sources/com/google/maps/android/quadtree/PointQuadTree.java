package com.google.maps.android.quadtree;

import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.quadtree.PointQuadTree.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PointQuadTree<T extends Item> {
    private static final int MAX_DEPTH = 40;
    private static final int MAX_ELEMENTS = 50;
    private final Bounds mBounds;
    private List<PointQuadTree<T>> mChildren;
    private final int mDepth;
    private List<T> mItems;

    public interface Item {
        Point getPoint();
    }

    public PointQuadTree(double minX, double maxX, double minY, double maxY) {
        Bounds bounds = new Bounds(minX, maxX, minY, maxY);
        this(bounds);
    }

    public PointQuadTree(Bounds bounds) {
        this(bounds, 0);
    }

    private PointQuadTree(double minX, double maxX, double minY, double maxY, int depth) {
        Bounds bounds = new Bounds(minX, maxX, minY, maxY);
        this(bounds, depth);
    }

    private PointQuadTree(Bounds bounds, int depth) {
        this.mChildren = null;
        this.mBounds = bounds;
        this.mDepth = depth;
    }

    public void add(T item) {
        Point point = item.getPoint();
        if (this.mBounds.contains(point.f89x, point.f90y)) {
            insert(point.f89x, point.f90y, item);
        }
    }

    private void insert(double x, double y, T item) {
        if (this.mChildren != null) {
            if (y < this.mBounds.midY) {
                if (x < this.mBounds.midX) {
                    ((PointQuadTree) this.mChildren.get(0)).insert(x, y, item);
                } else {
                    ((PointQuadTree) this.mChildren.get(1)).insert(x, y, item);
                }
            } else if (x < this.mBounds.midX) {
                ((PointQuadTree) this.mChildren.get(2)).insert(x, y, item);
            } else {
                ((PointQuadTree) this.mChildren.get(3)).insert(x, y, item);
            }
            return;
        }
        if (this.mItems == null) {
            this.mItems = new ArrayList();
        }
        this.mItems.add(item);
        if (this.mItems.size() > 50 && this.mDepth < 40) {
            split();
        }
    }

    private void split() {
        this.mChildren = new ArrayList(4);
        List<PointQuadTree<T>> list = this.mChildren;
        PointQuadTree pointQuadTree = new PointQuadTree(this.mBounds.minX, this.mBounds.midX, this.mBounds.minY, this.mBounds.midY, this.mDepth + 1);
        list.add(pointQuadTree);
        List<PointQuadTree<T>> list2 = this.mChildren;
        PointQuadTree pointQuadTree2 = new PointQuadTree(this.mBounds.midX, this.mBounds.maxX, this.mBounds.minY, this.mBounds.midY, this.mDepth + 1);
        list2.add(pointQuadTree2);
        List<PointQuadTree<T>> list3 = this.mChildren;
        PointQuadTree pointQuadTree3 = new PointQuadTree(this.mBounds.minX, this.mBounds.midX, this.mBounds.midY, this.mBounds.maxY, this.mDepth + 1);
        list3.add(pointQuadTree3);
        List<PointQuadTree<T>> list4 = this.mChildren;
        PointQuadTree pointQuadTree4 = new PointQuadTree(this.mBounds.midX, this.mBounds.maxX, this.mBounds.midY, this.mBounds.maxY, this.mDepth + 1);
        list4.add(pointQuadTree4);
        List<T> items = this.mItems;
        this.mItems = null;
        for (T item : items) {
            insert(item.getPoint().f89x, item.getPoint().f90y, item);
        }
    }

    public boolean remove(T item) {
        Point point = item.getPoint();
        if (!this.mBounds.contains(point.f89x, point.f90y)) {
            return false;
        }
        return remove(point.f89x, point.f90y, item);
    }

    private boolean remove(double x, double y, T item) {
        if (this.mChildren == null) {
            List<T> list = this.mItems;
            if (list == null) {
                return false;
            }
            return list.remove(item);
        } else if (y < this.mBounds.midY) {
            if (x < this.mBounds.midX) {
                return ((PointQuadTree) this.mChildren.get(0)).remove(x, y, item);
            }
            return ((PointQuadTree) this.mChildren.get(1)).remove(x, y, item);
        } else if (x < this.mBounds.midX) {
            return ((PointQuadTree) this.mChildren.get(2)).remove(x, y, item);
        } else {
            return ((PointQuadTree) this.mChildren.get(3)).remove(x, y, item);
        }
    }

    public void clear() {
        this.mChildren = null;
        List<T> list = this.mItems;
        if (list != null) {
            list.clear();
        }
    }

    public Collection<T> search(Bounds searchBounds) {
        List<T> results = new ArrayList<>();
        search(searchBounds, results);
        return results;
    }

    private void search(Bounds searchBounds, Collection<T> results) {
        if (this.mBounds.intersects(searchBounds)) {
            List<PointQuadTree<T>> list = this.mChildren;
            if (list != null) {
                for (PointQuadTree<T> quad : list) {
                    quad.search(searchBounds, results);
                }
            } else if (this.mItems != null) {
                if (searchBounds.contains(this.mBounds)) {
                    results.addAll(this.mItems);
                } else {
                    for (T item : this.mItems) {
                        if (searchBounds.contains(item.getPoint())) {
                            results.add(item);
                        }
                    }
                }
            }
        }
    }
}
