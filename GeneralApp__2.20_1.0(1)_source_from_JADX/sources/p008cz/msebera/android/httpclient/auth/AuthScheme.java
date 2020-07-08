package p008cz.msebera.android.httpclient.auth;

import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpRequest;

/* renamed from: cz.msebera.android.httpclient.auth.AuthScheme */
public interface AuthScheme {
    @Deprecated
    Header authenticate(Credentials credentials, HttpRequest httpRequest) throws AuthenticationException;

    String getParameter(String str);

    String getRealm();

    String getSchemeName();

    boolean isComplete();

    boolean isConnectionBased();

    void processChallenge(Header header) throws MalformedChallengeException;
}
