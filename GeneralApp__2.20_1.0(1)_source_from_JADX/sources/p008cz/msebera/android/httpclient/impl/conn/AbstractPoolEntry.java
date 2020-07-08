package p008cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;
import java.io.InterruptedIOException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import p008cz.msebera.android.httpclient.conn.OperatedClientConnection;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.conn.routing.RouteTracker;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.AbstractPoolEntry */
public abstract class AbstractPoolEntry {
    protected final ClientConnectionOperator connOperator;
    protected final OperatedClientConnection connection;
    protected volatile HttpRoute route;
    protected volatile Object state;
    protected volatile RouteTracker tracker = null;

    protected AbstractPoolEntry(ClientConnectionOperator connOperator2, HttpRoute route2) {
        Args.notNull(connOperator2, "Connection operator");
        this.connOperator = connOperator2;
        this.connection = connOperator2.createConnection();
        this.route = route2;
    }

    public Object getState() {
        return this.state;
    }

    public void setState(Object state2) {
        this.state = state2;
    }

    public void open(HttpRoute route2, HttpContext context, HttpParams params) throws IOException {
        HttpHost httpHost;
        Args.notNull(route2, "Route");
        Args.notNull(params, "HTTP parameters");
        if (this.tracker != null) {
            Asserts.check(!this.tracker.isConnected(), "Connection already open");
        }
        this.tracker = new RouteTracker(route2);
        HttpHost proxy = route2.getProxyHost();
        ClientConnectionOperator clientConnectionOperator = this.connOperator;
        OperatedClientConnection operatedClientConnection = this.connection;
        if (proxy != null) {
            httpHost = proxy;
        } else {
            httpHost = route2.getTargetHost();
        }
        clientConnectionOperator.openConnection(operatedClientConnection, httpHost, route2.getLocalAddress(), context, params);
        RouteTracker localTracker = this.tracker;
        if (localTracker == null) {
            throw new InterruptedIOException("Request aborted");
        } else if (proxy == null) {
            localTracker.connectTarget(this.connection.isSecure());
        } else {
            localTracker.connectProxy(proxy, this.connection.isSecure());
        }
    }

    public void tunnelTarget(boolean secure, HttpParams params) throws IOException {
        Args.notNull(params, "HTTP parameters");
        Asserts.notNull(this.tracker, "Route tracker");
        Asserts.check(this.tracker.isConnected(), "Connection not open");
        Asserts.check(!this.tracker.isTunnelled(), "Connection is already tunnelled");
        this.connection.update(null, this.tracker.getTargetHost(), secure, params);
        this.tracker.tunnelTarget(secure);
    }

    public void tunnelProxy(HttpHost next, boolean secure, HttpParams params) throws IOException {
        Args.notNull(next, "Next proxy");
        Args.notNull(params, "Parameters");
        Asserts.notNull(this.tracker, "Route tracker");
        Asserts.check(this.tracker.isConnected(), "Connection not open");
        this.connection.update(null, next, secure, params);
        this.tracker.tunnelProxy(next, secure);
    }

    public void layerProtocol(HttpContext context, HttpParams params) throws IOException {
        Args.notNull(params, "HTTP parameters");
        Asserts.notNull(this.tracker, "Route tracker");
        Asserts.check(this.tracker.isConnected(), "Connection not open");
        Asserts.check(this.tracker.isTunnelled(), "Protocol layering without a tunnel not supported");
        Asserts.check(!this.tracker.isLayered(), "Multiple protocol layering not supported");
        this.connOperator.updateSecureConnection(this.connection, this.tracker.getTargetHost(), context, params);
        this.tracker.layerProtocol(this.connection.isSecure());
    }

    /* access modifiers changed from: protected */
    public void shutdownEntry() {
        this.tracker = null;
        this.state = null;
    }
}
