package kotlin.random;

import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.ULongRange;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\"\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0001ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001c\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u001e\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a2\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0014\u0010\u0018\u001a\u00020\u0003*\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u001e\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a&\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001c\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001fH\u0007ø\u0001\u0000¢\u0006\u0002\u0010 \u001a\u0014\u0010!\u001a\u00020\b*\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\"\u001a\u001e\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0004\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a&\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a\u001c\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u001e\u001a\u00020'H\u0007ø\u0001\u0000¢\u0006\u0002\u0010(\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, mo24952d2 = {"checkUIntRangeBounds", "", "from", "Lkotlin/UInt;", "until", "checkUIntRangeBounds-J1ME1BU", "(II)V", "checkULongRangeBounds", "Lkotlin/ULong;", "checkULongRangeBounds-eb3DHEI", "(JJ)V", "nextUBytes", "Lkotlin/UByteArray;", "Lkotlin/random/Random;", "size", "", "(Lkotlin/random/Random;I)[B", "array", "nextUBytes-EVgfTAA", "(Lkotlin/random/Random;[B)[B", "fromIndex", "toIndex", "nextUBytes-Wvrt4B4", "(Lkotlin/random/Random;[BII)[B", "nextUInt", "(Lkotlin/random/Random;)I", "nextUInt-qCasIEU", "(Lkotlin/random/Random;I)I", "nextUInt-a8DCA5k", "(Lkotlin/random/Random;II)I", "range", "Lkotlin/ranges/UIntRange;", "(Lkotlin/random/Random;Lkotlin/ranges/UIntRange;)I", "nextULong", "(Lkotlin/random/Random;)J", "nextULong-V1Xi4fY", "(Lkotlin/random/Random;J)J", "nextULong-jmpaW-c", "(Lkotlin/random/Random;JJ)J", "Lkotlin/ranges/ULongRange;", "(Lkotlin/random/Random;Lkotlin/ranges/ULongRange;)J", "kotlin-stdlib"}, mo24953k = 2, mo24954mv = {1, 1, 15})
/* compiled from: URandom.kt */
public final class URandomKt {
    public static final int nextUInt(Random $this$nextUInt) {
        Intrinsics.checkParameterIsNotNull($this$nextUInt, "$this$nextUInt");
        return UInt.m1351constructorimpl($this$nextUInt.nextInt());
    }

    /* renamed from: nextUInt-qCasIEU reason: not valid java name */
    public static final int m2139nextUIntqCasIEU(Random $this$nextUInt, int until) {
        Intrinsics.checkParameterIsNotNull($this$nextUInt, "$this$nextUInt");
        return m2138nextUInta8DCA5k($this$nextUInt, 0, until);
    }

    /* renamed from: nextUInt-a8DCA5k reason: not valid java name */
    public static final int m2138nextUInta8DCA5k(Random $this$nextUInt, int from, int until) {
        Intrinsics.checkParameterIsNotNull($this$nextUInt, "$this$nextUInt");
        m2133checkUIntRangeBoundsJ1ME1BU(from, until);
        return UInt.m1351constructorimpl(Integer.MIN_VALUE ^ $this$nextUInt.nextInt(from ^ Integer.MIN_VALUE, until ^ Integer.MIN_VALUE));
    }

    public static final int nextUInt(Random $this$nextUInt, UIntRange range) {
        Intrinsics.checkParameterIsNotNull($this$nextUInt, "$this$nextUInt");
        Intrinsics.checkParameterIsNotNull(range, "range");
        if (range.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot get random in empty range: ");
            sb.append(range);
            throw new IllegalArgumentException(sb.toString());
        } else if (UnsignedKt.uintCompare(range.getLast(), -1) < 0) {
            return m2138nextUInta8DCA5k($this$nextUInt, range.getFirst(), UInt.m1351constructorimpl(range.getLast() + 1));
        } else {
            if (UnsignedKt.uintCompare(range.getFirst(), 0) > 0) {
                return UInt.m1351constructorimpl(m2138nextUInta8DCA5k($this$nextUInt, UInt.m1351constructorimpl(range.getFirst() - 1), range.getLast()) + 1);
            }
            return nextUInt($this$nextUInt);
        }
    }

    public static final long nextULong(Random $this$nextULong) {
        Intrinsics.checkParameterIsNotNull($this$nextULong, "$this$nextULong");
        return ULong.m1420constructorimpl($this$nextULong.nextLong());
    }

    /* renamed from: nextULong-V1Xi4fY reason: not valid java name */
    public static final long m2140nextULongV1Xi4fY(Random $this$nextULong, long until) {
        Intrinsics.checkParameterIsNotNull($this$nextULong, "$this$nextULong");
        return m2141nextULongjmpaWc($this$nextULong, 0, until);
    }

    /* renamed from: nextULong-jmpaW-c reason: not valid java name */
    public static final long m2141nextULongjmpaWc(Random $this$nextULong, long from, long until) {
        Intrinsics.checkParameterIsNotNull($this$nextULong, "$this$nextULong");
        m2134checkULongRangeBoundseb3DHEI(from, until);
        return ULong.m1420constructorimpl(Long.MIN_VALUE ^ $this$nextULong.nextLong(from ^ Long.MIN_VALUE, until ^ Long.MIN_VALUE));
    }

    public static final long nextULong(Random $this$nextULong, ULongRange range) {
        Intrinsics.checkParameterIsNotNull($this$nextULong, "$this$nextULong");
        Intrinsics.checkParameterIsNotNull(range, "range");
        if (range.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot get random in empty range: ");
            sb.append(range);
            throw new IllegalArgumentException(sb.toString());
        } else if (UnsignedKt.ulongCompare(range.getLast(), -1) < 0) {
            return m2141nextULongjmpaWc($this$nextULong, range.getFirst(), ULong.m1420constructorimpl(range.getLast() + ULong.m1420constructorimpl(((long) 1) & 4294967295L)));
        } else {
            if (UnsignedKt.ulongCompare(range.getFirst(), 0) <= 0) {
                return nextULong($this$nextULong);
            }
            long j = ((long) 1) & 4294967295L;
            return ULong.m1420constructorimpl(m2141nextULongjmpaWc($this$nextULong, ULong.m1420constructorimpl(range.getFirst() - ULong.m1420constructorimpl(j)), range.getLast()) + ULong.m1420constructorimpl(j));
        }
    }

    /* renamed from: nextUBytes-EVgfTAA reason: not valid java name */
    public static final byte[] m2135nextUBytesEVgfTAA(Random $this$nextUBytes, byte[] array) {
        Intrinsics.checkParameterIsNotNull($this$nextUBytes, "$this$nextUBytes");
        Intrinsics.checkParameterIsNotNull(array, "array");
        $this$nextUBytes.nextBytes(array);
        return array;
    }

    public static final byte[] nextUBytes(Random $this$nextUBytes, int size) {
        Intrinsics.checkParameterIsNotNull($this$nextUBytes, "$this$nextUBytes");
        return UByteArray.m1328constructorimpl($this$nextUBytes.nextBytes(size));
    }

    /* renamed from: nextUBytes-Wvrt4B4$default reason: not valid java name */
    public static /* synthetic */ byte[] m2137nextUBytesWvrt4B4$default(Random random, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UByteArray.m1334getSizeimpl(bArr);
        }
        return m2136nextUBytesWvrt4B4(random, bArr, i, i2);
    }

    /* renamed from: nextUBytes-Wvrt4B4 reason: not valid java name */
    public static final byte[] m2136nextUBytesWvrt4B4(Random $this$nextUBytes, byte[] array, int fromIndex, int toIndex) {
        Intrinsics.checkParameterIsNotNull($this$nextUBytes, "$this$nextUBytes");
        Intrinsics.checkParameterIsNotNull(array, "array");
        $this$nextUBytes.nextBytes(array, fromIndex, toIndex);
        return array;
    }

    /* renamed from: checkUIntRangeBounds-J1ME1BU reason: not valid java name */
    public static final void m2133checkUIntRangeBoundsJ1ME1BU(int from, int until) {
        if (!(UnsignedKt.uintCompare(until, from) > 0)) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(UInt.m1345boximpl(from), UInt.m1345boximpl(until)).toString());
        }
    }

    /* renamed from: checkULongRangeBounds-eb3DHEI reason: not valid java name */
    public static final void m2134checkULongRangeBoundseb3DHEI(long from, long until) {
        if (!(UnsignedKt.ulongCompare(until, from) > 0)) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(ULong.m1414boximpl(from), ULong.m1414boximpl(until)).toString());
        }
    }
}
