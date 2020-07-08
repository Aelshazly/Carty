package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u0005\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 J2\u00020\u0001:\u0001JB\u001d\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\u0015\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0011H\u0010¢\u0006\u0002\b\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002JV\u0010\u001a\u001a\u00020\u001b2K\u0010\u001c\u001aG\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u00110!¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110!¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u001b0\u001dH\bJf\u0010\u001a\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020!2K\u0010\u001c\u001aG\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u00110!¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110!¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u001b0\u001dH\bJ\r\u0010&\u001a\u00020!H\u0010¢\u0006\u0002\b'J\b\u0010(\u001a\u00020!H\u0016J\b\u0010)\u001a\u00020\u0011H\u0016J\u001d\u0010*\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u0001H\u0010¢\u0006\u0002\b,J\u0018\u0010-\u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010.\u001a\u00020!H\u0016J\r\u0010/\u001a\u00020\u0004H\u0010¢\u0006\u0002\b0J\u0015\u00101\u001a\u0002022\u0006\u00103\u001a\u00020!H\u0010¢\u0006\u0002\b4J\u0018\u00105\u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010.\u001a\u00020!H\u0016J(\u00106\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u00107\u001a\u00020!2\u0006\u0010#\u001a\u00020!H\u0016J(\u00106\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u00107\u001a\u00020!2\u0006\u0010#\u001a\u00020!H\u0016J\u0010\u00108\u001a\u00020!2\u0006\u00103\u001a\u00020!H\u0002J\u0010\u00109\u001a\u00020\u00112\u0006\u0010:\u001a\u00020;H\u0016J\u0018\u0010<\u001a\u00020\u00012\u0006\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020!H\u0016J\b\u0010=\u001a\u00020\u0001H\u0016J\b\u0010>\u001a\u00020\u0001H\u0016J\b\u0010?\u001a\u00020\u0004H\u0016J\b\u0010@\u001a\u00020\u0001H\u0002J\b\u0010A\u001a\u00020\u0011H\u0016J\u0010\u0010B\u001a\u00020\u001b2\u0006\u0010C\u001a\u00020DH\u0016J\u0015\u0010B\u001a\u00020\u001b2\u0006\u0010E\u001a\u00020FH\u0010¢\u0006\u0002\bGJ\b\u0010H\u001a\u00020IH\u0002R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006K"}, mo24952d2 = {"Lokio/SegmentedByteString;", "Lokio/ByteString;", "segments", "", "", "directory", "", "([[B[I)V", "getDirectory", "()[I", "getSegments", "()[[B", "[[B", "asByteBuffer", "Ljava/nio/ByteBuffer;", "kotlin.jvm.PlatformType", "base64", "", "base64Url", "digest", "algorithm", "digest$jvm", "equals", "", "other", "", "forEachSegment", "", "action", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "data", "", "offset", "byteCount", "beginIndex", "endIndex", "getSize", "getSize$jvm", "hashCode", "hex", "hmac", "key", "hmac$jvm", "indexOf", "fromIndex", "internalArray", "internalArray$jvm", "internalGet", "", "pos", "internalGet$jvm", "lastIndexOf", "rangeEquals", "otherOffset", "segment", "string", "charset", "Ljava/nio/charset/Charset;", "substring", "toAsciiLowercase", "toAsciiUppercase", "toByteArray", "toByteString", "toString", "write", "out", "Ljava/io/OutputStream;", "buffer", "Lokio/Buffer;", "write$jvm", "writeReplace", "Ljava/lang/Object;", "Companion", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
/* compiled from: SegmentedByteString.kt */
public final class SegmentedByteString extends ByteString {
    public static final Companion Companion = new Companion(null);
    private final transient int[] directory;
    private final transient byte[][] segments;

    @Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, mo24952d2 = {"Lokio/SegmentedByteString$Companion;", "", "()V", "of", "Lokio/ByteString;", "buffer", "Lokio/Buffer;", "byteCount", "", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
    /* compiled from: SegmentedByteString.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* renamed from: of */
        public final ByteString mo27989of(Buffer buffer, int byteCount) {
            Intrinsics.checkParameterIsNotNull(buffer, "buffer");
            Util.checkOffsetAndCount(buffer.size(), 0, (long) byteCount);
            int offset = 0;
            int segmentCount = 0;
            Segment s = buffer.head;
            while (offset < byteCount) {
                if (s == null) {
                    Intrinsics.throwNpe();
                }
                if (s.limit != s.pos) {
                    offset += s.limit - s.pos;
                    segmentCount++;
                    s = s.next;
                } else {
                    throw new AssertionError("s.limit == s.pos");
                }
            }
            byte[][] segments = new byte[segmentCount][];
            int[] directory = new int[(segmentCount * 2)];
            int offset2 = 0;
            int segmentCount2 = 0;
            Segment s2 = buffer.head;
            while (offset2 < byteCount) {
                if (s2 == null) {
                    Intrinsics.throwNpe();
                }
                segments[segmentCount2] = s2.data;
                offset2 += s2.limit - s2.pos;
                directory[segmentCount2] = Math.min(offset2, byteCount);
                directory[((Object[]) segments).length + segmentCount2] = s2.pos;
                s2.shared = true;
                segmentCount2++;
                s2 = s2.next;
            }
            return new SegmentedByteString(segments, directory, null);
        }
    }

    private SegmentedByteString(byte[][] segments2, int[] directory2) {
        super(ByteString.EMPTY.getData$jvm());
        this.segments = segments2;
        this.directory = directory2;
    }

    public /* synthetic */ SegmentedByteString(byte[][] segments2, int[] directory2, DefaultConstructorMarker $constructor_marker) {
        this(segments2, directory2);
    }

    public final byte[][] getSegments() {
        return this.segments;
    }

    public final int[] getDirectory() {
        return this.directory;
    }

    public String string(Charset charset) {
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return toByteString().string(charset);
    }

    public String base64() {
        return toByteString().base64();
    }

    public String hex() {
        return toByteString().hex();
    }

    public ByteString toAsciiLowercase() {
        return toByteString().toAsciiLowercase();
    }

    public ByteString toAsciiUppercase() {
        return toByteString().toAsciiUppercase();
    }

    public ByteString digest$jvm(String algorithm) {
        Intrinsics.checkParameterIsNotNull(algorithm, "algorithm");
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        int segmentCount$iv = ((Object[]) getSegments()).length;
        int pos$iv = 0;
        for (int s$iv = 0; s$iv < segmentCount$iv; s$iv++) {
            int segmentPos$iv = getDirectory()[segmentCount$iv + s$iv];
            int nextSegmentOffset$iv = getDirectory()[s$iv];
            digest.update(getSegments()[s$iv], segmentPos$iv, nextSegmentOffset$iv - pos$iv);
            pos$iv = nextSegmentOffset$iv;
        }
        byte[] digest2 = digest.digest();
        Intrinsics.checkExpressionValueIsNotNull(digest2, "digest.digest()");
        return new ByteString(digest2);
    }

    public ByteString hmac$jvm(String algorithm, ByteString key) {
        Intrinsics.checkParameterIsNotNull(algorithm, "algorithm");
        Intrinsics.checkParameterIsNotNull(key, "key");
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
            int segmentCount$iv = ((Object[]) getSegments()).length;
            int pos$iv = 0;
            for (int s$iv = 0; s$iv < segmentCount$iv; s$iv++) {
                int segmentPos$iv = getDirectory()[segmentCount$iv + s$iv];
                int nextSegmentOffset$iv = getDirectory()[s$iv];
                mac.update(getSegments()[s$iv], segmentPos$iv, nextSegmentOffset$iv - pos$iv);
                pos$iv = nextSegmentOffset$iv;
            }
            byte[] doFinal = mac.doFinal();
            Intrinsics.checkExpressionValueIsNotNull(doFinal, "mac.doFinal()");
            return new ByteString(doFinal);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String base64Url() {
        return toByteString().base64Url();
    }

    public ByteString substring(int beginIndex, int endIndex) {
        int index;
        int segmentOffset = 0;
        boolean z = true;
        if (beginIndex >= 0) {
            String str = "endIndex=";
            if (endIndex <= size()) {
                int subLen = endIndex - beginIndex;
                if (subLen < 0) {
                    z = false;
                }
                if (!z) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(endIndex);
                    sb.append(" < beginIndex=");
                    sb.append(beginIndex);
                    throw new IllegalArgumentException(sb.toString().toString());
                } else if (beginIndex == 0 && endIndex == size()) {
                    return this;
                } else {
                    if (beginIndex == endIndex) {
                        return ByteString.EMPTY;
                    }
                    int beginSegment = segment(beginIndex);
                    int endSegment = segment(endIndex - 1);
                    Object[] copyOfRange = Arrays.copyOfRange((Object[]) this.segments, beginSegment, endSegment + 1);
                    Intrinsics.checkExpressionValueIsNotNull(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
                    byte[][] newSegments = (byte[][]) copyOfRange;
                    int[] newDirectory = new int[(((Object[]) newSegments).length * 2)];
                    int index2 = 0;
                    if (beginSegment <= endSegment) {
                        int s = beginSegment;
                        while (true) {
                            newDirectory[index2] = Math.min(this.directory[s] - beginIndex, subLen);
                            index = index2 + 1;
                            newDirectory[index2 + ((Object[]) newSegments).length] = this.directory[((Object[]) this.segments).length + s];
                            if (s == endSegment) {
                                break;
                            }
                            s++;
                            index2 = index;
                        }
                        int i = index;
                    }
                    if (beginSegment != 0) {
                        segmentOffset = this.directory[beginSegment - 1];
                    }
                    int length = ((Object[]) newSegments).length;
                    newDirectory[length] = newDirectory[length] + (beginIndex - segmentOffset);
                    return new SegmentedByteString(newSegments, newDirectory);
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(endIndex);
                sb2.append(" > length(");
                sb2.append(size());
                sb2.append(')');
                throw new IllegalArgumentException(sb2.toString().toString());
            }
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("beginIndex=");
            sb3.append(beginIndex);
            sb3.append(" < 0");
            throw new IllegalArgumentException(sb3.toString().toString());
        }
    }

    public byte internalGet$jvm(int pos) {
        Util.checkOffsetAndCount((long) this.directory[((Object[]) this.segments).length - 1], (long) pos, 1);
        int segment = segment(pos);
        int segmentOffset = segment == 0 ? 0 : this.directory[segment - 1];
        int[] iArr = this.directory;
        byte[][] bArr = this.segments;
        return bArr[segment][(pos - segmentOffset) + iArr[((Object[]) bArr).length + segment]];
    }

    /* access modifiers changed from: private */
    public final int segment(int pos) {
        int i = Arrays.binarySearch(this.directory, 0, ((Object[]) this.segments).length, pos + 1);
        return i >= 0 ? i : ~i;
    }

    public int getSize$jvm() {
        return this.directory[((Object[]) this.segments).length - 1];
    }

    public byte[] toByteArray() {
        byte[] result = new byte[size()];
        int resultPos = 0;
        int segmentCount$iv = ((Object[]) getSegments()).length;
        int pos$iv = 0;
        for (int s$iv = 0; s$iv < segmentCount$iv; s$iv++) {
            int segmentPos$iv = getDirectory()[segmentCount$iv + s$iv];
            int nextSegmentOffset$iv = getDirectory()[s$iv];
            int byteCount = nextSegmentOffset$iv - pos$iv;
            Platform.arraycopy(getSegments()[s$iv], segmentPos$iv, result, resultPos, byteCount);
            resultPos += byteCount;
            pos$iv = nextSegmentOffset$iv;
        }
        return result;
    }

    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    public void write(OutputStream out) throws IOException {
        Intrinsics.checkParameterIsNotNull(out, "out");
        int segmentCount$iv = ((Object[]) getSegments()).length;
        int pos$iv = 0;
        for (int s$iv = 0; s$iv < segmentCount$iv; s$iv++) {
            int segmentPos$iv = getDirectory()[segmentCount$iv + s$iv];
            int nextSegmentOffset$iv = getDirectory()[s$iv];
            out.write(getSegments()[s$iv], segmentPos$iv, nextSegmentOffset$iv - pos$iv);
            pos$iv = nextSegmentOffset$iv;
        }
    }

    public void write$jvm(Buffer buffer) {
        Buffer buffer2 = buffer;
        Intrinsics.checkParameterIsNotNull(buffer2, "buffer");
        int segmentCount$iv = ((Object[]) getSegments()).length;
        int pos$iv = 0;
        for (int s$iv = 0; s$iv < segmentCount$iv; s$iv++) {
            int segmentPos$iv = getDirectory()[segmentCount$iv + s$iv];
            int nextSegmentOffset$iv = getDirectory()[s$iv];
            int offset = segmentPos$iv;
            Segment segment = new Segment(getSegments()[s$iv], offset, offset + (nextSegmentOffset$iv - pos$iv), true, false);
            if (buffer2.head == null) {
                segment.prev = segment;
                segment.next = segment.prev;
                buffer2.head = segment.next;
            } else {
                Segment segment2 = buffer2.head;
                if (segment2 == null) {
                    Intrinsics.throwNpe();
                }
                Segment segment3 = segment2.prev;
                if (segment3 == null) {
                    Intrinsics.throwNpe();
                }
                segment3.push(segment);
            }
            pos$iv = nextSegmentOffset$iv;
        }
        buffer2.setSize$jvm(buffer.size() + ((long) size()));
    }

    public boolean rangeEquals(int offset, ByteString other, int otherOffset, int byteCount) {
        int i = offset;
        ByteString byteString = other;
        Intrinsics.checkParameterIsNotNull(byteString, "other");
        if (i < 0 || i > size() - byteCount) {
            return false;
        }
        int otherOffset2 = otherOffset;
        int endIndex$iv = i + byteCount;
        int s$iv = segment(i);
        int pos$iv = offset;
        while (pos$iv < endIndex$iv) {
            int segmentOffset$iv = s$iv == 0 ? 0 : getDirectory()[s$iv - 1];
            int byteCount$iv = Math.min(endIndex$iv, segmentOffset$iv + (getDirectory()[s$iv] - segmentOffset$iv)) - pos$iv;
            int byteCount2 = byteCount$iv;
            if (byteString.rangeEquals(otherOffset2, getSegments()[s$iv], (pos$iv - segmentOffset$iv) + getDirectory()[((Object[]) getSegments()).length + s$iv], byteCount2) == 0) {
                return false;
            }
            otherOffset2 += byteCount2;
            pos$iv += byteCount$iv;
            s$iv++;
            int i2 = offset;
        }
        return true;
    }

    public boolean rangeEquals(int offset, byte[] other, int otherOffset, int byteCount) {
        int i = offset;
        byte[] bArr = other;
        int i2 = otherOffset;
        Intrinsics.checkParameterIsNotNull(bArr, "other");
        if (i < 0 || i > size() - byteCount || i2 < 0 || i2 > bArr.length - byteCount) {
            return false;
        }
        int otherOffset2 = otherOffset;
        int endIndex$iv = i + byteCount;
        int s$iv = segment(i);
        int pos$iv = offset;
        while (pos$iv < endIndex$iv) {
            int segmentOffset$iv = s$iv == 0 ? 0 : getDirectory()[s$iv - 1];
            int byteCount$iv = Math.min(endIndex$iv, segmentOffset$iv + (getDirectory()[s$iv] - segmentOffset$iv)) - pos$iv;
            int byteCount2 = byteCount$iv;
            if (Util.arrayRangeEquals(getSegments()[s$iv], (pos$iv - segmentOffset$iv) + getDirectory()[((Object[]) getSegments()).length + s$iv], bArr, otherOffset2, byteCount2) == 0) {
                return false;
            }
            otherOffset2 += byteCount2;
            pos$iv += byteCount$iv;
            s$iv++;
            int i3 = offset;
            int i4 = otherOffset;
        }
        return true;
    }

    public int indexOf(byte[] other, int fromIndex) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return toByteString().indexOf(other, fromIndex);
    }

    public int lastIndexOf(byte[] other, int fromIndex) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return toByteString().lastIndexOf(other, fromIndex);
    }

    private final ByteString toByteString() {
        return new ByteString(toByteArray());
    }

    public byte[] internalArray$jvm() {
        return toByteArray();
    }

    private final void forEachSegment(Function3<? super byte[], ? super Integer, ? super Integer, Unit> action) {
        int segmentCount = ((Object[]) getSegments()).length;
        int pos = 0;
        for (int s = 0; s < segmentCount; s++) {
            int segmentPos = getDirectory()[segmentCount + s];
            int nextSegmentOffset = getDirectory()[s];
            action.invoke(getSegments()[s], Integer.valueOf(segmentPos), Integer.valueOf(nextSegmentOffset - pos));
            pos = nextSegmentOffset;
        }
    }

    private final void forEachSegment(int beginIndex, int endIndex, Function3<? super byte[], ? super Integer, ? super Integer, Unit> action) {
        int s = segment(beginIndex);
        int pos = beginIndex;
        while (pos < endIndex) {
            int segmentOffset = s == 0 ? 0 : getDirectory()[s - 1];
            int byteCount = Math.min(endIndex, segmentOffset + (getDirectory()[s] - segmentOffset)) - pos;
            action.invoke(getSegments()[s], Integer.valueOf((pos - segmentOffset) + getDirectory()[((Object[]) getSegments()).length + s]), Integer.valueOf(byteCount));
            pos += byteCount;
            s++;
        }
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ByteString)) {
            return false;
        }
        if (((ByteString) other).size() != size() || !rangeEquals(0, (ByteString) other, 0, size())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = getHashCode$jvm();
        if (result != 0) {
            return result;
        }
        int result2 = 1;
        int segmentCount$iv = ((Object[]) getSegments()).length;
        int pos$iv = 0;
        for (int s$iv = 0; s$iv < segmentCount$iv; s$iv++) {
            int segmentPos$iv = getDirectory()[segmentCount$iv + s$iv];
            int nextSegmentOffset$iv = getDirectory()[s$iv];
            byte[] data = getSegments()[s$iv];
            int offset = segmentPos$iv;
            for (int i = offset; i < offset + (nextSegmentOffset$iv - pos$iv); i++) {
                result2 = (result2 * 31) + data[i];
            }
            pos$iv = nextSegmentOffset$iv;
        }
        setHashCode$jvm(result2);
        return result2;
    }

    public String toString() {
        return toByteString().toString();
    }

    private final Object writeReplace() {
        ByteString byteString = toByteString();
        if (byteString != null) {
            return byteString;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
    }
}
