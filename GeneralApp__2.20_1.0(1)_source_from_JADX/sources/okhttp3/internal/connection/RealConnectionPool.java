package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.Proxy.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.ConnectionPool;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Transmitter.TransmitterReference;
import okhttp3.internal.platform.Platform;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000i\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003*\u0001\n\u0018\u0000 12\u00020\u0001:\u00011B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0005J\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\r2\u0006\u0010#\u001a\u00020\u0014J\u0006\u0010$\u001a\u00020\u0003J\u0006\u0010%\u001a\u00020\u001dJ\u0006\u0010&\u001a\u00020\u0003J\u0018\u0010'\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u0005H\u0002J\u000e\u0010(\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\u0014J.\u0010)\u001a\u00020\r2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u000e\u0010.\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010/2\u0006\u00100\u001a\u00020\rR\u0010\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u00062"}, mo24952d2 = {"Lokhttp3/internal/connection/RealConnectionPool;", "", "maxIdleConnections", "", "keepAliveDuration", "", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "(IJLjava/util/concurrent/TimeUnit;)V", "cleanupRunnable", "okhttp3/internal/connection/RealConnectionPool$cleanupRunnable$1", "Lokhttp3/internal/connection/RealConnectionPool$cleanupRunnable$1;", "cleanupRunning", "", "getCleanupRunning", "()Z", "setCleanupRunning", "(Z)V", "connections", "Ljava/util/ArrayDeque;", "Lokhttp3/internal/connection/RealConnection;", "keepAliveDurationNs", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "getRouteDatabase", "()Lokhttp3/internal/connection/RouteDatabase;", "cleanup", "now", "connectFailed", "", "failedRoute", "Lokhttp3/Route;", "failure", "Ljava/io/IOException;", "connectionBecameIdle", "connection", "connectionCount", "evictAll", "idleConnectionCount", "pruneAndGetAllocationCount", "put", "transmitterAcquirePooledConnection", "address", "Lokhttp3/Address;", "transmitter", "Lokhttp3/internal/connection/Transmitter;", "routes", "", "requireMultiplexed", "Companion", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: RealConnectionPool.kt */
public final class RealConnectionPool {
    public static final Companion Companion = new Companion(null);
    private static final ThreadPoolExecutor executor;
    private final RealConnectionPool$cleanupRunnable$1 cleanupRunnable = new RealConnectionPool$cleanupRunnable$1(this);
    private boolean cleanupRunning;
    private final ArrayDeque<RealConnection> connections = new ArrayDeque<>();
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;
    private final RouteDatabase routeDatabase = new RouteDatabase();

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, mo24952d2 = {"Lokhttp3/internal/connection/RealConnectionPool$Companion;", "", "()V", "executor", "Ljava/util/concurrent/ThreadPoolExecutor;", "get", "Lokhttp3/internal/connection/RealConnectionPool;", "connectionPool", "Lokhttp3/ConnectionPool;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: RealConnectionPool.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final RealConnectionPool get(ConnectionPool connectionPool) {
            Intrinsics.checkParameterIsNotNull(connectionPool, "connectionPool");
            return connectionPool.getDelegate$okhttp();
        }
    }

    public RealConnectionPool(int maxIdleConnections2, long keepAliveDuration, TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "timeUnit");
        this.maxIdleConnections = maxIdleConnections2;
        this.keepAliveDurationNs = timeUnit.toNanos(keepAliveDuration);
        if (!(keepAliveDuration > 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append("keepAliveDuration <= 0: ");
            sb.append(keepAliveDuration);
            throw new IllegalArgumentException(sb.toString().toString());
        }
    }

    public final RouteDatabase getRouteDatabase() {
        return this.routeDatabase;
    }

    public final boolean getCleanupRunning() {
        return this.cleanupRunning;
    }

    public final void setCleanupRunning(boolean z) {
        this.cleanupRunning = z;
    }

    public final synchronized int idleConnectionCount() {
        int count$iv;
        Iterable<RealConnection> $this$count$iv = this.connections;
        if (!($this$count$iv instanceof Collection) || !((Collection) $this$count$iv).isEmpty()) {
            count$iv = 0;
            for (RealConnection it : $this$count$iv) {
                if (it.getTransmitters().isEmpty()) {
                    count$iv++;
                    if (count$iv < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
        } else {
            count$iv = 0;
        }
        return count$iv;
    }

    public final synchronized int connectionCount() {
        return this.connections.size();
    }

    public final boolean transmitterAcquirePooledConnection(Address address, Transmitter transmitter, List<Route> routes, boolean requireMultiplexed) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        Intrinsics.checkParameterIsNotNull(transmitter, "transmitter");
        boolean holdsLock = Thread.holdsLock(this);
        if (!_Assertions.ENABLED || holdsLock) {
            Iterator it = this.connections.iterator();
            while (it.hasNext()) {
                RealConnection connection = (RealConnection) it.next();
                if ((!requireMultiplexed || connection.isMultiplexed()) && connection.isEligible$okhttp(address, routes)) {
                    Intrinsics.checkExpressionValueIsNotNull(connection, "connection");
                    transmitter.acquireConnectionNoEvents(connection);
                    return true;
                }
            }
            return false;
        }
        throw new AssertionError("Assertion failed");
    }

    public final void put(RealConnection connection) {
        Intrinsics.checkParameterIsNotNull(connection, "connection");
        boolean holdsLock = Thread.holdsLock(this);
        if (!_Assertions.ENABLED || holdsLock) {
            if (!this.cleanupRunning) {
                this.cleanupRunning = true;
                executor.execute(this.cleanupRunnable);
            }
            this.connections.add(connection);
            return;
        }
        throw new AssertionError("Assertion failed");
    }

    public final boolean connectionBecameIdle(RealConnection connection) {
        Intrinsics.checkParameterIsNotNull(connection, "connection");
        boolean holdsLock = Thread.holdsLock(this);
        if (_Assertions.ENABLED && !holdsLock) {
            throw new AssertionError("Assertion failed");
        } else if (connection.getNoNewExchanges() || this.maxIdleConnections == 0) {
            this.connections.remove(connection);
            return true;
        } else {
            notifyAll();
            return false;
        }
    }

    public final void evictAll() {
        List<RealConnection> evictedConnections = new ArrayList<>();
        synchronized (this) {
            Iterator i = this.connections.iterator();
            Intrinsics.checkExpressionValueIsNotNull(i, "connections.iterator()");
            while (i.hasNext()) {
                RealConnection connection = (RealConnection) i.next();
                if (connection.getTransmitters().isEmpty()) {
                    connection.setNoNewExchanges(true);
                    Intrinsics.checkExpressionValueIsNotNull(connection, "connection");
                    evictedConnections.add(connection);
                    i.remove();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        for (RealConnection connection2 : evictedConnections) {
            Util.closeQuietly(connection2.socket());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0060, code lost:
        if (r2 != null) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0062, code lost:
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0065, code lost:
        okhttp3.internal.Util.closeQuietly(r2.socket());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x006e, code lost:
        return 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long cleanup(long r12) {
        /*
            r11 = this;
            r0 = 0
            r1 = 0
            r2 = 0
            okhttp3.internal.connection.RealConnection r2 = (okhttp3.internal.connection.RealConnection) r2
            r3 = -9223372036854775808
            monitor-enter(r11)
            r5 = 0
            java.util.ArrayDeque<okhttp3.internal.connection.RealConnection> r6 = r11.connections     // Catch:{ all -> 0x006f }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x006f }
        L_0x000f:
            boolean r7 = r6.hasNext()     // Catch:{ all -> 0x006f }
            if (r7 == 0) goto L_0x0038
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x006f }
            okhttp3.internal.connection.RealConnection r7 = (okhttp3.internal.connection.RealConnection) r7     // Catch:{ all -> 0x006f }
            java.lang.String r8 = "connection"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)     // Catch:{ all -> 0x006f }
            int r8 = r11.pruneAndGetAllocationCount(r7, r12)     // Catch:{ all -> 0x006f }
            if (r8 <= 0) goto L_0x0029
            int r0 = r0 + 1
            goto L_0x000f
        L_0x0029:
            int r1 = r1 + 1
            long r8 = r7.getIdleAtNanos$okhttp()     // Catch:{ all -> 0x006f }
            long r8 = r12 - r8
            int r10 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r10 <= 0) goto L_0x000f
            r3 = r8
            r2 = r7
            goto L_0x000f
        L_0x0038:
            long r6 = r11.keepAliveDurationNs     // Catch:{ all -> 0x006f }
            int r8 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x0059
            int r6 = r11.maxIdleConnections     // Catch:{ all -> 0x006f }
            if (r1 <= r6) goto L_0x0045
            goto L_0x0059
        L_0x0045:
            if (r1 <= 0) goto L_0x004c
            long r6 = r11.keepAliveDurationNs     // Catch:{ all -> 0x006f }
            long r6 = r6 - r3
            monitor-exit(r11)
            return r6
        L_0x004c:
            if (r0 <= 0) goto L_0x0052
            long r6 = r11.keepAliveDurationNs     // Catch:{ all -> 0x006f }
            monitor-exit(r11)
            return r6
        L_0x0052:
            r6 = 0
            r11.cleanupRunning = r6     // Catch:{ all -> 0x006f }
            r5 = -1
            monitor-exit(r11)
            return r5
        L_0x0059:
            java.util.ArrayDeque<okhttp3.internal.connection.RealConnection> r6 = r11.connections     // Catch:{ all -> 0x006f }
            r6.remove(r2)     // Catch:{ all -> 0x006f }
            monitor-exit(r11)
            if (r2 != 0) goto L_0x0065
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0065:
            java.net.Socket r5 = r2.socket()
            okhttp3.internal.Util.closeQuietly(r5)
            r5 = 0
            return r5
        L_0x006f:
            r5 = move-exception
            monitor-exit(r11)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnectionPool.cleanup(long):long");
    }

    private final int pruneAndGetAllocationCount(RealConnection connection, long now) {
        List references = connection.getTransmitters();
        int i = 0;
        while (i < references.size()) {
            Reference reference = (Reference) references.get(i);
            if (reference.get() != null) {
                i++;
            } else if (reference != null) {
                TransmitterReference transmitterRef = (TransmitterReference) reference;
                StringBuilder sb = new StringBuilder();
                sb.append("A connection to ");
                sb.append(connection.route().address().url());
                sb.append(" was leaked. ");
                sb.append("Did you forget to close a response body?");
                Platform.Companion.get().logCloseableLeak(sb.toString(), transmitterRef.getCallStackTrace());
                references.remove(i);
                connection.setNoNewExchanges(true);
                if (references.isEmpty()) {
                    connection.setIdleAtNanos$okhttp(now - this.keepAliveDurationNs);
                    return 0;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type okhttp3.internal.connection.Transmitter.TransmitterReference");
            }
        }
        return references.size();
    }

    public final void connectFailed(Route failedRoute, IOException failure) {
        Intrinsics.checkParameterIsNotNull(failedRoute, "failedRoute");
        Intrinsics.checkParameterIsNotNull(failure, "failure");
        if (failedRoute.proxy().type() != Type.DIRECT) {
            Address address = failedRoute.address();
            address.proxySelector().connectFailed(address.url().uri(), failedRoute.proxy().address(), failure);
        }
        this.routeDatabase.failed(failedRoute);
    }

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp ConnectionPool", true));
        executor = threadPoolExecutor;
    }
}
