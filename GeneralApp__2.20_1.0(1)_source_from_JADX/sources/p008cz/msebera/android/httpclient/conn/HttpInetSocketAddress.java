package p008cz.msebera.android.httpclient.conn;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.conn.HttpInetSocketAddress */
public class HttpInetSocketAddress extends InetSocketAddress {
    private static final long serialVersionUID = -6650701828361907957L;
    private final HttpHost httphost;

    public HttpInetSocketAddress(HttpHost httphost2, InetAddress addr, int port) {
        super(addr, port);
        Args.notNull(httphost2, "HTTP host");
        this.httphost = httphost2;
    }

    public HttpHost getHttpHost() {
        return this.httphost;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.httphost.getHostName());
        sb.append(":");
        sb.append(getPort());
        return sb.toString();
    }
}
