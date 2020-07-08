package p008cz.msebera.android.httpclient.impl.client;

import java.util.HashMap;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.auth.AuthScheme;
import p008cz.msebera.android.httpclient.client.AuthCache;
import p008cz.msebera.android.httpclient.conn.SchemePortResolver;
import p008cz.msebera.android.httpclient.conn.UnsupportedSchemeException;
import p008cz.msebera.android.httpclient.impl.conn.DefaultSchemePortResolver;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.client.BasicAuthCache */
public class BasicAuthCache implements AuthCache {
    private final HashMap<HttpHost, AuthScheme> map;
    private final SchemePortResolver schemePortResolver;

    public BasicAuthCache(SchemePortResolver schemePortResolver2) {
        this.map = new HashMap<>();
        this.schemePortResolver = schemePortResolver2 != null ? schemePortResolver2 : DefaultSchemePortResolver.INSTANCE;
    }

    public BasicAuthCache() {
        this(null);
    }

    /* access modifiers changed from: protected */
    public HttpHost getKey(HttpHost host) {
        if (host.getPort() > 0) {
            return host;
        }
        try {
            return new HttpHost(host.getHostName(), this.schemePortResolver.resolve(host), host.getSchemeName());
        } catch (UnsupportedSchemeException e) {
            return host;
        }
    }

    public void put(HttpHost host, AuthScheme authScheme) {
        Args.notNull(host, "HTTP host");
        this.map.put(getKey(host), authScheme);
    }

    public AuthScheme get(HttpHost host) {
        Args.notNull(host, "HTTP host");
        return (AuthScheme) this.map.get(getKey(host));
    }

    public void remove(HttpHost host) {
        Args.notNull(host, "HTTP host");
        this.map.remove(getKey(host));
    }

    public void clear() {
        this.map.clear();
    }

    public String toString() {
        return this.map.toString();
    }
}
