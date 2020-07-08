package p008cz.msebera.android.httpclient.impl.conn.tsccm;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import p008cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import p008cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException;
import p008cz.msebera.android.httpclient.conn.OperatedClientConnection;
import p008cz.msebera.android.httpclient.conn.params.ConnManagerParams;
import p008cz.msebera.android.httpclient.conn.params.ConnPerRoute;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.tsccm.ConnPoolByRoute */
public class ConnPoolByRoute extends AbstractConnPool {
    protected final ConnPerRoute connPerRoute;
    private final long connTTL;
    private final TimeUnit connTTLTimeUnit;
    protected final Queue<BasicPoolEntry> freeConnections;
    protected final Set<BasicPoolEntry> leasedConnections;
    public HttpClientAndroidLog log;
    protected volatile int maxTotalConnections;
    protected volatile int numConnections;
    protected final ClientConnectionOperator operator;
    /* access modifiers changed from: private */
    public final Lock poolLock;
    protected final Map<HttpRoute, RouteSpecificPool> routeToPool;
    protected volatile boolean shutdown;
    protected final Queue<WaitingThread> waitingThreads;

    public ConnPoolByRoute(ClientConnectionOperator operator2, ConnPerRoute connPerRoute2, int maxTotalConnections2) {
        this(operator2, connPerRoute2, maxTotalConnections2, -1, TimeUnit.MILLISECONDS);
    }

    public ConnPoolByRoute(ClientConnectionOperator operator2, ConnPerRoute connPerRoute2, int maxTotalConnections2, long connTTL2, TimeUnit connTTLTimeUnit2) {
        this.log = new HttpClientAndroidLog(getClass());
        Args.notNull(operator2, "Connection operator");
        Args.notNull(connPerRoute2, "Connections per route");
        this.poolLock = this.poolLock;
        this.leasedConnections = this.leasedConnections;
        this.operator = operator2;
        this.connPerRoute = connPerRoute2;
        this.maxTotalConnections = maxTotalConnections2;
        this.freeConnections = createFreeConnQueue();
        this.waitingThreads = createWaitingThreadQueue();
        this.routeToPool = createRouteToPoolMap();
        this.connTTL = connTTL2;
        this.connTTLTimeUnit = connTTLTimeUnit2;
    }

    /* access modifiers changed from: protected */
    public Lock getLock() {
        return this.poolLock;
    }

    @Deprecated
    public ConnPoolByRoute(ClientConnectionOperator operator2, HttpParams params) {
        this(operator2, ConnManagerParams.getMaxConnectionsPerRoute(params), ConnManagerParams.getMaxTotalConnections(params));
    }

    /* access modifiers changed from: protected */
    public Queue<BasicPoolEntry> createFreeConnQueue() {
        return new LinkedList();
    }

    /* access modifiers changed from: protected */
    public Queue<WaitingThread> createWaitingThreadQueue() {
        return new LinkedList();
    }

    /* access modifiers changed from: protected */
    public Map<HttpRoute, RouteSpecificPool> createRouteToPoolMap() {
        return new HashMap();
    }

    /* access modifiers changed from: protected */
    public RouteSpecificPool newRouteSpecificPool(HttpRoute route) {
        return new RouteSpecificPool(route, this.connPerRoute);
    }

    /* access modifiers changed from: protected */
    public WaitingThread newWaitingThread(Condition cond, RouteSpecificPool rospl) {
        return new WaitingThread(cond, rospl);
    }

    private void closeConnection(BasicPoolEntry entry) {
        OperatedClientConnection conn = entry.getConnection();
        if (conn != null) {
            try {
                conn.close();
            } catch (IOException ex) {
                this.log.debug("I/O error closing connection", ex);
            }
        }
    }

    /* access modifiers changed from: protected */
    public RouteSpecificPool getRoutePool(HttpRoute route, boolean create) {
        this.poolLock.lock();
        try {
            RouteSpecificPool rospl = (RouteSpecificPool) this.routeToPool.get(route);
            if (rospl == null && create) {
                rospl = newRouteSpecificPool(route);
                this.routeToPool.put(route, rospl);
            }
            return rospl;
        } finally {
            this.poolLock.unlock();
        }
    }

    public int getConnectionsInPool(HttpRoute route) {
        this.poolLock.lock();
        int i = 0;
        try {
            RouteSpecificPool rospl = getRoutePool(route, false);
            if (rospl != null) {
                i = rospl.getEntryCount();
            }
            return i;
        } finally {
            this.poolLock.unlock();
        }
    }

    public int getConnectionsInPool() {
        this.poolLock.lock();
        try {
            return this.numConnections;
        } finally {
            this.poolLock.unlock();
        }
    }

    public PoolEntryRequest requestPoolEntry(final HttpRoute route, final Object state) {
        final WaitingThreadAborter aborter = new WaitingThreadAborter();
        return new PoolEntryRequest() {
            public void abortRequest() {
                ConnPoolByRoute.this.poolLock.lock();
                try {
                    aborter.abort();
                } finally {
                    ConnPoolByRoute.this.poolLock.unlock();
                }
            }

            public BasicPoolEntry getPoolEntry(long timeout, TimeUnit tunit) throws InterruptedException, ConnectionPoolTimeoutException {
                return ConnPoolByRoute.this.getEntryBlocking(route, state, timeout, tunit, aborter);
            }
        };
    }

    /* access modifiers changed from: protected */
    public BasicPoolEntry getEntryBlocking(HttpRoute route, Object state, long timeout, TimeUnit tunit, WaitingThreadAborter aborter) throws ConnectionPoolTimeoutException, InterruptedException {
        Date deadline;
        HttpRoute httpRoute = route;
        Object obj = state;
        long j = timeout;
        if (j > 0) {
            deadline = new Date(System.currentTimeMillis() + tunit.toMillis(j));
        } else {
            TimeUnit timeUnit = tunit;
            deadline = null;
        }
        BasicPoolEntry entry = null;
        this.poolLock.lock();
        try {
            RouteSpecificPool rospl = getRoutePool(httpRoute, true);
            WaitingThread waitingThread = null;
            while (true) {
                if (entry != null) {
                    WaitingThreadAborter waitingThreadAborter = aborter;
                    break;
                }
                boolean z = false;
                Asserts.check(!this.shutdown, "Connection pool shut down");
                String str = " out of ";
                if (this.log.isDebugEnabled()) {
                    HttpClientAndroidLog httpClientAndroidLog = this.log;
                    StringBuilder sb = new StringBuilder();
                    sb.append("[");
                    sb.append(httpRoute);
                    sb.append("] total kept alive: ");
                    sb.append(this.freeConnections.size());
                    sb.append(", total issued: ");
                    sb.append(this.leasedConnections.size());
                    sb.append(", total allocated: ");
                    sb.append(this.numConnections);
                    sb.append(str);
                    sb.append(this.maxTotalConnections);
                    httpClientAndroidLog.debug(sb.toString());
                }
                entry = getFreeEntry(rospl, obj);
                if (entry != null) {
                    WaitingThreadAborter waitingThreadAborter2 = aborter;
                    break;
                }
                if (rospl.getCapacity() > 0) {
                    z = true;
                }
                boolean hasCapacity = z;
                String str2 = "]";
                String str3 = "][";
                if (this.log.isDebugEnabled()) {
                    HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Available capacity: ");
                    sb2.append(rospl.getCapacity());
                    sb2.append(str);
                    sb2.append(rospl.getMaxEntries());
                    sb2.append(" [");
                    sb2.append(httpRoute);
                    sb2.append(str3);
                    sb2.append(obj);
                    sb2.append(str2);
                    httpClientAndroidLog2.debug(sb2.toString());
                }
                if (hasCapacity && this.numConnections < this.maxTotalConnections) {
                    WaitingThreadAborter waitingThreadAborter3 = aborter;
                    entry = createEntry(rospl, this.operator);
                } else if (!hasCapacity || this.freeConnections.isEmpty()) {
                    if (this.log.isDebugEnabled()) {
                        HttpClientAndroidLog httpClientAndroidLog3 = this.log;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Need to wait for connection [");
                        sb3.append(httpRoute);
                        sb3.append(str3);
                        sb3.append(obj);
                        sb3.append(str2);
                        httpClientAndroidLog3.debug(sb3.toString());
                    }
                    if (waitingThread == null) {
                        waitingThread = newWaitingThread(this.poolLock.newCondition(), rospl);
                        try {
                            aborter.setWaitingThread(waitingThread);
                        } catch (Throwable th) {
                            th = th;
                        }
                    } else {
                        WaitingThreadAborter waitingThreadAborter4 = aborter;
                    }
                    rospl.queueThread(waitingThread);
                    this.waitingThreads.add(waitingThread);
                    boolean success = waitingThread.await(deadline);
                    rospl.removeThread(waitingThread);
                    this.waitingThreads.remove(waitingThread);
                    if (!success && deadline != null) {
                        if (deadline.getTime() <= System.currentTimeMillis()) {
                            throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
                        }
                    }
                } else {
                    deleteLeastUsedEntry();
                    RouteSpecificPool rospl2 = getRoutePool(httpRoute, true);
                    rospl = rospl2;
                    entry = createEntry(rospl2, this.operator);
                    WaitingThreadAborter waitingThreadAborter5 = aborter;
                }
                long j2 = timeout;
            }
            this.poolLock.unlock();
            return entry;
        } catch (Throwable th2) {
            th = th2;
            WaitingThreadAborter waitingThreadAborter6 = aborter;
            this.poolLock.unlock();
            throw th;
        }
    }

    public void freeEntry(BasicPoolEntry entry, boolean reusable, long validDuration, TimeUnit timeUnit) {
        String s;
        HttpRoute route = entry.getPlannedRoute();
        String str = "][";
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Releasing connection [");
            sb.append(route);
            sb.append(str);
            sb.append(entry.getState());
            sb.append("]");
            httpClientAndroidLog.debug(sb.toString());
        }
        this.poolLock.lock();
        try {
            if (this.shutdown) {
                closeConnection(entry);
                return;
            }
            this.leasedConnections.remove(entry);
            RouteSpecificPool rospl = getRoutePool(route, true);
            if (!reusable || rospl.getCapacity() < 0) {
                closeConnection(entry);
                rospl.dropEntry();
                this.numConnections--;
            } else {
                if (this.log.isDebugEnabled()) {
                    if (validDuration > 0) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("for ");
                        sb2.append(validDuration);
                        sb2.append(" ");
                        sb2.append(timeUnit);
                        s = sb2.toString();
                    } else {
                        s = "indefinitely";
                    }
                    HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Pooling connection [");
                    sb3.append(route);
                    sb3.append(str);
                    sb3.append(entry.getState());
                    sb3.append("]; keep alive ");
                    sb3.append(s);
                    httpClientAndroidLog2.debug(sb3.toString());
                }
                rospl.freeEntry(entry);
                entry.updateExpiry(validDuration, timeUnit);
                this.freeConnections.add(entry);
            }
            notifyWaitingThread(rospl);
            this.poolLock.unlock();
        } finally {
            this.poolLock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public BasicPoolEntry getFreeEntry(RouteSpecificPool rospl, Object state) {
        BasicPoolEntry entry = null;
        this.poolLock.lock();
        boolean done = false;
        while (!done) {
            try {
                entry = rospl.allocEntry(state);
                String str = "]";
                String str2 = "][";
                if (entry != null) {
                    if (this.log.isDebugEnabled()) {
                        HttpClientAndroidLog httpClientAndroidLog = this.log;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Getting free connection [");
                        sb.append(rospl.getRoute());
                        sb.append(str2);
                        sb.append(state);
                        sb.append(str);
                        httpClientAndroidLog.debug(sb.toString());
                    }
                    this.freeConnections.remove(entry);
                    if (entry.isExpired(System.currentTimeMillis())) {
                        if (this.log.isDebugEnabled()) {
                            HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("Closing expired free connection [");
                            sb2.append(rospl.getRoute());
                            sb2.append(str2);
                            sb2.append(state);
                            sb2.append(str);
                            httpClientAndroidLog2.debug(sb2.toString());
                        }
                        closeConnection(entry);
                        rospl.dropEntry();
                        this.numConnections--;
                    } else {
                        this.leasedConnections.add(entry);
                        done = true;
                    }
                } else {
                    done = true;
                    if (this.log.isDebugEnabled()) {
                        HttpClientAndroidLog httpClientAndroidLog3 = this.log;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("No free connections [");
                        sb3.append(rospl.getRoute());
                        sb3.append(str2);
                        sb3.append(state);
                        sb3.append(str);
                        httpClientAndroidLog3.debug(sb3.toString());
                    }
                }
            } catch (Throwable th) {
                this.poolLock.unlock();
                throw th;
            }
        }
        this.poolLock.unlock();
        return entry;
    }

    /* access modifiers changed from: protected */
    public BasicPoolEntry createEntry(RouteSpecificPool rospl, ClientConnectionOperator op) {
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Creating new connection [");
            sb.append(rospl.getRoute());
            sb.append("]");
            httpClientAndroidLog.debug(sb.toString());
        }
        BasicPoolEntry basicPoolEntry = new BasicPoolEntry(op, rospl.getRoute(), this.connTTL, this.connTTLTimeUnit);
        this.poolLock.lock();
        try {
            rospl.createdEntry(basicPoolEntry);
            this.numConnections++;
            this.leasedConnections.add(basicPoolEntry);
            return basicPoolEntry;
        } finally {
            this.poolLock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void deleteEntry(BasicPoolEntry entry) {
        HttpRoute route = entry.getPlannedRoute();
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Deleting connection [");
            sb.append(route);
            sb.append("][");
            sb.append(entry.getState());
            sb.append("]");
            httpClientAndroidLog.debug(sb.toString());
        }
        this.poolLock.lock();
        try {
            closeConnection(entry);
            RouteSpecificPool rospl = getRoutePool(route, true);
            rospl.deleteEntry(entry);
            this.numConnections--;
            if (rospl.isUnused()) {
                this.routeToPool.remove(route);
            }
        } finally {
            this.poolLock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void deleteLeastUsedEntry() {
        this.poolLock.lock();
        try {
            BasicPoolEntry entry = (BasicPoolEntry) this.freeConnections.remove();
            if (entry != null) {
                deleteEntry(entry);
            } else if (this.log.isDebugEnabled()) {
                this.log.debug("No free connection to delete");
            }
        } finally {
            this.poolLock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void handleLostEntry(HttpRoute route) {
        this.poolLock.lock();
        try {
            RouteSpecificPool rospl = getRoutePool(route, true);
            rospl.dropEntry();
            if (rospl.isUnused()) {
                this.routeToPool.remove(route);
            }
            this.numConnections--;
            notifyWaitingThread(rospl);
        } finally {
            this.poolLock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006d A[Catch:{ all -> 0x0077 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifyWaitingThread(p008cz.msebera.android.httpclient.impl.conn.tsccm.RouteSpecificPool r5) {
        /*
            r4 = this;
            r0 = 0
            java.util.concurrent.locks.Lock r1 = r4.poolLock
            r1.lock()
            if (r5 == 0) goto L_0x003b
            boolean r1 = r5.hasThread()     // Catch:{ all -> 0x0077 }
            if (r1 == 0) goto L_0x003b
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r1 = r4.log     // Catch:{ all -> 0x0077 }
            boolean r1 = r1.isDebugEnabled()     // Catch:{ all -> 0x0077 }
            if (r1 == 0) goto L_0x0035
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r1 = r4.log     // Catch:{ all -> 0x0077 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0077 }
            r2.<init>()     // Catch:{ all -> 0x0077 }
            java.lang.String r3 = "Notifying thread waiting on pool ["
            r2.append(r3)     // Catch:{ all -> 0x0077 }
            cz.msebera.android.httpclient.conn.routing.HttpRoute r3 = r5.getRoute()     // Catch:{ all -> 0x0077 }
            r2.append(r3)     // Catch:{ all -> 0x0077 }
            java.lang.String r3 = "]"
            r2.append(r3)     // Catch:{ all -> 0x0077 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0077 }
            r1.debug(r2)     // Catch:{ all -> 0x0077 }
        L_0x0035:
            cz.msebera.android.httpclient.impl.conn.tsccm.WaitingThread r1 = r5.nextThread()     // Catch:{ all -> 0x0077 }
            r0 = r1
            goto L_0x006b
        L_0x003b:
            java.util.Queue<cz.msebera.android.httpclient.impl.conn.tsccm.WaitingThread> r1 = r4.waitingThreads     // Catch:{ all -> 0x0077 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0077 }
            if (r1 != 0) goto L_0x005c
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r1 = r4.log     // Catch:{ all -> 0x0077 }
            boolean r1 = r1.isDebugEnabled()     // Catch:{ all -> 0x0077 }
            if (r1 == 0) goto L_0x0052
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r1 = r4.log     // Catch:{ all -> 0x0077 }
            java.lang.String r2 = "Notifying thread waiting on any pool"
            r1.debug(r2)     // Catch:{ all -> 0x0077 }
        L_0x0052:
            java.util.Queue<cz.msebera.android.httpclient.impl.conn.tsccm.WaitingThread> r1 = r4.waitingThreads     // Catch:{ all -> 0x0077 }
            java.lang.Object r1 = r1.remove()     // Catch:{ all -> 0x0077 }
            cz.msebera.android.httpclient.impl.conn.tsccm.WaitingThread r1 = (p008cz.msebera.android.httpclient.impl.conn.tsccm.WaitingThread) r1     // Catch:{ all -> 0x0077 }
            r0 = r1
            goto L_0x006b
        L_0x005c:
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r1 = r4.log     // Catch:{ all -> 0x0077 }
            boolean r1 = r1.isDebugEnabled()     // Catch:{ all -> 0x0077 }
            if (r1 == 0) goto L_0x006b
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r1 = r4.log     // Catch:{ all -> 0x0077 }
            java.lang.String r2 = "Notifying no-one, there are no waiting threads"
            r1.debug(r2)     // Catch:{ all -> 0x0077 }
        L_0x006b:
            if (r0 == 0) goto L_0x0070
            r0.wakeup()     // Catch:{ all -> 0x0077 }
        L_0x0070:
            java.util.concurrent.locks.Lock r1 = r4.poolLock
            r1.unlock()
            return
        L_0x0077:
            r1 = move-exception
            java.util.concurrent.locks.Lock r2 = r4.poolLock
            r2.unlock()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.conn.tsccm.ConnPoolByRoute.notifyWaitingThread(cz.msebera.android.httpclient.impl.conn.tsccm.RouteSpecificPool):void");
    }

    public void deleteClosedConnections() {
        this.poolLock.lock();
        try {
            Iterator<BasicPoolEntry> iter = this.freeConnections.iterator();
            while (iter.hasNext()) {
                BasicPoolEntry entry = (BasicPoolEntry) iter.next();
                if (!entry.getConnection().isOpen()) {
                    iter.remove();
                    deleteEntry(entry);
                }
            }
        } finally {
            this.poolLock.unlock();
        }
    }

    public void closeIdleConnections(long idletime, TimeUnit tunit) {
        Args.notNull(tunit, "Time unit");
        long t = 0;
        if (idletime > 0) {
            t = idletime;
        }
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Closing connections idle longer than ");
            sb.append(t);
            sb.append(" ");
            sb.append(tunit);
            httpClientAndroidLog.debug(sb.toString());
        }
        long deadline = System.currentTimeMillis() - tunit.toMillis(t);
        this.poolLock.lock();
        try {
            Iterator<BasicPoolEntry> iter = this.freeConnections.iterator();
            while (iter.hasNext()) {
                BasicPoolEntry entry = (BasicPoolEntry) iter.next();
                if (entry.getUpdated() <= deadline) {
                    if (this.log.isDebugEnabled()) {
                        HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Closing connection last used @ ");
                        sb2.append(new Date(entry.getUpdated()));
                        httpClientAndroidLog2.debug(sb2.toString());
                    }
                    iter.remove();
                    deleteEntry(entry);
                }
            }
        } finally {
            this.poolLock.unlock();
        }
    }

    public void closeExpiredConnections() {
        this.log.debug("Closing expired connections");
        long now = System.currentTimeMillis();
        this.poolLock.lock();
        try {
            Iterator<BasicPoolEntry> iter = this.freeConnections.iterator();
            while (iter.hasNext()) {
                BasicPoolEntry entry = (BasicPoolEntry) iter.next();
                if (entry.isExpired(now)) {
                    if (this.log.isDebugEnabled()) {
                        HttpClientAndroidLog httpClientAndroidLog = this.log;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Closing connection expired @ ");
                        sb.append(new Date(entry.getExpiry()));
                        httpClientAndroidLog.debug(sb.toString());
                    }
                    iter.remove();
                    deleteEntry(entry);
                }
            }
        } finally {
            this.poolLock.unlock();
        }
    }

    public void shutdown() {
        this.poolLock.lock();
        try {
            if (!this.shutdown) {
                this.shutdown = true;
                Iterator<BasicPoolEntry> iter1 = this.leasedConnections.iterator();
                while (iter1.hasNext()) {
                    BasicPoolEntry entry = (BasicPoolEntry) iter1.next();
                    iter1.remove();
                    closeConnection(entry);
                }
                Iterator<BasicPoolEntry> iter2 = this.freeConnections.iterator();
                while (iter2.hasNext()) {
                    BasicPoolEntry entry2 = (BasicPoolEntry) iter2.next();
                    iter2.remove();
                    if (this.log.isDebugEnabled()) {
                        HttpClientAndroidLog httpClientAndroidLog = this.log;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Closing connection [");
                        sb.append(entry2.getPlannedRoute());
                        sb.append("][");
                        sb.append(entry2.getState());
                        sb.append("]");
                        httpClientAndroidLog.debug(sb.toString());
                    }
                    closeConnection(entry2);
                }
                Iterator<WaitingThread> iwth = this.waitingThreads.iterator();
                while (iwth.hasNext()) {
                    WaitingThread waiter = (WaitingThread) iwth.next();
                    iwth.remove();
                    waiter.wakeup();
                }
                this.routeToPool.clear();
                this.poolLock.unlock();
            }
        } finally {
            this.poolLock.unlock();
        }
    }

    public void setMaxTotalConnections(int max) {
        this.poolLock.lock();
        try {
            this.maxTotalConnections = max;
        } finally {
            this.poolLock.unlock();
        }
    }

    public int getMaxTotalConnections() {
        return this.maxTotalConnections;
    }
}
