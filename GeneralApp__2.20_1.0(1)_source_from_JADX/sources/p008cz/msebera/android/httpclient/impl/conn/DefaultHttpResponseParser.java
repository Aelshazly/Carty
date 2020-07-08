package p008cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpResponseFactory;
import p008cz.msebera.android.httpclient.NoHttpResponseException;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.config.MessageConstraints;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.impl.DefaultHttpResponseFactory;
import p008cz.msebera.android.httpclient.impl.p012io.AbstractMessageParser;
import p008cz.msebera.android.httpclient.message.LineParser;
import p008cz.msebera.android.httpclient.message.ParserCursor;
import p008cz.msebera.android.httpclient.p013io.SessionInputBuffer;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.CharArrayBuffer;

/* renamed from: cz.msebera.android.httpclient.impl.conn.DefaultHttpResponseParser */
public class DefaultHttpResponseParser extends AbstractMessageParser<HttpResponse> {
    private final CharArrayBuffer lineBuf;
    public HttpClientAndroidLog log;
    private final HttpResponseFactory responseFactory;

    @Deprecated
    public DefaultHttpResponseParser(SessionInputBuffer buffer, LineParser parser, HttpResponseFactory responseFactory2, HttpParams params) {
        super(buffer, parser, params);
        this.log = new HttpClientAndroidLog(getClass());
        Args.notNull(responseFactory2, "Response factory");
        this.responseFactory = responseFactory2;
        this.lineBuf = new CharArrayBuffer(128);
    }

    public DefaultHttpResponseParser(SessionInputBuffer buffer, LineParser lineParser, HttpResponseFactory responseFactory2, MessageConstraints constraints) {
        super(buffer, lineParser, constraints);
        this.log = new HttpClientAndroidLog(getClass());
        this.responseFactory = responseFactory2 != null ? responseFactory2 : DefaultHttpResponseFactory.INSTANCE;
        this.lineBuf = new CharArrayBuffer(128);
    }

    public DefaultHttpResponseParser(SessionInputBuffer buffer, MessageConstraints constraints) {
        this(buffer, (LineParser) null, (HttpResponseFactory) null, constraints);
    }

    public DefaultHttpResponseParser(SessionInputBuffer buffer) {
        this(buffer, (LineParser) null, (HttpResponseFactory) null, MessageConstraints.DEFAULT);
    }

    /* access modifiers changed from: protected */
    public HttpResponse parseHead(SessionInputBuffer sessionBuffer) throws IOException, HttpException {
        int count = 0;
        while (true) {
            this.lineBuf.clear();
            int i = sessionBuffer.readLine(this.lineBuf);
            if (i == -1 && count == 0) {
                throw new NoHttpResponseException("The target server failed to respond");
            }
            ParserCursor cursor = new ParserCursor(0, this.lineBuf.length());
            if (this.lineParser.hasProtocolVersion(this.lineBuf, cursor)) {
                return this.responseFactory.newHttpResponse(this.lineParser.parseStatusLine(this.lineBuf, cursor), null);
            } else if (i != -1 && !reject(this.lineBuf, count)) {
                if (this.log.isDebugEnabled()) {
                    HttpClientAndroidLog httpClientAndroidLog = this.log;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Garbage in response: ");
                    sb.append(this.lineBuf.toString());
                    httpClientAndroidLog.debug(sb.toString());
                }
                count++;
            }
        }
        throw new ProtocolException("The server failed to respond with a valid HTTP response");
    }

    /* access modifiers changed from: protected */
    public boolean reject(CharArrayBuffer line, int count) {
        return false;
    }
}
