package p008cz.msebera.android.httpclient.impl.conn.tsccm;

import java.util.concurrent.TimeUnit;
import p008cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.tsccm.PoolEntryRequest */
public interface PoolEntryRequest {
    void abortRequest();

    BasicPoolEntry getPoolEntry(long j, TimeUnit timeUnit) throws InterruptedException, ConnectionPoolTimeoutException;
}
