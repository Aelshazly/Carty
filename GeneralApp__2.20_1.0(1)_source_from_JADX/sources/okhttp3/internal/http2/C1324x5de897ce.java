package okhttp3.internal.http2;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.Http2Connection.ReaderRunnable;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, mo24952d2 = {"<anonymous>", "", "run", "okhttp3/internal/Util$execute$1"}, mo24953k = 3, mo24954mv = {1, 1, 15})
/* renamed from: okhttp3.internal.http2.Http2Connection$ReaderRunnable$applyAndAckSettings$$inlined$execute$1 */
/* compiled from: Util.kt */
public final class C1324x5de897ce implements Runnable {
    final /* synthetic */ String $name;
    final /* synthetic */ ReaderRunnable this$0;

    public C1324x5de897ce(String str, ReaderRunnable readerRunnable) {
        this.$name = str;
        this.this$0 = readerRunnable;
    }

    public final void run() {
        String name$iv = this.$name;
        Thread currentThread$iv = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread$iv, "currentThread");
        String oldName$iv = currentThread$iv.getName();
        currentThread$iv.setName(name$iv);
        try {
            this.this$0.this$0.getListener$okhttp().onSettings(this.this$0.this$0);
        } finally {
            currentThread$iv.setName(oldName$iv);
        }
    }
}
