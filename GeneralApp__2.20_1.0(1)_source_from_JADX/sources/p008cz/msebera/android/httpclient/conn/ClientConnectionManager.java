package p008cz.msebera.android.httpclient.conn;

import java.util.concurrent.TimeUnit;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.conn.ClientConnectionManager */
public interface ClientConnectionManager {
    void closeExpiredConnections();

    void closeIdleConnections(long j, TimeUnit timeUnit);

    SchemeRegistry getSchemeRegistry();

    void releaseConnection(ManagedClientConnection managedClientConnection, long j, TimeUnit timeUnit);

    ClientConnectionRequest requestConnection(HttpRoute httpRoute, Object obj);

    void shutdown();
}
