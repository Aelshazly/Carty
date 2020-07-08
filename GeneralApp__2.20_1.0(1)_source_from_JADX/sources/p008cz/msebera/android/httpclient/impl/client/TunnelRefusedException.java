package p008cz.msebera.android.httpclient.impl.client;

import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpResponse;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.client.TunnelRefusedException */
public class TunnelRefusedException extends HttpException {
    private static final long serialVersionUID = -8646722842745617323L;
    private final HttpResponse response;

    public TunnelRefusedException(String message, HttpResponse response2) {
        super(message);
        this.response = response2;
    }

    public HttpResponse getResponse() {
        return this.response;
    }
}
