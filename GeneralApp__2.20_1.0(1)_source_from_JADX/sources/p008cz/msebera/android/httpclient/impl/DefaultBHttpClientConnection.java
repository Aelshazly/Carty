package p008cz.msebera.android.httpclient.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import p008cz.msebera.android.httpclient.HttpClientConnection;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.config.MessageConstraints;
import p008cz.msebera.android.httpclient.entity.ContentLengthStrategy;
import p008cz.msebera.android.httpclient.impl.p012io.DefaultHttpRequestWriterFactory;
import p008cz.msebera.android.httpclient.impl.p012io.DefaultHttpResponseParserFactory;
import p008cz.msebera.android.httpclient.p013io.HttpMessageParser;
import p008cz.msebera.android.httpclient.p013io.HttpMessageParserFactory;
import p008cz.msebera.android.httpclient.p013io.HttpMessageWriter;
import p008cz.msebera.android.httpclient.p013io.HttpMessageWriterFactory;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.DefaultBHttpClientConnection */
public class DefaultBHttpClientConnection extends BHttpConnectionBase implements HttpClientConnection {
    private final HttpMessageWriter<HttpRequest> requestWriter;
    private final HttpMessageParser<HttpResponse> responseParser;

    public DefaultBHttpClientConnection(int buffersize, int fragmentSizeHint, CharsetDecoder chardecoder, CharsetEncoder charencoder, MessageConstraints constraints, ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy, HttpMessageWriterFactory<HttpRequest> requestWriterFactory, HttpMessageParserFactory<HttpResponse> responseParserFactory) {
        super(buffersize, fragmentSizeHint, chardecoder, charencoder, constraints, incomingContentStrategy, outgoingContentStrategy);
        this.requestWriter = (requestWriterFactory != null ? requestWriterFactory : DefaultHttpRequestWriterFactory.INSTANCE).create(getSessionOutputBuffer());
        this.responseParser = (responseParserFactory != null ? responseParserFactory : DefaultHttpResponseParserFactory.INSTANCE).create(getSessionInputBuffer(), constraints);
    }

    public DefaultBHttpClientConnection(int buffersize, CharsetDecoder chardecoder, CharsetEncoder charencoder, MessageConstraints constraints) {
        this(buffersize, buffersize, chardecoder, charencoder, constraints, null, null, null, null);
    }

    public DefaultBHttpClientConnection(int buffersize) {
        this(buffersize, buffersize, null, null, null, null, null, null, null);
    }

    /* access modifiers changed from: protected */
    public void onResponseReceived(HttpResponse response) {
    }

    /* access modifiers changed from: protected */
    public void onRequestSubmitted(HttpRequest request) {
    }

    public void bind(Socket socket) throws IOException {
        super.bind(socket);
    }

    public boolean isResponseAvailable(int timeout) throws IOException {
        ensureOpen();
        try {
            return awaitInput(timeout);
        } catch (SocketTimeoutException e) {
            return false;
        }
    }

    public void sendRequestHeader(HttpRequest request) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        ensureOpen();
        this.requestWriter.write(request);
        onRequestSubmitted(request);
        incrementRequestCount();
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest request) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        ensureOpen();
        HttpEntity entity = request.getEntity();
        if (entity != null) {
            OutputStream outstream = prepareOutput(request);
            entity.writeTo(outstream);
            outstream.close();
        }
    }

    public HttpResponse receiveResponseHeader() throws HttpException, IOException {
        ensureOpen();
        HttpResponse response = (HttpResponse) this.responseParser.parse();
        onResponseReceived(response);
        if (response.getStatusLine().getStatusCode() >= 200) {
            incrementResponseCount();
        }
        return response;
    }

    public void receiveResponseEntity(HttpResponse response) throws HttpException, IOException {
        Args.notNull(response, "HTTP response");
        ensureOpen();
        response.setEntity(prepareInput(response));
    }

    public void flush() throws IOException {
        ensureOpen();
        doFlush();
    }
}
