package p008cz.msebera.android.httpclient.impl.conn;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import p008cz.msebera.android.httpclient.HttpClientConnection;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.config.ConnectionConfig;
import p008cz.msebera.android.httpclient.config.Lookup;
import p008cz.msebera.android.httpclient.config.Registry;
import p008cz.msebera.android.httpclient.config.RegistryBuilder;
import p008cz.msebera.android.httpclient.config.SocketConfig;
import p008cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException;
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
import p008cz.msebera.android.httpclient.pool.ConnFactory;
import p008cz.msebera.android.httpclient.pool.ConnPoolControl;
import p008cz.msebera.android.httpclient.pool.PoolStats;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;

/* renamed from: cz.msebera.android.httpclient.impl.conn.PoolingHttpClientConnectionManager */
public class PoolingHttpClientConnectionManager implements HttpClientConnectionManager, ConnPoolControl<HttpRoute>, Closeable {
    private final ConfigData configData;
    private final HttpClientConnectionOperator connectionOperator;
    private final AtomicBoolean isShutDown;
    public HttpClientAndroidLog log;
    private final CPool pool;

    /* renamed from: cz.msebera.android.httpclient.impl.conn.PoolingHttpClientConnectionManager$ConfigData */
    static class ConfigData {
        private final Map<HttpHost, ConnectionConfig> connectionConfigMap = new ConcurrentHashMap();
        private volatile ConnectionConfig defaultConnectionConfig;
        private volatile SocketConfig defaultSocketConfig;
        private final Map<HttpHost, SocketConfig> socketConfigMap = new ConcurrentHashMap();

        ConfigData() {
        }

        public SocketConfig getDefaultSocketConfig() {
            return this.defaultSocketConfig;
        }

        public void setDefaultSocketConfig(SocketConfig defaultSocketConfig2) {
            this.defaultSocketConfig = defaultSocketConfig2;
        }

        public ConnectionConfig getDefaultConnectionConfig() {
            return this.defaultConnectionConfig;
        }

        public void setDefaultConnectionConfig(ConnectionConfig defaultConnectionConfig2) {
            this.defaultConnectionConfig = defaultConnectionConfig2;
        }

        public SocketConfig getSocketConfig(HttpHost host) {
            return (SocketConfig) this.socketConfigMap.get(host);
        }

        public void setSocketConfig(HttpHost host, SocketConfig socketConfig) {
            this.socketConfigMap.put(host, socketConfig);
        }

        public ConnectionConfig getConnectionConfig(HttpHost host) {
            return (ConnectionConfig) this.connectionConfigMap.get(host);
        }

        public void setConnectionConfig(HttpHost host, ConnectionConfig connectionConfig) {
            this.connectionConfigMap.put(host, connectionConfig);
        }
    }

    /* renamed from: cz.msebera.android.httpclient.impl.conn.PoolingHttpClientConnectionManager$InternalConnectionFactory */
    static class InternalConnectionFactory implements ConnFactory<HttpRoute, ManagedHttpClientConnection> {
        private final ConfigData configData;
        private final HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory;

        InternalConnectionFactory(ConfigData configData2, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory2) {
            this.configData = configData2 != null ? configData2 : new ConfigData();
            this.connFactory = connFactory2 != null ? connFactory2 : ManagedHttpClientConnectionFactory.INSTANCE;
        }

        public ManagedHttpClientConnection create(HttpRoute route) throws IOException {
            ConnectionConfig config = null;
            if (route.getProxyHost() != null) {
                config = this.configData.getConnectionConfig(route.getProxyHost());
            }
            if (config == null) {
                config = this.configData.getConnectionConfig(route.getTargetHost());
            }
            if (config == null) {
                config = this.configData.getDefaultConnectionConfig();
            }
            if (config == null) {
                config = ConnectionConfig.DEFAULT;
            }
            return (ManagedHttpClientConnection) this.connFactory.create(route, config);
        }
    }

    private static Registry<ConnectionSocketFactory> getDefaultRegistry() {
        return RegistryBuilder.create().register(HttpHost.DEFAULT_SCHEME_NAME, PlainConnectionSocketFactory.getSocketFactory()).register("https", SSLConnectionSocketFactory.getSocketFactory()).build();
    }

    public PoolingHttpClientConnectionManager() {
        this(getDefaultRegistry());
    }

    public PoolingHttpClientConnectionManager(long timeToLive, TimeUnit tunit) {
        this(getDefaultRegistry(), null, null, null, timeToLive, tunit);
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry) {
        this(socketFactoryRegistry, null, null);
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry, DnsResolver dnsResolver) {
        this(socketFactoryRegistry, null, dnsResolver);
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
        this(socketFactoryRegistry, connFactory, null);
    }

    public PoolingHttpClientConnectionManager(HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
        this(getDefaultRegistry(), connFactory, null);
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory, DnsResolver dnsResolver) {
        this(socketFactoryRegistry, connFactory, null, dnsResolver, -1, TimeUnit.MILLISECONDS);
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory, SchemePortResolver schemePortResolver, DnsResolver dnsResolver, long timeToLive, TimeUnit tunit) {
        this.log = new HttpClientAndroidLog(getClass());
        this.configData = new ConfigData();
        CPool cPool = new CPool(new InternalConnectionFactory(this.configData, connFactory), 2, 20, timeToLive, tunit);
        this.pool = cPool;
        this.connectionOperator = new HttpClientConnectionOperator(socketFactoryRegistry, schemePortResolver, dnsResolver);
        this.isShutDown = new AtomicBoolean(false);
    }

    PoolingHttpClientConnectionManager(CPool pool2, Lookup<ConnectionSocketFactory> socketFactoryRegistry, SchemePortResolver schemePortResolver, DnsResolver dnsResolver) {
        this.log = new HttpClientAndroidLog(getClass());
        this.configData = new ConfigData();
        this.pool = pool2;
        this.connectionOperator = new HttpClientConnectionOperator(socketFactoryRegistry, schemePortResolver, dnsResolver);
        this.isShutDown = new AtomicBoolean(false);
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

    private String format(CPoolEntry entry) {
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

    public ConnectionRequest requestConnection(HttpRoute route, Object state) {
        Args.notNull(route, "HTTP route");
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Connection request: ");
            sb.append(format(route, state));
            sb.append(formatStats(route));
            httpClientAndroidLog.debug(sb.toString());
        }
        final Future<CPoolEntry> future = this.pool.lease(route, state, null);
        return new ConnectionRequest() {
            public boolean cancel() {
                return future.cancel(true);
            }

            public HttpClientConnection get(long timeout, TimeUnit tunit) throws InterruptedException, ExecutionException, ConnectionPoolTimeoutException {
                return PoolingHttpClientConnectionManager.this.leaseConnection(future, timeout, tunit);
            }
        };
    }

    /* access modifiers changed from: protected */
    public HttpClientConnection leaseConnection(Future<CPoolEntry> future, long timeout, TimeUnit tunit) throws InterruptedException, ExecutionException, ConnectionPoolTimeoutException {
        try {
            CPoolEntry entry = (CPoolEntry) future.get(timeout, tunit);
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
            return CPoolProxy.newProxy(entry);
        } catch (TimeoutException e) {
            throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00bf, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void releaseConnection(p008cz.msebera.android.httpclient.HttpClientConnection r11, java.lang.Object r12, long r13, java.util.concurrent.TimeUnit r15) {
        /*
            r10 = this;
            java.lang.String r0 = "Managed connection"
            p008cz.msebera.android.httpclient.util.Args.notNull(r11, r0)
            monitor-enter(r11)
            cz.msebera.android.httpclient.impl.conn.CPoolEntry r0 = p008cz.msebera.android.httpclient.impl.conn.CPoolProxy.detach(r11)     // Catch:{ all -> 0x0105 }
            if (r0 != 0) goto L_0x000e
            monitor-exit(r11)     // Catch:{ all -> 0x0105 }
            return
        L_0x000e:
            java.lang.Object r1 = r0.getConnection()     // Catch:{ all -> 0x0105 }
            cz.msebera.android.httpclient.conn.ManagedHttpClientConnection r1 = (p008cz.msebera.android.httpclient.conn.ManagedHttpClientConnection) r1     // Catch:{ all -> 0x0105 }
            r2 = 1
            r3 = 0
            boolean r4 = r1.isOpen()     // Catch:{ all -> 0x00c0 }
            if (r4 == 0) goto L_0x007c
            if (r15 == 0) goto L_0x0020
            r4 = r15
            goto L_0x0022
        L_0x0020:
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x00c0 }
        L_0x0022:
            r0.setState(r12)     // Catch:{ all -> 0x00c0 }
            r0.updateExpiry(r13, r4)     // Catch:{ all -> 0x00c0 }
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r5 = r10.log     // Catch:{ all -> 0x00c0 }
            boolean r5 = r5.isDebugEnabled()     // Catch:{ all -> 0x00c0 }
            if (r5 == 0) goto L_0x007c
            r5 = 0
            int r7 = (r13 > r5 ? 1 : (r13 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0058
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c0 }
            r5.<init>()     // Catch:{ all -> 0x00c0 }
            java.lang.String r6 = "for "
            r5.append(r6)     // Catch:{ all -> 0x00c0 }
            long r6 = r4.toMillis(r13)     // Catch:{ all -> 0x00c0 }
            double r6 = (double) r6     // Catch:{ all -> 0x00c0 }
            r8 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r6 = r6 / r8
            r5.append(r6)     // Catch:{ all -> 0x00c0 }
            java.lang.String r6 = " seconds"
            r5.append(r6)     // Catch:{ all -> 0x00c0 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00c0 }
            goto L_0x005a
        L_0x0058:
            java.lang.String r5 = "indefinitely"
        L_0x005a:
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r6 = r10.log     // Catch:{ all -> 0x00c0 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c0 }
            r7.<init>()     // Catch:{ all -> 0x00c0 }
            java.lang.String r8 = "Connection "
            r7.append(r8)     // Catch:{ all -> 0x00c0 }
            java.lang.String r8 = r10.format(r0)     // Catch:{ all -> 0x00c0 }
            r7.append(r8)     // Catch:{ all -> 0x00c0 }
            java.lang.String r8 = " can be kept alive "
            r7.append(r8)     // Catch:{ all -> 0x00c0 }
            r7.append(r5)     // Catch:{ all -> 0x00c0 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x00c0 }
            r6.debug(r7)     // Catch:{ all -> 0x00c0 }
        L_0x007c:
            cz.msebera.android.httpclient.impl.conn.CPool r4 = r10.pool     // Catch:{ all -> 0x0105 }
            boolean r5 = r1.isOpen()     // Catch:{ all -> 0x0105 }
            if (r5 == 0) goto L_0x008b
            boolean r5 = r0.isRouteComplete()     // Catch:{ all -> 0x0105 }
            if (r5 == 0) goto L_0x008b
            goto L_0x008c
        L_0x008b:
            r2 = 0
        L_0x008c:
            r4.release(r0, r2)     // Catch:{ all -> 0x0105 }
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r2 = r10.log     // Catch:{ all -> 0x0105 }
            boolean r2 = r2.isDebugEnabled()     // Catch:{ all -> 0x0105 }
            if (r2 == 0) goto L_0x00be
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r2 = r10.log     // Catch:{ all -> 0x0105 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0105 }
            r3.<init>()     // Catch:{ all -> 0x0105 }
            java.lang.String r4 = "Connection released: "
            r3.append(r4)     // Catch:{ all -> 0x0105 }
            java.lang.String r4 = r10.format(r0)     // Catch:{ all -> 0x0105 }
            r3.append(r4)     // Catch:{ all -> 0x0105 }
            java.lang.Object r4 = r0.getRoute()     // Catch:{ all -> 0x0105 }
            cz.msebera.android.httpclient.conn.routing.HttpRoute r4 = (p008cz.msebera.android.httpclient.conn.routing.HttpRoute) r4     // Catch:{ all -> 0x0105 }
            java.lang.String r4 = r10.formatStats(r4)     // Catch:{ all -> 0x0105 }
            r3.append(r4)     // Catch:{ all -> 0x0105 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0105 }
            r2.debug(r3)     // Catch:{ all -> 0x0105 }
        L_0x00be:
            monitor-exit(r11)     // Catch:{ all -> 0x0105 }
            return
        L_0x00c0:
            r4 = move-exception
            cz.msebera.android.httpclient.impl.conn.CPool r5 = r10.pool     // Catch:{ all -> 0x0105 }
            boolean r6 = r1.isOpen()     // Catch:{ all -> 0x0105 }
            if (r6 == 0) goto L_0x00d0
            boolean r6 = r0.isRouteComplete()     // Catch:{ all -> 0x0105 }
            if (r6 == 0) goto L_0x00d0
            goto L_0x00d1
        L_0x00d0:
            r2 = 0
        L_0x00d1:
            r5.release(r0, r2)     // Catch:{ all -> 0x0105 }
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r2 = r10.log     // Catch:{ all -> 0x0105 }
            boolean r2 = r2.isDebugEnabled()     // Catch:{ all -> 0x0105 }
            if (r2 == 0) goto L_0x0103
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r2 = r10.log     // Catch:{ all -> 0x0105 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0105 }
            r3.<init>()     // Catch:{ all -> 0x0105 }
            java.lang.String r5 = "Connection released: "
            r3.append(r5)     // Catch:{ all -> 0x0105 }
            java.lang.String r5 = r10.format(r0)     // Catch:{ all -> 0x0105 }
            r3.append(r5)     // Catch:{ all -> 0x0105 }
            java.lang.Object r5 = r0.getRoute()     // Catch:{ all -> 0x0105 }
            cz.msebera.android.httpclient.conn.routing.HttpRoute r5 = (p008cz.msebera.android.httpclient.conn.routing.HttpRoute) r5     // Catch:{ all -> 0x0105 }
            java.lang.String r5 = r10.formatStats(r5)     // Catch:{ all -> 0x0105 }
            r3.append(r5)     // Catch:{ all -> 0x0105 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0105 }
            r2.debug(r3)     // Catch:{ all -> 0x0105 }
        L_0x0103:
            throw r4     // Catch:{ all -> 0x0105 }
        L_0x0105:
            r0 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x0105 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.conn.PoolingHttpClientConnectionManager.releaseConnection(cz.msebera.android.httpclient.HttpClientConnection, java.lang.Object, long, java.util.concurrent.TimeUnit):void");
    }

    public void connect(HttpClientConnection managedConn, HttpRoute route, int connectTimeout, HttpContext context) throws IOException {
        ManagedHttpClientConnection conn;
        HttpHost host;
        SocketConfig socketConfig;
        Args.notNull(managedConn, "Managed Connection");
        Args.notNull(route, "HTTP route");
        synchronized (managedConn) {
            conn = (ManagedHttpClientConnection) CPoolProxy.getPoolEntry(managedConn).getConnection();
        }
        if (route.getProxyHost() != null) {
            host = route.getProxyHost();
        } else {
            host = route.getTargetHost();
        }
        InetSocketAddress localAddress = route.getLocalSocketAddress();
        SocketConfig socketConfig2 = this.configData.getSocketConfig(host);
        if (socketConfig2 == null) {
            socketConfig2 = this.configData.getDefaultSocketConfig();
        }
        if (socketConfig2 == null) {
            socketConfig = SocketConfig.DEFAULT;
        } else {
            socketConfig = socketConfig2;
        }
        this.connectionOperator.connect(conn, host, localAddress, connectTimeout, socketConfig, context);
    }

    public void upgrade(HttpClientConnection managedConn, HttpRoute route, HttpContext context) throws IOException {
        ManagedHttpClientConnection conn;
        Args.notNull(managedConn, "Managed Connection");
        Args.notNull(route, "HTTP route");
        synchronized (managedConn) {
            conn = (ManagedHttpClientConnection) CPoolProxy.getPoolEntry(managedConn).getConnection();
        }
        this.connectionOperator.upgrade(conn, route.getTargetHost(), context);
    }

    public void routeComplete(HttpClientConnection managedConn, HttpRoute route, HttpContext context) throws IOException {
        Args.notNull(managedConn, "Managed Connection");
        Args.notNull(route, "HTTP route");
        synchronized (managedConn) {
            CPoolProxy.getPoolEntry(managedConn).markRouteComplete();
        }
    }

    public void shutdown() {
        if (this.isShutDown.compareAndSet(false, true)) {
            this.log.debug("Connection manager is shutting down");
            try {
                this.pool.shutdown();
            } catch (IOException ex) {
                this.log.debug("I/O exception shutting down connection manager", ex);
            }
            this.log.debug("Connection manager shut down");
        }
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

    public SocketConfig getDefaultSocketConfig() {
        return this.configData.getDefaultSocketConfig();
    }

    public void setDefaultSocketConfig(SocketConfig defaultSocketConfig) {
        this.configData.setDefaultSocketConfig(defaultSocketConfig);
    }

    public ConnectionConfig getDefaultConnectionConfig() {
        return this.configData.getDefaultConnectionConfig();
    }

    public void setDefaultConnectionConfig(ConnectionConfig defaultConnectionConfig) {
        this.configData.setDefaultConnectionConfig(defaultConnectionConfig);
    }

    public SocketConfig getSocketConfig(HttpHost host) {
        return this.configData.getSocketConfig(host);
    }

    public void setSocketConfig(HttpHost host, SocketConfig socketConfig) {
        this.configData.setSocketConfig(host, socketConfig);
    }

    public ConnectionConfig getConnectionConfig(HttpHost host) {
        return this.configData.getConnectionConfig(host);
    }

    public void setConnectionConfig(HttpHost host, ConnectionConfig connectionConfig) {
        this.configData.setConnectionConfig(host, connectionConfig);
    }
}
