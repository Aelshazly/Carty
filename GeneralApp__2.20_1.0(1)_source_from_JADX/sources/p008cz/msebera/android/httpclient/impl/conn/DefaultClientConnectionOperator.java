package p008cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.client.protocol.ClientContext;
import p008cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import p008cz.msebera.android.httpclient.conn.DnsResolver;
import p008cz.msebera.android.httpclient.conn.OperatedClientConnection;
import p008cz.msebera.android.httpclient.conn.scheme.Scheme;
import p008cz.msebera.android.httpclient.conn.scheme.SchemeLayeredSocketFactory;
import p008cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.params.HttpConnectionParams;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.DefaultClientConnectionOperator */
public class DefaultClientConnectionOperator implements ClientConnectionOperator {
    protected final DnsResolver dnsResolver;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    protected final SchemeRegistry schemeRegistry;

    public DefaultClientConnectionOperator(SchemeRegistry schemes) {
        Args.notNull(schemes, "Scheme registry");
        this.schemeRegistry = schemes;
        this.dnsResolver = new SystemDefaultDnsResolver();
    }

    public DefaultClientConnectionOperator(SchemeRegistry schemes, DnsResolver dnsResolver2) {
        Args.notNull(schemes, "Scheme registry");
        Args.notNull(dnsResolver2, "DNS resolver");
        this.schemeRegistry = schemes;
        this.dnsResolver = dnsResolver2;
    }

    public OperatedClientConnection createConnection() {
        return new DefaultClientConnection();
    }

    private SchemeRegistry getSchemeRegistry(HttpContext context) {
        SchemeRegistry reg = (SchemeRegistry) context.getAttribute(ClientContext.SCHEME_REGISTRY);
        if (reg == null) {
            return this.schemeRegistry;
        }
        return reg;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e2 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void openConnection(p008cz.msebera.android.httpclient.conn.OperatedClientConnection r20, p008cz.msebera.android.httpclient.HttpHost r21, java.net.InetAddress r22, p008cz.msebera.android.httpclient.protocol.HttpContext r23, p008cz.msebera.android.httpclient.params.HttpParams r24) throws java.io.IOException {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = r22
            r5 = r23
            r6 = r24
            java.lang.String r0 = "Connection"
            p008cz.msebera.android.httpclient.util.Args.notNull(r2, r0)
            java.lang.String r0 = "Target host"
            p008cz.msebera.android.httpclient.util.Args.notNull(r3, r0)
            java.lang.String r0 = "HTTP parameters"
            p008cz.msebera.android.httpclient.util.Args.notNull(r6, r0)
            boolean r0 = r20.isOpen()
            r7 = 1
            r0 = r0 ^ r7
            java.lang.String r8 = "Connection must not be open"
            p008cz.msebera.android.httpclient.util.Asserts.check(r0, r8)
            cz.msebera.android.httpclient.conn.scheme.SchemeRegistry r8 = r1.getSchemeRegistry(r5)
            java.lang.String r0 = r21.getSchemeName()
            cz.msebera.android.httpclient.conn.scheme.Scheme r9 = r8.getScheme(r0)
            cz.msebera.android.httpclient.conn.scheme.SchemeSocketFactory r10 = r9.getSchemeSocketFactory()
            java.lang.String r0 = r21.getHostName()
            java.net.InetAddress[] r11 = r1.resolveHostname(r0)
            int r0 = r21.getPort()
            int r12 = r9.resolvePort(r0)
            r0 = 0
            r13 = r0
        L_0x0048:
            int r0 = r11.length
            if (r13 >= r0) goto L_0x00ee
            r14 = r11[r13]
            int r0 = r11.length
            int r0 = r0 - r7
            r15 = 0
            if (r13 != r0) goto L_0x0054
            r0 = 1
            goto L_0x0055
        L_0x0054:
            r0 = 0
        L_0x0055:
            r16 = r0
            java.net.Socket r7 = r10.createSocket(r6)
            r2.opening(r7, r3)
            cz.msebera.android.httpclient.conn.HttpInetSocketAddress r0 = new cz.msebera.android.httpclient.conn.HttpInetSocketAddress
            r0.<init>(r3, r14, r12)
            r17 = r0
            r0 = 0
            if (r4 == 0) goto L_0x0071
            r18 = r0
            java.net.InetSocketAddress r0 = new java.net.InetSocketAddress
            r0.<init>(r4, r15)
            r15 = r0
            goto L_0x0075
        L_0x0071:
            r18 = r0
            r15 = r18
        L_0x0075:
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r0 = r1.log
            boolean r0 = r0.isDebugEnabled()
            if (r0 == 0) goto L_0x0098
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r0 = r1.log
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r18 = r8
            java.lang.String r8 = "Connecting to "
            r4.append(r8)
            r8 = r17
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            r0.debug(r4)
            goto L_0x009c
        L_0x0098:
            r18 = r8
            r8 = r17
        L_0x009c:
            java.net.Socket r0 = r10.connectSocket(r7, r8, r15, r6)     // Catch:{ ConnectException -> 0x00b6, ConnectTimeoutException -> 0x00b1 }
            if (r7 == r0) goto L_0x00a6
            r7 = r0
            r2.opening(r7, r3)     // Catch:{ ConnectException -> 0x00b6, ConnectTimeoutException -> 0x00b1 }
        L_0x00a6:
            r1.prepareSocket(r7, r5, r6)     // Catch:{ ConnectException -> 0x00b6, ConnectTimeoutException -> 0x00b1 }
            boolean r4 = r10.isSecure(r7)     // Catch:{ ConnectException -> 0x00b6, ConnectTimeoutException -> 0x00b1 }
            r2.openCompleted(r4, r6)     // Catch:{ ConnectException -> 0x00b6, ConnectTimeoutException -> 0x00b1 }
            return
        L_0x00b1:
            r0 = move-exception
            if (r16 != 0) goto L_0x00b5
            goto L_0x00ba
        L_0x00b5:
            throw r0
        L_0x00b6:
            r0 = move-exception
            if (r16 != 0) goto L_0x00ed
        L_0x00ba:
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r0 = r1.log
            boolean r0 = r0.isDebugEnabled()
            if (r0 == 0) goto L_0x00e2
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r0 = r1.log
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r1 = "Connect to "
            r4.append(r1)
            r4.append(r8)
            java.lang.String r1 = " timed out. "
            r4.append(r1)
            java.lang.String r1 = "Connection will be retried using another IP address"
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r0.debug(r1)
        L_0x00e2:
            int r13 = r13 + 1
            r1 = r19
            r4 = r22
            r8 = r18
            r7 = 1
            goto L_0x0048
        L_0x00ed:
            throw r0
        L_0x00ee:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.conn.DefaultClientConnectionOperator.openConnection(cz.msebera.android.httpclient.conn.OperatedClientConnection, cz.msebera.android.httpclient.HttpHost, java.net.InetAddress, cz.msebera.android.httpclient.protocol.HttpContext, cz.msebera.android.httpclient.params.HttpParams):void");
    }

    public void updateSecureConnection(OperatedClientConnection conn, HttpHost target, HttpContext context, HttpParams params) throws IOException {
        Args.notNull(conn, "Connection");
        Args.notNull(target, "Target host");
        Args.notNull(params, "Parameters");
        Asserts.check(conn.isOpen(), "Connection must be open");
        Scheme schm = getSchemeRegistry(context).getScheme(target.getSchemeName());
        Asserts.check(schm.getSchemeSocketFactory() instanceof SchemeLayeredSocketFactory, "Socket factory must implement SchemeLayeredSocketFactory");
        SchemeLayeredSocketFactory lsf = (SchemeLayeredSocketFactory) schm.getSchemeSocketFactory();
        Socket sock = lsf.createLayeredSocket(conn.getSocket(), target.getHostName(), schm.resolvePort(target.getPort()), params);
        prepareSocket(sock, context, params);
        conn.update(sock, target, lsf.isSecure(sock), params);
    }

    /* access modifiers changed from: protected */
    public void prepareSocket(Socket sock, HttpContext context, HttpParams params) throws IOException {
        sock.setTcpNoDelay(HttpConnectionParams.getTcpNoDelay(params));
        sock.setSoTimeout(HttpConnectionParams.getSoTimeout(params));
        int linger = HttpConnectionParams.getLinger(params);
        if (linger >= 0) {
            sock.setSoLinger(linger > 0, linger);
        }
    }

    /* access modifiers changed from: protected */
    public InetAddress[] resolveHostname(String host) throws UnknownHostException {
        return this.dnsResolver.resolve(host);
    }
}
