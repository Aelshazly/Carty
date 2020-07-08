package p008cz.msebera.android.httpclient.impl.client.cache;

import android.support.p000v4.media.session.PlaybackStateCompat;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.CacheConfig */
public class CacheConfig implements Cloneable {
    public static final CacheConfig DEFAULT = new Builder().build();
    public static final boolean DEFAULT_303_CACHING_ENABLED = false;
    public static final int DEFAULT_ASYNCHRONOUS_WORKERS_CORE = 1;
    public static final int DEFAULT_ASYNCHRONOUS_WORKERS_MAX = 1;
    public static final int DEFAULT_ASYNCHRONOUS_WORKER_IDLE_LIFETIME_SECS = 60;
    public static final boolean DEFAULT_HEURISTIC_CACHING_ENABLED = false;
    public static final float DEFAULT_HEURISTIC_COEFFICIENT = 0.1f;
    public static final long DEFAULT_HEURISTIC_LIFETIME = 0;
    public static final int DEFAULT_MAX_CACHE_ENTRIES = 1000;
    public static final int DEFAULT_MAX_OBJECT_SIZE_BYTES = 8192;
    public static final int DEFAULT_MAX_UPDATE_RETRIES = 1;
    public static final int DEFAULT_REVALIDATION_QUEUE_SIZE = 100;
    public static final boolean DEFAULT_WEAK_ETAG_ON_PUTDELETE_ALLOWED = false;
    private boolean allow303Caching;
    private int asynchronousWorkerIdleLifetimeSecs;
    private int asynchronousWorkersCore;
    private int asynchronousWorkersMax;
    private boolean heuristicCachingEnabled;
    private float heuristicCoefficient;
    private long heuristicDefaultLifetime;
    private boolean isSharedCache;
    private int maxCacheEntries;
    private long maxObjectSize;
    private int maxUpdateRetries;
    private boolean neverCacheHTTP10ResponsesWithQuery;
    private int revalidationQueueSize;
    private boolean weakETagOnPutDeleteAllowed;

    /* renamed from: cz.msebera.android.httpclient.impl.client.cache.CacheConfig$Builder */
    public static class Builder {
        private boolean allow303Caching = false;
        private int asynchronousWorkerIdleLifetimeSecs = 60;
        private int asynchronousWorkersCore = 1;
        private int asynchronousWorkersMax = 1;
        private boolean heuristicCachingEnabled = false;
        private float heuristicCoefficient = 0.1f;
        private long heuristicDefaultLifetime = 0;
        private boolean isSharedCache = true;
        private int maxCacheEntries = 1000;
        private long maxObjectSize = PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        private int maxUpdateRetries = 1;
        private boolean neverCacheHTTP10ResponsesWithQuery;
        private int revalidationQueueSize = 100;
        private boolean weakETagOnPutDeleteAllowed = false;

        Builder() {
        }

        public Builder setMaxObjectSize(long maxObjectSize2) {
            this.maxObjectSize = maxObjectSize2;
            return this;
        }

        public Builder setMaxCacheEntries(int maxCacheEntries2) {
            this.maxCacheEntries = maxCacheEntries2;
            return this;
        }

        public Builder setMaxUpdateRetries(int maxUpdateRetries2) {
            this.maxUpdateRetries = maxUpdateRetries2;
            return this;
        }

        public Builder setAllow303Caching(boolean allow303Caching2) {
            this.allow303Caching = allow303Caching2;
            return this;
        }

        public Builder setWeakETagOnPutDeleteAllowed(boolean weakETagOnPutDeleteAllowed2) {
            this.weakETagOnPutDeleteAllowed = weakETagOnPutDeleteAllowed2;
            return this;
        }

        public Builder setHeuristicCachingEnabled(boolean heuristicCachingEnabled2) {
            this.heuristicCachingEnabled = heuristicCachingEnabled2;
            return this;
        }

        public Builder setHeuristicCoefficient(float heuristicCoefficient2) {
            this.heuristicCoefficient = heuristicCoefficient2;
            return this;
        }

        public Builder setHeuristicDefaultLifetime(long heuristicDefaultLifetime2) {
            this.heuristicDefaultLifetime = heuristicDefaultLifetime2;
            return this;
        }

        public Builder setSharedCache(boolean isSharedCache2) {
            this.isSharedCache = isSharedCache2;
            return this;
        }

        public Builder setAsynchronousWorkersMax(int asynchronousWorkersMax2) {
            this.asynchronousWorkersMax = asynchronousWorkersMax2;
            return this;
        }

        public Builder setAsynchronousWorkersCore(int asynchronousWorkersCore2) {
            this.asynchronousWorkersCore = asynchronousWorkersCore2;
            return this;
        }

        public Builder setAsynchronousWorkerIdleLifetimeSecs(int asynchronousWorkerIdleLifetimeSecs2) {
            this.asynchronousWorkerIdleLifetimeSecs = asynchronousWorkerIdleLifetimeSecs2;
            return this;
        }

        public Builder setRevalidationQueueSize(int revalidationQueueSize2) {
            this.revalidationQueueSize = revalidationQueueSize2;
            return this;
        }

        public Builder setNeverCacheHTTP10ResponsesWithQueryString(boolean neverCacheHTTP10ResponsesWithQuery2) {
            this.neverCacheHTTP10ResponsesWithQuery = neverCacheHTTP10ResponsesWithQuery2;
            return this;
        }

        public CacheConfig build() {
            CacheConfig cacheConfig = new CacheConfig(this.maxObjectSize, this.maxCacheEntries, this.maxUpdateRetries, this.allow303Caching, this.weakETagOnPutDeleteAllowed, this.heuristicCachingEnabled, this.heuristicCoefficient, this.heuristicDefaultLifetime, this.isSharedCache, this.asynchronousWorkersMax, this.asynchronousWorkersCore, this.asynchronousWorkerIdleLifetimeSecs, this.revalidationQueueSize, this.neverCacheHTTP10ResponsesWithQuery);
            return cacheConfig;
        }
    }

    @Deprecated
    public CacheConfig() {
        this.maxObjectSize = PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        this.maxCacheEntries = 1000;
        this.maxUpdateRetries = 1;
        this.allow303Caching = false;
        this.weakETagOnPutDeleteAllowed = false;
        this.heuristicCachingEnabled = false;
        this.heuristicCoefficient = 0.1f;
        this.heuristicDefaultLifetime = 0;
        this.isSharedCache = true;
        this.asynchronousWorkersMax = 1;
        this.asynchronousWorkersCore = 1;
        this.asynchronousWorkerIdleLifetimeSecs = 60;
        this.revalidationQueueSize = 100;
    }

    CacheConfig(long maxObjectSize2, int maxCacheEntries2, int maxUpdateRetries2, boolean allow303Caching2, boolean weakETagOnPutDeleteAllowed2, boolean heuristicCachingEnabled2, float heuristicCoefficient2, long heuristicDefaultLifetime2, boolean isSharedCache2, int asynchronousWorkersMax2, int asynchronousWorkersCore2, int asynchronousWorkerIdleLifetimeSecs2, int revalidationQueueSize2, boolean neverCacheHTTP10ResponsesWithQuery2) {
        this.maxObjectSize = maxObjectSize2;
        this.maxCacheEntries = maxCacheEntries2;
        this.maxUpdateRetries = maxUpdateRetries2;
        this.allow303Caching = allow303Caching2;
        this.weakETagOnPutDeleteAllowed = weakETagOnPutDeleteAllowed2;
        this.heuristicCachingEnabled = heuristicCachingEnabled2;
        this.heuristicCoefficient = heuristicCoefficient2;
        this.heuristicDefaultLifetime = heuristicDefaultLifetime2;
        this.isSharedCache = isSharedCache2;
        this.asynchronousWorkersMax = asynchronousWorkersMax2;
        this.asynchronousWorkersCore = asynchronousWorkersCore2;
        this.asynchronousWorkerIdleLifetimeSecs = asynchronousWorkerIdleLifetimeSecs2;
        this.revalidationQueueSize = revalidationQueueSize2;
    }

    @Deprecated
    public int getMaxObjectSizeBytes() {
        long j = this.maxObjectSize;
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j;
    }

    @Deprecated
    public void setMaxObjectSizeBytes(int maxObjectSizeBytes) {
        if (maxObjectSizeBytes > Integer.MAX_VALUE) {
            this.maxObjectSize = 2147483647L;
        } else {
            this.maxObjectSize = (long) maxObjectSizeBytes;
        }
    }

    public long getMaxObjectSize() {
        return this.maxObjectSize;
    }

    @Deprecated
    public void setMaxObjectSize(long maxObjectSize2) {
        this.maxObjectSize = maxObjectSize2;
    }

    public boolean isNeverCacheHTTP10ResponsesWithQuery() {
        return this.neverCacheHTTP10ResponsesWithQuery;
    }

    public int getMaxCacheEntries() {
        return this.maxCacheEntries;
    }

    @Deprecated
    public void setMaxCacheEntries(int maxCacheEntries2) {
        this.maxCacheEntries = maxCacheEntries2;
    }

    public int getMaxUpdateRetries() {
        return this.maxUpdateRetries;
    }

    @Deprecated
    public void setMaxUpdateRetries(int maxUpdateRetries2) {
        this.maxUpdateRetries = maxUpdateRetries2;
    }

    public boolean is303CachingEnabled() {
        return this.allow303Caching;
    }

    public boolean isWeakETagOnPutDeleteAllowed() {
        return this.weakETagOnPutDeleteAllowed;
    }

    public boolean isHeuristicCachingEnabled() {
        return this.heuristicCachingEnabled;
    }

    @Deprecated
    public void setHeuristicCachingEnabled(boolean heuristicCachingEnabled2) {
        this.heuristicCachingEnabled = heuristicCachingEnabled2;
    }

    public float getHeuristicCoefficient() {
        return this.heuristicCoefficient;
    }

    @Deprecated
    public void setHeuristicCoefficient(float heuristicCoefficient2) {
        this.heuristicCoefficient = heuristicCoefficient2;
    }

    public long getHeuristicDefaultLifetime() {
        return this.heuristicDefaultLifetime;
    }

    @Deprecated
    public void setHeuristicDefaultLifetime(long heuristicDefaultLifetimeSecs) {
        this.heuristicDefaultLifetime = heuristicDefaultLifetimeSecs;
    }

    public boolean isSharedCache() {
        return this.isSharedCache;
    }

    @Deprecated
    public void setSharedCache(boolean isSharedCache2) {
        this.isSharedCache = isSharedCache2;
    }

    public int getAsynchronousWorkersMax() {
        return this.asynchronousWorkersMax;
    }

    @Deprecated
    public void setAsynchronousWorkersMax(int max) {
        this.asynchronousWorkersMax = max;
    }

    public int getAsynchronousWorkersCore() {
        return this.asynchronousWorkersCore;
    }

    @Deprecated
    public void setAsynchronousWorkersCore(int min) {
        this.asynchronousWorkersCore = min;
    }

    public int getAsynchronousWorkerIdleLifetimeSecs() {
        return this.asynchronousWorkerIdleLifetimeSecs;
    }

    @Deprecated
    public void setAsynchronousWorkerIdleLifetimeSecs(int secs) {
        this.asynchronousWorkerIdleLifetimeSecs = secs;
    }

    public int getRevalidationQueueSize() {
        return this.revalidationQueueSize;
    }

    @Deprecated
    public void setRevalidationQueueSize(int size) {
        this.revalidationQueueSize = size;
    }

    /* access modifiers changed from: protected */
    public CacheConfig clone() throws CloneNotSupportedException {
        return (CacheConfig) super.clone();
    }

    public static Builder custom() {
        return new Builder();
    }

    public static Builder copy(CacheConfig config) {
        Args.notNull(config, "Cache config");
        return new Builder().setMaxObjectSize(config.getMaxObjectSize()).setMaxCacheEntries(config.getMaxCacheEntries()).setMaxUpdateRetries(config.getMaxUpdateRetries()).setHeuristicCachingEnabled(config.isHeuristicCachingEnabled()).setHeuristicCoefficient(config.getHeuristicCoefficient()).setHeuristicDefaultLifetime(config.getHeuristicDefaultLifetime()).setSharedCache(config.isSharedCache()).setAsynchronousWorkersMax(config.getAsynchronousWorkersMax()).setAsynchronousWorkersCore(config.getAsynchronousWorkersCore()).setAsynchronousWorkerIdleLifetimeSecs(config.getAsynchronousWorkerIdleLifetimeSecs()).setRevalidationQueueSize(config.getRevalidationQueueSize()).setNeverCacheHTTP10ResponsesWithQueryString(config.isNeverCacheHTTP10ResponsesWithQuery());
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[maxObjectSize=");
        builder.append(this.maxObjectSize);
        builder.append(", maxCacheEntries=");
        builder.append(this.maxCacheEntries);
        builder.append(", maxUpdateRetries=");
        builder.append(this.maxUpdateRetries);
        builder.append(", 303CachingEnabled=");
        builder.append(this.allow303Caching);
        builder.append(", weakETagOnPutDeleteAllowed=");
        builder.append(this.weakETagOnPutDeleteAllowed);
        builder.append(", heuristicCachingEnabled=");
        builder.append(this.heuristicCachingEnabled);
        builder.append(", heuristicCoefficient=");
        builder.append(this.heuristicCoefficient);
        builder.append(", heuristicDefaultLifetime=");
        builder.append(this.heuristicDefaultLifetime);
        builder.append(", isSharedCache=");
        builder.append(this.isSharedCache);
        builder.append(", asynchronousWorkersMax=");
        builder.append(this.asynchronousWorkersMax);
        builder.append(", asynchronousWorkersCore=");
        builder.append(this.asynchronousWorkersCore);
        builder.append(", asynchronousWorkerIdleLifetimeSecs=");
        builder.append(this.asynchronousWorkerIdleLifetimeSecs);
        builder.append(", revalidationQueueSize=");
        builder.append(this.revalidationQueueSize);
        builder.append(", neverCacheHTTP10ResponsesWithQuery=");
        builder.append(this.neverCacheHTTP10ResponsesWithQuery);
        builder.append("]");
        return builder.toString();
    }
}
