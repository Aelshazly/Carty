package p008cz.msebera.android.httpclient.conn.params;

import java.net.InetAddress;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.params.HttpAbstractParamBean;
import p008cz.msebera.android.httpclient.params.HttpParams;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.conn.params.ConnRouteParamBean */
public class ConnRouteParamBean extends HttpAbstractParamBean {
    public ConnRouteParamBean(HttpParams params) {
        super(params);
    }

    public void setDefaultProxy(HttpHost defaultProxy) {
        this.params.setParameter(ConnRoutePNames.DEFAULT_PROXY, defaultProxy);
    }

    public void setLocalAddress(InetAddress address) {
        this.params.setParameter(ConnRoutePNames.LOCAL_ADDRESS, address);
    }

    public void setForcedRoute(HttpRoute route) {
        this.params.setParameter(ConnRoutePNames.FORCED_ROUTE, route);
    }
}
