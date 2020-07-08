package p008cz.msebera.android.httpclient.pool;

import java.util.concurrent.Future;
import p008cz.msebera.android.httpclient.concurrent.FutureCallback;

/* renamed from: cz.msebera.android.httpclient.pool.ConnPool */
public interface ConnPool<T, E> {
    Future<E> lease(T t, Object obj, FutureCallback<E> futureCallback);

    void release(E e, boolean z);
}
