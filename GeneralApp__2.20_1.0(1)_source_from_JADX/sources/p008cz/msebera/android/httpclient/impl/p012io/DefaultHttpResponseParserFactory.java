package p008cz.msebera.android.httpclient.impl.p012io;

import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpResponseFactory;
import p008cz.msebera.android.httpclient.config.MessageConstraints;
import p008cz.msebera.android.httpclient.impl.DefaultHttpResponseFactory;
import p008cz.msebera.android.httpclient.message.BasicLineParser;
import p008cz.msebera.android.httpclient.message.LineParser;
import p008cz.msebera.android.httpclient.p013io.HttpMessageParser;
import p008cz.msebera.android.httpclient.p013io.HttpMessageParserFactory;
import p008cz.msebera.android.httpclient.p013io.SessionInputBuffer;

/* renamed from: cz.msebera.android.httpclient.impl.io.DefaultHttpResponseParserFactory */
public class DefaultHttpResponseParserFactory implements HttpMessageParserFactory<HttpResponse> {
    public static final DefaultHttpResponseParserFactory INSTANCE = new DefaultHttpResponseParserFactory();
    private final LineParser lineParser;
    private final HttpResponseFactory responseFactory;

    public DefaultHttpResponseParserFactory(LineParser lineParser2, HttpResponseFactory responseFactory2) {
        this.lineParser = lineParser2 != null ? lineParser2 : BasicLineParser.INSTANCE;
        this.responseFactory = responseFactory2 != null ? responseFactory2 : DefaultHttpResponseFactory.INSTANCE;
    }

    public DefaultHttpResponseParserFactory() {
        this(null, null);
    }

    public HttpMessageParser<HttpResponse> create(SessionInputBuffer buffer, MessageConstraints constraints) {
        return new DefaultHttpResponseParser(buffer, this.lineParser, this.responseFactory, constraints);
    }
}
