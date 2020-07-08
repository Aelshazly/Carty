package p008cz.msebera.android.httpclient.client.protocol;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpRequestInterceptor;
import p008cz.msebera.android.httpclient.auth.AuthProtocolState;
import p008cz.msebera.android.httpclient.auth.AuthScheme;
import p008cz.msebera.android.httpclient.auth.AuthScope;
import p008cz.msebera.android.httpclient.auth.AuthState;
import p008cz.msebera.android.httpclient.auth.Credentials;
import p008cz.msebera.android.httpclient.client.AuthCache;
import p008cz.msebera.android.httpclient.client.CredentialsProvider;
import p008cz.msebera.android.httpclient.conn.routing.RouteInfo;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.client.protocol.RequestAuthCache */
public class RequestAuthCache implements HttpRequestInterceptor {
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        Args.notNull(context, "HTTP context");
        HttpClientContext clientContext = HttpClientContext.adapt(context);
        AuthCache authCache = clientContext.getAuthCache();
        if (authCache == null) {
            this.log.debug("Auth cache not set in the context");
            return;
        }
        CredentialsProvider credsProvider = clientContext.getCredentialsProvider();
        if (credsProvider == null) {
            this.log.debug("Credentials provider not set in the context");
            return;
        }
        RouteInfo route = clientContext.getHttpRoute();
        if (route == null) {
            this.log.debug("Route info not set in the context");
            return;
        }
        HttpHost target = clientContext.getTargetHost();
        if (target == null) {
            this.log.debug("Target host not set in the context");
            return;
        }
        if (target.getPort() < 0) {
            target = new HttpHost(target.getHostName(), route.getTargetHost().getPort(), target.getSchemeName());
        }
        AuthState targetState = clientContext.getTargetAuthState();
        if (targetState != null && targetState.getState() == AuthProtocolState.UNCHALLENGED) {
            AuthScheme authScheme = authCache.get(target);
            if (authScheme != null) {
                doPreemptiveAuth(target, authScheme, targetState, credsProvider);
            }
        }
        HttpHost proxy = route.getProxyHost();
        AuthState proxyState = clientContext.getProxyAuthState();
        if (!(proxy == null || proxyState == null || proxyState.getState() != AuthProtocolState.UNCHALLENGED)) {
            AuthScheme authScheme2 = authCache.get(proxy);
            if (authScheme2 != null) {
                doPreemptiveAuth(proxy, authScheme2, proxyState, credsProvider);
            }
        }
    }

    private void doPreemptiveAuth(HttpHost host, AuthScheme authScheme, AuthState authState, CredentialsProvider credsProvider) {
        String schemeName = authScheme.getSchemeName();
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Re-using cached '");
            sb.append(schemeName);
            sb.append("' auth scheme for ");
            sb.append(host);
            httpClientAndroidLog.debug(sb.toString());
        }
        Credentials creds = credsProvider.getCredentials(new AuthScope(host, AuthScope.ANY_REALM, schemeName));
        if (creds != null) {
            if ("BASIC".equalsIgnoreCase(authScheme.getSchemeName())) {
                authState.setState(AuthProtocolState.CHALLENGED);
            } else {
                authState.setState(AuthProtocolState.SUCCESS);
            }
            authState.update(authScheme, creds);
            return;
        }
        this.log.debug("No credentials for preemptive authentication");
    }
}
