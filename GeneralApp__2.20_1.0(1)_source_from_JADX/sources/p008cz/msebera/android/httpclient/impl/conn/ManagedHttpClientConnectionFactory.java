package p008cz.msebera.android.httpclient.impl.conn;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.concurrent.atomic.AtomicLong;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.config.ConnectionConfig;
import p008cz.msebera.android.httpclient.conn.HttpConnectionFactory;
import p008cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.impl.p012io.DefaultHttpRequestWriterFactory;
import p008cz.msebera.android.httpclient.p013io.HttpMessageParserFactory;
import p008cz.msebera.android.httpclient.p013io.HttpMessageWriterFactory;

/* renamed from: cz.msebera.android.httpclient.impl.conn.ManagedHttpClientConnectionFactory */
public class ManagedHttpClientConnectionFactory implements HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> {
    private static final AtomicLong COUNTER = new AtomicLong();
    public static final ManagedHttpClientConnectionFactory INSTANCE = new ManagedHttpClientConnectionFactory();
    public HttpClientAndroidLog headerlog;
    public HttpClientAndroidLog log;
    private final HttpMessageWriterFactory<HttpRequest> requestWriterFactory;
    private final HttpMessageParserFactory<HttpResponse> responseParserFactory;
    public HttpClientAndroidLog wirelog;

    public ManagedHttpClientConnectionFactory(HttpMessageWriterFactory<HttpRequest> requestWriterFactory2, HttpMessageParserFactory<HttpResponse> responseParserFactory2) {
        this.log = new HttpClientAndroidLog(DefaultManagedHttpClientConnection.class);
        this.headerlog = new HttpClientAndroidLog("cz.msebera.android.httpclient.headers");
        this.wirelog = new HttpClientAndroidLog("cz.msebera.android.httpclient.wire");
        this.requestWriterFactory = requestWriterFactory2 != null ? requestWriterFactory2 : DefaultHttpRequestWriterFactory.INSTANCE;
        this.responseParserFactory = responseParserFactory2 != null ? responseParserFactory2 : DefaultHttpResponseParserFactory.INSTANCE;
    }

    public ManagedHttpClientConnectionFactory(HttpMessageParserFactory<HttpResponse> responseParserFactory2) {
        this(null, responseParserFactory2);
    }

    public ManagedHttpClientConnectionFactory() {
        this(null, null);
    }

    public ManagedHttpClientConnection create(HttpRoute route, ConnectionConfig config) {
        ConnectionConfig cconfig = config != null ? config : ConnectionConfig.DEFAULT;
        CharsetDecoder chardecoder = null;
        CharsetEncoder charencoder = null;
        Charset charset = cconfig.getCharset();
        CodingErrorAction malformedInputAction = cconfig.getMalformedInputAction() != null ? cconfig.getMalformedInputAction() : CodingErrorAction.REPORT;
        CodingErrorAction unmappableInputAction = cconfig.getUnmappableInputAction() != null ? cconfig.getUnmappableInputAction() : CodingErrorAction.REPORT;
        if (charset != null) {
            chardecoder = charset.newDecoder();
            chardecoder.onMalformedInput(malformedInputAction);
            chardecoder.onUnmappableCharacter(unmappableInputAction);
            charencoder = charset.newEncoder();
            charencoder.onMalformedInput(malformedInputAction);
            charencoder.onUnmappableCharacter(unmappableInputAction);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("http-outgoing-");
        sb.append(Long.toString(COUNTER.getAndIncrement()));
        LoggingManagedHttpClientConnection loggingManagedHttpClientConnection = new LoggingManagedHttpClientConnection(sb.toString(), this.log, this.headerlog, this.wirelog, cconfig.getBufferSize(), cconfig.getFragmentSizeHint(), chardecoder, charencoder, cconfig.getMessageConstraints(), null, null, this.requestWriterFactory, this.responseParserFactory);
        return loggingManagedHttpClientConnection;
    }
}
