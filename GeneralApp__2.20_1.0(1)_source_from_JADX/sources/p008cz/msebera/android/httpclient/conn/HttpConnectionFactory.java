package p008cz.msebera.android.httpclient.conn;

import p008cz.msebera.android.httpclient.HttpConnection;
import p008cz.msebera.android.httpclient.config.ConnectionConfig;

/* renamed from: cz.msebera.android.httpclient.conn.HttpConnectionFactory */
public interface HttpConnectionFactory<T, C extends HttpConnection> {
    C create(T t, ConnectionConfig connectionConfig);
}
