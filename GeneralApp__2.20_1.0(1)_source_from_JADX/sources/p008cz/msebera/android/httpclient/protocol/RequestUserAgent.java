package p008cz.msebera.android.httpclient.protocol;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpRequestInterceptor;
import p008cz.msebera.android.httpclient.params.CoreProtocolPNames;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.protocol.RequestUserAgent */
public class RequestUserAgent implements HttpRequestInterceptor {
    private final String userAgent;

    public RequestUserAgent(String userAgent2) {
        this.userAgent = userAgent2;
    }

    public RequestUserAgent() {
        this(null);
    }

    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        String str = "User-Agent";
        if (!request.containsHeader(str)) {
            String s = null;
            HttpParams params = request.getParams();
            if (params != null) {
                s = (String) params.getParameter(CoreProtocolPNames.USER_AGENT);
            }
            if (s == null) {
                s = this.userAgent;
            }
            if (s != null) {
                request.addHeader(str, s);
            }
        }
    }
}
