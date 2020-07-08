package p008cz.msebera.android.httpclient.impl.client;

import java.security.Principal;
import javax.net.ssl.SSLSession;
import p008cz.msebera.android.httpclient.HttpConnection;
import p008cz.msebera.android.httpclient.auth.AuthScheme;
import p008cz.msebera.android.httpclient.auth.AuthState;
import p008cz.msebera.android.httpclient.auth.Credentials;
import p008cz.msebera.android.httpclient.client.UserTokenHandler;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.impl.client.DefaultUserTokenHandler */
public class DefaultUserTokenHandler implements UserTokenHandler {
    public static final DefaultUserTokenHandler INSTANCE = new DefaultUserTokenHandler();

    public Object getUserToken(HttpContext context) {
        HttpClientContext clientContext = HttpClientContext.adapt(context);
        Principal userPrincipal = null;
        AuthState targetAuthState = clientContext.getTargetAuthState();
        if (targetAuthState != null) {
            userPrincipal = getAuthPrincipal(targetAuthState);
            if (userPrincipal == null) {
                userPrincipal = getAuthPrincipal(clientContext.getProxyAuthState());
            }
        }
        if (userPrincipal != null) {
            return userPrincipal;
        }
        HttpConnection conn = clientContext.getConnection();
        if (!conn.isOpen() || !(conn instanceof ManagedHttpClientConnection)) {
            return userPrincipal;
        }
        SSLSession sslsession = ((ManagedHttpClientConnection) conn).getSSLSession();
        if (sslsession != null) {
            return sslsession.getLocalPrincipal();
        }
        return userPrincipal;
    }

    private static Principal getAuthPrincipal(AuthState authState) {
        AuthScheme scheme = authState.getAuthScheme();
        if (scheme != null && scheme.isComplete() && scheme.isConnectionBased()) {
            Credentials creds = authState.getCredentials();
            if (creds != null) {
                return creds.getUserPrincipal();
            }
        }
        return null;
    }
}
