package p008cz.msebera.android.httpclient.impl.pool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import p008cz.msebera.android.httpclient.HttpClientConnection;
import p008cz.msebera.android.httpclient.HttpConnectionFactory;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.config.ConnectionConfig;
import p008cz.msebera.android.httpclient.config.SocketConfig;
import p008cz.msebera.android.httpclient.impl.DefaultBHttpClientConnection;
import p008cz.msebera.android.httpclient.impl.DefaultBHttpClientConnectionFactory;
import p008cz.msebera.android.httpclient.params.CoreConnectionPNames;
import p008cz.msebera.android.httpclient.params.HttpParamConfig;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.pool.ConnFactory;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.pool.BasicConnFactory */
public class BasicConnFactory implements ConnFactory<HttpHost, HttpClientConnection> {
    private final HttpConnectionFactory<? extends HttpClientConnection> connFactory;
    private final int connectTimeout;
    private final SocketFactory plainfactory;
    private final SocketConfig sconfig;
    private final SSLSocketFactory sslfactory;

    @Deprecated
    public BasicConnFactory(SSLSocketFactory sslfactory2, HttpParams params) {
        Args.notNull(params, "HTTP params");
        this.plainfactory = null;
        this.sslfactory = sslfactory2;
        this.connectTimeout = params.getIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 0);
        this.sconfig = HttpParamConfig.getSocketConfig(params);
        this.connFactory = new DefaultBHttpClientConnectionFactory(HttpParamConfig.getConnectionConfig(params));
    }

    @Deprecated
    public BasicConnFactory(HttpParams params) {
        this((SSLSocketFactory) null, params);
    }

    public BasicConnFactory(SocketFactory plainfactory2, SSLSocketFactory sslfactory2, int connectTimeout2, SocketConfig sconfig2, ConnectionConfig cconfig) {
        this.plainfactory = plainfactory2;
        this.sslfactory = sslfactory2;
        this.connectTimeout = connectTimeout2;
        this.sconfig = sconfig2 != null ? sconfig2 : SocketConfig.DEFAULT;
        this.connFactory = new DefaultBHttpClientConnectionFactory(cconfig != null ? cconfig : ConnectionConfig.DEFAULT);
    }

    public BasicConnFactory(int connectTimeout2, SocketConfig sconfig2, ConnectionConfig cconfig) {
        this(null, null, connectTimeout2, sconfig2, cconfig);
    }

    public BasicConnFactory(SocketConfig sconfig2, ConnectionConfig cconfig) {
        this(null, null, 0, sconfig2, cconfig);
    }

    public BasicConnFactory() {
        this(null, null, 0, SocketConfig.DEFAULT, ConnectionConfig.DEFAULT);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public HttpClientConnection create(Socket socket, HttpParams params) throws IOException {
        DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(params.getIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, 8192));
        conn.bind(socket);
        return conn;
    }

    public HttpClientConnection create(HttpHost host) throws IOException {
        String scheme = host.getSchemeName();
        Socket socket = null;
        String str = HttpHost.DEFAULT_SCHEME_NAME;
        if (str.equalsIgnoreCase(scheme)) {
            SocketFactory socketFactory = this.plainfactory;
            socket = socketFactory != null ? socketFactory.createSocket() : new Socket();
        }
        String str2 = "https";
        if (str2.equalsIgnoreCase(scheme)) {
            SocketFactory socketFactory2 = this.sslfactory;
            if (socketFactory2 == null) {
                socketFactory2 = SSLSocketFactory.getDefault();
            }
            socket = socketFactory2.createSocket();
        }
        if (socket != null) {
            String hostname = host.getHostName();
            int port = host.getPort();
            if (port == -1) {
                if (host.getSchemeName().equalsIgnoreCase(str)) {
                    port = 80;
                } else if (host.getSchemeName().equalsIgnoreCase(str2)) {
                    port = 443;
                }
            }
            socket.setSoTimeout(this.sconfig.getSoTimeout());
            socket.setTcpNoDelay(this.sconfig.isTcpNoDelay());
            int linger = this.sconfig.getSoLinger();
            if (linger >= 0) {
                socket.setSoLinger(linger > 0, linger);
            }
            socket.setKeepAlive(this.sconfig.isSoKeepAlive());
            socket.connect(new InetSocketAddress(hostname, port), this.connectTimeout);
            return (HttpClientConnection) this.connFactory.createConnection(socket);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(scheme);
        sb.append(" scheme is not supported");
        throw new IOException(sb.toString());
    }
}
