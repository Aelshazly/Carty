package p008cz.msebera.android.httpclient.impl.execchain;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import p008cz.msebera.android.httpclient.HttpClientConnection;
import p008cz.msebera.android.httpclient.concurrent.Cancellable;
import p008cz.msebera.android.httpclient.conn.ConnectionReleaseTrigger;
import p008cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;

/* renamed from: cz.msebera.android.httpclient.impl.execchain.ConnectionHolder */
class ConnectionHolder implements ConnectionReleaseTrigger, Cancellable, Closeable {
    public HttpClientAndroidLog log;
    private final HttpClientConnection managedConn;
    private final HttpClientConnectionManager manager;
    private volatile boolean released;
    private volatile boolean reusable;
    private volatile Object state;
    private volatile TimeUnit tunit;
    private volatile long validDuration;

    public ConnectionHolder(HttpClientAndroidLog log2, HttpClientConnectionManager manager2, HttpClientConnection managedConn2) {
        this.log = log2;
        this.manager = manager2;
        this.managedConn = managedConn2;
    }

    public boolean isReusable() {
        return this.reusable;
    }

    public void markReusable() {
        this.reusable = true;
    }

    public void markNonReusable() {
        this.reusable = false;
    }

    public void setState(Object state2) {
        this.state = state2;
    }

    public void setValidFor(long duration, TimeUnit tunit2) {
        synchronized (this.managedConn) {
            this.validDuration = duration;
            this.tunit = tunit2;
        }
    }

    public void releaseConnection() {
        synchronized (this.managedConn) {
            if (!this.released) {
                this.released = true;
                if (this.reusable) {
                    this.manager.releaseConnection(this.managedConn, this.state, this.validDuration, this.tunit);
                } else {
                    try {
                        this.managedConn.close();
                        this.log.debug("Connection discarded");
                        this.manager.releaseConnection(this.managedConn, null, 0, TimeUnit.MILLISECONDS);
                    } catch (IOException ex) {
                        try {
                            if (this.log.isDebugEnabled()) {
                                this.log.debug(ex.getMessage(), ex);
                            }
                        } finally {
                            this.manager.releaseConnection(this.managedConn, null, 0, TimeUnit.MILLISECONDS);
                        }
                    }
                }
            }
        }
    }

    public void abortConnection() {
        synchronized (this.managedConn) {
            if (!this.released) {
                this.released = true;
                try {
                    this.managedConn.shutdown();
                    this.log.debug("Connection discarded");
                    this.manager.releaseConnection(this.managedConn, null, 0, TimeUnit.MILLISECONDS);
                } catch (IOException ex) {
                    try {
                        if (this.log.isDebugEnabled()) {
                            this.log.debug(ex.getMessage(), ex);
                        }
                    } finally {
                        this.manager.releaseConnection(this.managedConn, null, 0, TimeUnit.MILLISECONDS);
                    }
                }
            }
        }
    }

    public boolean cancel() {
        boolean alreadyReleased = this.released;
        this.log.debug("Cancelling request execution");
        abortConnection();
        return !alreadyReleased;
    }

    public boolean isReleased() {
        return this.released;
    }

    public void close() throws IOException {
        abortConnection();
    }
}
