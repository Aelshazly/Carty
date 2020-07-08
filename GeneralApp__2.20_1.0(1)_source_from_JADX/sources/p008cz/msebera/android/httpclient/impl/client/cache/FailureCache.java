package p008cz.msebera.android.httpclient.impl.client.cache;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.FailureCache */
public interface FailureCache {
    int getErrorCount(String str);

    void increaseErrorCount(String str);

    void resetErrorCount(String str);
}
