package p008cz.msebera.android.httpclient.impl.client;

import java.io.IOException;
import java.net.Socket;
import p008cz.msebera.android.httpclient.ConnectionReuseStrategy;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.auth.AuthSchemeRegistry;
import p008cz.msebera.android.httpclient.auth.AuthScope;
import p008cz.msebera.android.httpclient.auth.AuthState;
import p008cz.msebera.android.httpclient.auth.Credentials;
import p008cz.msebera.android.httpclient.client.config.RequestConfig;
import p008cz.msebera.android.httpclient.client.params.HttpClientParamConfig;
import p008cz.msebera.android.httpclient.client.protocol.RequestClientConnControl;
import p008cz.msebera.android.httpclient.config.ConnectionConfig;
import p008cz.msebera.android.httpclient.conn.HttpConnectionFactory;
import p008cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.conn.routing.RouteInfo.LayerType;
import p008cz.msebera.android.httpclient.conn.routing.RouteInfo.TunnelType;
import p008cz.msebera.android.httpclient.entity.BufferedHttpEntity;
import p008cz.msebera.android.httpclient.impl.DefaultConnectionReuseStrategy;
import p008cz.msebera.android.httpclient.impl.auth.BasicSchemeFactory;
import p008cz.msebera.android.httpclient.impl.auth.DigestSchemeFactory;
import p008cz.msebera.android.httpclient.impl.auth.HttpAuthenticator;
import p008cz.msebera.android.httpclient.impl.auth.NTLMSchemeFactory;
import p008cz.msebera.android.httpclient.impl.conn.ManagedHttpClientConnectionFactory;
import p008cz.msebera.android.httpclient.impl.execchain.TunnelRefusedException;
import p008cz.msebera.android.httpclient.message.BasicHttpRequest;
import p008cz.msebera.android.httpclient.params.BasicHttpParams;
import p008cz.msebera.android.httpclient.params.HttpParamConfig;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.BasicHttpContext;
import p008cz.msebera.android.httpclient.protocol.HttpProcessor;
import p008cz.msebera.android.httpclient.protocol.HttpRequestExecutor;
import p008cz.msebera.android.httpclient.protocol.ImmutableHttpProcessor;
import p008cz.msebera.android.httpclient.protocol.RequestTargetHost;
import p008cz.msebera.android.httpclient.protocol.RequestUserAgent;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.EntityUtils;

/* renamed from: cz.msebera.android.httpclient.impl.client.ProxyClient */
public class ProxyClient {
    private final AuthSchemeRegistry authSchemeRegistry;
    private final HttpAuthenticator authenticator;
    private final HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory;
    private final ConnectionConfig connectionConfig;
    private final HttpProcessor httpProcessor;
    private final AuthState proxyAuthState;
    private final ProxyAuthenticationStrategy proxyAuthStrategy;
    private final RequestConfig requestConfig;
    private final HttpRequestExecutor requestExec;
    private final ConnectionReuseStrategy reuseStrategy;

    public ProxyClient(HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory2, ConnectionConfig connectionConfig2, RequestConfig requestConfig2) {
        this.connFactory = connFactory2 != null ? connFactory2 : ManagedHttpClientConnectionFactory.INSTANCE;
        this.connectionConfig = connectionConfig2 != null ? connectionConfig2 : ConnectionConfig.DEFAULT;
        this.requestConfig = requestConfig2 != null ? requestConfig2 : RequestConfig.DEFAULT;
        this.httpProcessor = new ImmutableHttpProcessor(new RequestTargetHost(), new RequestClientConnControl(), new RequestUserAgent());
        this.requestExec = new HttpRequestExecutor();
        this.proxyAuthStrategy = new ProxyAuthenticationStrategy();
        this.authenticator = new HttpAuthenticator();
        this.proxyAuthState = new AuthState();
        this.authSchemeRegistry = new AuthSchemeRegistry();
        this.authSchemeRegistry.register("Basic", new BasicSchemeFactory());
        this.authSchemeRegistry.register("Digest", new DigestSchemeFactory());
        this.authSchemeRegistry.register("NTLM", new NTLMSchemeFactory());
        this.reuseStrategy = new DefaultConnectionReuseStrategy();
    }

    @Deprecated
    public ProxyClient(HttpParams params) {
        this(null, HttpParamConfig.getConnectionConfig(params), HttpClientParamConfig.getRequestConfig(params));
    }

    public ProxyClient(RequestConfig requestConfig2) {
        this(null, null, requestConfig2);
    }

    public ProxyClient() {
        this(null, null, null);
    }

    @Deprecated
    public HttpParams getParams() {
        return new BasicHttpParams();
    }

    @Deprecated
    public AuthSchemeRegistry getAuthSchemeRegistry() {
        return this.authSchemeRegistry;
    }

    public Socket tunnel(HttpHost proxy, HttpHost target, Credentials credentials) throws IOException, HttpException {
        HttpHost host;
        HttpResponse response;
        HttpHost httpHost = proxy;
        HttpHost httpHost2 = target;
        Credentials credentials2 = credentials;
        Args.notNull(httpHost, "Proxy host");
        Args.notNull(httpHost2, "Target host");
        Args.notNull(credentials2, "Credentials");
        HttpHost host2 = target;
        if (host2.getPort() <= 0) {
            host = new HttpHost(host2.getHostName(), 80, host2.getSchemeName());
        } else {
            host = host2;
        }
        HttpRoute httpRoute = new HttpRoute(host, this.requestConfig.getLocalAddress(), proxy, false, TunnelType.TUNNELLED, LayerType.PLAIN);
        HttpRoute route = httpRoute;
        ManagedHttpClientConnection conn = (ManagedHttpClientConnection) this.connFactory.create(route, this.connectionConfig);
        BasicHttpContext basicHttpContext = new BasicHttpContext();
        HttpRequest connect = new BasicHttpRequest("CONNECT", host.toHostString(), HttpVersion.HTTP_1_1);
        BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(httpHost), credentials2);
        basicHttpContext.setAttribute("http.target_host", httpHost2);
        basicHttpContext.setAttribute("http.connection", conn);
        basicHttpContext.setAttribute("http.request", connect);
        basicHttpContext.setAttribute("http.route", route);
        basicHttpContext.setAttribute("http.auth.proxy-scope", this.proxyAuthState);
        basicHttpContext.setAttribute("http.auth.credentials-provider", credsProvider);
        basicHttpContext.setAttribute("http.authscheme-registry", this.authSchemeRegistry);
        basicHttpContext.setAttribute("http.request-config", this.requestConfig);
        this.requestExec.preProcess(connect, this.httpProcessor, basicHttpContext);
        while (true) {
            if (!conn.isOpen()) {
                conn.bind(new Socket(proxy.getHostName(), proxy.getPort()));
            }
            this.authenticator.generateAuthResponse(connect, this.proxyAuthState, basicHttpContext);
            HttpResponse response2 = this.requestExec.execute(connect, conn, basicHttpContext);
            int status = response2.getStatusLine().getStatusCode();
            if (status >= 200) {
                int i = status;
                HttpResponse response3 = response2;
                if (!this.authenticator.isAuthenticationRequested(proxy, response2, this.proxyAuthStrategy, this.proxyAuthState, basicHttpContext)) {
                    response = response3;
                    break;
                }
                if (!this.authenticator.handleAuthChallenge(proxy, response3, this.proxyAuthStrategy, this.proxyAuthState, basicHttpContext)) {
                    response = response3;
                    break;
                }
                HttpResponse response4 = response3;
                if (this.reuseStrategy.keepAlive(response4, basicHttpContext)) {
                    EntityUtils.consume(response4.getEntity());
                } else {
                    conn.close();
                }
                connect.removeHeaders("Proxy-Authorization");
            } else {
                HttpResponse response5 = response2;
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected response to CONNECT request: ");
                sb.append(response5.getStatusLine());
                throw new HttpException(sb.toString());
            }
        }
        if (response.getStatusLine().getStatusCode() <= 299) {
            return conn.getSocket();
        }
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            response.setEntity(new BufferedHttpEntity(entity));
        }
        conn.close();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("CONNECT refused by proxy: ");
        sb2.append(response.getStatusLine());
        throw new TunnelRefusedException(sb2.toString(), response);
    }
}
