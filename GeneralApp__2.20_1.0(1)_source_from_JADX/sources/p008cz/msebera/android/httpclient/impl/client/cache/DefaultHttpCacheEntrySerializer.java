package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheEntrySerializationException;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheEntrySerializer;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.DefaultHttpCacheEntrySerializer */
public class DefaultHttpCacheEntrySerializer implements HttpCacheEntrySerializer {
    public void writeTo(HttpCacheEntry cacheEntry, OutputStream os) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(os);
        try {
            oos.writeObject(cacheEntry);
        } finally {
            oos.close();
        }
    }

    public HttpCacheEntry readFrom(InputStream is) throws IOException {
        ObjectInputStream ois = new ObjectInputStream(is);
        try {
            HttpCacheEntry httpCacheEntry = (HttpCacheEntry) ois.readObject();
            ois.close();
            return httpCacheEntry;
        } catch (ClassNotFoundException ex) {
            StringBuilder sb = new StringBuilder();
            sb.append("Class not found: ");
            sb.append(ex.getMessage());
            throw new HttpCacheEntrySerializationException(sb.toString(), ex);
        } catch (Throwable th) {
            ois.close();
            throw th;
        }
    }
}
