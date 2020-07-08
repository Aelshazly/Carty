package p008cz.msebera.android.httpclient.conn.scheme;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import p008cz.msebera.android.httpclient.conn.ConnectTimeoutException;
import p008cz.msebera.android.httpclient.params.HttpParams;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.conn.scheme.SchemeLayeredSocketFactoryAdaptor2 */
class SchemeLayeredSocketFactoryAdaptor2 implements SchemeLayeredSocketFactory {
    private final LayeredSchemeSocketFactory factory;

    SchemeLayeredSocketFactoryAdaptor2(LayeredSchemeSocketFactory factory2) {
        this.factory = factory2;
    }

    public Socket createSocket(HttpParams params) throws IOException {
        return this.factory.createSocket(params);
    }

    public Socket connectSocket(Socket sock, InetSocketAddress remoteAddress, InetSocketAddress localAddress, HttpParams params) throws IOException, UnknownHostException, ConnectTimeoutException {
        return this.factory.connectSocket(sock, remoteAddress, localAddress, params);
    }

    public boolean isSecure(Socket sock) throws IllegalArgumentException {
        return this.factory.isSecure(sock);
    }

    public Socket createLayeredSocket(Socket socket, String target, int port, HttpParams params) throws IOException, UnknownHostException {
        return this.factory.createLayeredSocket(socket, target, port, true);
    }
}