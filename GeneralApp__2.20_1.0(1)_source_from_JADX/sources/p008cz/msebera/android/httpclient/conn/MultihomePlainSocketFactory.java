package p008cz.msebera.android.httpclient.conn;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import p008cz.msebera.android.httpclient.conn.scheme.SocketFactory;
import p008cz.msebera.android.httpclient.params.HttpConnectionParams;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.conn.MultihomePlainSocketFactory */
public final class MultihomePlainSocketFactory implements SocketFactory {
    private static final MultihomePlainSocketFactory DEFAULT_FACTORY = new MultihomePlainSocketFactory();

    public static MultihomePlainSocketFactory getSocketFactory() {
        return DEFAULT_FACTORY;
    }

    private MultihomePlainSocketFactory() {
    }

    public Socket createSocket() {
        return new Socket();
    }

    public Socket connectSocket(Socket socket, String host, int port, InetAddress localAddress, int localPort, HttpParams params) throws IOException {
        InetAddress inetAddress = localAddress;
        Args.notNull(host, "Target host");
        Args.notNull(params, "HTTP parameters");
        Socket sock = socket;
        if (sock == null) {
            sock = createSocket();
        }
        if (inetAddress != null || localPort > 0) {
            sock.bind(new InetSocketAddress(inetAddress, localPort > 0 ? localPort : 0));
        }
        int timeout = HttpConnectionParams.getConnectionTimeout(params);
        InetAddress[] inetadrs = InetAddress.getAllByName(host);
        List<InetAddress> addresses = new ArrayList<>(inetadrs.length);
        addresses.addAll(Arrays.asList(inetadrs));
        Collections.shuffle(addresses);
        Iterator it = addresses.iterator();
        Throwable th = null;
        Socket sock2 = sock;
        while (true) {
            if (!it.hasNext()) {
                int i = port;
                break;
            }
            InetAddress remoteAddress = (InetAddress) it.next();
            try {
                try {
                    sock2.connect(new InetSocketAddress(remoteAddress, port), timeout);
                    break;
                } catch (SocketTimeoutException e) {
                } catch (IOException e2) {
                    ex = e2;
                }
            } catch (SocketTimeoutException e3) {
                int i2 = port;
                StringBuilder sb = new StringBuilder();
                sb.append("Connect to ");
                sb.append(remoteAddress);
                sb.append(" timed out");
                throw new ConnectTimeoutException(sb.toString());
            } catch (IOException e4) {
                ex = e4;
                int i3 = port;
            }
            sock2 = new Socket();
            th = ex;
        }
        if (th == null) {
            return sock2;
        }
        throw th;
    }

    public final boolean isSecure(Socket sock) throws IllegalArgumentException {
        Args.notNull(sock, "Socket");
        Asserts.check(!sock.isClosed(), "Socket is closed");
        return false;
    }
}
