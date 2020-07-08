package p008cz.msebera.android.httpclient.protocol;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpResponseInterceptor;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.protocol.ResponseDate */
public class ResponseDate implements HttpResponseInterceptor {
    private static final HttpDateGenerator DATE_GENERATOR = new HttpDateGenerator();

    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        Args.notNull(response, "HTTP response");
        if (response.getStatusLine().getStatusCode() >= 200) {
            String str = "Date";
            if (!response.containsHeader(str)) {
                response.setHeader(str, DATE_GENERATOR.getCurrentDate());
            }
        }
    }
}
