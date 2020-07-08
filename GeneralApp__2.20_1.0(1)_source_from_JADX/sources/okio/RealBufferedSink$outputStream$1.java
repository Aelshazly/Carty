package okio;

import java.io.IOException;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J \u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000bH\u0016¨\u0006\u000e"}, mo24952d2 = {"okio/RealBufferedSink$outputStream$1", "Ljava/io/OutputStream;", "close", "", "flush", "toString", "", "write", "data", "", "offset", "", "byteCount", "b", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
/* compiled from: RealBufferedSink.kt */
public final class RealBufferedSink$outputStream$1 extends OutputStream {
    final /* synthetic */ RealBufferedSink this$0;

    RealBufferedSink$outputStream$1(RealBufferedSink $outer) {
        this.this$0 = $outer;
    }

    public void write(int b) {
        if (!this.this$0.closed) {
            this.this$0.bufferField.writeByte((byte) b);
            this.this$0.emitCompleteSegments();
            return;
        }
        throw new IOException("closed");
    }

    public void write(byte[] data, int offset, int byteCount) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        if (!this.this$0.closed) {
            this.this$0.bufferField.write(data, offset, byteCount);
            this.this$0.emitCompleteSegments();
            return;
        }
        throw new IOException("closed");
    }

    public void flush() {
        if (!this.this$0.closed) {
            this.this$0.flush();
        }
    }

    public void close() {
        this.this$0.close();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.this$0);
        sb.append(".outputStream()");
        return sb.toString();
    }
}
