package okhttp3.internal.platform;

import androidx.core.p003os.EnvironmentCompat;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00172\u00020\u0001:\u0002\u0016\u0017B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007\u0012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0007¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J(\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0015\u001a\u00020\rH\u0016R\u0012\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, mo24952d2 = {"Lokhttp3/internal/platform/Jdk8WithJettyBootPlatform;", "Lokhttp3/internal/platform/Platform;", "putMethod", "Ljava/lang/reflect/Method;", "getMethod", "removeMethod", "clientProviderClass", "Ljava/lang/Class;", "serverProviderClass", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/Class;Ljava/lang/Class;)V", "afterHandshake", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "configureTlsExtensions", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getSelectedProtocol", "socket", "AlpnProvider", "Companion", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: Jdk8WithJettyBootPlatform.kt */
public final class Jdk8WithJettyBootPlatform extends Platform {
    public static final Companion Companion = new Companion(null);
    private final Class<?> clientProviderClass;
    private final Method getMethod;
    private final Method putMethod;
    private final Method removeMethod;
    private final Class<?> serverProviderClass;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J0\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0017H\u0002¢\u0006\u0002\u0010\u0018R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0019"}, mo24952d2 = {"Lokhttp3/internal/platform/Jdk8WithJettyBootPlatform$AlpnProvider;", "Ljava/lang/reflect/InvocationHandler;", "protocols", "", "", "(Ljava/util/List;)V", "selected", "getSelected$okhttp", "()Ljava/lang/String;", "setSelected$okhttp", "(Ljava/lang/String;)V", "unsupported", "", "getUnsupported$okhttp", "()Z", "setUnsupported$okhttp", "(Z)V", "invoke", "", "proxy", "method", "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Jdk8WithJettyBootPlatform.kt */
    private static final class AlpnProvider implements InvocationHandler {
        private final List<String> protocols;
        private String selected;
        private boolean unsupported;

        public AlpnProvider(List<String> protocols2) {
            Intrinsics.checkParameterIsNotNull(protocols2, "protocols");
            this.protocols = protocols2;
        }

        public final boolean getUnsupported$okhttp() {
            return this.unsupported;
        }

        public final void setUnsupported$okhttp(boolean z) {
            this.unsupported = z;
        }

        public final String getSelected$okhttp() {
            return this.selected;
        }

        public final void setSelected$okhttp(String str) {
            this.selected = str;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Intrinsics.checkParameterIsNotNull(proxy, "proxy");
            Intrinsics.checkParameterIsNotNull(method, Param.METHOD);
            Object[] callArgs = args != null ? args : new Object[0];
            String methodName = method.getName();
            Class returnType = method.getReturnType();
            if (Intrinsics.areEqual((Object) methodName, (Object) "supports") && Intrinsics.areEqual((Object) Boolean.TYPE, (Object) returnType)) {
                return Boolean.valueOf(true);
            }
            if (!Intrinsics.areEqual((Object) methodName, (Object) "unsupported") || !Intrinsics.areEqual((Object) Void.TYPE, (Object) returnType)) {
                if (Intrinsics.areEqual((Object) methodName, (Object) "protocols")) {
                    if (callArgs.length == 0) {
                        return this.protocols;
                    }
                }
                String str = "null cannot be cast to non-null type kotlin.String";
                if ((Intrinsics.areEqual((Object) methodName, (Object) "selectProtocol") || Intrinsics.areEqual((Object) methodName, (Object) "select")) && Intrinsics.areEqual((Object) String.class, (Object) returnType) && callArgs.length == 1 && (callArgs[0] instanceof List)) {
                    Object obj = callArgs[0];
                    if (obj != null) {
                        List peerProtocols = (List) obj;
                        int size = peerProtocols.size();
                        if (size >= 0) {
                            int i = 0;
                            while (true) {
                                Object obj2 = peerProtocols.get(i);
                                if (obj2 != null) {
                                    String protocol = (String) obj2;
                                    if (!this.protocols.contains(protocol)) {
                                        if (i == size) {
                                            break;
                                        }
                                        i++;
                                    } else {
                                        this.selected = protocol;
                                        return this.selected;
                                    }
                                } else {
                                    throw new TypeCastException(str);
                                }
                            }
                        }
                        this.selected = (String) this.protocols.get(0);
                        return this.selected;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<*>");
                } else if ((!Intrinsics.areEqual((Object) methodName, (Object) "protocolSelected") && !Intrinsics.areEqual((Object) methodName, (Object) "selected")) || callArgs.length != 1) {
                    return method.invoke(this, Arrays.copyOf(callArgs, callArgs.length));
                } else {
                    Object obj3 = callArgs[0];
                    if (obj3 != null) {
                        this.selected = (String) obj3;
                        return null;
                    }
                    throw new TypeCastException(str);
                }
            } else {
                this.unsupported = true;
                return null;
            }
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¨\u0006\u0005"}, mo24952d2 = {"Lokhttp3/internal/platform/Jdk8WithJettyBootPlatform$Companion;", "", "()V", "buildIfSupported", "Lokhttp3/internal/platform/Platform;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Jdk8WithJettyBootPlatform.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final Platform buildIfSupported() {
            String jvmVersion = System.getProperty("java.specification.version", EnvironmentCompat.MEDIA_UNKNOWN);
            try {
                Intrinsics.checkExpressionValueIsNotNull(jvmVersion, "jvmVersion");
                if (Integer.parseInt(jvmVersion) >= 9) {
                    return null;
                }
            } catch (NumberFormatException e) {
            }
            String alpnClassName = "org.eclipse.jetty.alpn.ALPN";
            try {
                Class alpnClass = Class.forName(alpnClassName, true, null);
                StringBuilder sb = new StringBuilder();
                sb.append(alpnClassName);
                sb.append("$Provider");
                Class providerClass = Class.forName(sb.toString(), true, null);
                StringBuilder sb2 = new StringBuilder();
                sb2.append(alpnClassName);
                sb2.append("$ClientProvider");
                Class clientProviderClass = Class.forName(sb2.toString(), true, null);
                StringBuilder sb3 = new StringBuilder();
                sb3.append(alpnClassName);
                sb3.append("$ServerProvider");
                Class serverProviderClass = Class.forName(sb3.toString(), true, null);
                Method putMethod = alpnClass.getMethod("put", new Class[]{SSLSocket.class, providerClass});
                Method getMethod = alpnClass.getMethod("get", new Class[]{SSLSocket.class});
                Method removeMethod = alpnClass.getMethod("remove", new Class[]{SSLSocket.class});
                Intrinsics.checkExpressionValueIsNotNull(putMethod, "putMethod");
                Intrinsics.checkExpressionValueIsNotNull(getMethod, "getMethod");
                Intrinsics.checkExpressionValueIsNotNull(removeMethod, "removeMethod");
                Intrinsics.checkExpressionValueIsNotNull(clientProviderClass, "clientProviderClass");
                Intrinsics.checkExpressionValueIsNotNull(serverProviderClass, "serverProviderClass");
                Jdk8WithJettyBootPlatform jdk8WithJettyBootPlatform = new Jdk8WithJettyBootPlatform(putMethod, getMethod, removeMethod, clientProviderClass, serverProviderClass);
                return jdk8WithJettyBootPlatform;
            } catch (ClassNotFoundException | NoSuchMethodException e2) {
                return null;
            }
        }
    }

    public Jdk8WithJettyBootPlatform(Method putMethod2, Method getMethod2, Method removeMethod2, Class<?> clientProviderClass2, Class<?> serverProviderClass2) {
        Intrinsics.checkParameterIsNotNull(putMethod2, "putMethod");
        Intrinsics.checkParameterIsNotNull(getMethod2, "getMethod");
        Intrinsics.checkParameterIsNotNull(removeMethod2, "removeMethod");
        Intrinsics.checkParameterIsNotNull(clientProviderClass2, "clientProviderClass");
        Intrinsics.checkParameterIsNotNull(serverProviderClass2, "serverProviderClass");
        this.putMethod = putMethod2;
        this.getMethod = getMethod2;
        this.removeMethod = removeMethod2;
        this.clientProviderClass = clientProviderClass2;
        this.serverProviderClass = serverProviderClass2;
    }

    public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<? extends Protocol> protocols) {
        String str = "failed to set ALPN";
        Intrinsics.checkParameterIsNotNull(sslSocket, "sslSocket");
        Intrinsics.checkParameterIsNotNull(protocols, "protocols");
        List names = Platform.Companion.alpnProtocolNames(protocols);
        try {
            Object alpnProvider = Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.clientProviderClass, this.serverProviderClass}, new AlpnProvider(names));
            this.putMethod.invoke(null, new Object[]{sslSocket, alpnProvider});
        } catch (InvocationTargetException e) {
            throw new AssertionError(str, e);
        } catch (IllegalAccessException e2) {
            throw new AssertionError(str, e2);
        }
    }

    public void afterHandshake(SSLSocket sslSocket) {
        String str = "failed to remove ALPN";
        Intrinsics.checkParameterIsNotNull(sslSocket, "sslSocket");
        try {
            this.removeMethod.invoke(null, new Object[]{sslSocket});
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
            String str2 = null;
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(this.getMethod.invoke(null, new Object[]{socket}));
            if (invocationHandler != null) {
                AlpnProvider provider = (AlpnProvider) invocationHandler;
                if (provider.getUnsupported$okhttp() || provider.getSelected$okhttp() != null) {
                    if (!provider.getUnsupported$okhttp()) {
                        str2 = provider.getSelected$okhttp();
                    }
                    return str2;
                }
                Platform.Companion.get().log(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", null);
                return null;
            }
            throw new TypeCastException("null cannot be cast to non-null type okhttp3.internal.platform.Jdk8WithJettyBootPlatform.AlpnProvider");
        } catch (InvocationTargetException e) {
            throw new AssertionError(str, e);
        } catch (IllegalAccessException e2) {
            throw new AssertionError(str, e2);
        }
    }
}
