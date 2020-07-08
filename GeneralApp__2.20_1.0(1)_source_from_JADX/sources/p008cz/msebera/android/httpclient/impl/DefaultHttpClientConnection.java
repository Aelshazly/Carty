package p008cz.msebera.android.httpclient.impl;

import java.io.IOException;
import java.net.Socket;
import p008cz.msebera.android.httpclient.params.CoreConnectionPNames;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.DefaultHttpClientConnection */
public class DefaultHttpClientConnection extends SocketHttpClientConnection {
    public void bind(Socket socket, HttpParams params) throws IOException {
        Args.notNull(socket, "Socket");
        Args.notNull(params, "HTTP parameters");
        assertNotOpen();
        boolean z = true;
        socket.setTcpNoDelay(params.getBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true));
        socket.setSoTimeout(params.getIntParameter(CoreConnectionPNames.SO_TIMEOUT, 0));
        socket.setKeepAlive(params.getBooleanParameter(CoreConnectionPNames.SO_KEEPALIVE, false));
        int linger = params.getIntParameter(CoreConnectionPNames.SO_LINGER, -1);
        if (linger >= 0) {
            if (linger <= 0) {
                z = false;
            }
            socket.setSoLinger(z, linger);
        }
        super.bind(socket, params);
    }
}
