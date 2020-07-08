package kotlin;

import kotlin.ranges.ULongRange;
import okhttp3.internal.p017ws.WebSocketProtocol;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 m2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001mB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b \u0010\u000bJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010\t\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\rHÖ\u0001J\u0013\u0010'\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b(\u0010\u0005J\u0013\u0010)\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u0005J\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u001dJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b-\u0010\u001fJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b.\u0010\u000bJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b/\u0010\"J\u001b\u00100\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b1\u0010\u000bJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u001dJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u001fJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u000bJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b6\u0010\"J\u001b\u00107\u001a\u0002082\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b<\u0010\u001dJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b=\u0010\u001fJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b>\u0010\u000bJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b?\u0010\"J\u001b\u0010@\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\rH\fø\u0001\u0000¢\u0006\u0004\bB\u0010\u001fJ\u001b\u0010C\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\rH\fø\u0001\u0000¢\u0006\u0004\bD\u0010\u001fJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\bF\u0010\u001dJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\bG\u0010\u001fJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\bH\u0010\u000bJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\bI\u0010\"J\u0010\u0010J\u001a\u00020KH\b¢\u0006\u0004\bL\u0010MJ\u0010\u0010N\u001a\u00020OH\b¢\u0006\u0004\bP\u0010QJ\u0010\u0010R\u001a\u00020SH\b¢\u0006\u0004\bT\u0010UJ\u0010\u0010V\u001a\u00020\rH\b¢\u0006\u0004\bW\u0010XJ\u0010\u0010Y\u001a\u00020\u0003H\b¢\u0006\u0004\bZ\u0010\u0005J\u0010\u0010[\u001a\u00020\\H\b¢\u0006\u0004\b]\u0010^J\u000f\u0010_\u001a\u00020`H\u0016¢\u0006\u0004\ba\u0010bJ\u0013\u0010c\u001a\u00020\u000eH\bø\u0001\u0000¢\u0006\u0004\bd\u0010MJ\u0013\u0010e\u001a\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\bf\u0010XJ\u0013\u0010g\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\bh\u0010\u0005J\u0013\u0010i\u001a\u00020\u0016H\bø\u0001\u0000¢\u0006\u0004\bj\u0010^J\u001b\u0010k\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\bl\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006n"}, mo24952d2 = {"Lkotlin/ULong;", "", "data", "", "constructor-impl", "(J)J", "data$annotations", "()V", "and", "other", "and-VKZWuLQ", "(JJ)J", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(JB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "dec", "dec-impl", "div", "div-7apg3OU", "(JB)J", "div-WZ4Q5Ns", "(JI)J", "div-VKZWuLQ", "div-xj2QHRw", "(JS)J", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-VKZWuLQ", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-impl", "shr", "shr-impl", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(J)B", "toDouble", "", "toDouble-impl", "(J)D", "toFloat", "", "toFloat-impl", "(J)F", "toInt", "toInt-impl", "(J)I", "toLong", "toLong-impl", "toShort", "", "toShort-impl", "(J)S", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-VKZWuLQ", "Companion", "kotlin-stdlib"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: ULong.kt */
public final class ULong implements Comparable<ULong> {
    public static final Companion Companion = new Companion(null);
    public static final long MAX_VALUE = -1;
    public static final long MIN_VALUE = 0;
    public static final int SIZE_BITS = 64;
    public static final int SIZE_BYTES = 8;
    private final long data;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, mo24952d2 = {"Lkotlin/ULong$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/ULong;", "J", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: ULong.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    /* renamed from: box-impl reason: not valid java name */
    public static final /* synthetic */ ULong m1414boximpl(long j) {
        return new ULong(j);
    }

    /* renamed from: compareTo-VKZWuLQ reason: not valid java name */
    private int m1416compareToVKZWuLQ(long j) {
        return m1417compareToVKZWuLQ(this.data, j);
    }

    public static /* synthetic */ void data$annotations() {
    }

    /* renamed from: equals-impl reason: not valid java name */
    public static boolean m1426equalsimpl(long j, Object obj) {
        if (obj instanceof ULong) {
            if (j == ((ULong) obj).m1463unboximpl()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: equals-impl0 reason: not valid java name */
    public static final boolean m1427equalsimpl0(long j, long j2) {
        throw null;
    }

    /* renamed from: hashCode-impl reason: not valid java name */
    public static int m1428hashCodeimpl(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public boolean equals(Object obj) {
        return m1426equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m1428hashCodeimpl(this.data);
    }

    public String toString() {
        return m1457toStringimpl(this.data);
    }

    /* renamed from: unbox-impl reason: not valid java name */
    public final /* synthetic */ long m1463unboximpl() {
        return this.data;
    }

    private /* synthetic */ ULong(long data2) {
        this.data = data2;
    }

    /* renamed from: constructor-impl reason: not valid java name */
    public static long m1420constructorimpl(long data2) {
        return data2;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return m1416compareToVKZWuLQ(((ULong) obj).m1463unboximpl());
    }

    /* renamed from: compareTo-7apg3OU reason: not valid java name */
    private static final int m1415compareTo7apg3OU(long $this, byte other) {
        return UnsignedKt.ulongCompare($this, m1420constructorimpl(((long) other) & 255));
    }

    /* renamed from: compareTo-xj2QHRw reason: not valid java name */
    private static final int m1419compareToxj2QHRw(long $this, short other) {
        return UnsignedKt.ulongCompare($this, m1420constructorimpl(((long) other) & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    /* renamed from: compareTo-WZ4Q5Ns reason: not valid java name */
    private static final int m1418compareToWZ4Q5Ns(long $this, int other) {
        return UnsignedKt.ulongCompare($this, m1420constructorimpl(((long) other) & 4294967295L));
    }

    /* renamed from: compareTo-VKZWuLQ reason: not valid java name */
    private static int m1417compareToVKZWuLQ(long $this, long other) {
        return UnsignedKt.ulongCompare($this, other);
    }

    /* renamed from: plus-7apg3OU reason: not valid java name */
    private static final long m1436plus7apg3OU(long $this, byte other) {
        return m1420constructorimpl(m1420constructorimpl(((long) other) & 255) + $this);
    }

    /* renamed from: plus-xj2QHRw reason: not valid java name */
    private static final long m1439plusxj2QHRw(long $this, short other) {
        return m1420constructorimpl(m1420constructorimpl(((long) other) & WebSocketProtocol.PAYLOAD_SHORT_MAX) + $this);
    }

    /* renamed from: plus-WZ4Q5Ns reason: not valid java name */
    private static final long m1438plusWZ4Q5Ns(long $this, int other) {
        return m1420constructorimpl(m1420constructorimpl(((long) other) & 4294967295L) + $this);
    }

    /* renamed from: plus-VKZWuLQ reason: not valid java name */
    private static final long m1437plusVKZWuLQ(long $this, long other) {
        return m1420constructorimpl($this + other);
    }

    /* renamed from: minus-7apg3OU reason: not valid java name */
    private static final long m1431minus7apg3OU(long $this, byte other) {
        return m1420constructorimpl($this - m1420constructorimpl(((long) other) & 255));
    }

    /* renamed from: minus-xj2QHRw reason: not valid java name */
    private static final long m1434minusxj2QHRw(long $this, short other) {
        return m1420constructorimpl($this - m1420constructorimpl(((long) other) & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    /* renamed from: minus-WZ4Q5Ns reason: not valid java name */
    private static final long m1433minusWZ4Q5Ns(long $this, int other) {
        return m1420constructorimpl($this - m1420constructorimpl(((long) other) & 4294967295L));
    }

    /* renamed from: minus-VKZWuLQ reason: not valid java name */
    private static final long m1432minusVKZWuLQ(long $this, long other) {
        return m1420constructorimpl($this - other);
    }

    /* renamed from: times-7apg3OU reason: not valid java name */
    private static final long m1447times7apg3OU(long $this, byte other) {
        return m1420constructorimpl(m1420constructorimpl(((long) other) & 255) * $this);
    }

    /* renamed from: times-xj2QHRw reason: not valid java name */
    private static final long m1450timesxj2QHRw(long $this, short other) {
        return m1420constructorimpl(m1420constructorimpl(((long) other) & WebSocketProtocol.PAYLOAD_SHORT_MAX) * $this);
    }

    /* renamed from: times-WZ4Q5Ns reason: not valid java name */
    private static final long m1449timesWZ4Q5Ns(long $this, int other) {
        return m1420constructorimpl(m1420constructorimpl(((long) other) & 4294967295L) * $this);
    }

    /* renamed from: times-VKZWuLQ reason: not valid java name */
    private static final long m1448timesVKZWuLQ(long $this, long other) {
        return m1420constructorimpl($this * other);
    }

    /* renamed from: div-7apg3OU reason: not valid java name */
    private static final long m1422div7apg3OU(long $this, byte other) {
        return UnsignedKt.m1579ulongDivideeb3DHEI($this, m1420constructorimpl(((long) other) & 255));
    }

    /* renamed from: div-xj2QHRw reason: not valid java name */
    private static final long m1425divxj2QHRw(long $this, short other) {
        return UnsignedKt.m1579ulongDivideeb3DHEI($this, m1420constructorimpl(((long) other) & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    /* renamed from: div-WZ4Q5Ns reason: not valid java name */
    private static final long m1424divWZ4Q5Ns(long $this, int other) {
        return UnsignedKt.m1579ulongDivideeb3DHEI($this, m1420constructorimpl(((long) other) & 4294967295L));
    }

    /* renamed from: div-VKZWuLQ reason: not valid java name */
    private static final long m1423divVKZWuLQ(long $this, long other) {
        return UnsignedKt.m1579ulongDivideeb3DHEI($this, other);
    }

    /* renamed from: rem-7apg3OU reason: not valid java name */
    private static final long m1441rem7apg3OU(long $this, byte other) {
        return UnsignedKt.m1580ulongRemaindereb3DHEI($this, m1420constructorimpl(((long) other) & 255));
    }

    /* renamed from: rem-xj2QHRw reason: not valid java name */
    private static final long m1444remxj2QHRw(long $this, short other) {
        return UnsignedKt.m1580ulongRemaindereb3DHEI($this, m1420constructorimpl(((long) other) & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    /* renamed from: rem-WZ4Q5Ns reason: not valid java name */
    private static final long m1443remWZ4Q5Ns(long $this, int other) {
        return UnsignedKt.m1580ulongRemaindereb3DHEI($this, m1420constructorimpl(((long) other) & 4294967295L));
    }

    /* renamed from: rem-VKZWuLQ reason: not valid java name */
    private static final long m1442remVKZWuLQ(long $this, long other) {
        return UnsignedKt.m1580ulongRemaindereb3DHEI($this, other);
    }

    /* renamed from: inc-impl reason: not valid java name */
    private static final long m1429incimpl(long $this) {
        return m1420constructorimpl(1 + $this);
    }

    /* renamed from: dec-impl reason: not valid java name */
    private static final long m1421decimpl(long $this) {
        return m1420constructorimpl(-1 + $this);
    }

    /* renamed from: rangeTo-VKZWuLQ reason: not valid java name */
    private static final ULongRange m1440rangeToVKZWuLQ(long $this, long other) {
        ULongRange uLongRange = new ULongRange($this, other, null);
        return uLongRange;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=long, for r5v0, types: [long, int] */
    /* renamed from: shl-impl reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final long m1445shlimpl(long r3, long r5) {
        /*
            r0 = 0
            long r1 = r3 << r5
            long r1 = m1420constructorimpl(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ULong.m1445shlimpl(long, int):long");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=long, for r5v0, types: [long, int] */
    /* renamed from: shr-impl reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final long m1446shrimpl(long r3, long r5) {
        /*
            r0 = 0
            long r1 = r3 >>> r5
            long r1 = m1420constructorimpl(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ULong.m1446shrimpl(long, int):long");
    }

    /* renamed from: and-VKZWuLQ reason: not valid java name */
    private static final long m1413andVKZWuLQ(long $this, long other) {
        return m1420constructorimpl($this & other);
    }

    /* renamed from: or-VKZWuLQ reason: not valid java name */
    private static final long m1435orVKZWuLQ(long $this, long other) {
        return m1420constructorimpl($this | other);
    }

    /* renamed from: xor-VKZWuLQ reason: not valid java name */
    private static final long m1462xorVKZWuLQ(long $this, long other) {
        return m1420constructorimpl($this ^ other);
    }

    /* renamed from: inv-impl reason: not valid java name */
    private static final long m1430invimpl(long $this) {
        return m1420constructorimpl(~$this);
    }

    /* renamed from: toByte-impl reason: not valid java name */
    private static final byte m1451toByteimpl(long $this) {
        return (byte) ((int) $this);
    }

    /* renamed from: toShort-impl reason: not valid java name */
    private static final short m1456toShortimpl(long $this) {
        return (short) ((int) $this);
    }

    /* renamed from: toInt-impl reason: not valid java name */
    private static final int m1454toIntimpl(long $this) {
        return (int) $this;
    }

    /* renamed from: toLong-impl reason: not valid java name */
    private static final long m1455toLongimpl(long $this) {
        return $this;
    }

    /* renamed from: toUByte-impl reason: not valid java name */
    private static final byte m1458toUByteimpl(long $this) {
        return UByte.m1284constructorimpl((byte) ((int) $this));
    }

    /* renamed from: toUShort-impl reason: not valid java name */
    private static final short m1461toUShortimpl(long $this) {
        return UShort.m1517constructorimpl((short) ((int) $this));
    }

    /* renamed from: toUInt-impl reason: not valid java name */
    private static final int m1459toUIntimpl(long $this) {
        return UInt.m1351constructorimpl((int) $this);
    }

    /* renamed from: toULong-impl reason: not valid java name */
    private static final long m1460toULongimpl(long $this) {
        return $this;
    }

    /* renamed from: toFloat-impl reason: not valid java name */
    private static final float m1453toFloatimpl(long $this) {
        return (float) UnsignedKt.ulongToDouble($this);
    }

    /* renamed from: toDouble-impl reason: not valid java name */
    private static final double m1452toDoubleimpl(long $this) {
        return UnsignedKt.ulongToDouble($this);
    }

    /* renamed from: toString-impl reason: not valid java name */
    public static String m1457toStringimpl(long $this) {
        return UnsignedKt.ulongToString($this);
    }
}
