package p008cz.msebera.android.httpclient.conn;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import p008cz.msebera.android.httpclient.HttpClientConnection;
import p008cz.msebera.android.httpclient.concurrent.Cancellable;

/* renamed from: cz.msebera.android.httpclient.conn.ConnectionRequest */
public interface ConnectionRequest extends Cancellable {
    HttpClientConnection get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, ConnectionPoolTimeoutException;
}
