package p008cz.msebera.android.httpclient.impl.client.cache;

import p008cz.msebera.android.httpclient.client.cache.HttpCacheEntry;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.Variant */
class Variant {
    private final String cacheKey;
    private final HttpCacheEntry entry;
    private final String variantKey;

    public Variant(String variantKey2, String cacheKey2, HttpCacheEntry entry2) {
        this.variantKey = variantKey2;
        this.cacheKey = cacheKey2;
        this.entry = entry2;
    }

    public String getVariantKey() {
        return this.variantKey;
    }

    public String getCacheKey() {
        return this.cacheKey;
    }

    public HttpCacheEntry getEntry() {
        return this.entry;
    }
}
