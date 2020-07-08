package p008cz.msebera.android.httpclient;

/* renamed from: cz.msebera.android.httpclient.HttpConnectionMetrics */
public interface HttpConnectionMetrics {
    Object getMetric(String str);

    long getReceivedBytesCount();

    long getRequestCount();

    long getResponseCount();

    long getSentBytesCount();

    void reset();
}
