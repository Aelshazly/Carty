package okio;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, mo24952d2 = {"okio/AsyncTimeout$sink$1", "Lokio/Sink;", "close", "", "flush", "timeout", "Lokio/AsyncTimeout;", "toString", "", "write", "source", "Lokio/Buffer;", "byteCount", "", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
/* compiled from: AsyncTimeout.kt */
public final class AsyncTimeout$sink$1 implements Sink {
    final /* synthetic */ Sink $sink;
    final /* synthetic */ AsyncTimeout this$0;

    AsyncTimeout$sink$1(AsyncTimeout $outer, Sink $captured_local_variable$1) {
        this.this$0 = $outer;
        this.$sink = $captured_local_variable$1;
    }

    public void write(Buffer source, long byteCount) {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        Util.checkOffsetAndCount(source.size(), 0, byteCount);
        long remaining = byteCount;
        while (remaining > 0) {
            long toWrite = 0;
            Segment s = source.head;
            if (s == null) {
                Intrinsics.throwNpe();
            }
            while (true) {
                if (toWrite >= ((long) 65536)) {
                    break;
                }
                toWrite += (long) (s.limit - s.pos);
                if (toWrite >= remaining) {
                    toWrite = remaining;
                    break;
                }
                Segment segment = s.next;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                s = segment;
            }
            this.this$0.enter();
            try {
                this.$sink.write(source, toWrite);
                remaining -= toWrite;
                this.this$0.exit$jvm(true);
            } catch (IOException e) {
                throw this.this$0.exit$jvm(e);
            } catch (Throwable th) {
                this.this$0.exit$jvm(false);
                throw th;
            }
        }
    }

    public void flush() {
        this.this$0.enter();
        try {
            this.$sink.flush();
            this.this$0.exit$jvm(true);
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
            this.$sink.close();
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
        sb.append("AsyncTimeout.sink(");
        sb.append(this.$sink);
        sb.append(')');
        return sb.toString();
    }
}
