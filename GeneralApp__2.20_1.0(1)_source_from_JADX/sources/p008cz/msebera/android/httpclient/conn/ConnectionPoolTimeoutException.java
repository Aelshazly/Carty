package p008cz.msebera.android.httpclient.conn;

/* renamed from: cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException */
public class ConnectionPoolTimeoutException extends ConnectTimeoutException {
    private static final long serialVersionUID = -7898874842020245128L;

    public ConnectionPoolTimeoutException() {
    }

    public ConnectionPoolTimeoutException(String message) {
        super(message);
    }
}
