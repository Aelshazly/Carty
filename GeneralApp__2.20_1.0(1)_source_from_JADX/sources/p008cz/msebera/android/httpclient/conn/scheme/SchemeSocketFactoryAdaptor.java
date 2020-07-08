package p008cz.msebera.android.httpclient.conn.scheme;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import p008cz.msebera.android.httpclient.conn.ConnectTimeoutException;
import p008cz.msebera.android.httpclient.params.HttpParams;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.conn.scheme.SchemeSocketFactoryAdaptor */
class SchemeSocketFactoryAdaptor implements SchemeSocketFactory {
    private final SocketFactory factory;

    SchemeSocketFactoryAdaptor(SocketFactory factory2) {
        this.factory = factory2;
    }

    public Socket connectSocket(Socket sock, InetSocketAddress remoteAddress, InetSocketAddress localAddress, HttpParams params) throws IOException, UnknownHostException, ConnectTimeoutException {
        int localPort;
        InetAddress local;
        String host = remoteAddress.getHostName();
        int port = remoteAddress.getPort();
        if (localAddress != null) {
            local = localAddress.getAddress();
            localPort = localAddress.getPort();
        } else {
            local = null;
            localPort = 0;
        }
        return this.factory.connectSocket(sock, host, port, local, localPort, params);
    }

    public Socket createSocket(HttpParams params) throws IOException {
        return this.factory.createSocket();
    }

    public boolean isSecure(Socket sock) throws IllegalArgumentException {
        return this.factory.isSecure(sock);
    }

    public SocketFactory getFactory() {
        return this.factory;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof SchemeSocketFactoryAdaptor) {
            return this.factory.equals(((SchemeSocketFactoryAdaptor) obj).factory);
        }
        return this.factory.equals(obj);
    }

    public int hashCode() {
        return this.factory.hashCode();
    }
}
