package okio;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.IOException;
import java.io.InterruptedIOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, mo24952d2 = {"okio/Throttler$sink$1", "Lokio/ForwardingSink;", "write", "", "source", "Lokio/Buffer;", "byteCount", "", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
/* compiled from: Throttler.kt */
public final class Throttler$sink$1 extends ForwardingSink {
    final /* synthetic */ Sink $sink;
    final /* synthetic */ Throttler this$0;

    Throttler$sink$1(Throttler $outer, Sink $captured_local_variable$1, Sink $super_call_param$2) {
        this.this$0 = $outer;
        this.$sink = $captured_local_variable$1;
        super($super_call_param$2);
    }

    public void write(Buffer source, long byteCount) throws IOException {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        long remaining = byteCount;
        while (remaining > 0) {
            try {
                long toWrite = this.this$0.take$jvm(remaining);
                super.write(source, toWrite);
                remaining -= toWrite;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException("interrupted");
            }
        }
    }
}
