package p008cz.msebera.android.httpclient.pool;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import p008cz.msebera.android.httpclient.concurrent.FutureCallback;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.pool.PoolEntryFuture */
abstract class PoolEntryFuture<T> implements Future<T> {
    private final FutureCallback<T> callback;
    private volatile boolean cancelled;
    private volatile boolean completed;
    private final Condition condition;
    private final Lock lock;
    private T result;

    /* access modifiers changed from: protected */
    public abstract T getPoolEntry(long j, TimeUnit timeUnit) throws IOException, InterruptedException, TimeoutException;

    PoolEntryFuture(Lock lock2, FutureCallback<T> callback2) {
        this.lock = lock2;
        this.condition = lock2.newCondition();
        this.callback = callback2;
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        boolean z;
        this.lock.lock();
        try {
            if (this.completed) {
                z = false;
            } else {
                z = true;
                this.completed = true;
                this.cancelled = true;
                if (this.callback != null) {
                    this.callback.cancelled();
                }
                this.condition.signalAll();
            }
            return z;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public boolean isDone() {
        return this.completed;
    }

    public T get() throws InterruptedException, ExecutionException {
        try {
            return get(0, TimeUnit.MILLISECONDS);
        } catch (TimeoutException ex) {
            throw new ExecutionException(ex);
        }
    }

    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        T t;
        Args.notNull(unit, "Time unit");
        this.lock.lock();
        try {
            if (this.completed) {
                t = this.result;
            } else {
                this.result = getPoolEntry(timeout, unit);
                this.completed = true;
                if (this.callback != null) {
                    this.callback.completed(this.result);
                }
                t = this.result;
            }
            this.lock.unlock();
            return t;
        } catch (IOException ex) {
            this.completed = true;
            this.result = null;
            if (this.callback != null) {
                this.callback.failed(ex);
            }
            throw new ExecutionException(ex);
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    public boolean await(Date deadline) throws InterruptedException {
        boolean success;
        this.lock.lock();
        try {
            String str = "Operation interrupted";
            if (!this.cancelled) {
                if (deadline != null) {
                    success = this.condition.awaitUntil(deadline);
                } else {
                    this.condition.await();
                    success = true;
                }
                if (!this.cancelled) {
                    return success;
                }
                throw new InterruptedException(str);
            }
            throw new InterruptedException(str);
        } finally {
            this.lock.unlock();
        }
    }

    public void wakeup() {
        this.lock.lock();
        try {
            this.condition.signalAll();
        } finally {
            this.lock.unlock();
        }
    }
}
