package p008cz.msebera.android.httpclient.pool;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import p008cz.msebera.android.httpclient.concurrent.FutureCallback;
import p008cz.msebera.android.httpclient.pool.PoolEntry;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;

/* renamed from: cz.msebera.android.httpclient.pool.AbstractConnPool */
public abstract class AbstractConnPool<T, C, E extends PoolEntry<T, C>> implements ConnPool<T, E>, ConnPoolControl<T> {
    private final LinkedList<E> available = new LinkedList<>();
    private final ConnFactory<T, C> connFactory;
    private volatile int defaultMaxPerRoute;
    private volatile boolean isShutDown;
    private final Set<E> leased = new HashSet();
    private final Lock lock = new ReentrantLock();
    private final Map<T, Integer> maxPerRoute = new HashMap();
    private volatile int maxTotal;
    private final LinkedList<PoolEntryFuture<E>> pending = new LinkedList<>();
    private final Map<T, RouteSpecificPool<T, C, E>> routeToPool = new HashMap();

    /* access modifiers changed from: protected */
    public abstract E createEntry(T t, C c);

    public AbstractConnPool(ConnFactory<T, C> connFactory2, int defaultMaxPerRoute2, int maxTotal2) {
        this.connFactory = (ConnFactory) Args.notNull(connFactory2, "Connection factory");
        this.defaultMaxPerRoute = Args.notNegative(defaultMaxPerRoute2, "Max per route value");
        this.maxTotal = Args.notNegative(maxTotal2, "Max total value");
    }

    /* access modifiers changed from: protected */
    public void onLease(E e) {
    }

    /* access modifiers changed from: protected */
    public void onRelease(E e) {
    }

    public boolean isShutdown() {
        return this.isShutDown;
    }

    public void shutdown() throws IOException {
        if (!this.isShutDown) {
            this.isShutDown = true;
            this.lock.lock();
            try {
                Iterator it = this.available.iterator();
                while (it.hasNext()) {
                    ((PoolEntry) it.next()).close();
                }
                for (E entry : this.leased) {
                    entry.close();
                }
                for (RouteSpecificPool<T, C, E> pool : this.routeToPool.values()) {
                    pool.shutdown();
                }
                this.routeToPool.clear();
                this.leased.clear();
                this.available.clear();
            } finally {
                this.lock.unlock();
            }
        }
    }

    private RouteSpecificPool<T, C, E> getPool(final T route) {
        RouteSpecificPool<T, C, E> pool = (RouteSpecificPool) this.routeToPool.get(route);
        if (pool != null) {
            return pool;
        }
        RouteSpecificPool<T, C, E> pool2 = new RouteSpecificPool<T, C, E>(route) {
            /* access modifiers changed from: protected */
            public E createEntry(C conn) {
                return AbstractConnPool.this.createEntry(route, conn);
            }
        };
        this.routeToPool.put(route, pool2);
        return pool2;
    }

    public Future<E> lease(T route, Object state, FutureCallback<E> callback) {
        Args.notNull(route, "Route");
        Asserts.check(!this.isShutDown, "Connection pool shut down");
        final T t = route;
        final Object obj = state;
        C13002 r2 = new PoolEntryFuture<E>(this.lock, callback) {
            public E getPoolEntry(long timeout, TimeUnit tunit) throws InterruptedException, TimeoutException, IOException {
                E entry = AbstractConnPool.this.getPoolEntryBlocking(t, obj, timeout, tunit, this);
                AbstractConnPool.this.onLease(entry);
                return entry;
            }
        };
        return r2;
    }

    public Future<E> lease(T route, Object state) {
        return lease(route, state, null);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b5, code lost:
        if (r1.available.size() <= (r10 - 1)) goto L_0x00d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00bd, code lost:
        if (r1.available.isEmpty() != false) goto L_0x00d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00bf, code lost:
        r14 = (p008cz.msebera.android.httpclient.pool.PoolEntry) r1.available.removeLast();
        r14.close();
        getPool(r14.getRoute()).remove(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r11 = r6.add(r1.connFactory.create(r19));
        r16 = r0;
        r1.leased.add(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ea, code lost:
        r1.lock.unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f0, code lost:
        return r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public E getPoolEntryBlocking(T r19, java.lang.Object r20, long r21, java.util.concurrent.TimeUnit r23, p008cz.msebera.android.httpclient.pool.PoolEntryFuture<E> r24) throws java.io.IOException, java.lang.InterruptedException, java.util.concurrent.TimeoutException {
        /*
            r18 = this;
            r1 = r18
            r2 = r21
            r4 = r24
            r0 = 0
            r5 = 0
            int r7 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x001f
            java.util.Date r5 = new java.util.Date
            long r6 = java.lang.System.currentTimeMillis()
            r8 = r23
            long r9 = r8.toMillis(r2)
            long r6 = r6 + r9
            r5.<init>(r6)
            r0 = r5
            goto L_0x0022
        L_0x001f:
            r8 = r23
            r5 = r0
        L_0x0022:
            java.util.concurrent.locks.Lock r0 = r1.lock
            r0.lock()
            cz.msebera.android.httpclient.pool.RouteSpecificPool r0 = r18.getPool(r19)     // Catch:{ all -> 0x0150 }
            r6 = r0
            r0 = 0
        L_0x002d:
            if (r0 != 0) goto L_0x0141
            boolean r7 = r1.isShutDown     // Catch:{ all -> 0x0150 }
            r9 = 1
            r10 = 0
            if (r7 != 0) goto L_0x0037
            r7 = 1
            goto L_0x0038
        L_0x0037:
            r7 = 0
        L_0x0038:
            java.lang.String r11 = "Connection pool shut down"
            p008cz.msebera.android.httpclient.util.Asserts.check(r7, r11)     // Catch:{ all -> 0x0150 }
        L_0x003d:
            r7 = r20
            cz.msebera.android.httpclient.pool.PoolEntry r11 = r6.getFree(r7)     // Catch:{ all -> 0x013d }
            if (r11 != 0) goto L_0x0046
            goto L_0x005a
        L_0x0046:
            boolean r0 = r11.isClosed()     // Catch:{ all -> 0x013d }
            if (r0 != 0) goto L_0x012d
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x013d }
            boolean r0 = r11.isExpired(r12)     // Catch:{ all -> 0x013d }
            if (r0 == 0) goto L_0x005a
            r15 = r19
            goto L_0x012f
        L_0x005a:
            if (r11 == 0) goto L_0x006d
            java.util.LinkedList<E> r0 = r1.available     // Catch:{ all -> 0x013d }
            r0.remove(r11)     // Catch:{ all -> 0x013d }
            java.util.Set<E> r0 = r1.leased     // Catch:{ all -> 0x013d }
            r0.add(r11)     // Catch:{ all -> 0x013d }
            java.util.concurrent.locks.Lock r0 = r1.lock
            r0.unlock()
            return r11
        L_0x006d:
            int r0 = r18.getMax(r19)     // Catch:{ all -> 0x013d }
            r12 = r0
            int r0 = r6.getAllocatedCount()     // Catch:{ all -> 0x013d }
            int r0 = r0 + r9
            int r0 = r0 - r12
            int r0 = java.lang.Math.max(r10, r0)     // Catch:{ all -> 0x013d }
            r9 = r0
            if (r9 <= 0) goto L_0x0098
            r0 = 0
        L_0x0080:
            if (r0 >= r9) goto L_0x0098
            cz.msebera.android.httpclient.pool.PoolEntry r13 = r6.getLastUsed()     // Catch:{ all -> 0x013d }
            if (r13 != 0) goto L_0x0089
            goto L_0x0098
        L_0x0089:
            r13.close()     // Catch:{ all -> 0x013d }
            java.util.LinkedList<E> r14 = r1.available     // Catch:{ all -> 0x013d }
            r14.remove(r13)     // Catch:{ all -> 0x013d }
            r6.remove(r13)     // Catch:{ all -> 0x013d }
            int r0 = r0 + 1
            goto L_0x0080
        L_0x0098:
            int r0 = r6.getAllocatedCount()     // Catch:{ all -> 0x013d }
            if (r0 >= r12) goto L_0x00f6
            java.util.Set<E> r0 = r1.leased     // Catch:{ all -> 0x013d }
            int r0 = r0.size()     // Catch:{ all -> 0x013d }
            int r13 = r1.maxTotal     // Catch:{ all -> 0x013d }
            int r13 = r13 - r0
            int r10 = java.lang.Math.max(r13, r10)     // Catch:{ all -> 0x013d }
            if (r10 <= 0) goto L_0x00f1
            java.util.LinkedList<E> r13 = r1.available     // Catch:{ all -> 0x013d }
            int r13 = r13.size()     // Catch:{ all -> 0x013d }
            int r14 = r10 + -1
            if (r13 <= r14) goto L_0x00d5
            java.util.LinkedList<E> r14 = r1.available     // Catch:{ all -> 0x013d }
            boolean r14 = r14.isEmpty()     // Catch:{ all -> 0x013d }
            if (r14 != 0) goto L_0x00d5
            java.util.LinkedList<E> r14 = r1.available     // Catch:{ all -> 0x013d }
            java.lang.Object r14 = r14.removeLast()     // Catch:{ all -> 0x013d }
            cz.msebera.android.httpclient.pool.PoolEntry r14 = (p008cz.msebera.android.httpclient.pool.PoolEntry) r14     // Catch:{ all -> 0x013d }
            r14.close()     // Catch:{ all -> 0x013d }
            java.lang.Object r15 = r14.getRoute()     // Catch:{ all -> 0x013d }
            cz.msebera.android.httpclient.pool.RouteSpecificPool r15 = r1.getPool(r15)     // Catch:{ all -> 0x013d }
            r15.remove(r14)     // Catch:{ all -> 0x013d }
        L_0x00d5:
            cz.msebera.android.httpclient.pool.ConnFactory<T, C> r14 = r1.connFactory     // Catch:{ all -> 0x013d }
            r15 = r19
            java.lang.Object r14 = r14.create(r15)     // Catch:{ all -> 0x014e }
            cz.msebera.android.httpclient.pool.PoolEntry r16 = r6.add(r14)     // Catch:{ all -> 0x014e }
            r11 = r16
            r16 = r0
            java.util.Set<E> r0 = r1.leased     // Catch:{ all -> 0x014e }
            r0.add(r11)     // Catch:{ all -> 0x014e }
            java.util.concurrent.locks.Lock r0 = r1.lock
            r0.unlock()
            return r11
        L_0x00f1:
            r15 = r19
            r16 = r0
            goto L_0x00f8
        L_0x00f6:
            r15 = r19
        L_0x00f8:
            r10 = 0
            r6.queue(r4)     // Catch:{ all -> 0x0123 }
            java.util.LinkedList<cz.msebera.android.httpclient.pool.PoolEntryFuture<E>> r0 = r1.pending     // Catch:{ all -> 0x0123 }
            r0.add(r4)     // Catch:{ all -> 0x0123 }
            boolean r0 = r4.await(r5)     // Catch:{ all -> 0x0123 }
            r6.unqueue(r4)     // Catch:{ all -> 0x014e }
            java.util.LinkedList<cz.msebera.android.httpclient.pool.PoolEntryFuture<E>> r10 = r1.pending     // Catch:{ all -> 0x014e }
            r10.remove(r4)     // Catch:{ all -> 0x014e }
            if (r0 != 0) goto L_0x0120
            if (r5 == 0) goto L_0x0120
            long r13 = r5.getTime()     // Catch:{ all -> 0x014e }
            long r16 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x014e }
            int r10 = (r13 > r16 ? 1 : (r13 == r16 ? 0 : -1))
            if (r10 <= 0) goto L_0x011f
            goto L_0x0120
        L_0x011f:
            goto L_0x0146
        L_0x0120:
            r0 = r11
            goto L_0x002d
        L_0x0123:
            r0 = move-exception
            r6.unqueue(r4)     // Catch:{ all -> 0x014e }
            java.util.LinkedList<cz.msebera.android.httpclient.pool.PoolEntryFuture<E>> r13 = r1.pending     // Catch:{ all -> 0x014e }
            r13.remove(r4)     // Catch:{ all -> 0x014e }
            throw r0     // Catch:{ all -> 0x014e }
        L_0x012d:
            r15 = r19
        L_0x012f:
            r11.close()     // Catch:{ all -> 0x014e }
            java.util.LinkedList<E> r0 = r1.available     // Catch:{ all -> 0x014e }
            r0.remove(r11)     // Catch:{ all -> 0x014e }
            r6.free(r11, r10)     // Catch:{ all -> 0x014e }
            r0 = r11
            goto L_0x003d
        L_0x013d:
            r0 = move-exception
            r15 = r19
            goto L_0x0155
        L_0x0141:
            r15 = r19
            r7 = r20
            r11 = r0
        L_0x0146:
            java.util.concurrent.TimeoutException r0 = new java.util.concurrent.TimeoutException     // Catch:{ all -> 0x014e }
            java.lang.String r9 = "Timeout waiting for connection"
            r0.<init>(r9)     // Catch:{ all -> 0x014e }
            throw r0     // Catch:{ all -> 0x014e }
        L_0x014e:
            r0 = move-exception
            goto L_0x0155
        L_0x0150:
            r0 = move-exception
            r15 = r19
            r7 = r20
        L_0x0155:
            java.util.concurrent.locks.Lock r6 = r1.lock
            r6.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.pool.AbstractConnPool.getPoolEntryBlocking(java.lang.Object, java.lang.Object, long, java.util.concurrent.TimeUnit, cz.msebera.android.httpclient.pool.PoolEntryFuture):cz.msebera.android.httpclient.pool.PoolEntry");
    }

    public void release(E entry, boolean reusable) {
        this.lock.lock();
        try {
            if (this.leased.remove(entry)) {
                RouteSpecificPool<T, C, E> pool = getPool(entry.getRoute());
                pool.free(entry, reusable);
                if (!reusable || this.isShutDown) {
                    entry.close();
                } else {
                    this.available.addFirst(entry);
                    onRelease(entry);
                }
                PoolEntryFuture nextPending = pool.nextPending();
                if (nextPending != null) {
                    this.pending.remove(nextPending);
                } else {
                    nextPending = (PoolEntryFuture) this.pending.poll();
                }
                if (nextPending != null) {
                    nextPending.wakeup();
                }
            }
        } finally {
            this.lock.unlock();
        }
    }

    private int getMax(T route) {
        Integer v = (Integer) this.maxPerRoute.get(route);
        if (v != null) {
            return v.intValue();
        }
        return this.defaultMaxPerRoute;
    }

    public void setMaxTotal(int max) {
        Args.notNegative(max, "Max value");
        this.lock.lock();
        try {
            this.maxTotal = max;
        } finally {
            this.lock.unlock();
        }
    }

    public int getMaxTotal() {
        this.lock.lock();
        try {
            return this.maxTotal;
        } finally {
            this.lock.unlock();
        }
    }

    public void setDefaultMaxPerRoute(int max) {
        Args.notNegative(max, "Max per route value");
        this.lock.lock();
        try {
            this.defaultMaxPerRoute = max;
        } finally {
            this.lock.unlock();
        }
    }

    public int getDefaultMaxPerRoute() {
        this.lock.lock();
        try {
            return this.defaultMaxPerRoute;
        } finally {
            this.lock.unlock();
        }
    }

    public void setMaxPerRoute(T route, int max) {
        Args.notNull(route, "Route");
        Args.notNegative(max, "Max per route value");
        this.lock.lock();
        try {
            this.maxPerRoute.put(route, Integer.valueOf(max));
        } finally {
            this.lock.unlock();
        }
    }

    public int getMaxPerRoute(T route) {
        Args.notNull(route, "Route");
        this.lock.lock();
        try {
            return getMax(route);
        } finally {
            this.lock.unlock();
        }
    }

    public PoolStats getTotalStats() {
        this.lock.lock();
        try {
            return new PoolStats(this.leased.size(), this.pending.size(), this.available.size(), this.maxTotal);
        } finally {
            this.lock.unlock();
        }
    }

    public PoolStats getStats(T route) {
        Args.notNull(route, "Route");
        this.lock.lock();
        try {
            RouteSpecificPool<T, C, E> pool = getPool(route);
            return new PoolStats(pool.getLeasedCount(), pool.getPendingCount(), pool.getAvailableCount(), getMax(route));
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void enumAvailable(PoolEntryCallback<T, C> callback) {
        this.lock.lock();
        try {
            Iterator<E> it = this.available.iterator();
            while (it.hasNext()) {
                E entry = (PoolEntry) it.next();
                callback.process(entry);
                if (entry.isClosed()) {
                    getPool(entry.getRoute()).remove(entry);
                    it.remove();
                }
            }
            purgePoolMap();
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void enumLeased(PoolEntryCallback<T, C> callback) {
        this.lock.lock();
        try {
            for (E entry : this.leased) {
                callback.process(entry);
            }
        } finally {
            this.lock.unlock();
        }
    }

    private void purgePoolMap() {
        Iterator<Entry<T, RouteSpecificPool<T, C, E>>> it = this.routeToPool.entrySet().iterator();
        while (it.hasNext()) {
            RouteSpecificPool<T, C, E> pool = (RouteSpecificPool) ((Entry) it.next()).getValue();
            if (pool.getPendingCount() + pool.getAllocatedCount() == 0) {
                it.remove();
            }
        }
    }

    public void closeIdle(long idletime, TimeUnit tunit) {
        Args.notNull(tunit, "Time unit");
        long time = tunit.toMillis(idletime);
        if (time < 0) {
            time = 0;
        }
        final long deadline = System.currentTimeMillis() - time;
        enumAvailable(new PoolEntryCallback<T, C>() {
            public void process(PoolEntry<T, C> entry) {
                if (entry.getUpdated() <= deadline) {
                    entry.close();
                }
            }
        });
    }

    public void closeExpired() {
        final long now = System.currentTimeMillis();
        enumAvailable(new PoolEntryCallback<T, C>() {
            public void process(PoolEntry<T, C> entry) {
                if (entry.isExpired(now)) {
                    entry.close();
                }
            }
        });
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[leased: ");
        buffer.append(this.leased);
        buffer.append("][available: ");
        buffer.append(this.available);
        buffer.append("][pending: ");
        buffer.append(this.pending);
        buffer.append("]");
        return buffer.toString();
    }
}
