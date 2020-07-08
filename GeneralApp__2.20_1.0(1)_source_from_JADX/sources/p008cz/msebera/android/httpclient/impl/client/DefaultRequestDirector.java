package p008cz.msebera.android.httpclient.impl.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import p008cz.msebera.android.httpclient.ConnectionReuseStrategy;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.NoHttpResponseException;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.auth.AuthScheme;
import p008cz.msebera.android.httpclient.auth.AuthState;
import p008cz.msebera.android.httpclient.client.AuthenticationHandler;
import p008cz.msebera.android.httpclient.client.AuthenticationStrategy;
import p008cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import p008cz.msebera.android.httpclient.client.NonRepeatableRequestException;
import p008cz.msebera.android.httpclient.client.RedirectException;
import p008cz.msebera.android.httpclient.client.RedirectHandler;
import p008cz.msebera.android.httpclient.client.RedirectStrategy;
import p008cz.msebera.android.httpclient.client.RequestDirector;
import p008cz.msebera.android.httpclient.client.UserTokenHandler;
import p008cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import p008cz.msebera.android.httpclient.client.params.ClientPNames;
import p008cz.msebera.android.httpclient.client.params.HttpClientParams;
import p008cz.msebera.android.httpclient.client.utils.URIUtils;
import p008cz.msebera.android.httpclient.conn.ClientConnectionManager;
import p008cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy;
import p008cz.msebera.android.httpclient.conn.ManagedClientConnection;
import p008cz.msebera.android.httpclient.conn.routing.BasicRouteDirector;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.conn.routing.HttpRouteDirector;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner;
import p008cz.msebera.android.httpclient.entity.BufferedHttpEntity;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.message.BasicHttpRequest;
import p008cz.msebera.android.httpclient.params.HttpConnectionParams;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.params.HttpProtocolParams;
import p008cz.msebera.android.httpclient.protocol.ExecutionContext;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.protocol.HttpProcessor;
import p008cz.msebera.android.httpclient.protocol.HttpRequestExecutor;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.EntityUtils;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.client.DefaultRequestDirector */
public class DefaultRequestDirector implements RequestDirector {
    private final HttpAuthenticator authenticator;
    protected final ClientConnectionManager connManager;
    private int execCount;
    protected final HttpProcessor httpProcessor;
    protected final ConnectionKeepAliveStrategy keepAliveStrategy;
    public HttpClientAndroidLog log;
    protected ManagedClientConnection managedConn;
    private final int maxRedirects;
    protected final HttpParams params;
    @Deprecated
    protected final AuthenticationHandler proxyAuthHandler;
    protected final AuthState proxyAuthState;
    protected final AuthenticationStrategy proxyAuthStrategy;
    private int redirectCount;
    @Deprecated
    protected final RedirectHandler redirectHandler;
    protected final RedirectStrategy redirectStrategy;
    protected final HttpRequestExecutor requestExec;
    protected final HttpRequestRetryHandler retryHandler;
    protected final ConnectionReuseStrategy reuseStrategy;
    protected final HttpRoutePlanner routePlanner;
    @Deprecated
    protected final AuthenticationHandler targetAuthHandler;
    protected final AuthState targetAuthState;
    protected final AuthenticationStrategy targetAuthStrategy;
    protected final UserTokenHandler userTokenHandler;
    private HttpHost virtualHost;

    @Deprecated
    public DefaultRequestDirector(HttpRequestExecutor requestExec2, ClientConnectionManager conman, ConnectionReuseStrategy reustrat, ConnectionKeepAliveStrategy kastrat, HttpRoutePlanner rouplan, HttpProcessor httpProcessor2, HttpRequestRetryHandler retryHandler2, RedirectHandler redirectHandler2, AuthenticationHandler targetAuthHandler2, AuthenticationHandler proxyAuthHandler2, UserTokenHandler userTokenHandler2, HttpParams params2) {
        this(new HttpClientAndroidLog(DefaultRequestDirector.class), requestExec2, conman, reustrat, kastrat, rouplan, httpProcessor2, retryHandler2, (RedirectStrategy) new DefaultRedirectStrategyAdaptor(redirectHandler2), (AuthenticationStrategy) new AuthenticationStrategyAdaptor(targetAuthHandler2), (AuthenticationStrategy) new AuthenticationStrategyAdaptor(proxyAuthHandler2), userTokenHandler2, params2);
    }

    @Deprecated
    public DefaultRequestDirector(HttpClientAndroidLog log2, HttpRequestExecutor requestExec2, ClientConnectionManager conman, ConnectionReuseStrategy reustrat, ConnectionKeepAliveStrategy kastrat, HttpRoutePlanner rouplan, HttpProcessor httpProcessor2, HttpRequestRetryHandler retryHandler2, RedirectStrategy redirectStrategy2, AuthenticationHandler targetAuthHandler2, AuthenticationHandler proxyAuthHandler2, UserTokenHandler userTokenHandler2, HttpParams params2) {
        this(new HttpClientAndroidLog(DefaultRequestDirector.class), requestExec2, conman, reustrat, kastrat, rouplan, httpProcessor2, retryHandler2, redirectStrategy2, (AuthenticationStrategy) new AuthenticationStrategyAdaptor(targetAuthHandler2), (AuthenticationStrategy) new AuthenticationStrategyAdaptor(proxyAuthHandler2), userTokenHandler2, params2);
    }

    public DefaultRequestDirector(HttpClientAndroidLog log2, HttpRequestExecutor requestExec2, ClientConnectionManager conman, ConnectionReuseStrategy reustrat, ConnectionKeepAliveStrategy kastrat, HttpRoutePlanner rouplan, HttpProcessor httpProcessor2, HttpRequestRetryHandler retryHandler2, RedirectStrategy redirectStrategy2, AuthenticationStrategy targetAuthStrategy2, AuthenticationStrategy proxyAuthStrategy2, UserTokenHandler userTokenHandler2, HttpParams params2) {
        HttpClientAndroidLog httpClientAndroidLog = log2;
        HttpRequestExecutor httpRequestExecutor = requestExec2;
        ClientConnectionManager clientConnectionManager = conman;
        ConnectionReuseStrategy connectionReuseStrategy = reustrat;
        ConnectionKeepAliveStrategy connectionKeepAliveStrategy = kastrat;
        HttpRoutePlanner httpRoutePlanner = rouplan;
        HttpProcessor httpProcessor3 = httpProcessor2;
        HttpRequestRetryHandler httpRequestRetryHandler = retryHandler2;
        RedirectStrategy redirectStrategy3 = redirectStrategy2;
        AuthenticationStrategy authenticationStrategy = targetAuthStrategy2;
        AuthenticationStrategy authenticationStrategy2 = proxyAuthStrategy2;
        UserTokenHandler userTokenHandler3 = userTokenHandler2;
        HttpParams httpParams = params2;
        Args.notNull(httpClientAndroidLog, "Log");
        Args.notNull(httpRequestExecutor, "Request executor");
        Args.notNull(clientConnectionManager, "Client connection manager");
        Args.notNull(connectionReuseStrategy, "Connection reuse strategy");
        Args.notNull(connectionKeepAliveStrategy, "Connection keep alive strategy");
        Args.notNull(httpRoutePlanner, "Route planner");
        Args.notNull(httpProcessor3, "HTTP protocol processor");
        Args.notNull(httpRequestRetryHandler, "HTTP request retry handler");
        Args.notNull(redirectStrategy3, "Redirect strategy");
        Args.notNull(authenticationStrategy, "Target authentication strategy");
        Args.notNull(authenticationStrategy2, "Proxy authentication strategy");
        Args.notNull(userTokenHandler3, "User token handler");
        Args.notNull(httpParams, "HTTP parameters");
        this.log = httpClientAndroidLog;
        this.authenticator = new HttpAuthenticator(httpClientAndroidLog);
        this.requestExec = httpRequestExecutor;
        this.connManager = clientConnectionManager;
        this.reuseStrategy = connectionReuseStrategy;
        this.keepAliveStrategy = connectionKeepAliveStrategy;
        this.routePlanner = httpRoutePlanner;
        this.httpProcessor = httpProcessor3;
        this.retryHandler = httpRequestRetryHandler;
        this.redirectStrategy = redirectStrategy3;
        this.targetAuthStrategy = authenticationStrategy;
        this.proxyAuthStrategy = authenticationStrategy2;
        this.userTokenHandler = userTokenHandler3;
        this.params = httpParams;
        if (redirectStrategy3 instanceof DefaultRedirectStrategyAdaptor) {
            this.redirectHandler = ((DefaultRedirectStrategyAdaptor) redirectStrategy3).getHandler();
        } else {
            this.redirectHandler = null;
        }
        if (authenticationStrategy instanceof AuthenticationStrategyAdaptor) {
            this.targetAuthHandler = ((AuthenticationStrategyAdaptor) authenticationStrategy).getHandler();
        } else {
            this.targetAuthHandler = null;
        }
        if (authenticationStrategy2 instanceof AuthenticationStrategyAdaptor) {
            this.proxyAuthHandler = ((AuthenticationStrategyAdaptor) authenticationStrategy2).getHandler();
        } else {
            this.proxyAuthHandler = null;
        }
        this.managedConn = null;
        this.execCount = 0;
        this.redirectCount = 0;
        this.targetAuthState = new AuthState();
        this.proxyAuthState = new AuthState();
        this.maxRedirects = this.params.getIntParameter(ClientPNames.MAX_REDIRECTS, 100);
    }

    private RequestWrapper wrapRequest(HttpRequest request) throws ProtocolException {
        if (request instanceof HttpEntityEnclosingRequest) {
            return new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest) request);
        }
        return new RequestWrapper(request);
    }

    /* access modifiers changed from: protected */
    public void rewriteRequestURI(RequestWrapper request, HttpRoute route) throws ProtocolException {
        URI uri;
        try {
            URI uri2 = request.getURI();
            if (route.getProxyHost() == null || route.isTunnelled()) {
                if (uri2.isAbsolute()) {
                    uri = URIUtils.rewriteURI(uri2, null, true);
                } else {
                    uri = URIUtils.rewriteURI(uri2);
                }
            } else if (!uri2.isAbsolute()) {
                uri = URIUtils.rewriteURI(uri2, route.getTargetHost(), true);
            } else {
                uri = URIUtils.rewriteURI(uri2);
            }
            request.setURI(uri);
        } catch (URISyntaxException ex) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid URI: ");
            sb.append(request.getRequestLine().getUri());
            throw new ProtocolException(sb.toString(), ex);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:245:0x04c4, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x04c5, code lost:
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:0x04c7, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x04c8, code lost:
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x04ca, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x04cb, code lost:
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x04cd, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x04ce, code lost:
        r3 = r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p008cz.msebera.android.httpclient.HttpResponse execute(p008cz.msebera.android.httpclient.HttpHost r25, p008cz.msebera.android.httpclient.HttpRequest r26, p008cz.msebera.android.httpclient.protocol.HttpContext r27) throws p008cz.msebera.android.httpclient.HttpException, java.io.IOException {
        /*
            r24 = this;
            r1 = r24
            r2 = r27
            java.lang.String r3 = "http.user-token"
            cz.msebera.android.httpclient.auth.AuthState r4 = r1.targetAuthState
            java.lang.String r5 = "http.auth.target-scope"
            r2.setAttribute(r5, r4)
            cz.msebera.android.httpclient.auth.AuthState r4 = r1.proxyAuthState
            java.lang.String r5 = "http.auth.proxy-scope"
            r2.setAttribute(r5, r4)
            r4 = r25
            r5 = r26
            cz.msebera.android.httpclient.impl.client.RequestWrapper r6 = r1.wrapRequest(r5)
            cz.msebera.android.httpclient.params.HttpParams r7 = r1.params
            r6.setParams(r7)
            cz.msebera.android.httpclient.conn.routing.HttpRoute r7 = r1.determineRoute(r4, r6, r2)
            cz.msebera.android.httpclient.params.HttpParams r8 = r6.getParams()
            java.lang.String r9 = "http.virtual-host"
            java.lang.Object r8 = r8.getParameter(r9)
            cz.msebera.android.httpclient.HttpHost r8 = (p008cz.msebera.android.httpclient.HttpHost) r8
            r1.virtualHost = r8
            cz.msebera.android.httpclient.HttpHost r8 = r1.virtualHost
            if (r8 == 0) goto L_0x005f
            int r8 = r8.getPort()
            r9 = -1
            if (r8 != r9) goto L_0x005f
            if (r4 == 0) goto L_0x0042
            r8 = r4
            goto L_0x0046
        L_0x0042:
            cz.msebera.android.httpclient.HttpHost r8 = r7.getTargetHost()
        L_0x0046:
            int r10 = r8.getPort()
            if (r10 == r9) goto L_0x005f
            cz.msebera.android.httpclient.HttpHost r9 = new cz.msebera.android.httpclient.HttpHost
            cz.msebera.android.httpclient.HttpHost r11 = r1.virtualHost
            java.lang.String r11 = r11.getHostName()
            cz.msebera.android.httpclient.HttpHost r12 = r1.virtualHost
            java.lang.String r12 = r12.getSchemeName()
            r9.<init>(r11, r10, r12)
            r1.virtualHost = r9
        L_0x005f:
            cz.msebera.android.httpclient.impl.client.RoutedRequest r8 = new cz.msebera.android.httpclient.impl.client.RoutedRequest
            r8.<init>(r6, r7)
            r9 = 0
            r10 = 0
            r11 = 0
        L_0x0067:
            if (r10 != 0) goto L_0x0497
            cz.msebera.android.httpclient.impl.client.RequestWrapper r12 = r8.getRequest()     // Catch:{ ConnectionShutdownException -> 0x048a, HttpException -> 0x047c, IOException -> 0x046e, RuntimeException -> 0x0460 }
            cz.msebera.android.httpclient.conn.routing.HttpRoute r13 = r8.getRoute()     // Catch:{ ConnectionShutdownException -> 0x048a, HttpException -> 0x047c, IOException -> 0x046e, RuntimeException -> 0x0460 }
            r11 = 0
            java.lang.Object r14 = r2.getAttribute(r3)     // Catch:{ ConnectionShutdownException -> 0x048a, HttpException -> 0x047c, IOException -> 0x046e, RuntimeException -> 0x0460 }
            cz.msebera.android.httpclient.conn.ManagedClientConnection r15 = r1.managedConn     // Catch:{ ConnectionShutdownException -> 0x048a, HttpException -> 0x047c, IOException -> 0x046e, RuntimeException -> 0x0460 }
            if (r15 != 0) goto L_0x018b
            cz.msebera.android.httpclient.conn.ClientConnectionManager r15 = r1.connManager     // Catch:{ ConnectionShutdownException -> 0x017d, HttpException -> 0x016f, IOException -> 0x0161, RuntimeException -> 0x0153 }
            cz.msebera.android.httpclient.conn.ClientConnectionRequest r15 = r15.requestConnection(r13, r14)     // Catch:{ ConnectionShutdownException -> 0x017d, HttpException -> 0x016f, IOException -> 0x0161, RuntimeException -> 0x0153 }
            r16 = r4
            boolean r4 = r5 instanceof p008cz.msebera.android.httpclient.client.methods.AbortableHttpRequest     // Catch:{ ConnectionShutdownException -> 0x0147, HttpException -> 0x013b, IOException -> 0x012f, RuntimeException -> 0x0123 }
            if (r4 == 0) goto L_0x00bd
            r4 = r5
            cz.msebera.android.httpclient.client.methods.AbortableHttpRequest r4 = (p008cz.msebera.android.httpclient.client.methods.AbortableHttpRequest) r4     // Catch:{ ConnectionShutdownException -> 0x00b1, HttpException -> 0x00a5, IOException -> 0x0099, RuntimeException -> 0x008d }
            r4.setConnectionRequest(r15)     // Catch:{ ConnectionShutdownException -> 0x00b1, HttpException -> 0x00a5, IOException -> 0x0099, RuntimeException -> 0x008d }
            goto L_0x00bd
        L_0x008d:
            r0 = move-exception
            r3 = r0
            r19 = r5
            r17 = r6
            r18 = r7
            r20 = r10
            goto L_0x04db
        L_0x0099:
            r0 = move-exception
            r3 = r0
            r19 = r5
            r17 = r6
            r18 = r7
            r20 = r10
            goto L_0x04df
        L_0x00a5:
            r0 = move-exception
            r3 = r0
            r19 = r5
            r17 = r6
            r18 = r7
            r20 = r10
            goto L_0x04e3
        L_0x00b1:
            r0 = move-exception
            r3 = r0
            r19 = r5
            r17 = r6
            r18 = r7
            r20 = r10
            goto L_0x04e7
        L_0x00bd:
            cz.msebera.android.httpclient.params.HttpParams r4 = r1.params     // Catch:{ ConnectionShutdownException -> 0x0147, HttpException -> 0x013b, IOException -> 0x012f, RuntimeException -> 0x0123 }
            long r17 = p008cz.msebera.android.httpclient.client.params.HttpClientParams.getConnectionManagerTimeout(r4)     // Catch:{ ConnectionShutdownException -> 0x0147, HttpException -> 0x013b, IOException -> 0x012f, RuntimeException -> 0x0123 }
            r19 = r17
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x0110 }
            r17 = r6
            r18 = r7
            r6 = r19
            cz.msebera.android.httpclient.conn.ManagedClientConnection r4 = r15.getConnection(r6, r4)     // Catch:{ InterruptedException -> 0x010b }
            r1.managedConn = r4     // Catch:{ InterruptedException -> 0x010b }
            cz.msebera.android.httpclient.params.HttpParams r4 = r1.params     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            boolean r4 = p008cz.msebera.android.httpclient.params.HttpConnectionParams.isStaleCheckingEnabled(r4)     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            if (r4 == 0) goto L_0x0107
            cz.msebera.android.httpclient.conn.ManagedClientConnection r4 = r1.managedConn     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            boolean r4 = r4.isOpen()     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            if (r4 == 0) goto L_0x0103
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r4 = r1.log     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            r19 = r6
            java.lang.String r6 = "Stale connection check"
            r4.debug(r6)     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            cz.msebera.android.httpclient.conn.ManagedClientConnection r4 = r1.managedConn     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            boolean r4 = r4.isStale()     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            if (r4 == 0) goto L_0x0191
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r4 = r1.log     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            java.lang.String r6 = "Stale connection detected"
            r4.debug(r6)     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            cz.msebera.android.httpclient.conn.ManagedClientConnection r4 = r1.managedConn     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            r4.close()     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            goto L_0x0191
        L_0x0103:
            r19 = r6
            goto L_0x0191
        L_0x0107:
            r19 = r6
            goto L_0x0191
        L_0x010b:
            r0 = move-exception
            r19 = r6
            r3 = r0
            goto L_0x0116
        L_0x0110:
            r0 = move-exception
            r17 = r6
            r18 = r7
            r3 = r0
        L_0x0116:
            java.lang.Thread r4 = java.lang.Thread.currentThread()     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            r4.interrupt()     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            java.io.InterruptedIOException r4 = new java.io.InterruptedIOException     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            r4.<init>()     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            throw r4     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
        L_0x0123:
            r0 = move-exception
            r17 = r6
            r18 = r7
            r3 = r0
            r19 = r5
            r20 = r10
            goto L_0x04db
        L_0x012f:
            r0 = move-exception
            r17 = r6
            r18 = r7
            r3 = r0
            r19 = r5
            r20 = r10
            goto L_0x04df
        L_0x013b:
            r0 = move-exception
            r17 = r6
            r18 = r7
            r3 = r0
            r19 = r5
            r20 = r10
            goto L_0x04e3
        L_0x0147:
            r0 = move-exception
            r17 = r6
            r18 = r7
            r3 = r0
            r19 = r5
            r20 = r10
            goto L_0x04e7
        L_0x0153:
            r0 = move-exception
            r16 = r4
            r17 = r6
            r18 = r7
            r3 = r0
            r19 = r5
            r20 = r10
            goto L_0x04db
        L_0x0161:
            r0 = move-exception
            r16 = r4
            r17 = r6
            r18 = r7
            r3 = r0
            r19 = r5
            r20 = r10
            goto L_0x04df
        L_0x016f:
            r0 = move-exception
            r16 = r4
            r17 = r6
            r18 = r7
            r3 = r0
            r19 = r5
            r20 = r10
            goto L_0x04e3
        L_0x017d:
            r0 = move-exception
            r16 = r4
            r17 = r6
            r18 = r7
            r3 = r0
            r19 = r5
            r20 = r10
            goto L_0x04e7
        L_0x018b:
            r16 = r4
            r17 = r6
            r18 = r7
        L_0x0191:
            boolean r4 = r5 instanceof p008cz.msebera.android.httpclient.client.methods.AbortableHttpRequest     // Catch:{ ConnectionShutdownException -> 0x0458, HttpException -> 0x0450, IOException -> 0x0448, RuntimeException -> 0x0440 }
            if (r4 == 0) goto L_0x01be
            r4 = r5
            cz.msebera.android.httpclient.client.methods.AbortableHttpRequest r4 = (p008cz.msebera.android.httpclient.client.methods.AbortableHttpRequest) r4     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            cz.msebera.android.httpclient.conn.ManagedClientConnection r6 = r1.managedConn     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            r4.setReleaseTrigger(r6)     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            goto L_0x01be
        L_0x019e:
            r0 = move-exception
            r3 = r0
            r19 = r5
            r20 = r10
            goto L_0x04db
        L_0x01a6:
            r0 = move-exception
            r3 = r0
            r19 = r5
            r20 = r10
            goto L_0x04df
        L_0x01ae:
            r0 = move-exception
            r3 = r0
            r19 = r5
            r20 = r10
            goto L_0x04e3
        L_0x01b6:
            r0 = move-exception
            r3 = r0
            r19 = r5
            r20 = r10
            goto L_0x04e7
        L_0x01be:
            r1.tryConnect(r8, r2)     // Catch:{ TunnelRefusedException -> 0x0422 }
            java.net.URI r4 = r12.getURI()     // Catch:{ ConnectionShutdownException -> 0x0458, HttpException -> 0x0450, IOException -> 0x0448, RuntimeException -> 0x0440 }
            java.lang.String r4 = r4.getUserInfo()     // Catch:{ ConnectionShutdownException -> 0x0458, HttpException -> 0x0450, IOException -> 0x0448, RuntimeException -> 0x0440 }
            if (r4 == 0) goto L_0x01db
            cz.msebera.android.httpclient.auth.AuthState r6 = r1.targetAuthState     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            cz.msebera.android.httpclient.impl.auth.BasicScheme r7 = new cz.msebera.android.httpclient.impl.auth.BasicScheme     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            r7.<init>()     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            cz.msebera.android.httpclient.auth.UsernamePasswordCredentials r15 = new cz.msebera.android.httpclient.auth.UsernamePasswordCredentials     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            r15.<init>(r4)     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            r6.update(r7, r15)     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
        L_0x01db:
            cz.msebera.android.httpclient.HttpHost r6 = r1.virtualHost     // Catch:{ ConnectionShutdownException -> 0x0458, HttpException -> 0x0450, IOException -> 0x0448, RuntimeException -> 0x0440 }
            if (r6 == 0) goto L_0x01e4
            cz.msebera.android.httpclient.HttpHost r6 = r1.virtualHost     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            r16 = r6
            goto L_0x01f4
        L_0x01e4:
            java.net.URI r6 = r12.getURI()     // Catch:{ ConnectionShutdownException -> 0x0458, HttpException -> 0x0450, IOException -> 0x0448, RuntimeException -> 0x0440 }
            boolean r7 = r6.isAbsolute()     // Catch:{ ConnectionShutdownException -> 0x0458, HttpException -> 0x0450, IOException -> 0x0448, RuntimeException -> 0x0440 }
            if (r7 == 0) goto L_0x01f4
            cz.msebera.android.httpclient.HttpHost r7 = p008cz.msebera.android.httpclient.client.utils.URIUtils.extractHost(r6)     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            r16 = r7
        L_0x01f4:
            if (r16 != 0) goto L_0x01fb
            cz.msebera.android.httpclient.HttpHost r6 = r13.getTargetHost()     // Catch:{ ConnectionShutdownException -> 0x01b6, HttpException -> 0x01ae, IOException -> 0x01a6, RuntimeException -> 0x019e }
            goto L_0x01fd
        L_0x01fb:
            r6 = r16
        L_0x01fd:
            r12.resetHeaders()     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            r1.rewriteRequestURI(r12, r13)     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            java.lang.String r7 = "http.target_host"
            r2.setAttribute(r7, r6)     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            java.lang.String r7 = "http.route"
            r2.setAttribute(r7, r13)     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            java.lang.String r7 = "http.connection"
            cz.msebera.android.httpclient.conn.ManagedClientConnection r15 = r1.managedConn     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            r2.setAttribute(r7, r15)     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            cz.msebera.android.httpclient.protocol.HttpRequestExecutor r7 = r1.requestExec     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            cz.msebera.android.httpclient.protocol.HttpProcessor r15 = r1.httpProcessor     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            r7.preProcess(r12, r15, r2)     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            cz.msebera.android.httpclient.HttpResponse r7 = r1.tryExecute(r8, r2)     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            r11 = r7
            if (r11 != 0) goto L_0x0229
            r4 = r6
            r6 = r17
            r7 = r18
            goto L_0x0067
        L_0x0229:
            cz.msebera.android.httpclient.params.HttpParams r7 = r1.params     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            r11.setParams(r7)     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            cz.msebera.android.httpclient.protocol.HttpRequestExecutor r7 = r1.requestExec     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            cz.msebera.android.httpclient.protocol.HttpProcessor r15 = r1.httpProcessor     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            r7.postProcess(r11, r15, r2)     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            cz.msebera.android.httpclient.ConnectionReuseStrategy r7 = r1.reuseStrategy     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            boolean r7 = r7.keepAlive(r11, r2)     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            r9 = r7
            if (r9 == 0) goto L_0x032a
            cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy r7 = r1.keepAliveStrategy     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            long r15 = r7.getKeepAliveDuration(r11, r2)     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            r19 = r15
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r7 = r1.log     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            boolean r7 = r7.isDebugEnabled()     // Catch:{ ConnectionShutdownException -> 0x0418, HttpException -> 0x040e, IOException -> 0x0404, RuntimeException -> 0x03fa }
            if (r7 == 0) goto L_0x0316
            r15 = 0
            r21 = r4
            r7 = r5
            r4 = r19
            int r19 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r19 <= 0) goto L_0x02bf
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ ConnectionShutdownException -> 0x02b5, HttpException -> 0x02ab, IOException -> 0x02a1, RuntimeException -> 0x0297 }
            r15.<init>()     // Catch:{ ConnectionShutdownException -> 0x02b5, HttpException -> 0x02ab, IOException -> 0x02a1, RuntimeException -> 0x0297 }
            r16 = r6
            java.lang.String r6 = "for "
            r15.append(r6)     // Catch:{ ConnectionShutdownException -> 0x028f, HttpException -> 0x0287, IOException -> 0x027f, RuntimeException -> 0x0277 }
            r15.append(r4)     // Catch:{ ConnectionShutdownException -> 0x028f, HttpException -> 0x0287, IOException -> 0x027f, RuntimeException -> 0x0277 }
            java.lang.String r6 = " "
            r15.append(r6)     // Catch:{ ConnectionShutdownException -> 0x028f, HttpException -> 0x0287, IOException -> 0x027f, RuntimeException -> 0x0277 }
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ ConnectionShutdownException -> 0x028f, HttpException -> 0x0287, IOException -> 0x027f, RuntimeException -> 0x0277 }
            r15.append(r6)     // Catch:{ ConnectionShutdownException -> 0x028f, HttpException -> 0x0287, IOException -> 0x027f, RuntimeException -> 0x0277 }
            java.lang.String r6 = r15.toString()     // Catch:{ ConnectionShutdownException -> 0x028f, HttpException -> 0x0287, IOException -> 0x027f, RuntimeException -> 0x0277 }
            goto L_0x02c3
        L_0x0277:
            r0 = move-exception
            r3 = r0
            r19 = r7
            r20 = r10
            goto L_0x04db
        L_0x027f:
            r0 = move-exception
            r3 = r0
            r19 = r7
            r20 = r10
            goto L_0x04df
        L_0x0287:
            r0 = move-exception
            r3 = r0
            r19 = r7
            r20 = r10
            goto L_0x04e3
        L_0x028f:
            r0 = move-exception
            r3 = r0
            r19 = r7
            r20 = r10
            goto L_0x04e7
        L_0x0297:
            r0 = move-exception
            r16 = r6
            r3 = r0
            r19 = r7
            r20 = r10
            goto L_0x04db
        L_0x02a1:
            r0 = move-exception
            r16 = r6
            r3 = r0
            r19 = r7
            r20 = r10
            goto L_0x04df
        L_0x02ab:
            r0 = move-exception
            r16 = r6
            r3 = r0
            r19 = r7
            r20 = r10
            goto L_0x04e3
        L_0x02b5:
            r0 = move-exception
            r16 = r6
            r3 = r0
            r19 = r7
            r20 = r10
            goto L_0x04e7
        L_0x02bf:
            r16 = r6
            java.lang.String r6 = "indefinitely"
        L_0x02c3:
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r15 = r1.log     // Catch:{ ConnectionShutdownException -> 0x030e, HttpException -> 0x0306, IOException -> 0x02fe, RuntimeException -> 0x02f6 }
            r19 = r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ ConnectionShutdownException -> 0x02f0, HttpException -> 0x02ea, IOException -> 0x02e4, RuntimeException -> 0x02de }
            r7.<init>()     // Catch:{ ConnectionShutdownException -> 0x02f0, HttpException -> 0x02ea, IOException -> 0x02e4, RuntimeException -> 0x02de }
            r20 = r10
            java.lang.String r10 = "Connection can be kept alive "
            r7.append(r10)     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            r7.append(r6)     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            java.lang.String r7 = r7.toString()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            r15.debug(r7)     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            goto L_0x0322
        L_0x02de:
            r0 = move-exception
            r20 = r10
            r3 = r0
            goto L_0x04db
        L_0x02e4:
            r0 = move-exception
            r20 = r10
            r3 = r0
            goto L_0x04df
        L_0x02ea:
            r0 = move-exception
            r20 = r10
            r3 = r0
            goto L_0x04e3
        L_0x02f0:
            r0 = move-exception
            r20 = r10
            r3 = r0
            goto L_0x04e7
        L_0x02f6:
            r0 = move-exception
            r19 = r7
            r20 = r10
            r3 = r0
            goto L_0x04db
        L_0x02fe:
            r0 = move-exception
            r19 = r7
            r20 = r10
            r3 = r0
            goto L_0x04df
        L_0x0306:
            r0 = move-exception
            r19 = r7
            r20 = r10
            r3 = r0
            goto L_0x04e3
        L_0x030e:
            r0 = move-exception
            r19 = r7
            r20 = r10
            r3 = r0
            goto L_0x04e7
        L_0x0316:
            r21 = r4
            r16 = r6
            r22 = r19
            r19 = r5
            r20 = r10
            r4 = r22
        L_0x0322:
            cz.msebera.android.httpclient.conn.ManagedClientConnection r6 = r1.managedConn     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            r6.setIdleDuration(r4, r7)     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            goto L_0x0332
        L_0x032a:
            r21 = r4
            r19 = r5
            r16 = r6
            r20 = r10
        L_0x0332:
            cz.msebera.android.httpclient.impl.client.RoutedRequest r4 = r1.handleResponse(r8, r11, r2)     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            if (r4 != 0) goto L_0x033c
            r5 = 1
            r10 = r5
            goto L_0x03c1
        L_0x033c:
            if (r9 == 0) goto L_0x034b
            cz.msebera.android.httpclient.HttpEntity r5 = r11.getEntity()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            p008cz.msebera.android.httpclient.util.EntityUtils.consume(r5)     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            cz.msebera.android.httpclient.conn.ManagedClientConnection r6 = r1.managedConn     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            r6.markReusable()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            goto L_0x03ac
        L_0x034b:
            cz.msebera.android.httpclient.conn.ManagedClientConnection r5 = r1.managedConn     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            r5.close()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            cz.msebera.android.httpclient.auth.AuthState r5 = r1.proxyAuthState     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            cz.msebera.android.httpclient.auth.AuthProtocolState r5 = r5.getState()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            cz.msebera.android.httpclient.auth.AuthProtocolState r6 = p008cz.msebera.android.httpclient.auth.AuthProtocolState.CHALLENGED     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            int r5 = r5.compareTo(r6)     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            if (r5 <= 0) goto L_0x037e
            cz.msebera.android.httpclient.auth.AuthState r5 = r1.proxyAuthState     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            cz.msebera.android.httpclient.auth.AuthScheme r5 = r5.getAuthScheme()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            if (r5 == 0) goto L_0x037e
            cz.msebera.android.httpclient.auth.AuthState r5 = r1.proxyAuthState     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            cz.msebera.android.httpclient.auth.AuthScheme r5 = r5.getAuthScheme()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            boolean r5 = r5.isConnectionBased()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            if (r5 == 0) goto L_0x037e
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r5 = r1.log     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            java.lang.String r6 = "Resetting proxy auth state"
            r5.debug(r6)     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            cz.msebera.android.httpclient.auth.AuthState r5 = r1.proxyAuthState     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            r5.reset()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
        L_0x037e:
            cz.msebera.android.httpclient.auth.AuthState r5 = r1.targetAuthState     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            cz.msebera.android.httpclient.auth.AuthProtocolState r5 = r5.getState()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            cz.msebera.android.httpclient.auth.AuthProtocolState r6 = p008cz.msebera.android.httpclient.auth.AuthProtocolState.CHALLENGED     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            int r5 = r5.compareTo(r6)     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            if (r5 <= 0) goto L_0x03ac
            cz.msebera.android.httpclient.auth.AuthState r5 = r1.targetAuthState     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            cz.msebera.android.httpclient.auth.AuthScheme r5 = r5.getAuthScheme()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            if (r5 == 0) goto L_0x03ac
            cz.msebera.android.httpclient.auth.AuthState r5 = r1.targetAuthState     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            cz.msebera.android.httpclient.auth.AuthScheme r5 = r5.getAuthScheme()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            boolean r5 = r5.isConnectionBased()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            if (r5 == 0) goto L_0x03ac
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r5 = r1.log     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            java.lang.String r6 = "Resetting target auth state"
            r5.debug(r6)     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            cz.msebera.android.httpclient.auth.AuthState r5 = r1.targetAuthState     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            r5.reset()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
        L_0x03ac:
            cz.msebera.android.httpclient.conn.routing.HttpRoute r5 = r4.getRoute()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            cz.msebera.android.httpclient.conn.routing.HttpRoute r6 = r8.getRoute()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            boolean r5 = r5.equals(r6)     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            if (r5 != 0) goto L_0x03bd
            r24.releaseConnection()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
        L_0x03bd:
            r5 = r4
            r8 = r5
            r10 = r20
        L_0x03c1:
            cz.msebera.android.httpclient.conn.ManagedClientConnection r5 = r1.managedConn     // Catch:{ ConnectionShutdownException -> 0x03f4, HttpException -> 0x03ee, IOException -> 0x03e8, RuntimeException -> 0x03e2 }
            if (r5 == 0) goto L_0x03d8
            if (r14 != 0) goto L_0x03d1
            cz.msebera.android.httpclient.client.UserTokenHandler r5 = r1.userTokenHandler     // Catch:{ ConnectionShutdownException -> 0x03f4, HttpException -> 0x03ee, IOException -> 0x03e8, RuntimeException -> 0x03e2 }
            java.lang.Object r5 = r5.getUserToken(r2)     // Catch:{ ConnectionShutdownException -> 0x03f4, HttpException -> 0x03ee, IOException -> 0x03e8, RuntimeException -> 0x03e2 }
            r14 = r5
            r2.setAttribute(r3, r14)     // Catch:{ ConnectionShutdownException -> 0x03f4, HttpException -> 0x03ee, IOException -> 0x03e8, RuntimeException -> 0x03e2 }
        L_0x03d1:
            if (r14 == 0) goto L_0x03d8
            cz.msebera.android.httpclient.conn.ManagedClientConnection r5 = r1.managedConn     // Catch:{ ConnectionShutdownException -> 0x03f4, HttpException -> 0x03ee, IOException -> 0x03e8, RuntimeException -> 0x03e2 }
            r5.setState(r14)     // Catch:{ ConnectionShutdownException -> 0x03f4, HttpException -> 0x03ee, IOException -> 0x03e8, RuntimeException -> 0x03e2 }
        L_0x03d8:
            r4 = r16
            r6 = r17
            r7 = r18
            r5 = r19
            goto L_0x0067
        L_0x03e2:
            r0 = move-exception
            r3 = r0
            r20 = r10
            goto L_0x04db
        L_0x03e8:
            r0 = move-exception
            r3 = r0
            r20 = r10
            goto L_0x04df
        L_0x03ee:
            r0 = move-exception
            r3 = r0
            r20 = r10
            goto L_0x04e3
        L_0x03f4:
            r0 = move-exception
            r3 = r0
            r20 = r10
            goto L_0x04e7
        L_0x03fa:
            r0 = move-exception
            r19 = r5
            r16 = r6
            r20 = r10
            r3 = r0
            goto L_0x04db
        L_0x0404:
            r0 = move-exception
            r19 = r5
            r16 = r6
            r20 = r10
            r3 = r0
            goto L_0x04df
        L_0x040e:
            r0 = move-exception
            r19 = r5
            r16 = r6
            r20 = r10
            r3 = r0
            goto L_0x04e3
        L_0x0418:
            r0 = move-exception
            r19 = r5
            r16 = r6
            r20 = r10
            r3 = r0
            goto L_0x04e7
        L_0x0422:
            r0 = move-exception
            r19 = r5
            r20 = r10
            r3 = r0
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r4 = r1.log     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            boolean r4 = r4.isDebugEnabled()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            if (r4 == 0) goto L_0x0439
            cz.msebera.android.httpclient.extras.HttpClientAndroidLog r4 = r1.log     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            java.lang.String r5 = r3.getMessage()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            r4.debug(r5)     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
        L_0x0439:
            cz.msebera.android.httpclient.HttpResponse r4 = r3.getResponse()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            r11 = r4
            goto L_0x04a1
        L_0x0440:
            r0 = move-exception
            r19 = r5
            r20 = r10
            r3 = r0
            goto L_0x04db
        L_0x0448:
            r0 = move-exception
            r19 = r5
            r20 = r10
            r3 = r0
            goto L_0x04df
        L_0x0450:
            r0 = move-exception
            r19 = r5
            r20 = r10
            r3 = r0
            goto L_0x04e3
        L_0x0458:
            r0 = move-exception
            r19 = r5
            r20 = r10
            r3 = r0
            goto L_0x04e7
        L_0x0460:
            r0 = move-exception
            r16 = r4
            r19 = r5
            r17 = r6
            r18 = r7
            r20 = r10
            r3 = r0
            goto L_0x04db
        L_0x046e:
            r0 = move-exception
            r16 = r4
            r19 = r5
            r17 = r6
            r18 = r7
            r20 = r10
            r3 = r0
            goto L_0x04df
        L_0x047c:
            r0 = move-exception
            r16 = r4
            r19 = r5
            r17 = r6
            r18 = r7
            r20 = r10
            r3 = r0
            goto L_0x04e3
        L_0x048a:
            r0 = move-exception
            r16 = r4
            r19 = r5
            r17 = r6
            r18 = r7
            r20 = r10
            r3 = r0
            goto L_0x04e7
        L_0x0497:
            r16 = r4
            r19 = r5
            r17 = r6
            r18 = r7
            r20 = r10
        L_0x04a1:
            if (r11 == 0) goto L_0x04d0
            cz.msebera.android.httpclient.HttpEntity r3 = r11.getEntity()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            if (r3 == 0) goto L_0x04d0
            cz.msebera.android.httpclient.HttpEntity r3 = r11.getEntity()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            boolean r3 = r3.isStreaming()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            if (r3 != 0) goto L_0x04b4
            goto L_0x04d0
        L_0x04b4:
            cz.msebera.android.httpclient.HttpEntity r3 = r11.getEntity()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            cz.msebera.android.httpclient.conn.BasicManagedEntity r4 = new cz.msebera.android.httpclient.conn.BasicManagedEntity     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            cz.msebera.android.httpclient.conn.ManagedClientConnection r5 = r1.managedConn     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            r4.<init>(r3, r5, r9)     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            r3 = r4
            r11.setEntity(r3)     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            goto L_0x04da
        L_0x04c4:
            r0 = move-exception
            r3 = r0
            goto L_0x04db
        L_0x04c7:
            r0 = move-exception
            r3 = r0
            goto L_0x04df
        L_0x04ca:
            r0 = move-exception
            r3 = r0
            goto L_0x04e3
        L_0x04cd:
            r0 = move-exception
            r3 = r0
            goto L_0x04e7
        L_0x04d0:
            if (r9 == 0) goto L_0x04d7
            cz.msebera.android.httpclient.conn.ManagedClientConnection r3 = r1.managedConn     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
            r3.markReusable()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
        L_0x04d7:
            r24.releaseConnection()     // Catch:{ ConnectionShutdownException -> 0x04cd, HttpException -> 0x04ca, IOException -> 0x04c7, RuntimeException -> 0x04c4 }
        L_0x04da:
            return r11
        L_0x04db:
            r24.abortConnection()
            throw r3
        L_0x04df:
            r24.abortConnection()
            throw r3
        L_0x04e3:
            r24.abortConnection()
            throw r3
        L_0x04e7:
            java.io.InterruptedIOException r4 = new java.io.InterruptedIOException
            java.lang.String r5 = "Connection has been shut down"
            r4.<init>(r5)
            r4.initCause(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.client.DefaultRequestDirector.execute(cz.msebera.android.httpclient.HttpHost, cz.msebera.android.httpclient.HttpRequest, cz.msebera.android.httpclient.protocol.HttpContext):cz.msebera.android.httpclient.HttpResponse");
    }

    private void tryConnect(RoutedRequest req, HttpContext context) throws HttpException, IOException {
        HttpRoute route = req.getRoute();
        HttpRequest wrapper = req.getRequest();
        int connectCount = 0;
        while (true) {
            context.setAttribute("http.request", wrapper);
            connectCount++;
            try {
                if (!this.managedConn.isOpen()) {
                    this.managedConn.open(route, context, this.params);
                } else {
                    this.managedConn.setSocketTimeout(HttpConnectionParams.getSoTimeout(this.params));
                }
                establishRoute(route, context);
                return;
            } catch (IOException ex) {
                try {
                    this.managedConn.close();
                } catch (IOException e) {
                }
                if (!this.retryHandler.retryRequest(ex, connectCount, context)) {
                    throw ex;
                } else if (this.log.isInfoEnabled()) {
                    HttpClientAndroidLog httpClientAndroidLog = this.log;
                    StringBuilder sb = new StringBuilder();
                    sb.append("I/O exception (");
                    sb.append(ex.getClass().getName());
                    sb.append(") caught when connecting to ");
                    sb.append(route);
                    sb.append(": ");
                    sb.append(ex.getMessage());
                    httpClientAndroidLog.info(sb.toString());
                    if (this.log.isDebugEnabled()) {
                        this.log.debug(ex.getMessage(), ex);
                    }
                    HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Retrying connect to ");
                    sb2.append(route);
                    httpClientAndroidLog2.info(sb2.toString());
                }
            }
        }
    }

    private HttpResponse tryExecute(RoutedRequest req, HttpContext context) throws HttpException, IOException {
        RequestWrapper wrapper = req.getRequest();
        HttpRoute route = req.getRoute();
        IOException iOException = null;
        while (true) {
            this.execCount++;
            wrapper.incrementExecCount();
            if (!wrapper.isRepeatable()) {
                this.log.debug("Cannot retry non-repeatable request");
                if (iOException != null) {
                    throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity.  The cause lists the reason the original request failed.", iOException);
                }
                throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity.");
            }
            try {
                if (!this.managedConn.isOpen()) {
                    if (!route.isTunnelled()) {
                        this.log.debug("Reopening the direct connection.");
                        this.managedConn.open(route, context, this.params);
                    } else {
                        this.log.debug("Proxied connection. Need to start over.");
                        return null;
                    }
                }
                if (this.log.isDebugEnabled()) {
                    HttpClientAndroidLog httpClientAndroidLog = this.log;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Attempt ");
                    sb.append(this.execCount);
                    sb.append(" to execute request");
                    httpClientAndroidLog.debug(sb.toString());
                }
                return this.requestExec.execute(wrapper, this.managedConn, context);
            } catch (IOException ex) {
                this.log.debug("Closing the connection.");
                try {
                    this.managedConn.close();
                } catch (IOException e) {
                }
                if (this.retryHandler.retryRequest(ex, wrapper.getExecCount(), context)) {
                    if (this.log.isInfoEnabled()) {
                        HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("I/O exception (");
                        sb2.append(ex.getClass().getName());
                        sb2.append(") caught when processing request to ");
                        sb2.append(route);
                        sb2.append(": ");
                        sb2.append(ex.getMessage());
                        httpClientAndroidLog2.info(sb2.toString());
                    }
                    if (this.log.isDebugEnabled()) {
                        this.log.debug(ex.getMessage(), ex);
                    }
                    if (this.log.isInfoEnabled()) {
                        HttpClientAndroidLog httpClientAndroidLog3 = this.log;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Retrying request to ");
                        sb3.append(route);
                        httpClientAndroidLog3.info(sb3.toString());
                    }
                    iOException = ex;
                } else if (ex instanceof NoHttpResponseException) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(route.getTargetHost().toHostString());
                    sb4.append(" failed to respond");
                    NoHttpResponseException updatedex = new NoHttpResponseException(sb4.toString());
                    updatedex.setStackTrace(ex.getStackTrace());
                    throw updatedex;
                } else {
                    throw ex;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void releaseConnection() {
        try {
            this.managedConn.releaseConnection();
        } catch (IOException ignored) {
            this.log.debug("IOException releasing connection", ignored);
        }
        this.managedConn = null;
    }

    /* access modifiers changed from: protected */
    public HttpRoute determineRoute(HttpHost targetHost, HttpRequest request, HttpContext context) throws HttpException {
        HttpHost httpHost;
        HttpRoutePlanner httpRoutePlanner = this.routePlanner;
        if (targetHost != null) {
            httpHost = targetHost;
        } else {
            httpHost = (HttpHost) request.getParams().getParameter(ClientPNames.DEFAULT_HOST);
        }
        return httpRoutePlanner.determineRoute(httpHost, request, context);
    }

    /* access modifiers changed from: protected */
    public void establishRoute(HttpRoute route, HttpContext context) throws HttpException, IOException {
        int step;
        HttpRouteDirector rowdy = new BasicRouteDirector();
        do {
            HttpRoute fact = this.managedConn.getRoute();
            step = rowdy.nextStep(route, fact);
            switch (step) {
                case -1:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unable to establish route: planned = ");
                    sb.append(route);
                    sb.append("; current = ");
                    sb.append(fact);
                    throw new HttpException(sb.toString());
                case 0:
                    break;
                case 1:
                case 2:
                    this.managedConn.open(route, context, this.params);
                    continue;
                case 3:
                    boolean secure = createTunnelToTarget(route, context);
                    this.log.debug("Tunnel to target created.");
                    this.managedConn.tunnelTarget(secure, this.params);
                    continue;
                case 4:
                    int hop = fact.getHopCount() - 1;
                    boolean secure2 = createTunnelToProxy(route, hop, context);
                    this.log.debug("Tunnel to proxy created.");
                    this.managedConn.tunnelProxy(route.getHopTarget(hop), secure2, this.params);
                    continue;
                case 5:
                    this.managedConn.layerProtocol(context, this.params);
                    continue;
                default:
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Unknown step indicator ");
                    sb2.append(step);
                    sb2.append(" from RouteDirector.");
                    throw new IllegalStateException(sb2.toString());
            }
        } while (step > 0);
    }

    /* access modifiers changed from: protected */
    public boolean createTunnelToTarget(HttpRoute route, HttpContext context) throws HttpException, IOException {
        HttpResponse response;
        HttpHost proxy = route.getProxyHost();
        HttpHost target = route.getTargetHost();
        while (true) {
            if (!this.managedConn.isOpen()) {
                this.managedConn.open(route, context, this.params);
            }
            HttpRequest connect = createConnectRequest(route, context);
            connect.setParams(this.params);
            context.setAttribute("http.target_host", target);
            context.setAttribute("http.route", route);
            context.setAttribute(ExecutionContext.HTTP_PROXY_HOST, proxy);
            context.setAttribute("http.connection", this.managedConn);
            context.setAttribute("http.request", connect);
            this.requestExec.preProcess(connect, this.httpProcessor, context);
            response = this.requestExec.execute(connect, this.managedConn, context);
            response.setParams(this.params);
            this.requestExec.postProcess(response, this.httpProcessor, context);
            if (response.getStatusLine().getStatusCode() >= 200) {
                if (HttpClientParams.isAuthenticating(this.params)) {
                    if (!this.authenticator.isAuthenticationRequested(proxy, response, this.proxyAuthStrategy, this.proxyAuthState, context)) {
                        break;
                    }
                    if (!this.authenticator.authenticate(proxy, response, this.proxyAuthStrategy, this.proxyAuthState, context)) {
                        break;
                    } else if (this.reuseStrategy.keepAlive(response, context)) {
                        this.log.debug("Connection kept alive");
                        EntityUtils.consume(response.getEntity());
                    } else {
                        this.managedConn.close();
                    }
                }
                HttpResponse httpResponse = response;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected response to CONNECT request: ");
                sb.append(response.getStatusLine());
                throw new HttpException(sb.toString());
            }
        }
        if (response.getStatusLine().getStatusCode() > 299) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                response.setEntity(new BufferedHttpEntity(entity));
            }
            this.managedConn.close();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("CONNECT refused by proxy: ");
            sb2.append(response.getStatusLine());
            throw new TunnelRefusedException(sb2.toString(), response);
        }
        this.managedConn.markReusable();
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean createTunnelToProxy(HttpRoute route, int hop, HttpContext context) throws HttpException, IOException {
        throw new HttpException("Proxy chains are not supported.");
    }

    /* access modifiers changed from: protected */
    public HttpRequest createConnectRequest(HttpRoute route, HttpContext context) {
        HttpHost target = route.getTargetHost();
        String host = target.getHostName();
        int port = target.getPort();
        if (port < 0) {
            port = this.connManager.getSchemeRegistry().getScheme(target.getSchemeName()).getDefaultPort();
        }
        StringBuilder buffer = new StringBuilder(host.length() + 6);
        buffer.append(host);
        buffer.append(':');
        buffer.append(Integer.toString(port));
        return new BasicHttpRequest("CONNECT", buffer.toString(), HttpProtocolParams.getVersion(this.params));
    }

    /* access modifiers changed from: protected */
    public RoutedRequest handleResponse(RoutedRequest roureq, HttpResponse response, HttpContext context) throws HttpException, IOException {
        HttpHost target;
        HttpHost proxy;
        HttpResponse httpResponse = response;
        HttpContext httpContext = context;
        HttpRoute route = roureq.getRoute();
        RequestWrapper request = roureq.getRequest();
        HttpParams params2 = request.getParams();
        if (HttpClientParams.isAuthenticating(params2)) {
            HttpHost target2 = (HttpHost) httpContext.getAttribute("http.target_host");
            if (target2 == null) {
                target2 = route.getTargetHost();
            }
            if (target2.getPort() < 0) {
                target = new HttpHost(target2.getHostName(), this.connManager.getSchemeRegistry().getScheme(target2).getDefaultPort(), target2.getSchemeName());
            } else {
                target = target2;
            }
            boolean targetAuthRequested = this.authenticator.isAuthenticationRequested(target, response, this.targetAuthStrategy, this.targetAuthState, context);
            HttpHost proxy2 = route.getProxyHost();
            if (proxy2 == null) {
                proxy = route.getTargetHost();
            } else {
                proxy = proxy2;
            }
            boolean proxyAuthRequested = this.authenticator.isAuthenticationRequested(proxy, response, this.proxyAuthStrategy, this.proxyAuthState, context);
            if (targetAuthRequested) {
                if (this.authenticator.authenticate(target, response, this.targetAuthStrategy, this.targetAuthState, context)) {
                    return roureq;
                }
            }
            if (proxyAuthRequested) {
                if (this.authenticator.authenticate(proxy, response, this.proxyAuthStrategy, this.proxyAuthState, context)) {
                    return roureq;
                }
            }
        }
        if (!HttpClientParams.isRedirecting(params2) || !this.redirectStrategy.isRedirected(request, httpResponse, httpContext)) {
            return null;
        }
        int i = this.redirectCount;
        if (i < this.maxRedirects) {
            this.redirectCount = i + 1;
            this.virtualHost = null;
            HttpUriRequest redirect = this.redirectStrategy.getRedirect(request, httpResponse, httpContext);
            redirect.setHeaders(request.getOriginal().getAllHeaders());
            URI uri = redirect.getURI();
            HttpHost newTarget = URIUtils.extractHost(uri);
            if (newTarget != null) {
                if (!route.getTargetHost().equals(newTarget)) {
                    this.log.debug("Resetting target auth state");
                    this.targetAuthState.reset();
                    AuthScheme authScheme = this.proxyAuthState.getAuthScheme();
                    if (authScheme != null && authScheme.isConnectionBased()) {
                        this.log.debug("Resetting proxy auth state");
                        this.proxyAuthState.reset();
                    }
                }
                RequestWrapper wrapper = wrapRequest(redirect);
                wrapper.setParams(params2);
                HttpRoute newRoute = determineRoute(newTarget, wrapper, httpContext);
                RoutedRequest newRequest = new RoutedRequest(wrapper, newRoute);
                if (this.log.isDebugEnabled()) {
                    HttpClientAndroidLog httpClientAndroidLog = this.log;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Redirecting to '");
                    sb.append(uri);
                    sb.append("' via ");
                    sb.append(newRoute);
                    httpClientAndroidLog.debug(sb.toString());
                }
                return newRequest;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Redirect URI does not specify a valid host name: ");
            sb2.append(uri);
            throw new ProtocolException(sb2.toString());
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Maximum redirects (");
        sb3.append(this.maxRedirects);
        sb3.append(") exceeded");
        throw new RedirectException(sb3.toString());
    }

    private void abortConnection() {
        ManagedClientConnection mcc = this.managedConn;
        if (mcc != null) {
            this.managedConn = null;
            try {
                mcc.abortConnection();
            } catch (IOException ex) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug(ex.getMessage(), ex);
                }
            }
            try {
                mcc.releaseConnection();
            } catch (IOException ignored) {
                this.log.debug("Error releasing connection", ignored);
            }
        }
    }
}
