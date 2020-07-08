package p008cz.msebera.android.httpclient.client.params;

import java.net.InetAddress;
import java.util.Collection;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.auth.params.AuthPNames;
import p008cz.msebera.android.httpclient.client.config.RequestConfig;
import p008cz.msebera.android.httpclient.conn.params.ConnRoutePNames;
import p008cz.msebera.android.httpclient.params.CoreConnectionPNames;
import p008cz.msebera.android.httpclient.params.CoreProtocolPNames;
import p008cz.msebera.android.httpclient.params.HttpParams;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.client.params.HttpClientParamConfig */
public final class HttpClientParamConfig {
    private HttpClientParamConfig() {
    }

    public static RequestConfig getRequestConfig(HttpParams params) {
        return RequestConfig.custom().setSocketTimeout(params.getIntParameter(CoreConnectionPNames.SO_TIMEOUT, 0)).setStaleConnectionCheckEnabled(params.getBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, true)).setConnectTimeout(params.getIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 0)).setExpectContinueEnabled(params.getBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false)).setProxy((HttpHost) params.getParameter(ConnRoutePNames.DEFAULT_PROXY)).setLocalAddress((InetAddress) params.getParameter(ConnRoutePNames.LOCAL_ADDRESS)).setProxyPreferredAuthSchemes((Collection) params.getParameter(AuthPNames.PROXY_AUTH_PREF)).setTargetPreferredAuthSchemes((Collection) params.getParameter(AuthPNames.TARGET_AUTH_PREF)).setAuthenticationEnabled(params.getBooleanParameter(ClientPNames.HANDLE_AUTHENTICATION, true)).setCircularRedirectsAllowed(params.getBooleanParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, false)).setConnectionRequestTimeout((int) params.getLongParameter("http.conn-manager.timeout", 0)).setCookieSpec((String) params.getParameter(ClientPNames.COOKIE_POLICY)).setMaxRedirects(params.getIntParameter(ClientPNames.MAX_REDIRECTS, 50)).setRedirectsEnabled(params.getBooleanParameter(ClientPNames.HANDLE_REDIRECTS, true)).setRelativeRedirectsAllowed(!params.getBooleanParameter(ClientPNames.REJECT_RELATIVE_REDIRECT, false)).build();
    }
}
