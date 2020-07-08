package p008cz.msebera.android.httpclient.impl;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpConnectionMetrics;
import p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpRequestFactory;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpServerConnection;
import p008cz.msebera.android.httpclient.impl.entity.DisallowIdentityContentLengthStrategy;
import p008cz.msebera.android.httpclient.impl.entity.EntityDeserializer;
import p008cz.msebera.android.httpclient.impl.entity.EntitySerializer;
import p008cz.msebera.android.httpclient.impl.entity.LaxContentLengthStrategy;
import p008cz.msebera.android.httpclient.impl.entity.StrictContentLengthStrategy;
import p008cz.msebera.android.httpclient.impl.p012io.DefaultHttpRequestParser;
import p008cz.msebera.android.httpclient.impl.p012io.HttpResponseWriter;
import p008cz.msebera.android.httpclient.message.LineParser;
import p008cz.msebera.android.httpclient.p013io.EofSensor;
import p008cz.msebera.android.httpclient.p013io.HttpMessageParser;
import p008cz.msebera.android.httpclient.p013io.HttpMessageWriter;
import p008cz.msebera.android.httpclient.p013io.HttpTransportMetrics;
import p008cz.msebera.android.httpclient.p013io.SessionInputBuffer;
import p008cz.msebera.android.httpclient.p013io.SessionOutputBuffer;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.AbstractHttpServerConnection */
public abstract class AbstractHttpServerConnection implements HttpServerConnection {
    private final EntityDeserializer entitydeserializer = createEntityDeserializer();
    private final EntitySerializer entityserializer = createEntitySerializer();
    private EofSensor eofSensor = null;
    private SessionInputBuffer inbuffer = null;
    private HttpConnectionMetricsImpl metrics = null;
    private SessionOutputBuffer outbuffer = null;
    private HttpMessageParser<HttpRequest> requestParser = null;
    private HttpMessageWriter<HttpResponse> responseWriter = null;

    /* access modifiers changed from: protected */
    public abstract void assertOpen() throws IllegalStateException;

    /* access modifiers changed from: protected */
    public EntityDeserializer createEntityDeserializer() {
        return new EntityDeserializer(new DisallowIdentityContentLengthStrategy(new LaxContentLengthStrategy(0)));
    }

    /* access modifiers changed from: protected */
    public EntitySerializer createEntitySerializer() {
        return new EntitySerializer(new StrictContentLengthStrategy());
    }

    /* access modifiers changed from: protected */
    public HttpRequestFactory createHttpRequestFactory() {
        return DefaultHttpRequestFactory.INSTANCE;
    }

    /* access modifiers changed from: protected */
    public HttpMessageParser<HttpRequest> createRequestParser(SessionInputBuffer buffer, HttpRequestFactory requestFactory, HttpParams params) {
        return new DefaultHttpRequestParser(buffer, (LineParser) null, requestFactory, params);
    }

    /* access modifiers changed from: protected */
    public HttpMessageWriter<HttpResponse> createResponseWriter(SessionOutputBuffer buffer, HttpParams params) {
        return new HttpResponseWriter(buffer, null, params);
    }

    /* access modifiers changed from: protected */
    public HttpConnectionMetricsImpl createConnectionMetrics(HttpTransportMetrics inTransportMetric, HttpTransportMetrics outTransportMetric) {
        return new HttpConnectionMetricsImpl(inTransportMetric, outTransportMetric);
    }

    /* access modifiers changed from: protected */
    public void init(SessionInputBuffer inbuffer2, SessionOutputBuffer outbuffer2, HttpParams params) {
        this.inbuffer = (SessionInputBuffer) Args.notNull(inbuffer2, "Input session buffer");
        this.outbuffer = (SessionOutputBuffer) Args.notNull(outbuffer2, "Output session buffer");
        if (inbuffer2 instanceof EofSensor) {
            this.eofSensor = (EofSensor) inbuffer2;
        }
        this.requestParser = createRequestParser(inbuffer2, createHttpRequestFactory(), params);
        this.responseWriter = createResponseWriter(outbuffer2, params);
        this.metrics = createConnectionMetrics(inbuffer2.getMetrics(), outbuffer2.getMetrics());
    }

    public HttpRequest receiveRequestHeader() throws HttpException, IOException {
        assertOpen();
        HttpRequest request = (HttpRequest) this.requestParser.parse();
        this.metrics.incrementRequestCount();
        return request;
    }

    public void receiveRequestEntity(HttpEntityEnclosingRequest request) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        assertOpen();
        request.setEntity(this.entitydeserializer.deserialize(this.inbuffer, request));
    }

    /* access modifiers changed from: protected */
    public void doFlush() throws IOException {
        this.outbuffer.flush();
    }

    public void flush() throws IOException {
        assertOpen();
        doFlush();
    }

    public void sendResponseHeader(HttpResponse response) throws HttpException, IOException {
        Args.notNull(response, "HTTP response");
        assertOpen();
        this.responseWriter.write(response);
        if (response.getStatusLine().getStatusCode() >= 200) {
            this.metrics.incrementResponseCount();
        }
    }

    public void sendResponseEntity(HttpResponse response) throws HttpException, IOException {
        if (response.getEntity() != null) {
            this.entityserializer.serialize(this.outbuffer, response, response.getEntity());
        }
    }

    /* access modifiers changed from: protected */
    public boolean isEof() {
        EofSensor eofSensor2 = this.eofSensor;
        return eofSensor2 != null && eofSensor2.isEof();
    }

    public boolean isStale() {
        if (!isOpen() || isEof()) {
            return true;
        }
        try {
            this.inbuffer.isDataAvailable(1);
            return isEof();
        } catch (IOException e) {
            return true;
        }
    }

    public HttpConnectionMetrics getMetrics() {
        return this.metrics;
    }
}
