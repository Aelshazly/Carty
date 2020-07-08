package okio;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, mo24952d2 = {"okio/AsyncTimeout$source$1", "Lokio/Source;", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "timeout", "Lokio/AsyncTimeout;", "toString", "", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
/* compiled from: AsyncTimeout.kt */
public final class AsyncTimeout$source$1 implements Source {
    final /* synthetic */ Source $source;
    final /* synthetic */ AsyncTimeout this$0;

    AsyncTimeout$source$1(AsyncTimeout $outer, Source $captured_local_variable$1) {
        this.this$0 = $outer;
        this.$source = $captured_local_variable$1;
    }

    public long read(Buffer sink, long byteCount) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        this.this$0.enter();
        try {
            long result = this.$source.read(sink, byteCount);
            this.this$0.exit$jvm(true);
            return result;
        } catch (IOException e) {
            throw this.this$0.exit$jvm(e);
        } catch (Throwable th) {
            this.this$0.exit$jvm(false);
            throw th;
        }
    }

    public void close() {
        this.this$0.enter();
        try {
            this.$source.close();
            this.this$0.exit$jvm(true);
        } catch (IOException e) {
            throw this.this$0.exit$jvm(e);
        } catch (Throwable th) {
            this.this$0.exit$jvm(false);
            throw th;
        }
    }

    public AsyncTimeout timeout() {
        return this.this$0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AsyncTimeout.source(");
        sb.append(this.$source);
        sb.append(')');
        return sb.toString();
    }
}
