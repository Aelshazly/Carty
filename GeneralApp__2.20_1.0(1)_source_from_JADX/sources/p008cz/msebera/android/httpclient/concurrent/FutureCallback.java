package p008cz.msebera.android.httpclient.concurrent;

/* renamed from: cz.msebera.android.httpclient.concurrent.FutureCallback */
public interface FutureCallback<T> {
    void cancelled();

    void completed(T t);

    void failed(Exception exc);
}
