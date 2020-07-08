package p008cz.msebera.android.httpclient.impl.conn;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
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
/* renamed from: cz.msebera.android.httpclient.impl.conn.ProxySelectorRoutePlanner */
public class ProxySelectorRoutePlanner implements HttpRoutePlanner {
    protected ProxySelector proxySelector;
    protected final SchemeRegistry schemeRegistry;

    /* renamed from: cz.msebera.android.httpclient.impl.conn.ProxySelectorRoutePlanner$1 */
    static /* synthetic */ class C12911 {
        static final /* synthetic */ int[] $SwitchMap$java$net$Proxy$Type = new int[Type.values().length];

        static {
            try {
                $SwitchMap$java$net$Proxy$Type[Type.DIRECT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$net$Proxy$Type[Type.HTTP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$net$Proxy$Type[Type.SOCKS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public ProxySelectorRoutePlanner(SchemeRegistry schreg, ProxySelector prosel) {
        Args.notNull(schreg, "SchemeRegistry");
        this.schemeRegistry = schreg;
        this.proxySelector = prosel;
    }

    public ProxySelector getProxySelector() {
        return this.proxySelector;
    }

    public void setProxySelector(ProxySelector prosel) {
        this.proxySelector = prosel;
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
        HttpHost proxy = determineProxy(target, request, context);
        boolean secure = this.schemeRegistry.getScheme(target.getSchemeName()).isLayered();
        if (proxy == null) {
            route = new HttpRoute(target, local, secure);
        } else {
            route = new HttpRoute(target, local, proxy, secure);
        }
        return route;
    }

    /* access modifiers changed from: protected */
    public HttpHost determineProxy(HttpHost target, HttpRequest request, HttpContext context) throws HttpException {
        ProxySelector psel = this.proxySelector;
        if (psel == null) {
            psel = ProxySelector.getDefault();
        }
        if (psel == null) {
            return null;
        }
        try {
            Proxy p = chooseProxy(psel.select(new URI(target.toURI())), target, request, context);
            HttpHost result = null;
            if (p.type() == Type.HTTP) {
                if (p.address() instanceof InetSocketAddress) {
                    InetSocketAddress isa = (InetSocketAddress) p.address();
                    result = new HttpHost(getHost(isa), isa.getPort());
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unable to handle non-Inet proxy address: ");
                    sb.append(p.address());
                    throw new HttpException(sb.toString());
                }
            }
            return result;
        } catch (URISyntaxException usx) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Cannot convert host to URI: ");
            sb2.append(target);
            throw new HttpException(sb2.toString(), usx);
        }
    }

    /* access modifiers changed from: protected */
    public String getHost(InetSocketAddress isa) {
        return isa.isUnresolved() ? isa.getHostName() : isa.getAddress().getHostAddress();
    }

    /* access modifiers changed from: protected */
    public Proxy chooseProxy(List<Proxy> proxies, HttpHost target, HttpRequest request, HttpContext context) {
        Args.notEmpty(proxies, "List of proxies");
        Proxy result = null;
        int i = 0;
        while (result == null && i < proxies.size()) {
            Proxy p = (Proxy) proxies.get(i);
            int i2 = C12911.$SwitchMap$java$net$Proxy$Type[p.type().ordinal()];
            if (i2 == 1 || i2 == 2) {
                result = p;
            }
            i++;
        }
        if (result == null) {
            return Proxy.NO_PROXY;
        }
        return result;
    }
}
