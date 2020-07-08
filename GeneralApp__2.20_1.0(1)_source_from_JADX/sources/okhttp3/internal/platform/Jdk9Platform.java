package okhttp3.internal.platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J(\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\tH\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0010\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, mo24952d2 = {"Lokhttp3/internal/platform/Jdk9Platform;", "Lokhttp3/internal/platform/Platform;", "setProtocolMethod", "Ljava/lang/reflect/Method;", "getProtocolMethod", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getSelectedProtocol", "socket", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "Companion", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: Jdk9Platform.kt */
public final class Jdk9Platform extends Platform {
    public static final Companion Companion = new Companion(null);
    public final Method getProtocolMethod;
    public final Method setProtocolMethod;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¨\u0006\u0005"}, mo24952d2 = {"Lokhttp3/internal/platform/Jdk9Platform$Companion;", "", "()V", "buildIfSupported", "Lokhttp3/internal/platform/Jdk9Platform;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Jdk9Platform.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final Jdk9Platform buildIfSupported() {
            try {
                Method setProtocolMethod = SSLParameters.class.getMethod("setApplicationProtocols", new Class[]{String[].class});
                Method getProtocolMethod = SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]);
                Intrinsics.checkExpressionValueIsNotNull(setProtocolMethod, "setProtocolMethod");
                Intrinsics.checkExpressionValueIsNotNull(getProtocolMethod, "getProtocolMethod");
                return new Jdk9Platform(setProtocolMethod, getProtocolMethod);
            } catch (NoSuchMethodException e) {
                return null;
            }
        }
    }

    public Jdk9Platform(Method setProtocolMethod2, Method getProtocolMethod2) {
        Intrinsics.checkParameterIsNotNull(setProtocolMethod2, "setProtocolMethod");
        Intrinsics.checkParameterIsNotNull(getProtocolMethod2, "getProtocolMethod");
        this.setProtocolMethod = setProtocolMethod2;
        this.getProtocolMethod = getProtocolMethod2;
    }

    public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<? extends Protocol> protocols) {
        String str = "failed to set SSL parameters";
        Intrinsics.checkParameterIsNotNull(sslSocket, "sslSocket");
        Intrinsics.checkParameterIsNotNull(protocols, "protocols");
        try {
            SSLParameters sslParameters = sslSocket.getSSLParameters();
            List names = Platform.Companion.alpnProtocolNames(protocols);
            Method method = this.setProtocolMethod;
            Object[] objArr = new Object[1];
            Object[] array = names.toArray(new String[0]);
            if (array != null) {
                objArr[0] = array;
                method.invoke(sslParameters, objArr);
                sslSocket.setSSLParameters(sslParameters);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        } catch (IllegalAccessException e) {
            throw new AssertionError(str, e);
        } catch (InvocationTargetException e2) {
            throw new AssertionError(str, e2);
        }
    }

    public String getSelectedProtocol(SSLSocket socket) {
        String str = "failed to get ALPN selected protocol";
        Intrinsics.checkParameterIsNotNull(socket, "socket");
        try {
            String protocol = (String) this.getProtocolMethod.invoke(socket, new Object[0]);
            if (protocol != null) {
                if (!Intrinsics.areEqual((Object) protocol, (Object) "")) {
                    return protocol;
                }
            }
            return null;
        } catch (IllegalAccessException e) {
            throw new AssertionError(str, e);
        } catch (InvocationTargetException e2) {
            throw new AssertionError(str, e2);
        }
    }

    public X509TrustManager trustManager(SSLSocketFactory sslSocketFactory) {
        Intrinsics.checkParameterIsNotNull(sslSocketFactory, "sslSocketFactory");
        throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on JDK 9+");
    }
}
