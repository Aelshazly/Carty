package p008cz.msebera.android.httpclient.impl.client;

import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.HeaderElementIterator;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy;
import p008cz.msebera.android.httpclient.message.BasicHeaderElementIterator;
import p008cz.msebera.android.httpclient.protocol.HTTP;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.client.DefaultConnectionKeepAliveStrategy */
public class DefaultConnectionKeepAliveStrategy implements ConnectionKeepAliveStrategy {
    public static final DefaultConnectionKeepAliveStrategy INSTANCE = new DefaultConnectionKeepAliveStrategy();

    public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
        Args.notNull(response, "HTTP response");
        HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
        while (it.hasNext()) {
            HeaderElement he = it.nextElement();
            String param = he.getName();
            String value = he.getValue();
            if (value != null && param.equalsIgnoreCase("timeout")) {
                try {
                    return Long.parseLong(value) * 1000;
                } catch (NumberFormatException e) {
                }
            }
        }
        return -1;
    }
}
