package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0018\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, mo24952d2 = {"okio/Pipe$sink$1", "Lokio/Sink;", "timeout", "Lokio/Timeout;", "close", "", "flush", "write", "source", "Lokio/Buffer;", "byteCount", "", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
/* compiled from: Pipe.kt */
public final class Pipe$sink$1 implements Sink {
    final /* synthetic */ Pipe this$0;
    private final Timeout timeout = new Timeout();

    Pipe$sink$1(Pipe $outer) {
        this.this$0 = $outer;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008e, code lost:
        if (r5 == null) goto L_0x0145;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0090, code lost:
        r6 = r1.this$0;
        r8 = r5;
        r10 = r8.timeout();
        r11 = r6.sink().timeout();
        r13 = r10.timeoutNanos();
        r16 = r5;
        r17 = r6;
        r10.timeout(okio.Timeout.Companion.minTimeout(r11.timeoutNanos(), r10.timeoutNanos()), java.util.concurrent.TimeUnit.NANOSECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c0, code lost:
        if (r10.hasDeadline() == false) goto L_0x010e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00c2, code lost:
        r5 = r10.deadlineNanoTime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ca, code lost:
        if (r11.hasDeadline() == false) goto L_0x00de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00cc, code lost:
        r18 = r5;
        r10.deadlineNanoTime(java.lang.Math.min(r10.deadlineNanoTime(), r11.deadlineNanoTime()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00de, code lost:
        r18 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r8.write(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00e7, code lost:
        r10.timeout(r13, java.util.concurrent.TimeUnit.NANOSECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00f0, code lost:
        if (r11.hasDeadline() == false) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00f2, code lost:
        r10.deadlineNanoTime(r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00f8, code lost:
        r5 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00fb, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00fc, code lost:
        r5 = r18;
        r1 = r0;
        r10.timeout(r13, java.util.concurrent.TimeUnit.NANOSECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0108, code lost:
        if (r11.hasDeadline() != false) goto L_0x010a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x010a, code lost:
        r10.deadlineNanoTime(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x010d, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0112, code lost:
        if (r11.hasDeadline() == false) goto L_0x011b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0114, code lost:
        r10.deadlineNanoTime(r11.deadlineNanoTime());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r8.write(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0122, code lost:
        r10.timeout(r13, java.util.concurrent.TimeUnit.NANOSECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x012b, code lost:
        if (r11.hasDeadline() == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x012d, code lost:
        r10.clearDeadline();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0134, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0135, code lost:
        r1 = r0;
        r10.timeout(r13, java.util.concurrent.TimeUnit.NANOSECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x013f, code lost:
        if (r11.hasDeadline() != false) goto L_0x0141;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0141, code lost:
        r10.clearDeadline();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0144, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0145, code lost:
        r16 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(okio.Buffer r21, long r22) {
        /*
            r20 = this;
            r1 = r20
            r2 = r21
            java.lang.String r0 = "source"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            r3 = r22
            r0 = 0
            r5 = r0
            okio.Sink r5 = (okio.Sink) r5
            okio.Pipe r0 = r1.this$0
            okio.Buffer r6 = r0.getBuffer$jvm()
            monitor-enter(r6)
            r0 = 0
            r7 = r0
            okio.Pipe r8 = r1.this$0     // Catch:{ all -> 0x015b }
            boolean r8 = r8.getSinkClosed$jvm()     // Catch:{ all -> 0x015b }
            r8 = r8 ^ 1
            if (r8 == 0) goto L_0x014c
        L_0x0022:
            r8 = 0
            int r10 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r10 <= 0) goto L_0x008b
            okio.Pipe r10 = r1.this$0     // Catch:{ all -> 0x015b }
            okio.Sink r10 = r10.getFoldedSink$jvm()     // Catch:{ all -> 0x015b }
            if (r10 == 0) goto L_0x0034
            r8 = r10
            r9 = r0
            r5 = r8
            goto L_0x008b
        L_0x0034:
            okio.Pipe r10 = r1.this$0     // Catch:{ all -> 0x015b }
            boolean r10 = r10.getSourceClosed$jvm()     // Catch:{ all -> 0x015b }
            if (r10 != 0) goto L_0x0081
            okio.Pipe r10 = r1.this$0     // Catch:{ all -> 0x015b }
            long r10 = r10.getMaxBufferSize$jvm()     // Catch:{ all -> 0x015b }
            okio.Pipe r12 = r1.this$0     // Catch:{ all -> 0x015b }
            okio.Buffer r12 = r12.getBuffer$jvm()     // Catch:{ all -> 0x015b }
            long r12 = r12.size()     // Catch:{ all -> 0x015b }
            long r10 = r10 - r12
            int r12 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r12 != 0) goto L_0x005d
            okio.Timeout r8 = r1.timeout     // Catch:{ all -> 0x015b }
            okio.Pipe r9 = r1.this$0     // Catch:{ all -> 0x015b }
            okio.Buffer r9 = r9.getBuffer$jvm()     // Catch:{ all -> 0x015b }
            r8.waitUntilNotified(r9)     // Catch:{ all -> 0x015b }
            goto L_0x0022
        L_0x005d:
            long r8 = java.lang.Math.min(r10, r3)     // Catch:{ all -> 0x015b }
            okio.Pipe r12 = r1.this$0     // Catch:{ all -> 0x015b }
            okio.Buffer r12 = r12.getBuffer$jvm()     // Catch:{ all -> 0x015b }
            r12.write(r2, r8)     // Catch:{ all -> 0x015b }
            long r3 = r3 - r8
            okio.Pipe r12 = r1.this$0     // Catch:{ all -> 0x015b }
            okio.Buffer r12 = r12.getBuffer$jvm()     // Catch:{ all -> 0x015b }
            if (r12 == 0) goto L_0x0079
            java.lang.Object r12 = (java.lang.Object) r12     // Catch:{ all -> 0x015b }
            r12.notifyAll()     // Catch:{ all -> 0x015b }
            goto L_0x0022
        L_0x0079:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException     // Catch:{ all -> 0x015b }
            java.lang.String r12 = "null cannot be cast to non-null type java.lang.Object"
            r0.<init>(r12)     // Catch:{ all -> 0x015b }
            throw r0     // Catch:{ all -> 0x015b }
        L_0x0081:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x015b }
            java.lang.String r8 = "source is closed"
            r0.<init>(r8)     // Catch:{ all -> 0x015b }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x015b }
            throw r0     // Catch:{ all -> 0x015b }
        L_0x008b:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0148 }
            monitor-exit(r6)
            if (r5 == 0) goto L_0x0145
            okio.Pipe r6 = r1.this$0
            r8 = r5
            r9 = r0
            okio.Timeout r10 = r8.timeout()
            okio.Sink r11 = r6.sink()
            okio.Timeout r11 = r11.timeout()
            r12 = r0
            long r13 = r10.timeoutNanos()
            okio.Timeout$Companion r15 = okio.Timeout.Companion
            long r0 = r11.timeoutNanos()
            r16 = r5
            r17 = r6
            long r5 = r10.timeoutNanos()
            long r0 = r15.minTimeout(r0, r5)
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.NANOSECONDS
            r10.timeout(r0, r5)
            boolean r0 = r10.hasDeadline()
            if (r0 == 0) goto L_0x010e
            long r5 = r10.deadlineNanoTime()
            boolean r0 = r11.hasDeadline()
            if (r0 == 0) goto L_0x00de
            long r0 = r10.deadlineNanoTime()
            r18 = r5
            long r5 = r11.deadlineNanoTime()
            long r0 = java.lang.Math.min(r0, r5)
            r10.deadlineNanoTime(r0)
            goto L_0x00e0
        L_0x00de:
            r18 = r5
        L_0x00e0:
            r0 = r7
            r1 = r8
            r5 = 0
            r1.write(r2, r3)     // Catch:{ all -> 0x00fb }
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
            r10.timeout(r13, r0)
            boolean r0 = r11.hasDeadline()
            if (r0 == 0) goto L_0x00f8
            r5 = r18
            r10.deadlineNanoTime(r5)
            goto L_0x00fa
        L_0x00f8:
            r5 = r18
        L_0x00fa:
            goto L_0x0131
        L_0x00fb:
            r0 = move-exception
            r5 = r18
            r1 = r0
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
            r10.timeout(r13, r0)
            boolean r0 = r11.hasDeadline()
            if (r0 == 0) goto L_0x010d
            r10.deadlineNanoTime(r5)
        L_0x010d:
            throw r1
        L_0x010e:
            boolean r0 = r11.hasDeadline()
            if (r0 == 0) goto L_0x011b
            long r0 = r11.deadlineNanoTime()
            r10.deadlineNanoTime(r0)
        L_0x011b:
            r0 = 0
            r1 = r0
            r5 = r8
            r5.write(r2, r3)     // Catch:{ all -> 0x0134 }
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
            r10.timeout(r13, r0)
            boolean r0 = r11.hasDeadline()
            if (r0 == 0) goto L_0x0130
            r10.clearDeadline()
        L_0x0130:
        L_0x0131:
            goto L_0x0147
        L_0x0134:
            r0 = move-exception
            r1 = r0
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
            r10.timeout(r13, r0)
            boolean r0 = r11.hasDeadline()
            if (r0 == 0) goto L_0x0144
            r10.clearDeadline()
        L_0x0144:
            throw r1
        L_0x0145:
            r16 = r5
        L_0x0147:
            return
        L_0x0148:
            r0 = move-exception
            r16 = r5
            goto L_0x015c
        L_0x014c:
            r0 = 0
            java.lang.String r1 = "closed"
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x015b }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x015b }
            r0.<init>(r1)     // Catch:{ all -> 0x015b }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x015b }
            throw r0     // Catch:{ all -> 0x015b }
        L_0x015b:
            r0 = move-exception
        L_0x015c:
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Pipe$sink$1.write(okio.Buffer, long):void");
    }

    public void flush() {
        Sink sink = null;
        synchronized (this.this$0.getBuffer$jvm()) {
            if (!this.this$0.getSinkClosed$jvm()) {
                Sink it = this.this$0.getFoldedSink$jvm();
                if (it != null) {
                    sink = it;
                } else if (this.this$0.getSourceClosed$jvm()) {
                    if (this.this$0.getBuffer$jvm().size() > 0) {
                        throw new IOException("source is closed");
                    }
                }
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("closed".toString());
            }
        }
        if (sink != null) {
            Pipe this_$iv = this.this$0;
            Sink $receiver$iv = sink;
            Timeout this_$iv$iv = $receiver$iv.timeout();
            Timeout other$iv$iv = this_$iv.sink().timeout();
            long originalTimeout$iv$iv = this_$iv$iv.timeoutNanos();
            this_$iv$iv.timeout(Timeout.Companion.minTimeout(other$iv$iv.timeoutNanos(), this_$iv$iv.timeoutNanos()), TimeUnit.NANOSECONDS);
            if (this_$iv$iv.hasDeadline()) {
                long originalDeadline$iv$iv = this_$iv$iv.deadlineNanoTime();
                if (other$iv$iv.hasDeadline()) {
                    this_$iv$iv.deadlineNanoTime(Math.min(this_$iv$iv.deadlineNanoTime(), other$iv$iv.deadlineNanoTime()));
                }
                try {
                    $receiver$iv.flush();
                    this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                    if (other$iv$iv.hasDeadline()) {
                        this_$iv$iv.deadlineNanoTime(originalDeadline$iv$iv);
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                    if (other$iv$iv.hasDeadline()) {
                        this_$iv$iv.deadlineNanoTime(originalDeadline$iv$iv);
                    }
                    throw th2;
                }
            } else {
                if (other$iv$iv.hasDeadline()) {
                    this_$iv$iv.deadlineNanoTime(other$iv$iv.deadlineNanoTime());
                }
                try {
                    $receiver$iv.flush();
                    this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                    if (other$iv$iv.hasDeadline()) {
                        this_$iv$iv.clearDeadline();
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                    if (other$iv$iv.hasDeadline()) {
                        this_$iv$iv.clearDeadline();
                    }
                    throw th4;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005d, code lost:
        if (r2 == null) goto L_0x0105;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005f, code lost:
        r3 = r1.this$0;
        r4 = r2;
        r6 = r4.timeout();
        r7 = r3.sink().timeout();
        r9 = r6.timeoutNanos();
        r6.timeout(okio.Timeout.Companion.minTimeout(r7.timeoutNanos(), r6.timeoutNanos()), java.util.concurrent.TimeUnit.NANOSECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008b, code lost:
        if (r6.hasDeadline() == false) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008d, code lost:
        r11 = r6.deadlineNanoTime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0095, code lost:
        if (r7.hasDeadline() == false) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0097, code lost:
        r6.deadlineNanoTime(java.lang.Math.min(r6.deadlineNanoTime(), r7.deadlineNanoTime()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ad, code lost:
        r6.timeout(r9, java.util.concurrent.TimeUnit.NANOSECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b6, code lost:
        if (r7.hasDeadline() == false) goto L_0x0105;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b8, code lost:
        r6.deadlineNanoTime(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00bc, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00bd, code lost:
        r1 = r0;
        r6.timeout(r9, java.util.concurrent.TimeUnit.NANOSECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c7, code lost:
        if (r7.hasDeadline() != false) goto L_0x00c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00c9, code lost:
        r6.deadlineNanoTime(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00cc, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d1, code lost:
        if (r7.hasDeadline() == false) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d3, code lost:
        r6.deadlineNanoTime(r7.deadlineNanoTime());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00e1, code lost:
        r6.timeout(r9, java.util.concurrent.TimeUnit.NANOSECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ea, code lost:
        if (r7.hasDeadline() == false) goto L_0x0105;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ec, code lost:
        r6.clearDeadline();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f4, code lost:
        r1 = r0;
        r6.timeout(r9, java.util.concurrent.TimeUnit.NANOSECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00fe, code lost:
        if (r7.hasDeadline() != false) goto L_0x0100;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0100, code lost:
        r6.clearDeadline();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0103, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0105, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
            r16 = this;
            r1 = r16
            r0 = 0
            r2 = r0
            okio.Sink r2 = (okio.Sink) r2
            okio.Pipe r0 = r1.this$0
            okio.Buffer r3 = r0.getBuffer$jvm()
            monitor-enter(r3)
            r0 = 0
            r4 = r0
            okio.Pipe r5 = r1.this$0     // Catch:{ all -> 0x010e }
            boolean r5 = r5.getSinkClosed$jvm()     // Catch:{ all -> 0x010e }
            if (r5 == 0) goto L_0x0019
            monitor-exit(r3)
            return
        L_0x0019:
            okio.Pipe r5 = r1.this$0     // Catch:{ all -> 0x010e }
            okio.Sink r5 = r5.getFoldedSink$jvm()     // Catch:{ all -> 0x010e }
            if (r5 == 0) goto L_0x0024
            r6 = r0
            r2 = r5
            goto L_0x005a
        L_0x0024:
            okio.Pipe r5 = r1.this$0     // Catch:{ all -> 0x010e }
            boolean r5 = r5.getSourceClosed$jvm()     // Catch:{ all -> 0x010e }
            if (r5 == 0) goto L_0x0047
            okio.Pipe r5 = r1.this$0     // Catch:{ all -> 0x010e }
            okio.Buffer r5 = r5.getBuffer$jvm()     // Catch:{ all -> 0x010e }
            long r5 = r5.size()     // Catch:{ all -> 0x010e }
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 > 0) goto L_0x003d
            goto L_0x0047
        L_0x003d:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x010e }
            java.lang.String r5 = "source is closed"
            r0.<init>(r5)     // Catch:{ all -> 0x010e }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x010e }
            throw r0     // Catch:{ all -> 0x010e }
        L_0x0047:
            okio.Pipe r5 = r1.this$0     // Catch:{ all -> 0x010e }
            r6 = 1
            r5.setSinkClosed$jvm(r6)     // Catch:{ all -> 0x010e }
            okio.Pipe r5 = r1.this$0     // Catch:{ all -> 0x010e }
            okio.Buffer r5 = r5.getBuffer$jvm()     // Catch:{ all -> 0x010e }
            if (r5 == 0) goto L_0x0106
            java.lang.Object r5 = (java.lang.Object) r5     // Catch:{ all -> 0x010e }
            r5.notifyAll()     // Catch:{ all -> 0x010e }
        L_0x005a:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x010e }
            monitor-exit(r3)
            if (r2 == 0) goto L_0x0104
            okio.Pipe r3 = r1.this$0
            r4 = r2
            r5 = r0
            okio.Timeout r6 = r4.timeout()
            okio.Sink r7 = r3.sink()
            okio.Timeout r7 = r7.timeout()
            r8 = r0
            long r9 = r6.timeoutNanos()
            okio.Timeout$Companion r11 = okio.Timeout.Companion
            long r12 = r7.timeoutNanos()
            long r14 = r6.timeoutNanos()
            long r11 = r11.minTimeout(r12, r14)
            java.util.concurrent.TimeUnit r13 = java.util.concurrent.TimeUnit.NANOSECONDS
            r6.timeout(r11, r13)
            boolean r11 = r6.hasDeadline()
            if (r11 == 0) goto L_0x00cd
            long r11 = r6.deadlineNanoTime()
            boolean r13 = r7.hasDeadline()
            if (r13 == 0) goto L_0x00a6
            long r13 = r6.deadlineNanoTime()
            long r0 = r7.deadlineNanoTime()
            long r0 = java.lang.Math.min(r13, r0)
            r6.deadlineNanoTime(r0)
        L_0x00a6:
            r0 = 0
            r1 = r0
            r13 = r4
            r13.close()     // Catch:{ all -> 0x00bc }
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
            r6.timeout(r9, r0)
            boolean r0 = r7.hasDeadline()
            if (r0 == 0) goto L_0x00bb
            r6.deadlineNanoTime(r11)
        L_0x00bb:
            goto L_0x00f0
        L_0x00bc:
            r0 = move-exception
            r1 = r0
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
            r6.timeout(r9, r0)
            boolean r0 = r7.hasDeadline()
            if (r0 == 0) goto L_0x00cc
            r6.deadlineNanoTime(r11)
        L_0x00cc:
            throw r1
        L_0x00cd:
            boolean r0 = r7.hasDeadline()
            if (r0 == 0) goto L_0x00da
            long r0 = r7.deadlineNanoTime()
            r6.deadlineNanoTime(r0)
        L_0x00da:
            r0 = 0
            r1 = r0
            r11 = r4
            r11.close()     // Catch:{ all -> 0x00f3 }
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
            r6.timeout(r9, r0)
            boolean r0 = r7.hasDeadline()
            if (r0 == 0) goto L_0x00ef
            r6.clearDeadline()
        L_0x00ef:
        L_0x00f0:
            goto L_0x0105
        L_0x00f3:
            r0 = move-exception
            r1 = r0
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
            r6.timeout(r9, r0)
            boolean r0 = r7.hasDeadline()
            if (r0 == 0) goto L_0x0103
            r6.clearDeadline()
        L_0x0103:
            throw r1
        L_0x0104:
        L_0x0105:
            return
        L_0x0106:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException     // Catch:{ all -> 0x010e }
            java.lang.String r1 = "null cannot be cast to non-null type java.lang.Object"
            r0.<init>(r1)     // Catch:{ all -> 0x010e }
            throw r0     // Catch:{ all -> 0x010e }
        L_0x010e:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Pipe$sink$1.close():void");
    }

    public Timeout timeout() {
        return this.timeout;
    }
}
