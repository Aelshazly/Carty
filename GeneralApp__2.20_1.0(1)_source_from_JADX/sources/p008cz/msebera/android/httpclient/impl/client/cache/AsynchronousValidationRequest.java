package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.IOException;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import p008cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import p008cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.AsynchronousValidationRequest */
class AsynchronousValidationRequest implements Runnable {
    private final HttpCacheEntry cacheEntry;
    private final CachingExec cachingExec;
    private final int consecutiveFailedAttempts;
    private final HttpClientContext context;
    private final HttpExecutionAware execAware;
    private final String identifier;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private final AsynchronousValidator parent;
    private final HttpRequestWrapper request;
    private final HttpRoute route;

    AsynchronousValidationRequest(AsynchronousValidator parent2, CachingExec cachingExec2, HttpRoute route2, HttpRequestWrapper request2, HttpClientContext context2, HttpExecutionAware execAware2, HttpCacheEntry cacheEntry2, String identifier2, int consecutiveFailedAttempts2) {
        this.parent = parent2;
        this.cachingExec = cachingExec2;
        this.route = route2;
        this.request = request2;
        this.context = context2;
        this.execAware = execAware2;
        this.cacheEntry = cacheEntry2;
        this.identifier = identifier2;
        this.consecutiveFailedAttempts = consecutiveFailedAttempts2;
    }

    public void run() {
        try {
            if (revalidateCacheEntry()) {
                this.parent.jobSuccessful(this.identifier);
            } else {
                this.parent.jobFailed(this.identifier);
            }
        } finally {
            this.parent.markComplete(this.identifier);
        }
    }

    /* access modifiers changed from: protected */
    public boolean revalidateCacheEntry() {
        CloseableHttpResponse httpResponse;
        try {
            httpResponse = this.cachingExec.revalidateCacheEntry(this.route, this.request, this.context, this.execAware, this.cacheEntry);
            boolean z = isNotServerError(httpResponse.getStatusLine().getStatusCode()) && isNotStale(httpResponse);
            httpResponse.close();
            return z;
        } catch (IOException ioe) {
            this.log.debug("Asynchronous revalidation failed due to I/O error", ioe);
            return false;
        } catch (HttpException pe) {
            this.log.error("HTTP protocol exception during asynchronous revalidation", pe);
            return false;
        } catch (RuntimeException re) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("RuntimeException thrown during asynchronous revalidation: ");
            sb.append(re);
            httpClientAndroidLog.error(sb.toString());
            return false;
        } catch (Throwable th) {
            httpResponse.close();
            throw th;
        }
    }

    private boolean isNotServerError(int statusCode) {
        return statusCode < 500;
    }

    private boolean isNotStale(HttpResponse httpResponse) {
        Header[] warnings = httpResponse.getHeaders("Warning");
        if (warnings != null) {
            for (Header warning : warnings) {
                String warningValue = warning.getValue();
                if (warningValue.startsWith("110") || warningValue.startsWith("111")) {
                    return false;
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public String getIdentifier() {
        return this.identifier;
    }

    public int getConsecutiveFailedAttempts() {
        return this.consecutiveFailedAttempts;
    }
}
