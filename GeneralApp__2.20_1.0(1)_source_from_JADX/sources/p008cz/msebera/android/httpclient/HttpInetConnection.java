package p008cz.msebera.android.httpclient;

import java.net.InetAddress;

/* renamed from: cz.msebera.android.httpclient.HttpInetConnection */
public interface HttpInetConnection extends HttpConnection {
    InetAddress getLocalAddress();

    int getLocalPort();

    InetAddress getRemoteAddress();

    int getRemotePort();
}
