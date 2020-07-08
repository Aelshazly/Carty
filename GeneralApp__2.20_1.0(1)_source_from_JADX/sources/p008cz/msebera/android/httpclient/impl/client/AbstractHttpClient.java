package p008cz.msebera.android.httpclient.impl.client;

import p008cz.msebera.android.httpclient.ConnectionReuseStrategy;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpRequestInterceptor;
import p008cz.msebera.android.httpclient.HttpResponseInterceptor;
import p008cz.msebera.android.httpclient.auth.AuthSchemeRegistry;
import p008cz.msebera.android.httpclient.client.AuthenticationHandler;
import p008cz.msebera.android.httpclient.client.AuthenticationStrategy;
import p008cz.msebera.android.httpclient.client.BackoffManager;
import p008cz.msebera.android.httpclient.client.ConnectionBackoffStrategy;
import p008cz.msebera.android.httpclient.client.CookieStore;
import p008cz.msebera.android.httpclient.client.CredentialsProvider;
import p008cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import p008cz.msebera.android.httpclient.client.RedirectHandler;
import p008cz.msebera.android.httpclient.client.RedirectStrategy;
import p008cz.msebera.android.httpclient.client.RequestDirector;
import p008cz.msebera.android.httpclient.client.UserTokenHandler;
import p008cz.msebera.android.httpclient.client.params.ClientPNames;
import p008cz.msebera.android.httpclient.client.params.CookiePolicy;
import p008cz.msebera.android.httpclient.client.protocol.ClientContext;
import p008cz.msebera.android.httpclient.conn.ClientConnectionManager;
import p008cz.msebera.android.httpclient.conn.ClientConnectionManagerFactory;
import p008cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner;
import p008cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import p008cz.msebera.android.httpclient.cookie.CookieSpecRegistry;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.impl.DefaultConnectionReuseStrategy;
import p008cz.msebera.android.httpclient.impl.auth.BasicSchemeFactory;
import p008cz.msebera.android.httpclient.impl.auth.DigestSchemeFactory;
import p008cz.msebera.android.httpclient.impl.auth.NTLMSchemeFactory;
import p008cz.msebera.android.httpclient.impl.conn.BasicClientConnectionManager;
import p008cz.msebera.android.httpclient.impl.conn.DefaultHttpRoutePlanner;
import p008cz.msebera.android.httpclient.impl.conn.SchemeRegistryFactory;
import p008cz.msebera.android.httpclient.impl.cookie.BestMatchSpecFactory;
import p008cz.msebera.android.httpclient.impl.cookie.BrowserCompatSpecFactory;
import p008cz.msebera.android.httpclient.impl.cookie.IgnoreSpecFactory;
import p008cz.msebera.android.httpclient.impl.cookie.NetscapeDraftSpecFactory;
import p008cz.msebera.android.httpclient.impl.cookie.RFC2109SpecFactory;
import p008cz.msebera.android.httpclient.impl.cookie.RFC2965SpecFactory;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.BasicHttpContext;
import p008cz.msebera.android.httpclient.protocol.BasicHttpProcessor;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.protocol.HttpProcessor;
import p008cz.msebera.android.httpclient.protocol.HttpRequestExecutor;
import p008cz.msebera.android.httpclient.protocol.ImmutableHttpProcessor;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.client.AbstractHttpClient */
public abstract class AbstractHttpClient extends CloseableHttpClient {
    private BackoffManager backoffManager;
    private ClientConnectionManager connManager;
    private ConnectionBackoffStrategy connectionBackoffStrategy;
    private CookieStore cookieStore;
    private CredentialsProvider credsProvider;
    private HttpParams defaultParams;
    private ConnectionKeepAliveStrategy keepAliveStrategy;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private BasicHttpProcessor mutableProcessor;
    private ImmutableHttpProcessor protocolProcessor;
    private AuthenticationStrategy proxyAuthStrategy;
    private RedirectStrategy redirectStrategy;
    private HttpRequestExecutor requestExec;
    private HttpRequestRetryHandler retryHandler;
    private ConnectionReuseStrategy reuseStrategy;
    private HttpRoutePlanner routePlanner;
    private AuthSchemeRegistry supportedAuthSchemes;
    private CookieSpecRegistry supportedCookieSpecs;
    private AuthenticationStrategy targetAuthStrategy;
    private UserTokenHandler userTokenHandler;

    /* access modifiers changed from: protected */
    public abstract HttpParams createHttpParams();

    /* access modifiers changed from: protected */
    public abstract BasicHttpProcessor createHttpProcessor();

    protected AbstractHttpClient(ClientConnectionManager conman, HttpParams params) {
        this.defaultParams = params;
        this.connManager = conman;
    }

    /* access modifiers changed from: protected */
    public HttpContext createHttpContext() {
        HttpContext context = new BasicHttpContext();
        context.setAttribute(ClientContext.SCHEME_REGISTRY, getConnectionManager().getSchemeRegistry());
        context.setAttribute("http.authscheme-registry", getAuthSchemes());
        context.setAttribute("http.cookiespec-registry", getCookieSpecs());
        context.setAttribute("http.cookie-store", getCookieStore());
        context.setAttribute("http.auth.credentials-provider", getCredentialsProvider());
        return context;
    }

    /* access modifiers changed from: protected */
    public ClientConnectionManager createClientConnectionManager() {
        SchemeRegistry registry = SchemeRegistryFactory.createDefault();
        HttpParams params = getParams();
        ClientConnectionManagerFactory factory = null;
        String className = (String) params.getParameter(ClientPNames.CONNECTION_MANAGER_FACTORY_CLASS_NAME);
        if (className != null) {
            try {
                factory = (ClientConnectionManagerFactory) Class.forName(className).newInstance();
            } catch (ClassNotFoundException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid class name: ");
                sb.append(className);
                throw new IllegalStateException(sb.toString());
            } catch (IllegalAccessException ex) {
                throw new IllegalAccessError(ex.getMessage());
            } catch (InstantiationException ex2) {
                throw new InstantiationError(ex2.getMessage());
            }
        }
        if (factory != null) {
            return factory.newInstance(params, registry);
        }
        return new BasicClientConnectionManager(registry);
    }

    /* access modifiers changed from: protected */
    public AuthSchemeRegistry createAuthSchemeRegistry() {
        AuthSchemeRegistry registry = new AuthSchemeRegistry();
        registry.register("Basic", new BasicSchemeFactory());
        registry.register("Digest", new DigestSchemeFactory());
        registry.register("NTLM", new NTLMSchemeFactory());
        return registry;
    }

    /* access modifiers changed from: protected */
    public CookieSpecRegistry createCookieSpecRegistry() {
        CookieSpecRegistry registry = new CookieSpecRegistry();
        registry.register("best-match", new BestMatchSpecFactory());
        registry.register("compatibility", new BrowserCompatSpecFactory());
        registry.register("netscape", new NetscapeDraftSpecFactory());
        registry.register(CookiePolicy.RFC_2109, new RFC2109SpecFactory());
        registry.register(CookiePolicy.RFC_2965, new RFC2965SpecFactory());
        registry.register("ignoreCookies", new IgnoreSpecFactory());
        return registry;
    }

    /* access modifiers changed from: protected */
    public HttpRequestExecutor createRequestExecutor() {
        return new HttpRequestExecutor();
    }

    /* access modifiers changed from: protected */
    public ConnectionReuseStrategy createConnectionReuseStrategy() {
        return new DefaultConnectionReuseStrategy();
    }

    /* access modifiers changed from: protected */
    public ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy() {
        return new DefaultConnectionKeepAliveStrategy();
    }

    /* access modifiers changed from: protected */
    public HttpRequestRetryHandler createHttpRequestRetryHandler() {
        return new DefaultHttpRequestRetryHandler();
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public RedirectHandler createRedirectHandler() {
        return new DefaultRedirectHandler();
    }

    /* access modifiers changed from: protected */
    public AuthenticationStrategy createTargetAuthenticationStrategy() {
        return new TargetAuthenticationStrategy();
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public AuthenticationHandler createTargetAuthenticationHandler() {
        return new DefaultTargetAuthenticationHandler();
    }

    /* access modifiers changed from: protected */
    public AuthenticationStrategy createProxyAuthenticationStrategy() {
        return new ProxyAuthenticationStrategy();
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public AuthenticationHandler createProxyAuthenticationHandler() {
        return new DefaultProxyAuthenticationHandler();
    }

    /* access modifiers changed from: protected */
    public CookieStore createCookieStore() {
        return new BasicCookieStore();
    }

    /* access modifiers changed from: protected */
    public CredentialsProvider createCredentialsProvider() {
        return new BasicCredentialsProvider();
    }

    /* access modifiers changed from: protected */
    public HttpRoutePlanner createHttpRoutePlanner() {
        return new DefaultHttpRoutePlanner(getConnectionManager().getSchemeRegistry());
    }

    /* access modifiers changed from: protected */
    public UserTokenHandler createUserTokenHandler() {
        return new DefaultUserTokenHandler();
    }

    public final synchronized HttpParams getParams() {
        if (this.defaultParams == null) {
            this.defaultParams = createHttpParams();
        }
        return this.defaultParams;
    }

    public synchronized void setParams(HttpParams params) {
        this.defaultParams = params;
    }

    public final synchronized ClientConnectionManager getConnectionManager() {
        if (this.connManager == null) {
            this.connManager = createClientConnectionManager();
        }
        return this.connManager;
    }

    public final synchronized HttpRequestExecutor getRequestExecutor() {
        if (this.requestExec == null) {
            this.requestExec = createRequestExecutor();
        }
        return this.requestExec;
    }

    public final synchronized AuthSchemeRegistry getAuthSchemes() {
        if (this.supportedAuthSchemes == null) {
            this.supportedAuthSchemes = createAuthSchemeRegistry();
        }
        return this.supportedAuthSchemes;
    }

    public synchronized void setAuthSchemes(AuthSchemeRegistry registry) {
        this.supportedAuthSchemes = registry;
    }

    public final synchronized ConnectionBackoffStrategy getConnectionBackoffStrategy() {
        return this.connectionBackoffStrategy;
    }

    public synchronized void setConnectionBackoffStrategy(ConnectionBackoffStrategy strategy) {
        this.connectionBackoffStrategy = strategy;
    }

    public final synchronized CookieSpecRegistry getCookieSpecs() {
        if (this.supportedCookieSpecs == null) {
            this.supportedCookieSpecs = createCookieSpecRegistry();
        }
        return this.supportedCookieSpecs;
    }

    public final synchronized BackoffManager getBackoffManager() {
        return this.backoffManager;
    }

    public synchronized void setBackoffManager(BackoffManager manager) {
        this.backoffManager = manager;
    }

    public synchronized void setCookieSpecs(CookieSpecRegistry registry) {
        this.supportedCookieSpecs = registry;
    }

    public final synchronized ConnectionReuseStrategy getConnectionReuseStrategy() {
        if (this.reuseStrategy == null) {
            this.reuseStrategy = createConnectionReuseStrategy();
        }
        return this.reuseStrategy;
    }

    public synchronized void setReuseStrategy(ConnectionReuseStrategy strategy) {
        this.reuseStrategy = strategy;
    }

    public final synchronized ConnectionKeepAliveStrategy getConnectionKeepAliveStrategy() {
        if (this.keepAliveStrategy == null) {
            this.keepAliveStrategy = createConnectionKeepAliveStrategy();
        }
        return this.keepAliveStrategy;
    }

    public synchronized void setKeepAliveStrategy(ConnectionKeepAliveStrategy strategy) {
        this.keepAliveStrategy = strategy;
    }

    public final synchronized HttpRequestRetryHandler getHttpRequestRetryHandler() {
        if (this.retryHandler == null) {
            this.retryHandler = createHttpRequestRetryHandler();
        }
        return this.retryHandler;
    }

    public synchronized void setHttpRequestRetryHandler(HttpRequestRetryHandler handler) {
        this.retryHandler = handler;
    }

    @Deprecated
    public final synchronized RedirectHandler getRedirectHandler() {
        return createRedirectHandler();
    }

    @Deprecated
    public synchronized void setRedirectHandler(RedirectHandler handler) {
        this.redirectStrategy = new DefaultRedirectStrategyAdaptor(handler);
    }

    public final synchronized RedirectStrategy getRedirectStrategy() {
        if (this.redirectStrategy == null) {
            this.redirectStrategy = new DefaultRedirectStrategy();
        }
        return this.redirectStrategy;
    }

    public synchronized void setRedirectStrategy(RedirectStrategy strategy) {
        this.redirectStrategy = strategy;
    }

    @Deprecated
    public final synchronized AuthenticationHandler getTargetAuthenticationHandler() {
        return createTargetAuthenticationHandler();
    }

    @Deprecated
    public synchronized void setTargetAuthenticationHandler(AuthenticationHandler handler) {
        this.targetAuthStrategy = new AuthenticationStrategyAdaptor(handler);
    }

    public final synchronized AuthenticationStrategy getTargetAuthenticationStrategy() {
        if (this.targetAuthStrategy == null) {
            this.targetAuthStrategy = createTargetAuthenticationStrategy();
        }
        return this.targetAuthStrategy;
    }

    public synchronized void setTargetAuthenticationStrategy(AuthenticationStrategy strategy) {
        this.targetAuthStrategy = strategy;
    }

    @Deprecated
    public final synchronized AuthenticationHandler getProxyAuthenticationHandler() {
        return createProxyAuthenticationHandler();
    }

    @Deprecated
    public synchronized void setProxyAuthenticationHandler(AuthenticationHandler handler) {
        this.proxyAuthStrategy = new AuthenticationStrategyAdaptor(handler);
    }

    public final synchronized AuthenticationStrategy getProxyAuthenticationStrategy() {
        if (this.proxyAuthStrategy == null) {
            this.proxyAuthStrategy = createProxyAuthenticationStrategy();
        }
        return this.proxyAuthStrategy;
    }

    public synchronized void setProxyAuthenticationStrategy(AuthenticationStrategy strategy) {
        this.proxyAuthStrategy = strategy;
    }

    public final synchronized CookieStore getCookieStore() {
        if (this.cookieStore == null) {
            this.cookieStore = createCookieStore();
        }
        return this.cookieStore;
    }

    public synchronized void setCookieStore(CookieStore cookieStore2) {
        this.cookieStore = cookieStore2;
    }

    public final synchronized CredentialsProvider getCredentialsProvider() {
        if (this.credsProvider == null) {
            this.credsProvider = createCredentialsProvider();
        }
        return this.credsProvider;
    }

    public synchronized void setCredentialsProvider(CredentialsProvider credsProvider2) {
        this.credsProvider = credsProvider2;
    }

    public final synchronized HttpRoutePlanner getRoutePlanner() {
        if (this.routePlanner == null) {
            this.routePlanner = createHttpRoutePlanner();
        }
        return this.routePlanner;
    }

    public synchronized void setRoutePlanner(HttpRoutePlanner routePlanner2) {
        this.routePlanner = routePlanner2;
    }

    public final synchronized UserTokenHandler getUserTokenHandler() {
        if (this.userTokenHandler == null) {
            this.userTokenHandler = createUserTokenHandler();
        }
        return this.userTokenHandler;
    }

    public synchronized void setUserTokenHandler(UserTokenHandler handler) {
        this.userTokenHandler = handler;
    }

    /* access modifiers changed from: protected */
    public final synchronized BasicHttpProcessor getHttpProcessor() {
        if (this.mutableProcessor == null) {
            this.mutableProcessor = createHttpProcessor();
        }
        return this.mutableProcessor;
    }

    private synchronized HttpProcessor getProtocolProcessor() {
        if (this.protocolProcessor == null) {
            BasicHttpProcessor proc = getHttpProcessor();
            int reqc = proc.getRequestInterceptorCount();
            HttpRequestInterceptor[] reqinterceptors = new HttpRequestInterceptor[reqc];
            for (int i = 0; i < reqc; i++) {
                reqinterceptors[i] = proc.getRequestInterceptor(i);
            }
            int resc = proc.getResponseInterceptorCount();
            HttpResponseInterceptor[] resinterceptors = new HttpResponseInterceptor[resc];
            for (int i2 = 0; i2 < resc; i2++) {
                resinterceptors[i2] = proc.getResponseInterceptor(i2);
            }
            this.protocolProcessor = new ImmutableHttpProcessor(reqinterceptors, resinterceptors);
        }
        return this.protocolProcessor;
    }

    public synchronized int getResponseInterceptorCount() {
        return getHttpProcessor().getResponseInterceptorCount();
    }

    public synchronized HttpResponseInterceptor getResponseInterceptor(int index) {
        return getHttpProcessor().getResponseInterceptor(index);
    }

    public synchronized HttpRequestInterceptor getRequestInterceptor(int index) {
        return getHttpProcessor().getRequestInterceptor(index);
    }

    public synchronized int getRequestInterceptorCount() {
        return getHttpProcessor().getRequestInterceptorCount();
    }

    public synchronized void addResponseInterceptor(HttpResponseInterceptor itcp) {
        getHttpProcessor().addInterceptor(itcp);
        this.protocolProcessor = null;
    }

    public synchronized void addResponseInterceptor(HttpResponseInterceptor itcp, int index) {
        getHttpProcessor().addInterceptor(itcp, index);
        this.protocolProcessor = null;
    }

    public synchronized void clearResponseInterceptors() {
        getHttpProcessor().clearResponseInterceptors();
        this.protocolProcessor = null;
    }

    public synchronized void removeResponseInterceptorByClass(Class<? extends HttpResponseInterceptor> clazz) {
        getHttpProcessor().removeResponseInterceptorByClass(clazz);
        this.protocolProcessor = null;
    }

    public synchronized void addRequestInterceptor(HttpRequestInterceptor itcp) {
        getHttpProcessor().addInterceptor(itcp);
        this.protocolProcessor = null;
    }

    public synchronized void addRequestInterceptor(HttpRequestInterceptor itcp, int index) {
        getHttpProcessor().addInterceptor(itcp, index);
        this.protocolProcessor = null;
    }

    public synchronized void clearRequestInterceptors() {
        getHttpProcessor().clearRequestInterceptors();
        this.protocolProcessor = null;
    }

    public synchronized void removeRequestInterceptorByClass(Class<? extends HttpRequestInterceptor> clazz) {
        getHttpProcessor().removeRequestInterceptorByClass(clazz);
        this.protocolProcessor = null;
    }

    /* JADX INFO: used method not loaded: cz.msebera.android.httpclient.client.ConnectionBackoffStrategy.shouldBackoff(cz.msebera.android.httpclient.HttpResponse):null, types can be incorrect */
    /* JADX INFO: used method not loaded: cz.msebera.android.httpclient.client.ConnectionBackoffStrategy.shouldBackoff(java.lang.Throwable):null, types can be incorrect */
    /* JADX INFO: used method not loaded: cz.msebera.android.httpclient.client.ClientProtocolException.<init>(java.lang.Throwable):null, types can be incorrect */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0081, code lost:
        if (r3 == null) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0083, code lost:
        if (r4 == null) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0085, code lost:
        if (r15 == null) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0087, code lost:
        r5 = r28;
        r0 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x008b, code lost:
        r5 = r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r0 = (p008cz.msebera.android.httpclient.HttpHost) determineParams(r5).getParameter(p008cz.msebera.android.httpclient.client.params.ClientPNames.DEFAULT_HOST);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0099, code lost:
        r7 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a0, code lost:
        r8 = r2.determineRoute(r0, r5, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r0 = p008cz.msebera.android.httpclient.impl.client.CloseableHttpResponseProxy.newProxy(r1.execute(r15, r5, r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00af, code lost:
        if (r3.shouldBackoff((p008cz.msebera.android.httpclient.HttpResponse) r0) == false) goto L_0x00b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00b1, code lost:
        r4.backOff(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b5, code lost:
        r4.probe(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b8, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b9, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00be, code lost:
        if (r3.shouldBackoff((java.lang.Throwable) r0) != false) goto L_0x00c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00c0, code lost:
        r4.backOff(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c5, code lost:
        if ((r0 instanceof p008cz.msebera.android.httpclient.HttpException) == false) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c9, code lost:
        if ((r0 instanceof java.io.IOException) != false) goto L_0x00cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ce, code lost:
        throw ((java.io.IOException) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00d4, code lost:
        throw new java.lang.reflect.UndeclaredThrowableException(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00d8, code lost:
        throw ((p008cz.msebera.android.httpclient.HttpException) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00d9, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00de, code lost:
        if (r3.shouldBackoff((java.lang.Throwable) r0) != false) goto L_0x00e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e0, code lost:
        r4.backOff(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00e4, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00e5, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00e6, code lost:
        r7 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00f6, code lost:
        return p008cz.msebera.android.httpclient.impl.client.CloseableHttpResponseProxy.newProxy(r1.execute(r15, r28, r25));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00f7, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00fd, code lost:
        throw new p008cz.msebera.android.httpclient.client.ClientProtocolException((java.lang.Throwable) r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p008cz.msebera.android.httpclient.client.methods.CloseableHttpResponse doExecute(p008cz.msebera.android.httpclient.HttpHost r27, p008cz.msebera.android.httpclient.HttpRequest r28, p008cz.msebera.android.httpclient.protocol.HttpContext r29) throws java.io.IOException, p008cz.msebera.android.httpclient.client.ClientProtocolException {
        /*
            r26 = this;
            r14 = r26
            r15 = r27
            r12 = r28
            r11 = r29
            java.lang.String r0 = "HTTP request"
            p008cz.msebera.android.httpclient.util.Args.notNull(r12, r0)
            r1 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            monitor-enter(r26)
            cz.msebera.android.httpclient.protocol.HttpContext r0 = r26.createHttpContext()     // Catch:{ all -> 0x013a }
            if (r11 != 0) goto L_0x0020
            r1 = r0
            r10 = r1
            goto L_0x0027
        L_0x0020:
            cz.msebera.android.httpclient.protocol.DefaultedHttpContext r2 = new cz.msebera.android.httpclient.protocol.DefaultedHttpContext     // Catch:{ all -> 0x013a }
            r2.<init>(r11, r0)     // Catch:{ all -> 0x013a }
            r1 = r2
            r10 = r1
        L_0x0027:
            cz.msebera.android.httpclient.params.HttpParams r13 = r14.determineParams(r12)     // Catch:{ all -> 0x0135 }
            cz.msebera.android.httpclient.client.config.RequestConfig r1 = p008cz.msebera.android.httpclient.client.params.HttpClientParamConfig.getRequestConfig(r13)     // Catch:{ all -> 0x0135 }
            r9 = r1
            java.lang.String r1 = "http.request-config"
            r10.setAttribute(r1, r9)     // Catch:{ all -> 0x0135 }
            cz.msebera.android.httpclient.protocol.HttpRequestExecutor r2 = r26.getRequestExecutor()     // Catch:{ all -> 0x0135 }
            cz.msebera.android.httpclient.conn.ClientConnectionManager r3 = r26.getConnectionManager()     // Catch:{ all -> 0x0135 }
            cz.msebera.android.httpclient.ConnectionReuseStrategy r4 = r26.getConnectionReuseStrategy()     // Catch:{ all -> 0x0135 }
            cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy r5 = r26.getConnectionKeepAliveStrategy()     // Catch:{ all -> 0x0135 }
            cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner r6 = r26.getRoutePlanner()     // Catch:{ all -> 0x0135 }
            cz.msebera.android.httpclient.protocol.HttpProcessor r7 = r26.getProtocolProcessor()     // Catch:{ all -> 0x0135 }
            cz.msebera.android.httpclient.client.HttpRequestRetryHandler r8 = r26.getHttpRequestRetryHandler()     // Catch:{ all -> 0x0135 }
            cz.msebera.android.httpclient.client.RedirectStrategy r20 = r26.getRedirectStrategy()     // Catch:{ all -> 0x0135 }
            cz.msebera.android.httpclient.client.AuthenticationStrategy r21 = r26.getTargetAuthenticationStrategy()     // Catch:{ all -> 0x0135 }
            cz.msebera.android.httpclient.client.AuthenticationStrategy r22 = r26.getProxyAuthenticationStrategy()     // Catch:{ all -> 0x0135 }
            cz.msebera.android.httpclient.client.UserTokenHandler r23 = r26.getUserTokenHandler()     // Catch:{ all -> 0x0135 }
            r1 = r26
            r24 = r9
            r9 = r20
            r25 = r10
            r10 = r21
            r11 = r22
            r12 = r23
            cz.msebera.android.httpclient.client.RequestDirector r1 = r1.createClientRequestDirector(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ all -> 0x012e }
            cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner r2 = r26.getRoutePlanner()     // Catch:{ all -> 0x0125 }
            cz.msebera.android.httpclient.client.ConnectionBackoffStrategy r3 = r26.getConnectionBackoffStrategy()     // Catch:{ all -> 0x011a }
            cz.msebera.android.httpclient.client.BackoffManager r4 = r26.getBackoffManager()     // Catch:{ all -> 0x010d }
            monitor-exit(r26)     // Catch:{ all -> 0x00fe }
            if (r3 == 0) goto L_0x00e9
            if (r4 == 0) goto L_0x00e9
            if (r15 == 0) goto L_0x008b
            r5 = r28
            r0 = r15
            goto L_0x0099
        L_0x008b:
            r5 = r28
            cz.msebera.android.httpclient.params.HttpParams r0 = r14.determineParams(r5)     // Catch:{ HttpException -> 0x00e5 }
            java.lang.String r6 = "http.default-host"
            java.lang.Object r0 = r0.getParameter(r6)     // Catch:{ HttpException -> 0x00e5 }
            cz.msebera.android.httpclient.HttpHost r0 = (p008cz.msebera.android.httpclient.HttpHost) r0     // Catch:{ HttpException -> 0x00e5 }
        L_0x0099:
            r6 = r0
            r7 = r25
            cz.msebera.android.httpclient.conn.routing.HttpRoute r0 = r2.determineRoute(r6, r5, r7)     // Catch:{ HttpException -> 0x00f7 }
            r8 = r0
            cz.msebera.android.httpclient.HttpResponse r0 = r1.execute(r15, r5, r7)     // Catch:{ RuntimeException -> 0x00d9, Exception -> 0x00b9 }
            cz.msebera.android.httpclient.client.methods.CloseableHttpResponse r0 = p008cz.msebera.android.httpclient.impl.client.CloseableHttpResponseProxy.newProxy(r0)     // Catch:{ RuntimeException -> 0x00d9, Exception -> 0x00b9 }
            boolean r9 = r3.shouldBackoff(r0)     // Catch:{ HttpException -> 0x00f7 }
            if (r9 == 0) goto L_0x00b5
            r4.backOff(r8)     // Catch:{ HttpException -> 0x00f7 }
            goto L_0x00b8
        L_0x00b5:
            r4.probe(r8)     // Catch:{ HttpException -> 0x00f7 }
        L_0x00b8:
            return r0
        L_0x00b9:
            r0 = move-exception
            boolean r9 = r3.shouldBackoff(r0)     // Catch:{ HttpException -> 0x00f7 }
            if (r9 == 0) goto L_0x00c3
            r4.backOff(r8)     // Catch:{ HttpException -> 0x00f7 }
        L_0x00c3:
            boolean r9 = r0 instanceof p008cz.msebera.android.httpclient.HttpException     // Catch:{ HttpException -> 0x00f7 }
            if (r9 != 0) goto L_0x00d5
            boolean r9 = r0 instanceof java.io.IOException     // Catch:{ HttpException -> 0x00f7 }
            if (r9 == 0) goto L_0x00cf
            r9 = r0
            java.io.IOException r9 = (java.io.IOException) r9     // Catch:{ HttpException -> 0x00f7 }
            throw r9     // Catch:{ HttpException -> 0x00f7 }
        L_0x00cf:
            java.lang.reflect.UndeclaredThrowableException r9 = new java.lang.reflect.UndeclaredThrowableException     // Catch:{ HttpException -> 0x00f7 }
            r9.<init>(r0)     // Catch:{ HttpException -> 0x00f7 }
            throw r9     // Catch:{ HttpException -> 0x00f7 }
        L_0x00d5:
            r9 = r0
            cz.msebera.android.httpclient.HttpException r9 = (p008cz.msebera.android.httpclient.HttpException) r9     // Catch:{ HttpException -> 0x00f7 }
            throw r9     // Catch:{ HttpException -> 0x00f7 }
        L_0x00d9:
            r0 = move-exception
            boolean r9 = r3.shouldBackoff(r0)     // Catch:{ HttpException -> 0x00f7 }
            if (r9 == 0) goto L_0x00e3
            r4.backOff(r8)     // Catch:{ HttpException -> 0x00f7 }
        L_0x00e3:
            throw r0     // Catch:{ HttpException -> 0x00f7 }
        L_0x00e5:
            r0 = move-exception
            r7 = r25
            goto L_0x00f8
        L_0x00e9:
            r5 = r28
            r7 = r25
            cz.msebera.android.httpclient.HttpResponse r0 = r1.execute(r15, r5, r7)     // Catch:{ HttpException -> 0x00f7 }
            cz.msebera.android.httpclient.client.methods.CloseableHttpResponse r0 = p008cz.msebera.android.httpclient.impl.client.CloseableHttpResponseProxy.newProxy(r0)     // Catch:{ HttpException -> 0x00f7 }
            return r0
        L_0x00f7:
            r0 = move-exception
        L_0x00f8:
            cz.msebera.android.httpclient.client.ClientProtocolException r6 = new cz.msebera.android.httpclient.client.ClientProtocolException
            r6.<init>(r0)
            throw r6
        L_0x00fe:
            r0 = move-exception
            r5 = r28
            r7 = r25
            r16 = r1
            r17 = r2
            r18 = r3
            r19 = r4
            r1 = r7
            goto L_0x013c
        L_0x010d:
            r0 = move-exception
            r5 = r28
            r7 = r25
            r16 = r1
            r17 = r2
            r18 = r3
            r1 = r7
            goto L_0x013c
        L_0x011a:
            r0 = move-exception
            r5 = r28
            r7 = r25
            r16 = r1
            r17 = r2
            r1 = r7
            goto L_0x013c
        L_0x0125:
            r0 = move-exception
            r5 = r28
            r7 = r25
            r16 = r1
            r1 = r7
            goto L_0x013c
        L_0x012e:
            r0 = move-exception
            r5 = r28
            r7 = r25
            r1 = r7
            goto L_0x013c
        L_0x0135:
            r0 = move-exception
            r7 = r10
            r5 = r12
            r1 = r7
            goto L_0x013c
        L_0x013a:
            r0 = move-exception
            r5 = r12
        L_0x013c:
            monitor-exit(r26)     // Catch:{ all -> 0x013e }
            throw r0
        L_0x013e:
            r0 = move-exception
            goto L_0x013c
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.client.AbstractHttpClient.doExecute(cz.msebera.android.httpclient.HttpHost, cz.msebera.android.httpclient.HttpRequest, cz.msebera.android.httpclient.protocol.HttpContext):cz.msebera.android.httpclient.client.methods.CloseableHttpResponse");
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public RequestDirector createClientRequestDirector(HttpRequestExecutor requestExec2, ClientConnectionManager conman, ConnectionReuseStrategy reustrat, ConnectionKeepAliveStrategy kastrat, HttpRoutePlanner rouplan, HttpProcessor httpProcessor, HttpRequestRetryHandler retryHandler2, RedirectHandler redirectHandler, AuthenticationHandler targetAuthHandler, AuthenticationHandler proxyAuthHandler, UserTokenHandler userTokenHandler2, HttpParams params) {
        DefaultRequestDirector defaultRequestDirector = new DefaultRequestDirector(requestExec2, conman, reustrat, kastrat, rouplan, httpProcessor, retryHandler2, redirectHandler, targetAuthHandler, proxyAuthHandler, userTokenHandler2, params);
        return defaultRequestDirector;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public RequestDirector createClientRequestDirector(HttpRequestExecutor requestExec2, ClientConnectionManager conman, ConnectionReuseStrategy reustrat, ConnectionKeepAliveStrategy kastrat, HttpRoutePlanner rouplan, HttpProcessor httpProcessor, HttpRequestRetryHandler retryHandler2, RedirectStrategy redirectStrategy2, AuthenticationHandler targetAuthHandler, AuthenticationHandler proxyAuthHandler, UserTokenHandler userTokenHandler2, HttpParams params) {
        DefaultRequestDirector defaultRequestDirector = new DefaultRequestDirector(this.log, requestExec2, conman, reustrat, kastrat, rouplan, httpProcessor, retryHandler2, redirectStrategy2, targetAuthHandler, proxyAuthHandler, userTokenHandler2, params);
        return defaultRequestDirector;
    }

    /* access modifiers changed from: protected */
    public RequestDirector createClientRequestDirector(HttpRequestExecutor requestExec2, ClientConnectionManager conman, ConnectionReuseStrategy reustrat, ConnectionKeepAliveStrategy kastrat, HttpRoutePlanner rouplan, HttpProcessor httpProcessor, HttpRequestRetryHandler retryHandler2, RedirectStrategy redirectStrategy2, AuthenticationStrategy targetAuthStrategy2, AuthenticationStrategy proxyAuthStrategy2, UserTokenHandler userTokenHandler2, HttpParams params) {
        DefaultRequestDirector defaultRequestDirector = new DefaultRequestDirector(this.log, requestExec2, conman, reustrat, kastrat, rouplan, httpProcessor, retryHandler2, redirectStrategy2, targetAuthStrategy2, proxyAuthStrategy2, userTokenHandler2, params);
        return defaultRequestDirector;
    }

    /* access modifiers changed from: protected */
    public HttpParams determineParams(HttpRequest req) {
        return new ClientParamsStack(null, getParams(), req.getParams(), null);
    }

    public void close() {
        getConnectionManager().shutdown();
    }
}
