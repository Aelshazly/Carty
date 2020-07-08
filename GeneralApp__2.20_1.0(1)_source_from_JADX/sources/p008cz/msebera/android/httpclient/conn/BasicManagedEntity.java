package p008cz.msebera.android.httpclient.conn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.entity.HttpEntityWrapper;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.EntityUtils;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.conn.BasicManagedEntity */
public class BasicManagedEntity extends HttpEntityWrapper implements ConnectionReleaseTrigger, EofSensorWatcher {
    protected final boolean attemptReuse;
    protected ManagedClientConnection managedConn;

    public BasicManagedEntity(HttpEntity entity, ManagedClientConnection conn, boolean reuse) {
        super(entity);
        Args.notNull(conn, "Connection");
        this.managedConn = conn;
        this.attemptReuse = reuse;
    }

    public boolean isRepeatable() {
        return false;
    }

    public InputStream getContent() throws IOException {
        return new EofSensorInputStream(this.wrappedEntity.getContent(), this);
    }

    private void ensureConsumed() throws IOException {
        ManagedClientConnection managedClientConnection = this.managedConn;
        if (managedClientConnection != null) {
            try {
                if (this.attemptReuse) {
                    EntityUtils.consume(this.wrappedEntity);
                    this.managedConn.markReusable();
                } else {
                    managedClientConnection.unmarkReusable();
                }
            } finally {
                releaseManagedConnection();
            }
        }
    }

    @Deprecated
    public void consumeContent() throws IOException {
        ensureConsumed();
    }

    public void writeTo(OutputStream outstream) throws IOException {
        super.writeTo(outstream);
        ensureConsumed();
    }

    public void releaseConnection() throws IOException {
        ensureConsumed();
    }

    public void abortConnection() throws IOException {
        ManagedClientConnection managedClientConnection = this.managedConn;
        if (managedClientConnection != null) {
            try {
                managedClientConnection.abortConnection();
            } finally {
                this.managedConn = null;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean eofDetected(InputStream wrapped) throws IOException {
        try {
            if (this.managedConn != null) {
                if (this.attemptReuse) {
                    wrapped.close();
                    this.managedConn.markReusable();
                } else {
                    this.managedConn.unmarkReusable();
                }
            }
            releaseManagedConnection();
            return false;
        } catch (Throwable th) {
            releaseManagedConnection();
            throw th;
        }
    }

    public boolean streamClosed(InputStream wrapped) throws IOException {
        boolean valid;
        try {
            if (this.managedConn != null) {
                if (this.attemptReuse) {
                    valid = this.managedConn.isOpen();
                    wrapped.close();
                    this.managedConn.markReusable();
                } else {
                    this.managedConn.unmarkReusable();
                }
            }
        } catch (SocketException ex) {
            if (valid) {
                throw ex;
            }
        } catch (Throwable th) {
            releaseManagedConnection();
            throw th;
        }
        releaseManagedConnection();
        return false;
    }

    public boolean streamAbort(InputStream wrapped) throws IOException {
        ManagedClientConnection managedClientConnection = this.managedConn;
        if (managedClientConnection != null) {
            managedClientConnection.abortConnection();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void releaseManagedConnection() throws IOException {
        ManagedClientConnection managedClientConnection = this.managedConn;
        if (managedClientConnection != null) {
            try {
                managedClientConnection.releaseConnection();
            } finally {
                this.managedConn = null;
            }
        }
    }
}
