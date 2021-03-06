package p008cz.msebera.android.httpclient.impl.client;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p008cz.msebera.android.httpclient.HttpRequest;

/* renamed from: cz.msebera.android.httpclient.impl.client.StandardHttpRequestRetryHandler */
public class StandardHttpRequestRetryHandler extends DefaultHttpRequestRetryHandler {
    private final Map<String, Boolean> idempotentMethods;

    public StandardHttpRequestRetryHandler(int retryCount, boolean requestSentRetryEnabled) {
        super(retryCount, requestSentRetryEnabled);
        this.idempotentMethods = new ConcurrentHashMap();
        this.idempotentMethods.put("GET", Boolean.TRUE);
        this.idempotentMethods.put("HEAD", Boolean.TRUE);
        this.idempotentMethods.put("PUT", Boolean.TRUE);
        this.idempotentMethods.put("DELETE", Boolean.TRUE);
        this.idempotentMethods.put("OPTIONS", Boolean.TRUE);
        this.idempotentMethods.put("TRACE", Boolean.TRUE);
    }

    public StandardHttpRequestRetryHandler() {
        this(3, false);
    }

    /* access modifiers changed from: protected */
    public boolean handleAsIdempotent(HttpRequest request) {
        Boolean b = (Boolean) this.idempotentMethods.get(request.getRequestLine().getMethod().toUpperCase(Locale.ENGLISH));
        return b != null && b.booleanValue();
    }
}
