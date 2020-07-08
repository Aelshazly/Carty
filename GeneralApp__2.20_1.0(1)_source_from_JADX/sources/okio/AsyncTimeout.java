package okio;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00172\u00020\u0001:\u0002\u0017\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u0004J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\rJ\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\rJ\u0012\u0010\u000f\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0014J\u0010\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0002J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\tH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0000X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, mo24952d2 = {"Lokio/AsyncTimeout;", "Lokio/Timeout;", "()V", "inQueue", "", "next", "timeoutAt", "", "enter", "", "exit", "Ljava/io/IOException;", "cause", "exit$jvm", "throwOnTimeout", "newTimeoutException", "remainingNanos", "now", "sink", "Lokio/Sink;", "source", "Lokio/Source;", "timedOut", "Companion", "Watchdog", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
/* compiled from: AsyncTimeout.kt */
public class AsyncTimeout extends Timeout {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public static final long IDLE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(60);
    /* access modifiers changed from: private */
    public static final long IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(IDLE_TIMEOUT_MILLIS);
    private static final int TIMEOUT_WRITE_SIZE = 65536;
    /* access modifiers changed from: private */
    public static AsyncTimeout head;
    private boolean inQueue;
    /* access modifiers changed from: private */
    public AsyncTimeout next;
    /* access modifiers changed from: private */
    public long timeoutAt;

    @Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\n\u001a\u0004\u0018\u00010\tH\u0000¢\u0006\u0002\b\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0002J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, mo24952d2 = {"Lokio/AsyncTimeout$Companion;", "", "()V", "IDLE_TIMEOUT_MILLIS", "", "IDLE_TIMEOUT_NANOS", "TIMEOUT_WRITE_SIZE", "", "head", "Lokio/AsyncTimeout;", "awaitTimeout", "awaitTimeout$jvm", "cancelScheduledTimeout", "", "node", "scheduleTimeout", "", "timeoutNanos", "hasDeadline", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
    /* compiled from: AsyncTimeout.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* access modifiers changed from: private */
        public final void scheduleTimeout(AsyncTimeout node, long timeoutNanos, boolean hasDeadline) {
            synchronized (AsyncTimeout.class) {
                if (AsyncTimeout.head == null) {
                    AsyncTimeout.head = new AsyncTimeout();
                    new Watchdog().start();
                }
                long now = System.nanoTime();
                if (timeoutNanos != 0 && hasDeadline) {
                    node.timeoutAt = Math.min(timeoutNanos, node.deadlineNanoTime() - now) + now;
                } else if (timeoutNanos != 0) {
                    node.timeoutAt = now + timeoutNanos;
                } else if (hasDeadline) {
                    node.timeoutAt = node.deadlineNanoTime();
                } else {
                    throw new AssertionError();
                }
                long remainingNanos = node.remainingNanos(now);
                AsyncTimeout prev = AsyncTimeout.head;
                if (prev == null) {
                    Intrinsics.throwNpe();
                }
                while (true) {
                    if (prev.next == null) {
                        break;
                    }
                    AsyncTimeout access$getNext$p = prev.next;
                    if (access$getNext$p == null) {
                        Intrinsics.throwNpe();
                    }
                    if (remainingNanos < access$getNext$p.remainingNanos(now)) {
                        break;
                    }
                    AsyncTimeout access$getNext$p2 = prev.next;
                    if (access$getNext$p2 == null) {
                        Intrinsics.throwNpe();
                    }
                    prev = access$getNext$p2;
                }
                node.next = prev.next;
                prev.next = node;
                if (prev == AsyncTimeout.head) {
                    AsyncTimeout.class.notify();
                }
                Unit unit = Unit.INSTANCE;
            }
        }

        /* access modifiers changed from: private */
        public final boolean cancelScheduledTimeout(AsyncTimeout node) {
            synchronized (AsyncTimeout.class) {
                for (AsyncTimeout prev = AsyncTimeout.head; prev != null; prev = prev.next) {
                    if (prev.next == node) {
                        prev.next = node.next;
                        node.next = null;
                        return false;
                    }
                }
                return true;
            }
        }

        public final AsyncTimeout awaitTimeout$jvm() throws InterruptedException {
            AsyncTimeout access$getHead$cp = AsyncTimeout.head;
            if (access$getHead$cp == null) {
                Intrinsics.throwNpe();
            }
            AsyncTimeout node = access$getHead$cp.next;
            AsyncTimeout asyncTimeout = null;
            if (node == null) {
                long startNanos = System.nanoTime();
                AsyncTimeout.class.wait(AsyncTimeout.IDLE_TIMEOUT_MILLIS);
                AsyncTimeout access$getHead$cp2 = AsyncTimeout.head;
                if (access$getHead$cp2 == null) {
                    Intrinsics.throwNpe();
                }
                if (access$getHead$cp2.next == null && System.nanoTime() - startNanos >= AsyncTimeout.IDLE_TIMEOUT_NANOS) {
                    asyncTimeout = AsyncTimeout.head;
                }
                return asyncTimeout;
            }
            long waitNanos = node.remainingNanos(System.nanoTime());
            if (waitNanos > 0) {
                long waitMillis = waitNanos / 1000000;
                AsyncTimeout.class.wait(waitMillis, (int) (waitNanos - (1000000 * waitMillis)));
                return null;
            }
            AsyncTimeout access$getHead$cp3 = AsyncTimeout.head;
            if (access$getHead$cp3 == null) {
                Intrinsics.throwNpe();
            }
            access$getHead$cp3.next = node.next;
            node.next = null;
            return node;
        }
    }

    @Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, mo24952d2 = {"Lokio/AsyncTimeout$Watchdog;", "Ljava/lang/Thread;", "()V", "run", "", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
    /* compiled from: AsyncTimeout.kt */
    private static final class Watchdog extends Thread {
        public Watchdog() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0021, code lost:
            if (r2 == null) goto L_0x0001;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0023, code lost:
            r2.timedOut();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r5 = this;
                r0 = 0
            L_0x0001:
                r1 = 0
                r2 = r1
                okio.AsyncTimeout r2 = (okio.AsyncTimeout) r2     // Catch:{ InterruptedException -> 0x002a }
                java.lang.Class<okio.AsyncTimeout> r3 = okio.AsyncTimeout.class
                monitor-enter(r3)     // Catch:{ InterruptedException -> 0x002a }
                okio.AsyncTimeout$Companion r4 = okio.AsyncTimeout.Companion     // Catch:{ all -> 0x0027 }
                okio.AsyncTimeout r4 = r4.awaitTimeout$jvm()     // Catch:{ all -> 0x0027 }
                r2 = r4
                okio.AsyncTimeout r4 = okio.AsyncTimeout.head     // Catch:{ all -> 0x0027 }
                if (r2 != r4) goto L_0x001e
                okio.AsyncTimeout r1 = (okio.AsyncTimeout) r1     // Catch:{ all -> 0x0027 }
                okio.AsyncTimeout.head = r1     // Catch:{ all -> 0x0027 }
                monitor-exit(r3)     // Catch:{ InterruptedException -> 0x002a }
                return
            L_0x001e:
                kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0027 }
                monitor-exit(r3)     // Catch:{ InterruptedException -> 0x002a }
                if (r2 == 0) goto L_0x002b
                r2.timedOut()     // Catch:{ InterruptedException -> 0x002a }
                goto L_0x002b
            L_0x0027:
                r1 = move-exception
                monitor-exit(r3)     // Catch:{ InterruptedException -> 0x002a }
                throw r1     // Catch:{ InterruptedException -> 0x002a }
            L_0x002a:
                r1 = move-exception
            L_0x002b:
                goto L_0x0001
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.AsyncTimeout.Watchdog.run():void");
        }
    }

    public final void enter() {
        if (!this.inQueue) {
            long timeoutNanos = timeoutNanos();
            boolean hasDeadline = hasDeadline();
            if (timeoutNanos != 0 || hasDeadline) {
                this.inQueue = true;
                Companion.scheduleTimeout(this, timeoutNanos, hasDeadline);
                return;
            }
            return;
        }
        throw new IllegalStateException("Unbalanced enter/exit".toString());
    }

    public final boolean exit() {
        if (!this.inQueue) {
            return false;
        }
        this.inQueue = false;
        return Companion.cancelScheduledTimeout(this);
    }

    /* access modifiers changed from: private */
    public final long remainingNanos(long now) {
        return this.timeoutAt - now;
    }

    /* access modifiers changed from: protected */
    public void timedOut() {
    }

    public final Sink sink(Sink sink) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        return new AsyncTimeout$sink$1(this, sink);
    }

    public final Source source(Source source) {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        return new AsyncTimeout$source$1(this, source);
    }

    public final void exit$jvm(boolean throwOnTimeout) {
        if (exit() && throwOnTimeout) {
            throw newTimeoutException(null);
        }
    }

    public final IOException exit$jvm(IOException cause) {
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        return !exit() ? cause : newTimeoutException(cause);
    }

    /* access modifiers changed from: protected */
    public IOException newTimeoutException(IOException cause) {
        InterruptedIOException e = new InterruptedIOException("timeout");
        if (cause != null) {
            e.initCause(cause);
        }
        return e;
    }
}
