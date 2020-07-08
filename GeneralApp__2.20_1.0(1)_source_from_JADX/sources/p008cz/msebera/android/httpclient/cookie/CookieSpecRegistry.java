package p008cz.msebera.android.httpclient.cookie;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.config.Lookup;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.cookie.CookieSpecRegistry */
public final class CookieSpecRegistry implements Lookup<CookieSpecProvider> {
    private final ConcurrentHashMap<String, CookieSpecFactory> registeredSpecs = new ConcurrentHashMap<>();

    public void register(String name, CookieSpecFactory factory) {
        Args.notNull(name, "Name");
        Args.notNull(factory, "Cookie spec factory");
        this.registeredSpecs.put(name.toLowerCase(Locale.ENGLISH), factory);
    }

    public void unregister(String id) {
        Args.notNull(id, "Id");
        this.registeredSpecs.remove(id.toLowerCase(Locale.ENGLISH));
    }

    public CookieSpec getCookieSpec(String name, HttpParams params) throws IllegalStateException {
        Args.notNull(name, "Name");
        CookieSpecFactory factory = (CookieSpecFactory) this.registeredSpecs.get(name.toLowerCase(Locale.ENGLISH));
        if (factory != null) {
            return factory.newInstance(params);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unsupported cookie spec: ");
        sb.append(name);
        throw new IllegalStateException(sb.toString());
    }

    public CookieSpec getCookieSpec(String name) throws IllegalStateException {
        return getCookieSpec(name, null);
    }

    public List<String> getSpecNames() {
        return new ArrayList(this.registeredSpecs.keySet());
    }

    public void setItems(Map<String, CookieSpecFactory> map) {
        if (map != null) {
            this.registeredSpecs.clear();
            this.registeredSpecs.putAll(map);
        }
    }

    public CookieSpecProvider lookup(final String name) {
        return new CookieSpecProvider() {
            public CookieSpec create(HttpContext context) {
                return CookieSpecRegistry.this.getCookieSpec(name, ((HttpRequest) context.getAttribute("http.request")).getParams());
            }
        };
    }
}
