package p008cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;
import p008cz.msebera.android.httpclient.HttpConnection;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.IdleConnectionHandler */
public class IdleConnectionHandler {
    private final Map<HttpConnection, TimeValues> connectionToTimes = new HashMap();
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    /* renamed from: cz.msebera.android.httpclient.impl.conn.IdleConnectionHandler$TimeValues */
    private static class TimeValues {
        /* access modifiers changed from: private */
        public final long timeAdded;
        /* access modifiers changed from: private */
        public final long timeExpires;

        TimeValues(long now, long validDuration, TimeUnit validUnit) {
            this.timeAdded = now;
            if (validDuration > 0) {
                this.timeExpires = validUnit.toMillis(validDuration) + now;
            } else {
                this.timeExpires = LongCompanionObject.MAX_VALUE;
            }
        }
    }

    public void add(HttpConnection connection, long validDuration, TimeUnit unit) {
        long timeAdded = System.currentTimeMillis();
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Adding connection at: ");
            sb.append(timeAdded);
            httpClientAndroidLog.debug(sb.toString());
        }
        Map<HttpConnection, TimeValues> map = this.connectionToTimes;
        TimeValues timeValues = new TimeValues(timeAdded, validDuration, unit);
        map.put(connection, timeValues);
    }

    public boolean remove(HttpConnection connection) {
        TimeValues times = (TimeValues) this.connectionToTimes.remove(connection);
        boolean z = true;
        if (times == null) {
            this.log.warn("Removing a connection that never existed!");
            return true;
        }
        if (System.currentTimeMillis() > times.timeExpires) {
            z = false;
        }
        return z;
    }

    public void removeAll() {
        this.connectionToTimes.clear();
    }

    public void closeIdleConnections(long idleTime) {
        long idleTimeout = System.currentTimeMillis() - idleTime;
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Checking for connections, idle timeout: ");
            sb.append(idleTimeout);
            httpClientAndroidLog.debug(sb.toString());
        }
        for (Entry<HttpConnection, TimeValues> entry : this.connectionToTimes.entrySet()) {
            HttpConnection conn = (HttpConnection) entry.getKey();
            long connectionTime = ((TimeValues) entry.getValue()).timeAdded;
            if (connectionTime <= idleTimeout) {
                if (this.log.isDebugEnabled()) {
                    HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Closing idle connection, connection time: ");
                    sb2.append(connectionTime);
                    httpClientAndroidLog2.debug(sb2.toString());
                }
                try {
                    conn.close();
                } catch (IOException ex) {
                    this.log.debug("I/O error closing connection", ex);
                }
            }
        }
    }

    public void closeExpiredConnections() {
        long now = System.currentTimeMillis();
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Checking for expired connections, now: ");
            sb.append(now);
            httpClientAndroidLog.debug(sb.toString());
        }
        for (Entry<HttpConnection, TimeValues> entry : this.connectionToTimes.entrySet()) {
            HttpConnection conn = (HttpConnection) entry.getKey();
            TimeValues times = (TimeValues) entry.getValue();
            if (times.timeExpires <= now) {
                if (this.log.isDebugEnabled()) {
                    HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Closing connection, expired @: ");
                    sb2.append(times.timeExpires);
                    httpClientAndroidLog2.debug(sb2.toString());
                }
                try {
                    conn.close();
                } catch (IOException ex) {
                    this.log.debug("I/O error closing connection", ex);
                }
            }
        }
    }
}
