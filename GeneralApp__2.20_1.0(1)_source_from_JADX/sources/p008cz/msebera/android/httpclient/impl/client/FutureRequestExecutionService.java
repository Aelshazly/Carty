package p008cz.msebera.android.httpclient.impl.client;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import p008cz.msebera.android.httpclient.client.HttpClient;
import p008cz.msebera.android.httpclient.client.ResponseHandler;
import p008cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import p008cz.msebera.android.httpclient.concurrent.FutureCallback;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.impl.client.FutureRequestExecutionService */
public class FutureRequestExecutionService implements Closeable {
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final ExecutorService executorService;
    private final HttpClient httpclient;
    private final FutureRequestExecutionMetrics metrics = new FutureRequestExecutionMetrics();

    public FutureRequestExecutionService(HttpClient httpclient2, ExecutorService executorService2) {
        this.httpclient = httpclient2;
        this.executorService = executorService2;
    }

    public <T> HttpRequestFutureTask<T> execute(HttpUriRequest request, HttpContext context, ResponseHandler<T> responseHandler) {
        return execute(request, context, responseHandler, null);
    }

    public <T> HttpRequestFutureTask<T> execute(HttpUriRequest request, HttpContext context, ResponseHandler<T> responseHandler, FutureCallback<T> callback) {
        if (!this.closed.get()) {
            this.metrics.getScheduledConnections().incrementAndGet();
            HttpRequestTaskCallable httpRequestTaskCallable = new HttpRequestTaskCallable(this.httpclient, request, context, responseHandler, callback, this.metrics);
            HttpRequestFutureTask<T> httpRequestFutureTask = new HttpRequestFutureTask<>(request, httpRequestTaskCallable);
            this.executorService.execute(httpRequestFutureTask);
            return httpRequestFutureTask;
        }
        throw new IllegalStateException("Close has been called on this httpclient instance.");
    }

    public FutureRequestExecutionMetrics metrics() {
        return this.metrics;
    }

    public void close() throws IOException {
        this.closed.set(true);
        this.executorService.shutdownNow();
        HttpClient httpClient = this.httpclient;
        if (httpClient instanceof Closeable) {
            ((Closeable) httpClient).close();
        }
    }
}
