package p008cz.msebera.android.httpclient.client.protocol;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.auth.AuthState;
import p008cz.msebera.android.httpclient.conn.HttpRoutedConnection;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.client.protocol.RequestProxyAuthentication */
public class RequestProxyAuthentication extends RequestAuthenticationBase {
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        Args.notNull(context, "HTTP context");
        if (!request.containsHeader("Proxy-Authorization")) {
            HttpRoutedConnection conn = (HttpRoutedConnection) context.getAttribute("http.connection");
            if (conn == null) {
                this.log.debug("HTTP connection not set in the context");
            } else if (!conn.getRoute().isTunnelled()) {
                AuthState authState = (AuthState) context.getAttribute("http.auth.proxy-scope");
                if (authState == null) {
                    this.log.debug("Proxy auth state not set in the context");
                    return;
                }
                if (this.log.isDebugEnabled()) {
                    HttpClientAndroidLog httpClientAndroidLog = this.log;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Proxy auth state: ");
                    sb.append(authState.getState());
                    httpClientAndroidLog.debug(sb.toString());
                }
                process(authState, request, context);
            }
        }
    }
}
