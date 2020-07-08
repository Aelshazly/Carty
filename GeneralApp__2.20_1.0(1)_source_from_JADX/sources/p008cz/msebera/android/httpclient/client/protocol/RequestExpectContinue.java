package p008cz.msebera.android.httpclient.client.protocol;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpRequestInterceptor;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.protocol.HTTP;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.client.protocol.RequestExpectContinue */
public class RequestExpectContinue implements HttpRequestInterceptor {
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        String str = "Expect";
        if (!request.containsHeader(str) && (request instanceof HttpEntityEnclosingRequest)) {
            ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
            HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
            if (entity != null && entity.getContentLength() != 0 && !ver.lessEquals(HttpVersion.HTTP_1_0) && HttpClientContext.adapt(context).getRequestConfig().isExpectContinueEnabled()) {
                request.addHeader(str, HTTP.EXPECT_CONTINUE);
            }
        }
    }
}
