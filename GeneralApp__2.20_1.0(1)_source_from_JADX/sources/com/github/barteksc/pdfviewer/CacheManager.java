package com.github.barteksc.pdfviewer;

import android.graphics.RectF;
import com.github.barteksc.pdfviewer.model.PagePart;
import com.github.barteksc.pdfviewer.util.Constants.Cache;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

class CacheManager {
    private final PriorityQueue<PagePart> activeCache = new PriorityQueue<>(Cache.CACHE_SIZE, this.orderComparator);
    private final PagePartComparator orderComparator = new PagePartComparator();
    private final Object passiveActiveLock = new Object();
    private final PriorityQueue<PagePart> passiveCache = new PriorityQueue<>(Cache.CACHE_SIZE, this.orderComparator);
    private final List<PagePart> thumbnails = new ArrayList();

    class PagePartComparator implements Comparator<PagePart> {
        PagePartComparator() {
        }

        public int compare(PagePart part1, PagePart part2) {
            if (part1.getCacheOrder() == part2.getCacheOrder()) {
                return 0;
            }
            return part1.getCacheOrder() > part2.getCacheOrder() ? 1 : -1;
        }
    }

    public void cachePart(PagePart part) {
        synchronized (this.passiveActiveLock) {
            makeAFreeSpace();
            this.activeCache.offer(part);
        }
    }

    public void makeANewSet() {
        synchronized (this.passiveActiveLock) {
            this.passiveCache.addAll(this.activeCache);
            this.activeCache.clear();
        }
    }

    private void makeAFreeSpace() {
        synchronized (this.passiveActiveLock) {
            while (this.activeCache.size() + this.passiveCache.size() >= Cache.CACHE_SIZE && !this.passiveCache.isEmpty()) {
                ((PagePart) this.passiveCache.poll()).getRenderedBitmap().recycle();
            }
            while (this.activeCache.size() + this.passiveCache.size() >= Cache.CACHE_SIZE && !this.activeCache.isEmpty()) {
                ((PagePart) this.activeCache.poll()).getRenderedBitmap().recycle();
            }
        }
    }

    public void cacheThumbnail(PagePart part) {
        synchronized (this.thumbnails) {
            while (this.thumbnails.size() >= Cache.THUMBNAILS_CACHE_SIZE) {
                ((PagePart) this.thumbnails.remove(0)).getRenderedBitmap().recycle();
            }
            addWithoutDuplicates(this.thumbnails, part);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean upPartIfContained(int r8, android.graphics.RectF r9, int r10) {
        /*
            r7 = this;
            com.github.barteksc.pdfviewer.model.PagePart r6 = new com.github.barteksc.pdfviewer.model.PagePart
            r2 = 0
            r4 = 0
            r5 = 0
            r0 = r6
            r1 = r8
            r3 = r9
            r0.<init>(r1, r2, r3, r4, r5)
            java.lang.Object r1 = r7.passiveActiveLock
            monitor-enter(r1)
            java.util.PriorityQueue<com.github.barteksc.pdfviewer.model.PagePart> r2 = r7.passiveCache     // Catch:{ all -> 0x0033 }
            com.github.barteksc.pdfviewer.model.PagePart r2 = find(r2, r0)     // Catch:{ all -> 0x0033 }
            r3 = r2
            r4 = 1
            if (r2 == 0) goto L_0x0027
            java.util.PriorityQueue<com.github.barteksc.pdfviewer.model.PagePart> r2 = r7.passiveCache     // Catch:{ all -> 0x0033 }
            r2.remove(r3)     // Catch:{ all -> 0x0033 }
            r3.setCacheOrder(r10)     // Catch:{ all -> 0x0033 }
            java.util.PriorityQueue<com.github.barteksc.pdfviewer.model.PagePart> r2 = r7.activeCache     // Catch:{ all -> 0x0033 }
            r2.offer(r3)     // Catch:{ all -> 0x0033 }
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            return r4
        L_0x0027:
            java.util.PriorityQueue<com.github.barteksc.pdfviewer.model.PagePart> r2 = r7.activeCache     // Catch:{ all -> 0x0033 }
            com.github.barteksc.pdfviewer.model.PagePart r2 = find(r2, r0)     // Catch:{ all -> 0x0033 }
            if (r2 == 0) goto L_0x0030
            goto L_0x0031
        L_0x0030:
            r4 = 0
        L_0x0031:
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            return r4
        L_0x0033:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.barteksc.pdfviewer.CacheManager.upPartIfContained(int, android.graphics.RectF, int):boolean");
    }

    public boolean containsThumbnail(int page, RectF pageRelativeBounds) {
        PagePart fakePart = new PagePart(page, null, pageRelativeBounds, true, 0);
        synchronized (this.thumbnails) {
            for (PagePart part : this.thumbnails) {
                if (part.equals(fakePart)) {
                    return true;
                }
            }
            return false;
        }
    }

    private void addWithoutDuplicates(Collection<PagePart> collection, PagePart newPart) {
        for (PagePart part : collection) {
            if (part.equals(newPart)) {
                newPart.getRenderedBitmap().recycle();
                return;
            }
        }
        collection.add(newPart);
    }

    private static PagePart find(PriorityQueue<PagePart> vector, PagePart fakePart) {
        Iterator it = vector.iterator();
        while (it.hasNext()) {
            PagePart part = (PagePart) it.next();
            if (part.equals(fakePart)) {
                return part;
            }
        }
        return null;
    }

    public List<PagePart> getPageParts() {
        List<PagePart> parts;
        synchronized (this.passiveActiveLock) {
            parts = new ArrayList<>(this.passiveCache);
            parts.addAll(this.activeCache);
        }
        return parts;
    }

    public List<PagePart> getThumbnails() {
        List<PagePart> list;
        synchronized (this.thumbnails) {
            list = this.thumbnails;
        }
        return list;
    }

    public void recycle() {
        synchronized (this.passiveActiveLock) {
            Iterator it = this.passiveCache.iterator();
            while (it.hasNext()) {
                ((PagePart) it.next()).getRenderedBitmap().recycle();
            }
            this.passiveCache.clear();
            Iterator it2 = this.activeCache.iterator();
            while (it2.hasNext()) {
                ((PagePart) it2.next()).getRenderedBitmap().recycle();
            }
            this.activeCache.clear();
        }
        synchronized (this.thumbnails) {
            for (PagePart part : this.thumbnails) {
                part.getRenderedBitmap().recycle();
            }
            this.thumbnails.clear();
        }
    }
}
