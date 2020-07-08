package p008cz.msebera.android.httpclient;

/* renamed from: cz.msebera.android.httpclient.HttpException */
public class HttpException extends Exception {
    private static final long serialVersionUID = -5437299376222011036L;

    public HttpException() {
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Throwable cause) {
        super(message);
        initCause(cause);
    }
}
