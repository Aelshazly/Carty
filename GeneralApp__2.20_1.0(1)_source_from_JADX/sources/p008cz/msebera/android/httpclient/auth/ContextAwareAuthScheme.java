package p008cz.msebera.android.httpclient.auth;

import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.auth.ContextAwareAuthScheme */
public interface ContextAwareAuthScheme extends AuthScheme {
    Header authenticate(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) throws AuthenticationException;
}
