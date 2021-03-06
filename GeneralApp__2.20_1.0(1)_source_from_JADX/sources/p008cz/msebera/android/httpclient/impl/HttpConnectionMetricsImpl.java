package p008cz.msebera.android.httpclient.impl;

import java.util.HashMap;
import java.util.Map;
import p008cz.msebera.android.httpclient.HttpConnectionMetrics;
import p008cz.msebera.android.httpclient.p013io.HttpTransportMetrics;

/* renamed from: cz.msebera.android.httpclient.impl.HttpConnectionMetricsImpl */
public class HttpConnectionMetricsImpl implements HttpConnectionMetrics {
    public static final String RECEIVED_BYTES_COUNT = "http.received-bytes-count";
    public static final String REQUEST_COUNT = "http.request-count";
    public static final String RESPONSE_COUNT = "http.response-count";
    public static final String SENT_BYTES_COUNT = "http.sent-bytes-count";
    private final HttpTransportMetrics inTransportMetric;
    private Map<String, Object> metricsCache;
    private final HttpTransportMetrics outTransportMetric;
    private long requestCount = 0;
    private long responseCount = 0;

    public HttpConnectionMetricsImpl(HttpTransportMetrics inTransportMetric2, HttpTransportMetrics outTransportMetric2) {
        this.inTransportMetric = inTransportMetric2;
        this.outTransportMetric = outTransportMetric2;
    }

    public long getReceivedBytesCount() {
        HttpTransportMetrics httpTransportMetrics = this.inTransportMetric;
        if (httpTransportMetrics != null) {
            return httpTransportMetrics.getBytesTransferred();
        }
        return -1;
    }

    public long getSentBytesCount() {
        HttpTransportMetrics httpTransportMetrics = this.outTransportMetric;
        if (httpTransportMetrics != null) {
            return httpTransportMetrics.getBytesTransferred();
        }
        return -1;
    }

    public long getRequestCount() {
        return this.requestCount;
    }

    public void incrementRequestCount() {
        this.requestCount++;
    }

    public long getResponseCount() {
        return this.responseCount;
    }

    public void incrementResponseCount() {
        this.responseCount++;
    }

    public Object getMetric(String metricName) {
        Object value = null;
        Map<String, Object> map = this.metricsCache;
        if (map != null) {
            value = map.get(metricName);
        }
        if (value == null) {
            if (REQUEST_COUNT.equals(metricName)) {
                value = Long.valueOf(this.requestCount);
            } else if (RESPONSE_COUNT.equals(metricName)) {
                value = Long.valueOf(this.responseCount);
            } else if (RECEIVED_BYTES_COUNT.equals(metricName)) {
                HttpTransportMetrics httpTransportMetrics = this.inTransportMetric;
                if (httpTransportMetrics != null) {
                    return Long.valueOf(httpTransportMetrics.getBytesTransferred());
                }
                return null;
            } else if (SENT_BYTES_COUNT.equals(metricName)) {
                HttpTransportMetrics httpTransportMetrics2 = this.outTransportMetric;
                if (httpTransportMetrics2 != null) {
                    return Long.valueOf(httpTransportMetrics2.getBytesTransferred());
                }
                return null;
            }
        }
        return value;
    }

    public void setMetric(String metricName, Object obj) {
        if (this.metricsCache == null) {
            this.metricsCache = new HashMap();
        }
        this.metricsCache.put(metricName, obj);
    }

    public void reset() {
        HttpTransportMetrics httpTransportMetrics = this.outTransportMetric;
        if (httpTransportMetrics != null) {
            httpTransportMetrics.reset();
        }
        HttpTransportMetrics httpTransportMetrics2 = this.inTransportMetric;
        if (httpTransportMetrics2 != null) {
            httpTransportMetrics2.reset();
        }
        this.requestCount = 0;
        this.responseCount = 0;
        this.metricsCache = null;
    }
}
