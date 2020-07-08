package okhttp3.internal.http2;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.Http2Connection.ReaderRunnable;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, mo24952d2 = {"<anonymous>", "", "run", "okhttp3/internal/Util$execute$1"}, mo24953k = 3, mo24954mv = {1, 1, 15})
/* compiled from: Util.kt */
public final class Http2Connection$ReaderRunnable$ping$$inlined$tryExecute$1 implements Runnable {
    final /* synthetic */ String $name;
    final /* synthetic */ int $payload1$inlined;
    final /* synthetic */ int $payload2$inlined;
    final /* synthetic */ ReaderRunnable this$0;

    public Http2Connection$ReaderRunnable$ping$$inlined$tryExecute$1(String str, ReaderRunnable readerRunnable, int i, int i2) {
        this.$name = str;
        this.this$0 = readerRunnable;
        this.$payload1$inlined = i;
        this.$payload2$inlined = i2;
    }

    public final void run() {
        String name$iv = this.$name;
        Thread currentThread$iv = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread$iv, "currentThread");
        String oldName$iv = currentThread$iv.getName();
        currentThread$iv.setName(name$iv);
        try {
            this.this$0.this$0.writePing(true, this.$payload1$inlined, this.$payload2$inlined);
        } finally {
            currentThread$iv.setName(oldName$iv);
        }
    }
}
