package p008cz.msebera.android.httpclient.auth;

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
/* renamed from: cz.msebera.android.httpclient.auth.AuthSchemeRegistry */
public final class AuthSchemeRegistry implements Lookup<AuthSchemeProvider> {
    private final ConcurrentHashMap<String, AuthSchemeFactory> registeredSchemes = new ConcurrentHashMap<>();

    public void register(String name, AuthSchemeFactory factory) {
        Args.notNull(name, "Name");
        Args.notNull(factory, "Authentication scheme factory");
        this.registeredSchemes.put(name.toLowerCase(Locale.ENGLISH), factory);
    }

    public void unregister(String name) {
        Args.notNull(name, "Name");
        this.registeredSchemes.remove(name.toLowerCase(Locale.ENGLISH));
    }

    public AuthScheme getAuthScheme(String name, HttpParams params) throws IllegalStateException {
        Args.notNull(name, "Name");
        AuthSchemeFactory factory = (AuthSchemeFactory) this.registeredSchemes.get(name.toLowerCase(Locale.ENGLISH));
        if (factory != null) {
            return factory.newInstance(params);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unsupported authentication scheme: ");
        sb.append(name);
        throw new IllegalStateException(sb.toString());
    }

    public List<String> getSchemeNames() {
        return new ArrayList(this.registeredSchemes.keySet());
    }

    public void setItems(Map<String, AuthSchemeFactory> map) {
        if (map != null) {
            this.registeredSchemes.clear();
            this.registeredSchemes.putAll(map);
        }
    }

    public AuthSchemeProvider lookup(final String name) {
        return new AuthSchemeProvider() {
            public AuthScheme create(HttpContext context) {
                return AuthSchemeRegistry.this.getAuthScheme(name, ((HttpRequest) context.getAttribute("http.request")).getParams());
            }
        };
    }
}
