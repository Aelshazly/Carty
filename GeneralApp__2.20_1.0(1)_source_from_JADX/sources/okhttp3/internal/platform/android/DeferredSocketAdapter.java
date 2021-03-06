package okhttp3.internal.platform.android;

import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Protocol;
import okhttp3.internal.platform.Platform;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0011\u001a\u00020\u000bH\u0002J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\u0013\u001a\u00020\u0007H\u0016J\u0010\u0010\u0014\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, mo24952d2 = {"Lokhttp3/internal/platform/android/DeferredSocketAdapter;", "Lokhttp3/internal/platform/android/SocketAdapter;", "socketPackage", "", "(Ljava/lang/String;)V", "delegate", "initialized", "", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "protocols", "", "Lokhttp3/Protocol;", "getDelegate", "actualSSLSocketClass", "getSelectedProtocol", "isSupported", "matchesSocket", "matchesSocketFactory", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: DeferredSocketAdapter.kt */
public final class DeferredSocketAdapter implements SocketAdapter {
    private SocketAdapter delegate;
    private boolean initialized;
    private final String socketPackage;

    public DeferredSocketAdapter(String socketPackage2) {
        Intrinsics.checkParameterIsNotNull(socketPackage2, "socketPackage");
        this.socketPackage = socketPackage2;
    }

    public boolean isSupported() {
        return true;
    }

    public boolean matchesSocket(SSLSocket sslSocket) {
        Intrinsics.checkParameterIsNotNull(sslSocket, "sslSocket");
        String name = sslSocket.getClass().getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "sslSocket.javaClass.name");
        return StringsKt.startsWith$default(name, this.socketPackage, false, 2, null);
    }

    public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<? extends Protocol> protocols) {
        Intrinsics.checkParameterIsNotNull(sslSocket, "sslSocket");
        Intrinsics.checkParameterIsNotNull(protocols, "protocols");
        SocketAdapter delegate2 = getDelegate(sslSocket);
        if (delegate2 != null) {
            delegate2.configureTlsExtensions(sslSocket, hostname, protocols);
        }
    }

    public String getSelectedProtocol(SSLSocket sslSocket) {
        Intrinsics.checkParameterIsNotNull(sslSocket, "sslSocket");
        SocketAdapter delegate2 = getDelegate(sslSocket);
        if (delegate2 != null) {
            return delegate2.getSelectedProtocol(sslSocket);
        }
        return null;
    }

    private final synchronized SocketAdapter getDelegate(SSLSocket actualSSLSocketClass) {
        if (!this.initialized) {
            try {
                Class possibleClass = actualSSLSocketClass.getClass();
                while (true) {
                    String name = possibleClass.getName();
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.socketPackage);
                    sb.append(".OpenSSLSocketImpl");
                    if (!(!Intrinsics.areEqual((Object) name, (Object) sb.toString()))) {
                        this.delegate = new AndroidSocketAdapter(possibleClass);
                        break;
                    }
                    Class superclass = possibleClass.getSuperclass();
                    Intrinsics.checkExpressionValueIsNotNull(superclass, "possibleClass.superclass");
                    possibleClass = superclass;
                    if (possibleClass == null) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("No OpenSSLSocketImpl superclass of socket of type ");
                        sb2.append(actualSSLSocketClass);
                        throw new AssertionError(sb2.toString());
                    }
                }
            } catch (Exception e) {
                Platform platform = Platform.Companion.get();
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Failed to initialize DeferredSocketAdapter ");
                sb3.append(this.socketPackage);
                platform.log(5, sb3.toString(), e);
            }
            this.initialized = true;
        }
        return this.delegate;
    }

    public X509TrustManager trustManager(SSLSocketFactory sslSocketFactory) {
        Intrinsics.checkParameterIsNotNull(sslSocketFactory, "sslSocketFactory");
        return null;
    }

    public boolean matchesSocketFactory(SSLSocketFactory sslSocketFactory) {
        Intrinsics.checkParameterIsNotNull(sslSocketFactory, "sslSocketFactory");
        return false;
    }
}
