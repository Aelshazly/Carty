package kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001fB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u000fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0012J\u001b\u0010\u001b\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u0013\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020\rHÖ\u0001J\u0013\u0010%\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b&\u0010\u0005J\u0013\u0010'\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0005J\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b*\u0010\u000fJ\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b+\u0010\u0012J\u001b\u0010)\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u001fJ\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b-\u0010\u0018J\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b/\u0010\u000bJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u000fJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u0012J\u001b\u00100\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u001fJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u0018J\u001b\u00105\u001a\u0002062\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b7\u00108J\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b:\u0010\u000fJ\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b;\u0010\u0012J\u001b\u00109\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b<\u0010\u001fJ\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b=\u0010\u0018J\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u000fJ\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u0012J\u001b\u0010>\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u001fJ\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u0018J\u0010\u0010C\u001a\u00020\u0003H\b¢\u0006\u0004\bD\u0010\u0005J\u0010\u0010E\u001a\u00020FH\b¢\u0006\u0004\bG\u0010HJ\u0010\u0010I\u001a\u00020JH\b¢\u0006\u0004\bK\u0010LJ\u0010\u0010M\u001a\u00020\rH\b¢\u0006\u0004\bN\u0010OJ\u0010\u0010P\u001a\u00020QH\b¢\u0006\u0004\bR\u0010SJ\u0010\u0010T\u001a\u00020UH\b¢\u0006\u0004\bV\u0010WJ\u000f\u0010X\u001a\u00020YH\u0016¢\u0006\u0004\bZ\u0010[J\u0013\u0010\\\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b]\u0010\u0005J\u0013\u0010^\u001a\u00020\u0010H\bø\u0001\u0000¢\u0006\u0004\b_\u0010OJ\u0013\u0010`\u001a\u00020\u0013H\bø\u0001\u0000¢\u0006\u0004\ba\u0010SJ\u0013\u0010b\u001a\u00020\u0016H\bø\u0001\u0000¢\u0006\u0004\bc\u0010WJ\u001b\u0010d\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\be\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006g"}, mo24952d2 = {"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "data$annotations", "()V", "and", "other", "and-7apg3OU", "(BB)B", "compareTo", "", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "dec", "dec-impl", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(BJ)J", "div-xj2QHRw", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-7apg3OU", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "toByte-impl", "toDouble", "", "toDouble-impl", "(B)D", "toFloat", "", "toFloat-impl", "(B)F", "toInt", "toInt-impl", "(B)I", "toLong", "", "toLong-impl", "(B)J", "toShort", "", "toShort-impl", "(B)S", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-7apg3OU", "Companion", "kotlin-stdlib"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: UByte.kt */
public final class UByte implements Comparable<UByte> {
    public static final Companion Companion = new Companion(null);
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, mo24952d2 = {"Lkotlin/UByte$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UByte;", "B", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: UByte.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    /* renamed from: box-impl reason: not valid java name */
    public static final /* synthetic */ UByte m1278boximpl(byte b) {
        return new UByte(b);
    }

    /* renamed from: compareTo-7apg3OU reason: not valid java name */
    private int m1279compareTo7apg3OU(byte b) {
        return m1280compareTo7apg3OU(this.data, b);
    }

    public static /* synthetic */ void data$annotations() {
    }

    /* renamed from: equals-impl reason: not valid java name */
    public static boolean m1290equalsimpl(byte b, Object obj) {
        if (obj instanceof UByte) {
            if (b == ((UByte) obj).m1325unboximpl()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: equals-impl0 reason: not valid java name */
    public static final boolean m1291equalsimpl0(byte b, byte b2) {
        throw null;
    }

    /* renamed from: hashCode-impl reason: not valid java name */
    public static int m1292hashCodeimpl(byte b) {
        return b;
    }

    public boolean equals(Object obj) {
        return m1290equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m1292hashCodeimpl(this.data);
    }

    public String toString() {
        return m1319toStringimpl(this.data);
    }

    /* renamed from: unbox-impl reason: not valid java name */
    public final /* synthetic */ byte m1325unboximpl() {
        return this.data;
    }

    private /* synthetic */ UByte(byte data2) {
        this.data = data2;
    }

    /* renamed from: constructor-impl reason: not valid java name */
    public static byte m1284constructorimpl(byte data2) {
        return data2;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return m1279compareTo7apg3OU(((UByte) obj).m1325unboximpl());
    }

    /* renamed from: compareTo-7apg3OU reason: not valid java name */
    private static int m1280compareTo7apg3OU(byte $this, byte other) {
        return Intrinsics.compare((int) $this & MAX_VALUE, (int) other & MAX_VALUE);
    }

    /* renamed from: compareTo-xj2QHRw reason: not valid java name */
    private static final int m1283compareToxj2QHRw(byte $this, short other) {
        return Intrinsics.compare((int) $this & MAX_VALUE, (int) 65535 & other);
    }

    /* renamed from: compareTo-WZ4Q5Ns reason: not valid java name */
    private static final int m1282compareToWZ4Q5Ns(byte $this, int other) {
        return UnsignedKt.uintCompare(UInt.m1351constructorimpl($this & MAX_VALUE), other);
    }

    /* renamed from: compareTo-VKZWuLQ reason: not valid java name */
    private static final int m1281compareToVKZWuLQ(byte $this, long other) {
        return UnsignedKt.ulongCompare(ULong.m1420constructorimpl(((long) $this) & 255), other);
    }

    /* renamed from: plus-7apg3OU reason: not valid java name */
    private static final int m1300plus7apg3OU(byte $this, byte other) {
        return UInt.m1351constructorimpl(UInt.m1351constructorimpl($this & MAX_VALUE) + UInt.m1351constructorimpl(other & MAX_VALUE));
    }

    /* renamed from: plus-xj2QHRw reason: not valid java name */
    private static final int m1303plusxj2QHRw(byte $this, short other) {
        return UInt.m1351constructorimpl(UInt.m1351constructorimpl($this & MAX_VALUE) + UInt.m1351constructorimpl(65535 & other));
    }

    /* renamed from: plus-WZ4Q5Ns reason: not valid java name */
    private static final int m1302plusWZ4Q5Ns(byte $this, int other) {
        return UInt.m1351constructorimpl(UInt.m1351constructorimpl($this & MAX_VALUE) + other);
    }

    /* renamed from: plus-VKZWuLQ reason: not valid java name */
    private static final long m1301plusVKZWuLQ(byte $this, long other) {
        return ULong.m1420constructorimpl(ULong.m1420constructorimpl(((long) $this) & 255) + other);
    }

    /* renamed from: minus-7apg3OU reason: not valid java name */
    private static final int m1295minus7apg3OU(byte $this, byte other) {
        return UInt.m1351constructorimpl(UInt.m1351constructorimpl($this & MAX_VALUE) - UInt.m1351constructorimpl(other & MAX_VALUE));
    }

    /* renamed from: minus-xj2QHRw reason: not valid java name */
    private static final int m1298minusxj2QHRw(byte $this, short other) {
        return UInt.m1351constructorimpl(UInt.m1351constructorimpl($this & MAX_VALUE) - UInt.m1351constructorimpl(65535 & other));
    }

    /* renamed from: minus-WZ4Q5Ns reason: not valid java name */
    private static final int m1297minusWZ4Q5Ns(byte $this, int other) {
        return UInt.m1351constructorimpl(UInt.m1351constructorimpl($this & MAX_VALUE) - other);
    }

    /* renamed from: minus-VKZWuLQ reason: not valid java name */
    private static final long m1296minusVKZWuLQ(byte $this, long other) {
        return ULong.m1420constructorimpl(ULong.m1420constructorimpl(((long) $this) & 255) - other);
    }

    /* renamed from: times-7apg3OU reason: not valid java name */
    private static final int m1309times7apg3OU(byte $this, byte other) {
        return UInt.m1351constructorimpl(UInt.m1351constructorimpl($this & MAX_VALUE) * UInt.m1351constructorimpl(other & MAX_VALUE));
    }

    /* renamed from: times-xj2QHRw reason: not valid java name */
    private static final int m1312timesxj2QHRw(byte $this, short other) {
        return UInt.m1351constructorimpl(UInt.m1351constructorimpl($this & MAX_VALUE) * UInt.m1351constructorimpl(65535 & other));
    }

    /* renamed from: times-WZ4Q5Ns reason: not valid java name */
    private static final int m1311timesWZ4Q5Ns(byte $this, int other) {
        return UInt.m1351constructorimpl(UInt.m1351constructorimpl($this & MAX_VALUE) * other);
    }

    /* renamed from: times-VKZWuLQ reason: not valid java name */
    private static final long m1310timesVKZWuLQ(byte $this, long other) {
        return ULong.m1420constructorimpl(ULong.m1420constructorimpl(((long) $this) & 255) * other);
    }

    /* renamed from: div-7apg3OU reason: not valid java name */
    private static final int m1286div7apg3OU(byte $this, byte other) {
        return UnsignedKt.m1577uintDivideJ1ME1BU(UInt.m1351constructorimpl($this & MAX_VALUE), UInt.m1351constructorimpl(other & MAX_VALUE));
    }

    /* renamed from: div-xj2QHRw reason: not valid java name */
    private static final int m1289divxj2QHRw(byte $this, short other) {
        return UnsignedKt.m1577uintDivideJ1ME1BU(UInt.m1351constructorimpl($this & MAX_VALUE), UInt.m1351constructorimpl(65535 & other));
    }

    /* renamed from: div-WZ4Q5Ns reason: not valid java name */
    private static final int m1288divWZ4Q5Ns(byte $this, int other) {
        return UnsignedKt.m1577uintDivideJ1ME1BU(UInt.m1351constructorimpl($this & MAX_VALUE), other);
    }

    /* renamed from: div-VKZWuLQ reason: not valid java name */
    private static final long m1287divVKZWuLQ(byte $this, long other) {
        return UnsignedKt.m1579ulongDivideeb3DHEI(ULong.m1420constructorimpl(((long) $this) & 255), other);
    }

    /* renamed from: rem-7apg3OU reason: not valid java name */
    private static final int m1305rem7apg3OU(byte $this, byte other) {
        return UnsignedKt.m1578uintRemainderJ1ME1BU(UInt.m1351constructorimpl($this & MAX_VALUE), UInt.m1351constructorimpl(other & MAX_VALUE));
    }

    /* renamed from: rem-xj2QHRw reason: not valid java name */
    private static final int m1308remxj2QHRw(byte $this, short other) {
        return UnsignedKt.m1578uintRemainderJ1ME1BU(UInt.m1351constructorimpl($this & MAX_VALUE), UInt.m1351constructorimpl(65535 & other));
    }

    /* renamed from: rem-WZ4Q5Ns reason: not valid java name */
    private static final int m1307remWZ4Q5Ns(byte $this, int other) {
        return UnsignedKt.m1578uintRemainderJ1ME1BU(UInt.m1351constructorimpl($this & MAX_VALUE), other);
    }

    /* renamed from: rem-VKZWuLQ reason: not valid java name */
    private static final long m1306remVKZWuLQ(byte $this, long other) {
        return UnsignedKt.m1580ulongRemaindereb3DHEI(ULong.m1420constructorimpl(((long) $this) & 255), other);
    }

    /* renamed from: inc-impl reason: not valid java name */
    private static final byte m1293incimpl(byte $this) {
        return m1284constructorimpl((byte) ($this + 1));
    }

    /* renamed from: dec-impl reason: not valid java name */
    private static final byte m1285decimpl(byte $this) {
        return m1284constructorimpl((byte) ($this - 1));
    }

    /* renamed from: rangeTo-7apg3OU reason: not valid java name */
    private static final UIntRange m1304rangeTo7apg3OU(byte $this, byte other) {
        return new UIntRange(UInt.m1351constructorimpl($this & MAX_VALUE), UInt.m1351constructorimpl(other & MAX_VALUE), null);
    }

    /* renamed from: and-7apg3OU reason: not valid java name */
    private static final byte m1277and7apg3OU(byte $this, byte other) {
        return m1284constructorimpl((byte) ($this & other));
    }

    /* renamed from: or-7apg3OU reason: not valid java name */
    private static final byte m1299or7apg3OU(byte $this, byte other) {
        return m1284constructorimpl((byte) ($this | other));
    }

    /* renamed from: xor-7apg3OU reason: not valid java name */
    private static final byte m1324xor7apg3OU(byte $this, byte other) {
        return m1284constructorimpl((byte) ($this ^ other));
    }

    /* renamed from: inv-impl reason: not valid java name */
    private static final byte m1294invimpl(byte $this) {
        return m1284constructorimpl((byte) (~$this));
    }

    /* renamed from: toByte-impl reason: not valid java name */
    private static final byte m1313toByteimpl(byte $this) {
        return $this;
    }

    /* renamed from: toShort-impl reason: not valid java name */
    private static final short m1318toShortimpl(byte $this) {
        return (short) (((short) $this) & 255);
    }

    /* renamed from: toInt-impl reason: not valid java name */
    private static final int m1316toIntimpl(byte $this) {
        return $this & MAX_VALUE;
    }

    /* renamed from: toLong-impl reason: not valid java name */
    private static final long m1317toLongimpl(byte $this) {
        return ((long) $this) & 255;
    }

    /* renamed from: toUByte-impl reason: not valid java name */
    private static final byte m1320toUByteimpl(byte $this) {
        return $this;
    }

    /* renamed from: toUShort-impl reason: not valid java name */
    private static final short m1323toUShortimpl(byte $this) {
        return UShort.m1517constructorimpl((short) (((short) $this) & 255));
    }

    /* renamed from: toUInt-impl reason: not valid java name */
    private static final int m1321toUIntimpl(byte $this) {
        return UInt.m1351constructorimpl($this & MAX_VALUE);
    }

    /* renamed from: toULong-impl reason: not valid java name */
    private static final long m1322toULongimpl(byte $this) {
        return ULong.m1420constructorimpl(((long) $this) & 255);
    }

    /* renamed from: toFloat-impl reason: not valid java name */
    private static final float m1315toFloatimpl(byte $this) {
        return (float) ($this & MAX_VALUE);
    }

    /* renamed from: toDouble-impl reason: not valid java name */
    private static final double m1314toDoubleimpl(byte $this) {
        return (double) ($this & MAX_VALUE);
    }

    /* renamed from: toString-impl reason: not valid java name */
    public static String m1319toStringimpl(byte $this) {
        return String.valueOf($this & MAX_VALUE);
    }
}
