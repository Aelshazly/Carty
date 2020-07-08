package p008cz.msebera.android.httpclient.impl.conn;

import java.io.Closeable;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.LongCompanionObject;
import p008cz.msebera.android.httpclient.HttpClientConnection;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.config.ConnectionConfig;
import p008cz.msebera.android.httpclient.config.Lookup;
import p008cz.msebera.android.httpclient.config.Registry;
import p008cz.msebera.android.httpclient.config.RegistryBuilder;
import p008cz.msebera.android.httpclient.config.SocketConfig;
import p008cz.msebera.android.httpclient.conn.ConnectionRequest;
import p008cz.msebera.android.httpclient.conn.DnsResolver;
import p008cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import p008cz.msebera.android.httpclient.conn.HttpConnectionFactory;
import p008cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import p008cz.msebera.android.httpclient.conn.SchemePortResolver;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.conn.socket.ConnectionSocketFactory;
import p008cz.msebera.android.httpclient.conn.socket.PlainConnectionSocketFactory;
import p008cz.msebera.android.httpclient.conn.ssl.SSLConnectionSocketFactory;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;
import p008cz.msebera.android.httpclient.util.LangUtils;

/* renamed from: cz.msebera.android.httpclient.impl.conn.BasicHttpClientConnectionManager */
public class BasicHttpClientConnectionManager implements HttpClientConnectionManager, Closeable {
    private ManagedHttpClientConnection conn;
    private ConnectionConfig connConfig;
    private final HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory;
    private final HttpClientConnectionOperator connectionOperator;
    private long expiry;
    private final AtomicBoolean isShutdown;
    private boolean leased;
    public HttpClientAndroidLog log;
    private HttpRoute route;
    private SocketConfig socketConfig;
    private Object state;
    private long updated;

    private static Registry<ConnectionSocketFactory> getDefaultRegistry() {
        return RegistryBuilder.create().register(HttpHost.DEFAULT_SCHEME_NAME, PlainConnectionSocketFactory.getSocketFactory()).register("https", SSLConnectionSocketFactory.getSocketFactory()).build();
    }

    public BasicHttpClientConnectionManager(Lookup<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory2, SchemePortResolver schemePortResolver, DnsResolver dnsResolver) {
        this.log = new HttpClientAndroidLog(getClass());
        this.connectionOperator = new HttpClientConnectionOperator(socketFactoryRegistry, schemePortResolver, dnsResolver);
        this.connFactory = connFactory2 != null ? connFactory2 : ManagedHttpClientConnectionFactory.INSTANCE;
        this.expiry = LongCompanionObject.MAX_VALUE;
        this.socketConfig = SocketConfig.DEFAULT;
        this.connConfig = ConnectionConfig.DEFAULT;
        this.isShutdown = new AtomicBoolean(false);
    }

    public BasicHttpClientConnectionManager(Lookup<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory2) {
        this(socketFactoryRegistry, connFactory2, null, null);
    }

    public BasicHttpClientConnectionManager(Lookup<ConnectionSocketFactory> socketFactoryRegistry) {
        this(socketFactoryRegistry, null, null, null);
    }

    public BasicHttpClientConnectionManager() {
        this(getDefaultRegistry(), null, null, null);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }

    public void close() {
        shutdown();
    }

    /* access modifiers changed from: 0000 */
    public HttpRoute getRoute() {
        return this.route;
    }

    /* access modifiers changed from: 0000 */
    public Object getState() {
        return this.state;
    }

    public synchronized SocketConfig getSocketConfig() {
        return this.socketConfig;
    }

    public synchronized void setSocketConfig(SocketConfig socketConfig2) {
        this.socketConfig = socketConfig2 != null ? socketConfig2 : SocketConfig.DEFAULT;
    }

    public synchronized ConnectionConfig getConnectionConfig() {
        return this.connConfig;
    }

    public synchronized void setConnectionConfig(ConnectionConfig connConfig2) {
        this.connConfig = connConfig2 != null ? connConfig2 : ConnectionConfig.DEFAULT;
    }

    public final ConnectionRequest requestConnection(final HttpRoute route2, final Object state2) {
        Args.notNull(route2, "Route");
        return new ConnectionRequest() {
            public boolean cancel() {
                return false;
            }

            public HttpClientConnection get(long timeout, TimeUnit tunit) {
                return BasicHttpClientConnectionManager.this.getConnection(route2, state2);
            }
        };
    }

    private void closeConnection() {
        if (this.conn != null) {
            this.log.debug("Closing connection");
            try {
                this.conn.close();
            } catch (IOException iox) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("I/O exception closing connection", iox);
                }
            }
            this.conn = null;
        }
    }

    private void shutdownConnection() {
        if (this.conn != null) {
            this.log.debug("Shutting down connection");
            try {
                this.conn.shutdown();
            } catch (IOException iox) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("I/O exception shutting down connection", iox);
                }
            }
            this.conn = null;
        }
    }

    private void checkExpiry() {
        if (this.conn != null && System.currentTimeMillis() >= this.expiry) {
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog httpClientAndroidLog = this.log;
                StringBuilder sb = new StringBuilder();
                sb.append("Connection expired @ ");
                sb.append(new Date(this.expiry));
                httpClientAndroidLog.debug(sb.toString());
            }
            closeConnection();
        }
    }

    /* access modifiers changed from: 0000 */
    public synchronized HttpClientConnection getConnection(HttpRoute route2, Object state2) {
        boolean z = false;
        Asserts.check(!this.isShutdown.get(), "Connection manager has been shut down");
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Get connection for route ");
            sb.append(route2);
            httpClientAndroidLog.debug(sb.toString());
        }
        if (!this.leased) {
            z = true;
        }
        Asserts.check(z, "Connection is still allocated");
        if (!LangUtils.equals((Object) this.route, (Object) route2) || !LangUtils.equals(this.state, state2)) {
            closeConnection();
        }
        this.route = route2;
        this.state = state2;
        checkExpiry();
        if (this.conn == null) {
            this.conn = (ManagedHttpClientConnection) this.connFactory.create(route2, this.connConfig);
        }
        this.leased = true;
        return this.conn;
    }

    public synchronized void releaseConnection(HttpClientConnection conn2, Object state2, long keepalive, TimeUnit tunit) {
        String s;
        Args.notNull(conn2, "Connection");
        Asserts.check(conn2 == this.conn, "Connection not obtained from this manager");
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Releasing connection ");
            sb.append(conn2);
            httpClientAndroidLog.debug(sb.toString());
        }
        if (!this.isShutdown.get()) {
            try {
                this.updated = System.currentTimeMillis();
                if (!this.conn.isOpen()) {
                    this.conn = null;
                    this.route = null;
                    this.conn = null;
                    this.expiry = LongCompanionObject.MAX_VALUE;
                } else {
                    this.state = state2;
                    if (this.log.isDebugEnabled()) {
                        if (keepalive > 0) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("for ");
                            sb2.append(keepalive);
                            sb2.append(" ");
                            sb2.append(tunit);
                            s = sb2.toString();
                        } else {
                            s = "indefinitely";
                        }
                        HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Connection can be kept alive ");
                        sb3.append(s);
                        httpClientAndroidLog2.debug(sb3.toString());
                    }
                    if (keepalive > 0) {
                        this.expiry = this.updated + tunit.toMillis(keepalive);
                    } else {
                        this.expiry = LongCompanionObject.MAX_VALUE;
                    }
                }
            } finally {
                this.leased = false;
            }
        }
    }

    public void connect(HttpClientConnection conn2, HttpRoute route2, int connectTimeout, HttpContext context) throws IOException {
        HttpHost host;
        Args.notNull(conn2, "Connection");
        Args.notNull(route2, "HTTP route");
        Asserts.check(conn2 == this.conn, "Connection not obtained from this manager");
        if (route2.getProxyHost() != null) {
            host = route2.getProxyHost();
        } else {
            host = route2.getTargetHost();
        }
        this.connectionOperator.connect(this.conn, host, route2.getLocalSocketAddress(), connectTimeout, this.socketConfig, context);
    }

    public void upgrade(HttpClientConnection conn2, HttpRoute route2, HttpContext context) throws IOException {
        Args.notNull(conn2, "Connection");
        Args.notNull(route2, "HTTP route");
        Asserts.check(conn2 == this.conn, "Connection not obtained from this manager");
        this.connectionOperator.upgrade(this.conn, route2.getTargetHost(), context);
    }

    public void routeComplete(HttpClientConnection conn2, HttpRoute route2, HttpContext context) throws IOException {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0013, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void closeExpiredConnections() {
        /*
            r1 = this;
            monitor-enter(r1)
            java.util.concurrent.atomic.AtomicBoolean r0 = r1.isShutdown     // Catch:{ all -> 0x0014 }
            boolean r0 = r0.get()     // Catch:{ all -> 0x0014 }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r1)
            return
        L_0x000b:
            boolean r0 = r1.leased     // Catch:{ all -> 0x0014 }
            if (r0 != 0) goto L_0x0012
            r1.checkExpiry()     // Catch:{ all -> 0x0014 }
        L_0x0012:
            monitor-exit(r1)
            return
        L_0x0014:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.conn.BasicHttpClientConnectionManager.closeExpiredConnections():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void closeIdleConnections(long r8, java.util.concurrent.TimeUnit r10) {
        /*
            r7 = this;
            monitor-enter(r7)
            java.lang.String r0 = "Time unit"
            p008cz.msebera.android.httpclient.util.Args.notNull(r10, r0)     // Catch:{ all -> 0x0030 }
            java.util.concurrent.atomic.AtomicBoolean r0 = r7.isShutdown     // Catch:{ all -> 0x0030 }
            boolean r0 = r0.get()     // Catch:{ all -> 0x0030 }
            if (r0 == 0) goto L_0x0010
            monitor-exit(r7)
            return
        L_0x0010:
            boolean r0 = r7.leased     // Catch:{ all -> 0x0030 }
            if (r0 != 0) goto L_0x002e
            long r0 = r10.toMillis(r8)     // Catch:{ all -> 0x0030 }
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x0020
            r0 = 0
        L_0x0020:
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0030 }
            long r2 = r2 - r0
            long r4 = r7.updated     // Catch:{ all -> 0x0030 }
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 > 0) goto L_0x002e
            r7.closeConnection()     // Catch:{ all -> 0x0030 }
        L_0x002e:
            monitor-exit(r7)
            return
        L_0x0030:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.conn.BasicHttpClientConnectionManager.closeIdleConnections(long, java.util.concurrent.TimeUnit):void");
    }

    public synchronized void shutdown() {
        if (this.isShutdown.compareAndSet(false, true)) {
            shutdownConnection();
        }
    }
}
