package okio;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0016J\b\u0010\u0015\u001a\u00020\u000eH\u0002J\b\u0010\u0016\u001a\u00020\u000eH\u0002J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0018H\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J \u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0018H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, mo24952d2 = {"Lokio/GzipSource;", "Lokio/Source;", "source", "(Lokio/Source;)V", "crc", "Ljava/util/zip/CRC32;", "inflater", "Ljava/util/zip/Inflater;", "inflaterSource", "Lokio/InflaterSource;", "section", "", "Lokio/RealBufferedSource;", "checkEqual", "", "name", "", "expected", "", "actual", "close", "consumeHeader", "consumeTrailer", "read", "", "sink", "Lokio/Buffer;", "byteCount", "timeout", "Lokio/Timeout;", "updateCrc", "buffer", "offset", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
/* compiled from: GzipSource.kt */
public final class GzipSource implements Source {
    private final CRC32 crc = new CRC32();
    private final Inflater inflater = new Inflater(true);
    private final InflaterSource inflaterSource = new InflaterSource((BufferedSource) this.source, this.inflater);
    private byte section;
    private final RealBufferedSource source;

    public GzipSource(Source source2) {
        Intrinsics.checkParameterIsNotNull(source2, Param.SOURCE);
        this.source = new RealBufferedSource(source2);
    }

    public long read(Buffer sink, long byteCount) throws IOException {
        Buffer buffer = sink;
        long j = byteCount;
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        if (!(j >= 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append("byteCount < 0: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString().toString());
        } else if (j == 0) {
            return 0;
        } else {
            if (this.section == 0) {
                consumeHeader();
                this.section = 1;
            }
            if (this.section == 1) {
                long offset = sink.size();
                long result = this.inflaterSource.read(sink, j);
                if (result != -1) {
                    updateCrc(sink, offset, result);
                    return result;
                }
                this.section = 2;
            }
            if (this.section == 2) {
                consumeTrailer();
                this.section = 3;
                if (!this.source.exhausted()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1;
        }
    }

    private final void consumeHeader() throws IOException {
        boolean z;
        this.source.require(10);
        int i = this.source.bufferField.getByte(3);
        boolean z2 = true;
        boolean fhcrc = ((i >> 1) & 1) == 1;
        if (fhcrc) {
            updateCrc(this.source.bufferField, 0, 10);
        }
        checkEqual("ID1ID2", 8075, this.source.readShort());
        this.source.skip(8);
        if ((((i >> 2) & 1) == 1 ? 1 : 0) != 0) {
            this.source.require(2);
            if (fhcrc) {
                updateCrc(this.source.bufferField, 0, 2);
            }
            long xlen = (long) this.source.bufferField.readShortLe();
            this.source.require(xlen);
            if (fhcrc) {
                z = false;
                updateCrc(this.source.bufferField, 0, xlen);
            } else {
                z = false;
            }
            this.source.skip(xlen);
        } else {
            z = false;
        }
        if ((((i >> 3) & 1) == 1 ? 1 : 0) != 0) {
            long index = this.source.indexOf(0);
            if (index != -1) {
                if (fhcrc) {
                    updateCrc(this.source.bufferField, 0, index + 1);
                }
                this.source.skip(index + 1);
            } else {
                throw new EOFException();
            }
        }
        if (((i >> 4) & 1) != 1) {
            z2 = false;
        }
        if (z2) {
            long index2 = this.source.indexOf(0);
            if (index2 != -1) {
                if (fhcrc) {
                    boolean z3 = z;
                    updateCrc(this.source.bufferField, 0, index2 + 1);
                }
                this.source.skip(index2 + 1);
            } else {
                throw new EOFException();
            }
        }
        if (fhcrc) {
            checkEqual("FHCRC", this.source.readShortLe(), (short) ((int) this.crc.getValue()));
            this.crc.reset();
        }
    }

    private final void consumeTrailer() throws IOException {
        checkEqual("CRC", this.source.readIntLe(), (int) this.crc.getValue());
        checkEqual("ISIZE", this.source.readIntLe(), (int) this.inflater.getBytesWritten());
    }

    public Timeout timeout() {
        return this.source.timeout();
    }

    public void close() throws IOException {
        this.inflaterSource.close();
    }

    private final void updateCrc(Buffer buffer, long offset, long byteCount) {
        long offset2 = offset;
        long byteCount2 = byteCount;
        Segment s = buffer.head;
        if (s == null) {
            Intrinsics.throwNpe();
        }
        while (offset2 >= ((long) (s.limit - s.pos))) {
            offset2 -= (long) (s.limit - s.pos);
            Segment segment = s.next;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            s = segment;
        }
        while (byteCount2 > 0) {
            int pos = (int) (((long) s.pos) + offset2);
            int toUpdate = (int) Math.min((long) (s.limit - pos), byteCount2);
            this.crc.update(s.data, pos, toUpdate);
            byteCount2 -= (long) toUpdate;
            offset2 = 0;
            Segment segment2 = s.next;
            if (segment2 == null) {
                Intrinsics.throwNpe();
            }
            s = segment2;
        }
    }

    private final void checkEqual(String name, int expected, int actual) {
        if (actual != expected) {
            Object[] objArr = {name, Integer.valueOf(actual), Integer.valueOf(expected)};
            String format = String.format("%s: actual 0x%08x != expected 0x%08x", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
            throw new IOException(format);
        }
    }
}
