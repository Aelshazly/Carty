package p008cz.msebera.android.httpclient.protocol;

import java.io.IOException;
import java.net.InetAddress;
import p008cz.msebera.android.httpclient.HttpConnection;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpInetConnection;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpRequestInterceptor;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.protocol.RequestTargetHost */
public class RequestTargetHost implements HttpRequestInterceptor {
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        HttpCoreContext corecontext = HttpCoreContext.adapt(context);
        ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
        if (!request.getRequestLine().getMethod().equalsIgnoreCase("CONNECT") || !ver.lessEquals(HttpVersion.HTTP_1_0)) {
            String str = "Host";
            if (!request.containsHeader(str)) {
                HttpHost targethost = corecontext.getTargetHost();
                if (targethost == null) {
                    HttpConnection conn = corecontext.getConnection();
                    if (conn instanceof HttpInetConnection) {
                        InetAddress address = ((HttpInetConnection) conn).getRemoteAddress();
                        int port = ((HttpInetConnection) conn).getRemotePort();
                        if (address != null) {
                            targethost = new HttpHost(address.getHostName(), port);
                        }
                    }
                    if (targethost == null) {
                        if (!ver.lessEquals(HttpVersion.HTTP_1_0)) {
                            throw new ProtocolException("Target host missing");
                        }
                        return;
                    }
                }
                request.addHeader(str, targethost.toHostString());
            }
        }
    }
}
