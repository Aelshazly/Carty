package p008cz.msebera.android.httpclient.impl.pool;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpClientConnection;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.pool.PoolEntry;

/* renamed from: cz.msebera.android.httpclient.impl.pool.BasicPoolEntry */
public class BasicPoolEntry extends PoolEntry<HttpHost, HttpClientConnection> {
    public BasicPoolEntry(String id, HttpHost route, HttpClientConnection conn) {
        super(id, route, conn);
    }

    public void close() {
        try {
            ((HttpClientConnection) getConnection()).close();
        } catch (IOException e) {
        }
    }

    public boolean isClosed() {
        return !((HttpClientConnection) getConnection()).isOpen();
    }
}
