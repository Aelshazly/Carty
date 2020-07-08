package p008cz.msebera.android.httpclient.pool;

/* renamed from: cz.msebera.android.httpclient.pool.PoolStats */
public class PoolStats {
    private final int available;
    private final int leased;
    private final int max;
    private final int pending;

    public PoolStats(int leased2, int pending2, int free, int max2) {
        this.leased = leased2;
        this.pending = pending2;
        this.available = free;
        this.max = max2;
    }

    public int getLeased() {
        return this.leased;
    }

    public int getPending() {
        return this.pending;
    }

    public int getAvailable() {
        return this.available;
    }

    public int getMax() {
        return this.max;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[leased: ");
        buffer.append(this.leased);
        buffer.append("; pending: ");
        buffer.append(this.pending);
        buffer.append("; available: ");
        buffer.append(this.available);
        buffer.append("; max: ");
        buffer.append(this.max);
        buffer.append("]");
        return buffer.toString();
    }
}
