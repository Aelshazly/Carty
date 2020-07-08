package p008cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;
import p008cz.msebera.android.httpclient.conn.ClientConnectionManager;
import p008cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import p008cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import p008cz.msebera.android.httpclient.conn.ManagedClientConnection;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.conn.routing.RouteTracker;
import p008cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.SingleClientConnManager */
public class SingleClientConnManager implements ClientConnectionManager {
    public static final String MISUSE_MESSAGE = "Invalid use of SingleClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.";
    protected final boolean alwaysShutDown;
    protected final ClientConnectionOperator connOperator;
    protected volatile long connectionExpiresTime;
    protected volatile boolean isShutDown;
    protected volatile long lastReleaseTime;
    public HttpClientAndroidLog log;
    protected volatile ConnAdapter managedConn;
    protected final SchemeRegistry schemeRegistry;
    protected volatile PoolEntry uniquePoolEntry;

    /* renamed from: cz.msebera.android.httpclient.impl.conn.SingleClientConnManager$ConnAdapter */
    protected class ConnAdapter extends AbstractPooledConnAdapter {
        protected ConnAdapter(PoolEntry entry, HttpRoute route) {
            super(SingleClientConnManager.this, entry);
            markReusable();
            entry.route = route;
        }
    }

    /* renamed from: cz.msebera.android.httpclient.impl.conn.SingleClientConnManager$PoolEntry */
    protected class PoolEntry extends AbstractPoolEntry {
        protected PoolEntry() {
            super(SingleClientConnManager.this.connOperator, null);
        }

        /* access modifiers changed from: protected */
        public void close() throws IOException {
            shutdownEntry();
            if (this.connection.isOpen()) {
                this.connection.close();
            }
        }

        /* access modifiers changed from: protected */
        public void shutdown() throws IOException {
            shutdownEntry();
            if (this.connection.isOpen()) {
                this.connection.shutdown();
            }
        }
    }

    @Deprecated
    public SingleClientConnManager(HttpParams params, SchemeRegistry schreg) {
        this(schreg);
    }

    public SingleClientConnManager(SchemeRegistry schreg) {
        this.log = new HttpClientAndroidLog(getClass());
        Args.notNull(schreg, "Scheme registry");
        this.schemeRegistry = schreg;
        this.connOperator = createConnectionOperator(schreg);
        this.uniquePoolEntry = new PoolEntry();
        this.managedConn = null;
        this.lastReleaseTime = -1;
        this.alwaysShutDown = false;
        this.isShutDown = false;
    }

    public SingleClientConnManager() {
        this(SchemeRegistryFactory.createDefault());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }

    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    /* access modifiers changed from: protected */
    public ClientConnectionOperator createConnectionOperator(SchemeRegistry schreg) {
        return new DefaultClientConnectionOperator(schreg);
    }

    /* access modifiers changed from: protected */
    public final void assertStillUp() throws IllegalStateException {
        Asserts.check(!this.isShutDown, "Manager is shut down");
    }

    public final ClientConnectionRequest requestConnection(final HttpRoute route, final Object state) {
        return new ClientConnectionRequest() {
            public void abortRequest() {
            }

            public ManagedClientConnection getConnection(long timeout, TimeUnit tunit) {
                return SingleClientConnManager.this.getConnection(route, state);
            }
        };
    }

    public ManagedClientConnection getConnection(HttpRoute route, Object state) {
        ConnAdapter connAdapter;
        Args.notNull(route, "Route");
        assertStillUp();
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Get connection for route ");
            sb.append(route);
            httpClientAndroidLog.debug(sb.toString());
        }
        synchronized (this) {
            boolean z = true;
            Asserts.check(this.managedConn == null, MISUSE_MESSAGE);
            boolean recreate = false;
            boolean shutdown = false;
            closeExpiredConnections();
            if (this.uniquePoolEntry.connection.isOpen()) {
                RouteTracker tracker = this.uniquePoolEntry.tracker;
                if (tracker != null && tracker.toRoute().equals(route)) {
                    z = false;
                }
                shutdown = z;
            } else {
                recreate = true;
            }
            if (shutdown) {
                recreate = true;
                try {
                    this.uniquePoolEntry.shutdown();
                } catch (IOException iox) {
                    this.log.debug("Problem shutting down connection.", iox);
                }
            }
            if (recreate) {
                this.uniquePoolEntry = new PoolEntry();
            }
            this.managedConn = new ConnAdapter(this.uniquePoolEntry, route);
            connAdapter = this.managedConn;
        }
        return connAdapter;
    }

    public void releaseConnection(ManagedClientConnection conn, long validDuration, TimeUnit timeUnit) {
        Args.check(conn instanceof ConnAdapter, "Connection class mismatch, connection not obtained from this manager");
        assertStillUp();
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Releasing connection ");
            sb.append(conn);
            httpClientAndroidLog.debug(sb.toString());
        }
        ConnAdapter sca = (ConnAdapter) conn;
        synchronized (sca) {
            if (sca.poolEntry != null) {
                Asserts.check(sca.getManager() == this, "Connection not obtained from this manager");
                try {
                    if (sca.isOpen() && (this.alwaysShutDown || !sca.isMarkedReusable())) {
                        if (this.log.isDebugEnabled()) {
                            this.log.debug("Released connection open but not reusable.");
                        }
                        sca.shutdown();
                    }
                    sca.detach();
                    synchronized (this) {
                        try {
                            this.managedConn = null;
                            this.lastReleaseTime = System.currentTimeMillis();
                            if (validDuration > 0) {
                                this.connectionExpiresTime = timeUnit.toMillis(validDuration) + this.lastReleaseTime;
                            } else {
                                this.connectionExpiresTime = LongCompanionObject.MAX_VALUE;
                            }
                        } catch (Throwable th) {
                            th = th;
                            throw th;
                        }
                    }
                } catch (IOException iox) {
                    try {
                        if (this.log.isDebugEnabled()) {
                            this.log.debug("Exception shutting down released connection.", iox);
                        }
                        sca.detach();
                        synchronized (this) {
                            this.managedConn = null;
                            this.lastReleaseTime = System.currentTimeMillis();
                            if (validDuration > 0) {
                                this.connectionExpiresTime = timeUnit.toMillis(validDuration) + this.lastReleaseTime;
                            } else {
                                this.connectionExpiresTime = LongCompanionObject.MAX_VALUE;
                            }
                        }
                    } catch (Throwable th2) {
                        while (true) {
                            th = th2;
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    while (true) {
                        th = th3;
                    }
                    throw th;
                }
            }
        }
    }

    public void closeExpiredConnections() {
        if (System.currentTimeMillis() >= this.connectionExpiresTime) {
            closeIdleConnections(0, TimeUnit.MILLISECONDS);
        }
    }

    public void closeIdleConnections(long idletime, TimeUnit tunit) {
        assertStillUp();
        Args.notNull(tunit, "Time unit");
        synchronized (this) {
            if (this.managedConn == null && this.uniquePoolEntry.connection.isOpen()) {
                if (this.lastReleaseTime <= System.currentTimeMillis() - tunit.toMillis(idletime)) {
                    try {
                        this.uniquePoolEntry.close();
                    } catch (IOException iox) {
                        this.log.debug("Problem closing idle connection.", iox);
                    }
                }
            }
        }
    }

    public void shutdown() {
        this.isShutDown = true;
        synchronized (this) {
            try {
                if (this.uniquePoolEntry != null) {
                    this.uniquePoolEntry.shutdown();
                }
                this.uniquePoolEntry = null;
            } catch (IOException iox) {
                try {
                    this.log.debug("Problem while shutting down manager.", iox);
                    this.uniquePoolEntry = null;
                } catch (Throwable th) {
                    this.uniquePoolEntry = null;
                    this.managedConn = null;
                    throw th;
                }
            }
            this.managedConn = null;
        }
    }

    /* access modifiers changed from: protected */
    public void revokeConnection() {
        ConnAdapter conn = this.managedConn;
        if (conn != null) {
            conn.detach();
            synchronized (this) {
                try {
                    this.uniquePoolEntry.shutdown();
                } catch (IOException iox) {
                    this.log.debug("Problem while shutting down connection.", iox);
                }
            }
        }
    }
}
