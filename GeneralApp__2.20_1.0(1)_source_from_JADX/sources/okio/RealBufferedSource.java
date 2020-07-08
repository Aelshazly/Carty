package okio;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\rH\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012H\u0016J \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0012H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0012H\u0016J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0012H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\rH\u0016J\b\u0010\u001e\u001a\u00020\u0001H\u0016J\u0018\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J(\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\u0010\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020'H\u0016J \u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020'2\u0006\u0010 \u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\u0018\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0012H\u0016J\u0010\u0010(\u001a\u00020\u00122\u0006\u0010%\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020\u0014H\u0016J\b\u0010+\u001a\u00020'H\u0016J\u0010\u0010+\u001a\u00020'2\u0006\u0010#\u001a\u00020\u0012H\u0016J\b\u0010,\u001a\u00020\u0018H\u0016J\u0010\u0010,\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u0012H\u0016J\b\u0010-\u001a\u00020\u0012H\u0016J\u0010\u0010.\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020'H\u0016J\u0018\u0010.\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0012H\u0016J\b\u0010/\u001a\u00020\u0012H\u0016J\b\u00100\u001a\u00020\"H\u0016J\b\u00101\u001a\u00020\"H\u0016J\b\u00102\u001a\u00020\u0012H\u0016J\b\u00103\u001a\u00020\u0012H\u0016J\b\u00104\u001a\u000205H\u0016J\b\u00106\u001a\u000205H\u0016J\u0010\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0016J\u0018\u00107\u001a\u0002082\u0006\u0010#\u001a\u00020\u00122\u0006\u00109\u001a\u00020:H\u0016J\b\u0010;\u001a\u000208H\u0016J\u0010\u0010;\u001a\u0002082\u0006\u0010#\u001a\u00020\u0012H\u0016J\b\u0010<\u001a\u00020\"H\u0016J\n\u0010=\u001a\u0004\u0018\u000108H\u0016J\b\u0010>\u001a\u000208H\u0016J\u0010\u0010>\u001a\u0002082\u0006\u0010?\u001a\u00020\u0012H\u0016J\u0010\u0010@\u001a\u00020\r2\u0006\u0010#\u001a\u00020\u0012H\u0016J\u0010\u0010A\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u0012H\u0016J\u0010\u0010B\u001a\u00020\"2\u0006\u0010C\u001a\u00020DH\u0016J\u0010\u0010E\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u0012H\u0016J\b\u0010F\u001a\u00020GH\u0016J\b\u0010H\u001a\u000208H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068Ö\u0002X\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\r8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006I"}, mo24952d2 = {"Lokio/RealBufferedSource;", "Lokio/BufferedSource;", "source", "Lokio/Source;", "(Lokio/Source;)V", "buffer", "Lokio/Buffer;", "buffer$annotations", "()V", "getBuffer", "()Lokio/Buffer;", "bufferField", "closed", "", "close", "", "exhausted", "indexOf", "", "b", "", "fromIndex", "toIndex", "bytes", "Lokio/ByteString;", "indexOfElement", "targetBytes", "inputStream", "Ljava/io/InputStream;", "isOpen", "peek", "rangeEquals", "offset", "bytesOffset", "", "byteCount", "read", "sink", "Ljava/nio/ByteBuffer;", "", "readAll", "Lokio/Sink;", "readByte", "readByteArray", "readByteString", "readDecimalLong", "readFully", "readHexadecimalUnsignedLong", "readInt", "readIntLe", "readLong", "readLongLe", "readShort", "", "readShortLe", "readString", "", "charset", "Ljava/nio/charset/Charset;", "readUtf8", "readUtf8CodePoint", "readUtf8Line", "readUtf8LineStrict", "limit", "request", "require", "select", "options", "Lokio/Options;", "skip", "timeout", "Lokio/Timeout;", "toString", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
/* compiled from: RealBufferedSource.kt */
public final class RealBufferedSource implements BufferedSource {
    public final Buffer bufferField = new Buffer();
    public boolean closed;
    public final Source source;

    public static /* synthetic */ void buffer$annotations() {
    }

    public RealBufferedSource(Source source2) {
        Intrinsics.checkParameterIsNotNull(source2, Param.SOURCE);
        this.source = source2;
    }

    public Buffer getBuffer() {
        return this.bufferField;
    }

    public Buffer buffer() {
        return this.bufferField;
    }

    public long read(Buffer sink, long byteCount) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        if (!(byteCount >= 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append("byteCount < 0: ");
            sb.append(byteCount);
            throw new IllegalArgumentException(sb.toString().toString());
        } else if (!(true ^ this.closed)) {
            throw new IllegalStateException("closed".toString());
        } else if (this.bufferField.size() == 0 && this.source.read(this.bufferField, (long) 8192) == -1) {
            return -1;
        } else {
            return this.bufferField.read(sink, Math.min(byteCount, this.bufferField.size()));
        }
    }

    public boolean exhausted() {
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        } else if (!this.bufferField.exhausted() || this.source.read(this.bufferField, (long) 8192) != -1) {
            return false;
        } else {
            return true;
        }
    }

    public void require(long byteCount) {
        if (!request(byteCount)) {
            throw new EOFException();
        }
    }

    public boolean request(long byteCount) {
        if (!(byteCount >= 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append("byteCount < 0: ");
            sb.append(byteCount);
            throw new IllegalArgumentException(sb.toString().toString());
        } else if (!this.closed) {
            while (this.bufferField.size() < byteCount) {
                if (this.source.read(this.bufferField, (long) 8192) == -1) {
                    return false;
                }
            }
            return true;
        } else {
            throw new IllegalStateException("closed".toString());
        }
    }

    public byte readByte() {
        require(1);
        return this.bufferField.readByte();
    }

    public ByteString readByteString() {
        this.bufferField.writeAll(this.source);
        return this.bufferField.readByteString();
    }

    public ByteString readByteString(long byteCount) {
        require(byteCount);
        return this.bufferField.readByteString(byteCount);
    }

    public int select(Options options) {
        Intrinsics.checkParameterIsNotNull(options, "options");
        if (!this.closed) {
            do {
                int index = this.bufferField.selectPrefix$jvm(options, true);
                if (index != -2) {
                    if (index == -1) {
                        return -1;
                    }
                    this.bufferField.skip((long) options.getByteStrings$jvm()[index].size());
                    return index;
                }
            } while (this.source.read(this.bufferField, (long) 8192) != -1);
            return -1;
        }
        throw new IllegalStateException("closed".toString());
    }

    public byte[] readByteArray() {
        this.bufferField.writeAll(this.source);
        return this.bufferField.readByteArray();
    }

    public byte[] readByteArray(long byteCount) {
        require(byteCount);
        return this.bufferField.readByteArray(byteCount);
    }

    public int read(byte[] sink) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        return read(sink, 0, sink.length);
    }

    public void readFully(byte[] sink) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        try {
            require((long) sink.length);
            this.bufferField.readFully(sink);
        } catch (EOFException e) {
            int offset = 0;
            while (this.bufferField.size() > 0) {
                int read = this.bufferField.read(sink, offset, (int) this.bufferField.size());
                if (read != -1) {
                    offset += read;
                } else {
                    throw new AssertionError();
                }
            }
            throw e;
        }
    }

    public int read(byte[] sink, int offset, int byteCount) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        Util.checkOffsetAndCount((long) sink.length, (long) offset, (long) byteCount);
        if (this.bufferField.size() == 0 && this.source.read(this.bufferField, (long) 8192) == -1) {
            return -1;
        }
        return this.bufferField.read(sink, offset, (int) Math.min((long) byteCount, this.bufferField.size()));
    }

    public int read(ByteBuffer sink) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        if (this.bufferField.size() == 0 && this.source.read(this.bufferField, (long) 8192) == -1) {
            return -1;
        }
        return this.bufferField.read(sink);
    }

    public void readFully(Buffer sink, long byteCount) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        try {
            require(byteCount);
            this.bufferField.readFully(sink, byteCount);
        } catch (EOFException e) {
            sink.writeAll(this.bufferField);
            throw e;
        }
    }

    public long readAll(Sink sink) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        long totalBytesWritten = 0;
        while (this.source.read(this.bufferField, (long) 8192) != -1) {
            long emitByteCount = this.bufferField.completeSegmentByteCount();
            if (emitByteCount > 0) {
                totalBytesWritten += emitByteCount;
                sink.write(this.bufferField, emitByteCount);
            }
        }
        if (this.bufferField.size() <= 0) {
            return totalBytesWritten;
        }
        long totalBytesWritten2 = totalBytesWritten + this.bufferField.size();
        sink.write(this.bufferField, this.bufferField.size());
        return totalBytesWritten2;
    }

    public String readUtf8() {
        this.bufferField.writeAll(this.source);
        return this.bufferField.readUtf8();
    }

    public String readUtf8(long byteCount) {
        require(byteCount);
        return this.bufferField.readUtf8(byteCount);
    }

    public String readString(Charset charset) {
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        this.bufferField.writeAll(this.source);
        return this.bufferField.readString(charset);
    }

    public String readString(long byteCount, Charset charset) {
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        require(byteCount);
        return this.bufferField.readString(byteCount, charset);
    }

    public String readUtf8Line() {
        long newline = indexOf((byte) 10);
        if (newline != -1) {
            return this.bufferField.readUtf8Line$jvm(newline);
        }
        if (this.bufferField.size() != 0) {
            return readUtf8(this.bufferField.size());
        }
        return null;
    }

    public String readUtf8LineStrict() {
        return readUtf8LineStrict(LongCompanionObject.MAX_VALUE);
    }

    public String readUtf8LineStrict(long limit) {
        long j = limit;
        if (j >= 0) {
            long scanLength = j == LongCompanionObject.MAX_VALUE ? Long.MAX_VALUE : j + 1;
            byte b = (byte) 10;
            byte b2 = b;
            long newline = indexOf(b, 0, scanLength);
            if (newline != -1) {
                return this.bufferField.readUtf8Line$jvm(newline);
            }
            if (scanLength < LongCompanionObject.MAX_VALUE && request(scanLength) && this.bufferField.getByte(scanLength - 1) == ((byte) 13) && request(1 + scanLength) && this.bufferField.getByte(scanLength) == b2) {
                return this.bufferField.readUtf8Line$jvm(scanLength);
            }
            Buffer data = new Buffer();
            this.bufferField.copyTo(data, 0, Math.min((long) 32, this.bufferField.size()));
            StringBuilder sb = new StringBuilder();
            sb.append("\\n not found: limit=");
            sb.append(Math.min(this.bufferField.size(), j));
            sb.append(" content=");
            sb.append(data.readByteString().hex());
            sb.append("…");
            throw new EOFException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("limit < 0: ");
        sb2.append(j);
        throw new IllegalArgumentException(sb2.toString().toString());
    }

    public int readUtf8CodePoint() {
        require(1);
        int b0 = this.bufferField.getByte(0);
        if ((b0 & 224) == 192) {
            require(2);
        } else if ((b0 & 240) == 224) {
            require(3);
        } else if ((b0 & 248) == 240) {
            require(4);
        }
        return this.bufferField.readUtf8CodePoint();
    }

    public short readShort() {
        require(2);
        return this.bufferField.readShort();
    }

    public short readShortLe() {
        require(2);
        return this.bufferField.readShortLe();
    }

    public int readInt() {
        require(4);
        return this.bufferField.readInt();
    }

    public int readIntLe() {
        require(4);
        return this.bufferField.readIntLe();
    }

    public long readLong() {
        require(8);
        return this.bufferField.readLong();
    }

    public long readLongLe() {
        require(8);
        return this.bufferField.readLongLe();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        if (r2 == 0) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0035, code lost:
        r1 = kotlin.jvm.internal.StringCompanionObject.INSTANCE;
        r1 = new java.lang.Object[]{java.lang.Byte.valueOf(r6)};
        r1 = java.lang.String.format("Expected leading [0-9] or '-' character but was %#x", java.util.Arrays.copyOf(r1, r1.length));
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, "java.lang.String.format(format, *args)");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0057, code lost:
        throw new java.lang.NumberFormatException(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long readDecimalLong() {
        /*
            r10 = this;
            r0 = 1
            r10.require(r0)
            r2 = 0
            r4 = 0
            r5 = 0
        L_0x0009:
            long r6 = r2 + r0
            boolean r6 = r10.request(r6)
            if (r6 == 0) goto L_0x0058
            r6 = r10
            okio.Buffer r6 = r6.bufferField
            byte r6 = r6.getByte(r2)
            r7 = 48
            byte r7 = (byte) r7
            if (r6 < r7) goto L_0x0022
            r7 = 57
            byte r7 = (byte) r7
            if (r6 <= r7) goto L_0x002e
        L_0x0022:
            r7 = 0
            int r9 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x0030
            r9 = 45
            byte r9 = (byte) r9
            if (r6 == r9) goto L_0x002e
            goto L_0x0030
        L_0x002e:
            long r2 = r2 + r0
            goto L_0x0009
        L_0x0030:
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0035
            goto L_0x0058
        L_0x0035:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            kotlin.jvm.internal.StringCompanionObject r1 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.Byte r5 = java.lang.Byte.valueOf(r6)
            r1[r4] = r5
            int r4 = r1.length
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r4)
            java.lang.String r4 = "Expected leading [0-9] or '-' character but was %#x"
            java.lang.String r1 = java.lang.String.format(r4, r1)
            java.lang.String r4 = "java.lang.String.format(format, *args)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0058:
            r0 = r10
            r1 = r4
            okio.Buffer r0 = r0.bufferField
            long r0 = r0.readDecimalLong()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.RealBufferedSource.readDecimalLong():long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0042  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long readHexadecimalUnsignedLong() {
        /*
            r6 = this;
            r0 = 1
            r6.require(r0)
            r0 = 0
            r1 = 0
            r2 = 0
        L_0x0008:
            int r3 = r0 + 1
            long r3 = (long) r3
            boolean r3 = r6.request(r3)
            if (r3 == 0) goto L_0x0065
            r3 = r6
            okio.Buffer r3 = r3.bufferField
            long r4 = (long) r0
            byte r3 = r3.getByte(r4)
            r4 = 48
            byte r4 = (byte) r4
            if (r3 < r4) goto L_0x0026
            r4 = 57
            byte r4 = (byte) r4
            if (r3 <= r4) goto L_0x003b
        L_0x0026:
            r4 = 97
            byte r4 = (byte) r4
            if (r3 < r4) goto L_0x0030
            r4 = 102(0x66, float:1.43E-43)
            byte r4 = (byte) r4
            if (r3 <= r4) goto L_0x003b
        L_0x0030:
            r4 = 65
            byte r4 = (byte) r4
            if (r3 < r4) goto L_0x003f
            r4 = 70
            byte r4 = (byte) r4
            if (r3 <= r4) goto L_0x003b
            goto L_0x003f
        L_0x003b:
            int r0 = r0 + 1
            goto L_0x0008
        L_0x003f:
            if (r0 == 0) goto L_0x0042
            goto L_0x0065
        L_0x0042:
            java.lang.NumberFormatException r2 = new java.lang.NumberFormatException
            kotlin.jvm.internal.StringCompanionObject r4 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.Byte r5 = java.lang.Byte.valueOf(r3)
            r4[r1] = r5
            int r1 = r4.length
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r4, r1)
            java.lang.String r4 = "Expected leading [0-9a-fA-F] character but was %#x"
            java.lang.String r1 = java.lang.String.format(r4, r1)
            java.lang.String r4 = "java.lang.String.format(format, *args)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
            r2.<init>(r1)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        L_0x0065:
            r2 = r6
            okio.Buffer r1 = r2.bufferField
            long r1 = r1.readHexadecimalUnsignedLong()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.RealBufferedSource.readHexadecimalUnsignedLong():long");
    }

    public void skip(long byteCount) {
        long byteCount2 = byteCount;
        if (!this.closed) {
            while (byteCount2 > 0) {
                if (this.bufferField.size() == 0 && this.source.read(this.bufferField, (long) 8192) == -1) {
                    throw new EOFException();
                }
                long toSkip = Math.min(byteCount2, this.bufferField.size());
                this.bufferField.skip(toSkip);
                byteCount2 -= toSkip;
            }
            return;
        }
        throw new IllegalStateException("closed".toString());
    }

    public long indexOf(byte b) {
        return indexOf(b, 0, LongCompanionObject.MAX_VALUE);
    }

    public long indexOf(byte b, long fromIndex) {
        return indexOf(b, fromIndex, LongCompanionObject.MAX_VALUE);
    }

    public long indexOf(byte b, long fromIndex, long toIndex) {
        RealBufferedSource realBufferedSource = this;
        long j = toIndex;
        long fromIndex2 = fromIndex;
        boolean z = true;
        boolean z2 = false;
        if (!realBufferedSource.closed) {
            if (0 > fromIndex2 || j < fromIndex2) {
                z = false;
            }
            if (z) {
                long fromIndex3 = fromIndex2;
                boolean z3 = false;
                while (fromIndex3 < j) {
                    boolean z4 = z2;
                    long result = this.bufferField.indexOf(b, fromIndex3, toIndex);
                    if (result != -1) {
                        return result;
                    }
                    boolean z5 = z3;
                    long lastBufferSize = this.bufferField.size();
                    if (lastBufferSize < j) {
                        long j2 = result;
                        if (realBufferedSource.source.read(this.bufferField, (long) 8192) != -1) {
                            fromIndex3 = Math.max(fromIndex3, lastBufferSize);
                            realBufferedSource = this;
                            z3 = z5;
                            z2 = z4;
                        }
                    } else {
                        long j3 = result;
                    }
                    return -1;
                }
                return -1;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("fromIndex=");
            sb.append(fromIndex2);
            sb.append(" toIndex=");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString().toString());
        }
        throw new IllegalStateException("closed".toString());
    }

    public long indexOf(ByteString bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        return indexOf(bytes, 0);
    }

    public long indexOf(ByteString bytes, long fromIndex) {
        ByteString byteString = bytes;
        Intrinsics.checkParameterIsNotNull(byteString, "bytes");
        long fromIndex2 = fromIndex;
        if (!this.closed) {
            while (true) {
                long result = this.bufferField.indexOf(byteString, fromIndex2);
                if (result != -1) {
                    return result;
                }
                long lastBufferSize = this.bufferField.size();
                if (this.source.read(this.bufferField, (long) 8192) == -1) {
                    return -1;
                }
                fromIndex2 = Math.max(fromIndex2, (lastBufferSize - ((long) bytes.size())) + 1);
            }
        } else {
            throw new IllegalStateException("closed".toString());
        }
    }

    public long indexOfElement(ByteString targetBytes) {
        Intrinsics.checkParameterIsNotNull(targetBytes, "targetBytes");
        return indexOfElement(targetBytes, 0);
    }

    public long indexOfElement(ByteString targetBytes, long fromIndex) {
        ByteString byteString = targetBytes;
        Intrinsics.checkParameterIsNotNull(byteString, "targetBytes");
        long fromIndex2 = fromIndex;
        if (!this.closed) {
            while (true) {
                long result = this.bufferField.indexOfElement(byteString, fromIndex2);
                if (result != -1) {
                    return result;
                }
                long lastBufferSize = this.bufferField.size();
                if (this.source.read(this.bufferField, (long) 8192) == -1) {
                    return -1;
                }
                fromIndex2 = Math.max(fromIndex2, lastBufferSize);
            }
        } else {
            throw new IllegalStateException("closed".toString());
        }
    }

    public boolean rangeEquals(long offset, ByteString bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        return rangeEquals(offset, bytes, 0, bytes.size());
    }

    public boolean rangeEquals(long offset, ByteString bytes, int bytesOffset, int byteCount) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        } else if (offset < 0 || bytesOffset < 0 || byteCount < 0 || bytes.size() - bytesOffset < byteCount) {
            return false;
        } else {
            for (int i = 0; i < byteCount; i++) {
                long bufferOffset = ((long) i) + offset;
                if (!request(1 + bufferOffset) || this.bufferField.getByte(bufferOffset) != bytes.getByte(bytesOffset + i)) {
                    return false;
                }
            }
            return true;
        }
    }

    public BufferedSource peek() {
        return Okio.buffer((Source) new PeekSource(this));
    }

    public InputStream inputStream() {
        return new RealBufferedSource$inputStream$1(this);
    }

    public boolean isOpen() {
        return !this.closed;
    }

    public void close() {
        if (!this.closed) {
            this.closed = true;
            this.source.close();
            this.bufferField.clear();
        }
    }

    public Timeout timeout() {
        return this.source.timeout();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("buffer(");
        sb.append(this.source);
        sb.append(')');
        return sb.toString();
    }
}
