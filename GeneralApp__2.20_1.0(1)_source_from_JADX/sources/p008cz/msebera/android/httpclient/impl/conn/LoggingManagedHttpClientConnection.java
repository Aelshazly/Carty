package p008cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.config.MessageConstraints;
import p008cz.msebera.android.httpclient.entity.ContentLengthStrategy;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.p013io.HttpMessageParserFactory;
import p008cz.msebera.android.httpclient.p013io.HttpMessageWriterFactory;

/* renamed from: cz.msebera.android.httpclient.impl.conn.LoggingManagedHttpClientConnection */
class LoggingManagedHttpClientConnection extends DefaultManagedHttpClientConnection {
    private final HttpClientAndroidLog headerlog;
    public HttpClientAndroidLog log;
    private final Wire wire;

    public LoggingManagedHttpClientConnection(String id, HttpClientAndroidLog log2, HttpClientAndroidLog headerlog2, HttpClientAndroidLog wirelog, int buffersize, int fragmentSizeHint, CharsetDecoder chardecoder, CharsetEncoder charencoder, MessageConstraints constraints, ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy, HttpMessageWriterFactory<HttpRequest> requestWriterFactory, HttpMessageParserFactory<HttpResponse> responseParserFactory) {
        super(id, buffersize, fragmentSizeHint, chardecoder, charencoder, constraints, incomingContentStrategy, outgoingContentStrategy, requestWriterFactory, responseParserFactory);
        this.log = log2;
        this.headerlog = headerlog2;
        String str = id;
        this.wire = new Wire(wirelog, id);
    }

    public void close() throws IOException {
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append(getId());
            sb.append(": Close connection");
            httpClientAndroidLog.debug(sb.toString());
        }
        super.close();
    }

    public void shutdown() throws IOException {
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append(getId());
            sb.append(": Shutdown connection");
            httpClientAndroidLog.debug(sb.toString());
        }
        super.shutdown();
    }

    /* access modifiers changed from: protected */
    public InputStream getSocketInputStream(Socket socket) throws IOException {
        InputStream in = super.getSocketInputStream(socket);
        if (this.wire.enabled()) {
            return new LoggingInputStream(in, this.wire);
        }
        return in;
    }

    /* access modifiers changed from: protected */
    public OutputStream getSocketOutputStream(Socket socket) throws IOException {
        OutputStream out = super.getSocketOutputStream(socket);
        if (this.wire.enabled()) {
            return new LoggingOutputStream(out, this.wire);
        }
        return out;
    }

    /* access modifiers changed from: protected */
    public void onResponseReceived(HttpResponse response) {
        Header[] headers;
        if (response != null && this.headerlog.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.headerlog;
            StringBuilder sb = new StringBuilder();
            sb.append(getId());
            String str = " << ";
            sb.append(str);
            sb.append(response.getStatusLine().toString());
            httpClientAndroidLog.debug(sb.toString());
            for (Header header : response.getAllHeaders()) {
                HttpClientAndroidLog httpClientAndroidLog2 = this.headerlog;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(getId());
                sb2.append(str);
                sb2.append(header.toString());
                httpClientAndroidLog2.debug(sb2.toString());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onRequestSubmitted(HttpRequest request) {
        Header[] headers;
        if (request != null && this.headerlog.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.headerlog;
            StringBuilder sb = new StringBuilder();
            sb.append(getId());
            String str = " >> ";
            sb.append(str);
            sb.append(request.getRequestLine().toString());
            httpClientAndroidLog.debug(sb.toString());
            for (Header header : request.getAllHeaders()) {
                HttpClientAndroidLog httpClientAndroidLog2 = this.headerlog;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(getId());
                sb2.append(str);
                sb2.append(header.toString());
                httpClientAndroidLog2.debug(sb2.toString());
            }
        }
    }
}
