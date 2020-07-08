package p008cz.msebera.android.httpclient.impl.p012io;

import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.message.BasicLineFormatter;
import p008cz.msebera.android.httpclient.message.LineFormatter;
import p008cz.msebera.android.httpclient.p013io.HttpMessageWriter;
import p008cz.msebera.android.httpclient.p013io.HttpMessageWriterFactory;
import p008cz.msebera.android.httpclient.p013io.SessionOutputBuffer;

/* renamed from: cz.msebera.android.httpclient.impl.io.DefaultHttpResponseWriterFactory */
public class DefaultHttpResponseWriterFactory implements HttpMessageWriterFactory<HttpResponse> {
    public static final DefaultHttpResponseWriterFactory INSTANCE = new DefaultHttpResponseWriterFactory();
    private final LineFormatter lineFormatter;

    public DefaultHttpResponseWriterFactory(LineFormatter lineFormatter2) {
        this.lineFormatter = lineFormatter2 != null ? lineFormatter2 : BasicLineFormatter.INSTANCE;
    }

    public DefaultHttpResponseWriterFactory() {
        this(null);
    }

    public HttpMessageWriter<HttpResponse> create(SessionOutputBuffer buffer) {
        return new DefaultHttpResponseWriter(buffer, this.lineFormatter);
    }
}
