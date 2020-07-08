package okhttp3.internal.http2;

import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, mo24952d2 = {"<anonymous>", "", "run", "okhttp3/internal/Util$execute$1"}, mo24953k = 3, mo24954mv = {1, 1, 15})
/* compiled from: Util.kt */
public final class Http2Connection$pushRequestLater$$inlined$tryExecute$1 implements Runnable {
    final /* synthetic */ String $name;
    final /* synthetic */ List $requestHeaders$inlined;
    final /* synthetic */ int $streamId$inlined;
    final /* synthetic */ Http2Connection this$0;

    public Http2Connection$pushRequestLater$$inlined$tryExecute$1(String str, Http2Connection http2Connection, int i, List list) {
        this.$name = str;
        this.this$0 = http2Connection;
        this.$streamId$inlined = i;
        this.$requestHeaders$inlined = list;
    }

    public final void run() {
        String name$iv = this.$name;
        Thread currentThread$iv = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread$iv, "currentThread");
        String oldName$iv = currentThread$iv.getName();
        currentThread$iv.setName(name$iv);
        try {
            if (this.this$0.pushObserver.onRequest(this.$streamId$inlined, this.$requestHeaders$inlined)) {
                try {
                    this.this$0.getWriter().rstStream(this.$streamId$inlined, ErrorCode.CANCEL);
                    synchronized (this.this$0) {
                        this.this$0.currentPushRequests.remove(Integer.valueOf(this.$streamId$inlined));
                    }
                } catch (IOException e) {
                }
            }
        } finally {
            currentThread$iv.setName(oldName$iv);
        }
    }
}
