package p008cz.msebera.android.httpclient.cookie;

import p008cz.msebera.android.httpclient.ProtocolException;

/* renamed from: cz.msebera.android.httpclient.cookie.MalformedCookieException */
public class MalformedCookieException extends ProtocolException {
    private static final long serialVersionUID = -6695462944287282185L;

    public MalformedCookieException() {
    }

    public MalformedCookieException(String message) {
        super(message);
    }

    public MalformedCookieException(String message, Throwable cause) {
        super(message, cause);
    }
}
