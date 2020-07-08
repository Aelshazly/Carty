package p008cz.msebera.android.httpclient.conn;

import javax.net.ssl.SSLSession;
import p008cz.msebera.android.httpclient.HttpInetConnection;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.conn.HttpRoutedConnection */
public interface HttpRoutedConnection extends HttpInetConnection {
    HttpRoute getRoute();

    SSLSession getSSLSession();

    boolean isSecure();
}
