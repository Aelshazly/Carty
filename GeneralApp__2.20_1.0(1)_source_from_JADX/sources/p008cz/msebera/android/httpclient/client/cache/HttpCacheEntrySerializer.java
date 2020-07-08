package p008cz.msebera.android.httpclient.client.cache;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: cz.msebera.android.httpclient.client.cache.HttpCacheEntrySerializer */
public interface HttpCacheEntrySerializer {
    HttpCacheEntry readFrom(InputStream inputStream) throws IOException;

    void writeTo(HttpCacheEntry httpCacheEntry, OutputStream outputStream) throws IOException;
}
