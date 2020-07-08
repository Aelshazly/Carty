package p008cz.msebera.android.httpclient.impl.conn.tsccm;

import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;
import p008cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import p008cz.msebera.android.httpclient.conn.OperatedClientConnection;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.impl.conn.AbstractPoolEntry;
import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.tsccm.BasicPoolEntry */
public class BasicPoolEntry extends AbstractPoolEntry {
    private final long created;
    private long expiry;
    private long updated;
    private final long validUntil;

    public BasicPoolEntry(ClientConnectionOperator op, HttpRoute route, ReferenceQueue<Object> referenceQueue) {
        super(op, route);
        Args.notNull(route, "HTTP route");
        this.created = System.currentTimeMillis();
        this.validUntil = LongCompanionObject.MAX_VALUE;
        this.expiry = this.validUntil;
    }

    public BasicPoolEntry(ClientConnectionOperator op, HttpRoute route) {
        this(op, route, -1, TimeUnit.MILLISECONDS);
    }

    public BasicPoolEntry(ClientConnectionOperator op, HttpRoute route, long connTTL, TimeUnit timeunit) {
        super(op, route);
        Args.notNull(route, "HTTP route");
        this.created = System.currentTimeMillis();
        if (connTTL > 0) {
            this.validUntil = this.created + timeunit.toMillis(connTTL);
        } else {
            this.validUntil = LongCompanionObject.MAX_VALUE;
        }
        this.expiry = this.validUntil;
    }

    /* access modifiers changed from: protected */
    public final OperatedClientConnection getConnection() {
        return this.connection;
    }

    /* access modifiers changed from: protected */
    public final HttpRoute getPlannedRoute() {
        return this.route;
    }

    /* access modifiers changed from: protected */
    public final BasicPoolEntryRef getWeakRef() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void shutdownEntry() {
        super.shutdownEntry();
    }

    public long getCreated() {
        return this.created;
    }

    public long getUpdated() {
        return this.updated;
    }

    public long getExpiry() {
        return this.expiry;
    }

    public long getValidUntil() {
        return this.validUntil;
    }

    public void updateExpiry(long time, TimeUnit timeunit) {
        long newExpiry;
        this.updated = System.currentTimeMillis();
        if (time > 0) {
            newExpiry = this.updated + timeunit.toMillis(time);
        } else {
            newExpiry = LongCompanionObject.MAX_VALUE;
        }
        this.expiry = Math.min(this.validUntil, newExpiry);
    }

    public boolean isExpired(long now) {
        return now >= this.expiry;
    }
}
