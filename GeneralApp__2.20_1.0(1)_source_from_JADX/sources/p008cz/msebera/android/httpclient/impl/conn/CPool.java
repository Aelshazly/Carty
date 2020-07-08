package p008cz.msebera.android.httpclient.impl.conn;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import p008cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.pool.AbstractConnPool;
import p008cz.msebera.android.httpclient.pool.ConnFactory;

/* renamed from: cz.msebera.android.httpclient.impl.conn.CPool */
class CPool extends AbstractConnPool<HttpRoute, ManagedHttpClientConnection, CPoolEntry> {
    private static final AtomicLong COUNTER = new AtomicLong();
    public HttpClientAndroidLog log = new HttpClientAndroidLog(CPool.class);
    private final long timeToLive;
    private final TimeUnit tunit;

    public CPool(ConnFactory<HttpRoute, ManagedHttpClientConnection> connFactory, int defaultMaxPerRoute, int maxTotal, long timeToLive2, TimeUnit tunit2) {
        super(connFactory, defaultMaxPerRoute, maxTotal);
        this.timeToLive = timeToLive2;
        this.tunit = tunit2;
    }

    /* access modifiers changed from: protected */
    public CPoolEntry createEntry(HttpRoute route, ManagedHttpClientConnection conn) {
        CPoolEntry cPoolEntry = new CPoolEntry(this.log, Long.toString(COUNTER.getAndIncrement()), route, conn, this.timeToLive, this.tunit);
        return cPoolEntry;
    }
}
