package p008cz.msebera.android.httpclient.impl.client.cache;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.ExponentialBackOffSchedulingStrategy */
public class ExponentialBackOffSchedulingStrategy implements SchedulingStrategy {
    public static final long DEFAULT_BACK_OFF_RATE = 10;
    public static final long DEFAULT_INITIAL_EXPIRY_IN_MILLIS = TimeUnit.SECONDS.toMillis(6);
    public static final long DEFAULT_MAX_EXPIRY_IN_MILLIS = TimeUnit.SECONDS.toMillis(86400);
    private final long backOffRate;
    private final ScheduledExecutorService executor;
    private final long initialExpiryInMillis;
    private final long maxExpiryInMillis;

    public ExponentialBackOffSchedulingStrategy(CacheConfig cacheConfig) {
        this(cacheConfig, 10, DEFAULT_INITIAL_EXPIRY_IN_MILLIS, DEFAULT_MAX_EXPIRY_IN_MILLIS);
    }

    public ExponentialBackOffSchedulingStrategy(CacheConfig cacheConfig, long backOffRate2, long initialExpiryInMillis2, long maxExpiryInMillis2) {
        this((ScheduledExecutorService) createThreadPoolFromCacheConfig(cacheConfig), backOffRate2, initialExpiryInMillis2, maxExpiryInMillis2);
    }

    private static ScheduledThreadPoolExecutor createThreadPoolFromCacheConfig(CacheConfig cacheConfig) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(cacheConfig.getAsynchronousWorkersMax());
        scheduledThreadPoolExecutor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
        return scheduledThreadPoolExecutor;
    }

    ExponentialBackOffSchedulingStrategy(ScheduledExecutorService executor2, long backOffRate2, long initialExpiryInMillis2, long maxExpiryInMillis2) {
        this.executor = (ScheduledExecutorService) checkNotNull("executor", executor2);
        this.backOffRate = checkNotNegative("backOffRate", backOffRate2);
        this.initialExpiryInMillis = checkNotNegative("initialExpiryInMillis", initialExpiryInMillis2);
        this.maxExpiryInMillis = checkNotNegative("maxExpiryInMillis", maxExpiryInMillis2);
    }

    public void schedule(AsynchronousValidationRequest revalidationRequest) {
        checkNotNull("revalidationRequest", revalidationRequest);
        this.executor.schedule(revalidationRequest, calculateDelayInMillis(revalidationRequest.getConsecutiveFailedAttempts()), TimeUnit.MILLISECONDS);
    }

    public void close() {
        this.executor.shutdown();
    }

    public long getBackOffRate() {
        return this.backOffRate;
    }

    public long getInitialExpiryInMillis() {
        return this.initialExpiryInMillis;
    }

    public long getMaxExpiryInMillis() {
        return this.maxExpiryInMillis;
    }

    /* access modifiers changed from: protected */
    public long calculateDelayInMillis(int consecutiveFailedAttempts) {
        if (consecutiveFailedAttempts > 0) {
            return Math.min((long) (((double) this.initialExpiryInMillis) * Math.pow((double) this.backOffRate, (double) (consecutiveFailedAttempts - 1))), this.maxExpiryInMillis);
        }
        return 0;
    }

    protected static <T> T checkNotNull(String parameterName, T value) {
        if (value != null) {
            return value;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(parameterName);
        sb.append(" may not be null");
        throw new IllegalArgumentException(sb.toString());
    }

    protected static long checkNotNegative(String parameterName, long value) {
        if (value >= 0) {
            return value;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(parameterName);
        sb.append(" may not be negative");
        throw new IllegalArgumentException(sb.toString());
    }
}
