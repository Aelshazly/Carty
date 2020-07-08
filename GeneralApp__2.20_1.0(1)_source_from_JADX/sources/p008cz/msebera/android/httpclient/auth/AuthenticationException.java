package p008cz.msebera.android.httpclient.auth;

import p008cz.msebera.android.httpclient.ProtocolException;

/* renamed from: cz.msebera.android.httpclient.auth.AuthenticationException */
public class AuthenticationException extends ProtocolException {
    private static final long serialVersionUID = -6794031905674764776L;

    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
