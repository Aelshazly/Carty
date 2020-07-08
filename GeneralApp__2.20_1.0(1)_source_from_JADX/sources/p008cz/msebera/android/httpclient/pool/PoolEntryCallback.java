package p008cz.msebera.android.httpclient.pool;

/* renamed from: cz.msebera.android.httpclient.pool.PoolEntryCallback */
public interface PoolEntryCallback<T, C> {
    void process(PoolEntry<T, C> poolEntry);
}
