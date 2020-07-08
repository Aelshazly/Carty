package p008cz.msebera.android.httpclient.client;

import java.io.IOException;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.client.HttpRequestRetryHandler */
public interface HttpRequestRetryHandler {
    boolean retryRequest(IOException iOException, int i, HttpContext httpContext);
}
