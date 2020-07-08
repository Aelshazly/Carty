package p008cz.msebera.android.httpclient.impl.client;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.auth.AuthSchemeProvider;
import p008cz.msebera.android.httpclient.auth.AuthState;
import p008cz.msebera.android.httpclient.client.ClientProtocolException;
import p008cz.msebera.android.httpclient.client.CookieStore;
import p008cz.msebera.android.httpclient.client.CredentialsProvider;
import p008cz.msebera.android.httpclient.client.config.RequestConfig;
import p008cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import p008cz.msebera.android.httpclient.client.methods.Configurable;
import p008cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import p008cz.msebera.android.httpclient.client.params.ClientPNames;
import p008cz.msebera.android.httpclient.client.params.HttpClientParamConfig;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.config.Lookup;
import p008cz.msebera.android.httpclient.conn.ClientConnectionManager;
import p008cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import p008cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import p008cz.msebera.android.httpclient.conn.ManagedClientConnection;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner;
import p008cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import p008cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.impl.execchain.ClientExecChain;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.params.HttpParamsNames;
import p008cz.msebera.android.httpclient.protocol.BasicHttpContext;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.client.InternalHttpClient */
class InternalHttpClient extends CloseableHttpClient {
    private final Lookup<AuthSchemeProvider> authSchemeRegistry;
    private final List<Closeable> closeables;
    /* access modifiers changed from: private */
    public final HttpClientConnectionManager connManager;
    private final Lookup<CookieSpecProvider> cookieSpecRegistry;
    private final CookieStore cookieStore;
    private final CredentialsProvider credentialsProvider;
    private final RequestConfig defaultConfig;
    private final ClientExecChain execChain;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private final HttpRoutePlanner routePlanner;

    public InternalHttpClient(ClientExecChain execChain2, HttpClientConnectionManager connManager2, HttpRoutePlanner routePlanner2, Lookup<CookieSpecProvider> cookieSpecRegistry2, Lookup<AuthSchemeProvider> authSchemeRegistry2, CookieStore cookieStore2, CredentialsProvider credentialsProvider2, RequestConfig defaultConfig2, List<Closeable> closeables2) {
        Args.notNull(execChain2, "HTTP client exec chain");
        Args.notNull(connManager2, "HTTP connection manager");
        Args.notNull(routePlanner2, "HTTP route planner");
        this.execChain = execChain2;
        this.connManager = connManager2;
        this.routePlanner = routePlanner2;
        this.cookieSpecRegistry = cookieSpecRegistry2;
        this.authSchemeRegistry = authSchemeRegistry2;
        this.cookieStore = cookieStore2;
        this.credentialsProvider = credentialsProvider2;
        this.defaultConfig = defaultConfig2;
        this.closeables = closeables2;
    }

    private HttpRoute determineRoute(HttpHost target, HttpRequest request, HttpContext context) throws HttpException {
        HttpHost host = target;
        if (host == null) {
            host = (HttpHost) request.getParams().getParameter(ClientPNames.DEFAULT_HOST);
        }
        return this.routePlanner.determineRoute(host, request, context);
    }

    private void setupContext(HttpClientContext context) {
        String str = "http.auth.target-scope";
        if (context.getAttribute(str) == null) {
            context.setAttribute(str, new AuthState());
        }
        String str2 = "http.auth.proxy-scope";
        if (context.getAttribute(str2) == null) {
            context.setAttribute(str2, new AuthState());
        }
        String str3 = "http.authscheme-registry";
        if (context.getAttribute(str3) == null) {
            context.setAttribute(str3, this.authSchemeRegistry);
        }
        String str4 = "http.cookiespec-registry";
        if (context.getAttribute(str4) == null) {
            context.setAttribute(str4, this.cookieSpecRegistry);
        }
        String str5 = "http.cookie-store";
        if (context.getAttribute(str5) == null) {
            context.setAttribute(str5, this.cookieStore);
        }
        String str6 = "http.auth.credentials-provider";
        if (context.getAttribute(str6) == null) {
            context.setAttribute(str6, this.credentialsProvider);
        }
        String str7 = "http.request-config";
        if (context.getAttribute(str7) == null) {
            context.setAttribute(str7, this.defaultConfig);
        }
    }

    /* access modifiers changed from: protected */
    public CloseableHttpResponse doExecute(HttpHost target, HttpRequest request, HttpContext context) throws IOException, ClientProtocolException {
        Args.notNull(request, "HTTP request");
        HttpExecutionAware execAware = null;
        if (request instanceof HttpExecutionAware) {
            execAware = (HttpExecutionAware) request;
        }
        try {
            HttpRequestWrapper wrapper = HttpRequestWrapper.wrap(request);
            HttpClientContext localcontext = HttpClientContext.adapt(context != null ? context : new BasicHttpContext());
            RequestConfig config = null;
            if (request instanceof Configurable) {
                config = ((Configurable) request).getConfig();
            }
            if (config == null) {
                HttpParams params = request.getParams();
                if (!(params instanceof HttpParamsNames)) {
                    config = HttpClientParamConfig.getRequestConfig(params);
                } else if (!((HttpParamsNames) params).getNames().isEmpty()) {
                    config = HttpClientParamConfig.getRequestConfig(params);
                }
            }
            if (config != null) {
                localcontext.setRequestConfig(config);
            }
            setupContext(localcontext);
            return this.execChain.execute(determineRoute(target, wrapper, localcontext), wrapper, localcontext, execAware);
        } catch (HttpException httpException) {
            throw new ClientProtocolException((Throwable) httpException);
        }
    }

    public void close() {
        this.connManager.shutdown();
        List<Closeable> list = this.closeables;
        if (list != null) {
            for (Closeable closeable : list) {
                try {
                    closeable.close();
                } catch (IOException ex) {
                    this.log.error(ex.getMessage(), ex);
                }
            }
        }
    }

    public HttpParams getParams() {
        throw new UnsupportedOperationException();
    }

    public ClientConnectionManager getConnectionManager() {
        return new ClientConnectionManager() {
            public void shutdown() {
                InternalHttpClient.this.connManager.shutdown();
            }

            public ClientConnectionRequest requestConnection(HttpRoute route, Object state) {
                throw new UnsupportedOperationException();
            }

            public void releaseConnection(ManagedClientConnection conn, long validDuration, TimeUnit timeUnit) {
                throw new UnsupportedOperationException();
            }

            public SchemeRegistry getSchemeRegistry() {
                throw new UnsupportedOperationException();
            }

            public void closeIdleConnections(long idletime, TimeUnit tunit) {
                InternalHttpClient.this.connManager.closeIdleConnections(idletime, tunit);
            }

            public void closeExpiredConnections() {
                InternalHttpClient.this.connManager.closeExpiredConnections();
            }
        };
    }
}
