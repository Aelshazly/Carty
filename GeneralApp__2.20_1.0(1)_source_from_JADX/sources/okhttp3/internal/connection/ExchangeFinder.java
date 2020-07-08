package okhttp3.internal.connection;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteSelector.Selection;
import okhttp3.internal.http.ExchangeCodec;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u001e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0010J0\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\u0010H\u0002J8\u0010%\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u0010H\u0002J\u0006\u0010&\u001a\u00020\u0010J\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010'\u001a\u00020\u0010H\u0002J\u0006\u0010(\u001a\u00020)R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, mo24952d2 = {"Lokhttp3/internal/connection/ExchangeFinder;", "", "transmitter", "Lokhttp3/internal/connection/Transmitter;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "address", "Lokhttp3/Address;", "call", "Lokhttp3/Call;", "eventListener", "Lokhttp3/EventListener;", "(Lokhttp3/internal/connection/Transmitter;Lokhttp3/internal/connection/RealConnectionPool;Lokhttp3/Address;Lokhttp3/Call;Lokhttp3/EventListener;)V", "connectingConnection", "Lokhttp3/internal/connection/RealConnection;", "hasStreamFailure", "", "nextRouteToTry", "Lokhttp3/Route;", "routeSelection", "Lokhttp3/internal/connection/RouteSelector$Selection;", "routeSelector", "Lokhttp3/internal/connection/RouteSelector;", "find", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "chain", "Lokhttp3/Interceptor$Chain;", "doExtensiveHealthChecks", "findConnection", "connectTimeout", "", "readTimeout", "writeTimeout", "pingIntervalMillis", "connectionRetryEnabled", "findHealthyConnection", "hasRouteToTry", "retryCurrentRoute", "trackFailure", "", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: ExchangeFinder.kt */
public final class ExchangeFinder {
    private final Address address;
    private final Call call;
    private RealConnection connectingConnection;
    private final RealConnectionPool connectionPool;
    private final EventListener eventListener;
    private boolean hasStreamFailure;
    private Route nextRouteToTry;
    private Selection routeSelection;
    private final RouteSelector routeSelector = new RouteSelector(this.address, this.connectionPool.getRouteDatabase(), this.call, this.eventListener);
    private final Transmitter transmitter;

    public ExchangeFinder(Transmitter transmitter2, RealConnectionPool connectionPool2, Address address2, Call call2, EventListener eventListener2) {
        Intrinsics.checkParameterIsNotNull(transmitter2, "transmitter");
        Intrinsics.checkParameterIsNotNull(connectionPool2, "connectionPool");
        Intrinsics.checkParameterIsNotNull(address2, "address");
        Intrinsics.checkParameterIsNotNull(call2, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkParameterIsNotNull(eventListener2, "eventListener");
        this.transmitter = transmitter2;
        this.connectionPool = connectionPool2;
        this.address = address2;
        this.call = call2;
        this.eventListener = eventListener2;
    }

    public final ExchangeCodec find(OkHttpClient client, Chain chain, boolean doExtensiveHealthChecks) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        try {
            return findHealthyConnection(chain.connectTimeoutMillis(), chain.readTimeoutMillis(), chain.writeTimeoutMillis(), client.pingIntervalMillis(), client.retryOnConnectionFailure(), doExtensiveHealthChecks).newCodec$okhttp(client, chain);
        } catch (RouteException e) {
            trackFailure();
            throw e;
        } catch (IOException e2) {
            trackFailure();
            throw new RouteException(e2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        if (r0.isHealthy(r10) != false) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0024, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final okhttp3.internal.connection.RealConnection findHealthyConnection(int r5, int r6, int r7, int r8, boolean r9, boolean r10) throws java.io.IOException {
        /*
            r4 = this;
        L_0x0000:
            okhttp3.internal.connection.RealConnection r0 = r4.findConnection(r5, r6, r7, r8, r9)
            okhttp3.internal.connection.RealConnectionPool r1 = r4.connectionPool
            monitor-enter(r1)
            r2 = 0
            int r3 = r0.getSuccessCount$okhttp()     // Catch:{ all -> 0x0025 }
            if (r3 != 0) goto L_0x0017
            monitor-exit(r1)
            return r0
        L_0x0017:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0025 }
            monitor-exit(r1)
            boolean r1 = r0.isHealthy(r10)
            if (r1 != 0) goto L_0x0024
            r0.noNewExchanges()
            goto L_0x0000
        L_0x0024:
            return r0
        L_0x0025:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.ExchangeFinder.findHealthyConnection(int, int, int, int, boolean, boolean):okhttp3.internal.connection.RealConnection");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e1, code lost:
        if (r8.hasNext() == false) goto L_0x00e3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final okhttp3.internal.connection.RealConnection findConnection(int r21, int r22, int r23, int r24, boolean r25) throws java.io.IOException {
        /*
            r20 = this;
            r1 = r20
            r2 = 0
            r0 = 0
            r3 = r0
            okhttp3.internal.connection.RealConnection r3 = (okhttp3.internal.connection.RealConnection) r3
            r4 = r0
            okhttp3.Route r4 = (okhttp3.Route) r4
            kotlin.jvm.internal.Ref$ObjectRef r5 = new kotlin.jvm.internal.Ref$ObjectRef
            r5.<init>()
            r6 = 0
            okhttp3.internal.connection.RealConnectionPool r7 = r1.connectionPool
            monitor-enter(r7)
            r8 = 0
            okhttp3.internal.connection.Transmitter r9 = r1.transmitter     // Catch:{ all -> 0x0208 }
            boolean r9 = r9.isCanceled()     // Catch:{ all -> 0x0208 }
            if (r9 != 0) goto L_0x01fe
            r9 = 0
            r1.hasStreamFailure = r9     // Catch:{ all -> 0x0208 }
            okhttp3.internal.connection.Transmitter r10 = r1.transmitter     // Catch:{ all -> 0x0208 }
            okhttp3.internal.connection.RealConnection r10 = r10.getConnection()     // Catch:{ all -> 0x0208 }
            r5.element = r10     // Catch:{ all -> 0x0208 }
            okhttp3.internal.connection.Transmitter r10 = r1.transmitter     // Catch:{ all -> 0x0208 }
            okhttp3.internal.connection.RealConnection r10 = r10.getConnection()     // Catch:{ all -> 0x0208 }
            if (r10 == 0) goto L_0x0047
            okhttp3.internal.connection.Transmitter r10 = r1.transmitter     // Catch:{ all -> 0x0208 }
            okhttp3.internal.connection.RealConnection r10 = r10.getConnection()     // Catch:{ all -> 0x0208 }
            if (r10 != 0) goto L_0x003a
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x0208 }
        L_0x003a:
            boolean r10 = r10.getNoNewExchanges()     // Catch:{ all -> 0x0208 }
            if (r10 == 0) goto L_0x0047
            okhttp3.internal.connection.Transmitter r10 = r1.transmitter     // Catch:{ all -> 0x0208 }
            java.net.Socket r10 = r10.releaseConnectionNoEvents()     // Catch:{ all -> 0x0208 }
            goto L_0x0048
        L_0x0047:
            r10 = r0
        L_0x0048:
            r6 = r10
            okhttp3.internal.connection.Transmitter r10 = r1.transmitter     // Catch:{ all -> 0x0208 }
            okhttp3.internal.connection.RealConnection r10 = r10.getConnection()     // Catch:{ all -> 0x0208 }
            if (r10 == 0) goto L_0x005d
            okhttp3.internal.connection.Transmitter r10 = r1.transmitter     // Catch:{ all -> 0x0208 }
            okhttp3.internal.connection.RealConnection r10 = r10.getConnection()     // Catch:{ all -> 0x0208 }
            r3 = r10
            r10 = r0
            okhttp3.internal.connection.RealConnection r10 = (okhttp3.internal.connection.RealConnection) r10     // Catch:{ all -> 0x0208 }
            r5.element = r10     // Catch:{ all -> 0x0208 }
        L_0x005d:
            if (r3 != 0) goto L_0x0097
            okhttp3.internal.connection.RealConnectionPool r10 = r1.connectionPool     // Catch:{ all -> 0x0208 }
            okhttp3.Address r11 = r1.address     // Catch:{ all -> 0x0208 }
            okhttp3.internal.connection.Transmitter r12 = r1.transmitter     // Catch:{ all -> 0x0208 }
            boolean r10 = r10.transmitterAcquirePooledConnection(r11, r12, r0, r9)     // Catch:{ all -> 0x0208 }
            if (r10 == 0) goto L_0x0074
            r2 = 1
            okhttp3.internal.connection.Transmitter r10 = r1.transmitter     // Catch:{ all -> 0x0208 }
            okhttp3.internal.connection.RealConnection r10 = r10.getConnection()     // Catch:{ all -> 0x0208 }
            r3 = r10
            goto L_0x0097
        L_0x0074:
            okhttp3.Route r10 = r1.nextRouteToTry     // Catch:{ all -> 0x0208 }
            if (r10 == 0) goto L_0x0081
            okhttp3.Route r10 = r1.nextRouteToTry     // Catch:{ all -> 0x0208 }
            r4 = r10
            r10 = r0
            okhttp3.Route r10 = (okhttp3.Route) r10     // Catch:{ all -> 0x0208 }
            r1.nextRouteToTry = r10     // Catch:{ all -> 0x0208 }
            goto L_0x0097
        L_0x0081:
            boolean r10 = r20.retryCurrentRoute()     // Catch:{ all -> 0x0208 }
            if (r10 == 0) goto L_0x0097
            okhttp3.internal.connection.Transmitter r10 = r1.transmitter     // Catch:{ all -> 0x0208 }
            okhttp3.internal.connection.RealConnection r10 = r10.getConnection()     // Catch:{ all -> 0x0208 }
            if (r10 != 0) goto L_0x0092
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x0208 }
        L_0x0092:
            okhttp3.Route r10 = r10.route()     // Catch:{ all -> 0x0208 }
            r4 = r10
        L_0x0097:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0208 }
            monitor-exit(r7)
            if (r6 == 0) goto L_0x00a0
            okhttp3.internal.Util.closeQuietly(r6)
        L_0x00a0:
            T r7 = r5.element
            okhttp3.internal.connection.RealConnection r7 = (okhttp3.internal.connection.RealConnection) r7
            if (r7 == 0) goto L_0x00b8
            okhttp3.EventListener r7 = r1.eventListener
            okhttp3.Call r8 = r1.call
            T r10 = r5.element
            okhttp3.internal.connection.RealConnection r10 = (okhttp3.internal.connection.RealConnection) r10
            if (r10 != 0) goto L_0x00b3
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00b3:
            okhttp3.Connection r10 = (okhttp3.Connection) r10
            r7.connectionReleased(r8, r10)
        L_0x00b8:
            if (r2 == 0) goto L_0x00c9
            okhttp3.EventListener r7 = r1.eventListener
            okhttp3.Call r8 = r1.call
            if (r3 != 0) goto L_0x00c3
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00c3:
            r10 = r3
            okhttp3.Connection r10 = (okhttp3.Connection) r10
            r7.connectionAcquired(r8, r10)
        L_0x00c9:
            if (r3 == 0) goto L_0x00d1
            if (r3 != 0) goto L_0x00d0
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00d0:
            return r3
        L_0x00d1:
            r7 = 0
            if (r4 != 0) goto L_0x00ec
            okhttp3.internal.connection.RouteSelector$Selection r8 = r1.routeSelection
            if (r8 == 0) goto L_0x00e3
            if (r8 != 0) goto L_0x00dd
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00dd:
            boolean r8 = r8.hasNext()
            if (r8 != 0) goto L_0x00ec
        L_0x00e3:
            r7 = 1
            okhttp3.internal.connection.RouteSelector r8 = r1.routeSelector
            okhttp3.internal.connection.RouteSelector$Selection r8 = r8.next()
            r1.routeSelection = r8
        L_0x00ec:
            r8 = r0
            java.util.List r8 = (java.util.List) r8
            okhttp3.internal.connection.RealConnectionPool r10 = r1.connectionPool
            monitor-enter(r10)
            r11 = 0
            okhttp3.internal.connection.Transmitter r12 = r1.transmitter     // Catch:{ all -> 0x01fb }
            boolean r12 = r12.isCanceled()     // Catch:{ all -> 0x01fb }
            if (r12 != 0) goto L_0x01f1
            if (r7 == 0) goto L_0x011d
            okhttp3.internal.connection.RouteSelector$Selection r12 = r1.routeSelection     // Catch:{ all -> 0x01fb }
            if (r12 != 0) goto L_0x0104
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01fb }
        L_0x0104:
            java.util.List r12 = r12.getRoutes()     // Catch:{ all -> 0x01fb }
            r8 = r12
            okhttp3.internal.connection.RealConnectionPool r12 = r1.connectionPool     // Catch:{ all -> 0x01fb }
            okhttp3.Address r13 = r1.address     // Catch:{ all -> 0x01fb }
            okhttp3.internal.connection.Transmitter r14 = r1.transmitter     // Catch:{ all -> 0x01fb }
            boolean r9 = r12.transmitterAcquirePooledConnection(r13, r14, r8, r9)     // Catch:{ all -> 0x01fb }
            if (r9 == 0) goto L_0x011d
            r2 = 1
            okhttp3.internal.connection.Transmitter r9 = r1.transmitter     // Catch:{ all -> 0x01fb }
            okhttp3.internal.connection.RealConnection r9 = r9.getConnection()     // Catch:{ all -> 0x01fb }
            r3 = r9
        L_0x011d:
            if (r2 != 0) goto L_0x013c
            if (r4 != 0) goto L_0x012d
            okhttp3.internal.connection.RouteSelector$Selection r9 = r1.routeSelection     // Catch:{ all -> 0x01fb }
            if (r9 != 0) goto L_0x0128
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01fb }
        L_0x0128:
            okhttp3.Route r9 = r9.next()     // Catch:{ all -> 0x01fb }
            r4 = r9
        L_0x012d:
            okhttp3.internal.connection.RealConnection r9 = new okhttp3.internal.connection.RealConnection     // Catch:{ all -> 0x01fb }
            okhttp3.internal.connection.RealConnectionPool r12 = r1.connectionPool     // Catch:{ all -> 0x01fb }
            if (r4 != 0) goto L_0x0136
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01fb }
        L_0x0136:
            r9.<init>(r12, r4)     // Catch:{ all -> 0x01fb }
            r3 = r9
            r1.connectingConnection = r3     // Catch:{ all -> 0x01fb }
        L_0x013c:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x01fb }
            monitor-exit(r10)
            if (r2 == 0) goto L_0x0156
            okhttp3.EventListener r0 = r1.eventListener
            okhttp3.Call r9 = r1.call
            if (r3 != 0) goto L_0x014a
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x014a:
            r10 = r3
            okhttp3.Connection r10 = (okhttp3.Connection) r10
            r0.connectionAcquired(r9, r10)
            if (r3 != 0) goto L_0x0155
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0155:
            return r3
        L_0x0156:
            if (r3 != 0) goto L_0x015b
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x015b:
            okhttp3.Call r9 = r1.call
            okhttp3.EventListener r10 = r1.eventListener
            r12 = r3
            r13 = r21
            r14 = r22
            r15 = r23
            r16 = r24
            r17 = r25
            r18 = r9
            r19 = r10
            r12.connect(r13, r14, r15, r16, r17, r18, r19)
            okhttp3.internal.connection.RealConnectionPool r9 = r1.connectionPool
            okhttp3.internal.connection.RouteDatabase r9 = r9.getRouteDatabase()
            if (r3 != 0) goto L_0x0181
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0181:
            okhttp3.Route r10 = r3.route()
            r9.connected(r10)
            r9 = r0
            java.net.Socket r9 = (java.net.Socket) r9
            okhttp3.internal.connection.RealConnectionPool r10 = r1.connectionPool
            monitor-enter(r10)
            r11 = 0
            okhttp3.internal.connection.RealConnection r0 = (okhttp3.internal.connection.RealConnection) r0     // Catch:{ all -> 0x01ee }
            r1.connectingConnection = r0     // Catch:{ all -> 0x01ee }
            okhttp3.internal.connection.RealConnectionPool r0 = r1.connectionPool     // Catch:{ all -> 0x01ee }
            okhttp3.Address r12 = r1.address     // Catch:{ all -> 0x01ee }
            okhttp3.internal.connection.Transmitter r13 = r1.transmitter     // Catch:{ all -> 0x01ee }
            r14 = 1
            boolean r0 = r0.transmitterAcquirePooledConnection(r12, r13, r8, r14)     // Catch:{ all -> 0x01ee }
            if (r0 == 0) goto L_0x01bc
            if (r3 != 0) goto L_0x01a5
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01ee }
        L_0x01a5:
            r3.setNoNewExchanges(r14)     // Catch:{ all -> 0x01ee }
            if (r3 != 0) goto L_0x01ad
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01ee }
        L_0x01ad:
            java.net.Socket r0 = r3.socket()     // Catch:{ all -> 0x01ee }
            r9 = r0
            okhttp3.internal.connection.Transmitter r0 = r1.transmitter     // Catch:{ all -> 0x01ee }
            okhttp3.internal.connection.RealConnection r0 = r0.getConnection()     // Catch:{ all -> 0x01ee }
            r3 = r0
            r1.nextRouteToTry = r4     // Catch:{ all -> 0x01ee }
            goto L_0x01d0
        L_0x01bc:
            okhttp3.internal.connection.RealConnectionPool r0 = r1.connectionPool     // Catch:{ all -> 0x01ee }
            if (r3 != 0) goto L_0x01c3
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01ee }
        L_0x01c3:
            r0.put(r3)     // Catch:{ all -> 0x01ee }
            okhttp3.internal.connection.Transmitter r0 = r1.transmitter     // Catch:{ all -> 0x01ee }
            if (r3 != 0) goto L_0x01cd
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01ee }
        L_0x01cd:
            r0.acquireConnectionNoEvents(r3)     // Catch:{ all -> 0x01ee }
        L_0x01d0:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x01ee }
            monitor-exit(r10)
            if (r9 == 0) goto L_0x01d9
            okhttp3.internal.Util.closeQuietly(r9)
        L_0x01d9:
            okhttp3.EventListener r0 = r1.eventListener
            okhttp3.Call r10 = r1.call
            if (r3 != 0) goto L_0x01e2
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x01e2:
            r11 = r3
            okhttp3.Connection r11 = (okhttp3.Connection) r11
            r0.connectionAcquired(r10, r11)
            if (r3 != 0) goto L_0x01ed
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x01ed:
            return r3
        L_0x01ee:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        L_0x01f1:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x01fb }
            java.lang.String r9 = "Canceled"
            r0.<init>(r9)     // Catch:{ all -> 0x01fb }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x01fb }
            throw r0     // Catch:{ all -> 0x01fb }
        L_0x01fb:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        L_0x01fe:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0208 }
            java.lang.String r9 = "Canceled"
            r0.<init>(r9)     // Catch:{ all -> 0x0208 }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x0208 }
            throw r0     // Catch:{ all -> 0x0208 }
        L_0x0208:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.ExchangeFinder.findConnection(int, int, int, int, boolean):okhttp3.internal.connection.RealConnection");
    }

    public final RealConnection connectingConnection() {
        boolean holdsLock = Thread.holdsLock(this.connectionPool);
        if (!_Assertions.ENABLED || holdsLock) {
            return this.connectingConnection;
        }
        throw new AssertionError("Assertion failed");
    }

    public final void trackFailure() {
        boolean z = !Thread.holdsLock(this.connectionPool);
        if (!_Assertions.ENABLED || z) {
            synchronized (this.connectionPool) {
                this.hasStreamFailure = true;
                Unit unit = Unit.INSTANCE;
            }
            return;
        }
        throw new AssertionError("Assertion failed");
    }

    public final boolean hasStreamFailure() {
        boolean z;
        synchronized (this.connectionPool) {
            z = this.hasStreamFailure;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x003e, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean hasRouteToTry() {
        /*
            r5 = this;
            okhttp3.internal.connection.RealConnectionPool r0 = r5.connectionPool
            monitor-enter(r0)
            r1 = 0
            okhttp3.Route r2 = r5.nextRouteToTry     // Catch:{ all -> 0x003f }
            r3 = 1
            if (r2 == 0) goto L_0x000c
            monitor-exit(r0)
            return r3
        L_0x000c:
            boolean r2 = r5.retryCurrentRoute()     // Catch:{ all -> 0x003f }
            if (r2 == 0) goto L_0x0026
            okhttp3.internal.connection.Transmitter r2 = r5.transmitter     // Catch:{ all -> 0x003f }
            okhttp3.internal.connection.RealConnection r2 = r2.getConnection()     // Catch:{ all -> 0x003f }
            if (r2 != 0) goto L_0x001d
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x003f }
        L_0x001d:
            okhttp3.Route r2 = r2.route()     // Catch:{ all -> 0x003f }
            r5.nextRouteToTry = r2     // Catch:{ all -> 0x003f }
            monitor-exit(r0)
            return r3
        L_0x0026:
            okhttp3.internal.connection.RouteSelector$Selection r2 = r5.routeSelection     // Catch:{ all -> 0x003f }
            r4 = 0
            if (r2 == 0) goto L_0x0030
            boolean r2 = r2.hasNext()     // Catch:{ all -> 0x003f }
            goto L_0x0031
        L_0x0030:
            r2 = 0
        L_0x0031:
            if (r2 != 0) goto L_0x003d
            okhttp3.internal.connection.RouteSelector r2 = r5.routeSelector     // Catch:{ all -> 0x003f }
            boolean r2 = r2.hasNext()     // Catch:{ all -> 0x003f }
            if (r2 == 0) goto L_0x003c
            goto L_0x003d
        L_0x003c:
            r3 = 0
        L_0x003d:
            monitor-exit(r0)
            return r3
        L_0x003f:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.ExchangeFinder.hasRouteToTry():boolean");
    }

    private final boolean retryCurrentRoute() {
        if (this.transmitter.getConnection() != null) {
            RealConnection connection = this.transmitter.getConnection();
            if (connection == null) {
                Intrinsics.throwNpe();
            }
            if (connection.getRouteFailureCount$okhttp() == 0) {
                RealConnection connection2 = this.transmitter.getConnection();
                if (connection2 == null) {
                    Intrinsics.throwNpe();
                }
                if (Util.canReuseConnectionFor(connection2.route().address().url(), this.address.url())) {
                    return true;
                }
            }
        }
        return false;
    }
}
