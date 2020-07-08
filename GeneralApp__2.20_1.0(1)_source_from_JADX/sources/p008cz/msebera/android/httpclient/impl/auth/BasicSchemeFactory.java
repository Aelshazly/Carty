package p008cz.msebera.android.httpclient.impl.auth;

import java.nio.charset.Charset;
import p008cz.msebera.android.httpclient.auth.AuthScheme;
import p008cz.msebera.android.httpclient.auth.AuthSchemeFactory;
import p008cz.msebera.android.httpclient.auth.AuthSchemeProvider;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.impl.auth.BasicSchemeFactory */
public class BasicSchemeFactory implements AuthSchemeFactory, AuthSchemeProvider {
    private final Charset charset;

    public BasicSchemeFactory(Charset charset2) {
        this.charset = charset2;
    }

    public BasicSchemeFactory() {
        this(null);
    }

    public AuthScheme newInstance(HttpParams params) {
        return new BasicScheme();
    }

    public AuthScheme create(HttpContext context) {
        return new BasicScheme(this.charset);
    }
}
