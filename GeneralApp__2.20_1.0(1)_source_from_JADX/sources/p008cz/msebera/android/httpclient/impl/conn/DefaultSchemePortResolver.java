package p008cz.msebera.android.httpclient.impl.conn;

import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.conn.SchemePortResolver;
import p008cz.msebera.android.httpclient.conn.UnsupportedSchemeException;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.conn.DefaultSchemePortResolver */
public class DefaultSchemePortResolver implements SchemePortResolver {
    public static final DefaultSchemePortResolver INSTANCE = new DefaultSchemePortResolver();

    public int resolve(HttpHost host) throws UnsupportedSchemeException {
        Args.notNull(host, "HTTP host");
        int port = host.getPort();
        if (port > 0) {
            return port;
        }
        String name = host.getSchemeName();
        if (name.equalsIgnoreCase(HttpHost.DEFAULT_SCHEME_NAME)) {
            return 80;
        }
        if (name.equalsIgnoreCase("https")) {
            return 443;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" protocol is not supported");
        throw new UnsupportedSchemeException(sb.toString());
    }
}
