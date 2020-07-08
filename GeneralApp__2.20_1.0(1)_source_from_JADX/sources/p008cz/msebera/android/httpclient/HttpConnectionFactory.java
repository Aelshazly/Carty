package p008cz.msebera.android.httpclient;

import java.io.IOException;
import java.net.Socket;
import p008cz.msebera.android.httpclient.HttpConnection;

/* renamed from: cz.msebera.android.httpclient.HttpConnectionFactory */
public interface HttpConnectionFactory<T extends HttpConnection> {
    T createConnection(Socket socket) throws IOException;
}
