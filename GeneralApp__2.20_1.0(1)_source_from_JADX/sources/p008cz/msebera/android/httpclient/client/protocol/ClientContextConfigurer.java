package p008cz.msebera.android.httpclient.client.protocol;

import p008cz.msebera.android.httpclient.auth.AuthSchemeRegistry;
import p008cz.msebera.android.httpclient.client.CookieStore;
import p008cz.msebera.android.httpclient.client.CredentialsProvider;
import p008cz.msebera.android.httpclient.cookie.CookieSpecRegistry;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.client.protocol.ClientContextConfigurer */
public class ClientContextConfigurer implements ClientContext {
    private final HttpContext context;

    public ClientContextConfigurer(HttpContext context2) {
        Args.notNull(context2, "HTTP context");
        this.context = context2;
    }

    public void setCookieSpecRegistry(CookieSpecRegistry registry) {
        this.context.setAttribute("http.cookiespec-registry", registry);
    }

    public void setAuthSchemeRegistry(AuthSchemeRegistry registry) {
        this.context.setAttribute("http.authscheme-registry", registry);
    }

    public void setCookieStore(CookieStore store) {
        this.context.setAttribute("http.cookie-store", store);
    }

    public void setCredentialsProvider(CredentialsProvider provider) {
        this.context.setAttribute("http.auth.credentials-provider", provider);
    }
}
