package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http1.Http1ExchangeCodec;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Connection.Builder;
import okhttp3.internal.http2.Http2Connection.Listener;
import okhttp3.internal.http2.Http2ExchangeCodec;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.http2.StreamResetException;
import okhttp3.internal.p017ws.RealWebSocket.Streams;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.OkHostnameVerifier;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import p008cz.msebera.android.httpclient.HttpStatus;
import p008cz.msebera.android.httpclient.protocol.HTTP;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 p2\u00020\u00012\u00020\u0002:\u0001pB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u00105\u001a\u000206J>\u00107\u001a\u0002062\u0006\u00108\u001a\u00020\t2\u0006\u00109\u001a\u00020\t2\u0006\u0010:\u001a\u00020\t2\u0006\u0010;\u001a\u00020\t2\u0006\u0010<\u001a\u00020\u00172\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@J(\u0010A\u001a\u0002062\u0006\u00108\u001a\u00020\t2\u0006\u00109\u001a\u00020\t2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@H\u0002J\u0010\u0010B\u001a\u0002062\u0006\u0010C\u001a\u00020DH\u0002J0\u0010E\u001a\u0002062\u0006\u00108\u001a\u00020\t2\u0006\u00109\u001a\u00020\t2\u0006\u0010:\u001a\u00020\t2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@H\u0002J*\u0010F\u001a\u0004\u0018\u00010G2\u0006\u00109\u001a\u00020\t2\u0006\u0010:\u001a\u00020\t2\u0006\u0010H\u001a\u00020G2\u0006\u0010I\u001a\u00020JH\u0002J\b\u0010K\u001a\u00020GH\u0002J(\u0010L\u001a\u0002062\u0006\u0010C\u001a\u00020D2\u0006\u0010;\u001a\u00020\t2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@H\u0002J\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J%\u0010M\u001a\u00020\u00172\u0006\u0010N\u001a\u00020O2\u000e\u0010P\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010QH\u0000¢\u0006\u0002\bRJ\u000e\u0010S\u001a\u00020\u00172\u0006\u0010T\u001a\u00020\u0017J\u001d\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u00020ZH\u0000¢\u0006\u0002\b[J\u0015\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020_H\u0000¢\u0006\u0002\b`J\u0006\u0010\u0019\u001a\u000206J\u0010\u0010a\u001a\u0002062\u0006\u0010b\u001a\u00020\u000fH\u0016J\u0010\u0010c\u001a\u0002062\u0006\u0010d\u001a\u00020eH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0016\u0010f\u001a\u00020\u00172\f\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00060QH\u0002J\b\u0010)\u001a\u00020 H\u0016J\u0010\u0010h\u001a\u0002062\u0006\u0010;\u001a\u00020\tH\u0002J\u000e\u0010i\u001a\u00020\u00172\u0006\u0010I\u001a\u00020JJ\b\u0010j\u001a\u00020kH\u0016J\u0017\u0010l\u001a\u0002062\b\u0010m\u001a\u0004\u0018\u00010nH\u0000¢\u0006\u0002\boR\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0018\"\u0004\b\u001b\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010 X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010,\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010$\"\u0004\b.\u0010&R\u001d\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002020100¢\u0006\b\n\u0000\u001a\u0004\b3\u00104¨\u0006q"}, mo24952d2 = {"Lokhttp3/internal/connection/RealConnection;", "Lokhttp3/internal/http2/Http2Connection$Listener;", "Lokhttp3/Connection;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "route", "Lokhttp3/Route;", "(Lokhttp3/internal/connection/RealConnectionPool;Lokhttp3/Route;)V", "allocationLimit", "", "getConnectionPool", "()Lokhttp3/internal/connection/RealConnectionPool;", "handshake", "Lokhttp3/Handshake;", "http2Connection", "Lokhttp3/internal/http2/Http2Connection;", "idleAtNanos", "", "getIdleAtNanos$okhttp", "()J", "setIdleAtNanos$okhttp", "(J)V", "isMultiplexed", "", "()Z", "noNewExchanges", "getNoNewExchanges", "setNoNewExchanges", "(Z)V", "protocol", "Lokhttp3/Protocol;", "rawSocket", "Ljava/net/Socket;", "refusedStreamCount", "routeFailureCount", "getRouteFailureCount$okhttp", "()I", "setRouteFailureCount$okhttp", "(I)V", "sink", "Lokio/BufferedSink;", "socket", "source", "Lokio/BufferedSource;", "successCount", "getSuccessCount$okhttp", "setSuccessCount$okhttp", "transmitters", "", "Ljava/lang/ref/Reference;", "Lokhttp3/internal/connection/Transmitter;", "getTransmitters", "()Ljava/util/List;", "cancel", "", "connect", "connectTimeout", "readTimeout", "writeTimeout", "pingIntervalMillis", "connectionRetryEnabled", "call", "Lokhttp3/Call;", "eventListener", "Lokhttp3/EventListener;", "connectSocket", "connectTls", "connectionSpecSelector", "Lokhttp3/internal/connection/ConnectionSpecSelector;", "connectTunnel", "createTunnel", "Lokhttp3/Request;", "tunnelRequest", "url", "Lokhttp3/HttpUrl;", "createTunnelRequest", "establishProtocol", "isEligible", "address", "Lokhttp3/Address;", "routes", "", "isEligible$okhttp", "isHealthy", "doExtensiveChecks", "newCodec", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "chain", "Lokhttp3/Interceptor$Chain;", "newCodec$okhttp", "newWebSocketStreams", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "exchange", "Lokhttp3/internal/connection/Exchange;", "newWebSocketStreams$okhttp", "onSettings", "connection", "onStream", "stream", "Lokhttp3/internal/http2/Http2Stream;", "routeMatchesAny", "candidates", "startHttp2", "supportsUrl", "toString", "", "trackFailure", "e", "Ljava/io/IOException;", "trackFailure$okhttp", "Companion", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: RealConnection.kt */
public final class RealConnection extends Listener implements Connection {
    public static final Companion Companion = new Companion(null);
    private static final int MAX_TUNNEL_ATTEMPTS = 21;
    private static final String NPE_THROW_WITH_NULL = "throw with null exception";
    private int allocationLimit = 1;
    private final RealConnectionPool connectionPool;
    /* access modifiers changed from: private */
    public Handshake handshake;
    private Http2Connection http2Connection;
    private long idleAtNanos = LongCompanionObject.MAX_VALUE;
    private boolean noNewExchanges;
    private Protocol protocol;
    private Socket rawSocket;
    private int refusedStreamCount;
    private final Route route;
    private int routeFailureCount;
    private BufferedSink sink;
    /* access modifiers changed from: private */
    public Socket socket;
    private BufferedSource source;
    private int successCount;
    private final List<Reference<Transmitter>> transmitters = new ArrayList();

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0011"}, mo24952d2 = {"Lokhttp3/internal/connection/RealConnection$Companion;", "", "()V", "MAX_TUNNEL_ATTEMPTS", "", "NPE_THROW_WITH_NULL", "", "newTestConnection", "Lokhttp3/internal/connection/RealConnection;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "route", "Lokhttp3/Route;", "socket", "Ljava/net/Socket;", "idleAtNanos", "", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: RealConnection.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final RealConnection newTestConnection(RealConnectionPool connectionPool, Route route, Socket socket, long idleAtNanos) {
            Intrinsics.checkParameterIsNotNull(connectionPool, "connectionPool");
            Intrinsics.checkParameterIsNotNull(route, "route");
            Intrinsics.checkParameterIsNotNull(socket, "socket");
            RealConnection result = new RealConnection(connectionPool, route);
            result.socket = socket;
            result.setIdleAtNanos$okhttp(idleAtNanos);
            return result;
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24953k = 3, mo24954mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Type.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[ErrorCode.values().length];

        static {
            $EnumSwitchMapping$0[Type.DIRECT.ordinal()] = 1;
            $EnumSwitchMapping$0[Type.HTTP.ordinal()] = 2;
            $EnumSwitchMapping$1[ErrorCode.REFUSED_STREAM.ordinal()] = 1;
            $EnumSwitchMapping$1[ErrorCode.CANCEL.ordinal()] = 2;
        }
    }

    public RealConnection(RealConnectionPool connectionPool2, Route route2) {
        Intrinsics.checkParameterIsNotNull(connectionPool2, "connectionPool");
        Intrinsics.checkParameterIsNotNull(route2, "route");
        this.connectionPool = connectionPool2;
        this.route = route2;
    }

    public final RealConnectionPool getConnectionPool() {
        return this.connectionPool;
    }

    public final boolean getNoNewExchanges() {
        return this.noNewExchanges;
    }

    public final void setNoNewExchanges(boolean z) {
        this.noNewExchanges = z;
    }

    public final int getRouteFailureCount$okhttp() {
        return this.routeFailureCount;
    }

    public final void setRouteFailureCount$okhttp(int i) {
        this.routeFailureCount = i;
    }

    public final int getSuccessCount$okhttp() {
        return this.successCount;
    }

    public final void setSuccessCount$okhttp(int i) {
        this.successCount = i;
    }

    public final List<Reference<Transmitter>> getTransmitters() {
        return this.transmitters;
    }

    public final long getIdleAtNanos$okhttp() {
        return this.idleAtNanos;
    }

    public final void setIdleAtNanos$okhttp(long j) {
        this.idleAtNanos = j;
    }

    public final boolean isMultiplexed() {
        return this.http2Connection != null;
    }

    public final void noNewExchanges() {
        boolean z = !Thread.holdsLock(this.connectionPool);
        if (!_Assertions.ENABLED || z) {
            synchronized (this.connectionPool) {
                this.noNewExchanges = true;
                Unit unit = Unit.INSTANCE;
            }
            return;
        }
        throw new AssertionError("Assertion failed");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0133  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0174  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0181  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x018a A[EDGE_INSN: B:75:0x018a->B:66:0x018a ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:76:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void connect(int r17, int r18, int r19, int r20, boolean r21, okhttp3.Call r22, okhttp3.EventListener r23) {
        /*
            r16 = this;
            r7 = r16
            r8 = r22
            r9 = r23
            java.lang.String r0 = "call"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            java.lang.String r0 = "eventListener"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            okhttp3.Protocol r0 = r7.protocol
            if (r0 != 0) goto L_0x0016
            r0 = 1
            goto L_0x0017
        L_0x0016:
            r0 = 0
        L_0x0017:
            if (r0 == 0) goto L_0x01a4
            r10 = 0
            r0 = r10
            okhttp3.internal.connection.RouteException r0 = (okhttp3.internal.connection.RouteException) r0
            okhttp3.Route r1 = r7.route
            okhttp3.Address r1 = r1.address()
            java.util.List r11 = r1.connectionSpecs()
            okhttp3.internal.connection.ConnectionSpecSelector r1 = new okhttp3.internal.connection.ConnectionSpecSelector
            r1.<init>(r11)
            r12 = r1
            okhttp3.Route r1 = r7.route
            okhttp3.Address r1 = r1.address()
            javax.net.ssl.SSLSocketFactory r1 = r1.sslSocketFactory()
            if (r1 != 0) goto L_0x0093
            okhttp3.ConnectionSpec r1 = okhttp3.ConnectionSpec.CLEARTEXT
            boolean r1 = r11.contains(r1)
            if (r1 == 0) goto L_0x0081
            okhttp3.Route r1 = r7.route
            okhttp3.Address r1 = r1.address()
            okhttp3.HttpUrl r1 = r1.url()
            java.lang.String r1 = r1.host()
            okhttp3.internal.platform.Platform$Companion r2 = okhttp3.internal.platform.Platform.Companion
            okhttp3.internal.platform.Platform r2 = r2.get()
            boolean r2 = r2.isCleartextTrafficPermitted(r1)
            if (r2 == 0) goto L_0x005c
            goto L_0x00a5
        L_0x005c:
            okhttp3.internal.connection.RouteException r2 = new okhttp3.internal.connection.RouteException
            java.net.UnknownServiceException r3 = new java.net.UnknownServiceException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "CLEARTEXT communication to "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r5 = " not permitted by network security policy"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            java.io.IOException r3 = (java.io.IOException) r3
            r2.<init>(r3)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        L_0x0081:
            okhttp3.internal.connection.RouteException r1 = new okhttp3.internal.connection.RouteException
            java.net.UnknownServiceException r2 = new java.net.UnknownServiceException
            java.lang.String r3 = "CLEARTEXT communication not enabled for client"
            r2.<init>(r3)
            java.io.IOException r2 = (java.io.IOException) r2
            r1.<init>(r2)
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        L_0x0093:
            okhttp3.Route r1 = r7.route
            okhttp3.Address r1 = r1.address()
            java.util.List r1 = r1.protocols()
            okhttp3.Protocol r2 = okhttp3.Protocol.H2_PRIOR_KNOWLEDGE
            boolean r1 = r1.contains(r2)
            if (r1 != 0) goto L_0x018e
        L_0x00a5:
            r13 = r0
        L_0x00a6:
            okhttp3.Route r0 = r7.route     // Catch:{ IOException -> 0x0128 }
            boolean r0 = r0.requiresTunnel()     // Catch:{ IOException -> 0x0128 }
            if (r0 == 0) goto L_0x00d0
            r1 = r16
            r2 = r17
            r3 = r18
            r4 = r19
            r5 = r22
            r6 = r23
            r1.connectTunnel(r2, r3, r4, r5, r6)     // Catch:{ IOException -> 0x0128 }
            java.net.Socket r0 = r7.rawSocket     // Catch:{ IOException -> 0x0128 }
            if (r0 != 0) goto L_0x00cb
            r14 = r17
            r15 = r18
            r6 = r20
            goto L_0x00ef
        L_0x00cb:
            r14 = r17
            r15 = r18
            goto L_0x00d7
        L_0x00d0:
            r14 = r17
            r15 = r18
            r7.connectSocket(r14, r15, r8, r9)     // Catch:{ IOException -> 0x0126 }
        L_0x00d7:
            r6 = r20
            r7.establishProtocol(r12, r6, r8, r9)     // Catch:{ IOException -> 0x0124 }
            okhttp3.Route r0 = r7.route     // Catch:{ IOException -> 0x0124 }
            java.net.InetSocketAddress r0 = r0.socketAddress()     // Catch:{ IOException -> 0x0124 }
            okhttp3.Route r1 = r7.route     // Catch:{ IOException -> 0x0124 }
            java.net.Proxy r1 = r1.proxy()     // Catch:{ IOException -> 0x0124 }
            okhttp3.Protocol r2 = r7.protocol     // Catch:{ IOException -> 0x0124 }
            r9.connectEnd(r8, r0, r1, r2)     // Catch:{ IOException -> 0x0124 }
        L_0x00ef:
            okhttp3.Route r0 = r7.route
            boolean r0 = r0.requiresTunnel()
            if (r0 == 0) goto L_0x010e
            java.net.Socket r0 = r7.rawSocket
            if (r0 == 0) goto L_0x00fc
            goto L_0x010e
        L_0x00fc:
            okhttp3.internal.connection.RouteException r0 = new okhttp3.internal.connection.RouteException
            java.net.ProtocolException r1 = new java.net.ProtocolException
            java.lang.String r2 = "Too many tunnel connections attempted: 21"
            r1.<init>(r2)
            java.io.IOException r1 = (java.io.IOException) r1
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x010e:
            okhttp3.internal.http2.Http2Connection r1 = r7.http2Connection
            if (r1 == 0) goto L_0x0123
            okhttp3.internal.connection.RealConnectionPool r2 = r7.connectionPool
            monitor-enter(r2)
            r0 = 0
            int r3 = r1.maxConcurrentStreams()     // Catch:{ all -> 0x0120 }
            r7.allocationLimit = r3     // Catch:{ all -> 0x0120 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0120 }
            monitor-exit(r2)
            goto L_0x0123
        L_0x0120:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L_0x0123:
            return
        L_0x0124:
            r0 = move-exception
            goto L_0x012f
        L_0x0126:
            r0 = move-exception
            goto L_0x012d
        L_0x0128:
            r0 = move-exception
            r14 = r17
            r15 = r18
        L_0x012d:
            r6 = r20
        L_0x012f:
            java.net.Socket r1 = r7.socket
            if (r1 == 0) goto L_0x0136
            okhttp3.internal.Util.closeQuietly(r1)
        L_0x0136:
            java.net.Socket r1 = r7.rawSocket
            if (r1 == 0) goto L_0x013d
            okhttp3.internal.Util.closeQuietly(r1)
        L_0x013d:
            r1 = r10
            java.net.Socket r1 = (java.net.Socket) r1
            r7.socket = r1
            r7.rawSocket = r1
            r1 = r10
            okio.BufferedSource r1 = (okio.BufferedSource) r1
            r7.source = r1
            r1 = r10
            okio.BufferedSink r1 = (okio.BufferedSink) r1
            r7.sink = r1
            r1 = r10
            okhttp3.Handshake r1 = (okhttp3.Handshake) r1
            r7.handshake = r1
            r1 = r10
            okhttp3.Protocol r1 = (okhttp3.Protocol) r1
            r7.protocol = r1
            r1 = r10
            okhttp3.internal.http2.Http2Connection r1 = (okhttp3.internal.http2.Http2Connection) r1
            r7.http2Connection = r1
            okhttp3.Route r1 = r7.route
            java.net.InetSocketAddress r3 = r1.socketAddress()
            okhttp3.Route r1 = r7.route
            java.net.Proxy r4 = r1.proxy()
            r5 = 0
            r1 = r23
            r2 = r22
            r6 = r0
            r1.connectFailed(r2, r3, r4, r5, r6)
            if (r13 != 0) goto L_0x017b
            okhttp3.internal.connection.RouteException r1 = new okhttp3.internal.connection.RouteException
            r1.<init>(r0)
            r13 = r1
            goto L_0x017e
        L_0x017b:
            r13.addConnectException(r0)
        L_0x017e:
            if (r21 == 0) goto L_0x018a
            boolean r1 = r12.connectionFailed(r0)
            if (r1 == 0) goto L_0x018a
            goto L_0x00a6
        L_0x018a:
            r1 = r13
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        L_0x018e:
            r14 = r17
            r15 = r18
            okhttp3.internal.connection.RouteException r1 = new okhttp3.internal.connection.RouteException
            java.net.UnknownServiceException r2 = new java.net.UnknownServiceException
            java.lang.String r3 = "H2_PRIOR_KNOWLEDGE cannot be used with HTTPS"
            r2.<init>(r3)
            java.io.IOException r2 = (java.io.IOException) r2
            r1.<init>(r2)
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        L_0x01a4:
            r14 = r17
            r15 = r18
            r0 = 0
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "already connected"
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.connect(int, int, int, int, boolean, okhttp3.Call, okhttp3.EventListener):void");
    }

    private final void connectTunnel(int connectTimeout, int readTimeout, int writeTimeout, Call call, EventListener eventListener) throws IOException {
        Request tunnelRequest = createTunnelRequest();
        HttpUrl url = tunnelRequest.url();
        int i = 0;
        while (i < 21) {
            connectSocket(connectTimeout, readTimeout, call, eventListener);
            Request createTunnel = createTunnel(readTimeout, writeTimeout, tunnelRequest, url);
            if (createTunnel != null) {
                tunnelRequest = createTunnel;
                Socket socket2 = this.rawSocket;
                if (socket2 != null) {
                    Util.closeQuietly(socket2);
                }
                this.rawSocket = null;
                this.sink = null;
                this.source = null;
                eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), null);
                i++;
            } else {
                return;
            }
        }
    }

    private final void connectSocket(int connectTimeout, int readTimeout, Call call, EventListener eventListener) throws IOException {
        Socket rawSocket2;
        Proxy proxy = this.route.proxy();
        Address address = this.route.address();
        Type type = proxy.type();
        if (type != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
            if (i == 1 || i == 2) {
                rawSocket2 = address.socketFactory().createSocket();
                if (rawSocket2 == null) {
                    Intrinsics.throwNpe();
                }
                this.rawSocket = rawSocket2;
                eventListener.connectStart(call, this.route.socketAddress(), proxy);
                rawSocket2.setSoTimeout(readTimeout);
                Platform.Companion.get().connectSocket(rawSocket2, this.route.socketAddress(), connectTimeout);
                try {
                    this.source = Okio.buffer(Okio.source(rawSocket2));
                    this.sink = Okio.buffer(Okio.sink(rawSocket2));
                } catch (NullPointerException npe) {
                    if (Intrinsics.areEqual((Object) npe.getMessage(), (Object) NPE_THROW_WITH_NULL)) {
                        throw new IOException(npe);
                    }
                    return;
                }
            }
        }
        rawSocket2 = new Socket(proxy);
        this.rawSocket = rawSocket2;
        eventListener.connectStart(call, this.route.socketAddress(), proxy);
        rawSocket2.setSoTimeout(readTimeout);
        try {
            Platform.Companion.get().connectSocket(rawSocket2, this.route.socketAddress(), connectTimeout);
            this.source = Okio.buffer(Okio.source(rawSocket2));
            this.sink = Okio.buffer(Okio.sink(rawSocket2));
        } catch (ConnectException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to connect to ");
            sb.append(this.route.socketAddress());
            ConnectException connectException = new ConnectException(sb.toString());
            connectException.initCause(e);
            throw connectException;
        }
    }

    private final void establishProtocol(ConnectionSpecSelector connectionSpecSelector, int pingIntervalMillis, Call call, EventListener eventListener) throws IOException {
        if (this.route.address().sslSocketFactory() != null) {
            eventListener.secureConnectStart(call);
            connectTls(connectionSpecSelector);
            eventListener.secureConnectEnd(call, this.handshake);
            if (this.protocol == Protocol.HTTP_2) {
                startHttp2(pingIntervalMillis);
            }
        } else if (this.route.address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
            this.socket = this.rawSocket;
            this.protocol = Protocol.H2_PRIOR_KNOWLEDGE;
            startHttp2(pingIntervalMillis);
        } else {
            this.socket = this.rawSocket;
            this.protocol = Protocol.HTTP_1_1;
        }
    }

    private final void startHttp2(int pingIntervalMillis) throws IOException {
        Socket socket2 = this.socket;
        if (socket2 == null) {
            Intrinsics.throwNpe();
        }
        BufferedSource source2 = this.source;
        if (source2 == null) {
            Intrinsics.throwNpe();
        }
        BufferedSink sink2 = this.sink;
        if (sink2 == null) {
            Intrinsics.throwNpe();
        }
        socket2.setSoTimeout(0);
        Http2Connection http2Connection2 = new Builder(true).socket(socket2, this.route.address().url().host(), source2, sink2).listener(this).pingIntervalMillis(pingIntervalMillis).build();
        this.http2Connection = http2Connection2;
        Http2Connection.start$default(http2Connection2, false, 1, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x01b8  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x01c4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void connectTls(okhttp3.internal.connection.ConnectionSpecSelector r17) throws java.io.IOException {
        /*
            r16 = this;
            r1 = r16
            okhttp3.Route r0 = r1.route
            okhttp3.Address r2 = r0.address()
            javax.net.ssl.SSLSocketFactory r3 = r2.sslSocketFactory()
            r4 = 0
            r0 = 0
            r5 = r0
            javax.net.ssl.SSLSocket r5 = (javax.net.ssl.SSLSocket) r5
            if (r3 != 0) goto L_0x0017
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01b3 }
        L_0x0017:
            java.net.Socket r6 = r1.rawSocket     // Catch:{ all -> 0x01b3 }
            okhttp3.HttpUrl r7 = r2.url()     // Catch:{ all -> 0x01b3 }
            java.lang.String r7 = r7.host()     // Catch:{ all -> 0x01b3 }
            okhttp3.HttpUrl r8 = r2.url()     // Catch:{ all -> 0x01b3 }
            int r8 = r8.port()     // Catch:{ all -> 0x01b3 }
            r9 = 1
            java.net.Socket r6 = r3.createSocket(r6, r7, r8, r9)     // Catch:{ all -> 0x01b3 }
            if (r6 == 0) goto L_0x01a7
            javax.net.ssl.SSLSocket r6 = (javax.net.ssl.SSLSocket) r6     // Catch:{ all -> 0x01b3 }
            r5 = r6
            r6 = r17
            okhttp3.ConnectionSpec r7 = r6.configureSecureSocket(r5)     // Catch:{ all -> 0x01b1 }
            boolean r8 = r7.supportsTlsExtensions()     // Catch:{ all -> 0x01b1 }
            if (r8 == 0) goto L_0x0054
            okhttp3.internal.platform.Platform$Companion r8 = okhttp3.internal.platform.Platform.Companion     // Catch:{ all -> 0x01b1 }
            okhttp3.internal.platform.Platform r8 = r8.get()     // Catch:{ all -> 0x01b1 }
            okhttp3.HttpUrl r10 = r2.url()     // Catch:{ all -> 0x01b1 }
            java.lang.String r10 = r10.host()     // Catch:{ all -> 0x01b1 }
            java.util.List r11 = r2.protocols()     // Catch:{ all -> 0x01b1 }
            r8.configureTlsExtensions(r5, r10, r11)     // Catch:{ all -> 0x01b1 }
        L_0x0054:
            r5.startHandshake()     // Catch:{ all -> 0x01b1 }
            javax.net.ssl.SSLSession r8 = r5.getSession()     // Catch:{ all -> 0x01b1 }
            okhttp3.Handshake$Companion r10 = okhttp3.Handshake.Companion     // Catch:{ all -> 0x01b1 }
            java.lang.String r11 = "sslSocketSession"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r11)     // Catch:{ all -> 0x01b1 }
            okhttp3.Handshake r10 = r10.get(r8)     // Catch:{ all -> 0x01b1 }
            javax.net.ssl.HostnameVerifier r11 = r2.hostnameVerifier()     // Catch:{ all -> 0x01b1 }
            if (r11 != 0) goto L_0x006f
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01b1 }
        L_0x006f:
            okhttp3.HttpUrl r12 = r2.url()     // Catch:{ all -> 0x01b1 }
            java.lang.String r12 = r12.host()     // Catch:{ all -> 0x01b1 }
            boolean r11 = r11.verify(r12, r8)     // Catch:{ all -> 0x01b1 }
            if (r11 != 0) goto L_0x0122
            java.util.List r11 = r10.peerCertificates()     // Catch:{ all -> 0x01b1 }
            r12 = r11
            java.util.Collection r12 = (java.util.Collection) r12     // Catch:{ all -> 0x01b1 }
            boolean r12 = r12.isEmpty()     // Catch:{ all -> 0x01b1 }
            r12 = r12 ^ r9
            if (r12 == 0) goto L_0x00fc
            r12 = 0
            java.lang.Object r12 = r11.get(r12)     // Catch:{ all -> 0x01b1 }
            if (r12 != 0) goto L_0x009a
            kotlin.TypeCastException r0 = new kotlin.TypeCastException     // Catch:{ all -> 0x01b1 }
            java.lang.String r9 = "null cannot be cast to non-null type java.security.cert.X509Certificate"
            r0.<init>(r9)     // Catch:{ all -> 0x01b1 }
            throw r0     // Catch:{ all -> 0x01b1 }
        L_0x009a:
            java.security.cert.X509Certificate r12 = (java.security.cert.X509Certificate) r12     // Catch:{ all -> 0x01b1 }
            javax.net.ssl.SSLPeerUnverifiedException r13 = new javax.net.ssl.SSLPeerUnverifiedException     // Catch:{ all -> 0x01b1 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x01b1 }
            r14.<init>()     // Catch:{ all -> 0x01b1 }
            java.lang.String r15 = "\n              |Hostname "
            r14.append(r15)     // Catch:{ all -> 0x01b1 }
            okhttp3.HttpUrl r15 = r2.url()     // Catch:{ all -> 0x01b1 }
            java.lang.String r15 = r15.host()     // Catch:{ all -> 0x01b1 }
            r14.append(r15)     // Catch:{ all -> 0x01b1 }
            java.lang.String r15 = " not verified:\n              |    certificate: "
            r14.append(r15)     // Catch:{ all -> 0x01b1 }
            okhttp3.CertificatePinner$Companion r15 = okhttp3.CertificatePinner.Companion     // Catch:{ all -> 0x01b1 }
            r0 = r12
            java.security.cert.Certificate r0 = (java.security.cert.Certificate) r0     // Catch:{ all -> 0x01b1 }
            java.lang.String r0 = r15.pin(r0)     // Catch:{ all -> 0x01b1 }
            r14.append(r0)     // Catch:{ all -> 0x01b1 }
            java.lang.String r0 = "\n              |    DN: "
            r14.append(r0)     // Catch:{ all -> 0x01b1 }
            java.security.Principal r0 = r12.getSubjectDN()     // Catch:{ all -> 0x01b1 }
            java.lang.String r15 = "cert.subjectDN"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r15)     // Catch:{ all -> 0x01b1 }
            java.lang.String r0 = r0.getName()     // Catch:{ all -> 0x01b1 }
            r14.append(r0)     // Catch:{ all -> 0x01b1 }
            java.lang.String r0 = "\n              |    subjectAltNames: "
            r14.append(r0)     // Catch:{ all -> 0x01b1 }
            okhttp3.internal.tls.OkHostnameVerifier r0 = okhttp3.internal.tls.OkHostnameVerifier.INSTANCE     // Catch:{ all -> 0x01b1 }
            java.util.List r0 = r0.allSubjectAltNames(r12)     // Catch:{ all -> 0x01b1 }
            r14.append(r0)     // Catch:{ all -> 0x01b1 }
            java.lang.String r0 = "\n              "
            r14.append(r0)     // Catch:{ all -> 0x01b1 }
            java.lang.String r0 = r14.toString()     // Catch:{ all -> 0x01b1 }
            r14 = 0
            java.lang.String r0 = kotlin.text.StringsKt.trimMargin$default(r0, r14, r9, r14)     // Catch:{ all -> 0x01b1 }
            r13.<init>(r0)     // Catch:{ all -> 0x01b1 }
            java.lang.Throwable r13 = (java.lang.Throwable) r13     // Catch:{ all -> 0x01b1 }
            throw r13     // Catch:{ all -> 0x01b1 }
        L_0x00fc:
            javax.net.ssl.SSLPeerUnverifiedException r0 = new javax.net.ssl.SSLPeerUnverifiedException     // Catch:{ all -> 0x01b1 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x01b1 }
            r9.<init>()     // Catch:{ all -> 0x01b1 }
            java.lang.String r12 = "Hostname "
            r9.append(r12)     // Catch:{ all -> 0x01b1 }
            okhttp3.HttpUrl r12 = r2.url()     // Catch:{ all -> 0x01b1 }
            java.lang.String r12 = r12.host()     // Catch:{ all -> 0x01b1 }
            r9.append(r12)     // Catch:{ all -> 0x01b1 }
            java.lang.String r12 = " not verified (no certificates)"
            r9.append(r12)     // Catch:{ all -> 0x01b1 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x01b1 }
            r0.<init>(r9)     // Catch:{ all -> 0x01b1 }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x01b1 }
            throw r0     // Catch:{ all -> 0x01b1 }
        L_0x0122:
            r14 = r0
            okhttp3.CertificatePinner r0 = r2.certificatePinner()     // Catch:{ all -> 0x01b1 }
            if (r0 != 0) goto L_0x012c
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01b1 }
        L_0x012c:
            okhttp3.Handshake r9 = new okhttp3.Handshake     // Catch:{ all -> 0x01b1 }
            okhttp3.TlsVersion r11 = r10.tlsVersion()     // Catch:{ all -> 0x01b1 }
            okhttp3.CipherSuite r12 = r10.cipherSuite()     // Catch:{ all -> 0x01b1 }
            java.util.List r13 = r10.localCertificates()     // Catch:{ all -> 0x01b1 }
            okhttp3.internal.connection.RealConnection$connectTls$1 r15 = new okhttp3.internal.connection.RealConnection$connectTls$1     // Catch:{ all -> 0x01b1 }
            r15.<init>(r0, r10, r2)     // Catch:{ all -> 0x01b1 }
            kotlin.jvm.functions.Function0 r15 = (kotlin.jvm.functions.Function0) r15     // Catch:{ all -> 0x01b1 }
            r9.<init>(r11, r12, r13, r15)     // Catch:{ all -> 0x01b1 }
            r1.handshake = r9     // Catch:{ all -> 0x01b1 }
            okhttp3.HttpUrl r9 = r2.url()     // Catch:{ all -> 0x01b1 }
            java.lang.String r9 = r9.host()     // Catch:{ all -> 0x01b1 }
            okhttp3.internal.connection.RealConnection$connectTls$2 r11 = new okhttp3.internal.connection.RealConnection$connectTls$2     // Catch:{ all -> 0x01b1 }
            r11.<init>(r1)     // Catch:{ all -> 0x01b1 }
            kotlin.jvm.functions.Function0 r11 = (kotlin.jvm.functions.Function0) r11     // Catch:{ all -> 0x01b1 }
            r0.check$okhttp(r9, r11)     // Catch:{ all -> 0x01b1 }
            boolean r9 = r7.supportsTlsExtensions()     // Catch:{ all -> 0x01b1 }
            if (r9 == 0) goto L_0x016a
            okhttp3.internal.platform.Platform$Companion r9 = okhttp3.internal.platform.Platform.Companion     // Catch:{ all -> 0x01b1 }
            okhttp3.internal.platform.Platform r9 = r9.get()     // Catch:{ all -> 0x01b1 }
            java.lang.String r9 = r9.getSelectedProtocol(r5)     // Catch:{ all -> 0x01b1 }
            r14 = r9
            goto L_0x016b
        L_0x016a:
        L_0x016b:
            r9 = r14
            r11 = r5
            java.net.Socket r11 = (java.net.Socket) r11     // Catch:{ all -> 0x01b1 }
            r1.socket = r11     // Catch:{ all -> 0x01b1 }
            r11 = r5
            java.net.Socket r11 = (java.net.Socket) r11     // Catch:{ all -> 0x01b1 }
            okio.Source r11 = okio.Okio.source(r11)     // Catch:{ all -> 0x01b1 }
            okio.BufferedSource r11 = okio.Okio.buffer(r11)     // Catch:{ all -> 0x01b1 }
            r1.source = r11     // Catch:{ all -> 0x01b1 }
            r11 = r5
            java.net.Socket r11 = (java.net.Socket) r11     // Catch:{ all -> 0x01b1 }
            okio.Sink r11 = okio.Okio.sink(r11)     // Catch:{ all -> 0x01b1 }
            okio.BufferedSink r11 = okio.Okio.buffer(r11)     // Catch:{ all -> 0x01b1 }
            r1.sink = r11     // Catch:{ all -> 0x01b1 }
            if (r9 == 0) goto L_0x0194
            okhttp3.Protocol$Companion r11 = okhttp3.Protocol.Companion     // Catch:{ all -> 0x01b1 }
            okhttp3.Protocol r11 = r11.get(r9)     // Catch:{ all -> 0x01b1 }
            goto L_0x0196
        L_0x0194:
            okhttp3.Protocol r11 = okhttp3.Protocol.HTTP_1_1     // Catch:{ all -> 0x01b1 }
        L_0x0196:
            r1.protocol = r11     // Catch:{ all -> 0x01b1 }
            r0 = 1
            if (r5 == 0) goto L_0x01a4
            okhttp3.internal.platform.Platform$Companion r4 = okhttp3.internal.platform.Platform.Companion
            okhttp3.internal.platform.Platform r4 = r4.get()
            r4.afterHandshake(r5)
        L_0x01a4:
            return
        L_0x01a7:
            r6 = r17
            kotlin.TypeCastException r0 = new kotlin.TypeCastException     // Catch:{ all -> 0x01b1 }
            java.lang.String r7 = "null cannot be cast to non-null type javax.net.ssl.SSLSocket"
            r0.<init>(r7)     // Catch:{ all -> 0x01b1 }
            throw r0     // Catch:{ all -> 0x01b1 }
        L_0x01b1:
            r0 = move-exception
            goto L_0x01b6
        L_0x01b3:
            r0 = move-exception
            r6 = r17
        L_0x01b6:
            if (r5 == 0) goto L_0x01c1
            okhttp3.internal.platform.Platform$Companion r7 = okhttp3.internal.platform.Platform.Companion
            okhttp3.internal.platform.Platform r7 = r7.get()
            r7.afterHandshake(r5)
        L_0x01c1:
            if (r5 == 0) goto L_0x01ca
            r7 = r5
            java.net.Socket r7 = (java.net.Socket) r7
            okhttp3.internal.Util.closeQuietly(r7)
        L_0x01ca:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.connectTls(okhttp3.internal.connection.ConnectionSpecSelector):void");
    }

    private final Request createTunnel(int readTimeout, int writeTimeout, Request tunnelRequest, HttpUrl url) throws IOException {
        Response response;
        Request nextRequest = tunnelRequest;
        StringBuilder sb = new StringBuilder();
        sb.append("CONNECT ");
        sb.append(Util.toHostHeader(url, true));
        sb.append(" HTTP/1.1");
        String requestLine = sb.toString();
        do {
            BufferedSource source2 = this.source;
            if (source2 == null) {
                Intrinsics.throwNpe();
            }
            BufferedSink sink2 = this.sink;
            if (sink2 == null) {
                Intrinsics.throwNpe();
            }
            Http1ExchangeCodec tunnelCodec = new Http1ExchangeCodec(null, null, source2, sink2);
            source2.timeout().timeout((long) readTimeout, TimeUnit.MILLISECONDS);
            sink2.timeout().timeout((long) writeTimeout, TimeUnit.MILLISECONDS);
            tunnelCodec.writeRequest(nextRequest.headers(), requestLine);
            tunnelCodec.finishRequest();
            Response.Builder readResponseHeaders = tunnelCodec.readResponseHeaders(false);
            if (readResponseHeaders == null) {
                Intrinsics.throwNpe();
            }
            response = readResponseHeaders.request(nextRequest).build();
            tunnelCodec.skipConnectBody(response);
            int code = response.code();
            if (code != 200) {
                if (code == 407) {
                    Request authenticate = this.route.address().proxyAuthenticator().authenticate(this.route, response);
                    if (authenticate != null) {
                        nextRequest = authenticate;
                    } else {
                        throw new IOException("Failed to authenticate with proxy");
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Unexpected response code for CONNECT: ");
                    sb2.append(response.code());
                    throw new IOException(sb2.toString());
                }
            } else if (source2.getBuffer().exhausted() && sink2.getBuffer().exhausted()) {
                return null;
            } else {
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
        } while (!StringsKt.equals("close", Response.header$default(response, "Connection", null, 2, null), true));
        return nextRequest;
    }

    private final Request createTunnelRequest() throws IOException {
        Request proxyConnectRequest = new Request.Builder().url(this.route.address().url()).method("CONNECT", null).header("Host", Util.toHostHeader(this.route.address().url(), true)).header("Proxy-Connection", HTTP.CONN_KEEP_ALIVE).header("User-Agent", Version.userAgent).build();
        Request authenticatedRequest = this.route.address().proxyAuthenticator().authenticate(this.route, new Response.Builder().request(proxyConnectRequest).protocol(Protocol.HTTP_1_1).code(HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED).message("Preemptive Authenticate").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1).receivedResponseAtMillis(-1).header("Proxy-Authenticate", "OkHttp-Preemptive").build());
        return authenticatedRequest != null ? authenticatedRequest : proxyConnectRequest;
    }

    public final boolean isEligible$okhttp(Address address, List<Route> routes) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        if (this.transmitters.size() >= this.allocationLimit || this.noNewExchanges || !this.route.address().equalsNonHost$okhttp(address)) {
            return false;
        }
        if (Intrinsics.areEqual((Object) address.url().host(), (Object) route().address().url().host())) {
            return true;
        }
        if (this.http2Connection == null || routes == null || !routeMatchesAny(routes) || address.hostnameVerifier() != OkHostnameVerifier.INSTANCE || !supportsUrl(address.url())) {
            return false;
        }
        try {
            CertificatePinner certificatePinner = address.certificatePinner();
            if (certificatePinner == null) {
                Intrinsics.throwNpe();
            }
            String host = address.url().host();
            Handshake handshake2 = handshake();
            if (handshake2 == null) {
                Intrinsics.throwNpe();
            }
            certificatePinner.check(host, handshake2.peerCertificates());
            return true;
        } catch (SSLPeerUnverifiedException e) {
            return false;
        }
    }

    private final boolean routeMatchesAny(List<Route> candidates) {
        boolean z;
        Iterable<Route> $this$any$iv = candidates;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Route it : $this$any$iv) {
            if (it.proxy().type() == Type.DIRECT && this.route.proxy().type() == Type.DIRECT && Intrinsics.areEqual((Object) this.route.socketAddress(), (Object) it.socketAddress())) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004c, code lost:
        if (r1.verify(r4, (java.security.cert.X509Certificate) r5) != false) goto L_0x0058;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean supportsUrl(okhttp3.HttpUrl r7) {
        /*
            r6 = this;
            java.lang.String r0 = "url"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            okhttp3.Route r0 = r6.route
            okhttp3.Address r0 = r0.address()
            okhttp3.HttpUrl r0 = r0.url()
            int r1 = r7.port()
            int r2 = r0.port()
            r3 = 0
            if (r1 == r2) goto L_0x001b
            return r3
        L_0x001b:
            java.lang.String r1 = r7.host()
            java.lang.String r2 = r0.host()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r2)
            r2 = 1
            if (r1 == 0) goto L_0x002b
            return r2
        L_0x002b:
            okhttp3.Handshake r1 = r6.handshake
            if (r1 == 0) goto L_0x0057
            okhttp3.internal.tls.OkHostnameVerifier r1 = okhttp3.internal.tls.OkHostnameVerifier.INSTANCE
            java.lang.String r4 = r7.host()
            okhttp3.Handshake r5 = r6.handshake
            if (r5 != 0) goto L_0x003c
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x003c:
            java.util.List r5 = r5.peerCertificates()
            java.lang.Object r5 = r5.get(r3)
            if (r5 == 0) goto L_0x004f
            java.security.cert.X509Certificate r5 = (java.security.cert.X509Certificate) r5
            boolean r1 = r1.verify(r4, r5)
            if (r1 == 0) goto L_0x0057
            goto L_0x0058
        L_0x004f:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            java.lang.String r2 = "null cannot be cast to non-null type java.security.cert.X509Certificate"
            r1.<init>(r2)
            throw r1
        L_0x0057:
            r2 = 0
        L_0x0058:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.supportsUrl(okhttp3.HttpUrl):boolean");
    }

    public final ExchangeCodec newCodec$okhttp(OkHttpClient client, Chain chain) throws SocketException {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        Socket socket2 = this.socket;
        if (socket2 == null) {
            Intrinsics.throwNpe();
        }
        BufferedSource source2 = this.source;
        if (source2 == null) {
            Intrinsics.throwNpe();
        }
        BufferedSink sink2 = this.sink;
        if (sink2 == null) {
            Intrinsics.throwNpe();
        }
        Http2Connection http2Connection2 = this.http2Connection;
        if (http2Connection2 != null) {
            return new Http2ExchangeCodec(client, this, chain, http2Connection2);
        }
        socket2.setSoTimeout(chain.readTimeoutMillis());
        source2.timeout().timeout((long) chain.readTimeoutMillis(), TimeUnit.MILLISECONDS);
        sink2.timeout().timeout((long) chain.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        return new Http1ExchangeCodec(client, this, source2, sink2);
    }

    public final Streams newWebSocketStreams$okhttp(Exchange exchange) throws SocketException {
        Intrinsics.checkParameterIsNotNull(exchange, "exchange");
        Socket socket2 = this.socket;
        if (socket2 == null) {
            Intrinsics.throwNpe();
        }
        BufferedSource source2 = this.source;
        if (source2 == null) {
            Intrinsics.throwNpe();
        }
        BufferedSink sink2 = this.sink;
        if (sink2 == null) {
            Intrinsics.throwNpe();
        }
        socket2.setSoTimeout(0);
        noNewExchanges();
        RealConnection$newWebSocketStreams$1 realConnection$newWebSocketStreams$1 = new RealConnection$newWebSocketStreams$1(exchange, source2, sink2, true, source2, sink2);
        return realConnection$newWebSocketStreams$1;
    }

    public Route route() {
        return this.route;
    }

    public final void cancel() {
        Socket socket2 = this.rawSocket;
        if (socket2 != null) {
            Util.closeQuietly(socket2);
        }
    }

    public Socket socket() {
        Socket socket2 = this.socket;
        if (socket2 == null) {
            Intrinsics.throwNpe();
        }
        return socket2;
    }

    public final boolean isHealthy(boolean doExtensiveChecks) {
        int readTimeout;
        Socket socket2 = this.socket;
        if (socket2 == null) {
            Intrinsics.throwNpe();
        }
        BufferedSource source2 = this.source;
        if (source2 == null) {
            Intrinsics.throwNpe();
        }
        if (socket2.isClosed() || socket2.isInputShutdown() || socket2.isOutputShutdown()) {
            return false;
        }
        Http2Connection http2Connection2 = this.http2Connection;
        if (http2Connection2 != null) {
            return !http2Connection2.isShutdown();
        }
        if (doExtensiveChecks) {
            try {
                readTimeout = socket2.getSoTimeout();
                socket2.setSoTimeout(1);
                boolean z = !source2.exhausted();
                socket2.setSoTimeout(readTimeout);
                return z;
            } catch (SocketTimeoutException e) {
            } catch (IOException e2) {
                return false;
            } catch (Throwable th) {
                socket2.setSoTimeout(readTimeout);
                throw th;
            }
        }
        return true;
    }

    public void onStream(Http2Stream stream) throws IOException {
        Intrinsics.checkParameterIsNotNull(stream, "stream");
        stream.close(ErrorCode.REFUSED_STREAM, null);
    }

    public void onSettings(Http2Connection connection) {
        Intrinsics.checkParameterIsNotNull(connection, "connection");
        synchronized (this.connectionPool) {
            this.allocationLimit = connection.maxConcurrentStreams();
            Unit unit = Unit.INSTANCE;
        }
    }

    public Handshake handshake() {
        return this.handshake;
    }

    public final void trackFailure$okhttp(IOException e) {
        boolean z = !Thread.holdsLock(this.connectionPool);
        if (!_Assertions.ENABLED || z) {
            synchronized (this.connectionPool) {
                if (e instanceof StreamResetException) {
                    int i = WhenMappings.$EnumSwitchMapping$1[((StreamResetException) e).errorCode.ordinal()];
                    if (i == 1) {
                        this.refusedStreamCount++;
                        if (this.refusedStreamCount > 1) {
                            this.noNewExchanges = true;
                            this.routeFailureCount++;
                        }
                    } else if (i != 2) {
                        this.noNewExchanges = true;
                        this.routeFailureCount++;
                    }
                } else if (!isMultiplexed() || (e instanceof ConnectionShutdownException)) {
                    this.noNewExchanges = true;
                    if (this.successCount == 0) {
                        if (e != null) {
                            this.connectionPool.connectFailed(this.route, e);
                        }
                        this.routeFailureCount++;
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
            return;
        }
        throw new AssertionError("Assertion failed");
    }

    public Protocol protocol() {
        Protocol protocol2 = this.protocol;
        if (protocol2 == null) {
            Intrinsics.throwNpe();
        }
        return protocol2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x005f, code lost:
        if (r1 != null) goto L_0x0064;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
            r2 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Connection{"
            r0.append(r1)
            okhttp3.Route r1 = r2.route
            okhttp3.Address r1 = r1.address()
            okhttp3.HttpUrl r1 = r1.url()
            java.lang.String r1 = r1.host()
            r0.append(r1)
            r1 = 58
            r0.append(r1)
            okhttp3.Route r1 = r2.route
            okhttp3.Address r1 = r1.address()
            okhttp3.HttpUrl r1 = r1.url()
            int r1 = r1.port()
            r0.append(r1)
            r1 = 44
            r0.append(r1)
            java.lang.String r1 = " proxy="
            r0.append(r1)
            okhttp3.Route r1 = r2.route
            java.net.Proxy r1 = r1.proxy()
            r0.append(r1)
            java.lang.String r1 = " hostAddress="
            r0.append(r1)
            okhttp3.Route r1 = r2.route
            java.net.InetSocketAddress r1 = r1.socketAddress()
            r0.append(r1)
            java.lang.String r1 = " cipherSuite="
            r0.append(r1)
            okhttp3.Handshake r1 = r2.handshake
            if (r1 == 0) goto L_0x0062
            okhttp3.CipherSuite r1 = r1.cipherSuite()
            if (r1 == 0) goto L_0x0062
            goto L_0x0064
        L_0x0062:
            java.lang.String r1 = "none"
        L_0x0064:
            r0.append(r1)
            java.lang.String r1 = " protocol="
            r0.append(r1)
            okhttp3.Protocol r1 = r2.protocol
            r0.append(r1)
            r1 = 125(0x7d, float:1.75E-43)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.toString():java.lang.String");
    }
}
