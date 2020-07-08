package p008cz.msebera.android.httpclient.impl.pool;

import java.util.concurrent.atomic.AtomicLong;
import p008cz.msebera.android.httpclient.HttpClientConnection;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.config.ConnectionConfig;
import p008cz.msebera.android.httpclient.config.SocketConfig;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.pool.AbstractConnPool;
import p008cz.msebera.android.httpclient.pool.ConnFactory;

/* renamed from: cz.msebera.android.httpclient.impl.pool.BasicConnPool */
public class BasicConnPool extends AbstractConnPool<HttpHost, HttpClientConnection, BasicPoolEntry> {
    private static final AtomicLong COUNTER = new AtomicLong();

    public BasicConnPool(ConnFactory<HttpHost, HttpClientConnection> connFactory) {
        super(connFactory, 2, 20);
    }

    @Deprecated
    public BasicConnPool(HttpParams params) {
        super(new BasicConnFactory(params), 2, 20);
    }

    public BasicConnPool(SocketConfig sconfig, ConnectionConfig cconfig) {
        super(new BasicConnFactory(sconfig, cconfig), 2, 20);
    }

    public BasicConnPool() {
        super(new BasicConnFactory(SocketConfig.DEFAULT, ConnectionConfig.DEFAULT), 2, 20);
    }

    /* access modifiers changed from: protected */
    public BasicPoolEntry createEntry(HttpHost host, HttpClientConnection conn) {
        return new BasicPoolEntry(Long.toString(COUNTER.getAndIncrement()), host, conn);
    }
}
