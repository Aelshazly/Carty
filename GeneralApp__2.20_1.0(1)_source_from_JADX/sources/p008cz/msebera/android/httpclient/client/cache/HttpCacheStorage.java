package p008cz.msebera.android.httpclient.client.cache;

import java.io.IOException;

/* renamed from: cz.msebera.android.httpclient.client.cache.HttpCacheStorage */
public interface HttpCacheStorage {
    HttpCacheEntry getEntry(String str) throws IOException;

    void putEntry(String str, HttpCacheEntry httpCacheEntry) throws IOException;

    void removeEntry(String str) throws IOException;

    void updateEntry(String str, HttpCacheUpdateCallback httpCacheUpdateCallback) throws IOException, HttpCacheUpdateException;
}
