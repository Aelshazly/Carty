package p008cz.msebera.android.httpclient.message;

import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.RequestLine;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.message.BasicHttpRequest */
public class BasicHttpRequest extends AbstractHttpMessage implements HttpRequest {
    private final String method;
    private RequestLine requestline;
    private final String uri;

    public BasicHttpRequest(String method2, String uri2) {
        this.method = (String) Args.notNull(method2, "Method name");
        this.uri = (String) Args.notNull(uri2, "Request URI");
        this.requestline = null;
    }

    public BasicHttpRequest(String method2, String uri2, ProtocolVersion ver) {
        this(new BasicRequestLine(method2, uri2, ver));
    }

    public BasicHttpRequest(RequestLine requestline2) {
        this.requestline = (RequestLine) Args.notNull(requestline2, "Request line");
        this.method = requestline2.getMethod();
        this.uri = requestline2.getUri();
    }

    public ProtocolVersion getProtocolVersion() {
        return getRequestLine().getProtocolVersion();
    }

    public RequestLine getRequestLine() {
        if (this.requestline == null) {
            this.requestline = new BasicRequestLine(this.method, this.uri, HttpVersion.HTTP_1_1);
        }
        return this.requestline;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.method);
        sb.append(' ');
        sb.append(this.uri);
        sb.append(' ');
        sb.append(this.headergroup);
        return sb.toString();
    }
}
