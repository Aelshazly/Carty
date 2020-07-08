package p008cz.msebera.android.httpclient;

import java.io.IOException;

/* renamed from: cz.msebera.android.httpclient.ConnectionClosedException */
public class ConnectionClosedException extends IOException {
    private static final long serialVersionUID = 617550366255636674L;

    public ConnectionClosedException(String message) {
        super(message);
    }
}
