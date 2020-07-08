package p008cz.msebera.android.httpclient.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import p008cz.msebera.android.httpclient.HttpInetConnection;
import p008cz.msebera.android.httpclient.impl.p012io.SocketInputBuffer;
import p008cz.msebera.android.httpclient.impl.p012io.SocketOutputBuffer;
import p008cz.msebera.android.httpclient.p013io.SessionInputBuffer;
import p008cz.msebera.android.httpclient.p013io.SessionOutputBuffer;
import p008cz.msebera.android.httpclient.params.CoreConnectionPNames;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.SocketHttpClientConnection */
public class SocketHttpClientConnection extends AbstractHttpClientConnection implements HttpInetConnection {
    private volatile boolean open;
    private volatile Socket socket = null;

    /* access modifiers changed from: protected */
    public void assertNotOpen() {
        Asserts.check(!this.open, "Connection is already open");
    }

    /* access modifiers changed from: protected */
    public void assertOpen() {
        Asserts.check(this.open, "Connection is not open");
    }

    /* access modifiers changed from: protected */
    public SessionInputBuffer createSessionInputBuffer(Socket socket2, int buffersize, HttpParams params) throws IOException {
        return new SocketInputBuffer(socket2, buffersize, params);
    }

    /* access modifiers changed from: protected */
    public SessionOutputBuffer createSessionOutputBuffer(Socket socket2, int buffersize, HttpParams params) throws IOException {
        return new SocketOutputBuffer(socket2, buffersize, params);
    }

    /* access modifiers changed from: protected */
    public void bind(Socket socket2, HttpParams params) throws IOException {
        Args.notNull(socket2, "Socket");
        Args.notNull(params, "HTTP parameters");
        this.socket = socket2;
        int buffersize = params.getIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, -1);
        init(createSessionInputBuffer(socket2, buffersize, params), createSessionOutputBuffer(socket2, buffersize, params), params);
        this.open = true;
    }

    public boolean isOpen() {
        return this.open;
    }

    /* access modifiers changed from: protected */
    public Socket getSocket() {
        return this.socket;
    }

    public InetAddress getLocalAddress() {
        if (this.socket != null) {
            return this.socket.getLocalAddress();
        }
        return null;
    }

    public int getLocalPort() {
        if (this.socket != null) {
            return this.socket.getLocalPort();
        }
        return -1;
    }

    public InetAddress getRemoteAddress() {
        if (this.socket != null) {
            return this.socket.getInetAddress();
        }
        return null;
    }

    public int getRemotePort() {
        if (this.socket != null) {
            return this.socket.getPort();
        }
        return -1;
    }

    public void setSocketTimeout(int timeout) {
        assertOpen();
        if (this.socket != null) {
            try {
                this.socket.setSoTimeout(timeout);
            } catch (SocketException e) {
            }
        }
    }

    public int getSocketTimeout() {
        if (this.socket == null) {
            return -1;
        }
        try {
            return this.socket.getSoTimeout();
        } catch (SocketException e) {
            return -1;
        }
    }

    public void shutdown() throws IOException {
        this.open = false;
        Socket tmpsocket = this.socket;
        if (tmpsocket != null) {
            tmpsocket.close();
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0011 A[ExcHandler: IOException | UnsupportedOperationException (e java.lang.Throwable), Splitter:B:6:0x000d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() throws java.io.IOException {
        /*
            r2 = this;
            boolean r0 = r2.open
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            r0 = 0
            r2.open = r0
            java.net.Socket r0 = r2.socket
            r2.doFlush()     // Catch:{ all -> 0x001f }
            r0.shutdownOutput()     // Catch:{ IOException -> 0x0013, UnsupportedOperationException -> 0x0011 }
            goto L_0x0014
        L_0x0011:
            r1 = move-exception
            goto L_0x001a
        L_0x0013:
            r1 = move-exception
        L_0x0014:
            r0.shutdownInput()     // Catch:{ IOException -> 0x0018, UnsupportedOperationException -> 0x0011 }
            goto L_0x0019
        L_0x0018:
            r1 = move-exception
        L_0x0019:
        L_0x001a:
            r0.close()
            return
        L_0x001f:
            r1 = move-exception
            r0.close()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.SocketHttpClientConnection.close():void");
    }

    private static void formatAddress(StringBuilder buffer, SocketAddress socketAddress) {
        Object obj;
        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress addr = (InetSocketAddress) socketAddress;
            if (addr.getAddress() != null) {
                obj = addr.getAddress().getHostAddress();
            } else {
                obj = addr.getAddress();
            }
            buffer.append(obj);
            buffer.append(':');
            buffer.append(addr.getPort());
            return;
        }
        buffer.append(socketAddress);
    }

    public String toString() {
        if (this.socket == null) {
            return super.toString();
        }
        StringBuilder buffer = new StringBuilder();
        SocketAddress remoteAddress = this.socket.getRemoteSocketAddress();
        SocketAddress localAddress = this.socket.getLocalSocketAddress();
        if (!(remoteAddress == null || localAddress == null)) {
            formatAddress(buffer, localAddress);
            buffer.append("<->");
            formatAddress(buffer, remoteAddress);
        }
        return buffer.toString();
    }
}
