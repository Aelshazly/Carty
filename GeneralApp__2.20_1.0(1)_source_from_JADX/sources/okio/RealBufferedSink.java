package okio;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.EOFException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0001H\u0016J\b\u0010\u0011\u001a\u00020\u0001H\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0016J\b\u0010\u0013\u001a\u00020\rH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001eH\u0016J \u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u001bH\u0016J\u0018\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\u001a\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#H\u0016J\u0018\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020$2\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010%\u001a\u00020!2\u0006\u0010\u001c\u001a\u00020$H\u0016J\u0010\u0010&\u001a\u00020\u00012\u0006\u0010'\u001a\u00020\u001bH\u0016J\u0010\u0010(\u001a\u00020\u00012\u0006\u0010)\u001a\u00020!H\u0016J\u0010\u0010*\u001a\u00020\u00012\u0006\u0010)\u001a\u00020!H\u0016J\u0010\u0010+\u001a\u00020\u00012\u0006\u0010,\u001a\u00020\u001bH\u0016J\u0010\u0010-\u001a\u00020\u00012\u0006\u0010,\u001a\u00020\u001bH\u0016J\u0010\u0010.\u001a\u00020\u00012\u0006\u0010)\u001a\u00020!H\u0016J\u0010\u0010/\u001a\u00020\u00012\u0006\u0010)\u001a\u00020!H\u0016J\u0010\u00100\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u001bH\u0016J\u0010\u00102\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u001bH\u0016J\u0018\u00103\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u00192\u0006\u00105\u001a\u000206H\u0016J(\u00103\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u00192\u0006\u00107\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\u001b2\u0006\u00105\u001a\u000206H\u0016J\u0010\u00109\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u0019H\u0016J \u00109\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u00192\u0006\u00107\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\u001bH\u0016J\u0010\u0010:\u001a\u00020\u00012\u0006\u0010;\u001a\u00020\u001bH\u0016R\u001b\u0010\u0005\u001a\u00020\u00068Ö\u0002X\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\r8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006<"}, mo24952d2 = {"Lokio/RealBufferedSink;", "Lokio/BufferedSink;", "sink", "Lokio/Sink;", "(Lokio/Sink;)V", "buffer", "Lokio/Buffer;", "buffer$annotations", "()V", "getBuffer", "()Lokio/Buffer;", "bufferField", "closed", "", "close", "", "emit", "emitCompleteSegments", "flush", "isOpen", "outputStream", "Ljava/io/OutputStream;", "timeout", "Lokio/Timeout;", "toString", "", "write", "", "source", "Ljava/nio/ByteBuffer;", "", "offset", "byteCount", "", "byteString", "Lokio/ByteString;", "Lokio/Source;", "writeAll", "writeByte", "b", "writeDecimalLong", "v", "writeHexadecimalUnsignedLong", "writeInt", "i", "writeIntLe", "writeLong", "writeLongLe", "writeShort", "s", "writeShortLe", "writeString", "string", "charset", "Ljava/nio/charset/Charset;", "beginIndex", "endIndex", "writeUtf8", "writeUtf8CodePoint", "codePoint", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
/* compiled from: RealBufferedSink.kt */
public final class RealBufferedSink implements BufferedSink {
    public final Buffer bufferField = new Buffer();
    public boolean closed;
    public final Sink sink;

    public static /* synthetic */ void buffer$annotations() {
    }

    public RealBufferedSink(Sink sink2) {
        Intrinsics.checkParameterIsNotNull(sink2, "sink");
        this.sink = sink2;
    }

    public Buffer getBuffer() {
        return this.bufferField;
    }

    public Buffer buffer() {
        return this.bufferField;
    }

    public void write(Buffer source, long byteCount) {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        if (!this.closed) {
            this.bufferField.write(source, byteCount);
            emitCompleteSegments();
            return;
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink write(ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(byteString, "byteString");
        if (!this.closed) {
            this.bufferField.write(byteString);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink writeUtf8(String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        if (!this.closed) {
            this.bufferField.writeUtf8(string);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink writeUtf8(String string, int beginIndex, int endIndex) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        if (!this.closed) {
            this.bufferField.writeUtf8(string, beginIndex, endIndex);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink writeUtf8CodePoint(int codePoint) {
        if (!this.closed) {
            this.bufferField.writeUtf8CodePoint(codePoint);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink writeString(String string, Charset charset) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        if (!this.closed) {
            this.bufferField.writeString(string, charset);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink writeString(String string, int beginIndex, int endIndex, Charset charset) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        if (!this.closed) {
            this.bufferField.writeString(string, beginIndex, endIndex, charset);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink write(byte[] source) {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        if (!this.closed) {
            this.bufferField.write(source);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink write(byte[] source, int offset, int byteCount) {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        if (!this.closed) {
            this.bufferField.write(source, offset, byteCount);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public int write(ByteBuffer source) {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        if (!this.closed) {
            int result = this.bufferField.write(source);
            emitCompleteSegments();
            return result;
        }
        throw new IllegalStateException("closed".toString());
    }

    public long writeAll(Source source) {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        long totalBytesRead = 0;
        while (true) {
            long readCount = source.read(this.bufferField, (long) 8192);
            if (readCount == -1) {
                return totalBytesRead;
            }
            totalBytesRead += readCount;
            emitCompleteSegments();
        }
    }

    public BufferedSink write(Source source, long byteCount) {
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        long byteCount2 = byteCount;
        while (byteCount2 > 0) {
            long read = source.read(this.bufferField, byteCount2);
            if (read != -1) {
                byteCount2 -= read;
                emitCompleteSegments();
            } else {
                throw new EOFException();
            }
        }
        return this;
    }

    public BufferedSink writeByte(int b) {
        if (!this.closed) {
            this.bufferField.writeByte(b);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink writeShort(int s) {
        if (!this.closed) {
            this.bufferField.writeShort(s);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink writeShortLe(int s) {
        if (!this.closed) {
            this.bufferField.writeShortLe(s);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink writeInt(int i) {
        if (!this.closed) {
            this.bufferField.writeInt(i);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink writeIntLe(int i) {
        if (!this.closed) {
            this.bufferField.writeIntLe(i);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink writeLong(long v) {
        if (!this.closed) {
            this.bufferField.writeLong(v);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink writeLongLe(long v) {
        if (!this.closed) {
            this.bufferField.writeLongLe(v);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink writeDecimalLong(long v) {
        if (!this.closed) {
            this.bufferField.writeDecimalLong(v);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink writeHexadecimalUnsignedLong(long v) {
        if (!this.closed) {
            this.bufferField.writeHexadecimalUnsignedLong(v);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink emitCompleteSegments() {
        if (!this.closed) {
            long byteCount = this.bufferField.completeSegmentByteCount();
            if (byteCount > 0) {
                this.sink.write(this.bufferField, byteCount);
            }
            return this;
        }
        throw new IllegalStateException("closed".toString());
    }

    public BufferedSink emit() {
        if (!this.closed) {
            long byteCount = this.bufferField.size();
            if (byteCount > 0) {
                this.sink.write(this.bufferField, byteCount);
            }
            return this;
        }
        throw new IllegalStateException("closed".toString());
    }

    public OutputStream outputStream() {
        return new RealBufferedSink$outputStream$1(this);
    }

    public void flush() {
        if (!this.closed) {
            if (this.bufferField.size() > 0) {
                this.sink.write(this.bufferField, this.bufferField.size());
            }
            this.sink.flush();
            return;
        }
        throw new IllegalStateException("closed".toString());
    }

    public boolean isOpen() {
        return !this.closed;
    }

    public void close() {
        if (!this.closed) {
            Throwable thrown = null;
            try {
                if (this.bufferField.size() > 0) {
                    this.sink.write(this.bufferField, this.bufferField.size());
                }
            } catch (Throwable e) {
                thrown = e;
            }
            try {
                this.sink.close();
            } catch (Throwable e2) {
                if (thrown == null) {
                    thrown = e2;
                }
            }
            this.closed = true;
            if (thrown != null) {
                throw thrown;
            }
        }
    }

    public Timeout timeout() {
        return this.sink.timeout();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("buffer(");
        sb.append(this.sink);
        sb.append(')');
        return sb.toString();
    }
}
