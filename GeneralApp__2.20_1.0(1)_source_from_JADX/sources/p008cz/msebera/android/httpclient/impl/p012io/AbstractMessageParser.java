package p008cz.msebera.android.httpclient.impl.p012io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpMessage;
import p008cz.msebera.android.httpclient.MessageConstraintException;
import p008cz.msebera.android.httpclient.ParseException;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.config.MessageConstraints;
import p008cz.msebera.android.httpclient.message.BasicLineParser;
import p008cz.msebera.android.httpclient.message.LineParser;
import p008cz.msebera.android.httpclient.p013io.HttpMessageParser;
import p008cz.msebera.android.httpclient.p013io.SessionInputBuffer;
import p008cz.msebera.android.httpclient.params.HttpParamConfig;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.CharArrayBuffer;

/* renamed from: cz.msebera.android.httpclient.impl.io.AbstractMessageParser */
public abstract class AbstractMessageParser<T extends HttpMessage> implements HttpMessageParser<T> {
    private static final int HEADERS = 1;
    private static final int HEAD_LINE = 0;
    private final List<CharArrayBuffer> headerLines;
    protected final LineParser lineParser;
    private T message;
    private final MessageConstraints messageConstraints;
    private final SessionInputBuffer sessionBuffer;
    private int state;

    /* access modifiers changed from: protected */
    public abstract T parseHead(SessionInputBuffer sessionInputBuffer) throws IOException, HttpException, ParseException;

    @Deprecated
    public AbstractMessageParser(SessionInputBuffer buffer, LineParser parser, HttpParams params) {
        Args.notNull(buffer, "Session input buffer");
        Args.notNull(params, "HTTP parameters");
        this.sessionBuffer = buffer;
        this.messageConstraints = HttpParamConfig.getMessageConstraints(params);
        this.lineParser = parser != null ? parser : BasicLineParser.INSTANCE;
        this.headerLines = new ArrayList();
        this.state = 0;
    }

    public AbstractMessageParser(SessionInputBuffer buffer, LineParser lineParser2, MessageConstraints constraints) {
        this.sessionBuffer = (SessionInputBuffer) Args.notNull(buffer, "Session input buffer");
        this.lineParser = lineParser2 != null ? lineParser2 : BasicLineParser.INSTANCE;
        this.messageConstraints = constraints != null ? constraints : MessageConstraints.DEFAULT;
        this.headerLines = new ArrayList();
        this.state = 0;
    }

    public static Header[] parseHeaders(SessionInputBuffer inbuffer, int maxHeaderCount, int maxLineLen, LineParser parser) throws HttpException, IOException {
        return parseHeaders(inbuffer, maxHeaderCount, maxLineLen, parser != null ? parser : BasicLineParser.INSTANCE, new ArrayList<>());
    }

    public static Header[] parseHeaders(SessionInputBuffer inbuffer, int maxHeaderCount, int maxLineLen, LineParser parser, List<CharArrayBuffer> headerLines2) throws HttpException, IOException {
        Args.notNull(inbuffer, "Session input buffer");
        Args.notNull(parser, "Line parser");
        Args.notNull(headerLines2, "Header line list");
        CharArrayBuffer current = null;
        CharArrayBuffer previous = null;
        while (true) {
            if (current == null) {
                current = new CharArrayBuffer(64);
            } else {
                current.clear();
            }
            if (inbuffer.readLine(current) == -1 || current.length() < 1) {
                Header[] headers = new Header[headerLines2.size()];
                int i = 0;
            } else {
                if ((current.charAt(0) == ' ' || current.charAt(0) == 9) && previous != null) {
                    int i2 = 0;
                    while (i2 < current.length()) {
                        char ch = current.charAt(i2);
                        if (ch != ' ' && ch != 9) {
                            break;
                        }
                        i2++;
                    }
                    if (maxLineLen <= 0 || ((previous.length() + 1) + current.length()) - i2 <= maxLineLen) {
                        previous.append(' ');
                        previous.append(current, i2, current.length() - i2);
                    } else {
                        throw new MessageConstraintException("Maximum line length limit exceeded");
                    }
                } else {
                    headerLines2.add(current);
                    previous = current;
                    current = null;
                }
                if (maxHeaderCount > 0 && headerLines2.size() >= maxHeaderCount) {
                    throw new MessageConstraintException("Maximum header count exceeded");
                }
            }
        }
        Header[] headers2 = new Header[headerLines2.size()];
        int i3 = 0;
        while (i3 < headerLines2.size()) {
            try {
                headers2[i3] = parser.parseHeader((CharArrayBuffer) headerLines2.get(i3));
                i3++;
            } catch (ParseException ex) {
                throw new ProtocolException(ex.getMessage());
            }
        }
        return headers2;
    }

    public T parse() throws IOException, HttpException {
        int st = this.state;
        if (st == 0) {
            try {
                this.message = parseHead(this.sessionBuffer);
                this.state = 1;
            } catch (ParseException px) {
                throw new ProtocolException(px.getMessage(), px);
            }
        } else if (st != 1) {
            throw new IllegalStateException("Inconsistent parser state");
        }
        this.message.setHeaders(parseHeaders(this.sessionBuffer, this.messageConstraints.getMaxHeaderCount(), this.messageConstraints.getMaxLineLength(), this.lineParser, this.headerLines));
        T result = this.message;
        this.message = null;
        this.headerLines.clear();
        this.state = 0;
        return result;
    }
}
