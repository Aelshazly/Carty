package p008cz.msebera.android.httpclient.impl.client.cache;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.jvm.internal.LongCompanionObject;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.DefaultFailureCache */
public class DefaultFailureCache implements FailureCache {
    static final int DEFAULT_MAX_SIZE = 1000;
    static final int MAX_UPDATE_TRIES = 10;
    private final int maxSize;
    private final ConcurrentMap<String, FailureCacheValue> storage;

    public DefaultFailureCache() {
        this(1000);
    }

    public DefaultFailureCache(int maxSize2) {
        this.maxSize = maxSize2;
        this.storage = new ConcurrentHashMap();
    }

    public int getErrorCount(String identifier) {
        if (identifier != null) {
            FailureCacheValue storedErrorCode = (FailureCacheValue) this.storage.get(identifier);
            if (storedErrorCode != null) {
                return storedErrorCode.getErrorCount();
            }
            return 0;
        }
        throw new IllegalArgumentException("identifier may not be null");
    }

    public void resetErrorCount(String identifier) {
        if (identifier != null) {
            this.storage.remove(identifier);
            return;
        }
        throw new IllegalArgumentException("identifier may not be null");
    }

    public void increaseErrorCount(String identifier) {
        if (identifier != null) {
            updateValue(identifier);
            removeOldestEntryIfMapSizeExceeded();
            return;
        }
        throw new IllegalArgumentException("identifier may not be null");
    }

    private void updateValue(String identifier) {
        for (int i = 0; i < 10; i++) {
            FailureCacheValue oldValue = (FailureCacheValue) this.storage.get(identifier);
            if (oldValue == null) {
                if (this.storage.putIfAbsent(identifier, new FailureCacheValue(identifier, 1)) == null) {
                    return;
                }
            } else {
                int errorCount = oldValue.getErrorCount();
                if (errorCount != Integer.MAX_VALUE) {
                    if (this.storage.replace(identifier, oldValue, new FailureCacheValue(identifier, errorCount + 1))) {
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    private void removeOldestEntryIfMapSizeExceeded() {
        if (this.storage.size() > this.maxSize) {
            FailureCacheValue valueWithOldestTimestamp = findValueWithOldestTimestamp();
            if (valueWithOldestTimestamp != null) {
                this.storage.remove(valueWithOldestTimestamp.getKey(), valueWithOldestTimestamp);
            }
        }
    }

    private FailureCacheValue findValueWithOldestTimestamp() {
        long oldestTimestamp = LongCompanionObject.MAX_VALUE;
        FailureCacheValue oldestValue = null;
        for (Entry<String, FailureCacheValue> storageEntry : this.storage.entrySet()) {
            long creationTimeInNanos = ((FailureCacheValue) storageEntry.getValue()).getCreationTimeInNanos();
            if (creationTimeInNanos < oldestTimestamp) {
                oldestTimestamp = creationTimeInNanos;
                oldestValue = (FailureCacheValue) storageEntry.getValue();
            }
        }
        return oldestValue;
    }
}
