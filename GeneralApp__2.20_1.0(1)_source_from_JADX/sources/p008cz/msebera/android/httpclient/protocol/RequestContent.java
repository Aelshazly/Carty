package p008cz.msebera.android.httpclient.protocol;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpRequestInterceptor;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.protocol.RequestContent */
public class RequestContent implements HttpRequestInterceptor {
    private final boolean overwrite;

    public RequestContent() {
        this(false);
    }

    public RequestContent(boolean overwrite2) {
        this.overwrite = overwrite2;
    }

    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        if (request instanceof HttpEntityEnclosingRequest) {
            String str = "Transfer-Encoding";
            String str2 = "Content-Length";
            if (this.overwrite) {
                request.removeHeaders(str);
                request.removeHeaders(str2);
            } else if (request.containsHeader(str)) {
                throw new ProtocolException("Transfer-encoding header already present");
            } else if (request.containsHeader(str2)) {
                throw new ProtocolException("Content-Length header already present");
            }
            ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
            HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
            if (entity == null) {
                request.addHeader(str2, "0");
                return;
            }
            if (!entity.isChunked() && entity.getContentLength() >= 0) {
                request.addHeader(str2, Long.toString(entity.getContentLength()));
            } else if (!ver.lessEquals(HttpVersion.HTTP_1_0)) {
                request.addHeader(str, HTTP.CHUNK_CODING);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Chunked transfer encoding not allowed for ");
                sb.append(ver);
                throw new ProtocolException(sb.toString());
            }
            if (entity.getContentType() != null && !request.containsHeader("Content-Type")) {
                request.addHeader(entity.getContentType());
            }
            if (entity.getContentEncoding() != null && !request.containsHeader("Content-Encoding")) {
                request.addHeader(entity.getContentEncoding());
            }
        }
    }
}
