package p008cz.msebera.android.httpclient.protocol;

import java.io.IOException;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpResponseInterceptor;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.protocol.ResponseConnControl */
public class ResponseConnControl implements HttpResponseInterceptor {
    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        Args.notNull(response, "HTTP response");
        HttpCoreContext corecontext = HttpCoreContext.adapt(context);
        int status = response.getStatusLine().getStatusCode();
        String str = HTTP.CONN_CLOSE;
        String str2 = "Connection";
        if (status == 400 || status == 408 || status == 411 || status == 413 || status == 414 || status == 503 || status == 501) {
            response.setHeader(str2, str);
            return;
        }
        Header explicit = response.getFirstHeader(str2);
        if (explicit == null || !str.equalsIgnoreCase(explicit.getValue())) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                ProtocolVersion ver = response.getStatusLine().getProtocolVersion();
                if (entity.getContentLength() < 0 && (!entity.isChunked() || ver.lessEquals(HttpVersion.HTTP_1_0))) {
                    response.setHeader(str2, str);
                    return;
                }
            }
            HttpRequest request = corecontext.getRequest();
            if (request != null) {
                Header header = request.getFirstHeader(str2);
                if (header != null) {
                    response.setHeader(str2, header.getValue());
                } else if (request.getProtocolVersion().lessEquals(HttpVersion.HTTP_1_0)) {
                    response.setHeader(str2, str);
                }
            }
        }
    }
}
