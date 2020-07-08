package p008cz.msebera.android.httpclient.impl.execchain;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.auth.AuthScheme;
import p008cz.msebera.android.httpclient.auth.AuthState;
import p008cz.msebera.android.httpclient.client.RedirectException;
import p008cz.msebera.android.httpclient.client.RedirectStrategy;
import p008cz.msebera.android.httpclient.client.config.RequestConfig;
import p008cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import p008cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import p008cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.client.utils.URIUtils;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.EntityUtils;

/* renamed from: cz.msebera.android.httpclient.impl.execchain.RedirectExec */
public class RedirectExec implements ClientExecChain {
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private final RedirectStrategy redirectStrategy;
    private final ClientExecChain requestExecutor;
    private final HttpRoutePlanner routePlanner;

    public RedirectExec(ClientExecChain requestExecutor2, HttpRoutePlanner routePlanner2, RedirectStrategy redirectStrategy2) {
        Args.notNull(requestExecutor2, "HTTP client request executor");
        Args.notNull(routePlanner2, "HTTP route planner");
        Args.notNull(redirectStrategy2, "HTTP redirect strategy");
        this.requestExecutor = requestExecutor2;
        this.routePlanner = routePlanner2;
        this.redirectStrategy = redirectStrategy2;
    }

    public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware) throws IOException, HttpException {
        CloseableHttpResponse response;
        HttpClientContext httpClientContext = context;
        Args.notNull(route, "HTTP route");
        Args.notNull(request, "HTTP request");
        Args.notNull(httpClientContext, "HTTP context");
        List<URI> redirectLocations = context.getRedirectLocations();
        if (redirectLocations != null) {
            redirectLocations.clear();
        }
        RequestConfig config = context.getRequestConfig();
        int maxRedirects = config.getMaxRedirects() > 0 ? config.getMaxRedirects() : 50;
        int redirectCount = 0;
        HttpRequestWrapper currentRequest = request;
        HttpRoute currentRoute = route;
        while (true) {
            response = this.requestExecutor.execute(currentRoute, currentRequest, httpClientContext, execAware);
            try {
                if (!config.isRedirectsEnabled() || !this.redirectStrategy.isRedirected(currentRequest, response, httpClientContext)) {
                    return response;
                }
                if (redirectCount < maxRedirects) {
                    redirectCount++;
                    HttpRequest redirect = this.redirectStrategy.getRedirect(currentRequest, response, httpClientContext);
                    if (!redirect.headerIterator().hasNext()) {
                        redirect.setHeaders(request.getOriginal().getAllHeaders());
                    }
                    currentRequest = HttpRequestWrapper.wrap(redirect);
                    if (currentRequest instanceof HttpEntityEnclosingRequest) {
                        RequestEntityProxy.enhance((HttpEntityEnclosingRequest) currentRequest);
                    }
                    URI uri = currentRequest.getURI();
                    HttpHost newTarget = URIUtils.extractHost(uri);
                    if (newTarget != null) {
                        if (!currentRoute.getTargetHost().equals(newTarget)) {
                            AuthState targetAuthState = context.getTargetAuthState();
                            if (targetAuthState != null) {
                                HttpUriRequest httpUriRequest = redirect;
                                this.log.debug("Resetting target auth state");
                                targetAuthState.reset();
                            } else {
                                HttpRequest redirect2 = redirect;
                            }
                            AuthState proxyAuthState = context.getProxyAuthState();
                            if (proxyAuthState != null) {
                                AuthScheme authScheme = proxyAuthState.getAuthScheme();
                                if (authScheme == null || !authScheme.isConnectionBased()) {
                                } else {
                                    AuthScheme authScheme2 = authScheme;
                                    this.log.debug("Resetting proxy auth state");
                                    proxyAuthState.reset();
                                }
                            }
                        } else {
                            HttpRequest redirect3 = redirect;
                        }
                        currentRoute = this.routePlanner.determineRoute(newTarget, currentRequest, httpClientContext);
                        if (this.log.isDebugEnabled()) {
                            HttpClientAndroidLog httpClientAndroidLog = this.log;
                            StringBuilder sb = new StringBuilder();
                            sb.append("Redirecting to '");
                            sb.append(uri);
                            sb.append("' via ");
                            sb.append(currentRoute);
                            httpClientAndroidLog.debug(sb.toString());
                        }
                        EntityUtils.consume(response.getEntity());
                        response.close();
                        HttpRoute httpRoute = route;
                        HttpRequestWrapper httpRequestWrapper = request;
                    } else {
                        HttpUriRequest httpUriRequest2 = redirect;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Redirect URI does not specify a valid host name: ");
                        sb2.append(uri);
                        throw new ProtocolException(sb2.toString());
                    }
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Maximum redirects (");
                    sb3.append(maxRedirects);
                    sb3.append(") exceeded");
                    throw new RedirectException(sb3.toString());
                }
            } catch (RuntimeException ex) {
                response.close();
                throw ex;
            } catch (IOException ex2) {
                response.close();
                throw ex2;
            } catch (HttpException e) {
                HttpException ex3 = e;
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException ioex) {
                    this.log.debug("I/O error while releasing connection", ioex);
                } catch (Throwable th) {
                    response.close();
                    throw th;
                }
                response.close();
                throw ex3;
            }
        }
        return response;
    }
}
