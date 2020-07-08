package p008cz.msebera.android.httpclient.auth;

import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.auth.AuthOption */
public final class AuthOption {
    private final AuthScheme authScheme;
    private final Credentials creds;

    public AuthOption(AuthScheme authScheme2, Credentials creds2) {
        Args.notNull(authScheme2, "Auth scheme");
        Args.notNull(creds2, "User credentials");
        this.authScheme = authScheme2;
        this.creds = creds2;
    }

    public AuthScheme getAuthScheme() {
        return this.authScheme;
    }

    public Credentials getCredentials() {
        return this.creds;
    }

    public String toString() {
        return this.authScheme.toString();
    }
}
