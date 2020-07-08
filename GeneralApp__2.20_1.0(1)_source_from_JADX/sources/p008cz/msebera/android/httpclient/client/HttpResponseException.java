package p008cz.msebera.android.httpclient.client;

/* renamed from: cz.msebera.android.httpclient.client.HttpResponseException */
public class HttpResponseException extends ClientProtocolException {
    private static final long serialVersionUID = -7186627969477257933L;
    private final int statusCode;

    public HttpResponseException(int statusCode2, String s) {
        super(s);
        this.statusCode = statusCode2;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
