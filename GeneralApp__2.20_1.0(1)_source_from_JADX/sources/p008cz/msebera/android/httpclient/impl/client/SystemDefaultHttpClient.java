package p008cz.msebera.android.httpclient.impl.client;

import java.net.ProxySelector;
import p008cz.msebera.android.httpclient.ConnectionReuseStrategy;
import p008cz.msebera.android.httpclient.conn.ClientConnectionManager;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner;
import p008cz.msebera.android.httpclient.impl.DefaultConnectionReuseStrategy;
import p008cz.msebera.android.httpclient.impl.NoConnectionReuseStrategy;
import p008cz.msebera.android.httpclient.impl.conn.PoolingClientConnectionManager;
import p008cz.msebera.android.httpclient.impl.conn.ProxySelectorRoutePlanner;
import p008cz.msebera.android.httpclient.impl.conn.SchemeRegistryFactory;
import p008cz.msebera.android.httpclient.params.HttpParams;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.client.SystemDefaultHttpClient */
public class SystemDefaultHttpClient extends DefaultHttpClient {
    public SystemDefaultHttpClient(HttpParams params) {
        super(null, params);
    }

    public SystemDefaultHttpClient() {
        super(null, null);
    }

    /* access modifiers changed from: protected */
    public ClientConnectionManager createClientConnectionManager() {
        PoolingClientConnectionManager connmgr = new PoolingClientConnectionManager(SchemeRegistryFactory.createSystemDefault());
        String str = "true";
        if (str.equalsIgnoreCase(System.getProperty("http.keepAlive", str))) {
            int max = Integer.parseInt(System.getProperty("http.maxConnections", "5"));
            connmgr.setDefaultMaxPerRoute(max);
            connmgr.setMaxTotal(max * 2);
        }
        return connmgr;
    }

    /* access modifiers changed from: protected */
    public HttpRoutePlanner createHttpRoutePlanner() {
        return new ProxySelectorRoutePlanner(getConnectionManager().getSchemeRegistry(), ProxySelector.getDefault());
    }

    /* access modifiers changed from: protected */
    public ConnectionReuseStrategy createConnectionReuseStrategy() {
        String str = "true";
        if (str.equalsIgnoreCase(System.getProperty("http.keepAlive", str))) {
            return new DefaultConnectionReuseStrategy();
        }
        return new NoConnectionReuseStrategy();
    }
}
