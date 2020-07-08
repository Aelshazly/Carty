package p008cz.msebera.android.httpclient.pool;

import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.pool.PoolEntry */
public abstract class PoolEntry<T, C> {
    private final C conn;
    private final long created;
    private long expiry;

    /* renamed from: id */
    private final String f244id;
    private final T route;
    private volatile Object state;
    private long updated;
    private final long validUnit;

    public abstract void close();

    public abstract boolean isClosed();

    public PoolEntry(String id, T route2, C conn2, long timeToLive, TimeUnit tunit) {
        Args.notNull(route2, "Route");
        Args.notNull(conn2, "Connection");
        Args.notNull(tunit, "Time unit");
        this.f244id = id;
        this.route = route2;
        this.conn = conn2;
        this.created = System.currentTimeMillis();
        if (timeToLive > 0) {
            this.validUnit = this.created + tunit.toMillis(timeToLive);
        } else {
            this.validUnit = LongCompanionObject.MAX_VALUE;
        }
        this.expiry = this.validUnit;
    }

    public PoolEntry(String id, T route2, C conn2) {
        this(id, route2, conn2, 0, TimeUnit.MILLISECONDS);
    }

    public String getId() {
        return this.f244id;
    }

    public T getRoute() {
        return this.route;
    }

    public C getConnection() {
        return this.conn;
    }

    public long getCreated() {
        return this.created;
    }

    public long getValidUnit() {
        return this.validUnit;
    }

    public Object getState() {
        return this.state;
    }

    public void setState(Object state2) {
        this.state = state2;
    }

    public synchronized long getUpdated() {
        return this.updated;
    }

    public synchronized long getExpiry() {
        return this.expiry;
    }

    public synchronized void updateExpiry(long time, TimeUnit tunit) {
        long newExpiry;
        Args.notNull(tunit, "Time unit");
        this.updated = System.currentTimeMillis();
        if (time > 0) {
            newExpiry = this.updated + tunit.toMillis(time);
        } else {
            newExpiry = LongCompanionObject.MAX_VALUE;
        }
        this.expiry = Math.min(newExpiry, this.validUnit);
    }

    public synchronized boolean isExpired(long now) {
        return now >= this.expiry;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[id:");
        buffer.append(this.f244id);
        buffer.append("][route:");
        buffer.append(this.route);
        buffer.append("][state:");
        buffer.append(this.state);
        buffer.append("]");
        return buffer.toString();
    }
}
