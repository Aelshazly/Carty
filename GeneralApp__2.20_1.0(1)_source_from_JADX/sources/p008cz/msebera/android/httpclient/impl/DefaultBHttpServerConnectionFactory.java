package p008cz.msebera.android.httpclient.impl;

import java.io.IOException;
import java.net.Socket;
import p008cz.msebera.android.httpclient.HttpConnectionFactory;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.config.ConnectionConfig;
import p008cz.msebera.android.httpclient.entity.ContentLengthStrategy;
import p008cz.msebera.android.httpclient.p013io.HttpMessageParserFactory;
import p008cz.msebera.android.httpclient.p013io.HttpMessageWriterFactory;

/* renamed from: cz.msebera.android.httpclient.impl.DefaultBHttpServerConnectionFactory */
public class DefaultBHttpServerConnectionFactory implements HttpConnectionFactory<DefaultBHttpServerConnection> {
    public static final DefaultBHttpServerConnectionFactory INSTANCE = new DefaultBHttpServerConnectionFactory();
    private final ConnectionConfig cconfig;
    private final ContentLengthStrategy incomingContentStrategy;
    private final ContentLengthStrategy outgoingContentStrategy;
    private final HttpMessageParserFactory<HttpRequest> requestParserFactory;
    private final HttpMessageWriterFactory<HttpResponse> responseWriterFactory;

    public DefaultBHttpServerConnectionFactory(ConnectionConfig cconfig2, ContentLengthStrategy incomingContentStrategy2, ContentLengthStrategy outgoingContentStrategy2, HttpMessageParserFactory<HttpRequest> requestParserFactory2, HttpMessageWriterFactory<HttpResponse> responseWriterFactory2) {
        this.cconfig = cconfig2 != null ? cconfig2 : ConnectionConfig.DEFAULT;
        this.incomingContentStrategy = incomingContentStrategy2;
        this.outgoingContentStrategy = outgoingContentStrategy2;
        this.requestParserFactory = requestParserFactory2;
        this.responseWriterFactory = responseWriterFactory2;
    }

    public DefaultBHttpServerConnectionFactory(ConnectionConfig cconfig2, HttpMessageParserFactory<HttpRequest> requestParserFactory2, HttpMessageWriterFactory<HttpResponse> responseWriterFactory2) {
        this(cconfig2, null, null, requestParserFactory2, responseWriterFactory2);
    }

    public DefaultBHttpServerConnectionFactory(ConnectionConfig cconfig2) {
        this(cconfig2, null, null, null, null);
    }

    public DefaultBHttpServerConnectionFactory() {
        this(null, null, null, null, null);
    }

    public DefaultBHttpServerConnection createConnection(Socket socket) throws IOException {
        DefaultBHttpServerConnection conn = new DefaultBHttpServerConnection(this.cconfig.getBufferSize(), this.cconfig.getFragmentSizeHint(), ConnSupport.createDecoder(this.cconfig), ConnSupport.createEncoder(this.cconfig), this.cconfig.getMessageConstraints(), this.incomingContentStrategy, this.outgoingContentStrategy, this.requestParserFactory, this.responseWriterFactory);
        conn.bind(socket);
        return conn;
    }
}
