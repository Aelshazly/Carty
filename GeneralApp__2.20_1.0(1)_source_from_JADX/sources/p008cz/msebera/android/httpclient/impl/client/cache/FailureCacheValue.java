package p008cz.msebera.android.httpclient.impl.client.cache;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.FailureCacheValue */
public class FailureCacheValue {
    private final long creationTimeInNanos = System.nanoTime();
    private final int errorCount;
    private final String key;

    public FailureCacheValue(String key2, int errorCount2) {
        this.key = key2;
        this.errorCount = errorCount2;
    }

    public long getCreationTimeInNanos() {
        return this.creationTimeInNanos;
    }

    public String getKey() {
        return this.key;
    }

    public int getErrorCount() {
        return this.errorCount;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[entry creationTimeInNanos=");
        sb.append(this.creationTimeInNanos);
        sb.append("; ");
        sb.append("key=");
        sb.append(this.key);
        sb.append("; errorCount=");
        sb.append(this.errorCount);
        sb.append(']');
        return sb.toString();
    }
}
