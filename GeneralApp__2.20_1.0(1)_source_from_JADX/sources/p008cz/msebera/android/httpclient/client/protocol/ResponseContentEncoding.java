package p008cz.msebera.android.httpclient.client.protocol;

import com.loopj.android.http.AsyncHttpClient;
import java.io.IOException;
import java.util.Locale;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHeaders;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpResponseInterceptor;
import p008cz.msebera.android.httpclient.client.entity.DeflateDecompressingEntity;
import p008cz.msebera.android.httpclient.client.entity.GzipDecompressingEntity;
import p008cz.msebera.android.httpclient.protocol.HTTP;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.client.protocol.ResponseContentEncoding */
public class ResponseContentEncoding implements HttpResponseInterceptor {
    public static final String UNCOMPRESSED = "http.client.response.uncompressed";

    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        HttpEntity entity = response.getEntity();
        if (!(entity == null || entity.getContentLength() == 0)) {
            Header ceheader = entity.getContentEncoding();
            if (ceheader != null) {
                HeaderElement[] codecs = ceheader.getElements();
                boolean uncompressed = false;
                if (codecs.length > 0) {
                    HeaderElement codec = codecs[0];
                    String codecname = codec.getName().toLowerCase(Locale.ENGLISH);
                    if (AsyncHttpClient.ENCODING_GZIP.equals(codecname) || "x-gzip".equals(codecname)) {
                        response.setEntity(new GzipDecompressingEntity(response.getEntity()));
                        uncompressed = true;
                    } else if ("deflate".equals(codecname)) {
                        response.setEntity(new DeflateDecompressingEntity(response.getEntity()));
                        uncompressed = true;
                    } else if (!HTTP.IDENTITY_CODING.equals(codecname)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unsupported Content-Coding: ");
                        sb.append(codec.getName());
                        throw new HttpException(sb.toString());
                    } else {
                        return;
                    }
                }
                if (uncompressed) {
                    response.removeHeaders("Content-Length");
                    response.removeHeaders("Content-Encoding");
                    response.removeHeaders(HttpHeaders.CONTENT_MD5);
                }
            }
        }
    }
}
