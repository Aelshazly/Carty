package p008cz.msebera.android.httpclient.conn;

import java.util.concurrent.TimeUnit;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.conn.ClientConnectionRequest */
public interface ClientConnectionRequest {
    void abortRequest();

    ManagedClientConnection getConnection(long j, TimeUnit timeUnit) throws InterruptedException, ConnectionPoolTimeoutException;
}
