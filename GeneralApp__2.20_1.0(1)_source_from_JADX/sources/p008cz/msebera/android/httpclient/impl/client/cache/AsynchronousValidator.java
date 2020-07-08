package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.RejectedExecutionException;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import p008cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.AsynchronousValidator */
class AsynchronousValidator implements Closeable {
    private final CacheKeyGenerator cacheKeyGenerator;
    private final FailureCache failureCache;
    public HttpClientAndroidLog log;
    private final Set<String> queued;
    private final SchedulingStrategy schedulingStrategy;

    public AsynchronousValidator(CacheConfig config) {
        this((SchedulingStrategy) new ImmediateSchedulingStrategy(config));
    }

    AsynchronousValidator(SchedulingStrategy schedulingStrategy2) {
        this.log = new HttpClientAndroidLog(getClass());
        this.schedulingStrategy = schedulingStrategy2;
        this.queued = new HashSet();
        this.cacheKeyGenerator = new CacheKeyGenerator();
        this.failureCache = new DefaultFailureCache();
    }

    public void close() throws IOException {
        this.schedulingStrategy.close();
    }

    public synchronized void revalidateCacheEntry(CachingExec cachingExec, HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware, HttpCacheEntry entry) {
        synchronized (this) {
            String uri = this.cacheKeyGenerator.getVariantURI(context.getTargetHost(), request, entry);
            if (!this.queued.contains(uri)) {
                AsynchronousValidationRequest asynchronousValidationRequest = new AsynchronousValidationRequest(this, cachingExec, route, request, context, execAware, entry, uri, this.failureCache.getErrorCount(uri));
                try {
                    this.schedulingStrategy.schedule(asynchronousValidationRequest);
                    this.queued.add(uri);
                } catch (RejectedExecutionException ree) {
                    HttpClientAndroidLog httpClientAndroidLog = this.log;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Revalidation for [");
                    sb.append(uri);
                    sb.append("] not scheduled: ");
                    sb.append(ree);
                    httpClientAndroidLog.debug(sb.toString());
                }
            }
        }
        return;
    }

    /* access modifiers changed from: 0000 */
    public synchronized void markComplete(String identifier) {
        this.queued.remove(identifier);
    }

    /* access modifiers changed from: 0000 */
    public void jobSuccessful(String identifier) {
        this.failureCache.resetErrorCount(identifier);
    }

    /* access modifiers changed from: 0000 */
    public void jobFailed(String identifier) {
        this.failureCache.increaseErrorCount(identifier);
    }

    /* access modifiers changed from: 0000 */
    public Set<String> getScheduledIdentifiers() {
        return Collections.unmodifiableSet(this.queued);
    }
}
