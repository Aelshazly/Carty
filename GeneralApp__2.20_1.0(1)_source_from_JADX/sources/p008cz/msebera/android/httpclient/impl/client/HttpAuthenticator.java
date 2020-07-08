package p008cz.msebera.android.httpclient.impl.client;

import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.auth.AuthState;
import p008cz.msebera.android.httpclient.client.AuthenticationStrategy;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.client.HttpAuthenticator */
public class HttpAuthenticator extends p008cz.msebera.android.httpclient.impl.auth.HttpAuthenticator {
    public HttpAuthenticator(HttpClientAndroidLog log) {
        super(log);
    }

    public HttpAuthenticator() {
    }

    public boolean authenticate(HttpHost host, HttpResponse response, AuthenticationStrategy authStrategy, AuthState authState, HttpContext context) {
        return handleAuthChallenge(host, response, authStrategy, authState, context);
    }
}
