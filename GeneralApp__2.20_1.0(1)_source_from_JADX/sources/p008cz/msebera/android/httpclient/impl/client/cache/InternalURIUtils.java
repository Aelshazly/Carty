package p008cz.msebera.android.httpclient.impl.client.cache;

import java.net.URI;
import java.net.URISyntaxException;
import p008cz.msebera.android.httpclient.client.utils.URIUtils;
import p008cz.msebera.android.httpclient.conn.routing.RouteInfo;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.InternalURIUtils */
class InternalURIUtils {
    public static URI rewriteURIForRoute(URI uri, RouteInfo route) throws URISyntaxException {
        if (uri == null) {
            return null;
        }
        if (route.getProxyHost() == null || route.isTunnelled()) {
            if (uri.isAbsolute()) {
                return URIUtils.rewriteURI(uri, null, true);
            }
            return URIUtils.rewriteURI(uri);
        } else if (!uri.isAbsolute()) {
            return URIUtils.rewriteURI(uri, route.getTargetHost(), true);
        } else {
            return URIUtils.rewriteURI(uri);
        }
    }

    private InternalURIUtils() {
    }
}
