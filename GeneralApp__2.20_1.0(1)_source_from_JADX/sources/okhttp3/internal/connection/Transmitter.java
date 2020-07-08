package okhttp3.internal.connection;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.Util;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.platform.Platform;
import okio.Timeout;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001 \u0018\u00002\u00020\u0001:\u0001FB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010#\u001a\u00020$2\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010%\u001a\u00020$J\u0006\u0010&\u001a\u00020\tJ\u0006\u0010'\u001a\u00020$J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0006\u0010,\u001a\u00020$J;\u0010-\u001a\u0002H.\"\n\b\u0000\u0010.*\u0004\u0018\u00010/2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00020\t2\u0006\u00102\u001a\u0002H.H\u0000¢\u0006\u0004\b3\u00104J\u0006\u00105\u001a\u00020\tJ)\u00106\u001a\u0002H.\"\n\b\u0000\u0010.*\u0004\u0018\u00010/2\u0006\u00102\u001a\u0002H.2\u0006\u00107\u001a\u00020\tH\u0002¢\u0006\u0002\u00108J\u001d\u00109\u001a\u00020\u00152\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\tH\u0000¢\u0006\u0002\b=J\u0012\u0010\u001c\u001a\u0004\u0018\u00010/2\b\u00102\u001a\u0004\u0018\u00010/J\u000e\u0010>\u001a\u00020$2\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010?\u001a\u0004\u0018\u00010@J\u0006\u0010\u001f\u001a\u00020AJ\u0006\u0010\"\u001a\u00020$J\u0006\u0010B\u001a\u00020$J!\u0010C\u001a\u0002H.\"\n\b\u0000\u0010.*\u0004\u0018\u00010/2\u0006\u0010D\u001a\u0002H.H\u0002¢\u0006\u0002\u0010ER\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001a\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020 X\u0004¢\u0006\u0004\n\u0002\u0010!R\u000e\u0010\"\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006G"}, mo24952d2 = {"Lokhttp3/internal/connection/Transmitter;", "", "client", "Lokhttp3/OkHttpClient;", "call", "Lokhttp3/Call;", "(Lokhttp3/OkHttpClient;Lokhttp3/Call;)V", "callStackTrace", "canceled", "", "connection", "Lokhttp3/internal/connection/RealConnection;", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "setConnection", "(Lokhttp3/internal/connection/RealConnection;)V", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "eventListener", "Lokhttp3/EventListener;", "exchange", "Lokhttp3/internal/connection/Exchange;", "exchangeFinder", "Lokhttp3/internal/connection/ExchangeFinder;", "exchangeRequestDone", "exchangeResponseDone", "isCanceled", "()Z", "noMoreExchanges", "request", "Lokhttp3/Request;", "timeout", "okhttp3/internal/connection/Transmitter$timeout$1", "Lokhttp3/internal/connection/Transmitter$timeout$1;", "timeoutEarlyExit", "acquireConnectionNoEvents", "", "callStart", "canRetry", "cancel", "createAddress", "Lokhttp3/Address;", "url", "Lokhttp3/HttpUrl;", "exchangeDoneDueToException", "exchangeMessageDone", "E", "Ljava/io/IOException;", "requestDone", "responseDone", "e", "exchangeMessageDone$okhttp", "(Lokhttp3/internal/connection/Exchange;ZZLjava/io/IOException;)Ljava/io/IOException;", "hasExchange", "maybeReleaseConnection", "force", "(Ljava/io/IOException;Z)Ljava/io/IOException;", "newExchange", "chain", "Lokhttp3/Interceptor$Chain;", "doExtensiveHealthChecks", "newExchange$okhttp", "prepareToConnect", "releaseConnectionNoEvents", "Ljava/net/Socket;", "Lokio/Timeout;", "timeoutEnter", "timeoutExit", "cause", "(Ljava/io/IOException;)Ljava/io/IOException;", "TransmitterReference", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: Transmitter.kt */
public final class Transmitter {
    private final Call call;
    private Object callStackTrace;
    private boolean canceled;
    private final OkHttpClient client;
    private RealConnection connection;
    private final RealConnectionPool connectionPool = this.client.connectionPool().getDelegate$okhttp();
    private final EventListener eventListener = this.client.eventListenerFactory().create(this.call);
    private Exchange exchange;
    private ExchangeFinder exchangeFinder;
    private boolean exchangeRequestDone;
    private boolean exchangeResponseDone;
    private boolean noMoreExchanges;
    private Request request;
    private final Transmitter$timeout$1 timeout;
    private boolean timeoutEarlyExit;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, mo24952d2 = {"Lokhttp3/internal/connection/Transmitter$TransmitterReference;", "Ljava/lang/ref/WeakReference;", "Lokhttp3/internal/connection/Transmitter;", "referent", "callStackTrace", "", "(Lokhttp3/internal/connection/Transmitter;Ljava/lang/Object;)V", "getCallStackTrace", "()Ljava/lang/Object;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Transmitter.kt */
    public static final class TransmitterReference extends WeakReference<Transmitter> {
        private final Object callStackTrace;

        public TransmitterReference(Transmitter referent, Object callStackTrace2) {
            Intrinsics.checkParameterIsNotNull(referent, "referent");
            super(referent);
            this.callStackTrace = callStackTrace2;
        }

        public final Object getCallStackTrace() {
            return this.callStackTrace;
        }
    }

    public Transmitter(OkHttpClient client2, Call call2) {
        Intrinsics.checkParameterIsNotNull(client2, "client");
        Intrinsics.checkParameterIsNotNull(call2, NotificationCompat.CATEGORY_CALL);
        this.client = client2;
        this.call = call2;
        Transmitter$timeout$1 transmitter$timeout$1 = new Transmitter$timeout$1(this);
        transmitter$timeout$1.timeout((long) this.client.callTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.timeout = transmitter$timeout$1;
    }

    public final RealConnection getConnection() {
        return this.connection;
    }

    public final void setConnection(RealConnection realConnection) {
        this.connection = realConnection;
    }

    public final boolean isCanceled() {
        boolean z;
        synchronized (this.connectionPool) {
            z = this.canceled;
        }
        return z;
    }

    public final Timeout timeout() {
        return this.timeout;
    }

    public final void timeoutEnter() {
        this.timeout.enter();
    }

    public final void timeoutEarlyExit() {
        if (!this.timeoutEarlyExit) {
            this.timeoutEarlyExit = true;
            this.timeout.exit();
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    private final <E extends IOException> E timeoutExit(E cause) {
        if (this.timeoutEarlyExit || !this.timeout.exit()) {
            return cause;
        }
        InterruptedIOException e = new InterruptedIOException("timeout");
        if (cause != null) {
            e.initCause((Throwable) cause);
        }
        return e;
    }

    public final void callStart() {
        this.callStackTrace = Platform.Companion.get().getStackTraceForCloseable("response.body().close()");
        this.eventListener.callStart(this.call);
    }

    public final void prepareToConnect(Request request2) {
        Intrinsics.checkParameterIsNotNull(request2, "request");
        Request request3 = this.request;
        if (request3 != null) {
            if (request3 == null) {
                Intrinsics.throwNpe();
            }
            if (Util.canReuseConnectionFor(request3.url(), request2.url())) {
                ExchangeFinder exchangeFinder2 = this.exchangeFinder;
                if (exchangeFinder2 == null) {
                    Intrinsics.throwNpe();
                }
                if (exchangeFinder2.hasRouteToTry()) {
                    return;
                }
            }
            if (!(this.exchange == null)) {
                throw new IllegalStateException("Check failed.".toString());
            } else if (this.exchangeFinder != null) {
                maybeReleaseConnection(null, true);
                this.exchangeFinder = null;
            }
        }
        this.request = request2;
        ExchangeFinder exchangeFinder3 = new ExchangeFinder(this, this.connectionPool, createAddress(request2.url()), this.call, this.eventListener);
        this.exchangeFinder = exchangeFinder3;
    }

    private final Address createAddress(HttpUrl url) {
        SSLSocketFactory sslSocketFactory = null;
        HostnameVerifier hostnameVerifier = null;
        CertificatePinner certificatePinner = null;
        if (url.isHttps()) {
            sslSocketFactory = this.client.sslSocketFactory();
            hostnameVerifier = this.client.hostnameVerifier();
            certificatePinner = this.client.certificatePinner();
        }
        Address address = new Address(url.host(), url.port(), this.client.dns(), this.client.socketFactory(), sslSocketFactory, hostnameVerifier, certificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
        return address;
    }

    public final Exchange newExchange$okhttp(Chain chain, boolean doExtensiveHealthChecks) {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        synchronized (this.connectionPool) {
            boolean z = true;
            if (!this.noMoreExchanges) {
                if (this.exchange != null) {
                    z = false;
                }
                if (z) {
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()".toString());
                }
            } else {
                throw new IllegalStateException("released".toString());
            }
        }
        ExchangeFinder exchangeFinder2 = this.exchangeFinder;
        if (exchangeFinder2 == null) {
            Intrinsics.throwNpe();
        }
        ExchangeCodec codec = exchangeFinder2.find(this.client, chain, doExtensiveHealthChecks);
        Call call2 = this.call;
        EventListener eventListener2 = this.eventListener;
        ExchangeFinder exchangeFinder3 = this.exchangeFinder;
        if (exchangeFinder3 == null) {
            Intrinsics.throwNpe();
        }
        Exchange exchange2 = new Exchange(this, call2, eventListener2, exchangeFinder3, codec);
        synchronized (this.connectionPool) {
            this.exchange = exchange2;
            this.exchangeRequestDone = false;
            this.exchangeResponseDone = false;
        }
        return exchange2;
    }

    public final void acquireConnectionNoEvents(RealConnection connection2) {
        Intrinsics.checkParameterIsNotNull(connection2, "connection");
        boolean holdsLock = Thread.holdsLock(this.connectionPool);
        if (!_Assertions.ENABLED || holdsLock) {
            if (this.connection == null) {
                this.connection = connection2;
                connection2.getTransmitters().add(new TransmitterReference(this, this.callStackTrace));
                return;
            }
            throw new IllegalStateException("Check failed.".toString());
        }
        throw new AssertionError("Assertion failed");
    }

    public final Socket releaseConnectionNoEvents() {
        boolean holdsLock = Thread.holdsLock(this.connectionPool);
        if (!_Assertions.ENABLED || holdsLock) {
            RealConnection realConnection = this.connection;
            if (realConnection == null) {
                Intrinsics.throwNpe();
            }
            int index$iv = 0;
            Iterator it = realConnection.getTransmitters().iterator();
            while (true) {
                if (!it.hasNext()) {
                    index$iv = -1;
                    break;
                } else if (Intrinsics.areEqual((Object) (Transmitter) ((Reference) it.next()).get(), (Object) this)) {
                    break;
                } else {
                    index$iv++;
                }
            }
            int index = index$iv;
            if (index != -1) {
                RealConnection released = this.connection;
                if (released == null) {
                    Intrinsics.throwNpe();
                }
                released.getTransmitters().remove(index);
                this.connection = null;
                if (released.getTransmitters().isEmpty()) {
                    released.setIdleAtNanos$okhttp(System.nanoTime());
                    if (this.connectionPool.connectionBecameIdle(released)) {
                        return released.socket();
                    }
                }
                return null;
            }
            throw new IllegalStateException("Check failed.".toString());
        }
        throw new AssertionError("Assertion failed");
    }

    public final void exchangeDoneDueToException() {
        synchronized (this.connectionPool) {
            if (!this.noMoreExchanges) {
                this.exchange = null;
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0055, code lost:
        if (r1 == false) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0057, code lost:
        r0 = maybeReleaseConnection(r0, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005c, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <E extends java.io.IOException> E exchangeMessageDone$okhttp(okhttp3.internal.connection.Exchange r9, boolean r10, boolean r11, E r12) {
        /*
            r8 = this;
            java.lang.String r0 = "exchange"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            r0 = r12
            r1 = 0
            okhttp3.internal.connection.RealConnectionPool r2 = r8.connectionPool
            monitor-enter(r2)
            r3 = 0
            okhttp3.internal.connection.Exchange r4 = r8.exchange     // Catch:{ all -> 0x005d }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r4)     // Catch:{ all -> 0x005d }
            r5 = 1
            r4 = r4 ^ r5
            if (r4 == 0) goto L_0x0017
            monitor-exit(r2)
            return r0
        L_0x0017:
            r4 = 0
            if (r10 == 0) goto L_0x0021
            boolean r6 = r8.exchangeRequestDone     // Catch:{ all -> 0x005d }
            if (r6 != 0) goto L_0x001f
            r4 = 1
        L_0x001f:
            r8.exchangeRequestDone = r5     // Catch:{ all -> 0x005d }
        L_0x0021:
            if (r11 == 0) goto L_0x002a
            boolean r6 = r8.exchangeResponseDone     // Catch:{ all -> 0x005d }
            if (r6 != 0) goto L_0x0028
            r4 = 1
        L_0x0028:
            r8.exchangeResponseDone = r5     // Catch:{ all -> 0x005d }
        L_0x002a:
            boolean r6 = r8.exchangeRequestDone     // Catch:{ all -> 0x005d }
            if (r6 == 0) goto L_0x0052
            boolean r6 = r8.exchangeResponseDone     // Catch:{ all -> 0x005d }
            if (r6 == 0) goto L_0x0052
            if (r4 == 0) goto L_0x0052
            r1 = 1
            okhttp3.internal.connection.Exchange r6 = r8.exchange     // Catch:{ all -> 0x005d }
            if (r6 != 0) goto L_0x003c
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x005d }
        L_0x003c:
            okhttp3.internal.connection.RealConnection r6 = r6.connection()     // Catch:{ all -> 0x005d }
            if (r6 != 0) goto L_0x0045
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x005d }
        L_0x0045:
            int r7 = r6.getSuccessCount$okhttp()     // Catch:{ all -> 0x005d }
            int r7 = r7 + r5
            r6.setSuccessCount$okhttp(r7)     // Catch:{ all -> 0x005d }
            r5 = 0
            okhttp3.internal.connection.Exchange r5 = (okhttp3.internal.connection.Exchange) r5     // Catch:{ all -> 0x005d }
            r8.exchange = r5     // Catch:{ all -> 0x005d }
        L_0x0052:
            kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x005d }
            monitor-exit(r2)
            if (r1 == 0) goto L_0x005c
            r2 = 0
            java.io.IOException r0 = r8.maybeReleaseConnection(r0, r2)
        L_0x005c:
            return r0
        L_0x005d:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.Transmitter.exchangeMessageDone$okhttp(okhttp3.internal.connection.Exchange, boolean, boolean, java.io.IOException):java.io.IOException");
    }

    public final IOException noMoreExchanges(IOException e) {
        synchronized (this.connectionPool) {
            this.noMoreExchanges = true;
            Unit unit = Unit.INSTANCE;
        }
        return maybeReleaseConnection(e, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x001d A[Catch:{ all -> 0x0017 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x008d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <E extends java.io.IOException> E maybeReleaseConnection(E r11, boolean r12) {
        /*
            r10 = this;
            r0 = r11
            r1 = 0
            kotlin.jvm.internal.Ref$ObjectRef r2 = new kotlin.jvm.internal.Ref$ObjectRef
            r2.<init>()
            r3 = 0
            okhttp3.internal.connection.RealConnectionPool r4 = r10.connectionPool
            monitor-enter(r4)
            r5 = 0
            r6 = 0
            r7 = 1
            if (r12 == 0) goto L_0x001a
            okhttp3.internal.connection.Exchange r8 = r10.exchange     // Catch:{ all -> 0x0017 }
            if (r8 != 0) goto L_0x0015
            goto L_0x001a
        L_0x0015:
            r8 = 0
            goto L_0x001b
        L_0x0017:
            r5 = move-exception
            goto L_0x009c
        L_0x001a:
            r8 = 1
        L_0x001b:
            if (r8 == 0) goto L_0x008d
            okhttp3.internal.connection.RealConnection r8 = r10.connection     // Catch:{ all -> 0x0017 }
            okhttp3.Connection r8 = (okhttp3.Connection) r8     // Catch:{ all -> 0x0017 }
            r2.element = r8     // Catch:{ all -> 0x0017 }
            okhttp3.internal.connection.RealConnection r8 = r10.connection     // Catch:{ all -> 0x0017 }
            r9 = 0
            if (r8 == 0) goto L_0x0037
            okhttp3.internal.connection.Exchange r8 = r10.exchange     // Catch:{ all -> 0x0017 }
            if (r8 != 0) goto L_0x0037
            if (r12 != 0) goto L_0x0032
            boolean r8 = r10.noMoreExchanges     // Catch:{ all -> 0x0017 }
            if (r8 == 0) goto L_0x0037
        L_0x0032:
            java.net.Socket r8 = r10.releaseConnectionNoEvents()     // Catch:{ all -> 0x0017 }
            goto L_0x0038
        L_0x0037:
            r8 = r9
        L_0x0038:
            r1 = r8
            okhttp3.internal.connection.RealConnection r8 = r10.connection     // Catch:{ all -> 0x0017 }
            if (r8 == 0) goto L_0x0041
            okhttp3.Connection r9 = (okhttp3.Connection) r9     // Catch:{ all -> 0x0017 }
            r2.element = r9     // Catch:{ all -> 0x0017 }
        L_0x0041:
            boolean r8 = r10.noMoreExchanges     // Catch:{ all -> 0x0017 }
            if (r8 == 0) goto L_0x004b
            okhttp3.internal.connection.Exchange r8 = r10.exchange     // Catch:{ all -> 0x0017 }
            if (r8 != 0) goto L_0x004b
            r8 = 1
            goto L_0x004c
        L_0x004b:
            r8 = 0
        L_0x004c:
            r3 = r8
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0017 }
            monitor-exit(r4)
            if (r1 == 0) goto L_0x0055
            okhttp3.internal.Util.closeQuietly(r1)
        L_0x0055:
            T r4 = r2.element
            okhttp3.Connection r4 = (okhttp3.Connection) r4
            if (r4 == 0) goto L_0x006b
            okhttp3.EventListener r4 = r10.eventListener
            okhttp3.Call r5 = r10.call
            T r8 = r2.element
            okhttp3.Connection r8 = (okhttp3.Connection) r8
            if (r8 != 0) goto L_0x0068
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0068:
            r4.connectionReleased(r5, r8)
        L_0x006b:
            if (r3 == 0) goto L_0x008b
            if (r0 == 0) goto L_0x0070
            r6 = 1
        L_0x0070:
            r4 = r6
            java.io.IOException r0 = r10.timeoutExit(r0)
            if (r4 == 0) goto L_0x0084
            okhttp3.EventListener r5 = r10.eventListener
            okhttp3.Call r6 = r10.call
            if (r0 != 0) goto L_0x0080
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0080:
            r5.callFailed(r6, r0)
            goto L_0x008b
        L_0x0084:
            okhttp3.EventListener r5 = r10.eventListener
            okhttp3.Call r6 = r10.call
            r5.callEnd(r6)
        L_0x008b:
            return r0
        L_0x008d:
            r6 = 0
            java.lang.String r7 = "cannot release connection while it is in use"
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0017 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0017 }
            r6.<init>(r7)     // Catch:{ all -> 0x0017 }
            java.lang.Throwable r6 = (java.lang.Throwable) r6     // Catch:{ all -> 0x0017 }
            throw r6     // Catch:{ all -> 0x0017 }
        L_0x009c:
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.Transmitter.maybeReleaseConnection(java.io.IOException, boolean):java.io.IOException");
    }

    public final boolean canRetry() {
        ExchangeFinder exchangeFinder2 = this.exchangeFinder;
        if (exchangeFinder2 == null) {
            Intrinsics.throwNpe();
        }
        if (exchangeFinder2.hasStreamFailure()) {
            ExchangeFinder exchangeFinder3 = this.exchangeFinder;
            if (exchangeFinder3 == null) {
                Intrinsics.throwNpe();
            }
            if (exchangeFinder3.hasRouteToTry()) {
                return true;
            }
        }
        return false;
    }

    public final boolean hasExchange() {
        boolean z;
        synchronized (this.connectionPool) {
            z = this.exchange != null;
        }
        return z;
    }

    public final void cancel() {
        Exchange exchangeToCancel;
        RealConnection realConnection;
        RealConnection connectionToCancel;
        synchronized (this.connectionPool) {
            this.canceled = true;
            exchangeToCancel = this.exchange;
            ExchangeFinder exchangeFinder2 = this.exchangeFinder;
            if (exchangeFinder2 != null) {
                realConnection = exchangeFinder2.connectingConnection();
                if (realConnection != null) {
                    connectionToCancel = realConnection;
                    Unit unit = Unit.INSTANCE;
                }
            }
            realConnection = this.connection;
            connectionToCancel = realConnection;
            Unit unit2 = Unit.INSTANCE;
        }
        if (exchangeToCancel != null) {
            exchangeToCancel.cancel();
        } else if (connectionToCancel != null) {
            connectionToCancel.cancel();
        }
    }
}
