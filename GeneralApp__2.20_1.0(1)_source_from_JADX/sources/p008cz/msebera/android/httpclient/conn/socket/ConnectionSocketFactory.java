package p008cz.msebera.android.httpclient.conn.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.conn.socket.ConnectionSocketFactory */
public interface ConnectionSocketFactory {
    Socket connectSocket(int i, Socket socket, HttpHost httpHost, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpContext httpContext) throws IOException;

    Socket createSocket(HttpContext httpContext) throws IOException;
}
