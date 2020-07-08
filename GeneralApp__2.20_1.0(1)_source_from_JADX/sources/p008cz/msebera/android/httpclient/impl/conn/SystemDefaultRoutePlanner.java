package p008cz.msebera.android.httpclient.impl.conn;

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
import p008cz.msebera.android.httpclient.conn.SchemePortResolver;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.impl.conn.SystemDefaultRoutePlanner */
public class SystemDefaultRoutePlanner extends DefaultRoutePlanner {
    private final ProxySelector proxySelector;

    /* renamed from: cz.msebera.android.httpclient.impl.conn.SystemDefaultRoutePlanner$1 */
    static /* synthetic */ class C12931 {
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

    public SystemDefaultRoutePlanner(SchemePortResolver schemePortResolver, ProxySelector proxySelector2) {
        super(schemePortResolver);
        this.proxySelector = proxySelector2 != null ? proxySelector2 : ProxySelector.getDefault();
    }

    public SystemDefaultRoutePlanner(ProxySelector proxySelector2) {
        this(null, proxySelector2);
    }

    /* access modifiers changed from: protected */
    public HttpHost determineProxy(HttpHost target, HttpRequest request, HttpContext context) throws HttpException {
        try {
            Proxy p = chooseProxy(this.proxySelector.select(new URI(target.toURI())));
            if (p.type() != Type.HTTP) {
                return null;
            }
            if (p.address() instanceof InetSocketAddress) {
                InetSocketAddress isa = (InetSocketAddress) p.address();
                return new HttpHost(getHost(isa), isa.getPort());
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to handle non-Inet proxy address: ");
            sb.append(p.address());
            throw new HttpException(sb.toString());
        } catch (URISyntaxException ex) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Cannot convert host to URI: ");
            sb2.append(target);
            throw new HttpException(sb2.toString(), ex);
        }
    }

    private String getHost(InetSocketAddress isa) {
        return isa.isUnresolved() ? isa.getHostName() : isa.getAddress().getHostAddress();
    }

    private Proxy chooseProxy(List<Proxy> proxies) {
        Proxy result = null;
        int i = 0;
        while (result == null && i < proxies.size()) {
            Proxy p = (Proxy) proxies.get(i);
            int i2 = C12931.$SwitchMap$java$net$Proxy$Type[p.type().ordinal()];
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
