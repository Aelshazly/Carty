package p008cz.msebera.android.httpclient.impl.execchain;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.conn.EofSensorInputStream;
import p008cz.msebera.android.httpclient.conn.EofSensorWatcher;
import p008cz.msebera.android.httpclient.entity.HttpEntityWrapper;

/* renamed from: cz.msebera.android.httpclient.impl.execchain.ResponseEntityProxy */
class ResponseEntityProxy extends HttpEntityWrapper implements EofSensorWatcher {
    private final ConnectionHolder connHolder;

    public static void enchance(HttpResponse response, ConnectionHolder connHolder2) {
        HttpEntity entity = response.getEntity();
        if (entity != null && entity.isStreaming() && connHolder2 != null) {
            response.setEntity(new ResponseEntityProxy(entity, connHolder2));
        }
    }

    ResponseEntityProxy(HttpEntity entity, ConnectionHolder connHolder2) {
        super(entity);
        this.connHolder = connHolder2;
    }

    private void cleanup() {
        ConnectionHolder connectionHolder = this.connHolder;
        if (connectionHolder != null) {
            connectionHolder.abortConnection();
        }
    }

    public void releaseConnection() throws IOException {
        ConnectionHolder connectionHolder = this.connHolder;
        if (connectionHolder != null) {
            try {
                if (connectionHolder.isReusable()) {
                    this.connHolder.releaseConnection();
                }
            } finally {
                cleanup();
            }
        }
    }

    public boolean isRepeatable() {
        return false;
    }

    public InputStream getContent() throws IOException {
        return new EofSensorInputStream(this.wrappedEntity.getContent(), this);
    }

    @Deprecated
    public void consumeContent() throws IOException {
        releaseConnection();
    }

    public void writeTo(OutputStream outstream) throws IOException {
        try {
            this.wrappedEntity.writeTo(outstream);
            releaseConnection();
        } finally {
            cleanup();
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean eofDetected(InputStream wrapped) throws IOException {
        try {
            wrapped.close();
            releaseConnection();
            cleanup();
            return false;
        } catch (Throwable th) {
            cleanup();
            throw th;
        }
    }

    public boolean streamClosed(InputStream wrapped) throws IOException {
        boolean open;
        try {
            open = this.connHolder != null && !this.connHolder.isReleased();
            wrapped.close();
            releaseConnection();
        } catch (SocketException ex) {
            if (open) {
                throw ex;
            }
        } catch (Throwable th) {
            cleanup();
            throw th;
        }
        cleanup();
        return false;
    }

    public boolean streamAbort(InputStream wrapped) throws IOException {
        cleanup();
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ResponseEntityProxy{");
        sb.append(this.wrappedEntity);
        sb.append('}');
        return sb.toString();
    }
}
