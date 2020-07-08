package p008cz.msebera.android.httpclient.impl.conn;

import java.net.InetAddress;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.conn.params.ConnRouteParams;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner;
import p008cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.DefaultHttpRoutePlanner */
public class DefaultHttpRoutePlanner implements HttpRoutePlanner {
    protected final SchemeRegistry schemeRegistry;

    public DefaultHttpRoutePlanner(SchemeRegistry schreg) {
        Args.notNull(schreg, "Scheme registry");
        this.schemeRegistry = schreg;
    }

    public HttpRoute determineRoute(HttpHost target, HttpRequest request, HttpContext context) throws HttpException {
        HttpRoute route;
        Args.notNull(request, "HTTP request");
        HttpRoute route2 = ConnRouteParams.getForcedRoute(request.getParams());
        if (route2 != null) {
            return route2;
        }
        Asserts.notNull(target, "Target host");
        InetAddress local = ConnRouteParams.getLocalAddress(request.getParams());
        HttpHost proxy = ConnRouteParams.getDefaultProxy(request.getParams());
        try {
            boolean secure = this.schemeRegistry.getScheme(target.getSchemeName()).isLayered();
            if (proxy == null) {
                route = new HttpRoute(target, local, secure);
            } else {
                route = new HttpRoute(target, local, proxy, secure);
            }
            return route;
        } catch (IllegalStateException ex) {
            throw new HttpException(ex.getMessage());
        }
    }
}
