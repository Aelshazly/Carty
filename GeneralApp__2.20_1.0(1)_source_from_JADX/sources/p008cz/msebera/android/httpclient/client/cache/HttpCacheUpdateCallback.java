package p008cz.msebera.android.httpclient.client.cache;

import java.io.IOException;

/* renamed from: cz.msebera.android.httpclient.client.cache.HttpCacheUpdateCallback */
public interface HttpCacheUpdateCallback {
    HttpCacheEntry update(HttpCacheEntry httpCacheEntry) throws IOException;
}
