package p008cz.msebera.android.httpclient.conn.routing;

import java.net.InetAddress;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.conn.routing.RouteInfo.LayerType;
import p008cz.msebera.android.httpclient.conn.routing.RouteInfo.TunnelType;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;
import p008cz.msebera.android.httpclient.util.LangUtils;

/* renamed from: cz.msebera.android.httpclient.conn.routing.RouteTracker */
public final class RouteTracker implements RouteInfo, Cloneable {
    private boolean connected;
    private LayerType layered;
    private final InetAddress localAddress;
    private HttpHost[] proxyChain;
    private boolean secure;
    private final HttpHost targetHost;
    private TunnelType tunnelled;

    public RouteTracker(HttpHost target, InetAddress local) {
        Args.notNull(target, "Target host");
        this.targetHost = target;
        this.localAddress = local;
        this.tunnelled = TunnelType.PLAIN;
        this.layered = LayerType.PLAIN;
    }

    public void reset() {
        this.connected = false;
        this.proxyChain = null;
        this.tunnelled = TunnelType.PLAIN;
        this.layered = LayerType.PLAIN;
        this.secure = false;
    }

    public RouteTracker(HttpRoute route) {
        this(route.getTargetHost(), route.getLocalAddress());
    }

    public final void connectTarget(boolean secure2) {
        Asserts.check(!this.connected, "Already connected");
        this.connected = true;
        this.secure = secure2;
    }

    public final void connectProxy(HttpHost proxy, boolean secure2) {
        Args.notNull(proxy, "Proxy host");
        Asserts.check(!this.connected, "Already connected");
        this.connected = true;
        this.proxyChain = new HttpHost[]{proxy};
        this.secure = secure2;
    }

    public final void tunnelTarget(boolean secure2) {
        Asserts.check(this.connected, "No tunnel unless connected");
        Asserts.notNull(this.proxyChain, "No tunnel without proxy");
        this.tunnelled = TunnelType.TUNNELLED;
        this.secure = secure2;
    }

    public final void tunnelProxy(HttpHost proxy, boolean secure2) {
        Args.notNull(proxy, "Proxy host");
        Asserts.check(this.connected, "No tunnel unless connected");
        Asserts.notNull(this.proxyChain, "No tunnel without proxy");
        HttpHost[] httpHostArr = this.proxyChain;
        HttpHost[] proxies = new HttpHost[(httpHostArr.length + 1)];
        System.arraycopy(httpHostArr, 0, proxies, 0, httpHostArr.length);
        proxies[proxies.length - 1] = proxy;
        this.proxyChain = proxies;
        this.secure = secure2;
    }

    public final void layerProtocol(boolean secure2) {
        Asserts.check(this.connected, "No layered protocol unless connected");
        this.layered = LayerType.LAYERED;
        this.secure = secure2;
    }

    public final HttpHost getTargetHost() {
        return this.targetHost;
    }

    public final InetAddress getLocalAddress() {
        return this.localAddress;
    }

    public final int getHopCount() {
        if (!this.connected) {
            return 0;
        }
        HttpHost[] httpHostArr = this.proxyChain;
        if (httpHostArr == null) {
            return 1;
        }
        return httpHostArr.length + 1;
    }

    public final HttpHost getHopTarget(int hop) {
        Args.notNegative(hop, "Hop index");
        int hopcount = getHopCount();
        Args.check(hop < hopcount, "Hop index exceeds tracked route length");
        if (hop < hopcount - 1) {
            return this.proxyChain[hop];
        }
        return this.targetHost;
    }

    public final HttpHost getProxyHost() {
        HttpHost[] httpHostArr = this.proxyChain;
        if (httpHostArr == null) {
            return null;
        }
        return httpHostArr[0];
    }

    public final boolean isConnected() {
        return this.connected;
    }

    public final TunnelType getTunnelType() {
        return this.tunnelled;
    }

    public final boolean isTunnelled() {
        return this.tunnelled == TunnelType.TUNNELLED;
    }

    public final LayerType getLayerType() {
        return this.layered;
    }

    public final boolean isLayered() {
        return this.layered == LayerType.LAYERED;
    }

    public final boolean isSecure() {
        return this.secure;
    }

    public final HttpRoute toRoute() {
        if (!this.connected) {
            return null;
        }
        HttpRoute httpRoute = new HttpRoute(this.targetHost, this.localAddress, this.proxyChain, this.secure, this.tunnelled, this.layered);
        return httpRoute;
    }

    public final boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof RouteTracker)) {
            return false;
        }
        RouteTracker that = (RouteTracker) o;
        if (!(this.connected == that.connected && this.secure == that.secure && this.tunnelled == that.tunnelled && this.layered == that.layered && LangUtils.equals((Object) this.targetHost, (Object) that.targetHost) && LangUtils.equals((Object) this.localAddress, (Object) that.localAddress) && LangUtils.equals((Object[]) this.proxyChain, (Object[]) that.proxyChain))) {
            z = false;
        }
        return z;
    }

    public final int hashCode() {
        int hash = LangUtils.hashCode(LangUtils.hashCode(17, (Object) this.targetHost), (Object) this.localAddress);
        HttpHost[] httpHostArr = this.proxyChain;
        if (httpHostArr != null) {
            for (HttpHost element : httpHostArr) {
                hash = LangUtils.hashCode(hash, (Object) element);
            }
        }
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(hash, this.connected), this.secure), (Object) this.tunnelled), (Object) this.layered);
    }

    public final String toString() {
        StringBuilder cab = new StringBuilder((getHopCount() * 30) + 50);
        cab.append("RouteTracker[");
        InetAddress inetAddress = this.localAddress;
        String str = "->";
        if (inetAddress != null) {
            cab.append(inetAddress);
            cab.append(str);
        }
        cab.append('{');
        if (this.connected) {
            cab.append('c');
        }
        if (this.tunnelled == TunnelType.TUNNELLED) {
            cab.append('t');
        }
        if (this.layered == LayerType.LAYERED) {
            cab.append('l');
        }
        if (this.secure) {
            cab.append('s');
        }
        cab.append("}->");
        HttpHost[] httpHostArr = this.proxyChain;
        if (httpHostArr != null) {
            for (HttpHost element : httpHostArr) {
                cab.append(element);
                cab.append(str);
            }
        }
        cab.append(this.targetHost);
        cab.append(']');
        return cab.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
