package p008cz.msebera.android.httpclient.conn;

import p008cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import p008cz.msebera.android.httpclient.params.HttpParams;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.conn.ClientConnectionManagerFactory */
public interface ClientConnectionManagerFactory {
    ClientConnectionManager newInstance(HttpParams httpParams, SchemeRegistry schemeRegistry);
}
