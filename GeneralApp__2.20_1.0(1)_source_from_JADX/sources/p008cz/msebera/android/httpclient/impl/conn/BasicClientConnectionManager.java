package p008cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import p008cz.msebera.android.httpclient.HttpClientConnection;
import p008cz.msebera.android.httpclient.conn.ClientConnectionManager;
import p008cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import p008cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import p008cz.msebera.android.httpclient.conn.ManagedClientConnection;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.BasicClientConnectionManager */
public class BasicClientConnectionManager implements ClientConnectionManager {
    private static final AtomicLong COUNTER = new AtomicLong();
    public static final String MISUSE_MESSAGE = "Invalid use of BasicClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.";
    private ManagedClientConnectionImpl conn;
    private final ClientConnectionOperator connOperator;
    public HttpClientAndroidLog log;
    private HttpPoolEntry poolEntry;
    private final SchemeRegistry schemeRegistry;
    private volatile boolean shutdown;

    public BasicClientConnectionManager(SchemeRegistry schreg) {
        this.log = new HttpClientAndroidLog(getClass());
        Args.notNull(schreg, "Scheme registry");
        this.schemeRegistry = schreg;
        this.connOperator = createConnectionOperator(schreg);
    }

    public BasicClientConnectionManager() {
        this(SchemeRegistryFactory.createDefault());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }

    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    /* access modifiers changed from: protected */
    public ClientConnectionOperator createConnectionOperator(SchemeRegistry schreg) {
        return new DefaultClientConnectionOperator(schreg);
    }

    public final ClientConnectionRequest requestConnection(final HttpRoute route, final Object state) {
        return new ClientConnectionRequest() {
            public void abortRequest() {
            }

            public ManagedClientConnection getConnection(long timeout, TimeUnit tunit) {
                return BasicClientConnectionManager.this.getConnection(route, state);
            }
        };
    }

    private void assertNotShutdown() {
        Asserts.check(!this.shutdown, "Connection manager has been shut down");
    }

    /* access modifiers changed from: 0000 */
    public ManagedClientConnection getConnection(HttpRoute route, Object state) {
        ManagedClientConnectionImpl managedClientConnectionImpl;
        Args.notNull(route, "Route");
        synchronized (this) {
            assertNotShutdown();
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog httpClientAndroidLog = this.log;
                StringBuilder sb = new StringBuilder();
                sb.append("Get connection for route ");
                sb.append(route);
                httpClientAndroidLog.debug(sb.toString());
            }
            Asserts.check(this.conn == null, MISUSE_MESSAGE);
            if (this.poolEntry != null && !this.poolEntry.getPlannedRoute().equals(route)) {
                this.poolEntry.close();
                this.poolEntry = null;
            }
            if (this.poolEntry == null) {
                HttpPoolEntry httpPoolEntry = new HttpPoolEntry(this.log, Long.toString(COUNTER.getAndIncrement()), route, this.connOperator.createConnection(), 0, TimeUnit.MILLISECONDS);
                this.poolEntry = httpPoolEntry;
            }
            if (this.poolEntry.isExpired(System.currentTimeMillis())) {
                this.poolEntry.close();
                this.poolEntry.getTracker().reset();
            }
            this.conn = new ManagedClientConnectionImpl(this, this.connOperator, this.poolEntry);
            managedClientConnectionImpl = this.conn;
        }
        return managedClientConnectionImpl;
    }

    private void shutdownConnection(HttpClientConnection conn2) {
        try {
            conn2.shutdown();
        } catch (IOException iox) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("I/O exception shutting down connection", iox);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00bc, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void releaseConnection(p008cz.msebera.android.httpclient.conn.ManagedClientConnection r8, long r9, java.util.concurrent.TimeUnit r11) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof p008cz.msebera.android.httpclient.impl.conn.ManagedClientConnectionImpl
            java.lang.String r1 = "Connection class mismatch, connection not obtained from this manager"
            p008cz.msebera.android.httpclient.util.Args.check(r0, r1)
            r0 = r8
            cz.msebera.android.httpclient.impl.conn.ManagedClientConnectionImpl r0 = (p008cz.msebera.android.httpclient.impl.conn.ManagedClientConnectionImpl) r0
            monitor-enter(r0)
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r1 = r7.log     // Catch:{ all -> 0x00d2 }
            boolean r1 = r1.isDebugEnabled()     // Catch:{ all -> 0x00d2 }
            if (r1 == 0) goto L_0x0029
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r1 = r7.log     // Catch:{ all -> 0x00d2 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d2 }
            r2.<init>()     // Catch:{ all -> 0x00d2 }
            java.lang.String r3 = "Releasing connection "
            r2.append(r3)     // Catch:{ all -> 0x00d2 }
            r2.append(r8)     // Catch:{ all -> 0x00d2 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00d2 }
            r1.debug(r2)     // Catch:{ all -> 0x00d2 }
        L_0x0029:
            cz.msebera.android.httpclient.impl.conn.HttpPoolEntry r1 = r0.getPoolEntry()     // Catch:{ all -> 0x00d2 }
            if (r1 != 0) goto L_0x0031
            monitor-exit(r0)     // Catch:{ all -> 0x00d2 }
            return
        L_0x0031:
            cz.msebera.android.httpclient.conn.ClientConnectionManager r1 = r0.getManager()     // Catch:{ all -> 0x00d2 }
            if (r1 != r7) goto L_0x0039
            r2 = 1
            goto L_0x003a
        L_0x0039:
            r2 = 0
        L_0x003a:
            java.lang.String r3 = "Connection not obtained from this manager"
            p008cz.msebera.android.httpclient.util.Asserts.check(r2, r3)     // Catch:{ all -> 0x00d2 }
            monitor-enter(r7)     // Catch:{ all -> 0x00d2 }
            boolean r2 = r7.shutdown     // Catch:{ all -> 0x00cf }
            if (r2 == 0) goto L_0x004a
            r7.shutdownConnection(r0)     // Catch:{ all -> 0x00cf }
            monitor-exit(r7)     // Catch:{ all -> 0x00cf }
            monitor-exit(r0)     // Catch:{ all -> 0x00d2 }
            return
        L_0x004a:
            r2 = 0
            boolean r3 = r0.isOpen()     // Catch:{ all -> 0x00bd }
            if (r3 == 0) goto L_0x005a
            boolean r3 = r0.isMarkedReusable()     // Catch:{ all -> 0x00bd }
            if (r3 != 0) goto L_0x005a
            r7.shutdownConnection(r0)     // Catch:{ all -> 0x00bd }
        L_0x005a:
            boolean r3 = r0.isMarkedReusable()     // Catch:{ all -> 0x00bd }
            if (r3 == 0) goto L_0x00ab
            cz.msebera.android.httpclient.impl.conn.HttpPoolEntry r3 = r7.poolEntry     // Catch:{ all -> 0x00bd }
            if (r11 == 0) goto L_0x0066
            r4 = r11
            goto L_0x0068
        L_0x0066:
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x00bd }
        L_0x0068:
            r3.updateExpiry(r9, r4)     // Catch:{ all -> 0x00bd }
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r3 = r7.log     // Catch:{ all -> 0x00bd }
            boolean r3 = r3.isDebugEnabled()     // Catch:{ all -> 0x00bd }
            if (r3 == 0) goto L_0x00ab
            r3 = 0
            int r5 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0093
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bd }
            r3.<init>()     // Catch:{ all -> 0x00bd }
            java.lang.String r4 = "for "
            r3.append(r4)     // Catch:{ all -> 0x00bd }
            r3.append(r9)     // Catch:{ all -> 0x00bd }
            java.lang.String r4 = " "
            r3.append(r4)     // Catch:{ all -> 0x00bd }
            r3.append(r11)     // Catch:{ all -> 0x00bd }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00bd }
            goto L_0x0095
        L_0x0093:
            java.lang.String r3 = "indefinitely"
        L_0x0095:
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r4 = r7.log     // Catch:{ all -> 0x00bd }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bd }
            r5.<init>()     // Catch:{ all -> 0x00bd }
            java.lang.String r6 = "Connection can be kept alive "
            r5.append(r6)     // Catch:{ all -> 0x00bd }
            r5.append(r3)     // Catch:{ all -> 0x00bd }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00bd }
            r4.debug(r5)     // Catch:{ all -> 0x00bd }
        L_0x00ab:
            r0.detach()     // Catch:{ all -> 0x00cf }
            r7.conn = r2     // Catch:{ all -> 0x00cf }
            cz.msebera.android.httpclient.impl.conn.HttpPoolEntry r3 = r7.poolEntry     // Catch:{ all -> 0x00cf }
            boolean r3 = r3.isClosed()     // Catch:{ all -> 0x00cf }
            if (r3 == 0) goto L_0x00ba
            r7.poolEntry = r2     // Catch:{ all -> 0x00cf }
        L_0x00ba:
            monitor-exit(r7)     // Catch:{ all -> 0x00cf }
            monitor-exit(r0)     // Catch:{ all -> 0x00d2 }
            return
        L_0x00bd:
            r3 = move-exception
            r0.detach()     // Catch:{ all -> 0x00cf }
            r7.conn = r2     // Catch:{ all -> 0x00cf }
            cz.msebera.android.httpclient.impl.conn.HttpPoolEntry r4 = r7.poolEntry     // Catch:{ all -> 0x00cf }
            boolean r4 = r4.isClosed()     // Catch:{ all -> 0x00cf }
            if (r4 == 0) goto L_0x00cd
            r7.poolEntry = r2     // Catch:{ all -> 0x00cf }
        L_0x00cd:
            throw r3     // Catch:{ all -> 0x00cf }
        L_0x00cf:
            r2 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x00cf }
            throw r2     // Catch:{ all -> 0x00d2 }
        L_0x00d2:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00d2 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.conn.BasicClientConnectionManager.releaseConnection(cz.msebera.android.httpclient.conn.ManagedClientConnection, long, java.util.concurrent.TimeUnit):void");
    }

    public void closeExpiredConnections() {
        synchronized (this) {
            assertNotShutdown();
            long now = System.currentTimeMillis();
            if (this.poolEntry != null && this.poolEntry.isExpired(now)) {
                this.poolEntry.close();
                this.poolEntry.getTracker().reset();
            }
        }
    }

    public void closeIdleConnections(long idletime, TimeUnit tunit) {
        Args.notNull(tunit, "Time unit");
        synchronized (this) {
            assertNotShutdown();
            long time = tunit.toMillis(idletime);
            if (time < 0) {
                time = 0;
            }
            long deadline = System.currentTimeMillis() - time;
            if (this.poolEntry != null && this.poolEntry.getUpdated() <= deadline) {
                this.poolEntry.close();
                this.poolEntry.getTracker().reset();
            }
        }
    }

    public void shutdown() {
        synchronized (this) {
            this.shutdown = true;
            try {
                if (this.poolEntry != null) {
                    this.poolEntry.close();
                }
            } finally {
                this.poolEntry = null;
                this.conn = null;
            }
        }
    }
}
