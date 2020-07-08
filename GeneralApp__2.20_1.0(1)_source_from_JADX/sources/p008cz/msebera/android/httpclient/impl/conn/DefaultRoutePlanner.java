package p008cz.msebera.android.httpclient.impl.conn;

import java.net.InetAddress;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.client.config.RequestConfig;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.conn.SchemePortResolver;
import p008cz.msebera.android.httpclient.conn.UnsupportedSchemeException;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.conn.DefaultRoutePlanner */
public class DefaultRoutePlanner implements HttpRoutePlanner {
    private final SchemePortResolver schemePortResolver;

    public DefaultRoutePlanner(SchemePortResolver schemePortResolver2) {
        this.schemePortResolver = schemePortResolver2 != null ? schemePortResolver2 : DefaultSchemePortResolver.INSTANCE;
    }

    public HttpRoute determineRoute(HttpHost host, HttpRequest request, HttpContext context) throws HttpException {
        HttpHost target;
        Args.notNull(request, "Request");
        if (host != null) {
            RequestConfig config = HttpClientContext.adapt(context).getRequestConfig();
            InetAddress local = config.getLocalAddress();
            HttpHost proxy = config.getProxy();
            if (proxy == null) {
                proxy = determineProxy(host, request, context);
            }
            if (host.getPort() <= 0) {
                try {
                    target = new HttpHost(host.getHostName(), this.schemePortResolver.resolve(host), host.getSchemeName());
                } catch (UnsupportedSchemeException ex) {
                    throw new HttpException(ex.getMessage());
                }
            } else {
                target = host;
            }
            boolean secure = target.getSchemeName().equalsIgnoreCase("https");
            if (proxy == null) {
                return new HttpRoute(target, local, secure);
            }
            return new HttpRoute(target, local, proxy, secure);
        }
        throw new ProtocolException("Target host is not specified");
    }

    /* access modifiers changed from: protected */
    public HttpHost determineProxy(HttpHost target, HttpRequest request, HttpContext context) throws HttpException {
        return null;
    }
}
