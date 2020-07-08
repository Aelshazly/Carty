package p008cz.msebera.android.httpclient.conn.ssl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.conn.socket.LayeredConnectionSocketFactory;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.TextUtils;

/* renamed from: cz.msebera.android.httpclient.conn.ssl.SSLConnectionSocketFactory */
public class SSLConnectionSocketFactory implements LayeredConnectionSocketFactory {
    public static final X509HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER = new AllowAllHostnameVerifier();
    public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = new BrowserCompatHostnameVerifier();
    public static final String SSL = "SSL";
    public static final String SSLV2 = "SSLv2";
    public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER = new StrictHostnameVerifier();
    public static final String TLS = "TLS";
    private final X509HostnameVerifier hostnameVerifier;
    private final SSLSocketFactory socketfactory;
    private final String[] supportedCipherSuites;
    private final String[] supportedProtocols;

    public static SSLConnectionSocketFactory getSocketFactory() throws SSLInitializationException {
        return new SSLConnectionSocketFactory(SSLContexts.createDefault(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    private static String[] split(String s) {
        if (TextUtils.isBlank(s)) {
            return null;
        }
        return s.split(" *, *");
    }

    public static SSLConnectionSocketFactory getSystemSocketFactory() throws SSLInitializationException {
        return new SSLConnectionSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault(), split(System.getProperty("https.protocols")), split(System.getProperty("https.cipherSuites")), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public SSLConnectionSocketFactory(SSLContext sslContext) {
        this(sslContext, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public SSLConnectionSocketFactory(SSLContext sslContext, X509HostnameVerifier hostnameVerifier2) {
        this(((SSLContext) Args.notNull(sslContext, "SSL context")).getSocketFactory(), (String[]) null, (String[]) null, hostnameVerifier2);
    }

    public SSLConnectionSocketFactory(SSLContext sslContext, String[] supportedProtocols2, String[] supportedCipherSuites2, X509HostnameVerifier hostnameVerifier2) {
        this(((SSLContext) Args.notNull(sslContext, "SSL context")).getSocketFactory(), supportedProtocols2, supportedCipherSuites2, hostnameVerifier2);
    }

    public SSLConnectionSocketFactory(SSLSocketFactory socketfactory2, X509HostnameVerifier hostnameVerifier2) {
        this(socketfactory2, (String[]) null, (String[]) null, hostnameVerifier2);
    }

    public SSLConnectionSocketFactory(SSLSocketFactory socketfactory2, String[] supportedProtocols2, String[] supportedCipherSuites2, X509HostnameVerifier hostnameVerifier2) {
        this.socketfactory = (SSLSocketFactory) Args.notNull(socketfactory2, "SSL socket factory");
        this.supportedProtocols = supportedProtocols2;
        this.supportedCipherSuites = supportedCipherSuites2;
        this.hostnameVerifier = hostnameVerifier2 != null ? hostnameVerifier2 : BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
    }

    /* access modifiers changed from: protected */
    public void prepareSocket(SSLSocket socket) throws IOException {
    }

    public Socket createSocket(HttpContext context) throws IOException {
        return SocketFactory.getDefault().createSocket();
    }

    public Socket connectSocket(int connectTimeout, Socket socket, HttpHost host, InetSocketAddress remoteAddress, InetSocketAddress localAddress, HttpContext context) throws IOException {
        Args.notNull(host, "HTTP host");
        Args.notNull(remoteAddress, "Remote address");
        Socket sock = socket != null ? socket : createSocket(context);
        if (localAddress != null) {
            sock.bind(localAddress);
        }
        if (connectTimeout > 0) {
            try {
                if (sock.getSoTimeout() == 0) {
                    sock.setSoTimeout(connectTimeout);
                }
            } catch (IOException ex) {
                try {
                    sock.close();
                } catch (IOException e) {
                }
                throw ex;
            }
        }
        sock.connect(remoteAddress, connectTimeout);
        if (!(sock instanceof SSLSocket)) {
            return createLayeredSocket(sock, host.getHostName(), remoteAddress.getPort(), context);
        }
        SSLSocket sslsock = (SSLSocket) sock;
        sslsock.startHandshake();
        verifyHostname(sslsock, host.getHostName());
        return sock;
    }

    public Socket createLayeredSocket(Socket socket, String target, int port, HttpContext context) throws IOException {
        SSLSocket sslsock = (SSLSocket) this.socketfactory.createSocket(socket, target, port, true);
        String[] strArr = this.supportedProtocols;
        if (strArr != null) {
            sslsock.setEnabledProtocols(strArr);
        } else {
            String[] allProtocols = sslsock.getSupportedProtocols();
            List<String> enabledProtocols = new ArrayList<>(allProtocols.length);
            for (String protocol : allProtocols) {
                if (!protocol.startsWith("SSL")) {
                    enabledProtocols.add(protocol);
                }
            }
            sslsock.setEnabledProtocols((String[]) enabledProtocols.toArray(new String[enabledProtocols.size()]));
        }
        String[] allProtocols2 = this.supportedCipherSuites;
        if (allProtocols2 != null) {
            sslsock.setEnabledCipherSuites(allProtocols2);
        }
        prepareSocket(sslsock);
        sslsock.startHandshake();
        verifyHostname(sslsock, target);
        return sslsock;
    }

    /* access modifiers changed from: 0000 */
    public X509HostnameVerifier getHostnameVerifier() {
        return this.hostnameVerifier;
    }

    private void verifyHostname(SSLSocket sslsock, String hostname) throws IOException {
        try {
            this.hostnameVerifier.verify(hostname, sslsock);
        } catch (IOException iox) {
            try {
                sslsock.close();
            } catch (Exception e) {
            }
            throw iox;
        }
    }
}
