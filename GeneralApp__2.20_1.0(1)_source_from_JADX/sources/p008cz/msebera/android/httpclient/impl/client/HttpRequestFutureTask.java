package p008cz.msebera.android.httpclient.impl.client;

import java.util.concurrent.FutureTask;
import p008cz.msebera.android.httpclient.client.methods.HttpUriRequest;

/* renamed from: cz.msebera.android.httpclient.impl.client.HttpRequestFutureTask */
public class HttpRequestFutureTask<V> extends FutureTask<V> {
    private final HttpRequestTaskCallable<V> callable;
    private final HttpUriRequest request;

    public HttpRequestFutureTask(HttpUriRequest request2, HttpRequestTaskCallable<V> httpCallable) {
        super(httpCallable);
        this.request = request2;
        this.callable = httpCallable;
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        this.callable.cancel();
        if (mayInterruptIfRunning) {
            this.request.abort();
        }
        return super.cancel(mayInterruptIfRunning);
    }

    public long scheduledTime() {
        return this.callable.getScheduled();
    }

    public long startedTime() {
        return this.callable.getStarted();
    }

    public long endedTime() {
        if (isDone()) {
            return this.callable.getEnded();
        }
        throw new IllegalStateException("Task is not done yet");
    }

    public long requestDuration() {
        if (isDone()) {
            return endedTime() - startedTime();
        }
        throw new IllegalStateException("Task is not done yet");
    }

    public long taskDuration() {
        if (isDone()) {
            return endedTime() - scheduledTime();
        }
        throw new IllegalStateException("Task is not done yet");
    }

    public String toString() {
        return this.request.getRequestLine().getUri();
    }
}
