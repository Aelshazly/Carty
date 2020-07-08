package p008cz.msebera.android.httpclient.conn.params;

import p008cz.msebera.android.httpclient.params.HttpAbstractParamBean;
import p008cz.msebera.android.httpclient.params.HttpParams;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.conn.params.ConnManagerParamBean */
public class ConnManagerParamBean extends HttpAbstractParamBean {
    public ConnManagerParamBean(HttpParams params) {
        super(params);
    }

    public void setTimeout(long timeout) {
        this.params.setLongParameter("http.conn-manager.timeout", timeout);
    }

    public void setMaxTotalConnections(int maxConnections) {
        this.params.setIntParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, maxConnections);
    }

    public void setConnectionsPerRoute(ConnPerRouteBean connPerRoute) {
        this.params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, connPerRoute);
    }
}
