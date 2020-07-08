package p008cz.msebera.android.httpclient.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import p008cz.msebera.android.httpclient.HttpClientConnection;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.conn.HttpClientConnectionManager */
public interface HttpClientConnectionManager {
    void closeExpiredConnections();

    void closeIdleConnections(long j, TimeUnit timeUnit);

    void connect(HttpClientConnection httpClientConnection, HttpRoute httpRoute, int i, HttpContext httpContext) throws IOException;

    void releaseConnection(HttpClientConnection httpClientConnection, Object obj, long j, TimeUnit timeUnit);

    ConnectionRequest requestConnection(HttpRoute httpRoute, Object obj);

    void routeComplete(HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpContext httpContext) throws IOException;

    void shutdown();

    void upgrade(HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpContext httpContext) throws IOException;
}
