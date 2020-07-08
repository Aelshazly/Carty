package p008cz.msebera.android.httpclient.impl.client;

import java.util.HashMap;
import java.util.Map;
import p008cz.msebera.android.httpclient.client.BackoffManager;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.pool.ConnPoolControl;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.client.AIMDBackoffManager */
public class AIMDBackoffManager implements BackoffManager {
    private double backoffFactor;
    private int cap;
    private final Clock clock;
    private final ConnPoolControl<HttpRoute> connPerRoute;
    private long coolDown;
    private final Map<HttpRoute, Long> lastRouteBackoffs;
    private final Map<HttpRoute, Long> lastRouteProbes;

    public AIMDBackoffManager(ConnPoolControl<HttpRoute> connPerRoute2) {
        this(connPerRoute2, new SystemClock());
    }

    AIMDBackoffManager(ConnPoolControl<HttpRoute> connPerRoute2, Clock clock2) {
        this.coolDown = 5000;
        this.backoffFactor = 0.5d;
        this.cap = 2;
        this.clock = clock2;
        this.connPerRoute = connPerRoute2;
        this.lastRouteProbes = new HashMap();
        this.lastRouteBackoffs = new HashMap();
    }

    public void backOff(HttpRoute route) {
        synchronized (this.connPerRoute) {
            int curr = this.connPerRoute.getMaxPerRoute(route);
            Long lastUpdate = getLastUpdate(this.lastRouteBackoffs, route);
            long now = this.clock.getCurrentTime();
            if (now - lastUpdate.longValue() >= this.coolDown) {
                this.connPerRoute.setMaxPerRoute(route, getBackedOffPoolSize(curr));
                this.lastRouteBackoffs.put(route, Long.valueOf(now));
            }
        }
    }

    private int getBackedOffPoolSize(int curr) {
        if (curr <= 1) {
            return 1;
        }
        return (int) Math.floor(this.backoffFactor * ((double) curr));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void probe(p008cz.msebera.android.httpclient.conn.routing.HttpRoute r13) {
        /*
            r12 = this;
            cz.msebera.android.httpclient.pool.ConnPoolControl<cz.msebera.android.httpclient.conn.routing.HttpRoute> r0 = r12.connPerRoute
            monitor-enter(r0)
            cz.msebera.android.httpclient.pool.ConnPoolControl<cz.msebera.android.httpclient.conn.routing.HttpRoute> r1 = r12.connPerRoute     // Catch:{ all -> 0x0050 }
            int r1 = r1.getMaxPerRoute(r13)     // Catch:{ all -> 0x0050 }
            int r2 = r12.cap     // Catch:{ all -> 0x0050 }
            if (r1 < r2) goto L_0x0010
            int r2 = r12.cap     // Catch:{ all -> 0x0050 }
            goto L_0x0012
        L_0x0010:
            int r2 = r1 + 1
        L_0x0012:
            java.util.Map<cz.msebera.android.httpclient.conn.routing.HttpRoute, java.lang.Long> r3 = r12.lastRouteProbes     // Catch:{ all -> 0x0050 }
            java.lang.Long r3 = r12.getLastUpdate(r3, r13)     // Catch:{ all -> 0x0050 }
            java.util.Map<cz.msebera.android.httpclient.conn.routing.HttpRoute, java.lang.Long> r4 = r12.lastRouteBackoffs     // Catch:{ all -> 0x0050 }
            java.lang.Long r4 = r12.getLastUpdate(r4, r13)     // Catch:{ all -> 0x0050 }
            cz.msebera.android.httpclient.impl.client.Clock r5 = r12.clock     // Catch:{ all -> 0x0050 }
            long r5 = r5.getCurrentTime()     // Catch:{ all -> 0x0050 }
            long r7 = r3.longValue()     // Catch:{ all -> 0x0050 }
            long r7 = r5 - r7
            long r9 = r12.coolDown     // Catch:{ all -> 0x0050 }
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 < 0) goto L_0x004e
            long r7 = r4.longValue()     // Catch:{ all -> 0x0050 }
            long r7 = r5 - r7
            long r9 = r12.coolDown     // Catch:{ all -> 0x0050 }
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 >= 0) goto L_0x003d
            goto L_0x004e
        L_0x003d:
            cz.msebera.android.httpclient.pool.ConnPoolControl<cz.msebera.android.httpclient.conn.routing.HttpRoute> r7 = r12.connPerRoute     // Catch:{ all -> 0x0050 }
            r7.setMaxPerRoute(r13, r2)     // Catch:{ all -> 0x0050 }
            java.util.Map<cz.msebera.android.httpclient.conn.routing.HttpRoute, java.lang.Long> r7 = r12.lastRouteProbes     // Catch:{ all -> 0x0050 }
            java.lang.Long r8 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0050 }
            r7.put(r13, r8)     // Catch:{ all -> 0x0050 }
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            return
        L_0x004e:
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            return
        L_0x0050:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.client.AIMDBackoffManager.probe(cz.msebera.android.httpclient.conn.routing.HttpRoute):void");
    }

    private Long getLastUpdate(Map<HttpRoute, Long> updates, HttpRoute route) {
        Long lastUpdate = (Long) updates.get(route);
        if (lastUpdate == null) {
            return Long.valueOf(0);
        }
        return lastUpdate;
    }

    public void setBackoffFactor(double d) {
        Args.check(d > 0.0d && d < 1.0d, "Backoff factor must be 0.0 < f < 1.0");
        this.backoffFactor = d;
    }

    public void setCooldownMillis(long l) {
        Args.positive(this.coolDown, "Cool down");
        this.coolDown = l;
    }

    public void setPerHostConnectionCap(int cap2) {
        Args.positive(cap2, "Per host connection cap");
        this.cap = cap2;
    }
}
