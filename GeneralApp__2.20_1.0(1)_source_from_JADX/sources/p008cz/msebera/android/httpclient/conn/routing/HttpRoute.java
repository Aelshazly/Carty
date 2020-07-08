package p008cz.msebera.android.httpclient.conn.routing;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.conn.routing.RouteInfo.LayerType;
import p008cz.msebera.android.httpclient.conn.routing.RouteInfo.TunnelType;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.LangUtils;

/* renamed from: cz.msebera.android.httpclient.conn.routing.HttpRoute */
public final class HttpRoute implements RouteInfo, Cloneable {
    private final LayerType layered;
    private final InetAddress localAddress;
    private final List<HttpHost> proxyChain;
    private final boolean secure;
    private final HttpHost targetHost;
    private final TunnelType tunnelled;

    private HttpRoute(HttpHost target, InetAddress local, List<HttpHost> proxies, boolean secure2, TunnelType tunnelled2, LayerType layered2) {
        Args.notNull(target, "Target host");
        this.targetHost = target;
        this.localAddress = local;
        if (proxies == null || proxies.isEmpty()) {
            this.proxyChain = null;
        } else {
            this.proxyChain = new ArrayList(proxies);
        }
        if (tunnelled2 == TunnelType.TUNNELLED) {
            Args.check(this.proxyChain != null, "Proxy required if tunnelled");
        }
        this.secure = secure2;
        this.tunnelled = tunnelled2 != null ? tunnelled2 : TunnelType.PLAIN;
        this.layered = layered2 != null ? layered2 : LayerType.PLAIN;
    }

    public HttpRoute(HttpHost target, InetAddress local, HttpHost[] proxies, boolean secure2, TunnelType tunnelled2, LayerType layered2) {
        this(target, local, proxies != null ? Arrays.asList(proxies) : null, secure2, tunnelled2, layered2);
    }

    public HttpRoute(HttpHost target, InetAddress local, HttpHost proxy, boolean secure2, TunnelType tunnelled2, LayerType layered2) {
        this(target, local, proxy != null ? Collections.singletonList(proxy) : null, secure2, tunnelled2, layered2);
    }

    public HttpRoute(HttpHost target, InetAddress local, boolean secure2) {
        this(target, local, Collections.emptyList(), secure2, TunnelType.PLAIN, LayerType.PLAIN);
    }

    public HttpRoute(HttpHost target) {
        this(target, (InetAddress) null, Collections.emptyList(), false, TunnelType.PLAIN, LayerType.PLAIN);
    }

    public HttpRoute(HttpHost target, InetAddress local, HttpHost proxy, boolean secure2) {
        this(target, local, Collections.singletonList(Args.notNull(proxy, "Proxy host")), secure2, secure2 ? TunnelType.TUNNELLED : TunnelType.PLAIN, secure2 ? LayerType.LAYERED : LayerType.PLAIN);
    }

    public HttpRoute(HttpHost target, HttpHost proxy) {
        this(target, null, proxy, false);
    }

    public final HttpHost getTargetHost() {
        return this.targetHost;
    }

    public final InetAddress getLocalAddress() {
        return this.localAddress;
    }

    public final InetSocketAddress getLocalSocketAddress() {
        InetAddress inetAddress = this.localAddress;
        if (inetAddress != null) {
            return new InetSocketAddress(inetAddress, 0);
        }
        return null;
    }

    public final int getHopCount() {
        List<HttpHost> list = this.proxyChain;
        if (list != null) {
            return 1 + list.size();
        }
        return 1;
    }

    public final HttpHost getHopTarget(int hop) {
        Args.notNegative(hop, "Hop index");
        int hopcount = getHopCount();
        Args.check(hop < hopcount, "Hop index exceeds tracked route length");
        if (hop < hopcount - 1) {
            return (HttpHost) this.proxyChain.get(hop);
        }
        return this.targetHost;
    }

    public final HttpHost getProxyHost() {
        List<HttpHost> list = this.proxyChain;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (HttpHost) this.proxyChain.get(0);
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

    public final boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HttpRoute)) {
            return false;
        }
        HttpRoute that = (HttpRoute) obj;
        if (this.secure != that.secure || this.tunnelled != that.tunnelled || this.layered != that.layered || !LangUtils.equals((Object) this.targetHost, (Object) that.targetHost) || !LangUtils.equals((Object) this.localAddress, (Object) that.localAddress) || !LangUtils.equals((Object) this.proxyChain, (Object) that.proxyChain)) {
            z = false;
        }
        return z;
    }

    public final int hashCode() {
        int hash = LangUtils.hashCode(LangUtils.hashCode(17, (Object) this.targetHost), (Object) this.localAddress);
        List<HttpHost> list = this.proxyChain;
        if (list != null) {
            for (HttpHost element : list) {
                hash = LangUtils.hashCode(hash, (Object) element);
            }
        }
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(hash, this.secure), (Object) this.tunnelled), (Object) this.layered);
    }

    public final String toString() {
        StringBuilder cab = new StringBuilder((getHopCount() * 30) + 50);
        InetAddress inetAddress = this.localAddress;
        String str = "->";
        if (inetAddress != null) {
            cab.append(inetAddress);
            cab.append(str);
        }
        cab.append('{');
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
        List<HttpHost> list = this.proxyChain;
        if (list != null) {
            for (HttpHost aProxyChain : list) {
                cab.append(aProxyChain);
                cab.append(str);
            }
        }
        cab.append(this.targetHost);
        return cab.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
