package p008cz.msebera.android.httpclient.client;

import p008cz.msebera.android.httpclient.ProtocolException;

/* renamed from: cz.msebera.android.httpclient.client.RedirectException */
public class RedirectException extends ProtocolException {
    private static final long serialVersionUID = 4418824536372559326L;

    public RedirectException() {
    }

    public RedirectException(String message) {
        super(message);
    }

    public RedirectException(String message, Throwable cause) {
        super(message, cause);
    }
}
