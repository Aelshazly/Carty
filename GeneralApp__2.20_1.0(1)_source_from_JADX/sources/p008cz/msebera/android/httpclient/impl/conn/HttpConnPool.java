package p008cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import p008cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import p008cz.msebera.android.httpclient.conn.OperatedClientConnection;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.pool.AbstractConnPool;
import p008cz.msebera.android.httpclient.pool.ConnFactory;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.HttpConnPool */
class HttpConnPool extends AbstractConnPool<HttpRoute, OperatedClientConnection, HttpPoolEntry> {
    private static final AtomicLong COUNTER = new AtomicLong();
    public HttpClientAndroidLog log;
    private final long timeToLive;
    private final TimeUnit tunit;

    /* renamed from: cz.msebera.android.httpclient.impl.conn.HttpConnPool$InternalConnFactory */
    static class InternalConnFactory implements ConnFactory<HttpRoute, OperatedClientConnection> {
        private final ClientConnectionOperator connOperator;

        InternalConnFactory(ClientConnectionOperator connOperator2) {
            this.connOperator = connOperator2;
        }

        public OperatedClientConnection create(HttpRoute route) throws IOException {
            return this.connOperator.createConnection();
        }
    }

    public HttpConnPool(HttpClientAndroidLog log2, ClientConnectionOperator connOperator, int defaultMaxPerRoute, int maxTotal, long timeToLive2, TimeUnit tunit2) {
        super(new InternalConnFactory(connOperator), defaultMaxPerRoute, maxTotal);
        this.log = log2;
        this.timeToLive = timeToLive2;
        this.tunit = tunit2;
    }

    /* access modifiers changed from: protected */
    public HttpPoolEntry createEntry(HttpRoute route, OperatedClientConnection conn) {
        HttpPoolEntry httpPoolEntry = new HttpPoolEntry(this.log, Long.toString(COUNTER.getAndIncrement()), route, conn, this.timeToLive, this.tunit);
        return httpPoolEntry;
    }
}
