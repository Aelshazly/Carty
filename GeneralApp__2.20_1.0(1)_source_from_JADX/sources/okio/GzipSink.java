package okio;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\r\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b\u0010J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u001b\u001a\u00020\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\b\u001a\u00020\t8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, mo24952d2 = {"Lokio/GzipSink;", "Lokio/Sink;", "sink", "(Lokio/Sink;)V", "closed", "", "crc", "Ljava/util/zip/CRC32;", "deflater", "Ljava/util/zip/Deflater;", "()Ljava/util/zip/Deflater;", "deflaterSink", "Lokio/DeflaterSink;", "Lokio/RealBufferedSink;", "close", "", "-deprecated_deflater", "flush", "timeout", "Lokio/Timeout;", "updateCrc", "buffer", "Lokio/Buffer;", "byteCount", "", "write", "source", "writeFooter", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
/* compiled from: GzipSink.kt */
public final class GzipSink implements Sink {
    private boolean closed;
    private final CRC32 crc = new CRC32();
    private final Deflater deflater = new Deflater(-1, true);
    private final DeflaterSink deflaterSink = new DeflaterSink((BufferedSink) this.sink, this.deflater);
    private final RealBufferedSink sink;

    public GzipSink(Sink sink2) {
        Intrinsics.checkParameterIsNotNull(sink2, "sink");
        this.sink = new RealBufferedSink(sink2);
        Buffer $receiver = this.sink.bufferField;
        $receiver.writeShort(8075);
        $receiver.writeByte(8);
        $receiver.writeByte(0);
        $receiver.writeInt(0);
        $receiver.writeByte(0);
        $receiver.writeByte(0);
    }

    public final Deflater deflater() {
        return this.deflater;
    }

    public void write(Buffer source, long byteCount) throws IOException {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        if (!(byteCount >= 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append("byteCount < 0: ");
            sb.append(byteCount);
            throw new IllegalArgumentException(sb.toString().toString());
        } else if (byteCount != 0) {
            updateCrc(source, byteCount);
            this.deflaterSink.write(source, byteCount);
        }
    }

    public void flush() throws IOException {
        this.deflaterSink.flush();
    }

    public Timeout timeout() {
        return this.sink.timeout();
    }

    public void close() throws IOException {
        if (!this.closed) {
            Throwable thrown = null;
            try {
                this.deflaterSink.finishDeflate$jvm();
                writeFooter();
            } catch (Throwable e) {
                thrown = e;
            }
            try {
                this.deflater.end();
            } catch (Throwable e2) {
                if (thrown == null) {
                    thrown = e2;
                }
            }
            try {
                this.sink.close();
            } catch (Throwable e3) {
                if (thrown == null) {
                    thrown = e3;
                }
            }
            this.closed = true;
            if (thrown != null) {
                throw thrown;
            }
        }
    }

    private final void writeFooter() {
        this.sink.writeIntLe((int) this.crc.getValue());
        this.sink.writeIntLe((int) this.deflater.getBytesRead());
    }

    private final void updateCrc(Buffer buffer, long byteCount) {
        Segment head = buffer.head;
        if (head == null) {
            Intrinsics.throwNpe();
        }
        long remaining = byteCount;
        while (remaining > 0) {
            int segmentLength = (int) Math.min(remaining, (long) (head.limit - head.pos));
            this.crc.update(head.data, head.pos, segmentLength);
            remaining -= (long) segmentLength;
            Segment segment = head.next;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            head = segment;
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "deflater", imports = {}))
    /* renamed from: -deprecated_deflater reason: not valid java name */
    public final Deflater m2389deprecated_deflater() {
        return this.deflater;
    }
}
