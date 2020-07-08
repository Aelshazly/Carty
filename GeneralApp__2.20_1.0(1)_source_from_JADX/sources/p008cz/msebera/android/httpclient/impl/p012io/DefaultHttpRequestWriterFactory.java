package p008cz.msebera.android.httpclient.impl.p012io;

import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.message.BasicLineFormatter;
import p008cz.msebera.android.httpclient.message.LineFormatter;
import p008cz.msebera.android.httpclient.p013io.HttpMessageWriter;
import p008cz.msebera.android.httpclient.p013io.HttpMessageWriterFactory;
import p008cz.msebera.android.httpclient.p013io.SessionOutputBuffer;

/* renamed from: cz.msebera.android.httpclient.impl.io.DefaultHttpRequestWriterFactory */
public class DefaultHttpRequestWriterFactory implements HttpMessageWriterFactory<HttpRequest> {
    public static final DefaultHttpRequestWriterFactory INSTANCE = new DefaultHttpRequestWriterFactory();
    private final LineFormatter lineFormatter;

    public DefaultHttpRequestWriterFactory(LineFormatter lineFormatter2) {
        this.lineFormatter = lineFormatter2 != null ? lineFormatter2 : BasicLineFormatter.INSTANCE;
    }

    public DefaultHttpRequestWriterFactory() {
        this(null);
    }

    public HttpMessageWriter<HttpRequest> create(SessionOutputBuffer buffer) {
        return new DefaultHttpRequestWriter(buffer, this.lineFormatter);
    }
}
