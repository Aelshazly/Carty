package p008cz.msebera.android.httpclient.impl.auth;

import p008cz.msebera.android.httpclient.auth.AuthScheme;
import p008cz.msebera.android.httpclient.auth.AuthSchemeFactory;
import p008cz.msebera.android.httpclient.auth.AuthSchemeProvider;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.impl.auth.NTLMSchemeFactory */
public class NTLMSchemeFactory implements AuthSchemeFactory, AuthSchemeProvider {
    public AuthScheme newInstance(HttpParams params) {
        return new NTLMScheme();
    }

    public AuthScheme create(HttpContext context) {
        return new NTLMScheme();
    }
}
