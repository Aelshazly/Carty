package p008cz.msebera.android.httpclient.client.protocol;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpResponseInterceptor;
import p008cz.msebera.android.httpclient.auth.AuthProtocolState;
import p008cz.msebera.android.httpclient.auth.AuthScheme;
import p008cz.msebera.android.httpclient.auth.AuthState;
import p008cz.msebera.android.httpclient.client.AuthCache;
import p008cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.impl.client.BasicAuthCache;
import p008cz.msebera.android.httpclient.protocol.ExecutionContext;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.client.protocol.ResponseAuthCache */
public class ResponseAuthCache implements HttpResponseInterceptor {
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    /* renamed from: cz.msebera.android.httpclient.client.protocol.ResponseAuthCache$1 */
    static /* synthetic */ class C09671 {
        static final /* synthetic */ int[] $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState = new int[AuthProtocolState.values().length];

        static {
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.CHALLENGED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        Args.notNull(response, "HTTP request");
        Args.notNull(context, "HTTP context");
        String str = "http.auth.auth-cache";
        AuthCache authCache = (AuthCache) context.getAttribute(str);
        HttpHost target = (HttpHost) context.getAttribute("http.target_host");
        AuthState targetState = (AuthState) context.getAttribute("http.auth.target-scope");
        if (!(target == null || targetState == null)) {
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog httpClientAndroidLog = this.log;
                StringBuilder sb = new StringBuilder();
                sb.append("Target auth state: ");
                sb.append(targetState.getState());
                httpClientAndroidLog.debug(sb.toString());
            }
            if (isCachable(targetState)) {
                SchemeRegistry schemeRegistry = (SchemeRegistry) context.getAttribute(ClientContext.SCHEME_REGISTRY);
                if (target.getPort() < 0) {
                    target = new HttpHost(target.getHostName(), schemeRegistry.getScheme(target).resolvePort(target.getPort()), target.getSchemeName());
                }
                if (authCache == null) {
                    authCache = new BasicAuthCache();
                    context.setAttribute(str, authCache);
                }
                int i = C09671.$SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[targetState.getState().ordinal()];
                if (i == 1) {
                    cache(authCache, target, targetState.getAuthScheme());
                } else if (i == 2) {
                    uncache(authCache, target, targetState.getAuthScheme());
                }
            }
        }
        HttpHost proxy = (HttpHost) context.getAttribute(ExecutionContext.HTTP_PROXY_HOST);
        AuthState proxyState = (AuthState) context.getAttribute("http.auth.proxy-scope");
        if (proxy != null && proxyState != null) {
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Proxy auth state: ");
                sb2.append(proxyState.getState());
                httpClientAndroidLog2.debug(sb2.toString());
            }
            if (isCachable(proxyState)) {
                if (authCache == null) {
                    authCache = new BasicAuthCache();
                    context.setAttribute(str, authCache);
                }
                int i2 = C09671.$SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[proxyState.getState().ordinal()];
                if (i2 == 1) {
                    cache(authCache, proxy, proxyState.getAuthScheme());
                } else if (i2 == 2) {
                    uncache(authCache, proxy, proxyState.getAuthScheme());
                }
            }
        }
    }

    private boolean isCachable(AuthState authState) {
        AuthScheme authScheme = authState.getAuthScheme();
        boolean z = false;
        if (authScheme == null || !authScheme.isComplete()) {
            return false;
        }
        String schemeName = authScheme.getSchemeName();
        if (schemeName.equalsIgnoreCase("Basic") || schemeName.equalsIgnoreCase("Digest")) {
            z = true;
        }
        return z;
    }

    private void cache(AuthCache authCache, HttpHost host, AuthScheme authScheme) {
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Caching '");
            sb.append(authScheme.getSchemeName());
            sb.append("' auth scheme for ");
            sb.append(host);
            httpClientAndroidLog.debug(sb.toString());
        }
        authCache.put(host, authScheme);
    }

    private void uncache(AuthCache authCache, HttpHost host, AuthScheme authScheme) {
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Removing from cache '");
            sb.append(authScheme.getSchemeName());
            sb.append("' auth scheme for ");
            sb.append(host);
            httpClientAndroidLog.debug(sb.toString());
        }
        authCache.remove(host);
    }
}
