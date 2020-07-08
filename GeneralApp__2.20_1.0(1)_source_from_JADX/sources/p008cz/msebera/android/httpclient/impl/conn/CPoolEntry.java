package p008cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import p008cz.msebera.android.httpclient.HttpClientConnection;
import p008cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.pool.PoolEntry;

/* renamed from: cz.msebera.android.httpclient.impl.conn.CPoolEntry */
class CPoolEntry extends PoolEntry<HttpRoute, ManagedHttpClientConnection> {
    public HttpClientAndroidLog log;
    private volatile boolean routeComplete;

    public CPoolEntry(HttpClientAndroidLog log2, String id, HttpRoute route, ManagedHttpClientConnection conn, long timeToLive, TimeUnit tunit) {
        super(id, route, conn, timeToLive, tunit);
        this.log = log2;
    }

    public void markRouteComplete() {
        this.routeComplete = true;
    }

    public boolean isRouteComplete() {
        return this.routeComplete;
    }

    public void closeConnection() throws IOException {
        ((HttpClientConnection) getConnection()).close();
    }

    public void shutdownConnection() throws IOException {
        ((HttpClientConnection) getConnection()).shutdown();
    }

    public boolean isExpired(long now) {
        boolean expired = super.isExpired(now);
        if (expired && this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Connection ");
            sb.append(this);
            sb.append(" expired @ ");
            sb.append(new Date(getExpiry()));
            httpClientAndroidLog.debug(sb.toString());
        }
        return expired;
    }

    public boolean isClosed() {
        return !((HttpClientConnection) getConnection()).isOpen();
    }

    public void close() {
        try {
            closeConnection();
        } catch (IOException ex) {
            this.log.debug("I/O error closing connection", ex);
        }
    }
}
