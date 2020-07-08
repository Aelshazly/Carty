package p008cz.msebera.android.httpclient.protocol;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpRequestInterceptor;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.protocol.RequestConnControl */
public class RequestConnControl implements HttpRequestInterceptor {
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        if (!request.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
            String str = "Connection";
            if (!request.containsHeader(str)) {
                request.addHeader(str, HTTP.CONN_KEEP_ALIVE);
            }
        }
    }
}
