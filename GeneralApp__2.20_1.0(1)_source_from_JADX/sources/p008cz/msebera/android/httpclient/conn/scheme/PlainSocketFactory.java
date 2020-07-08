package p008cz.msebera.android.httpclient.conn.scheme;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import p008cz.msebera.android.httpclient.conn.ConnectTimeoutException;
import p008cz.msebera.android.httpclient.params.HttpConnectionParams;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.conn.scheme.PlainSocketFactory */
public class PlainSocketFactory implements SocketFactory, SchemeSocketFactory {
    private final HostNameResolver nameResolver;

    public static PlainSocketFactory getSocketFactory() {
        return new PlainSocketFactory();
    }

    @Deprecated
    public PlainSocketFactory(HostNameResolver nameResolver2) {
        this.nameResolver = nameResolver2;
    }

    public PlainSocketFactory() {
        this.nameResolver = null;
    }

    public Socket createSocket(HttpParams params) {
        return new Socket();
    }

    public Socket createSocket() {
        return new Socket();
    }

    public Socket connectSocket(Socket socket, InetSocketAddress remoteAddress, InetSocketAddress localAddress, HttpParams params) throws IOException, ConnectTimeoutException {
        Args.notNull(remoteAddress, "Remote address");
        Args.notNull(params, "HTTP parameters");
        Socket sock = socket;
        if (sock == null) {
            sock = createSocket();
        }
        if (localAddress != null) {
            sock.setReuseAddress(HttpConnectionParams.getSoReuseaddr(params));
            sock.bind(localAddress);
        }
        int connTimeout = HttpConnectionParams.getConnectionTimeout(params);
        try {
            sock.setSoTimeout(HttpConnectionParams.getSoTimeout(params));
            sock.connect(remoteAddress, connTimeout);
            return sock;
        } catch (SocketTimeoutException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Connect to ");
            sb.append(remoteAddress);
            sb.append(" timed out");
            throw new ConnectTimeoutException(sb.toString());
        }
    }

    public final boolean isSecure(Socket sock) {
        return false;
    }

    @Deprecated
    public Socket connectSocket(Socket socket, String host, int port, InetAddress localAddress, int localPort, HttpParams params) throws IOException, UnknownHostException, ConnectTimeoutException {
        InetAddress remoteAddress;
        InetSocketAddress local = null;
        if (localAddress != null || localPort > 0) {
            local = new InetSocketAddress(localAddress, localPort > 0 ? localPort : 0);
        }
        HostNameResolver hostNameResolver = this.nameResolver;
        if (hostNameResolver != null) {
            remoteAddress = hostNameResolver.resolve(host);
        } else {
            remoteAddress = InetAddress.getByName(host);
        }
        return connectSocket(socket, new InetSocketAddress(remoteAddress, port), local, params);
    }
}
