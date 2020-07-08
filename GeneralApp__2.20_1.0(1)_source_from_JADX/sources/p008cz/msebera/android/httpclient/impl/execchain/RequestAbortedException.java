package p008cz.msebera.android.httpclient.impl.execchain;

import java.io.InterruptedIOException;

/* renamed from: cz.msebera.android.httpclient.impl.execchain.RequestAbortedException */
public class RequestAbortedException extends InterruptedIOException {
    private static final long serialVersionUID = 4973849966012490112L;

    public RequestAbortedException(String message) {
        super(message);
    }

    public RequestAbortedException(String message, Throwable cause) {
        super(message);
        if (cause != null) {
            initCause(cause);
        }
    }
}
