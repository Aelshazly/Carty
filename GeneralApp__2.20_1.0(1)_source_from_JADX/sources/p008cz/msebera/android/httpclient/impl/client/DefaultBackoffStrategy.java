package p008cz.msebera.android.httpclient.impl.client;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.client.ConnectionBackoffStrategy;

/* renamed from: cz.msebera.android.httpclient.impl.client.DefaultBackoffStrategy */
public class DefaultBackoffStrategy implements ConnectionBackoffStrategy {
    public boolean shouldBackoff(Throwable t) {
        return (t instanceof SocketTimeoutException) || (t instanceof ConnectException);
    }

    public boolean shouldBackoff(HttpResponse resp) {
        return resp.getStatusLine().getStatusCode() == 503;
    }
}
