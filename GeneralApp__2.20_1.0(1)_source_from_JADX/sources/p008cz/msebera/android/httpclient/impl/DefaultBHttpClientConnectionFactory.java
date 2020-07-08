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

/* renamed from: cz.msebera.android.httpclient.impl.DefaultBHttpClientConnectionFactory */
public class DefaultBHttpClientConnectionFactory implements HttpConnectionFactory<DefaultBHttpClientConnection> {
    public static final DefaultBHttpClientConnectionFactory INSTANCE = new DefaultBHttpClientConnectionFactory();
    private final ConnectionConfig cconfig;
    private final ContentLengthStrategy incomingContentStrategy;
    private final ContentLengthStrategy outgoingContentStrategy;
    private final HttpMessageWriterFactory<HttpRequest> requestWriterFactory;
    private final HttpMessageParserFactory<HttpResponse> responseParserFactory;

    public DefaultBHttpClientConnectionFactory(ConnectionConfig cconfig2, ContentLengthStrategy incomingContentStrategy2, ContentLengthStrategy outgoingContentStrategy2, HttpMessageWriterFactory<HttpRequest> requestWriterFactory2, HttpMessageParserFactory<HttpResponse> responseParserFactory2) {
        this.cconfig = cconfig2 != null ? cconfig2 : ConnectionConfig.DEFAULT;
        this.incomingContentStrategy = incomingContentStrategy2;
        this.outgoingContentStrategy = outgoingContentStrategy2;
        this.requestWriterFactory = requestWriterFactory2;
        this.responseParserFactory = responseParserFactory2;
    }

    public DefaultBHttpClientConnectionFactory(ConnectionConfig cconfig2, HttpMessageWriterFactory<HttpRequest> requestWriterFactory2, HttpMessageParserFactory<HttpResponse> responseParserFactory2) {
        this(cconfig2, null, null, requestWriterFactory2, responseParserFactory2);
    }

    public DefaultBHttpClientConnectionFactory(ConnectionConfig cconfig2) {
        this(cconfig2, null, null, null, null);
    }

    public DefaultBHttpClientConnectionFactory() {
        this(null, null, null, null, null);
    }

    public DefaultBHttpClientConnection createConnection(Socket socket) throws IOException {
        DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(this.cconfig.getBufferSize(), this.cconfig.getFragmentSizeHint(), ConnSupport.createDecoder(this.cconfig), ConnSupport.createEncoder(this.cconfig), this.cconfig.getMessageConstraints(), this.incomingContentStrategy, this.outgoingContentStrategy, this.requestWriterFactory, this.responseParserFactory);
        conn.bind(socket);
        return conn;
    }
}
