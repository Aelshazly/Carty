package p008cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import p008cz.msebera.android.httpclient.conn.ClientConnectionManager;
import p008cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import p008cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import p008cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException;
import p008cz.msebera.android.httpclient.conn.DnsResolver;
import p008cz.msebera.android.httpclient.conn.ManagedClientConnection;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.pool.ConnPoolControl;
import p008cz.msebera.android.httpclient.pool.PoolStats;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.PoolingClientConnectionManager */
public class PoolingClientConnectionManager implements ClientConnectionManager, ConnPoolControl<HttpRoute> {
    private final DnsResolver dnsResolver;
    public HttpClientAndroidLog log;
    private final ClientConnectionOperator operator;
    private final HttpConnPool pool;
    private final SchemeRegistry schemeRegistry;

    public PoolingClientConnectionManager(SchemeRegistry schreg) {
        this(schreg, -1, TimeUnit.MILLISECONDS);
    }

    public PoolingClientConnectionManager(SchemeRegistry schreg, DnsResolver dnsResolver2) {
        this(schreg, -1, TimeUnit.MILLISECONDS, dnsResolver2);
    }

    public PoolingClientConnectionManager() {
        this(SchemeRegistryFactory.createDefault());
    }

    public PoolingClientConnectionManager(SchemeRegistry schemeRegistry2, long timeToLive, TimeUnit tunit) {
        this(schemeRegistry2, timeToLive, tunit, new SystemDefaultDnsResolver());
    }

    public PoolingClientConnectionManager(SchemeRegistry schemeRegistry2, long timeToLive, TimeUnit tunit, DnsResolver dnsResolver2) {
        this.log = new HttpClientAndroidLog(getClass());
        Args.notNull(schemeRegistry2, "Scheme registry");
        Args.notNull(dnsResolver2, "DNS resolver");
        this.schemeRegistry = schemeRegistry2;
        this.dnsResolver = dnsResolver2;
        this.operator = createConnectionOperator(schemeRegistry2);
        HttpConnPool httpConnPool = new HttpConnPool(this.log, this.operator, 2, 20, timeToLive, tunit);
        this.pool = httpConnPool;
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
    public ClientConnectionOperator createConnectionOperator(SchemeRegistry schreg) {
        return new DefaultClientConnectionOperator(schreg, this.dnsResolver);
    }

    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    private String format(HttpRoute route, Object state) {
        StringBuilder buf = new StringBuilder();
        buf.append("[route: ");
        buf.append(route);
        String str = "]";
        buf.append(str);
        if (state != null) {
            buf.append("[state: ");
            buf.append(state);
            buf.append(str);
        }
        return buf.toString();
    }

    private String formatStats(HttpRoute route) {
        StringBuilder buf = new StringBuilder();
        PoolStats totals = this.pool.getTotalStats();
        PoolStats stats = this.pool.getStats(route);
        buf.append("[total kept alive: ");
        buf.append(totals.getAvailable());
        String str = "; ";
        buf.append(str);
        buf.append("route allocated: ");
        buf.append(stats.getLeased() + stats.getAvailable());
        String str2 = " of ";
        buf.append(str2);
        buf.append(stats.getMax());
        buf.append(str);
        buf.append("total allocated: ");
        buf.append(totals.getLeased() + totals.getAvailable());
        buf.append(str2);
        buf.append(totals.getMax());
        buf.append("]");
        return buf.toString();
    }

    private String format(HttpPoolEntry entry) {
        StringBuilder buf = new StringBuilder();
        buf.append("[id: ");
        buf.append(entry.getId());
        String str = "]";
        buf.append(str);
        buf.append("[route: ");
        buf.append(entry.getRoute());
        buf.append(str);
        Object state = entry.getState();
        if (state != null) {
            buf.append("[state: ");
            buf.append(state);
            buf.append(str);
        }
        return buf.toString();
    }

    public ClientConnectionRequest requestConnection(HttpRoute route, Object state) {
        Args.notNull(route, "HTTP route");
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Connection request: ");
            sb.append(format(route, state));
            sb.append(formatStats(route));
            httpClientAndroidLog.debug(sb.toString());
        }
        final Future<HttpPoolEntry> future = this.pool.lease(route, state);
        return new ClientConnectionRequest() {
            public void abortRequest() {
                future.cancel(true);
            }

            public ManagedClientConnection getConnection(long timeout, TimeUnit tunit) throws InterruptedException, ConnectionPoolTimeoutException {
                return PoolingClientConnectionManager.this.leaseConnection(future, timeout, tunit);
            }
        };
    }

    /* access modifiers changed from: 0000 */
    public ManagedClientConnection leaseConnection(Future<HttpPoolEntry> future, long timeout, TimeUnit tunit) throws InterruptedException, ConnectionPoolTimeoutException {
        try {
            HttpPoolEntry entry = (HttpPoolEntry) future.get(timeout, tunit);
            if (entry == null || future.isCancelled()) {
                throw new InterruptedException();
            }
            Asserts.check(entry.getConnection() != null, "Pool entry with no connection");
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog httpClientAndroidLog = this.log;
                StringBuilder sb = new StringBuilder();
                sb.append("Connection leased: ");
                sb.append(format(entry));
                sb.append(formatStats((HttpRoute) entry.getRoute()));
                httpClientAndroidLog.debug(sb.toString());
            }
            return new ManagedClientConnectionImpl(this, this.operator, entry);
        } catch (ExecutionException ex) {
            Throwable cause = ex.getCause();
            if (cause == 0) {
                cause = ex;
            }
            this.log.error("Unexpected exception leasing connection from pool", cause);
            throw new InterruptedException();
        } catch (TimeoutException e) {
            throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d6, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void releaseConnection(p008cz.msebera.android.httpclient.conn.ManagedClientConnection r7, long r8, java.util.concurrent.TimeUnit r10) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof p008cz.msebera.android.httpclient.impl.conn.ManagedClientConnectionImpl
            java.lang.String r1 = "Connection class mismatch, connection not obtained from this manager"
            p008cz.msebera.android.httpclient.util.Args.check(r0, r1)
            r0 = r7
            cz.msebera.android.httpclient.impl.conn.ManagedClientConnectionImpl r0 = (p008cz.msebera.android.httpclient.impl.conn.ManagedClientConnectionImpl) r0
            cz.msebera.android.httpclient.conn.ClientConnectionManager r1 = r0.getManager()
            if (r1 != r6) goto L_0x0012
            r1 = 1
            goto L_0x0013
        L_0x0012:
            r1 = 0
        L_0x0013:
            java.lang.String r2 = "Connection not obtained from this manager"
            p008cz.msebera.android.httpclient.util.Asserts.check(r1, r2)
            monitor-enter(r0)
            cz.msebera.android.httpclient.impl.conn.HttpPoolEntry r1 = r0.detach()     // Catch:{ all -> 0x00e2 }
            if (r1 != 0) goto L_0x0021
            monitor-exit(r0)     // Catch:{ all -> 0x00e2 }
            return
        L_0x0021:
            boolean r2 = r0.isOpen()     // Catch:{ all -> 0x00d7 }
            if (r2 == 0) goto L_0x0041
            boolean r2 = r0.isMarkedReusable()     // Catch:{ all -> 0x00d7 }
            if (r2 != 0) goto L_0x0041
            r0.shutdown()     // Catch:{ IOException -> 0x0031 }
            goto L_0x0041
        L_0x0031:
            r2 = move-exception
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r3 = r6.log     // Catch:{ all -> 0x00d7 }
            boolean r3 = r3.isDebugEnabled()     // Catch:{ all -> 0x00d7 }
            if (r3 == 0) goto L_0x0041
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r3 = r6.log     // Catch:{ all -> 0x00d7 }
            java.lang.String r4 = "I/O exception shutting down released connection"
            r3.debug(r4, r2)     // Catch:{ all -> 0x00d7 }
        L_0x0041:
            boolean r2 = r0.isMarkedReusable()     // Catch:{ all -> 0x00d7 }
            if (r2 == 0) goto L_0x009c
            if (r10 == 0) goto L_0x004b
            r2 = r10
            goto L_0x004d
        L_0x004b:
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x00d7 }
        L_0x004d:
            r1.updateExpiry(r8, r2)     // Catch:{ all -> 0x00d7 }
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r2 = r6.log     // Catch:{ all -> 0x00d7 }
            boolean r2 = r2.isDebugEnabled()     // Catch:{ all -> 0x00d7 }
            if (r2 == 0) goto L_0x009c
            r2 = 0
            int r4 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x0078
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d7 }
            r2.<init>()     // Catch:{ all -> 0x00d7 }
            java.lang.String r3 = "for "
            r2.append(r3)     // Catch:{ all -> 0x00d7 }
            r2.append(r8)     // Catch:{ all -> 0x00d7 }
            java.lang.String r3 = " "
            r2.append(r3)     // Catch:{ all -> 0x00d7 }
            r2.append(r10)     // Catch:{ all -> 0x00d7 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00d7 }
            goto L_0x007a
        L_0x0078:
            java.lang.String r2 = "indefinitely"
        L_0x007a:
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r3 = r6.log     // Catch:{ all -> 0x00d7 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d7 }
            r4.<init>()     // Catch:{ all -> 0x00d7 }
            java.lang.String r5 = "Connection "
            r4.append(r5)     // Catch:{ all -> 0x00d7 }
            java.lang.String r5 = r6.format(r1)     // Catch:{ all -> 0x00d7 }
            r4.append(r5)     // Catch:{ all -> 0x00d7 }
            java.lang.String r5 = " can be kept alive "
            r4.append(r5)     // Catch:{ all -> 0x00d7 }
            r4.append(r2)     // Catch:{ all -> 0x00d7 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00d7 }
            r3.debug(r4)     // Catch:{ all -> 0x00d7 }
        L_0x009c:
            cz.msebera.android.httpclient.impl.conn.HttpConnPool r2 = r6.pool     // Catch:{ all -> 0x00e2 }
            boolean r3 = r0.isMarkedReusable()     // Catch:{ all -> 0x00e2 }
            r2.release(r1, r3)     // Catch:{ all -> 0x00e2 }
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r2 = r6.log     // Catch:{ all -> 0x00e2 }
            boolean r2 = r2.isDebugEnabled()     // Catch:{ all -> 0x00e2 }
            if (r2 == 0) goto L_0x00d5
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r2 = r6.log     // Catch:{ all -> 0x00e2 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e2 }
            r3.<init>()     // Catch:{ all -> 0x00e2 }
            java.lang.String r4 = "Connection released: "
            r3.append(r4)     // Catch:{ all -> 0x00e2 }
            java.lang.String r4 = r6.format(r1)     // Catch:{ all -> 0x00e2 }
            r3.append(r4)     // Catch:{ all -> 0x00e2 }
            java.lang.Object r4 = r1.getRoute()     // Catch:{ all -> 0x00e2 }
            cz.msebera.android.httpclient.conn.routing.HttpRoute r4 = (p008cz.msebera.android.httpclient.conn.routing.HttpRoute) r4     // Catch:{ all -> 0x00e2 }
            java.lang.String r4 = r6.formatStats(r4)     // Catch:{ all -> 0x00e2 }
            r3.append(r4)     // Catch:{ all -> 0x00e2 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00e2 }
            r2.debug(r3)     // Catch:{ all -> 0x00e2 }
        L_0x00d5:
            monitor-exit(r0)     // Catch:{ all -> 0x00e2 }
            return
        L_0x00d7:
            r2 = move-exception
            cz.msebera.android.httpclient.impl.conn.HttpConnPool r3 = r6.pool     // Catch:{ all -> 0x00e2 }
            boolean r4 = r0.isMarkedReusable()     // Catch:{ all -> 0x00e2 }
            r3.release(r1, r4)     // Catch:{ all -> 0x00e2 }
            throw r2     // Catch:{ all -> 0x00e2 }
        L_0x00e2:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00e2 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.conn.PoolingClientConnectionManager.releaseConnection(cz.msebera.android.httpclient.conn.ManagedClientConnection, long, java.util.concurrent.TimeUnit):void");
    }

    public void shutdown() {
        this.log.debug("Connection manager is shutting down");
        try {
            this.pool.shutdown();
        } catch (IOException ex) {
            this.log.debug("I/O exception shutting down connection manager", ex);
        }
        this.log.debug("Connection manager shut down");
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
        this.pool.closeIdle(idleTimeout, tunit);
    }

    public void closeExpiredConnections() {
        this.log.debug("Closing expired connections");
        this.pool.closeExpired();
    }

    public int getMaxTotal() {
        return this.pool.getMaxTotal();
    }

    public void setMaxTotal(int max) {
        this.pool.setMaxTotal(max);
    }

    public int getDefaultMaxPerRoute() {
        return this.pool.getDefaultMaxPerRoute();
    }

    public void setDefaultMaxPerRoute(int max) {
        this.pool.setDefaultMaxPerRoute(max);
    }

    public int getMaxPerRoute(HttpRoute route) {
        return this.pool.getMaxPerRoute(route);
    }

    public void setMaxPerRoute(HttpRoute route, int max) {
        this.pool.setMaxPerRoute(route, max);
    }

    public PoolStats getTotalStats() {
        return this.pool.getTotalStats();
    }

    public PoolStats getStats(HttpRoute route) {
        return this.pool.getStats(route);
    }
}
