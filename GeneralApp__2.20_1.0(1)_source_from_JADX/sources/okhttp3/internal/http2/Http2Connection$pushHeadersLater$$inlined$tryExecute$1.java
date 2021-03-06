package okhttp3.internal.http2;

import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, mo24952d2 = {"<anonymous>", "", "run", "okhttp3/internal/Util$execute$1"}, mo24953k = 3, mo24954mv = {1, 1, 15})
/* compiled from: Util.kt */
public final class Http2Connection$pushHeadersLater$$inlined$tryExecute$1 implements Runnable {
    final /* synthetic */ boolean $inFinished$inlined;
    final /* synthetic */ String $name;
    final /* synthetic */ List $requestHeaders$inlined;
    final /* synthetic */ int $streamId$inlined;
    final /* synthetic */ Http2Connection this$0;

    public Http2Connection$pushHeadersLater$$inlined$tryExecute$1(String str, Http2Connection http2Connection, int i, List list, boolean z) {
        this.$name = str;
        this.this$0 = http2Connection;
        this.$streamId$inlined = i;
        this.$requestHeaders$inlined = list;
        this.$inFinished$inlined = z;
    }

    public final void run() {
        String name$iv = this.$name;
        Thread currentThread$iv = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread$iv, "currentThread");
        String oldName$iv = currentThread$iv.getName();
        currentThread$iv.setName(name$iv);
        try {
            boolean cancel = this.this$0.pushObserver.onHeaders(this.$streamId$inlined, this.$requestHeaders$inlined, this.$inFinished$inlined);
            if (cancel) {
                try {
                    this.this$0.getWriter().rstStream(this.$streamId$inlined, ErrorCode.CANCEL);
                } catch (IOException e) {
                }
            }
            if (cancel || this.$inFinished$inlined) {
                synchronized (this.this$0) {
                    this.this$0.currentPushRequests.remove(Integer.valueOf(this.$streamId$inlined));
                }
            }
        } finally {
            currentThread$iv.setName(oldName$iv);
        }
    }
}
