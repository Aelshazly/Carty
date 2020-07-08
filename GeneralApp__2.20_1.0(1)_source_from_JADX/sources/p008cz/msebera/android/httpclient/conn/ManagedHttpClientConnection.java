package p008cz.msebera.android.httpclient.conn;

import java.io.IOException;
import java.net.Socket;
import javax.net.ssl.SSLSession;
import p008cz.msebera.android.httpclient.HttpClientConnection;
import p008cz.msebera.android.httpclient.HttpInetConnection;

/* renamed from: cz.msebera.android.httpclient.conn.ManagedHttpClientConnection */
public interface ManagedHttpClientConnection extends HttpClientConnection, HttpInetConnection {
    void bind(Socket socket) throws IOException;

    String getId();

    SSLSession getSSLSession();

    Socket getSocket();
}
