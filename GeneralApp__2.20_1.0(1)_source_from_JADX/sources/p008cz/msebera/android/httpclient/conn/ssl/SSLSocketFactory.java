package p008cz.msebera.android.httpclient.conn.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.conn.ConnectTimeoutException;
import p008cz.msebera.android.httpclient.conn.HttpInetSocketAddress;
import p008cz.msebera.android.httpclient.conn.scheme.HostNameResolver;
import p008cz.msebera.android.httpclient.conn.scheme.LayeredSchemeSocketFactory;
import p008cz.msebera.android.httpclient.conn.scheme.LayeredSocketFactory;
import p008cz.msebera.android.httpclient.conn.scheme.SchemeLayeredSocketFactory;
import p008cz.msebera.android.httpclient.conn.socket.LayeredConnectionSocketFactory;
import p008cz.msebera.android.httpclient.params.HttpConnectionParams;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;
import p008cz.msebera.android.httpclient.util.TextUtils;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.conn.ssl.SSLSocketFactory */
public class SSLSocketFactory implements LayeredConnectionSocketFactory, SchemeLayeredSocketFactory, LayeredSchemeSocketFactory, LayeredSocketFactory {
    public static final X509HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER = new AllowAllHostnameVerifier();
    public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = new BrowserCompatHostnameVerifier();
    public static final String SSL = "SSL";
    public static final String SSLV2 = "SSLv2";
    public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER = new StrictHostnameVerifier();
    public static final String TLS = "TLS";
    private volatile X509HostnameVerifier hostnameVerifier;
    private final HostNameResolver nameResolver;
    private final javax.net.ssl.SSLSocketFactory socketfactory;
    private final String[] supportedCipherSuites;
    private final String[] supportedProtocols;

    public static SSLSocketFactory getSocketFactory() throws SSLInitializationException {
        return new SSLSocketFactory(SSLContexts.createDefault(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    private static String[] split(String s) {
        if (TextUtils.isBlank(s)) {
            return null;
        }
        return s.split(" *, *");
    }

    public static SSLSocketFactory getSystemSocketFactory() throws SSLInitializationException {
        return new SSLSocketFactory((javax.net.ssl.SSLSocketFactory) javax.net.ssl.SSLSocketFactory.getDefault(), split(System.getProperty("https.protocols")), split(System.getProperty("https.cipherSuites")), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public SSLSocketFactory(String algorithm, KeyStore keystore, String keyPassword, KeyStore truststore, SecureRandom random, HostNameResolver nameResolver2) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        this(SSLContexts.custom().useProtocol(algorithm).setSecureRandom(random).loadKeyMaterial(keystore, keyPassword != null ? keyPassword.toCharArray() : null).loadTrustMaterial(truststore).build(), nameResolver2);
    }

    public SSLSocketFactory(String algorithm, KeyStore keystore, String keyPassword, KeyStore truststore, SecureRandom random, TrustStrategy trustStrategy, X509HostnameVerifier hostnameVerifier2) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        this(SSLContexts.custom().useProtocol(algorithm).setSecureRandom(random).loadKeyMaterial(keystore, keyPassword != null ? keyPassword.toCharArray() : null).loadTrustMaterial(truststore, trustStrategy).build(), hostnameVerifier2);
    }

    public SSLSocketFactory(String algorithm, KeyStore keystore, String keyPassword, KeyStore truststore, SecureRandom random, X509HostnameVerifier hostnameVerifier2) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        this(SSLContexts.custom().useProtocol(algorithm).setSecureRandom(random).loadKeyMaterial(keystore, keyPassword != null ? keyPassword.toCharArray() : null).loadTrustMaterial(truststore).build(), hostnameVerifier2);
    }

    public SSLSocketFactory(KeyStore keystore, String keystorePassword, KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        this(SSLContexts.custom().loadKeyMaterial(keystore, keystorePassword != null ? keystorePassword.toCharArray() : null).loadTrustMaterial(truststore).build(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public SSLSocketFactory(KeyStore keystore, String keystorePassword) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        this(SSLContexts.custom().loadKeyMaterial(keystore, keystorePassword != null ? keystorePassword.toCharArray() : null).build(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public SSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        this(SSLContexts.custom().loadTrustMaterial(truststore).build(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public SSLSocketFactory(TrustStrategy trustStrategy, X509HostnameVerifier hostnameVerifier2) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        this(SSLContexts.custom().loadTrustMaterial(null, trustStrategy).build(), hostnameVerifier2);
    }

    public SSLSocketFactory(TrustStrategy trustStrategy) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        this(SSLContexts.custom().loadTrustMaterial(null, trustStrategy).build(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public SSLSocketFactory(SSLContext sslContext) {
        this(sslContext, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public SSLSocketFactory(SSLContext sslContext, HostNameResolver nameResolver2) {
        this.socketfactory = sslContext.getSocketFactory();
        this.hostnameVerifier = BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
        this.nameResolver = nameResolver2;
        this.supportedProtocols = null;
        this.supportedCipherSuites = null;
    }

    public SSLSocketFactory(SSLContext sslContext, X509HostnameVerifier hostnameVerifier2) {
        this(((SSLContext) Args.notNull(sslContext, "SSL context")).getSocketFactory(), (String[]) null, (String[]) null, hostnameVerifier2);
    }

    public SSLSocketFactory(SSLContext sslContext, String[] supportedProtocols2, String[] supportedCipherSuites2, X509HostnameVerifier hostnameVerifier2) {
        this(((SSLContext) Args.notNull(sslContext, "SSL context")).getSocketFactory(), supportedProtocols2, supportedCipherSuites2, hostnameVerifier2);
    }

    public SSLSocketFactory(javax.net.ssl.SSLSocketFactory socketfactory2, X509HostnameVerifier hostnameVerifier2) {
        this(socketfactory2, (String[]) null, (String[]) null, hostnameVerifier2);
    }

    public SSLSocketFactory(javax.net.ssl.SSLSocketFactory socketfactory2, String[] supportedProtocols2, String[] supportedCipherSuites2, X509HostnameVerifier hostnameVerifier2) {
        this.socketfactory = (javax.net.ssl.SSLSocketFactory) Args.notNull(socketfactory2, "SSL socket factory");
        this.supportedProtocols = supportedProtocols2;
        this.supportedCipherSuites = supportedCipherSuites2;
        this.hostnameVerifier = hostnameVerifier2 != null ? hostnameVerifier2 : BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
        this.nameResolver = null;
    }

    public Socket createSocket(HttpParams params) throws IOException {
        return createSocket((HttpContext) null);
    }

    public Socket createSocket() throws IOException {
        return createSocket((HttpContext) null);
    }

    public Socket connectSocket(Socket socket, InetSocketAddress remoteAddress, InetSocketAddress localAddress, HttpParams params) throws IOException, UnknownHostException, ConnectTimeoutException {
        HttpHost host;
        Args.notNull(remoteAddress, "Remote address");
        Args.notNull(params, "HTTP parameters");
        if (remoteAddress instanceof HttpInetSocketAddress) {
            host = ((HttpInetSocketAddress) remoteAddress).getHttpHost();
        } else {
            host = new HttpHost(remoteAddress.getHostName(), remoteAddress.getPort(), "https");
        }
        int socketTimeout = HttpConnectionParams.getSoTimeout(params);
        int connectTimeout = HttpConnectionParams.getConnectionTimeout(params);
        socket.setSoTimeout(socketTimeout);
        return connectSocket(connectTimeout, socket, host, remoteAddress, localAddress, (HttpContext) null);
    }

    public boolean isSecure(Socket sock) throws IllegalArgumentException {
        Args.notNull(sock, "Socket");
        Asserts.check(sock instanceof SSLSocket, "Socket not created by this factory");
        Asserts.check(!sock.isClosed(), "Socket is closed");
        return true;
    }

    public Socket createLayeredSocket(Socket socket, String host, int port, HttpParams params) throws IOException, UnknownHostException {
        return createLayeredSocket(socket, host, port, (HttpContext) null);
    }

    public Socket createLayeredSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
        return createLayeredSocket(socket, host, port, (HttpContext) null);
    }

    public void setHostnameVerifier(X509HostnameVerifier hostnameVerifier2) {
        Args.notNull(hostnameVerifier2, "Hostname verifier");
        this.hostnameVerifier = hostnameVerifier2;
    }

    public X509HostnameVerifier getHostnameVerifier() {
        return this.hostnameVerifier;
    }

    public Socket connectSocket(Socket socket, String host, int port, InetAddress local, int localPort, HttpParams params) throws IOException, UnknownHostException, ConnectTimeoutException {
        InetAddress remote;
        HostNameResolver hostNameResolver = this.nameResolver;
        if (hostNameResolver != null) {
            remote = hostNameResolver.resolve(host);
        } else {
            remote = InetAddress.getByName(host);
        }
        InetSocketAddress localAddress = null;
        if (local != null || localPort > 0) {
            localAddress = new InetSocketAddress(local, localPort > 0 ? localPort : 0);
        }
        return connectSocket(socket, new HttpInetSocketAddress(new HttpHost(host, port), remote, port), localAddress, params);
    }

    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
        return createLayeredSocket(socket, host, port, autoClose);
    }

    /* access modifiers changed from: protected */
    public void prepareSocket(SSLSocket socket) throws IOException {
    }

    private void internalPrepareSocket(SSLSocket socket) throws IOException {
        String[] strArr = this.supportedProtocols;
        if (strArr != null) {
            socket.setEnabledProtocols(strArr);
        }
        String[] strArr2 = this.supportedCipherSuites;
        if (strArr2 != null) {
            socket.setEnabledCipherSuites(strArr2);
        }
        prepareSocket(socket);
    }

    public Socket createSocket(HttpContext context) throws IOException {
        SSLSocket sock = (SSLSocket) this.socketfactory.createSocket();
        internalPrepareSocket(sock);
        return sock;
    }

    public Socket connectSocket(int connectTimeout, Socket socket, HttpHost host, InetSocketAddress remoteAddress, InetSocketAddress localAddress, HttpContext context) throws IOException {
        Args.notNull(host, "HTTP host");
        Args.notNull(remoteAddress, "Remote address");
        Socket sock = socket != null ? socket : createSocket(context);
        if (localAddress != null) {
            sock.bind(localAddress);
        }
        try {
            sock.connect(remoteAddress, connectTimeout);
            if (!(sock instanceof SSLSocket)) {
                return createLayeredSocket(sock, host.getHostName(), remoteAddress.getPort(), context);
            }
            SSLSocket sslsock = (SSLSocket) sock;
            sslsock.startHandshake();
            verifyHostname(sslsock, host.getHostName());
            return sock;
        } catch (IOException ex) {
            try {
                sock.close();
            } catch (IOException e) {
            }
            throw ex;
        }
    }

    public Socket createLayeredSocket(Socket socket, String target, int port, HttpContext context) throws IOException {
        SSLSocket sslsock = (SSLSocket) this.socketfactory.createSocket(socket, target, port, true);
        internalPrepareSocket(sslsock);
        sslsock.startHandshake();
        verifyHostname(sslsock, target);
        return sslsock;
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
