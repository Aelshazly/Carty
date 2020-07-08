package p008cz.msebera.android.httpclient.impl.client;

import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.client.ServiceUnavailableRetryStrategy;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.client.DefaultServiceUnavailableRetryStrategy */
public class DefaultServiceUnavailableRetryStrategy implements ServiceUnavailableRetryStrategy {
    private final int maxRetries;
    private final long retryInterval;

    public DefaultServiceUnavailableRetryStrategy(int maxRetries2, int retryInterval2) {
        Args.positive(maxRetries2, "Max retries");
        Args.positive(retryInterval2, "Retry interval");
        this.maxRetries = maxRetries2;
        this.retryInterval = (long) retryInterval2;
    }

    public DefaultServiceUnavailableRetryStrategy() {
        this(1, 1000);
    }

    public boolean retryRequest(HttpResponse response, int executionCount, HttpContext context) {
        return executionCount <= this.maxRetries && response.getStatusLine().getStatusCode() == 503;
    }

    public long getRetryInterval() {
        return this.retryInterval;
    }
}
