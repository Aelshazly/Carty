package p008cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.config.Lookup;
import p008cz.msebera.android.httpclient.conn.DnsResolver;
import p008cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import p008cz.msebera.android.httpclient.conn.SchemePortResolver;
import p008cz.msebera.android.httpclient.conn.UnsupportedSchemeException;
import p008cz.msebera.android.httpclient.conn.socket.ConnectionSocketFactory;
import p008cz.msebera.android.httpclient.conn.socket.LayeredConnectionSocketFactory;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.conn.HttpClientConnectionOperator */
class HttpClientConnectionOperator {
    static final String SOCKET_FACTORY_REGISTRY = "http.socket-factory-registry";
    private final DnsResolver dnsResolver;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private final SchemePortResolver schemePortResolver;
    private final Lookup<ConnectionSocketFactory> socketFactoryRegistry;

    HttpClientConnectionOperator(Lookup<ConnectionSocketFactory> socketFactoryRegistry2, SchemePortResolver schemePortResolver2, DnsResolver dnsResolver2) {
        Args.notNull(socketFactoryRegistry2, "Socket factory registry");
        this.socketFactoryRegistry = socketFactoryRegistry2;
        this.schemePortResolver = schemePortResolver2 != null ? schemePortResolver2 : DefaultSchemePortResolver.INSTANCE;
        this.dnsResolver = dnsResolver2 != null ? dnsResolver2 : SystemDefaultDnsResolver.INSTANCE;
    }

    private Lookup<ConnectionSocketFactory> getSocketFactoryRegistry(HttpContext context) {
        Lookup<ConnectionSocketFactory> reg = (Lookup) context.getAttribute("http.socket-factory-registry");
        if (reg == null) {
            return this.socketFactoryRegistry;
        }
        return reg;
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0140 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00e8 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00ee A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0135 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect(p008cz.msebera.android.httpclient.conn.ManagedHttpClientConnection r22, p008cz.msebera.android.httpclient.HttpHost r23, java.net.InetSocketAddress r24, int r25, p008cz.msebera.android.httpclient.config.SocketConfig r26, p008cz.msebera.android.httpclient.protocol.HttpContext r27) throws java.io.IOException {
        /*
            r21 = this;
            r1 = r21
            r2 = r22
            r10 = r23
            r11 = r27
            cz.msebera.android.httpclient.config.Lookup r12 = r1.getSocketFactoryRegistry(r11)
            java.lang.String r0 = r23.getSchemeName()
            java.lang.Object r0 = r12.lookup(r0)
            r13 = r0
            cz.msebera.android.httpclient.conn.socket.ConnectionSocketFactory r13 = (p008cz.msebera.android.httpclient.conn.socket.ConnectionSocketFactory) r13
            if (r13 == 0) goto L_0x0147
            java.net.InetAddress r0 = r23.getAddress()
            r14 = 0
            r15 = 1
            if (r0 == 0) goto L_0x002a
            java.net.InetAddress[] r0 = new java.net.InetAddress[r15]
            java.net.InetAddress r3 = r23.getAddress()
            r0[r14] = r3
            goto L_0x0034
        L_0x002a:
            cz.msebera.android.httpclient.conn.DnsResolver r0 = r1.dnsResolver
            java.lang.String r3 = r23.getHostName()
            java.net.InetAddress[] r0 = r0.resolve(r3)
        L_0x0034:
            r9 = r0
            cz.msebera.android.httpclient.conn.SchemePortResolver r0 = r1.schemePortResolver
            int r8 = r0.resolve(r10)
            r0 = 0
            r7 = r0
        L_0x003d:
            int r0 = r9.length
            if (r7 >= r0) goto L_0x0146
            r6 = r9[r7]
            int r0 = r9.length
            int r0 = r0 - r15
            if (r7 != r0) goto L_0x0048
            r0 = 1
            goto L_0x0049
        L_0x0048:
            r0 = 0
        L_0x0049:
            r16 = r0
            java.net.Socket r5 = r13.createSocket(r11)
            int r0 = r26.getSoTimeout()
            r5.setSoTimeout(r0)
            boolean r0 = r26.isSoReuseAddress()
            r5.setReuseAddress(r0)
            boolean r0 = r26.isTcpNoDelay()
            r5.setTcpNoDelay(r0)
            boolean r0 = r26.isSoKeepAlive()
            r5.setKeepAlive(r0)
            int r4 = r26.getSoLinger()
            if (r4 < 0) goto L_0x0079
            if (r4 <= 0) goto L_0x0075
            r0 = 1
            goto L_0x0076
        L_0x0075:
            r0 = 0
        L_0x0076:
            r5.setSoLinger(r0, r4)
        L_0x0079:
            r2.bind(r5)
            java.net.InetSocketAddress r0 = new java.net.InetSocketAddress
            r0.<init>(r6, r8)
            r3 = r0
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r0 = r1.log
            boolean r0 = r0.isDebugEnabled()
            if (r0 == 0) goto L_0x00a0
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r0 = r1.log
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "Connecting to "
            r14.append(r15)
            r14.append(r3)
            java.lang.String r14 = r14.toString()
            r0.debug(r14)
        L_0x00a0:
            r14 = r3
            r3 = r13
            r15 = r4
            r4 = r25
            r17 = r5
            r18 = r6
            r6 = r23
            r19 = r7
            r7 = r14
            r20 = r8
            r8 = r24
            r11 = r9
            r9 = r27
            java.net.Socket r0 = r3.connectSocket(r4, r5, r6, r7, r8, r9)     // Catch:{ SocketTimeoutException -> 0x0107, ConnectException -> 0x00e9, NoRouteToHostException -> 0x00e2 }
            r5 = r0
            r2.bind(r5)     // Catch:{ SocketTimeoutException -> 0x00e0, ConnectException -> 0x00de, NoRouteToHostException -> 0x00dc }
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r0 = r1.log     // Catch:{ SocketTimeoutException -> 0x00e0, ConnectException -> 0x00de, NoRouteToHostException -> 0x00dc }
            boolean r0 = r0.isDebugEnabled()     // Catch:{ SocketTimeoutException -> 0x00e0, ConnectException -> 0x00de, NoRouteToHostException -> 0x00dc }
            if (r0 == 0) goto L_0x00db
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r0 = r1.log     // Catch:{ SocketTimeoutException -> 0x00e0, ConnectException -> 0x00de, NoRouteToHostException -> 0x00dc }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00e0, ConnectException -> 0x00de, NoRouteToHostException -> 0x00dc }
            r3.<init>()     // Catch:{ SocketTimeoutException -> 0x00e0, ConnectException -> 0x00de, NoRouteToHostException -> 0x00dc }
            java.lang.String r4 = "Connection established "
            r3.append(r4)     // Catch:{ SocketTimeoutException -> 0x00e0, ConnectException -> 0x00de, NoRouteToHostException -> 0x00dc }
            r3.append(r2)     // Catch:{ SocketTimeoutException -> 0x00e0, ConnectException -> 0x00de, NoRouteToHostException -> 0x00dc }
            java.lang.String r3 = r3.toString()     // Catch:{ SocketTimeoutException -> 0x00e0, ConnectException -> 0x00de, NoRouteToHostException -> 0x00dc }
            r0.debug(r3)     // Catch:{ SocketTimeoutException -> 0x00e0, ConnectException -> 0x00de, NoRouteToHostException -> 0x00dc }
        L_0x00db:
            return
        L_0x00dc:
            r0 = move-exception
            goto L_0x00e5
        L_0x00de:
            r0 = move-exception
            goto L_0x00ec
        L_0x00e0:
            r0 = move-exception
            goto L_0x010a
        L_0x00e2:
            r0 = move-exception
            r5 = r17
        L_0x00e5:
            if (r16 != 0) goto L_0x00e8
            goto L_0x010d
        L_0x00e8:
            throw r0
        L_0x00e9:
            r0 = move-exception
            r5 = r17
        L_0x00ec:
            if (r16 == 0) goto L_0x0106
            java.lang.String r3 = r0.getMessage()
            java.lang.String r4 = "Connection timed out"
            boolean r4 = r4.equals(r3)
            if (r4 == 0) goto L_0x0100
            cz.msebera.android.httpclient.conn.ConnectTimeoutException r4 = new cz.msebera.android.httpclient.conn.ConnectTimeoutException
            r4.<init>(r0, r10, r11)
            throw r4
        L_0x0100:
            cz.msebera.android.httpclient.conn.HttpHostConnectException r4 = new cz.msebera.android.httpclient.conn.HttpHostConnectException
            r4.<init>(r0, r10, r11)
            throw r4
        L_0x0106:
            goto L_0x010c
        L_0x0107:
            r0 = move-exception
            r5 = r17
        L_0x010a:
            if (r16 != 0) goto L_0x0140
        L_0x010c:
        L_0x010d:
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r0 = r1.log
            boolean r0 = r0.isDebugEnabled()
            if (r0 == 0) goto L_0x0135
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r0 = r1.log
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Connect to "
            r3.append(r4)
            r3.append(r14)
            java.lang.String r4 = " timed out. "
            r3.append(r4)
            java.lang.String r4 = "Connection will be retried using another IP address"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.debug(r3)
        L_0x0135:
            int r7 = r19 + 1
            r9 = r11
            r8 = r20
            r14 = 0
            r15 = 1
            r11 = r27
            goto L_0x003d
        L_0x0140:
            cz.msebera.android.httpclient.conn.ConnectTimeoutException r3 = new cz.msebera.android.httpclient.conn.ConnectTimeoutException
            r3.<init>(r0, r10, r11)
            throw r3
        L_0x0146:
            return
        L_0x0147:
            cz.msebera.android.httpclient.conn.UnsupportedSchemeException r0 = new cz.msebera.android.httpclient.conn.UnsupportedSchemeException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r23.getSchemeName()
            r3.append(r4)
            java.lang.String r4 = " protocol is not supported"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.conn.HttpClientConnectionOperator.connect(cz.msebera.android.httpclient.conn.ManagedHttpClientConnection, cz.msebera.android.httpclient.HttpHost, java.net.InetSocketAddress, int, cz.msebera.android.httpclient.config.SocketConfig, cz.msebera.android.httpclient.protocol.HttpContext):void");
    }

    public void upgrade(ManagedHttpClientConnection conn, HttpHost host, HttpContext context) throws IOException {
        ConnectionSocketFactory sf = (ConnectionSocketFactory) getSocketFactoryRegistry(HttpClientContext.adapt(context)).lookup(host.getSchemeName());
        if (sf == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(host.getSchemeName());
            sb.append(" protocol is not supported");
            throw new UnsupportedSchemeException(sb.toString());
        } else if (sf instanceof LayeredConnectionSocketFactory) {
            conn.bind(((LayeredConnectionSocketFactory) sf).createLayeredSocket(conn.getSocket(), host.getHostName(), this.schemePortResolver.resolve(host), context));
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(host.getSchemeName());
            sb2.append(" protocol does not support connection upgrade");
            throw new UnsupportedSchemeException(sb2.toString());
        }
    }
}
