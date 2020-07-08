package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.Closeable;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.SchedulingStrategy */
public interface SchedulingStrategy extends Closeable {
    void schedule(AsynchronousValidationRequest asynchronousValidationRequest);
}
