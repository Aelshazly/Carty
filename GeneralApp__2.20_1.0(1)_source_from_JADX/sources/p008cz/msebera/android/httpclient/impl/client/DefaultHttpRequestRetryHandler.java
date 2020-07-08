package p008cz.msebera.android.httpclient.impl.client;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.SSLException;
import p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import p008cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.client.DefaultHttpRequestRetryHandler */
public class DefaultHttpRequestRetryHandler implements HttpRequestRetryHandler {
    public static final DefaultHttpRequestRetryHandler INSTANCE = new DefaultHttpRequestRetryHandler();
    private final Set<Class<? extends IOException>> nonRetriableClasses;
    private final boolean requestSentRetryEnabled;
    private final int retryCount;

    protected DefaultHttpRequestRetryHandler(int retryCount2, boolean requestSentRetryEnabled2, Collection<Class<? extends IOException>> clazzes) {
        this.retryCount = retryCount2;
        this.requestSentRetryEnabled = requestSentRetryEnabled2;
        this.nonRetriableClasses = new HashSet();
        for (Class<? extends IOException> clazz : clazzes) {
            this.nonRetriableClasses.add(clazz);
        }
    }

    public DefaultHttpRequestRetryHandler(int retryCount2, boolean requestSentRetryEnabled2) {
        this(retryCount2, requestSentRetryEnabled2, Arrays.asList(new Class[]{InterruptedIOException.class, UnknownHostException.class, ConnectException.class, SSLException.class}));
    }

    public DefaultHttpRequestRetryHandler() {
        this(3, false);
    }

    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
        Args.notNull(exception, "Exception parameter");
        Args.notNull(context, "HTTP context");
        if (executionCount > this.retryCount || this.nonRetriableClasses.contains(exception.getClass())) {
            return false;
        }
        for (Class<? extends IOException> rejectException : this.nonRetriableClasses) {
            if (rejectException.isInstance(exception)) {
                return false;
            }
        }
        HttpClientContext clientContext = HttpClientContext.adapt(context);
        HttpRequest request = clientContext.getRequest();
        if (requestIsAborted(request)) {
            return false;
        }
        if (!handleAsIdempotent(request) && clientContext.isRequestSent() && !this.requestSentRetryEnabled) {
            return false;
        }
        return true;
    }

    public boolean isRequestSentRetryEnabled() {
        return this.requestSentRetryEnabled;
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    /* access modifiers changed from: protected */
    public boolean handleAsIdempotent(HttpRequest request) {
        return !(request instanceof HttpEntityEnclosingRequest);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public boolean requestIsAborted(HttpRequest request) {
        HttpRequest req = request;
        if (request instanceof RequestWrapper) {
            req = ((RequestWrapper) request).getOriginal();
        }
        return (req instanceof HttpUriRequest) && ((HttpUriRequest) req).isAborted();
    }
}
