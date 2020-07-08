package p008cz.msebera.android.httpclient.protocol;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpResponseInterceptor;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.protocol.ResponseServer */
public class ResponseServer implements HttpResponseInterceptor {
    private final String originServer;

    public ResponseServer(String originServer2) {
        this.originServer = originServer2;
    }

    public ResponseServer() {
        this(null);
    }

    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        Args.notNull(response, "HTTP response");
        String str = "Server";
        if (!response.containsHeader(str)) {
            String str2 = this.originServer;
            if (str2 != null) {
                response.addHeader(str, str2);
            }
        }
    }
}
