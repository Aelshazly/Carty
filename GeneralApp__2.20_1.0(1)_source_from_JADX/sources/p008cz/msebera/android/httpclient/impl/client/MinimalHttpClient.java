package p008cz.msebera.android.httpclient.impl.client;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.client.ClientProtocolException;
import p008cz.msebera.android.httpclient.client.config.RequestConfig;
import p008cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import p008cz.msebera.android.httpclient.client.methods.Configurable;
import p008cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.conn.ClientConnectionManager;
import p008cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import p008cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import p008cz.msebera.android.httpclient.conn.ManagedClientConnection;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import p008cz.msebera.android.httpclient.impl.DefaultConnectionReuseStrategy;
import p008cz.msebera.android.httpclient.impl.execchain.MinimalClientExec;
import p008cz.msebera.android.httpclient.params.BasicHttpParams;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.BasicHttpContext;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.protocol.HttpRequestExecutor;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.client.MinimalHttpClient */
class MinimalHttpClient extends CloseableHttpClient {
    /* access modifiers changed from: private */
    public final HttpClientConnectionManager connManager;
    private final HttpParams params = new BasicHttpParams();
    private final MinimalClientExec requestExecutor;

    public MinimalHttpClient(HttpClientConnectionManager connManager2) {
        this.connManager = (HttpClientConnectionManager) Args.notNull(connManager2, "HTTP connection manager");
        this.requestExecutor = new MinimalClientExec(new HttpRequestExecutor(), connManager2, DefaultConnectionReuseStrategy.INSTANCE, DefaultConnectionKeepAliveStrategy.INSTANCE);
    }

    /* access modifiers changed from: protected */
    public CloseableHttpResponse doExecute(HttpHost target, HttpRequest request, HttpContext context) throws IOException, ClientProtocolException {
        Args.notNull(target, "Target host");
        Args.notNull(request, "HTTP request");
        HttpExecutionAware execAware = null;
        if (request instanceof HttpExecutionAware) {
            execAware = (HttpExecutionAware) request;
        }
        try {
            HttpRequestWrapper wrapper = HttpRequestWrapper.wrap(request);
            HttpClientContext localcontext = HttpClientContext.adapt(context != null ? context : new BasicHttpContext());
            HttpRoute route = new HttpRoute(target);
            RequestConfig config = null;
            if (request instanceof Configurable) {
                config = ((Configurable) request).getConfig();
            }
            if (config != null) {
                localcontext.setRequestConfig(config);
            }
            return this.requestExecutor.execute(route, wrapper, localcontext, execAware);
        } catch (HttpException httpException) {
            throw new ClientProtocolException((Throwable) httpException);
        }
    }

    public HttpParams getParams() {
        return this.params;
    }

    public void close() {
        this.connManager.shutdown();
    }

    public ClientConnectionManager getConnectionManager() {
        return new ClientConnectionManager() {
            public void shutdown() {
                MinimalHttpClient.this.connManager.shutdown();
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
                MinimalHttpClient.this.connManager.closeIdleConnections(idletime, tunit);
            }

            public void closeExpiredConnections() {
                MinimalHttpClient.this.connManager.closeExpiredConnections();
            }
        };
    }
}
