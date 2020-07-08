package p008cz.msebera.android.httpclient.pool;

import java.io.IOException;

/* renamed from: cz.msebera.android.httpclient.pool.ConnFactory */
public interface ConnFactory<T, C> {
    C create(T t) throws IOException;
}
