package p008cz.msebera.android.httpclient.client.protocol;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpRequestInterceptor;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.client.protocol.RequestAcceptEncoding */
public class RequestAcceptEncoding implements HttpRequestInterceptor {
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        String str = "Accept-Encoding";
        if (!request.containsHeader(str)) {
            request.addHeader(str, "gzip,deflate");
        }
    }
}
