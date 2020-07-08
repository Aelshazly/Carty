package p008cz.msebera.android.httpclient.impl.execchain;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import p008cz.msebera.android.httpclient.ConnectionReuseStrategy;
import p008cz.msebera.android.httpclient.HttpClientConnection;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.auth.AuthProtocolState;
import p008cz.msebera.android.httpclient.auth.AuthState;
import p008cz.msebera.android.httpclient.client.AuthenticationStrategy;
import p008cz.msebera.android.httpclient.client.NonRepeatableRequestException;
import p008cz.msebera.android.httpclient.client.UserTokenHandler;
import p008cz.msebera.android.httpclient.client.config.RequestConfig;
import p008cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import p008cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.client.protocol.RequestClientConnControl;
import p008cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy;
import p008cz.msebera.android.httpclient.conn.ConnectionRequest;
import p008cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import p008cz.msebera.android.httpclient.conn.routing.BasicRouteDirector;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.conn.routing.HttpRouteDirector;
import p008cz.msebera.android.httpclient.conn.routing.RouteTracker;
import p008cz.msebera.android.httpclient.entity.BufferedHttpEntity;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.impl.auth.HttpAuthenticator;
import p008cz.msebera.android.httpclient.impl.conn.ConnectionShutdownException;
import p008cz.msebera.android.httpclient.message.BasicHttpRequest;
import p008cz.msebera.android.httpclient.protocol.HttpProcessor;
import p008cz.msebera.android.httpclient.protocol.HttpRequestExecutor;
import p008cz.msebera.android.httpclient.protocol.ImmutableHttpProcessor;
import p008cz.msebera.android.httpclient.protocol.RequestTargetHost;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.EntityUtils;

/* renamed from: cz.msebera.android.httpclient.impl.execchain.MainClientExec */
public class MainClientExec implements ClientExecChain {
    private final HttpAuthenticator authenticator;
    private final HttpClientConnectionManager connManager;
    private final ConnectionKeepAliveStrategy keepAliveStrategy;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private final AuthenticationStrategy proxyAuthStrategy;
    private final HttpProcessor proxyHttpProcessor;
    private final HttpRequestExecutor requestExecutor;
    private final ConnectionReuseStrategy reuseStrategy;
    private final HttpRouteDirector routeDirector;
    private final AuthenticationStrategy targetAuthStrategy;
    private final UserTokenHandler userTokenHandler;

    public MainClientExec(HttpRequestExecutor requestExecutor2, HttpClientConnectionManager connManager2, ConnectionReuseStrategy reuseStrategy2, ConnectionKeepAliveStrategy keepAliveStrategy2, AuthenticationStrategy targetAuthStrategy2, AuthenticationStrategy proxyAuthStrategy2, UserTokenHandler userTokenHandler2) {
        Args.notNull(requestExecutor2, "HTTP request executor");
        Args.notNull(connManager2, "Client connection manager");
        Args.notNull(reuseStrategy2, "Connection reuse strategy");
        Args.notNull(keepAliveStrategy2, "Connection keep alive strategy");
        Args.notNull(targetAuthStrategy2, "Target authentication strategy");
        Args.notNull(proxyAuthStrategy2, "Proxy authentication strategy");
        Args.notNull(userTokenHandler2, "User token handler");
        this.authenticator = new HttpAuthenticator();
        this.proxyHttpProcessor = new ImmutableHttpProcessor(new RequestTargetHost(), new RequestClientConnControl());
        this.routeDirector = new BasicRouteDirector();
        this.requestExecutor = requestExecutor2;
        this.connManager = connManager2;
        this.reuseStrategy = reuseStrategy2;
        this.keepAliveStrategy = keepAliveStrategy2;
        this.targetAuthStrategy = targetAuthStrategy2;
        this.proxyAuthStrategy = proxyAuthStrategy2;
        this.userTokenHandler = userTokenHandler2;
    }

    public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware) throws IOException, HttpException {
        AuthState targetAuthState;
        AuthState proxyAuthState;
        ConnectionShutdownException ex;
        HttpException ex2;
        ConnectionHolder connHolder;
        IOException ex3;
        RuntimeException ex4;
        ConnectionHolder connHolder2;
        Object userToken;
        HttpResponse response;
        Object userToken2;
        ConnectionHolder connHolder3;
        HttpClientConnection managedConn;
        int execCount;
        String str;
        int timeout;
        HttpClientConnection managedConn2;
        HttpClientConnection managedConn3;
        HttpResponse response2;
        ConnectionHolder connHolder4;
        long duration;
        String s;
        HttpRoute httpRoute = route;
        HttpRequestWrapper httpRequestWrapper = request;
        HttpClientContext httpClientContext = context;
        HttpExecutionAware httpExecutionAware = execAware;
        String str2 = "Proxy-Authorization";
        String str3 = "Authorization";
        Args.notNull(httpRoute, "HTTP route");
        Args.notNull(httpRequestWrapper, "HTTP request");
        Args.notNull(httpClientContext, "HTTP context");
        AuthState targetAuthState2 = context.getTargetAuthState();
        if (targetAuthState2 == null) {
            AuthState targetAuthState3 = new AuthState();
            httpClientContext.setAttribute("http.auth.target-scope", targetAuthState3);
            targetAuthState = targetAuthState3;
        } else {
            targetAuthState = targetAuthState2;
        }
        AuthState proxyAuthState2 = context.getProxyAuthState();
        if (proxyAuthState2 == null) {
            AuthState proxyAuthState3 = new AuthState();
            httpClientContext.setAttribute("http.auth.proxy-scope", proxyAuthState3);
            proxyAuthState = proxyAuthState3;
        } else {
            proxyAuthState = proxyAuthState2;
        }
        if (httpRequestWrapper instanceof HttpEntityEnclosingRequest) {
            RequestEntityProxy.enhance((HttpEntityEnclosingRequest) httpRequestWrapper);
        }
        Object userToken3 = context.getUserToken();
        ConnectionRequest connRequest = this.connManager.requestConnection(httpRoute, userToken3);
        String str4 = "Request aborted";
        if (httpExecutionAware != null) {
            if (!execAware.isAborted()) {
                httpExecutionAware.setCancellable(connRequest);
            } else {
                connRequest.cancel();
                throw new RequestAbortedException(str4);
            }
        }
        RequestConfig config = context.getRequestConfig();
        try {
            int timeout2 = config.getConnectionRequestTimeout();
            int i = timeout2;
            HttpClientConnection managedConn4 = connRequest.get(timeout2 > 0 ? (long) timeout2 : 0, TimeUnit.MILLISECONDS);
            httpClientContext.setAttribute("http.connection", managedConn4);
            if (config.isStaleConnectionCheckEnabled() && managedConn4.isOpen()) {
                this.log.debug("Stale connection check");
                if (managedConn4.isStale()) {
                    this.log.debug("Stale connection detected");
                    managedConn4.close();
                }
            }
            ConnectionRequest connectionRequest = connRequest;
            ConnectionHolder connHolder5 = new ConnectionHolder(this.log, this.connManager, managedConn4);
            if (httpExecutionAware != null) {
                try {
                    httpExecutionAware.setCancellable(connHolder5);
                } catch (ConnectionShutdownException e) {
                    ex = e;
                    HttpClientConnection httpClientConnection = managedConn4;
                    ConnectionHolder connectionHolder = connHolder5;
                    Object obj = userToken3;
                    InterruptedIOException ioex = new InterruptedIOException("Connection has been shut down");
                    ioex.initCause(ex);
                    throw ioex;
                } catch (HttpException e2) {
                    ex2 = e2;
                    HttpClientConnection httpClientConnection2 = managedConn4;
                    connHolder = connHolder5;
                    Object obj2 = userToken3;
                    connHolder.abortConnection();
                    throw ex2;
                } catch (IOException e3) {
                    ex3 = e3;
                    HttpClientConnection httpClientConnection3 = managedConn4;
                    connHolder = connHolder5;
                    Object obj3 = userToken3;
                    connHolder.abortConnection();
                    throw ex3;
                } catch (RuntimeException e4) {
                    ex4 = e4;
                    HttpClientConnection httpClientConnection4 = managedConn4;
                    connHolder = connHolder5;
                    Object obj4 = userToken3;
                    connHolder.abortConnection();
                    throw ex4;
                }
            }
            int execCount2 = 1;
            while (true) {
                if (execCount2 > 1) {
                    if (!RequestEntityProxy.isRepeatable(request)) {
                        throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity.");
                    }
                }
                if (httpExecutionAware != null) {
                    if (execAware.isAborted()) {
                        throw new RequestAbortedException(str4);
                    }
                }
                try {
                    if (!managedConn4.isOpen()) {
                        try {
                            HttpClientAndroidLog httpClientAndroidLog = this.log;
                            execCount = execCount2;
                            StringBuilder sb = new StringBuilder();
                            HttpClientConnection managedConn5 = managedConn4;
                            try {
                                sb.append("Opening connection ");
                                sb.append(httpRoute);
                                httpClientAndroidLog.debug(sb.toString());
                                managedConn = managedConn5;
                                str = str4;
                                connHolder3 = connHolder5;
                                userToken = userToken3;
                                try {
                                    establishRoute(proxyAuthState, managedConn, route, request, context);
                                } catch (TunnelRefusedException e5) {
                                    TunnelRefusedException ex5 = e5;
                                    try {
                                        if (this.log.isDebugEnabled()) {
                                            this.log.debug(ex5.getMessage());
                                        }
                                        response = ex5.getResponse();
                                        connHolder2 = connHolder3;
                                        HttpClientConnection httpClientConnection5 = managedConn;
                                    } catch (ConnectionShutdownException e6) {
                                        ex = e6;
                                        ConnectionHolder connectionHolder2 = connHolder3;
                                        HttpClientConnection httpClientConnection6 = managedConn;
                                        InterruptedIOException ioex2 = new InterruptedIOException("Connection has been shut down");
                                        ioex2.initCause(ex);
                                        throw ioex2;
                                    } catch (HttpException e7) {
                                        ex2 = e7;
                                        connHolder = connHolder3;
                                        HttpClientConnection httpClientConnection7 = managedConn;
                                        connHolder.abortConnection();
                                        throw ex2;
                                    } catch (IOException e8) {
                                        ex3 = e8;
                                        connHolder = connHolder3;
                                        HttpClientConnection httpClientConnection8 = managedConn;
                                        connHolder.abortConnection();
                                        throw ex3;
                                    } catch (RuntimeException e9) {
                                        ex4 = e9;
                                        connHolder = connHolder3;
                                        HttpClientConnection httpClientConnection9 = managedConn;
                                        connHolder.abortConnection();
                                        throw ex4;
                                    }
                                }
                            } catch (ConnectionShutdownException e10) {
                                HttpClientConnection httpClientConnection10 = managedConn5;
                                Object obj5 = userToken3;
                                ex = e10;
                                ConnectionHolder connectionHolder3 = connHolder5;
                                HttpClientConnection httpClientConnection11 = httpClientConnection10;
                                InterruptedIOException ioex22 = new InterruptedIOException("Connection has been shut down");
                                ioex22.initCause(ex);
                                throw ioex22;
                            } catch (HttpException e11) {
                                HttpClientConnection httpClientConnection12 = managedConn5;
                                Object obj6 = userToken3;
                                ex2 = e11;
                                connHolder = connHolder5;
                                HttpClientConnection httpClientConnection13 = httpClientConnection12;
                                connHolder.abortConnection();
                                throw ex2;
                            } catch (IOException e12) {
                                HttpClientConnection httpClientConnection14 = managedConn5;
                                Object obj7 = userToken3;
                                ex3 = e12;
                                connHolder = connHolder5;
                                HttpClientConnection httpClientConnection15 = httpClientConnection14;
                                connHolder.abortConnection();
                                throw ex3;
                            } catch (RuntimeException e13) {
                                HttpClientConnection httpClientConnection16 = managedConn5;
                                Object obj8 = userToken3;
                                ex4 = e13;
                                connHolder = connHolder5;
                                HttpClientConnection httpClientConnection17 = httpClientConnection16;
                                connHolder.abortConnection();
                                throw ex4;
                            }
                        } catch (ConnectionShutdownException e14) {
                            Object obj9 = userToken3;
                            ex = e14;
                            HttpClientConnection httpClientConnection18 = managedConn4;
                            ConnectionHolder connectionHolder4 = connHolder5;
                            InterruptedIOException ioex222 = new InterruptedIOException("Connection has been shut down");
                            ioex222.initCause(ex);
                            throw ioex222;
                        } catch (HttpException e15) {
                            Object obj10 = userToken3;
                            ex2 = e15;
                            HttpClientConnection httpClientConnection19 = managedConn4;
                            connHolder = connHolder5;
                            connHolder.abortConnection();
                            throw ex2;
                        } catch (IOException e16) {
                            Object obj11 = userToken3;
                            ex3 = e16;
                            HttpClientConnection httpClientConnection20 = managedConn4;
                            connHolder = connHolder5;
                            connHolder.abortConnection();
                            throw ex3;
                        } catch (RuntimeException e17) {
                            Object obj12 = userToken3;
                            ex4 = e17;
                            HttpClientConnection httpClientConnection21 = managedConn4;
                            connHolder = connHolder5;
                            connHolder.abortConnection();
                            throw ex4;
                        }
                    } else {
                        execCount = execCount2;
                        managedConn = managedConn4;
                        str = str4;
                        connHolder3 = connHolder5;
                        userToken = userToken3;
                    }
                    try {
                        timeout = config.getSocketTimeout();
                        if (timeout >= 0) {
                            managedConn2 = managedConn;
                            try {
                                managedConn2.setSocketTimeout(timeout);
                            } catch (ConnectionShutdownException e18) {
                                ex = e18;
                                ConnectionHolder connectionHolder5 = connHolder3;
                                HttpClientConnection httpClientConnection22 = managedConn2;
                                InterruptedIOException ioex2222 = new InterruptedIOException("Connection has been shut down");
                                ioex2222.initCause(ex);
                                throw ioex2222;
                            } catch (HttpException e19) {
                                ex2 = e19;
                                connHolder = connHolder3;
                                HttpClientConnection httpClientConnection23 = managedConn2;
                                connHolder.abortConnection();
                                throw ex2;
                            } catch (IOException e20) {
                                ex3 = e20;
                                connHolder = connHolder3;
                                HttpClientConnection httpClientConnection24 = managedConn2;
                                connHolder.abortConnection();
                                throw ex3;
                            } catch (RuntimeException e21) {
                                ex4 = e21;
                                connHolder = connHolder3;
                                HttpClientConnection httpClientConnection25 = managedConn2;
                                connHolder.abortConnection();
                                throw ex4;
                            }
                        } else {
                            managedConn2 = managedConn;
                        }
                        if (httpExecutionAware != null) {
                            if (execAware.isAborted()) {
                                throw new RequestAbortedException(str);
                            }
                        }
                    } catch (ConnectionShutdownException e22) {
                        ConnectionHolder connectionHolder6 = connHolder3;
                        HttpClientConnection httpClientConnection26 = managedConn;
                        ex = e22;
                        InterruptedIOException ioex22222 = new InterruptedIOException("Connection has been shut down");
                        ioex22222.initCause(ex);
                        throw ioex22222;
                    } catch (HttpException e23) {
                        connHolder = connHolder3;
                        HttpClientConnection httpClientConnection27 = managedConn;
                        ex2 = e23;
                        connHolder.abortConnection();
                        throw ex2;
                    } catch (IOException e24) {
                        connHolder = connHolder3;
                        HttpClientConnection httpClientConnection28 = managedConn;
                        ex3 = e24;
                        connHolder.abortConnection();
                        throw ex3;
                    } catch (RuntimeException e25) {
                        connHolder = connHolder3;
                        HttpClientConnection httpClientConnection29 = managedConn;
                        ex4 = e25;
                        connHolder.abortConnection();
                        throw ex4;
                    }
                    try {
                        if (this.log.isDebugEnabled()) {
                            HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("Executing request ");
                            sb2.append(request.getRequestLine());
                            httpClientAndroidLog2.debug(sb2.toString());
                        }
                        if (!httpRequestWrapper.containsHeader(str3)) {
                            if (this.log.isDebugEnabled()) {
                                HttpClientAndroidLog httpClientAndroidLog3 = this.log;
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append("Target auth state: ");
                                sb3.append(targetAuthState.getState());
                                httpClientAndroidLog3.debug(sb3.toString());
                            }
                            this.authenticator.generateAuthResponse(httpRequestWrapper, targetAuthState, httpClientContext);
                        }
                        if (!httpRequestWrapper.containsHeader(str2)) {
                            if (!route.isTunnelled()) {
                                if (this.log.isDebugEnabled()) {
                                    HttpClientAndroidLog httpClientAndroidLog4 = this.log;
                                    StringBuilder sb4 = new StringBuilder();
                                    sb4.append("Proxy auth state: ");
                                    sb4.append(proxyAuthState.getState());
                                    httpClientAndroidLog4.debug(sb4.toString());
                                }
                                this.authenticator.generateAuthResponse(httpRequestWrapper, proxyAuthState, httpClientContext);
                            }
                        }
                        HttpResponse response3 = this.requestExecutor.execute(httpRequestWrapper, managedConn2, httpClientContext);
                        if (this.reuseStrategy.keepAlive(response3, httpClientContext)) {
                            try {
                                duration = this.keepAliveStrategy.getKeepAliveDuration(response3, httpClientContext);
                                if (this.log.isDebugEnabled()) {
                                    if (duration > 0) {
                                        StringBuilder sb5 = new StringBuilder();
                                        response2 = response3;
                                        sb5.append("for ");
                                        sb5.append(duration);
                                        sb5.append(" ");
                                        sb5.append(TimeUnit.MILLISECONDS);
                                        s = sb5.toString();
                                    } else {
                                        response2 = response3;
                                        s = "indefinitely";
                                    }
                                    try {
                                        HttpClientAndroidLog httpClientAndroidLog5 = this.log;
                                        managedConn3 = managedConn2;
                                        try {
                                            StringBuilder sb6 = new StringBuilder();
                                            int i2 = timeout;
                                            sb6.append("Connection can be kept alive ");
                                            sb6.append(s);
                                            httpClientAndroidLog5.debug(sb6.toString());
                                        } catch (ConnectionShutdownException e26) {
                                            ex = e26;
                                            ConnectionHolder connectionHolder7 = connHolder3;
                                            HttpClientConnection httpClientConnection30 = managedConn3;
                                        } catch (HttpException e27) {
                                            ex2 = e27;
                                            connHolder = connHolder3;
                                            HttpClientConnection httpClientConnection31 = managedConn3;
                                            connHolder.abortConnection();
                                            throw ex2;
                                        } catch (IOException e28) {
                                            ex3 = e28;
                                            connHolder = connHolder3;
                                            HttpClientConnection httpClientConnection32 = managedConn3;
                                            connHolder.abortConnection();
                                            throw ex3;
                                        } catch (RuntimeException e29) {
                                            ex4 = e29;
                                            connHolder = connHolder3;
                                            HttpClientConnection httpClientConnection33 = managedConn3;
                                            connHolder.abortConnection();
                                            throw ex4;
                                        }
                                    } catch (ConnectionShutdownException e30) {
                                        ex = e30;
                                        ConnectionHolder connectionHolder8 = connHolder3;
                                        HttpClientConnection httpClientConnection34 = managedConn2;
                                        InterruptedIOException ioex222222 = new InterruptedIOException("Connection has been shut down");
                                        ioex222222.initCause(ex);
                                        throw ioex222222;
                                    } catch (HttpException e31) {
                                        ex2 = e31;
                                        connHolder = connHolder3;
                                        HttpClientConnection httpClientConnection35 = managedConn2;
                                        connHolder.abortConnection();
                                        throw ex2;
                                    } catch (IOException e32) {
                                        ex3 = e32;
                                        connHolder = connHolder3;
                                        HttpClientConnection httpClientConnection36 = managedConn2;
                                        connHolder.abortConnection();
                                        throw ex3;
                                    } catch (RuntimeException e33) {
                                        ex4 = e33;
                                        connHolder = connHolder3;
                                        HttpClientConnection httpClientConnection37 = managedConn2;
                                        connHolder.abortConnection();
                                        throw ex4;
                                    }
                                } else {
                                    response2 = response3;
                                    managedConn3 = managedConn2;
                                    int i3 = timeout;
                                }
                                try {
                                    connHolder4 = connHolder3;
                                } catch (ConnectionShutdownException e34) {
                                    ex = e34;
                                    ConnectionHolder connectionHolder9 = connHolder3;
                                    HttpClientConnection httpClientConnection38 = managedConn3;
                                    InterruptedIOException ioex2222222 = new InterruptedIOException("Connection has been shut down");
                                    ioex2222222.initCause(ex);
                                    throw ioex2222222;
                                } catch (HttpException e35) {
                                    ex2 = e35;
                                    connHolder = connHolder3;
                                    HttpClientConnection httpClientConnection39 = managedConn3;
                                    connHolder.abortConnection();
                                    throw ex2;
                                } catch (IOException e36) {
                                    ex3 = e36;
                                    connHolder = connHolder3;
                                    HttpClientConnection httpClientConnection40 = managedConn3;
                                    connHolder.abortConnection();
                                    throw ex3;
                                } catch (RuntimeException e37) {
                                    ex4 = e37;
                                    connHolder = connHolder3;
                                    HttpClientConnection httpClientConnection41 = managedConn3;
                                    connHolder.abortConnection();
                                    throw ex4;
                                }
                            } catch (ConnectionShutdownException e38) {
                                ex = e38;
                                ConnectionHolder connectionHolder10 = connHolder3;
                                HttpClientConnection httpClientConnection42 = managedConn2;
                                InterruptedIOException ioex22222222 = new InterruptedIOException("Connection has been shut down");
                                ioex22222222.initCause(ex);
                                throw ioex22222222;
                            } catch (HttpException e39) {
                                ex2 = e39;
                                connHolder = connHolder3;
                                HttpClientConnection httpClientConnection43 = managedConn2;
                                connHolder.abortConnection();
                                throw ex2;
                            } catch (IOException e40) {
                                ex3 = e40;
                                connHolder = connHolder3;
                                HttpClientConnection httpClientConnection44 = managedConn2;
                                connHolder.abortConnection();
                                throw ex3;
                            } catch (RuntimeException e41) {
                                ex4 = e41;
                                connHolder = connHolder3;
                                HttpClientConnection httpClientConnection45 = managedConn2;
                                connHolder.abortConnection();
                                throw ex4;
                            }
                            try {
                                connHolder4.setValidFor(duration, TimeUnit.MILLISECONDS);
                                connHolder4.markReusable();
                            } catch (ConnectionShutdownException e42) {
                                ex = e42;
                                ConnectionHolder connectionHolder11 = connHolder4;
                                HttpClientConnection httpClientConnection46 = managedConn3;
                            } catch (HttpException e43) {
                                ex2 = e43;
                                connHolder = connHolder4;
                                HttpClientConnection httpClientConnection47 = managedConn3;
                                connHolder.abortConnection();
                                throw ex2;
                            } catch (IOException e44) {
                                ex3 = e44;
                                connHolder = connHolder4;
                                HttpClientConnection httpClientConnection48 = managedConn3;
                                connHolder.abortConnection();
                                throw ex3;
                            } catch (RuntimeException e45) {
                                ex4 = e45;
                                connHolder = connHolder4;
                                HttpClientConnection httpClientConnection49 = managedConn3;
                                connHolder.abortConnection();
                                throw ex4;
                            }
                        } else {
                            response2 = response3;
                            managedConn3 = managedConn2;
                            int i4 = timeout;
                            connHolder4 = connHolder3;
                            try {
                                connHolder4.markNonReusable();
                            } catch (ConnectionShutdownException e46) {
                                ConnectionHolder connectionHolder12 = connHolder4;
                                HttpClientConnection httpClientConnection50 = managedConn3;
                                ex = e46;
                                InterruptedIOException ioex222222222 = new InterruptedIOException("Connection has been shut down");
                                ioex222222222.initCause(ex);
                                throw ioex222222222;
                            } catch (HttpException e47) {
                                connHolder = connHolder4;
                                HttpClientConnection httpClientConnection51 = managedConn3;
                                ex2 = e47;
                                connHolder.abortConnection();
                                throw ex2;
                            } catch (IOException e48) {
                                connHolder = connHolder4;
                                HttpClientConnection httpClientConnection52 = managedConn3;
                                ex3 = e48;
                                connHolder.abortConnection();
                                throw ex3;
                            } catch (RuntimeException e49) {
                                connHolder = connHolder4;
                                HttpClientConnection httpClientConnection53 = managedConn3;
                                ex4 = e49;
                                connHolder.abortConnection();
                                throw ex4;
                            }
                        }
                        HttpClientConnection managedConn6 = managedConn3;
                        connHolder2 = connHolder4;
                        try {
                            if (!needAuthentication(targetAuthState, proxyAuthState, route, response2, context)) {
                                response = response2;
                                break;
                            }
                            try {
                                HttpEntity entity = response2.getEntity();
                                if (connHolder2.isReusable()) {
                                    EntityUtils.consume(entity);
                                } else {
                                    managedConn6.close();
                                    if (proxyAuthState.getState() == AuthProtocolState.SUCCESS && proxyAuthState.getAuthScheme() != null && proxyAuthState.getAuthScheme().isConnectionBased()) {
                                        this.log.debug("Resetting proxy auth state");
                                        proxyAuthState.reset();
                                    }
                                    if (targetAuthState.getState() == AuthProtocolState.SUCCESS && targetAuthState.getAuthScheme() != null && targetAuthState.getAuthScheme().isConnectionBased()) {
                                        this.log.debug("Resetting target auth state");
                                        targetAuthState.reset();
                                    }
                                }
                                HttpRequest original = request.getOriginal();
                                if (!original.containsHeader(str3)) {
                                    httpRequestWrapper.removeHeaders(str3);
                                }
                                if (!original.containsHeader(str2)) {
                                    httpRequestWrapper.removeHeaders(str2);
                                }
                                execCount2 = execCount + 1;
                                str4 = str;
                                userToken3 = userToken;
                                managedConn4 = managedConn6;
                                connHolder5 = connHolder2;
                                httpRoute = route;
                            } catch (ConnectionShutdownException e50) {
                                ex = e50;
                                ConnectionHolder connectionHolder13 = connHolder2;
                                InterruptedIOException ioex2222222222 = new InterruptedIOException("Connection has been shut down");
                                ioex2222222222.initCause(ex);
                                throw ioex2222222222;
                            } catch (HttpException e51) {
                                ex2 = e51;
                                connHolder = connHolder2;
                                connHolder.abortConnection();
                                throw ex2;
                            } catch (IOException e52) {
                                ex3 = e52;
                                connHolder = connHolder2;
                                connHolder.abortConnection();
                                throw ex3;
                            } catch (RuntimeException e53) {
                                ex4 = e53;
                                connHolder = connHolder2;
                                connHolder.abortConnection();
                                throw ex4;
                            }
                        } catch (ConnectionShutdownException e54) {
                            ConnectionHolder connectionHolder14 = connHolder2;
                            ex = e54;
                            InterruptedIOException ioex22222222222 = new InterruptedIOException("Connection has been shut down");
                            ioex22222222222.initCause(ex);
                            throw ioex22222222222;
                        } catch (HttpException e55) {
                            connHolder = connHolder2;
                            ex2 = e55;
                            connHolder.abortConnection();
                            throw ex2;
                        } catch (IOException e56) {
                            connHolder = connHolder2;
                            ex3 = e56;
                            connHolder.abortConnection();
                            throw ex3;
                        } catch (RuntimeException e57) {
                            connHolder = connHolder2;
                            ex4 = e57;
                            connHolder.abortConnection();
                            throw ex4;
                        }
                    } catch (ConnectionShutdownException e58) {
                        ConnectionHolder connectionHolder15 = connHolder3;
                        HttpClientConnection httpClientConnection54 = managedConn2;
                        ex = e58;
                        InterruptedIOException ioex222222222222 = new InterruptedIOException("Connection has been shut down");
                        ioex222222222222.initCause(ex);
                        throw ioex222222222222;
                    } catch (HttpException e59) {
                        connHolder = connHolder3;
                        HttpClientConnection httpClientConnection55 = managedConn2;
                        ex2 = e59;
                        connHolder.abortConnection();
                        throw ex2;
                    } catch (IOException e60) {
                        connHolder = connHolder3;
                        HttpClientConnection httpClientConnection56 = managedConn2;
                        ex3 = e60;
                        connHolder.abortConnection();
                        throw ex3;
                    } catch (RuntimeException e61) {
                        connHolder = connHolder3;
                        HttpClientConnection httpClientConnection57 = managedConn2;
                        ex4 = e61;
                        connHolder.abortConnection();
                        throw ex4;
                    }
                } catch (ConnectionShutdownException e62) {
                    HttpClientConnection httpClientConnection58 = managedConn4;
                    ConnectionHolder connectionHolder16 = connHolder5;
                    Object obj13 = userToken3;
                    ex = e62;
                    InterruptedIOException ioex2222222222222 = new InterruptedIOException("Connection has been shut down");
                    ioex2222222222222.initCause(ex);
                    throw ioex2222222222222;
                } catch (HttpException e63) {
                    HttpClientConnection httpClientConnection59 = managedConn4;
                    connHolder = connHolder5;
                    Object obj14 = userToken3;
                    ex2 = e63;
                    connHolder.abortConnection();
                    throw ex2;
                } catch (IOException e64) {
                    HttpClientConnection httpClientConnection60 = managedConn4;
                    connHolder = connHolder5;
                    Object obj15 = userToken3;
                    ex3 = e64;
                    connHolder.abortConnection();
                    throw ex3;
                } catch (RuntimeException e65) {
                    HttpClientConnection httpClientConnection61 = managedConn4;
                    connHolder = connHolder5;
                    Object obj16 = userToken3;
                    ex4 = e65;
                    connHolder.abortConnection();
                    throw ex4;
                }
            }
            if (userToken == null) {
                userToken2 = this.userTokenHandler.getUserToken(httpClientContext);
                try {
                    httpClientContext.setAttribute("http.user-token", userToken2);
                } catch (ConnectionShutdownException e66) {
                    ex = e66;
                    Object obj17 = userToken2;
                    ConnectionHolder connectionHolder17 = connHolder2;
                } catch (HttpException e67) {
                    ex2 = e67;
                    Object obj18 = userToken2;
                    connHolder = connHolder2;
                    connHolder.abortConnection();
                    throw ex2;
                } catch (IOException e68) {
                    ex3 = e68;
                    Object obj19 = userToken2;
                    connHolder = connHolder2;
                    connHolder.abortConnection();
                    throw ex3;
                } catch (RuntimeException e69) {
                    ex4 = e69;
                    Object obj20 = userToken2;
                    connHolder = connHolder2;
                    connHolder.abortConnection();
                    throw ex4;
                }
            } else {
                userToken2 = userToken;
            }
            if (userToken2 != null) {
                connHolder = connHolder2;
                try {
                    connHolder.setState(userToken2);
                } catch (ConnectionShutdownException e70) {
                    ex = e70;
                    Object obj21 = userToken2;
                    InterruptedIOException ioex22222222222222 = new InterruptedIOException("Connection has been shut down");
                    ioex22222222222222.initCause(ex);
                    throw ioex22222222222222;
                } catch (HttpException e71) {
                    ex2 = e71;
                    Object obj22 = userToken2;
                    connHolder.abortConnection();
                    throw ex2;
                } catch (IOException e72) {
                    ex3 = e72;
                    Object obj23 = userToken2;
                    connHolder.abortConnection();
                    throw ex3;
                } catch (RuntimeException e73) {
                    ex4 = e73;
                    Object obj24 = userToken2;
                    connHolder.abortConnection();
                    throw ex4;
                }
            } else {
                connHolder = connHolder2;
            }
            HttpEntity entity2 = response.getEntity();
            if (entity2 != null) {
                if (entity2.isStreaming()) {
                    return new HttpResponseProxy(response, connHolder);
                }
            }
            connHolder.releaseConnection();
            return new HttpResponseProxy(response, null);
        } catch (InterruptedException e74) {
            String str5 = str4;
            ConnectionRequest connectionRequest2 = connRequest;
            Object obj25 = userToken3;
            InterruptedException interrupted = e74;
            Thread.currentThread().interrupt();
            throw new RequestAbortedException(str5, interrupted);
        } catch (ExecutionException e75) {
            ConnectionRequest connectionRequest3 = connRequest;
            Object obj26 = userToken3;
            ExecutionException ex6 = e75;
            Throwable cause = ex6.getCause();
            if (cause == 0) {
                cause = ex6;
            }
            throw new RequestAbortedException("Request execution failed", cause);
        }
    }

    /* access modifiers changed from: 0000 */
    public void establishRoute(AuthState proxyAuthState, HttpClientConnection managedConn, HttpRoute route, HttpRequest request, HttpClientContext context) throws HttpException, IOException {
        int step;
        int timeout = context.getRequestConfig().getConnectTimeout();
        RouteTracker tracker = new RouteTracker(route);
        do {
            HttpRoute fact = tracker.toRoute();
            step = this.routeDirector.nextStep(route, fact);
            int i = 0;
            switch (step) {
                case -1:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unable to establish route: planned = ");
                    sb.append(route);
                    sb.append("; current = ");
                    sb.append(fact);
                    throw new HttpException(sb.toString());
                case 0:
                    this.connManager.routeComplete(managedConn, route, context);
                    continue;
                case 1:
                    HttpClientConnectionManager httpClientConnectionManager = this.connManager;
                    if (timeout > 0) {
                        i = timeout;
                    }
                    httpClientConnectionManager.connect(managedConn, route, i, context);
                    tracker.connectTarget(route.isSecure());
                    continue;
                case 2:
                    this.connManager.connect(managedConn, route, timeout > 0 ? timeout : 0, context);
                    tracker.connectProxy(route.getProxyHost(), false);
                    continue;
                case 3:
                    boolean secure = createTunnelToTarget(proxyAuthState, managedConn, route, request, context);
                    this.log.debug("Tunnel to target created.");
                    tracker.tunnelTarget(secure);
                    continue;
                case 4:
                    int hop = fact.getHopCount() - 1;
                    boolean secure2 = createTunnelToProxy(route, hop, context);
                    this.log.debug("Tunnel to proxy created.");
                    tracker.tunnelProxy(route.getHopTarget(hop), secure2);
                    continue;
                case 5:
                    this.connManager.upgrade(managedConn, route, context);
                    tracker.layerProtocol(route.isSecure());
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

    private boolean createTunnelToTarget(AuthState proxyAuthState, HttpClientConnection managedConn, HttpRoute route, HttpRequest request, HttpClientContext context) throws HttpException, IOException {
        HttpResponse response;
        HttpClientConnection httpClientConnection = managedConn;
        HttpClientContext httpClientContext = context;
        RequestConfig config = context.getRequestConfig();
        int timeout = config.getConnectTimeout();
        HttpHost target = route.getTargetHost();
        HttpHost proxy = route.getProxyHost();
        HttpResponse response2 = null;
        HttpRequest connect = new BasicHttpRequest("CONNECT", target.toHostString(), request.getProtocolVersion());
        this.requestExecutor.preProcess(connect, this.proxyHttpProcessor, httpClientContext);
        while (true) {
            int i = 0;
            if (response2 == null) {
                if (!managedConn.isOpen()) {
                    HttpClientConnectionManager httpClientConnectionManager = this.connManager;
                    if (timeout > 0) {
                        i = timeout;
                    }
                    httpClientConnectionManager.connect(httpClientConnection, route, i, httpClientContext);
                } else {
                    HttpRoute httpRoute = route;
                }
                connect.removeHeaders("Proxy-Authorization");
                this.authenticator.generateAuthResponse(connect, proxyAuthState, httpClientContext);
                HttpResponse response3 = this.requestExecutor.execute(connect, httpClientConnection, httpClientContext);
                int status = response3.getStatusLine().getStatusCode();
                if (status >= 200) {
                    if (config.isAuthenticationEnabled()) {
                        int i2 = status;
                        HttpResponse response4 = response3;
                        if (this.authenticator.isAuthenticationRequested(proxy, response3, this.proxyAuthStrategy, proxyAuthState, context)) {
                            if (this.authenticator.handleAuthChallenge(proxy, response4, this.proxyAuthStrategy, proxyAuthState, context)) {
                                HttpResponse response5 = response4;
                                if (this.reuseStrategy.keepAlive(response5, httpClientContext)) {
                                    this.log.debug("Connection kept alive");
                                    EntityUtils.consume(response5.getEntity());
                                } else {
                                    managedConn.close();
                                }
                                response2 = null;
                            } else {
                                response = response4;
                            }
                        } else {
                            response = response4;
                        }
                    } else {
                        response = response3;
                    }
                    response2 = response;
                } else {
                    int i3 = status;
                    HttpResponse response6 = response3;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unexpected response to CONNECT request: ");
                    sb.append(response6.getStatusLine());
                    throw new HttpException(sb.toString());
                }
            } else {
                HttpRoute httpRoute2 = route;
                if (response2.getStatusLine().getStatusCode() <= 299) {
                    return false;
                }
                HttpEntity entity = response2.getEntity();
                if (entity != null) {
                    response2.setEntity(new BufferedHttpEntity(entity));
                }
                managedConn.close();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("CONNECT refused by proxy: ");
                sb2.append(response2.getStatusLine());
                throw new TunnelRefusedException(sb2.toString(), response2);
            }
        }
    }

    private boolean createTunnelToProxy(HttpRoute route, int hop, HttpClientContext context) throws HttpException {
        throw new HttpException("Proxy chains are not supported.");
    }

    private boolean needAuthentication(AuthState targetAuthState, AuthState proxyAuthState, HttpRoute route, HttpResponse response, HttpClientContext context) {
        HttpHost proxy;
        if (context.getRequestConfig().isAuthenticationEnabled()) {
            HttpHost target = context.getTargetHost();
            if (target == null) {
                target = route.getTargetHost();
            }
            if (target.getPort() < 0) {
                target = new HttpHost(target.getHostName(), route.getTargetHost().getPort(), target.getSchemeName());
            }
            boolean targetAuthRequested = this.authenticator.isAuthenticationRequested(target, response, this.targetAuthStrategy, targetAuthState, context);
            HttpHost proxy2 = route.getProxyHost();
            if (proxy2 == null) {
                proxy = route.getTargetHost();
            } else {
                proxy = proxy2;
            }
            boolean proxyAuthRequested = this.authenticator.isAuthenticationRequested(proxy, response, this.proxyAuthStrategy, proxyAuthState, context);
            if (targetAuthRequested) {
                return this.authenticator.handleAuthChallenge(target, response, this.targetAuthStrategy, targetAuthState, context);
            } else if (proxyAuthRequested) {
                return this.authenticator.handleAuthChallenge(proxy, response, this.proxyAuthStrategy, proxyAuthState, context);
            }
        }
        return false;
    }
}
