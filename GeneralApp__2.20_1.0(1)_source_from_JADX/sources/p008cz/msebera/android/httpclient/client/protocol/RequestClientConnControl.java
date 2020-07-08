package p008cz.msebera.android.httpclient.client.protocol;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpRequestInterceptor;
import p008cz.msebera.android.httpclient.conn.routing.RouteInfo;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.protocol.HTTP;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.client.protocol.RequestClientConnControl */
public class RequestClientConnControl implements HttpRequestInterceptor {
    private static final String PROXY_CONN_DIRECTIVE = "Proxy-Connection";
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        boolean equalsIgnoreCase = request.getRequestLine().getMethod().equalsIgnoreCase("CONNECT");
        String str = HTTP.CONN_KEEP_ALIVE;
        String str2 = PROXY_CONN_DIRECTIVE;
        if (equalsIgnoreCase) {
            request.setHeader(str2, str);
            return;
        }
        RouteInfo route = HttpClientContext.adapt(context).getHttpRoute();
        if (route == null) {
            this.log.debug("Connection route not set in the context");
            return;
        }
        if (route.getHopCount() == 1 || route.isTunnelled()) {
            String str3 = "Connection";
            if (!request.containsHeader(str3)) {
                request.addHeader(str3, str);
            }
        }
        if (route.getHopCount() == 2 && !route.isTunnelled() && !request.containsHeader(str2)) {
            request.addHeader(str2, str);
        }
    }
}
