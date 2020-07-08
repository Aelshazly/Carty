package p008cz.msebera.android.httpclient.client;

import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.client.ServiceUnavailableRetryStrategy */
public interface ServiceUnavailableRetryStrategy {
    long getRetryInterval();

    boolean retryRequest(HttpResponse httpResponse, int i, HttpContext httpContext);
}
