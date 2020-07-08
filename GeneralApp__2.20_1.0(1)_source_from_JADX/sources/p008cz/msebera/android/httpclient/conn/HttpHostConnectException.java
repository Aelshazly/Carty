package p008cz.msebera.android.httpclient.conn;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.util.Arrays;
import p008cz.msebera.android.httpclient.HttpHost;

/* renamed from: cz.msebera.android.httpclient.conn.HttpHostConnectException */
public class HttpHostConnectException extends ConnectException {
    private static final long serialVersionUID = -3194482710275220224L;
    private final HttpHost host;

    @Deprecated
    public HttpHostConnectException(HttpHost host2, ConnectException cause) {
        this(cause, host2, null);
    }

    public HttpHostConnectException(IOException cause, HttpHost host2, InetAddress... remoteAddresses) {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append("Connect to ");
        sb.append(host2 != null ? host2.toHostString() : "remote host");
        if (remoteAddresses == null || remoteAddresses.length <= 0) {
            str = "";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(" ");
            sb2.append(Arrays.asList(remoteAddresses));
            str = sb2.toString();
        }
        sb.append(str);
        if (cause == null || cause.getMessage() == null) {
            str2 = " refused";
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(" failed: ");
            sb3.append(cause.getMessage());
            str2 = sb3.toString();
        }
        sb.append(str2);
        super(sb.toString());
        this.host = host2;
        initCause(cause);
    }

    public HttpHost getHost() {
        return this.host;
    }
}
