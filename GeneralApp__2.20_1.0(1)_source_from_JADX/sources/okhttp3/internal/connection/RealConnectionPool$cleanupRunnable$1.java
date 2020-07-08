package okhttp3.internal.connection;

import kotlin.Metadata;
import okhttp3.internal.Util;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, mo24952d2 = {"okhttp3/internal/connection/RealConnectionPool$cleanupRunnable$1", "Ljava/lang/Runnable;", "run", "", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: RealConnectionPool.kt */
public final class RealConnectionPool$cleanupRunnable$1 implements Runnable {
    final /* synthetic */ RealConnectionPool this$0;

    RealConnectionPool$cleanupRunnable$1(RealConnectionPool $outer) {
        this.this$0 = $outer;
    }

    public void run() {
        while (true) {
            long waitNanos = this.this$0.cleanup(System.nanoTime());
            if (waitNanos != -1) {
                try {
                    Util.lockAndWaitNanos(this.this$0, waitNanos);
                } catch (InterruptedException e) {
                    this.this$0.evictAll();
                }
            } else {
                return;
            }
        }
    }
}
