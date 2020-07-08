package p008cz.msebera.android.httpclient.protocol;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpResponseInterceptor;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.protocol.ResponseContent */
public class ResponseContent implements HttpResponseInterceptor {
    private final boolean overwrite;

    public ResponseContent() {
        this(false);
    }

    public ResponseContent(boolean overwrite2) {
        this.overwrite = overwrite2;
    }

    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        Args.notNull(response, "HTTP response");
        String str = "Transfer-Encoding";
        String str2 = "Content-Length";
        if (this.overwrite) {
            response.removeHeaders(str);
            response.removeHeaders(str2);
        } else if (response.containsHeader(str)) {
            throw new ProtocolException("Transfer-encoding header already present");
        } else if (response.containsHeader(str2)) {
            throw new ProtocolException("Content-Length header already present");
        }
        ProtocolVersion ver = response.getStatusLine().getProtocolVersion();
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            long len = entity.getContentLength();
            if (entity.isChunked() && !ver.lessEquals(HttpVersion.HTTP_1_0)) {
                response.addHeader(str, HTTP.CHUNK_CODING);
            } else if (len >= 0) {
                response.addHeader(str2, Long.toString(entity.getContentLength()));
            }
            if (entity.getContentType() != null && !response.containsHeader("Content-Type")) {
                response.addHeader(entity.getContentType());
            }
            if (entity.getContentEncoding() != null && !response.containsHeader("Content-Encoding")) {
                response.addHeader(entity.getContentEncoding());
                return;
            }
            return;
        }
        int status = response.getStatusLine().getStatusCode();
        if (status != 204 && status != 304 && status != 205) {
            response.addHeader(str2, "0");
        }
    }
}
