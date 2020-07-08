package p008cz.msebera.android.httpclient.client.protocol;

import java.io.IOException;
import java.util.Collection;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpRequestInterceptor;
import p008cz.msebera.android.httpclient.client.params.ClientPNames;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.client.protocol.RequestDefaultHeaders */
public class RequestDefaultHeaders implements HttpRequestInterceptor {
    private final Collection<? extends Header> defaultHeaders;

    public RequestDefaultHeaders(Collection<? extends Header> defaultHeaders2) {
        this.defaultHeaders = defaultHeaders2;
    }

    public RequestDefaultHeaders() {
        this(null);
    }

    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        if (!request.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
            Collection<? extends Header> defHeaders = (Collection) request.getParams().getParameter(ClientPNames.DEFAULT_HEADERS);
            if (defHeaders == null) {
                defHeaders = this.defaultHeaders;
            }
            if (defHeaders != null) {
                for (Header defHeader : defHeaders) {
                    request.addHeader(defHeader);
                }
            }
        }
    }
}
