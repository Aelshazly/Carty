package p008cz.msebera.android.httpclient.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.concurrent.BasicFuture */
public class BasicFuture<T> implements Future<T>, Cancellable {
    private final FutureCallback<T> callback;
    private volatile boolean cancelled;
    private volatile boolean completed;

    /* renamed from: ex */
    private volatile Exception f125ex;
    private volatile T result;

    public BasicFuture(FutureCallback<T> callback2) {
        this.callback = callback2;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public boolean isDone() {
        return this.completed;
    }

    private T getResult() throws ExecutionException {
        if (this.f125ex == null) {
            return this.result;
        }
        throw new ExecutionException(this.f125ex);
    }

    public synchronized T get() throws InterruptedException, ExecutionException {
        while (!this.completed) {
            wait();
        }
        return getResult();
    }

    public synchronized T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        Args.notNull(unit, "Time unit");
        long msecs = unit.toMillis(timeout);
        long startTime = msecs <= 0 ? 0 : System.currentTimeMillis();
        long waitTime = msecs;
        if (this.completed) {
            return getResult();
        } else if (waitTime > 0) {
            while (true) {
                wait(waitTime);
                if (this.completed) {
                    return getResult();
                }
                waitTime = msecs - (System.currentTimeMillis() - startTime);
                if (waitTime <= 0) {
                    throw new TimeoutException();
                }
            }
        } else {
            throw new TimeoutException();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        if (r1 == null) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        r1.completed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        r1 = r2.callback;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean completed(T r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.completed     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0008
            r0 = 0
            monitor-exit(r2)     // Catch:{ all -> 0x0019 }
            return r0
        L_0x0008:
            r0 = 1
            r2.completed = r0     // Catch:{ all -> 0x0019 }
            r2.result = r3     // Catch:{ all -> 0x0019 }
            r2.notifyAll()     // Catch:{ all -> 0x0019 }
            monitor-exit(r2)     // Catch:{ all -> 0x0019 }
            cz.msebera.android.httpclient.concurrent.FutureCallback<T> r1 = r2.callback
            if (r1 == 0) goto L_0x0018
            r1.completed(r3)
        L_0x0018:
            return r0
        L_0x0019:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0019 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.concurrent.BasicFuture.completed(java.lang.Object):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        if (r1 == null) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        r1.failed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        r1 = r2.callback;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean failed(java.lang.Exception r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.completed     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0008
            r0 = 0
            monitor-exit(r2)     // Catch:{ all -> 0x0019 }
            return r0
        L_0x0008:
            r0 = 1
            r2.completed = r0     // Catch:{ all -> 0x0019 }
            r2.f125ex = r3     // Catch:{ all -> 0x0019 }
            r2.notifyAll()     // Catch:{ all -> 0x0019 }
            monitor-exit(r2)     // Catch:{ all -> 0x0019 }
            cz.msebera.android.httpclient.concurrent.FutureCallback<T> r1 = r2.callback
            if (r1 == 0) goto L_0x0018
            r1.failed(r3)
        L_0x0018:
            return r0
        L_0x0019:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0019 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.concurrent.BasicFuture.failed(java.lang.Exception):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        if (r1 == null) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        r1.cancelled();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        r1 = r2.callback;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean cancel(boolean r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.completed     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0008
            r0 = 0
            monitor-exit(r2)     // Catch:{ all -> 0x0019 }
            return r0
        L_0x0008:
            r0 = 1
            r2.completed = r0     // Catch:{ all -> 0x0019 }
            r2.cancelled = r0     // Catch:{ all -> 0x0019 }
            r2.notifyAll()     // Catch:{ all -> 0x0019 }
            monitor-exit(r2)     // Catch:{ all -> 0x0019 }
            cz.msebera.android.httpclient.concurrent.FutureCallback<T> r1 = r2.callback
            if (r1 == 0) goto L_0x0018
            r1.cancelled()
        L_0x0018:
            return r0
        L_0x0019:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0019 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.concurrent.BasicFuture.cancel(boolean):boolean");
    }

    public boolean cancel() {
        return cancel(true);
    }
}
