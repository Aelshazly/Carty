package p008cz.msebera.android.httpclient.impl.conn.tsccm;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.tsccm.WaitingThread */
public class WaitingThread {
    private boolean aborted;
    private final Condition cond;
    private final RouteSpecificPool pool;
    private Thread waiter;

    public WaitingThread(Condition cond2, RouteSpecificPool pool2) {
        Args.notNull(cond2, "Condition");
        this.cond = cond2;
        this.pool = pool2;
    }

    public final Condition getCondition() {
        return this.cond;
    }

    public final RouteSpecificPool getPool() {
        return this.pool;
    }

    public final Thread getThread() {
        return this.waiter;
    }

    public boolean await(Date deadline) throws InterruptedException {
        boolean success;
        if (this.waiter == null) {
            String str = "Operation interrupted";
            if (!this.aborted) {
                this.waiter = Thread.currentThread();
                if (deadline != null) {
                    try {
                        success = this.cond.awaitUntil(deadline);
                    } catch (Throwable th) {
                        this.waiter = null;
                        throw th;
                    }
                } else {
                    this.cond.await();
                    success = true;
                }
                if (!this.aborted) {
                    this.waiter = null;
                    return success;
                }
                throw new InterruptedException(str);
            }
            throw new InterruptedException(str);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("A thread is already waiting on this object.\ncaller: ");
        sb.append(Thread.currentThread());
        sb.append("\nwaiter: ");
        sb.append(this.waiter);
        throw new IllegalStateException(sb.toString());
    }

    public void wakeup() {
        if (this.waiter != null) {
            this.cond.signalAll();
            return;
        }
        throw new IllegalStateException("Nobody waiting on this object.");
    }

    public void interrupt() {
        this.aborted = true;
        this.cond.signalAll();
    }
}
