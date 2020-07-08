package p008cz.msebera.android.httpclient.impl.client;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.net.ssl.SSLContext;
import p008cz.msebera.android.httpclient.ConnectionReuseStrategy;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequestInterceptor;
import p008cz.msebera.android.httpclient.HttpResponseInterceptor;
import p008cz.msebera.android.httpclient.auth.AuthSchemeProvider;
import p008cz.msebera.android.httpclient.client.AuthenticationStrategy;
import p008cz.msebera.android.httpclient.client.BackoffManager;
import p008cz.msebera.android.httpclient.client.ConnectionBackoffStrategy;
import p008cz.msebera.android.httpclient.client.CookieStore;
import p008cz.msebera.android.httpclient.client.CredentialsProvider;
import p008cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import p008cz.msebera.android.httpclient.client.RedirectStrategy;
import p008cz.msebera.android.httpclient.client.ServiceUnavailableRetryStrategy;
import p008cz.msebera.android.httpclient.client.UserTokenHandler;
import p008cz.msebera.android.httpclient.client.config.RequestConfig;
import p008cz.msebera.android.httpclient.config.ConnectionConfig;
import p008cz.msebera.android.httpclient.config.Lookup;
import p008cz.msebera.android.httpclient.config.SocketConfig;
import p008cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy;
import p008cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import p008cz.msebera.android.httpclient.conn.SchemePortResolver;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner;
import p008cz.msebera.android.httpclient.conn.socket.LayeredConnectionSocketFactory;
import p008cz.msebera.android.httpclient.conn.ssl.X509HostnameVerifier;
import p008cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import p008cz.msebera.android.httpclient.impl.execchain.ClientExecChain;
import p008cz.msebera.android.httpclient.protocol.HttpProcessor;
import p008cz.msebera.android.httpclient.protocol.HttpRequestExecutor;
import p008cz.msebera.android.httpclient.util.TextUtils;
import p008cz.msebera.android.httpclient.util.VersionInfo;

/* renamed from: cz.msebera.android.httpclient.impl.client.HttpClientBuilder */
public class HttpClientBuilder {
    static final String DEFAULT_USER_AGENT;
    private boolean authCachingDisabled;
    private Lookup<AuthSchemeProvider> authSchemeRegistry;
    private boolean automaticRetriesDisabled;
    private BackoffManager backoffManager;
    private List<Closeable> closeables;
    private HttpClientConnectionManager connManager;
    private ConnectionBackoffStrategy connectionBackoffStrategy;
    private boolean connectionStateDisabled;
    private boolean contentCompressionDisabled;
    private boolean cookieManagementDisabled;
    private Lookup<CookieSpecProvider> cookieSpecRegistry;
    private CookieStore cookieStore;
    private CredentialsProvider credentialsProvider;
    private ConnectionConfig defaultConnectionConfig;
    private Collection<? extends Header> defaultHeaders;
    private RequestConfig defaultRequestConfig;
    private SocketConfig defaultSocketConfig;
    private X509HostnameVerifier hostnameVerifier;
    private HttpProcessor httpprocessor;
    private ConnectionKeepAliveStrategy keepAliveStrategy;
    private int maxConnPerRoute = 0;
    private int maxConnTotal = 0;
    private HttpHost proxy;
    private AuthenticationStrategy proxyAuthStrategy;
    private boolean redirectHandlingDisabled;
    private RedirectStrategy redirectStrategy;
    private HttpRequestExecutor requestExec;
    private LinkedList<HttpRequestInterceptor> requestFirst;
    private LinkedList<HttpRequestInterceptor> requestLast;
    private LinkedList<HttpResponseInterceptor> responseFirst;
    private LinkedList<HttpResponseInterceptor> responseLast;
    private HttpRequestRetryHandler retryHandler;
    private ConnectionReuseStrategy reuseStrategy;
    private HttpRoutePlanner routePlanner;
    private SchemePortResolver schemePortResolver;
    private ServiceUnavailableRetryStrategy serviceUnavailStrategy;
    private LayeredConnectionSocketFactory sslSocketFactory;
    private SSLContext sslcontext;
    private boolean systemProperties;
    private AuthenticationStrategy targetAuthStrategy;
    private String userAgent;
    private UserTokenHandler userTokenHandler;

    static {
        VersionInfo vi = VersionInfo.loadVersionInfo("cz.msebera.android.httpclient.client", HttpClientBuilder.class.getClassLoader());
        String release = vi != null ? vi.getRelease() : VersionInfo.UNAVAILABLE;
        StringBuilder sb = new StringBuilder();
        sb.append("Apache-HttpClient/");
        sb.append(release);
        sb.append(" (java 1.5)");
        DEFAULT_USER_AGENT = sb.toString();
    }

    public static HttpClientBuilder create() {
        return new HttpClientBuilder();
    }

    protected HttpClientBuilder() {
    }

    public final HttpClientBuilder setRequestExecutor(HttpRequestExecutor requestExec2) {
        this.requestExec = requestExec2;
        return this;
    }

    public final HttpClientBuilder setHostnameVerifier(X509HostnameVerifier hostnameVerifier2) {
        this.hostnameVerifier = hostnameVerifier2;
        return this;
    }

    public final HttpClientBuilder setSslcontext(SSLContext sslcontext2) {
        this.sslcontext = sslcontext2;
        return this;
    }

    public final HttpClientBuilder setSSLSocketFactory(LayeredConnectionSocketFactory sslSocketFactory2) {
        this.sslSocketFactory = sslSocketFactory2;
        return this;
    }

    public final HttpClientBuilder setMaxConnTotal(int maxConnTotal2) {
        this.maxConnTotal = maxConnTotal2;
        return this;
    }

    public final HttpClientBuilder setMaxConnPerRoute(int maxConnPerRoute2) {
        this.maxConnPerRoute = maxConnPerRoute2;
        return this;
    }

    public final HttpClientBuilder setDefaultSocketConfig(SocketConfig config) {
        this.defaultSocketConfig = config;
        return this;
    }

    public final HttpClientBuilder setDefaultConnectionConfig(ConnectionConfig config) {
        this.defaultConnectionConfig = config;
        return this;
    }

    public final HttpClientBuilder setConnectionManager(HttpClientConnectionManager connManager2) {
        this.connManager = connManager2;
        return this;
    }

    public final HttpClientBuilder setConnectionReuseStrategy(ConnectionReuseStrategy reuseStrategy2) {
        this.reuseStrategy = reuseStrategy2;
        return this;
    }

    public final HttpClientBuilder setKeepAliveStrategy(ConnectionKeepAliveStrategy keepAliveStrategy2) {
        this.keepAliveStrategy = keepAliveStrategy2;
        return this;
    }

    public final HttpClientBuilder setTargetAuthenticationStrategy(AuthenticationStrategy targetAuthStrategy2) {
        this.targetAuthStrategy = targetAuthStrategy2;
        return this;
    }

    public final HttpClientBuilder setProxyAuthenticationStrategy(AuthenticationStrategy proxyAuthStrategy2) {
        this.proxyAuthStrategy = proxyAuthStrategy2;
        return this;
    }

    public final HttpClientBuilder setUserTokenHandler(UserTokenHandler userTokenHandler2) {
        this.userTokenHandler = userTokenHandler2;
        return this;
    }

    public final HttpClientBuilder disableConnectionState() {
        this.connectionStateDisabled = true;
        return this;
    }

    public final HttpClientBuilder setSchemePortResolver(SchemePortResolver schemePortResolver2) {
        this.schemePortResolver = schemePortResolver2;
        return this;
    }

    public final HttpClientBuilder setUserAgent(String userAgent2) {
        this.userAgent = userAgent2;
        return this;
    }

    public final HttpClientBuilder setDefaultHeaders(Collection<? extends Header> defaultHeaders2) {
        this.defaultHeaders = defaultHeaders2;
        return this;
    }

    public final HttpClientBuilder addInterceptorFirst(HttpResponseInterceptor itcp) {
        if (itcp == null) {
            return this;
        }
        if (this.responseFirst == null) {
            this.responseFirst = new LinkedList<>();
        }
        this.responseFirst.addFirst(itcp);
        return this;
    }

    public final HttpClientBuilder addInterceptorLast(HttpResponseInterceptor itcp) {
        if (itcp == null) {
            return this;
        }
        if (this.responseLast == null) {
            this.responseLast = new LinkedList<>();
        }
        this.responseLast.addLast(itcp);
        return this;
    }

    public final HttpClientBuilder addInterceptorFirst(HttpRequestInterceptor itcp) {
        if (itcp == null) {
            return this;
        }
        if (this.requestFirst == null) {
            this.requestFirst = new LinkedList<>();
        }
        this.requestFirst.addFirst(itcp);
        return this;
    }

    public final HttpClientBuilder addInterceptorLast(HttpRequestInterceptor itcp) {
        if (itcp == null) {
            return this;
        }
        if (this.requestLast == null) {
            this.requestLast = new LinkedList<>();
        }
        this.requestLast.addLast(itcp);
        return this;
    }

    public final HttpClientBuilder disableCookieManagement() {
        this.cookieManagementDisabled = true;
        return this;
    }

    public final HttpClientBuilder disableContentCompression() {
        this.contentCompressionDisabled = true;
        return this;
    }

    public final HttpClientBuilder disableAuthCaching() {
        this.authCachingDisabled = true;
        return this;
    }

    public final HttpClientBuilder setHttpProcessor(HttpProcessor httpprocessor2) {
        this.httpprocessor = httpprocessor2;
        return this;
    }

    public final HttpClientBuilder setRetryHandler(HttpRequestRetryHandler retryHandler2) {
        this.retryHandler = retryHandler2;
        return this;
    }

    public final HttpClientBuilder disableAutomaticRetries() {
        this.automaticRetriesDisabled = true;
        return this;
    }

    public final HttpClientBuilder setProxy(HttpHost proxy2) {
        this.proxy = proxy2;
        return this;
    }

    public final HttpClientBuilder setRoutePlanner(HttpRoutePlanner routePlanner2) {
        this.routePlanner = routePlanner2;
        return this;
    }

    public final HttpClientBuilder setRedirectStrategy(RedirectStrategy redirectStrategy2) {
        this.redirectStrategy = redirectStrategy2;
        return this;
    }

    public final HttpClientBuilder disableRedirectHandling() {
        this.redirectHandlingDisabled = true;
        return this;
    }

    public final HttpClientBuilder setConnectionBackoffStrategy(ConnectionBackoffStrategy connectionBackoffStrategy2) {
        this.connectionBackoffStrategy = connectionBackoffStrategy2;
        return this;
    }

    public final HttpClientBuilder setBackoffManager(BackoffManager backoffManager2) {
        this.backoffManager = backoffManager2;
        return this;
    }

    public final HttpClientBuilder setServiceUnavailableRetryStrategy(ServiceUnavailableRetryStrategy serviceUnavailStrategy2) {
        this.serviceUnavailStrategy = serviceUnavailStrategy2;
        return this;
    }

    public final HttpClientBuilder setDefaultCookieStore(CookieStore cookieStore2) {
        this.cookieStore = cookieStore2;
        return this;
    }

    public final HttpClientBuilder setDefaultCredentialsProvider(CredentialsProvider credentialsProvider2) {
        this.credentialsProvider = credentialsProvider2;
        return this;
    }

    public final HttpClientBuilder setDefaultAuthSchemeRegistry(Lookup<AuthSchemeProvider> authSchemeRegistry2) {
        this.authSchemeRegistry = authSchemeRegistry2;
        return this;
    }

    public final HttpClientBuilder setDefaultCookieSpecRegistry(Lookup<CookieSpecProvider> cookieSpecRegistry2) {
        this.cookieSpecRegistry = cookieSpecRegistry2;
        return this;
    }

    public final HttpClientBuilder setDefaultRequestConfig(RequestConfig config) {
        this.defaultRequestConfig = config;
        return this;
    }

    public final HttpClientBuilder useSystemProperties() {
        this.systemProperties = true;
        return this;
    }

    /* access modifiers changed from: protected */
    public ClientExecChain decorateMainExec(ClientExecChain mainExec) {
        return mainExec;
    }

    /* access modifiers changed from: protected */
    public ClientExecChain decorateProtocolExec(ClientExecChain protocolExec) {
        return protocolExec;
    }

    /* access modifiers changed from: protected */
    public void addCloseable(Closeable closeable) {
        if (closeable != null) {
            if (this.closeables == null) {
                this.closeables = new ArrayList();
            }
            this.closeables.add(closeable);
        }
    }

    private static String[] split(String s) {
        if (TextUtils.isBlank(s)) {
            return null;
        }
        return s.split(" *, *");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [cz.msebera.android.httpclient.conn.HttpClientConnectionManager] */
    /* JADX WARNING: type inference failed for: r21v0 */
    /* JADX WARNING: type inference failed for: r4v1, types: [cz.msebera.android.httpclient.conn.HttpClientConnectionManager] */
    /* JADX WARNING: type inference failed for: r13v0, types: [cz.msebera.android.httpclient.conn.HttpClientConnectionManager] */
    /* JADX WARNING: type inference failed for: r21v1 */
    /* JADX WARNING: type inference failed for: r2v27 */
    /* JADX WARNING: type inference failed for: r21v2 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 5 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p008cz.msebera.android.httpclient.impl.client.CloseableHttpClient build() {
        /*
            r30 = this;
            r0 = r30
            cz.msebera.android.httpclient.protocol.HttpRequestExecutor r1 = r0.requestExec
            if (r1 != 0) goto L_0x000c
            cz.msebera.android.httpclient.protocol.HttpRequestExecutor r2 = new cz.msebera.android.httpclient.protocol.HttpRequestExecutor
            r2.<init>()
            r1 = r2
        L_0x000c:
            cz.msebera.android.httpclient.conn.HttpClientConnectionManager r2 = r0.connManager
            java.lang.String r3 = "http.keepAlive"
            r10 = 0
            java.lang.String r4 = "true"
            if (r2 != 0) goto L_0x00c4
            cz.msebera.android.httpclient.conn.socket.LayeredConnectionSocketFactory r5 = r0.sslSocketFactory
            if (r5 != 0) goto L_0x0065
            boolean r6 = r0.systemProperties
            if (r6 == 0) goto L_0x0028
            java.lang.String r6 = "https.protocols"
            java.lang.String r6 = java.lang.System.getProperty(r6)
            java.lang.String[] r6 = split(r6)
            goto L_0x0029
        L_0x0028:
            r6 = r10
        L_0x0029:
            boolean r7 = r0.systemProperties
            if (r7 == 0) goto L_0x0038
            java.lang.String r7 = "https.cipherSuites"
            java.lang.String r7 = java.lang.System.getProperty(r7)
            java.lang.String[] r7 = split(r7)
            goto L_0x0039
        L_0x0038:
            r7 = r10
        L_0x0039:
            cz.msebera.android.httpclient.conn.ssl.X509HostnameVerifier r8 = r0.hostnameVerifier
            if (r8 != 0) goto L_0x003f
            cz.msebera.android.httpclient.conn.ssl.X509HostnameVerifier r8 = p008cz.msebera.android.httpclient.conn.ssl.SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER
        L_0x003f:
            javax.net.ssl.SSLContext r9 = r0.sslcontext
            if (r9 == 0) goto L_0x004a
            cz.msebera.android.httpclient.conn.ssl.SSLConnectionSocketFactory r11 = new cz.msebera.android.httpclient.conn.ssl.SSLConnectionSocketFactory
            r11.<init>(r9, r6, r7, r8)
            r5 = r11
            goto L_0x0065
        L_0x004a:
            boolean r9 = r0.systemProperties
            if (r9 == 0) goto L_0x005b
            cz.msebera.android.httpclient.conn.ssl.SSLConnectionSocketFactory r9 = new cz.msebera.android.httpclient.conn.ssl.SSLConnectionSocketFactory
            javax.net.SocketFactory r11 = javax.net.ssl.SSLSocketFactory.getDefault()
            javax.net.ssl.SSLSocketFactory r11 = (javax.net.ssl.SSLSocketFactory) r11
            r9.<init>(r11, r6, r7, r8)
            r5 = r9
            goto L_0x0065
        L_0x005b:
            cz.msebera.android.httpclient.conn.ssl.SSLConnectionSocketFactory r9 = new cz.msebera.android.httpclient.conn.ssl.SSLConnectionSocketFactory
            javax.net.ssl.SSLContext r11 = p008cz.msebera.android.httpclient.conn.ssl.SSLContexts.createDefault()
            r9.<init>(r11, r8)
            r5 = r9
        L_0x0065:
            cz.msebera.android.httpclient.impl.conn.PoolingHttpClientConnectionManager r6 = new cz.msebera.android.httpclient.impl.conn.PoolingHttpClientConnectionManager
            cz.msebera.android.httpclient.config.RegistryBuilder r7 = p008cz.msebera.android.httpclient.config.RegistryBuilder.create()
            cz.msebera.android.httpclient.conn.socket.PlainConnectionSocketFactory r8 = p008cz.msebera.android.httpclient.conn.socket.PlainConnectionSocketFactory.getSocketFactory()
            java.lang.String r9 = "http"
            cz.msebera.android.httpclient.config.RegistryBuilder r7 = r7.register(r9, r8)
            java.lang.String r8 = "https"
            cz.msebera.android.httpclient.config.RegistryBuilder r7 = r7.register(r8, r5)
            cz.msebera.android.httpclient.config.Registry r7 = r7.build()
            r6.<init>(r7)
            cz.msebera.android.httpclient.config.SocketConfig r7 = r0.defaultSocketConfig
            if (r7 == 0) goto L_0x0089
            r6.setDefaultSocketConfig(r7)
        L_0x0089:
            cz.msebera.android.httpclient.config.ConnectionConfig r7 = r0.defaultConnectionConfig
            if (r7 == 0) goto L_0x0090
            r6.setDefaultConnectionConfig(r7)
        L_0x0090:
            boolean r7 = r0.systemProperties
            if (r7 == 0) goto L_0x00b2
            java.lang.String r7 = java.lang.System.getProperty(r3, r4)
            boolean r8 = r4.equalsIgnoreCase(r7)
            if (r8 == 0) goto L_0x00b2
            java.lang.String r8 = "http.maxConnections"
            java.lang.String r9 = "5"
            java.lang.String r7 = java.lang.System.getProperty(r8, r9)
            int r8 = java.lang.Integer.parseInt(r7)
            r6.setDefaultMaxPerRoute(r8)
            int r9 = r8 * 2
            r6.setMaxTotal(r9)
        L_0x00b2:
            int r7 = r0.maxConnTotal
            if (r7 <= 0) goto L_0x00b9
            r6.setMaxTotal(r7)
        L_0x00b9:
            int r7 = r0.maxConnPerRoute
            if (r7 <= 0) goto L_0x00c0
            r6.setDefaultMaxPerRoute(r7)
        L_0x00c0:
            r2 = r6
            r21 = r2
            goto L_0x00c6
        L_0x00c4:
            r21 = r2
        L_0x00c6:
            cz.msebera.android.httpclient.ConnectionReuseStrategy r2 = r0.reuseStrategy
            if (r2 != 0) goto L_0x00e5
            boolean r5 = r0.systemProperties
            if (r5 == 0) goto L_0x00e0
            java.lang.String r3 = java.lang.System.getProperty(r3, r4)
            boolean r4 = r4.equalsIgnoreCase(r3)
            if (r4 == 0) goto L_0x00db
            cz.msebera.android.httpclient.impl.DefaultConnectionReuseStrategy r2 = p008cz.msebera.android.httpclient.impl.DefaultConnectionReuseStrategy.INSTANCE
            goto L_0x00dd
        L_0x00db:
            cz.msebera.android.httpclient.impl.NoConnectionReuseStrategy r2 = p008cz.msebera.android.httpclient.impl.NoConnectionReuseStrategy.INSTANCE
        L_0x00dd:
            r22 = r2
            goto L_0x00e7
        L_0x00e0:
            cz.msebera.android.httpclient.impl.DefaultConnectionReuseStrategy r2 = p008cz.msebera.android.httpclient.impl.DefaultConnectionReuseStrategy.INSTANCE
            r22 = r2
            goto L_0x00e7
        L_0x00e5:
            r22 = r2
        L_0x00e7:
            cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy r2 = r0.keepAliveStrategy
            if (r2 != 0) goto L_0x00f0
            cz.msebera.android.httpclient.impl.client.DefaultConnectionKeepAliveStrategy r2 = p008cz.msebera.android.httpclient.impl.client.DefaultConnectionKeepAliveStrategy.INSTANCE
            r23 = r2
            goto L_0x00f2
        L_0x00f0:
            r23 = r2
        L_0x00f2:
            cz.msebera.android.httpclient.client.AuthenticationStrategy r2 = r0.targetAuthStrategy
            if (r2 != 0) goto L_0x00fb
            cz.msebera.android.httpclient.impl.client.TargetAuthenticationStrategy r2 = p008cz.msebera.android.httpclient.impl.client.TargetAuthenticationStrategy.INSTANCE
            r24 = r2
            goto L_0x00fd
        L_0x00fb:
            r24 = r2
        L_0x00fd:
            cz.msebera.android.httpclient.client.AuthenticationStrategy r2 = r0.proxyAuthStrategy
            if (r2 != 0) goto L_0x0106
            cz.msebera.android.httpclient.impl.client.ProxyAuthenticationStrategy r2 = p008cz.msebera.android.httpclient.impl.client.ProxyAuthenticationStrategy.INSTANCE
            r25 = r2
            goto L_0x0108
        L_0x0106:
            r25 = r2
        L_0x0108:
            cz.msebera.android.httpclient.client.UserTokenHandler r2 = r0.userTokenHandler
            if (r2 != 0) goto L_0x011a
            boolean r3 = r0.connectionStateDisabled
            if (r3 != 0) goto L_0x0115
            cz.msebera.android.httpclient.impl.client.DefaultUserTokenHandler r2 = p008cz.msebera.android.httpclient.impl.client.DefaultUserTokenHandler.INSTANCE
            r26 = r2
            goto L_0x011c
        L_0x0115:
            cz.msebera.android.httpclient.impl.client.NoopUserTokenHandler r2 = p008cz.msebera.android.httpclient.impl.client.NoopUserTokenHandler.INSTANCE
            r26 = r2
            goto L_0x011c
        L_0x011a:
            r26 = r2
        L_0x011c:
            cz.msebera.android.httpclient.impl.execchain.MainClientExec r11 = new cz.msebera.android.httpclient.impl.execchain.MainClientExec
            r2 = r11
            r3 = r1
            r4 = r21
            r5 = r22
            r6 = r23
            r7 = r24
            r8 = r25
            r9 = r26
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            cz.msebera.android.httpclient.impl.execchain.ClientExecChain r2 = r0.decorateMainExec(r2)
            cz.msebera.android.httpclient.protocol.HttpProcessor r3 = r0.httpprocessor
            if (r3 != 0) goto L_0x0225
            java.lang.String r4 = r0.userAgent
            if (r4 != 0) goto L_0x0149
            boolean r5 = r0.systemProperties
            if (r5 == 0) goto L_0x0145
            java.lang.String r5 = "http.agent"
            java.lang.String r4 = java.lang.System.getProperty(r5)
        L_0x0145:
            if (r4 != 0) goto L_0x0149
            java.lang.String r4 = DEFAULT_USER_AGENT
        L_0x0149:
            cz.msebera.android.httpclient.protocol.HttpProcessorBuilder r5 = p008cz.msebera.android.httpclient.protocol.HttpProcessorBuilder.create()
            java.util.LinkedList<cz.msebera.android.httpclient.HttpRequestInterceptor> r6 = r0.requestFirst
            if (r6 == 0) goto L_0x0165
            java.util.Iterator r6 = r6.iterator()
        L_0x0155:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0165
            java.lang.Object r7 = r6.next()
            cz.msebera.android.httpclient.HttpRequestInterceptor r7 = (p008cz.msebera.android.httpclient.HttpRequestInterceptor) r7
            r5.addFirst(r7)
            goto L_0x0155
        L_0x0165:
            java.util.LinkedList<cz.msebera.android.httpclient.HttpResponseInterceptor> r6 = r0.responseFirst
            if (r6 == 0) goto L_0x017d
            java.util.Iterator r6 = r6.iterator()
        L_0x016d:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x017d
            java.lang.Object r7 = r6.next()
            cz.msebera.android.httpclient.HttpResponseInterceptor r7 = (p008cz.msebera.android.httpclient.HttpResponseInterceptor) r7
            r5.addFirst(r7)
            goto L_0x016d
        L_0x017d:
            r6 = 6
            cz.msebera.android.httpclient.HttpRequestInterceptor[] r6 = new p008cz.msebera.android.httpclient.HttpRequestInterceptor[r6]
            r7 = 0
            cz.msebera.android.httpclient.client.protocol.RequestDefaultHeaders r8 = new cz.msebera.android.httpclient.client.protocol.RequestDefaultHeaders
            java.util.Collection<? extends cz.msebera.android.httpclient.Header> r9 = r0.defaultHeaders
            r8.<init>(r9)
            r6[r7] = r8
            r7 = 1
            cz.msebera.android.httpclient.protocol.RequestContent r8 = new cz.msebera.android.httpclient.protocol.RequestContent
            r8.<init>()
            r6[r7] = r8
            cz.msebera.android.httpclient.protocol.RequestTargetHost r7 = new cz.msebera.android.httpclient.protocol.RequestTargetHost
            r7.<init>()
            r8 = 2
            r6[r8] = r7
            r7 = 3
            cz.msebera.android.httpclient.client.protocol.RequestClientConnControl r8 = new cz.msebera.android.httpclient.client.protocol.RequestClientConnControl
            r8.<init>()
            r6[r7] = r8
            r7 = 4
            cz.msebera.android.httpclient.protocol.RequestUserAgent r8 = new cz.msebera.android.httpclient.protocol.RequestUserAgent
            r8.<init>(r4)
            r6[r7] = r8
            r7 = 5
            cz.msebera.android.httpclient.client.protocol.RequestExpectContinue r8 = new cz.msebera.android.httpclient.client.protocol.RequestExpectContinue
            r8.<init>()
            r6[r7] = r8
            r5.addAll(r6)
            boolean r6 = r0.cookieManagementDisabled
            if (r6 != 0) goto L_0x01c1
            cz.msebera.android.httpclient.client.protocol.RequestAddCookies r6 = new cz.msebera.android.httpclient.client.protocol.RequestAddCookies
            r6.<init>()
            r5.add(r6)
        L_0x01c1:
            boolean r6 = r0.contentCompressionDisabled
            if (r6 != 0) goto L_0x01cd
            cz.msebera.android.httpclient.client.protocol.RequestAcceptEncoding r6 = new cz.msebera.android.httpclient.client.protocol.RequestAcceptEncoding
            r6.<init>()
            r5.add(r6)
        L_0x01cd:
            boolean r6 = r0.authCachingDisabled
            if (r6 != 0) goto L_0x01d9
            cz.msebera.android.httpclient.client.protocol.RequestAuthCache r6 = new cz.msebera.android.httpclient.client.protocol.RequestAuthCache
            r6.<init>()
            r5.add(r6)
        L_0x01d9:
            boolean r6 = r0.cookieManagementDisabled
            if (r6 != 0) goto L_0x01e5
            cz.msebera.android.httpclient.client.protocol.ResponseProcessCookies r6 = new cz.msebera.android.httpclient.client.protocol.ResponseProcessCookies
            r6.<init>()
            r5.add(r6)
        L_0x01e5:
            boolean r6 = r0.contentCompressionDisabled
            if (r6 != 0) goto L_0x01f1
            cz.msebera.android.httpclient.client.protocol.ResponseContentEncoding r6 = new cz.msebera.android.httpclient.client.protocol.ResponseContentEncoding
            r6.<init>()
            r5.add(r6)
        L_0x01f1:
            java.util.LinkedList<cz.msebera.android.httpclient.HttpRequestInterceptor> r6 = r0.requestLast
            if (r6 == 0) goto L_0x0209
            java.util.Iterator r6 = r6.iterator()
        L_0x01f9:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0209
            java.lang.Object r7 = r6.next()
            cz.msebera.android.httpclient.HttpRequestInterceptor r7 = (p008cz.msebera.android.httpclient.HttpRequestInterceptor) r7
            r5.addLast(r7)
            goto L_0x01f9
        L_0x0209:
            java.util.LinkedList<cz.msebera.android.httpclient.HttpResponseInterceptor> r6 = r0.responseLast
            if (r6 == 0) goto L_0x0221
            java.util.Iterator r6 = r6.iterator()
        L_0x0211:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0221
            java.lang.Object r7 = r6.next()
            cz.msebera.android.httpclient.HttpResponseInterceptor r7 = (p008cz.msebera.android.httpclient.HttpResponseInterceptor) r7
            r5.addLast(r7)
            goto L_0x0211
        L_0x0221:
            cz.msebera.android.httpclient.protocol.HttpProcessor r3 = r5.build()
        L_0x0225:
            cz.msebera.android.httpclient.impl.execchain.ProtocolExec r4 = new cz.msebera.android.httpclient.impl.execchain.ProtocolExec
            r4.<init>(r2, r3)
            r2 = r4
            cz.msebera.android.httpclient.impl.execchain.ClientExecChain r2 = r0.decorateProtocolExec(r2)
            boolean r4 = r0.automaticRetriesDisabled
            if (r4 != 0) goto L_0x023f
            cz.msebera.android.httpclient.client.HttpRequestRetryHandler r4 = r0.retryHandler
            if (r4 != 0) goto L_0x0239
            cz.msebera.android.httpclient.impl.client.DefaultHttpRequestRetryHandler r4 = p008cz.msebera.android.httpclient.impl.client.DefaultHttpRequestRetryHandler.INSTANCE
        L_0x0239:
            cz.msebera.android.httpclient.impl.execchain.RetryExec r5 = new cz.msebera.android.httpclient.impl.execchain.RetryExec
            r5.<init>(r2, r4)
            r2 = r5
        L_0x023f:
            cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner r4 = r0.routePlanner
            if (r4 != 0) goto L_0x0269
            cz.msebera.android.httpclient.conn.SchemePortResolver r5 = r0.schemePortResolver
            if (r5 != 0) goto L_0x0249
            cz.msebera.android.httpclient.impl.conn.DefaultSchemePortResolver r5 = p008cz.msebera.android.httpclient.impl.conn.DefaultSchemePortResolver.INSTANCE
        L_0x0249:
            cz.msebera.android.httpclient.HttpHost r6 = r0.proxy
            if (r6 == 0) goto L_0x0254
            cz.msebera.android.httpclient.impl.conn.DefaultProxyRoutePlanner r7 = new cz.msebera.android.httpclient.impl.conn.DefaultProxyRoutePlanner
            r7.<init>(r6, r5)
            r4 = r7
            goto L_0x0269
        L_0x0254:
            boolean r6 = r0.systemProperties
            if (r6 == 0) goto L_0x0263
            cz.msebera.android.httpclient.impl.conn.SystemDefaultRoutePlanner r6 = new cz.msebera.android.httpclient.impl.conn.SystemDefaultRoutePlanner
            java.net.ProxySelector r7 = java.net.ProxySelector.getDefault()
            r6.<init>(r5, r7)
            r4 = r6
            goto L_0x0269
        L_0x0263:
            cz.msebera.android.httpclient.impl.conn.DefaultRoutePlanner r6 = new cz.msebera.android.httpclient.impl.conn.DefaultRoutePlanner
            r6.<init>(r5)
            r4 = r6
        L_0x0269:
            boolean r5 = r0.redirectHandlingDisabled
            if (r5 != 0) goto L_0x0279
            cz.msebera.android.httpclient.client.RedirectStrategy r5 = r0.redirectStrategy
            if (r5 != 0) goto L_0x0273
            cz.msebera.android.httpclient.impl.client.DefaultRedirectStrategy r5 = p008cz.msebera.android.httpclient.impl.client.DefaultRedirectStrategy.INSTANCE
        L_0x0273:
            cz.msebera.android.httpclient.impl.execchain.RedirectExec r6 = new cz.msebera.android.httpclient.impl.execchain.RedirectExec
            r6.<init>(r2, r4, r5)
            r2 = r6
        L_0x0279:
            cz.msebera.android.httpclient.client.ServiceUnavailableRetryStrategy r5 = r0.serviceUnavailStrategy
            if (r5 == 0) goto L_0x0283
            cz.msebera.android.httpclient.impl.execchain.ServiceUnavailableRetryExec r6 = new cz.msebera.android.httpclient.impl.execchain.ServiceUnavailableRetryExec
            r6.<init>(r2, r5)
            r2 = r6
        L_0x0283:
            cz.msebera.android.httpclient.client.BackoffManager r6 = r0.backoffManager
            cz.msebera.android.httpclient.client.ConnectionBackoffStrategy r7 = r0.connectionBackoffStrategy
            if (r6 == 0) goto L_0x0291
            if (r7 == 0) goto L_0x0291
            cz.msebera.android.httpclient.impl.execchain.BackoffStrategyExec r8 = new cz.msebera.android.httpclient.impl.execchain.BackoffStrategyExec
            r8.<init>(r2, r7, r6)
            r2 = r8
        L_0x0291:
            cz.msebera.android.httpclient.config.Lookup<cz.msebera.android.httpclient.auth.AuthSchemeProvider> r8 = r0.authSchemeRegistry
            if (r8 != 0) goto L_0x02be
            cz.msebera.android.httpclient.config.RegistryBuilder r9 = p008cz.msebera.android.httpclient.config.RegistryBuilder.create()
            cz.msebera.android.httpclient.impl.auth.BasicSchemeFactory r11 = new cz.msebera.android.httpclient.impl.auth.BasicSchemeFactory
            r11.<init>()
            java.lang.String r12 = "Basic"
            cz.msebera.android.httpclient.config.RegistryBuilder r9 = r9.register(r12, r11)
            cz.msebera.android.httpclient.impl.auth.DigestSchemeFactory r11 = new cz.msebera.android.httpclient.impl.auth.DigestSchemeFactory
            r11.<init>()
            java.lang.String r12 = "Digest"
            cz.msebera.android.httpclient.config.RegistryBuilder r9 = r9.register(r12, r11)
            cz.msebera.android.httpclient.impl.auth.NTLMSchemeFactory r11 = new cz.msebera.android.httpclient.impl.auth.NTLMSchemeFactory
            r11.<init>()
            java.lang.String r12 = "NTLM"
            cz.msebera.android.httpclient.config.RegistryBuilder r9 = r9.register(r12, r11)
            cz.msebera.android.httpclient.config.Registry r8 = r9.build()
        L_0x02be:
            cz.msebera.android.httpclient.config.Lookup<cz.msebera.android.httpclient.cookie.CookieSpecProvider> r9 = r0.cookieSpecRegistry
            if (r9 != 0) goto L_0x0317
            cz.msebera.android.httpclient.config.RegistryBuilder r11 = p008cz.msebera.android.httpclient.config.RegistryBuilder.create()
            cz.msebera.android.httpclient.impl.cookie.BestMatchSpecFactory r12 = new cz.msebera.android.httpclient.impl.cookie.BestMatchSpecFactory
            r12.<init>()
            java.lang.String r13 = "best-match"
            cz.msebera.android.httpclient.config.RegistryBuilder r11 = r11.register(r13, r12)
            cz.msebera.android.httpclient.impl.cookie.RFC2965SpecFactory r12 = new cz.msebera.android.httpclient.impl.cookie.RFC2965SpecFactory
            r12.<init>()
            java.lang.String r13 = "standard"
            cz.msebera.android.httpclient.config.RegistryBuilder r11 = r11.register(r13, r12)
            cz.msebera.android.httpclient.impl.cookie.BrowserCompatSpecFactory r12 = new cz.msebera.android.httpclient.impl.cookie.BrowserCompatSpecFactory
            r12.<init>()
            java.lang.String r13 = "compatibility"
            cz.msebera.android.httpclient.config.RegistryBuilder r11 = r11.register(r13, r12)
            cz.msebera.android.httpclient.impl.cookie.NetscapeDraftSpecFactory r12 = new cz.msebera.android.httpclient.impl.cookie.NetscapeDraftSpecFactory
            r12.<init>()
            java.lang.String r13 = "netscape"
            cz.msebera.android.httpclient.config.RegistryBuilder r11 = r11.register(r13, r12)
            cz.msebera.android.httpclient.impl.cookie.IgnoreSpecFactory r12 = new cz.msebera.android.httpclient.impl.cookie.IgnoreSpecFactory
            r12.<init>()
            java.lang.String r13 = "ignoreCookies"
            cz.msebera.android.httpclient.config.RegistryBuilder r11 = r11.register(r13, r12)
            cz.msebera.android.httpclient.impl.cookie.RFC2109SpecFactory r12 = new cz.msebera.android.httpclient.impl.cookie.RFC2109SpecFactory
            r12.<init>()
            java.lang.String r13 = "rfc2109"
            cz.msebera.android.httpclient.config.RegistryBuilder r11 = r11.register(r13, r12)
            cz.msebera.android.httpclient.impl.cookie.RFC2965SpecFactory r12 = new cz.msebera.android.httpclient.impl.cookie.RFC2965SpecFactory
            r12.<init>()
            java.lang.String r13 = "rfc2965"
            cz.msebera.android.httpclient.config.RegistryBuilder r11 = r11.register(r13, r12)
            cz.msebera.android.httpclient.config.Registry r9 = r11.build()
        L_0x0317:
            cz.msebera.android.httpclient.client.CookieStore r11 = r0.cookieStore
            if (r11 != 0) goto L_0x0324
            cz.msebera.android.httpclient.impl.client.BasicCookieStore r12 = new cz.msebera.android.httpclient.impl.client.BasicCookieStore
            r12.<init>()
            r11 = r12
            r27 = r11
            goto L_0x0326
        L_0x0324:
            r27 = r11
        L_0x0326:
            cz.msebera.android.httpclient.client.CredentialsProvider r11 = r0.credentialsProvider
            if (r11 != 0) goto L_0x0340
            boolean r12 = r0.systemProperties
            if (r12 == 0) goto L_0x0337
            cz.msebera.android.httpclient.impl.client.SystemDefaultCredentialsProvider r12 = new cz.msebera.android.httpclient.impl.client.SystemDefaultCredentialsProvider
            r12.<init>()
            r11 = r12
            r28 = r11
            goto L_0x0342
        L_0x0337:
            cz.msebera.android.httpclient.impl.client.BasicCredentialsProvider r12 = new cz.msebera.android.httpclient.impl.client.BasicCredentialsProvider
            r12.<init>()
            r11 = r12
            r28 = r11
            goto L_0x0342
        L_0x0340:
            r28 = r11
        L_0x0342:
            cz.msebera.android.httpclient.impl.client.InternalHttpClient r29 = new cz.msebera.android.httpclient.impl.client.InternalHttpClient
            cz.msebera.android.httpclient.client.config.RequestConfig r11 = r0.defaultRequestConfig
            if (r11 == 0) goto L_0x0349
            goto L_0x034b
        L_0x0349:
            cz.msebera.android.httpclient.client.config.RequestConfig r11 = p008cz.msebera.android.httpclient.client.config.RequestConfig.DEFAULT
        L_0x034b:
            r19 = r11
            java.util.List<java.io.Closeable> r11 = r0.closeables
            if (r11 == 0) goto L_0x0356
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>(r11)
        L_0x0356:
            r20 = r10
            r11 = r29
            r12 = r2
            r13 = r21
            r14 = r4
            r15 = r9
            r16 = r8
            r17 = r27
            r18 = r28
            r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return r29
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.client.HttpClientBuilder.build():cz.msebera.android.httpclient.impl.client.CloseableHttpClient");
    }
}
