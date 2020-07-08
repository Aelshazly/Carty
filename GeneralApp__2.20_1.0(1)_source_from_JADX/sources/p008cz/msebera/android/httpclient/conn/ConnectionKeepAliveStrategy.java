package p008cz.msebera.android.httpclient.conn;

import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy */
public interface ConnectionKeepAliveStrategy {
    long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext);
}
