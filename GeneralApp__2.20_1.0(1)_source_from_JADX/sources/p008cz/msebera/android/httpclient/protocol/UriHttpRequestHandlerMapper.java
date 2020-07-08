package p008cz.msebera.android.httpclient.protocol;

import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.protocol.UriHttpRequestHandlerMapper */
public class UriHttpRequestHandlerMapper implements HttpRequestHandlerMapper {
    private final UriPatternMatcher<HttpRequestHandler> matcher;

    protected UriHttpRequestHandlerMapper(UriPatternMatcher<HttpRequestHandler> matcher2) {
        this.matcher = (UriPatternMatcher) Args.notNull(matcher2, "Pattern matcher");
    }

    public UriHttpRequestHandlerMapper() {
        this(new UriPatternMatcher());
    }

    public void register(String pattern, HttpRequestHandler handler) {
        Args.notNull(pattern, "Pattern");
        Args.notNull(handler, "Handler");
        this.matcher.register(pattern, handler);
    }

    public void unregister(String pattern) {
        this.matcher.unregister(pattern);
    }

    /* access modifiers changed from: protected */
    public String getRequestPath(HttpRequest request) {
        String uriPath = request.getRequestLine().getUri();
        int index = uriPath.indexOf("?");
        if (index != -1) {
            return uriPath.substring(0, index);
        }
        int index2 = uriPath.indexOf("#");
        if (index2 != -1) {
            return uriPath.substring(0, index2);
        }
        return uriPath;
    }

    public HttpRequestHandler lookup(HttpRequest request) {
        Args.notNull(request, "HTTP request");
        return (HttpRequestHandler) this.matcher.lookup(getRequestPath(request));
    }
}
