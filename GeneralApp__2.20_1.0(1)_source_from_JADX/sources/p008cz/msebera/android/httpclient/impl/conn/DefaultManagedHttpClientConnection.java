package p008cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.config.MessageConstraints;
import p008cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import p008cz.msebera.android.httpclient.entity.ContentLengthStrategy;
import p008cz.msebera.android.httpclient.impl.DefaultBHttpClientConnection;
import p008cz.msebera.android.httpclient.p013io.HttpMessageParserFactory;
import p008cz.msebera.android.httpclient.p013io.HttpMessageWriterFactory;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.impl.conn.DefaultManagedHttpClientConnection */
public class DefaultManagedHttpClientConnection extends DefaultBHttpClientConnection implements ManagedHttpClientConnection, HttpContext {
    private final Map<String, Object> attributes;

    /* renamed from: id */
    private final String f236id;
    private volatile boolean shutdown;

    public DefaultManagedHttpClientConnection(String id, int buffersize, int fragmentSizeHint, CharsetDecoder chardecoder, CharsetEncoder charencoder, MessageConstraints constraints, ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy, HttpMessageWriterFactory<HttpRequest> requestWriterFactory, HttpMessageParserFactory<HttpResponse> responseParserFactory) {
        super(buffersize, fragmentSizeHint, chardecoder, charencoder, constraints, incomingContentStrategy, outgoingContentStrategy, requestWriterFactory, responseParserFactory);
        this.f236id = id;
        this.attributes = new ConcurrentHashMap();
    }

    public DefaultManagedHttpClientConnection(String id, int buffersize) {
        this(id, buffersize, buffersize, null, null, null, null, null, null, null);
    }

    public String getId() {
        return this.f236id;
    }

    public void shutdown() throws IOException {
        this.shutdown = true;
        super.shutdown();
    }

    public Object getAttribute(String id) {
        return this.attributes.get(id);
    }

    public Object removeAttribute(String id) {
        return this.attributes.remove(id);
    }

    public void setAttribute(String id, Object obj) {
        this.attributes.put(id, obj);
    }

    public void bind(Socket socket) throws IOException {
        if (!this.shutdown) {
            super.bind(socket);
        } else {
            socket.close();
            throw new InterruptedIOException("Connection already shutdown");
        }
    }

    public Socket getSocket() {
        return super.getSocket();
    }

    public SSLSession getSSLSession() {
        Socket socket = super.getSocket();
        if (socket instanceof SSLSocket) {
            return ((SSLSocket) socket).getSession();
        }
        return null;
    }
}
