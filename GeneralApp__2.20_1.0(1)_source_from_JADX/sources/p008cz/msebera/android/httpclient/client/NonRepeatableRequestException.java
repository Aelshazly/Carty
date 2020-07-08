package p008cz.msebera.android.httpclient.client;

import p008cz.msebera.android.httpclient.ProtocolException;

/* renamed from: cz.msebera.android.httpclient.client.NonRepeatableRequestException */
public class NonRepeatableRequestException extends ProtocolException {
    private static final long serialVersionUID = 82685265288806048L;

    public NonRepeatableRequestException() {
    }

    public NonRepeatableRequestException(String message) {
        super(message);
    }

    public NonRepeatableRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
