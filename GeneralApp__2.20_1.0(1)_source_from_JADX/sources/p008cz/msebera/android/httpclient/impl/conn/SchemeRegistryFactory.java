package p008cz.msebera.android.httpclient.impl.conn;

import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.conn.scheme.PlainSocketFactory;
import p008cz.msebera.android.httpclient.conn.scheme.Scheme;
import p008cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import p008cz.msebera.android.httpclient.conn.scheme.SchemeSocketFactory;
import p008cz.msebera.android.httpclient.conn.ssl.SSLSocketFactory;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.SchemeRegistryFactory */
public final class SchemeRegistryFactory {
    public static SchemeRegistry createDefault() {
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme(HttpHost.DEFAULT_SCHEME_NAME, 80, (SchemeSocketFactory) PlainSocketFactory.getSocketFactory()));
        registry.register(new Scheme("https", 443, (SchemeSocketFactory) SSLSocketFactory.getSocketFactory()));
        return registry;
    }

    public static SchemeRegistry createSystemDefault() {
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme(HttpHost.DEFAULT_SCHEME_NAME, 80, (SchemeSocketFactory) PlainSocketFactory.getSocketFactory()));
        registry.register(new Scheme("https", 443, (SchemeSocketFactory) SSLSocketFactory.getSystemSocketFactory()));
        return registry;
    }
}
