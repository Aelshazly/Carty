package okio;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0000\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B/\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u0004\u0018\u00010\u0000J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0000J\u0006\u0010\u0013\u001a\u00020\u0000J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0006J\u0006\u0010\u0016\u001a\u00020\u0000J\u0016\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u0004\u0018\u00010\u00008\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u0004\u0018\u00010\u00008\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, mo24952d2 = {"Lokio/Segment;", "", "()V", "data", "", "pos", "", "limit", "shared", "", "owner", "([BIIZZ)V", "next", "prev", "compact", "", "pop", "push", "segment", "sharedCopy", "split", "byteCount", "unsharedCopy", "writeTo", "sink", "Companion", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
/* compiled from: Segment.kt */
public final class Segment {
    public static final Companion Companion = new Companion(null);
    public static final int SHARE_MINIMUM = 1024;
    public static final int SIZE = 8192;
    public final byte[] data;
    public int limit;
    public Segment next;
    public boolean owner;
    public int pos;
    public Segment prev;
    public boolean shared;

    @Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, mo24952d2 = {"Lokio/Segment$Companion;", "", "()V", "SHARE_MINIMUM", "", "SIZE", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
    /* compiled from: Segment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    public Segment() {
        this.data = new byte[8192];
        this.owner = true;
        this.shared = false;
    }

    public Segment(byte[] data2, int pos2, int limit2, boolean shared2, boolean owner2) {
        Intrinsics.checkParameterIsNotNull(data2, "data");
        this.data = data2;
        this.pos = pos2;
        this.limit = limit2;
        this.shared = shared2;
        this.owner = owner2;
    }

    public final Segment sharedCopy() {
        this.shared = true;
        Segment segment = new Segment(this.data, this.pos, this.limit, true, false);
        return segment;
    }

    public final Segment unsharedCopy() {
        byte[] bArr = this.data;
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        Segment segment = new Segment(copyOf, this.pos, this.limit, false, true);
        return segment;
    }

    public final Segment pop() {
        Segment result = this.next;
        if (result == this) {
            result = null;
        }
        Segment segment = this.prev;
        if (segment == null) {
            Intrinsics.throwNpe();
        }
        segment.next = this.next;
        Segment segment2 = this.next;
        if (segment2 == null) {
            Intrinsics.throwNpe();
        }
        segment2.prev = this.prev;
        Segment segment3 = null;
        this.next = segment3;
        this.prev = segment3;
        return result;
    }

    public final Segment push(Segment segment) {
        Intrinsics.checkParameterIsNotNull(segment, "segment");
        segment.prev = this;
        segment.next = this.next;
        Segment segment2 = this.next;
        if (segment2 == null) {
            Intrinsics.throwNpe();
        }
        segment2.prev = segment;
        this.next = segment;
        return segment;
    }

    public final Segment split(int byteCount) {
        Segment prefix;
        if (byteCount > 0 && byteCount <= this.limit - this.pos) {
            if (byteCount >= 1024) {
                prefix = sharedCopy();
            } else {
                Segment prefix2 = SegmentPool.take();
                Platform.arraycopy(this.data, this.pos, prefix2.data, 0, byteCount);
                prefix = prefix2;
            }
            prefix.limit = prefix.pos + byteCount;
            this.pos += byteCount;
            Segment segment = this.prev;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            segment.push(prefix);
            return prefix;
        }
        throw new IllegalArgumentException("byteCount out of range".toString());
    }

    public final void compact() {
        int i = 0;
        if (this.prev != this) {
            Segment segment = this.prev;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            if (segment.owner) {
                int byteCount = this.limit - this.pos;
                Segment segment2 = this.prev;
                if (segment2 == null) {
                    Intrinsics.throwNpe();
                }
                int i2 = 8192 - segment2.limit;
                Segment segment3 = this.prev;
                if (segment3 == null) {
                    Intrinsics.throwNpe();
                }
                if (!segment3.shared) {
                    Segment segment4 = this.prev;
                    if (segment4 == null) {
                        Intrinsics.throwNpe();
                    }
                    i = segment4.pos;
                }
                if (byteCount <= i2 + i) {
                    Segment segment5 = this.prev;
                    if (segment5 == null) {
                        Intrinsics.throwNpe();
                    }
                    writeTo(segment5, byteCount);
                    pop();
                    SegmentPool.recycle(this);
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalStateException("cannot compact".toString());
    }

    public final void writeTo(Segment sink, int byteCount) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        if (sink.owner) {
            int i = sink.limit;
            if (i + byteCount > 8192) {
                if (!sink.shared) {
                    int i2 = i + byteCount;
                    int i3 = sink.pos;
                    if (i2 - i3 <= 8192) {
                        byte[] bArr = sink.data;
                        Platform.arraycopy(bArr, i3, bArr, 0, i - i3);
                        sink.limit -= sink.pos;
                        sink.pos = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            Platform.arraycopy(this.data, this.pos, sink.data, sink.limit, byteCount);
            sink.limit += byteCount;
            this.pos += byteCount;
            return;
        }
        throw new IllegalStateException("only owner can write".toString());
    }
}
