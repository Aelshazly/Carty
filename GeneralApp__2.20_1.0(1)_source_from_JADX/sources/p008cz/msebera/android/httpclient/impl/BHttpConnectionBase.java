package p008cz.msebera.android.httpclient.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.concurrent.atomic.AtomicReference;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpConnection;
import p008cz.msebera.android.httpclient.HttpConnectionMetrics;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpInetConnection;
import p008cz.msebera.android.httpclient.HttpMessage;
import p008cz.msebera.android.httpclient.config.MessageConstraints;
import p008cz.msebera.android.httpclient.entity.BasicHttpEntity;
import p008cz.msebera.android.httpclient.entity.ContentLengthStrategy;
import p008cz.msebera.android.httpclient.impl.entity.LaxContentLengthStrategy;
import p008cz.msebera.android.httpclient.impl.entity.StrictContentLengthStrategy;
import p008cz.msebera.android.httpclient.impl.p012io.ChunkedInputStream;
import p008cz.msebera.android.httpclient.impl.p012io.ChunkedOutputStream;
import p008cz.msebera.android.httpclient.impl.p012io.ContentLengthInputStream;
import p008cz.msebera.android.httpclient.impl.p012io.ContentLengthOutputStream;
import p008cz.msebera.android.httpclient.impl.p012io.HttpTransportMetricsImpl;
import p008cz.msebera.android.httpclient.impl.p012io.IdentityInputStream;
import p008cz.msebera.android.httpclient.impl.p012io.IdentityOutputStream;
import p008cz.msebera.android.httpclient.impl.p012io.SessionInputBufferImpl;
import p008cz.msebera.android.httpclient.impl.p012io.SessionOutputBufferImpl;
import p008cz.msebera.android.httpclient.p013io.SessionInputBuffer;
import p008cz.msebera.android.httpclient.p013io.SessionOutputBuffer;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;
import p008cz.msebera.android.httpclient.util.NetUtils;

/* renamed from: cz.msebera.android.httpclient.impl.BHttpConnectionBase */
public class BHttpConnectionBase implements HttpConnection, HttpInetConnection {
    private final HttpConnectionMetricsImpl connMetrics;
    private final SessionInputBufferImpl inbuffer;
    private final ContentLengthStrategy incomingContentStrategy;
    private final SessionOutputBufferImpl outbuffer;
    private final ContentLengthStrategy outgoingContentStrategy;
    private final AtomicReference<Socket> socketHolder;

    protected BHttpConnectionBase(int buffersize, int fragmentSizeHint, CharsetDecoder chardecoder, CharsetEncoder charencoder, MessageConstraints constraints, ContentLengthStrategy incomingContentStrategy2, ContentLengthStrategy outgoingContentStrategy2) {
        int i = buffersize;
        Args.positive(buffersize, "Buffer size");
        HttpTransportMetricsImpl inTransportMetrics = new HttpTransportMetricsImpl();
        HttpTransportMetricsImpl outTransportMetrics = new HttpTransportMetricsImpl();
        SessionInputBufferImpl sessionInputBufferImpl = new SessionInputBufferImpl(inTransportMetrics, buffersize, -1, constraints != null ? constraints : MessageConstraints.DEFAULT, chardecoder);
        this.inbuffer = sessionInputBufferImpl;
        int i2 = fragmentSizeHint;
        CharsetEncoder charsetEncoder = charencoder;
        this.outbuffer = new SessionOutputBufferImpl(outTransportMetrics, buffersize, fragmentSizeHint, charencoder);
        this.connMetrics = new HttpConnectionMetricsImpl(inTransportMetrics, outTransportMetrics);
        this.incomingContentStrategy = incomingContentStrategy2 != null ? incomingContentStrategy2 : LaxContentLengthStrategy.INSTANCE;
        this.outgoingContentStrategy = outgoingContentStrategy2 != null ? outgoingContentStrategy2 : StrictContentLengthStrategy.INSTANCE;
        this.socketHolder = new AtomicReference<>();
    }

    /* access modifiers changed from: protected */
    public void ensureOpen() throws IOException {
        Socket socket = (Socket) this.socketHolder.get();
        Asserts.check(socket != null, "Connection is not open");
        if (!this.inbuffer.isBound()) {
            this.inbuffer.bind(getSocketInputStream(socket));
        }
        if (!this.outbuffer.isBound()) {
            this.outbuffer.bind(getSocketOutputStream(socket));
        }
    }

    /* access modifiers changed from: protected */
    public InputStream getSocketInputStream(Socket socket) throws IOException {
        return socket.getInputStream();
    }

    /* access modifiers changed from: protected */
    public OutputStream getSocketOutputStream(Socket socket) throws IOException {
        return socket.getOutputStream();
    }

    /* access modifiers changed from: protected */
    public void bind(Socket socket) throws IOException {
        Args.notNull(socket, "Socket");
        this.socketHolder.set(socket);
        this.inbuffer.bind(null);
        this.outbuffer.bind(null);
    }

    /* access modifiers changed from: protected */
    public SessionInputBuffer getSessionInputBuffer() {
        return this.inbuffer;
    }

    /* access modifiers changed from: protected */
    public SessionOutputBuffer getSessionOutputBuffer() {
        return this.outbuffer;
    }

    /* access modifiers changed from: protected */
    public void doFlush() throws IOException {
        this.outbuffer.flush();
    }

    public boolean isOpen() {
        return this.socketHolder.get() != null;
    }

    /* access modifiers changed from: protected */
    public Socket getSocket() {
        return (Socket) this.socketHolder.get();
    }

    /* access modifiers changed from: protected */
    public OutputStream createOutputStream(long len, SessionOutputBuffer outbuffer2) {
        if (len == -2) {
            return new ChunkedOutputStream(2048, outbuffer2);
        }
        if (len == -1) {
            return new IdentityOutputStream(outbuffer2);
        }
        return new ContentLengthOutputStream(outbuffer2, len);
    }

    /* access modifiers changed from: protected */
    public OutputStream prepareOutput(HttpMessage message) throws HttpException {
        return createOutputStream(this.outgoingContentStrategy.determineLength(message), this.outbuffer);
    }

    /* access modifiers changed from: protected */
    public InputStream createInputStream(long len, SessionInputBuffer inbuffer2) {
        if (len == -2) {
            return new ChunkedInputStream(inbuffer2);
        }
        if (len == -1) {
            return new IdentityInputStream(inbuffer2);
        }
        return new ContentLengthInputStream(inbuffer2, len);
    }

    /* access modifiers changed from: protected */
    public HttpEntity prepareInput(HttpMessage message) throws HttpException {
        BasicHttpEntity entity = new BasicHttpEntity();
        long len = this.incomingContentStrategy.determineLength(message);
        InputStream instream = createInputStream(len, this.inbuffer);
        if (len == -2) {
            entity.setChunked(true);
            entity.setContentLength(-1);
            entity.setContent(instream);
        } else if (len == -1) {
            entity.setChunked(false);
            entity.setContentLength(-1);
            entity.setContent(instream);
        } else {
            entity.setChunked(false);
            entity.setContentLength(len);
            entity.setContent(instream);
        }
        Header contentTypeHeader = message.getFirstHeader("Content-Type");
        if (contentTypeHeader != null) {
            entity.setContentType(contentTypeHeader);
        }
        Header contentEncodingHeader = message.getFirstHeader("Content-Encoding");
        if (contentEncodingHeader != null) {
            entity.setContentEncoding(contentEncodingHeader);
        }
        return entity;
    }

    public InetAddress getLocalAddress() {
        Socket socket = (Socket) this.socketHolder.get();
        if (socket != null) {
            return socket.getLocalAddress();
        }
        return null;
    }

    public int getLocalPort() {
        Socket socket = (Socket) this.socketHolder.get();
        if (socket != null) {
            return socket.getLocalPort();
        }
        return -1;
    }

    public InetAddress getRemoteAddress() {
        Socket socket = (Socket) this.socketHolder.get();
        if (socket != null) {
            return socket.getInetAddress();
        }
        return null;
    }

    public int getRemotePort() {
        Socket socket = (Socket) this.socketHolder.get();
        if (socket != null) {
            return socket.getPort();
        }
        return -1;
    }

    public void setSocketTimeout(int timeout) {
        Socket socket = (Socket) this.socketHolder.get();
        if (socket != null) {
            try {
                socket.setSoTimeout(timeout);
            } catch (SocketException e) {
            }
        }
    }

    public int getSocketTimeout() {
        Socket socket = (Socket) this.socketHolder.get();
        if (socket == null) {
            return -1;
        }
        try {
            return socket.getSoTimeout();
        } catch (SocketException e) {
            return -1;
        }
    }

    public void shutdown() throws IOException {
        Socket socket = (Socket) this.socketHolder.getAndSet(null);
        if (socket != null) {
            socket.close();
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0019 A[ExcHandler: IOException | UnsupportedOperationException (e java.lang.Throwable), Splitter:B:4:0x0015] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() throws java.io.IOException {
        /*
            r2 = this;
            java.util.concurrent.atomic.AtomicReference<java.net.Socket> r0 = r2.socketHolder
            r1 = 0
            java.lang.Object r0 = r0.getAndSet(r1)
            java.net.Socket r0 = (java.net.Socket) r0
            if (r0 == 0) goto L_0x002b
            cz.msebera.android.httpclient.impl.io.SessionInputBufferImpl r1 = r2.inbuffer     // Catch:{ all -> 0x0026 }
            r1.clear()     // Catch:{ all -> 0x0026 }
            cz.msebera.android.httpclient.impl.io.SessionOutputBufferImpl r1 = r2.outbuffer     // Catch:{ all -> 0x0026 }
            r1.flush()     // Catch:{ all -> 0x0026 }
            r0.shutdownOutput()     // Catch:{ IOException -> 0x001b, UnsupportedOperationException -> 0x0019 }
            goto L_0x001c
        L_0x0019:
            r1 = move-exception
            goto L_0x0022
        L_0x001b:
            r1 = move-exception
        L_0x001c:
            r0.shutdownInput()     // Catch:{ IOException -> 0x0020, UnsupportedOperationException -> 0x0019 }
            goto L_0x0021
        L_0x0020:
            r1 = move-exception
        L_0x0021:
        L_0x0022:
            r0.close()
            goto L_0x002b
        L_0x0026:
            r1 = move-exception
            r0.close()
            throw r1
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.BHttpConnectionBase.close():void");
    }

    private int fillInputBuffer(int timeout) throws IOException {
        Socket socket = (Socket) this.socketHolder.get();
        int oldtimeout = socket.getSoTimeout();
        try {
            socket.setSoTimeout(timeout);
            return this.inbuffer.fillBuffer();
        } finally {
            socket.setSoTimeout(oldtimeout);
        }
    }

    /* access modifiers changed from: protected */
    public boolean awaitInput(int timeout) throws IOException {
        if (this.inbuffer.hasBufferedData()) {
            return true;
        }
        fillInputBuffer(timeout);
        return this.inbuffer.hasBufferedData();
    }

    public boolean isStale() {
        if (!isOpen()) {
            return true;
        }
        boolean z = false;
        try {
            if (fillInputBuffer(1) < 0) {
                z = true;
            }
            return z;
        } catch (SocketTimeoutException e) {
            return false;
        } catch (IOException e2) {
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void incrementRequestCount() {
        this.connMetrics.incrementRequestCount();
    }

    /* access modifiers changed from: protected */
    public void incrementResponseCount() {
        this.connMetrics.incrementResponseCount();
    }

    public HttpConnectionMetrics getMetrics() {
        return this.connMetrics;
    }

    public String toString() {
        Socket socket = (Socket) this.socketHolder.get();
        if (socket == null) {
            return "[Not bound]";
        }
        StringBuilder buffer = new StringBuilder();
        SocketAddress remoteAddress = socket.getRemoteSocketAddress();
        SocketAddress localAddress = socket.getLocalSocketAddress();
        if (!(remoteAddress == null || localAddress == null)) {
            NetUtils.formatAddress(buffer, localAddress);
            buffer.append("<->");
            NetUtils.formatAddress(buffer, remoteAddress);
        }
        return buffer.toString();
    }
}
