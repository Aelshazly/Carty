package p008cz.msebera.android.httpclient.impl.client.cache;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.ImmediateSchedulingStrategy */
public class ImmediateSchedulingStrategy implements SchedulingStrategy {
    private final ExecutorService executor;

    public ImmediateSchedulingStrategy(CacheConfig cacheConfig) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(cacheConfig.getAsynchronousWorkersCore(), cacheConfig.getAsynchronousWorkersMax(), (long) cacheConfig.getAsynchronousWorkerIdleLifetimeSecs(), TimeUnit.SECONDS, new ArrayBlockingQueue(cacheConfig.getRevalidationQueueSize()));
        this((ExecutorService) threadPoolExecutor);
    }

    ImmediateSchedulingStrategy(ExecutorService executor2) {
        this.executor = executor2;
    }

    public void schedule(AsynchronousValidationRequest revalidationRequest) {
        if (revalidationRequest != null) {
            this.executor.execute(revalidationRequest);
            return;
        }
        throw new IllegalArgumentException("AsynchronousValidationRequest may not be null");
    }

    public void close() {
        this.executor.shutdown();
    }

    /* access modifiers changed from: 0000 */
    public void awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        this.executor.awaitTermination(timeout, unit);
    }
}
