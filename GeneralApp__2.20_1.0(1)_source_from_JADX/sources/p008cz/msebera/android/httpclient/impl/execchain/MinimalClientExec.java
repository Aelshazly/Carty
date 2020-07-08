package p008cz.msebera.android.httpclient.impl.execchain;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import p008cz.msebera.android.httpclient.ConnectionReuseStrategy;
import p008cz.msebera.android.httpclient.HttpClientConnection;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.client.config.RequestConfig;
import p008cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import p008cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import p008cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.client.protocol.RequestClientConnControl;
import p008cz.msebera.android.httpclient.client.utils.URIUtils;
import p008cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy;
import p008cz.msebera.android.httpclient.conn.ConnectionRequest;
import p008cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.impl.conn.ConnectionShutdownException;
import p008cz.msebera.android.httpclient.protocol.HttpProcessor;
import p008cz.msebera.android.httpclient.protocol.HttpRequestExecutor;
import p008cz.msebera.android.httpclient.protocol.ImmutableHttpProcessor;
import p008cz.msebera.android.httpclient.protocol.RequestContent;
import p008cz.msebera.android.httpclient.protocol.RequestTargetHost;
import p008cz.msebera.android.httpclient.protocol.RequestUserAgent;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.VersionInfo;

/* renamed from: cz.msebera.android.httpclient.impl.execchain.MinimalClientExec */
public class MinimalClientExec implements ClientExecChain {
    private final HttpClientConnectionManager connManager;
    private final HttpProcessor httpProcessor;
    private final ConnectionKeepAliveStrategy keepAliveStrategy;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private final HttpRequestExecutor requestExecutor;
    private final ConnectionReuseStrategy reuseStrategy;

    public MinimalClientExec(HttpRequestExecutor requestExecutor2, HttpClientConnectionManager connManager2, ConnectionReuseStrategy reuseStrategy2, ConnectionKeepAliveStrategy keepAliveStrategy2) {
        Args.notNull(requestExecutor2, "HTTP request executor");
        Args.notNull(connManager2, "Client connection manager");
        Args.notNull(reuseStrategy2, "Connection reuse strategy");
        Args.notNull(keepAliveStrategy2, "Connection keep alive strategy");
        this.httpProcessor = new ImmutableHttpProcessor(new RequestContent(), new RequestTargetHost(), new RequestClientConnControl(), new RequestUserAgent(VersionInfo.getUserAgent("Apache-HttpClient", "cz.msebera.android.httpclient.client", getClass())));
        this.requestExecutor = requestExecutor2;
        this.connManager = connManager2;
        this.reuseStrategy = reuseStrategy2;
        this.keepAliveStrategy = keepAliveStrategy2;
    }

    static void rewriteRequestURI(HttpRequestWrapper request, HttpRoute route) throws ProtocolException {
        URI uri;
        try {
            URI uri2 = request.getURI();
            if (uri2 != null) {
                if (uri2.isAbsolute()) {
                    uri = URIUtils.rewriteURI(uri2, null, true);
                } else {
                    uri = URIUtils.rewriteURI(uri2);
                }
                request.setURI(uri);
            }
        } catch (URISyntaxException ex) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid URI: ");
            sb.append(request.getRequestLine().getUri());
            throw new ProtocolException(sb.toString(), ex);
        }
    }

    public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware) throws IOException, HttpException {
        HttpRoute httpRoute = route;
        HttpRequestWrapper httpRequestWrapper = request;
        HttpClientContext httpClientContext = context;
        HttpExecutionAware httpExecutionAware = execAware;
        Args.notNull(httpRoute, "HTTP route");
        Args.notNull(httpRequestWrapper, "HTTP request");
        Args.notNull(httpClientContext, "HTTP context");
        rewriteRequestURI(httpRequestWrapper, httpRoute);
        ConnectionRequest connRequest = this.connManager.requestConnection(httpRoute, null);
        String str = "Request aborted";
        if (httpExecutionAware != null) {
            if (!execAware.isAborted()) {
                httpExecutionAware.setCancellable(connRequest);
            } else {
                connRequest.cancel();
                throw new RequestAbortedException(str);
            }
        }
        RequestConfig config = context.getRequestConfig();
        try {
            int timeout = config.getConnectionRequestTimeout();
            HttpClientConnection managedConn = connRequest.get(timeout > 0 ? (long) timeout : 0, TimeUnit.MILLISECONDS);
            ConnectionHolder releaseTrigger = new ConnectionHolder(this.log, this.connManager, managedConn);
            if (httpExecutionAware != null) {
                try {
                    if (!execAware.isAborted()) {
                        httpExecutionAware.setCancellable(releaseTrigger);
                    } else {
                        releaseTrigger.close();
                        throw new RequestAbortedException(str);
                    }
                } catch (ConnectionShutdownException ex) {
                    InterruptedIOException ioex = new InterruptedIOException("Connection has been shut down");
                    ioex.initCause(ex);
                    throw ioex;
                } catch (HttpException ex2) {
                    releaseTrigger.abortConnection();
                    throw ex2;
                } catch (IOException ex3) {
                    releaseTrigger.abortConnection();
                    throw ex3;
                } catch (RuntimeException ex4) {
                    releaseTrigger.abortConnection();
                    throw ex4;
                }
            }
            if (!managedConn.isOpen()) {
                int timeout2 = config.getConnectTimeout();
                this.connManager.connect(managedConn, httpRoute, timeout2 > 0 ? timeout2 : 0, httpClientContext);
                this.connManager.routeComplete(managedConn, httpRoute, httpClientContext);
            }
            int timeout3 = config.getSocketTimeout();
            if (timeout3 >= 0) {
                managedConn.setSocketTimeout(timeout3);
            }
            HttpHost target = null;
            HttpRequest original = request.getOriginal();
            if (original instanceof HttpUriRequest) {
                URI uri = ((HttpUriRequest) original).getURI();
                if (uri.isAbsolute()) {
                    int i = timeout3;
                    target = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
                }
            }
            if (target == null) {
                target = route.getTargetHost();
            }
            httpClientContext.setAttribute("http.target_host", target);
            httpClientContext.setAttribute("http.request", httpRequestWrapper);
            httpClientContext.setAttribute("http.connection", managedConn);
            httpClientContext.setAttribute("http.route", httpRoute);
            this.httpProcessor.process(httpRequestWrapper, httpClientContext);
            HttpResponse response = this.requestExecutor.execute(httpRequestWrapper, managedConn, httpClientContext);
            this.httpProcessor.process(response, httpClientContext);
            if (this.reuseStrategy.keepAlive(response, httpClientContext)) {
                releaseTrigger.setValidFor(this.keepAliveStrategy.getKeepAliveDuration(response, httpClientContext), TimeUnit.MILLISECONDS);
                releaseTrigger.markReusable();
            } else {
                releaseTrigger.markNonReusable();
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                if (entity.isStreaming()) {
                    return new HttpResponseProxy(response, releaseTrigger);
                }
            }
            releaseTrigger.releaseConnection();
            return new HttpResponseProxy(response, null);
        } catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
            throw new RequestAbortedException(str, interrupted);
        } catch (ExecutionException ex5) {
            Throwable cause = ex5.getCause();
            if (cause == 0) {
                cause = ex5;
            }
            throw new RequestAbortedException("Request execution failed", cause);
        }
    }
}
