package p008cz.msebera.android.httpclient.impl.conn.tsccm;

import java.util.concurrent.TimeUnit;
import p008cz.msebera.android.httpclient.conn.ClientConnectionManager;
import p008cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import p008cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import p008cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException;
import p008cz.msebera.android.httpclient.conn.ManagedClientConnection;
import p008cz.msebera.android.httpclient.conn.params.ConnPerRouteBean;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.impl.conn.DefaultClientConnectionOperator;
import p008cz.msebera.android.httpclient.impl.conn.SchemeRegistryFactory;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.tsccm.ThreadSafeClientConnManager */
public class ThreadSafeClientConnManager implements ClientConnectionManager {
    protected final ClientConnectionOperator connOperator;
    protected final ConnPerRouteBean connPerRoute;
    protected final AbstractConnPool connectionPool;
    public HttpClientAndroidLog log;
    protected final ConnPoolByRoute pool;
    protected final SchemeRegistry schemeRegistry;

    public ThreadSafeClientConnManager(SchemeRegistry schreg) {
        this(schreg, -1, TimeUnit.MILLISECONDS);
    }

    public ThreadSafeClientConnManager() {
        this(SchemeRegistryFactory.createDefault());
    }

    public ThreadSafeClientConnManager(SchemeRegistry schreg, long connTTL, TimeUnit connTTLTimeUnit) {
        this(schreg, connTTL, connTTLTimeUnit, new ConnPerRouteBean());
    }

    public ThreadSafeClientConnManager(SchemeRegistry schreg, long connTTL, TimeUnit connTTLTimeUnit, ConnPerRouteBean connPerRoute2) {
        Args.notNull(schreg, "Scheme registry");
        this.log = new HttpClientAndroidLog(getClass());
        this.schemeRegistry = schreg;
        this.connPerRoute = connPerRoute2;
        this.connOperator = createConnectionOperator(schreg);
        this.pool = createConnectionPool(connTTL, connTTLTimeUnit);
        this.connectionPool = this.pool;
    }

    @Deprecated
    public ThreadSafeClientConnManager(HttpParams params, SchemeRegistry schreg) {
        Args.notNull(schreg, "Scheme registry");
        this.log = new HttpClientAndroidLog(getClass());
        this.schemeRegistry = schreg;
        this.connPerRoute = new ConnPerRouteBean();
        this.connOperator = createConnectionOperator(schreg);
        this.pool = (ConnPoolByRoute) createConnectionPool(params);
        this.connectionPool = this.pool;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public AbstractConnPool createConnectionPool(HttpParams params) {
        return new ConnPoolByRoute(this.connOperator, params);
    }

    /* access modifiers changed from: protected */
    public ConnPoolByRoute createConnectionPool(long connTTL, TimeUnit connTTLTimeUnit) {
        ConnPoolByRoute connPoolByRoute = new ConnPoolByRoute(this.connOperator, this.connPerRoute, 20, connTTL, connTTLTimeUnit);
        return connPoolByRoute;
    }

    /* access modifiers changed from: protected */
    public ClientConnectionOperator createConnectionOperator(SchemeRegistry schreg) {
        return new DefaultClientConnectionOperator(schreg);
    }

    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    public ClientConnectionRequest requestConnection(final HttpRoute route, Object state) {
        final PoolEntryRequest poolRequest = this.pool.requestPoolEntry(route, state);
        return new ClientConnectionRequest() {
            public void abortRequest() {
                poolRequest.abortRequest();
            }

            public ManagedClientConnection getConnection(long timeout, TimeUnit tunit) throws InterruptedException, ConnectionPoolTimeoutException {
                Args.notNull(route, "Route");
                if (ThreadSafeClientConnManager.this.log.isDebugEnabled()) {
                    HttpClientAndroidLog httpClientAndroidLog = ThreadSafeClientConnManager.this.log;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Get connection: ");
                    sb.append(route);
                    sb.append(", timeout = ");
                    sb.append(timeout);
                    httpClientAndroidLog.debug(sb.toString());
                }
                return new BasicPooledConnAdapter(ThreadSafeClientConnManager.this, poolRequest.getPoolEntry(timeout, tunit));
            }
        };
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:34:0x0076=Splitter:B:34:0x0076, B:19:0x0038=Splitter:B:19:0x0038} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void releaseConnection(p008cz.msebera.android.httpclient.conn.ManagedClientConnection r11, long r12, java.util.concurrent.TimeUnit r14) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof p008cz.msebera.android.httpclient.impl.conn.tsccm.BasicPooledConnAdapter
            java.lang.String r1 = "Connection class mismatch, connection not obtained from this manager"
            p008cz.msebera.android.httpclient.util.Args.check(r0, r1)
            r0 = r11
            cz.msebera.android.httpclient.impl.conn.tsccm.BasicPooledConnAdapter r0 = (p008cz.msebera.android.httpclient.impl.conn.tsccm.BasicPooledConnAdapter) r0
            cz.msebera.android.httpclient.impl.conn.AbstractPoolEntry r1 = r0.getPoolEntry()
            if (r1 == 0) goto L_0x001e
            cz.msebera.android.httpclient.conn.ClientConnectionManager r1 = r0.getManager()
            if (r1 != r10) goto L_0x0018
            r1 = 1
            goto L_0x0019
        L_0x0018:
            r1 = 0
        L_0x0019:
            java.lang.String r2 = "Connection not obtained from this manager"
            p008cz.msebera.android.httpclient.util.Asserts.check(r1, r2)
        L_0x001e:
            monitor-enter(r0)
            cz.msebera.android.httpclient.impl.conn.AbstractPoolEntry r1 = r0.getPoolEntry()     // Catch:{ all -> 0x00c8 }
            cz.msebera.android.httpclient.impl.conn.tsccm.BasicPoolEntry r1 = (p008cz.msebera.android.httpclient.impl.conn.tsccm.BasicPoolEntry) r1     // Catch:{ all -> 0x00c8 }
            if (r1 != 0) goto L_0x0029
            monitor-exit(r0)     // Catch:{ all -> 0x00c8 }
            return
        L_0x0029:
            boolean r2 = r0.isOpen()     // Catch:{ IOException -> 0x0066 }
            if (r2 == 0) goto L_0x0038
            boolean r2 = r0.isMarkedReusable()     // Catch:{ IOException -> 0x0066 }
            if (r2 != 0) goto L_0x0038
            r0.shutdown()     // Catch:{ IOException -> 0x0066 }
        L_0x0038:
            boolean r2 = r0.isMarkedReusable()     // Catch:{ all -> 0x00c8 }
            r8 = r2
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r2 = r10.log     // Catch:{ all -> 0x00c8 }
            boolean r2 = r2.isDebugEnabled()     // Catch:{ all -> 0x00c8 }
            if (r2 == 0) goto L_0x0056
            if (r8 == 0) goto L_0x004f
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r2 = r10.log     // Catch:{ all -> 0x00c8 }
            java.lang.String r3 = "Released connection is reusable."
            r2.debug(r3)     // Catch:{ all -> 0x00c8 }
            goto L_0x0056
        L_0x004f:
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r2 = r10.log     // Catch:{ all -> 0x00c8 }
            java.lang.String r3 = "Released connection is not reusable."
            r2.debug(r3)     // Catch:{ all -> 0x00c8 }
        L_0x0056:
            r0.detach()     // Catch:{ all -> 0x00c8 }
            cz.msebera.android.httpclient.impl.conn.tsccm.ConnPoolByRoute r2 = r10.pool     // Catch:{ all -> 0x00c8 }
        L_0x005b:
            r3 = r1
            r4 = r8
            r5 = r12
            r7 = r14
            r2.freeEntry(r3, r4, r5, r7)     // Catch:{ all -> 0x00c8 }
            goto L_0x009a
        L_0x0063:
            r2 = move-exception
            r8 = r2
            goto L_0x009c
        L_0x0066:
            r2 = move-exception
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r3 = r10.log     // Catch:{ all -> 0x0063 }
            boolean r3 = r3.isDebugEnabled()     // Catch:{ all -> 0x0063 }
            if (r3 == 0) goto L_0x0076
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r3 = r10.log     // Catch:{ all -> 0x0063 }
            java.lang.String r4 = "Exception shutting down released connection."
            r3.debug(r4, r2)     // Catch:{ all -> 0x0063 }
        L_0x0076:
            boolean r2 = r0.isMarkedReusable()     // Catch:{ all -> 0x00c8 }
            r8 = r2
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r2 = r10.log     // Catch:{ all -> 0x00c8 }
            boolean r2 = r2.isDebugEnabled()     // Catch:{ all -> 0x00c8 }
            if (r2 == 0) goto L_0x0094
            if (r8 == 0) goto L_0x008d
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r2 = r10.log     // Catch:{ all -> 0x00c8 }
            java.lang.String r3 = "Released connection is reusable."
            r2.debug(r3)     // Catch:{ all -> 0x00c8 }
            goto L_0x0094
        L_0x008d:
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r2 = r10.log     // Catch:{ all -> 0x00c8 }
            java.lang.String r3 = "Released connection is not reusable."
            r2.debug(r3)     // Catch:{ all -> 0x00c8 }
        L_0x0094:
            r0.detach()     // Catch:{ all -> 0x00c8 }
            cz.msebera.android.httpclient.impl.conn.tsccm.ConnPoolByRoute r2 = r10.pool     // Catch:{ all -> 0x00c8 }
            goto L_0x005b
        L_0x009a:
            monitor-exit(r0)     // Catch:{ all -> 0x00c8 }
            return
        L_0x009c:
            boolean r2 = r0.isMarkedReusable()     // Catch:{ all -> 0x00c8 }
            r9 = r2
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r2 = r10.log     // Catch:{ all -> 0x00c8 }
            boolean r2 = r2.isDebugEnabled()     // Catch:{ all -> 0x00c8 }
            if (r2 == 0) goto L_0x00ba
            if (r9 == 0) goto L_0x00b3
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r2 = r10.log     // Catch:{ all -> 0x00c8 }
            java.lang.String r3 = "Released connection is reusable."
            r2.debug(r3)     // Catch:{ all -> 0x00c8 }
            goto L_0x00ba
        L_0x00b3:
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r2 = r10.log     // Catch:{ all -> 0x00c8 }
            java.lang.String r3 = "Released connection is not reusable."
            r2.debug(r3)     // Catch:{ all -> 0x00c8 }
        L_0x00ba:
            r0.detach()     // Catch:{ all -> 0x00c8 }
            cz.msebera.android.httpclient.impl.conn.tsccm.ConnPoolByRoute r2 = r10.pool     // Catch:{ all -> 0x00c8 }
            r3 = r1
            r4 = r9
            r5 = r12
            r7 = r14
            r2.freeEntry(r3, r4, r5, r7)     // Catch:{ all -> 0x00c8 }
            throw r8     // Catch:{ all -> 0x00c8 }
        L_0x00c8:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00c8 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.conn.tsccm.ThreadSafeClientConnManager.releaseConnection(cz.msebera.android.httpclient.conn.ManagedClientConnection, long, java.util.concurrent.TimeUnit):void");
    }

    public void shutdown() {
        this.log.debug("Shutting down");
        this.pool.shutdown();
    }

    public int getConnectionsInPool(HttpRoute route) {
        return this.pool.getConnectionsInPool(route);
    }

    public int getConnectionsInPool() {
        return this.pool.getConnectionsInPool();
    }

    public void closeIdleConnections(long idleTimeout, TimeUnit tunit) {
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Closing connections idle longer than ");
            sb.append(idleTimeout);
            sb.append(" ");
            sb.append(tunit);
            httpClientAndroidLog.debug(sb.toString());
        }
        this.pool.closeIdleConnections(idleTimeout, tunit);
    }

    public void closeExpiredConnections() {
        this.log.debug("Closing expired connections");
        this.pool.closeExpiredConnections();
    }

    public int getMaxTotal() {
        return this.pool.getMaxTotalConnections();
    }

    public void setMaxTotal(int max) {
        this.pool.setMaxTotalConnections(max);
    }

    public int getDefaultMaxPerRoute() {
        return this.connPerRoute.getDefaultMaxPerRoute();
    }

    public void setDefaultMaxPerRoute(int max) {
        this.connPerRoute.setDefaultMaxPerRoute(max);
    }

    public int getMaxForRoute(HttpRoute route) {
        return this.connPerRoute.getMaxForRoute(route);
    }

    public void setMaxForRoute(HttpRoute route, int max) {
        this.connPerRoute.setMaxForRoute(route, max);
    }
}
