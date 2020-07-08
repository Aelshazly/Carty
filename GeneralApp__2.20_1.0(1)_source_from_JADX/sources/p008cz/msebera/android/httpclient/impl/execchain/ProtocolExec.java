package p008cz.msebera.android.httpclient.impl.execchain;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.auth.AuthScope;
import p008cz.msebera.android.httpclient.auth.UsernamePasswordCredentials;
import p008cz.msebera.android.httpclient.client.CredentialsProvider;
import p008cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import p008cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import p008cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import p008cz.msebera.android.httpclient.client.params.ClientPNames;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.client.utils.URIUtils;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.impl.client.BasicCredentialsProvider;
import p008cz.msebera.android.httpclient.protocol.HttpProcessor;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.execchain.ProtocolExec */
public class ProtocolExec implements ClientExecChain {
    private final HttpProcessor httpProcessor;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private final ClientExecChain requestExecutor;

    public ProtocolExec(ClientExecChain requestExecutor2, HttpProcessor httpProcessor2) {
        Args.notNull(requestExecutor2, "HTTP client request executor");
        Args.notNull(httpProcessor2, "HTTP protocol processor");
        this.requestExecutor = requestExecutor2;
        this.httpProcessor = httpProcessor2;
    }

    /* access modifiers changed from: 0000 */
    public void rewriteRequestURI(HttpRequestWrapper request, HttpRoute route) throws ProtocolException {
        URI uri;
        try {
            URI uri2 = request.getURI();
            if (uri2 != null) {
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
            }
        } catch (URISyntaxException ex) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid URI: ");
            sb.append(request.getRequestLine().getUri());
            throw new ProtocolException(sb.toString(), ex);
        }
    }

    public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware) throws IOException, HttpException {
        Args.notNull(route, "HTTP route");
        Args.notNull(request, "HTTP request");
        Args.notNull(context, "HTTP context");
        HttpRequest original = request.getOriginal();
        URI uri = null;
        if (original instanceof HttpUriRequest) {
            uri = ((HttpUriRequest) original).getURI();
        } else {
            String uriString = original.getRequestLine().getUri();
            try {
                uri = URI.create(uriString);
            } catch (IllegalArgumentException ex) {
                if (this.log.isDebugEnabled()) {
                    HttpClientAndroidLog httpClientAndroidLog = this.log;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unable to parse '");
                    sb.append(uriString);
                    sb.append("' as a valid URI; ");
                    sb.append("request URI and Host header may be inconsistent");
                    httpClientAndroidLog.debug(sb.toString(), ex);
                }
            }
        }
        request.setURI(uri);
        rewriteRequestURI(request, route);
        HttpHost virtualHost = (HttpHost) request.getParams().getParameter(ClientPNames.VIRTUAL_HOST);
        if (virtualHost != null && virtualHost.getPort() == -1) {
            int port = route.getTargetHost().getPort();
            if (port != -1) {
                virtualHost = new HttpHost(virtualHost.getHostName(), port, virtualHost.getSchemeName());
            }
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Using virtual host");
                sb2.append(virtualHost);
                httpClientAndroidLog2.debug(sb2.toString());
            }
        }
        HttpHost target = null;
        if (virtualHost != null) {
            target = virtualHost;
        } else if (!(uri == null || !uri.isAbsolute() || uri.getHost() == null)) {
            target = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
        }
        if (target == null) {
            target = route.getTargetHost();
        }
        if (uri != null) {
            String userinfo = uri.getUserInfo();
            if (userinfo != null) {
                CredentialsProvider credsProvider = context.getCredentialsProvider();
                if (credsProvider == null) {
                    credsProvider = new BasicCredentialsProvider();
                    context.setCredentialsProvider(credsProvider);
                }
                credsProvider.setCredentials(new AuthScope(target), new UsernamePasswordCredentials(userinfo));
            }
        }
        context.setAttribute("http.target_host", target);
        context.setAttribute("http.route", route);
        context.setAttribute("http.request", request);
        this.httpProcessor.process(request, context);
        CloseableHttpResponse response = this.requestExecutor.execute(route, request, context, execAware);
        try {
            context.setAttribute("http.response", response);
            this.httpProcessor.process(response, context);
            return response;
        } catch (RuntimeException ex2) {
            response.close();
            throw ex2;
        } catch (IOException ex3) {
            response.close();
            throw ex3;
        } catch (HttpException ex4) {
            response.close();
            throw ex4;
        }
    }
}
