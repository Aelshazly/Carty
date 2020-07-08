package p008cz.msebera.android.httpclient.impl.client;

import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.client.RoutedRequest */
public class RoutedRequest {
    protected final RequestWrapper request;
    protected final HttpRoute route;

    public RoutedRequest(RequestWrapper req, HttpRoute route2) {
        this.request = req;
        this.route = route2;
    }

    public final RequestWrapper getRequest() {
        return this.request;
    }

    public final HttpRoute getRoute() {
        return this.route;
    }
}
