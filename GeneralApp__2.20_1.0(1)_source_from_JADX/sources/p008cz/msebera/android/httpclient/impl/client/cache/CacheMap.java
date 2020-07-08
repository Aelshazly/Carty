package p008cz.msebera.android.httpclient.impl.client.cache;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheEntry;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.CacheMap */
final class CacheMap extends LinkedHashMap<String, HttpCacheEntry> {
    private static final long serialVersionUID = -7750025207539768511L;
    private final int maxEntries;

    CacheMap(int maxEntries2) {
        super(20, 0.75f, true);
        this.maxEntries = maxEntries2;
    }

    /* access modifiers changed from: protected */
    public boolean removeEldestEntry(Entry<String, HttpCacheEntry> entry) {
        return size() > this.maxEntries;
    }
}
