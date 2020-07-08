package okio;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u00004\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0003\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0000\u001a \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\fH\u0000\u001a\u0019\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\fH\b\u001a\u0019\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u0005H\b\u001a\u0015\u0010\u000f\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\f\u001a\u0015\u0010\u000f\u001a\u00020\f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fH\f\u001a\u0015\u0010\u000f\u001a\u00020\f*\u00020\u00052\u0006\u0010\u0011\u001a\u00020\fH\f\u001a\f\u0010\u0012\u001a\u00020\u0005*\u00020\u0005H\u0000\u001a\f\u0010\u0012\u001a\u00020\f*\u00020\fH\u0000\u001a\f\u0010\u0012\u001a\u00020\u0013*\u00020\u0013H\u0000\u001a\u0015\u0010\u0014\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\f\u001a\u0015\u0010\u0015\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\f¨\u0006\u0016"}, mo24952d2 = {"arrayRangeEquals", "", "a", "", "aOffset", "", "b", "bOffset", "byteCount", "checkOffsetAndCount", "", "size", "", "offset", "minOf", "and", "", "other", "reverseBytes", "", "shl", "shr", "jvm"}, mo24953k = 2, mo24954mv = {1, 1, 11})
/* renamed from: okio.-Util reason: invalid class name */
/* compiled from: -Util.kt */
public final class Util {
    public static final void checkOffsetAndCount(long size, long offset, long byteCount) {
        if ((offset | byteCount) < 0 || offset > size || size - offset < byteCount) {
            StringBuilder sb = new StringBuilder();
            sb.append("size=");
            sb.append(size);
            sb.append(" offset=");
            sb.append(offset);
            sb.append(" byteCount=");
            sb.append(byteCount);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
    }

    public static final short reverseBytes(short $receiver) {
        int i = 65535 & $receiver;
        return (short) (((65280 & i) >>> 8) | ((i & 255) << 8));
    }

    public static final int reverseBytes(int $receiver) {
        return ((-16777216 & $receiver) >>> 24) | ((16711680 & $receiver) >>> 8) | ((65280 & $receiver) << 8) | (($receiver & 255) << 24);
    }

    public static final long reverseBytes(long $receiver) {
        return ((-72057594037927936L & $receiver) >>> 56) | ((71776119061217280L & $receiver) >>> 40) | ((280375465082880L & $receiver) >>> 24) | ((1095216660480L & $receiver) >>> 8) | ((4278190080L & $receiver) << 8) | ((16711680 & $receiver) << 24) | ((65280 & $receiver) << 40) | ((255 & $receiver) << 56);
    }

    public static final int shr(byte $receiver, int other) {
        return $receiver >> other;
    }

    public static final int shl(byte $receiver, int other) {
        return $receiver << other;
    }

    public static final int and(byte $receiver, int other) {
        return $receiver & other;
    }

    public static final long and(byte $receiver, long other) {
        return ((long) $receiver) & other;
    }

    public static final long and(int $receiver, long other) {
        return ((long) $receiver) & other;
    }

    public static final long minOf(long a, int b) {
        return Math.min(a, (long) b);
    }

    public static final long minOf(int a, long b) {
        return Math.min((long) a, b);
    }

    public static final boolean arrayRangeEquals(byte[] a, int aOffset, byte[] b, int bOffset, int byteCount) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        for (int i = 0; i < byteCount; i++) {
            if (a[i + aOffset] != b[i + bOffset]) {
                return false;
            }
        }
        return true;
    }
}
